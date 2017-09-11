/**
 * 
 */
package br.com.seta.processo.suprimentos;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.TransacaoMongo;
import br.com.seta.processo.constant.VariaveisSolicitacaoContrato;
import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.ServicoContratado;
import br.com.seta.processo.dto.SolicitacaoContrato;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.helper.DocumentoHelper;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.pagecomponentes.modal.ErroDialog;
import br.com.seta.processo.provider.ListaContratosProvider;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.validacao.ValidadorBean;

/**
 * @author Eliel Sobral
 *
 */

public class ListaContratos extends BonitaPage {

	private static final long serialVersionUID = 1L;
	private Long caseID = getCaseId();
	private ListaContratosProvider provider = new ListaContratosProvider(caseID);
	private WebMarkupContainer rptListaContratos = new WebMarkupContainer("rptListaContratos");
	private ModalDetalhes modalDetalhes;
	private WebMarkupContainer modalEnviar;
	private AjaxButton btnModalDetalhes;
	private AjaxButton btnModalEnviar;
	private ErroDialog erroDialog;
	
	private SolicitacaoContrato solicitacaoContrato = new SolicitacaoContrato();
	private Contrato contratoSelecionado = new Contrato();

	@Inject 
	private TransacaoMongo transacaoMongo;
	@Inject	
	private ExecuteRestAPI executeRestAPI;
	@Inject 
	private ValidadorBean validador;
	
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	private transient User user = (User) Session.get().getAttribute("user");
	

	public ListaContratos(PageParameters pageParameters) throws ClientProtocolException, IOException, ParseException, InstantiationException, IllegalAccessException, HttpStatusException, BonitaException {
		super(pageParameters);
		
		setTituloPagina("Lista Contratos");
		provider = new ListaContratosProvider(caseID);
		this.solicitacaoContrato = (SolicitacaoContrato) executeRestAPI.retriveVariableTask(user,this.getTaskId(),SolicitacaoContrato.class,VariaveisSolicitacaoContrato.SOLICITACAO_CONTRATO);
		processVariables.put(VariaveisSolicitacaoContrato.SOLICITACAO_CONTRATO, this.solicitacaoContrato);
		executeRestAPI.executeUpdateVariable(user, getTaskId(), processVariables);

		ListaContratosForm form = new ListaContratosForm("form");
		this.modalDetalhes = (ModalDetalhes) new ModalDetalhes("modalDetalhes").setOutputMarkupId(true);
		this.modalEnviar = (WebMarkupContainer) new ModalEnviar("modalEnviar").setOutputMarkupId(true);
		this.erroDialog = new ErroDialog("erroDialog", "erroDialog");		
		
		form.add(modalDetalhes, modalEnviar, erroDialog);
		add(form);
	}

	private class ListaContratosForm extends Form<Object> {

		private static final long serialVersionUID = 1L;

		public ListaContratosForm(String id) {
			super(id);

			final DataView<Contrato> dtvListaContratos = new DataView<Contrato>("dtvListaContratos", provider) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(final Item<Contrato> item) {
					final Contrato contrato = (Contrato) item.getDefaultModelObject();
					item.setOutputMarkupId(true);

					item.add(new Label("lblDtvIntencaoCompra", contrato.getNumeroInstanciaOrigem()));
					item.add(new Label("lblDtvFornecedor", contrato.getNomeFornecedor()));

					final CheckBox chkRootcontainer = new CheckBox("chkRootcontainer", new PropertyModel<Boolean>(contrato, "selecionado"));

					btnModalDetalhes = new AjaxButton("btnModalDetalhes") {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
							modalDetalhes.setContrato(contrato);

							target.add(modalDetalhes);
							target.appendJavaScript("iniciarCamposData();");
							target.appendJavaScript("$('#modalDetalhes').modal('show')");
						}
					};

					item.add(btnModalDetalhes);
					item.add(chkRootcontainer);
				}
			};
			dtvListaContratos.setOutputMarkupId(true);

