package br.com.seta.processo.dto;

import java.io.Serializable;

/**
 * @author Eliel Sobral
 *
 */
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricao;

	public Categoria(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoria [");
		if (descricao != null) {
			builder.append("descricao=");
			builder.append(descricao);
		}
		builder.append("]");
		return builder.toString();
	}

}
