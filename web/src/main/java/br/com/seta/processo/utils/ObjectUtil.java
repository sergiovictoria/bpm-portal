package br.com.seta.processo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;


public final class ObjectUtil {


	private static Logger logger = Logger.getLogger(ObjectUtil.class);

	/***
	 * 
	 * @author Sérgio da Victória
	 * @param valueObj
	 * @return Map Field
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */

	public static Map<String, Object> getFieldNamesAndValues(final Object valueObj) throws IllegalArgumentException, IllegalAccessException	{
		logger.info("Begin - getFieldNamesAndValues");
		Class<? extends Object> clazz = valueObj.getClass();
		logger.info("Class name got is:: " + clazz.getName());
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Field[] valueObjFields = clazz.getDeclaredFields();
		for (Field field : valueObjFields) {
			String fieldName = field.getName();
			logger.info("Getting Field Values for Field:: " + field.getName());
			field.setAccessible(true);
			Object object = field.get(valueObj);
			logger.info("Value of field" + fieldName + "newObj:: " + object);
			fieldMap.put(fieldName, object);
		}
		logger.info("End - getFieldNamesAndValues");
		return fieldMap;
	}


	/***
	 * 
	 * @author Sérgio da Victória
	 * @param valueObj
	 * @return Map Field
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException 
	 */


	public static Map<String, Object> getFieldNamesAndValues(Class<? extends Object> clazz) throws IllegalArgumentException, IllegalAccessException, InstantiationException	{
		logger.info("Begin - getFieldNamesAndValues");
		logger.info("Class name got is:: " + clazz.getName());
		Object valueObj = clazz.newInstance();
		logger.info("Getting Object  new Object:: " + valueObj.getClass().getCanonicalName());
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Field[] valueObjFields = clazz.getDeclaredFields();
		for (Field field : valueObjFields) {
			String fieldName = field.getName();
			field.setAccessible(true);
			Object object = field.get(valueObj);
			logger.info("Value of field" + fieldName + "newObj:: " + object);
			fieldMap.put(fieldName, object);
		}
		logger.info("End - getFieldNamesAndValues");
		return fieldMap;
	}

	/***
	 * 	
	 * @param clazz
	 * @return List Field to Map Object 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static List<String> getFieldNames(Class<? extends Object> clazz) throws IllegalArgumentException, IllegalAccessException, InstantiationException	{
		List<String> fieldNames = new ArrayList<String>(0);
		Field[] valueObjFields = clazz.getDeclaredFields();
		for (Field field : valueObjFields) {
			String fieldName = field.getName();
			if (!fieldName.equals("serialVersionUID"))
				fieldNames.add(fieldName);
		}
		return fieldNames;
	}


	/**
	 * @author Sérgio da Victória
	 * @param className
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */

	public static Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = Class.forName(className);
		Object object = clazz.newInstance();
		return object;
	}

	/***
	 * 
	 * @author Sérgio da Victória
	 * @param className
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getStaticValue(final String className, final String fieldName) throws SecurityException, NoSuchFieldException, ClassNotFoundException,	IllegalArgumentException, IllegalAccessException {
		final Field field = Class.forName(className).getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(Class.forName(className));
	}


	/***
	 * @author Sérgio da Victória
	 * @param className
	 * @param fieldName
	 * @param newValue
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setStaticValue(final String className, final String fieldName, final Object newValue) throws SecurityException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		final Field field = Class.forName(className).getDeclaredField(fieldName);
		field.setAccessible(true);
		final Object oldValue = field.get(Class.forName(className));
		field.set(oldValue, newValue);
	}


	/***
	 * @author Sérgio da Victória
	 * @param classInstance
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getInstanceValue(final Object classInstance, final String fieldName) throws SecurityException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		final Field field = classInstance.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(classInstance);
	}



	/***
	 * @author Sérgio da Victória
	 * @param classInstance
	 * @param fieldName
	 * @param newValue
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setInstanceValue(final Object classInstance, final String fieldName, final Object newValue) throws SecurityException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		final Field field = classInstance.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(classInstance, newValue);
	}


	public void imprime( ) {
		int b = 2;
		System.out.println("" + b + 3);
		System.out.println(+ b + 3 );
		String x = "Seta";
		String y = x;
		System.out.println("y = " + y);
		x = x + "Atacadista";
		System.out.println("y = " + y);
	}


	public static void load () {
//		Map<String, String> mapa = new Map<String, String>();

	}


	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		new ObjectUtil().imprime();
		//		ObjectUtil objectUtil = new ObjectUtil();
		//
		//		List<String> list = objectUtil.getFieldNames(Atividade.class);
		//		for (String string : list) {
		//			System.out.println("string ------------> "+string);
		//		}

		//		new ObjectUtil().getFieldNames(Atividade.class);
		//		new ObjectUtil().getFieldNamesAndValues(Atividade.class);
		//		new ObjectUtil().getMethodsNamesAndValues(Atividade.class);
	}

}
