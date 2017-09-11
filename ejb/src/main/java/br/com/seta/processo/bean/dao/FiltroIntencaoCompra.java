package br.com.seta.processo.bean.dao;

import java.io.Serializable;
import java.util.Date;

public class FiltroIntencaoCompra implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String RECEBIMENTO = "Recebimento - Suprimentos";
	public static final String SUPRIMENTOS = "Processo de Suprimentos";
	
	private String processo;
	private Long caseId;
	private String solicitante;
	private String fornecedor;
	private String empresa;
	private String tipoDespesa;
	private String status;
	private Date inicio;
	private Date fim;
	
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public String getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProcesso() {
		return processo;
	}
	public void setProcesso(String processo) {
		this.processo = processo;
	}
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	@Override
	public String toString() {
		return "FiltroIntencaoCompra [processo=" + processo + ", caseId="
				+ caseId + ", solicitante=" + solicitante + ", fornecedor="
				+ fornecedor + ", empresa=" + empresa + ", tipoDespesa="
				+ tipoDespesa + ", status=" + status + ", inicio=" + inicio
				+ ", fim=" + fim + "]";
	}	
}
