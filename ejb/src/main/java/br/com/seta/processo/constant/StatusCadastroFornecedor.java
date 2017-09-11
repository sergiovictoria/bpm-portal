package br.com.seta.processo.constant;

import java.io.Serializable;

/**
 * Domínio contendo os status possíveis do processo de cadastro de fornecedor
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class StatusCadastroFornecedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String CADASTRO_PRE_ANALISADO = "Cadastro - Pré-Analisado";
	public static final String CADASTRO_SOLICITACAO_CORRECAO = "Cadastro – Solicitado Correção";
	public static final String CADASTRO_INTEGRADO_CONSINCO = "Cadastro – Integração Consinco";
	public static final String CADASTRO_FINALIZADO = "Cadastro – Finalizado";
	public static final String SOLICITANTE_PREENCHIMENTO = "Solicitante – Preenchimento";
	public static final String SOLICITANTE_CORRIGIDO = "Solicitante – Corrigido";
	public static final String GERENTE_COMERCIAL_APROVADO = "Gerente Comercial – Aprovado";
	public static final String GERENTE_COMERCIAL_REJEITADO = "Gerente Comercial – Rejeitado";
}
