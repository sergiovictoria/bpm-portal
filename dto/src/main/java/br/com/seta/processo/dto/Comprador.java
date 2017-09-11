package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Comprador implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private BigDecimal seqcomprador;
	
	
	public Comprador(BigDecimal seqcomprador, String nome) {
		this.seqcomprador = seqcomprador;
		this.nome = nome;
	}

	public Comprador() {
	}

	
	public Comprador(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public BigDecimal getSeqcomprador() {
		return seqcomprador;
	}

	public void setSeqcomprador(BigDecimal seqcomprador) {
		this.seqcomprador = seqcomprador;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comprador [");
		if (nome != null) {
			builder.append("nome=");
			builder.append(nome);
		}
		builder.append("]");
		return builder.toString();
	}

	

}
