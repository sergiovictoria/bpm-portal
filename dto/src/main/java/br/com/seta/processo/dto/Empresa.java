package br.com.seta.processo.dto;

import java.math.BigDecimal;

public class Empresa implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal nroempresa;
	private String razaosocial; 
	private String fantazia;
	private String endereco;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private BigDecimal nrocgc;
	private BigDecimal digcgc;
	private java.util.Date dataExecucao;		
	
	public String getRazaosocial() {
		return razaosocial;
	}
	public BigDecimal getNroempresa() {
		return nroempresa;
	}
	public void setNroempresa(BigDecimal nroempresa) {
		this.nroempresa = nroempresa;
	}
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	public String getFantazia() {
		return fantazia;
	}
	public void setFantazia(String fantazia) {
		this.fantazia = fantazia;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public BigDecimal getNrocgc() {
		return nrocgc;
	}
	public void setNrocgc(BigDecimal nrocgc) {
		this.nrocgc = nrocgc;
	}
	
	public BigDecimal getDigcgc() {
		return digcgc;
	}
	public void setDigcgc(BigDecimal digcgc) {
		this.digcgc = digcgc;
	}
	public java.util.Date getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(java.util.Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	
	public Empresa(String razaosocial, String fantazia, BigDecimal nroempresa, String endereco,
			String bairro, String cep, String cidade, String uf,
			BigDecimal nrocgc) {
		this.razaosocial = razaosocial;
		this.fantazia = fantazia;
		this.nroempresa = nroempresa;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.nrocgc = nrocgc;
	}
	
	public Empresa(String razaosocial, String fantazia, BigDecimal nroempresa, String endereco,
			String bairro, String cep, String cidade, String uf,
			BigDecimal nrocgc, BigDecimal digcgc) {
		this(razaosocial,  fantazia, nroempresa, endereco, bairro, cep, cidade, uf, nrocgc);
		this.digcgc = digcgc;
	}

	
	public Empresa(String razaosocial, String fantazia, BigDecimal nroempresa, String endereco,
			String bairro, String cep, String cidade, String uf,
			BigDecimal nrocgc, BigDecimal digcgc, BigDecimal index) {
		this(razaosocial,  fantazia, nroempresa, endereco, bairro, cep, cidade, uf, nrocgc, digcgc);
	}
	
	public Empresa() {

	}
	
	@Override
	public String toString() {
		return "Empresa [nroempresa=" + nroempresa + ", razaosocial="
				+ razaosocial + ", fantazia=" + fantazia + ", endereco="
				+ endereco + ", bairro=" + bairro + ", cep=" + cep
				+ ", cidade=" + cidade + ", uf=" + uf + ", nrocgc=" + nrocgc
				+ ", digcgc=" + digcgc + ", dataExecucao=" + dataExecucao + "]";
	}
}
