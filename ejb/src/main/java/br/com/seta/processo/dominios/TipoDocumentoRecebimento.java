package br.com.seta.processo.dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TipoDocumentoRecebimento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final List<String> TIPOS_DOCS = new ArrayList<String>();
	
	static{
		TIPOS_DOCS.add("Boleto");
		TIPOS_DOCS.add("Danfe");
		TIPOS_DOCS.add("NF");
		TIPOS_DOCS.add("Outro");
	}
	
	public static List<String> getValores(){
		return Collections.unmodifiableList(TIPOS_DOCS);
	}	
	
}
