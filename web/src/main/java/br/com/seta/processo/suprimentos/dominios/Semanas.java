package br.com.seta.processo.suprimentos.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class Semanas {
	
	private static final List<String> _SEMANA;
	
	static{
		ArrayList<String> semana = new ArrayList<String>();
		
		semana.add("Domingo");
		semana.add("Segunda-Feira");
		semana.add("Terça-Feira");
		semana.add("Quarta-Feira");
		semana.add("Quinta-Feira");
		semana.add("Sexta-Feira");
		semana.add("Sábado");
		
		_SEMANA = Collections.unmodifiableList(semana);
	}

	public static List<String> getSemana() {
		return _SEMANA; 
	}
}
