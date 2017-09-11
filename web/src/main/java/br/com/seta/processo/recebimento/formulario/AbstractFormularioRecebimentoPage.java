package br.com.seta.processo.recebimento.formulario;

import static br.com.seta.processo.constant.VariaveisRecebimento.RECEBIMENTO;
import static br.com.seta.processo.constant.VariaveisRecebimento.REQUISICAO;
import static br.com.seta.processo.constant.VariaveisRecebimento.SOLICITACAO;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.pagecomponentes.secaoAbas.PaginaComAbas;


public abstract class AbstractFormularioRecebimentoPage extends PaginaComAbas {
	private static final long serialVersionUID = 1L;

	private SolicitacaoIntencaoCompra solicitacao;
	private OrRequisicao requisicao;
	private Recebimento recebimento;
	
	public AbstractFormularioRecebimentoPage(PageParameters pageParameters, long taskId)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
		
		try {
			solicitacao = (SolicitacaoIntencaoCompra) retriveProcessVariable(SOLICITACAO, taskId, SolicitacaoIntencaoCompra.class, new SolicitacaoIntencaoCompra());
			requisicao = (OrRequisicao) retriveProcessVariable(REQUISICAO, taskId, OrRequisicao.class, new OrRequisicao());
			recebimento = (Recebimento) retriveProcessVariable(RECEBIMENTO, taskId, Recebimento.class, new Recebimento());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public SolicitacaoIntencaoCompra getSolicitacao() {
		return solicitacao;
	}

	public OrRequisicao getRequisicao() {
		return requisicao;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

}
