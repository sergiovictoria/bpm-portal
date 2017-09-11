package br.com.seta.processo.recebimento.formulario;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.recebimento.secoesBotoes.SecaoBotoesEscanearDocumento;

public class EscanearDocumentoServicoFormulario extends BaseFormularioRecebimentoPage {
	private static final long serialVersionUID = 1L;

	public EscanearDocumentoServicoFormulario(PageParameters pageParameters,
			long taskId, long caseId, PageReference paginaAnterior)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters, taskId, caseId, paginaAnterior);
		
		SecaoBotoesEscanearDocumento secaoBotoes = new SecaoBotoesEscanearDocumento("secaoBotoes", paginaAnterior, this, getCaseId());		
		addSecaoBotoes(secaoBotoes).setAbaAtiva(1);
	}

}
