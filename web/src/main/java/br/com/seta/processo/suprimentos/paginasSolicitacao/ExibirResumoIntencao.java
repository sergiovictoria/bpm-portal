package br.com.seta.processo.suprimentos.paginasSolicitacao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.suprimentos.DadosSolicitacaoIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotaoExibirFormulario;
import br.com.seta.processo.suprimentos.paginasFormulario.ExibirResumoIntencaoFormulario;

public class ExibirResumoIntencao extends DadosSolicitacaoIntencaoCompraTemplate {
	private static final long serialVersionUID = 1L;

	public ExibirResumoIntencao(PageParameters pageParameters)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
		
		ExibirResumoIntencaoFormulario formulario = new ExibirResumoIntencaoFormulario(pageParameters, getRequisicao(), getSolicitacao(), this.getPageReference());
		SecaoBotaoExibirFormulario exibirFormulario = new SecaoBotaoExibirFormulario("secaoBotoesAcao", formulario);
		addSecaoBotes(exibirFormulario);
		exibeSecaoHistorico(true);
	}
	
}
