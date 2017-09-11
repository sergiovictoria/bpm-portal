package br.com.seta.processo.dto;


/**
 * Contém todos os estados possíveis do processo de Cadastro de Fornecedor do Bonita BPM. Estes estados serão utilizados nos decision gateways
 * do processo para controlar o fluxo da instância
 * 
 * @author Sérgio da Victória
 *
 */

public class EstadosCadastroProdutos {

	public static final String EM_PRE_ANALISE = "Em pré-análise";
	
	public static final StatusProcesso APROVADO   = new StatusProcesso("Aprovado");

	public static final StatusProcesso REJEITADO  = new StatusProcesso("Rejeitado");
	
	public static final StatusProcesso FINALIZADO = new StatusProcesso("Finalizado");
	
	public static final StatusProcesso ENVIADO_PARA_CORRRECAO = new StatusProcesso("Enviado para correção");
	
	public static final StatusProcesso AGUARDANDO_NOVA_ACAO = new StatusProcesso("");
	
}
