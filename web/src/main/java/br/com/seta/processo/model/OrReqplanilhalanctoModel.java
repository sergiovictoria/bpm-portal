package br.com.seta.processo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.wicket.util.io.IClusterable;


public class OrReqplanilhalanctoModel implements IClusterable, Serializable {


	private static final long serialVersionUID = 1L;
		
	private BigDecimal Vlrtotal;       
	private BigDecimal Vlrliqreq;
	private BigDecimal VlrTotalReq;
	private BigDecimal vlrlancamento;
	
	
	public BigDecimal getVlrtotal() {
		return Vlrtotal;
	}
	public void setVlrtotal(BigDecimal vlrtotal) {
		Vlrtotal = vlrtotal;
	}
	public BigDecimal getVlrliqreq() {
		return Vlrliqreq;
	}
	public void setVlrliqreq(BigDecimal vlrliqreq) {
		Vlrliqreq = vlrliqreq;
	}
	public BigDecimal getVlrTotalReq() {
		return VlrTotalReq;
	}
	public void setVlrTotalReq(BigDecimal vlrTotalReq) {
		VlrTotalReq = vlrTotalReq;
	}
	public BigDecimal getVlrlancamento() {
		return vlrlancamento;
	}
	public void setVlrlancamento(BigDecimal vlrlancamento) {
		this.vlrlancamento = vlrlancamento;
	}
	
	


}

