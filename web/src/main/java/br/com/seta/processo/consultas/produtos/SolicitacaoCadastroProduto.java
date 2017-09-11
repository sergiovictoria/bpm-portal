package br.com.seta.processo.consultas.produtos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import javax.inject.Inject;

import org.apache.http.ParseException;
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
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.dao.interfaces.InstanciaCadastroProdutoDao;
import br.com.seta.processo.dto.AprovacaoGerenciaComercial;
import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.HistoricoProduto;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.pagecomponentes.OrdemColunaTabelaCssProvider;
import br.com.seta.processo.provider.HistoricoProdutoProvider;

public class SolicitacaoCadastroProduto extends BonitaPage {

	private static final long serialVersionUID = 1L;	
	
	private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");	
	
	private final WebMarkupContainer cadastroProdutosMarkupContainer = new WebMarkupContainer("cadastroProdutosMarkupContainer");
	private CadastroProduto cadastroProduto; 
	private FormularioProduto formularioProduto;
    private HistoricoProdutoProvider historicoProdutoProvider;
    
    @Inject
    private InstanciaCadastroProdutoDao dao;

    public SolicitacaoCadastroProduto(Long caseId){
    	this.cadastroProduto = dao.buscaCadastroProduto(caseId);
    	this.formularioProduto = dao.buscaFormularioProduto(caseId);
    	iniciaPagina();
    }
    
    public SolicitacaoCadastroProduto(CadastroProduto cadastro, FormularioProduto formulario){
    	this.cadastroProduto = cadastro;
    	this.formularioProduto = formulario;
    	iniciaPagina();
    }

	private void iniciaPagina() {
		try{
			setTituloPagina("Cadastro do Produto");
    		Collections.sort(this.formularioProduto.getHistoricoProdutos(), Collections.reverseOrder(HistoricoProduto.Comparators.DATA));
    		historicoProdutoProvider = new HistoricoProdutoProvider(formularioProduto.getHistoricoProdutos());
    		add(new CadastroProdutosForm("CadastroProdutosForm"));
    	}catch(Exception e){
    		throw new RuntimeException(e);
    	}
	}

	public class CadastroProdutosForm extends Form<Void> {

		private static final long serialVersionUID = 1L;
		
		private final DataView<HistoricoProduto> historicoView;
		private WebMarkupContainer historicoContainer;

		public CadastroProdutosForm(String id) throws HttpStatus401Exception, ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException {
			super(id);
			cadastroProdutosMarkupContainer.setOutputMarkupId(true);
			historicoContainer = (WebMarkupContainer) new WebMarkupContainer("historicoContainer").setOutputMarkupId(true);
						
			Label nomeRespPreench  = new Label("nomeRespPreench", cadastroProduto.getNomeRespPreench());
			Label emailSolicitante = new Label("emailSolicitante", cadastroProduto.getEmailSolicitante());
			Label telefoneSolicitante = new Label("telefoneSolicitante", cadastroProduto.getTelefoneSolicitante());
			Label area =  new Label("area", cadastroProduto.getArea());
			Label nomeSolicitante = new Label("nomeSolicitante", cadastroProduto.getNomeSolicitante());
			Label dataHoraSolicitacao = new Label("dataHoraSolicitacao", dateFormater.format(cadastroProduto.getDataSolicitacao()));
			TextArea<String> descricao =  new TextArea<String>("descricao", new PropertyModel<String>(cadastroProduto, "descricao"));
			SecaoAprovacao secaoAprovacao = new SecaoAprovacao("secaoAprovacao", formularioProduto.getAprovacaoGerenciaComercial());
			
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
						setResponsePage(new VisualizaDadosProduto(formularioProduto, cadastroProduto));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			};
			
			Button consultaCadProdutoBtn = new Button("consultaCadProdutoBtn"){
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onSubmit() {
					setResponsePage(ConsultaProdutos.class);
				}
				
			};

			historicoView.setItemsPerPage(10L);
			historicoContainer.add(historicoView, navigator, DisplayMotivo, DisplayStatus, DisplayNome, displayData);
			cadastroProdutosMarkupContainer.add(exibirPlanilhaBtn, consultaCadProdutoBtn, nomeRespPreench,emailSolicitante,telefoneSolicitante,area,nomeSolicitante, dataHoraSolicitacao, descricao, secaoAprovacao,historicoContainer);
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
}
