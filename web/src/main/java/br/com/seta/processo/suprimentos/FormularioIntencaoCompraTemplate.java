package br.com.seta.processo.suprimentos;

import static br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA;
import static br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.page.BonitaPage;

public abstract class FormularioIntencaoCompraTemplate extends BonitaPage {
	private static final long serialVersionUID = 1L;

	private OrRequisicao requisicao = new OrRequisicao();
	private SolicitacaoIntencaoCompra solicitacao = new SolicitacaoIntencaoCompra();
	private Documento formularioCadastroItens;
	private Documento orcamento;
	private Documento formularioCadastroFornecedor;
	private PageReference paginaAnterior = null;
	
	private Form<Void> formFormulario;
	private WebMarkupContainer abaFaturamento, abaItens, abaFormaDePagamento, abaContabilizacao;
	private Panel secaoFaturamento, secaoItens, secaoFormaPagamento, secaoContabilizacao;
	
	public FormularioIntencaoCompraTemplate(PageParameters pageParameters)	throws HttpStatus401Exception, HttpStatus404Exception,	ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
		try {
			this.solicitacao = (SolicitacaoIntencaoCompra) retriveProcessVariable(SOLICITACAO_INTENCAO_COMPRA, SolicitacaoIntencaoCompra.class, new SolicitacaoIntencaoCompra());
			this.requisicao = (OrRequisicao) retriveProcessVariable(SOLICITACAO_INTENCAO_DTO, OrRequisicao.class, new OrRequisicao());
			iniciar();
		} catch (ParseException | InstantiationException
				| IllegalAccessException | HttpStatusException e) {
			throw new RuntimeException(e);
		}
	}
	
	public FormularioIntencaoCompraTemplate(PageParameters pageParameters, OrRequisicao requisicao) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this(pageParameters, requisicao, new SolicitacaoIntencaoCompra(), null);
	}
	
	public FormularioIntencaoCompraTemplate(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		super(pageParameters);
		
		this.paginaAnterior = paginaAnterior;
		this.requisicao = requisicao;
		this.solicitacao = solicitacao;
		
		iniciar();
	}

	private void iniciar() {
		this.formFormulario = new Form<Void>("formFormulario");		
		add(this.formFormulario);
		iniciaAbas();
	}
	
	public OrRequisicao getRequisicao(){
		return this.requisicao;
	}
	
	public void setRequisicao(OrRequisicao requisicao){
		this.requisicao = requisicao;
	}
	
	protected void addAbas(WebMarkupContainer ...abas){
		this.formFormulario.addOrReplace(abas);
	}
	
	protected void addSecaoBotoesAcao(Panel panel){
		this.formFormulario.add(panel);
	}
	
	protected void addSecaoFaturamento(Panel panel){
		this.formFormulario.addOrReplace(panel);
		this.secaoFaturamento = panel;
	}
	
	protected void addSecaoItens(Panel panel){
		this.formFormulario.addOrReplace(panel);
		this.secaoItens = panel;
	}
	
	protected void addSecaoFormaPagamento(Panel panel){
		this.formFormulario.addOrReplace(panel);
		this.secaoFormaPagamento = panel;
	}
	
	protected void addSecaoContabilizacao(Panel panel){
		this.formFormulario.addOrReplace(panel);
		this.secaoContabilizacao = panel;
	}
	
	protected void addAbaFaturamento(WebMarkupContainer aba){
		this.formFormulario.addOrReplace(aba);
		this.abaFaturamento = aba;
	}
	
	protected void addAbaItens(WebMarkupContainer aba){
		this.formFormulario.addOrReplace(aba);
		this.abaItens = aba;
	}
	
	protected void addAbaFormaPagamento(WebMarkupContainer aba){
		this.formFormulario.addOrReplace(aba);
		this.abaFormaDePagamento = aba;
	}
	
	protected void addAbaContabilizacao(WebMarkupContainer aba){
		this.formFormulario.addOrReplace(aba);
		this.abaContabilizacao = aba;
	}

	public Form<Void> getFormFormulario() {
		return formFormulario;
	}

	public WebMarkupContainer getAbaFaturamento() {
		return abaFaturamento;
	}

	public WebMarkupContainer getAbaItens() {
		return abaItens;
	}

	public WebMarkupContainer getAbaFormaDePagamento() {
		return abaFormaDePagamento;
	}

	public WebMarkupContainer getAbaContabilizacao() {
		return abaContabilizacao;
	}

	public Panel getSecaoFaturamento() {
		return secaoFaturamento;
	}

	public Panel getSecaoItens() {
		return secaoItens;
	}

	public Panel getSecaoFormaPagamento() {
		return secaoFormaPagamento;
	}

	public Panel getSecaoContabilizacao() {
		return secaoContabilizacao;
	}
	
	public Documento getFormularioCadastroItens() {
		return formularioCadastroItens;
	}

	public void setFormularioCadastroItens(Documento cadastroItens) {
		this.formularioCadastroItens = cadastroItens;
	}

	public Documento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Documento orcamento) {
		this.orcamento = orcamento;
	}
	
	public PageReference getPaginaAnterior(){
		return this.paginaAnterior;
	}
	
	public void setPaginaAnterior(PageReference paginaAnterior){
		this.paginaAnterior = paginaAnterior;
	}
	
	private void iniciaAbas(){
		WebMarkupContainer abaFaturamento = new WebMarkupContainer("abaFaturamento");
		WebMarkupContainer abaItens = new WebMarkupContainer("abaItens"); 
		WebMarkupContainer abaFormaDePagamento = new WebMarkupContainer("abaFormaDePagamento"); 
		WebMarkupContainer abaContabilizacao = new WebMarkupContainer("abaContabilizacao");	
		
		addAbas(abaFaturamento, abaItens, abaFormaDePagamento, abaContabilizacao);
	}

	public SolicitacaoIntencaoCompra getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoIntencaoCompra solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Documento getFormularioCadastroFornecedor() {
		return formularioCadastroFornecedor;
	}

	public void setFormularioCadastroFornecedor(
			Documento formularioCadastroFornecedor) {
		this.formularioCadastroFornecedor = formularioCadastroFornecedor;
	}
	
	
	
	
}
