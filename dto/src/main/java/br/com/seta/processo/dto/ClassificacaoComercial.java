package br.com.seta.processo.dto;

import java.io.Serializable;

public class ClassificacaoComercial implements Serializable {

	private static final long serialVersionUID = 1L;

	private String classifcomercabc;

	/**
	 * Constructor
	 */
	public ClassificacaoComercial() {
	}

	/**
	 * @return the classifcomercabc
	 */
	public final String getClassifcomercabc() {
		return classifcomercabc;
	}

	/**
	 * @param classifcomercabc
	 *            the classifcomercabc to set
	 */
	public final void setClassifcomercabc(String classifcomercabc) {
		this.classifcomercabc = classifcomercabc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassificacaoComercial [");
		if (classifcomercabc != null) {
			builder.append("classifcomercabc=");
			builder.append(classifcomercabc);
		}
		builder.append("]");
		return builder.toString();
	}

}
