package br.com.seta.processo.recebimento.secoesBotoes;

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
import org.apache.wicket.markup.html.panel.Panel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.recebimento.formulario.VerificarStatusDivergenciaFormulario;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoBotoesVerificarStatusDivergencia extends Panel{
	private static final long serialVersionUID = 1L;

	private AjaxButton cienteBtn, voltarBtn;
	private Recebimento recebimento;
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra;
	@Inject private ExecuteRestAPI executeRestAPI;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	private Long taskID;
	
	public SecaoBotoesVerificarStatusDivergencia(String id, final PageReference paginaAnterior, VerificarStatusDivergenciaFormulario verificarStatusDivergenciaFormulario) {
		super(id);
		
		this.solicitacaoIntencaoCompra = verificarStatusDivergenciaFormulario.getSolicitacao(); 
		this.recebimento = verificarStatusDivergenciaFormulario.getRecebimento();
		this.taskID = verificarStatusDivergenciaFormulario.getTaskId();
		
		cienteBtn = new AjaxButton("cienteBtn") {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra);
				recebimento.setIsStatus("Rejeitado");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
				
				
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		add(cienteBtn, voltarBtn);
	}
	
		
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo(VariaveisRecebimento.STATUS_DIVERGENCIA_MOTIVO);
		historico.setStatus(VariaveisRecebimento.STATUS_DIVERGENCIA_CHEKIN);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}


	private User getUser() {
		return (User) Session.get().getAttribute("user");

	}

}
