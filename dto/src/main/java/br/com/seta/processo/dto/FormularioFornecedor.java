package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import br.com.seta.processo.validacoes.NoMinimoUmPreenchido;
import br.com.seta.processo.validacoes.SePreenchidoVerificaTambem;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaFisicaCheck;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaJuridicaCheck;

@Entity("FormularioFornecedor")
@NoMinimoUmPreenchido(value={"foneComercial", "foneFiscal", "foneFinanceiro"}, message="Informe ao menos um telefone de contato")
@SePreenchidoVerificaTambem.List({
	@SePreenchidoVerificaTambem(campo="foneComercial", camposAVerificar={"ddFoneComercial"}, message="Preencha também o ddd do Telefone Comercial"),
	@SePreenchidoVerificaTambem(campo="foneFiscal", camposAVerificar={"ddFoneFiscal"}, message="Preencha também o ddd do Telefone Fiscal"),
	@SePreenchidoVerificaTambem(campo="foneFinanceiro", camposAVerificar={"ddFoneFinanceiro"}, message="Preencha também o ddd do Telefone Financeiro")
})
public class FormularioFornecedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String idUsuario;
	
	private Integer caseId;
	private String idFornecedorC5;
	private Date dataSolicitacao;
	private String tipoSolicatacao;
	private String categoria;
	private String comprador;
	@NotEmpty(message="Selecione um Fornecedor") 
	private String tipoFornecedor;	
	@NotBlank(message="Informe o Tipo da Pessoa")
	private String tipoPessoa;
	private String razaoSocialFornedor;	
	
	@Size(max=30, message="A Razão Social Reduzida não pode exceder 30 caracteres", groups=FornecedorPessoaJuridicaCheck.class)
	@NotEmpty(message="A Razão Social Reduzida é obrigatório", groups=FornecedorPessoaJuridicaCheck.class)
	private String razaoSocialReduzido;	
	
	private String nome;
		
	@Size(max=30, message="O Nome Reduzido não pode exceder 30 caracteres", groups=FornecedorPessoaFisicaCheck.class)
	@NotEmpty(message="O Nome Reduzido é obrigatório", groups=FornecedorPessoaFisicaCheck.class)
	private String nomeReduzido;
		
	@Pattern(regexp="[0-9]{14}", message="O CNPJ deve conter 14 dígitos e não possuir caracteres especiais", groups=FornecedorPessoaJuridicaCheck.class)
	@NotEmpty(message="O número do CNPJ ou do CPF é obrigatório" , groups=FornecedorPessoaJuridicaCheck.class)
	@CNPJ(message="O CNPJ está inválido" , groups=FornecedorPessoaJuridicaCheck.class)
	private String cpnjJuridico;
	
	private String digCpnjJuridico;
	
	@Pattern(regexp="[0-9]{11}", message="O CPF deve conter 11 dígitos e não possuir caracteres especiais", groups=FornecedorPessoaFisicaCheck.class)
	@NotEmpty(message="O número do CNPJ ou do CPF é obrigatório" , groups=FornecedorPessoaFisicaCheck.class)
	@CPF(message="O CPF está inválido" , groups=FornecedorPessoaFisicaCheck.class)
	private String cpfFisica;
	
	private String digCpfFisica;
	
	
	private String inscricaoEstadual;
	private String rg;
	private java.util.Date dataFundacao;
	private java.util.Date dataNascimento;
	@Size(max=15, message="A inscrição municipal não pode exceder 15 caracteres", groups=FornecedorPessoaJuridicaCheck.class)
	private String inscricaoMunicipal;
	@Pattern(regexp="^[a-z A-Z]{1,6}/[a-z A-Z]{2}$", message="Orgão Emissor/UF deve estar no formato  Orgão Emissor/UF, sendo que o Orgão Emissor deve conter no máximo 6 caracteres e UF dois caracteres", groups=FornecedorPessoaFisicaCheck.class)
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
	
	@NotBlank(message="O e-mail de contato é obrigatório")
	@Email(message="O e-mail de contato está inválido")
	@Size(max=50, message="O e-mail de contato não pode exceder 50 caracteres")
	private String emailContato;
	
	@Size(max=80, message="O site/homepage não pode exceder 80 caracteres")
	private String homePage;
	
	@Email(message="O e-mail para NF-e está inválido")
	@Size(max=50, message="O e-mail de contato não pode exceder 50 caracteres")
	private String emailParaNf;
	
	@Size(max=10, message="O telefone comercial não pode exceder 10 caracteres")
	private String foneComercial;
	
	
	@Size(max=10, message="O telefone fiscal não pode exceder 10 caracteres")
	private String foneFiscal;
	
	
	@Size(max=10, message="O telefone financeiro não pode exceder 10 caracteres")
	private String foneFinanceiro;
	
	
	
	@Size(max=2, message="O ddd do telefone comercial não pode exceder 2 caracteres")
	private String ddFoneComercial;
	
	
	@Size(max=2, message="O ddd do telefone fiscal não pode exceder 2 caracteres")
	private String ddFoneFiscal;
	
	
	@Size(max=2, message="O ddd do telefone financeiro não pode exceder 2 caracteres")
	private String ddFoneFinanceiro;
	
	
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
	private String complementoTitularConta;
	private String bairroTitularConta;
	private String cidadeTitularConta;
	private String ufTitularConta;
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
	
	@NotEmpty(message="O nome do responsável para acordos é obrigatório")
	@Size(max=40, message="O nome do responsável para acordos não pode exceder 40 caracteres")
	private String nomeCompletoReponsavelAcordo;
	
	@Pattern(regexp="[0-9]{11}", message="O CPF do responsável para acordos deve conter 11 dígitos e não possuir caracteres especiais")
	@CPF(message="O CPF do responsável para acordos está inválido")
	private String cpfReponsavelAcordo;
	@Size(max=20, message="O rg do responsável para acordos não pode exceder 40 caracteres")
	private String rgReponsavelAcordo;
	
	@Size(max=40, message="O cargo do responsável para acordos não pode exceder 40 caracteres")
	private String cargoReponsavelAcordo;
	@Size(max=10, message="O telefone do responsável para acordos não pode exceder 10 caracteres")
	private String foneReponsavelAcordo;
	
	@Size(max=2, message="O ddd do telefone do responsável para acordos não pode exceder 2 caracteres")
	private String ddFoneReponsavelAcordo;
	
	
	@Email(message="O e-mail responsável para acordos está inválido")
	@Size(max=50, message="O e-mail de contato do responsável para acordos não pode exceder 50 caracteres")
	private String emailReponsavelAcordo;
	
	@NotEmpty(message="O nome do preposto é obrigatório")
	@Size(max=40, message="O nome do preposto não pode exceder 40 caracteres")
	private String nomePrepostoRepresentante;
	
	@Pattern(regexp="[0-9]{11}", message="O CPF do preposto deve conter 11 dígitos e não possuir caracteres especiais")
	@CPF(message="O CPF do preposto está inválido")
	private String cpfPrepostoRepresentante;
	
	@Size(max=20, message="O rg do preposto não pode exceder 40 caracteres")
	private String rgPrepostoRepresentante;
	@Size(max=40, message="O cargo do preposto não pode exceder 40 caracteres")
	private String cargoPrepostoRepresentante;
	@Size(max=10, message="O telefone do preposto não pode exceder 10 caracteres")
	private String fonePrepostoRepresentante;	
	@Size(max=2, message="O ddd do telefone do preposto não pode exceder 2 caracteres")
	private String ddFonePrepostoRepresentante;
	
	@Email(message="O e-mail de contato do preposto está inválido")
	@Size(max=50, message="O e-mail de contato do preposto não pode exceder 50 caracteres")
	private String emailPrepostoRepresentante;
	
	private boolean ieProdutorRural;
	private boolean contribuinteIPI;
	private boolean microEmpresa;
	
	private String dadosAdicionais;
	private String identificador;
	private String isPrecadastro;
	private String initUserMail;
	private String emailsAprovadorNovosCadastros;
	private String emailsCadastro;
	
	
	private List<Erros> erros = new ArrayList<Erros>();
	private AprovacaoGerenciaComercial aprovacaoGerenciaComercial;
	
	@Embedded
	private List<HistoricoFornecedor> historicoFornecedores = new ArrayList<HistoricoFornecedor>();
	
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

	public String getNomeReduzido() {
		return nomeReduzido;
	}

	public void setNomeReduzido(String nomeReduzido) {
		this.nomeReduzido = nomeReduzido;
	}

	public String getCpnjJuridico() {
		return cpnjJuridico;
	}

	public void setCpnjJuridico(String cpnjJuridico) {
		this.cpnjJuridico = cpnjJuridico;
	}

	public String getDigCpnjJuridico() {
		return digCpnjJuridico;
	}

	public void setDigCpnjJuridico(String digCpnjJuridico) {
		this.digCpnjJuridico = digCpnjJuridico;
	}

	public String getCpfFisica() {
		return cpfFisica;
	}

	public void setCpfFisica(String cpfFisica) {
		this.cpfFisica = cpfFisica;
	}

	public String getDigCpfFisica() {
		return digCpfFisica;
	}

	public void setDigCpfFisica(String digCpfFisica) {
		this.digCpfFisica = digCpfFisica;
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

	public String getDdFoneComercial() {
		return ddFoneComercial;
	}

	public void setDdFoneComercial(String ddFoneComercial) {
		this.ddFoneComercial = ddFoneComercial;
	}

	public String getDdFoneFiscal() {
		return ddFoneFiscal;
	}

	public void setDdFoneFiscal(String ddFoneFiscal) {
		this.ddFoneFiscal = ddFoneFiscal;
	}

	public String getDdFoneFinanceiro() {
		return ddFoneFinanceiro;
	}

	public void setDdFoneFinanceiro(String ddFoneFinanceiro) {
		this.ddFoneFinanceiro = ddFoneFinanceiro;
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

	public String getDdFoneReponsavelAcordo() {
		return ddFoneReponsavelAcordo;
	}

	public void setDdFoneReponsavelAcordo(String ddFoneReponsavelAcordo) {
		this.ddFoneReponsavelAcordo = ddFoneReponsavelAcordo;
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

	public String getDdFonePrepostoRepresentante() {
		return ddFonePrepostoRepresentante;
	}

	public void setDdFonePrepostoRepresentante(String ddFonePrepostoRepresentante) {
		this.ddFonePrepostoRepresentante = ddFonePrepostoRepresentante;
	}

	public String getEmailPrepostoRepresentante() {
		return emailPrepostoRepresentante;
	}

	public void setEmailPrepostoRepresentante(String emailPrepostoRepresentante) {
		this.emailPrepostoRepresentante = emailPrepostoRepresentante;
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

	public AprovacaoGerenciaComercial getAprovacaoGerenciaComercial() {
		return aprovacaoGerenciaComercial;
	}

	public void setAprovacaoGerenciaComercial(AprovacaoGerenciaComercial aprovacaoGerenciaComercial) {
		this.aprovacaoGerenciaComercial = aprovacaoGerenciaComercial;
	}

	public List<HistoricoFornecedor> getHistoricoFornecedores() {
		return historicoFornecedores;
	}

	public void setHistoricoFornecedores(List<HistoricoFornecedor> historicoFornecedores) {
		this.historicoFornecedores = historicoFornecedores;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getIdFornecedorC5() {
		return idFornecedorC5;
	}

	public void setIdFornecedorC5(String idFornecedorC5) {
		this.idFornecedorC5 = idFornecedorC5;
	}

		
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getComplementoTitularConta() {
		return complementoTitularConta;
	}

	public void setComplementoTitularConta(String complementoTitularConta) {
		this.complementoTitularConta = complementoTitularConta;
	}

	public String getUfTitularConta() {
		return ufTitularConta;
	}

	public void setUfTitularConta(String ufTitularConta) {
		this.ufTitularConta = ufTitularConta;
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

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getIsPrecadastro() {
		return isPrecadastro;
	}

	public void setIsPrecadastro(String isPrecadastro) {
		this.isPrecadastro = isPrecadastro;
	}

	public String getInitUserMail() {
		return initUserMail;
	}

	public void setInitUserMail(String initUserMail) {
		this.initUserMail = initUserMail;
	}

	public String getEmailsAprovadorNovosCadastros() {
		return emailsAprovadorNovosCadastros;
	}

	public void setEmailsAprovadorNovosCadastros(
			String emailsAprovadorNovosCadastros) {
		this.emailsAprovadorNovosCadastros = emailsAprovadorNovosCadastros;
	}

	public String getEmailsCadastro() {
		return emailsCadastro;
	}

	public void setEmailsCadastro(String emailsCadastro) {
		this.emailsCadastro = emailsCadastro;
	}
	

	@Override
	public String toString() {
		return "FormularioFornecedor [idUsuario=" + idUsuario + ", caseId="
				+ caseId + ", idFornecedorC5=" + idFornecedorC5
				+ ", dataSolicitacao=" + dataSolicitacao + ", tipoSolicatacao="
				+ tipoSolicatacao + ", categoria=" + categoria + ", comprador="
				+ comprador + ", tipoFornecedor=" + tipoFornecedor
				+ ", tipoPessoa=" + tipoPessoa + ", razaoSocialFornedor="
				+ razaoSocialFornedor + ", razaoSocialReduzido="
				+ razaoSocialReduzido + ", nome=" + nome + ", nomeReduzido="
				+ nomeReduzido + ", cpnjJuridico=" + cpnjJuridico
				+ ", digCpnjJuridico=" + digCpnjJuridico + ", cpfFisica="
				+ cpfFisica + ", digCpfFisica=" + digCpfFisica
				+ ", inscricaoEstadual=" + inscricaoEstadual + ", rg=" + rg
				+ ", dataFundacao=" + dataFundacao + ", dataNascimento="
				+ dataNascimento + ", inscricaoMunicipal=" + inscricaoMunicipal
				+ ", orgaoExpUF=" + orgaoExpUF + ", simplesNacional="
				+ simplesNacional + ", dataExpedicao=" + dataExpedicao
				+ ", estadoUf=" + estadoUf + ", cidade=" + cidade + ", cep="
				+ cep + ", bairro=" + bairro + ", tipoLogradouro="
				+ tipoLogradouro + ", logradouro=" + logradouro + ", numero="
				+ numero + ", complemento=" + complemento + ", emailContato="
				+ emailContato + ", homePage=" + homePage + ", emailParaNf="
				+ emailParaNf + ", foneComercial=" + foneComercial
				+ ", foneFiscal=" + foneFiscal + ", foneFinanceiro="
				+ foneFinanceiro + ", ddFoneComercial=" + ddFoneComercial
				+ ", ddFoneFiscal=" + ddFoneFiscal + ", ddFoneFinanceiro="
				+ ddFoneFinanceiro + ", formaPagamento=" + formaPagamento
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
				+ ", complementoTitularConta=" + complementoTitularConta
				+ ", bairroTitularConta=" + bairroTitularConta
				+ ", cidadeTitularConta=" + cidadeTitularConta
				+ ", ufTitularConta=" + ufTitularConta + ", cepTitularConta="
				+ cepTitularConta + ", prazoPagamento=" + prazoPagamento
				+ ", observacaoTitularConta=" + observacaoTitularConta
				+ ", recebeNfDevolucao=" + recebeNfDevolucao
				+ ", negocicacaoBoleto=" + negocicacaoBoleto
				+ ", trocaNfBonificacao=" + trocaNfBonificacao
				+ ", trocaProdutoProduto=" + trocaProdutoProduto
				+ ", trocaRecolheProduto=" + trocaRecolheProduto
				+ ", prazoEntrega=" + prazoEntrega + ", prazoAtrasoEntrega="
				+ prazoAtrasoEntrega + ", prazoVisita=" + prazoVisita
				+ ", tipoFrete=" + tipoFrete + ", observacaoEntrega="
				+ observacaoEntrega + ", nomeCompletoReponsavelAcordo="
				+ nomeCompletoReponsavelAcordo + ", cpfReponsavelAcordo="
				+ cpfReponsavelAcordo + ", rgReponsavelAcordo="
				+ rgReponsavelAcordo + ", cargoReponsavelAcordo="
				+ cargoReponsavelAcordo + ", foneReponsavelAcordo="
				+ foneReponsavelAcordo + ", ddFoneReponsavelAcordo="
				+ ddFoneReponsavelAcordo + ", emailReponsavelAcordo="
				+ emailReponsavelAcordo + ", nomePrepostoRepresentante="
				+ nomePrepostoRepresentante + ", cpfPrepostoRepresentante="
				+ cpfPrepostoRepresentante + ", rgPrepostoRepresentante="
				+ rgPrepostoRepresentante + ", cargoPrepostoRepresentante="
				+ cargoPrepostoRepresentante + ", fonePrepostoRepresentante="
				+ fonePrepostoRepresentante + ", ddFonePrepostoRepresentante="
				+ ddFonePrepostoRepresentante + ", emailPrepostoRepresentante="
				+ emailPrepostoRepresentante + ", ieProdutorRural="
				+ ieProdutorRural + ", contribuinteIPI=" + contribuinteIPI
				+ ", microEmpresa=" + microEmpresa + ", dadosAdicionais="
				+ dadosAdicionais + ", identificador=" + identificador
				+ ", isPrecadastro=" + isPrecadastro + ", initUserMail="
				+ initUserMail + ", emailsAprovadorNovosCadastros="
				+ emailsAprovadorNovosCadastros + ", emailsCadastro="
				+ emailsCadastro + ", erros=" + erros
				+ ", aprovacaoGerenciaComercial=" + aprovacaoGerenciaComercial
				+ ", historicoFornecedores=" + historicoFornecedores
				+ ", indenizaTroca=" + indenizaTroca + ", acordoComercial="
				+ acordoComercial + "]";
	}



}
