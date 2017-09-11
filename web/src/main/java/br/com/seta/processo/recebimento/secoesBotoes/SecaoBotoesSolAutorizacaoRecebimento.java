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
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.pagecomponentes.modal.ComentariosModal;
import br.com.seta.processo.pagecomponentes.modal.ErroDialog;
import br.com.seta.processo.pagecomponentes.modal.Modal;
import br.com.seta.processo.recebimento.formulario.SolicitarAutorizacaoRecebimentoFormulario;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoBotoesSolAutorizacaoRecebimento extends Panel {
	private static final long serialVersionUID = 1L;
	
	private ComentariosModal comentariosModal;
	private AprovacaoModal aprovacaoModal;
	private RejeicaoModal rejeicaoModal;
	private ErroDialog erroDialog;
	private AjaxButton voltarBtn;
	private Recebimento recebimento;
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject private ExecuteRestAPI executeRestAPI;
	private Long taskID;
	
	public SecaoBotoesSolAutorizacaoRecebimento(String id, SolicitarAutorizacaoRecebimentoFormulario solicitarAutorizacaoRecebimentoFormulario, final PageReference paginaAnterior) {
		super(id);
		
		this.solicitacaoIntencaoCompra = solicitarAutorizacaoRecebimentoFormulario.getSolicitacao(); 
		this.recebimento = solicitarAutorizacaoRecebimentoFormulario.getRecebimento();
		this.taskID = solicitarAutorizacaoRecebimentoFormulario.getTaskId();
		
		comentariosModal = new ComentariosModal("comentariosModal", "comentariosModal", new Fragment("botao", "botaoOkComentariosFragment", this));
		aprovacaoModal = new AprovacaoModal();
		rejeicaoModal = new RejeicaoModal();
		erroDialog = new ErroDialog("erroDialog");
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		add(comentariosModal, aprovacaoModal, rejeicaoModal, erroDialog, voltarBtn);
	}
	
	private class AprovacaoModal extends Modal{
		private static final long serialVersionUID = 1L;

		public AprovacaoModal() {
			super("aprovacaoModal", 
					"aprovacaoModal", 
					CONFIRMACAO, 
					new CorpoConfirmacaoModal("Deseja solicitar a aprovação do recebimento?"), 
					"Confirmação", 
					new BotaoFecharFragment(), 
					new AprovarBtnFragment());
		}
		
	}
	
	private class RejeicaoModal extends Modal{
		private static final long serialVersionUID = 1L;		
		
		public RejeicaoModal() {
			super("rejeicaoModal", 
					"rejeicaoModal", 
					CONFIRMACAO, 
					new CorpoConfirmacaoModal("Deseja rejeitar o recebimento?"), 
					"Confirmação", 
					new BotaoFecharFragment(), 
					new RejeitarBtnFragment());
		}
		
	}
	
	private class CorpoConfirmacaoModal extends Fragment{
		private static final long serialVersionUID = 1L;

		private Label texto;
		
		public CorpoConfirmacaoModal(String mensagem) {
			super("corpoModal", "corpoModalConfirmacao", SecaoBotoesSolAutorizacaoRecebimento.this);
			
			texto = new Label("texto", mensagem);
			
			add(texto);
		}
		
	}
	
	private class BotaoFecharFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		public BotaoFecharFragment() {
			super("botao", "botaoFecharFragment", SecaoBotoesSolAutorizacaoRecebimento.this);
		}		
	}
	
	private class AprovarBtnFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton aprovarBtn;
		
		public AprovarBtnFragment() {
			super("botao", "aprovarBtnFragment", SecaoBotoesSolAutorizacaoRecebimento.this);
			
			aprovarBtn = new AjaxButton("aprovarBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					aprovacaoModal.oculta(target);					
					if(validaComentarios(target)){
						
						recebimento.setIsProduto("Aprovado");
						adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,"Aprovado");
						processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
						processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
						try {
							executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
							setResponsePage(Atividades.class);
						} catch (BonitaException e) {
							e.printStackTrace();
						}
						
					}
				}
			};
			
			add(aprovarBtn);
		}
		
	}
	
	private class RejeitarBtnFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton rejeitarBtn;
		
		public RejeitarBtnFragment() {
			super("botao", "rejeitarBtnFragment", SecaoBotoesSolAutorizacaoRecebimento.this);
			
			rejeitarBtn = new AjaxButton("rejeitarBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					rejeicaoModal.oculta(target);
					if(validaComentarios(target)){
						
						recebimento.setIsProduto("Rejeitado");
						recebimento.setIsStatus("Rejeitado");
						adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,"Rejeitado");
						processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
						processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
						try {
							executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
							setResponsePage(Atividades.class);
						} catch (BonitaException e) {
							e.printStackTrace();
						}
						
					}
				}
			};
			
			add(rejeitarBtn);
		}
		
	}
	
	private boolean validaComentarios(AjaxRequestTarget target){
		String comentarios = comentariosModal.getComentarios();
		if(comentarios == null || comentarios.trim().isEmpty()){
			erroDialog.removeTodasMensagens().addMensagemErro("Os comentarios são obrigatórios").exibe(target);
			return false;
		}
		
		return true;
	}
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, String status) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(comentariosModal.getComentarios());
		if (status.equals("Aprovado")) {
			historico.setStatus(VariaveisRecebimento.RECEBIDO_DIVERGENCIA_APROVADO);
		}else {
			historico.setStatus(VariaveisRecebimento.RECEBIDO_DIVERGENCIA_REPROVADO);
		}
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null)solicitacao.setHistorico(new ArrayList<Historico>());solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return 	(User) Session.get().getAttribute("user");

	}

}
