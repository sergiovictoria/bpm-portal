package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author Eliel Sobral
 *
 */
public class FornecedorIntencaoCompra implements Serializable, Comparator<FornecedorIntencaoCompra> {
	private static final long serialVersionUID = 1L;

	private String nomeFornecedor;

	public FornecedorIntencaoCompra() {
	}

	/**
	 * @return the nomeFornecedor
	 */
	public final String getNomeFornecedor() {
		return nomeFornecedor;
	}

	/**
	 * @param nomeFornecedor
	 *            the nomeFornecedor to set
	 */
	public final void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fornecedor [");
		if (nomeFornecedor != null) {
			builder.append("nomeFornecedor=");
			builder.append(nomeFornecedor);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compare(FornecedorIntencaoCompra o1, FornecedorIntencaoCompra o2) {
		return o1.getNomeFornecedor().compareTo(o2.getNomeFornecedor());
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
		result = prime * result + ((nomeFornecedor == null) ? 0 : nomeFornecedor.hashCode());
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
		FornecedorIntencaoCompra other = (FornecedorIntencaoCompra) obj;
		if (nomeFornecedor == null) {
			if (other.nomeFornecedor != null)
				return false;
		} else if (!nomeFornecedor.equals(other.nomeFornecedor))
			return false;
		return true;
	}

}
