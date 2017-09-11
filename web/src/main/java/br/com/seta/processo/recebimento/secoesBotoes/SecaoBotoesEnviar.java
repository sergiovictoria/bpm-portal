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
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.recebimento.formulario.ConsultaRequisicaoCompraFormulario;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoBotoesEnviar extends Panel {
	
	private static final long serialVersionUID = 1L;
	
	private AjaxButton voltarBtn;
	private AjaxButton enviarBtn;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject private ExecuteRestAPI executeRestAPI;
	private String comentariosAprovacao;
	
	public SecaoBotoesEnviar(String id, final PageReference paginaAnterior, final ConsultaRequisicaoCompraFormulario consultaRequisicaoCompraFormulario, final String status) {
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
				OrRequisicao orRequisicao = consultaRequisicaoCompraFormulario.getRequisicao();
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = consultaRequisicaoCompraFormulario.getSolicitacao();
				Recebimento recebimento  = consultaRequisicaoCompraFormulario.getRecebimento();
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra, status);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), consultaRequisicaoCompraFormulario.getTaskId() , processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		TextArea<String> comentariosAprovacao = new TextArea<String>("comentariosAprovacao", new PropertyModel<String>(this,"comentariosAprovacao"));
		add(voltarBtn, enviarBtn,comentariosAprovacao);
	}
	
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, String status) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentariosAprovacao);
		historico.setStatus(status);//(VariaveisRecebimento.CHECKIN_LOJA);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}


	private User getUser() {
		return 	(User) Session.get().getAttribute("user");
	}

}
