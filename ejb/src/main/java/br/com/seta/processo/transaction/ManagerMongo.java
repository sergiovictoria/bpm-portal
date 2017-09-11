package br.com.seta.processo.transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jboss.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.Contrato;

import com.mongodb.MongoException;

public class ManagerMongo<T>implements Serializable {


	private static final long serialVersionUID = 1L;
	private final Class<T> workingClass;
	private static Logger logger = Logger.getLogger(ManagerMongo.class);
	private Datastore datastore;

	public ManagerMongo(Class<T> workingClass) {
		this.workingClass = workingClass;
		this.datastore = MongoConnectionManager.getInstance().getDatastore();
		logger.info("Criando Transaçao com  Mongo Host [ "+MongoConnectionManager.getInstance().getHostMongo()+" ]  Porta [ "+ MongoConnectionManager.getInstance().getPortMongo() + " ] Banco [ "+ MongoConnectionManager.getInstance().getDbMongo() +" ]");
	}

	public void save(Collection<? extends Object> objects) throws MongoException {
		logger.info(" Gravando Objecto [ "+objects.getClass()+" ]");
		try {
			Iterable<T> iterable = (Iterable<T>) objects;
			getDatastore().save(iterable);
		}catch (RuntimeException e) {
			throw new MongoException("Erro ao tentar salvar collection no mogno [save] ",e);    
		}
	}

	public void save(Object object) throws MongoException {
		logger.info(" Gravando Objecto [ "+object.getClass()+" ]");
		try {
			getDatastore().save(object);
		}catch (RuntimeException e) {
			throw new MongoException("Erro ao tentar salvar objeto no mogno [save] ",e);   
		}
	}

	public void updateWithTwoAttributes(String key, Object value, String keyOps, Object valueOps) throws MongoException {
		logger.info(" Fazendo chamda mongo [updateWithTwoAttributes] ");
		try {
			Query<T> query = getDatastore().find(workingClass).field(key).equal(value);
			UpdateOperations<T> ops =  getDatastore().createUpdateOperations(workingClass).set(keyOps,valueOps);
			datastore.update(query, ops); 
		}catch (Exception e) {
			throw new MongoException("Erro ao tentar fazer [updateWithTwoAttributes] ",e);    
		}
	}

	public void updateWithTMap(String key, Object value, Map<String,Object> opsMap) throws MongoException {
		logger.info(" Fazendo chamda mongo [updateWithTMap] ");
		try {
			Query<T> query = getDatastore().find(workingClass).field(key).equal(value);
			Set<Entry<String, Object>> set = opsMap.entrySet();
			Iterator<Entry<String, Object>> it = set.iterator();
			UpdateOperations<T> updateQuery = null;
			while(it.hasNext()){
				Entry<String, Object> entry = (Entry<String, Object>)it.next();
				updateQuery =  getDatastore().createUpdateOperations(workingClass).set(entry.getKey(),entry.getValue());
				datastore.update(query, updateQuery);
			}
		}catch (Exception e) {
			throw new MongoException("Erro ao tentar fazer update de [updateWithTMap] ",e);    
		}
	}
	
	
	public void updateWithTMap(Map<String,Object> parameters, Map<String,Object> opsMap) throws MongoException {
		logger.info(" Fazendo chamda mongo [updateWithTMap] ");
		try {
			
			Query query = getDatastore().createQuery(workingClass);
			for(Map.Entry<String, Object> entry : parameters.entrySet()) {
				query.field(entry.getKey()).equal(entry.getValue());
			};
			
			Set<Entry<String, Object>> set = opsMap.entrySet();
			Iterator<Entry<String, Object>> it = set.iterator();
			UpdateOperations<T> updateQuery = null;
			while(it.hasNext()){
				Entry<String, Object> entry = (Entry<String, Object>)it.next();
				updateQuery =  getDatastore().createUpdateOperations(workingClass).set(entry.getKey(),entry.getValue());
				datastore.update(query, updateQuery);
			}
			
		}catch (Exception e) {
			throw new MongoException("Erro ao tentar fazer update de [updateWithTMap] ",e);    
		}
	}


