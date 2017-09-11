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
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.pagecomponentes.UploadArquivo;
import br.com.seta.processo.pagecomponentes.modal.Modal;
import br.com.seta.processo.recebimento.formulario.MedirServicoFormulario;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoBotoesMedicaoService extends Panel{
	private static final long serialVersionUID = 1L;	

	private String comentarios;
	private Modal comentariosModal, modalConfirmacaoMedicaoOk, modalConfirmacaoMedicaoDivergente;
	private UploadArquivo anexoDocMedicao;
	private AjaxButton voltarBtn;
	private Templete  parentPage;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject private ExecuteRestAPI executeRestAPI;
	@Inject private DocumentoDao documentoDao;
	private Recebimento recebimento;
	SolicitacaoIntencaoCompra solicitacaoIntencaoCompra;
	private Long caseID, taskID;

	public SecaoBotoesMedicaoService(String id, Templete parentPage, MedirServicoFormulario medirServicoFormulario, final PageReference paginaAnterior) {
		super(id);	

		this.parentPage = parentPage;
		this.caseID = medirServicoFormulario.getCaseId();
		this.taskID = medirServicoFormulario.getTaskId();
		this.solicitacaoIntencaoCompra = medirServicoFormulario.getSolicitacao(); 
		this.recebimento = medirServicoFormulario.getRecebimento();

		this.comentariosModal = new Modal("comentariosModal", "comentariosModal");
		comentariosModal
		.setTitulo("Comentários")
		.adicionaCorpo(new CorpoModalFragment())
		.adicionaBotao(new FecharBtnFragment())
		.adicionaBotao(new Fragment("botao", "comentariosOkBtnFragment", SecaoBotoesMedicaoService.this));

		modalConfirmacaoMedicaoOk = new ModalConfirmacaoMedicaoOk();
		modalConfirmacaoMedicaoDivergente = new ModalConfirmacaoMedicaoDivergente();

		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		add(comentariosModal, modalConfirmacaoMedicaoOk, modalConfirmacaoMedicaoDivergente, voltarBtn);
	}

	private class ModalConfirmacaoMedicaoOk extends Modal{
		private static final long serialVersionUID = 1L;

		public ModalConfirmacaoMedicaoOk() {
			super("modalConfirmacaoMedicaoOk", "modalConfirmacaoMedicaoOk", Modal.CONFIRMACAO);
			setTitulo("Confirmação")
			.adicionaCorpo(new ConteudoModalsConfirmacao("Deseja confirmar que a medição está ok e concluir?"))
			.adicionaBotao(new FecharBtnFragment())
			.adicionaBotao(new MedicaoOkBtnFragment());
		}

	}

	private class ModalConfirmacaoMedicaoDivergente extends Modal{
		private static final long serialVersionUID = 1L;

		public ModalConfirmacaoMedicaoDivergente() {
			super("modalConfirmacaoMedicaoDivergente", "modalConfirmacaoMedicaoDivergente", Modal.CONFIRMACAO);
			setTitulo("Confirmação")
			.adicionaCorpo(new ConteudoModalsConfirmacao("Deseja confirmar que a medição está divergente e concluir?"))
			.adicionaBotao(new FecharBtnFragment())
			.adicionaBotao(new MedicaoDivergenteBtnFragment());
		}

	}

	private class ConteudoModalsConfirmacao extends Fragment{
		private static final long serialVersionUID = 1L;

		private Label textoModalConfirmacao;

		public ConteudoModalsConfirmacao(String mensagem) {
			super("corpoModal", "conteudoModalsConfirmacao", SecaoBotoesMedicaoService.this);

			textoModalConfirmacao = new Label("textoModalConfirmacao", mensagem);

			add(textoModalConfirmacao);
		}

	}

	private class CorpoModalFragment extends Fragment {
		private static final long serialVersionUID = 1L;		

		public CorpoModalFragment() {
			super("corpoModal", "corpoModalCometarios", SecaoBotoesMedicaoService.this);
			anexoDocMedicao = new UploadArquivo("anexoDocMedicao");
			TextArea<String> comentariosTxt = new TextArea<String>("comentariosTxt", new PropertyModel<String>(SecaoBotoesMedicaoService.this, "comentarios"));
			add(comentariosTxt, anexoDocMedicao);
		}
	}

	private class MedicaoOkBtnFragment extends Fragment {
		private static final long serialVersionUID = 1L;

		private AjaxButton okBtn;

		public MedicaoOkBtnFragment() {
			super("botao", "medicaoOkBtnFragment", SecaoBotoesMedicaoService.this);

			okBtn = new AjaxButton("okBtn") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					modalConfirmacaoMedicaoOk.oculta(target);

					if(comentarios == null || comentarios.trim().isEmpty()){
						parentPage.setMensagemErro("Os comentários são obrigatórios", target);
					}else{
						Documento documento = anexoDocMedicao.getAnexo(caseID, "Documento de Medição de Serviço"); 

						if (documento!=null) {
							recebimento.setIsmedicacao("OK");
							documentoDao.salvaDocumento(documento);
							adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,Boolean.FALSE);
							processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
							processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
							try {
								executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
								setResponsePage(Atividades.class);
							} catch (BonitaException e) {
								e.printStackTrace();
							}
						}else {
							parentPage.setMensagemErro("Anexo obrigatório", target);
						}
					}
				}
			};

			add(okBtn);
		}

	}

	private class MedicaoDivergenteBtnFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton okBtn;

		public MedicaoDivergenteBtnFragment() {
			super("botao", "medicaoDivergenteBtnFragment", SecaoBotoesMedicaoService.this);

			okBtn = new AjaxButton("okBtn") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					modalConfirmacaoMedicaoDivergente.oculta(target);

					if(comentarios == null || comentarios.trim().isEmpty()){
						parentPage.setMensagemErro("Os comentários são obrigatórios", target);
					}else{
						Documento documento = anexoDocMedicao.getAnexo(caseID, "Documento de Medição de Serviço"); 

						if (documento!=null) {
							documentoDao.salvaDocumento(documento);
							recebimento.setIsmedicacao("Divergente");
							adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,Boolean.TRUE);
							processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
							processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
							try {
								executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
								setResponsePage(Atividades.class);
							} catch (BonitaException e) {
								e.printStackTrace();
							}
						}else {
							parentPage.setMensagemErro("Anexo obrigatório", target);
						}
					}
				}
			};

			add(okBtn);
		}

	}

	private class FecharBtnFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		public FecharBtnFragment() {
			super("botao", "fecharBtnFragment", SecaoBotoesMedicaoService.this);
		}

	}



	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, Boolean divergente) {
		
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentarios);
		if (!divergente) {
			historico.setStatus(VariaveisRecebimento.MEDIR_SERVIÇO_OK);
		}else {
			historico.setStatus(VariaveisRecebimento.MEDIR_SERVIÇO_DIVERGENTE);
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
