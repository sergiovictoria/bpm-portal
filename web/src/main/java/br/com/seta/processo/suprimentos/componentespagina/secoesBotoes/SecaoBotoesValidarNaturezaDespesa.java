package br.com.seta.processo.suprimentos.componentespagina.secoesBotoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.PageReference;
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
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;
import br.com.seta.processo.validacao.ValidadorBean;

public class SecaoBotoesValidarNaturezaDespesa extends Panel {

	private static final long serialVersionUID = 1L;

	private AjaxButton voltarBtn;
	private AjaxButton enviarBtn;
	private String comentariosAprovacao;
	Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject 
	private ExecuteRestAPI executeRestAPI;
	@Inject
	private ValidadorBean validador;	
	
	public SecaoBotoesValidarNaturezaDespesa(String id, final FormularioIntencaoCompraTemplate template, final PageReference paginaAnterior) {
		super(id);	
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		enviarBtn = new AjaxButton("enviarBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  template.getSolicitacao();
				OrRequisicao orRequisicao = template.getRequisicao();
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				try {
					validador.valida(orRequisicao);					
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), template.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				}catch(ValidacaoBeanException e){
					target.appendJavaScript("$('#envioModal').modal('hide')");
					template.setMensagensErro(e.getMessages(), target);
				}
				catch (BonitaException e) {
					e.printStackTrace();
				}
			}
		};
		
		TextArea<String> comentariosAprovacao = new TextArea<String>("comentariosAprovacao", new PropertyModel<String>(this,"comentariosAprovacao"));
		add(voltarBtn, enviarBtn,comentariosAprovacao);
		
	}	
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentariosAprovacao);
		historico.setStatus(StatusSolicitacaoIntencaoCompra.VALIDA_NATUREZA_DESPESA_CHECKIN);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return (User) Session.get().getAttribute("user");	
	}
	
}
