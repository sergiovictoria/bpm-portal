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

import br.com.seta.processo.bean.TransacaoMongo;
import br.com.seta.processo.constant.StatusSolicitacaoIntencaoCompra;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.suprimentos.AnalisarStatusContratoFormularioTemplate;

public class SecaoBotoesAnalisarStatusContrato extends Panel {

	private static final long serialVersionUID = 1L;

	private String comentariosAprovacao;
	Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject private ExecuteRestAPI executeRestAPI;
	@Inject private TransacaoMongo transacaoMongo;
	private AjaxButton voltarBtn, enviarBtn, cancelarIntencaoBtn;

	public SecaoBotoesAnalisarStatusContrato(String id, final AnalisarStatusContratoFormularioTemplate parentPage , final PageReference paginaAnterior) {
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

				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  parentPage.getSolicitacao();
				OrRequisicao orRequisicao = parentPage.getRequisicao();
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra, Boolean.FALSE);
				solicitacaoIntencaoCompra.setContinuaIntencao(Boolean.FALSE);
				solicitacaoIntencaoCompra.setStatus("Aprovado");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				transacaoMongo.updateWithTwoAttributes("numeroInstanciaOrigem", solicitacaoIntencaoCompra.getNumeroIntencaoSolicitacaoCompra(), "statusContrato", 2, Contrato.class);
				
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), parentPage.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}

			}
		};

		cancelarIntencaoBtn = new AjaxButton("cancelarIntencaoBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  parentPage.getSolicitacao();
				OrRequisicao orRequisicao = parentPage.getRequisicao();
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra, Boolean.TRUE);
				solicitacaoIntencaoCompra.setContinuaIntencao(Boolean.TRUE);
				solicitacaoIntencaoCompra.setStatus("Rejeitado");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				transacaoMongo.updateWithTwoAttributes("numeroInstanciaOrigem", solicitacaoIntencaoCompra.getNumeroIntencaoSolicitacaoCompra(), "statusContrato", 5, Contrato.class);
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), parentPage.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}

			}
		};

		TextArea<String> comentariosAprovacao = new TextArea<String>("comentariosAprovacao", new PropertyModel<String>(this,"comentariosAprovacao"));
		add(voltarBtn, enviarBtn, cancelarIntencaoBtn,comentariosAprovacao);

	}

	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, Boolean juridico) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentariosAprovacao);
		if (!juridico) {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.SOLICITANTE_JURIDICO_ANALISE);
		}else {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.SOLICITANTE_JURIDICO_CANCELADO);
		}
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return (User) Session.get().getAttribute("user");
	}

}
