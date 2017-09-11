package br.com.seta.processo.pagecomponentes.secaoAbas;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoCadastrarItens;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoContabilizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamentoVisualizacao;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFormaPagamento;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoItens;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotaoExibirFormulario;

public class PaginaTesteAbas extends PaginaComAbas {
	private static final long serialVersionUID = 1L;

	public PaginaTesteAbas(PageParameters pageParameters)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
		
		SecaoFaturamentoVisualizacao secaoFaturamento = new SecaoFaturamentoVisualizacao("conteudo", new OrRequisicao(), this);
		SecaoItens secaoItens = new SecaoItens("conteudo", new OrRequisicao(), this);
		SecaoFormaPagamento secaoFormaPagamento = new SecaoFormaPagamento("conteudo", new OrRequisicao(), this);
		SecaoContabilizacao secaoContabilizacao = new SecaoContabilizacao("conteudo", new OrRequisicao());
		SecaoCadastrarItens secaoCadastroItens = new SecaoCadastrarItens("conteudo", new OrRequisicao());
		SecaoContabilizacao secaoContabilizacaoValidacao = new SecaoContabilizacao("conteudo", new OrRequisicao());
		
		AjaxEventBehavior onclickEvent = new AjaxEventBehavior("click") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				System.out.println("Houve um event de click!!!!");	
				target.appendJavaScript("ativarAbaSecao('linkD', 'secaoContabilizacao');");
			}
		};
		
		Aba a = new Aba("linkA", "secao <br> Faturamento", "secaoFaturamento", secaoFaturamento);
		Aba b = new Aba("linkB", "Secao <br> Itens", "SecaoItens", secaoItens);
		Aba c = new Aba("linkC", "secao <br> Forma <br> Pagamento", "secaoFormaPagamento", secaoFormaPagamento);
		Aba d = new Aba("linkD", "secao <br> Contabilizacao", "secaoContabilizacao", secaoContabilizacao, false, onclickEvent);
		LinkAba linkE = new LinkAba("linkE", "#secaoCadastro", "Seção <br> Cadastro");
		SecaoAba secaoE = new SecaoAba("secaoCadastro", secaoCadastroItens);
		Aba e = new Aba(linkE, secaoE);
		Aba f = new Aba("linkF", "secao <br> Forma <br> Pagamento 2", "secaoFormaPagamento2", secaoContabilizacaoValidacao, false);

		SecaoBotaoExibirFormulario secaoBotoes = new SecaoBotaoExibirFormulario("secaoBotoes", this);
		
		this.addAba(a).addAba(b).addAba(c).addAba(d).addAba(e).addAba(f).addSecaoBotoes(secaoBotoes);
	}

}
