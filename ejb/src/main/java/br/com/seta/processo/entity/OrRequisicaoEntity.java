package br.com.seta.processo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "OR_REQUISICAO")
public class OrRequisicaoEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long seqrequisicao;
	private short nroempresa;
	private short nroempresaorc;
	private String codmodelo;
	private short cgo;
	private String especienf;
	private Integer seqpessoa;
	private BigDecimal versaopessoa;
	private long nrorequisicao;
	private Date dtacompra;
	private Date dtainclusao;
	private Short codhistorico;
	private String observacao;
	private BigDecimal valor;
	private BigDecimal vlrdescontos;
	private BigDecimal vlroutrasopdesc;
	private BigDecimal vlracrescimos;
	private BigDecimal vlrliqreq;
	private String prazopagto;
	private String observacaofin;
	private String status;
	private String usualteracao;
	private Date dtaalteracao;
	private String tipopgto;
	private Short qtdparcela;
	private Short diasentrevenc;
	private Date dtavencinicial;
	private String diafixo;
	private String usuautorizacao;
	private Date dtaautorizacao;
	private Short seqcomprador;
	private String autorizado;
	private String autorizadonivel2;
	private String usuautorizacao2;
	private Date dtaautorizacao2;
	private BigDecimal vlrautorizado;
	private Integer seqtransportador;
	private String exigeitensnota;
	private Short nroempresanatdesp;
	private Boolean mcsseqcontrato;
	private Set<OrReqitensdespesaEntity> reqitensdespesaEntities = new HashSet<OrReqitensdespesaEntity>(0);
	private Set<OrReqplanilhalanctoEntity> reqplanilhalanctoEntities = new HashSet<OrReqplanilhalanctoEntity>(0);
	private Set<OrRequisicaovenctoEntity> requisicaovenctoEntities = new HashSet<OrRequisicaovenctoEntity>(0);

	public OrRequisicaoEntity() {
	}

	public OrRequisicaoEntity(long seqrequisicao, short nroempresa,
			short nroempresaorc, short cgo, long nrorequisicao, Date dtacompra,
			Date dtainclusao) {
		this.seqrequisicao = seqrequisicao;
		this.nroempresa = nroempresa;
		this.nroempresaorc = nroempresaorc;
		this.cgo = cgo;
		this.nrorequisicao = nrorequisicao;
		this.dtacompra = dtacompra;
		this.dtainclusao = dtainclusao;
	}

	public OrRequisicaoEntity(long seqrequisicao, short nroempresa,
			short nroempresaorc, String codmodelo, short cgo, String especienf,
			Integer seqpessoa, BigDecimal versaopessoa, long nrorequisicao,
			Date dtacompra, Date dtainclusao, Short codhistorico,
			String observacao, BigDecimal valor, BigDecimal vlrdescontos,
			BigDecimal vlroutrasopdesc, BigDecimal vlracrescimos,
			BigDecimal vlrliqreq, String prazopagto, String observacaofin,
			String status, String usualteracao, Date dtaalteracao,
			String tipopgto, Short qtdparcela, Short diasentrevenc,
			Date dtavencinicial, String diafixo, String usuautorizacao,
			Date dtaautorizacao, Short seqcomprador, String autorizado,
			String autorizadonivel2, String usuautorizacao2,
			Date dtaautorizacao2, BigDecimal vlrautorizado,
			Integer seqtransportador, String exigeitensnota,
			Short nroempresanatdesp, Boolean mcsseqcontrato) {
		this.seqrequisicao = seqrequisicao;
		this.nroempresa = nroempresa;
		this.nroempresaorc = nroempresaorc;
		this.codmodelo = codmodelo;
		this.cgo = cgo;
		this.especienf = especienf;
		this.seqpessoa = seqpessoa;
		this.versaopessoa = versaopessoa;
		this.nrorequisicao = nrorequisicao;
		this.dtacompra = dtacompra;
		this.dtainclusao = dtainclusao;
		this.codhistorico = codhistorico;
		this.observacao = observacao;
		this.valor = valor;
		this.vlrdescontos = vlrdescontos;
		this.vlroutrasopdesc = vlroutrasopdesc;
		this.vlracrescimos = vlracrescimos;
		this.vlrliqreq = vlrliqreq;
		this.prazopagto = prazopagto;
		this.observacaofin = observacaofin;
		this.status = status;
		this.usualteracao = usualteracao;
		this.dtaalteracao = dtaalteracao;
		this.tipopgto = tipopgto;
		this.qtdparcela = qtdparcela;
		this.diasentrevenc = diasentrevenc;
		this.dtavencinicial = dtavencinicial;
		this.diafixo = diafixo;
		this.usuautorizacao = usuautorizacao;
		this.dtaautorizacao = dtaautorizacao;
		this.seqcomprador = seqcomprador;
		this.autorizado = autorizado;
		this.autorizadonivel2 = autorizadonivel2;
		this.usuautorizacao2 = usuautorizacao2;
		this.dtaautorizacao2 = dtaautorizacao2;
		this.vlrautorizado = vlrautorizado;
		this.seqtransportador = seqtransportador;
		this.exigeitensnota = exigeitensnota;
		this.nroempresanatdesp = nroempresanatdesp;
		this.mcsseqcontrato = mcsseqcontrato;
	}

	@Id
	@Column(name = "SEQREQUISICAO", unique = true, nullable = false, precision = 15, scale = 0)
	public long getSeqrequisicao() {
		return this.seqrequisicao;
	}

	public void setSeqrequisicao(long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}

	@Column(name = "NROEMPRESA", nullable = false, precision = 3, scale = 0)
	public short getNroempresa() {
		return this.nroempresa;
	}

	public void setNroempresa(short nroempresa) {
		this.nroempresa = nroempresa;
	}

	@Column(name = "NROEMPRESAORC", nullable = false, precision = 3, scale = 0)
	public short getNroempresaorc() {
		return this.nroempresaorc;
	}

	public void setNroempresaorc(short nroempresaorc) {
		this.nroempresaorc = nroempresaorc;
	}

	@Column(name = "CODMODELO", length = 2)
	public String getCodmodelo() {
		return this.codmodelo;
	}

	public void setCodmodelo(String codmodelo) {
		this.codmodelo = codmodelo;
	}

	@Column(name = "CGO", nullable = false, precision = 3, scale = 0)
	public short getCgo() {
		return this.cgo;
	}

	public void setCgo(short cgo) {
		this.cgo = cgo;
	}

	@Column(name = "ESPECIENF", length = 4)
	public String getEspecienf() {
		return this.especienf;
	}

	public void setEspecienf(String especienf) {
		this.especienf = especienf;
	}

	@Column(name = "SEQPESSOA", precision = 8, scale = 0)
	public Integer getSeqpessoa() {
		return this.seqpessoa;
	}

	public void setSeqpessoa(Integer seqpessoa) {
		this.seqpessoa = seqpessoa;
	}

	@Column(name = "VERSAOPESSOA", precision = 2, scale = 0)
	public BigDecimal getVersaopessoa() {
		return this.versaopessoa;
	}

	public void setVersaopessoa(BigDecimal versaopessoa) {
		this.versaopessoa = versaopessoa;
	}

	@Column(name = "NROREQUISICAO", nullable = false, precision = 10, scale = 0)
	public long getNrorequisicao() {
		return this.nrorequisicao;
	}

	public void setNrorequisicao(long nrorequisicao) {
		this.nrorequisicao = nrorequisicao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTACOMPRA", nullable = false, length = 7)
	public Date getDtacompra() {
		return this.dtacompra;
	}

	public void setDtacompra(Date dtacompra) {
		this.dtacompra = dtacompra;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAINCLUSAO", nullable = false, length = 7)
	public Date getDtainclusao() {
		return this.dtainclusao;
	}

	public void setDtainclusao(Date dtainclusao) {
		this.dtainclusao = dtainclusao;
	}

	@Column(name = "CODHISTORICO", precision = 4, scale = 0)
	public Short getCodhistorico() {
		return this.codhistorico;
	}

	public void setCodhistorico(Short codhistorico) {
		this.codhistorico = codhistorico;
	}

	@Column(name = "OBSERVACAO", length = 250)
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "VALOR", precision = 15)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Column(name = "VLRDESCONTOS", precision = 15)
	public BigDecimal getVlrdescontos() {
		return this.vlrdescontos;
	}

	public void setVlrdescontos(BigDecimal vlrdescontos) {
		this.vlrdescontos = vlrdescontos;
	}

	@Column(name = "VLROUTRASOPDESC", precision = 15)
	public BigDecimal getVlroutrasopdesc() {
		return this.vlroutrasopdesc;
	}

	public void setVlroutrasopdesc(BigDecimal vlroutrasopdesc) {
		this.vlroutrasopdesc = vlroutrasopdesc;
	}

	@Column(name = "VLRACRESCIMOS", precision = 15)
	public BigDecimal getVlracrescimos() {
		return this.vlracrescimos;
	}

	public void setVlracrescimos(BigDecimal vlracrescimos) {
		this.vlracrescimos = vlracrescimos;
	}

	@Column(name = "VLRLIQREQ", precision = 15)
	public BigDecimal getVlrliqreq() {
		return this.vlrliqreq;
	}

	public void setVlrliqreq(BigDecimal vlrliqreq) {
		this.vlrliqreq = vlrliqreq;
	}

	@Column(name = "PRAZOPAGTO", length = 100)
	public String getPrazopagto() {
		return this.prazopagto;
	}

	public void setPrazopagto(String prazopagto) {
		this.prazopagto = prazopagto;
	}

	@Column(name = "OBSERVACAOFIN", length = 250)
	public String getObservacaofin() {
		return this.observacaofin;
	}

	public void setObservacaofin(String observacaofin) {
		this.observacaofin = observacaofin;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "USUALTERACAO", length = 12)
	public String getUsualteracao() {
		return this.usualteracao;
	}

	public void setUsualteracao(String usualteracao) {
		this.usualteracao = usualteracao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAALTERACAO", length = 7)
	public Date getDtaalteracao() {
		return this.dtaalteracao;
	}

	public void setDtaalteracao(Date dtaalteracao) {
		this.dtaalteracao = dtaalteracao;
	}

	@Column(name = "TIPOPGTO", length = 1)
	public String getTipopgto() {
		return this.tipopgto;
	}

	public void setTipopgto(String tipopgto) {
		this.tipopgto = tipopgto;
	}

	@Column(name = "QTDPARCELA", precision = 3, scale = 0)
	public Short getQtdparcela() {
		return this.qtdparcela;
	}

	public void setQtdparcela(Short qtdparcela) {
		this.qtdparcela = qtdparcela;
	}

	@Column(name = "DIASENTREVENC", precision = 3, scale = 0)
	public Short getDiasentrevenc() {
		return this.diasentrevenc;
	}

	public void setDiasentrevenc(Short diasentrevenc) {
		this.diasentrevenc = diasentrevenc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCINICIAL", length = 7)
	public Date getDtavencinicial() {
		return this.dtavencinicial;
	}

	public void setDtavencinicial(Date dtavencinicial) {
		this.dtavencinicial = dtavencinicial;
	}

	@Column(name = "DIAFIXO", length = 1)
	public String getDiafixo() {
		return this.diafixo;
	}

	public void setDiafixo(String diafixo) {
		this.diafixo = diafixo;
	}

	@Column(name = "USUAUTORIZACAO", length = 12)
	public String getUsuautorizacao() {
		return this.usuautorizacao;
	}

	public void setUsuautorizacao(String usuautorizacao) {
		this.usuautorizacao = usuautorizacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAAUTORIZACAO", length = 7)
	public Date getDtaautorizacao() {
		return this.dtaautorizacao;
	}

	public void setDtaautorizacao(Date dtaautorizacao) {
		this.dtaautorizacao = dtaautorizacao;
	}

	@Column(name = "SEQCOMPRADOR", precision = 3, scale = 0)
	public Short getSeqcomprador() {
		return this.seqcomprador;
	}

	public void setSeqcomprador(Short seqcomprador) {
		this.seqcomprador = seqcomprador;
	}

	@Column(name = "AUTORIZADO", length = 1)
	public String getAutorizado() {
		return this.autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}

	@Column(name = "AUTORIZADONIVEL2", length = 1)
	public String getAutorizadonivel2() {
		return this.autorizadonivel2;
	}

	public void setAutorizadonivel2(String autorizadonivel2) {
		this.autorizadonivel2 = autorizadonivel2;
	}

	@Column(name = "USUAUTORIZACAO2", length = 12)
	public String getUsuautorizacao2() {
		return this.usuautorizacao2;
	}

	public void setUsuautorizacao2(String usuautorizacao2) {
		this.usuautorizacao2 = usuautorizacao2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAAUTORIZACAO2", length = 7)
	public Date getDtaautorizacao2() {
		return this.dtaautorizacao2;
	}

	public void setDtaautorizacao2(Date dtaautorizacao2) {
		this.dtaautorizacao2 = dtaautorizacao2;
	}

	@Column(name = "VLRAUTORIZADO", precision = 15)
	public BigDecimal getVlrautorizado() {
		return this.vlrautorizado;
	}

	public void setVlrautorizado(BigDecimal vlrautorizado) {
		this.vlrautorizado = vlrautorizado;
	}

	@Column(name = "SEQTRANSPORTADOR", precision = 8, scale = 0)
	public Integer getSeqtransportador() {
		return this.seqtransportador;
	}

	public void setSeqtransportador(Integer seqtransportador) {
		this.seqtransportador = seqtransportador;
	}

	@Column(name = "EXIGEITENSNOTA", length = 1)
	public String getExigeitensnota() {
		return this.exigeitensnota;
	}

	public void setExigeitensnota(String exigeitensnota) {
		this.exigeitensnota = exigeitensnota;
	}

	@Column(name = "NROEMPRESANATDESP", precision = 3, scale = 0)
	public Short getNroempresanatdesp() {
		return this.nroempresanatdesp;
	}

	public void setNroempresanatdesp(Short nroempresanatdesp) {
		this.nroempresanatdesp = nroempresanatdesp;
	}

	@Column(name = "MCSSEQCONTRATO", precision = 1, scale = 0)
	public Boolean getMcsseqcontrato() {
		return this.mcsseqcontrato;
	}

	public void setMcsseqcontrato(Boolean mcsseqcontrato) {
		this.mcsseqcontrato = mcsseqcontrato;
	}
	
	

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="SEQREQUISICAO")
	public Set<OrReqitensdespesaEntity> getReqitensdespesaEntities() {
		return reqitensdespesaEntities;
	}

	public void setReqitensdespesaEntities(
			Set<OrReqitensdespesaEntity> reqitensdespesaEntities) {
		this.reqitensdespesaEntities = reqitensdespesaEntities;
	}

	
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="SEQREQUISICAO")
	public Set<OrReqplanilhalanctoEntity> getReqplanilhalanctoEntities() {
		return reqplanilhalanctoEntities;
	}

	public void setReqplanilhalanctoEntities(
			Set<OrReqplanilhalanctoEntity> reqplanilhalanctoEntities) {
		this.reqplanilhalanctoEntities = reqplanilhalanctoEntities;
	}

	
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="SEQREQUISICAO")
	public Set<OrRequisicaovenctoEntity> getRequisicaovenctoEntities() {
		return requisicaovenctoEntities;
	}

	public void setRequisicaovenctoEntities(
			Set<OrRequisicaovenctoEntity> requisicaovenctoEntities) {
		this.requisicaovenctoEntities = requisicaovenctoEntities;
	}

	
	

}
