package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Comparator;

public class SolicitanteRequisicao implements Serializable, Comparator<SolicitanteRequisicao> {
	private static final long serialVersionUID = 1L;

	private String nomeSolicitanteBPM;

	public SolicitanteRequisicao() {
	}

	/**
	 * @return the nomeSolicitanteBPM
	 */
	public final String getNomeSolicitanteBPM() {
		return nomeSolicitanteBPM;
	}

	/**
	 * @param nomeSolicitanteBPM
	 *            the nomeSolicitanteBPM to set
	 */
	public final void setNomeSolicitanteBPM(String nomeSolicitanteBPM) {
		this.nomeSolicitanteBPM = nomeSolicitanteBPM;
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
		result = prime * result + ((nomeSolicitanteBPM == null) ? 0 : nomeSolicitanteBPM.hashCode());
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
		SolicitanteRequisicao other = (SolicitanteRequisicao) obj;
		if (nomeSolicitanteBPM == null) {
			if (other.nomeSolicitanteBPM != null)
				return false;
		} else if (!nomeSolicitanteBPM.equals(other.nomeSolicitanteBPM))
			return false;
		return true;
	}

	@Override
	public int compare(SolicitanteRequisicao o1, SolicitanteRequisicao o2) {
		return 0;
	}

}
