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
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.pagecomponentes.modal.ComentariosModal;
import br.com.seta.processo.pagecomponentes.modal.ErroDialog;
import br.com.seta.processo.pagecomponentes.modal.Modal;
import br.com.seta.processo.recebimento.formulario.EscanearDocumentacaoRecebimentoFormulario;
import br.com.seta.processo.recebimento.secoes.SecaoValidacao;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.validacao.ValidadorBean;
import br.com.seta.processo.validacoesCheck.TipoDocumentoCheck;

public class SecaoBotoesEscanearDocumentacaoRecebimento extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject	private ValidadorBean validador;
	@Inject private ExecuteRestAPI executeRestAPI;
	@Inject private DocumentoDao documentoDao;
	
	private ErroDialog erroDialog;
	private ComentariosModal comentariosModal;
	private ConfirmacaoModal confirmacaoModal;
	private AjaxButton voltarBtn;
	private SecaoValidacao secaoValidacao;
	private Recebimento recebimento;
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra;
	
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	private Long taskID;
	
	public SecaoBotoesEscanearDocumentacaoRecebimento(String id, SecaoValidacao secaoValidacao, Recebimento recebimento, EscanearDocumentacaoRecebimentoFormulario escanearDocumentacaoRecebimentoFormulario, final PageReference paginaAnterior) {
		super(id);
		
		this.recebimento = recebimento;
		this.solicitacaoIntencaoCompra = escanearDocumentacaoRecebimentoFormulario.getSolicitacao();
		this.taskID = escanearDocumentacaoRecebimentoFormulario.getTaskId();
		
		erroDialog = new ErroDialog("erroDialog", "erroDialog");
		comentariosModal = new ComentariosModal("comentariosModal", "comentariosModal", new BotoesModalComentarios());
		confirmacaoModal = new ConfirmacaoModal();
		this.secaoValidacao = secaoValidacao;
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		add(erroDialog, comentariosModal, confirmacaoModal, voltarBtn);
	}
	
	private class ConfirmacaoModal extends Modal{
		private static final long serialVersionUID = 1L;

		public ConfirmacaoModal() {
			super("confirmacaoModal", "confirmacaoModal", Modal.CONFIRMACAO, new ConteudoModalConfirmacaoFragment(), "Confirmação", new BotoesModalConfirmacao());
		}
		
	}
	
	private class ConteudoModalConfirmacaoFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		public ConteudoModalConfirmacaoFragment() {
			super("corpoModal", "conteudoModalConfirmacaoFragment", SecaoBotoesEscanearDocumentacaoRecebimento.this);
		}
		
	}
	
	private class BotoesModalConfirmacao extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton okBtn;
		
		public BotoesModalConfirmacao() {
			super("botao", "botoesModalConfirmacao", SecaoBotoesEscanearDocumentacaoRecebimento.this);
			
			okBtn = new AjaxButton("okBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					confirmacaoModal.oculta(target);
					try {
						
						validaDados();
						recebimento.setNecessidadeAjusteImpostos(secaoValidacao.getNecessidadeAjusteImpostos());
						recebimento.setIsImposto(secaoValidacao.getNecessidadeAjusteImpostos());
						Documento anexoDocEscaneado = secaoValidacao.getAnexoDocEscaneado();
						adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra);
						processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
						processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
						
						try {
							executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
							documentoDao.salvaDocumento(anexoDocEscaneado);
							setResponsePage(Atividades.class);
						} catch (BonitaException e) {
							e.printStackTrace();
						}

					} catch (ValidacaoBeanException e) {
						erroDialog.removeTodasMensagens().addMensagensErro(e.getMessages()).exibe(target);
					}					
					
				}

				private void validaDados() throws ValidacaoBeanException {
					String necessidadeAjusteImpostos = secaoValidacao.getNecessidadeAjusteImpostos();
					Documento anexoDocEscaneado = secaoValidacao.getAnexoDocEscaneado();
					ArrayList<String> erros = new ArrayList<String>();
					
					validador.valida(recebimento, TipoDocumentoCheck.class);
					
					if(necessidadeAjusteImpostos == null || necessidadeAjusteImpostos.trim().isEmpty()){
						erros.add("É necessário informar se existe a necessidade de ajustes de impostos");
					}
					if(anexoDocEscaneado == null){
						erros.add("É obrigatório anexar o documento escaneado");
					}
					
					if(erros.size() > 0)
						throw new ValidacaoBeanException(erros);
				}
			};
			
			add(okBtn);
		}
		
	}
	
	private class BotoesModalComentarios extends Fragment{
		private static final long serialVersionUID = 1L;

		public BotoesModalComentarios() {
			super("botao", "botoesModalComentarios", SecaoBotoesEscanearDocumentacaoRecebimento.this);
		}
		
	}
	
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(comentariosModal.getComentarios());
		historico.setStatus(VariaveisRecebimento.ESCANEAR_DOCUMENTO_PRODUTO);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null)solicitacao.setHistorico(new ArrayList<Historico>());solicitacao.getHistorico().add(historico);
	}


	private User getUser() {
		return 	(User) Session.get().getAttribute("user");

	}

}
