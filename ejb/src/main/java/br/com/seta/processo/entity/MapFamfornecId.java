package br.com.seta.processo.entity;


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MapFamfornecId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal seqfamilia;
	private BigDecimal seqfornecedor;

	public MapFamfornecId() {
	}

	public MapFamfornecId(BigDecimal seqfamilia, BigDecimal seqfornecedor) {
		this.seqfamilia = seqfamilia;
		this.seqfornecedor = seqfornecedor;
	}

	@Column(name = "SEQFAMILIA", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqfamilia() {
		return this.seqfamilia;
	}

	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}

	@Column(name = "SEQFORNECEDOR", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqfornecedor() {
		return this.seqfornecedor;
	}

	public void setSeqfornecedor(BigDecimal seqfornecedor) {
		this.seqfornecedor = seqfornecedor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MapFamfornecId))
			return false;
		MapFamfornecId castOther = (MapFamfornecId) other;

		return ((this.getSeqfamilia() == castOther.getSeqfamilia()) || (this.getSeqfamilia() != null && castOther.getSeqfamilia() != null && this
				.getSeqfamilia().equals(castOther.getSeqfamilia())))
				&& ((this.getSeqfornecedor() == castOther.getSeqfornecedor()) || (this.getSeqfornecedor() != null
						&& castOther.getSeqfornecedor() != null && this.getSeqfornecedor().equals(castOther.getSeqfornecedor())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSeqfamilia() == null ? 0 : this.getSeqfamilia().hashCode());
		result = 37 * result + (getSeqfornecedor() == null ? 0 : this.getSeqfornecedor().hashCode());
		return result;
	}

}
