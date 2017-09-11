package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.mongodb.morphia.annotations.Entity;


@Entity("OrSolicitacaovencto")
public class OrSolicitacaovencto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	
	private Date dtavencimento;
	private Date dtaprogramada;
	private BigDecimal vlrtotal = BigDecimal.ZERO;
	private BigDecimal vlrdesconto = BigDecimal.ZERO;
	private BigDecimal vlroutoperdesc = BigDecimal.ZERO;
	private BigDecimal vlracrescimo = BigDecimal.ZERO;
	private BigDecimal vlrliquido = BigDecimal.ZERO;
	private long nronota;
	private short nroparcela;
	private int qtdparcela;
	private Integer qtdPrazo;
	
	public Date getDtavencimento() {
		return dtavencimento;
	}
	public void setDtavencimento(Date dtavencimento) {
		this.dtavencimento = dtavencimento;
	}
	public Date getDtaprogramada() {
		return dtaprogramada;
	}
	public void setDtaprogramada(Date dtaprogramada) {
		this.dtaprogramada = dtaprogramada;
	}
	public BigDecimal getVlrtotal() {
		return vlrtotal;
	}
	public void setVlrtotal(BigDecimal vlrtotal) {
		this.vlrtotal = vlrtotal;
	}
	public BigDecimal getVlrdesconto() {
		return vlrdesconto;
	}
	public void setVlrdesconto(BigDecimal vlrdesconto) {
		this.vlrdesconto = vlrdesconto;
	}
	public BigDecimal getVlroutoperdesc() {
		return vlroutoperdesc;
	}
	public void setVlroutoperdesc(BigDecimal vlroutoperdesc) {
		this.vlroutoperdesc = vlroutoperdesc;
	}
	public BigDecimal getVlracrescimo() {
		return vlracrescimo;
	}
	public void setVlracrescimo(BigDecimal vlracrescimo) {
		this.vlracrescimo = vlracrescimo;
	}
	public BigDecimal getVlrliquido() {
		return vlrliquido;
	}
	public void setVlrliquido(BigDecimal vlrliquido) {
		this.vlrliquido = vlrliquido;
	}
	public long getNronota() {
		return nronota;
	}
	public void setNronota(long nronota) {
		this.nronota = nronota;
	}
	public short getNroparcela() {
		return nroparcela;
	}
	public void setNroparcela(short nroparcela) {
		this.nroparcela = nroparcela;
	}
	public int getQtdparcela() {
		return qtdparcela;
	}
	public void setQtdparcela(int qtdparcela) {
		this.qtdparcela = qtdparcela;
	}
	public Integer getQtdPrazo() {
		return qtdPrazo;
	}
	public void setQtdPrazo(Integer qtdPrazo) {
		this.qtdPrazo = qtdPrazo;
	}
	
}
