package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Pagamento implements Serializable {


	private static final long serialVersionUID = 1L;
	private String abertoquitado; 
	private java.util.Date dataquitacao;
	private BigDecimal nrotitulo;
	private String seriedoc;
	private BigDecimal valornominal;
	private java.util.Date datavencimento;
	private java.util.Date dataExecucao;
	
	
	
	public Pagamento() {
	}
	
		
	public Pagamento(String abertoquitado, Date dataquitacao,
			BigDecimal nrotitulo, String seriedoc, BigDecimal valornominal,
			Date datavencimento) {
		this.abertoquitado = abertoquitado;
		this.dataquitacao = dataquitacao;
		this.nrotitulo = nrotitulo;
		this.seriedoc = seriedoc;
		this.valornominal = valornominal;
		this.datavencimento = datavencimento;
	}
	
	
	public String getAbertoquitado() {
		return abertoquitado;
	}
	public void setAbertoquitado(String abertoquitado) {
		this.abertoquitado = abertoquitado;
	}
	public java.util.Date getDataquitacao() {
		return dataquitacao;
	}
	public void setDataquitacao(java.util.Date dataquitacao) {
		this.dataquitacao = dataquitacao;
	}
	public BigDecimal getNrotitulo() {
		return nrotitulo;
	}
	public void setNrotitulo(BigDecimal nrotitulo) {
		this.nrotitulo = nrotitulo;
	}
	public String getSeriedoc() {
		return seriedoc;
	}
	public void setSeriedoc(String seriedoc) {
		this.seriedoc = seriedoc;
	}
	public BigDecimal getValornominal() {
		return valornominal;
	}
	public void setValornominal(BigDecimal valornominal) {
		this.valornominal = valornominal;
	}
	public java.util.Date getDatavencimento() {
		return datavencimento;
	}
	public void setDatavencimento(java.util.Date datavencimento) {
		this.datavencimento = datavencimento;
	}


	public java.util.Date getDataExecucao() {
		return dataExecucao;
	}


	public void setDataExecucao(java.util.Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	
	
	
	
	
		

	
	

}
