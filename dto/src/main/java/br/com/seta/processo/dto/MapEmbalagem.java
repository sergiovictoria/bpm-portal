package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MapEmbalagem implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private BigDecimal seqfamilia;
	private BigDecimal qtdembalagem;
	private MapFamilia mapFamilia;
	private String embalagem;
	private BigDecimal pesobruto;
	private BigDecimal pesoliquido;
	private BigDecimal altura;
	private BigDecimal largura;
	private BigDecimal profundidade;
	private String status;
	private String indreplicacao;
	private String indgeroureplicacao;
	private BigDecimal qtdunidemb;
	private BigDecimal multeqpemb;
	private String embpesavel;
	private BigDecimal fatormultvolume;
	private String embdecimal;
	private String indgeracapacvol;
	private String multeqpembalagem;
	private long nrobaseexportacao;
	private BigDecimal litros;
	private String indgeraseparacao;
	private String tipoembpreco;
	private String statusembpreco;
	private Date dtahoraltembpreco;
	private String usualtembpreco;
	private BigDecimal pesomedidaadic;
	private String embconvnfe;
	private BigDecimal fatorconvembnfe;
	private Date dtahoralteracargapdv;
	private String usuarioalteracao;
	
	private String tipo;


	public MapEmbalagem() {
	}

	public BigDecimal getSeqfamilia() {
		return seqfamilia;
	}

	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}

	public BigDecimal getQtdembalagem() {
		return qtdembalagem;
	}

	public void setQtdembalagem(BigDecimal qtdembalagem) {
		this.qtdembalagem = qtdembalagem;
	}

	public MapFamilia getMapFamilia() {
		return mapFamilia;
	}

	public void setMapFamilia(MapFamilia mapFamilia) {
		this.mapFamilia = mapFamilia;
	}

	public String getEmbalagem() {
		return embalagem;
	}


	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}

	public BigDecimal getPesobruto() {
		return pesobruto;
	}

	public void setPesobruto(BigDecimal pesobruto) {
		this.pesobruto = pesobruto;
	}


	public BigDecimal getPesoliquido() {
		return pesoliquido;
	}

	public void setPesoliquido(BigDecimal pesoliquido) {
		this.pesoliquido = pesoliquido;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public BigDecimal getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(BigDecimal profundidade) {
		this.profundidade = profundidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIndreplicacao() {
		return indreplicacao;
	}

	public void setIndreplicacao(String indreplicacao) {
		this.indreplicacao = indreplicacao;
	}

	public String getIndgeroureplicacao() {
		return indgeroureplicacao;
	}

	public void setIndgeroureplicacao(String indgeroureplicacao) {
		this.indgeroureplicacao = indgeroureplicacao;
	}

	public BigDecimal getQtdunidemb() {
		return qtdunidemb;
	}

	public void setQtdunidemb(BigDecimal qtdunidemb) {
		this.qtdunidemb = qtdunidemb;
	}

	public BigDecimal getMulteqpemb() {
		return multeqpemb;
	}

	public void setMulteqpemb(BigDecimal multeqpemb) {
		this.multeqpemb = multeqpemb;
	}

	public String getEmbpesavel() {
		return embpesavel;
	}

	public void setEmbpesavel(String embpesavel) {
		this.embpesavel = embpesavel;
	}

	public BigDecimal getFatormultvolume() {
		return fatormultvolume;
	}

	public void setFatormultvolume(BigDecimal fatormultvolume) {
		this.fatormultvolume = fatormultvolume;
	}

	public String getEmbdecimal() {
		return embdecimal;
	}

	public void setEmbdecimal(String embdecimal) {
		this.embdecimal = embdecimal;
	}

	public String getIndgeracapacvol() {
		return indgeracapacvol;
	}

	public void setIndgeracapacvol(String indgeracapacvol) {
		this.indgeracapacvol = indgeracapacvol;
	}

	public String getMulteqpembalagem() {
		return multeqpembalagem;
	}

	public void setMulteqpembalagem(String multeqpembalagem) {
		this.multeqpembalagem = multeqpembalagem;
	}

	public long getNrobaseexportacao() {
		return nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	public BigDecimal getLitros() {
		return litros;
	}

	public void setLitros(BigDecimal litros) {
		this.litros = litros;
	}

	public String getIndgeraseparacao() {
		return indgeraseparacao;
	}

	public void setIndgeraseparacao(String indgeraseparacao) {
		this.indgeraseparacao = indgeraseparacao;
	}

	public String getTipoembpreco() {
		return tipoembpreco;
	}

	public void setTipoembpreco(String tipoembpreco) {
		this.tipoembpreco = tipoembpreco;
	}

	public String getStatusembpreco() {
		return statusembpreco;
	}

	public void setStatusembpreco(String statusembpreco) {
		this.statusembpreco = statusembpreco;
	}

	public Date getDtahoraltembpreco() {
		return dtahoraltembpreco;
	}

	public void setDtahoraltembpreco(Date dtahoraltembpreco) {
		this.dtahoraltembpreco = dtahoraltembpreco;
	}

	public String getUsualtembpreco() {
		return usualtembpreco;
	}

	public void setUsualtembpreco(String usualtembpreco) {
		this.usualtembpreco = usualtembpreco;
	}

	public BigDecimal getPesomedidaadic() {
		return pesomedidaadic;
	}

	public void setPesomedidaadic(BigDecimal pesomedidaadic) {
		this.pesomedidaadic = pesomedidaadic;
	}

	public String getEmbconvnfe() {
		return embconvnfe;
	}

	public void setEmbconvnfe(String embconvnfe) {
		this.embconvnfe = embconvnfe;
	}

	public BigDecimal getFatorconvembnfe() {
		return fatorconvembnfe;
	}

	public void setFatorconvembnfe(BigDecimal fatorconvembnfe) {
		this.fatorconvembnfe = fatorconvembnfe;
	}

	public Date getDtahoralteracargapdv() {
		return dtahoralteracargapdv;
	}

	public void setDtahoralteracargapdv(Date dtahoralteracargapdv) {
		this.dtahoralteracargapdv = dtahoralteracargapdv;
	}

	public String getUsuarioalteracao() {
		return usuarioalteracao;
	}

	public void setUsuarioalteracao(String usuarioalteracao) {
		this.usuarioalteracao = usuarioalteracao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
