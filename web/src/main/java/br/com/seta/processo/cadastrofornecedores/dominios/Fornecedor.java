package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.List;

/**
 * Dados do domínio de fornecedores
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Fornecedor {

	private static final List<String> FORNECEDORES;
	
	static{
		ArrayList<String> fornecedores = new ArrayList<String>();
		fornecedores.add("FORNECEDOR");
		fornecedores.add("PRESTADOR DE SERVIÇO");
		FORNECEDORES = fornecedores;
	}
	
	/**
	 * 
	 * @return Uma lista imutável contendo os nomes dos forncedores
	 */
	public static List<String> getFornecedores(){
		return FORNECEDORES;
	}
}
