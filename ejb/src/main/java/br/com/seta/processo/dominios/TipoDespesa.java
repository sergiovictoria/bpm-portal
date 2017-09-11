package br.com.seta.processo.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TipoDespesa {
	
	private static final List<String> TIPOS_DESPESA = new ArrayList<String>();
	
	static{
		TIPOS_DESPESA.add("Serviço");
		TIPOS_DESPESA.add("Produto"); 
	}
	
	public static List<String> getValores(){
		return Collections.unmodifiableList(TIPOS_DESPESA);		
	}

}
