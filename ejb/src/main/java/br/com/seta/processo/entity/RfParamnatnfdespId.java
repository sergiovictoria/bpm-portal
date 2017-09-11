package br.com.seta.processo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RfParamnatnfdespId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private short codhistorico;
	private short nroempresa;

	public RfParamnatnfdespId() {
	}

	public RfParamnatnfdespId(short codhistorico, short nroempresa) {
		this.codhistorico = codhistorico;
		this.nroempresa = nroempresa;
	}

	@Column(name = "CODHISTORICO", nullable = false, precision = 4, scale = 0)
	public short getCodhistorico() {
		return this.codhistorico;
	}

	public void setCodhistorico(short codhistorico) {
		this.codhistorico = codhistorico;
	}

	@Column(name = "NROEMPRESA", nullable = false, precision = 3, scale = 0)
	public short getNroempresa() {
		return this.nroempresa;
	}

	public void setNroempresa(short nroempresa) {
		this.nroempresa = nroempresa;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RfParamnatnfdespId))
			return false;
		RfParamnatnfdespId castOther = (RfParamnatnfdespId) other;

		return (this.getCodhistorico() == castOther.getCodhistorico()) && (this.getNroempresa() == castOther.getNroempresa());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodhistorico();
		result = 37 * result + this.getNroempresa();
		return result;
	}

}
