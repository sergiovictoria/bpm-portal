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
import org.apache.wicket.markup.html.panel.Fragment;
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
import br.com.seta.processo.pagecomponentes.modal.Modal;
import br.com.seta.processo.recebimento.formulario.IntegracaoParaPagamentoFormulario;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoBotoesIntegraParaPagamento extends Panel{

	private static final long serialVersionUID = 1L;
	
	private String comentarios;
	
	private ComentariosModal comentariosModal;
	private FinalizarModal finalizarModal;
	private AjaxButton voltarBtn;
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = new SolicitacaoIntencaoCompra();
	private Recebimento recebimento = new Recebimento();
	private OrRequisicao orRequisicao = new OrRequisicao();
	private Long taskID;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject private ExecuteRestAPI executeRestAPI;	
	
	public SecaoBotoesIntegraParaPagamento(String id, final PageReference paginaAnterior, IntegracaoParaPagamentoFormulario integracaoParaPagamentoFormulario) {
		super(id);
		
		solicitacaoIntencaoCompra = integracaoParaPagamentoFormulario.getSolicitacao();
		recebimento =  integracaoParaPagamentoFormulario.getRecebimento();
		orRequisicao = integracaoParaPagamentoFormulario.getRequisicao();
		taskID = integracaoParaPagamentoFormulario.getTaskId();
		
		
		comentariosModal = new ComentariosModal();
		finalizarModal = new FinalizarModal();
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		add(voltarBtn, comentariosModal, finalizarModal);
	}
	
	private class ComentariosModal extends Modal{
		private static final long serialVersionUID = 1L;
		
		
		public ComentariosModal() {
			super("comentariosModal", 
					"comentariosModal", 
					Modal.CONFIRMACAO, 
					new CorpoComentariosModal(), 
					"Comentarios", 
					new Fragment("botao", "botoesComentariosModal", SecaoBotoesIntegraParaPagamento.this));
			
		}
		
	}
	
	private class CorpoComentariosModal extends Fragment{
		private static final long serialVersionUID = 1L;

		private TextArea<String> comentariosTxt;
		
		public CorpoComentariosModal() {
			super("corpoModal", "corpoComentariosModal", SecaoBotoesIntegraParaPagamento.this);
			
			comentariosTxt = new TextArea<String>("comentariosTxt", new PropertyModel<String>(SecaoBotoesIntegraParaPagamento.this, "comentarios"));
			
			add(comentariosTxt);
		}
		
	}
	
	private class FinalizarModal extends Modal{
		private static final long serialVersionUID = 1L;

		public FinalizarModal() {
			super("finalizarModal", 
					"finalizarModal", 
					Modal.CONFIRMACAO, 
					new Fragment("corpoModal", "corpoModalFinalizar", SecaoBotoesIntegraParaPagamento.this), 
					"Finalizar", 
					new BotoesFinalizarModal());
		}
		
	}
	
	private class BotoesFinalizarModal extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton okBtn;
		
		public BotoesFinalizarModal() {
			super("botao", "botoesFinalizarModal", SecaoBotoesIntegraParaPagamento.this);

			okBtn = new AjaxButton("okBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra);
					
					processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
					processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
					processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
					
					try {
						executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
						setResponsePage(Atividades.class);
					} catch (BonitaException e) {
						e.printStackTrace();
					}
				}
			};
			
			add(okBtn);
		}
		
	}

	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentarios);
		historico.setStatus(VariaveisRecebimento.CLASSIFICAO_FISCAL_IMPOSTOS);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}


	private User getUser() {
		return (User) Session.get().getAttribute("user");

	}
	

}
