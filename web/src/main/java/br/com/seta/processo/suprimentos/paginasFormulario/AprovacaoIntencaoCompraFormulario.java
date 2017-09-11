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
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamentoVisualizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFormaPagamento;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoItens;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesAprovacao;

public class AprovacaoIntencaoCompraFormulario extends FormularioIntencaoCompraTemplate {
	private static final long serialVersionUID = 1L;	

	public AprovacaoIntencaoCompraFormulario(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior) throws HttpStatus401Exception,
			HttpStatus404Exception, ClientProtocolException,
			GenericHttpStatusException, IOException {
		
		super(pageParameters, requisicao, solicitacao, paginaAnterior);			
		
		SecaoFaturamentoVisualizacao secaoFaturamento = new SecaoFaturamentoVisualizacao("secaoFaturamento", requisicao, this);
		secaoFaturamento.habilitaAbrirModalAnexoCadFornecedorBtn(false);
		secaoFaturamento.habilitaDownloadFormularioCadFornecedor(false);
		
		SecaoFormaPagamento secaoFormaPagamento = new SecaoFormaPagamento("secaoFormaPagamento", requisicao, this);
		secaoFormaPagamento.habilitaFormaPagamentoDropDoown(false);
		secaoFormaPagamento.habilitaObservacaoInput(false);
		secaoFormaPagamento.habilitaTransportadoraCb(false);
		secaoFormaPagamento.habilitaValorTranspCb(false);
		secaoFormaPagamento.habilitaValorFreteInput(false);
		secaoFormaPagamento.habilitaTabelaDesdobramentos(false);
		secaoFormaPagamento.habilitaFreteCb(false);
		
		SecaoItens secaoItens = new SecaoItens("secaoItens", requisicao, this);
		secaoItens.habilitaCfop(false);
		secaoItens.habilitaAnexarCadProdutosBtn(false);
		secaoItens.habilitaArquivoCadProdutoBtn(false);
		
		SecaoContabilizacao secaoContabilizacao = new SecaoContabilizacao("secaoContabilizacao", requisicao);
		secaoContabilizacao.visibilidadeSecaoBuscaNaturezaDespesa(false);
		
		addSecaoFaturamento(secaoFaturamento);
		addSecaoItens(secaoItens);
		addSecaoFormaPagamento(secaoFormaPagamento); 
		addSecaoContabilizacao(secaoContabilizacao);
		addSecaoBotoesAcao(new SecaoBotoesAprovacao("secaoBotoesAcao", paginaAnterior, this));	
		
		WebMarkupContainer abaContabilizacao = new WebMarkupContainer("abaContabilizacao");	
		abaContabilizacao.add(new AjaxEventBehavior("click"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.appendJavaScript("ativarAbaSecao('tab-contabilizacao', 'secao-contabilizacao');");				
				SecaoContabilizacao secao = (SecaoContabilizacao) getSecaoContabilizacao();
				secao.atualizaSecao(target);
			}		
			
		});
		
		addAbaContabilizacao(abaContabilizacao);

	}	
	
	public AprovacaoIntencaoCompraFormulario(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this(pageParameters, new OrRequisicao(), new SolicitacaoIntencaoCompra(), null);
	}

}
