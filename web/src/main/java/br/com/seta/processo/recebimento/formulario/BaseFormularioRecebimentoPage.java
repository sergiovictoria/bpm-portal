package br.com.seta.processo.recebimento.formulario;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.pagecomponentes.secaoAbas.Aba;
import br.com.seta.processo.recebimento.secoes.SecaoAnexos;
import br.com.seta.processo.recebimento.secoes.SecaoContabilizacao;
import br.com.seta.processo.recebimento.secoes.SecaoFaturamento;
import br.com.seta.processo.recebimento.secoes.SecaoFormaPagamento;
import br.com.seta.processo.recebimento.secoes.SecaoItens;

public abstract  class BaseFormularioRecebimentoPage extends AbstractFormularioRecebimentoPage {
	private static final long serialVersionUID = 1L;

	private PageReference paginaAnterior;
	
	public BaseFormularioRecebimentoPage(PageParameters pageParameters, long taskId, long caseId) throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters, taskId);
	
		
		SecaoFaturamento secaoFaturamento = new SecaoFaturamento("conteudo", getRequisicao(), this);
		SecaoItens secaoItens = new SecaoItens("conteudo", getRequisicao(), this);
		SecaoFormaPagamento secaoFormaPagamento = new SecaoFormaPagamento("conteudo", getRequisicao(), this);
		SecaoContabilizacao secaoContabilizacao = new SecaoContabilizacao("conteudo", getRequisicao());	
		Long numeroIntencaoSolicitacaoCompra = getSolicitacao().getNumeroIntencaoSolicitacaoCompra() == null ? 0 : getSolicitacao().getNumeroIntencaoSolicitacaoCompra();
		SecaoAnexos secaoAnexos = new SecaoAnexos("conteudo", numeroIntencaoSolicitacaoCompra, getCaseId());		
		
		Aba faturamento = new Aba("linkFaturamento", "Faturamento", "secaoFaturamento", secaoFaturamento);
		Aba itens = new Aba("linItens", "Itens", "secaoItens", secaoItens);
		Aba formaPagamento = new Aba("linkFormaPagamento", "Forma <br> de<br> Pagamento", "secaoFormaPagamento", secaoFormaPagamento);
		Aba contabilizacao = new Aba("linkContabilizacao", "Contabilizacao", "secaoContabilizacao", secaoContabilizacao);
		Aba anexos = new Aba("linkAnexos", "Anexos", "secaoAnexos", secaoAnexos);		
		
		addAba(faturamento).addAba(itens).addAba(formaPagamento).addAba(contabilizacao).addAba(anexos).setAbaAtiva(1);
	}
	
	public BaseFormularioRecebimentoPage(PageParameters pageParameters, long taskId, long caseId, PageReference paginaAnterior) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this(pageParameters, taskId, caseId);
		this.paginaAnterior = paginaAnterior;		
	}

	public PageReference getPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(PageReference paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

}
