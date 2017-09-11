package br.com.seta.processo.servicos;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import br.com.seta.processo.context.JndiContext;

public final class RemoteMongo implements Serializable {
	
	private static Logger logger = Logger.getLogger(RemoteMongo.class);
	private static final long serialVersionUID = 1L;
	private Context context;
	private static RemoteMongo remoteMongo;
	private static final String _PACKAGE = "Processo-ear-1.1.13/Processo-ejb/";
	
	
	/*****
	 * 
	 * @return Singleton Object  
	 */
	
	public static RemoteMongo getInstance(){
		if (remoteMongo == null){
			logger.info("  **** Criando Serviço Singleton Serviços *** ");
			remoteMongo = new RemoteMongo();
		}
		return remoteMongo;
	}
	

	public RemoteMongo( ) {
		this.context = new JndiContext().getContext();
	}
	
	/****
	 * 
	 * @param repository
	 * @param clazzInterface
	 * @param serviceName
	 * @return
	 * @throws NamingException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public List<?> getObjects(Class<?> repository, Class<?> clazzInterface, Class<?> workingClass, String serviceName) throws NamingException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		logger.info("Fazendo remote invoke "+doLookup(repository,clazzInterface));
		List<?> objects;
		Object ejbRemote = this.context.lookup(doLookup(repository,clazzInterface));
		Method ejbMethod =  clazzInterface.getMethod(serviceName, new Class[]{workingClass.getClass()});
		objects = (List<?>) ejbMethod.invoke(ejbRemote,workingClass);
		return objects;
	}
	
	
	/****
	 * 
	 * @param repository
	 * @param clazzInterface
	 * @param serviceName
	 * @param key
	 * @param value
	 * @return
	 * @throws NamingException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	
	public List<?> getObjects(Class<?> repository, Class<?> clazzInterface, Class<?> workingClass, String serviceName, String key, Object value) throws NamingException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class[] keyParam = new Class[1];	
		keyParam[0] = String.class;
		Class[] valueParam = new Class[1];	
		valueParam[0] = Object.class; 
		Class[] classParam = new Class[1];	
		classParam[0] =  workingClass.getClass();
		logger.info("Fazendo remote invoke "+doLookup(repository,clazzInterface));
		List<?> objects;
		Object ejbRemote = this.context.lookup(doLookup(repository,clazzInterface));
		Method ejbMethod =  clazzInterface.getMethod(serviceName,keyParam[0],valueParam[0],classParam[0]);
		objects = (List<?>) ejbMethod.invoke(ejbRemote,key,value,workingClass);
		return objects;
		
	}
	
	/****
	 * 
	 * @param repository
	 * @param clazzInterface
	 * @param object
	 * @param serviceName
	 * @throws NamingException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void save(Class<?> repository, Class<?> clazzInterface, Class<?> workingClass, Object object, String serviceName) throws NamingException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class[] objectParam = new Class[1];	
		objectParam[0] =  Object.class;
    	Class[] classParam = new Class[1];	
		classParam[0] =  workingClass.getClass();
		Object ejbRemote = this.context.lookup(doLookup(repository,clazzInterface));
		Method ejbMethod =  clazzInterface.getMethod(serviceName,objectParam[0],classParam[0]);
		ejbMethod.invoke(ejbRemote,object, workingClass);
	}
	
	
     /******
      * @author Sérgio da Victória
      * @
      * @param repository Nome da Implementação, Repository
      * @param clazzInterface Classe Interface para lookup 
      * @param serviceName Nome do Serviço
      * @param key    Atributo para bosca 
      * @param value  Valor do Atributo para Busca
      * @param keyNew Atributo para update
      * @param valueNew Valor do Atributo update
      * @throws NamingException
      * @throws NoSuchMethodException
      * @throws SecurityException
      * @throws IllegalAccessException
      * @throws IllegalArgumentException
      * @throws InvocationTargetException
      */

	public void update(Class<?> repository, Class<?> clazzInterface, Class<?> workingClass, String serviceName, String key, Object value, String keyNew, Object valueNew) throws NamingException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	
		Class[] keyParam = new Class[2];	
		keyParam[0] = String.class;
		keyParam[1] = String.class;

		
		Class[] valueParam = new Class[2];	
		valueParam[0] = Object.class; 
		valueParam[1] = Object.class; 
				
		Class[] classParam = new Class[1];	
		classParam[0] =  workingClass.getClass();
				
		
		logger.info("Fazendo remote invoke "+doLookup(repository,clazzInterface));
		Object ejbRemote = this.context.lookup(doLookup(repository,clazzInterface));
		Method ejbMethod =  clazzInterface.getMethod(serviceName,keyParam[0],valueParam[0],keyParam[1],valueParam[1],classParam[0]);
		ejbMethod.invoke(ejbRemote,key,value,keyNew,valueNew,workingClass);

		
	}
	
	
    /******
     * @author Sérgio da Victória
     * @
     * @param repository Nome da Implementação, Repository
     * @param clazzInterface Classe Interface para lookup 
     * @param serviceName Nome do Serviço
     * @param key    Atributo para bosca 
     * @param value  Valor do Atributo para Busca
     * @param map Mapeamento dos Valores para Update
     * @throws NamingException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */

	public void update(Class<?> repository, Class<?> clazzInterface, Class<?> workingClass, String serviceName, String key, Object value, Map<String, Object> map) throws NamingException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	
		Class[] keyParam = new Class[2];	
		keyParam[0] = String.class;

		
		Class[] valueParam = new Class[1];	
		valueParam[0] = Object.class; 
		
		Class[] valueMap = new Class[1];	
		valueMap[0] = Map.class; 

				
		Class[] classParam = new Class[1];	
		classParam[0] =  workingClass.getClass();
				
		
		logger.info("Fazendo remote invoke "+doLookup(repository,clazzInterface));
		Object ejbRemote = this.context.lookup(doLookup(repository,clazzInterface));
		Method ejbMethod =  clazzInterface.getMethod(serviceName,keyParam[0],valueParam[0],valueMap[0],classParam[0]);
		ejbMethod.invoke(ejbRemote,key,value,map,workingClass);

		
	}
	
	
	
	private String doLookup(Class<?> repository, Class<?> clazzInterface) {
		return _PACKAGE+repository.getSimpleName()+"!"+clazzInterface.getCanonicalName();
	}
	
	
	public static void main(String[] args) {
	}

}
