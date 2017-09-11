package br.com.seta.processo.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.TransacaoMongoLocal;
import br.com.seta.processo.interfaces.TransacaoMongoRemote;
import br.com.seta.processo.transaction.ManagerMongo;


@Stateless(name="TransacaoMongoRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TransacaoMongoLocal.class)
@Remote(TransacaoMongoRemote.class)
@Interceptors({LogInterceptor.class})
public class TransacaoMongoRepository implements TransacaoMongoLocal, TransacaoMongoRemote {

	@Inject
	private Logger logger;



	@PostConstruct
	public void init() {
		logger.info("Criando transação mongo - EJB");
	}


	@Override
	public void save(Object object, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [save] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.save(object);
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [save] " ,e);    
		}
	}


	@Override
	public void save(Collection<? extends Object> objects, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [save] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.save(objects);
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [save] " ,e);   
		}
	}


	@Override
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

	@Override
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
	

	@Override
	public void remove(String key, Object value, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [remove] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.remove(key, value);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [remove] " ,e);
		}
	}

	
	@Override
	public void update(String key, Object value, Object object, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [update] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.update(key, value, object);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [update] " ,e);
		}
	}


	@Override
	public void updateWithTMap(String key, Object value, Map<String, Object> opsMap, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [updateWithTMap] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.updateWithTMap(key, value, opsMap);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [updateWithTMap] " ,e);
		}
	}


	@Override
	public void updateWithTwoAttributes(String key, Object value, String keyOps, Object valueOps, Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [updateWithTwoAttributes] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		try {
			managerMongo.updateWithTwoAttributes(key, value, keyOps, valueOps);
		}catch (Exception e ) {
			logger.error(" Erro ao tentar acessar  EJB -  [updateWithTwoAttributes] " ,e);
		}
	}
	
	@Override
	public Object find(Class<?> type) {
		logger.info(" Fazendo Chamada   EJB -  [find] ");
		ManagerMongo<Object> managerMongo = new ManagerMongo<Object>(getInstanceofClass(type));
		List<Object> Objects = new ArrayList<Object>(0);
		try {
			Objects =  (List<Object>) managerMongo.find();
		}catch (Exception e) {
			logger.error(" Erro ao tentar acessar  EJB -  [find] " ,e);
		}
		return Objects;
	}

	private Class<Object> getInstanceofClass(Class<?> type) {
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


	

}
