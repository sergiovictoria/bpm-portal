package br.com.seta.processo.dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TipoDivergencia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String PARCIAL = "Parcial";
	public static final String TOTAL = "Total";
	private static final List<String> TIPOS_DIVERGENCIA = new ArrayList<String>();
	
	static{
		TIPOS_DIVERGENCIA.add(PARCIAL);
		TIPOS_DIVERGENCIA.add(TOTAL);		
	}
	
	public static List<String> getValores(){
		return Collections.unmodifiableList(TIPOS_DIVERGENCIA);
	}	

}
