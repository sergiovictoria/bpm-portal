/**
 * 
 */
package br.com.seta.processo.constant;

import java.io.Serializable;

/**
 * @author Sérgio da Victória
 *
 *   
 */
public final class StatusSolicitacaoIntencaoCompra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String SOCITACAO_ABERTA                = "Solicitante - Preenchimento";
	public static final String APROVADO_FUNCIONAL_APROVADO     = "Aprovado Funcional - Aprovado";
	public static final String APROVADO_FUNCIONAL_REJEITADO    = "Aprovado Funcional - Rejeitado";
	public static final String APROVADOR_HIERARQUICO_APROVADO  = "Aprovado Hierárquico - Aprovado";
	public static final String APROVADOR_HIERARQUICO_REJEITADO = "Aprovado Hierárquico - Rejeitado";
	public static final String VALIDA_NATUREZA_DESPESA_CHECKIN = "CheckIn – Validar Natureza de Despesa";
	public static final String SOLICITANTE_ABRIR_REQUISICAO    = "Solicitante – Abrir Requisição";
	public static final String SOLICITANTE_CANCELA_REQUISICAO  = "Solicitante – Cancelar Intenção";
	public static final String SOLICITANTE_ALTERAR_REQUISICAO  = "Solicitante – Alterar Intenção";
	public static final String SOLICITANTE_MAIS_INFORMACOES    = "Cadastro – Incluir Itens (Solicitar mais Informações)";
	public static final String SOLICITANTE_CADASTRADO_SUCESSO  = "Cadastro – Incluir Itens / Fornecedor (Enviado para C5)";
	public static final String SOLICITANTE_CADASTRADO_VERIFICA = "Solicitante – Verificar informações para cadastro";
	public static final String SOLICITANTE_CADASTRADO_SOLICITA = "Cadastro – Incluir Itens - Fornecedor (Solicitar mais Informações)";
	public static final String SOLICITANTE_JURIDICO_ANALISE    = "Jurídico – Contrato enviado para análise";
	public static final String SOLICITANTE_JURIDICO_CANCELADO  = "Jurídico – Intenção cancelada";
	
	

}
