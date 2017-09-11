package br.com.seta.processo.bean.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FiltroSolicitacaoPagamento implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private String userNameSolicitante;
	private BigDecimal codFornecedor;
	private Long nroNota;
	private BigDecimal valorDe;
	private BigDecimal valorAte;
	private Date inicioDataSolicitacao;
	private Date fimDataSolicitacao;
	private String status;	

	public FiltroSolicitacaoPagamento(){
		
	}
	
	public FiltroSolicitacaoPagamento(String userNameSolicitante,
			BigDecimal codFornecedor, Long nroNota, BigDecimal valorDe,
			BigDecimal valorAte, Date inicioDataSolicitacao, Date fimDataSolicitacao, String status) {
		super();
		this.userNameSolicitante = userNameSolicitante;
		this.codFornecedor = codFornecedor;
		this.nroNota = nroNota;
		this.valorDe = valorDe;
		this.valorAte = valorAte;
		this.inicioDataSolicitacao = inicioDataSolicitacao;
		this.fimDataSolicitacao = fimDataSolicitacao;
		this.status = status;
	}

	public String getUserNameSolicitante() {
		return userNameSolicitante;
	}

	public BigDecimal getCodFornecedor() {
		return codFornecedor;
	}

	public Long getNroNota() {
		return nroNota;
	}

	public BigDecimal getValorDe() {
		return valorDe;
	}

	public BigDecimal getValorAte() {
		return valorAte;
	}

	public String getStatus() {
		return status;
	}	

	public void setUserNameSolicitante(String userNameSolicitante) {
		this.userNameSolicitante = userNameSolicitante;
	}

	public void setCodFornecedor(BigDecimal codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public void setNroNota(Long nroNota) {
		this.nroNota = nroNota;
	}

	public void setValorDe(BigDecimal valorDe) {
		this.valorDe = valorDe;
	}

	public void setValorAte(BigDecimal valorAte) {
		this.valorAte = valorAte;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

	public Date getInicioDataSolicitacao() {
		return inicioDataSolicitacao;
	}

	public Date getFimDataSolicitacao() {
		return fimDataSolicitacao;
	}

	public void setInicioDataSolicitacao(Date inicioDataSolicitacao) {
		this.inicioDataSolicitacao = inicioDataSolicitacao;
	}

	public void setFimDataSolicitacao(Date fimDataSolicitacao) {
		this.fimDataSolicitacao = fimDataSolicitacao;
	}

	@Override
	public String toString() {
		return "FiltroSolicitacaoPagamento [userNameSolicitante="
				+ userNameSolicitante + ", codFornecedor=" + codFornecedor
				+ ", nroNota=" + nroNota + ", valorDe=" + valorDe
				+ ", valorAte=" + valorAte + ", vencDe=" + inicioDataSolicitacao
				+ ", vencAte=" + fimDataSolicitacao + ", status=" + status + "]";
	}	
	
}
