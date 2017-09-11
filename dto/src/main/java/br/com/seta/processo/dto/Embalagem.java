package br.com.seta.processo.dto;

import java.io.Serializable;

public class Embalagem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private String quantidade;
	private String embalagem;
	private String ean;
	private String pesoLiq;
	private String pesoBruto;
	private String comprimento;
	private String altura;
	private String largura;
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getEmbalagem() {
		return embalagem;
	}
	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getPesoLiq() {
		return pesoLiq;
	}
	public void setPesoLiq(String pesoLiq) {
		this.pesoLiq = pesoLiq;
	}
	public String getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(String pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	public String getComprimento() {
		return comprimento;
	}
	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getLargura() {
		return largura;
	}
	public void setLargura(String largura) {
		this.largura = largura;
	}
}
