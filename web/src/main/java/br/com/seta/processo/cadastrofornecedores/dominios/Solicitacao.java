package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * Domínio dos tipos de solicitação existentes para o Cadastro de Fornecedores
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

public class Solicitacao {	
	
	public static final String INCLUSÃO = "INCLUSÃO";
	public static final String ALTERAÇÃO_ATUALIZAÇÃO = "ALTERAÇÃO/ATUALIZAÇÃO";
	public static final String SUBSTITUIÇÃO = "SUBSTITUIÇÃO";
	public static final String REATIVAÇÃO = "REATIVAÇÃO";
	public static final String INATIVAR_FORA_DE_LINHA = "INATIVAR/FORA DE LINHA";
	
	private static final List<String> TIPOS_SOLICITACAO;
	
	static{
		ArrayList<String> tiposSolicitacao = new ArrayList<String>();
		tiposSolicitacao.add(INCLUSÃO);
		tiposSolicitacao.add(ALTERAÇÃO_ATUALIZAÇÃO);
		tiposSolicitacao.add(SUBSTITUIÇÃO);
		tiposSolicitacao.add(REATIVAÇÃO);
		tiposSolicitacao.add(INATIVAR_FORA_DE_LINHA);
		
		TIPOS_SOLICITACAO = Collections.unmodifiableList(tiposSolicitacao);
	}	
	
	/**	  
	 * @return Lista imutável contendo os tipos de solicitação
	 */
	public static final List<String> getTiposSolicitacao(){
		return TIPOS_SOLICITACAO;
	}
}
