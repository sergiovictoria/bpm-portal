package br.com.seta.processo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class MaxAtributofixoId implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private String tipatributofixo;
	private short seqatributofixo;

	public MaxAtributofixoId() {
	}

	public MaxAtributofixoId(String tipatributofixo, short seqatributofixo) {
		this.tipatributofixo = tipatributofixo;
		this.seqatributofixo = seqatributofixo;
	}

	@Column(name = "TIPATRIBUTOFIXO", nullable = false, length = 21)
	public String getTipatributofixo() {
		return this.tipatributofixo;
	}

	public void setTipatributofixo(String tipatributofixo) {
		this.tipatributofixo = tipatributofixo;
	}

	@Column(name = "SEQATRIBUTOFIXO", nullable = false, precision = 3, scale = 0)
	public short getSeqatributofixo() {
		return this.seqatributofixo;
	}

	public void setSeqatributofixo(short seqatributofixo) {
		this.seqatributofixo = seqatributofixo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaxAtributofixoId))
			return false;
		MaxAtributofixoId castOther = (MaxAtributofixoId) other;

		return ((this.getTipatributofixo() == castOther.getTipatributofixo()) || (this.getTipatributofixo() != null
				&& castOther.getTipatributofixo() != null && this.getTipatributofixo().equals(castOther.getTipatributofixo())))
				&& (this.getSeqatributofixo() == castOther.getSeqatributofixo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTipatributofixo() == null ? 0 : this.getTipatributofixo().hashCode());
		result = 37 * result + this.getSeqatributofixo();
		return result;
	}

}
