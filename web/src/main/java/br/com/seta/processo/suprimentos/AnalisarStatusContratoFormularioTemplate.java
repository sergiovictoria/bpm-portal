package br.com.seta.processo.suprimentos;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
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

public class AnalisarStatusContratoFormularioTemplate extends BonitaPage{
	private static final long serialVersionUID = 1L;

	private OrRequisicao requisicao = new OrRequisicao();
	private SolicitacaoIntencaoCompra solicitacao = new SolicitacaoIntencaoCompra();
	private PageReference paginaAnterior = null;
	
	private Form<Void> formFormulario;
	private Panel secaoFaturamento, secaoItens, secaoFormaPagamento, secaoContabilizacao, secaoContrato;
	
	public AnalisarStatusContratoFormularioTemplate(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		super(pageParameters);
		
		this.paginaAnterior = paginaAnterior;
		this.requisicao = requisicao;
		this.solicitacao = solicitacao;
		
		this.formFormulario = new Form<Void>("formFormulario");		
		add(this.formFormulario);
		
		addSecaoBotoesAcao(new SecaoVazia("secaoBotoesAcao"));
		addSecaoFaturamento(new SecaoVazia("secaoFaturamento"));
		addSecaoItens(new SecaoVazia("secaoItens"));
		addSecaoFormaPagamento(new SecaoVazia("secaoFormaPagamento"));
		addSecaoContabilizacao(new SecaoVazia("secaoContabilizacao"));
		addSecaoContrato(new SecaoVazia("secaoContrato"));
	}
	
	protected void addSecaoBotoesAcao(Panel panel){
		this.formFormulario.addOrReplace(panel);
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
	
	protected void addSecaoContrato(Panel panel){
		this.formFormulario.addOrReplace(panel);
		this.secaoContrato = panel;
	}

	public OrRequisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(OrRequisicao requisicao) {
		this.requisicao = requisicao;
	}

	public SolicitacaoIntencaoCompra getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoIntencaoCompra solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Form<Void> getFormFormulario() {
		return formFormulario;
	}

	public void setFormFormulario(Form<Void> formFormulario) {
		this.formFormulario = formFormulario;
	}

	public Panel getSecaoFaturamento() {
		return secaoFaturamento;
	}

	public void setSecaoFaturamento(Panel secaoFaturamento) {
		this.secaoFaturamento = secaoFaturamento;
	}

	public Panel getSecaoItens() {
		return secaoItens;
	}

	public void setSecaoItens(Panel secaoItens) {
		this.secaoItens = secaoItens;
	}

	public Panel getSecaoFormaPagamento() {
		return secaoFormaPagamento;
	}

	public void setSecaoFormaPagamento(Panel secaoFormaPagamento) {
		this.secaoFormaPagamento = secaoFormaPagamento;
	}

	public Panel getSecaoContabilizacao() {
		return secaoContabilizacao;
	}

	public void setSecaoContabilizacao(Panel secaoContabilizacao) {
		this.secaoContabilizacao = secaoContabilizacao;
	}

	public Panel getSecaoContrato() {
		return secaoContrato;
	}

	public void setSecaoContrato(Panel secaoContrato) {
		this.secaoContrato = secaoContrato;
	}

	public PageReference getPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(PageReference paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

}
