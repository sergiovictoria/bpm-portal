package br.com.seta.processo.dto;

import java.io.Serializable;

public class MapNcmDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codncm;

	private String descricao;

	public String getCodncm() {
		return codncm;
	}

	public void setCodncm(String codncm) {
		this.codncm = codncm;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
