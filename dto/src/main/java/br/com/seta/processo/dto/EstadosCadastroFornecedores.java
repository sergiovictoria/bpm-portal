package br.com.seta.processo.dto;


/**
 * Contém todos os estados possíveis do processo de Cadastro de Fornecedor do Bonita BPM. Estes estados serão utilizados nos decision gateways
 * do processo para controlar o fluxo da instância
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class EstadosCadastroFornecedores {
	/**
	 * O cadastro do fornecedor está em Pré-Análise pela área de Comercial/CSC
	 */
	public static final StatusCadastroFornecedor EM_PRE_ANALISE = new StatusCadastroFornecedor("Em pré-análise");
	
	/**
	 *O cadastro do fornecedor foi pré-analisado pela área de Comercial/CSC 
	 */
	public static final StatusCadastroFornecedor PRE_ANALISADO = new StatusCadastroFornecedor("Pré-analisado");	
	
	/**
	 * O cadstro do fornecedor está pendente de aprovação pela Gerencia Comercial
	 */
	public static final StatusCadastroFornecedor PENDENTE_DE_APROVACAO = new StatusCadastroFornecedor("Pendente de aprovação");
	
	/**
	 * O cadastro do fornecedor foi aprovado pelo Gerente Comercial
	 */
	public static final StatusCadastroFornecedor APROVADO_PELO_GERENTE  = new StatusCadastroFornecedor("Aprovado");
	
	/**
	 * O cadastro do fornecedor foi rejeitado pelo Gerente Comercial
	 */
	public static final StatusCadastroFornecedor REJEITADO_PELO_GERENTE = new StatusCadastroFornecedor("Rejeitado");
	
	/**
	 * Os dados provenientes da planilha (excel ou ods) do fornecedor estão incorretos segundo avaliação do Cadastro
	 */
	public static final StatusCadastroFornecedor INCORRETO = new StatusCadastroFornecedor("Incorreto");	
	
	/**
	 * Os dados provenientes da planilha do fornecedor foram corrigidos
	 */
	public static final StatusCadastroFornecedor CORRIGIDO = new StatusCadastroFornecedor("Corrigido");
	
	/**
	 * Os dados provenientes da planilha foram integrados com a Consinco
	 */
	public static final StatusCadastroFornecedor INTEGRADO_CONSINCO = new StatusCadastroFornecedor("Integrado com a Consinco");
	
	/**
	 * Os dados provenientes da planilha (excel ou ods) do fornecedor estão corretos e o cadastro foi finalizado (integrado com a Consinco)
	 */
	public static final StatusCadastroFornecedor FINALIZADO = new StatusCadastroFornecedor("Finalizado");
}
