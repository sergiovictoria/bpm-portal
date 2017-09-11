package br.com.seta.processo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "OR_REQUISICAOVENCTO")

public class OrRequisicaovenctoEntity implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private OrRequisicaovenctoId id;
	private Date dtavencimento;
	private Date dtaprogramada;
	private BigDecimal vlrtotal;
	private BigDecimal vlrdesconto;
	private BigDecimal vlroutoperdesc;
	private BigDecimal vlracrescimo;
	private BigDecimal vlrliquido;

	public OrRequisicaovenctoEntity() {
	}

	public OrRequisicaovenctoEntity(OrRequisicaovenctoId id, Date dtavencimento,
			Date dtaprogramada) {
		this.id = id;
		this.dtavencimento = dtavencimento;
		this.dtaprogramada = dtaprogramada;
	}

	public OrRequisicaovenctoEntity(OrRequisicaovenctoId id, Date dtavencimento,
			Date dtaprogramada, BigDecimal vlrtotal, BigDecimal vlrdesconto,
			BigDecimal vlroutoperdesc, BigDecimal vlracrescimo,
			BigDecimal vlrliquido) {
		this.id = id;
		this.dtavencimento = dtavencimento;
		this.dtaprogramada = dtaprogramada;
		this.vlrtotal = vlrtotal;
		this.vlrdesconto = vlrdesconto;
		this.vlroutoperdesc = vlroutoperdesc;
		this.vlracrescimo = vlracrescimo;
		this.vlrliquido = vlrliquido;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "seqrequisicao", column = @Column(name = "SEQREQUISICAO", nullable = false, precision = 15, scale = 0)),
			@AttributeOverride(name = "nroparcela", column = @Column(name = "NROPARCELA", nullable = false, precision = 3, scale = 0)) })
	public OrRequisicaovenctoId getId() {
		return this.id;
	}

	public void setId(OrRequisicaovenctoId id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCIMENTO", nullable = false, length = 7)
	public Date getDtavencimento() {
		return this.dtavencimento;
	}

	public void setDtavencimento(Date dtavencimento) {
		this.dtavencimento = dtavencimento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAPROGRAMADA", nullable = false, length = 7)
	public Date getDtaprogramada() {
		return this.dtaprogramada;
	}

	public void setDtaprogramada(Date dtaprogramada) {
		this.dtaprogramada = dtaprogramada;
	}

	@Column(name = "VLRTOTAL", precision = 15)
	public BigDecimal getVlrtotal() {
		return this.vlrtotal;
	}

	public void setVlrtotal(BigDecimal vlrtotal) {
		this.vlrtotal = vlrtotal;
	}

	@Column(name = "VLRDESCONTO", precision = 15)
	public BigDecimal getVlrdesconto() {
		return this.vlrdesconto;
	}

	public void setVlrdesconto(BigDecimal vlrdesconto) {
		this.vlrdesconto = vlrdesconto;
	}

	@Column(name = "VLROUTOPERDESC", precision = 15)
	public BigDecimal getVlroutoperdesc() {
		return this.vlroutoperdesc;
	}

	public void setVlroutoperdesc(BigDecimal vlroutoperdesc) {
		this.vlroutoperdesc = vlroutoperdesc;
	}

	@Column(name = "VLRACRESCIMO", precision = 15)
	public BigDecimal getVlracrescimo() {
		return this.vlracrescimo;
	}

	public void setVlracrescimo(BigDecimal vlracrescimo) {
		this.vlracrescimo = vlracrescimo;
	}

	@Column(name = "VLRLIQUIDO", precision = 15)
	public BigDecimal getVlrliquido() {
		return this.vlrliquido;
	}

	public void setVlrliquido(BigDecimal vlrliquido) {
		this.vlrliquido = vlrliquido;
	}

}
