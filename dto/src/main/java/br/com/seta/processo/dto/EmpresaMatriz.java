package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmpresaMatriz implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	private BigDecimal nroempresaorc;
	private BigDecimal nroempresa;
	private String razaosocial;
	private String fantasia;
	private String endereco; 
	private String bairro;
	private String cep;
	private String cidade; 
	private String uf;
	private BigDecimal nrocgc;
	
	
	
	public EmpresaMatriz( ) {
	}
	
		
	
	public EmpresaMatriz(BigDecimal nroempresaorc, BigDecimal nroempresa,
			String razaosocial, String fantasia, String endereco,
			String bairro, String cep, String cidade, String uf,
			BigDecimal nrocgc) {
		this.nroempresaorc = nroempresaorc;
		this.nroempresa = nroempresa;
		this.razaosocial = razaosocial;
		this.fantasia = fantasia;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.nrocgc = nrocgc;
	}
	
	
	public BigDecimal getNroempresaorc() {
		return nroempresaorc;
	}
	public void setNroempresaorc(BigDecimal nroempresaorc) {
		this.nroempresaorc = nroempresaorc;
	}
	public BigDecimal getNroempresa() {
		return nroempresa;
	}
	public void setNroempresa(BigDecimal nroempresa) {
		this.nroempresa = nroempresa;
	}
	public String getRazaosocial() {
		return razaosocial;
	}
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
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
	
	
	
	

	


}
