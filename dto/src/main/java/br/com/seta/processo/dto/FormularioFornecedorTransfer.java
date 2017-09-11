package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.seta.processo.validacoesCheck.FornecedorPessoaFisicaCheck;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaJuridicaCheck;

public class FormularioFornecedorTransfer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tipoSolicatacao;
	private String categoria;
	private String comprador;
	@NotEmpty(message="Selecione um Fornecedor")
	private String tipoFornecedor;	
	private String razaoSocialFornedor;	
	@Size(max=30, message="A Razão Social Reduzida não pode exceder 30 caracteres", groups=FornecedorPessoaJuridicaCheck.class)
	@NotEmpty(message="A Razão Social Reduzida é obrigatório", groups=FornecedorPessoaJuridicaCheck.class)
	private String razaoSocialReduzido;	
	@Size(max=30, message="O nome não pode exceder 30 caracteres", groups=FornecedorPessoaFisicaCheck.class)
	@NotEmpty(message="O nome é obrigatório", groups=FornecedorPessoaFisicaCheck.class)
	private String nome;
	@Pattern(regexp="[0-9]{14}|[0-9]{0}", message="O CNPJ deve conter 14 dígitos e não possuir caracteres especiais", groups=FornecedorPessoaJuridicaCheck.class)
	private String cpnjJuridico;
	@Pattern(regexp="[0-9]{11}|[0-9]{0}", message="O CPF deve conter 11 dígitos e não possuir caracteres especiais", groups=FornecedorPessoaFisicaCheck.class)
	private String cpfFisica;
	private String inscricaoEstadual;
	private String rg;
	private java.util.Date dataFundacao;
	private java.util.Date dataNascimento;
	@Size(max=15, message="A inscrição municipal não pode exceder 15 caracteres", groups=FornecedorPessoaJuridicaCheck.class)
	private String inscricaoMunicipal;
	private String orgaoExpUF;
	private String simplesNacional;
	private java.util.Date dataExpedicao;
	private String estadoUf;
	@Size(max=30, message="A cidade não pode exceder 30 caracteres")
	private String cidade;
	@Size(max=12, message="O cep não pode exceder 12 caracteres")
	private String cep;
	@Size(max=30, message="O bairro não pode exceder 30 caracteres")
	private String bairro;	
	private String tipoLogradouro;
	@Size(max=35, message="O logradouro não pode exceder 35 caracteres")
	private String logradouro;
	@Size(max=10, message="O número do logradouro não pode exceder 10 caracteres")
	private String numero;
	@Size(max=10, message="O complemento não pode exceder 10 caracteres")
	private String complemento;
	@Size(max=50, message="O e-mail de contato não pode exceder 50 caracteres")
	private String emailContato;
	@Size(max=80, message="O site/homepage não pode exceder 80 caracteres")
	private String homePage;
	@Size(max=250, message="O e-mail NF-e não pode exceder 250 caracteres")
	private String emailParaNf;
	@Size(max=12, message="O telefone comercial não pode exceder 12 caracteres")
	private String foneComercial;
	@Size(max=12, message="O telefone fiscal não pode exceder 12 caracteres")
	private String foneFiscal;
	@Size(max=12, message="O telefone financeiro não pode exceder 12 caracteres")
	private String foneFinanceiro;	
	private String formaPagamento;
	private String tipoDaConta;
	private String tipoDeConta;
	private String bancoTitularConta;
	private String agenciaTitularConta;
	private String contaTitularConta;
	private String digitoAgenciaTitularConta;
	private String digitoContaTitularConta;
	private String nomeTitularConta;
	private String cpfCnpjTitularConta;
	private String enderecoTitularConta;
	private String numeroTitularConta;
	private String bairroTitularConta;
	private String cidadeTitularConta;
	private String cepTitularConta;
	private String prazoPagamento;
	private String observacaoTitularConta;
	
	
	private String recebeNfDevolucao;
	private String negocicacaoBoleto;
	private String trocaNfBonificacao;
	private String trocaProdutoProduto;
	private String trocaRecolheProduto;
	private String prazoEntrega;
	private String prazoAtrasoEntrega;
	private String prazoVisita;
	@NotEmpty(message="Selecione o tipo de frete")
	private String tipoFrete;
	private String observacaoEntrega;
	
	
	private String nomeCompletoReponsavelAcordo;
	private String cpfReponsavelAcordo;
	private String rgReponsavelAcordo;
	private String cargoReponsavelAcordo;
	private String foneReponsavelAcordo;
	private String emailReponsavelAcordo;
	
	
	private String nomePrepostoRepresentante;
	private String cpfPrepostoRepresentante;
	private String rgPrepostoRepresentante;
	private String cargoPrepostoRepresentante;
	private String fonePrepostoRepresentante;
	private String emailPrepostoRepresentante;
	
	private boolean ieProdutorRural;
	private boolean contribuinteIPI;
	private boolean microEmpresa;
	
	private String dadosAdicionais;
	
	private List<Erros> erros = new ArrayList<Erros>();
	private AprovacaoGerenciaComercial aprovacaoGerenciaComercial;
	private List<HistoricoFornecedor> historicoFornecedores = new ArrayList<HistoricoFornecedor>();
	private Date dataSolicitacao;	
	
	private String indenizaTroca;
	private String acordoComercial;
	
	public String getTipoSolicatacao() {
		return tipoSolicatacao;
	}
	public void setTipoSolicatacao(String tipoSolicatacao) {
		this.tipoSolicatacao = tipoSolicatacao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public String getTipoFornecedor() {
		return tipoFornecedor;
	}
	public void setTipoFornecedor(String tipoFornecedor) {
		this.tipoFornecedor = tipoFornecedor;
	}
	public String getRazaoSocialFornedor() {
		return razaoSocialFornedor;
	}
	public void setRazaoSocialFornedor(String razaoSocialFornedor) {
		this.razaoSocialFornedor = razaoSocialFornedor;
	}
	public String getRazaoSocialReduzido() {
		return razaoSocialReduzido;
	}
	public void setRazaoSocialReduzido(String razaoSocialReduzido) {
		this.razaoSocialReduzido = razaoSocialReduzido;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpnjJuridico() {
		return cpnjJuridico;
	}
	public void setCpnjJuridico(String cpnjJuridico) {
		this.cpnjJuridico = cpnjJuridico;
	}
	public String getCpfFisica() {
		return cpfFisica;
	}
	public void setCpfFisica(String cpfFisica) {
		this.cpfFisica = cpfFisica;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public java.util.Date getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(java.util.Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	public java.util.Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(java.util.Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}
	public String getOrgaoExpUF() {
		return orgaoExpUF;
	}
	public void setOrgaoExpUF(String orgaoExpUF) {
		this.orgaoExpUF = orgaoExpUF;
	}
	public String getSimplesNacional() {
		return simplesNacional;
	}
	public void setSimplesNacional(String simplesNacional) {
		this.simplesNacional = simplesNacional;
	}
	public java.util.Date getDataExpedicao() {
		return dataExpedicao;
	}
	public void setDataExpedicao(java.util.Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}
	public String getEstadoUf() {
		return estadoUf;
	}
	public void setEstadoUf(String estadoUf) {
		this.estadoUf = estadoUf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getEmailContato() {
		return emailContato;
	}
	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getEmailParaNf() {
		return emailParaNf;
	}
	public void setEmailParaNf(String emailParaNf) {
		this.emailParaNf = emailParaNf;
	}
	public String getFoneComercial() {
		return foneComercial;
	}
	public void setFoneComercial(String foneComercial) {
		this.foneComercial = foneComercial;
	}
	public String getFoneFiscal() {
		return foneFiscal;
	}
	public void setFoneFiscal(String foneFiscal) {
		this.foneFiscal = foneFiscal;
	}
	public String getFoneFinanceiro() {
		return foneFinanceiro;
	}
	public void setFoneFinanceiro(String foneFinanceiro) {
		this.foneFinanceiro = foneFinanceiro;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getTipoDaConta() {
		return tipoDaConta;
	}
	public void setTipoDaConta(String tipoDaConta) {
		this.tipoDaConta = tipoDaConta;
	}
	public String getTipoDeConta() {
		return tipoDeConta;
	}
	public void setTipoDeConta(String tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}
	public String getBancoTitularConta() {
		return bancoTitularConta;
	}
	public void setBancoTitularConta(String bancoTitularConta) {
		this.bancoTitularConta = bancoTitularConta;
	}
	public String getAgenciaTitularConta() {
		return agenciaTitularConta;
	}
	public void setAgenciaTitularConta(String agenciaTitularConta) {
		this.agenciaTitularConta = agenciaTitularConta;
	}
	public String getContaTitularConta() {
		return contaTitularConta;
	}
	public void setContaTitularConta(String contaTitularConta) {
		this.contaTitularConta = contaTitularConta;
	}
	public String getDigitoAgenciaTitularConta() {
		return digitoAgenciaTitularConta;
	}
	public void setDigitoAgenciaTitularConta(String digitoAgenciaTitularConta) {
		this.digitoAgenciaTitularConta = digitoAgenciaTitularConta;
	}
	public String getDigitoContaTitularConta() {
		return digitoContaTitularConta;
	}
	public void setDigitoContaTitularConta(String digitoContaTitularConta) {
		this.digitoContaTitularConta = digitoContaTitularConta;
	}
	public String getNomeTitularConta() {
		return nomeTitularConta;
	}
	public void setNomeTitularConta(String nomeTitularConta) {
		this.nomeTitularConta = nomeTitularConta;
	}
	public String getCpfCnpjTitularConta() {
		return cpfCnpjTitularConta;
	}
	public void setCpfCnpjTitularConta(String cpfCnpjTitularConta) {
		this.cpfCnpjTitularConta = cpfCnpjTitularConta;
	}
	public String getEnderecoTitularConta() {
		return enderecoTitularConta;
	}
	public void setEnderecoTitularConta(String enderecoTitularConta) {
		this.enderecoTitularConta = enderecoTitularConta;
	}
	public String getNumeroTitularConta() {
		return numeroTitularConta;
	}
	public void setNumeroTitularConta(String numeroTitularConta) {
		this.numeroTitularConta = numeroTitularConta;
	}
	public String getBairroTitularConta() {
		return bairroTitularConta;
	}
	public void setBairroTitularConta(String bairroTitularConta) {
		this.bairroTitularConta = bairroTitularConta;
	}
	public String getCidadeTitularConta() {
		return cidadeTitularConta;
	}
	public void setCidadeTitularConta(String cidadeTitularConta) {
		this.cidadeTitularConta = cidadeTitularConta;
	}
	public String getCepTitularConta() {
		return cepTitularConta;
	}
	public void setCepTitularConta(String cepTitularConta) {
		this.cepTitularConta = cepTitularConta;
	}
	public String getPrazoPagamento() {
		return prazoPagamento;
	}
	public void setPrazoPagamento(String prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}
	public String getObservacaoTitularConta() {
		return observacaoTitularConta;
	}
	public void setObservacaoTitularConta(String observacaoTitularConta) {
		this.observacaoTitularConta = observacaoTitularConta;
	}
	public String getRecebeNfDevolucao() {
		return recebeNfDevolucao;
	}
	public void setRecebeNfDevolucao(String recebeNfDevolucao) {
		this.recebeNfDevolucao = recebeNfDevolucao;
	}
	public String getNegocicacaoBoleto() {
		return negocicacaoBoleto;
	}
	public void setNegocicacaoBoleto(String negocicacaoBoleto) {
		this.negocicacaoBoleto = negocicacaoBoleto;
	}
	public String getTrocaNfBonificacao() {
		return trocaNfBonificacao;
	}
	public void setTrocaNfBonificacao(String trocaNfBonificacao) {
		this.trocaNfBonificacao = trocaNfBonificacao;
	}
	public String getTrocaProdutoProduto() {
		return trocaProdutoProduto;
	}
	public void setTrocaProdutoProduto(String trocaProdutoProduto) {
		this.trocaProdutoProduto = trocaProdutoProduto;
	}
	public String getTrocaRecolheProduto() {
		return trocaRecolheProduto;
	}
	public void setTrocaRecolheProduto(String trocaRecolheProduto) {
		this.trocaRecolheProduto = trocaRecolheProduto;
	}
	public String getPrazoEntrega() {
		return prazoEntrega;
	}
	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	public String getPrazoAtrasoEntrega() {
		return prazoAtrasoEntrega;
	}
	public void setPrazoAtrasoEntrega(String prazoAtrasoEntrega) {
		this.prazoAtrasoEntrega = prazoAtrasoEntrega;
	}
	public String getPrazoVisita() {
		return prazoVisita;
	}
	public void setPrazoVisita(String prazoVisita) {
		this.prazoVisita = prazoVisita;
	}
	public String getTipoFrete() {
		return tipoFrete;
	}
	public void setTipoFrete(String tipoFrete) {
		this.tipoFrete = tipoFrete;
	}
	public String getObservacaoEntrega() {
		return observacaoEntrega;
	}
	public void setObservacaoEntrega(String observacaoEntrega) {
		this.observacaoEntrega = observacaoEntrega;
	}
	public String getNomeCompletoReponsavelAcordo() {
		return nomeCompletoReponsavelAcordo;
	}
	public void setNomeCompletoReponsavelAcordo(String nomeCompletoReponsavelAcordo) {
		this.nomeCompletoReponsavelAcordo = nomeCompletoReponsavelAcordo;
	}
	public String getCpfReponsavelAcordo() {
		return cpfReponsavelAcordo;
	}
	public void setCpfReponsavelAcordo(String cpfReponsavelAcordo) {
		this.cpfReponsavelAcordo = cpfReponsavelAcordo;
	}
	public String getRgReponsavelAcordo() {
		return rgReponsavelAcordo;
	}
	public void setRgReponsavelAcordo(String rgReponsavelAcordo) {
		this.rgReponsavelAcordo = rgReponsavelAcordo;
	}
	public String getCargoReponsavelAcordo() {
		return cargoReponsavelAcordo;
	}
	public void setCargoReponsavelAcordo(String cargoReponsavelAcordo) {
		this.cargoReponsavelAcordo = cargoReponsavelAcordo;
	}
	public String getFoneReponsavelAcordo() {
		return foneReponsavelAcordo;
	}
	public void setFoneReponsavelAcordo(String foneReponsavelAcordo) {
		this.foneReponsavelAcordo = foneReponsavelAcordo;
	}
	public String getEmailReponsavelAcordo() {
		return emailReponsavelAcordo;
	}
	public void setEmailReponsavelAcordo(String emailReponsavelAcordo) {
		this.emailReponsavelAcordo = emailReponsavelAcordo;
	}
	public String getNomePrepostoRepresentante() {
		return nomePrepostoRepresentante;
	}
	public void setNomePrepostoRepresentante(String nomePrepostoRepresentante) {
		this.nomePrepostoRepresentante = nomePrepostoRepresentante;
	}
	public String getCpfPrepostoRepresentante() {
		return cpfPrepostoRepresentante;
	}
	public void setCpfPrepostoRepresentante(String cpfPrepostoRepresentante) {
		this.cpfPrepostoRepresentante = cpfPrepostoRepresentante;
	}
	public String getRgPrepostoRepresentante() {
		return rgPrepostoRepresentante;
	}
	public void setRgPrepostoRepresentante(String rgPrepostoRepresentante) {
		this.rgPrepostoRepresentante = rgPrepostoRepresentante;
	}
	public String getCargoPrepostoRepresentante() {
		return cargoPrepostoRepresentante;
	}
	public void setCargoPrepostoRepresentante(String cargoPrepostoRepresentante) {
		this.cargoPrepostoRepresentante = cargoPrepostoRepresentante;
	}
	public String getFonePrepostoRepresentante() {
		return fonePrepostoRepresentante;
	}
	public void setFonePrepostoRepresentante(String fonePrepostoRepresentante) {
		this.fonePrepostoRepresentante = fonePrepostoRepresentante;
	}
	public String getEmailPrepostoRepresentante() {
		return emailPrepostoRepresentante;
	}
	public void setEmailPrepostoRepresentante(String emailPrepostoRepresentante) {
		this.emailPrepostoRepresentante = emailPrepostoRepresentante;
	}
	public AprovacaoGerenciaComercial getAprovacaoGerenciaComercial() {
		return aprovacaoGerenciaComercial;
	}
	public void setAprovacaoGerenciaComercial(
			AprovacaoGerenciaComercial aprovacaoGerenciaComercial) {
		this.aprovacaoGerenciaComercial = aprovacaoGerenciaComercial;
	}
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public boolean isIeProdutorRural() {
		return ieProdutorRural;
	}
	public void setIeProdutorRural(boolean ieProdutorRural) {
		this.ieProdutorRural = ieProdutorRural;
	}
	public boolean isContribuinteIPI() {
		return contribuinteIPI;
	}
	public void setContribuinteIPI(boolean contribuinteIPI) {
		this.contribuinteIPI = contribuinteIPI;
	}
	public boolean isMicroEmpresa() {
		return microEmpresa;
	}
	public void setMicroEmpresa(boolean microEmpresa) {
		this.microEmpresa = microEmpresa;
	}
	public String getDadosAdicionais() {
		return dadosAdicionais;
	}
	public void setDadosAdicionais(String dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}
	public List<Erros> getErros() {
		return erros;
	}
	public void setErros(List<Erros> erros) {
		this.erros = erros;
	}
	public List<HistoricoFornecedor> getHistoricoFornecedores() {
		return historicoFornecedores;
	}
	public void setHistoricoFornecedores(List<HistoricoFornecedor> historicoFornecedores) {
		this.historicoFornecedores = historicoFornecedores;
	}
	
	public String getIndenizaTroca() {
		return indenizaTroca;
	}
	public void setIndenizaTroca(String indenizaTroca) {
		this.indenizaTroca = indenizaTroca;
	}
	public String getAcordoComercial() {
		return acordoComercial;
	}
	public void setAcordoComercial(String acordoComercial) {
		this.acordoComercial = acordoComercial;
	}
	@Override
	public String toString() {
		return "FormularioFonecedor [tipoSolicatacao=" + tipoSolicatacao
				+ ", categoria=" + categoria + ", comprador=" + comprador
				+ ", tipoFornecedor=" + tipoFornecedor
				+ ", razaoSocialFornedor=" + razaoSocialFornedor
				+ ", razaoSocialReduzido=" + razaoSocialReduzido + ", nome="
				+ nome + ", cpnjJuridico=" + cpnjJuridico + ", cpfFisica="
				+ cpfFisica + ", inscricaoEstadual=" + inscricaoEstadual
				+ ", rg=" + rg + ", dataFundacao=" + dataFundacao
				+ ", dataNascimento=" + dataNascimento
				+ ", inscricaoMunicipal=" + inscricaoMunicipal
				+ ", orgaoExpUF=" + orgaoExpUF + ", simplesNacional="
				+ simplesNacional + ", dataExpedicao=" + dataExpedicao
				+ ", estadoUf=" + estadoUf + ", cidade=" + cidade + ", cep="
				+ cep + ", bairro=" + bairro + ", tipo=" + tipoLogradouro
				+ ", logradouro=" + logradouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", emailContato="
				+ emailContato + ", homePage=" + homePage + ", emailParaNf="
				+ emailParaNf + ", foneComercial=" + foneComercial
				+ ", foneFiscal=" + foneFiscal + ", foneFinanceiro="
				+ foneFinanceiro + ", formaPagamento=" + formaPagamento
				+ ", tipoDaConta=" + tipoDaConta + ", tipoDeConta="
				+ tipoDeConta + ", bancoTitularConta=" + bancoTitularConta
				+ ", agenciaTitularConta=" + agenciaTitularConta
				+ ", contaTitularConta=" + contaTitularConta
				+ ", digitoAgenciaTitularConta=" + digitoAgenciaTitularConta
				+ ", digitoContaTitularConta=" + digitoContaTitularConta
				+ ", nomeTitularConta=" + nomeTitularConta
				+ ", cpfCnpjTitularConta=" + cpfCnpjTitularConta
				+ ", enderecoTitularConta=" + enderecoTitularConta
				+ ", numeroTitularConta=" + numeroTitularConta
				+ ", bairroTitularConta=" + bairroTitularConta
				+ ", cidadeTitularConta=" + cidadeTitularConta
				+ ", cepTitularConta=" + cepTitularConta + ", prazoPagamento="
				+ prazoPagamento + ", observacaoTitularConta="
				+ observacaoTitularConta + ", recebeNfDevolucao="
				+ recebeNfDevolucao + ", negocicacaoBoleto="
				+ negocicacaoBoleto + ", trocaNfBonificacao="
				+ trocaNfBonificacao + ", trocaProdutoProduto="
				+ trocaProdutoProduto + ", trocaRecolheProduto="
				+ trocaRecolheProduto + ", prazoEntrega=" + prazoEntrega
				+ ", prazoAtrasoEntrega=" + prazoAtrasoEntrega
				+ ", prazoVisita=" + prazoVisita + ", tipoFrete=" + tipoFrete
				+ ", observacao=" + observacaoEntrega
				+ ", nomeCompletoReponsavelAcordo="
				+ nomeCompletoReponsavelAcordo + ", cpfReponsavelAcordo="
				+ cpfReponsavelAcordo + ", rgReponsavelAcordo="
				+ rgReponsavelAcordo + ", cargoReponsavelAcordo="
				+ cargoReponsavelAcordo + ", foneReponsavelAcordo="
				+ foneReponsavelAcordo + ", emailReponsavelAcordo="
				+ emailReponsavelAcordo + ", nomePrepostoRepresentante="
				+ nomePrepostoRepresentante + ", cpfPrepostoRepresentante="
				+ cpfPrepostoRepresentante + ", rgPrepostoRepresentante="
				+ rgPrepostoRepresentante + ", cargoPrepostoRepresentante="
				+ cargoPrepostoRepresentante + ", fonePrepostoRepresentante="
				+ fonePrepostoRepresentante + ", emailPrepostoRepresentante="
				+ emailPrepostoRepresentante + ", ieProdutorRural="
				+ ieProdutorRural + ", contribuinteIPI=" + contribuinteIPI
				+ ", microEmpresa=" + microEmpresa + ", dadosAdicionais="
				+ dadosAdicionais + ", erros=" + erros
				+ ", aprovacaoGerenciaComercial=" + aprovacaoGerenciaComercial
				+ ", historicoFornecedors=" + historicoFornecedores
				+ ", dataSolicitacao=" + dataSolicitacao + "]";
	}
	


}
