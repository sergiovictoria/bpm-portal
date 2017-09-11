package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapFamilia implements java.io.Serializable {

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
	
	private boolean vasilhame;
	private boolean permiteDecimais;
	private boolean bebidaAlcoolica;
	private boolean pesavelchk;
	private boolean permiteMultiplicacao;
	
	List<MafFornecedor> fornecedores = new ArrayList<MafFornecedor>();
	List<MapEmbalagem> embalagens = new ArrayList<MapEmbalagem>();

	public MapFamilia() {
	}

	public BigDecimal getSeqfamilia() {
		return seqfamilia;
	}

	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getPesavel() {
		return pesavel;
	}

	public void setPesavel(String pesavel) {
		this.pesavel = pesavel;
	}

	public String getIndisentopis() {
		return indisentopis;
	}

	public void setIndisentopis(String indisentopis) {
		this.indisentopis = indisentopis;
	}

	public BigDecimal getIndiceformbaseipi() {
		return indiceformbaseipi;
	}

	public void setIndiceformbaseipi(BigDecimal indiceformbaseipi) {
		this.indiceformbaseipi = indiceformbaseipi;
	}

	public BigDecimal getAliquotaipi() {
		return aliquotaipi;
	}

	public void setAliquotaipi(BigDecimal aliquotaipi) {
		this.aliquotaipi = aliquotaipi;
	}

	public String getPmtdecimal() {
		return pmtdecimal;
	}

	public void setPmtdecimal(String pmtdecimal) {
		this.pmtdecimal = pmtdecimal;
	}

	public String getPmtmultiplicacao() {
		return pmtmultiplicacao;
	}

	public void setPmtmultiplicacao(String pmtmultiplicacao) {
		this.pmtmultiplicacao = pmtmultiplicacao;
	}

	public String getIndvasilhame() {
		return indvasilhame;
	}

	public void setIndvasilhame(String indvasilhame) {
		this.indvasilhame = indvasilhame;
	}

	public String getArquivofigura() {
		return arquivofigura;
	}

	public void setArquivofigura(String arquivofigura) {
		this.arquivofigura = arquivofigura;
	}

	public Date getDtahorinclusao() {
		return dtahorinclusao;
	}

	public void setDtahorinclusao(Date dtahorinclusao) {
		this.dtahorinclusao = dtahorinclusao;
	}

	public String getUsuarioinclusao() {
		return usuarioinclusao;
	}

	public void setUsuarioinclusao(String usuarioinclusao) {
		this.usuarioinclusao = usuarioinclusao;
	}

	public Date getDtahoralteracao() {
		return dtahoralteracao;
	}

	public void setDtahoralteracao(Date dtahoralteracao) {
		this.dtahoralteracao = dtahoralteracao;
	}

	public String getUsuarioalteracao() {
		return usuarioalteracao;
	}

	public void setUsuarioalteracao(String usuarioalteracao) {
		this.usuarioalteracao = usuarioalteracao;
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

	public String getIndcalcipisaida() {
		return indcalcipisaida;
	}

	public void setIndcalcipisaida(String indcalcipisaida) {
		this.indcalcipisaida = indcalcipisaida;
	}

	public BigDecimal getPerbaseipi() {
		return perbaseipi;
	}

	public void setPerbaseipi(BigDecimal perbaseipi) {
		this.perbaseipi = perbaseipi;
	}

	public BigDecimal getPerisentoipi() {
		return perisentoipi;
	}

	public void setPerisentoipi(BigDecimal perisentoipi) {
		this.perisentoipi = perisentoipi;
	}

	public BigDecimal getPeroutroipi() {
		return peroutroipi;
	}

	public void setPeroutroipi(BigDecimal peroutroipi) {
		this.peroutroipi = peroutroipi;
	}

	public BigDecimal getPeraliquotaipi() {
		return peraliquotaipi;
	}

	public void setPeraliquotaipi(BigDecimal peraliquotaipi) {
		this.peraliquotaipi = peraliquotaipi;
	}

	public BigDecimal getVlripipauta() {
		return vlripipauta;
	}

	public void setVlripipauta(BigDecimal vlripipauta) {
		this.vlripipauta = vlripipauta;
	}

	public String getIndtribpiscofins() {
		return indtribpiscofins;
	}

	public void setIndtribpiscofins(String indtribpiscofins) {
		this.indtribpiscofins = indtribpiscofins;
	}

	public String getIndcontrolevda() {
		return indcontrolevda;
	}

	public void setIndcontrolevda(String indcontrolevda) {
		this.indcontrolevda = indcontrolevda;
	}

	public String getIndgenerico() {
		return indgenerico;
	}

	public void setIndgenerico(String indgenerico) {
		this.indgenerico = indgenerico;
	}

	public String getIndprecomonitorado() {
		return indprecomonitorado;
	}

	public void setIndprecomonitorado(String indprecomonitorado) {
		this.indprecomonitorado = indprecomonitorado;
	}

	public String getIndstpiscofins() {
		return indstpiscofins;
	}

	public void setIndstpiscofins(String indstpiscofins) {
		this.indstpiscofins = indstpiscofins;
	}

	public String getIndusaloteestoque() {
		return indusaloteestoque;
	}

	public void setIndusaloteestoque(String indusaloteestoque) {
		this.indusaloteestoque = indusaloteestoque;
	}

	public String getIndisentodebpiscofins() {
		return indisentodebpiscofins;
	}

	public void setIndisentodebpiscofins(String indisentodebpiscofins) {
		this.indisentodebpiscofins = indisentodebpiscofins;
	}

	public String getIndconfaz() {
		return indconfaz;
	}

	public void setIndconfaz(String indconfaz) {
		this.indconfaz = indconfaz;
	}

	public String getIndaliqzpiscofins() {
		return indaliqzpiscofins;
	}

	public void setIndaliqzpiscofins(String indaliqzpiscofins) {
		this.indaliqzpiscofins = indaliqzpiscofins;
	}

	public String getIndpermconvenio() {
		return indpermconvenio;
	}

	public void setIndpermconvenio(String indpermconvenio) {
		this.indpermconvenio = indpermconvenio;
	}

	public String getObsemergencial() {
		return obsemergencial;
	}

	public void setObsemergencial(String obsemergencial) {
		this.obsemergencial = obsemergencial;
	}

	public Short getCodtipi() {
		return codtipi;
	}

	public void setCodtipi(Short codtipi) {
		this.codtipi = codtipi;
	}

	public String getIndcomplvlrimp() {
		return indcomplvlrimp;
	}

	public void setIndcomplvlrimp(String indcomplvlrimp) {
		this.indcomplvlrimp = indcomplvlrimp;
	}

	public Byte getTiporeceita() {
		return tiporeceita;
	}

	public void setTiporeceita(Byte tiporeceita) {
		this.tiporeceita = tiporeceita;
	}

	public Short getCodservico() {
		return codservico;
	}

	public void setCodservico(Short codservico) {
		this.codservico = codservico;
	}

	public String getIndmonopiscofins() {
		return indmonopiscofins;
	}

	public void setIndmonopiscofins(String indmonopiscofins) {
		this.indmonopiscofins = indmonopiscofins;
	}

	public String getCodsefazcomblub() {
		return codsefazcomblub;
	}

	public void setCodsefazcomblub(String codsefazcomblub) {
		this.codsefazcomblub = codsefazcomblub;
	}

	public String getInddigcheckout() {
		return inddigcheckout;
	}

	public void setInddigcheckout(String inddigcheckout) {
		this.inddigcheckout = inddigcheckout;
	}

	public String getIndexiglicenca() {
		return indexiglicenca;
	}

	public void setIndexiglicenca(String indexiglicenca) {
		this.indexiglicenca = indexiglicenca;
	}

	public String getCodnbmsh() {
		return codnbmsh;
	}

	public void setCodnbmsh(String codnbmsh) {
		this.codnbmsh = codnbmsh;
	}

	public String getCompcestabasica() {
		return compcestabasica;
	}

	public void setCompcestabasica(String compcestabasica) {
		this.compcestabasica = compcestabasica;
	}

	public String getPapelimune() {
		return papelimune;
	}

	public void setPapelimune(String papelimune) {
		this.papelimune = papelimune;
	}

	public String getIndusafigura() {
		return indusafigura;
	}

	public void setIndusafigura(String indusafigura) {
		this.indusafigura = indusafigura;
	}

	public String getIndsimilar() {
		return indsimilar;
	}

	public void setIndsimilar(String indsimilar) {
		this.indsimilar = indsimilar;
	}

	public String getIndreceita() {
		return indreceita;
	}

	public void setIndreceita(String indreceita) {
		this.indreceita = indreceita;
	}

	public String getIndreceitacor() {
		return indreceitacor;
	}

	public void setIndreceitacor(String indreceitacor) {
		this.indreceitacor = indreceitacor;
	}

	public BigDecimal getSeqfamiliatmp() {
		return seqfamiliatmp;
	}

	public void setSeqfamiliatmp(BigDecimal seqfamiliatmp) {
		this.seqfamiliatmp = seqfamiliatmp;
	}

	public String getSituacaonfpis() {
		return situacaonfpis;
	}

	public void setSituacaonfpis(String situacaonfpis) {
		this.situacaonfpis = situacaonfpis;
	}

	public String getSituacaonfcofins() {
		return situacaonfcofins;
	}

	public void setSituacaonfcofins(String situacaonfcofins) {
		this.situacaonfcofins = situacaonfcofins;
	}

	public BigDecimal getPerbasepis() {
		return perbasepis;
	}

	public void setPerbasepis(BigDecimal perbasepis) {
		this.perbasepis = perbasepis;
	}

	public BigDecimal getPerbasecofins() {
		return perbasecofins;
	}

	public void setPerbasecofins(BigDecimal perbasecofins) {
		this.perbasecofins = perbasecofins;
	}

	public String getIndisentosuframa() {
		return indisentosuframa;
	}

	public void setIndisentosuframa(String indisentosuframa) {
		this.indisentosuframa = indisentosuframa;
	}

	public String getIndinconsistanvisa() {
		return indinconsistanvisa;
	}

	public void setIndinconsistanvisa(String indinconsistanvisa) {
		this.indinconsistanvisa = indinconsistanvisa;
	}

	public long getNrobaseexportacao() {
		return nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	public Short getNroempresaabastec() {
		return nroempresaabastec;
	}

	public void setNroempresaabastec(Short nroempresaabastec) {
		this.nroempresaabastec = nroempresaabastec;
	}

	public String getAtualizncmipi() {
		return atualizncmipi;
	}

	public void setAtualizncmipi(String atualizncmipi) {
		this.atualizncmipi = atualizncmipi;
	}

	public String getIndmedicamento() {
		return indmedicamento;
	}

	public void setIndmedicamento(String indmedicamento) {
		this.indmedicamento = indmedicamento;
	}

	public String getIndetico() {
		return indetico;
	}

	public void setIndetico(String indetico) {
		this.indetico = indetico;
	}

	public BigDecimal getAliqpadraoicms() {
		return aliqpadraoicms;
	}

	public void setAliqpadraoicms(BigDecimal aliqpadraoicms) {
		this.aliqpadraoicms = aliqpadraoicms;
	}

	public Integer getCodnatrec() {
		return codnatrec;
	}

	public void setCodnatrec(Integer codnatrec) {
		this.codnatrec = codnatrec;
	}

	public String getSituacaonfpissai() {
		return situacaonfpissai;
	}

	public void setSituacaonfpissai(String situacaonfpissai) {
		this.situacaonfpissai = situacaonfpissai;
	}

	public String getSituacaonfcofinssai() {
		return situacaonfcofinssai;
	}

	public void setSituacaonfcofinssai(String situacaonfcofinssai) {
		this.situacaonfcofinssai = situacaonfcofinssai;
	}

	public String getIndisentotsa() {
		return indisentotsa;
	}

	public void setIndisentotsa(String indisentotsa) {
		this.indisentotsa = indisentotsa;
	}

	public String getIndaceitavendafrac() {
		return indaceitavendafrac;
	}

	public void setIndaceitavendafrac(String indaceitavendafrac) {
		this.indaceitavendafrac = indaceitavendafrac;
	}

	public Integer getSeqcertificado() {
		return seqcertificado;
	}

	public void setSeqcertificado(Integer seqcertificado) {
		this.seqcertificado = seqcertificado;
	}

	public String getTextonfcomplemcertif() {
		return textonfcomplemcertif;
	}

	public void setTextonfcomplemcertif(String textonfcomplemcertif) {
		this.textonfcomplemcertif = textonfcomplemcertif;
	}

	public BigDecimal getSeqnatrec() {
		return seqnatrec;
	}

	public void setSeqnatrec(BigDecimal seqnatrec) {
		this.seqnatrec = seqnatrec;
	}

	public String getAtualizancmimpimport() {
		return atualizancmimpimport;
	}

	public void setAtualizancmimpimport(String atualizancmimpimport) {
		this.atualizancmimpimport = atualizancmimpimport;
	}

	public BigDecimal getPerimpostoimport() {
		return perimpostoimport;
	}

	public void setPerimpostoimport(BigDecimal perimpostoimport) {
		this.perimpostoimport = perimpostoimport;
	}

	public BigDecimal getPerredimpostoimport() {
		return perredimpostoimport;
	}

	public void setPerredimpostoimport(BigDecimal perredimpostoimport) {
		this.perredimpostoimport = perredimpostoimport;
	}

	public BigDecimal getPerredbaseipient() {
		return perredbaseipient;
	}

	public void setPerredbaseipient(BigDecimal perredbaseipient) {
		this.perredbaseipient = perredbaseipient;
	}

	public String getIndmedcontrolado() {
		return indmedcontrolado;
	}

	public void setIndmedcontrolado(String indmedcontrolado) {
		this.indmedcontrolado = indmedcontrolado;
	}

	public String getListamedcontrolado() {
		return listamedcontrolado;
	}

	public void setListamedcontrolado(String listamedcontrolado) {
		this.listamedcontrolado = listamedcontrolado;
	}

	public String getCodmedcontrolado() {
		return codmedcontrolado;
	}

	public void setCodmedcontrolado(String codmedcontrolado) {
		this.codmedcontrolado = codmedcontrolado;
	}

	public Short getSubitemcodservico() {
		return subitemcodservico;
	}

	public void setSubitemcodservico(Short subitemcodservico) {
		this.subitemcodservico = subitemcodservico;
	}

	public String getIndbebidaalcoolica() {
		return indbebidaalcoolica;
	}

	public void setIndbebidaalcoolica(String indbebidaalcoolica) {
		this.indbebidaalcoolica = indbebidaalcoolica;
	}

	public Short getCodisentagiars() {
		return codisentagiars;
	}

	public void setCodisentagiars(Short codisentagiars) {
		this.codisentagiars = codisentagiars;
	}

	public Short getCodoutrasgiars() {
		return codoutrasgiars;
	}

	public void setCodoutrasgiars(Short codoutrasgiars) {
		this.codoutrasgiars = codoutrasgiars;
	}

	public Long getSeqinfconservdomest() {
		return seqinfconservdomest;
	}

	public void setSeqinfconservdomest(Long seqinfconservdomest) {
		this.seqinfconservdomest = seqinfconservdomest;
	}

	public String getIndetiquetaantifurto() {
		return indetiquetaantifurto;
	}

	public void setIndetiquetaantifurto(String indetiquetaantifurto) {
		this.indetiquetaantifurto = indetiquetaantifurto;
	}

	public String getSitpiscofinssimples() {
		return sitpiscofinssimples;
	}

	public void setSitpiscofinssimples(String sitpiscofinssimples) {
		this.sitpiscofinssimples = sitpiscofinssimples;
	}

	public String getIndexigerequisicao() {
		return indexigerequisicao;
	}

	public void setIndexigerequisicao(String indexigerequisicao) {
		this.indexigerequisicao = indexigerequisicao;
	}

	public String getSituacaonfipi() {
		return situacaonfipi;
	}

	public void setSituacaonfipi(String situacaonfipi) {
		this.situacaonfipi = situacaonfipi;
	}

	public String getSituacaonfipisai() {
		return situacaonfipisai;
	}

	public void setSituacaonfipisai(String situacaonfipisai) {
		this.situacaonfipisai = situacaonfipisai;
	}

	public Short getNrodiagarantia() {
		return nrodiagarantia;
	}

	public void setNrodiagarantia(Short nrodiagarantia) {
		this.nrodiagarantia = nrodiagarantia;
	}

	public String getIndtiporeceita() {
		return indtiporeceita;
	}

	public void setIndtiporeceita(String indtiporeceita) {
		this.indtiporeceita = indtiporeceita;
	}

	public String getIndantimicrobiano() {
		return indantimicrobiano;
	}

	public void setIndantimicrobiano(String indantimicrobiano) {
		this.indantimicrobiano = indantimicrobiano;
	}

	public Date getDtahoralteracargapdv() {
		return dtahoralteracargapdv;
	}

	public void setDtahoralteracargapdv(Date dtahoralteracargapdv) {
		this.dtahoralteracargapdv = dtahoralteracargapdv;
	}

	public String getIndusoprolongado() {
		return indusoprolongado;
	}

	public void setIndusoprolongado(String indusoprolongado) {
		this.indusoprolongado = indusoprolongado;
	}

	public Boolean getUnidademedidamed() {
		return unidademedidamed;
	}

	public void setUnidademedidamed(Boolean unidademedidamed) {
		this.unidademedidamed = unidademedidamed;
	}

	public String getIndfarmaciapopular() {
		return indfarmaciapopular;
	}

	public void setIndfarmaciapopular(String indfarmaciapopular) {
		this.indfarmaciapopular = indfarmaciapopular;
	}

	public BigDecimal getAliqpadraoicmsprotege() {
		return aliqpadraoicmsprotege;
	}

	public void setAliqpadraoicmsprotege(BigDecimal aliqpadraoicmsprotege) {
		this.aliqpadraoicmsprotege = aliqpadraoicmsprotege;
	}

	public String getIndescritoutrosei() {
		return indescritoutrosei;
	}

	public void setIndescritoutrosei(String indescritoutrosei) {
		this.indescritoutrosei = indescritoutrosei;
	}

	public Short getQtdparcelaetiqueta() {
		return qtdparcelaetiqueta;
	}

	public void setQtdparcelaetiqueta(Short qtdparcelaetiqueta) {
		this.qtdparcelaetiqueta = qtdparcelaetiqueta;
	}

	public Short getNrodiasgarantiafab() {
		return nrodiasgarantiafab;
	}

	public void setNrodiasgarantiafab(Short nrodiasgarantiafab) {
		this.nrodiasgarantiafab = nrodiasgarantiafab;
	}

	public String getTipomodalgarantia() {
		return tipomodalgarantia;
	}

	public void setTipomodalgarantia(String tipomodalgarantia) {
		this.tipomodalgarantia = tipomodalgarantia;
	}

	public Short getCodproddacal() {
		return codproddacal;
	}

	public void setCodproddacal(Short codproddacal) {
		this.codproddacal = codproddacal;
	}

	public String getCodnve() {
		return codnve;
	}

	public void setCodnve(String codnve) {
		this.codnve = codnve;
	}

	public Date getDtahorinclusaomsgcont() {
		return dtahorinclusaomsgcont;
	}

	public void setDtahorinclusaomsgcont(Date dtahorinclusaomsgcont) {
		this.dtahorinclusaomsgcont = dtahorinclusaomsgcont;
	}

	public String getUsuarioinclusaomsgcont() {
		return usuarioinclusaomsgcont;
	}

	public void setUsuarioinclusaomsgcont(String usuarioinclusaomsgcont) {
		this.usuarioinclusaomsgcont = usuarioinclusaomsgcont;
	}

	public String getLeituramsgcont() {
		return leituramsgcont;
	}

	public void setLeituramsgcont(String leituramsgcont) {
		this.leituramsgcont = leituramsgcont;
	}

	public BigDecimal getSeqtipofamilia() {
		return seqtipofamilia;
	}

	public void setSeqtipofamilia(BigDecimal seqtipofamilia) {
		this.seqtipofamilia = seqtipofamilia;
	}

	public BigDecimal getSeqmarcafamilia() {
		return seqmarcafamilia;
	}

	public void setSeqmarcafamilia(BigDecimal seqmarcafamilia) {
		this.seqmarcafamilia = seqmarcafamilia;
	}

	public BigDecimal getSeqcomplementofamilia() {
		return seqcomplementofamilia;
	}

	public void setSeqcomplementofamilia(BigDecimal seqcomplementofamilia) {
		this.seqcomplementofamilia = seqcomplementofamilia;
	}

	public BigDecimal getSeqgramaturafamilia() {
		return seqgramaturafamilia;
	}

	public void setSeqgramaturafamilia(BigDecimal seqgramaturafamilia) {
		this.seqgramaturafamilia = seqgramaturafamilia;
	}

	public String getIndpermsusppiscofins() {
		return indpermsusppiscofins;
	}

	public void setIndpermsusppiscofins(String indpermsusppiscofins) {
		this.indpermsusppiscofins = indpermsusppiscofins;
	}

	public String getIndexigelote() {
		return indexigelote;
	}

	public void setIndexigelote(String indexigelote) {
		this.indexigelote = indexigelote;
	}

	public String getClasseenquadramentoipi() {
		return classeenquadramentoipi;
	}

	public void setClasseenquadramentoipi(String classeenquadramentoipi) {
		this.classeenquadramentoipi = classeenquadramentoipi;
	}

	public String getCodigoseloipi() {
		return codigoseloipi;
	}

	public void setCodigoseloipi(String codigoseloipi) {
		this.codigoseloipi = codigoseloipi;
	}

	public String getCodigoenquadramentoipi() {
		return codigoenquadramentoipi;
	}

	public void setCodigoenquadramentoipi(String codigoenquadramentoipi) {
		this.codigoenquadramentoipi = codigoenquadramentoipi;
	}

	public Short getGiarstipo() {
		return giarstipo;
	}

	public void setGiarstipo(Short giarstipo) {
		this.giarstipo = giarstipo;
	}

	public Short getGiarscodigo() {
		return giarscodigo;
	}

	public void setGiarscodigo(Short giarscodigo) {
		this.giarscodigo = giarscodigo;
	}

	public String getGiarsespecificacao() {
		return giarsespecificacao;
	}

	public void setGiarsespecificacao(String giarsespecificacao) {
		this.giarsespecificacao = giarsespecificacao;
	}

	public BigDecimal getLitrostabipipiscofins() {
		return litrostabipipiscofins;
	}

	public void setLitrostabipipiscofins(BigDecimal litrostabipipiscofins) {
		this.litrostabipipiscofins = litrostabipipiscofins;
	}

	public Integer getCodgiacreditopresumido() {
		return codgiacreditopresumido;
	}

	public void setCodgiacreditopresumido(Integer codgiacreditopresumido) {
		this.codgiacreditopresumido = codgiacreditopresumido;
	}

	public Integer getCodgiatransferencia() {
		return codgiatransferencia;
	}

	public void setCodgiatransferencia(Integer codgiatransferencia) {
		this.codgiatransferencia = codgiatransferencia;
	}

	public Integer getCodgiarecebimento() {
		return codgiarecebimento;
	}

	public void setCodgiarecebimento(Integer codgiarecebimento) {
		this.codgiarecebimento = codgiarecebimento;
	}

	public Integer getCodgiaoutroscreditos() {
		return codgiaoutroscreditos;
	}

	public void setCodgiaoutroscreditos(Integer codgiaoutroscreditos) {
		this.codgiaoutroscreditos = codgiaoutroscreditos;
	}

	public Integer getCodgiaoutrosdebitos() {
		return codgiaoutrosdebitos;
	}

	public void setCodgiaoutrosdebitos(Integer codgiaoutrosdebitos) {
		this.codgiaoutrosdebitos = codgiaoutrosdebitos;
	}

	public Integer getCodcest() {
		return codcest;
	}

	public void setCodcest(Integer codcest) {
		this.codcest = codcest;
	}

	public BigDecimal getQtdmaxtransflocal() {
		return qtdmaxtransflocal;
	}

	public void setQtdmaxtransflocal(BigDecimal qtdmaxtransflocal) {
		this.qtdmaxtransflocal = qtdmaxtransflocal;
	}

	public boolean isVasilhame() {
		return vasilhame;
	}

	public void setVasilhame(boolean vasilhame) {
		this.vasilhame = vasilhame;
	}

	public boolean isPermiteDecimais() {
		return permiteDecimais;
	}

	public void setPermiteDecimais(boolean permiteDecimais) {
		this.permiteDecimais = permiteDecimais;
	}

	public boolean isBebidaAlcoolica() {
		return bebidaAlcoolica;
	}

	public void setBebidaAlcoolica(boolean bebidaAlcoolica) {
		this.bebidaAlcoolica = bebidaAlcoolica;
	}

	public boolean isPesavelchk() {
		return pesavelchk;
	}

	public void setPesavelchk(boolean pesavelchk) {
		this.pesavelchk = pesavelchk;
	}

	public boolean isPermiteMultiplicacao() {
		return permiteMultiplicacao;
	}

	public void setPermiteMultiplicacao(boolean permiteMultiplicacao) {
		this.permiteMultiplicacao = permiteMultiplicacao;
	}

	public List<MafFornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<MafFornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<MapEmbalagem> getEmbalagens() {
		return embalagens;
	}

	public void setEmbalagens(List<MapEmbalagem> embalagens) {
		this.embalagens = embalagens;
	}
}
