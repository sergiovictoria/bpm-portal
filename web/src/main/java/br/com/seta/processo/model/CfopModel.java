package br.com.seta.processo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.wicket.util.io.IClusterable;

public class CfopModel implements Serializable, IClusterable {


	private static final long serialVersionUID = 1L;
	private String descricao;
	private BigDecimal cfop;
		
		
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public BigDecimal getCfop() {
		return cfop;
	}
	public void setCfop(BigDecimal cfop) {
		this.cfop = cfop;
	} 

	


}
