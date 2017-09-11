package br.com.seta.processo.servicos;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;


public class PropertiesDtoUtils {

	private Properties props = new Properties();
	private static PropertiesDtoUtils propertiesUtils;
	private static final Map<String, String> env = System.getenv();	
	private static final String _SEPARATOR = System.getProperty("file.separator");
	
	
	/*****
	 * 
	 * @return Singleton Object  
	 */
	
	public static PropertiesDtoUtils getInstance(){
		if (propertiesUtils == null){
			propertiesUtils = new PropertiesDtoUtils();
		}
		return propertiesUtils;
	}
	
	
	public PropertiesDtoUtils() {
		try{ 
		    FileInputStream is  = new FileInputStream(new File((String)env.get("BPM_PROPERTIES")+_SEPARATOR+"processo.properties"));  
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
			throw new IllegalArgumentException("Valor da propriedade está invalido ");
		}
		return returnValue;
	}

	public static void main(String[] args) {
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
		      System.out.format("%s=%s%n", envName, env.get(envName));
		}
	}
}
