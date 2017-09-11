package br.com.seta.processo.dto;



import java.math.BigDecimal;
import java.util.Date;

public class OrvCentrocusto implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private java.math.BigDecimal nroempresa;
	private String centrocusto;
	private String descricao;
	private String linkexterno;
	private String usualteracao;
	private Date dtaalteracao;
	private String liberadagasto;
	private String situacao;
	private BigDecimal seqcentroresultado;

	public OrvCentrocusto() {
	}

	public java.math.BigDecimal getNroempresa() {
		return nroempresa;
	}

	public void setNroempresa(java.math.BigDecimal nroempresa) {
		this.nroempresa = nroempresa;
	}

	public String getCentrocusto() {
		return centrocusto;
	}

	public void setCentrocusto(String centrocusto) {
		this.centrocusto = centrocusto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinkexterno() {
		return linkexterno;
	}

	public void setLinkexterno(String linkexterno) {
		this.linkexterno = linkexterno;
	}

	public String getUsualteracao() {
		return usualteracao;
	}

	public void setUsualteracao(String usualteracao) {
		this.usualteracao = usualteracao;
	}

	public Date getDtaalteracao() {
		return dtaalteracao;
	}

	public void setDtaalteracao(Date dtaalteracao) {
		this.dtaalteracao = dtaalteracao;
	}

	public String getLiberadagasto() {
		return liberadagasto;
	}

	public void setLiberadagasto(String liberadagasto) {
		this.liberadagasto = liberadagasto;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getSeqcentroresultado() {
		return seqcentroresultado;
	}

	public void setSeqcentroresultado(BigDecimal seqcentroresultado) {
		this.seqcentroresultado = seqcentroresultado;
	}
	

	
	

}
