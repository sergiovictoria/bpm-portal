package br.com.seta.processo.recebimento.formulario;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.recebimento.secoesBotoes.SecaoBotoesEnviar;

public class ConsultaRequisicaoCompraFormulario extends BaseFormularioRecebimentoPage {
	private static final long serialVersionUID = 1L;

	public ConsultaRequisicaoCompraFormulario(PageParameters pageParameters, long taskId, long caseId) throws HttpStatus401Exception,
			HttpStatus404Exception, ClientProtocolException,
			GenericHttpStatusException, IOException {
		super(pageParameters, taskId, caseId);		
		
		setAbaAtiva(1);
	}
	
	public ConsultaRequisicaoCompraFormulario(PageParameters pageParameters, long taskId, long caseId, PageReference paginaAnterior, String status) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this(pageParameters, taskId, caseId);
		
	
		SecaoBotoesEnviar secaoBotoes = new SecaoBotoesEnviar("secaoBotoes", paginaAnterior, this, status);		
		addSecaoBotoes(secaoBotoes).setAbaAtiva(1);
	}

}
