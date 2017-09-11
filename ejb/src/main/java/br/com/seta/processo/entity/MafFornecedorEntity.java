package br.com.seta.processo.entity;



import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "MAF_FORNECEDOR")
@Cacheable
public class MafFornecedorEntity implements java.io.Serializable {


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

	public MafFornecedorEntity() {
	}

	
	@Id
	@Column(name = "SEQFORNECEDOR", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqfornecedor() {
		return this.seqfornecedor;
	}

	public void setSeqfornecedor(BigDecimal seqfornecedor) {
		this.seqfornecedor = seqfornecedor;
	}

	@Column(name = "TIPFORNECEDOR", nullable = false, length = 1)
	public String getTipfornecedor() {
		return this.tipfornecedor;
	}

	public void setTipfornecedor(String tipfornecedor) {
		this.tipfornecedor = tipfornecedor;
	}

	@Column(name = "MICROEMPRESA", nullable = false, length = 1)
	public String getMicroempresa() {
		return this.microempresa;
	}

	public void setMicroempresa(String microempresa) {
		this.microempresa = microempresa;
	}

	@Column(name = "CONDICAOFRETE", nullable = false, length = 1)
	public String getCondicaofrete() {
		return this.condicaofrete;
	}

	public void setCondicaofrete(String condicaofrete) {
		this.condicaofrete = condicaofrete;
	}

	@Column(name = "CODIGOEAN", precision = 13, scale = 0)
	public Long getCodigoean() {
		return this.codigoean;
	}

	public void setCodigoean(Long codigoean) {
		this.codigoean = codigoean;
	}

	@Column(name = "SOLPRORROGVENCTO", nullable = false, length = 1)
	public String getSolprorrogvencto() {
		return this.solprorrogvencto;
	}

	public void setSolprorrogvencto(String solprorrogvencto) {
		this.solprorrogvencto = solprorrogvencto;
	}

	@Column(name = "TOLERANCTRANSPDIAS", precision = 2, scale = 0)
	public Byte getToleranctranspdias() {
		return this.toleranctranspdias;
	}

	public void setToleranctranspdias(Byte toleranctranspdias) {
		this.toleranctranspdias = toleranctranspdias;
	}

	@Column(name = "STATUSGERAL", nullable = false, length = 1)
	public String getStatusgeral() {
		return this.statusgeral;
	}

	public void setStatusgeral(String statusgeral) {
		this.statusgeral = statusgeral;
	}

	@Column(name = "INDAVALCADASTRO", length = 1)
	public String getIndavalcadastro() {
		return this.indavalcadastro;
	}

	public void setIndavalcadastro(String indavalcadastro) {
		this.indavalcadastro = indavalcadastro;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAALTERACAO", length = 7)
	public Date getDtaalteracao() {
		return this.dtaalteracao;
	}

	public void setDtaalteracao(Date dtaalteracao) {
		this.dtaalteracao = dtaalteracao;
	}

	@Column(name = "USUALTERACAO", length = 12)
	public String getUsualteracao() {
		return this.usualteracao;
	}

	public void setUsualteracao(String usualteracao) {
		this.usualteracao = usualteracao;
	}

	@Column(name = "NOMECONTATO", length = 40)
	public String getNomecontato() {
		return this.nomecontato;
	}

	public void setNomecontato(String nomecontato) {
		this.nomecontato = nomecontato;
	}

	@Column(name = "EMAILCONTATO", length = 40)
	public String getEmailcontato() {
		return this.emailcontato;
	}

	public void setEmailcontato(String emailcontato) {
		this.emailcontato = emailcontato;
	}

	@Column(name = "FONECONTATO", length = 40)
	public String getFonecontato() {
		return this.fonecontato;
	}

	public void setFonecontato(String fonecontato) {
		this.fonecontato = fonecontato;
	}

	@Column(name = "FAXCONTATO", length = 40)
	public String getFaxcontato() {
		return this.faxcontato;
	}

	public void setFaxcontato(String faxcontato) {
		this.faxcontato = faxcontato;
	}

	@Column(name = "INDGERACOTTRANSF", length = 1)
	public String getIndgeracottransf() {
		return this.indgeracottransf;
	}

	public void setIndgeracottransf(String indgeracottransf) {
		this.indgeracottransf = indgeracottransf;
	}

	@Column(name = "OBSERVACAO", length = 250)
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "INDSUSPPISCOFINS", length = 1)
	public String getIndsusppiscofins() {
		return this.indsusppiscofins;
	}

	public void setIndsusppiscofins(String indsusppiscofins) {
		this.indsusppiscofins = indsusppiscofins;
	}

	@Column(name = "PZOMEDRETIRARQEDI", precision = 22, scale = 0)
	public BigDecimal getPzomedretirarqedi() {
		return this.pzomedretirarqedi;
	}

	public void setPzomedretirarqedi(BigDecimal pzomedretirarqedi) {
		this.pzomedretirarqedi = pzomedretirarqedi;
	}

	@Column(name = "PZOMAXRETIRARQEDI", precision = 22, scale = 0)
	public BigDecimal getPzomaxretirarqedi() {
		return this.pzomaxretirarqedi;
	}

	public void setPzomaxretirarqedi(BigDecimal pzomaxretirarqedi) {
		this.pzomaxretirarqedi = pzomaxretirarqedi;
	}

	@Column(name = "PZOMEDRETIRARQEDINF", precision = 22, scale = 0)
	public BigDecimal getPzomedretirarqedinf() {
		return this.pzomedretirarqedinf;
	}

	public void setPzomedretirarqedinf(BigDecimal pzomedretirarqedinf) {
		this.pzomedretirarqedinf = pzomedretirarqedinf;
	}

	@Column(name = "EMAILPZOMEDRETIRARQ", length = 250)
	public String getEmailpzomedretirarq() {
		return this.emailpzomedretirarq;
	}

	public void setEmailpzomedretirarq(String emailpzomedretirarq) {
		this.emailpzomedretirarq = emailpzomedretirarq;
	}

	@Column(name = "EMAILPZOMAXRETIRARQ", length = 250)
	public String getEmailpzomaxretirarq() {
		return this.emailpzomaxretirarq;
	}

	public void setEmailpzomaxretirarq(String emailpzomaxretirarq) {
		this.emailpzomaxretirarq = emailpzomaxretirarq;
	}

	@Column(name = "EMAILPZOMEDRETIRARQNF", length = 250)
	public String getEmailpzomedretirarqnf() {
		return this.emailpzomedretirarqnf;
	}

	public void setEmailpzomedretirarqnf(String emailpzomedretirarqnf) {
		this.emailpzomedretirarqnf = emailpzomedretirarqnf;
	}

	@Column(name = "NOMECONTATOEMAILPED", length = 40)
	public String getNomecontatoemailped() {
		return this.nomecontatoemailped;
	}

	public void setNomecontatoemailped(String nomecontatoemailped) {
		this.nomecontatoemailped = nomecontatoemailped;
	}

	@Column(name = "EMAILPEDCOMPRA", length = 250)
	public String getEmailpedcompra() {
		return this.emailpedcompra;
	}

	public void setEmailpedcompra(String emailpedcompra) {
		this.emailpedcompra = emailpedcompra;
	}

	@Column(name = "AGENDA_SEG", length = 1)
	public String getAgendaSeg() {
		return this.agendaSeg;
	}

	public void setAgendaSeg(String agendaSeg) {
		this.agendaSeg = agendaSeg;
	}

	@Column(name = "AGENDA_TER", length = 1)
	public String getAgendaTer() {
		return this.agendaTer;
	}

	public void setAgendaTer(String agendaTer) {
		this.agendaTer = agendaTer;
	}

	@Column(name = "AGENDA_QUA", length = 1)
	public String getAgendaQua() {
		return this.agendaQua;
	}

	public void setAgendaQua(String agendaQua) {
		this.agendaQua = agendaQua;
	}

	@Column(name = "AGENDA_QUI", length = 1)
	public String getAgendaQui() {
		return this.agendaQui;
	}

	public void setAgendaQui(String agendaQui) {
		this.agendaQui = agendaQui;
	}

	@Column(name = "AGENDA_SEX", length = 1)
	public String getAgendaSex() {
		return this.agendaSex;
	}

	public void setAgendaSex(String agendaSex) {
		this.agendaSex = agendaSex;
	}

	@Column(name = "AGENDA_SAB", length = 1)
	public String getAgendaSab() {
		return this.agendaSab;
	}

	public void setAgendaSab(String agendaSab) {
		this.agendaSab = agendaSab;
	}

	@Column(name = "AGENDA_DOM", length = 1)
	public String getAgendaDom() {
		return this.agendaDom;
	}

	public void setAgendaDom(String agendaDom) {
		this.agendaDom = agendaDom;
	}

	@Column(name = "AGENDA_PERIODICIDADE", length = 1)
	public String getAgendaPeriodicidade() {
		return this.agendaPeriodicidade;
	}

	public void setAgendaPeriodicidade(String agendaPeriodicidade) {
		this.agendaPeriodicidade = agendaPeriodicidade;
	}

	@Column(name = "AGENDA_DIAS_INTERVALO", precision = 3, scale = 0)
	public Short getAgendaDiasIntervalo() {
		return this.agendaDiasIntervalo;
	}

	public void setAgendaDiasIntervalo(Short agendaDiasIntervalo) {
		this.agendaDiasIntervalo = agendaDiasIntervalo;
	}

	@Column(name = "AGENDA_HORA_VISITA", precision = 3, scale = 1)
	public BigDecimal getAgendaHoraVisita() {
		return this.agendaHoraVisita;
	}

	public void setAgendaHoraVisita(BigDecimal agendaHoraVisita) {
		this.agendaHoraVisita = agendaHoraVisita;
	}

	@Column(name = "MODELOAFVFORNEC", length = 1)
	public String getModeloafvfornec() {
		return this.modeloafvfornec;
	}

	public void setModeloafvfornec(String modeloafvfornec) {
		this.modeloafvfornec = modeloafvfornec;
	}

	@Column(name = "INDPRODUZMARCAPROPRIAEMP", length = 1)
	public String getIndproduzmarcapropriaemp() {
		return this.indproduzmarcapropriaemp;
	}

	public void setIndproduzmarcapropriaemp(String indproduzmarcapropriaemp) {
		this.indproduzmarcapropriaemp = indproduzmarcapropriaemp;
	}

	@Column(name = "INDMEDICAMENTO", length = 1)
	public String getIndmedicamento() {
		return this.indmedicamento;
	}

	public void setIndmedicamento(String indmedicamento) {
		this.indmedicamento = indmedicamento;
	}

	@Column(name = "INDMEDIDAMETADISTRIB", length = 1)
	public String getIndmedidametadistrib() {
		return this.indmedidametadistrib;
	}

	public void setIndmedidametadistrib(String indmedidametadistrib) {
		this.indmedidametadistrib = indmedidametadistrib;
	}

	@Column(name = "INDEXIBEFORNECEDIPEDIDO", length = 1)
	public String getIndexibefornecedipedido() {
		return this.indexibefornecedipedido;
	}

	public void setIndexibefornecedipedido(String indexibefornecedipedido) {
		this.indexibefornecedipedido = indexibefornecedipedido;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@Column(name = "NOMECONTATOEMAILAGE", length = 40)
	public String getNomecontatoemailage() {
		return this.nomecontatoemailage;
	}

	public void setNomecontatoemailage(String nomecontatoemailage) {
		this.nomecontatoemailage = nomecontatoemailage;
	}

	@Column(name = "EMAILAGEFORNEC", length = 250)
	public String getEmailagefornec() {
		return this.emailagefornec;
	}

	public void setEmailagefornec(String emailagefornec) {
		this.emailagefornec = emailagefornec;
	}

	@Column(name = "NOMECONTATOEMAILDEV", length = 40)
	public String getNomecontatoemaildev() {
		return this.nomecontatoemaildev;
	}

	public void setNomecontatoemaildev(String nomecontatoemaildev) {
		this.nomecontatoemaildev = nomecontatoemaildev;
	}

	@Column(name = "EMAILDEVFORNEC", length = 250)
	public String getEmaildevfornec() {
		return this.emaildevfornec;
	}

	public void setEmaildevfornec(String emaildevfornec) {
		this.emaildevfornec = emaildevfornec;
	}

	@Column(name = "INDRECEBVENCVLRLIQ", length = 1)
	public String getIndrecebvencvlrliq() {
		return this.indrecebvencvlrliq;
	}

	public void setIndrecebvencvlrliq(String indrecebvencvlrliq) {
		this.indrecebvencvlrliq = indrecebvencvlrliq;
	}

	@Column(name = "INDEQUIPINDUSTRIATRANSF", length = 1)
	public String getIndequipindustriatransf() {
		return this.indequipindustriatransf;
	}

	public void setIndequipindustriatransf(String indequipindustriatransf) {
		this.indequipindustriatransf = indequipindustriatransf;
	}

	@Column(name = "INDCODBUSCAPROD", length = 1)
	public String getIndcodbuscaprod() {
		return this.indcodbuscaprod;
	}

	public void setIndcodbuscaprod(String indcodbuscaprod) {
		this.indcodbuscaprod = indcodbuscaprod;
	}

	@Column(name = "NOMECONTATOTITULO", length = 40)
	public String getNomecontatotitulo() {
		return this.nomecontatotitulo;
	}

	public void setNomecontatotitulo(String nomecontatotitulo) {
		this.nomecontatotitulo = nomecontatotitulo;
	}

	@Column(name = "EMAILTITULO", length = 250)
	public String getEmailtitulo() {
		return this.emailtitulo;
	}

	public void setEmailtitulo(String emailtitulo) {
		this.emailtitulo = emailtitulo;
	}

	@Column(name = "NOMECONTATOLANCPROMO", length = 40)
	public String getNomecontatolancpromo() {
		return this.nomecontatolancpromo;
	}

	public void setNomecontatolancpromo(String nomecontatolancpromo) {
		this.nomecontatolancpromo = nomecontatolancpromo;
	}

	@Column(name = "EMAILLANCPROMO", length = 250)
	public String getEmaillancpromo() {
		return this.emaillancpromo;
	}

	public void setEmaillancpromo(String emaillancpromo) {
		this.emaillancpromo = emaillancpromo;
	}

	@Column(name = "INDPERMDESCVENDA", length = 1)
	public String getIndpermdescvenda() {
		return this.indpermdescvenda;
	}

	public void setIndpermdescvenda(String indpermdescvenda) {
		this.indpermdescvenda = indpermdescvenda;
	}

	@Column(name = "INDBLOQDTAVENCPED", length = 1)
	public String getIndbloqdtavencped() {
		return this.indbloqdtavencped;
	}

	public void setIndbloqdtavencped(String indbloqdtavencped) {
		this.indbloqdtavencped = indbloqdtavencped;
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

	@Column(name = "NOMECONTATOEMAILACORDO", length = 40)
	public String getNomecontatoemailacordo() {
		return this.nomecontatoemailacordo;
	}

	public void setNomecontatoemailacordo(String nomecontatoemailacordo) {
		this.nomecontatoemailacordo = nomecontatoemailacordo;
	}

	@Column(name = "EMAILACORDO", length = 250)
	public String getEmailacordo() {
		return this.emailacordo;
	}

	public void setEmailacordo(String emailacordo) {
		this.emailacordo = emailacordo;
	}

	@Column(name = "INDPARCEIROPROMOC", length = 1)
	public String getIndparceiropromoc() {
		return this.indparceiropromoc;
	}

	public void setIndparceiropromoc(String indparceiropromoc) {
		this.indparceiropromoc = indparceiropromoc;
	}

	@Column(name = "INDEXIGECONTRATOPS", length = 1)
	public String getIndexigecontratops() {
		return this.indexigecontratops;
	}

	public void setIndexigecontratops(String indexigecontratops) {
		this.indexigecontratops = indexigecontratops;
	}

	@Column(name = "INDIPIEQUIPARAINDUSTRIA", length = 1)
	public String getIndipiequiparaindustria() {
		return this.indipiequiparaindustria;
	}

	public void setIndipiequiparaindustria(String indipiequiparaindustria) {
		this.indipiequiparaindustria = indipiequiparaindustria;
	}

	@Column(name = "EMAILREGDEVFORNEC", length = 250)
	public String getEmailregdevfornec() {
		return this.emailregdevfornec;
	}

	public void setEmailregdevfornec(String emailregdevfornec) {
		this.emailregdevfornec = emailregdevfornec;
	}

	@Column(name = "NOMECONTATOEMAILREGDEV", length = 40)
	public String getNomecontatoemailregdev() {
		return this.nomecontatoemailregdev;
	}

	public void setNomecontatoemailregdev(String nomecontatoemailregdev) {
		this.nomecontatoemailregdev = nomecontatoemailregdev;
	}

	@Column(name = "INDAGENDACOMPRADOR", length = 1)
	public String getIndagendacomprador() {
		return this.indagendacomprador;
	}

	public void setIndagendacomprador(String indagendacomprador) {
		this.indagendacomprador = indagendacomprador;
	}

}
