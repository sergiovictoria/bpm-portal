package br.com.seta.processo.dto;

import java.io.Serializable;

public class Erros implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String atributo;
	private Exception e;
	private String erro;
	private String motivo;
	private String celula;
	private String coluna;
	
	
	public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getCelula() {
		return celula;
	}
	public void setCelula(String celula) {
		this.celula = celula;
	}
	public String getColuna() {
		return coluna;
	}
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	
	
	@Override
	public String toString() {
		return "Erros [atributo=" + atributo + ", e=" + e + ", erro=" + erro
				+ ", motivo=" + motivo + ", celula=" + celula + ", coluna="
				+ coluna + "]";
	}

	

}
