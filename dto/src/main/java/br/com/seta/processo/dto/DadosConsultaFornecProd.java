package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DadosConsultaFornecProd implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_EM_ANDAMENTO  = "Em andamento";
	public static final String STATUS_APROVADO  = "Aprovado";
	public static final String STATUS_REJEITADO  = "Rejeitado";
	
	public static final String OPERACAO_CAD_PRODUTO = "Cadastro de Produto";
	public static final String OPERACAO_CAD_FORNECEDOR = "Cadastro de Fornecedores";
	
	private Long caseId;
	private String operacao;
	private String descricao;
	private String status;
	private Date dataRequisicao;
	private Date dataAprovacaoRejeicao;
	
	public DadosConsultaFornecProd(Long caseId, String operacao,
			String descricao, String status, Date dataRequisicao, Date dataAprovacaoRejeicao) {
		super();
		this.caseId = caseId;
		this.operacao = operacao;
		this.descricao = descricao;
		this.status = status;
		this.dataRequisicao = dataRequisicao;
		this.dataAprovacaoRejeicao = dataAprovacaoRejeicao;
	}
	
	public static List<String> getOperacoes(){
		return Arrays.asList(OPERACAO_CAD_PRODUTO, OPERACAO_CAD_FORNECEDOR);		
	}
	
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataRequisicao() {
		return dataRequisicao;
	}
	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}
	public Date getDataAprovacaoRejeicao() {
		return dataAprovacaoRejeicao;
	}
	public void setDataAprovacaoRejeicao(Date dataAprovacaoRejeicao) {
		this.dataAprovacaoRejeicao = dataAprovacaoRejeicao;
	}
	

	@Override
	public String toString() {
		return "DadosConsultaFornecProd [caseId=" + caseId + ", operacao="
				+ operacao + ", descricao=" + descricao + ", status=" + status
				+ ", dataRequisicao=" + dataRequisicao
				+ ", dataAprovacaoRejeicao=" + dataAprovacaoRejeicao + "]";
	}
	
	
}
