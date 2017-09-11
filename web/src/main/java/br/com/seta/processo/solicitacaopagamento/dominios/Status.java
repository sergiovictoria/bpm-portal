package br.com.seta.processo.solicitacaopagamento.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Status {
	
	private static final List<String> STATUS;
	
	static{
		ArrayList<String> status = new ArrayList<String>();
		status.add("Aprovado");
		status.add("Em Análise");
		status.add("Rejeitado");
		
		STATUS = Collections.unmodifiableList(status);
	}

	public static List<String> getStatus() {
		return STATUS;
	}
}
