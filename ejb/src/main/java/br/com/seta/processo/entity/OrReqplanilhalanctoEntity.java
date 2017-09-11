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
@Table(name = "OR_REQPLANILHALANCTO")
public class OrReqplanilhalanctoEntity implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private OrReqplanilhalanctoId id;
	private String nroplanilha;
	private short nroempresa;
	private short filial;
	private Long contadebito;
	private String tipoentidadedb;
	private Integer codentidadedb;
	private Integer centrocustodb;
	private Long contacredito;
	private String tipoentidadecr;
	private Integer codentidadecr;
	private Integer centrocustocr;
	private String historico;
	private BigDecimal vlrlancamento;
	private Date dtacontabiliza;
	private String tablink;
	private Long nrodocumento;
	private String situacao;
	private String tipocontab;
	private BigDecimal percrateio;

	public OrReqplanilhalanctoEntity() {
	}

	public OrReqplanilhalanctoEntity(OrReqplanilhalanctoId id, String nroplanilha,
			short nroempresa, short filial) {
		this.id = id;
		this.nroplanilha = nroplanilha;
		this.nroempresa = nroempresa;
		this.filial = filial;
	}

	public OrReqplanilhalanctoEntity(OrReqplanilhalanctoId id, String nroplanilha,
			short nroempresa, short filial, Long contadebito,
			String tipoentidadedb, Integer codentidadedb,
			Integer centrocustodb, Long contacredito, String tipoentidadecr,
			Integer codentidadecr, Integer centrocustocr, String historico,
			BigDecimal vlrlancamento, Date dtacontabiliza, String tablink,
			Long nrodocumento, String situacao, String tipocontab,
			BigDecimal percrateio) {
		this.id = id;
		this.nroplanilha = nroplanilha;
		this.nroempresa = nroempresa;
		this.filial = filial;
		this.contadebito = contadebito;
		this.tipoentidadedb = tipoentidadedb;
		this.codentidadedb = codentidadedb;
		this.centrocustodb = centrocustodb;
		this.contacredito = contacredito;
		this.tipoentidadecr = tipoentidadecr;
		this.codentidadecr = codentidadecr;
		this.centrocustocr = centrocustocr;
		this.historico = historico;
		this.vlrlancamento = vlrlancamento;
		this.dtacontabiliza = dtacontabiliza;
		this.tablink = tablink;
		this.nrodocumento = nrodocumento;
		this.situacao = situacao;
		this.tipocontab = tipocontab;
		this.percrateio = percrateio;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "seqrequisicao", column = @Column(name = "SEQREQUISICAO", nullable = false, precision = 15, scale = 0)),
			@AttributeOverride(name = "nrolinha", column = @Column(name = "NROLINHA", nullable = false, precision = 3, scale = 0)) })
	public OrReqplanilhalanctoId getId() {
		return this.id;
	}

	public void setId(OrReqplanilhalanctoId id) {
		this.id = id;
	}

	@Column(name = "NROPLANILHA", nullable = false, length = 12)
	public String getNroplanilha() {
		return this.nroplanilha;
	}

	public void setNroplanilha(String nroplanilha) {
		this.nroplanilha = nroplanilha;
	}

	@Column(name = "NROEMPRESA", nullable = false, precision = 3, scale = 0)
	public short getNroempresa() {
		return this.nroempresa;
	}

	public void setNroempresa(short nroempresa) {
		this.nroempresa = nroempresa;
	}

	@Column(name = "FILIAL", nullable = false, precision = 3, scale = 0)
	public short getFilial() {
		return this.filial;
	}

	public void setFilial(short filial) {
		this.filial = filial;
	}

	@Column(name = "CONTADEBITO", precision = 15, scale = 0)
	public Long getContadebito() {
		return this.contadebito;
	}

	public void setContadebito(Long contadebito) {
		this.contadebito = contadebito;
	}

	@Column(name = "TIPOENTIDADEDB", length = 2)
	public String getTipoentidadedb() {
		return this.tipoentidadedb;
	}

	public void setTipoentidadedb(String tipoentidadedb) {
		this.tipoentidadedb = tipoentidadedb;
	}

	@Column(name = "CODENTIDADEDB", precision = 8, scale = 0)
	public Integer getCodentidadedb() {
		return this.codentidadedb;
	}

	public void setCodentidadedb(Integer codentidadedb) {
		this.codentidadedb = codentidadedb;
	}

	@Column(name = "CENTROCUSTODB", precision = 6, scale = 0)
	public Integer getCentrocustodb() {
		return this.centrocustodb;
	}

	public void setCentrocustodb(Integer centrocustodb) {
		this.centrocustodb = centrocustodb;
	}

	@Column(name = "CONTACREDITO", precision = 15, scale = 0)
	public Long getContacredito() {
		return this.contacredito;
	}

	public void setContacredito(Long contacredito) {
		this.contacredito = contacredito;
	}

	@Column(name = "TIPOENTIDADECR", length = 2)
	public String getTipoentidadecr() {
		return this.tipoentidadecr;
	}

	public void setTipoentidadecr(String tipoentidadecr) {
		this.tipoentidadecr = tipoentidadecr;
	}

	@Column(name = "CODENTIDADECR", precision = 8, scale = 0)
	public Integer getCodentidadecr() {
		return this.codentidadecr;
	}

	public void setCodentidadecr(Integer codentidadecr) {
		this.codentidadecr = codentidadecr;
	}

	@Column(name = "CENTROCUSTOCR", precision = 6, scale = 0)
	public Integer getCentrocustocr() {
		return this.centrocustocr;
	}

	public void setCentrocustocr(Integer centrocustocr) {
		this.centrocustocr = centrocustocr;
	}

	@Column(name = "HISTORICO", length = 250)
	public String getHistorico() {
		return this.historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	@Column(name = "VLRLANCAMENTO", precision = 15)
	public BigDecimal getVlrlancamento() {
		return this.vlrlancamento;
	}

	public void setVlrlancamento(BigDecimal vlrlancamento) {
		this.vlrlancamento = vlrlancamento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTACONTABILIZA", length = 7)
	public Date getDtacontabiliza() {
		return this.dtacontabiliza;
	}

	public void setDtacontabiliza(Date dtacontabiliza) {
		this.dtacontabiliza = dtacontabiliza;
	}

	@Column(name = "TABLINK", length = 3)
	public String getTablink() {
		return this.tablink;
	}

	public void setTablink(String tablink) {
		this.tablink = tablink;
	}

	@Column(name = "NRODOCUMENTO", precision = 15, scale = 0)
	public Long getNrodocumento() {
		return this.nrodocumento;
	}

	public void setNrodocumento(Long nrodocumento) {
		this.nrodocumento = nrodocumento;
	}

	@Column(name = "SITUACAO", length = 1)
	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Column(name = "TIPOCONTAB", length = 1)
	public String getTipocontab() {
		return this.tipocontab;
	}

	public void setTipocontab(String tipocontab) {
		this.tipocontab = tipocontab;
	}

	@Column(name = "PERCRATEIO", precision = 7, scale = 4)
	public BigDecimal getPercrateio() {
		return this.percrateio;
	}

	public void setPercrateio(BigDecimal percrateio) {
		this.percrateio = percrateio;
	}

}
