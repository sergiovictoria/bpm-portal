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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "MAP_FAMILIA")
@NamedNativeQueries({  
    @NamedNativeQuery(name = "FAMILIA.ALL",       query = "SELECT MAF.SEQFAMILIA, MAF.FAMILIA FROM MAP_FAMILIA MAF ORDER BY MAF.FAMILIA", resultClass = MapFamiliaEntity.class),
    @NamedNativeQuery(name = "FAMILIA.LIKE_NAME", query = "SELECT MAF.SEQFAMILIA, MAF.FAMILIA FROM MAP_FAMILIA MAF WHERE MAF.FAMILIA  LIKE :familia ORDER BY MAF.FAMILIA", resultClass = MapFamiliaEntity.class),
    @NamedNativeQuery(name = "FAMILIA.LIKE.SIZE", query = "SELECT COUNT(*) FROM MAP_FAMILIA MAF WHERE MAF.FAMILIA  LIKE :familia"),
    @NamedNativeQuery(name = "FAMILIA.FIND_ONE",  query = "SELECT MAF.* FROM MAP_FAMILIA MAF WHERE MAF.SEQFAMILIA = :seqfamilia", resultClass = MapFamiliaEntity.class)
}) 

public class MapFamiliaEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal seqfamilia;
	private String familia;
	private String pesavel;
	private String indisentopis;
	private BigDecimal indiceformbaseipi;
	private BigDecimal aliquotaipi;
	private String pmtdecimal;
	private String pmtmultiplicacao;
	private String indvasilhame;
	private String arquivofigura;
	private Date dtahorinclusao;
	private String usuarioinclusao;
	private Date dtahoralteracao;
	private String usuarioalteracao;
	private String indreplicacao;
	private String indgeroureplicacao;
	private String indcalcipisaida;
	private BigDecimal perbaseipi;
	private BigDecimal perisentoipi;
	private BigDecimal peroutroipi;
	private BigDecimal peraliquotaipi;
	private BigDecimal vlripipauta;
	private String indtribpiscofins;
	private String indcontrolevda;
	private String indgenerico;
	private String indprecomonitorado;
	private String indstpiscofins;
	private String indusaloteestoque;
	private String indisentodebpiscofins;
	private String indconfaz;
	private String indaliqzpiscofins;
	private String indpermconvenio;
	private String obsemergencial;
	private Short codtipi;
	private String indcomplvlrimp;
	private Byte tiporeceita;
	private Short codservico;
	private String indmonopiscofins;
	private String codsefazcomblub;
	private String inddigcheckout;
	private String indexiglicenca;
	private String codnbmsh;
	private String compcestabasica;
	private String papelimune;
	private String indusafigura;
	private String indsimilar;
	private String indreceita;
	private String indreceitacor;
	private BigDecimal seqfamiliatmp;
	private String situacaonfpis;
	private String situacaonfcofins;
	private BigDecimal perbasepis;
	private BigDecimal perbasecofins;
	private String indisentosuframa;
	private String indinconsistanvisa;
	private long nrobaseexportacao;
	private Short nroempresaabastec;
	private String atualizncmipi;
	private String indmedicamento;
	private String indetico;
	private BigDecimal aliqpadraoicms;
	private Integer codnatrec;
	private String situacaonfpissai;
	private String situacaonfcofinssai;
	private String indisentotsa;
	private String indaceitavendafrac;
	private Integer seqcertificado;
	private String textonfcomplemcertif;
	private BigDecimal seqnatrec;
	private String atualizancmimpimport;
	private BigDecimal perimpostoimport;
	private BigDecimal perredimpostoimport;
	private BigDecimal perredbaseipient;
	private String indmedcontrolado;
	private String listamedcontrolado;
	private String codmedcontrolado;
	private Short subitemcodservico;
	private String indbebidaalcoolica;
	private Short codisentagiars;
	private Short codoutrasgiars;
	private Long seqinfconservdomest;
	private String indetiquetaantifurto;
	private String sitpiscofinssimples;
	private String indexigerequisicao;
	private String situacaonfipi;
	private String situacaonfipisai;
	private Short nrodiagarantia;
	private String indtiporeceita;
	private String indantimicrobiano;
	private Date dtahoralteracargapdv;
	private String indusoprolongado;
	private Boolean unidademedidamed;
	private String indfarmaciapopular;
	private BigDecimal aliqpadraoicmsprotege;
	private String indescritoutrosei;
	private Short qtdparcelaetiqueta;
	private Short nrodiasgarantiafab;
	private String tipomodalgarantia;
	private Short codproddacal;
	private String codnve;
	private Date dtahorinclusaomsgcont;
	private String usuarioinclusaomsgcont;
	private String leituramsgcont;
	private BigDecimal seqtipofamilia;
	private BigDecimal seqmarcafamilia;
	private BigDecimal seqcomplementofamilia;
	private BigDecimal seqgramaturafamilia;
	private String indpermsusppiscofins;
	private String indexigelote;
	private String classeenquadramentoipi;
	private String codigoseloipi;
	private String codigoenquadramentoipi;
	private Short giarstipo;
	private Short giarscodigo;
	private String giarsespecificacao;
	private BigDecimal litrostabipipiscofins;
	private Integer codgiacreditopresumido;
	private Integer codgiatransferencia;
	private Integer codgiarecebimento;
	private Integer codgiaoutroscreditos;
	private Integer codgiaoutrosdebitos;
	private Integer codcest;
	private BigDecimal qtdmaxtransflocal;
	private Set<MapEmbalagemEntity> mapEmbalagemEntities = new HashSet<MapEmbalagemEntity>(0);
	private Set<MapFamfornecEntity> mapFamfornecEntities = new  HashSet<MapFamfornecEntity>(0);


	public MapFamiliaEntity() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQFAMILIA")
    @SequenceGenerator(name="SEQFAMILIA", sequenceName="S_SEQFAMILIA", allocationSize=1)
	@Column(name = "SEQFAMILIA", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqfamilia() {
		return this.seqfamilia;
	}

	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}


	@Column(name = "FAMILIA", nullable = false, length = 35)
	public String getFamilia() {
		return this.familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	@Column(name = "PESAVEL", nullable = false, length = 1)
	public String getPesavel() {
		return this.pesavel;
	}

	public void setPesavel(String pesavel) {
		this.pesavel = pesavel;
	}

	@Column(name = "INDISENTOPIS", length = 1)
	public String getIndisentopis() {
		return this.indisentopis;
	}

	public void setIndisentopis(String indisentopis) {
		this.indisentopis = indisentopis;
	}

	@Column(name = "INDICEFORMBASEIPI", nullable = false, precision = 5)
	public BigDecimal getIndiceformbaseipi() {
		return this.indiceformbaseipi;
	}

	public void setIndiceformbaseipi(BigDecimal indiceformbaseipi) {
		this.indiceformbaseipi = indiceformbaseipi;
	}

	@Column(name = "ALIQUOTAIPI", nullable = false, precision = 4)
	public BigDecimal getAliquotaipi() {
		return this.aliquotaipi;
	}

	public void setAliquotaipi(BigDecimal aliquotaipi) {
		this.aliquotaipi = aliquotaipi;
	}

	@Column(name = "PMTDECIMAL", nullable = false, length = 1)
	public String getPmtdecimal() {
		return this.pmtdecimal;
	}

	public void setPmtdecimal(String pmtdecimal) {
		this.pmtdecimal = pmtdecimal;
	}

	@Column(name = "PMTMULTIPLICACAO", length = 1)
	public String getPmtmultiplicacao() {
		return this.pmtmultiplicacao;
	}

	public void setPmtmultiplicacao(String pmtmultiplicacao) {
		this.pmtmultiplicacao = pmtmultiplicacao;
	}

	@Column(name = "INDVASILHAME", nullable = false, length = 1)
	public String getIndvasilhame() {
		return this.indvasilhame;
	}

	public void setIndvasilhame(String indvasilhame) {
		this.indvasilhame = indvasilhame;
	}

	@Column(name = "ARQUIVOFIGURA", length = 50)
	public String getArquivofigura() {
		return this.arquivofigura;
	}

	public void setArquivofigura(String arquivofigura) {
		this.arquivofigura = arquivofigura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAHORINCLUSAO", length = 7)
	public Date getDtahorinclusao() {
		return this.dtahorinclusao;
	}

	public void setDtahorinclusao(Date dtahorinclusao) {
		this.dtahorinclusao = dtahorinclusao;
	}

	@Column(name = "USUARIOINCLUSAO", length = 12)
	public String getUsuarioinclusao() {
		return this.usuarioinclusao;
	}

	public void setUsuarioinclusao(String usuarioinclusao) {
		this.usuarioinclusao = usuarioinclusao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAHORALTERACAO", length = 7)
	public Date getDtahoralteracao() {
		return this.dtahoralteracao;
	}

	public void setDtahoralteracao(Date dtahoralteracao) {
		this.dtahoralteracao = dtahoralteracao;
	}

	@Column(name = "USUARIOALTERACAO", length = 12)
	public String getUsuarioalteracao() {
		return this.usuarioalteracao;
	}

	public void setUsuarioalteracao(String usuarioalteracao) {
		this.usuarioalteracao = usuarioalteracao;
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

	@Column(name = "INDCALCIPISAIDA", length = 1)
	public String getIndcalcipisaida() {
		return this.indcalcipisaida;
	}

	public void setIndcalcipisaida(String indcalcipisaida) {
		this.indcalcipisaida = indcalcipisaida;
	}

	@Column(name = "PERBASEIPI", precision = 7, scale = 4)
	public BigDecimal getPerbaseipi() {
		return this.perbaseipi;
	}

	public void setPerbaseipi(BigDecimal perbaseipi) {
		this.perbaseipi = perbaseipi;
	}

	@Column(name = "PERISENTOIPI", precision = 7, scale = 4)
	public BigDecimal getPerisentoipi() {
		return this.perisentoipi;
	}

	public void setPerisentoipi(BigDecimal perisentoipi) {
		this.perisentoipi = perisentoipi;
	}

	@Column(name = "PEROUTROIPI", precision = 7, scale = 4)
	public BigDecimal getPeroutroipi() {
		return this.peroutroipi;
	}

	public void setPeroutroipi(BigDecimal peroutroipi) {
		this.peroutroipi = peroutroipi;
	}

	@Column(name = "PERALIQUOTAIPI", precision = 4)
	public BigDecimal getPeraliquotaipi() {
		return this.peraliquotaipi;
	}

	public void setPeraliquotaipi(BigDecimal peraliquotaipi) {
		this.peraliquotaipi = peraliquotaipi;
	}

	@Column(name = "VLRIPIPAUTA", precision = 15, scale = 6)
	public BigDecimal getVlripipauta() {
		return this.vlripipauta;
	}

	public void setVlripipauta(BigDecimal vlripipauta) {
		this.vlripipauta = vlripipauta;
	}

	@Column(name = "INDTRIBPISCOFINS", length = 1)
	public String getIndtribpiscofins() {
		return this.indtribpiscofins;
	}

	public void setIndtribpiscofins(String indtribpiscofins) {
		this.indtribpiscofins = indtribpiscofins;
	}

	@Column(name = "INDCONTROLEVDA", length = 1)
	public String getIndcontrolevda() {
		return this.indcontrolevda;
	}

	public void setIndcontrolevda(String indcontrolevda) {
		this.indcontrolevda = indcontrolevda;
	}

	@Column(name = "INDGENERICO", length = 1)
	public String getIndgenerico() {
		return this.indgenerico;
	}

	public void setIndgenerico(String indgenerico) {
		this.indgenerico = indgenerico;
	}

	@Column(name = "INDPRECOMONITORADO", length = 1)
	public String getIndprecomonitorado() {
		return this.indprecomonitorado;
	}

	public void setIndprecomonitorado(String indprecomonitorado) {
		this.indprecomonitorado = indprecomonitorado;
	}

	@Column(name = "INDSTPISCOFINS", length = 1)
	public String getIndstpiscofins() {
		return this.indstpiscofins;
	}

	public void setIndstpiscofins(String indstpiscofins) {
		this.indstpiscofins = indstpiscofins;
	}

	@Column(name = "INDUSALOTEESTOQUE", length = 1)
	public String getIndusaloteestoque() {
		return this.indusaloteestoque;
	}

	public void setIndusaloteestoque(String indusaloteestoque) {
		this.indusaloteestoque = indusaloteestoque;
	}

	@Column(name = "INDISENTODEBPISCOFINS", length = 1)
	public String getIndisentodebpiscofins() {
		return this.indisentodebpiscofins;
	}

	public void setIndisentodebpiscofins(String indisentodebpiscofins) {
		this.indisentodebpiscofins = indisentodebpiscofins;
	}

	@Column(name = "INDCONFAZ", length = 1)
	public String getIndconfaz() {
		return this.indconfaz;
	}

	public void setIndconfaz(String indconfaz) {
		this.indconfaz = indconfaz;
	}

	@Column(name = "INDALIQZPISCOFINS", length = 1)
	public String getIndaliqzpiscofins() {
		return this.indaliqzpiscofins;
	}

	public void setIndaliqzpiscofins(String indaliqzpiscofins) {
		this.indaliqzpiscofins = indaliqzpiscofins;
	}

	@Column(name = "INDPERMCONVENIO", length = 1)
	public String getIndpermconvenio() {
		return this.indpermconvenio;
	}

	public void setIndpermconvenio(String indpermconvenio) {
		this.indpermconvenio = indpermconvenio;
	}

	@Column(name = "OBSEMERGENCIAL", length = 250)
	public String getObsemergencial() {
		return this.obsemergencial;
	}

	public void setObsemergencial(String obsemergencial) {
		this.obsemergencial = obsemergencial;
	}

	@Column(name = "CODTIPI", precision = 3, scale = 0)
	public Short getCodtipi() {
		return this.codtipi;
	}

	public void setCodtipi(Short codtipi) {
		this.codtipi = codtipi;
	}

	@Column(name = "INDCOMPLVLRIMP", length = 1)
	public String getIndcomplvlrimp() {
		return this.indcomplvlrimp;
	}

	public void setIndcomplvlrimp(String indcomplvlrimp) {
		this.indcomplvlrimp = indcomplvlrimp;
	}

	@Column(name = "TIPORECEITA", precision = 2, scale = 0)
	public Byte getTiporeceita() {
		return this.tiporeceita;
	}

	public void setTiporeceita(Byte tiporeceita) {
		this.tiporeceita = tiporeceita;
	}

	@Column(name = "CODSERVICO", precision = 4, scale = 0)
	public Short getCodservico() {
		return this.codservico;
	}

	public void setCodservico(Short codservico) {
		this.codservico = codservico;
	}

	@Column(name = "INDMONOPISCOFINS", length = 1)
	public String getIndmonopiscofins() {
		return this.indmonopiscofins;
	}

	public void setIndmonopiscofins(String indmonopiscofins) {
		this.indmonopiscofins = indmonopiscofins;
	}

	@Column(name = "CODSEFAZCOMBLUB", length = 14)
	public String getCodsefazcomblub() {
		return this.codsefazcomblub;
	}

	public void setCodsefazcomblub(String codsefazcomblub) {
		this.codsefazcomblub = codsefazcomblub;
	}

	@Column(name = "INDDIGCHECKOUT", length = 1)
	public String getInddigcheckout() {
		return this.inddigcheckout;
	}

	public void setInddigcheckout(String inddigcheckout) {
		this.inddigcheckout = inddigcheckout;
	}

	@Column(name = "INDEXIGLICENCA", length = 1)
	public String getIndexiglicenca() {
		return this.indexiglicenca;
	}

	public void setIndexiglicenca(String indexiglicenca) {
		this.indexiglicenca = indexiglicenca;
	}

	@Column(name = "CODNBMSH", length = 10)
	public String getCodnbmsh() {
		return this.codnbmsh;
	}

	public void setCodnbmsh(String codnbmsh) {
		this.codnbmsh = codnbmsh;
	}

	@Column(name = "COMPCESTABASICA", length = 1)
	public String getCompcestabasica() {
		return this.compcestabasica;
	}

	public void setCompcestabasica(String compcestabasica) {
		this.compcestabasica = compcestabasica;
	}

	@Column(name = "PAPELIMUNE", length = 1)
	public String getPapelimune() {
		return this.papelimune;
	}

	public void setPapelimune(String papelimune) {
		this.papelimune = papelimune;
	}

	@Column(name = "INDUSAFIGURA", length = 1)
	public String getIndusafigura() {
		return this.indusafigura;
	}

	public void setIndusafigura(String indusafigura) {
		this.indusafigura = indusafigura;
	}

	@Column(name = "INDSIMILAR", length = 1)
	public String getIndsimilar() {
		return this.indsimilar;
	}

	public void setIndsimilar(String indsimilar) {
		this.indsimilar = indsimilar;
	}

	@Column(name = "INDRECEITA", length = 1)
	public String getIndreceita() {
		return this.indreceita;
	}

	public void setIndreceita(String indreceita) {
		this.indreceita = indreceita;
	}

	@Column(name = "INDRECEITACOR", length = 1)
	public String getIndreceitacor() {
		return this.indreceitacor;
	}

	public void setIndreceitacor(String indreceitacor) {
		this.indreceitacor = indreceitacor;
	}

	@Column(name = "SEQFAMILIATMP", precision = 22, scale = 0)
	public BigDecimal getSeqfamiliatmp() {
		return this.seqfamiliatmp;
	}

	public void setSeqfamiliatmp(BigDecimal seqfamiliatmp) {
		this.seqfamiliatmp = seqfamiliatmp;
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

	@Column(name = "PERBASEPIS", precision = 7, scale = 4)
	public BigDecimal getPerbasepis() {
		return this.perbasepis;
	}

	public void setPerbasepis(BigDecimal perbasepis) {
		this.perbasepis = perbasepis;
	}

	@Column(name = "PERBASECOFINS", precision = 7, scale = 4)
	public BigDecimal getPerbasecofins() {
		return this.perbasecofins;
	}

	public void setPerbasecofins(BigDecimal perbasecofins) {
		this.perbasecofins = perbasecofins;
	}

	@Column(name = "INDISENTOSUFRAMA", length = 1)
	public String getIndisentosuframa() {
		return this.indisentosuframa;
	}

	public void setIndisentosuframa(String indisentosuframa) {
		this.indisentosuframa = indisentosuframa;
	}

	@Column(name = "INDINCONSISTANVISA", length = 1)
	public String getIndinconsistanvisa() {
		return this.indinconsistanvisa;
	}

	public void setIndinconsistanvisa(String indinconsistanvisa) {
		this.indinconsistanvisa = indinconsistanvisa;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@Column(name = "NROEMPRESAABASTEC", precision = 3, scale = 0)
	public Short getNroempresaabastec() {
		return this.nroempresaabastec;
	}

	public void setNroempresaabastec(Short nroempresaabastec) {
		this.nroempresaabastec = nroempresaabastec;
	}

	@Column(name = "ATUALIZNCMIPI", length = 1)
	public String getAtualizncmipi() {
		return this.atualizncmipi;
	}

	public void setAtualizncmipi(String atualizncmipi) {
		this.atualizncmipi = atualizncmipi;
	}

	@Column(name = "INDMEDICAMENTO", length = 1)
	public String getIndmedicamento() {
		return this.indmedicamento;
	}

	public void setIndmedicamento(String indmedicamento) {
		this.indmedicamento = indmedicamento;
	}

	@Column(name = "INDETICO", length = 1)
	public String getIndetico() {
		return this.indetico;
	}

	public void setIndetico(String indetico) {
		this.indetico = indetico;
	}

	@Column(name = "ALIQPADRAOICMS", precision = 15)
	public BigDecimal getAliqpadraoicms() {
		return this.aliqpadraoicms;
	}

	public void setAliqpadraoicms(BigDecimal aliqpadraoicms) {
		this.aliqpadraoicms = aliqpadraoicms;
	}

	@Column(name = "CODNATREC", precision = 5, scale = 0)
	public Integer getCodnatrec() {
		return this.codnatrec;
	}

	public void setCodnatrec(Integer codnatrec) {
		this.codnatrec = codnatrec;
	}

	@Column(name = "SITUACAONFPISSAI", length = 2)
	public String getSituacaonfpissai() {
		return this.situacaonfpissai;
	}

	public void setSituacaonfpissai(String situacaonfpissai) {
		this.situacaonfpissai = situacaonfpissai;
	}

	@Column(name = "SITUACAONFCOFINSSAI", length = 2)
	public String getSituacaonfcofinssai() {
		return this.situacaonfcofinssai;
	}

	public void setSituacaonfcofinssai(String situacaonfcofinssai) {
		this.situacaonfcofinssai = situacaonfcofinssai;
	}

	@Column(name = "INDISENTOTSA", length = 1)
	public String getIndisentotsa() {
		return this.indisentotsa;
	}

	public void setIndisentotsa(String indisentotsa) {
		this.indisentotsa = indisentotsa;
	}

	@Column(name = "INDACEITAVENDAFRAC", length = 1)
	public String getIndaceitavendafrac() {
		return this.indaceitavendafrac;
	}

	public void setIndaceitavendafrac(String indaceitavendafrac) {
		this.indaceitavendafrac = indaceitavendafrac;
	}

	@Column(name = "SEQCERTIFICADO", precision = 5, scale = 0)
	public Integer getSeqcertificado() {
		return this.seqcertificado;
	}

	public void setSeqcertificado(Integer seqcertificado) {
		this.seqcertificado = seqcertificado;
	}

	@Column(name = "TEXTONFCOMPLEMCERTIF", length = 2000)
	public String getTextonfcomplemcertif() {
		return this.textonfcomplemcertif;
	}

	public void setTextonfcomplemcertif(String textonfcomplemcertif) {
		this.textonfcomplemcertif = textonfcomplemcertif;
	}

	@Column(name = "SEQNATREC", precision = 22, scale = 0)
	public BigDecimal getSeqnatrec() {
		return this.seqnatrec;
	}

	public void setSeqnatrec(BigDecimal seqnatrec) {
		this.seqnatrec = seqnatrec;
	}

	@Column(name = "ATUALIZANCMIMPIMPORT", length = 1)
	public String getAtualizancmimpimport() {
		return this.atualizancmimpimport;
	}

	public void setAtualizancmimpimport(String atualizancmimpimport) {
		this.atualizancmimpimport = atualizancmimpimport;
	}

	@Column(name = "PERIMPOSTOIMPORT", precision = 4)
	public BigDecimal getPerimpostoimport() {
		return this.perimpostoimport;
	}

	public void setPerimpostoimport(BigDecimal perimpostoimport) {
		this.perimpostoimport = perimpostoimport;
	}

	@Column(name = "PERREDIMPOSTOIMPORT", precision = 7, scale = 4)
	public BigDecimal getPerredimpostoimport() {
		return this.perredimpostoimport;
	}

	public void setPerredimpostoimport(BigDecimal perredimpostoimport) {
		this.perredimpostoimport = perredimpostoimport;
	}

	@Column(name = "PERREDBASEIPIENT", precision = 7, scale = 4)
	public BigDecimal getPerredbaseipient() {
		return this.perredbaseipient;
	}

	public void setPerredbaseipient(BigDecimal perredbaseipient) {
		this.perredbaseipient = perredbaseipient;
	}

	@Column(name = "INDMEDCONTROLADO", length = 1)
	public String getIndmedcontrolado() {
		return this.indmedcontrolado;
	}

	public void setIndmedcontrolado(String indmedcontrolado) {
		this.indmedcontrolado = indmedcontrolado;
	}

	@Column(name = "LISTAMEDCONTROLADO", length = 210)
	public String getListamedcontrolado() {
		return this.listamedcontrolado;
	}

	public void setListamedcontrolado(String listamedcontrolado) {
		this.listamedcontrolado = listamedcontrolado;
	}

	@Column(name = "CODMEDCONTROLADO", length = 5)
	public String getCodmedcontrolado() {
		return this.codmedcontrolado;
	}

	public void setCodmedcontrolado(String codmedcontrolado) {
		this.codmedcontrolado = codmedcontrolado;
	}

	@Column(name = "SUBITEMCODSERVICO", precision = 4, scale = 0)
	public Short getSubitemcodservico() {
		return this.subitemcodservico;
	}

	public void setSubitemcodservico(Short subitemcodservico) {
		this.subitemcodservico = subitemcodservico;
	}

	@Column(name = "INDBEBIDAALCOOLICA", length = 1)
	public String getIndbebidaalcoolica() {
		return this.indbebidaalcoolica;
	}

	public void setIndbebidaalcoolica(String indbebidaalcoolica) {
		this.indbebidaalcoolica = indbebidaalcoolica;
	}

	@Column(name = "CODISENTAGIARS", precision = 3, scale = 0)
	public Short getCodisentagiars() {
		return this.codisentagiars;
	}

	public void setCodisentagiars(Short codisentagiars) {
		this.codisentagiars = codisentagiars;
	}

	@Column(name = "CODOUTRASGIARS", precision = 3, scale = 0)
	public Short getCodoutrasgiars() {
		return this.codoutrasgiars;
	}

	public void setCodoutrasgiars(Short codoutrasgiars) {
		this.codoutrasgiars = codoutrasgiars;
	}

	@Column(name = "SEQINFCONSERVDOMEST", precision = 10, scale = 0)
	public Long getSeqinfconservdomest() {
		return this.seqinfconservdomest;
	}

	public void setSeqinfconservdomest(Long seqinfconservdomest) {
		this.seqinfconservdomest = seqinfconservdomest;
	}

	@Column(name = "INDETIQUETAANTIFURTO", length = 1)
	public String getIndetiquetaantifurto() {
		return this.indetiquetaantifurto;
	}

	public void setIndetiquetaantifurto(String indetiquetaantifurto) {
		this.indetiquetaantifurto = indetiquetaantifurto;
	}

	@Column(name = "SITPISCOFINSSIMPLES", length = 1)
	public String getSitpiscofinssimples() {
		return this.sitpiscofinssimples;
	}

	public void setSitpiscofinssimples(String sitpiscofinssimples) {
		this.sitpiscofinssimples = sitpiscofinssimples;
	}

	@Column(name = "INDEXIGEREQUISICAO", length = 1)
	public String getIndexigerequisicao() {
		return this.indexigerequisicao;
	}

	public void setIndexigerequisicao(String indexigerequisicao) {
		this.indexigerequisicao = indexigerequisicao;
	}

	@Column(name = "SITUACAONFIPI", length = 2)
	public String getSituacaonfipi() {
		return this.situacaonfipi;
	}

	public void setSituacaonfipi(String situacaonfipi) {
		this.situacaonfipi = situacaonfipi;
	}

	@Column(name = "SITUACAONFIPISAI", length = 2)
	public String getSituacaonfipisai() {
		return this.situacaonfipisai;
	}

	public void setSituacaonfipisai(String situacaonfipisai) {
		this.situacaonfipisai = situacaonfipisai;
	}

	@Column(name = "NRODIAGARANTIA", precision = 3, scale = 0)
	public Short getNrodiagarantia() {
		return this.nrodiagarantia;
	}

	public void setNrodiagarantia(Short nrodiagarantia) {
		this.nrodiagarantia = nrodiagarantia;
	}

	@Column(name = "INDTIPORECEITA", length = 1)
	public String getIndtiporeceita() {
		return this.indtiporeceita;
	}

	public void setIndtiporeceita(String indtiporeceita) {
		this.indtiporeceita = indtiporeceita;
	}

	@Column(name = "INDANTIMICROBIANO", length = 1)
	public String getIndantimicrobiano() {
		return this.indantimicrobiano;
	}

	public void setIndantimicrobiano(String indantimicrobiano) {
		this.indantimicrobiano = indantimicrobiano;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAHORALTERACARGAPDV", length = 7)
	public Date getDtahoralteracargapdv() {
		return this.dtahoralteracargapdv;
	}

	public void setDtahoralteracargapdv(Date dtahoralteracargapdv) {
		this.dtahoralteracargapdv = dtahoralteracargapdv;
	}

	@Column(name = "INDUSOPROLONGADO", length = 1)
	public String getIndusoprolongado() {
		return this.indusoprolongado;
	}

	public void setIndusoprolongado(String indusoprolongado) {
		this.indusoprolongado = indusoprolongado;
	}

	@Column(name = "UNIDADEMEDIDAMED", precision = 1, scale = 0)
	public Boolean getUnidademedidamed() {
		return this.unidademedidamed;
	}

	public void setUnidademedidamed(Boolean unidademedidamed) {
		this.unidademedidamed = unidademedidamed;
	}

	@Column(name = "INDFARMACIAPOPULAR", length = 1)
	public String getIndfarmaciapopular() {
		return this.indfarmaciapopular;
	}

	public void setIndfarmaciapopular(String indfarmaciapopular) {
		this.indfarmaciapopular = indfarmaciapopular;
	}

	@Column(name = "ALIQPADRAOICMSPROTEGE", precision = 4)
	public BigDecimal getAliqpadraoicmsprotege() {
		return this.aliqpadraoicmsprotege;
	}

	public void setAliqpadraoicmsprotege(BigDecimal aliqpadraoicmsprotege) {
		this.aliqpadraoicmsprotege = aliqpadraoicmsprotege;
	}

	@Column(name = "INDESCRITOUTROSEI", length = 1)
	public String getIndescritoutrosei() {
		return this.indescritoutrosei;
	}

	public void setIndescritoutrosei(String indescritoutrosei) {
		this.indescritoutrosei = indescritoutrosei;
	}

	@Column(name = "QTDPARCELAETIQUETA", precision = 3, scale = 0)
	public Short getQtdparcelaetiqueta() {
		return this.qtdparcelaetiqueta;
	}

	public void setQtdparcelaetiqueta(Short qtdparcelaetiqueta) {
		this.qtdparcelaetiqueta = qtdparcelaetiqueta;
	}

	@Column(name = "NRODIASGARANTIAFAB", precision = 4, scale = 0)
	public Short getNrodiasgarantiafab() {
		return this.nrodiasgarantiafab;
	}

	public void setNrodiasgarantiafab(Short nrodiasgarantiafab) {
		this.nrodiasgarantiafab = nrodiasgarantiafab;
	}

	@Column(name = "TIPOMODALGARANTIA", length = 1)
	public String getTipomodalgarantia() {
		return this.tipomodalgarantia;
	}

	public void setTipomodalgarantia(String tipomodalgarantia) {
		this.tipomodalgarantia = tipomodalgarantia;
	}

	@Column(name = "CODPRODDACAL", precision = 3, scale = 0)
	public Short getCodproddacal() {
		return this.codproddacal;
	}

	public void setCodproddacal(Short codproddacal) {
		this.codproddacal = codproddacal;
	}

	@Column(name = "CODNVE", length = 6)
	public String getCodnve() {
		return this.codnve;
	}

	public void setCodnve(String codnve) {
		this.codnve = codnve;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAHORINCLUSAOMSGCONT", length = 7)
	public Date getDtahorinclusaomsgcont() {
		return this.dtahorinclusaomsgcont;
	}

	public void setDtahorinclusaomsgcont(Date dtahorinclusaomsgcont) {
		this.dtahorinclusaomsgcont = dtahorinclusaomsgcont;
	}

	@Column(name = "USUARIOINCLUSAOMSGCONT", length = 12)
	public String getUsuarioinclusaomsgcont() {
		return this.usuarioinclusaomsgcont;
	}

	public void setUsuarioinclusaomsgcont(String usuarioinclusaomsgcont) {
		this.usuarioinclusaomsgcont = usuarioinclusaomsgcont;
	}

	@Column(name = "LEITURAMSGCONT", length = 1)
	public String getLeituramsgcont() {
		return this.leituramsgcont;
	}

	public void setLeituramsgcont(String leituramsgcont) {
		this.leituramsgcont = leituramsgcont;
	}

	@Column(name = "SEQTIPOFAMILIA", precision = 22, scale = 0)
	public BigDecimal getSeqtipofamilia() {
		return this.seqtipofamilia;
	}

	public void setSeqtipofamilia(BigDecimal seqtipofamilia) {
		this.seqtipofamilia = seqtipofamilia;
	}

	@Column(name = "SEQMARCAFAMILIA", precision = 22, scale = 0)
	public BigDecimal getSeqmarcafamilia() {
		return this.seqmarcafamilia;
	}

	public void setSeqmarcafamilia(BigDecimal seqmarcafamilia) {
		this.seqmarcafamilia = seqmarcafamilia;
	}

	@Column(name = "SEQCOMPLEMENTOFAMILIA", precision = 22, scale = 0)
	public BigDecimal getSeqcomplementofamilia() {
		return this.seqcomplementofamilia;
	}

	public void setSeqcomplementofamilia(BigDecimal seqcomplementofamilia) {
		this.seqcomplementofamilia = seqcomplementofamilia;
	}

	@Column(name = "SEQGRAMATURAFAMILIA", precision = 22, scale = 0)
	public BigDecimal getSeqgramaturafamilia() {
		return this.seqgramaturafamilia;
	}

	public void setSeqgramaturafamilia(BigDecimal seqgramaturafamilia) {
		this.seqgramaturafamilia = seqgramaturafamilia;
	}

	@Column(name = "INDPERMSUSPPISCOFINS", length = 1)
	public String getIndpermsusppiscofins() {
		return this.indpermsusppiscofins;
	}

	public void setIndpermsusppiscofins(String indpermsusppiscofins) {
		this.indpermsusppiscofins = indpermsusppiscofins;
	}

	@Column(name = "INDEXIGELOTE", length = 1)
	public String getIndexigelote() {
		return this.indexigelote;
	}

	public void setIndexigelote(String indexigelote) {
		this.indexigelote = indexigelote;
	}

	@Column(name = "CLASSEENQUADRAMENTOIPI", length = 5)
	public String getClasseenquadramentoipi() {
		return this.classeenquadramentoipi;
	}

	public void setClasseenquadramentoipi(String classeenquadramentoipi) {
		this.classeenquadramentoipi = classeenquadramentoipi;
	}

	@Column(name = "CODIGOSELOIPI", length = 60)
	public String getCodigoseloipi() {
		return this.codigoseloipi;
	}

	public void setCodigoseloipi(String codigoseloipi) {
		this.codigoseloipi = codigoseloipi;
	}

	@Column(name = "CODIGOENQUADRAMENTOIPI", length = 3)
	public String getCodigoenquadramentoipi() {
		return this.codigoenquadramentoipi;
	}

	public void setCodigoenquadramentoipi(String codigoenquadramentoipi) {
		this.codigoenquadramentoipi = codigoenquadramentoipi;
	}

	@Column(name = "GIARSTIPO", precision = 3, scale = 0)
	public Short getGiarstipo() {
		return this.giarstipo;
	}

	public void setGiarstipo(Short giarstipo) {
		this.giarstipo = giarstipo;
	}

	@Column(name = "GIARSCODIGO", precision = 3, scale = 0)
	public Short getGiarscodigo() {
		return this.giarscodigo;
	}

	public void setGiarscodigo(Short giarscodigo) {
		this.giarscodigo = giarscodigo;
	}

	@Column(name = "GIARSESPECIFICACAO", length = 60)
	public String getGiarsespecificacao() {
		return this.giarsespecificacao;
	}

	public void setGiarsespecificacao(String giarsespecificacao) {
		this.giarsespecificacao = giarsespecificacao;
	}

	@Column(name = "LITROSTABIPIPISCOFINS", precision = 12, scale = 4)
	public BigDecimal getLitrostabipipiscofins() {
		return this.litrostabipipiscofins;
	}

	public void setLitrostabipipiscofins(BigDecimal litrostabipipiscofins) {
		this.litrostabipipiscofins = litrostabipipiscofins;
	}

	@Column(name = "CODGIACREDITOPRESUMIDO", precision = 8, scale = 0)
	public Integer getCodgiacreditopresumido() {
		return this.codgiacreditopresumido;
	}

	public void setCodgiacreditopresumido(Integer codgiacreditopresumido) {
		this.codgiacreditopresumido = codgiacreditopresumido;
	}

	@Column(name = "CODGIATRANSFERENCIA", precision = 8, scale = 0)
	public Integer getCodgiatransferencia() {
		return this.codgiatransferencia;
	}

	public void setCodgiatransferencia(Integer codgiatransferencia) {
		this.codgiatransferencia = codgiatransferencia;
	}

	@Column(name = "CODGIARECEBIMENTO", precision = 8, scale = 0)
	public Integer getCodgiarecebimento() {
		return this.codgiarecebimento;
	}

	public void setCodgiarecebimento(Integer codgiarecebimento) {
		this.codgiarecebimento = codgiarecebimento;
	}

	@Column(name = "CODGIAOUTROSCREDITOS", precision = 8, scale = 0)
	public Integer getCodgiaoutroscreditos() {
		return this.codgiaoutroscreditos;
	}

	public void setCodgiaoutroscreditos(Integer codgiaoutroscreditos) {
		this.codgiaoutroscreditos = codgiaoutroscreditos;
	}

	@Column(name = "CODGIAOUTROSDEBITOS", precision = 8, scale = 0)
	public Integer getCodgiaoutrosdebitos() {
		return this.codgiaoutrosdebitos;
	}

	public void setCodgiaoutrosdebitos(Integer codgiaoutrosdebitos) {
		this.codgiaoutrosdebitos = codgiaoutrosdebitos;
	}

	@Column(name = "CODCEST", precision = 7, scale = 0)
	public Integer getCodcest() {
		return this.codcest;
	}

	public void setCodcest(Integer codcest) {
		this.codcest = codcest;
	}

	@Column(name = "QTDMAXTRANSFLOCAL", precision = 12, scale = 3)
	public BigDecimal getQtdmaxtransflocal() {
		return this.qtdmaxtransflocal;
	}

	public void setQtdmaxtransflocal(BigDecimal qtdmaxtransflocal) {
		this.qtdmaxtransflocal = qtdmaxtransflocal;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mapFamiliaEntity")
	public Set<MapEmbalagemEntity> getMapEmbalagemEntities() {
		return mapEmbalagemEntities;
	}
	public void setMapEmbalagemEntities(Set<MapEmbalagemEntity> mapEmbalagemEntities) {
		this.mapEmbalagemEntities = mapEmbalagemEntities;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mapFamiliaEntity")
	public Set<MapFamfornecEntity> getMapFamfornecEntities() {
		return mapFamfornecEntities;
	}
	public void setMapFamfornecEntities(Set<MapFamfornecEntity> mapFamfornecEntities) {
		this.mapFamfornecEntities = mapFamfornecEntities;
	}


}
