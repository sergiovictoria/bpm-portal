package br.com.seta.processo.utils;

import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtils {

	private Properties props = new Properties();
	private static PropertiesEJBUtils propertiesUtils;
		
	
	/*****
	 * 
	 * @return Singleton Object  
	 */
	
	public static PropertiesEJBUtils getInstance(){
		if (propertiesUtils == null){
			propertiesUtils = new PropertiesEJBUtils();
		}
		return propertiesUtils;
	}
	
	
	public PropertiesUtils() {
		try{ 
		    InputStream is = this.getClass().getClassLoader().getResourceAsStream("processo.properties");  
			this.props.load(is);
			is.close();
		}catch (Exception e ) {
			throw new IllegalArgumentException("Valor da propriedade está invalido ");
		}
		
	}

	public String getValues(final String chave){
		String returnValue = null;
		try {
			returnValue=(String)props.getProperty(chave);
		} catch(Exception e){
			throw new IllegalArgumentException("Valor da propriedade está invalido   [ "+props.getProperty(chave) +"  ] ");
		}
		return returnValue;
	}

}
