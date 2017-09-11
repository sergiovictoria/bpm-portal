package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.util.Date;



public class Pessoa implements java.io.Serializable { 

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
	private BigDecimal nrocgccpf;
	private BigDecimal digcgccpf;
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
	private boolean selected = Boolean.FALSE;
	
	public Pessoa() {
	}
	
	public Pessoa(BigDecimal seqpessoa, String fantasia, BigDecimal nrocgccpf,  BigDecimal digcgccpf, BigDecimal versao, String nomerazao) {
		this.seqpessoa = seqpessoa;
		this.versao = versao;
		this.nomerazao = nomerazao;
		this.fantasia = fantasia;
		this.nrocgccpf = nrocgccpf;
		this.digcgccpf = digcgccpf;
	}
	
	public BigDecimal getSeqpessoa() {
		return seqpessoa;
	}

	public void setSeqpessoa(BigDecimal seqpessoa) {
		this.seqpessoa = seqpessoa;
	}
	public BigDecimal getVersao() {
		return versao;
	}
	public void setVersao(BigDecimal versao) {
		this.versao = versao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDtaativacao() {
		return dtaativacao;
	}
	public void setDtaativacao(Date dtaativacao) {
		this.dtaativacao = dtaativacao;
	}
	public String getNomerazao() {
		return nomerazao;
	}
	public void setNomerazao(String nomerazao) {
		this.nomerazao = nomerazao;
	}
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	public String getPalavrachave() {
		return palavrachave;
	}
	public void setPalavrachave(String palavrachave) {
		this.palavrachave = palavrachave;
	}
	public String getFisicajuridica() {
		return fisicajuridica;
	}
	public void setFisicajuridica(String fisicajuridica) {
		this.fisicajuridica = fisicajuridica;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getSeqcidade() {
		return seqcidade;
	}
	public void setSeqcidade(Integer seqcidade) {
		this.seqcidade = seqcidade;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Integer getSeqbairro() {
		return seqbairro;
	}
	public void setSeqbairro(Integer seqbairro) {
		this.seqbairro = seqbairro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Integer getSeqlogradouro() {
		return seqlogradouro;
	}
	public void setSeqlogradouro(Integer seqlogradouro) {
		this.seqlogradouro = seqlogradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNrologradouro() {
		return nrologradouro;
	}
	public void setNrologradouro(String nrologradouro) {
		this.nrologradouro = nrologradouro;
	}
	public String getCmpltologradouro() {
		return cmpltologradouro;
	}
	public void setCmpltologradouro(String cmpltologradouro) {
		this.cmpltologradouro = cmpltologradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getFoneddd1() {
		return foneddd1;
	}
	public void setFoneddd1(String foneddd1) {
		this.foneddd1 = foneddd1;
	}
	public String getFonenro1() {
		return fonenro1;
	}
	public void setFonenro1(String fonenro1) {
		this.fonenro1 = fonenro1;
	}
	public String getFonecmpl1() {
		return fonecmpl1;
	}
	public void setFonecmpl1(String fonecmpl1) {
		this.fonecmpl1 = fonecmpl1;
	}
	public String getFoneddd2() {
		return foneddd2;
	}
	public void setFoneddd2(String foneddd2) {
		this.foneddd2 = foneddd2;
	}
	public String getFonenro2() {
		return fonenro2;
	}
	public void setFonenro2(String fonenro2) {
		this.fonenro2 = fonenro2;
	}
	public String getFonecmpl2() {
		return fonecmpl2;
	}
	public void setFonecmpl2(String fonecmpl2) {
		this.fonecmpl2 = fonecmpl2;
	}
	public String getFoneddd3() {
		return foneddd3;
	}
	public void setFoneddd3(String foneddd3) {
		this.foneddd3 = foneddd3;
	}
	public String getFonenro3() {
		return fonenro3;
	}
	public void setFonenro3(String fonenro3) {
		this.fonenro3 = fonenro3;
	}
	public String getFonecmpl3() {
		return fonecmpl3;
	}
	public void setFonecmpl3(String fonecmpl3) {
		this.fonecmpl3 = fonecmpl3;
	}
	public String getFaxddd() {
		return faxddd;
	}
	public void setFaxddd(String faxddd) {
		this.faxddd = faxddd;
	}
	public String getFaxnro() {
		return faxnro;
	}
	public void setFaxnro(String faxnro) {
		this.faxnro = faxnro;
	}
	public BigDecimal getNrocgccpf() {
		return nrocgccpf;
	}
	public void setNrocgccpf(BigDecimal nrocgccpf) {
		this.nrocgccpf = nrocgccpf;
	}
	public BigDecimal getDigcgccpf() {
		return digcgccpf;
	}
	public void setDigcgccpf(BigDecimal digcgccpf) {
		this.digcgccpf = digcgccpf;
	}
	public String getInscricaorg() {
		return inscricaorg;
	}
	public void setInscricaorg(String inscricaorg) {
		this.inscricaorg = inscricaorg;
	}
	public Date getDtanascfund() {
		return dtanascfund;
	}
	public void setDtanascfund(Date dtanascfund) {
		this.dtanascfund = dtanascfund;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public Long getCodclientefora() {
		return codclientefora;
	}
	public void setCodclientefora(Long codclientefora) {
		this.codclientefora = codclientefora;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEstadocivil() {
		return estadocivil;
	}
	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getRendafaturamento() {
		return rendafaturamento;
	}
	public void setRendafaturamento(String rendafaturamento) {
		this.rendafaturamento = rendafaturamento;
	}
	public String getGrauinstrucao() {
		return grauinstrucao;
	}
	public void setGrauinstrucao(String grauinstrucao) {
		this.grauinstrucao = grauinstrucao;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getPorte() {
		return porte;
	}
	public void setPorte(String porte) {
		this.porte = porte;
	}
	public Date getDtainclusao() {
		return dtainclusao;
	}
	public void setDtainclusao(Date dtainclusao) {
		this.dtainclusao = dtainclusao;
	}
	public String getUsuinclusao() {
		return usuinclusao;
	}
	public void setUsuinclusao(String usuinclusao) {
		this.usuinclusao = usuinclusao;
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
	public Date getDtainativacao() {
		return dtainativacao;
	}
	public void setDtainativacao(Date dtainativacao) {
		this.dtainativacao = dtainativacao;
	}
	public String getUsuinativacao() {
		return usuinativacao;
	}
	public void setUsuinativacao(String usuinativacao) {
		this.usuinativacao = usuinativacao;
	}
	public String getObsinativacao() {
		return obsinativacao;
	}
	public void setObsinativacao(String obsinativacao) {
		this.obsinativacao = obsinativacao;
	}
	public String getIndcontribicms() {
		return indcontribicms;
	}
	public void setIndcontribicms(String indcontribicms) {
		this.indcontribicms = indcontribicms;
	}
	public String getIndreplicacao() {
		return indreplicacao;
	}
	public void setIndreplicacao(String indreplicacao) {
		this.indreplicacao = indreplicacao;
	}
	public String getIndgeroureplic() {
		return indgeroureplic;
	}
	public void setIndgeroureplic(String indgeroureplic) {
		this.indgeroureplic = indgeroureplic;
	}
	public String getIndfornecedor() {
		return indfornecedor;
	}
	public void setIndfornecedor(String indfornecedor) {
		this.indfornecedor = indfornecedor;
	}
	public String getIndcliente() {
		return indcliente;
	}
	public void setIndcliente(String indcliente) {
		this.indcliente = indcliente;
	}
	public String getIndvendedor() {
		return indvendedor;
	}
	public void setIndvendedor(String indvendedor) {
		this.indvendedor = indvendedor;
	}
	public String getIndparceiro() {
		return indparceiro;
	}
	public void setIndparceiro(String indparceiro) {
		this.indparceiro = indparceiro;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getInscprodutor() {
		return inscprodutor;
	}
	public void setInscprodutor(String inscprodutor) {
		this.inscprodutor = inscprodutor;
	}
	public String getInscmunicipal() {
		return inscmunicipal;
	}
	public void setInscmunicipal(String inscmunicipal) {
		this.inscmunicipal = inscmunicipal;
	}
	public String getIndprodrural() {
		return indprodrural;
	}
	public void setIndprodrural(String indprodrural) {
		this.indprodrural = indprodrural;
	}
	public String getIndcontribipi() {
		return indcontribipi;
	}
	public void setIndcontribipi(String indcontribipi) {
		this.indcontribipi = indcontribipi;
	}
	public String getIndmicroempresa() {
		return indmicroempresa;
	}
	public void setIndmicroempresa(String indmicroempresa) {
		this.indmicroempresa = indmicroempresa;
	}
	public Long getNit() {
		return nit;
	}
	public void setNit(Long nit) {
		this.nit = nit;
	}
	public String getNroinscsuframa() {
		return nroinscsuframa;
	}
	public void setNroinscsuframa(String nroinscsuframa) {
		this.nroinscsuframa = nroinscsuframa;
	}
	public Long getNrocei() {
		return nrocei;
	}
	public void setNrocei(Long nrocei) {
		this.nrocei = nrocei;
	}
	public String getInscricaorgst() {
		return inscricaorgst;
	}
	public void setInscricaorgst(String inscricaorgst) {
		this.inscricaorgst = inscricaorgst;
	}
	public Date getDtabaseexportacao() {
		return dtabaseexportacao;
	}
	public void setDtabaseexportacao(Date dtabaseexportacao) {
		this.dtabaseexportacao = dtabaseexportacao;
	}
	public String getIndsusppiscofins() {
		return indsusppiscofins;
	}
	public void setIndsusppiscofins(String indsusppiscofins) {
		this.indsusppiscofins = indsusppiscofins;
	}
	public Long getCnae() {
		return cnae;
	}
	public void setCnae(Long cnae) {
		this.cnae = cnae;
	}
	public String getOrgexp() {
		return orgexp;
	}
	public void setOrgexp(String orgexp) {
		this.orgexp = orgexp;
	}
	public String getOrgexpuf() {
		return orgexpuf;
	}
	public void setOrgexpuf(String orgexpuf) {
		this.orgexpuf = orgexpuf;
	}
	public long getNrobaseexportacao() {
		return nrobaseexportacao;
	}
	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}
	public String getEmailnfe() {
		return emailnfe;
	}
	public void setEmailnfe(String emailnfe) {
		this.emailnfe = emailnfe;
	}
	public String getIndprofprescritor() {
		return indprofprescritor;
	}
	public void setIndprofprescritor(String indprofprescritor) {
		this.indprofprescritor = indprofprescritor;
	}
	public Long getInscinss() {
		return inscinss;
	}
	public void setInscinss(Long inscinss) {
		this.inscinss = inscinss;
	}
	public String getPisnit() {
		return pisnit;
	}
	public void setPisnit(String pisnit) {
		this.pisnit = pisnit;
	}
	public String getUsavpe() {
		return usavpe;
	}
	public void setUsavpe(String usavpe) {
		this.usavpe = usavpe;
	}
	public Date getDtaalteracaoroadshow() {
		return dtaalteracaoroadshow;
	}
	public void setDtaalteracaoroadshow(Date dtaalteracaoroadshow) {
		this.dtaalteracaoroadshow = dtaalteracaoroadshow;
	}
	public Date getDtahorainclusao() {
		return dtahorainclusao;
	}
	public void setDtahorainclusao(Date dtahorainclusao) {
		this.dtahorainclusao = dtahorainclusao;
	}
	public Date getDatahoraalteracao() {
		return datahoraalteracao;
	}
	public void setDatahoraalteracao(Date datahoraalteracao) {
		this.datahoraalteracao = datahoraalteracao;
	}
	public Date getDtaexpedicaodoc() {
		return dtaexpedicaodoc;
	}
	public void setDtaexpedicaodoc(Date dtaexpedicaodoc) {
		this.dtaexpedicaodoc = dtaexpedicaodoc;
	}
	public String getInscrcaceal() {
		return inscrcaceal;
	}
	public void setInscrcaceal(String inscrcaceal) {
		this.inscrcaceal = inscrcaceal;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getIndusainstrboleto() {
		return indusainstrboleto;
	}
	public void setIndusainstrboleto(String indusainstrboleto) {
		this.indusainstrboleto = indusainstrboleto;
	}
	public String getInstrucaoboleto() {
		return instrucaoboleto;
	}
	public void setInstrucaoboleto(String instrucaoboleto) {
		this.instrucaoboleto = instrucaoboleto;
	}
	public String getTipolanctoindeniz() {
		return tipolanctoindeniz;
	}
	public void setTipolanctoindeniz(String tipolanctoindeniz) {
		this.tipolanctoindeniz = tipolanctoindeniz;
	}
	public Long getNrocnpjprodrural() {
		return nrocnpjprodrural;
	}
	public void setNrocnpjprodrural(Long nrocnpjprodrural) {
		this.nrocnpjprodrural = nrocnpjprodrural;
	}
	public Byte getDigcnpjprodrural() {
		return digcnpjprodrural;
	}
	public void setDigcnpjprodrural(Byte digcnpjprodrural) {
		this.digcnpjprodrural = digcnpjprodrural;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Pessoa [seqpessoa=" + seqpessoa + ", versao=" + versao
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
