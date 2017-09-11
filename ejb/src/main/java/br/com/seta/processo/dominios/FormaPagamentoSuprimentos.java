package br.com.seta.processo.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FormaPagamentoSuprimentos {
	
	private static final List<String> FORMAS_PAGTOS = new ArrayList<String>();
	
	static{
		FORMAS_PAGTOS.add("Boleto");
		FORMAS_PAGTOS.add("Crédito em Conta");
	}
	
	public static List<String> getValores(){
		return Collections.unmodifiableList(FORMAS_PAGTOS);
	}

}