	public void update(String key, Object value, Object object) throws MongoException {
		logger.info(" Fazendo chamda mongo [update] ");
		try {
			getDatastore().delete(getDatastore().createQuery(workingClass).field(key).equal(value));
			getDatastore().save(object);
		}catch (Exception e) {
			throw new MongoException("Erro ao tentar fazer [update] ",e);    
		}
	}



	public void remove(String key, Object value) throws MongoException {
		logger.info(" Fazendo chamda mongo [remove] ");
		try {
			getDatastore().delete(getDatastore().createQuery(workingClass).field(key).equal(value));
		}catch (Exception e) {
			throw new MongoException("Erro ao tentar fazer [remove] ",e);    
		}
	}

	public void remove(T entity){		
		getDatastore().delete(entity);
	}


	public List<T> find() throws MongoException {
		logger.info(" Fazendo chamda mongo [find] ");
		List<T> objects = new ArrayList<T>();
		try {
			logger.info(" Criando consulta mongo lista de objetos " );
			objects = getDatastore().createQuery(workingClass).asList();
			return objects; 
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [find] ",e);
		}
	}

	public List<T> find(String key, Object value) throws MongoException {
		logger.info(" Fazendo chamda mongo [find] ");
		List<T> objects = new ArrayList<T>();
		try {
			logger.info(" Criando consulta mongo lista de objetos " );
			objects = getDatastore().find(workingClass).field(key).equal(value).asList();
			return objects; 
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [find] ",e);
		}
	}


	public Object findCollection(Class<?> type) throws MongoException {
		logger.info(" Fazendo chamda mongo [find] ");
		List<T> objects = new ArrayList<T>();
		try {
			logger.info(" Criando consulta mongo lista de objetos " );
			Query<T> query = getDatastore().find(workingClass);
			objects = query.asList();
			return objects.size() > 0 ? objects.get(0) : null;
		}catch (Exception e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [find] ",e);
		}
	}

	public void removeCollection (Class<?> type) throws MongoException {
		logger.info(" Fazendo chamda mongo [removeCollection] ");
		try {
			logger.info(" Removendo collection "+type);
			Query<T> query = getDatastore().find(workingClass);
			getDatastore().delete(query);
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [find] ",e);
		}
	}

	public List<T> find(Map<String, Object> parameters) throws MongoException {
		logger.info(" Fazendo chamda mongo [find] ");
		List<T> objects = new ArrayList<T>();
		try {
			logger.info(" Criando consulta mongo lista de objetos " );
			Query query = getDatastore().createQuery(workingClass);
			for(Map.Entry<String, Object> entry : parameters.entrySet()) {
				query.field(entry.getKey()).equal(entry.getValue());
			};
			objects = query.asList();
			return objects; 
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [find] ",e);
		}
	}


	public Object findOne(String key, Object value) throws MongoException {
		logger.info(" Fazendo chamda mongo [findOne] ");
		List<T> objects = new ArrayList<T>();
		try {
			logger.info(" Criando consulta mongo objeto " );
			objects = getDatastore().find(workingClass).field(key).equal(value).asList();
			return objects.size() > 0 ? objects.get(0) : null;  
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura do objeto  [findOne] ",e);
		}
	}

	public Object findOne() throws MongoException {
		logger.info(" Fazendo chamda mongo [findOne] ");
		List<T> objects = new ArrayList<T>();
		try {
			logger.info(" Criando consulta mongo objeto " );
			objects = getDatastore().find(workingClass).asList();
			return objects.size() > 0 ? objects.get(0) : null;  
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura do objeto  [findOne] ",e);
		}
	}
	
	
	public List<T> findAll(){
		return getDatastore().find(workingClass).asList();
	}


	public List<T> findTextSearch(String textSearch,long first, long count) throws MongoException {
		logger.info(" Fazendo chamda mongo procurando por findTextSearch");
		List<T> objects = new ArrayList<T>();
		try {
			objects = getDatastore().createQuery(workingClass).search(textSearch).order("caseId").offset((int)first).limit((int)count).asList();
			return objects; 
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [findTextSearch] ",e);
		}
	}


