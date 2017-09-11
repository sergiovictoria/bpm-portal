package br.com.seta.processo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.wicket.util.io.IClusterable;


public class ValoresModel implements IClusterable, Serializable {


	private static final long serialVersionUID = 1L;
	
	
	private BigDecimal Valor;        
	private BigDecimal Vlrliqreq;     
	private BigDecimal Vlrdescontos;  
	private BigDecimal Vlracrescimos;
	private String desdobramentoParcela;
	
	
	public BigDecimal getValor() {
		return Valor;
	}
	public void setValor(BigDecimal valor) {
		Valor = valor;
	}
	public BigDecimal getVlrliqreq() {
		return Vlrliqreq;
	}
	public void setVlrliqreq(BigDecimal vlrliqreq) {
		Vlrliqreq = vlrliqreq;
	}
	public BigDecimal getVlrdescontos() {
		return Vlrdescontos;
	}
	public void setVlrdescontos(BigDecimal vlrdescontos) {
		Vlrdescontos = vlrdescontos;
	}
	public BigDecimal getVlracrescimos() {
		return Vlracrescimos;
	}
	public void setVlracrescimos(BigDecimal vlracrescimos) {
		Vlracrescimos = vlracrescimos;
	}
	public String getDesdobramentoParcela() {
		return desdobramentoParcela;
	}
	public void setDesdobramentoParcela(String desdobramentoParcela) {
		this.desdobramentoParcela = desdobramentoParcela;
	}
	
	
	
	


}

