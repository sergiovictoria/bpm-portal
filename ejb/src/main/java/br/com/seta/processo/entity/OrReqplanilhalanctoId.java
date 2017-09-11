package br.com.seta.processo.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OrReqplanilhalanctoId implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private long seqrequisicao;
	private short nrolinha;

	public OrReqplanilhalanctoId() {
	}

	public OrReqplanilhalanctoId(long seqrequisicao, short nrolinha) {
		this.seqrequisicao = seqrequisicao;
		this.nrolinha = nrolinha;
	}

	@Column(name = "SEQREQUISICAO", nullable = false, precision = 15, scale = 0)
	public long getSeqrequisicao() {
		return this.seqrequisicao;
	}

	public void setSeqrequisicao(long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}

	@Column(name = "NROLINHA", nullable = false, precision = 3, scale = 0)
	public short getNrolinha() {
		return this.nrolinha;
	}

	public void setNrolinha(short nrolinha) {
		this.nrolinha = nrolinha;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrReqplanilhalanctoId))
			return false;
		OrReqplanilhalanctoId castOther = (OrReqplanilhalanctoId) other;

		return (this.getSeqrequisicao() == castOther.getSeqrequisicao())
				&& (this.getNrolinha() == castOther.getNrolinha());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getSeqrequisicao();
		result = 37 * result + this.getNrolinha();
		return result;
	}

}
