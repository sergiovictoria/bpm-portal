package br.com.seta.processo.mapping;

import java.util.HashMap;

/**
 * Classe que representa a resposta a um contrato do Bonita
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class BonitaResponseMessage extends HashMap<String, Object> {	

	private static final long serialVersionUID = 1L;

	public BonitaResponseMessage(){
		
	}
	
	public BonitaResponseMessage add(String key, Object value){
		this.put(key, value);
		return this;
	}
}
