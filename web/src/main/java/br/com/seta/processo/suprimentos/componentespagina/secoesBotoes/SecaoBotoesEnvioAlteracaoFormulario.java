package br.com.seta.processo.suprimentos.componentespagina.secoesBotoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.StatusSolicitacaoIntencaoCompra;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;
import br.com.seta.processo.validacao.ValidadorBean;

public class SecaoBotoesEnvioAlteracaoFormulario extends Panel {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ValidadorBean validador;	
	
	private String comentariosAprovacao;
	
	public SecaoBotoesEnvioAlteracaoFormulario(String id, final FormularioIntencaoCompraTemplate parentPage, final OrRequisicao requisicao, final SolicitacaoIntencaoCompra solicitacao) {
		super(id);	
		
		AjaxButton voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(parentPage.getPaginaAnterior().getPage());
			}
		};
		
		AjaxButton envioBtn = new AjaxButton("envioBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
				adicionaPreenchimentoAoHistorico(solicitacao);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacao);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,requisicao);
								
				try {
					validador.valida(requisicao);
					parentPage.executeFlowAndUpdateVariables(processVariables);
					parentPage.redirecionaParaPaginaDeAtividades();
				}catch(ValidacaoBeanException e){
					target.appendJavaScript("$('#envioModal').modal('hide')");
					parentPage.setMensagensErro(e.getMessages(), target);
				}				
				catch (BonitaException e) {
					e.printStackTrace();
				}
			}

		};
		
		TextArea<String> comentariosAprovacao = new TextArea<String>("comentariosAprovacao", new PropertyModel<String>(this,"comentariosAprovacao"));
		add(voltarBtn, envioBtn, comentariosAprovacao);
		
	}
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentariosAprovacao);
		historico.setStatus(StatusSolicitacaoIntencaoCompra.SOLICITANTE_ALTERAR_REQUISICAO);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return 	(User) Session.get().getAttribute("user");
	}

}
