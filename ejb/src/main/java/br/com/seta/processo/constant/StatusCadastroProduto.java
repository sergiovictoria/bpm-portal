package br.com.seta.processo.constant;

/**
 * Domínio contendo os status possíveis do processo de cadastro de produto
 * 
 * @author Sérgio da Victória
 *
 */
public class StatusCadastroProduto {
	public static final String CADASTRO_PRE_ANALISADO       = "Analista CSC - Pré-Cadastro revisado";
	public static final String SOLICITANTE_CORRIGIDO        = "Solicitante – Corrigido";
	public static final String GERENTE_COMERCIAL_APROVADO   = "Gerente Comercial – Aprovado";
	public static final String GERENTE_COMERCIAL_REJEITADO  = "Gerente Comercial – Rejeitado";
	public static final String SOLICITANTE_PRE_CADASTRO     = "Solicitante – Pré-Cadastro";
	public static final String CADASTRO_DEFINIR_CLASSIFICAO = "Precificação – Classificação Comercial";
	public static final String LOGISTICA_CADASTRO_APROVADO  = "Logística – Cadastro Aprovado";
	public static final String LOGISTICA_CADASTRO_REJEITADO = "Logística – Cadastro Rejeitado";
	public static final String LOGISTICA_CORRECAO           = "Analista CSC – Dados Corrigidos para Logística";
	public static final String CADASTRO_SOLICITA_CORRECAO   = "Cadastro – Solicitada Correção ao CSC";
	public static final String CADASTRO_REVISADO            = "Cadastro – Informações Revisada pelo CSC";
	public static final String ANALISTA_DADOS_CORRIGIDO     = "Analista CSC – Dados Corrigidos para Cadastro";
	public static final String FISCAL_SOLICITA_CORRECAO = "Fiscal - Solicitada Correção para CSC";
	public static final String FISCAL_CADASTRO_APROVADO = "Fiscal - Cadastro Aprovado";
	public static final String DADOS_FISCAIS_CORRIGIDOS = "Analista CSC – Dados Corrigidos para Fiscal";
}
