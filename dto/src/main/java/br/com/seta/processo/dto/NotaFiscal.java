package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class NotaFiscal implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private String situacao;
	private String requisicoes;
	private BigDecimal nronota;
	private java.util.Date dataExecucao;
	
	

	public NotaFiscal( ) {
	}


	public NotaFiscal(String situacao, String requisicoes, BigDecimal nronota) {
		this.situacao = situacao;
		this.requisicoes = requisicoes;
		this.nronota = nronota;
	}



	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getRequisicoes() {
		return requisicoes;
	}


	public void setRequisicoes(String requisicoes) {
		this.requisicoes = requisicoes;
	}


	public BigDecimal getNronota() {
		return nronota;
	}


	public void setNronota(BigDecimal nronota) {
		this.nronota = nronota;
	}


	public java.util.Date getDataExecucao() {
		return dataExecucao;
	}


	public void setDataExecucao(java.util.Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	
	
	



}
