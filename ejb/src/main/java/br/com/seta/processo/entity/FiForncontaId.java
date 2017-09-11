package br.com.seta.processo.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class FiForncontaId implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int seqpessoa;
	private short seqconta;

	public FiForncontaId() {
	}

	public FiForncontaId(int seqpessoa, short seqconta) {
		this.seqpessoa = seqpessoa;
		this.seqconta = seqconta;
	}

	@Column(name = "SEQPESSOA", nullable = false, precision = 8, scale = 0)
	public int getSeqpessoa() {
		return this.seqpessoa;
	}

	public void setSeqpessoa(int seqpessoa) {
		this.seqpessoa = seqpessoa;
	}

	@Column(name = "SEQCONTA", nullable = false, precision = 3, scale = 0)
	public short getSeqconta() {
		return this.seqconta;
	}

	public void setSeqconta(short seqconta) {
		this.seqconta = seqconta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FiForncontaId))
			return false;
		FiForncontaId castOther = (FiForncontaId) other;

		return (this.getSeqpessoa() == castOther.getSeqpessoa()) && (this.getSeqconta() == castOther.getSeqconta());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getSeqpessoa();
		result = 37 * result + this.getSeqconta();
		return result;
	}

}
