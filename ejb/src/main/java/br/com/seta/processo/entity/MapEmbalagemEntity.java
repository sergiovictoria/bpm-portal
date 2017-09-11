package br.com.seta.processo.entity;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "MAP_FAMEMBALAGEM")
public class MapEmbalagemEntity implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private MapFamembalagemId id;
	private MapFamiliaEntity mapFamiliaEntity;
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


	public MapEmbalagemEntity() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "seqfamilia", column = @Column(name = "SEQFAMILIA", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "qtdembalagem", column = @Column(name = "QTDEMBALAGEM", nullable = false, precision = 12, scale = 6)) })
	public MapFamembalagemId getId() {
		return this.id;
	}

	public void setId(MapFamembalagemId id) {
		this.id = id;
	}

	
	@Column(name = "EMBALAGEM", nullable = false, length = 2)
	public String getEmbalagem() {
		return this.embalagem;
	}

	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}

	@Column(name = "PESOBRUTO", nullable = false, precision = 7, scale = 3)
	public BigDecimal getPesobruto() {
		return this.pesobruto;
	}

	public void setPesobruto(BigDecimal pesobruto) {
		this.pesobruto = pesobruto;
	}

	@Column(name = "PESOLIQUIDO", nullable = false, precision = 7, scale = 3)
	public BigDecimal getPesoliquido() {
		return this.pesoliquido;
	}

	public void setPesoliquido(BigDecimal pesoliquido) {
		this.pesoliquido = pesoliquido;
	}

	@Column(name = "ALTURA", nullable = false, precision = 9, scale = 6)
	public BigDecimal getAltura() {
		return this.altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	@Column(name = "LARGURA", nullable = false, precision = 9, scale = 6)
	public BigDecimal getLargura() {
		return this.largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	@Column(name = "PROFUNDIDADE", nullable = false, precision = 9, scale = 6)
	public BigDecimal getProfundidade() {
		return this.profundidade;
	}

	public void setProfundidade(BigDecimal profundidade) {
		this.profundidade = profundidade;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "INDREPLICACAO", length = 1)
	public String getIndreplicacao() {
		return this.indreplicacao;
	}

	public void setIndreplicacao(String indreplicacao) {
		this.indreplicacao = indreplicacao;
	}

	@Column(name = "INDGEROUREPLICACAO", length = 1)
	public String getIndgeroureplicacao() {
		return this.indgeroureplicacao;
	}

	public void setIndgeroureplicacao(String indgeroureplicacao) {
		this.indgeroureplicacao = indgeroureplicacao;
	}

	@Column(name = "QTDUNIDEMB", precision = 12, scale = 6)
	public BigDecimal getQtdunidemb() {
		return this.qtdunidemb;
	}

	public void setQtdunidemb(BigDecimal qtdunidemb) {
		this.qtdunidemb = qtdunidemb;
	}

	@Column(name = "MULTEQPEMB", precision = 10, scale = 6)
	public BigDecimal getMulteqpemb() {
		return this.multeqpemb;
	}

	public void setMulteqpemb(BigDecimal multeqpemb) {
		this.multeqpemb = multeqpemb;
	}

	@Column(name = "EMBPESAVEL", length = 1)
	public String getEmbpesavel() {
		return this.embpesavel;
	}

	public void setEmbpesavel(String embpesavel) {
		this.embpesavel = embpesavel;
	}

	@Column(name = "FATORMULTVOLUME", precision = 12, scale = 6)
	public BigDecimal getFatormultvolume() {
		return this.fatormultvolume;
	}

	public void setFatormultvolume(BigDecimal fatormultvolume) {
		this.fatormultvolume = fatormultvolume;
	}

	@Column(name = "EMBDECIMAL", length = 1)
	public String getEmbdecimal() {
		return this.embdecimal;
	}

	public void setEmbdecimal(String embdecimal) {
		this.embdecimal = embdecimal;
	}

	@Column(name = "INDGERACAPACVOL", length = 1)
	public String getIndgeracapacvol() {
		return this.indgeracapacvol;
	}

	public void setIndgeracapacvol(String indgeracapacvol) {
		this.indgeracapacvol = indgeracapacvol;
	}

	@Column(name = "MULTEQPEMBALAGEM", length = 2)
	public String getMulteqpembalagem() {
		return this.multeqpembalagem;
	}

	public void setMulteqpembalagem(String multeqpembalagem) {
		this.multeqpembalagem = multeqpembalagem;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@Column(name = "LITROS", precision = 7, scale = 3)
	public BigDecimal getLitros() {
		return this.litros;
	}

	public void setLitros(BigDecimal litros) {
		this.litros = litros;
	}

	@Column(name = "INDGERASEPARACAO", length = 1)
	public String getIndgeraseparacao() {
		return this.indgeraseparacao;
	}

	public void setIndgeraseparacao(String indgeraseparacao) {
		this.indgeraseparacao = indgeraseparacao;
	}

	@Column(name = "TIPOEMBPRECO", length = 1)
	public String getTipoembpreco() {
		return this.tipoembpreco;
	}

	public void setTipoembpreco(String tipoembpreco) {
		this.tipoembpreco = tipoembpreco;
	}

	@Column(name = "STATUSEMBPRECO", length = 1)
	public String getStatusembpreco() {
		return this.statusembpreco;
	}

	public void setStatusembpreco(String statusembpreco) {
		this.statusembpreco = statusembpreco;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAHORALTEMBPRECO", length = 7)
	public Date getDtahoraltembpreco() {
		return this.dtahoraltembpreco;
	}

	public void setDtahoraltembpreco(Date dtahoraltembpreco) {
		this.dtahoraltembpreco = dtahoraltembpreco;
	}

	@Column(name = "USUALTEMBPRECO", length = 12)
	public String getUsualtembpreco() {
		return this.usualtembpreco;
	}

	public void setUsualtembpreco(String usualtembpreco) {
		this.usualtembpreco = usualtembpreco;
	}

	@Column(name = "PESOMEDIDAADIC", precision = 12, scale = 6)
	public BigDecimal getPesomedidaadic() {
		return this.pesomedidaadic;
	}

	public void setPesomedidaadic(BigDecimal pesomedidaadic) {
		this.pesomedidaadic = pesomedidaadic;
	}

	@Column(name = "EMBCONVNFE", length = 2)
	public String getEmbconvnfe() {
		return this.embconvnfe;
	}

	public void setEmbconvnfe(String embconvnfe) {
		this.embconvnfe = embconvnfe;
	}

	@Column(name = "FATORCONVEMBNFE", precision = 10, scale = 6)
	public BigDecimal getFatorconvembnfe() {
		return this.fatorconvembnfe;
	}

	public void setFatorconvembnfe(BigDecimal fatorconvembnfe) {
		this.fatorconvembnfe = fatorconvembnfe;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAHORALTERACARGAPDV", length = 7)
	public Date getDtahoralteracargapdv() {
		return this.dtahoralteracargapdv;
	}

	public void setDtahoralteracargapdv(Date dtahoralteracargapdv) {
		this.dtahoralteracargapdv = dtahoralteracargapdv;
	}

	@Column(name = "USUARIOALTERACAO", length = 12)
	public String getUsuarioalteracao() {
		return this.usuarioalteracao;
	}

	public void setUsuarioalteracao(String usuarioalteracao) {
		this.usuarioalteracao = usuarioalteracao;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEQFAMILIA", nullable = false, insertable = false, updatable = false)
	public MapFamiliaEntity getMapFamiliaEntity() {
		return mapFamiliaEntity;
	}

	public void setMapFamiliaEntity(MapFamiliaEntity mapFamiliaEntity) {
		this.mapFamiliaEntity = mapFamiliaEntity;
	}
    
    

}
