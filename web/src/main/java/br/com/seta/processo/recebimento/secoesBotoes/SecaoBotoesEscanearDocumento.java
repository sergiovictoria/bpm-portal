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

import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.constant.ConstantesRecebimento;
import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.pagecomponentes.UploadArquivo;
import br.com.seta.processo.pagecomponentes.modal.ErroDialog;
import br.com.seta.processo.pagecomponentes.modal.Modal;
import br.com.seta.processo.recebimento.formulario.EscanearDocumentoServicoFormulario;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoBotoesEscanearDocumento extends Panel {
	
	private static final long serialVersionUID = 1L;

	private long caseId;
	private String comentarios;
	
	private AjaxButton voltarBtn;
	private Modal anexarDocEscaneadoModal;
	private UploadArquivo uploadArquivo;
	private ErroDialog erroDialog;
	@Inject 
	private ExecuteRestAPI executeRestAPI;
	@Inject
	private DocumentoDao documentoDao;
	
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = new SolicitacaoIntencaoCompra();
	private Long taskID;
	
	public SecaoBotoesEscanearDocumento(String id, final PageReference paginaAnterior, EscanearDocumentoServicoFormulario escanearDocumentoServicoFormulario, long caseId) {
		super(id);		
		this.caseId = caseId;
		this.solicitacaoIntencaoCompra = escanearDocumentoServicoFormulario.getSolicitacao();
		this.taskID = escanearDocumentoServicoFormulario.getTaskId();
		
		anexarDocEscaneadoModal = new Modal("anexarDocEscaneadoModal", "anexarDocEscaneadoModal")
			.setTitulo("Anexar")
			.adicionaCorpo(new CorpoModalAnexoFragment())
			.adicionaBotao(new BotoesFragment());
		erroDialog = new ErroDialog("erroDialog", "erroDialog");
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		add(voltarBtn, anexarDocEscaneadoModal, erroDialog);
	}
	
	private class CorpoModalAnexoFragment extends Fragment {
		
		private static final long serialVersionUID = 1L;

		private TextArea<String> comentariosTxt;
		
		public CorpoModalAnexoFragment() {
			
			super("corpoModal", "corpoModalAnexoFragment", SecaoBotoesEscanearDocumento.this);
			comentariosTxt = new TextArea<String>("comentariosTxt", new PropertyModel<String>(SecaoBotoesEscanearDocumento.this, "comentarios"));
			uploadArquivo = new UploadArquivo("uploadArquivo");	
			add(comentariosTxt, uploadArquivo);
			
		}		
	}
	
	private class BotoesFragment extends Fragment{	
		private static final long serialVersionUID = 1L;
		
		private AjaxButton okButton;

		public BotoesFragment() {
			super("botao", "botoesFragment", SecaoBotoesEscanearDocumento.this);			
					
			okButton = new AjaxButton("okButton") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					anexarDocEscaneadoModal.oculta(target);
					if(!uploadArquivo.existeArquivoAnexado()){
						erroDialog
						.removeTodasMensagens()
						.addMensagemErro("O anexo é obrigatório")
						.exibe(target);
					}else{
						adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra);
						processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
						Documento docEscaneado = uploadArquivo.getAnexo(caseId, ConstantesRecebimento.DOCUMENTO_ESCANEADO);
						
						try {
							documentoDao.salvaDocumento(docEscaneado);
							executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
							setResponsePage(Atividades.class);
						} catch (BonitaException e) {
							e.printStackTrace();
						}
						
					}
				}
			};
			
			add(okButton);
		}
		
	}
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentarios);
		historico.setStatus(VariaveisRecebimento.ESCANEAR_DOCUMENTO_SERVICO);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return (User) Session.get().getAttribute("user");

	}

}
