package br.com.seta.processo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OR_NFDESPESA")
public class OrNfdespesaEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private long seqnota;
	private long nronota;
	private String serie;
	private String subserie;
	private Integer seqpessoa;
	private short nroempresa;
	private short nroempresaorc;
	private short cgo;
	private Date dtaemissao;
	private Date dtaentrada;
	private BigDecimal valor;
	private String prazopagto;
	private BigDecimal aliqinss;
	private BigDecimal vlrinss;
	private BigDecimal aliqir;
	private BigDecimal vlrir;
	private BigDecimal aliqissst;
	private BigDecimal vlrissst;
	private BigDecimal aliqpis;
	private BigDecimal vlrpis;
	private BigDecimal aliqcofins;
	private BigDecimal vlrcofins;
	private BigDecimal aliqcssll;
	private BigDecimal vlrcssll;
	private String integradofisci;
	private String integradocapitis;
	private Long identfisci;
	private String autorizado;
	private String usuautorizacao;
	private Date dtaautorizacao;
	private String usualteracao;
	private Date dtaalteracao;
	private Integer nroap;
	private String observacao;
	private BigDecimal vlrdescontos;
	private BigDecimal vlracrescimos;
	private String importado;
	private Byte versaopessoa;
	private String codmodelo;
	private Short codhistorico;
	private Integer cfop;
	private String tipotributacao;
	private BigDecimal aliqicms;
	private BigDecimal aliqiss;
	private BigDecimal aliqsestsenat;
	private Date dtavenctoinss;
	private Date dtavenctoir;
	private Date dtavenctopis;
	private Date dtavenctoicms;
	private Date dtavenctoissst;
	private Date dtavenctocofins;
	private Date dtavenctocssll;
	private Date dtavenctoiss;
	private Date dtavenctosestsenat;
	private BigDecimal vlrliqnota;
	private BigDecimal vlrsestsenat;
	private BigDecimal vlriss;
	private BigDecimal vlrisento;
	private BigDecimal vlroutras;
	private BigDecimal vlricms;
	private BigDecimal vlrbasepis;
	private BigDecimal vlrbasecofins;
	private BigDecimal vlrbaseiss;
	private BigDecimal vlrbaseicms;
	private BigDecimal vlrbaseinss;
	private BigDecimal vlrbaseir;
	private BigDecimal vlrbaseissst;
	private BigDecimal vlrbasecssll;
	private BigDecimal vlrbasesestsenat;
	private String tributicms;
	private String tributpis;
	private String tributcofins;
	private String situacao;
	private Long nroprocesso;
	private String especienf;
	private String autorizadonivel2;
	private String usuautorizacao2;
	private Date dtaautorizacao2;
	private BigDecimal vlrautorizado;
	private BigDecimal vlroutrasopdesc;
	private String exigeitensnota;
	private BigDecimal vlrfinanceiro;
	private String tipotributacaoipi;
	private String geratitipi;
	private BigDecimal aliqipi;
	private BigDecimal vlrbaseipi;
	private BigDecimal vlripi;
	private Date dtavenctoipi;
	private BigDecimal vlroutrosipi;
	private BigDecimal vlrisentoipi;
	private String integradoapuracao;
	private Date dtaretencaoiss;
	private BigDecimal seqdociss;
	private BigDecimal seqoperacaoiss;
	private BigDecimal vlrdeducaoissItem;
	private String nfe;
	private Short seqclasseconsumo;
	private Short tipoligacao;
	private Short grupotensao;
	private Short tipoassinante;
	private String retencaopisnfdesp;
	private String retencaocofinsnfdesp;
	private String situacaonfpis;
	private String situacaonfcofins;
	private Long linkerp;
	private String nfechaveacesso;
	private Short qtdparcela;
	private Short diasentrevenc;
	private Date dtavencinicial;
	private String diafixo;
	private String tipopgto;
	private Long seqrequisicao;
	private String tributpiscred;
	private String cstpiscred;
	private BigDecimal aliqpiscred;
	private BigDecimal vlrbasepiscred;
	private BigDecimal vlrpiscred;
	private String tributcofinscred;
	private String cstcofinscred;
	private BigDecimal aliqcofinscred;
	private BigDecimal vlrbasecofinscred;
	private BigDecimal vlrcofinscred;
	private String recompoeapurapc;
	private Integer nromedidor;
	private Long qtdeconsumo;
	private String usuinclusao;
	private Date dtainclusao;
	private Byte cstiss;
	private BigDecimal vlrtotfornec;
	private BigDecimal vlrservntribut;
	private BigDecimal vlrcobterceiro;
	private BigDecimal vlrdespacess;
	private BigDecimal vlrbaseicmsrettransp;
	private BigDecimal aliqicmsrettransp;
	private BigDecimal vlricmsrettransp;
	private Date dtavenctoicmsrettransp;
	private Long codrecdarfpisret;
	private Long codrecdarfcofinsret;
	private Long codrecdarfcsllret;
	private String indvenctoantprazomin;
	private Byte codsitdoc;
	private BigDecimal vlrbaseicmsdif;
	private BigDecimal aliqicmsdif;
	private BigDecimal vlricmsdif;
	private Integer nroaidfiss;
	private Short anoaidfiss;
	private Byte tiporecoliss;
	private Byte atividadeiss;
	private BigDecimal vlrbaseinsspat;
	private BigDecimal aliqinsspat;
	private BigDecimal vlrinsspat;
	private Date dtavenctoinsspat;
	private Integer seqtransportador;
	private String indutilcssemacordo;
	private Short nroempresaacordo;
	private Integer nroacordo;
	private BigDecimal seqccacordoassoc;
	private String flagupdate;
	private BigDecimal vlrpedagio;
	private Integer codservicodeiss;
	private Integer seqtpreciss;
	private Integer codbaselegaldms;
	private Byte seqmotnaoretencaoiss;
	private Short nroempresanatdesp;
	private Short codrecdarfirrf;
	private String indnfimportada;
	private String indnfimportconsistida;
	private String requisicoes;
	private Long mcsseqcontrato;
	private Short cfps;
	private BigDecimal seqtribiss;
	private Integer seqresponsavel;
	private BigDecimal vlrbaseinsspesfis;
	private BigDecimal aliqinsspesfis;
	private BigDecimal vlrinsspesfis;
	private Date dtavenctoinsspesfis;
	private BigDecimal vlrbaseirpesfis;
	private BigDecimal aliqirpesfis;
	private BigDecimal vlrirpesfis;
	private Date dtavenctoirpesfis;
	private Short codrecdarfirrfpesfis;
	private String nfechaveacessoservico;
	private Set<OrNfitensdespesaEntity> orNfitensdespesas = new HashSet<OrNfitensdespesaEntity>();

	public OrNfdespesaEntity() {
	}

	public OrNfdespesaEntity(long seqnota, long nronota, short nroempresa,
			short nroempresaorc, short cgo, Date dtaemissao, Date dtaentrada,
			BigDecimal vlrfinanceiro) {
		this.seqnota = seqnota;
		this.nronota = nronota;
		this.nroempresa = nroempresa;
		this.nroempresaorc = nroempresaorc;
		this.cgo = cgo;
		this.dtaemissao = dtaemissao;
		this.dtaentrada = dtaentrada;
		this.vlrfinanceiro = vlrfinanceiro;
	}

	public OrNfdespesaEntity(long seqnota, long nronota, String serie,
			String subserie, Integer seqpessoa, short nroempresa,
			short nroempresaorc, short cgo, Date dtaemissao, Date dtaentrada,
			BigDecimal valor, String prazopagto, BigDecimal aliqinss,
			BigDecimal vlrinss, BigDecimal aliqir, BigDecimal vlrir,
			BigDecimal aliqissst, BigDecimal vlrissst, BigDecimal aliqpis,
			BigDecimal vlrpis, BigDecimal aliqcofins, BigDecimal vlrcofins,
			BigDecimal aliqcssll, BigDecimal vlrcssll, String integradofisci,
			String integradocapitis, Long identfisci, String autorizado,
			String usuautorizacao, Date dtaautorizacao, String usualteracao,
			Date dtaalteracao, Integer nroap, String observacao,
			BigDecimal vlrdescontos, BigDecimal vlracrescimos,
			String importado, Byte versaopessoa, String codmodelo,
			Short codhistorico, Integer cfop, String tipotributacao,
			BigDecimal aliqicms, BigDecimal aliqiss, BigDecimal aliqsestsenat,
			Date dtavenctoinss, Date dtavenctoir, Date dtavenctopis,
			Date dtavenctoicms, Date dtavenctoissst, Date dtavenctocofins,
			Date dtavenctocssll, Date dtavenctoiss, Date dtavenctosestsenat,
			BigDecimal vlrliqnota, BigDecimal vlrsestsenat, BigDecimal vlriss,
			BigDecimal vlrisento, BigDecimal vlroutras, BigDecimal vlricms,
			BigDecimal vlrbasepis, BigDecimal vlrbasecofins,
			BigDecimal vlrbaseiss, BigDecimal vlrbaseicms,
			BigDecimal vlrbaseinss, BigDecimal vlrbaseir,
			BigDecimal vlrbaseissst, BigDecimal vlrbasecssll,
			BigDecimal vlrbasesestsenat, String tributicms, String tributpis,
			String tributcofins, String situacao, Long nroprocesso,
			String especienf, String autorizadonivel2, String usuautorizacao2,
			Date dtaautorizacao2, BigDecimal vlrautorizado,
			BigDecimal vlroutrasopdesc, String exigeitensnota,
			BigDecimal vlrfinanceiro, String tipotributacaoipi,
			String geratitipi, BigDecimal aliqipi, BigDecimal vlrbaseipi,
			BigDecimal vlripi, Date dtavenctoipi, BigDecimal vlroutrosipi,
			BigDecimal vlrisentoipi, String integradoapuracao,
			Date dtaretencaoiss, BigDecimal seqdociss,
			BigDecimal seqoperacaoiss, BigDecimal vlrdeducaoissItem,
			String nfe, Short seqclasseconsumo, Short tipoligacao,
			Short grupotensao, Short tipoassinante, String retencaopisnfdesp,
			String retencaocofinsnfdesp, String situacaonfpis,
			String situacaonfcofins, Long linkerp, String nfechaveacesso,
			Short qtdparcela, Short diasentrevenc, Date dtavencinicial,
			String diafixo, String tipopgto, Long seqrequisicao,
			String tributpiscred, String cstpiscred, BigDecimal aliqpiscred,
			BigDecimal vlrbasepiscred, BigDecimal vlrpiscred,
			String tributcofinscred, String cstcofinscred,
			BigDecimal aliqcofinscred, BigDecimal vlrbasecofinscred,
			BigDecimal vlrcofinscred, String recompoeapurapc,
			Integer nromedidor, Long qtdeconsumo, String usuinclusao,
			Date dtainclusao, Byte cstiss, BigDecimal vlrtotfornec,
			BigDecimal vlrservntribut, BigDecimal vlrcobterceiro,
			BigDecimal vlrdespacess, BigDecimal vlrbaseicmsrettransp,
			BigDecimal aliqicmsrettransp, BigDecimal vlricmsrettransp,
			Date dtavenctoicmsrettransp, Long codrecdarfpisret,
			Long codrecdarfcofinsret, Long codrecdarfcsllret,
			String indvenctoantprazomin, Byte codsitdoc,
			BigDecimal vlrbaseicmsdif, BigDecimal aliqicmsdif,
			BigDecimal vlricmsdif, Integer nroaidfiss, Short anoaidfiss,
			Byte tiporecoliss, Byte atividadeiss, BigDecimal vlrbaseinsspat,
			BigDecimal aliqinsspat, BigDecimal vlrinsspat,
			Date dtavenctoinsspat, Integer seqtransportador,
			String indutilcssemacordo, Short nroempresaacordo,
			Integer nroacordo, BigDecimal seqccacordoassoc, String flagupdate,
			BigDecimal vlrpedagio, Integer codservicodeiss,
			Integer seqtpreciss, Integer codbaselegaldms,
			Byte seqmotnaoretencaoiss, Short nroempresanatdesp,
			Short codrecdarfirrf, String indnfimportada,
			String indnfimportconsistida, String requisicoes,
			Long mcsseqcontrato, Short cfps, BigDecimal seqtribiss,
			Integer seqresponsavel, BigDecimal vlrbaseinsspesfis,
			BigDecimal aliqinsspesfis, BigDecimal vlrinsspesfis,
			Date dtavenctoinsspesfis, BigDecimal vlrbaseirpesfis,
			BigDecimal aliqirpesfis, BigDecimal vlrirpesfis,
			Date dtavenctoirpesfis, Short codrecdarfirrfpesfis,
			String nfechaveacessoservico, Set<OrNfitensdespesaEntity> orNfitensdespesas) {
		this.seqnota = seqnota;
		this.nronota = nronota;
		this.serie = serie;
		this.subserie = subserie;
		this.seqpessoa = seqpessoa;
		this.nroempresa = nroempresa;
		this.nroempresaorc = nroempresaorc;
		this.cgo = cgo;
		this.dtaemissao = dtaemissao;
		this.dtaentrada = dtaentrada;
		this.valor = valor;
		this.prazopagto = prazopagto;
		this.aliqinss = aliqinss;
		this.vlrinss = vlrinss;
		this.aliqir = aliqir;
		this.vlrir = vlrir;
		this.aliqissst = aliqissst;
		this.vlrissst = vlrissst;
		this.aliqpis = aliqpis;
		this.vlrpis = vlrpis;
		this.aliqcofins = aliqcofins;
		this.vlrcofins = vlrcofins;
		this.aliqcssll = aliqcssll;
		this.vlrcssll = vlrcssll;
		this.integradofisci = integradofisci;
		this.integradocapitis = integradocapitis;
		this.identfisci = identfisci;
		this.autorizado = autorizado;
		this.usuautorizacao = usuautorizacao;
		this.dtaautorizacao = dtaautorizacao;
		this.usualteracao = usualteracao;
		this.dtaalteracao = dtaalteracao;
		this.nroap = nroap;
		this.observacao = observacao;
		this.vlrdescontos = vlrdescontos;
		this.vlracrescimos = vlracrescimos;
		this.importado = importado;
		this.versaopessoa = versaopessoa;
		this.codmodelo = codmodelo;
		this.codhistorico = codhistorico;
		this.cfop = cfop;
		this.tipotributacao = tipotributacao;
		this.aliqicms = aliqicms;
		this.aliqiss = aliqiss;
		this.aliqsestsenat = aliqsestsenat;
		this.dtavenctoinss = dtavenctoinss;
		this.dtavenctoir = dtavenctoir;
		this.dtavenctopis = dtavenctopis;
		this.dtavenctoicms = dtavenctoicms;
		this.dtavenctoissst = dtavenctoissst;
		this.dtavenctocofins = dtavenctocofins;
		this.dtavenctocssll = dtavenctocssll;
		this.dtavenctoiss = dtavenctoiss;
		this.dtavenctosestsenat = dtavenctosestsenat;
		this.vlrliqnota = vlrliqnota;
		this.vlrsestsenat = vlrsestsenat;
		this.vlriss = vlriss;
		this.vlrisento = vlrisento;
		this.vlroutras = vlroutras;
		this.vlricms = vlricms;
		this.vlrbasepis = vlrbasepis;
		this.vlrbasecofins = vlrbasecofins;
		this.vlrbaseiss = vlrbaseiss;
		this.vlrbaseicms = vlrbaseicms;
		this.vlrbaseinss = vlrbaseinss;
		this.vlrbaseir = vlrbaseir;
		this.vlrbaseissst = vlrbaseissst;
		this.vlrbasecssll = vlrbasecssll;
		this.vlrbasesestsenat = vlrbasesestsenat;
		this.tributicms = tributicms;
		this.tributpis = tributpis;
		this.tributcofins = tributcofins;
		this.situacao = situacao;
		this.nroprocesso = nroprocesso;
		this.especienf = especienf;
		this.autorizadonivel2 = autorizadonivel2;
		this.usuautorizacao2 = usuautorizacao2;
		this.dtaautorizacao2 = dtaautorizacao2;
		this.vlrautorizado = vlrautorizado;
		this.vlroutrasopdesc = vlroutrasopdesc;
		this.exigeitensnota = exigeitensnota;
		this.vlrfinanceiro = vlrfinanceiro;
		this.tipotributacaoipi = tipotributacaoipi;
		this.geratitipi = geratitipi;
		this.aliqipi = aliqipi;
		this.vlrbaseipi = vlrbaseipi;
		this.vlripi = vlripi;
		this.dtavenctoipi = dtavenctoipi;
		this.vlroutrosipi = vlroutrosipi;
		this.vlrisentoipi = vlrisentoipi;
		this.integradoapuracao = integradoapuracao;
		this.dtaretencaoiss = dtaretencaoiss;
		this.seqdociss = seqdociss;
		this.seqoperacaoiss = seqoperacaoiss;
		this.vlrdeducaoissItem = vlrdeducaoissItem;
		this.nfe = nfe;
		this.seqclasseconsumo = seqclasseconsumo;
		this.tipoligacao = tipoligacao;
		this.grupotensao = grupotensao;
		this.tipoassinante = tipoassinante;
		this.retencaopisnfdesp = retencaopisnfdesp;
		this.retencaocofinsnfdesp = retencaocofinsnfdesp;
		this.situacaonfpis = situacaonfpis;
		this.situacaonfcofins = situacaonfcofins;
		this.linkerp = linkerp;
		this.nfechaveacesso = nfechaveacesso;
		this.qtdparcela = qtdparcela;
		this.diasentrevenc = diasentrevenc;
		this.dtavencinicial = dtavencinicial;
		this.diafixo = diafixo;
		this.tipopgto = tipopgto;
		this.seqrequisicao = seqrequisicao;
		this.tributpiscred = tributpiscred;
		this.cstpiscred = cstpiscred;
		this.aliqpiscred = aliqpiscred;
		this.vlrbasepiscred = vlrbasepiscred;
		this.vlrpiscred = vlrpiscred;
		this.tributcofinscred = tributcofinscred;
		this.cstcofinscred = cstcofinscred;
		this.aliqcofinscred = aliqcofinscred;
		this.vlrbasecofinscred = vlrbasecofinscred;
		this.vlrcofinscred = vlrcofinscred;
		this.recompoeapurapc = recompoeapurapc;
		this.nromedidor = nromedidor;
		this.qtdeconsumo = qtdeconsumo;
		this.usuinclusao = usuinclusao;
		this.dtainclusao = dtainclusao;
		this.cstiss = cstiss;
		this.vlrtotfornec = vlrtotfornec;
		this.vlrservntribut = vlrservntribut;
		this.vlrcobterceiro = vlrcobterceiro;
		this.vlrdespacess = vlrdespacess;
		this.vlrbaseicmsrettransp = vlrbaseicmsrettransp;
		this.aliqicmsrettransp = aliqicmsrettransp;
		this.vlricmsrettransp = vlricmsrettransp;
		this.dtavenctoicmsrettransp = dtavenctoicmsrettransp;
		this.codrecdarfpisret = codrecdarfpisret;
		this.codrecdarfcofinsret = codrecdarfcofinsret;
		this.codrecdarfcsllret = codrecdarfcsllret;
		this.indvenctoantprazomin = indvenctoantprazomin;
		this.codsitdoc = codsitdoc;
		this.vlrbaseicmsdif = vlrbaseicmsdif;
		this.aliqicmsdif = aliqicmsdif;
		this.vlricmsdif = vlricmsdif;
		this.nroaidfiss = nroaidfiss;
		this.anoaidfiss = anoaidfiss;
		this.tiporecoliss = tiporecoliss;
		this.atividadeiss = atividadeiss;
		this.vlrbaseinsspat = vlrbaseinsspat;
		this.aliqinsspat = aliqinsspat;
		this.vlrinsspat = vlrinsspat;
		this.dtavenctoinsspat = dtavenctoinsspat;
		this.seqtransportador = seqtransportador;
		this.indutilcssemacordo = indutilcssemacordo;
		this.nroempresaacordo = nroempresaacordo;
		this.nroacordo = nroacordo;
		this.seqccacordoassoc = seqccacordoassoc;
		this.flagupdate = flagupdate;
		this.vlrpedagio = vlrpedagio;
		this.codservicodeiss = codservicodeiss;
		this.seqtpreciss = seqtpreciss;
		this.codbaselegaldms = codbaselegaldms;
		this.seqmotnaoretencaoiss = seqmotnaoretencaoiss;
		this.nroempresanatdesp = nroempresanatdesp;
		this.codrecdarfirrf = codrecdarfirrf;
		this.indnfimportada = indnfimportada;
		this.indnfimportconsistida = indnfimportconsistida;
		this.requisicoes = requisicoes;
		this.mcsseqcontrato = mcsseqcontrato;
		this.cfps = cfps;
		this.seqtribiss = seqtribiss;
		this.seqresponsavel = seqresponsavel;
		this.vlrbaseinsspesfis = vlrbaseinsspesfis;
		this.aliqinsspesfis = aliqinsspesfis;
		this.vlrinsspesfis = vlrinsspesfis;
		this.dtavenctoinsspesfis = dtavenctoinsspesfis;
		this.vlrbaseirpesfis = vlrbaseirpesfis;
		this.aliqirpesfis = aliqirpesfis;
		this.vlrirpesfis = vlrirpesfis;
		this.dtavenctoirpesfis = dtavenctoirpesfis;
		this.codrecdarfirrfpesfis = codrecdarfirrfpesfis;
		this.nfechaveacessoservico = nfechaveacessoservico;
		this.orNfitensdespesas = orNfitensdespesas;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQNOTA")
    @SequenceGenerator(name="SEQNOTA", sequenceName="S_ORNFDESPESA", allocationSize=1)
	@Column(name = "SEQNOTA", unique = true, nullable = false, precision = 15, scale = 0)
	public long getSeqnota() {
		return this.seqnota;
	}

	public void setSeqnota(long seqnota) {
		this.seqnota = seqnota;
	}

	@Column(name = "NRONOTA", nullable = false, precision = 10, scale = 0)
	public long getNronota() {
		return this.nronota;
	}

	public void setNronota(long nronota) {
		this.nronota = nronota;
	}

	@Column(name = "SERIE", length = 4)
	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Column(name = "SUBSERIE", length = 4)
	public String getSubserie() {
		return this.subserie;
	}

	public void setSubserie(String subserie) {
		this.subserie = subserie;
	}

	@Column(name = "SEQPESSOA", precision = 8, scale = 0)
	public Integer getSeqpessoa() {
		return this.seqpessoa;
	}

	public void setSeqpessoa(Integer seqpessoa) {
		this.seqpessoa = seqpessoa;
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

	@Column(name = "CGO", nullable = false, precision = 3, scale = 0)
	public short getCgo() {
		return this.cgo;
	}

	public void setCgo(short cgo) {
		this.cgo = cgo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAEMISSAO", nullable = false, length = 7)
	public Date getDtaemissao() {
		return this.dtaemissao;
	}

	public void setDtaemissao(Date dtaemissao) {
		this.dtaemissao = dtaemissao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAENTRADA", nullable = false, length = 7)
	public Date getDtaentrada() {
		return this.dtaentrada;
	}

	public void setDtaentrada(Date dtaentrada) {
		this.dtaentrada = dtaentrada;
	}

	@Column(name = "VALOR", precision = 15)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Column(name = "PRAZOPAGTO", length = 100)
	public String getPrazopagto() {
		return this.prazopagto;
	}

	public void setPrazopagto(String prazopagto) {
		this.prazopagto = prazopagto;
	}

	@Column(name = "ALIQINSS", precision = 5)
	public BigDecimal getAliqinss() {
		return this.aliqinss;
	}

	public void setAliqinss(BigDecimal aliqinss) {
		this.aliqinss = aliqinss;
	}

	@Column(name = "VLRINSS", precision = 15)
	public BigDecimal getVlrinss() {
		return this.vlrinss;
	}

	public void setVlrinss(BigDecimal vlrinss) {
		this.vlrinss = vlrinss;
	}

	@Column(name = "ALIQIR", precision = 5)
	public BigDecimal getAliqir() {
		return this.aliqir;
	}

	public void setAliqir(BigDecimal aliqir) {
		this.aliqir = aliqir;
	}

	@Column(name = "VLRIR", precision = 15)
	public BigDecimal getVlrir() {
		return this.vlrir;
	}

	public void setVlrir(BigDecimal vlrir) {
		this.vlrir = vlrir;
	}

	@Column(name = "ALIQISSST", precision = 5)
	public BigDecimal getAliqissst() {
		return this.aliqissst;
	}

	public void setAliqissst(BigDecimal aliqissst) {
		this.aliqissst = aliqissst;
	}

	@Column(name = "VLRISSST", precision = 15)
	public BigDecimal getVlrissst() {
		return this.vlrissst;
	}

	public void setVlrissst(BigDecimal vlrissst) {
		this.vlrissst = vlrissst;
	}

	@Column(name = "ALIQPIS", precision = 7, scale = 4)
	public BigDecimal getAliqpis() {
		return this.aliqpis;
	}

	public void setAliqpis(BigDecimal aliqpis) {
		this.aliqpis = aliqpis;
	}

	@Column(name = "VLRPIS", scale = 6)
	public BigDecimal getVlrpis() {
		return this.vlrpis;
	}

	public void setVlrpis(BigDecimal vlrpis) {
		this.vlrpis = vlrpis;
	}

	@Column(name = "ALIQCOFINS", precision = 7, scale = 4)
	public BigDecimal getAliqcofins() {
		return this.aliqcofins;
	}

	public void setAliqcofins(BigDecimal aliqcofins) {
		this.aliqcofins = aliqcofins;
	}

	@Column(name = "VLRCOFINS", scale = 6)
	public BigDecimal getVlrcofins() {
		return this.vlrcofins;
	}

	public void setVlrcofins(BigDecimal vlrcofins) {
		this.vlrcofins = vlrcofins;
	}

	@Column(name = "ALIQCSSLL", precision = 5)
	public BigDecimal getAliqcssll() {
		return this.aliqcssll;
	}

	public void setAliqcssll(BigDecimal aliqcssll) {
		this.aliqcssll = aliqcssll;
	}

	@Column(name = "VLRCSSLL", precision = 15)
	public BigDecimal getVlrcssll() {
		return this.vlrcssll;
	}

	public void setVlrcssll(BigDecimal vlrcssll) {
		this.vlrcssll = vlrcssll;
	}

	@Column(name = "INTEGRADOFISCI", length = 1)
	public String getIntegradofisci() {
		return this.integradofisci;
	}

	public void setIntegradofisci(String integradofisci) {
		this.integradofisci = integradofisci;
	}

	@Column(name = "INTEGRADOCAPITIS", length = 1)
	public String getIntegradocapitis() {
		return this.integradocapitis;
	}

	public void setIntegradocapitis(String integradocapitis) {
		this.integradocapitis = integradocapitis;
	}

	@Column(name = "IDENTFISCI", precision = 15, scale = 0)
	public Long getIdentfisci() {
		return this.identfisci;
	}

	public void setIdentfisci(Long identfisci) {
		this.identfisci = identfisci;
	}

	@Column(name = "AUTORIZADO", length = 1)
	public String getAutorizado() {
		return this.autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
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

	@Column(name = "NROAP", precision = 8, scale = 0)
	public Integer getNroap() {
		return this.nroap;
	}

	public void setNroap(Integer nroap) {
		this.nroap = nroap;
	}

	@Column(name = "OBSERVACAO", length = 250)
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "VLRDESCONTOS", precision = 15)
	public BigDecimal getVlrdescontos() {
		return this.vlrdescontos;
	}

	public void setVlrdescontos(BigDecimal vlrdescontos) {
		this.vlrdescontos = vlrdescontos;
	}

	@Column(name = "VLRACRESCIMOS", precision = 15)
	public BigDecimal getVlracrescimos() {
		return this.vlracrescimos;
	}

	public void setVlracrescimos(BigDecimal vlracrescimos) {
		this.vlracrescimos = vlracrescimos;
	}

	@Column(name = "IMPORTADO", length = 1)
	public String getImportado() {
		return this.importado;
	}

	public void setImportado(String importado) {
		this.importado = importado;
	}

	@Column(name = "VERSAOPESSOA", precision = 2, scale = 0)
	public Byte getVersaopessoa() {
		return this.versaopessoa;
	}

	public void setVersaopessoa(Byte versaopessoa) {
		this.versaopessoa = versaopessoa;
	}

	@Column(name = "CODMODELO", length = 2)
	public String getCodmodelo() {
		return this.codmodelo;
	}

	public void setCodmodelo(String codmodelo) {
		this.codmodelo = codmodelo;
	}

	@Column(name = "CODHISTORICO", precision = 4, scale = 0)
	public Short getCodhistorico() {
		return this.codhistorico;
	}

	public void setCodhistorico(Short codhistorico) {
		this.codhistorico = codhistorico;
	}

	@Column(name = "CFOP", precision = 5, scale = 0)
	public Integer getCfop() {
		return this.cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	@Column(name = "TIPOTRIBUTACAO", length = 1)
	public String getTipotributacao() {
		return this.tipotributacao;
	}

	public void setTipotributacao(String tipotributacao) {
		this.tipotributacao = tipotributacao;
	}

	@Column(name = "ALIQICMS", precision = 5)
	public BigDecimal getAliqicms() {
		return this.aliqicms;
	}

	public void setAliqicms(BigDecimal aliqicms) {
		this.aliqicms = aliqicms;
	}

	@Column(name = "ALIQISS", precision = 5)
	public BigDecimal getAliqiss() {
		return this.aliqiss;
	}

	public void setAliqiss(BigDecimal aliqiss) {
		this.aliqiss = aliqiss;
	}

	@Column(name = "ALIQSESTSENAT", precision = 5)
	public BigDecimal getAliqsestsenat() {
		return this.aliqsestsenat;
	}

	public void setAliqsestsenat(BigDecimal aliqsestsenat) {
		this.aliqsestsenat = aliqsestsenat;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOINSS", length = 7)
	public Date getDtavenctoinss() {
		return this.dtavenctoinss;
	}

	public void setDtavenctoinss(Date dtavenctoinss) {
		this.dtavenctoinss = dtavenctoinss;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOIR", length = 7)
	public Date getDtavenctoir() {
		return this.dtavenctoir;
	}

	public void setDtavenctoir(Date dtavenctoir) {
		this.dtavenctoir = dtavenctoir;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOPIS", length = 7)
	public Date getDtavenctopis() {
		return this.dtavenctopis;
	}

	public void setDtavenctopis(Date dtavenctopis) {
		this.dtavenctopis = dtavenctopis;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOICMS", length = 7)
	public Date getDtavenctoicms() {
		return this.dtavenctoicms;
	}

	public void setDtavenctoicms(Date dtavenctoicms) {
		this.dtavenctoicms = dtavenctoicms;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOISSST", length = 7)
	public Date getDtavenctoissst() {
		return this.dtavenctoissst;
	}

	public void setDtavenctoissst(Date dtavenctoissst) {
		this.dtavenctoissst = dtavenctoissst;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOCOFINS", length = 7)
	public Date getDtavenctocofins() {
		return this.dtavenctocofins;
	}

	public void setDtavenctocofins(Date dtavenctocofins) {
		this.dtavenctocofins = dtavenctocofins;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOCSSLL", length = 7)
	public Date getDtavenctocssll() {
		return this.dtavenctocssll;
	}

	public void setDtavenctocssll(Date dtavenctocssll) {
		this.dtavenctocssll = dtavenctocssll;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOISS", length = 7)
	public Date getDtavenctoiss() {
		return this.dtavenctoiss;
	}

	public void setDtavenctoiss(Date dtavenctoiss) {
		this.dtavenctoiss = dtavenctoiss;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOSESTSENAT", length = 7)
	public Date getDtavenctosestsenat() {
		return this.dtavenctosestsenat;
	}

	public void setDtavenctosestsenat(Date dtavenctosestsenat) {
		this.dtavenctosestsenat = dtavenctosestsenat;
	}

	@Column(name = "VLRLIQNOTA", precision = 15)
	public BigDecimal getVlrliqnota() {
		return this.vlrliqnota;
	}

	public void setVlrliqnota(BigDecimal vlrliqnota) {
		this.vlrliqnota = vlrliqnota;
	}

	@Column(name = "VLRSESTSENAT", precision = 15)
	public BigDecimal getVlrsestsenat() {
		return this.vlrsestsenat;
	}

	public void setVlrsestsenat(BigDecimal vlrsestsenat) {
		this.vlrsestsenat = vlrsestsenat;
	}

	@Column(name = "VLRISS", precision = 15)
	public BigDecimal getVlriss() {
		return this.vlriss;
	}

	public void setVlriss(BigDecimal vlriss) {
		this.vlriss = vlriss;
	}

	@Column(name = "VLRISENTO", precision = 15)
	public BigDecimal getVlrisento() {
		return this.vlrisento;
	}

	public void setVlrisento(BigDecimal vlrisento) {
		this.vlrisento = vlrisento;
	}

	@Column(name = "VLROUTRAS", precision = 15)
	public BigDecimal getVlroutras() {
		return this.vlroutras;
	}

	public void setVlroutras(BigDecimal vlroutras) {
		this.vlroutras = vlroutras;
	}

	@Column(name = "VLRICMS", precision = 15)
	public BigDecimal getVlricms() {
		return this.vlricms;
	}

	public void setVlricms(BigDecimal vlricms) {
		this.vlricms = vlricms;
	}

	@Column(name = "VLRBASEPIS", precision = 15)
	public BigDecimal getVlrbasepis() {
		return this.vlrbasepis;
	}

	public void setVlrbasepis(BigDecimal vlrbasepis) {
		this.vlrbasepis = vlrbasepis;
	}

	@Column(name = "VLRBASECOFINS", precision = 15)
	public BigDecimal getVlrbasecofins() {
		return this.vlrbasecofins;
	}

	public void setVlrbasecofins(BigDecimal vlrbasecofins) {
		this.vlrbasecofins = vlrbasecofins;
	}

	@Column(name = "VLRBASEISS", precision = 15)
	public BigDecimal getVlrbaseiss() {
		return this.vlrbaseiss;
	}

	public void setVlrbaseiss(BigDecimal vlrbaseiss) {
		this.vlrbaseiss = vlrbaseiss;
	}

	@Column(name = "VLRBASEICMS", precision = 15)
	public BigDecimal getVlrbaseicms() {
		return this.vlrbaseicms;
	}

	public void setVlrbaseicms(BigDecimal vlrbaseicms) {
		this.vlrbaseicms = vlrbaseicms;
	}

	@Column(name = "VLRBASEINSS", precision = 15)
	public BigDecimal getVlrbaseinss() {
		return this.vlrbaseinss;
	}

	public void setVlrbaseinss(BigDecimal vlrbaseinss) {
		this.vlrbaseinss = vlrbaseinss;
	}

	@Column(name = "VLRBASEIR", precision = 15)
	public BigDecimal getVlrbaseir() {
		return this.vlrbaseir;
	}

	public void setVlrbaseir(BigDecimal vlrbaseir) {
		this.vlrbaseir = vlrbaseir;
	}

	@Column(name = "VLRBASEISSST", precision = 15)
	public BigDecimal getVlrbaseissst() {
		return this.vlrbaseissst;
	}

	public void setVlrbaseissst(BigDecimal vlrbaseissst) {
		this.vlrbaseissst = vlrbaseissst;
	}

	@Column(name = "VLRBASECSSLL", precision = 15)
	public BigDecimal getVlrbasecssll() {
		return this.vlrbasecssll;
	}

	public void setVlrbasecssll(BigDecimal vlrbasecssll) {
		this.vlrbasecssll = vlrbasecssll;
	}

	@Column(name = "VLRBASESESTSENAT", precision = 15)
	public BigDecimal getVlrbasesestsenat() {
		return this.vlrbasesestsenat;
	}

	public void setVlrbasesestsenat(BigDecimal vlrbasesestsenat) {
		this.vlrbasesestsenat = vlrbasesestsenat;
	}

	@Column(name = "TRIBUTICMS", length = 1)
	public String getTributicms() {
		return this.tributicms;
	}

	public void setTributicms(String tributicms) {
		this.tributicms = tributicms;
	}

	@Column(name = "TRIBUTPIS", length = 1)
	public String getTributpis() {
		return this.tributpis;
	}

	public void setTributpis(String tributpis) {
		this.tributpis = tributpis;
	}

	@Column(name = "TRIBUTCOFINS", length = 1)
	public String getTributcofins() {
		return this.tributcofins;
	}

	public void setTributcofins(String tributcofins) {
		this.tributcofins = tributcofins;
	}

	@Column(name = "SITUACAO", length = 1)
	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Column(name = "NROPROCESSO", precision = 15, scale = 0)
	public Long getNroprocesso() {
		return this.nroprocesso;
	}

	public void setNroprocesso(Long nroprocesso) {
		this.nroprocesso = nroprocesso;
	}

	@Column(name = "ESPECIENF", length = 4)
	public String getEspecienf() {
		return this.especienf;
	}

	public void setEspecienf(String especienf) {
		this.especienf = especienf;
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

	@Column(name = "VLROUTRASOPDESC", precision = 15)
	public BigDecimal getVlroutrasopdesc() {
		return this.vlroutrasopdesc;
	}

	public void setVlroutrasopdesc(BigDecimal vlroutrasopdesc) {
		this.vlroutrasopdesc = vlroutrasopdesc;
	}

	@Column(name = "EXIGEITENSNOTA", length = 1)
	public String getExigeitensnota() {
		return this.exigeitensnota;
	}

	public void setExigeitensnota(String exigeitensnota) {
		this.exigeitensnota = exigeitensnota;
	}

	@Column(name = "VLRFINANCEIRO", nullable = false, precision = 15)
	public BigDecimal getVlrfinanceiro() {
		return this.vlrfinanceiro;
	}

	public void setVlrfinanceiro(BigDecimal vlrfinanceiro) {
		this.vlrfinanceiro = vlrfinanceiro;
	}

	@Column(name = "TIPOTRIBUTACAOIPI", length = 1)
	public String getTipotributacaoipi() {
		return this.tipotributacaoipi;
	}

	public void setTipotributacaoipi(String tipotributacaoipi) {
		this.tipotributacaoipi = tipotributacaoipi;
	}

	@Column(name = "GERATITIPI", length = 1)
	public String getGeratitipi() {
		return this.geratitipi;
	}

	public void setGeratitipi(String geratitipi) {
		this.geratitipi = geratitipi;
	}

	@Column(name = "ALIQIPI", precision = 5)
	public BigDecimal getAliqipi() {
		return this.aliqipi;
	}

	public void setAliqipi(BigDecimal aliqipi) {
		this.aliqipi = aliqipi;
	}

	@Column(name = "VLRBASEIPI", precision = 15)
	public BigDecimal getVlrbaseipi() {
		return this.vlrbaseipi;
	}

	public void setVlrbaseipi(BigDecimal vlrbaseipi) {
		this.vlrbaseipi = vlrbaseipi;
	}

	@Column(name = "VLRIPI", precision = 15)
	public BigDecimal getVlripi() {
		return this.vlripi;
	}

	public void setVlripi(BigDecimal vlripi) {
		this.vlripi = vlripi;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOIPI", length = 7)
	public Date getDtavenctoipi() {
		return this.dtavenctoipi;
	}

	public void setDtavenctoipi(Date dtavenctoipi) {
		this.dtavenctoipi = dtavenctoipi;
	}

	@Column(name = "VLROUTROSIPI", precision = 15)
	public BigDecimal getVlroutrosipi() {
		return this.vlroutrosipi;
	}

	public void setVlroutrosipi(BigDecimal vlroutrosipi) {
		this.vlroutrosipi = vlroutrosipi;
	}

	@Column(name = "VLRISENTOIPI", precision = 15)
	public BigDecimal getVlrisentoipi() {
		return this.vlrisentoipi;
	}

	public void setVlrisentoipi(BigDecimal vlrisentoipi) {
		this.vlrisentoipi = vlrisentoipi;
	}

	@Column(name = "INTEGRADOAPURACAO", length = 1)
	public String getIntegradoapuracao() {
		return this.integradoapuracao;
	}

	public void setIntegradoapuracao(String integradoapuracao) {
		this.integradoapuracao = integradoapuracao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTARETENCAOISS", length = 7)
	public Date getDtaretencaoiss() {
		return this.dtaretencaoiss;
	}

	public void setDtaretencaoiss(Date dtaretencaoiss) {
		this.dtaretencaoiss = dtaretencaoiss;
	}

	@Column(name = "SEQDOCISS", precision = 22, scale = 0)
	public BigDecimal getSeqdociss() {
		return this.seqdociss;
	}

	public void setSeqdociss(BigDecimal seqdociss) {
		this.seqdociss = seqdociss;
	}

	@Column(name = "SEQOPERACAOISS", precision = 22, scale = 0)
	public BigDecimal getSeqoperacaoiss() {
		return this.seqoperacaoiss;
	}

	public void setSeqoperacaoiss(BigDecimal seqoperacaoiss) {
		this.seqoperacaoiss = seqoperacaoiss;
	}

	@Column(name = "VLRDEDUCAOISS_ITEM", precision = 13)
	public BigDecimal getVlrdeducaoissItem() {
		return this.vlrdeducaoissItem;
	}

	public void setVlrdeducaoissItem(BigDecimal vlrdeducaoissItem) {
		this.vlrdeducaoissItem = vlrdeducaoissItem;
	}

	@Column(name = "NFE", length = 1)
	public String getNfe() {
		return this.nfe;
	}

	public void setNfe(String nfe) {
		this.nfe = nfe;
	}

	@Column(name = "SEQCLASSECONSUMO", precision = 3, scale = 0)
	public Short getSeqclasseconsumo() {
		return this.seqclasseconsumo;
	}

	public void setSeqclasseconsumo(Short seqclasseconsumo) {
		this.seqclasseconsumo = seqclasseconsumo;
	}

	@Column(name = "TIPOLIGACAO", precision = 3, scale = 0)
	public Short getTipoligacao() {
		return this.tipoligacao;
	}

	public void setTipoligacao(Short tipoligacao) {
		this.tipoligacao = tipoligacao;
	}

	@Column(name = "GRUPOTENSAO", precision = 3, scale = 0)
	public Short getGrupotensao() {
		return this.grupotensao;
	}

	public void setGrupotensao(Short grupotensao) {
		this.grupotensao = grupotensao;
	}

	@Column(name = "TIPOASSINANTE", precision = 3, scale = 0)
	public Short getTipoassinante() {
		return this.tipoassinante;
	}

	public void setTipoassinante(Short tipoassinante) {
		this.tipoassinante = tipoassinante;
	}

	@Column(name = "RETENCAOPISNFDESP", length = 1)
	public String getRetencaopisnfdesp() {
		return this.retencaopisnfdesp;
	}

	public void setRetencaopisnfdesp(String retencaopisnfdesp) {
		this.retencaopisnfdesp = retencaopisnfdesp;
	}

	@Column(name = "RETENCAOCOFINSNFDESP", length = 1)
	public String getRetencaocofinsnfdesp() {
		return this.retencaocofinsnfdesp;
	}

	public void setRetencaocofinsnfdesp(String retencaocofinsnfdesp) {
		this.retencaocofinsnfdesp = retencaocofinsnfdesp;
	}

	@Column(name = "SITUACAONFPIS", length = 2)
	public String getSituacaonfpis() {
		return this.situacaonfpis;
	}

	public void setSituacaonfpis(String situacaonfpis) {
		this.situacaonfpis = situacaonfpis;
	}

	@Column(name = "SITUACAONFCOFINS", length = 2)
	public String getSituacaonfcofins() {
		return this.situacaonfcofins;
	}

	public void setSituacaonfcofins(String situacaonfcofins) {
		this.situacaonfcofins = situacaonfcofins;
	}

	@Column(name = "LINKERP", precision = 15, scale = 0)
	public Long getLinkerp() {
		return this.linkerp;
	}

	public void setLinkerp(Long linkerp) {
		this.linkerp = linkerp;
	}

	@Column(name = "NFECHAVEACESSO", length = 44)
	public String getNfechaveacesso() {
		return this.nfechaveacesso;
	}

	public void setNfechaveacesso(String nfechaveacesso) {
		this.nfechaveacesso = nfechaveacesso;
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

	@Column(name = "TIPOPGTO", length = 1)
	public String getTipopgto() {
		return this.tipopgto;
	}

	public void setTipopgto(String tipopgto) {
		this.tipopgto = tipopgto;
	}

	@Column(name = "SEQREQUISICAO", precision = 15, scale = 0)
	public Long getSeqrequisicao() {
		return this.seqrequisicao;
	}

	public void setSeqrequisicao(Long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}

	@Column(name = "TRIBUTPISCRED", length = 1)
	public String getTributpiscred() {
		return this.tributpiscred;
	}

	public void setTributpiscred(String tributpiscred) {
		this.tributpiscred = tributpiscred;
	}

	@Column(name = "CSTPISCRED", length = 2)
	public String getCstpiscred() {
		return this.cstpiscred;
	}

	public void setCstpiscred(String cstpiscred) {
		this.cstpiscred = cstpiscred;
	}

	@Column(name = "ALIQPISCRED", precision = 7, scale = 4)
	public BigDecimal getAliqpiscred() {
		return this.aliqpiscred;
	}

	public void setAliqpiscred(BigDecimal aliqpiscred) {
		this.aliqpiscred = aliqpiscred;
	}

	@Column(name = "VLRBASEPISCRED", precision = 13)
	public BigDecimal getVlrbasepiscred() {
		return this.vlrbasepiscred;
	}

	public void setVlrbasepiscred(BigDecimal vlrbasepiscred) {
		this.vlrbasepiscred = vlrbasepiscred;
	}

	@Column(name = "VLRPISCRED", scale = 6)
	public BigDecimal getVlrpiscred() {
		return this.vlrpiscred;
	}

	public void setVlrpiscred(BigDecimal vlrpiscred) {
		this.vlrpiscred = vlrpiscred;
	}

	@Column(name = "TRIBUTCOFINSCRED", length = 1)
	public String getTributcofinscred() {
		return this.tributcofinscred;
	}

	public void setTributcofinscred(String tributcofinscred) {
		this.tributcofinscred = tributcofinscred;
	}

	@Column(name = "CSTCOFINSCRED", length = 2)
	public String getCstcofinscred() {
		return this.cstcofinscred;
	}

	public void setCstcofinscred(String cstcofinscred) {
		this.cstcofinscred = cstcofinscred;
	}

	@Column(name = "ALIQCOFINSCRED", precision = 7, scale = 4)
	public BigDecimal getAliqcofinscred() {
		return this.aliqcofinscred;
	}

	public void setAliqcofinscred(BigDecimal aliqcofinscred) {
		this.aliqcofinscred = aliqcofinscred;
	}

	@Column(name = "VLRBASECOFINSCRED", precision = 13)
	public BigDecimal getVlrbasecofinscred() {
		return this.vlrbasecofinscred;
	}

	public void setVlrbasecofinscred(BigDecimal vlrbasecofinscred) {
		this.vlrbasecofinscred = vlrbasecofinscred;
	}

	@Column(name = "VLRCOFINSCRED", scale = 6)
	public BigDecimal getVlrcofinscred() {
		return this.vlrcofinscred;
	}

	public void setVlrcofinscred(BigDecimal vlrcofinscred) {
		this.vlrcofinscred = vlrcofinscred;
	}

	@Column(name = "RECOMPOEAPURAPC", length = 1)
	public String getRecompoeapurapc() {
		return this.recompoeapurapc;
	}

	public void setRecompoeapurapc(String recompoeapurapc) {
		this.recompoeapurapc = recompoeapurapc;
	}

	@Column(name = "NROMEDIDOR", precision = 8, scale = 0)
	public Integer getNromedidor() {
		return this.nromedidor;
	}

	public void setNromedidor(Integer nromedidor) {
		this.nromedidor = nromedidor;
	}

	@Column(name = "QTDECONSUMO", precision = 14, scale = 0)
	public Long getQtdeconsumo() {
		return this.qtdeconsumo;
	}

	public void setQtdeconsumo(Long qtdeconsumo) {
		this.qtdeconsumo = qtdeconsumo;
	}

	@Column(name = "USUINCLUSAO", length = 12)
	public String getUsuinclusao() {
		return this.usuinclusao;
	}

	public void setUsuinclusao(String usuinclusao) {
		this.usuinclusao = usuinclusao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAINCLUSAO", length = 7)
	public Date getDtainclusao() {
		return this.dtainclusao;
	}

	public void setDtainclusao(Date dtainclusao) {
		this.dtainclusao = dtainclusao;
	}

	@Column(name = "CSTISS", precision = 2, scale = 0)
	public Byte getCstiss() {
		return this.cstiss;
	}

	public void setCstiss(Byte cstiss) {
		this.cstiss = cstiss;
	}

	@Column(name = "VLRTOTFORNEC", precision = 15)
	public BigDecimal getVlrtotfornec() {
		return this.vlrtotfornec;
	}

	public void setVlrtotfornec(BigDecimal vlrtotfornec) {
		this.vlrtotfornec = vlrtotfornec;
	}

	@Column(name = "VLRSERVNTRIBUT", precision = 15)
	public BigDecimal getVlrservntribut() {
		return this.vlrservntribut;
	}

	public void setVlrservntribut(BigDecimal vlrservntribut) {
		this.vlrservntribut = vlrservntribut;
	}

	@Column(name = "VLRCOBTERCEIRO", precision = 15)
	public BigDecimal getVlrcobterceiro() {
		return this.vlrcobterceiro;
	}

	public void setVlrcobterceiro(BigDecimal vlrcobterceiro) {
		this.vlrcobterceiro = vlrcobterceiro;
	}

	@Column(name = "VLRDESPACESS", precision = 15)
	public BigDecimal getVlrdespacess() {
		return this.vlrdespacess;
	}

	public void setVlrdespacess(BigDecimal vlrdespacess) {
		this.vlrdespacess = vlrdespacess;
	}

	@Column(name = "VLRBASEICMSRETTRANSP", precision = 15)
	public BigDecimal getVlrbaseicmsrettransp() {
		return this.vlrbaseicmsrettransp;
	}

	public void setVlrbaseicmsrettransp(BigDecimal vlrbaseicmsrettransp) {
		this.vlrbaseicmsrettransp = vlrbaseicmsrettransp;
	}

	@Column(name = "ALIQICMSRETTRANSP", precision = 5)
	public BigDecimal getAliqicmsrettransp() {
		return this.aliqicmsrettransp;
	}

	public void setAliqicmsrettransp(BigDecimal aliqicmsrettransp) {
		this.aliqicmsrettransp = aliqicmsrettransp;
	}

	@Column(name = "VLRICMSRETTRANSP", precision = 15)
	public BigDecimal getVlricmsrettransp() {
		return this.vlricmsrettransp;
	}

	public void setVlricmsrettransp(BigDecimal vlricmsrettransp) {
		this.vlricmsrettransp = vlricmsrettransp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOICMSRETTRANSP", length = 7)
	public Date getDtavenctoicmsrettransp() {
		return this.dtavenctoicmsrettransp;
	}

	public void setDtavenctoicmsrettransp(Date dtavenctoicmsrettransp) {
		this.dtavenctoicmsrettransp = dtavenctoicmsrettransp;
	}

	@Column(name = "CODRECDARFPISRET", precision = 10, scale = 0)
	public Long getCodrecdarfpisret() {
		return this.codrecdarfpisret;
	}

	public void setCodrecdarfpisret(Long codrecdarfpisret) {
		this.codrecdarfpisret = codrecdarfpisret;
	}

	@Column(name = "CODRECDARFCOFINSRET", precision = 10, scale = 0)
	public Long getCodrecdarfcofinsret() {
		return this.codrecdarfcofinsret;
	}

	public void setCodrecdarfcofinsret(Long codrecdarfcofinsret) {
		this.codrecdarfcofinsret = codrecdarfcofinsret;
	}

	@Column(name = "CODRECDARFCSLLRET", precision = 10, scale = 0)
	public Long getCodrecdarfcsllret() {
		return this.codrecdarfcsllret;
	}

	public void setCodrecdarfcsllret(Long codrecdarfcsllret) {
		this.codrecdarfcsllret = codrecdarfcsllret;
	}

	@Column(name = "INDVENCTOANTPRAZOMIN", length = 1)
	public String getIndvenctoantprazomin() {
		return this.indvenctoantprazomin;
	}

	public void setIndvenctoantprazomin(String indvenctoantprazomin) {
		this.indvenctoantprazomin = indvenctoantprazomin;
	}

	@Column(name = "CODSITDOC", precision = 2, scale = 0)
	public Byte getCodsitdoc() {
		return this.codsitdoc;
	}

	public void setCodsitdoc(Byte codsitdoc) {
		this.codsitdoc = codsitdoc;
	}

	@Column(name = "VLRBASEICMSDIF", precision = 15)
	public BigDecimal getVlrbaseicmsdif() {
		return this.vlrbaseicmsdif;
	}

	public void setVlrbaseicmsdif(BigDecimal vlrbaseicmsdif) {
		this.vlrbaseicmsdif = vlrbaseicmsdif;
	}

	@Column(name = "ALIQICMSDIF", precision = 5)
	public BigDecimal getAliqicmsdif() {
		return this.aliqicmsdif;
	}

	public void setAliqicmsdif(BigDecimal aliqicmsdif) {
		this.aliqicmsdif = aliqicmsdif;
	}

	@Column(name = "VLRICMSDIF", precision = 15)
	public BigDecimal getVlricmsdif() {
		return this.vlricmsdif;
	}

	public void setVlricmsdif(BigDecimal vlricmsdif) {
		this.vlricmsdif = vlricmsdif;
	}

	@Column(name = "NROAIDFISS", precision = 7, scale = 0)
	public Integer getNroaidfiss() {
		return this.nroaidfiss;
	}

	public void setNroaidfiss(Integer nroaidfiss) {
		this.nroaidfiss = nroaidfiss;
	}

	@Column(name = "ANOAIDFISS", precision = 4, scale = 0)
	public Short getAnoaidfiss() {
		return this.anoaidfiss;
	}

	public void setAnoaidfiss(Short anoaidfiss) {
		this.anoaidfiss = anoaidfiss;
	}

	@Column(name = "TIPORECOLISS", precision = 2, scale = 0)
	public Byte getTiporecoliss() {
		return this.tiporecoliss;
	}

	public void setTiporecoliss(Byte tiporecoliss) {
		this.tiporecoliss = tiporecoliss;
	}

	@Column(name = "ATIVIDADEISS", precision = 2, scale = 0)
	public Byte getAtividadeiss() {
		return this.atividadeiss;
	}

	public void setAtividadeiss(Byte atividadeiss) {
		this.atividadeiss = atividadeiss;
	}

	@Column(name = "VLRBASEINSSPAT", precision = 15)
	public BigDecimal getVlrbaseinsspat() {
		return this.vlrbaseinsspat;
	}

	public void setVlrbaseinsspat(BigDecimal vlrbaseinsspat) {
		this.vlrbaseinsspat = vlrbaseinsspat;
	}

	@Column(name = "ALIQINSSPAT", precision = 5)
	public BigDecimal getAliqinsspat() {
		return this.aliqinsspat;
	}

	public void setAliqinsspat(BigDecimal aliqinsspat) {
		this.aliqinsspat = aliqinsspat;
	}

	@Column(name = "VLRINSSPAT", precision = 15)
	public BigDecimal getVlrinsspat() {
		return this.vlrinsspat;
	}

	public void setVlrinsspat(BigDecimal vlrinsspat) {
		this.vlrinsspat = vlrinsspat;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOINSSPAT", length = 7)
	public Date getDtavenctoinsspat() {
		return this.dtavenctoinsspat;
	}

	public void setDtavenctoinsspat(Date dtavenctoinsspat) {
		this.dtavenctoinsspat = dtavenctoinsspat;
	}

	@Column(name = "SEQTRANSPORTADOR", precision = 8, scale = 0)
	public Integer getSeqtransportador() {
		return this.seqtransportador;
	}

	public void setSeqtransportador(Integer seqtransportador) {
		this.seqtransportador = seqtransportador;
	}

	@Column(name = "INDUTILCSSEMACORDO", length = 1)
	public String getIndutilcssemacordo() {
		return this.indutilcssemacordo;
	}

	public void setIndutilcssemacordo(String indutilcssemacordo) {
		this.indutilcssemacordo = indutilcssemacordo;
	}

	@Column(name = "NROEMPRESAACORDO", precision = 3, scale = 0)
	public Short getNroempresaacordo() {
		return this.nroempresaacordo;
	}

	public void setNroempresaacordo(Short nroempresaacordo) {
		this.nroempresaacordo = nroempresaacordo;
	}

	@Column(name = "NROACORDO", precision = 6, scale = 0)
	public Integer getNroacordo() {
		return this.nroacordo;
	}

	public void setNroacordo(Integer nroacordo) {
		this.nroacordo = nroacordo;
	}

	@Column(name = "SEQCCACORDOASSOC", precision = 22, scale = 0)
	public BigDecimal getSeqccacordoassoc() {
		return this.seqccacordoassoc;
	}

	public void setSeqccacordoassoc(BigDecimal seqccacordoassoc) {
		this.seqccacordoassoc = seqccacordoassoc;
	}

	@Column(name = "FLAGUPDATE", length = 1)
	public String getFlagupdate() {
		return this.flagupdate;
	}

	public void setFlagupdate(String flagupdate) {
		this.flagupdate = flagupdate;
	}

	@Column(name = "VLRPEDAGIO", precision = 15)
	public BigDecimal getVlrpedagio() {
		return this.vlrpedagio;
	}

	public void setVlrpedagio(BigDecimal vlrpedagio) {
		this.vlrpedagio = vlrpedagio;
	}

	@Column(name = "CODSERVICODEISS", precision = 7, scale = 0)
	public Integer getCodservicodeiss() {
		return this.codservicodeiss;
	}

	public void setCodservicodeiss(Integer codservicodeiss) {
		this.codservicodeiss = codservicodeiss;
	}

	@Column(name = "SEQTPRECISS", precision = 5, scale = 0)
	public Integer getSeqtpreciss() {
		return this.seqtpreciss;
	}

	public void setSeqtpreciss(Integer seqtpreciss) {
		this.seqtpreciss = seqtpreciss;
	}

	@Column(name = "CODBASELEGALDMS", precision = 5, scale = 0)
	public Integer getCodbaselegaldms() {
		return this.codbaselegaldms;
	}

	public void setCodbaselegaldms(Integer codbaselegaldms) {
		this.codbaselegaldms = codbaselegaldms;
	}

	@Column(name = "SEQMOTNAORETENCAOISS", precision = 2, scale = 0)
	public Byte getSeqmotnaoretencaoiss() {
		return this.seqmotnaoretencaoiss;
	}

	public void setSeqmotnaoretencaoiss(Byte seqmotnaoretencaoiss) {
		this.seqmotnaoretencaoiss = seqmotnaoretencaoiss;
	}

	@Column(name = "NROEMPRESANATDESP", precision = 3, scale = 0)
	public Short getNroempresanatdesp() {
		return this.nroempresanatdesp;
	}

	public void setNroempresanatdesp(Short nroempresanatdesp) {
		this.nroempresanatdesp = nroempresanatdesp;
	}

	@Column(name = "CODRECDARFIRRF", precision = 4, scale = 0)
	public Short getCodrecdarfirrf() {
		return this.codrecdarfirrf;
	}

	public void setCodrecdarfirrf(Short codrecdarfirrf) {
		this.codrecdarfirrf = codrecdarfirrf;
	}

	@Column(name = "INDNFIMPORTADA", length = 1)
	public String getIndnfimportada() {
		return this.indnfimportada;
	}

	public void setIndnfimportada(String indnfimportada) {
		this.indnfimportada = indnfimportada;
	}

	@Column(name = "INDNFIMPORTCONSISTIDA", length = 1)
	public String getIndnfimportconsistida() {
		return this.indnfimportconsistida;
	}

	public void setIndnfimportconsistida(String indnfimportconsistida) {
		this.indnfimportconsistida = indnfimportconsistida;
	}

	@Column(name = "REQUISICOES", length = 256)
	public String getRequisicoes() {
		return this.requisicoes;
	}

	public void setRequisicoes(String requisicoes) {
		this.requisicoes = requisicoes;
	}

	@Column(name = "MCSSEQCONTRATO", precision = 15, scale = 0)
	public Long getMcsseqcontrato() {
		return this.mcsseqcontrato;
	}

	public void setMcsseqcontrato(Long mcsseqcontrato) {
		this.mcsseqcontrato = mcsseqcontrato;
	}

	@Column(name = "CFPS", precision = 4, scale = 0)
	public Short getCfps() {
		return this.cfps;
	}

	public void setCfps(Short cfps) {
		this.cfps = cfps;
	}

	@Column(name = "SEQTRIBISS", precision = 22, scale = 0)
	public BigDecimal getSeqtribiss() {
		return this.seqtribiss;
	}

	public void setSeqtribiss(BigDecimal seqtribiss) {
		this.seqtribiss = seqtribiss;
	}

	@Column(name = "SEQRESPONSAVEL", precision = 8, scale = 0)
	public Integer getSeqresponsavel() {
		return this.seqresponsavel;
	}

	public void setSeqresponsavel(Integer seqresponsavel) {
		this.seqresponsavel = seqresponsavel;
	}

	@Column(name = "VLRBASEINSSPESFIS", precision = 15)
	public BigDecimal getVlrbaseinsspesfis() {
		return this.vlrbaseinsspesfis;
	}

	public void setVlrbaseinsspesfis(BigDecimal vlrbaseinsspesfis) {
		this.vlrbaseinsspesfis = vlrbaseinsspesfis;
	}

	@Column(name = "ALIQINSSPESFIS", precision = 5)
	public BigDecimal getAliqinsspesfis() {
		return this.aliqinsspesfis;
	}

	public void setAliqinsspesfis(BigDecimal aliqinsspesfis) {
		this.aliqinsspesfis = aliqinsspesfis;
	}

	@Column(name = "VLRINSSPESFIS", precision = 15)
	public BigDecimal getVlrinsspesfis() {
		return this.vlrinsspesfis;
	}

	public void setVlrinsspesfis(BigDecimal vlrinsspesfis) {
		this.vlrinsspesfis = vlrinsspesfis;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOINSSPESFIS", length = 7)
	public Date getDtavenctoinsspesfis() {
		return this.dtavenctoinsspesfis;
	}

	public void setDtavenctoinsspesfis(Date dtavenctoinsspesfis) {
		this.dtavenctoinsspesfis = dtavenctoinsspesfis;
	}

	@Column(name = "VLRBASEIRPESFIS", precision = 15)
	public BigDecimal getVlrbaseirpesfis() {
		return this.vlrbaseirpesfis;
	}

	public void setVlrbaseirpesfis(BigDecimal vlrbaseirpesfis) {
		this.vlrbaseirpesfis = vlrbaseirpesfis;
	}

	@Column(name = "ALIQIRPESFIS", precision = 5)
	public BigDecimal getAliqirpesfis() {
		return this.aliqirpesfis;
	}

	public void setAliqirpesfis(BigDecimal aliqirpesfis) {
		this.aliqirpesfis = aliqirpesfis;
	}

	@Column(name = "VLRIRPESFIS", precision = 15)
	public BigDecimal getVlrirpesfis() {
		return this.vlrirpesfis;
	}

	public void setVlrirpesfis(BigDecimal vlrirpesfis) {
		this.vlrirpesfis = vlrirpesfis;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAVENCTOIRPESFIS", length = 7)
	public Date getDtavenctoirpesfis() {
		return this.dtavenctoirpesfis;
	}

	public void setDtavenctoirpesfis(Date dtavenctoirpesfis) {
		this.dtavenctoirpesfis = dtavenctoirpesfis;
	}

	@Column(name = "CODRECDARFIRRFPESFIS", precision = 4, scale = 0)
	public Short getCodrecdarfirrfpesfis() {
		return this.codrecdarfirrfpesfis;
	}

	public void setCodrecdarfirrfpesfis(Short codrecdarfirrfpesfis) {
		this.codrecdarfirrfpesfis = codrecdarfirrfpesfis;
	}

	@Column(name = "NFECHAVEACESSOSERVICO", length = 16)
	public String getNfechaveacessoservico() {
		return this.nfechaveacessoservico;
	}

	public void setNfechaveacessoservico(String nfechaveacessoservico) {
		this.nfechaveacessoservico = nfechaveacessoservico;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orNfdespesa")
	public Set<OrNfitensdespesaEntity> getOrNfitensdespesas() {
		return this.orNfitensdespesas;
	}

	public void setOrNfitensdespesas(Set<OrNfitensdespesaEntity> orNfitensdespesas) {
		this.orNfitensdespesas = orNfitensdespesas;
	}

}
