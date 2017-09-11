package br.com.seta.processo.recebimento.formulario;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.recebimento.secoesBotoes.SecaoBotoesValidarPrestacaoServico;

public class ValidarPrestacaoServicoFormulario extends BaseFormularioRecebimentoPage {
	private static final long serialVersionUID = 1L;

	public ValidarPrestacaoServicoFormulario(PageParameters pageParameters, long taskId, long caseId, PageReference paginaAnterior) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		super(pageParameters, taskId, caseId);		
		
		SecaoBotoesValidarPrestacaoServico secaoBotoes = new SecaoBotoesValidarPrestacaoServico("secaoBotoes", paginaAnterior, this);
		
		addSecaoBotoes(secaoBotoes).setAbaAtiva(1);
	}

}
