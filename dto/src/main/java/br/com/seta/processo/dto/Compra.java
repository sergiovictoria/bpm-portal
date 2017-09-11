package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Compra implements Serializable {


	private static final long serialVersionUID = 1L;
		
	private BigDecimal nrorequisicao;
	private String status;
	private java.util.Date dtacompra;
	private java.util.Date dataExecucao;
	

	public Compra( ) {
	}
	
	
	public Compra(BigDecimal nrorequisicao, String status, Date dtacompra) {
		this.nrorequisicao = nrorequisicao;
		this.status = status;
		this.dtacompra = dtacompra;
	}
	
	
	public BigDecimal getNrorequisicao() {
		return nrorequisicao;
	}
	public void setNrorequisicao(BigDecimal nrorequisicao) {
		this.nrorequisicao = nrorequisicao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.util.Date getDtacompra() {
		return dtacompra;
	}
	public void setDtacompra(java.util.Date dtacompra) {
		this.dtacompra = dtacompra;
	}


	public java.util.Date getDataExecucao() {
		return dataExecucao;
	}


	public void setDataExecucao(java.util.Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	
	
	
	


	

}
