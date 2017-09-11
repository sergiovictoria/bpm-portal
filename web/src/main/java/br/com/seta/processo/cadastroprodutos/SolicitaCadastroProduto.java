package br.com.seta.processo.cadastroprodutos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.constant.VariaveisCadastroProduto;
import br.com.seta.processo.dto.AprovacaoGerenciaComercial;
import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.HistoricoProduto;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.page.TaskPage;
import br.com.seta.processo.pagecomponentes.OrdemColunaTabelaCssProvider;
import br.com.seta.processo.provider.HistoricoProdutoProvider;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.service.GroupService;

public class SolicitaCadastroProduto extends TaskPage {

	private static final long serialVersionUID = 1L;	

	private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	@Inject	private ExecuteRestAPI executeRestAPI;	
	@Inject private GroupService groupService;

	private final WebMarkupContainer cadastroProdutosMarkupContainer = new WebMarkupContainer("cadastroProdutosMarkupContainer");
	private transient User user = (User) Session.get().getAttribute("user");
	private CadastroProduto cadastroProduto; 
	private FormularioProduto formularioProduto;
	private HistoricoProdutoProvider historicoProdutoProvider;

	public SolicitaCadastroProduto(final PageParameters pageParameters) throws HttpStatus401Exception, ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException {
		super(pageParameters);
		cadastroProduto = (CadastroProduto) executeRestAPI.retriveVariableTask(user, super.getTaskId(), CadastroProduto.class, VariaveisCadastroProduto.CADASTRO_PRODUTO);
		formularioProduto = (FormularioProduto) executeRestAPI.retriveVariableTask(user, getTaskId(), FormularioProduto.class, VariaveisCadastroProduto.FORMULARIO_PRODUTO);
		Collections.sort(this.formularioProduto.getHistoricoProdutos(), Collections.reverseOrder(HistoricoProduto.Comparators.DATA));
		historicoProdutoProvider = new HistoricoProdutoProvider(formularioProduto.getHistoricoProdutos());
		this.formularioProduto.setEmailsAprovadoNovosCadastro(EmailsAprovadoNovosCadastro());
		this.formularioProduto.setEmailsPrecificacao(EmailsPrecificao());
		this.formularioProduto.setEmailsLogistica(EmailsLogistica());
		this.formularioProduto.setEmailsCadastro(EmailsCdastro());
		this.formularioProduto.setEmailsFiscal(EmailsFiscal());
		add(new CadastroProdutosForm("CadastroProdutosForm",pageParameters));
	}

	public class CadastroProdutosForm extends Form<PageParameters> {

		private static final long serialVersionUID = 1L;
		private java.util.Date date = new java.util.Date();
		private final DataView<HistoricoProduto> historicoView;
		private WebMarkupContainer historicoContainer;
		private SecaoAprovacao secaoAprovacao;
		private PreenchimentoInternoPanel preenchimentoInternoPanel;

		public CadastroProdutosForm(String id, final PageParameters parameters) throws HttpStatus401Exception, ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException {
			super(id, new CompoundPropertyModel<PageParameters>(parameters));
			cadastroProdutosMarkupContainer.setOutputMarkupId(true);			
			
			preenchimentoInternoPanel = new PreenchimentoInternoPanel("preenchimentoInternoPanel", cadastroProduto, PreenchimentoInternoPanel.NAO_BUSCAR_DADOS_RESP_PREENCHIMENTO_DO_USUARIO_DA_SESSAO);
			preenchimentoInternoPanel.setEnabled(false);
			secaoAprovacao = new SecaoAprovacao("secaoAprovacao", formularioProduto.getAprovacaoGerenciaComercial());
			historicoContainer = (WebMarkupContainer) new WebMarkupContainer("historicoContainer").setOutputMarkupId(true);
			historicoView = new DataView<HistoricoProduto>("sortingHistorico", historicoProdutoProvider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<HistoricoProduto> item) {
					HistoricoProduto historicoProduto = (HistoricoProduto) item.getModelObject();
					item.add(new Label("satus", historicoProduto.getStatus()));
					item.add(new Label("nome", historicoProduto.getNome()));
					item.add(new Label("data", dateFormater.format(historicoProduto.getData()) ));
					item.add(new Label("motivo", historicoProduto.getMotivo()));
					item.add(new Label("comentario", historicoProduto.getComentario()));
				}
			};			

			PagingNavigator navigator = new AjaxPagingNavigator("navigator", historicoView){

				private static final long serialVersionUID = 1L;

				@Override
				protected void onAjaxEvent(AjaxRequestTarget target) {
					target.add(historicoContainer);
				}
			};

			AjaxFallbackOrderByBorder<?> DisplayMotivo = new OrderByBorderTabelaHistorico("DisplayMotivo", "DisplayMotivo", historicoProdutoProvider, historicoContainer, historicoView, new OrdemColunaTabelaCssProvider());			
			AjaxFallbackOrderByBorder<?> DisplayStatus = new OrderByBorderTabelaHistorico("DisplayStatus", "DisplayStatus", historicoProdutoProvider, historicoContainer, historicoView, new OrdemColunaTabelaCssProvider());
			AjaxFallbackOrderByBorder<?> DisplayNome = new OrderByBorderTabelaHistorico("DisplayNome", "DisplayNome", historicoProdutoProvider, historicoContainer, historicoView, new OrdemColunaTabelaCssProvider()); 
			AjaxFallbackOrderByBorder<?> displayData = new OrderByBorderTabelaHistorico("displayData", "displayData", historicoProdutoProvider, historicoContainer, historicoView, new OrdemColunaTabelaCssProvider());

