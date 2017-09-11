package br.com.seta.processo.relatorios.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estado {
	
	private static final List<String> ESTADOS;
	
	static{
		ArrayList<String> estados = new ArrayList<String>();
		estados.add("Todos");
		estados.add("Disponível");
//		estados.add("Em trabalho");
		estados.add("Finalizado");
		
		ESTADOS = Collections.unmodifiableList(estados);
	}
	
	/**
	 * Lista imutável contendo o domínio dos bancos do Processo de Cadastro de Fornecedor
	 * @return
	 */
	public static List<String> getEstados(){
		return ESTADOS;
	}
}
