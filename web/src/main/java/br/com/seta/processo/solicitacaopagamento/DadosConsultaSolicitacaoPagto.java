package br.com.seta.processo.solicitacaopagamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.dto.SolicitacaoPagamento;

public class DadosConsultaSolicitacaoPagto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long caseId;
	private String fornecedor;
	private Long nroNota;
	private BigDecimal valor;
	private String status;
	private Date dataSolicitacao;
	private String comentario;
	
	public DadosConsultaSolicitacaoPagto(){
		
	}
	
	public DadosConsultaSolicitacaoPagto(InstanciaProcesso instancia, SolicitacaoPagamento solicitacao){
		this.caseId = instancia.getCaseId();
		this.dataSolicitacao = instancia.getInicio();
		this.fornecedor = solicitacao.getFornecedor();
		this.nroNota = solicitacao.getNroNota();
		this.valor = solicitacao.getValor();
		this.status = solicitacao.getHistorico().get(solicitacao.getHistorico().size()-1).getStatus();
		this.comentario = solicitacao.getHistorico().get(solicitacao.getHistorico().size()-1).getComentario();
	}

	public Long getCaseId() {
		return caseId;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public Long getNroNota() {
		return nroNota;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getStatus() {
		return status;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setNroNota(Long nroNota) {
		this.nroNota = nroNota;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "DadosConsultaSolicitacaoPagto [caseId=" + caseId
				+ ", fornecedor=" + fornecedor + ", nroNota=" + nroNota
				+ ", valor=" + valor + ", status=" + status
				+ ", dataSolicitacao=" + dataSolicitacao + ", comentario="
				+ comentario + "]";
	}
	
}