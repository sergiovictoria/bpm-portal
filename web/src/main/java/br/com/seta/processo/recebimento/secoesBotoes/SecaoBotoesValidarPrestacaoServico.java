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
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.pagecomponentes.modal.ErroDialog;
import br.com.seta.processo.pagecomponentes.modal.Modal;
import br.com.seta.processo.recebimento.formulario.ValidarPrestacaoServicoFormulario;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoBotoesValidarPrestacaoServico extends Panel{
	private static final long serialVersionUID = 1L;

	private String comentariosAprovacao;
	private String comentariosRejeicao;
	
	private Modal recebimentoAprovadoModal, recebimentoReprovadoModal;
	private ErroDialog erroDialog;
	private AjaxButton voltarBtn;
	
	private Recebimento recebimento;
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra;
	@Inject private ExecuteRestAPI executeRestAPI;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	private Long taskID;	
	
	public SecaoBotoesValidarPrestacaoServico(String id, final PageReference paginaAnterior, ValidarPrestacaoServicoFormulario validarPrestacaoServicoFormulario) {
		
		
		super(id);		
		
		this.solicitacaoIntencaoCompra = validarPrestacaoServicoFormulario.getSolicitacao(); 
		this.recebimento = validarPrestacaoServicoFormulario.getRecebimento();
		this.taskID = validarPrestacaoServicoFormulario.getTaskId();
		
		recebimentoAprovadoModal = new RecebimentoAprovadoModal();
		recebimentoReprovadoModal = new RecebimentoReprovadoModal();
		erroDialog = new ErroDialog("erroDialog", "erroDialog");
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		add(recebimentoAprovadoModal, recebimentoReprovadoModal, erroDialog, voltarBtn);
	}
	
	private class RecebimentoAprovadoModal extends Modal{
		private static final long serialVersionUID = 1L;
		
		public RecebimentoAprovadoModal() {
			super("recebimentoAprovadoModal", 
					"recebimentoAprovadoModal", 
					Modal.CONFIRMACAO, 
					new ConteudoModalConfirmacaoFragment("Deseja autorizar a execução?", new PropertyModel<String>(SecaoBotoesValidarPrestacaoServico.this, "comentariosAprovacao")), 
					"Aprovar",
					new FecharBtnFragment(),
					new RecebimentoAprovadoBtnFragment());
		}
		
	}
	
	private class RecebimentoReprovadoModal extends Modal{
		private static final long serialVersionUID = 1L;

		public RecebimentoReprovadoModal() {
			super("recebimentoReprovadoModal", 
					"recebimentoReprovadoModal", 
					Modal.CONFIRMACAO, 
					new ConteudoModalConfirmacaoFragment("Deseja rejeitar?", new PropertyModel<String>(SecaoBotoesValidarPrestacaoServico.this, "comentariosRejeicao")), 
					"Reprovar", 
					new FecharBtnFragment(),
					new RecebimentoReprovadoBtnFragment());
		}
		
	}
	
	private class ConteudoModalConfirmacaoFragment extends Fragment{
		private static final long serialVersionUID = 1L;
		
		private Label mensagemLbl;
		private TextArea<String> comentariosTxt;
		
		public ConteudoModalConfirmacaoFragment(String mensagem, IModel<String> comentariosModel) {
			super("corpoModal", "conteudoModalConfirmacaoFragment", SecaoBotoesValidarPrestacaoServico.this);
			
			mensagemLbl = new Label("mensagemLbl", mensagem);
			comentariosTxt = new TextArea<String>("comentariosTxt", comentariosModel);
			
			add(mensagemLbl, comentariosTxt);
		}		
	}
	
	private class FecharBtnFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		public FecharBtnFragment() {
			super("botao", "fecharBtnFragment", SecaoBotoesValidarPrestacaoServico.this);
		}
		
	}
	
	private class RecebimentoAprovadoBtnFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton recebimentoAprovadoBtn;
		
		public RecebimentoAprovadoBtnFragment() {
			super("botao", "recebimentoAprovadoBtnFragment", SecaoBotoesValidarPrestacaoServico.this);
			
			recebimentoAprovadoBtn = new AjaxButton("recebimentoAprovadoBtn") {			
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {					
					recebimentoAprovadoModal.oculta(target);
					if(comentariosAprovacao == null || comentariosAprovacao.trim().isEmpty())
						erroDialog.removeTodasMensagens().addMensagemErro("O comentário é obrigatório").exibe(target);
					
					adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,Boolean.TRUE);
					processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
					processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
					recebimento.setIsPrestacaoServico("Aprovado");
					
					try {
						executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
						setResponsePage(Atividades.class);
					} catch (BonitaException e) {
						e.printStackTrace();
					}
 
						
				}
			};
			
			add(recebimentoAprovadoBtn);
		}
		
	}
	
	private class RecebimentoReprovadoBtnFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton recebimentoReprovadoBtn;
		
		public RecebimentoReprovadoBtnFragment() {
			super("botao", "recebimentoReprovadoBtnFragment", SecaoBotoesValidarPrestacaoServico.this);
			
			recebimentoReprovadoBtn = new AjaxButton("recebimentoReprovadoBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					recebimentoReprovadoModal.oculta(target);
					if(comentariosRejeicao == null || comentariosRejeicao.trim().isEmpty())
						erroDialog.removeTodasMensagens().addMensagemErro("O comentário é obrigatório").exibe(target);
					adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,Boolean.FALSE);
					processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
					processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
					recebimento.setIsPrestacaoServico("Rejeitado");
					
					try {
						executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
						setResponsePage(Atividades.class);
					} catch (BonitaException e) {
						e.printStackTrace();
					}
 
				}
			};
			
			add(recebimentoReprovadoBtn);
		}
		
	}
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, Boolean aprovacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		if (aprovacao) {
		   historico.setMotivo(this.comentariosAprovacao);
		}else {
			historico.setMotivo(this.comentariosRejeicao);
		}
		historico.setStatus(VariaveisRecebimento.VALIDAR_PRESTACAO_SERVICO);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return (User) Session.get().getAttribute("user");
	}
	

}
