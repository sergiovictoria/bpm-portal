package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Domínio dos tipos de conta do processo de Cadastrar Fornecedor
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class TipoDaConta {
	
	private static final List<String> TIPOS_DA_CONTA;
	
	static{
		ArrayList<String> tiposDaConta = new ArrayList<String>();
		tiposDaConta.add("CONTA PESSOA JURÍDICA");
		tiposDaConta.add("CONTA DE TERCEIRO");
		tiposDaConta.add("CONTA PARA PAGAMENTOS VIA DEPÓSITO");
		TIPOS_DA_CONTA = Collections.unmodifiableList(tiposDaConta);
	}
	
	/**
	 * @return Retorna uma lista imutável com os tipos de contas do processo de Cadastro de Fornecedor
	 */
	public static List<String> getTiposDaConta(){
		return TIPOS_DA_CONTA;
	}

}
