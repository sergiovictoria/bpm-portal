package br.com.seta.processo.model;

import java.io.Serializable;

import org.apache.wicket.util.io.IClusterable;

public class RequisicaoModel implements IClusterable, Serializable {
	

	private static final long serialVersionUID = 1L;
	private String datacompra = " ";
	private String datainclusao = " ";
	
	
	public String getDatacompra() {
		return datacompra;
	}
	public void setDatacompra(String datacompra) {
		this.datacompra = datacompra;
	}
	public String getDatainclusao() {
		return datainclusao;
	}
	public void setDatainclusao(String datainclusao) {
		this.datainclusao = datainclusao;
	}
	
	
	

}
