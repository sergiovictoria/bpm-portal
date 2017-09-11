package br.com.seta.processo.map;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;

import br.com.seta.processo.annotations.MongoColumm;
import br.com.seta.processo.annotations.MongoEntity;
import br.com.seta.processo.connection.MongoConnection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;



public class MapObjectJsonValue<T> implements Serializable {

	private static Logger logger = Logger.getLogger(MapObjectJsonValue.class);

	private static final long serialVersionUID = 1L;
	private final Class<T> workingClass;
	private DBCollection dbCollection;


	public MapObjectJsonValue(Class<T> workingClass) {

		logger.info("Criando Entity do Mongo");
		this.workingClass = workingClass;
		String collection;
		try {
			collection = getWorkingClass().getAnnotation(MongoEntity.class).collectionName();
		}catch (Exception e) {
			collection = this.workingClass.getCanonicalName().toString(); 
		}
		dbCollection =	MongoConnection.getInstance().getDB().getCollection(collection);

	}


	public List<T> converToMap(Cursor cursor) {
		List<T> objects = new ArrayList<T>();
		try {
			logger.info("Convertendo cursor mongo ");
			String objectJson = JSON.serialize(cursor);
			objects = getObjectToObjects(objectJson,this.workingClass);
		}catch (RuntimeException e) {
			logger.error(" Ocorreu um erro converToMap ",e);
		}
		return (List<T>) objects;
	}


	public BasicDBObject converterToJsonObject(Object object) {
		BasicDBObject documents = new BasicDBObject();
		Method[] method = workingClass.getMethods();
		try {
			for (Method dto : method) {
				if(dto.isAnnotationPresent(MongoColumm.class)) {
					if ((dto.getAnnotation(MongoColumm.class).list()!=null) && (dto.getAnnotation(MongoColumm.class).list().equals("Set"))) {
						Method m = workingClass.getMethod(dto.getAnnotation(MongoColumm.class).property(), new Class[] { } );
						Set<?> retObject =  (Set<?>) m.invoke(object, new Object[] {} );
						for (Object obj : retObject) {
							boolean existeSubdocumentos = false;
							Class<? extends Object> classType = obj.getClass();
							String nameSubdocument =  classType.getAnnotation(MongoEntity.class).collectionName();
							Method[] oMethod = classType.getMethods();
							BasicDBObject subDocuments = new BasicDBObject();
							for (Method oLocal : oMethod) {
								if (oLocal.isAnnotationPresent(MongoColumm.class)) {
									String key  = oLocal.getAnnotation(MongoColumm.class).property();
									Field field = classType.getDeclaredField(key);
									field.setAccessible(true);
									Object value = field.get(obj);
									if ((key!=null) &&  (value!=null) ) {
										Type type = field.getGenericType();
										if (type == BigDecimal.class) {
											double valueCovert = ((BigDecimal) value).doubleValue();
											subDocuments.put(key, valueCovert);
											existeSubdocumentos = true;
										}else {
											subDocuments.put(key, value);
											existeSubdocumentos = true;
										}
									}
								}
							}
							if (existeSubdocumentos) {
								documents.put(nameSubdocument, subDocuments);
							}
						}
					}else {
						String key = dto.getAnnotation(MongoColumm.class).property();
						Field field = getWorkingClass().getDeclaredField(key);
						field.setAccessible(true);
						Object value = field.get(object);
						Type type = field.getGenericType();
						if ( (value!=null) && (value!=null) ) {
							if (type == BigDecimal.class ) {
								double valueCovert = ((BigDecimal) value).doubleValue();
								documents.append(key, valueCovert);
							}else{
								documents.append(key, value);	
							}
						}
					}
				}
			}
		}catch(Exception e ) {
			logger.error(" Erro ao tentar maper de objeto para formato mongo ",e);
		}
		
		return documents;
		
	}



	public List<DBObject> convertToJsonListObjects(Collection<? extends Object> objects) throws MongoException {
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		try {
			logger.info("Iniciando a conversão  [ "+getWorkingClass().getName() +" ] " );
			Gson gson= new Gson();
			for (Object dto : objects) {
				DBObject dbObject = (DBObject) JSON.parse(gson.toJson(dto));
				dbObjects.add(dbObject);
			}
		}catch (RuntimeException e) {
			throw new MongoException("Ocorreu um erro converterToJsonObject ",e);
		}
		return dbObjects;
	}


	public List<T> find(String key, Object value) throws MongoException {
		List<T> objects = new ArrayList<T>();
		try {
			logger.info(" Criando consulta mongo lista de objetos " );
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put(key, value);
			DBCursor cursor = getDbCollection().find(whereQuery);
			objects = (List<T>) converToMap(cursor);
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos ",e);
		}
		return (List<T>) objects;
	}


	public Object findOne(String key, Object value) throws MongoException {
		try {
			logger.info(" Criando consulta mongo objeto " );
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put(key, value);
			DBCursor cursor = getDbCollection().find(whereQuery);
			List<Object> objects = (List<Object>) converToMap(cursor);
			return objects.size()> 0 ? objects.get(0) : null;
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura do objeto ",e);
		}
	}

	public void save(Collection<? extends Object> objects) throws MongoException {
		try {
			List<DBObject> dbObjects = convertToJsonListObjects(objects);
			getDbCollection().insert(dbObjects);
		}catch (RuntimeException e) {
			throw new MongoException("Erro ao tentar salvar collection no mogno ",e);    
		}
	}


	public void save(Object object) throws MongoException {
		try {
			DBObject dbObject = converterToJsonObject(object);
			getDbCollection().insert(dbObject);
		}catch (RuntimeException e) {
			throw new MongoException("Erro ao tentar salvar objeto no mogno ",e);   
		}
	}


	public void update(String key, Object value, Object object) throws MongoException {
		try {
			BasicDBObject searchQuery = new BasicDBObject().append(key,value);
			getDbCollection().update(searchQuery,converterToJsonObject(object));
		}catch (Exception e) {
			throw new MongoException("Erro ao tentar fazer update de objeto ",e);    
		}
	}


	public void remove(String key, Object value) throws MongoException {
		try {
			BasicDBObject deleteQuery = new BasicDBObject().append(key,value);
			getDbCollection().remove(deleteQuery);
		}catch (Exception e) {
			throw new MongoException("Erro ao tentar fazer remover objeto ",e);    
		}
	}

	private static <T> List<T> getObjectToObjects(String json, Class<T> clazz) {
		/*****
		Type listOfObject = new TypeToken<List<T>>(){}.getType();
		TypeToken<List<T>> token = new TypeToken<List<T>>(){};
		objects = gson.fromJson(objectJson, token.getType());  Dessa forma não coverte nem fodendo  *****/
		Gson gson= new Gson();
		//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(json).getAsJsonArray();
		List<T> list = new ArrayList<T>();
		for (final JsonElement jsonElement : array) {
			T entity = gson.fromJson(jsonElement, clazz);
			list.add(entity);
		}
		return list;
	}


	public DBCollection getDbCollection() {
		return this.dbCollection;
	}


	public Class<T> getWorkingClass() {
		return workingClass;
	}



}
