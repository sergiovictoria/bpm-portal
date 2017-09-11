package br.com.seta.processo.recebimento.solicitacao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.recebimento.formulario.SimularGuiaCegaFormulario;
import br.com.seta.processo.suprimentos.DadosSolicitacaoIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotaoExibirFormulario;


public class SimularGuiaCega extends DadosSolicitacaoIntencaoCompraTemplate {
	
	private static final long serialVersionUID = 1L;

	public SimularGuiaCega(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
		
		SimularGuiaCegaFormulario formulario = new SimularGuiaCegaFormulario(pageParameters, getTaskId(), getCaseId(), this.getPageReference());
		SecaoBotaoExibirFormulario exibirFormulario = new SecaoBotaoExibirFormulario("secaoBotoesAcao", formulario);
		addSecaoBotes(exibirFormulario);
	}
}