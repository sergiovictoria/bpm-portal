package br.com.seta.processo.dto;

import java.io.Serializable;

public class ModeloDocumento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codmodelo;
	private String descricao; 
	private String codespecie;
	private boolean selected = Boolean.FALSE;
	
	
	public ModeloDocumento(String codmodelo, String descricao, String codespecie) {
		super();
		this.codmodelo = codmodelo;
		this.descricao = descricao;
		this.codespecie = codespecie;
	}
	
	
	public String getCodmodelo() {
		return codmodelo;
	}
	public void setCodmodelo(String codmodelo) {
		this.codmodelo = codmodelo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCodespecie() {
		return codespecie;
	}
	public void setCodespecie(String codespecie) {
		this.codespecie = codespecie;
	}
	
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "ModeloDocumento [codmodelo=" + codmodelo + ", descricao="
				+ descricao + ", codespecie=" + codespecie + "]";
	}
	
}
