package br.com.seta.processo.entity;



import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "GE_PESSOA")
@NamedNativeQueries({  
    @NamedNativeQuery(name = "Pessoa.TodasAtivas",          query = "SELECT G.* FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' ORDER BY G.NOMERAZAO", resultClass = PessoaEntity.class),
    @NamedNativeQuery(name = "Pessoa.TodasAtivasID",        query = "SELECT G.* FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.SEQPESSOA = :seqpessoa AND G.STATUS = 'A' ORDER BY G.NOMERAZAO", resultClass = PessoaEntity.class),
    @NamedNativeQuery(name = "Pessoa.AtivasIdPessoa",       query = "SELECT G.* FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.SEQPESSOA = :seqpessoa AND G.STATUS = 'A'", resultClass = PessoaEntity.class),
    @NamedNativeQuery(name = "Pessoa.AtivasIdPessoaStatus", query = "SELECT G.* FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.SEQPESSOA = :seqpessoa AND G.STATUS = :status", resultClass = PessoaEntity.class)
})       


public class PessoaEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal seqpessoa;
	private BigDecimal versao;
	private String status;
	private Date dtaativacao;
	private String nomerazao;
	private String fantasia;
	private String palavrachave;
	private String fisicajuridica;
	private String sexo;
	private Integer seqcidade;
	private String cidade;
	private String uf;
	private Integer seqbairro;
	private String bairro;
	private Integer seqlogradouro;
	private String logradouro;
	private String nrologradouro;
	private String cmpltologradouro;
	private String cep;
	private String foneddd1;
	private String fonenro1;
	private String fonecmpl1;
	private String foneddd2;
	private String fonenro2;
	private String fonecmpl2;
	private String foneddd3;
	private String fonenro3;
	private String fonecmpl3;
	private String faxddd;
	private String faxnro;
	private Long nrocgccpf;
	private Byte digcgccpf;
	private String inscricaorg;
	private Date dtanascfund;
	private String origem;
	private Long codclientefora;
	private String email;
	private String estadocivil;
	private String atividade;
	private String rendafaturamento;
	private String grauinstrucao;
	private String grupo;
	private String porte;
	private Date dtainclusao;
	private String usuinclusao;
	private Date dtaalteracao;
	private String usualteracao;
	private Date dtainativacao;
	private String usuinativacao;
	private String obsinativacao;
	private String indcontribicms;
	private String indreplicacao;
	private String indgeroureplic;
	private String indfornecedor;
	private String indcliente;
	private String indvendedor;
	private String indparceiro;
	private String pais;
	private String homepage;
	private String inscprodutor;
	private String inscmunicipal;
	private String indprodrural;
	private String indcontribipi;
	private String indmicroempresa;
	private Long nit;
	private String nroinscsuframa;
	private Long nrocei;
	private String inscricaorgst;
	private Date dtabaseexportacao;
	private String indsusppiscofins;
	private Long cnae;
	private String orgexp;
	private String orgexpuf;
	private long nrobaseexportacao;
	private String emailnfe;
	private String indprofprescritor;
	private Long inscinss;
	private String pisnit;
	private String usavpe;
	private Date dtaalteracaoroadshow;
	private Date dtahorainclusao;
	private Date datahoraalteracao;
	private Date dtaexpedicaodoc;
	private String inscrcaceal;
	private String matricula;
	private String indusainstrboleto;
	private String instrucaoboleto;
	private String tipolanctoindeniz;
	private Long nrocnpjprodrural;
	private Byte digcnpjprodrural;

	public PessoaEntity() {
	}

	public PessoaEntity(BigDecimal seqpessoa, String status, String nomerazao,	long nrobaseexportacao) {
		this.seqpessoa = seqpessoa;
		this.status = status;
		this.nomerazao = nomerazao;
		this.nrobaseexportacao = nrobaseexportacao;
	}

	public PessoaEntity(BigDecimal seqpessoa, BigDecimal versao, String status,
			Date dtaativacao, String nomerazao, String fantasia,
			String palavrachave, String fisicajuridica, String sexo,
			Integer seqcidade, String cidade, String uf, Integer seqbairro,
			String bairro, Integer seqlogradouro, String logradouro,
			String nrologradouro, String cmpltologradouro, String cep,
			String foneddd1, String fonenro1, String fonecmpl1,
			String foneddd2, String fonenro2, String fonecmpl2,
			String foneddd3, String fonenro3, String fonecmpl3, String faxddd,
			String faxnro, Long nrocgccpf, Byte digcgccpf, String inscricaorg,
			Date dtanascfund, String origem, Long codclientefora, String email,
			String estadocivil, String atividade, String rendafaturamento,
			String grauinstrucao, String grupo, String porte, Date dtainclusao,
			String usuinclusao, Date dtaalteracao, String usualteracao,
			Date dtainativacao, String usuinativacao, String obsinativacao,
			String indcontribicms, String indreplicacao, String indgeroureplic,
			String indfornecedor, String indcliente, String indvendedor,
			String indparceiro, String pais, String homepage,
			String inscprodutor, String inscmunicipal, String indprodrural,
			String indcontribipi, String indmicroempresa, Long nit,
			String nroinscsuframa, Long nrocei, String inscricaorgst,
			Date dtabaseexportacao, String indsusppiscofins, Long cnae,
			String orgexp, String orgexpuf, long nrobaseexportacao,
			String emailnfe, String indprofprescritor, Long inscinss,
			String pisnit, String usavpe, Date dtaalteracaoroadshow,
			Date dtahorainclusao, Date datahoraalteracao, Date dtaexpedicaodoc,
			String inscrcaceal, String matricula, String indusainstrboleto,
			String instrucaoboleto, String tipolanctoindeniz,
			Long nrocnpjprodrural, Byte digcnpjprodrural) {
		this.seqpessoa = seqpessoa;
		this.versao = versao;
		this.status = status;
		this.dtaativacao = dtaativacao;
		this.nomerazao = nomerazao;
		this.fantasia = fantasia;
		this.palavrachave = palavrachave;
		this.fisicajuridica = fisicajuridica;
		this.sexo = sexo;
		this.seqcidade = seqcidade;
		this.cidade = cidade;
		this.uf = uf;
		this.seqbairro = seqbairro;
		this.bairro = bairro;
		this.seqlogradouro = seqlogradouro;
		this.logradouro = logradouro;
		this.nrologradouro = nrologradouro;
		this.cmpltologradouro = cmpltologradouro;
		this.cep = cep;
		this.foneddd1 = foneddd1;
		this.fonenro1 = fonenro1;
		this.fonecmpl1 = fonecmpl1;
		this.foneddd2 = foneddd2;
		this.fonenro2 = fonenro2;
		this.fonecmpl2 = fonecmpl2;
		this.foneddd3 = foneddd3;
		this.fonenro3 = fonenro3;
		this.fonecmpl3 = fonecmpl3;
		this.faxddd = faxddd;
		this.faxnro = faxnro;
		this.nrocgccpf = nrocgccpf;
		this.digcgccpf = digcgccpf;
		this.inscricaorg = inscricaorg;
		this.dtanascfund = dtanascfund;
		this.origem = origem;
		this.codclientefora = codclientefora;
		this.email = email;
		this.estadocivil = estadocivil;
		this.atividade = atividade;
		this.rendafaturamento = rendafaturamento;
		this.grauinstrucao = grauinstrucao;
		this.grupo = grupo;
		this.porte = porte;
		this.dtainclusao = dtainclusao;
		this.usuinclusao = usuinclusao;
		this.dtaalteracao = dtaalteracao;
		this.usualteracao = usualteracao;
		this.dtainativacao = dtainativacao;
		this.usuinativacao = usuinativacao;
		this.obsinativacao = obsinativacao;
		this.indcontribicms = indcontribicms;
		this.indreplicacao = indreplicacao;
		this.indgeroureplic = indgeroureplic;
		this.indfornecedor = indfornecedor;
		this.indcliente = indcliente;
		this.indvendedor = indvendedor;
		this.indparceiro = indparceiro;
		this.pais = pais;
		this.homepage = homepage;
		this.inscprodutor = inscprodutor;
		this.inscmunicipal = inscmunicipal;
		this.indprodrural = indprodrural;
		this.indcontribipi = indcontribipi;
		this.indmicroempresa = indmicroempresa;
		this.nit = nit;
		this.nroinscsuframa = nroinscsuframa;
		this.nrocei = nrocei;
		this.inscricaorgst = inscricaorgst;
		this.dtabaseexportacao = dtabaseexportacao;
		this.indsusppiscofins = indsusppiscofins;
		this.cnae = cnae;
		this.orgexp = orgexp;
		this.orgexpuf = orgexpuf;
		this.nrobaseexportacao = nrobaseexportacao;
		this.emailnfe = emailnfe;
		this.indprofprescritor = indprofprescritor;
		this.inscinss = inscinss;
		this.pisnit = pisnit;
		this.usavpe = usavpe;
		this.dtaalteracaoroadshow = dtaalteracaoroadshow;
		this.dtahorainclusao = dtahorainclusao;
		this.datahoraalteracao = datahoraalteracao;
		this.dtaexpedicaodoc = dtaexpedicaodoc;
		this.inscrcaceal = inscrcaceal;
		this.matricula = matricula;
		this.indusainstrboleto = indusainstrboleto;
		this.instrucaoboleto = instrucaoboleto;
		this.tipolanctoindeniz = tipolanctoindeniz;
		this.nrocnpjprodrural = nrocnpjprodrural;
		this.digcnpjprodrural = digcnpjprodrural;
	}

	@Id
	@Column(name = "SEQPESSOA", unique = true, nullable = false, precision = 8, scale = 0)
	public BigDecimal getSeqpessoa() {
		return seqpessoa;
	}

	public void setSeqpessoa(BigDecimal seqpessoa) {
		this.seqpessoa = seqpessoa;
	}
	

	@Column(name = "VERSAO", precision = 2, scale = 0)
	public BigDecimal getVersao() {
		return this.versao;
	}
	public void setVersao(BigDecimal versao) {
		this.versao = versao;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAATIVACAO", length = 7)
	public Date getDtaativacao() {
		return this.dtaativacao;
	}

	public void setDtaativacao(Date dtaativacao) {
		this.dtaativacao = dtaativacao;
	}

	@Column(name = "NOMERAZAO", nullable = false, length = 40)
	public String getNomerazao() {
		return this.nomerazao;
	}

	public void setNomerazao(String nomerazao) {
		this.nomerazao = nomerazao;
	}

	@Column(name = "FANTASIA", length = 30)
	public String getFantasia() {
		return this.fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	@Column(name = "PALAVRACHAVE", length = 30)
	public String getPalavrachave() {
		return this.palavrachave;
	}

	public void setPalavrachave(String palavrachave) {
		this.palavrachave = palavrachave;
	}

	@Column(name = "FISICAJURIDICA", length = 1)
	public String getFisicajuridica() {
		return this.fisicajuridica;
	}

	public void setFisicajuridica(String fisicajuridica) {
		this.fisicajuridica = fisicajuridica;
	}

	@Column(name = "SEXO", length = 1)
	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Column(name = "SEQCIDADE", precision = 6, scale = 0)
	public Integer getSeqcidade() {
		return this.seqcidade;
	}

	public void setSeqcidade(Integer seqcidade) {
		this.seqcidade = seqcidade;
	}

	@Column(name = "CIDADE", length = 30)
	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "UF", length = 2)
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(name = "SEQBAIRRO", precision = 7, scale = 0)
	public Integer getSeqbairro() {
		return this.seqbairro;
	}

	public void setSeqbairro(Integer seqbairro) {
		this.seqbairro = seqbairro;
	}

	@Column(name = "BAIRRO", length = 30)
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "SEQLOGRADOURO", precision = 6, scale = 0)
	public Integer getSeqlogradouro() {
		return this.seqlogradouro;
	}

	public void setSeqlogradouro(Integer seqlogradouro) {
		this.seqlogradouro = seqlogradouro;
	}

	@Column(name = "LOGRADOURO", length = 35)
	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "NROLOGRADOURO", length = 10)
	public String getNrologradouro() {
		return this.nrologradouro;
	}

	public void setNrologradouro(String nrologradouro) {
		this.nrologradouro = nrologradouro;
	}

	@Column(name = "CMPLTOLOGRADOURO", length = 10)
	public String getCmpltologradouro() {
		return this.cmpltologradouro;
	}

	public void setCmpltologradouro(String cmpltologradouro) {
		this.cmpltologradouro = cmpltologradouro;
	}

	@Column(name = "CEP", length = 12)
	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "FONEDDD1", length = 5)
	public String getFoneddd1() {
		return this.foneddd1;
	}

	public void setFoneddd1(String foneddd1) {
		this.foneddd1 = foneddd1;
	}

	@Column(name = "FONENRO1", length = 12)
	public String getFonenro1() {
		return this.fonenro1;
	}

	public void setFonenro1(String fonenro1) {
		this.fonenro1 = fonenro1;
	}

	@Column(name = "FONECMPL1", length = 12)
	public String getFonecmpl1() {
		return this.fonecmpl1;
	}

	public void setFonecmpl1(String fonecmpl1) {
		this.fonecmpl1 = fonecmpl1;
	}

	@Column(name = "FONEDDD2", length = 5)
	public String getFoneddd2() {
		return this.foneddd2;
	}

	public void setFoneddd2(String foneddd2) {
		this.foneddd2 = foneddd2;
	}

	@Column(name = "FONENRO2", length = 12)
	public String getFonenro2() {
		return this.fonenro2;
	}

	public void setFonenro2(String fonenro2) {
		this.fonenro2 = fonenro2;
	}

	@Column(name = "FONECMPL2", length = 12)
	public String getFonecmpl2() {
		return this.fonecmpl2;
	}

	public void setFonecmpl2(String fonecmpl2) {
		this.fonecmpl2 = fonecmpl2;
	}

	@Column(name = "FONEDDD3", length = 5)
	public String getFoneddd3() {
		return this.foneddd3;
	}

	public void setFoneddd3(String foneddd3) {
		this.foneddd3 = foneddd3;
	}

	@Column(name = "FONENRO3", length = 12)
	public String getFonenro3() {
		return this.fonenro3;
	}

	public void setFonenro3(String fonenro3) {
		this.fonenro3 = fonenro3;
	}

	@Column(name = "FONECMPL3", length = 12)
	public String getFonecmpl3() {
		return this.fonecmpl3;
	}

	public void setFonecmpl3(String fonecmpl3) {
		this.fonecmpl3 = fonecmpl3;
	}

	@Column(name = "FAXDDD", length = 5)
	public String getFaxddd() {
		return this.faxddd;
	}

	public void setFaxddd(String faxddd) {
		this.faxddd = faxddd;
	}

	@Column(name = "FAXNRO", length = 12)
	public String getFaxnro() {
		return this.faxnro;
	}

	public void setFaxnro(String faxnro) {
		this.faxnro = faxnro;
	}

	@Column(name = "NROCGCCPF", precision = 13, scale = 0)
	public Long getNrocgccpf() {
		return this.nrocgccpf;
	}

	public void setNrocgccpf(Long nrocgccpf) {
		this.nrocgccpf = nrocgccpf;
	}

	@Column(name = "DIGCGCCPF", precision = 2, scale = 0)
	public Byte getDigcgccpf() {
		return this.digcgccpf;
	}

	public void setDigcgccpf(Byte digcgccpf) {
		this.digcgccpf = digcgccpf;
	}

	@Column(name = "INSCRICAORG", length = 20)
	public String getInscricaorg() {
		return this.inscricaorg;
	}

	public void setInscricaorg(String inscricaorg) {
		this.inscricaorg = inscricaorg;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTANASCFUND", length = 7)
	public Date getDtanascfund() {
		return this.dtanascfund;
	}

	public void setDtanascfund(Date dtanascfund) {
		this.dtanascfund = dtanascfund;
	}

	@Column(name = "ORIGEM", length = 5)
	public String getOrigem() {
		return this.origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	@Column(name = "CODCLIENTEFORA", precision = 15, scale = 0)
	public Long getCodclientefora() {
		return this.codclientefora;
	}

	public void setCodclientefora(Long codclientefora) {
		this.codclientefora = codclientefora;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ESTADOCIVIL", length = 1)
	public String getEstadocivil() {
		return this.estadocivil;
	}

	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}

	@Column(name = "ATIVIDADE", length = 35)
	public String getAtividade() {
		return this.atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	@Column(name = "RENDAFATURAMENTO", length = 30)
	public String getRendafaturamento() {
		return this.rendafaturamento;
	}

	public void setRendafaturamento(String rendafaturamento) {
		this.rendafaturamento = rendafaturamento;
	}

	@Column(name = "GRAUINSTRUCAO", length = 30)
	public String getGrauinstrucao() {
		return this.grauinstrucao;
	}

	public void setGrauinstrucao(String grauinstrucao) {
		this.grauinstrucao = grauinstrucao;
	}

	@Column(name = "GRUPO", length = 35)
	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Column(name = "PORTE", length = 30)
	public String getPorte() {
		return this.porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAINCLUSAO", length = 7)
	public Date getDtainclusao() {
		return this.dtainclusao;
	}

	public void setDtainclusao(Date dtainclusao) {
		this.dtainclusao = dtainclusao;
	}

	@Column(name = "USUINCLUSAO", length = 12)
	public String getUsuinclusao() {
		return this.usuinclusao;
	}

	public void setUsuinclusao(String usuinclusao) {
		this.usuinclusao = usuinclusao;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAINATIVACAO", length = 7)
	public Date getDtainativacao() {
		return this.dtainativacao;
	}

	public void setDtainativacao(Date dtainativacao) {
		this.dtainativacao = dtainativacao;
	}

	@Column(name = "USUINATIVACAO", length = 12)
	public String getUsuinativacao() {
		return this.usuinativacao;
	}

	public void setUsuinativacao(String usuinativacao) {
		this.usuinativacao = usuinativacao;
	}

	@Column(name = "OBSINATIVACAO", length = 50)
	public String getObsinativacao() {
		return this.obsinativacao;
	}

	public void setObsinativacao(String obsinativacao) {
		this.obsinativacao = obsinativacao;
	}

	@Column(name = "INDCONTRIBICMS", length = 1)
	public String getIndcontribicms() {
		return this.indcontribicms;
	}

	public void setIndcontribicms(String indcontribicms) {
		this.indcontribicms = indcontribicms;
	}

	@Column(name = "INDREPLICACAO", length = 1)
	public String getIndreplicacao() {
		return this.indreplicacao;
	}

	public void setIndreplicacao(String indreplicacao) {
		this.indreplicacao = indreplicacao;
	}

	@Column(name = "INDGEROUREPLIC", length = 1)
	public String getIndgeroureplic() {
		return this.indgeroureplic;
	}

	public void setIndgeroureplic(String indgeroureplic) {
		this.indgeroureplic = indgeroureplic;
	}

	@Column(name = "INDFORNECEDOR", length = 1)
	public String getIndfornecedor() {
		return this.indfornecedor;
	}

	public void setIndfornecedor(String indfornecedor) {
		this.indfornecedor = indfornecedor;
	}

	@Column(name = "INDCLIENTE", length = 1)
	public String getIndcliente() {
		return this.indcliente;
	}

	public void setIndcliente(String indcliente) {
		this.indcliente = indcliente;
	}

	@Column(name = "INDVENDEDOR", length = 1)
	public String getIndvendedor() {
		return this.indvendedor;
	}

	public void setIndvendedor(String indvendedor) {
		this.indvendedor = indvendedor;
	}

	@Column(name = "INDPARCEIRO", length = 1)
	public String getIndparceiro() {
		return this.indparceiro;
	}

	public void setIndparceiro(String indparceiro) {
		this.indparceiro = indparceiro;
	}

	@Column(name = "PAIS", length = 40)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Column(name = "HOMEPAGE", length = 80)
	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	@Column(name = "INSCPRODUTOR", length = 15)
	public String getInscprodutor() {
		return this.inscprodutor;
	}

	public void setInscprodutor(String inscprodutor) {
		this.inscprodutor = inscprodutor;
	}

	@Column(name = "INSCMUNICIPAL", length = 15)
	public String getInscmunicipal() {
		return this.inscmunicipal;
	}

	public void setInscmunicipal(String inscmunicipal) {
		this.inscmunicipal = inscmunicipal;
	}

	@Column(name = "INDPRODRURAL", length = 1)
	public String getIndprodrural() {
		return this.indprodrural;
	}

	public void setIndprodrural(String indprodrural) {
		this.indprodrural = indprodrural;
	}

	@Column(name = "INDCONTRIBIPI", length = 1)
	public String getIndcontribipi() {
		return this.indcontribipi;
	}

	public void setIndcontribipi(String indcontribipi) {
		this.indcontribipi = indcontribipi;
	}

	@Column(name = "INDMICROEMPRESA", length = 1)
	public String getIndmicroempresa() {
		return this.indmicroempresa;
	}

	public void setIndmicroempresa(String indmicroempresa) {
		this.indmicroempresa = indmicroempresa;
	}

	@Column(name = "NIT", precision = 15, scale = 0)
	public Long getNit() {
		return this.nit;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	@Column(name = "NROINSCSUFRAMA", length = 9)
	public String getNroinscsuframa() {
		return this.nroinscsuframa;
	}

	public void setNroinscsuframa(String nroinscsuframa) {
		this.nroinscsuframa = nroinscsuframa;
	}

	@Column(name = "NROCEI", precision = 12, scale = 0)
	public Long getNrocei() {
		return this.nrocei;
	}

	public void setNrocei(Long nrocei) {
		this.nrocei = nrocei;
	}

	@Column(name = "INSCRICAORGST", length = 20)
	public String getInscricaorgst() {
		return this.inscricaorgst;
	}

	public void setInscricaorgst(String inscricaorgst) {
		this.inscricaorgst = inscricaorgst;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTABASEEXPORTACAO", length = 7)
	public Date getDtabaseexportacao() {
		return this.dtabaseexportacao;
	}

	public void setDtabaseexportacao(Date dtabaseexportacao) {
		this.dtabaseexportacao = dtabaseexportacao;
	}

	@Column(name = "INDSUSPPISCOFINS", length = 1)
	public String getIndsusppiscofins() {
		return this.indsusppiscofins;
	}

	public void setIndsusppiscofins(String indsusppiscofins) {
		this.indsusppiscofins = indsusppiscofins;
	}

	@Column(name = "CNAE", precision = 10, scale = 0)
	public Long getCnae() {
		return this.cnae;
	}

	public void setCnae(Long cnae) {
		this.cnae = cnae;
	}

	@Column(name = "ORGEXP", length = 6)
	public String getOrgexp() {
		return this.orgexp;
	}

	public void setOrgexp(String orgexp) {
		this.orgexp = orgexp;
	}

	@Column(name = "ORGEXPUF", length = 2)
	public String getOrgexpuf() {
		return this.orgexpuf;
	}

	public void setOrgexpuf(String orgexpuf) {
		this.orgexpuf = orgexpuf;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@Column(name = "EMAILNFE", length = 250)
	public String getEmailnfe() {
		return this.emailnfe;
	}

	public void setEmailnfe(String emailnfe) {
		this.emailnfe = emailnfe;
	}

	@Column(name = "INDPROFPRESCRITOR", length = 1)
	public String getIndprofprescritor() {
		return this.indprofprescritor;
	}

	public void setIndprofprescritor(String indprofprescritor) {
		this.indprofprescritor = indprofprescritor;
	}

	@Column(name = "INSCINSS", precision = 15, scale = 0)
	public Long getInscinss() {
		return this.inscinss;
	}

	public void setInscinss(Long inscinss) {
		this.inscinss = inscinss;
	}

	@Column(name = "PISNIT", length = 20)
	public String getPisnit() {
		return this.pisnit;
	}

	public void setPisnit(String pisnit) {
		this.pisnit = pisnit;
	}

	@Column(name = "USAVPE", length = 1)
	public String getUsavpe() {
		return this.usavpe;
	}

	public void setUsavpe(String usavpe) {
		this.usavpe = usavpe;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAALTERACAOROADSHOW", length = 7)
	public Date getDtaalteracaoroadshow() {
		return this.dtaalteracaoroadshow;
	}

	public void setDtaalteracaoroadshow(Date dtaalteracaoroadshow) {
		this.dtaalteracaoroadshow = dtaalteracaoroadshow;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAHORAINCLUSAO", length = 7)
	public Date getDtahorainclusao() {
		return this.dtahorainclusao;
	}

	public void setDtahorainclusao(Date dtahorainclusao) {
		this.dtahorainclusao = dtahorainclusao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATAHORAALTERACAO", length = 7)
	public Date getDatahoraalteracao() {
		return this.datahoraalteracao;
	}

	public void setDatahoraalteracao(Date datahoraalteracao) {
		this.datahoraalteracao = datahoraalteracao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAEXPEDICAODOC", length = 7)
	public Date getDtaexpedicaodoc() {
		return this.dtaexpedicaodoc;
	}

	public void setDtaexpedicaodoc(Date dtaexpedicaodoc) {
		this.dtaexpedicaodoc = dtaexpedicaodoc;
	}

	@Column(name = "INSCRCACEAL", length = 1)
	public String getInscrcaceal() {
		return this.inscrcaceal;
	}

	public void setInscrcaceal(String inscrcaceal) {
		this.inscrcaceal = inscrcaceal;
	}

	@Column(name = "MATRICULA", length = 15)
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Column(name = "INDUSAINSTRBOLETO", length = 1)
	public String getIndusainstrboleto() {
		return this.indusainstrboleto;
	}

	public void setIndusainstrboleto(String indusainstrboleto) {
		this.indusainstrboleto = indusainstrboleto;
	}

	@Column(name = "INSTRUCAOBOLETO", length = 200)
	public String getInstrucaoboleto() {
		return this.instrucaoboleto;
	}

	public void setInstrucaoboleto(String instrucaoboleto) {
		this.instrucaoboleto = instrucaoboleto;
	}

	@Column(name = "TIPOLANCTOINDENIZ", length = 1)
	public String getTipolanctoindeniz() {
		return this.tipolanctoindeniz;
	}

	public void setTipolanctoindeniz(String tipolanctoindeniz) {
		this.tipolanctoindeniz = tipolanctoindeniz;
	}

	@Column(name = "NROCNPJPRODRURAL", precision = 13, scale = 0)
	public Long getNrocnpjprodrural() {
		return this.nrocnpjprodrural;
	}

	public void setNrocnpjprodrural(Long nrocnpjprodrural) {
		this.nrocnpjprodrural = nrocnpjprodrural;
	}

	@Column(name = "DIGCNPJPRODRURAL", precision = 2, scale = 0)
	public Byte getDigcnpjprodrural() {
		return this.digcnpjprodrural;
	}

	public void setDigcnpjprodrural(Byte digcnpjprodrural) {
		this.digcnpjprodrural = digcnpjprodrural;
	}

	
	@Override
	public String toString() {
		return "PessoaEntity [seqpessoa=" + seqpessoa + ", versao=" + versao
				+ ", status=" + status + ", dtaativacao=" + dtaativacao
				+ ", nomerazao=" + nomerazao + ", fantasia=" + fantasia
				+ ", palavrachave=" + palavrachave + ", fisicajuridica="
				+ fisicajuridica + ", sexo=" + sexo + ", seqcidade="
				+ seqcidade + ", cidade=" + cidade + ", uf=" + uf
				+ ", seqbairro=" + seqbairro + ", bairro=" + bairro
				+ ", seqlogradouro=" + seqlogradouro + ", logradouro="
				+ logradouro + ", nrologradouro=" + nrologradouro
				+ ", cmpltologradouro=" + cmpltologradouro + ", cep=" + cep
				+ ", foneddd1=" + foneddd1 + ", fonenro1=" + fonenro1
				+ ", fonecmpl1=" + fonecmpl1 + ", foneddd2=" + foneddd2
				+ ", fonenro2=" + fonenro2 + ", fonecmpl2=" + fonecmpl2
				+ ", foneddd3=" + foneddd3 + ", fonenro3=" + fonenro3
				+ ", fonecmpl3=" + fonecmpl3 + ", faxddd=" + faxddd
				+ ", faxnro=" + faxnro + ", nrocgccpf=" + nrocgccpf
				+ ", digcgccpf=" + digcgccpf + ", inscricaorg=" + inscricaorg
				+ ", dtanascfund=" + dtanascfund + ", origem=" + origem
				+ ", codclientefora=" + codclientefora + ", email=" + email
				+ ", estadocivil=" + estadocivil + ", atividade=" + atividade
				+ ", rendafaturamento=" + rendafaturamento + ", grauinstrucao="
				+ grauinstrucao + ", grupo=" + grupo + ", porte=" + porte
				+ ", dtainclusao=" + dtainclusao + ", usuinclusao="
				+ usuinclusao + ", dtaalteracao=" + dtaalteracao
				+ ", usualteracao=" + usualteracao + ", dtainativacao="
				+ dtainativacao + ", usuinativacao=" + usuinativacao
				+ ", obsinativacao=" + obsinativacao + ", indcontribicms="
				+ indcontribicms + ", indreplicacao=" + indreplicacao
				+ ", indgeroureplic=" + indgeroureplic + ", indfornecedor="
				+ indfornecedor + ", indcliente=" + indcliente
				+ ", indvendedor=" + indvendedor + ", indparceiro="
				+ indparceiro + ", pais=" + pais + ", homepage=" + homepage
				+ ", inscprodutor=" + inscprodutor + ", inscmunicipal="
				+ inscmunicipal + ", indprodrural=" + indprodrural
				+ ", indcontribipi=" + indcontribipi + ", indmicroempresa="
				+ indmicroempresa + ", nit=" + nit + ", nroinscsuframa="
				+ nroinscsuframa + ", nrocei=" + nrocei + ", inscricaorgst="
				+ inscricaorgst + ", dtabaseexportacao=" + dtabaseexportacao
				+ ", indsusppiscofins=" + indsusppiscofins + ", cnae=" + cnae
				+ ", orgexp=" + orgexp + ", orgexpuf=" + orgexpuf
				+ ", nrobaseexportacao=" + nrobaseexportacao + ", emailnfe="
				+ emailnfe + ", indprofprescritor=" + indprofprescritor
				+ ", inscinss=" + inscinss + ", pisnit=" + pisnit + ", usavpe="
				+ usavpe + ", dtaalteracaoroadshow=" + dtaalteracaoroadshow
				+ ", dtahorainclusao=" + dtahorainclusao
				+ ", datahoraalteracao=" + datahoraalteracao
				+ ", dtaexpedicaodoc=" + dtaexpedicaodoc + ", inscrcaceal="
				+ inscrcaceal + ", matricula=" + matricula
				+ ", indusainstrboleto=" + indusainstrboleto
				+ ", instrucaoboleto=" + instrucaoboleto
				+ ", tipolanctoindeniz=" + tipolanctoindeniz
				+ ", nrocnpjprodrural=" + nrocnpjprodrural
				+ ", digcnpjprodrural=" + digcnpjprodrural + "]";
	}

	
	
}
