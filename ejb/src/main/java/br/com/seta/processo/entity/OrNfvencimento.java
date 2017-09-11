package br.com.seta.processo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "OR_NFVENCIMENTO")
public class OrNfvencimento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal seqnota;
	
	@Id
	@Column(name = "SEQNOTA", unique = true, nullable = false, precision = 8, scale = 0)
	public BigDecimal getSeqnota() {
		return seqnota;
	}
	public void setSeqnota(BigDecimal seqnota) {
		this.seqnota = seqnota;
	}
	
}