			btnModalEnviar = new AjaxButton("btnModalEnviar") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					int contador = 0;
					for (Contrato contrato : provider.getContratos()) {

						if (contrato.isSelecionado()) {
							contador++;
							solicitacaoContrato.getListaContratoSelected().add(contrato.getNumeroInstanciaOrigem());
						}

					}
					if (contador == 0) {
						setMensagemErro("Selecione um contrato para enviar!", target);
					} else {
						target.add(modalEnviar);
						target.appendJavaScript("$('#modalEnviar').modal('show')");
					}
				}

			};

			dtvListaContratos.setItemsPerPage(10L);
			AjaxPagingNavigator ajaxPagingNavigator = new AjaxPagingNavigator("ajaxPagingNavigator", dtvListaContratos);

			rptListaContratos.setOutputMarkupId(true);

			rptListaContratos.add(dtvListaContratos, ajaxPagingNavigator);

			add(btnModalEnviar);
			add(rptListaContratos);
		}
	}

	/*
	 * MODAL ENVIAR
	 */
	private final class ModalEnviar extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public ModalEnviar(String id) {
			super(id);

			AjaxButton btnEnviar = new AjaxButton("btnEnviar") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

					for (Contrato c : provider.getContratosAtualizadosParaEnviar()) {
						boolean dadosOK = validaCamposPreenchidos(c);
						if (dadosOK) {
							try {
								atualizaDadosContrato(c);
								exibeMensagemSucesso("Enviar", "O contrato # " + c.getNumeroContrato() + " foi enviado com sucesso!", target);
							} catch (BonitaException e) {
								exibeMensagemAdvertencia("Enviar", "Ao tentar atuliazar dados no mongo db, entre em contato com adminstrador do sistema", target);
							}
						} else {
							exibeMensagemAdvertencia("Erro", "Existem dados incompletos no contrato " + c.getNumeroContrato() + ", por favor verifique", target);
						}
					}

					provider.refresh();

					if (provider.getContratos().size()==0) {
						Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
						processVariables.put(VariaveisSolicitacaoContrato.SOLICITACAO_CONTRATO,solicitacaoContrato);
						try {
							executeRestAPI.executeFlowAndUpdateVariable(user, getTaskId(), processVariables);
							setResponsePage(Atividades.class);
						} catch (Exception e) {
							setMensagemErro("Erro ao enviar para BPM - Seta !", target);
						}
					}

					target.add(rptListaContratos, modalDetalhes);
				}

				private boolean validaCamposPreenchidos(Contrato c) {
					boolean validado = true;

					if (c.getNumeroContrato() == 0) {
						validado = false;
					}
					if (c.getNumeroInstanciaJuridico() == 0) {
						validado = false;
					}
					if (null == c.getDataInicioContrato()) {
						validado = false;
					}
					if (null == c.getDataFimContrato()) {
						validado = false;
					}
					if (null == c.getContrato()) {
						validado = false;
					}
					if (null == c.getObservacaoContrato() || "".equals(c.getObservacaoContrato())) {
						validado = false;
					}
					return validado;
				}

				/**
				 * @param c
				 * @throws BonitaException 
				 */
				private void atualizaDadosContrato(Contrato c) throws BonitaException {
					
					Set<ServicoContratado> servicosContratados = new HashSet<ServicoContratado>();
					OrRequisicao orRequisicao = (OrRequisicao) executeRestAPI.findObjectForProceesID(c.getNumeroInstanciaOrigem(),OrRequisicao.class);
					Set<OrReqitensdespesa> orReqitensdespesas = orRequisicao.getOrReqitensdespesas(); 
					
					for(OrReqitensdespesa dto :orReqitensdespesas ) {
						ServicoContratado servicoContratado = new ServicoContratado();
						servicoContratado.setCodigoItem(new Long(dto.getCodproduto()));
						servicoContratado.setDescricaoItem(dto.getDescricao());
						servicoContratado.setNumeroContrato(c.getNumeroContrato());
						servicosContratados.add(servicoContratado);
					}
					
					c.setStatusContrato(4L);
					c.setServicoContratado(servicosContratados);
					transacaoMongo.remove("numeroInstanciaOrigem", Long.valueOf(c.getNumeroInstanciaOrigem()), Contrato.class);
					transacaoMongo.save(c,Contrato.class);
					
				}
			};

			add(btnEnviar);

		}

	}

	/*
	 * MODAL DETALHES
	 */
	private final class ModalDetalhes extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		private TextField<String> nomeFornecedor;
		private DateTextField dataInicioContrato;
		private DateTextField dataFimContrato;
		private TextField<String> numeroContrato;
		private TextField<String> numeroInstanciaJuridico;
		private TextArea<String> observacaoContrato;		
		Contrato contrato = new Contrato();

		private FileUploadField anexoField;
		private Documento arquivo = new Documento();
		private FileUpload fileUpload;

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModalDetalhes(String id) {
			super(id);

			this.nomeFornecedor = new TextField<String>("nomeFornecedor", new PropertyModel(this, "contrato.nomeFornecedor"));
			this.nomeFornecedor.setEnabled(false);
			this.dataInicioContrato = new DateTextField("dataInicioContrato", new PropertyModel<Date>(this, "contrato.dataInicioContrato"), "dd/MM/yyyy");
			this.dataFimContrato = new DateTextField("dataFimContrato", new PropertyModel<Date>(this, "contrato.dataFimContrato"), "dd/MM/yyyy");
			this.numeroContrato = new TextField<>("numeroContrato", new PropertyModel(this, "contrato.numeroContrato"));
			this.numeroInstanciaJuridico = new TextField<>("numeroInstanciaJuridico", new PropertyModel(this, "contrato.numeroInstanciaJuridico"));
			this.observacaoContrato = new TextArea<>("observacaoContrato", new PropertyModel(this, "contrato.observacaoContrato"));

			this.anexoField = new FileUploadField("anexoField");

			AjaxButton btnConfirmar = new AjaxButton("btnConfirmar") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					fileUpload = anexoField.getFileUpload();
					if (null != fileUpload) {
						DocumentoHelper.preencheDocumento(fileUpload, 0, arquivo);	
						contrato.setContrato(anexoField.getFileUpload().getBytes());
					}
					
					try {
						validador.valida(contrato);
						target.add(rptListaContratos);
					} catch (ValidacaoBeanException e) {
						erroDialog.removeTodasMensagens()
						.addMensagensErro(e.getMessages())
						.addFuncaoJsNoClickDoBtnFechar("$('#modalDetalhes').modal('show')")
						.exibe(target);
					}

					
				}
			};

			add(nomeFornecedor);
			add(dataInicioContrato);
			add(dataFimContrato);
			add(numeroContrato);
			add(numeroInstanciaJuridico);
			add(observacaoContrato);

			add(anexoField);
			add(btnConfirmar);

		}

		public final void setContrato(Contrato contrato) {
			this.contrato = contrato;
		}

	}	

	public Contrato getContratoSelecionado() {
		return contratoSelecionado;
	}

	public void setContratoSelecionado(Contrato contratoSelecionado) {
		this.contratoSelecionado = contratoSelecionado;
	}
	


}
