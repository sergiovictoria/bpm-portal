package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cfop implements Serializable {	

	private static final long serialVersionUID = 1L;
	private BigDecimal cfop;
	private BigDecimal complemento;
	private String descricaored;
	private boolean selected;	
	
	public BigDecimal getCfop() {
		return cfop;
	}
	public void setCfop(BigDecimal cfop) {
		this.cfop = cfop;
	}
	public BigDecimal getComplemento() {
		return complemento;
	}
	public void setComplemento(BigDecimal complemento) {
		this.complemento = complemento;
	}
	public String getDescricaored() {
		return descricaored;
	}
	public void setDescricaored(String descricaored) {
		this.descricaored = descricaored;
	}	
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "Cfop [cfop=" + cfop + ", complemento=" + complemento
				+ ", descricaored=" + descricaored + "]";
	}

	
	

}
