package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Domínio dos tipos de logradouro do Cadastro de Fornecedores
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class TipoLogradouro {
	
	private static List<String> TIPOS_LOGRADOURO;
	
	static{
		ArrayList<String> tiposLogradouro = new ArrayList<String>();
		tiposLogradouro.add("ALAMEDA");
		tiposLogradouro.add("APARTAMENTO");
		tiposLogradouro.add("AVENIDA");
		tiposLogradouro.add("BECO");
		tiposLogradouro.add("BLOCO");
		tiposLogradouro.add("CAMINHO");
		tiposLogradouro.add("ESCADINHA");
		tiposLogradouro.add("ESTAÇÃO");
		tiposLogradouro.add("ESTRADA");
		tiposLogradouro.add("FAZENDA");
		tiposLogradouro.add("FORTALEZA");
		tiposLogradouro.add("GALERIA");
		tiposLogradouro.add("LADEIRA");
		tiposLogradouro.add("LARGO");
		tiposLogradouro.add("PRAÇA");
		tiposLogradouro.add("PARQUE");
		tiposLogradouro.add("PRAIA");
		tiposLogradouro.add("QUADRA");
		tiposLogradouro.add("QUILÔMETRO");
		tiposLogradouro.add("QUINTA");
		tiposLogradouro.add("RODOVIA");
		tiposLogradouro.add("RUA");
		tiposLogradouro.add("SUPER QUADRA");
		tiposLogradouro.add("TRAVESSA");
		tiposLogradouro.add("VIADUTO");
		tiposLogradouro.add("VILA");
		TIPOS_LOGRADOURO = Collections.unmodifiableList(tiposLogradouro);
	}
	
	/**
	 * 
	 * @return Uma lista imutável contendo os tipos dos logradouros
	 */
	public static List<String> getTiposLogradouro(){
		return TIPOS_LOGRADOURO;
	}

}
