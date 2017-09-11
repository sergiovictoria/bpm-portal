package br.com.seta.processo.recebimento.secoesBotoes;

import static br.com.seta.processo.cadastrofornecedores.dominios.SimNao.NAO;
import static br.com.seta.processo.cadastrofornecedores.dominios.SimNao.SIM;
import static br.com.seta.processo.dominios.TipoDivergencia.PARCIAL;
import static br.com.seta.processo.dominios.TipoDivergencia.TOTAL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.pagecomponentes.modal.ErroDialog;
import br.com.seta.processo.pagecomponentes.modal.Modal;
import br.com.seta.processo.recebimento.formulario.SimularGuiaCegaFormulario;
import br.com.seta.processo.recebimento.guiacega.GuiaCega;
import br.com.seta.processo.recebimento.secoes.SecaoValidacao;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.validacao.ValidadorBean;
import br.com.seta.processo.validacoesCheck.GuiaCegaCheck;

public class SecaoBotoesGuiaCega extends Panel {
	private static final long serialVersionUID = 1L;	

	@Inject	
	private ValidadorBean validador;
	@Inject 
	private ExecuteRestAPI executeRestAPI;
	@Inject
	private DocumentoDao documentoDao;
	
	private AjaxButton voltarBtn;
	private AjaxButton submitBtn;
	private ImprimirBtn imprimirBtn;
	private Link<Void> abrirGuiaCegaLnk;
	private ErroDialog erroDialog;
	private ModalConfirmacao modalConfirmacao;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	private Long taskID;
	private String comentarios;
	private SecaoValidacao secaoValidacao;
	private OrRequisicao requisicao;
	private Recebimento recebimento;
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra;

