package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author Eliel Sobral
 *
 */
public class GrupoRecebimentoIntencaoCompra implements Serializable, Comparator<GrupoRecebimentoIntencaoCompra> {
	private static final long serialVersionUID = 1L;

	private String grupoRecebimento;

	/**
	 * Constructor
	 */
	public GrupoRecebimentoIntencaoCompra() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the grupoRecebimento
	 */
	public final String getGrupoRecebimento() {
		return grupoRecebimento;
	}

	/**
	 * @param grupoRecebimento
	 *            the grupoRecebimento to set
	 */
	public final void setGrupoRecebimento(String grupoRecebimento) {
		this.grupoRecebimento = grupoRecebimento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GrupoRecebimentoIntencaoCompra [");
		if (grupoRecebimento != null) {
			builder.append("grupoRecebimento=");
			builder.append(grupoRecebimento);
		}
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grupoRecebimento == null) ? 0 : grupoRecebimento.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoRecebimentoIntencaoCompra other = (GrupoRecebimentoIntencaoCompra) obj;
		if (grupoRecebimento == null) {
			if (other.grupoRecebimento != null)
				return false;
		} else if (!grupoRecebimento.equals(other.grupoRecebimento))
			return false;
		return true;
	}

	@Override
	public int compare(GrupoRecebimentoIntencaoCompra o1, GrupoRecebimentoIntencaoCompra o2) {
		return o1.getGrupoRecebimento().compareTo(o2.getGrupoRecebimento());
	}

}
