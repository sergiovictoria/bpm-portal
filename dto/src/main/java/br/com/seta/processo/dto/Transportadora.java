package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Transportadora implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal seqpessoa;
	private String nomerazao;
	private String tipotransporte;
	private Boolean selected = Boolean.FALSE;
	
	public BigDecimal getSeqpessoa() {
		return seqpessoa;
	}
	public void setSeqpessoa(BigDecimal seqpessoa) {
		this.seqpessoa = seqpessoa;
	}
	
	public String getNomerazao() {
		return nomerazao;
	}
	public void setNomerazao(String nomerazao) {
		this.nomerazao = nomerazao;
	}
	
	public String getTipotransporte() {
		return tipotransporte;
	}
	public void setTipotransporte(String tipotransporte) {
		this.tipotransporte = tipotransporte;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}	
	
	@Override
	public String toString() {
		return "Transportadora [seqpessoa=" + seqpessoa + ", nomerazao="
				+ nomerazao + ", tipotransporte=" + tipotransporte
				+ ", selected=" + selected + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomerazao == null) ? 0 : nomerazao.hashCode());
		result = prime * result
				+ ((selected == null) ? 0 : selected.hashCode());
		result = prime * result
				+ ((seqpessoa == null) ? 0 : seqpessoa.hashCode());
		result = prime * result
				+ ((tipotransporte == null) ? 0 : tipotransporte.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transportadora other = (Transportadora) obj;
		if (nomerazao == null) {
			if (other.nomerazao != null)
				return false;
		} else if (!nomerazao.equals(other.nomerazao))
			return false;
		if (selected == null) {
			if (other.selected != null)
				return false;
		} else if (!selected.equals(other.selected))
			return false;
		if (seqpessoa == null) {
			if (other.seqpessoa != null)
				return false;
		} else if (!seqpessoa.equals(other.seqpessoa))
			return false;
		if (tipotransporte == null) {
			if (other.tipotransporte != null)
				return false;
		} else if (!tipotransporte.equals(other.tipotransporte))
			return false;
		return true;
	}
	

	
	
	

}
