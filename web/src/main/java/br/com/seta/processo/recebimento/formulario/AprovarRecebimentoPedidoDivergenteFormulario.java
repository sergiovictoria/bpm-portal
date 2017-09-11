package br.com.seta.processo.recebimento.formulario;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.pagecomponentes.secaoAbas.Aba;
import br.com.seta.processo.recebimento.secoes.SecaoValidacao;
import br.com.seta.processo.recebimento.secoesBotoes.SecaoBotoesAprovRecebPedidoDivergente;

public class AprovarRecebimentoPedidoDivergenteFormulario extends BaseFormularioRecebimentoPage {
	private static final long serialVersionUID = 1L;

	public AprovarRecebimentoPedidoDivergenteFormulario(
			PageParameters pageParameters, long taskId, long caseId,
			PageReference paginaAnterior) throws HttpStatus401Exception,
			HttpStatus404Exception, ClientProtocolException,
			GenericHttpStatusException, IOException {
		super(pageParameters, taskId, caseId, paginaAnterior);
		
		SecaoValidacao secaoValidacao = new SecaoValidacao("conteudo", caseId, getSolicitacao(), getRequisicao(), getRecebimento());
		secaoValidacao
		.exibeSecaoDadosParaValidacao(false);
		
		Aba validacao = new Aba("linkValidacao", "Validação", "secaoValidacao", secaoValidacao);
		
		addAba(validacao).addSecaoBotoes(new SecaoBotoesAprovRecebPedidoDivergente("secaoBotoes", this, paginaAnterior)).setAbaAtiva(6);
	}
}
