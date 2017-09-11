package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CtCusto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal centrocusto;
	private String descricao;
	
	
	public BigDecimal getCentrocusto() {
		return centrocusto;
	}
	public void setCentrocusto(BigDecimal centrocusto) {
		this.centrocusto = centrocusto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
