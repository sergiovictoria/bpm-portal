package br.com.seta.processo.suprimentos.paginasFormulario;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.mapper.parameter.PageParameters;

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
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesValidarNaturezaDespesa;

public class ValidarNaturezaDespesaFormulario extends FormularioIntencaoCompraTemplate {
	private static final long serialVersionUID = 1L;

	public ValidarNaturezaDespesaFormulario(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior) throws HttpStatus401Exception,
			HttpStatus404Exception, ClientProtocolException,
			GenericHttpStatusException, IOException {
		super(pageParameters, requisicao, solicitacao, paginaAnterior);
		
		SecaoFaturamento secaoFaturamento = new SecaoFaturamento("secaoFaturamento", requisicao, this);
		secaoFaturamento.habilitaAbrirModalFornecedoresBtn(false);
		secaoFaturamento.habilitaAbrirModalAnexoOrcamentoBtn(false);
		secaoFaturamento.habilitaDownloadFormularioCadFornecedor(false);
		secaoFaturamento.habilitaAbrirModalAnexoCadFornecedorBtn(false);
		
		SecaoItens secaoItens = new SecaoItens("secaoItens", requisicao, this);
		secaoItens.habilitaQuantidade(false);
		secaoItens.habilitaValorUnitario(false);
		secaoItens.habilitaSecaoTotais(false);
		secaoItens.habilitaBtnExclusao(false);
		secaoItens.habilitaBuscarItensBtn(false);
		secaoItens.habilitaAnexarCadProdutosBtn(false);
		secaoItens.habilitaArquivoCadProdutoBtn(false);
		
		SecaoFormaPagamento secaoFormaPagamento = new SecaoFormaPagamento("secaoFormaPagamento", requisicao, this);	
		secaoFormaPagamento.setEnabled(false);
		
		SecaoContabilizacao secaoContabilizacao = new SecaoContabilizacao("secaoContabilizacao", requisicao);
		
		addSecaoFaturamento(secaoFaturamento);
		addSecaoItens(secaoItens);
		addSecaoFormaPagamento(secaoFormaPagamento); 
		addSecaoContabilizacao(secaoContabilizacao);
		addSecaoBotoesAcao(new SecaoBotoesValidarNaturezaDespesa("secaoBotoesAcao", this, paginaAnterior));	
		
		WebMarkupContainer abaItens = new WebMarkupContainer("abaItens"); 
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
		
		abaContabilizacao.add(new AjaxEventBehavior("click"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.appendJavaScript("ativarAbaSecao('tab-contabilizacao', 'secao-contabilizacao');");				
				SecaoContabilizacao secao = (SecaoContabilizacao) getSecaoContabilizacao();
				secao.atualizaSecao(target);
			}		
			
		});
		
		addAbaItens(abaItens);
		addAbaContabilizacao(abaContabilizacao);

	}
	
	public ValidarNaturezaDespesaFormulario(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this(pageParameters, new OrRequisicao(), new SolicitacaoIntencaoCompra(), null);
	}

}
