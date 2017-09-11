package br.com.seta.processo.utils;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

/**
 * Classe com conjunto de métodos utilitários para o Velocity
 * 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class VelocityUtils {
	
	public static Template getTemplate(String path, String nomeTemplate){
		VelocityEngine velocityEngine  = new VelocityEngine();		
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path", path);
		return velocityEngine.getTemplate(nomeTemplate);
	}

}
