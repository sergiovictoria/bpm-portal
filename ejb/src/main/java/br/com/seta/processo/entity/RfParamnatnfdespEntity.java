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
@Table(name = "RF_PARAMNATNFDESP")
public class RfParamnatnfdespEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private RfParamnatnfdespId id;
	private short cgo;
	private String codespecie;
	private short codoperacao;
	private String tiptributacao;
	private String usualteracao;
	private Date dtaalteracao;
	private String tributicms;
	private String tributpis;
	private String tributcofins;
	private String especienf;
	private String deschistorico;
	private short cfopestado;
	private short cfopforaestado;
	private short cfopexterior;
	private String modelonf;
	private String gerlivrofiscal;
	private String gertitfinanceiro;
	private String gercontabilizacao;
	private String codproduto;
	private String codespecieir;
	private String tiporeciss;
	private String exigeitensnota;
	private String tipotributacaoipi;
	private String geratitipi;
	private String indgeradbcrpiscofins;
	private Integer codmotivopiscofins;
	private String retencaopisnfdesp;
	private String retencaocofinsnfdesp;
	private Integer codmotivopiscofinsesp;
	private String blocoefdpc;
	private Byte tabbasecalccred;
	private String indgerades;
	private String exigerequisicao;
	private String cstpis;
	private BigDecimal aliqpis;
	private Long ctacredpis;
	private String tipoentcredpis;
	private Integer codentcredpis;
	private Integer ccuscredpis;
	private Long ctadebpis;
	private String tipoentdebpis;
	private Integer codentdebpis;
	private Integer ccusdebpis;
	private String deschistpis;
	private String cstcofins;
	private Long ctacredcofins;
	private String tipoentcredcofins;
	private Integer codentcredcofins;
	private Integer ccuscredcofins;
	private BigDecimal aliqcofins;
	private Long ctadebcofins;
	private String tipoentdebcofins;
	private Integer codentdebcofins;
	private Integer ccusdebcofins;
	private String deschistcofins;
	private String status;
	private String indcredratpc;
	private Long ctacontpc;
	private Integer ccuspc;
	private Short coddespdac;
	private Long seqpadraovenctoinss;
	private Long seqpadraovenctopis;
	private Long seqpadraovenctocofins;
	private Long seqpadraovenctocssll;
	private Long seqpadraovenctoicms;
	private Long seqpadraovenctoir;
	private Long seqpadraovenctoissst;
	private Long seqpadraovenctoissqn;
	private Long seqpadraovenctosestsenat;
	private Long seqpadraovenctoipi;
	private Byte cstiss;
	private String indgeradmssc;
	private String indcalcinssauto;
	private String indcalccredpiscofauto;
	private String indcalcretpiscofauto;
	private String indcalccssllauto;
	private String indcalcicmsauto;
	private String indcalcirauto;
	private String indcalcissstauto;
	private String indcalcissqnauto;
	private String indcalcsestsenacauto;
	private String indcalcipiauto;
	private String csticms;
	private String situacaonfipi;
	private BigDecimal aliqcsll;
	private String integradomini;
	private String geraciap;
	private Long seqpadraovenctoinsspat;
	private String indcalcinsspatauto;
	private BigDecimal perdespesaprod;
	private String somapedagiobase;
	private Integer codservicodeiss;
	private Integer seqtpreciss;
	private Integer codbaselegaldms;
	private String codsitrespdes;
	private String indgeradesbh;
	private String compdomini;
	private BigDecimal bemdomini;
	private String codobservacao;
	private Short codrecdarfirrf;
	private Byte moddocservprestdesbh;
	private Short seriedocdesbh;
	private Byte subseriedocdesbh;
	private Short seqtributacaoiss;
	private Short cfps;
	private BigDecimal seqtribiss;
	private Long seqabaciwebparamnf;
	private BigDecimal aliqissqn;
	private BigDecimal aliqissst;
	private String codespecieissst;
	private String codespecieissqn;
	private String codespeciepis;
	private String codespeciecofins;
	private String codespecieicms;
	private String codespecieipi;
	private BigDecimal aliqir;
	private String codespeciecssll;
	private BigDecimal aliqsest;
	private String codespeciesest;
	private BigDecimal aliqinsspatronal;
	private String codespecieinsspatronal;
	private BigDecimal aliqinssjur;
	private String codespecieinssjur;
	private BigDecimal aliqirpesfis;
	private String codespecieirpesfis;
	private Short codrecdarfirrfpesfis;
	private Long seqpadraovenctoirpesfis;
	private String indcalcirautopesfis;
	private BigDecimal aliqinsspesfis;
	private String codespecieinsspesfis;
	private Long seqpadraovenctoinsspesfis;
	private String indcalcinssautopesfis;

	public RfParamnatnfdespEntity() {
	}

	public RfParamnatnfdespEntity(RfParamnatnfdespId id, short cgo, short codoperacao, String tiptributacao, String tributicms, String tributpis,
			String tributcofins, short cfopestado, short cfopforaestado, short cfopexterior, String modelonf, String tipotributacaoipi,
			String geratitipi, String indgerades, String status, String indcredratpc, String indcalcinssauto, String indcalccredpiscofauto,
			String indcalcretpiscofauto, String indcalccssllauto, String indcalcicmsauto, String indcalcirauto, String indcalcissstauto,
			String indcalcissqnauto, String indcalcsestsenacauto, String indcalcipiauto) {
		this.id = id;
		this.cgo = cgo;
		this.codoperacao = codoperacao;
		this.tiptributacao = tiptributacao;
		this.tributicms = tributicms;
		this.tributpis = tributpis;
		this.tributcofins = tributcofins;
		this.cfopestado = cfopestado;
		this.cfopforaestado = cfopforaestado;
		this.cfopexterior = cfopexterior;
		this.modelonf = modelonf;
		this.tipotributacaoipi = tipotributacaoipi;
		this.geratitipi = geratitipi;
		this.indgerades = indgerades;
		this.status = status;
		this.indcredratpc = indcredratpc;
		this.indcalcinssauto = indcalcinssauto;
		this.indcalccredpiscofauto = indcalccredpiscofauto;
		this.indcalcretpiscofauto = indcalcretpiscofauto;
		this.indcalccssllauto = indcalccssllauto;
		this.indcalcicmsauto = indcalcicmsauto;
		this.indcalcirauto = indcalcirauto;
		this.indcalcissstauto = indcalcissstauto;
		this.indcalcissqnauto = indcalcissqnauto;
		this.indcalcsestsenacauto = indcalcsestsenacauto;
		this.indcalcipiauto = indcalcipiauto;
	}

	public RfParamnatnfdespEntity(RfParamnatnfdespId id, short cgo, String codespecie, short codoperacao, String tiptributacao,
			String usualteracao, Date dtaalteracao, String tributicms, String tributpis, String tributcofins, String especienf,
			String deschistorico, short cfopestado, short cfopforaestado, short cfopexterior, String modelonf, String gerlivrofiscal,
			String gertitfinanceiro, String gercontabilizacao, String codproduto, String codespecieir, String tiporeciss,
			String exigeitensnota, String tipotributacaoipi, String geratitipi, String indgeradbcrpiscofins, Integer codmotivopiscofins,
			String retencaopisnfdesp, String retencaocofinsnfdesp, Integer codmotivopiscofinsesp, String blocoefdpc, Byte tabbasecalccred,
			String indgerades, String exigerequisicao, String cstpis, BigDecimal aliqpis, Long ctacredpis, String tipoentcredpis,
			Integer codentcredpis, Integer ccuscredpis, Long ctadebpis, String tipoentdebpis, Integer codentdebpis, Integer ccusdebpis,
			String deschistpis, String cstcofins, Long ctacredcofins, String tipoentcredcofins, Integer codentcredcofins,
			Integer ccuscredcofins, BigDecimal aliqcofins, Long ctadebcofins, String tipoentdebcofins, Integer codentdebcofins,
			Integer ccusdebcofins, String deschistcofins, String status, String indcredratpc, Long ctacontpc, Integer ccuspc,
			Short coddespdac, Long seqpadraovenctoinss, Long seqpadraovenctopis, Long seqpadraovenctocofins, Long seqpadraovenctocssll,
			Long seqpadraovenctoicms, Long seqpadraovenctoir, Long seqpadraovenctoissst, Long seqpadraovenctoissqn,
			Long seqpadraovenctosestsenat, Long seqpadraovenctoipi, Byte cstiss, String indgeradmssc, String indcalcinssauto,
			String indcalccredpiscofauto, String indcalcretpiscofauto, String indcalccssllauto, String indcalcicmsauto,
			String indcalcirauto, String indcalcissstauto, String indcalcissqnauto, String indcalcsestsenacauto, String indcalcipiauto,
			String csticms, String situacaonfipi, BigDecimal aliqcsll, String integradomini, String geraciap, Long seqpadraovenctoinsspat,
			String indcalcinsspatauto, BigDecimal perdespesaprod, String somapedagiobase, Integer codservicodeiss, Integer seqtpreciss,
			Integer codbaselegaldms, String codsitrespdes, String indgeradesbh, String compdomini, BigDecimal bemdomini,
			String codobservacao, Short codrecdarfirrf, Byte moddocservprestdesbh, Short seriedocdesbh, Byte subseriedocdesbh,
			Short seqtributacaoiss, Short cfps, BigDecimal seqtribiss, Long seqabaciwebparamnf, BigDecimal aliqissqn, BigDecimal aliqissst,
			String codespecieissst, String codespecieissqn, String codespeciepis, String codespeciecofins, String codespecieicms,
			String codespecieipi, BigDecimal aliqir, String codespeciecssll, BigDecimal aliqsest, String codespeciesest,
			BigDecimal aliqinsspatronal, String codespecieinsspatronal, BigDecimal aliqinssjur, String codespecieinssjur,
			BigDecimal aliqirpesfis, String codespecieirpesfis, Short codrecdarfirrfpesfis, Long seqpadraovenctoirpesfis,
			String indcalcirautopesfis, BigDecimal aliqinsspesfis, String codespecieinsspesfis, Long seqpadraovenctoinsspesfis,
			String indcalcinssautopesfis) {
		this.id = id;
		this.cgo = cgo;
		this.codespecie = codespecie;
		this.codoperacao = codoperacao;
		this.tiptributacao = tiptributacao;
		this.usualteracao = usualteracao;
		this.dtaalteracao = dtaalteracao;
		this.tributicms = tributicms;
		this.tributpis = tributpis;
		this.tributcofins = tributcofins;
		this.especienf = especienf;
		this.deschistorico = deschistorico;
		this.cfopestado = cfopestado;
		this.cfopforaestado = cfopforaestado;
		this.cfopexterior = cfopexterior;
		this.modelonf = modelonf;
		this.gerlivrofiscal = gerlivrofiscal;
		this.gertitfinanceiro = gertitfinanceiro;
		this.gercontabilizacao = gercontabilizacao;
		this.codproduto = codproduto;
		this.codespecieir = codespecieir;
		this.tiporeciss = tiporeciss;
		this.exigeitensnota = exigeitensnota;
		this.tipotributacaoipi = tipotributacaoipi;
		this.geratitipi = geratitipi;
		this.indgeradbcrpiscofins = indgeradbcrpiscofins;
		this.codmotivopiscofins = codmotivopiscofins;
		this.retencaopisnfdesp = retencaopisnfdesp;
		this.retencaocofinsnfdesp = retencaocofinsnfdesp;
		this.codmotivopiscofinsesp = codmotivopiscofinsesp;
		this.blocoefdpc = blocoefdpc;
		this.tabbasecalccred = tabbasecalccred;
		this.indgerades = indgerades;
		this.exigerequisicao = exigerequisicao;
		this.cstpis = cstpis;
		this.aliqpis = aliqpis;
		this.ctacredpis = ctacredpis;
		this.tipoentcredpis = tipoentcredpis;
		this.codentcredpis = codentcredpis;
		this.ccuscredpis = ccuscredpis;
		this.ctadebpis = ctadebpis;
		this.tipoentdebpis = tipoentdebpis;
		this.codentdebpis = codentdebpis;
		this.ccusdebpis = ccusdebpis;
		this.deschistpis = deschistpis;
		this.cstcofins = cstcofins;
		this.ctacredcofins = ctacredcofins;
		this.tipoentcredcofins = tipoentcredcofins;
		this.codentcredcofins = codentcredcofins;
		this.ccuscredcofins = ccuscredcofins;
		this.aliqcofins = aliqcofins;
		this.ctadebcofins = ctadebcofins;
		this.tipoentdebcofins = tipoentdebcofins;
		this.codentdebcofins = codentdebcofins;
		this.ccusdebcofins = ccusdebcofins;
		this.deschistcofins = deschistcofins;
		this.status = status;
		this.indcredratpc = indcredratpc;
		this.ctacontpc = ctacontpc;
		this.ccuspc = ccuspc;
		this.coddespdac = coddespdac;
		this.seqpadraovenctoinss = seqpadraovenctoinss;
		this.seqpadraovenctopis = seqpadraovenctopis;
		this.seqpadraovenctocofins = seqpadraovenctocofins;
		this.seqpadraovenctocssll = seqpadraovenctocssll;
		this.seqpadraovenctoicms = seqpadraovenctoicms;
		this.seqpadraovenctoir = seqpadraovenctoir;
		this.seqpadraovenctoissst = seqpadraovenctoissst;
		this.seqpadraovenctoissqn = seqpadraovenctoissqn;
		this.seqpadraovenctosestsenat = seqpadraovenctosestsenat;
		this.seqpadraovenctoipi = seqpadraovenctoipi;
		this.cstiss = cstiss;
		this.indgeradmssc = indgeradmssc;
		this.indcalcinssauto = indcalcinssauto;
		this.indcalccredpiscofauto = indcalccredpiscofauto;
		this.indcalcretpiscofauto = indcalcretpiscofauto;
		this.indcalccssllauto = indcalccssllauto;
		this.indcalcicmsauto = indcalcicmsauto;
		this.indcalcirauto = indcalcirauto;
		this.indcalcissstauto = indcalcissstauto;
		this.indcalcissqnauto = indcalcissqnauto;
		this.indcalcsestsenacauto = indcalcsestsenacauto;
		this.indcalcipiauto = indcalcipiauto;
		this.csticms = csticms;
		this.situacaonfipi = situacaonfipi;
		this.aliqcsll = aliqcsll;
		this.integradomini = integradomini;
		this.geraciap = geraciap;
		this.seqpadraovenctoinsspat = seqpadraovenctoinsspat;
		this.indcalcinsspatauto = indcalcinsspatauto;
		this.perdespesaprod = perdespesaprod;
		this.somapedagiobase = somapedagiobase;
		this.codservicodeiss = codservicodeiss;
		this.seqtpreciss = seqtpreciss;
		this.codbaselegaldms = codbaselegaldms;
		this.codsitrespdes = codsitrespdes;
		this.indgeradesbh = indgeradesbh;
		this.compdomini = compdomini;
		this.bemdomini = bemdomini;
		this.codobservacao = codobservacao;
		this.codrecdarfirrf = codrecdarfirrf;
		this.moddocservprestdesbh = moddocservprestdesbh;
		this.seriedocdesbh = seriedocdesbh;
		this.subseriedocdesbh = subseriedocdesbh;
		this.seqtributacaoiss = seqtributacaoiss;
		this.cfps = cfps;
		this.seqtribiss = seqtribiss;
		this.seqabaciwebparamnf = seqabaciwebparamnf;
		this.aliqissqn = aliqissqn;
		this.aliqissst = aliqissst;
		this.codespecieissst = codespecieissst;
		this.codespecieissqn = codespecieissqn;
		this.codespeciepis = codespeciepis;
		this.codespeciecofins = codespeciecofins;
		this.codespecieicms = codespecieicms;
		this.codespecieipi = codespecieipi;
		this.aliqir = aliqir;
		this.codespeciecssll = codespeciecssll;
		this.aliqsest = aliqsest;
		this.codespeciesest = codespeciesest;
		this.aliqinsspatronal = aliqinsspatronal;
		this.codespecieinsspatronal = codespecieinsspatronal;
		this.aliqinssjur = aliqinssjur;
		this.codespecieinssjur = codespecieinssjur;
		this.aliqirpesfis = aliqirpesfis;
		this.codespecieirpesfis = codespecieirpesfis;
		this.codrecdarfirrfpesfis = codrecdarfirrfpesfis;
		this.seqpadraovenctoirpesfis = seqpadraovenctoirpesfis;
		this.indcalcirautopesfis = indcalcirautopesfis;
		this.aliqinsspesfis = aliqinsspesfis;
		this.codespecieinsspesfis = codespecieinsspesfis;
		this.seqpadraovenctoinsspesfis = seqpadraovenctoinsspesfis;
		this.indcalcinssautopesfis = indcalcinssautopesfis;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codhistorico", column = @Column(name = "CODHISTORICO", nullable = false, precision = 4, scale = 0)),
			@AttributeOverride(name = "nroempresa", column = @Column(name = "NROEMPRESA", nullable = false, precision = 3, scale = 0)) })
	public RfParamnatnfdespId getId() {
		return this.id;
	}

	public void setId(RfParamnatnfdespId id) {
		this.id = id;
	}

	@Column(name = "CGO", nullable = false, precision = 4, scale = 0)
	public short getCgo() {
		return this.cgo;
	}

	public void setCgo(short cgo) {
		this.cgo = cgo;
	}

	@Column(name = "CODESPECIE", length = 6)
	public String getCodespecie() {
		return this.codespecie;
	}

	public void setCodespecie(String codespecie) {
		this.codespecie = codespecie;
	}

	@Column(name = "CODOPERACAO", nullable = false, precision = 3, scale = 0)
	public short getCodoperacao() {
		return this.codoperacao;
	}

	public void setCodoperacao(short codoperacao) {
		this.codoperacao = codoperacao;
	}

	@Column(name = "TIPTRIBUTACAO", nullable = false, length = 1)
	public String getTiptributacao() {
		return this.tiptributacao;
	}

	public void setTiptributacao(String tiptributacao) {
		this.tiptributacao = tiptributacao;
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

	@Column(name = "TRIBUTICMS", nullable = false, length = 1)
	public String getTributicms() {
		return this.tributicms;
	}

	public void setTributicms(String tributicms) {
		this.tributicms = tributicms;
	}

	@Column(name = "TRIBUTPIS", nullable = false, length = 1)
	public String getTributpis() {
		return this.tributpis;
	}

	public void setTributpis(String tributpis) {
		this.tributpis = tributpis;
	}

	@Column(name = "TRIBUTCOFINS", nullable = false, length = 1)
	public String getTributcofins() {
		return this.tributcofins;
	}

	public void setTributcofins(String tributcofins) {
		this.tributcofins = tributcofins;
	}

	@Column(name = "ESPECIENF", length = 4)
	public String getEspecienf() {
		return this.especienf;
	}

	public void setEspecienf(String especienf) {
		this.especienf = especienf;
	}

	@Column(name = "DESCHISTORICO", length = 250)
	public String getDeschistorico() {
		return this.deschistorico;
	}

	public void setDeschistorico(String deschistorico) {
		this.deschistorico = deschistorico;
	}

	@Column(name = "CFOPESTADO", nullable = false, precision = 4, scale = 0)
	public short getCfopestado() {
		return this.cfopestado;
	}

	public void setCfopestado(short cfopestado) {
		this.cfopestado = cfopestado;
	}

	@Column(name = "CFOPFORAESTADO", nullable = false, precision = 4, scale = 0)
	public short getCfopforaestado() {
		return this.cfopforaestado;
	}

	public void setCfopforaestado(short cfopforaestado) {
		this.cfopforaestado = cfopforaestado;
	}

	@Column(name = "CFOPEXTERIOR", nullable = false, precision = 4, scale = 0)
	public short getCfopexterior() {
		return this.cfopexterior;
	}

	public void setCfopexterior(short cfopexterior) {
		this.cfopexterior = cfopexterior;
	}

	@Column(name = "MODELONF", nullable = false, length = 2)
	public String getModelonf() {
		return this.modelonf;
	}

	public void setModelonf(String modelonf) {
		this.modelonf = modelonf;
	}

	@Column(name = "GERLIVROFISCAL", length = 1)
	public String getGerlivrofiscal() {
		return this.gerlivrofiscal;
	}

	public void setGerlivrofiscal(String gerlivrofiscal) {
		this.gerlivrofiscal = gerlivrofiscal;
	}

	@Column(name = "GERTITFINANCEIRO", length = 1)
	public String getGertitfinanceiro() {
		return this.gertitfinanceiro;
	}

	public void setGertitfinanceiro(String gertitfinanceiro) {
		this.gertitfinanceiro = gertitfinanceiro;
	}

	@Column(name = "GERCONTABILIZACAO", length = 1)
	public String getGercontabilizacao() {
		return this.gercontabilizacao;
	}

	public void setGercontabilizacao(String gercontabilizacao) {
		this.gercontabilizacao = gercontabilizacao;
	}

	@Column(name = "CODPRODUTO", length = 14)
	public String getCodproduto() {
		return this.codproduto;
	}

	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
	}

	@Column(name = "CODESPECIEIR", length = 6)
	public String getCodespecieir() {
		return this.codespecieir;
	}

	public void setCodespecieir(String codespecieir) {
		this.codespecieir = codespecieir;
	}

	@Column(name = "TIPORECISS", length = 1)
	public String getTiporeciss() {
		return this.tiporeciss;
	}

	public void setTiporeciss(String tiporeciss) {
		this.tiporeciss = tiporeciss;
	}

	@Column(name = "EXIGEITENSNOTA", length = 1)
	public String getExigeitensnota() {
		return this.exigeitensnota;
	}

	public void setExigeitensnota(String exigeitensnota) {
		this.exigeitensnota = exigeitensnota;
	}

	@Column(name = "TIPOTRIBUTACAOIPI", nullable = false, length = 1)
	public String getTipotributacaoipi() {
		return this.tipotributacaoipi;
	}

	public void setTipotributacaoipi(String tipotributacaoipi) {
		this.tipotributacaoipi = tipotributacaoipi;
	}

	@Column(name = "GERATITIPI", nullable = false, length = 1)
	public String getGeratitipi() {
		return this.geratitipi;
	}

	public void setGeratitipi(String geratitipi) {
		this.geratitipi = geratitipi;
	}

	@Column(name = "INDGERADBCRPISCOFINS", length = 1)
	public String getIndgeradbcrpiscofins() {
		return this.indgeradbcrpiscofins;
	}

	public void setIndgeradbcrpiscofins(String indgeradbcrpiscofins) {
		this.indgeradbcrpiscofins = indgeradbcrpiscofins;
	}

	@Column(name = "CODMOTIVOPISCOFINS", precision = 5, scale = 0)
	public Integer getCodmotivopiscofins() {
		return this.codmotivopiscofins;
	}

	public void setCodmotivopiscofins(Integer codmotivopiscofins) {
		this.codmotivopiscofins = codmotivopiscofins;
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

	@Column(name = "CODMOTIVOPISCOFINSESP", precision = 5, scale = 0)
	public Integer getCodmotivopiscofinsesp() {
		return this.codmotivopiscofinsesp;
	}

	public void setCodmotivopiscofinsesp(Integer codmotivopiscofinsesp) {
		this.codmotivopiscofinsesp = codmotivopiscofinsesp;
	}

	@Column(name = "BLOCOEFDPC", length = 1)
	public String getBlocoefdpc() {
		return this.blocoefdpc;
	}

	public void setBlocoefdpc(String blocoefdpc) {
		this.blocoefdpc = blocoefdpc;
	}

	@Column(name = "TABBASECALCCRED", precision = 2, scale = 0)
	public Byte getTabbasecalccred() {
		return this.tabbasecalccred;
	}

	public void setTabbasecalccred(Byte tabbasecalccred) {
		this.tabbasecalccred = tabbasecalccred;
	}

	@Column(name = "INDGERADES", nullable = false, length = 1)
	public String getIndgerades() {
		return this.indgerades;
	}

	public void setIndgerades(String indgerades) {
		this.indgerades = indgerades;
	}

	@Column(name = "EXIGEREQUISICAO", length = 1)
	public String getExigerequisicao() {
		return this.exigerequisicao;
	}

	public void setExigerequisicao(String exigerequisicao) {
		this.exigerequisicao = exigerequisicao;
	}

	@Column(name = "CSTPIS", length = 2)
	public String getCstpis() {
		return this.cstpis;
	}

	public void setCstpis(String cstpis) {
		this.cstpis = cstpis;
	}

	@Column(name = "ALIQPIS", precision = 7, scale = 4)
	public BigDecimal getAliqpis() {
		return this.aliqpis;
	}

	public void setAliqpis(BigDecimal aliqpis) {
		this.aliqpis = aliqpis;
	}

	@Column(name = "CTACREDPIS", precision = 15, scale = 0)
	public Long getCtacredpis() {
		return this.ctacredpis;
	}

	public void setCtacredpis(Long ctacredpis) {
		this.ctacredpis = ctacredpis;
	}

	@Column(name = "TIPOENTCREDPIS", length = 2)
	public String getTipoentcredpis() {
		return this.tipoentcredpis;
	}

	public void setTipoentcredpis(String tipoentcredpis) {
		this.tipoentcredpis = tipoentcredpis;
	}

	@Column(name = "CODENTCREDPIS", precision = 8, scale = 0)
	public Integer getCodentcredpis() {
		return this.codentcredpis;
	}

	public void setCodentcredpis(Integer codentcredpis) {
		this.codentcredpis = codentcredpis;
	}

	@Column(name = "CCUSCREDPIS", precision = 6, scale = 0)
	public Integer getCcuscredpis() {
		return this.ccuscredpis;
	}

	public void setCcuscredpis(Integer ccuscredpis) {
		this.ccuscredpis = ccuscredpis;
	}

	@Column(name = "CTADEBPIS", precision = 15, scale = 0)
	public Long getCtadebpis() {
		return this.ctadebpis;
	}

	public void setCtadebpis(Long ctadebpis) {
		this.ctadebpis = ctadebpis;
	}

	@Column(name = "TIPOENTDEBPIS", length = 2)
	public String getTipoentdebpis() {
		return this.tipoentdebpis;
	}

	public void setTipoentdebpis(String tipoentdebpis) {
		this.tipoentdebpis = tipoentdebpis;
	}

	@Column(name = "CODENTDEBPIS", precision = 8, scale = 0)
	public Integer getCodentdebpis() {
		return this.codentdebpis;
	}

	public void setCodentdebpis(Integer codentdebpis) {
		this.codentdebpis = codentdebpis;
	}

	@Column(name = "CCUSDEBPIS", precision = 6, scale = 0)
	public Integer getCcusdebpis() {
		return this.ccusdebpis;
	}

	public void setCcusdebpis(Integer ccusdebpis) {
		this.ccusdebpis = ccusdebpis;
	}

	@Column(name = "DESCHISTPIS", length = 250)
	public String getDeschistpis() {
		return this.deschistpis;
	}

	public void setDeschistpis(String deschistpis) {
		this.deschistpis = deschistpis;
	}

	@Column(name = "CSTCOFINS", length = 2)
	public String getCstcofins() {
		return this.cstcofins;
	}

	public void setCstcofins(String cstcofins) {
		this.cstcofins = cstcofins;
	}

	@Column(name = "CTACREDCOFINS", precision = 15, scale = 0)
	public Long getCtacredcofins() {
		return this.ctacredcofins;
	}

	public void setCtacredcofins(Long ctacredcofins) {
		this.ctacredcofins = ctacredcofins;
	}

	@Column(name = "TIPOENTCREDCOFINS", length = 2)
	public String getTipoentcredcofins() {
		return this.tipoentcredcofins;
	}

	public void setTipoentcredcofins(String tipoentcredcofins) {
		this.tipoentcredcofins = tipoentcredcofins;
	}

	@Column(name = "CODENTCREDCOFINS", precision = 8, scale = 0)
	public Integer getCodentcredcofins() {
		return this.codentcredcofins;
	}

	public void setCodentcredcofins(Integer codentcredcofins) {
		this.codentcredcofins = codentcredcofins;
	}

	@Column(name = "CCUSCREDCOFINS", precision = 6, scale = 0)
	public Integer getCcuscredcofins() {
		return this.ccuscredcofins;
	}

	public void setCcuscredcofins(Integer ccuscredcofins) {
		this.ccuscredcofins = ccuscredcofins;
	}

	@Column(name = "ALIQCOFINS", precision = 7, scale = 4)
	public BigDecimal getAliqcofins() {
		return this.aliqcofins;
	}

	public void setAliqcofins(BigDecimal aliqcofins) {
		this.aliqcofins = aliqcofins;
	}

	@Column(name = "CTADEBCOFINS", precision = 15, scale = 0)
	public Long getCtadebcofins() {
		return this.ctadebcofins;
	}

	public void setCtadebcofins(Long ctadebcofins) {
		this.ctadebcofins = ctadebcofins;
	}

	@Column(name = "TIPOENTDEBCOFINS", length = 2)
	public String getTipoentdebcofins() {
		return this.tipoentdebcofins;
	}

	public void setTipoentdebcofins(String tipoentdebcofins) {
		this.tipoentdebcofins = tipoentdebcofins;
	}

	@Column(name = "CODENTDEBCOFINS", precision = 8, scale = 0)
	public Integer getCodentdebcofins() {
		return this.codentdebcofins;
	}

	public void setCodentdebcofins(Integer codentdebcofins) {
		this.codentdebcofins = codentdebcofins;
	}

	@Column(name = "CCUSDEBCOFINS", precision = 6, scale = 0)
	public Integer getCcusdebcofins() {
		return this.ccusdebcofins;
	}

	public void setCcusdebcofins(Integer ccusdebcofins) {
		this.ccusdebcofins = ccusdebcofins;
	}

	@Column(name = "DESCHISTCOFINS", length = 250)
	public String getDeschistcofins() {
		return this.deschistcofins;
	}

	public void setDeschistcofins(String deschistcofins) {
		this.deschistcofins = deschistcofins;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "INDCREDRATPC", nullable = false, length = 1)
	public String getIndcredratpc() {
		return this.indcredratpc;
	}

	public void setIndcredratpc(String indcredratpc) {
		this.indcredratpc = indcredratpc;
	}

	@Column(name = "CTACONTPC", precision = 15, scale = 0)
	public Long getCtacontpc() {
		return this.ctacontpc;
	}

	public void setCtacontpc(Long ctacontpc) {
		this.ctacontpc = ctacontpc;
	}

	@Column(name = "CCUSPC", precision = 6, scale = 0)
	public Integer getCcuspc() {
		return this.ccuspc;
	}

	public void setCcuspc(Integer ccuspc) {
		this.ccuspc = ccuspc;
	}

	@Column(name = "CODDESPDAC", precision = 3, scale = 0)
	public Short getCoddespdac() {
		return this.coddespdac;
	}

	public void setCoddespdac(Short coddespdac) {
		this.coddespdac = coddespdac;
	}

	@Column(name = "SEQPADRAOVENCTOINSS", precision = 15, scale = 0)
	public Long getSeqpadraovenctoinss() {
		return this.seqpadraovenctoinss;
	}

	public void setSeqpadraovenctoinss(Long seqpadraovenctoinss) {
		this.seqpadraovenctoinss = seqpadraovenctoinss;
	}

	@Column(name = "SEQPADRAOVENCTOPIS", precision = 15, scale = 0)
	public Long getSeqpadraovenctopis() {
		return this.seqpadraovenctopis;
	}

	public void setSeqpadraovenctopis(Long seqpadraovenctopis) {
		this.seqpadraovenctopis = seqpadraovenctopis;
	}

	@Column(name = "SEQPADRAOVENCTOCOFINS", precision = 15, scale = 0)
	public Long getSeqpadraovenctocofins() {
		return this.seqpadraovenctocofins;
	}

	public void setSeqpadraovenctocofins(Long seqpadraovenctocofins) {
		this.seqpadraovenctocofins = seqpadraovenctocofins;
	}

	@Column(name = "SEQPADRAOVENCTOCSSLL", precision = 15, scale = 0)
	public Long getSeqpadraovenctocssll() {
		return this.seqpadraovenctocssll;
	}

	public void setSeqpadraovenctocssll(Long seqpadraovenctocssll) {
		this.seqpadraovenctocssll = seqpadraovenctocssll;
	}

	@Column(name = "SEQPADRAOVENCTOICMS", precision = 15, scale = 0)
	public Long getSeqpadraovenctoicms() {
		return this.seqpadraovenctoicms;
	}

	public void setSeqpadraovenctoicms(Long seqpadraovenctoicms) {
		this.seqpadraovenctoicms = seqpadraovenctoicms;
	}

	@Column(name = "SEQPADRAOVENCTOIR", precision = 15, scale = 0)
	public Long getSeqpadraovenctoir() {
		return this.seqpadraovenctoir;
	}

	public void setSeqpadraovenctoir(Long seqpadraovenctoir) {
		this.seqpadraovenctoir = seqpadraovenctoir;
	}

	@Column(name = "SEQPADRAOVENCTOISSST", precision = 15, scale = 0)
	public Long getSeqpadraovenctoissst() {
		return this.seqpadraovenctoissst;
	}

	public void setSeqpadraovenctoissst(Long seqpadraovenctoissst) {
		this.seqpadraovenctoissst = seqpadraovenctoissst;
	}

	@Column(name = "SEQPADRAOVENCTOISSQN", precision = 15, scale = 0)
	public Long getSeqpadraovenctoissqn() {
		return this.seqpadraovenctoissqn;
	}

	public void setSeqpadraovenctoissqn(Long seqpadraovenctoissqn) {
		this.seqpadraovenctoissqn = seqpadraovenctoissqn;
	}

	@Column(name = "SEQPADRAOVENCTOSESTSENAT", precision = 15, scale = 0)
	public Long getSeqpadraovenctosestsenat() {
		return this.seqpadraovenctosestsenat;
	}

	public void setSeqpadraovenctosestsenat(Long seqpadraovenctosestsenat) {
		this.seqpadraovenctosestsenat = seqpadraovenctosestsenat;
	}

	@Column(name = "SEQPADRAOVENCTOIPI", precision = 15, scale = 0)
	public Long getSeqpadraovenctoipi() {
		return this.seqpadraovenctoipi;
	}

	public void setSeqpadraovenctoipi(Long seqpadraovenctoipi) {
		this.seqpadraovenctoipi = seqpadraovenctoipi;
	}

	@Column(name = "CSTISS", precision = 2, scale = 0)
	public Byte getCstiss() {
		return this.cstiss;
	}

	public void setCstiss(Byte cstiss) {
		this.cstiss = cstiss;
	}

	@Column(name = "INDGERADMSSC", length = 1)
	public String getIndgeradmssc() {
		return this.indgeradmssc;
	}

	public void setIndgeradmssc(String indgeradmssc) {
		this.indgeradmssc = indgeradmssc;
	}

	@Column(name = "INDCALCINSSAUTO", nullable = false, length = 1)
	public String getIndcalcinssauto() {
		return this.indcalcinssauto;
	}

	public void setIndcalcinssauto(String indcalcinssauto) {
		this.indcalcinssauto = indcalcinssauto;
	}

	@Column(name = "INDCALCCREDPISCOFAUTO", nullable = false, length = 1)
	public String getIndcalccredpiscofauto() {
		return this.indcalccredpiscofauto;
	}

	public void setIndcalccredpiscofauto(String indcalccredpiscofauto) {
		this.indcalccredpiscofauto = indcalccredpiscofauto;
	}

	@Column(name = "INDCALCRETPISCOFAUTO", nullable = false, length = 1)
	public String getIndcalcretpiscofauto() {
		return this.indcalcretpiscofauto;
	}

	public void setIndcalcretpiscofauto(String indcalcretpiscofauto) {
		this.indcalcretpiscofauto = indcalcretpiscofauto;
	}

	@Column(name = "INDCALCCSSLLAUTO", nullable = false, length = 1)
	public String getIndcalccssllauto() {
		return this.indcalccssllauto;
	}

	public void setIndcalccssllauto(String indcalccssllauto) {
		this.indcalccssllauto = indcalccssllauto;
	}

	@Column(name = "INDCALCICMSAUTO", nullable = false, length = 1)
	public String getIndcalcicmsauto() {
		return this.indcalcicmsauto;
	}

	public void setIndcalcicmsauto(String indcalcicmsauto) {
		this.indcalcicmsauto = indcalcicmsauto;
	}

	@Column(name = "INDCALCIRAUTO", nullable = false, length = 1)
	public String getIndcalcirauto() {
		return this.indcalcirauto;
	}

	public void setIndcalcirauto(String indcalcirauto) {
		this.indcalcirauto = indcalcirauto;
	}

	@Column(name = "INDCALCISSSTAUTO", nullable = false, length = 1)
	public String getIndcalcissstauto() {
		return this.indcalcissstauto;
	}

	public void setIndcalcissstauto(String indcalcissstauto) {
		this.indcalcissstauto = indcalcissstauto;
	}

	@Column(name = "INDCALCISSQNAUTO", nullable = false, length = 1)
	public String getIndcalcissqnauto() {
		return this.indcalcissqnauto;
	}

	public void setIndcalcissqnauto(String indcalcissqnauto) {
		this.indcalcissqnauto = indcalcissqnauto;
	}

	@Column(name = "INDCALCSESTSENACAUTO", nullable = false, length = 1)
	public String getIndcalcsestsenacauto() {
		return this.indcalcsestsenacauto;
	}

	public void setIndcalcsestsenacauto(String indcalcsestsenacauto) {
		this.indcalcsestsenacauto = indcalcsestsenacauto;
	}

	@Column(name = "INDCALCIPIAUTO", nullable = false, length = 1)
	public String getIndcalcipiauto() {
		return this.indcalcipiauto;
	}

	public void setIndcalcipiauto(String indcalcipiauto) {
		this.indcalcipiauto = indcalcipiauto;
	}

	@Column(name = "CSTICMS", length = 3)
	public String getCsticms() {
		return this.csticms;
	}

	public void setCsticms(String csticms) {
		this.csticms = csticms;
	}

	@Column(name = "SITUACAONFIPI", length = 2)
	public String getSituacaonfipi() {
		return this.situacaonfipi;
	}

	public void setSituacaonfipi(String situacaonfipi) {
		this.situacaonfipi = situacaonfipi;
	}

	@Column(name = "ALIQCSLL", precision = 5)
	public BigDecimal getAliqcsll() {
		return this.aliqcsll;
	}

	public void setAliqcsll(BigDecimal aliqcsll) {
		this.aliqcsll = aliqcsll;
	}

	@Column(name = "INTEGRADOMINI", length = 1)
	public String getIntegradomini() {
		return this.integradomini;
	}

	public void setIntegradomini(String integradomini) {
		this.integradomini = integradomini;
	}

	@Column(name = "GERACIAP", length = 1)
	public String getGeraciap() {
		return this.geraciap;
	}

	public void setGeraciap(String geraciap) {
		this.geraciap = geraciap;
	}

	@Column(name = "SEQPADRAOVENCTOINSSPAT", precision = 15, scale = 0)
	public Long getSeqpadraovenctoinsspat() {
		return this.seqpadraovenctoinsspat;
	}

	public void setSeqpadraovenctoinsspat(Long seqpadraovenctoinsspat) {
		this.seqpadraovenctoinsspat = seqpadraovenctoinsspat;
	}

	@Column(name = "INDCALCINSSPATAUTO", length = 1)
	public String getIndcalcinsspatauto() {
		return this.indcalcinsspatauto;
	}

	public void setIndcalcinsspatauto(String indcalcinsspatauto) {
		this.indcalcinsspatauto = indcalcinsspatauto;
	}

	@Column(name = "PERDESPESAPROD", precision = 4)
	public BigDecimal getPerdespesaprod() {
		return this.perdespesaprod;
	}

	public void setPerdespesaprod(BigDecimal perdespesaprod) {
		this.perdespesaprod = perdespesaprod;
	}

	@Column(name = "SOMAPEDAGIOBASE", length = 1)
	public String getSomapedagiobase() {
		return this.somapedagiobase;
	}

	public void setSomapedagiobase(String somapedagiobase) {
		this.somapedagiobase = somapedagiobase;
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

	@Column(name = "CODSITRESPDES", length = 2)
	public String getCodsitrespdes() {
		return this.codsitrespdes;
	}

	public void setCodsitrespdes(String codsitrespdes) {
		this.codsitrespdes = codsitrespdes;
	}

	@Column(name = "INDGERADESBH", length = 1)
	public String getIndgeradesbh() {
		return this.indgeradesbh;
	}

	public void setIndgeradesbh(String indgeradesbh) {
		this.indgeradesbh = indgeradesbh;
	}

	@Column(name = "COMPDOMINI", length = 1)
	public String getCompdomini() {
		return this.compdomini;
	}

	public void setCompdomini(String compdomini) {
		this.compdomini = compdomini;
	}

	@Column(name = "BEMDOMINI", precision = 22, scale = 0)
	public BigDecimal getBemdomini() {
		return this.bemdomini;
	}

	public void setBemdomini(BigDecimal bemdomini) {
		this.bemdomini = bemdomini;
	}

	@Column(name = "CODOBSERVACAO", length = 250)
	public String getCodobservacao() {
		return this.codobservacao;
	}

	public void setCodobservacao(String codobservacao) {
		this.codobservacao = codobservacao;
	}

	@Column(name = "CODRECDARFIRRF", precision = 4, scale = 0)
	public Short getCodrecdarfirrf() {
		return this.codrecdarfirrf;
	}

	public void setCodrecdarfirrf(Short codrecdarfirrf) {
		this.codrecdarfirrf = codrecdarfirrf;
	}

	@Column(name = "MODDOCSERVPRESTDESBH", precision = 2, scale = 0)
	public Byte getModdocservprestdesbh() {
		return this.moddocservprestdesbh;
	}

	public void setModdocservprestdesbh(Byte moddocservprestdesbh) {
		this.moddocservprestdesbh = moddocservprestdesbh;
	}

	@Column(name = "SERIEDOCDESBH", precision = 4, scale = 0)
	public Short getSeriedocdesbh() {
		return this.seriedocdesbh;
	}

	public void setSeriedocdesbh(Short seriedocdesbh) {
		this.seriedocdesbh = seriedocdesbh;
	}

	@Column(name = "SUBSERIEDOCDESBH", precision = 2, scale = 0)
	public Byte getSubseriedocdesbh() {
		return this.subseriedocdesbh;
	}

	public void setSubseriedocdesbh(Byte subseriedocdesbh) {
		this.subseriedocdesbh = subseriedocdesbh;
	}

	@Column(name = "SEQTRIBUTACAOISS", precision = 3, scale = 0)
	public Short getSeqtributacaoiss() {
		return this.seqtributacaoiss;
	}

	public void setSeqtributacaoiss(Short seqtributacaoiss) {
		this.seqtributacaoiss = seqtributacaoiss;
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

	@Column(name = "SEQABACIWEBPARAMNF", precision = 15, scale = 0)
	public Long getSeqabaciwebparamnf() {
		return this.seqabaciwebparamnf;
	}

	public void setSeqabaciwebparamnf(Long seqabaciwebparamnf) {
		this.seqabaciwebparamnf = seqabaciwebparamnf;
	}

	@Column(name = "ALIQISSQN", precision = 7, scale = 4)
	public BigDecimal getAliqissqn() {
		return this.aliqissqn;
	}

	public void setAliqissqn(BigDecimal aliqissqn) {
		this.aliqissqn = aliqissqn;
	}

	@Column(name = "ALIQISSST", precision = 7, scale = 4)
	public BigDecimal getAliqissst() {
		return this.aliqissst;
	}

	public void setAliqissst(BigDecimal aliqissst) {
		this.aliqissst = aliqissst;
	}

	@Column(name = "CODESPECIEISSST", length = 6)
	public String getCodespecieissst() {
		return this.codespecieissst;
	}

	public void setCodespecieissst(String codespecieissst) {
		this.codespecieissst = codespecieissst;
	}

	@Column(name = "CODESPECIEISSQN", length = 6)
	public String getCodespecieissqn() {
		return this.codespecieissqn;
	}

	public void setCodespecieissqn(String codespecieissqn) {
		this.codespecieissqn = codespecieissqn;
	}

	@Column(name = "CODESPECIEPIS", length = 6)
	public String getCodespeciepis() {
		return this.codespeciepis;
	}

	public void setCodespeciepis(String codespeciepis) {
		this.codespeciepis = codespeciepis;
	}

	@Column(name = "CODESPECIECOFINS", length = 6)
	public String getCodespeciecofins() {
		return this.codespeciecofins;
	}

	public void setCodespeciecofins(String codespeciecofins) {
		this.codespeciecofins = codespeciecofins;
	}

	@Column(name = "CODESPECIEICMS", length = 6)
	public String getCodespecieicms() {
		return this.codespecieicms;
	}

	public void setCodespecieicms(String codespecieicms) {
		this.codespecieicms = codespecieicms;
	}

	@Column(name = "CODESPECIEIPI", length = 6)
	public String getCodespecieipi() {
		return this.codespecieipi;
	}

	public void setCodespecieipi(String codespecieipi) {
		this.codespecieipi = codespecieipi;
	}

	@Column(name = "ALIQIR", precision = 7, scale = 4)
	public BigDecimal getAliqir() {
		return this.aliqir;
	}

	public void setAliqir(BigDecimal aliqir) {
		this.aliqir = aliqir;
	}

	@Column(name = "CODESPECIECSSLL", length = 6)
	public String getCodespeciecssll() {
		return this.codespeciecssll;
	}

	public void setCodespeciecssll(String codespeciecssll) {
		this.codespeciecssll = codespeciecssll;
	}

	@Column(name = "ALIQSEST", precision = 7, scale = 4)
	public BigDecimal getAliqsest() {
		return this.aliqsest;
	}

	public void setAliqsest(BigDecimal aliqsest) {
		this.aliqsest = aliqsest;
	}

	@Column(name = "CODESPECIESEST", length = 6)
	public String getCodespeciesest() {
		return this.codespeciesest;
	}

	public void setCodespeciesest(String codespeciesest) {
		this.codespeciesest = codespeciesest;
	}

	@Column(name = "ALIQINSSPATRONAL", precision = 7, scale = 4)
	public BigDecimal getAliqinsspatronal() {
		return this.aliqinsspatronal;
	}

	public void setAliqinsspatronal(BigDecimal aliqinsspatronal) {
		this.aliqinsspatronal = aliqinsspatronal;
	}

	@Column(name = "CODESPECIEINSSPATRONAL", length = 6)
	public String getCodespecieinsspatronal() {
		return this.codespecieinsspatronal;
	}

	public void setCodespecieinsspatronal(String codespecieinsspatronal) {
		this.codespecieinsspatronal = codespecieinsspatronal;
	}

	@Column(name = "ALIQINSSJUR", precision = 7, scale = 4)
	public BigDecimal getAliqinssjur() {
		return this.aliqinssjur;
	}

	public void setAliqinssjur(BigDecimal aliqinssjur) {
		this.aliqinssjur = aliqinssjur;
	}

	@Column(name = "CODESPECIEINSSJUR", length = 6)
	public String getCodespecieinssjur() {
		return this.codespecieinssjur;
	}

	public void setCodespecieinssjur(String codespecieinssjur) {
		this.codespecieinssjur = codespecieinssjur;
	}

	@Column(name = "ALIQIRPESFIS", precision = 7, scale = 4)
	public BigDecimal getAliqirpesfis() {
		return this.aliqirpesfis;
	}

	public void setAliqirpesfis(BigDecimal aliqirpesfis) {
		this.aliqirpesfis = aliqirpesfis;
	}

	@Column(name = "CODESPECIEIRPESFIS", length = 6)
	public String getCodespecieirpesfis() {
		return this.codespecieirpesfis;
	}

	public void setCodespecieirpesfis(String codespecieirpesfis) {
		this.codespecieirpesfis = codespecieirpesfis;
	}

	@Column(name = "CODRECDARFIRRFPESFIS", precision = 4, scale = 0)
	public Short getCodrecdarfirrfpesfis() {
		return this.codrecdarfirrfpesfis;
	}

	public void setCodrecdarfirrfpesfis(Short codrecdarfirrfpesfis) {
		this.codrecdarfirrfpesfis = codrecdarfirrfpesfis;
	}

	@Column(name = "SEQPADRAOVENCTOIRPESFIS", precision = 15, scale = 0)
	public Long getSeqpadraovenctoirpesfis() {
		return this.seqpadraovenctoirpesfis;
	}

	public void setSeqpadraovenctoirpesfis(Long seqpadraovenctoirpesfis) {
		this.seqpadraovenctoirpesfis = seqpadraovenctoirpesfis;
	}

	@Column(name = "INDCALCIRAUTOPESFIS", length = 1)
	public String getIndcalcirautopesfis() {
		return this.indcalcirautopesfis;
	}

	public void setIndcalcirautopesfis(String indcalcirautopesfis) {
		this.indcalcirautopesfis = indcalcirautopesfis;
	}

	@Column(name = "ALIQINSSPESFIS", precision = 7, scale = 4)
	public BigDecimal getAliqinsspesfis() {
		return this.aliqinsspesfis;
	}

	public void setAliqinsspesfis(BigDecimal aliqinsspesfis) {
		this.aliqinsspesfis = aliqinsspesfis;
	}

	@Column(name = "CODESPECIEINSSPESFIS", length = 6)
	public String getCodespecieinsspesfis() {
		return this.codespecieinsspesfis;
	}

	public void setCodespecieinsspesfis(String codespecieinsspesfis) {
		this.codespecieinsspesfis = codespecieinsspesfis;
	}

	@Column(name = "SEQPADRAOVENCTOINSSPESFIS", precision = 15, scale = 0)
	public Long getSeqpadraovenctoinsspesfis() {
		return this.seqpadraovenctoinsspesfis;
	}

	public void setSeqpadraovenctoinsspesfis(Long seqpadraovenctoinsspesfis) {
		this.seqpadraovenctoinsspesfis = seqpadraovenctoinsspesfis;
	}

	@Column(name = "INDCALCINSSAUTOPESFIS", length = 1)
	public String getIndcalcinssautopesfis() {
		return this.indcalcinssautopesfis;
	}

	public void setIndcalcinssautopesfis(String indcalcinssautopesfis) {
		this.indcalcinssautopesfis = indcalcinssautopesfis;
	}
}
