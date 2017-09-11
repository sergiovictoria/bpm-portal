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
import br.com.seta.processo.suprimentos.FormularioCadastroItensTemplate;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoCadastrarItens;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamentoVisualizacao;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesCadastro;

public class FormularioCadastroItens extends FormularioCadastroItensTemplate {
	private static final long serialVersionUID = 1L;

	public FormularioCadastroItens(PageParameters pageParameters,
			OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao,
			PageReference paginaAnterior) throws HttpStatus401Exception,
			HttpStatus404Exception, ClientProtocolException,
			GenericHttpStatusException, IOException {
		super(pageParameters, requisicao, solicitacao, paginaAnterior);
		
		SecaoFaturamentoVisualizacao secaoFaturamento = new SecaoFaturamentoVisualizacao("secaoFaturamento", requisicao, this);
		secaoFaturamento.habilitaAbrirModalAnexoOrcamentoBtn(false);
		secaoFaturamento.habilitaAbrirModalAnexoCadFornecedorBtn(false);
		secaoFaturamento.habilitaDownloadFormularioCadFornecedor(false);
		
		addSecaoFaturamento(secaoFaturamento);
		addSecaoItens(new SecaoCadastrarItens("secaoItens", requisicao));
		addSecaoBotoesAcao(new SecaoBotoesCadastro("secaoBotoesAcao", paginaAnterior, this));
		
		WebMarkupContainer abaFaturamento = new WebMarkupContainer("abaFaturamento");	
		abaFaturamento.add(new AjaxEventBehavior("click") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {	
				target.add(getSecaoFaturamento());
				target.appendJavaScript("ativarAbaSecao('tab-faturamento', 'secao-faturamento');");
			}
		});
		
		addAbaFaturamento(abaFaturamento);
	}
	
	public FormularioCadastroItens(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this(pageParameters, new OrRequisicao(), new SolicitacaoIntencaoCompra(), null);
	}
	
	

}