			Button exibirPlanilhaBtn =  new Button("exibirPlanilhaBtn"){			
				private static final long serialVersionUID = 1L;
				@Override
				public void onSubmit() {
					try {						
						setResponsePage(new VisualizaDadosProduto(parameters, this.getPage().getPageReference(), formularioProduto));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			};

			historicoView.setItemsPerPage(10L);
			historicoContainer.add(historicoView, navigator, DisplayMotivo, DisplayStatus, DisplayNome, displayData);
			cadastroProdutosMarkupContainer.add(exibirPlanilhaBtn, preenchimentoInternoPanel ,secaoAprovacao,historicoContainer);
			//cadastroProdutosMarkupContainer.add(exibirPlanilhaBtn,nomeRespPreench,emailSolicitante,telefoneSolicitante,area,nomeSolicitante, dataHoraSolicitacao, descricao, secaoAprovacao,historicoContainer);
			add(cadastroProdutosMarkupContainer);
			if(historicoView.getItemCount() == 0) historicoContainer.setVisible(false);
		}
	}

	/**
	 * Seção que contém os dados da aprovação da Gerência Comercial
	 * 
	 * @author Sérgio da Victória
	 *
	 */
	private class SecaoAprovacao extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		Label nomeAprovador, comentariosAprovador, dataAprovacao;		
		String nomeAprovadorValor, comentariosAprovadorValor,  dataAprovacaoValor = "";
		public SecaoAprovacao(String id, AprovacaoGerenciaComercial aprovacaoGerenciaComercial) {
			super(id);			
			if(aprovacaoGerenciaComercial != null){
				nomeAprovadorValor = aprovacaoGerenciaComercial.getNome();
				comentariosAprovadorValor = aprovacaoGerenciaComercial.getComentarios();
				dataAprovacaoValor = aprovacaoGerenciaComercial.getDataHora() != null ? dateFormater.format(aprovacaoGerenciaComercial.getDataHora()) : "";
			}			

			nomeAprovador = new Label("nomeAprovador", nomeAprovadorValor);
			comentariosAprovador = new Label("comentariosAprovador", comentariosAprovadorValor);
			dataAprovacao = new Label("dataAprovacao", dataAprovacaoValor);

			add(nomeAprovador, comentariosAprovador, dataAprovacao);
		}		
	}

	/**
	 * OrderByBorder customizado para a tabela de histórico
	 * 
	 * @author Sérgio da Victória
	 *
	 */
	@SuppressWarnings("rawtypes")
	private class OrderByBorderTabelaHistorico extends AjaxFallbackOrderByBorder{
		private static final long serialVersionUID = 1L;

		private WebMarkupContainer historicoContainer;
		private DataView<HistoricoProduto> historicoView;

		@SuppressWarnings("unchecked")
		public OrderByBorderTabelaHistorico(String id, Object sortProperty, ISortStateLocator stateLocator,
				WebMarkupContainer historicoContainer, DataView<HistoricoProduto> historicoView) {
			super(id, sortProperty, stateLocator);
			this.historicoContainer = historicoContainer;
			this.historicoView = historicoView;
		}

		@SuppressWarnings("unchecked")
		public OrderByBorderTabelaHistorico(String id, Object sortProperty, ISortStateLocator stateLocator, WebMarkupContainer historicoContainer, DataView<HistoricoProduto> historicoView, AjaxFallbackOrderByLink.ICssProvider cssProvider){
			super(id, sortProperty, stateLocator, cssProvider);
			this.historicoContainer = historicoContainer;
			this.historicoView = historicoView;
		}

		@Override
		protected void onAjaxClick(AjaxRequestTarget target) {
			target.add(this.historicoContainer);
		}

		@Override
		protected void onSortChanged() {
			historicoView.setCurrentPage(0);
		}
	}

	private  String EmailsAprovadoNovosCadastro() {
		String emails="";
		try {
			List<User> users = executeRestAPI.findUserForRolesByNameRole("Aprovador Novos Cadastros");	
			for (User user : users) {
				if ((user.getPersonnal_data_email()!=null) &&  (user.getPersonnal_data_email()!=" ") &&  (user.getPersonnal_data_email()!="") ) {
					emails = emails + user.getPersonnal_data_email()+",";
				}
			}
		}catch (Exception e) {
		}
		return emails;
	}


	private  String EmailsPrecificao() {
		String emails = "";
		try {
			List<String> emailsFiscal = groupService.findEmails("Precificação");
			for (String dto : emailsFiscal) {
				if ((dto!=null) && (dto!=" ")  && (dto!="") ) {
					emails += dto + ", ";
				}
			}
		}catch (Exception e) {
		}
		return emails;
	}


	private  String EmailsLogistica() {
		String emails = "";
		try {
			List<String> emailsFiscal = groupService.findEmails("Logistica");
			for (String dto : emailsFiscal) {
				if ((dto!=null) && (dto!=" ")  && (dto!="") ) {
					emails += dto + ", ";
				}
			}
		}catch (Exception e) {
		}
		return emails;
	}

	private  String EmailsCdastro() {
		String emails = "";
		try {
			List<String> emailsFiscal = groupService.findEmails("Cadastro");
			for (String dto : emailsFiscal) {
				if ((dto!=null) && (dto!=" ")  && (dto!="") ) {
					emails += dto + ", ";
				}
			}
		}catch (Exception e) {
		}
		return emails;
	}

	private String EmailsFiscal() {
		String emails = "";
		try {
			List<String> emailsFiscal = groupService.findEmails("Fiscal");
			for (String dto : emailsFiscal) {
				if ((dto!=null) && (dto!=" ")  && (dto!="") ) {
					emails += dto + ", ";
				}
			}
		}catch (Exception e) {
		}
		return emails;
	}

}
