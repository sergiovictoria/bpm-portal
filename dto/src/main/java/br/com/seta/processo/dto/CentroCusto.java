package br.com.seta.processo.dto;

import java.math.BigDecimal;


public class CentroCusto implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	
	
	 private BigDecimal nrolinha;
	 private String nroplanilha;
	 private BigDecimal nroempresa;
	 private String filial;
	 private BigDecimal contadebito;
	 private BigDecimal centrocustodb;
	 private BigDecimal contacredito;
	 private String historico;
	 private BigDecimal codhistorico;
	 private String tipoentidadecr;
	 private String tipoentidadedb;
	 private BigDecimal codentidadedb;
	 private BigDecimal codentidadecr;
	 private BigDecimal centrocustocr;
	 
	 
	public BigDecimal getNrolinha() {
		return nrolinha;
	}
	public void setNrolinha(BigDecimal nrolinha) {
		this.nrolinha = nrolinha;
	}
	public String getNroplanilha() {
		return nroplanilha;
	}
	public void setNroplanilha(String nroplanilha) {
		this.nroplanilha = nroplanilha;
	}
	public BigDecimal getNroempresa() {
		return nroempresa;
	}
	public void setNroempresa(BigDecimal nroempresa) {
		this.nroempresa = nroempresa;
	}
	public String getFilial() {
		return filial;
	}
	public void setFilial(String filial) {
		this.filial = filial;
	}
	public BigDecimal getContadebito() {
		return contadebito;
	}
	public void setContadebito(BigDecimal contadebito) {
		this.contadebito = contadebito;
	}
	public BigDecimal getCentrocustodb() {
		return centrocustodb;
	}
	public void setCentrocustodb(BigDecimal centrocustodb) {
		this.centrocustodb = centrocustodb;
	}
	public BigDecimal getContacredito() {
		return contacredito;
	}
	public void setContacredito(BigDecimal contacredito) {
		this.contacredito = contacredito;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public BigDecimal getCodhistorico() {
		return codhistorico;
	}
	public void setCodhistorico(BigDecimal codhistorico) {
		this.codhistorico = codhistorico;
	}
		
	public String getTipoentidadecr() {
		return tipoentidadecr;
	}
	public void setTipoentidadecr(String tipoentidadecr) {
		this.tipoentidadecr = tipoentidadecr;
	}
	public BigDecimal getCodentidadedb() {
		return codentidadedb;
	}
	public void setCodentidadedb(BigDecimal codentidadedb) {
		this.codentidadedb = codentidadedb;
	}
	public BigDecimal getCodentidadecr() {
		return codentidadecr;
	}
	public void setCodentidadecr(BigDecimal codentidadecr) {
		this.codentidadecr = codentidadecr;
	}
	public BigDecimal getCentrocustocr() {
		return centrocustocr;
	}
	public void setCentrocustocr(BigDecimal centrocustocr) {
		this.centrocustocr = centrocustocr;
	}
	public String getTipoentidadedb() {
		return tipoentidadedb;
	}
	public void setTipoentidadedb(String tipoentidadedb) {
		this.tipoentidadedb = tipoentidadedb;
	}
	
	@Override
	public String toString() {
		return "CentroCusto [nrolinha=" + nrolinha + ", nroplanilha="
				+ nroplanilha + ", nroempresa=" + nroempresa + ", filial="
				+ filial + ", contadebito=" + contadebito + ", centrocustodb="
				+ centrocustodb + ", contacredito=" + contacredito
				+ ", historico=" + historico + ", codhistorico=" + codhistorico
				+ ", tipoentidadecr=" + tipoentidadecr + "]";
	}
	

	
	 




}
