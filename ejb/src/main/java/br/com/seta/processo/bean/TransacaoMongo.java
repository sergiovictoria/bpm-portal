package br.com.seta.processo.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.connection.MongoConnection;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.transaction.ManagerMongo;
import br.com.seta.processo.utils.WrapperUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;


@Stateless(name="TransacaoMongo")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class TransacaoMongo {

	@Inject	private Logger logger;
	private DBCollection dbCollection;
	private static final String _FIND_REQUSICAO_RECEBIMENTO = "{$and:[{\"status\" : \"Rejeitado\" },{\"nomeProcesso\" : \"Processo de Suprimentos\" },{ fim: { $exists: true } } ]}";
	private static final String _FIND_REQUSICAO_SLICE       = "{atividades: {$slice: -1}, \"atividades.variaveis.dtoOrRequisicao.valor\" : 1 }}";



	@PostConstruct
	public void init() {
		logger.info("Criando transação mongo - EJB");
	}



	public void save(Object object, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [save] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.save(object);
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [save] " ,e);    
		}
	}



	public void save(Collection<? extends Object> objects, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [save] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.save(objects);
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [save] " ,e);   
		}
	}

	public Collection<? extends Object> find(String key, Object value,	Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [find] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		List<Object> Objects = new ArrayList<Object>(0);
		try {
			Objects =  (List<Object>) managerMongo.find(key,value);
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [find] " ,e);
		}
		return Objects;
	}

	public Collection<? extends Object> find(Map<String, Object> parameters, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [find] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		List<Object> Objects = new ArrayList<Object>(0);
		try {
			Objects =  (List<Object>) managerMongo.find(parameters);
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [find] " ,e);
		}
		return Objects;
	}


	public Object findCollection(Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [findCollection] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		Object object = new Object();
		try {
			object  = managerMongo.findCollection(getInstanceofClass(type));
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [findCollection] " ,e);
		}
		return object==null ? null : object;
	}

	public void removeCollection(Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [removeCollection] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.removeCollection(getInstanceofClass(type));
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [removeCollection] " ,e);
		}
	}

	public Object findOne(String key, Object value, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [findOne] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		Object object = new Object();
		try {
			object  = managerMongo.findOne(key,value);
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [findOne] " ,e);
		}
		return object;
	}

	public Object findOne(Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [findOne] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		Object object = new Object();
		try {
			object  = managerMongo.findOne();
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [findOne] " ,e);
		}
		return object;
	}


	public void remove(String key, Object value, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [remove] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.remove(key, value);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [remove] " ,e);
		}
	}



	public void update(String key, Object value, Object object, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [update] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.update(key, value, object);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [update] " ,e);
		}
	}


	public void updateWithTMap(String key, Object value, Map<String, Object> opsMap, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [updateWithTMap] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.updateWithTMap(key, value, opsMap);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [updateWithTMap] " ,e);
		}
	}


	public void updateWithTwoAttributes(String key, Object value, String keyOps, Object valueOps, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [updateWithTwoAttributes] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.updateWithTwoAttributes(key, value, keyOps, valueOps);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [updateWithTwoAttributes] " ,e);
		}
	}
	
	public void updateWithTMap(Map<String,Object> parameters, Map<String,Object> opsMap, Class<?> type) throws MongoException {
		logger.info(" Fazendo chamda update [updateWithTMap] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.updateWithTMap(parameters, opsMap);
		}catch (Exception e) {
			throw new MongoException("Erro ao tentar fazer update de [updateWithTMap] ",e);    
		}
	}

	@SuppressWarnings("unused")
	private Collection<? extends Object> getRecords(final String textSearch, final int first, final int count, final Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [getTextSearch] ");
		List<Object> objects = new ArrayList<Object>(0);
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			objects = managerMongo.findTextSearch(textSearch, first, count);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [getTextSearch] " ,e);
		}
		return objects;
	}


	public Collection<? extends Object> getRecords(final String textSearch , final long first, final long count, final java.util.Date firstDate, final java.util.Date lastDate, final Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [getTextSearch] ");
		List<Object> objects = new ArrayList<Object>(0);
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			objects = managerMongo.findTextSearch(textSearch, first, count, WrapperUtils.converteMongoHours(firstDate,true), WrapperUtils.converteMongoHours(lastDate,false));
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [getTextSearch] " ,e);
		}
		return objects;
	}


	public Collection<? extends Object> getRecords(final long first, final long count, final java.util.Date firstDate, final java.util.Date lastDate, final Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [getTextSearch] ");
		List<Object> objects = new ArrayList<Object>(0);
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			objects = managerMongo.findTextSearch(first, count, WrapperUtils.converteMongoHours(firstDate,true), WrapperUtils.converteMongoHours(lastDate,false));
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [getTextSearch] " ,e);
		}
		return objects;
	}



	public long getRecordsSize(final String textSearch, final Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [getTextSearchSize] ");
		long count = 0;
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			count = managerMongo.findTextSearchSize(textSearch);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [getTextSearchSize] " ,e);
		}
		return count;
	}



	public long getRecordsSize(String textSearch,long first, long count, java.util.Date firstDate, java.util.Date lastDate, final Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [getTextSearchDateSize] ");
		long retunCount = 0;
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			retunCount = managerMongo.findTextSearchSize(textSearch, first, count, WrapperUtils.converteMongoHours(firstDate,true), WrapperUtils.converteMongoHours(lastDate,false));
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [getTextSearchSize] " ,e);
		}
		return retunCount;
	}


	public long getRecordsSize(String textSearch,long first, long count, final Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [getTextSearchSize] ");
		long retunCount = 0;
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			retunCount = managerMongo.findTextSearchSize(textSearch, first, count);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [getTextSearchSize] " ,e);
		}
		return retunCount;
	}


	public long size(String textSearch, java.util.Date firstDate, java.util.Date lastDate, final Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [getTextSearchDateSize] ");
		long retunCount = 0;
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			retunCount = managerMongo.findSize(textSearch, WrapperUtils.converteMongoHours(firstDate,true), WrapperUtils.converteMongoHours(lastDate,false));
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [getTextSearchSize] " ,e);
		}
		return retunCount;
	}

	public long size(java.util.Date firstDate, java.util.Date lastDate, final Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [getTextSearchDateSize] ");
		long retunCount = 0;
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			retunCount = managerMongo.findSize(WrapperUtils.converteMongoHours(firstDate,true), WrapperUtils.converteMongoHours(lastDate,false));
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [getTextSearchSize] " ,e);
		}
		return retunCount;
	}

	public  void listaRequisicoes() {

		Gson gson= new Gson();
		this.dbCollection = MongoConnection.getInstance().getDB().getCollection("InstanciaProcesso");
		BasicDBObject query = (BasicDBObject) JSON.parse(_FIND_REQUSICAO_RECEBIMENTO);
		BasicDBObject slice = (BasicDBObject) JSON.parse(_FIND_REQUSICAO_SLICE);
		Cursor cursor =  this.dbCollection.find(query,slice);
		
		JSON json =new JSON();
		String serialize = json.serialize(cursor);
		System.out.println(serialize);
		
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(serialize).getAsJsonArray();
		List<InstanciaProcesso> list = new ArrayList<InstanciaProcesso>();
		for (final JsonElement jsonElement : array) {
			InstanciaProcesso entity = gson.fromJson(jsonElement, InstanciaProcesso.class);
			list.add(entity);
		}
	
		for (InstanciaProcesso dto : list) {
				
			System.out.println ("dto"+dto.getAtividades().get(0).getValorVariavel("dtoOrRequisicao"));
		}
		 
	}



	@SuppressWarnings("unchecked")
	public Class<Object> getInstanceofClass(Class<?> type) {
		Class<Object> clazz = null;
		try {
			clazz = (Class<Object>) Class.forName(type.getCanonicalName());
		}catch (Exception e ) {  
		}
		return clazz;
	}


	@PreDestroy
	public void destroy() {
		logger.info("Destruindo transação mongo - EJB");
	}


	public static void main(String[] args)  {
		new TransacaoMongo().listaRequisicoes();

		//		TransacaoMongo mongo = new TransacaoMongo();
		////		Contrato c = new Contrato();
		////		c.setCodigoFornecedor(13L);
		////		c.setStatusContrato(1L);
		////		c.setDataInicioContrato(new java.util.Date());
		////		c.setNumeroContrato(13L);
		////		c.setDataFimContrato(new java.util.Date());
		////		mongo.save(c,Contrato.class);
		////		mongo.updateWithTwoAttributes("statusContrato", 3, "statusContrato",333, Contrato.class);
		//		Map<String, Object> parameters = new HashMap<String, Object>();
		//		parameters.put("statusContrato", 2);
		//		parameters.put("codigoFornecedor",13);
		//		List<Contrato> contratos = (List<Contrato>) mongo.find(parameters, Contrato.class);
		//		for (Contrato dto : contratos) {
		//			System.out.println("dto "+dto.getCodigoFornecedor());
		//		}
	}

}
