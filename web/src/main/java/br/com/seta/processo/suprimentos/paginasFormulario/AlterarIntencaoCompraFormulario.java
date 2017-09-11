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
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesEnvioAlteracaoFormulario;

public class AlterarIntencaoCompraFormulario extends FormularioIntencaoCompraTemplate {
	private static final long serialVersionUID = 1L;

	public AlterarIntencaoCompraFormulario(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior) throws HttpStatus401Exception,
			HttpStatus404Exception, ClientProtocolException,
			GenericHttpStatusException, IOException {
		super(pageParameters, requisicao, solicitacao, paginaAnterior);
		
		
		SecaoFaturamentoVisualizacao secaoFaturamento = new SecaoFaturamentoVisualizacao("secaoFaturamento", requisicao, this);
		secaoFaturamento.habilitaAbrirModalAnexoOrcamentoBtn(false);
		secaoFaturamento.habilitaDownloadFormularioCadFornecedor(false);
		secaoFaturamento.habilitaAbrirModalAnexoCadFornecedorBtn(false);
		
		SecaoItens secaoItens = new SecaoItens("secaoItens", requisicao, this);
		secaoItens.habilitaCfop(false);
		secaoItens.habilitaBtnExclusao(false);
		secaoItens.habilitaAnexarCadProdutosBtn(false);
		secaoItens.habilitaArquivoCadProdutoBtn(false);
		secaoItens.habilitaBuscarItensBtn(false);
		
		SecaoFormaPagamento secaoFormaPagamento = new SecaoFormaPagamento("secaoFormaPagamento", requisicao, this);
		secaoFormaPagamento.habilitaFormaPagamentoDropDoown(false);
		secaoFormaPagamento.habilitaObservacaoInput(false);
		secaoFormaPagamento.habilitaFreteCb(false);
		secaoFormaPagamento.habilitaTransportadoraCb(false);
		secaoFormaPagamento.habilitaValorTranspCb(false);
		secaoFormaPagamento.habilitaValorFreteInput(false);
		secaoFormaPagamento.habilitaFreteCb(false);
		secaoFormaPagamento.habilitaLocalDeEntrega(false);
				
		SecaoContabilizacao secaoContabilizacao = new SecaoContabilizacao("secaoContabilizacao", requisicao);
		secaoContabilizacao.setEnabled(false);
		
		addSecaoFaturamento(secaoFaturamento);
		addSecaoItens(secaoItens);
		addSecaoFormaPagamento(secaoFormaPagamento); 
		addSecaoContabilizacao(secaoContabilizacao);		
		addSecaoBotoesAcao(new SecaoBotoesEnvioAlteracaoFormulario("secaoBotoesAcao", this, getRequisicao(), getSolicitacao()));
		
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

}
