package br.com.seta.processo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OrReqitensdespesaId implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private long seqrequisicao;
	private short nroitem;

	public OrReqitensdespesaId() {
	}

	public OrReqitensdespesaId(long seqrequisicao, short nroitem) {
		this.seqrequisicao = seqrequisicao;
		this.nroitem = nroitem;
	}

	@Column(name = "SEQREQUISICAO", nullable = false, precision = 15, scale = 0)
	public long getSeqrequisicao() {
		return this.seqrequisicao;
	}

	public void setSeqrequisicao(long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}

	@Column(name = "NROITEM", nullable = false, precision = 3, scale = 0)
	public short getNroitem() {
		return this.nroitem;
	}

	public void setNroitem(short nroitem) {
		this.nroitem = nroitem;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrReqitensdespesaId))
			return false;
		OrReqitensdespesaId castOther = (OrReqitensdespesaId) other;

		return (this.getSeqrequisicao() == castOther.getSeqrequisicao())
				&& (this.getNroitem() == castOther.getNroitem());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getSeqrequisicao();
		result = 37 * result + this.getNroitem();
		return result;
	}

}
