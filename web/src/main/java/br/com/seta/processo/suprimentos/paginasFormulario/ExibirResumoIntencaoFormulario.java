package br.com.seta.processo.suprimentos.paginasFormulario;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoContabilizacaoValidacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamentoVisualizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFormaPagamento;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoItens;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesExibirResumoIntencao;

public class ExibirResumoIntencaoFormulario extends FormularioIntencaoCompraTemplate {
	private static final long serialVersionUID = 1L;
	
	private Set<OrReqplanilhalancto> rateiosValidados = new HashSet<OrReqplanilhalancto>();
	
	public ExibirResumoIntencaoFormulario(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, PageReference paginaAnterior)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters, requisicao, solicitacao, paginaAnterior);
		
		SecaoFaturamentoVisualizacao secaoFaturamento = new SecaoFaturamentoVisualizacao("secaoFaturamento", requisicao, this);
		secaoFaturamento.habilitaAbrirModalAnexoOrcamentoBtn(false);
		secaoFaturamento.habilitaDownloadFormularioCadFornecedor(false);
		secaoFaturamento.habilitaAbrirModalAnexoCadFornecedorBtn(false);
		
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
		
		SecaoContabilizacaoValidacao secaoContabilizacao = new SecaoContabilizacaoValidacao("secaoContabilizacao", requisicao, rateiosValidados);
		
		addSecaoFaturamento(secaoFaturamento);
		addSecaoItens(secaoItens);
		addSecaoFormaPagamento(secaoFormaPagamento); 
		addSecaoContabilizacao(secaoContabilizacao);
		addSecaoBotoesAcao(new SecaoBotoesExibirResumoIntencao("secaoBotoesAcao", this, paginaAnterior));	
	}
	
	public Set<OrReqplanilhalancto> getRateiosValidados(){
		return this.rateiosValidados;
	}

}
