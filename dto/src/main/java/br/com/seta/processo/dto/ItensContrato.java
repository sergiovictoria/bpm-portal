/**
 * 
 */
package br.com.seta.processo.dto;

import java.io.Serializable;

public class ItensContrato implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codproduto;
	private String descricao;
	private java.util.Date dataInicioContrato;
	private java.util.Date dataFimContrato;
	private Long codigoItem;
	private Long numeroContrato;
	private Long quantidade;
	private Long vlr;
	private int cfop;
	

	public String getCodproduto() {
		return codproduto;
	}

	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public java.util.Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(java.util.Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public java.util.Date getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(java.util.Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	public Long getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(Long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Long getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(Long codigoItem) {
		this.codigoItem = codigoItem;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Long getVlr() {
		return vlr;
	}

	public void setVlr(Long vlr) {
		this.vlr = vlr;
	}

	public int getCfop() {
		return cfop;
	}

	public void setCfop(int cfop) {
		this.cfop = cfop;
	}

	
}
