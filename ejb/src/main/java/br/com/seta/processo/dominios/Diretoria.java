package br.com.seta.processo.dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Diretoria implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final List<String> _DIRETORIA = new ArrayList<String>();
	
	static{
		_DIRETORIA.add("Administrativo");
		_DIRETORIA.add("Comercial"); 
		_DIRETORIA.add("Operacional"); 
	}
	
	public static List<String> getValores(){
		return Collections.unmodifiableList(_DIRETORIA);		
	}
	


}