	public SecaoBotoesGuiaCega(String id, final Recebimento recebimento, SecaoValidacao secaoValidacao, SimularGuiaCegaFormulario simularGuiaCegaFormulario, final PageReference paginaAnterior) {
		super(id);

		simularGuiaCegaFormulario.getCaseId();
		
		this.solicitacaoIntencaoCompra = simularGuiaCegaFormulario.getSolicitacao(); 
		this.recebimento = simularGuiaCegaFormulario.getRecebimento();
		this.requisicao = simularGuiaCegaFormulario.getRequisicao();
		this.taskID = simularGuiaCegaFormulario.getTaskId();

		this.secaoValidacao = secaoValidacao;
		this.recebimento = recebimento;
		this.imprimirBtn = new ImprimirBtn();
		
		this.abrirGuiaCegaLnk = new Link<Void>("abrirGuiaCegaLnk") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				GuiaCega guiaCega = new GuiaCega(requisicao, solicitacaoIntencaoCompra, recebimento);
				setResponsePage(guiaCega);
			}
			
		};
		
		this.submitBtn = new AjaxButton("submitBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.add(modalConfirmacao);
				modalConfirmacao.exibe(target);
			}
		};
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};

		modalConfirmacao = new ModalConfirmacao();
		
		erroDialog = new ErroDialog("erroDialog", "erroDialog");
		

		add(imprimirBtn, abrirGuiaCegaLnk, submitBtn, modalConfirmacao, erroDialog, voltarBtn);
	}

	private class ModalConfirmacao extends Modal{
		private static final long serialVersionUID = 1L;

		private CorpoModalFragment corpoModalFragment;
		private BotoesModalFragment botoesModalFragment;

		public ModalConfirmacao() {
			super("modalConfirmacao", "modalConfirmacao", Modal.CONFIRMACAO);

			setOutputMarkupId(true);

			corpoModalFragment = new CorpoModalFragment();
			botoesModalFragment = new BotoesModalFragment();

			setTitulo("Confirmação").adicionaCorpo(corpoModalFragment).adicionaBotao(botoesModalFragment);
		}

	}

	private class ImprimirBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;

		public ImprimirBtn() {
			super("imprimirBtn");
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			target.appendJavaScript("document.getElementById('abrirGuiaCegaLnk').click()");
		}	
		
	}

	private class CorpoModalFragment extends Fragment{
		private static final long serialVersionUID = 1L;		

		public CorpoModalFragment() {
			super("corpoModal", "corpoModal", SecaoBotoesGuiaCega.this);
			TextArea<String> comentariosTxt = new TextArea<String>("comentariosTxt", new PropertyModel<String>(SecaoBotoesGuiaCega.this, "comentarios"));
			add(comentariosTxt);
		}

		@Override
		protected void onBeforeRender() {
			super.onBeforeRender();			
		}		

	}

	public class BotoesModalFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton enviarBtn, rejeitarBtn, solicitarVerificacaoBtn;

		public BotoesModalFragment() {
			super("botao", "botoesModal", SecaoBotoesGuiaCega.this);

			setOutputMarkupId(true);

			this.enviarBtn = new EnviarBtn();
			this.rejeitarBtn = new RejeitarBtn();
			this.solicitarVerificacaoBtn = new SolicitarVerificacaoBtn();

			add(enviarBtn, rejeitarBtn, solicitarVerificacaoBtn);
		}

		@Override
		protected void onBeforeRender() {			
			super.onBeforeRender();

			if(recebimento.getIsDivergencia() == null || recebimento.getIsDivergencia().equals(NAO)){
				this.enviarBtn.setVisible(true);
				this.rejeitarBtn.setVisible(false);
				this.solicitarVerificacaoBtn.setVisible(false);
			}				
			else{
				if(recebimento.getTipoDivergencia().equals(TOTAL)){
					this.enviarBtn.setVisible(false);
					this.rejeitarBtn.setVisible(true);
					this.solicitarVerificacaoBtn.setVisible(false);
				}								
				else{
					this.enviarBtn.setVisible(false);
					this.rejeitarBtn.setVisible(false);
					this.solicitarVerificacaoBtn.setVisible(true);
				}					
			}	
		}

	}

	private class EnviarBtn extends AjaxButton {
		private static final long serialVersionUID = 1L;
		// Sem Divergência
		public EnviarBtn() {
			super("enviarBtn");	
			setOutputMarkupId(true);
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			try {
				validarDados();

				persistirDocumentoDeRecebimento();
				
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,"Sem");
				recebimento.setIsDivergencia("Sem");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}

			} catch (ValidacaoBeanException e) {
				modalConfirmacao.oculta(target);
				erroDialog.removeTodasMensagens().addMensagensErro(e.getMessages()).exibe(target);
			}
		}
	}

	private class RejeitarBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;

		public RejeitarBtn() {
			super("rejeitarBtn");			
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			//  Divergência Total 
			try {
				validarDados();
				
				persistirNotaDivergencia();				
				
				recebimento.setIsDivergencia("Total");
				recebimento.setIsStatus("Rejeitado");
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,"Total");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}

			} catch (ValidacaoBeanException e) {
				modalConfirmacao.oculta(target);
				erroDialog.removeTodasMensagens().addMensagensErro(e.getMessages()).exibe(target);
			}
		}
	}

	private class SolicitarVerificacaoBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;

		public SolicitarVerificacaoBtn() {
			super("solicitarVerificacaoBtn");			
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			// Divergência Parcial
			try {
				validarDados();
				
				persistirNotaDivergencia();
				persistirDocumentoDeRecebimento();
				
				recebimento.setIsDivergencia("Parcial");
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,"Parcial");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisRecebimento.RECEBIMENTO,recebimento);
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}

			} catch (ValidacaoBeanException e) {
				modalConfirmacao.oculta(target);
				erroDialog.removeTodasMensagens().addMensagensErro(e.getMessages()).exibe(target);
			}
		}

	}

	private void validarDadosFormulario() throws ValidacaoBeanException{
		validador.valida(recebimento, GuiaCegaCheck.class);		
	}

	private void validarAnexoDocDivergencia() throws ValidacaoBeanException{
		if(recebimento.getIsDivergencia().equals(SIM)){
			Documento docDivergencias = secaoValidacao.getAnexoDocDivergencias();
			if(docDivergencias == null)
				throw new ValidacaoBeanException(Arrays.asList("É necessário anexar uma nota com divergência"));
		}
	}
	
	private void validarAnexoDocRecebimento() throws ValidacaoBeanException{
		Documento docRecebimento = secaoValidacao.getAnexoDocRecebimento();
		
		if(naoPossuiDivergencia() || possuiDivergenciaParcial()){
			if(docRecebimento == null)
				throw new ValidacaoBeanException(Arrays.asList("É necessário anexar o documento de recebimento"));
		}

	}
	
	private boolean naoPossuiDivergencia(){
		if(recebimento.getIsDivergencia() == null || recebimento.getIsDivergencia().equals(NAO))
			return true;
		
		return false;
	}
	
	private boolean possuiDivergenciaParcial(){
		if(recebimento.getIsDivergencia() != null && recebimento.getIsDivergencia().equals(SIM)){
			if(recebimento.getTipoDivergencia() != null && recebimento.getTipoDivergencia().equals(PARCIAL))
				return true;
		}
		
		return false;
	}

	public void validarDados() throws ValidacaoBeanException {
		validarDadosFormulario();
		validarAnexoDocDivergencia();
		validarAnexoDocRecebimento();
	}


	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, String status) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		
		if (status.equals("Sem")) {
			historico.setMotivo("");
			historico.setComentario(this.comentarios);
			historico.setStatus(VariaveisRecebimento.CHECKIN_GUIA_CEGA_SEM);
		}else if (status.equals("Parcial")) {
			historico.setMotivo(recebimento.getMotivoOuJustificativaDivergencia());
			historico.setComentario(this.comentarios);
			historico.setStatus(VariaveisRecebimento.CHECKIN_GUIA_CEGA_PARCIAL);
		}else {
			historico.setMotivo("");
			historico.setComentario(this.comentarios);
			historico.setStatus(VariaveisRecebimento.CHECKIN_GUIA_CEGA_TOTAL);
		}
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null)solicitacao.setHistorico(new ArrayList<Historico>());solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return (User) Session.get().getAttribute("user");

	}
	
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	private void persistirNotaDivergencia(){
		Documento docDivergencias = secaoValidacao.getAnexoDocDivergencias();
		if(docDivergencias != null)
			documentoDao.salvaDocumento(docDivergencias);
	}
	
	private void persistirDocumentoDeRecebimento(){
		Documento docRecebimento = secaoValidacao.getAnexoDocRecebimento();
		if(docRecebimento != null)
			documentoDao.salvaDocumento(docRecebimento);
	}

}
