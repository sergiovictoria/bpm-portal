package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.mongodb.morphia.annotations.Entity;


@Entity("OrRequisicaovencto")
public class OrRequisicaovencto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	
	private Date dtavencimento;
	private Date dtaprogramada;
	private BigDecimal vlrtotal = BigDecimal.ZERO;
	private BigDecimal vlrdesconto = BigDecimal.ZERO;
	private BigDecimal vlroutoperdesc = BigDecimal.ZERO;
	private BigDecimal vlracrescimo = BigDecimal.ZERO;
	private BigDecimal vlrliquido = BigDecimal.ZERO;
	private long seqrequisicao;
	private short nroparcela;
	
	

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
		BigDecimal valorLiquido = this.vlrtotal.add(vlracrescimo).subtract(vlrdesconto);
		vlrliquido = valorLiquido;
		return vlrliquido;
	}
	
	
	public void setVlrliquido(BigDecimal vlrliquido) {
		this.vlrliquido = vlrliquido;
	}	

	public long getSeqrequisicao() {
		return seqrequisicao;
	}
	public void setSeqrequisicao(long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}
	

	public short getNroparcela() {
		return nroparcela;
	}
	public void setNroparcela(short nroparcela) {
		this.nroparcela = nroparcela;
	}
	
	


	
	
}
