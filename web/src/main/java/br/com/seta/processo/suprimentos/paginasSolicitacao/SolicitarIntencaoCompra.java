package br.com.seta.processo.suprimentos.paginasSolicitacao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.suprimentos.SolicitacaoIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.secoes.SecaoVazia;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotoesEnviarSolicitacaoIntencaoCompra;

public class SolicitarIntencaoCompra extends SolicitacaoIntencaoCompraTemplate {
	private static final long serialVersionUID = 1L;

	public SolicitarIntencaoCompra(PageParameters pageParameters)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException, BonitaException {
		super(pageParameters);
		
		setTituloPagina("Solicitar Intenção Compra");
		
		addSecaoBotes(new SecaoBotoesEnviarSolicitacaoIntencaoCompra("secaoBotoesAcao", getSolicitacao(), getSolicitacaoModel(),this));
		addSecaoHistorico(new SecaoVazia("secaoHistorico"));
	}

}
