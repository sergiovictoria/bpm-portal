package br.com.seta.processo.map;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import br.com.seta.processo.annotations.MongoColumm;
import br.com.seta.processo.annotations.MongoEntity;
import br.com.seta.processo.connection.MongoConnection;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;



public class MapObjectValue<T> implements Serializable {

	private static Logger logger = Logger.getLogger(MapObjectValue.class);

	private static final long serialVersionUID = 1L;
	private final Class<T> workingClass;
	private DBCollection dbCollection;
	private Object valueAttribute;


	public MapObjectValue(Class<T> workingClass) {
		logger.info("Criando Entity do Mongo");
		this.workingClass = workingClass;
		dbCollection =	MongoConnection.getInstance().getDB().getCollection(getWorkingClass().getAnnotation(MongoEntity.class).collectionName());
	}




	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> converToMap(Cursor cursor) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException, ClassNotFoundException {

		logger.info("Acessando converToMap ");
		
		List<Object>  objects = new ArrayList<Object>(0);

		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			Class<T> c = (Class<T>) Class.forName(getWorkingClass().getCanonicalName());
			Object instance = c.newInstance();
			for(Method method : getWorkingClass().getDeclaredMethods()) {
				if(method.isAnnotationPresent(MongoColumm.class)) {
					String attribute = method.getAnnotation(MongoColumm.class).property();
					if (object.containsKey(attribute)) {
						valueAttribute = object.get(attribute);
						if ( (valueAttribute!=null) && (!valueAttribute.equals("null")) && (!valueAttribute.equals(null))  ) {
							Field field = getWorkingClass().getDeclaredField(attribute);
							field.setAccessible(true);
							field.set(instance, object.get(attribute));
						}
					}
				}
			}
			objects.add(instance);
		}

		logger.info("converToMap está retornando um lista de objectos [ "+objects.size()+ " ]");
		
		return (List<T>) objects;

	}



	public BasicDBObject converterToDBObject(Object object) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException, ClassNotFoundException {
		
		logger.info(" Acessando converterToDBObject ");
		
		BasicDBObject documents = new BasicDBObject();
		for(Method method : getWorkingClass().getDeclaredMethods()) {
			if(method.isAnnotationPresent(MongoColumm.class)) {
				String attribute = method.getAnnotation(MongoColumm.class).property();
				Field field = getWorkingClass().getDeclaredField(attribute);
				field.setAccessible(true);
				if (field.get(object)!=null) 
					documents.append(attribute,field.get(object));
			}
		}
		
		logger.info("converterToDBObject está retornando um lista de objectos [ "+documents.size()+ " ]");
		return documents;
	}


	public List<DBObject> convertMapObjectList(List<Object> objects) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ParseException, ClassNotFoundException {
		
		logger.info("Acessando convertMapObjectList " );
		
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		for (Object dto : objects) {
			BasicDBObject documents = new BasicDBObject();
			for(Method method : getWorkingClass().getDeclaredMethods()) {
				if(method.isAnnotationPresent(MongoColumm.class)) {
					String attribute = method.getAnnotation(MongoColumm.class).property();
					Field field = getWorkingClass().getDeclaredField(attribute);
					field.setAccessible(true);
					documents.append(attribute,field.get(dto));
					if (field.get(dto)!=null) 
						documents.append(attribute,field.get(dto));
				}
			}
			dbObjects.add(documents);
		}
		
		logger.info("convertMapObjectList está retornando um lista de objectos [ "+dbObjects.size()+ " ]");
		return dbObjects;
		
	}

		
	public void save(List<Object> object) {
		try {
			List<DBObject> dbObjects = convertMapObjectList(object);
			getDbCollection().insert(dbObjects);
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro ao tentar incluir Objectos no Mongo - DB");    
		}
	}


	public void save(Object object) {
		try {
			DBObject dbObject = converterToDBObject(object);
			getDbCollection().insert(dbObject);
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro ao tentar incluir Objectos no Mongo - DB");    
		}
	}

	public List<T> getObjectsDate(java.util.Date startDate, java.util.Date endDate) throws ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		logger.info("Acessando getObjectsDate " );
		logger.info(" Listando Start to Date ====> "+startDate  );
		logger.info(" Listando End   to Date ====> "+endDate  );
		BasicDBObject query = new BasicDBObject();
		query.put("dataCriacao", BasicDBObjectBuilder.start("$gte", startDate).add("$lte", endDate).get());
		DBCursor cursor = getDbCollection().find(query);
		logger.info("getObjectsDate está retornando [ "+cursor.size() +" ]" );
		return converToMap(cursor);
	}


	public DBCollection getDbCollection() {
		return this.dbCollection;
	}


	public Class<T> getWorkingClass() {
		return workingClass;
	}



}
