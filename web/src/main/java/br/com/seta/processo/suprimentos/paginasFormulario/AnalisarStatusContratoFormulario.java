package br.com.seta.processo.suprimentos.paginasFormulario;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.suprimentos.AnalisarStatusContratoFormularioTemplate;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoContabilizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoContrato;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamentoVisualizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFormaPagamento;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoItens;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesAnalisarStatusContrato;

public class AnalisarStatusContratoFormulario extends AnalisarStatusContratoFormularioTemplate {
	private static final long serialVersionUID = 1L;

	public AnalisarStatusContratoFormulario(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao,
			PageReference paginaAnterior) throws HttpStatus401Exception,
			HttpStatus404Exception, ClientProtocolException,
			GenericHttpStatusException, IOException {
		super(pageParameters, requisicao, solicitacao, paginaAnterior);
		
		SecaoFaturamentoVisualizacao secaoFaturamento = new SecaoFaturamentoVisualizacao("secaoFaturamento", getRequisicao(), this);
		secaoFaturamento.habilitaAbrirModalAnexoOrcamentoBtn(false);
		secaoFaturamento.habilitaAbrirModalAnexoCadFornecedorBtn(false);
		secaoFaturamento.habilitaDownloadFormularioCadFornecedor(false);
		
		SecaoItens secaoItens = new SecaoItens("secaoItens", getRequisicao(), this);
		secaoItens.habilitaAnexarCadProdutosBtn(false);
		secaoItens.habilitaArquivoCadProdutoBtn(false);
		secaoItens.habilitaBtnExclusao(false);
		secaoItens.habilitaBuscarItensBtn(false);
		secaoItens.habilitaCfop(false);
		secaoItens.habilitaQuantidade(false);
		secaoItens.habilitaValorUnitario(false);
		secaoItens.habilitaSecaoTotais(false);
		
		SecaoFormaPagamento secaoFormaPagamento = new SecaoFormaPagamento("secaoFormaPagamento", getRequisicao(), this);
		secaoFormaPagamento.setEnabled(false);
		
		SecaoContabilizacao secaoContabilizacao = new SecaoContabilizacao("secaoContabilizacao", getRequisicao());
		secaoContabilizacao.setEnabled(false);
		
		SecaoContrato secaoContrato = new SecaoContrato("secaoContrato", getRequisicao());
		
		addSecaoFaturamento(secaoFaturamento);
		addSecaoItens(secaoItens);
		addSecaoFormaPagamento(secaoFormaPagamento);
		addSecaoContabilizacao(secaoContabilizacao);
		addSecaoContrato(secaoContrato);
		addSecaoBotoesAcao(new SecaoBotoesAnalisarStatusContrato("secaoBotoesAcao", this, paginaAnterior));
	}
	
	public AnalisarStatusContratoFormulario(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this(pageParameters, new OrRequisicao(), new SolicitacaoIntencaoCompra(), null);
	}

}
