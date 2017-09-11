package br.com.seta.processo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrRequisicaovenctoId implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private long seqrequisicao;
	private short nroparcela;
	

	public OrRequisicaovenctoId() {
	}

	public OrRequisicaovenctoId(long seqrequisicao, short nroparcela) {
		this.seqrequisicao = seqrequisicao;
		this.nroparcela = nroparcela;
	}

	@Column(name = "SEQREQUISICAO", nullable = false, precision = 15, scale = 0)
	public long getSeqrequisicao() {
		return this.seqrequisicao;
	}

	public void setSeqrequisicao(long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}

	@Column(name = "NROPARCELA", nullable = false, precision = 3, scale = 0)
	public short getNroparcela() {
		return this.nroparcela;
	}

	public void setNroparcela(short nroparcela) {
		this.nroparcela = nroparcela;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrRequisicaovenctoId))
			return false;
		OrRequisicaovenctoId castOther = (OrRequisicaovenctoId) other;

		return (this.getSeqrequisicao() == castOther.getSeqrequisicao())
				&& (this.getNroparcela() == castOther.getNroparcela());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getSeqrequisicao();
		result = 37 * result + this.getNroparcela();
		return result;
	}

}
