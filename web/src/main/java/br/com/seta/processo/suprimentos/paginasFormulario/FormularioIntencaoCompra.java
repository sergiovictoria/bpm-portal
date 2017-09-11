package br.com.seta.processo.suprimentos.paginasFormulario;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoContabilizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamento;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFormaPagamento;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoItens;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesEnvioFormulario;
import br.com.seta.processo.validacoes.FornecedorSemCadastro;
import br.com.seta.processo.validacoes.ItensSemCadastro;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@ItensSemCadastro(message="É necessário anexar o formulário de cadastro para os produtos sem cadastro")
@FornecedorSemCadastro(message="É necessário anexar o formulário de cadastro de fornecedores")
public class FormularioIntencaoCompra extends FormularioIntencaoCompraTemplate {
	
	private static final long serialVersionUID = 1L;	
	
	private PageReference paginaAnterior = null;
	private SolicitacaoIntencaoCompra solicitacao = new SolicitacaoIntencaoCompra();
	
	public FormularioIntencaoCompra(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		
		super(pageParameters, requisicao);	
		
		this.solicitacao = solicitacao;
		this.paginaAnterior = paginaAnterior;
		
		addSecaoBotoesAcao(new SecaoBotoesEnvioFormulario("secaoBotoesAcao", this, requisicao, solicitacao));		
		addSecaoFaturamento(new SecaoFaturamento("secaoFaturamento", requisicao, this));
		addSecaoItens((Panel) new SecaoItens("secaoItens", requisicao, this).setOutputMarkupId(true));
		addSecaoFormaPagamento(new SecaoFormaPagamento("secaoFormaPagamento", requisicao, this)); 
		addSecaoContabilizacao((Panel)new SecaoContabilizacao("secaoContabilizacao", requisicao).setOutputMarkupId(true));
		
		iniciaSecaoAbas();
	}
	
	public FormularioIntencaoCompra(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this(pageParameters, new OrRequisicao(), new SolicitacaoIntencaoCompra(), null);
	}
	
	public SolicitacaoIntencaoCompra getSolicitacao(){
		return this.solicitacao;
	}
	
	public PageReference getPaginaAnterior(){
		return this.paginaAnterior;
	}
	
	@NotNull(message="É necessário anexar o orçamento")
	public Documento getOrcamento(){
		return super.getOrcamento();
	}
	
	private void iniciaSecaoAbas(){
		WebMarkupContainer abaFaturamento = new WebMarkupContainer("abaFaturamento");
		WebMarkupContainer abaItens = new WebMarkupContainer("abaItens"); 
		WebMarkupContainer abaFormaDePagamento = new WebMarkupContainer("abaFormaDePagamento"); 
		WebMarkupContainer abaContabilizacao = new WebMarkupContainer("abaContabilizacao");		
		
		abaItens.add(new AjaxEventBehavior("click") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {				
				target.appendJavaScript("ativarAbaSecao('tab-itens', 'secao-itens');");
				SecaoItens secao = (SecaoItens) getSecaoItens();
				secao.atualizaSecao(target);
			}
		});
		
		abaFormaDePagamento.add(new AjaxEventBehavior("click"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.appendJavaScript("ativarAbaSecao('tab-forma-pagamento', 'secao-forma-pagamento');");				
				SecaoFormaPagamento secao = (SecaoFormaPagamento) getSecaoFormaPagamento();
				secao.atualizaSecao(target);
			}
		});
		
		abaContabilizacao.add(new AjaxEventBehavior("click"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.appendJavaScript("ativarAbaSecao('tab-contabilizacao', 'secao-contabilizacao');");				
				SecaoContabilizacao secao = (SecaoContabilizacao) getSecaoContabilizacao();
				secao.atualizaSecao(target);
			}		
			
		});
		
		addAbaFaturamento(abaFaturamento);
		addAbaItens(abaItens);
		addAbaFormaPagamento(abaFormaDePagamento);
		addAbaContabilizacao(abaContabilizacao);		
	}

}
