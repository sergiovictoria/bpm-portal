package br.com.seta.processo.entity;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrNfitensdespesa generated by hbm2java
 */
@Entity
@Table(name = "OR_NFITENSDESPESA")
public class OrNfitensdespesaEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private OrNfitensdespesaId id;
	private OrNfdespesaEntity orNfdespesa;
	private short nroempresa;
	private short nroempresaorc;
	private String codproduto;
	private short versaoprod;
	private String descricao;
	private String servico;
	private int cfop;
	private Byte complemento;
	private String unidadepadrao;
	private String veiculo;
	private BigDecimal aliqipiprod;
	private BigDecimal aliqicmsprod;
	private String codstf;
	private String unidade;
	private BigDecimal quantidade;
	private BigDecimal vlrtotal;
	private BigDecimal vlrdesconto;
	private BigDecimal vlrisento;
	private BigDecimal vlroutras;
	private BigDecimal vlrbaseicmsprop;
	private BigDecimal vlricms;
	private BigDecimal aliqicms;
	private String tipotributacao;
	private BigDecimal vlrbasepis;
	private BigDecimal aliqpis;
	private BigDecimal vlrpis;
	private BigDecimal vlrbasecofins;
	private BigDecimal aliqcofins;
	private BigDecimal vlrcofins;
	private BigDecimal vlrbaseiss;
	private BigDecimal aliqiss;
	private BigDecimal vlriss;
	private BigDecimal inss;
	private BigDecimal irrf;
	private BigDecimal vlrissst;
	private BigDecimal vlrcssll;
	private BigDecimal vlracrescimos;
	private BigDecimal aliqinss;
	private BigDecimal aliqirrf;
	private BigDecimal aliqissst;
	private BigDecimal aliqcssll;
	private BigDecimal vlrliqdesp;
	private BigDecimal aliqsestsenat;
	private BigDecimal vlrsestsenat;
	private BigDecimal vlrbaseinss;
	private BigDecimal vlrbaseirrf;
	private BigDecimal vlrbaseissst;
	private BigDecimal vlrbasecssll;
	private BigDecimal vlrbasesestsenat;
	private String tributaicmsnfdesp;
	private String tributapisnfdesp;
	private String tributacofinsnfdesp;
	private BigDecimal percredbaseicms;
	private BigDecimal vlrbasicmsstpro;
	private String codncm;
	private String indisentopiscofins;
	private String indisentodebpiscofins;
	private BigDecimal vlritem;
	private String indfinanceiro;
	private String tipotributacaoipi;
	private String geratitipi;
	private BigDecimal aliqipi;
	private BigDecimal vlrbaseipi;
	private BigDecimal vlripi;
	private BigDecimal vlroutrosipi;
	private BigDecimal vlrisentoipi;
	private BigDecimal seqtribiss;
	private BigDecimal seqtpreciss;
	private BigDecimal seqmotnaoretiss;
	private BigDecimal vlrdeducaoiss;
	private String retencaopisnfdesp;
	private String retencaocofinsnfdesp;
	private String situacaonfpis;
	private String situacaonfcofins;
	private String cstpiscred;
	private BigDecimal aliqpiscred;
	private BigDecimal vlrbasepiscred;
	private BigDecimal vlrpiscred;
	private String cstcofinscred;
	private BigDecimal aliqcofinscred;
	private BigDecimal vlrbasecofinscred;
	private BigDecimal vlrcofinscred;
	private BigDecimal aliqicmsdif;
	private BigDecimal vlrbaseicmsdif;
	private BigDecimal vlricmsdif;
	private Byte cstiss;
	private Integer codtributacao;
	private BigDecimal vlrbaseicmsrettransp;
	private BigDecimal aliqicmsrettransp;
	private BigDecimal vlricmsrettransp;
	private String situacaonfipi;
	private String indgeraciap;
	private String indgeradomini;
	private Long nrobem;
	private BigDecimal aliqinsspat;
	private BigDecimal vlrbaseinsspat;
	private BigDecimal vlrinsspat;
	private Integer codservicodeiss;
	private BigDecimal aliqinsspesfis;
	private BigDecimal vlrbaseinsspesfis;
	private BigDecimal insspesfis;
	private BigDecimal aliqirrfpesfis;
	private BigDecimal vlrbaseirrfpesfis;
	private BigDecimal irrfpesfis;

	public OrNfitensdespesaEntity() {
	}

	public OrNfitensdespesaEntity(OrNfitensdespesaId id, OrNfdespesaEntity orNfdespesa,
			short nroempresa, short nroempresaorc, String codproduto,
			short versaoprod, int cfop, BigDecimal quantidade,
			BigDecimal vlrtotal, BigDecimal vlritem, String indfinanceiro) {
		this.id = id;
		this.orNfdespesa = orNfdespesa;
		this.nroempresa = nroempresa;
		this.nroempresaorc = nroempresaorc;
		this.codproduto = codproduto;
		this.versaoprod = versaoprod;
		this.cfop = cfop;
		this.quantidade = quantidade;
		this.vlrtotal = vlrtotal;
		this.vlritem = vlritem;
		this.indfinanceiro = indfinanceiro;
	}

	public OrNfitensdespesaEntity(OrNfitensdespesaId id, OrNfdespesaEntity orNfdespesa,
			short nroempresa, short nroempresaorc, String codproduto,
			short versaoprod, String descricao, String servico, int cfop,
			Byte complemento, String unidadepadrao, String veiculo,
			BigDecimal aliqipiprod, BigDecimal aliqicmsprod, String codstf,
			String unidade, BigDecimal quantidade, BigDecimal vlrtotal,
			BigDecimal vlrdesconto, BigDecimal vlrisento, BigDecimal vlroutras,
			BigDecimal vlrbaseicmsprop, BigDecimal vlricms,
			BigDecimal aliqicms, String tipotributacao, BigDecimal vlrbasepis,
			BigDecimal aliqpis, BigDecimal vlrpis, BigDecimal vlrbasecofins,
			BigDecimal aliqcofins, BigDecimal vlrcofins, BigDecimal vlrbaseiss,
			BigDecimal aliqiss, BigDecimal vlriss, BigDecimal inss,
			BigDecimal irrf, BigDecimal vlrissst, BigDecimal vlrcssll,
			BigDecimal vlracrescimos, BigDecimal aliqinss, BigDecimal aliqirrf,
			BigDecimal aliqissst, BigDecimal aliqcssll, BigDecimal vlrliqdesp,
			BigDecimal aliqsestsenat, BigDecimal vlrsestsenat,
			BigDecimal vlrbaseinss, BigDecimal vlrbaseirrf,
			BigDecimal vlrbaseissst, BigDecimal vlrbasecssll,
			BigDecimal vlrbasesestsenat, String tributaicmsnfdesp,
			String tributapisnfdesp, String tributacofinsnfdesp,
			BigDecimal percredbaseicms, BigDecimal vlrbasicmsstpro,
			String codncm, String indisentopiscofins,
			String indisentodebpiscofins, BigDecimal vlritem,
			String indfinanceiro, String tipotributacaoipi, String geratitipi,
			BigDecimal aliqipi, BigDecimal vlrbaseipi, BigDecimal vlripi,
			BigDecimal vlroutrosipi, BigDecimal vlrisentoipi,
			BigDecimal seqtribiss, BigDecimal seqtpreciss,
			BigDecimal seqmotnaoretiss, BigDecimal vlrdeducaoiss,
			String retencaopisnfdesp, String retencaocofinsnfdesp,
			String situacaonfpis, String situacaonfcofins, String cstpiscred,
			BigDecimal aliqpiscred, BigDecimal vlrbasepiscred,
			BigDecimal vlrpiscred, String cstcofinscred,
			BigDecimal aliqcofinscred, BigDecimal vlrbasecofinscred,
			BigDecimal vlrcofinscred, BigDecimal aliqicmsdif,
			BigDecimal vlrbaseicmsdif, BigDecimal vlricmsdif, Byte cstiss,
			Integer codtributacao, BigDecimal vlrbaseicmsrettransp,
			BigDecimal aliqicmsrettransp, BigDecimal vlricmsrettransp,
			String situacaonfipi, String indgeraciap, String indgeradomini,
			Long nrobem, BigDecimal aliqinsspat, BigDecimal vlrbaseinsspat,
			BigDecimal vlrinsspat, Integer codservicodeiss,
			BigDecimal aliqinsspesfis, BigDecimal vlrbaseinsspesfis,
			BigDecimal insspesfis, BigDecimal aliqirrfpesfis,
			BigDecimal vlrbaseirrfpesfis, BigDecimal irrfpesfis) {
		this.id = id;
		this.orNfdespesa = orNfdespesa;
		this.nroempresa = nroempresa;
		this.nroempresaorc = nroempresaorc;
		this.codproduto = codproduto;
		this.versaoprod = versaoprod;
		this.descricao = descricao;
		this.servico = servico;
		this.cfop = cfop;
		this.complemento = complemento;
		this.unidadepadrao = unidadepadrao;
		this.veiculo = veiculo;
		this.aliqipiprod = aliqipiprod;
		this.aliqicmsprod = aliqicmsprod;
		this.codstf = codstf;
		this.unidade = unidade;
		this.quantidade = quantidade;
		this.vlrtotal = vlrtotal;
		this.vlrdesconto = vlrdesconto;
		this.vlrisento = vlrisento;
		this.vlroutras = vlroutras;
		this.vlrbaseicmsprop = vlrbaseicmsprop;
		this.vlricms = vlricms;
		this.aliqicms = aliqicms;
		this.tipotributacao = tipotributacao;
		this.vlrbasepis = vlrbasepis;
		this.aliqpis = aliqpis;
		this.vlrpis = vlrpis;
		this.vlrbasecofins = vlrbasecofins;
		this.aliqcofins = aliqcofins;
		this.vlrcofins = vlrcofins;
		this.vlrbaseiss = vlrbaseiss;
		this.aliqiss = aliqiss;
		this.vlriss = vlriss;
		this.inss = inss;
		this.irrf = irrf;
		this.vlrissst = vlrissst;
		this.vlrcssll = vlrcssll;
		this.vlracrescimos = vlracrescimos;
		this.aliqinss = aliqinss;
		this.aliqirrf = aliqirrf;
		this.aliqissst = aliqissst;
		this.aliqcssll = aliqcssll;
		this.vlrliqdesp = vlrliqdesp;
		this.aliqsestsenat = aliqsestsenat;
		this.vlrsestsenat = vlrsestsenat;
		this.vlrbaseinss = vlrbaseinss;
		this.vlrbaseirrf = vlrbaseirrf;
		this.vlrbaseissst = vlrbaseissst;
		this.vlrbasecssll = vlrbasecssll;
		this.vlrbasesestsenat = vlrbasesestsenat;
		this.tributaicmsnfdesp = tributaicmsnfdesp;
		this.tributapisnfdesp = tributapisnfdesp;
		this.tributacofinsnfdesp = tributacofinsnfdesp;
		this.percredbaseicms = percredbaseicms;
		this.vlrbasicmsstpro = vlrbasicmsstpro;
		this.codncm = codncm;
		this.indisentopiscofins = indisentopiscofins;
		this.indisentodebpiscofins = indisentodebpiscofins;
		this.vlritem = vlritem;
		this.indfinanceiro = indfinanceiro;
		this.tipotributacaoipi = tipotributacaoipi;
		this.geratitipi = geratitipi;
		this.aliqipi = aliqipi;
		this.vlrbaseipi = vlrbaseipi;
		this.vlripi = vlripi;
		this.vlroutrosipi = vlroutrosipi;
		this.vlrisentoipi = vlrisentoipi;
		this.seqtribiss = seqtribiss;
		this.seqtpreciss = seqtpreciss;
		this.seqmotnaoretiss = seqmotnaoretiss;
		this.vlrdeducaoiss = vlrdeducaoiss;
		this.retencaopisnfdesp = retencaopisnfdesp;
		this.retencaocofinsnfdesp = retencaocofinsnfdesp;
		this.situacaonfpis = situacaonfpis;
		this.situacaonfcofins = situacaonfcofins;
		this.cstpiscred = cstpiscred;
		this.aliqpiscred = aliqpiscred;
		this.vlrbasepiscred = vlrbasepiscred;
		this.vlrpiscred = vlrpiscred;
		this.cstcofinscred = cstcofinscred;
		this.aliqcofinscred = aliqcofinscred;
		this.vlrbasecofinscred = vlrbasecofinscred;
		this.vlrcofinscred = vlrcofinscred;
		this.aliqicmsdif = aliqicmsdif;
		this.vlrbaseicmsdif = vlrbaseicmsdif;
		this.vlricmsdif = vlricmsdif;
		this.cstiss = cstiss;
		this.codtributacao = codtributacao;
		this.vlrbaseicmsrettransp = vlrbaseicmsrettransp;
		this.aliqicmsrettransp = aliqicmsrettransp;
		this.vlricmsrettransp = vlricmsrettransp;
		this.situacaonfipi = situacaonfipi;
		this.indgeraciap = indgeraciap;
		this.indgeradomini = indgeradomini;
		this.nrobem = nrobem;
		this.aliqinsspat = aliqinsspat;
		this.vlrbaseinsspat = vlrbaseinsspat;
		this.vlrinsspat = vlrinsspat;
		this.codservicodeiss = codservicodeiss;
		this.aliqinsspesfis = aliqinsspesfis;
		this.vlrbaseinsspesfis = vlrbaseinsspesfis;
		this.insspesfis = insspesfis;
		this.aliqirrfpesfis = aliqirrfpesfis;
		this.vlrbaseirrfpesfis = vlrbaseirrfpesfis;
		this.irrfpesfis = irrfpesfis;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "seqnota", column = @Column(name = "SEQNOTA", nullable = false, precision = 15, scale = 0)),
			@AttributeOverride(name = "nroitem", column = @Column(name = "NROITEM", nullable = false, precision = 3, scale = 0)) })
	public OrNfitensdespesaId getId() {
		return this.id;
	}

	public void setId(OrNfitensdespesaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEQNOTA", nullable = false, insertable = false, updatable = false)
	public OrNfdespesaEntity getOrNfdespesa() {
		return this.orNfdespesa;
	}

	public void setOrNfdespesa(OrNfdespesaEntity orNfdespesa) {
		this.orNfdespesa = orNfdespesa;
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

	@Column(name = "CODPRODUTO", nullable = false, length = 14)
	public String getCodproduto() {
		return this.codproduto;
	}

	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
	}

	@Column(name = "VERSAOPROD", nullable = false, precision = 3, scale = 0)
	public short getVersaoprod() {
		return this.versaoprod;
	}

	public void setVersaoprod(short versaoprod) {
		this.versaoprod = versaoprod;
	}

	@Column(name = "DESCRICAO", length = 75)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "SERVICO", length = 1)
	public String getServico() {
		return this.servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	@Column(name = "CFOP", nullable = false, precision = 5, scale = 0)
	public int getCfop() {
		return this.cfop;
	}

	public void setCfop(int cfop) {
		this.cfop = cfop;
	}

	@Column(name = "COMPLEMENTO", precision = 2, scale = 0)
	public Byte getComplemento() {
		return this.complemento;
	}

	public void setComplemento(Byte complemento) {
		this.complemento = complemento;
	}

	@Column(name = "UNIDADEPADRAO", length = 4)
	public String getUnidadepadrao() {
		return this.unidadepadrao;
	}

	public void setUnidadepadrao(String unidadepadrao) {
		this.unidadepadrao = unidadepadrao;
	}

	@Column(name = "VEICULO", length = 1)
	public String getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	@Column(name = "ALIQIPIPROD", precision = 5)
	public BigDecimal getAliqipiprod() {
		return this.aliqipiprod;
	}

	public void setAliqipiprod(BigDecimal aliqipiprod) {
		this.aliqipiprod = aliqipiprod;
	}

	@Column(name = "ALIQICMSPROD", precision = 5)
	public BigDecimal getAliqicmsprod() {
		return this.aliqicmsprod;
	}

	public void setAliqicmsprod(BigDecimal aliqicmsprod) {
		this.aliqicmsprod = aliqicmsprod;
	}

	@Column(name = "CODSTF", length = 3)
	public String getCodstf() {
		return this.codstf;
	}

	public void setCodstf(String codstf) {
		this.codstf = codstf;
	}

	@Column(name = "UNIDADE", length = 4)
	public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@Column(name = "QUANTIDADE", nullable = false, precision = 13, scale = 3)
	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "VLRTOTAL", nullable = false, precision = 13)
	public BigDecimal getVlrtotal() {
		return this.vlrtotal;
	}

	public void setVlrtotal(BigDecimal vlrtotal) {
		this.vlrtotal = vlrtotal;
	}

	@Column(name = "VLRDESCONTO", precision = 13)
	public BigDecimal getVlrdesconto() {
		return this.vlrdesconto;
	}

	public void setVlrdesconto(BigDecimal vlrdesconto) {
		this.vlrdesconto = vlrdesconto;
	}

	@Column(name = "VLRISENTO", precision = 13)
	public BigDecimal getVlrisento() {
		return this.vlrisento;
	}

	public void setVlrisento(BigDecimal vlrisento) {
		this.vlrisento = vlrisento;
	}

	@Column(name = "VLROUTRAS", precision = 13)
	public BigDecimal getVlroutras() {
		return this.vlroutras;
	}

	public void setVlroutras(BigDecimal vlroutras) {
		this.vlroutras = vlroutras;
	}

	@Column(name = "VLRBASEICMSPROP", precision = 13)
	public BigDecimal getVlrbaseicmsprop() {
		return this.vlrbaseicmsprop;
	}

	public void setVlrbaseicmsprop(BigDecimal vlrbaseicmsprop) {
		this.vlrbaseicmsprop = vlrbaseicmsprop;
	}

	@Column(name = "VLRICMS", precision = 13)
	public BigDecimal getVlricms() {
		return this.vlricms;
	}

	public void setVlricms(BigDecimal vlricms) {
		this.vlricms = vlricms;
	}

	@Column(name = "ALIQICMS", precision = 4)
	public BigDecimal getAliqicms() {
		return this.aliqicms;
	}

	public void setAliqicms(BigDecimal aliqicms) {
		this.aliqicms = aliqicms;
	}

	@Column(name = "TIPOTRIBUTACAO", length = 1)
	public String getTipotributacao() {
		return this.tipotributacao;
	}

	public void setTipotributacao(String tipotributacao) {
		this.tipotributacao = tipotributacao;
	}

	@Column(name = "VLRBASEPIS", precision = 13)
	public BigDecimal getVlrbasepis() {
		return this.vlrbasepis;
	}

	public void setVlrbasepis(BigDecimal vlrbasepis) {
		this.vlrbasepis = vlrbasepis;
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

	@Column(name = "VLRBASECOFINS", precision = 13)
	public BigDecimal getVlrbasecofins() {
		return this.vlrbasecofins;
	}

	public void setVlrbasecofins(BigDecimal vlrbasecofins) {
		this.vlrbasecofins = vlrbasecofins;
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

	@Column(name = "VLRBASEISS", precision = 13)
	public BigDecimal getVlrbaseiss() {
		return this.vlrbaseiss;
	}

	public void setVlrbaseiss(BigDecimal vlrbaseiss) {
		this.vlrbaseiss = vlrbaseiss;
	}

	@Column(name = "ALIQISS", precision = 5)
	public BigDecimal getAliqiss() {
		return this.aliqiss;
	}

	public void setAliqiss(BigDecimal aliqiss) {
		this.aliqiss = aliqiss;
	}

	@Column(name = "VLRISS", precision = 13, scale = 3)
	public BigDecimal getVlriss() {
		return this.vlriss;
	}

	public void setVlriss(BigDecimal vlriss) {
		this.vlriss = vlriss;
	}

	@Column(name = "INSS", precision = 13)
	public BigDecimal getInss() {
		return this.inss;
	}

	public void setInss(BigDecimal inss) {
		this.inss = inss;
	}

	@Column(name = "IRRF", precision = 13)
	public BigDecimal getIrrf() {
		return this.irrf;
	}

	public void setIrrf(BigDecimal irrf) {
		this.irrf = irrf;
	}

	@Column(name = "VLRISSST", precision = 13)
	public BigDecimal getVlrissst() {
		return this.vlrissst;
	}

	public void setVlrissst(BigDecimal vlrissst) {
		this.vlrissst = vlrissst;
	}

	@Column(name = "VLRCSSLL", precision = 13)
	public BigDecimal getVlrcssll() {
		return this.vlrcssll;
	}

	public void setVlrcssll(BigDecimal vlrcssll) {
		this.vlrcssll = vlrcssll;
	}

	@Column(name = "VLRACRESCIMOS", precision = 15)
	public BigDecimal getVlracrescimos() {
		return this.vlracrescimos;
	}

	public void setVlracrescimos(BigDecimal vlracrescimos) {
		this.vlracrescimos = vlracrescimos;
	}

	@Column(name = "ALIQINSS", precision = 5)
	public BigDecimal getAliqinss() {
		return this.aliqinss;
	}

	public void setAliqinss(BigDecimal aliqinss) {
		this.aliqinss = aliqinss;
	}

	@Column(name = "ALIQIRRF", precision = 5)
	public BigDecimal getAliqirrf() {
		return this.aliqirrf;
	}

	public void setAliqirrf(BigDecimal aliqirrf) {
		this.aliqirrf = aliqirrf;
	}

	@Column(name = "ALIQISSST", precision = 5)
	public BigDecimal getAliqissst() {
		return this.aliqissst;
	}

	public void setAliqissst(BigDecimal aliqissst) {
		this.aliqissst = aliqissst;
	}

	@Column(name = "ALIQCSSLL", precision = 5)
	public BigDecimal getAliqcssll() {
		return this.aliqcssll;
	}

	public void setAliqcssll(BigDecimal aliqcssll) {
		this.aliqcssll = aliqcssll;
	}

	@Column(name = "VLRLIQDESP", precision = 15)
	public BigDecimal getVlrliqdesp() {
		return this.vlrliqdesp;
	}

	public void setVlrliqdesp(BigDecimal vlrliqdesp) {
		this.vlrliqdesp = vlrliqdesp;
	}

	@Column(name = "ALIQSESTSENAT", precision = 5)
	public BigDecimal getAliqsestsenat() {
		return this.aliqsestsenat;
	}

	public void setAliqsestsenat(BigDecimal aliqsestsenat) {
		this.aliqsestsenat = aliqsestsenat;
	}

	@Column(name = "VLRSESTSENAT", precision = 15)
	public BigDecimal getVlrsestsenat() {
		return this.vlrsestsenat;
	}

	public void setVlrsestsenat(BigDecimal vlrsestsenat) {
		this.vlrsestsenat = vlrsestsenat;
	}

	@Column(name = "VLRBASEINSS", precision = 13)
	public BigDecimal getVlrbaseinss() {
		return this.vlrbaseinss;
	}

	public void setVlrbaseinss(BigDecimal vlrbaseinss) {
		this.vlrbaseinss = vlrbaseinss;
	}

	@Column(name = "VLRBASEIRRF", precision = 13)
	public BigDecimal getVlrbaseirrf() {
		return this.vlrbaseirrf;
	}

	public void setVlrbaseirrf(BigDecimal vlrbaseirrf) {
		this.vlrbaseirrf = vlrbaseirrf;
	}

	@Column(name = "VLRBASEISSST", precision = 13)
	public BigDecimal getVlrbaseissst() {
		return this.vlrbaseissst;
	}

	public void setVlrbaseissst(BigDecimal vlrbaseissst) {
		this.vlrbaseissst = vlrbaseissst;
	}

	@Column(name = "VLRBASECSSLL", precision = 13)
	public BigDecimal getVlrbasecssll() {
		return this.vlrbasecssll;
	}

	public void setVlrbasecssll(BigDecimal vlrbasecssll) {
		this.vlrbasecssll = vlrbasecssll;
	}

	@Column(name = "VLRBASESESTSENAT", precision = 13)
	public BigDecimal getVlrbasesestsenat() {
		return this.vlrbasesestsenat;
	}

	public void setVlrbasesestsenat(BigDecimal vlrbasesestsenat) {
		this.vlrbasesestsenat = vlrbasesestsenat;
	}

	@Column(name = "TRIBUTAICMSNFDESP", length = 1)
	public String getTributaicmsnfdesp() {
		return this.tributaicmsnfdesp;
	}

	public void setTributaicmsnfdesp(String tributaicmsnfdesp) {
		this.tributaicmsnfdesp = tributaicmsnfdesp;
	}

	@Column(name = "TRIBUTAPISNFDESP", length = 1)
	public String getTributapisnfdesp() {
		return this.tributapisnfdesp;
	}

	public void setTributapisnfdesp(String tributapisnfdesp) {
		this.tributapisnfdesp = tributapisnfdesp;
	}

	@Column(name = "TRIBUTACOFINSNFDESP", length = 1)
	public String getTributacofinsnfdesp() {
		return this.tributacofinsnfdesp;
	}

	public void setTributacofinsnfdesp(String tributacofinsnfdesp) {
		this.tributacofinsnfdesp = tributacofinsnfdesp;
	}

	@Column(name = "PERCREDBASEICMS", precision = 5)
	public BigDecimal getPercredbaseicms() {
		return this.percredbaseicms;
	}

	public void setPercredbaseicms(BigDecimal percredbaseicms) {
		this.percredbaseicms = percredbaseicms;
	}

	@Column(name = "VLRBASICMSSTPRO", precision = 13)
	public BigDecimal getVlrbasicmsstpro() {
		return this.vlrbasicmsstpro;
	}

	public void setVlrbasicmsstpro(BigDecimal vlrbasicmsstpro) {
		this.vlrbasicmsstpro = vlrbasicmsstpro;
	}

	@Column(name = "CODNCM", length = 10)
	public String getCodncm() {
		return this.codncm;
	}

	public void setCodncm(String codncm) {
		this.codncm = codncm;
	}

	@Column(name = "INDISENTOPISCOFINS", length = 2)
	public String getIndisentopiscofins() {
		return this.indisentopiscofins;
	}

	public void setIndisentopiscofins(String indisentopiscofins) {
		this.indisentopiscofins = indisentopiscofins;
	}

	@Column(name = "INDISENTODEBPISCOFINS", length = 1)
	public String getIndisentodebpiscofins() {
		return this.indisentodebpiscofins;
	}

	public void setIndisentodebpiscofins(String indisentodebpiscofins) {
		this.indisentodebpiscofins = indisentodebpiscofins;
	}

	@Column(name = "VLRITEM", nullable = false, precision = 15)
	public BigDecimal getVlritem() {
		return this.vlritem;
	}

	public void setVlritem(BigDecimal vlritem) {
		this.vlritem = vlritem;
	}

	@Column(name = "INDFINANCEIRO", nullable = false, length = 1)
	public String getIndfinanceiro() {
		return this.indfinanceiro;
	}

	public void setIndfinanceiro(String indfinanceiro) {
		this.indfinanceiro = indfinanceiro;
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

	@Column(name = "SEQTRIBISS", precision = 22, scale = 0)
	public BigDecimal getSeqtribiss() {
		return this.seqtribiss;
	}

	public void setSeqtribiss(BigDecimal seqtribiss) {
		this.seqtribiss = seqtribiss;
	}

	@Column(name = "SEQTPRECISS", precision = 22, scale = 0)
	public BigDecimal getSeqtpreciss() {
		return this.seqtpreciss;
	}

	public void setSeqtpreciss(BigDecimal seqtpreciss) {
		this.seqtpreciss = seqtpreciss;
	}

	@Column(name = "SEQMOTNAORETISS", precision = 22, scale = 0)
	public BigDecimal getSeqmotnaoretiss() {
		return this.seqmotnaoretiss;
	}

	public void setSeqmotnaoretiss(BigDecimal seqmotnaoretiss) {
		this.seqmotnaoretiss = seqmotnaoretiss;
	}

	@Column(name = "VLRDEDUCAOISS", precision = 13)
	public BigDecimal getVlrdeducaoiss() {
		return this.vlrdeducaoiss;
	}

	public void setVlrdeducaoiss(BigDecimal vlrdeducaoiss) {
		this.vlrdeducaoiss = vlrdeducaoiss;
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

	@Column(name = "ALIQICMSDIF", precision = 5)
	public BigDecimal getAliqicmsdif() {
		return this.aliqicmsdif;
	}

	public void setAliqicmsdif(BigDecimal aliqicmsdif) {
		this.aliqicmsdif = aliqicmsdif;
	}

	@Column(name = "VLRBASEICMSDIF", precision = 15)
	public BigDecimal getVlrbaseicmsdif() {
		return this.vlrbaseicmsdif;
	}

	public void setVlrbaseicmsdif(BigDecimal vlrbaseicmsdif) {
		this.vlrbaseicmsdif = vlrbaseicmsdif;
	}

	@Column(name = "VLRICMSDIF", precision = 15)
	public BigDecimal getVlricmsdif() {
		return this.vlricmsdif;
	}

	public void setVlricmsdif(BigDecimal vlricmsdif) {
		this.vlricmsdif = vlricmsdif;
	}

	@Column(name = "CSTISS", precision = 2, scale = 0)
	public Byte getCstiss() {
		return this.cstiss;
	}

	public void setCstiss(Byte cstiss) {
		this.cstiss = cstiss;
	}

	@Column(name = "CODTRIBUTACAO", precision = 5, scale = 0)
	public Integer getCodtributacao() {
		return this.codtributacao;
	}

	public void setCodtributacao(Integer codtributacao) {
		this.codtributacao = codtributacao;
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

	@Column(name = "SITUACAONFIPI", length = 2)
	public String getSituacaonfipi() {
		return this.situacaonfipi;
	}

	public void setSituacaonfipi(String situacaonfipi) {
		this.situacaonfipi = situacaonfipi;
	}

	@Column(name = "INDGERACIAP", length = 1)
	public String getIndgeraciap() {
		return this.indgeraciap;
	}

	public void setIndgeraciap(String indgeraciap) {
		this.indgeraciap = indgeraciap;
	}

	@Column(name = "INDGERADOMINI", length = 1)
	public String getIndgeradomini() {
		return this.indgeradomini;
	}

	public void setIndgeradomini(String indgeradomini) {
		this.indgeradomini = indgeradomini;
	}

	@Column(name = "NROBEM", precision = 10, scale = 0)
	public Long getNrobem() {
		return this.nrobem;
	}

	public void setNrobem(Long nrobem) {
		this.nrobem = nrobem;
	}

	@Column(name = "ALIQINSSPAT", precision = 5)
	public BigDecimal getAliqinsspat() {
		return this.aliqinsspat;
	}

	public void setAliqinsspat(BigDecimal aliqinsspat) {
		this.aliqinsspat = aliqinsspat;
	}

	@Column(name = "VLRBASEINSSPAT", precision = 13)
	public BigDecimal getVlrbaseinsspat() {
		return this.vlrbaseinsspat;
	}

	public void setVlrbaseinsspat(BigDecimal vlrbaseinsspat) {
		this.vlrbaseinsspat = vlrbaseinsspat;
	}

	@Column(name = "VLRINSSPAT", precision = 13)
	public BigDecimal getVlrinsspat() {
		return this.vlrinsspat;
	}

	public void setVlrinsspat(BigDecimal vlrinsspat) {
		this.vlrinsspat = vlrinsspat;
	}

	@Column(name = "CODSERVICODEISS", precision = 7, scale = 0)
	public Integer getCodservicodeiss() {
		return this.codservicodeiss;
	}

	public void setCodservicodeiss(Integer codservicodeiss) {
		this.codservicodeiss = codservicodeiss;
	}

	@Column(name = "ALIQINSSPESFIS", precision = 5)
	public BigDecimal getAliqinsspesfis() {
		return this.aliqinsspesfis;
	}

	public void setAliqinsspesfis(BigDecimal aliqinsspesfis) {
		this.aliqinsspesfis = aliqinsspesfis;
	}

	@Column(name = "VLRBASEINSSPESFIS", precision = 13)
	public BigDecimal getVlrbaseinsspesfis() {
		return this.vlrbaseinsspesfis;
	}

	public void setVlrbaseinsspesfis(BigDecimal vlrbaseinsspesfis) {
		this.vlrbaseinsspesfis = vlrbaseinsspesfis;
	}

	@Column(name = "INSSPESFIS", precision = 13)
	public BigDecimal getInsspesfis() {
		return this.insspesfis;
	}

	public void setInsspesfis(BigDecimal insspesfis) {
		this.insspesfis = insspesfis;
	}

	@Column(name = "ALIQIRRFPESFIS", precision = 5)
	public BigDecimal getAliqirrfpesfis() {
		return this.aliqirrfpesfis;
	}

	public void setAliqirrfpesfis(BigDecimal aliqirrfpesfis) {
		this.aliqirrfpesfis = aliqirrfpesfis;
	}

	@Column(name = "VLRBASEIRRFPESFIS", precision = 13)
	public BigDecimal getVlrbaseirrfpesfis() {
		return this.vlrbaseirrfpesfis;
	}

	public void setVlrbaseirrfpesfis(BigDecimal vlrbaseirrfpesfis) {
		this.vlrbaseirrfpesfis = vlrbaseirrfpesfis;
	}

	@Column(name = "IRRFPESFIS", precision = 13)
	public BigDecimal getIrrfpesfis() {
		return this.irrfpesfis;
	}

	public void setIrrfpesfis(BigDecimal irrfpesfis) {
		this.irrfpesfis = irrfpesfis;
	}

}
