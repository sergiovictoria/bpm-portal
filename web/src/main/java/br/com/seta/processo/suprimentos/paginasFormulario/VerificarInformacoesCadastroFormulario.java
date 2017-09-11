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
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoContabilizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamentoVisualizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFormaPagamento;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoItens;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesVerificarInfosCadastro;

public class VerificarInformacoesCadastroFormulario extends FormularioIntencaoCompraTemplate {
	private static final long serialVersionUID = 1L;
	
	public VerificarInformacoesCadastroFormulario(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		
		super(pageParameters, requisicao, solicitacao, paginaAnterior);
		
		SecaoFaturamentoVisualizacao secaoFaturamento = new SecaoFaturamentoVisualizacao("secaoFaturamento", requisicao, this);
		
		SecaoItens secaoItens = new SecaoItens("secaoItens", requisicao, this);
		secaoItens.habilitaAnexarCadProdutosBtn(false);
		secaoItens.habilitaArquivoCadProdutoBtn(false);
		secaoItens.habilitaBtnExclusao(false);
		secaoItens.habilitaBuscarItensBtn(false);
		secaoItens.habilitaCfop(false);
		secaoItens.habilitaQuantidade(false);
		secaoItens.habilitaValorUnitario(false);
		secaoItens.habilitaSecaoTotais(false);		
		
		SecaoFormaPagamento secaoFormaPagamento = new SecaoFormaPagamento("secaoFormaPagamento", requisicao, this);
		secaoFormaPagamento.setEnabled(false);
		
		SecaoContabilizacao secaoContabilizacao = new SecaoContabilizacao("secaoContabilizacao", requisicao);
		secaoContabilizacao.setEnabled(false);
		
		addSecaoFaturamento(secaoFaturamento);
		addSecaoItens(secaoItens);
		addSecaoFormaPagamento(secaoFormaPagamento); 
		addSecaoContabilizacao(secaoContabilizacao);
		
		addSecaoBotoesAcao(new SecaoBotoesVerificarInfosCadastro("secaoBotoesAcao", this, paginaAnterior));	
	}
}
