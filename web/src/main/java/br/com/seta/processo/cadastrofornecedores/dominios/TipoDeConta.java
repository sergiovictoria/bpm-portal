package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Domínio de Tipo de Contas do processo de Cadastror de Fornecedor
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class TipoDeConta {
	
	private static final List<String> TIPOS_DE_CONTA;
	
	static{
		ArrayList<String> tiposDeConta = new ArrayList<String>();
		tiposDeConta.add("CONTA CORRENTE INDIVIDUAL");
		tiposDeConta.add("CONTA CORRENTE CONJUNTA");
		tiposDeConta.add("DEPÓSITO JUDICIAL INDIVIDUAL");
		tiposDeConta.add("DEPÓSITO JUDICIAL CONJUNTA");
		tiposDeConta.add("POUPANÇA INDIVIDUAL");
		tiposDeConta.add("POUPANÇA CONJUNTA");
		TIPOS_DE_CONTA = Collections.unmodifiableList(tiposDeConta);
	}
	
	public static List<String> getTiposDeConta(){
		return TIPOS_DE_CONTA;
	}

}
