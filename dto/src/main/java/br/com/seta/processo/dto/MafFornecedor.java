package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MafFornecedor implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private BigDecimal seqfornecedor;
	private String tipfornecedor;
	private String microempresa;
	private String condicaofrete;
	private Long codigoean;
	private String solprorrogvencto;
	private Byte toleranctranspdias;
	private String statusgeral;
	private String indavalcadastro;
	private String indreplicacao;
	private String indgeroureplicacao;
	private Date dtaalteracao;
	private String usualteracao;
	private String nomecontato;
	private String emailcontato;
	private String fonecontato;
	private String faxcontato;
	private String indgeracottransf;
	private String observacao;
	private String indsusppiscofins;
	private BigDecimal pzomedretirarqedi;
	private BigDecimal pzomaxretirarqedi;
	private BigDecimal pzomedretirarqedinf;
	private String emailpzomedretirarq;
	private String emailpzomaxretirarq;
	private String emailpzomedretirarqnf;
	private String nomecontatoemailped;
	private String emailpedcompra;
	private String agendaSeg;
	private String agendaTer;
	private String agendaQua;
	private String agendaQui;
	private String agendaSex;
	private String agendaSab;
	private String agendaDom;
	private String agendaPeriodicidade;
	private Short agendaDiasIntervalo;
	private BigDecimal agendaHoraVisita;
	private String modeloafvfornec;
	private String indproduzmarcapropriaemp;
	private String indmedicamento;
	private String indmedidametadistrib;
	private String indexibefornecedipedido;
	private long nrobaseexportacao;
	private String nomecontatoemailage;
	private String emailagefornec;
	private String nomecontatoemaildev;
	private String emaildevfornec;
	private String indrecebvencvlrliq;
	private String indequipindustriatransf;
	private String indcodbuscaprod;
	private String nomecontatotitulo;
	private String emailtitulo;
	private String nomecontatolancpromo;
	private String emaillancpromo;
	private String indpermdescvenda;
	private String indbloqdtavencped;
	private Date dtahorinclusaomsgcont;
	private String usuarioinclusaomsgcont;
	private String leituramsgcont;
	private String nomecontatoemailacordo;
	private String emailacordo;
	private String indparceiropromoc;
	private String indexigecontratops;
	private String indipiequiparaindustria;
	private String emailregdevfornec;
	private String nomecontatoemailregdev;
	private String indagendacomprador;
	
	private boolean princicalchk;
	
	private GePessoa gePessoa;
	private MapFamfornec mapFamfornec;

	public MafFornecedor() {
	}

	public BigDecimal getSeqfornecedor() {
		return seqfornecedor;
	}

	public void setSeqfornecedor(BigDecimal seqfornecedor) {
		this.seqfornecedor = seqfornecedor;
	}

	public String getTipfornecedor() {
		return tipfornecedor;
	}

	public void setTipfornecedor(String tipfornecedor) {
		this.tipfornecedor = tipfornecedor;
	}

	public String getMicroempresa() {
		return microempresa;
	}

	public void setMicroempresa(String microempresa) {
		this.microempresa = microempresa;
	}

	public String getCondicaofrete() {
		return condicaofrete;
	}

	public void setCondicaofrete(String condicaofrete) {
		this.condicaofrete = condicaofrete;
	}

	public Long getCodigoean() {
		return codigoean;
	}

	public void setCodigoean(Long codigoean) {
		this.codigoean = codigoean;
	}

	public String getSolprorrogvencto() {
		return solprorrogvencto;
	}

	public void setSolprorrogvencto(String solprorrogvencto) {
		this.solprorrogvencto = solprorrogvencto;
	}

	public Byte getToleranctranspdias() {
		return toleranctranspdias;
	}

	public void setToleranctranspdias(Byte toleranctranspdias) {
		this.toleranctranspdias = toleranctranspdias;
	}

	public String getStatusgeral() {
		return statusgeral;
	}

	public void setStatusgeral(String statusgeral) {
		this.statusgeral = statusgeral;
	}

	public String getIndavalcadastro() {
		return indavalcadastro;
	}

	public void setIndavalcadastro(String indavalcadastro) {
		this.indavalcadastro = indavalcadastro;
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

	public Date getDtaalteracao() {
		return dtaalteracao;
	}

	public void setDtaalteracao(Date dtaalteracao) {
		this.dtaalteracao = dtaalteracao;
	}

	public String getUsualteracao() {
		return usualteracao;
	}

	public void setUsualteracao(String usualteracao) {
		this.usualteracao = usualteracao;
	}

	public String getNomecontato() {
		return nomecontato;
	}

	public void setNomecontato(String nomecontato) {
		this.nomecontato = nomecontato;
	}

	public String getEmailcontato() {
		return emailcontato;
	}

	public void setEmailcontato(String emailcontato) {
		this.emailcontato = emailcontato;
	}

	public String getFonecontato() {
		return fonecontato;
	}

	public void setFonecontato(String fonecontato) {
		this.fonecontato = fonecontato;
	}

	public String getFaxcontato() {
		return faxcontato;
	}

	public void setFaxcontato(String faxcontato) {
		this.faxcontato = faxcontato;
	}

	public String getIndgeracottransf() {
		return indgeracottransf;
	}

	public void setIndgeracottransf(String indgeracottransf) {
		this.indgeracottransf = indgeracottransf;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getIndsusppiscofins() {
		return indsusppiscofins;
	}

	public void setIndsusppiscofins(String indsusppiscofins) {
		this.indsusppiscofins = indsusppiscofins;
	}

	public BigDecimal getPzomedretirarqedi() {
		return pzomedretirarqedi;
	}

	public void setPzomedretirarqedi(BigDecimal pzomedretirarqedi) {
		this.pzomedretirarqedi = pzomedretirarqedi;
	}

	public BigDecimal getPzomaxretirarqedi() {
		return pzomaxretirarqedi;
	}

	public void setPzomaxretirarqedi(BigDecimal pzomaxretirarqedi) {
		this.pzomaxretirarqedi = pzomaxretirarqedi;
	}

	public BigDecimal getPzomedretirarqedinf() {
		return pzomedretirarqedinf;
	}

	public void setPzomedretirarqedinf(BigDecimal pzomedretirarqedinf) {
		this.pzomedretirarqedinf = pzomedretirarqedinf;
	}

	public String getEmailpzomedretirarq() {
		return emailpzomedretirarq;
	}

	public void setEmailpzomedretirarq(String emailpzomedretirarq) {
		this.emailpzomedretirarq = emailpzomedretirarq;
	}

	public String getEmailpzomaxretirarq() {
		return emailpzomaxretirarq;
	}

	public void setEmailpzomaxretirarq(String emailpzomaxretirarq) {
		this.emailpzomaxretirarq = emailpzomaxretirarq;
	}

	public String getEmailpzomedretirarqnf() {
		return emailpzomedretirarqnf;
	}

	public void setEmailpzomedretirarqnf(String emailpzomedretirarqnf) {
		this.emailpzomedretirarqnf = emailpzomedretirarqnf;
	}

	public String getNomecontatoemailped() {
		return nomecontatoemailped;
	}

	public void setNomecontatoemailped(String nomecontatoemailped) {
		this.nomecontatoemailped = nomecontatoemailped;
	}

	public String getEmailpedcompra() {
		return emailpedcompra;
	}

	public void setEmailpedcompra(String emailpedcompra) {
		this.emailpedcompra = emailpedcompra;
	}

	public String getAgendaSeg() {
		return agendaSeg;
	}

	public void setAgendaSeg(String agendaSeg) {
		this.agendaSeg = agendaSeg;
	}

	public String getAgendaTer() {
		return agendaTer;
	}

	public void setAgendaTer(String agendaTer) {
		this.agendaTer = agendaTer;
	}

	public String getAgendaQua() {
		return agendaQua;
	}

	public void setAgendaQua(String agendaQua) {
		this.agendaQua = agendaQua;
	}

	public String getAgendaQui() {
		return agendaQui;
	}

	public void setAgendaQui(String agendaQui) {
		this.agendaQui = agendaQui;
	}

	public String getAgendaSex() {
		return agendaSex;
	}

	public void setAgendaSex(String agendaSex) {
		this.agendaSex = agendaSex;
	}

	public String getAgendaSab() {
		return agendaSab;
	}

	public void setAgendaSab(String agendaSab) {
		this.agendaSab = agendaSab;
	}

	public String getAgendaDom() {
		return agendaDom;
	}

	public void setAgendaDom(String agendaDom) {
		this.agendaDom = agendaDom;
	}

	public String getAgendaPeriodicidade() {
		return agendaPeriodicidade;
	}

	public void setAgendaPeriodicidade(String agendaPeriodicidade) {
		this.agendaPeriodicidade = agendaPeriodicidade;
	}

	public Short getAgendaDiasIntervalo() {
		return agendaDiasIntervalo;
	}

	public void setAgendaDiasIntervalo(Short agendaDiasIntervalo) {
		this.agendaDiasIntervalo = agendaDiasIntervalo;
	}

	public BigDecimal getAgendaHoraVisita() {
		return agendaHoraVisita;
	}

	public void setAgendaHoraVisita(BigDecimal agendaHoraVisita) {
		this.agendaHoraVisita = agendaHoraVisita;
	}

	public String getModeloafvfornec() {
		return modeloafvfornec;
	}

	public void setModeloafvfornec(String modeloafvfornec) {
		this.modeloafvfornec = modeloafvfornec;
	}

	public String getIndproduzmarcapropriaemp() {
		return indproduzmarcapropriaemp;
	}

	public void setIndproduzmarcapropriaemp(String indproduzmarcapropriaemp) {
		this.indproduzmarcapropriaemp = indproduzmarcapropriaemp;
	}

	public String getIndmedicamento() {
		return indmedicamento;
	}

	public void setIndmedicamento(String indmedicamento) {
		this.indmedicamento = indmedicamento;
	}

	public String getIndmedidametadistrib() {
		return indmedidametadistrib;
	}

	public void setIndmedidametadistrib(String indmedidametadistrib) {
		this.indmedidametadistrib = indmedidametadistrib;
	}

	public String getIndexibefornecedipedido() {
		return indexibefornecedipedido;
	}

	public void setIndexibefornecedipedido(String indexibefornecedipedido) {
		this.indexibefornecedipedido = indexibefornecedipedido;
	}

	public long getNrobaseexportacao() {
		return nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	public String getNomecontatoemailage() {
		return nomecontatoemailage;
	}

	public void setNomecontatoemailage(String nomecontatoemailage) {
		this.nomecontatoemailage = nomecontatoemailage;
	}

	public String getEmailagefornec() {
		return emailagefornec;
	}

	public void setEmailagefornec(String emailagefornec) {
		this.emailagefornec = emailagefornec;
	}

	public String getNomecontatoemaildev() {
		return nomecontatoemaildev;
	}

	public void setNomecontatoemaildev(String nomecontatoemaildev) {
		this.nomecontatoemaildev = nomecontatoemaildev;
	}

	public String getEmaildevfornec() {
		return emaildevfornec;
	}

	public void setEmaildevfornec(String emaildevfornec) {
		this.emaildevfornec = emaildevfornec;
	}

	public String getIndrecebvencvlrliq() {
		return indrecebvencvlrliq;
	}

	public void setIndrecebvencvlrliq(String indrecebvencvlrliq) {
		this.indrecebvencvlrliq = indrecebvencvlrliq;
	}

	public String getIndequipindustriatransf() {
		return indequipindustriatransf;
	}

	public void setIndequipindustriatransf(String indequipindustriatransf) {
		this.indequipindustriatransf = indequipindustriatransf;
	}

	public String getIndcodbuscaprod() {
		return indcodbuscaprod;
	}

	public void setIndcodbuscaprod(String indcodbuscaprod) {
		this.indcodbuscaprod = indcodbuscaprod;
	}

	public String getNomecontatotitulo() {
		return nomecontatotitulo;
	}

	public void setNomecontatotitulo(String nomecontatotitulo) {
		this.nomecontatotitulo = nomecontatotitulo;
	}

	public String getEmailtitulo() {
		return emailtitulo;
	}

	public void setEmailtitulo(String emailtitulo) {
		this.emailtitulo = emailtitulo;
	}

	public String getNomecontatolancpromo() {
		return nomecontatolancpromo;
	}

	public void setNomecontatolancpromo(String nomecontatolancpromo) {
		this.nomecontatolancpromo = nomecontatolancpromo;
	}

	public String getEmaillancpromo() {
		return emaillancpromo;
	}

	public void setEmaillancpromo(String emaillancpromo) {
		this.emaillancpromo = emaillancpromo;
	}

	public String getIndpermdescvenda() {
		return indpermdescvenda;
	}

	public void setIndpermdescvenda(String indpermdescvenda) {
		this.indpermdescvenda = indpermdescvenda;
	}

	public String getIndbloqdtavencped() {
		return indbloqdtavencped;
	}

	public void setIndbloqdtavencped(String indbloqdtavencped) {
		this.indbloqdtavencped = indbloqdtavencped;
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

	public String getNomecontatoemailacordo() {
		return nomecontatoemailacordo;
	}

	public void setNomecontatoemailacordo(String nomecontatoemailacordo) {
		this.nomecontatoemailacordo = nomecontatoemailacordo;
	}

	public String getEmailacordo() {
		return emailacordo;
	}

	public void setEmailacordo(String emailacordo) {
		this.emailacordo = emailacordo;
	}

	public String getIndparceiropromoc() {
		return indparceiropromoc;
	}

	public void setIndparceiropromoc(String indparceiropromoc) {
		this.indparceiropromoc = indparceiropromoc;
	}

	public String getIndexigecontratops() {
		return indexigecontratops;
	}

	public void setIndexigecontratops(String indexigecontratops) {
		this.indexigecontratops = indexigecontratops;
	}

	public String getIndipiequiparaindustria() {
		return indipiequiparaindustria;
	}

	public void setIndipiequiparaindustria(String indipiequiparaindustria) {
		this.indipiequiparaindustria = indipiequiparaindustria;
	}

	public String getEmailregdevfornec() {
		return emailregdevfornec;
	}

	public void setEmailregdevfornec(String emailregdevfornec) {
		this.emailregdevfornec = emailregdevfornec;
	}

	public String getNomecontatoemailregdev() {
		return nomecontatoemailregdev;
	}

	public void setNomecontatoemailregdev(String nomecontatoemailregdev) {
		this.nomecontatoemailregdev = nomecontatoemailregdev;
	}

	public String getIndagendacomprador() {
		return indagendacomprador;
	}

	public void setIndagendacomprador(String indagendacomprador) {
		this.indagendacomprador = indagendacomprador;
	}

	public GePessoa getGePessoa() {
		return gePessoa;
	}

	public void setGePessoa(GePessoa gePessoa) {
		this.gePessoa = gePessoa;
	}

	public MapFamfornec getMapFamfornec() {
		return mapFamfornec;
	}

	public void setMapFamfornec(MapFamfornec mapFamfornec) {
		this.mapFamfornec = mapFamfornec;
	}

	public boolean isPrincicalchk() {
		return princicalchk;
	}

	public void setPrincicalchk(boolean princicalchk) {
		this.princicalchk = princicalchk;
	}
	
}
