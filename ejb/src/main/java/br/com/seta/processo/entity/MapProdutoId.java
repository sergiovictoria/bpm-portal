package br.com.seta.processo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class MapProdutoId implements Serializable {


	private static final long serialVersionUID = 1L;
	private BigDecimal seqfamilia;

	public MapProdutoId() {
	}
	
	
	public MapProdutoId(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}

	@Column(name = "SEQFAMILIA", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqfamilia() {
		return this.seqfamilia;
	}

	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seqfamilia == null) ? 0 : seqfamilia.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MapProdutoId)) {
			return false;
		}
		MapProdutoId other = (MapProdutoId) obj;
		if (seqfamilia == null) {
			if (other.seqfamilia != null) {
				return false;
			}
		} else if (!seqfamilia.equals(other.seqfamilia)) {
			return false;
		}
		return true;
	}



}
