package br.com.seta.processo.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GE_SEQUENCIA")

public class GeSequenciaEntity implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private String nometabela;
	private BigDecimal sequencia;

	public GeSequenciaEntity() {
	}
	
	@Id
	@Column(name = "SEQPESSOA", unique = true, nullable = false, precision = 8, scale = 0)
	public BigDecimal getSequencia() {
		return this.sequencia;
	}

	public void setSequencia(BigDecimal sequencia) {
		this.sequencia = sequencia;
	}

	@Column(name = "NOMETABELA", length = 25)
	public String getNometabela() {
		return this.nometabela;
	}

	public void setNometabela(String nometabela) {
		this.nometabela = nometabela;
	}



}
