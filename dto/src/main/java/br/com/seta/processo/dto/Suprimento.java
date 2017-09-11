package br.com.seta.processo.dto;

import java.util.Date;
import java.util.List;

import br.com.seta.processo.annotations.MongoColumm;
import br.com.seta.processo.annotations.MongoEntity;
import br.com.seta.processo.annotations.ReportColumn;
import br.com.seta.processo.classTypes.Classes;

@MongoEntity(collectionName="suprimento")
public class Suprimento implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeProcesso;
    
	@ReportColumn(property="responsavelPreenchimento",title="Preencimento", colClass=Classes.STRING)
    private String responsavelPreenchimento;
	
	private long   identificadorDoProcesso;
	private long   processoPai;
	
	@ReportColumn(property="identificadorInstancia",title="Id Instância", colClass=Classes.LONG)
	private long  identificadorInstancia;
	
	@ReportColumn(property="dataSolicitacao",title="Data solicitação", colClass=Classes.DATE)
	private Date dataSolicitacao;
	
	@ReportColumn(property="dataCriacao",title="Data criação", colClass=Classes.DATE)
	private Date dataCriacao;
	
	private String numeroRequisicao; 
	private String aprovadorFuncional;
	private String aprovadorHierarquico;
	
	@ReportColumn(property="nomeSolicitante",title="N. Solicitante", colClass=Classes.STRING)
	private String nomeSolicitante;
	
	@ReportColumn(property="areaSolicitante",title="A. Solicitante", colClass=Classes.STRING)
	private String areaSolicitante;
	
	@ReportColumn(property="emailSolicitante",title="E-Mail", colClass=Classes.STRING)
	private String emailSolicitante;
	
	@ReportColumn(property="nomeFornecedor",title="N. Fornecedor", colClass=Classes.STRING)
	private String nomeFornecedor;
	
	@ReportColumn(property="enderecoEntrega",title="Local Entrega", colClass=Classes.STRING)
	private String enderecoEntrega;
	
	private String faturamentoCNPJ;
	private long numeroParcelas;
	
	@ReportColumn(property="tipoDespesa",title="Tipo Despesa", colClass=Classes.STRING)
	private String tipoDespesa;
	
	@ReportColumn(property="valorFrete",title="Vrl. Frete", colClass=Classes.STRING)
	private String valorFrete;
	
	private String emailProcesso;
	private List<String> listaDeProduto;
	private String formaPagamento;	
	private String prazoPagamento;
	private java.util.Date dataExecucao;
	
	@MongoColumm(property="nomeProcesso")
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}
	
	@MongoColumm(property="responsavelPreenchimento")
	public String getResponsavelPreenchimento() {
		return responsavelPreenchimento;
	}
	public void setResponsavelPreenchimento(String responsavelPreenchimento) {
		this.responsavelPreenchimento = responsavelPreenchimento;
	}
	
	@MongoColumm(property="identificadorDoProcesso")
	public long getIdentificadorDoProcesso() {
		return identificadorDoProcesso;
	}
	public void setIdentificadorDoProcesso(long identificadorDoProcesso) {
		this.identificadorDoProcesso = identificadorDoProcesso;
	}
	
	@MongoColumm(property="processoPai")
	public long getProcessoPai() {
		return processoPai;
	}
	public void setProcessoPai(long processoPai) {
		this.processoPai = processoPai;
	}
		
	@MongoColumm(property="identificadorInstancia")
	public long getIdentificadorInstancia() {
		return identificadorInstancia;
	}
	public void setIdentificadorInstancia(long identificadorInstancia) {
		this.identificadorInstancia = identificadorInstancia;
	}
	
	@MongoColumm(property="dataSolicitacao")
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	
	@MongoColumm(property="dataCriacao")
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@MongoColumm(property="numeroRequisicao")
	public String getNumeroRequisicao() {
		return numeroRequisicao;
	}
	public void setNumeroRequisicao(String numeroRequisicao) {
		this.numeroRequisicao = numeroRequisicao;
	}
	
	@MongoColumm(property="aprovadorFuncional")
	public String getAprovadorFuncional() {
		return aprovadorFuncional;
	}
	public void setAprovadorFuncional(String aprovadorFuncional) {
		this.aprovadorFuncional = aprovadorFuncional;
	}
	
	@MongoColumm(property="aprovadorHierarquico")
	public String getAprovadorHierarquico() {
		return aprovadorHierarquico;
	}
	public void setAprovadorHierarquico(String aprovadorHierarquico) {
		this.aprovadorHierarquico = aprovadorHierarquico;
	}
	
	@MongoColumm(property="areaSolicitante")
	public String getAreaSolicitante() {
		return areaSolicitante;
	}
	public void setAreaSolicitante(String areaSolicitante) {
		this.areaSolicitante = areaSolicitante;
	}
	
	@MongoColumm(property="emailSolicitante")
	public String getEmailSolicitante() {
		return emailSolicitante;
	}
	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}
	
	@MongoColumm(property="nomeFornecedor")
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	
	@MongoColumm(property="enderecoEntrega")
	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}
	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	@MongoColumm(property="faturamentoCNPJ")
	public String getFaturamentoCNPJ() {
		return faturamentoCNPJ;
	}
	public void setFaturamentoCNPJ(String faturamentoCNPJ) {
		this.faturamentoCNPJ = faturamentoCNPJ;
	}
	
	@MongoColumm(property="numeroParcelas")
	public long getNumeroParcelas() {
		return numeroParcelas;
	}
	public void setNumeroParcelas(long numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	@MongoColumm(property="tipoDespesa")	
	public String getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
	
	@MongoColumm(property="valorFrete")
	public String getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(String valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	@MongoColumm(property="emailProcesso")
	public String getEmailProcesso() {
		return emailProcesso;
	}
	public void setEmailProcesso(String emailProcesso) {
		this.emailProcesso = emailProcesso;
	}
	
	@MongoColumm(property="listaDeProduto")
	public List<String> getListaDeProduto() {
		return listaDeProduto;
	}
	public void setListaDeProduto(List<String> listaDeProduto) {
		this.listaDeProduto = listaDeProduto;
	}
	
	
	@MongoColumm(property="formaPagamento")
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	@MongoColumm(property="prazoPagamento")
	public String getPrazoPagamento() {
		return prazoPagamento;
	}
	public void setPrazoPagamento(String prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}
	
	@MongoColumm(property="nomeSolicitante")
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	
	@MongoColumm(property="dataExecucao")
	public java.util.Date getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(java.util.Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	
	
}