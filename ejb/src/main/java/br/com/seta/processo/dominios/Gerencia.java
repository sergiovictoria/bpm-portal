package br.com.seta.processo.dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Gerencia implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final List<String> _GERENCIA = new ArrayList<String>();
	
	
	static{
		_GERENCIA.add("Diretoria de Controller");
		_GERENCIA.add("FC - Fiscal"); 
		_GERENCIA.add("Diretoria Relações Financeiras"); 
		_GERENCIA.add("Diretoria de TI"); 
		_GERENCIA.add("Diretoria de RH Estratégico"); 
		_GERENCIA.add("Jurídico"); 
		_GERENCIA.add("Líderes empresariais - ADM"); 
		_GERENCIA.add("Comitê Diretivo"); 
		_GERENCIA.add("Diretoria Operacional"); 
		_GERENCIA.add("LJ - Custo Geral de loja"); 
		_GERENCIA.add("Diretoria Logística");
		_GERENCIA.add("Líderes empresariais - OP");
		_GERENCIA.add("Gerência comercial");
	}
	
	public static List<String> getValores(){
		return Collections.unmodifiableList(_GERENCIA);		
	}
	


}