	public List<T> findTextSearch(String textSearch,long first, long count, java.util.Date firstDate, java.util.Date lastDate) throws MongoException {
		logger.info(" Fazendo chamda mongo procurando por findTextSearch");
		Query<T> qs =  getDatastore().createQuery(workingClass).search(textSearch).order("caseId").offset((int)first).limit((int)count).field("status.data").greaterThanOrEq(firstDate).field("status.data").lessThanOrEq(lastDate);
		List<T> objects = new ArrayList<T>();
		try {
			objects = qs.asList();
			return objects; 
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [findTextSearch] ",e);
		}
	}

	public List<T> findTextSearch(long first, long count, java.util.Date firstDate, java.util.Date lastDate) throws MongoException {
		logger.info(" Fazendo chamda mongo procurando por findTextSearch");
		Query<T> qs =  getDatastore().createQuery(workingClass).order("caseId").offset((int)first).limit((int)count).field("status.data").greaterThanOrEq(firstDate).field("status.data").lessThanOrEq(lastDate);
		List<T> objects = qs.asList();
		return objects; 
	}


	public long findTextSearchSize(String textSearch) throws MongoException {
		logger.info(" Fazendo chamda mongo procurando por findTextSearchSize");
		try {
			return getDatastore().createQuery(workingClass).search(textSearch).countAll();
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [findTextSearchSize] ",e);
		}
	}

	public long findTextSearchSize(String textSearch, long first, long count) throws MongoException {
		logger.info(" Fazendo chamda mongo procurando por findTextSearchSize");
		try {
			return getDatastore().createQuery(workingClass).search(textSearch).order("caseId").offset((int)first).limit((int)count).countAll();
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [findTextSearchSize] ",e);
		}
	}	

	public long findTextSearchSize(long first, long count)  {
		logger.info(" Fazendo chamda mongo procurando por findTextSearchSize");
		return getDatastore().createQuery(workingClass).order("caseId").offset((int)first).limit((int)count).countAll();		
	}	

	public long findTextSearchSize(String textSearch,long first, long count, java.util.Date firstDate, java.util.Date lastDate) throws MongoException {
		logger.info(" Fazendo chamda mongo procurando por findTextSearchDateSize");
		long retunCount = 0;
		try {
			retunCount  = getDatastore().createQuery(workingClass).search(textSearch).order("caseId").offset((int)first).limit((int)count).field("status.data").greaterThanOrEq(firstDate).field("status.data").lessThanOrEq(lastDate).asList().size();
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [findTextSearchDateSize] ",e);
		}
		return retunCount;
	}

	public long findTextSearchSize(long first, long count, java.util.Date firstDate, java.util.Date lastDate){
		logger.info(" Fazendo chamda mongo procurando por findTextSearchDateSize");
		long retunCount  = getDatastore().createQuery(workingClass).order("caseId").offset((int)first).limit((int)count).field("status.data").greaterThanOrEq(firstDate).field("status.data").lessThanOrEq(lastDate).asList().size();

		return retunCount;
	}	

	public long findSize(String textSearch, java.util.Date firstDate, java.util.Date lastDate) throws MongoException {
		logger.info("Fazendo chamda mongo procurando por findTextSearchDateSize");
		long retunCount = 0;
		try {
			retunCount  = getDatastore().createQuery(workingClass).search(textSearch).field("status.data").greaterThanOrEq(firstDate).field("status.data").lessThanOrEq(lastDate).asList().size();
		}catch (RuntimeException e ) {
			throw new MongoException("Ocorreu um erro na procura dos objetos [findTextSearchDateSize] ",e);
		}
		return retunCount;
	}

	public long findSize(java.util.Date firstDate, java.util.Date lastDate){
		logger.info("Fazendo chamda mongo procurando por findTextSearchDateSize");
		long retunCount  = getDatastore().createQuery(workingClass).field("status.data").greaterThanOrEq(firstDate).field("status.data").lessThanOrEq(lastDate).asList().size();

		return retunCount;
	}	

	public Datastore getDatastore() {
		return this.datastore;
	}
	
	public static void main(String[] args) {
		ManagerMongo<Contrato> teste =  new ManagerMongo<Contrato>(Contrato.class);
		List<Contrato> contratos = teste.find("statusContrato",2);
		for (Contrato dto : contratos) {
			System.out.println(" dto"+dto.getStatusContrato());
		}
	}
}
