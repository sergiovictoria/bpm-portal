package br.com.seta.processo.recebimento.formulario;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.PageReference;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.Comentario;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.pagecomponentes.secaoAbas.Aba;
import br.com.seta.processo.recebimento.secoes.SecaoValidacao;
import br.com.seta.processo.recebimento.secoesBotoes.SecaoBotoesVerificarStatusDivergencia;

public class VerificarStatusDivergenciaFormulario extends BaseFormularioRecebimentoPage {
	private static final long serialVersionUID = 1L;

	public VerificarStatusDivergenciaFormulario(PageParameters pageParameters,
			long taskId, long caseId, PageReference paginaAnterior)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters, taskId, caseId, paginaAnterior);
		
		SecaoValidacao secaoValidacao = new SecaoValidacao("conteudo", getCaseId(), getSolicitacao(), getRequisicao(), getRecebimento());
		secaoValidacao
		.exibeSecaoGuiaCega(true)
		.habilitaSecaoGuiaCega(false)
		.setMensagem(getMensagemRejeicao())
		.exibeSecaoMensagem(true);
		
		Aba validacao = new Aba("linkValidacao", "Validação", "secaoValidacao", secaoValidacao);
		
		addAba(validacao).addSecaoBotoes(new SecaoBotoesVerificarStatusDivergencia("secaoBotoes", paginaAnterior,this)).setAbaAtiva(6);
	}
	
	private String getMensagemRejeicao(){
		List<Comentario> comentarios = getRecebimento().getComentariosAprovRecebimentoDivergente();
		
		if(comentarios.size() == 0)
			return "";
		
		Usuario usuario = comentarios.get(0).getUsuario();
		return "Recebimento rejeitado pelo usuário <b>" + usuario.getNomeCompleto() + "</b>";
	}

}
