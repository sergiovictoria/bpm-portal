package br.com.seta.processo.recebimento.solicitacao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.recebimento.formulario.ConsultaRequisicaoCompraFormulario;
import br.com.seta.processo.suprimentos.DadosSolicitacaoIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.secoesBotoes.SecaoBotaoExibirFormulario;

public class ConsultaRequisicaoCompraProduto extends DadosSolicitacaoIntencaoCompraTemplate {

	private static final long serialVersionUID = 1L;
	
	public ConsultaRequisicaoCompraProduto(PageParameters pageParameters)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
		
		ConsultaRequisicaoCompraFormulario formulario = new ConsultaRequisicaoCompraFormulario(pageParameters, getTaskId(), getCaseId(), this.getPageReference(), VariaveisRecebimento.CHECKIN_LOJA_CONSULTA_REQ_PRODUTO);
		SecaoBotaoExibirFormulario exibirFormulario = new SecaoBotaoExibirFormulario("secaoBotoesAcao", formulario);
		addSecaoBotes(exibirFormulario);
	}

}
