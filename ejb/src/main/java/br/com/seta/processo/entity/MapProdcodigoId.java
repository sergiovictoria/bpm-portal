package br.com.seta.processo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class MapProdcodigoId implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private String codacesso;
	private int cgcfornec;
	private String tipcodigo;

	public MapProdcodigoId() {
	}

	public MapProdcodigoId(String codacesso, int cgcfornec, String tipcodigo) {
		this.codacesso = codacesso;
		this.cgcfornec = cgcfornec;
		this.tipcodigo = tipcodigo;
	}

	@Column(name = "CODACESSO", nullable = false, length = 60)
	public String getCodacesso() {
		return this.codacesso;
	}

	public void setCodacesso(String codacesso) {
		this.codacesso = codacesso;
	}

	@Column(name = "CGCFORNEC", nullable = false, precision = 8, scale = 0)
	public int getCgcfornec() {
		return this.cgcfornec;
	}

	public void setCgcfornec(int cgcfornec) {
		this.cgcfornec = cgcfornec;
	}

	@Column(name = "TIPCODIGO", nullable = false, length = 1)
	public String getTipcodigo() {
		return this.tipcodigo;
	}

	public void setTipcodigo(String tipcodigo) {
		this.tipcodigo = tipcodigo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MapProdcodigoId))
			return false;
		MapProdcodigoId castOther = (MapProdcodigoId) other;

		return ((this.getCodacesso() == castOther.getCodacesso()) || (this.getCodacesso() != null && castOther.getCodacesso() != null && this
				.getCodacesso().equals(castOther.getCodacesso())))
				&& (this.getCgcfornec() == castOther.getCgcfornec())
				&& ((this.getTipcodigo() == castOther.getTipcodigo()) || (this.getTipcodigo() != null && castOther.getTipcodigo() != null && this
						.getTipcodigo().equals(castOther.getTipcodigo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCodacesso() == null ? 0 : this.getCodacesso().hashCode());
		result = 37 * result + this.getCgcfornec();
		result = 37 * result + (getTipcodigo() == null ? 0 : this.getTipcodigo().hashCode());
		return result;
	}

}
