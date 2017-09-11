package br.com.seta.processo.suprimentos;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.suprimentos.componentespagina.secoes.SecaoVazia;

public abstract class FormularioCadastroItensTemplate extends BonitaPage {
	private static final long serialVersionUID = 1L;

	private OrRequisicao requisicao = new OrRequisicao();
	private SolicitacaoIntencaoCompra solicitacao = new SolicitacaoIntencaoCompra();
	private PageReference paginaAnterior;
	
	private Form<Void> formFormulario;
	private Panel secaoFaturamento, secaoCadastrarItens;
	private WebMarkupContainer abaFaturamento, abaCadastrarItens;	
	
	public FormularioCadastroItensTemplate(PageParameters pageParameters)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
	}
	
	public FormularioCadastroItensTemplate(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		super(pageParameters);
		
		this.setPaginaAnterior(paginaAnterior);
		this.requisicao = requisicao;
		this.setSolicitacao(solicitacao);
		
		this.formFormulario = new Form<Void>("formFormulario");		
		add(this.formFormulario);
		addSecaoBotoesAcao(new SecaoVazia("secaoBotoesAcao"));
		addSecaoFaturamento(new SecaoVazia("secaoFaturamento"));
		addSecaoItens(new SecaoVazia("secaoItens"));
		
		addAbaFaturamento(new WebMarkupContainer("abaFaturamento"));
		addAbaCadastrarItens(new WebMarkupContainer("abaItens")); 
	}
	
	protected void addAbaFaturamento(WebMarkupContainer aba){
		this.formFormulario.addOrReplace(aba);
		this.abaFaturamento = aba;
	}
	
	protected void addAbaCadastrarItens(WebMarkupContainer aba){
		this.formFormulario.addOrReplace(aba);
		this.abaCadastrarItens = aba;
	}
	
	protected void addSecaoBotoesAcao(Panel panel){
		this.formFormulario.addOrReplace(panel);
	}
	
	protected void addSecaoFaturamento(Panel panel){
		this.formFormulario.addOrReplace(panel);
		this.setSecaoFaturamento(panel);
	}
	
	protected void addSecaoItens(Panel panel){
		this.formFormulario.addOrReplace(panel);
		this.setSecaoCadastrarItens(panel);
	}
	
	public OrRequisicao getRequisicao(){
		return this.requisicao;
	}
	
	public void setRequisicao(OrRequisicao requisicao){
		this.requisicao = requisicao;
	}

	public Panel getSecaoFaturamento() {
		return secaoFaturamento;
	}

	public void setSecaoFaturamento(Panel abaFaturamento) {
		this.secaoFaturamento = abaFaturamento;
	}

	public PageReference getPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(PageReference paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

	public SolicitacaoIntencaoCompra getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoIntencaoCompra solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Panel getSecaoCadastrarItens() {
		return secaoCadastrarItens;
	}

	public void setSecaoCadastrarItens(Panel abaCadastrarItens) {
		this.secaoCadastrarItens = abaCadastrarItens;
	}

	public WebMarkupContainer getAbaFaturamento() {
		return abaFaturamento;
	}

	public void setAbaFaturamento(WebMarkupContainer abaFaturamento) {
		this.abaFaturamento = abaFaturamento;
	}

	public WebMarkupContainer getAbaCadastrarItens() {
		return abaCadastrarItens;
	}

	public void setAbaCadastrarItens(WebMarkupContainer abaCadastrarItens) {
		this.abaCadastrarItens = abaCadastrarItens;
	}

}
