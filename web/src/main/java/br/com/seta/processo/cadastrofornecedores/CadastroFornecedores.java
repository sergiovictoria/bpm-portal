package br.com.seta.processo.cadastrofornecedores;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;

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
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.constant.VariaveisCadastroFornecedores;
import br.com.seta.processo.dto.AprovacaoGerenciaComercial;
import br.com.seta.processo.dto.CadastroFornecedor;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.HistoricoFornecedor;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.page.TaskPage;
import br.com.seta.processo.pagecomponentes.OrdemColunaTabelaCssProvider;
import br.com.seta.processo.provider.HistoricoFonecedorProvider;
import br.com.seta.processo.service.ExecuteRestAPI;

public class CadastroFornecedores extends TaskPage {

	private static final long serialVersionUID = 1L;	
	
	private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	@Inject	private ExecuteRestAPI executeRestAPI;	
	
	private final WebMarkupContainer cadastroFornecedoresMarkupContainer = new WebMarkupContainer("cadastroFornecedoresMarkupContainer");
	private transient User user = (User) Session.get().getAttribute("user");
	private CadastroFornecedor cadastroFornecedor; 
	private FormularioFornecedor formularioFornecedor;
    private HistoricoFonecedorProvider historicoFonecedorProvider;

	public CadastroFornecedores(final PageParameters pageParameters) throws HttpStatus401Exception, ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException {
		super(pageParameters);
		cadastroFornecedor = (CadastroFornecedor) executeRestAPI.retriveVariableTask(user, super.getTaskId(), CadastroFornecedor.class, VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR);
		formularioFornecedor = (FormularioFornecedor) executeRestAPI.retriveVariableTask(user, getTaskId(), FormularioFornecedor.class, VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR);
		Collections.sort(this.formularioFornecedor.getHistoricoFornecedores(), Collections.reverseOrder(HistoricoFornecedor.Comparators.DATA));
		historicoFonecedorProvider = new HistoricoFonecedorProvider(formularioFornecedor.getHistoricoFornecedores());
		add(new CadastroFornecedoresForm("CadastroFornecedoresForm",pageParameters));
	}

	public class CadastroFornecedoresForm extends Form<PageParameters> {

		private static final long serialVersionUID = 1L;
		private final DataView<HistoricoFornecedor> historicoView;
		private WebMarkupContainer historicoContainer;

		@SuppressWarnings({ "rawtypes" })
		public CadastroFornecedoresForm(String id, final PageParameters parameters) throws HttpStatus401Exception, ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException {
			super(id, new CompoundPropertyModel<PageParameters>(parameters));
			cadastroFornecedoresMarkupContainer.setOutputMarkupId(true);
			historicoContainer = (WebMarkupContainer) new WebMarkupContainer("historicoContainer").setOutputMarkupId(true);
						
			Label nomeRespPreench  = new Label("nomeRespPreench", cadastroFornecedor.getNomeRespPreench());
			Label emailSolicitante = new Label("emailSolicitante", cadastroFornecedor.getEmailSolicitante());
			Label telefoneSolicitante = new Label("telefoneSolicitante", cadastroFornecedor.getTelefoneSolicitante());
			Label area =  new Label("area", cadastroFornecedor.getArea());
			Label nomeSolicitante = new Label("nomeSolicitante", cadastroFornecedor.getNomeSolicitante());
			Label dataHoraSolicitacao = new Label("dataHoraSolicitacao", dateFormater.format(cadastroFornecedor.getDataSolicitacao()));
			TextArea<String> descricao =  new TextArea<String>("descricao", new PropertyModel<String>(cadastroFornecedor, "descricao"));
			SecaoAprovacao secaoAprovacao = new SecaoAprovacao("secaoAprovacao", formularioFornecedor.getAprovacaoGerenciaComercial());
			
			historicoView = new DataView<HistoricoFornecedor>("sortingHistorico", historicoFonecedorProvider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<HistoricoFornecedor> item) {
					HistoricoFornecedor historicoFornecedor = (HistoricoFornecedor) item.getModelObject();
					item.add(new Label("satus", historicoFornecedor.getStatus()));
					item.add(new Label("nome", historicoFornecedor.getNome()));
					item.add(new Label("data", dateFormater.format(historicoFornecedor.getData()) ));
					item.add(new Label("motivo", historicoFornecedor.getMotivo()));
					item.add(new Label("comentario", historicoFornecedor.getComentario()));
				}
			};			

			PagingNavigator navigator = new AjaxPagingNavigator("navigator", historicoView){
				
				private static final long serialVersionUID = 1L;

				@Override
				protected void onAjaxEvent(AjaxRequestTarget target) {
					target.add(historicoContainer);
				}
			};
			
			AjaxFallbackOrderByBorder DisplayMotivo = new OrderByBorderTabelaHistorico("DisplayMotivo", "DisplayMotivo", historicoFonecedorProvider, historicoContainer, historicoView, new OrdemColunaTabelaCssProvider());			
			AjaxFallbackOrderByBorder DisplayStatus = new OrderByBorderTabelaHistorico("DisplayStatus", "DisplayStatus", historicoFonecedorProvider, historicoContainer, historicoView, new OrdemColunaTabelaCssProvider());
			AjaxFallbackOrderByBorder DisplayNome = new OrderByBorderTabelaHistorico("DisplayNome", "DisplayNome", historicoFonecedorProvider, historicoContainer, historicoView, new OrdemColunaTabelaCssProvider()); 
			AjaxFallbackOrderByBorder displayData = new OrderByBorderTabelaHistorico("displayData", "displayData", historicoFonecedorProvider, historicoContainer, historicoView, new OrdemColunaTabelaCssProvider());
			
			Button exibirPlanilhaBtn =  new Button("exibirPlanilhaBtn"){			
				private static final long serialVersionUID = 1L;
				@Override
				public void onSubmit() {
					try {						
						setResponsePage(new VisualizarPlanilha(parameters, this.getPage().getPageReference(), formularioFornecedor));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			};

			historicoView.setItemsPerPage(10L);
			historicoContainer.add(historicoView, navigator, DisplayMotivo, DisplayStatus, DisplayNome, displayData);
			cadastroFornecedoresMarkupContainer.add(exibirPlanilhaBtn,nomeRespPreench,emailSolicitante,telefoneSolicitante,area,nomeSolicitante, dataHoraSolicitacao, descricao, secaoAprovacao,historicoContainer);
			add(cadastroFornecedoresMarkupContainer);
			//Caso não tenhamos items no histórico, o mesmo será omitido
			if(historicoView.getItemCount() == 0) historicoContainer.setVisible(false);
		}
	}
	
	/**
	 * Seção que contém os dados da aprovação da Gerência Comercial
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
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
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	public class OrderByBorderTabelaHistorico extends AjaxFallbackOrderByBorder{
		private static final long serialVersionUID = 1L;
		
		private WebMarkupContainer historicoContainer;
		private DataView<HistoricoFornecedor> historicoView;
		
		public OrderByBorderTabelaHistorico(String id, Object sortProperty, ISortStateLocator stateLocator,
				WebMarkupContainer historicoContainer, DataView<HistoricoFornecedor> historicoView) {
			super(id, sortProperty, stateLocator);
			this.historicoContainer = historicoContainer;
			this.historicoView = historicoView;
		}
		
		public OrderByBorderTabelaHistorico(String id, Object sortProperty, ISortStateLocator stateLocator, WebMarkupContainer historicoContainer, DataView<HistoricoFornecedor> historicoView, AjaxFallbackOrderByLink.ICssProvider cssProvider){
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
	
}
