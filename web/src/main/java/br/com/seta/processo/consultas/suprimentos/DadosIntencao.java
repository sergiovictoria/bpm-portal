package br.com.seta.processo.consultas.suprimentos;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.pagecomponentes.secaoAbas.Aba;
import br.com.seta.processo.pagecomponentes.secaoAbas.PaginaComAbas;
import br.com.seta.processo.recebimento.secoes.SecaoAnexos;
import br.com.seta.processo.recebimento.secoes.SecaoContabilizacao;
import br.com.seta.processo.recebimento.secoes.SecaoFaturamento;
import br.com.seta.processo.recebimento.secoes.SecaoFormaPagamento;
import br.com.seta.processo.recebimento.secoes.SecaoItens;
import br.com.seta.processo.recebimento.secoes.SecaoValidacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoSolicitacao;

public class DadosIntencao extends PaginaComAbas {
	private static final long serialVersionUID = 1L;

	public DadosIntencao(PageParameters pageParameters, OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, Recebimento recebimento, Long caseId)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);		
		
		setTituloPagina("Detalhes");
		
		SecaoSolicitacao secaoSolicitacao = new SecaoSolicitacao("conteudo", solicitacao);
		SecaoFaturamento secaoFaturamento = new SecaoFaturamento("conteudo", requisicao, this);
		SecaoItens secaoItens = new SecaoItens("conteudo", requisicao, this);
		SecaoFormaPagamento secaoFormaPagamento = new SecaoFormaPagamento("conteudo", requisicao, this);
		SecaoContabilizacao secaoContabilizacao = new SecaoContabilizacao("conteudo",requisicao);	
		SecaoAnexos secaoAnexos = new SecaoAnexos("conteudo", caseId);
		
		Aba solicit = new Aba("linkSolicitacao", "Solicitação", "secaoSolicitacao", secaoSolicitacao);
		Aba faturamento = new Aba("linkFaturamento", "Faturamento", "secaoFaturamento", secaoFaturamento);
		Aba itens = new Aba("linItens", "Itens", "secaoItens", secaoItens);
		Aba formaPagamento = new Aba("linkFormaPagamento", "Forma <br> de<br> Pagamento", "secaoFormaPagamento", secaoFormaPagamento);
		Aba contabilizacao = new Aba("linkContabilizacao", "Contabilizacao", "secaoContabilizacao", secaoContabilizacao);
		Aba anexos = new Aba("linkAnexos", "Anexos", "secaoAnexos", secaoAnexos);		
		
		addSecaoBotoes(new BotaoVoltarPanel("secaoBotoes"));
		addAba(solicit);
		addAba(faturamento);
		addAba(itens);
		addAba(formaPagamento);
		addAba(contabilizacao);			
		
		if(recebimento != null){
			SecaoValidacao secaoValidacao = new SecaoValidacao("conteudo", caseId, solicitacao, requisicao, recebimento);
			secaoValidacao
			.exibeSecaoGuiaCega(true)
			.exibeSecaoNotaFiscalSerie(true)
			.exibeSecaoNotaFiscalSerie(true)
			.exibeSecaoTipoDocumento(true)
			.setEnabled(false);			
			
			Aba validacao = new Aba("linkValidacao", "Validação", "secaoValidacao", secaoValidacao);
			addAba(validacao );
		}
		
		addAba(anexos)	;
		setAbaAtiva(1);
	}
	


}
