package br.com.seta.processo.dao;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.beanutils.PropertyUtils;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.Compra;
import br.com.seta.processo.dto.ModeloDocumento;
import br.com.seta.processo.dto.Pagamento;
import br.com.seta.processo.dto.Pessoa;
import br.com.seta.processo.qualifier.ConsincoDatabase;
import br.com.seta.processo.utils.ObjectUtil;



public abstract class GenericJpaDaoConSinco<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(GenericJpaDaoConSinco.class);
	

	@ConsincoDatabase
	@Inject
	private EntityManager em;

	private Class<T> entityBeanType;




	public GenericJpaDaoConSinco() {
		try {
			Type genericSuperclass = super.getClass().getGenericSuperclass();
			ParameterizedType type = (ParameterizedType) genericSuperclass;
			this.entityBeanType = ((Class) type.getActualTypeArguments()[0]);
		} catch (Exception e) {
//			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		}
	}



	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	protected EntityManager getEntityManager() {
		if (this.em == null) {
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		}

		return this.em;
	}

	protected Class<T> getEntityBeanType() {
		return this.entityBeanType;
	}


	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEntityManager().createQuery("from " + getEntityBeanType().getName()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String nmAtributeAsc) {
		if (nmAtributeAsc == null)
			return findAll();

		return getEntityManager().createQuery("from " + getEntityBeanType().getName() + " order by " + nmAtributeAsc + " asc").getResultList();
	}

	public T persist(T entity) {
		return getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(entity);
	}

	public void insert(T entity) {
		getEntityManager().persist( entity);
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<T> findFromNativeNamedQuery(String nativeNamedQuery){
		Query query = getEntityManager().createNamedQuery(nativeNamedQuery, entityBeanType);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findFromNativeNamedQuery(String nativeNamedQuery, Map<String, Object> parameters){
		Query query = getEntityManager().createNamedQuery(nativeNamedQuery, entityBeanType);
		
		if(parameters != null){
			Iterator<String> iterator = parameters.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				query.setParameter(key, parameters.get(key));
			}
		}
		
		return (List<T>)query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findFromNativeQuery(String nativeNamedQuery, Map<String, Object> parameters, long first, long count){
		Query query = getEntityManager().createNativeQuery(nativeNamedQuery, entityBeanType);
		query.setHint("org.hibernate.cacheable", true);
        query.setHint("org.hibernate.cacheMode", "NORMAL");
		if(parameters != null){
			Iterator<String> iterator = parameters.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				query.setParameter(key, parameters.get(key));
			}
		}
		query.setFirstResult((int)first).setMaxResults((int)count);
		return (List<T>)query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findFromNativeQuery(String nativeNamedQuery, Map<String, Object> parameters) {
		Query query = getEntityManager().createNativeQuery(nativeNamedQuery, entityBeanType);
		query.setHint("org.hibernate.cacheable", true);
        query.setHint("org.hibernate.cacheMode", "NORMAL");
		if(parameters != null){
			Iterator<String> iterator = parameters.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				query.setParameter(key, parameters.get(key));
			}
		}
		return (List<T>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findFromNativeNamedQuery(String nativeNamedQuery, Object... parameters){
		Query query = getEntityManager().createNamedQuery(nativeNamedQuery, entityBeanType);
		
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		return (List<T>)query.getResultList();
	}
	
	/**
	 * Executa uma consulta baseando-se em uma consulta JPQL, cujo seus parâmetros são baseados em suas posições
	 * 
	 * @param jpqlQuery Consulta JPQL
	 * @param parameters parâmetros (opcional)
	 * @return Uma lista contendo o resultado
	 */
	public List<T> findFromJPQLQuery(String jpqlQuery, Object... parameters){
		TypedQuery<T> query = getEntityManager().createQuery(jpqlQuery, entityBeanType);
		
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		
		return query.getResultList();		
	}
	
	/**
	 * Executa uma consulta baseando-se em uma consulta JPQL
	 * 
	 * @param jpqlQuery Consulta JPQL
	 * @return Uma lista contendo o resultado
	 */
	public List<T> findFromJPQLQuery(String jpqlQuery){
		List<?> list =  getEntityManager().createQuery(jpqlQuery, entityBeanType).getResultList();
		System.out.println(list);
		return getEntityManager().createQuery(jpqlQuery, entityBeanType).getResultList();
	}
	
	/**
	 * Executa uma consulta nativa em SQL
	 * 
	 * @param nativeQuery
	 * @param parameters
	 * @return Uma lista contendo o resultado
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFromNativeQuery(String nativeQuery, Object... parameters){
		Query query = getEntityManager().createNativeQuery(nativeQuery, entityBeanType);		
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		return query.getResultList();
	}
	
	
	
	/**
	 * Executa uma consulta nativa em SQL
	 * 
	 * @param nativeQuery
	 * @param parameters
	 * @return Uma lista contendo o resultado
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFromNativeQuery(String nativeQuery, Object object){
		Query query = getEntityManager().createNativeQuery(nativeQuery, entityBeanType);
		query.setParameter("filtero", object);
		return query.getResultList();
	}
	
	/****
	 * @author Sérgio da Victória
	 * @param namedQuery Query
	 * @param parameters Parametros
	 * @return Retorno NamedQuery Entity
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFromNamedQuery(String namedQuery, Object... parameters){
		Query query = getEntityManager().createNamedQuery(namedQuery);
		query.setHint("org.hibernate.cacheable", true);
        query.setHint("org.hibernate.cacheMode", "NORMAL");
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		return query.getResultList();
	}
	
	/****
	 * @author Sérgio da Victória
	 * @param namedQuery Query
	 * @param parameters Parametros
	 * @return Retorno NamedQuery Entity
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFromNamedQuery(String namedQuery, Object filtro, long first, long count){
		Query query = getEntityManager().createNamedQuery(namedQuery);
		query.setHint("org.hibernate.cacheable", true);
        query.setHint("org.hibernate.cacheMode", "NORMAL");
		query.setParameter("filtro",filtro);
		query.setFirstResult((int)first).setMaxResults((int)count);
		return query.getResultList();
	}
	
	
	/****
	 * @author Sérgio da Victória
	 * @param namedQuery Query
	 * @param parameters Parametros
	 * @return Retorno NamedQuery Entity
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFromNamedQuery(String namedQuery, long first, long count){
		Query query = getEntityManager().createNamedQuery(namedQuery);
		query.setHint("org.hibernate.cacheable", true);
        query.setHint("org.hibernate.cacheMode", "NORMAL");
		query.setFirstResult((int)first).setMaxResults((int)count);
		return (List<T>) query.getResultList();
	}
	
	/****
	 * @author Sérgio da Victória
	 * @param namedQuery Query
	 * @param parameters Parametros
	 * @return Retorno NamedQuery Entity
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFromNamedQuery(String namedQuery, Object filtro){
		Query query = getEntityManager().createNamedQuery(namedQuery);
		query.setHint("org.hibernate.cacheable", true);
        query.setHint("org.hibernate.cacheMode", "NORMAL");
		query.setParameter("filtro",filtro);
		return query.getResultList();
	}
	
	/****
	 * @author Sérgio da Victória
	 * @param namedQuery Query
	 * @param parameters Parametros
	 * @return Retorno NamedQuery Entity
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFromNamedQuery(String namedQuery){
		Query query = getEntityManager().createNamedQuery(namedQuery);	
		query.setHint("org.hibernate.cacheable", true);
        query.setHint("org.hibernate.cacheMode", "NORMAL");
		return query.getResultList();
	}
	
	
	/**
	 * Executa uma consulta nativa em SQL
	 * 
	 * @param nativeQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFromNativeQuery(String nativeQuery){
		Query query = getEntityManager().createNativeQuery(nativeQuery, entityBeanType);
		return query.getResultList();
	}
	
	public T findOneFromNativeQuery(String nativeQuery, Object... parameters){
		List<T> results = findFromNativeQuery(nativeQuery, parameters);
		if(results.isEmpty()){
			return null;
		} 		
		return results.get(0);
	}	

	/**
	 * Executa uma declaração sql de insert, update ou delete
	 * 
	 * @param nativeQuery Declaração nativa sql
	 * @param parameters parâmetros
	 * @return a quantidade de registros alterados
	 */
	public int executeNativeQuery(String nativeQuery, Object... parameters){
		Query query = getEntityManager().createNativeQuery(nativeQuery);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		return query.executeUpdate();
	}

	public static abstract interface QueryWrapper {
		public abstract String getQueryString();
		public abstract void setParam(int paramInt, Query paramQuery, Object paramObject);
	}


	public static <T> List<T> map(Class<T> type, List<Object[]> records){
		List<T> result = new LinkedList<>();
		for(Object[] record : records){
			result.add(map(type, record));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getResultList(Query query, Class<T> type){
		List<Object[]> records = query.getResultList();
		return map(type, records);
	}

	public static <T> T map(Class<T> type, Object[] tuple){
		List<Class<?>> tupleTypes = new ArrayList<>();
		for(Object field : tuple){
			tupleTypes.add(field.getClass());
		}
		try {
			Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
			return ctor.newInstance(tuple);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getResultListMap(Query query, Class<T> type){
		List<Object[]> records = (List<Object[]>) query.getResultList();
		return mapResult(type, records);
	}

	public static <T> List<T> mapResult(Class<T> type, List<Object[]> records){
		List<T> result = new LinkedList<>();
		for(Object[] record : records){
			result.add(mapResult(type, record));
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public static <T> T mapResult(Class<T> clazz, Object[] tuple){
		T object = null;
		try {
			object = (T) ObjectUtil.createObject(clazz.getCanonicalName());
			List<String>  fieldNames = ObjectUtil.getFieldNames(clazz);
			int row = 0;
			for (Object dto : tuple) {
				Field f = clazz.getDeclaredField(fieldNames.get(row));
				f.setAccessible(true);
				if ((dto!=null)) {
				     f.set(object, Class.forName(dto.getClass().getCanonicalName()).cast(dto));
				}    
				row++;
			}
		}catch (Exception e ) {
			logger.error("ERROR >>>> ",e);
			e.printStackTrace();
		}
		return object;
	}
	
	
	public List<String> getStringList(List<Object> object) {
		List<String>  strings = new ArrayList<String>();
		for (Object dto : object) {
			strings.add((String)dto);
		}
    	return strings;
	}
	

	public List<Pagamento> getPagamentoS(List<Object[]> object) {
		
		List<Pagamento> pagamentos = new ArrayList<Pagamento>(object.size());
		for (Object[] objects : object) {
			Pagamento pagamento = new Pagamento((String)objects[0],(java.util.Date)objects[1],(BigDecimal)objects[2],(String)objects[3],(BigDecimal)objects[4],(java.util.Date)objects[5]);
			pagamentos.add(pagamento);
		}
		return pagamentos;
		
	}

	public List<ModeloDocumento> getModeloDocumentoS(List<Object[]> object) {
		
		List<ModeloDocumento> modeloDocumentos = new ArrayList<ModeloDocumento>(object.size());
		for (Object[] objects : object) {
			ModeloDocumento modeloDocumento = new ModeloDocumento((String)objects[0],(String)objects[1],(String)objects[2]);
			modeloDocumentos.add(modeloDocumento);
		}
		return modeloDocumentos;
		
	}	
	
	

	public List<String> getNaturezaDespesaNamesConcat(List<Object[]> object) {
		List<String> list = new ArrayList<String>(object.size());
		for (Object[] objects : object) {
			list.add(objects[0].toString()+" "+objects[1].toString());
		}
		return list;
	}

	
	public List<String> getNaturezaDespesaFiltroItemS(List<Object[]> object) {
		List<String> list = new ArrayList<String>(object.size());
		for (Object[] objects : object) {
			list.add((String) objects[0].toString());
		}
		return list;
	}
	
	
	
	public List<Pessoa> mapPessoaS(List<Object[]> object) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>(object.size());
		for (Object[] objects : object) {
			Pessoa pessoa = new Pessoa((BigDecimal)objects[0],(String)objects[1],(BigDecimal)objects[2],(BigDecimal)objects[3],(BigDecimal)objects[4],(String)objects[5]);
			pessoas.add(pessoa);
		}
		return pessoas;
	}
	


	public List<Compra> getCompras(List<Object[]> object) {
		List<Compra> compras = new ArrayList<Compra>(object.size());
		for (Object[] objects : object) {
			Compra compra = new Compra((BigDecimal)objects[0],(String)objects[1],(java.util.Date)objects[2]);
			compras.add(compra);
		}
		return compras;
	}



	public static Object copyObject(List<Object> orig, Class<? extends Object> clazz) {
		List<Object> destList = null;
		try {
			Class<?> requestCLass = Class.forName(clazz.getCanonicalName());
			destList = new ArrayList<Object>(orig.size());
			for (Object list : orig) {
				Object requestObject = requestCLass.newInstance();
				destList.add(copyObject(requestObject,list));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return destList;
	}
	
	
	public static List<? extends Object> copyObjectS(List<? extends Object> orig, Class<? extends Object> clazz) {
		List<Object> destList = null;
		try {
			Class<?> requestCLass = Class.forName(clazz.getCanonicalName());
			destList = new ArrayList<Object>(orig.size());
			for (Object list : orig) {
				Object requestObject = requestCLass.newInstance();
				destList.add(copyObject(requestObject,list));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return destList;
	}
	

	

	public static Object copyObject(Object dest, Object orig) {
		PropertyDescriptor [] destDesc = PropertyUtils.getPropertyDescriptors (dest);
		try {
			for (int i=0; i <destDesc.length; i++) {
				Class<?> destType = destDesc[i].getPropertyType();
				Class<?> origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
				if (destType!=null && destType.equals(origType) && !destType.equals(Class.class)) {
					if (!Collection.class.isAssignableFrom(origType)) {
						Object value = PropertyUtils.getProperty (orig, destDesc[i].getName());
						PropertyUtils.setProperty (dest, destDesc[i].getName(),value);
					}
				}
			}
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return dest;
	}

	
}





