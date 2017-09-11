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
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;

/**
 * Seção com os botões de aprovação
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class SecaoBotoesAprovacaoHierarquica extends Panel {
	private static final long serialVersionUID = 1L;

	private AjaxButton voltarBtn;
	private AjaxButton aprovarBtn;
	private AjaxButton rejeitarBtn;
	private String comentariosAprovacao;
	Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject private ExecuteRestAPI executeRestAPI;	
	
	public SecaoBotoesAprovacaoHierarquica(String id, final FormularioIntencaoCompraTemplate template, final PageReference paginaAnterior) {
		super(id);	
		
				
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		aprovarBtn = new AjaxButton("aprovarBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  template.getSolicitacao();
				OrRequisicao orRequisicao = template.getRequisicao();
				solicitacaoIntencaoCompra.setCanceladoRejeitadoAprovHierar(Boolean.FALSE);
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra, Boolean.TRUE);
				solicitacaoIntencaoCompra.setStatus("Aprovado");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), template.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}
			}
		};
		
		
		rejeitarBtn = new AjaxButton("rejeitarBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  template.getSolicitacao();
				OrRequisicao orRequisicao = template.getRequisicao();
				solicitacaoIntencaoCompra.setCanceladoRejeitadoAprovHierar(Boolean.TRUE);
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra, Boolean.FALSE);
				solicitacaoIntencaoCompra.setStatus("Rejeitado");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), template.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}
				
			}
		};		
		
		TextArea<String> comentariosAprovacao = new TextArea<String>("comentariosAprovacao", new PropertyModel<String>(this,"comentariosAprovacao"));
		add(voltarBtn, aprovarBtn, rejeitarBtn,comentariosAprovacao);
		
	}
	
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, Boolean aprovadoRejeitado) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentariosAprovacao);
		if (aprovadoRejeitado) {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.APROVADOR_HIERARQUICO_APROVADO);
		}else {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.APROVADOR_HIERARQUICO_REJEITADO);
		}
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}


	private User getUser() {
		return 	(User) Session.get().getAttribute("user");

	}
}
