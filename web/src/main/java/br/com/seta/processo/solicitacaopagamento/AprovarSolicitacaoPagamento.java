package br.com.seta.processo.solicitacaopagamento;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.solicitacaopagamento.abas.SecaoSolicitacao;
import br.com.seta.processo.solicitacaopagamento.componentespagina.AprovacaoSolicPagamentoPanel;
import br.com.seta.processo.solicitacaopagamento.componentespagina.DownloadBoletoPanel;

/**
 * 
 * Controller da página AprovarSolicitacaoPagamento.html. Representa a atividade de aprovação de uma solicitação de pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class AprovarSolicitacaoPagamento extends SolicitacaoPagamentoTemplate {

	private static final long serialVersionUID = 1L;

	public AprovarSolicitacaoPagamento(PageParameters pageParameters) throws ClientProtocolException, IOException, ParseException, InstantiationException, IllegalAccessException, HttpStatusException{
		super(pageParameters);
		addSecaoBotoes(new AprovacaoSolicPagamentoPanel("secaoBotoes", this));
		adicionaSecaoAnexo(new DownloadBoletoPanel("secaoAnexo", getTaskId(), getCaseId()));
		
		SecaoSolicitacao solicitacaoContainer = getSolicitacaoPanel();
		solicitacaoContainer.getTelefoneSolicitante().setEnabled(false);
	}
}
