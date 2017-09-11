package br.com.seta.processo.entity;


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class MapFamembalagemId implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private BigDecimal seqfamilia;
	private BigDecimal qtdembalagem;

	public MapFamembalagemId() {
	}

	public MapFamembalagemId(BigDecimal seqfamilia, BigDecimal qtdembalagem) {
		this.seqfamilia = seqfamilia;
		this.qtdembalagem = qtdembalagem;
	}

	@Column(name = "SEQFAMILIA", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqfamilia() {
		return this.seqfamilia;
	}

	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}

	@Column(name = "QTDEMBALAGEM", nullable = false, precision = 12, scale = 6)
	public BigDecimal getQtdembalagem() {
		return this.qtdembalagem;
	}

	public void setQtdembalagem(BigDecimal qtdembalagem) {
		this.qtdembalagem = qtdembalagem;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MapFamembalagemId))
			return false;
		MapFamembalagemId castOther = (MapFamembalagemId) other;

		return ((this.getSeqfamilia() == castOther.getSeqfamilia()) || (this.getSeqfamilia() != null && castOther.getSeqfamilia() != null && this
				.getSeqfamilia().equals(castOther.getSeqfamilia())))
				&& ((this.getQtdembalagem() == castOther.getQtdembalagem()) || (this.getQtdembalagem() != null
						&& castOther.getQtdembalagem() != null && this.getQtdembalagem().equals(castOther.getQtdembalagem())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSeqfamilia() == null ? 0 : this.getSeqfamilia().hashCode());
		result = 37 * result + (getQtdembalagem() == null ? 0 : this.getQtdembalagem().hashCode());
		return result;
	}

}
