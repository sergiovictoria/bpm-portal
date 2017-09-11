package br.com.seta.processo.consultas.produtos;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;

import br.com.seta.processo.bean.dao.FiltroCadastroProdutos;
import br.com.seta.processo.consultas.DadosCadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.pagecomponentes.OrderCssProvider;
import br.com.seta.processo.provider.ConsultaCadastroProdutoProvider;
import br.com.seta.processo.utils.DateUtils;
import static br.com.seta.processo.comparators.DadosCadProdutoComparators.*;


public class ConsultaProdutos extends BonitaPage {
	
	
	private static final long serialVersionUID = 1L;
	
	private static final int ITENS_POR_PAGINA = 15;
	
	private Form<Void> cadProdutosForm;
	private ModalFiltro modalFiltro;
	private CadastroProdutosDataView cadProdutosDataView;
	private AjaxPagingNavigator navigator;
	private ConsultaCadastroProdutoProvider provider;
	private WebMarkupContainer dadosProdutoTabela;
	
	public ConsultaProdutos(){
		this.setTituloPagina("Consulta Cadastro de Produto");
		
		this.cadProdutosForm = new Form<Void>("cadProdutosForm");
		this.modalFiltro = new ModalFiltro("modalFiltro");
		this.provider = new ConsultaCadastroProdutoProvider();
		this.cadProdutosDataView = new CadastroProdutosDataView("cadProdutosDataView", this.provider, ITENS_POR_PAGINA);
		this.navigator = new AjaxPagingNavigator("navigator", this.cadProdutosDataView);
		
		dadosProdutoTabela = (WebMarkupContainer) new WebMarkupContainer("dadosProdutoTabela").setOutputMarkupId(true);
		dadosProdutoTabela.add(cadProdutosDataView);
		
		this.cadProdutosForm.add(dadosProdutoTabela, navigator);
		add(cadProdutosForm, modalFiltro);
		
		OrderByBorderDadosCadProduto caseIdOrder = new OrderByBorderDadosCadProduto("caseIdOrder", POR_CASE_ID);
		OrderByBorderDadosCadProduto dataOrder = new OrderByBorderDadosCadProduto("dataOrder", POR_DATA);
		OrderByBorderDadosCadProduto idFornecedorOrder = new OrderByBorderDadosCadProduto("idFornecedorOrder", POR_ID_FORNECEDOR);
		OrderByBorderDadosCadProduto eanOrder = new OrderByBorderDadosCadProduto("eanOrder", POR_EAN_UNIDADE);
		OrderByBorderDadosCadProduto idProdutoOrder = new OrderByBorderDadosCadProduto("idProdutoOrder", POR_ID_PRODUTO);
		OrderByBorderDadosCadProduto produtoDescOrder = new OrderByBorderDadosCadProduto("produtoDescOrder", POR_DESC_PRODUTO);
		OrderByBorderDadosCadProduto codNcmOrder = new OrderByBorderDadosCadProduto("codNcmOrder", POR_COD_NCM);
		OrderByBorderDadosCadProduto descNcmOrder = new OrderByBorderDadosCadProduto("descNcmOrder", POR_DESC_NCM);
		OrderByBorderDadosCadProduto statusOrder = new OrderByBorderDadosCadProduto("statusOrder", POR_STATUS);
		
		this.dadosProdutoTabela.add(caseIdOrder, dataOrder, idFornecedorOrder, eanOrder, idProdutoOrder, produtoDescOrder, codNcmOrder, descNcmOrder, statusOrder);
	}
	
	/**
	 * Tabela que contém os dados dos Cadastros de Produto
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class CadastroProdutosDataView extends DataView<DadosCadastroProduto>{
		private static final long serialVersionUID = 1L;

		protected CadastroProdutosDataView(String id, IDataProvider<DadosCadastroProduto> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<DadosCadastroProduto> item) {
			final DadosCadastroProduto dados = (DadosCadastroProduto) item.getDefaultModelObject();
			FormularioProduto fp = dados.getFormularioProduto();
			
			Label caseIdLbl = new Label("caseIdLbl", dados.getCaseId());
			Label dataLbl = new Label("dataLbl", dados.getInicio());
			Label idFornecLbl = new Label("idFornecLbl", fp.getSeqfornecedor());
			Label eanUnidLbl = new Label("eanUnidLbl", fp.getEanUnidade());
			Label idProdutoLbl = new Label("idProdutoLbl", fp.getCodProduto());
			Label descrProdutoLbl = new Label("descrProdutoLbl", fp.getDescCompleta());
			Label codNcmLbl = new Label("codNcmLbl", fp.getCnm());
			Label descNcmLbl = new Label("descNcmLbl", fp.getDescricaoNcm());
			Label statusLbl = new Label("statusLbl", dados.getStatus());
			
			AjaxButton detalhesBtn = new AjaxButton("detalhesBtn"){
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					setResponsePage(new SolicitacaoCadastroProduto(dados.getCaseId()));
				}
			};
			
			item.add(caseIdLbl, dataLbl, idFornecLbl, eanUnidLbl, idProdutoLbl, descrProdutoLbl, codNcmLbl, descNcmLbl, statusLbl, detalhesBtn);
		}
		
	}
	
	/**
	 * Modal de Filtro contendo os critérios de busca dos Cadastro de Produto finalizados
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class ModalFiltro extends Form<FiltroCadastroProdutos>{

		private static final long serialVersionUID = 1L;
		
		private TextField<String> caseIdTF, codFornecConsincoTF, codProdConsincoTF, descricaoTF, ncmTF, eanTF;
		private DateTextField inicioDTF, fimDTF;
		private DropDownChoice<String> statusCb;
		private AjaxButton pesquisarBtn;
		private FiltroCadastroProdutos filtroProdutos;
		
		public ModalFiltro(String id) {
			super(id);
			this.filtroProdutos = new FiltroCadastroProdutos();
			setModel(new CompoundPropertyModel<FiltroCadastroProdutos>(this.filtroProdutos));
			
			this.caseIdTF = new TextField<String>("caseId");
			this.codFornecConsincoTF = new TextField<String>("codFornecedor");
			this.codProdConsincoTF = new TextField<String>("codProdutoC5");
			this.descricaoTF = new TextField<String>("descricaoProduto"); 
			this.ncmTF = new TextField<String>("ncm");
			this.eanTF = new TextField<String>("eanUnidade");
			this.inicioDTF = new DateTextField("inicio"); 
			this.fimDTF = new DateTextField("fim");
			this.statusCb = new DropDownChoice<String>("status", StatusProcessoCadastroProduto.listaStatus());		
			
			this.pesquisarBtn = new AjaxButton("pesquisarBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					Date inicio = filtroProdutos.getInicio();
					Date fim = filtroProdutos.getFim();
					if(inicio != null)
						filtroProdutos.setInicio(DateUtils.inicioDoDiaDe(inicio));
					if(fim != null)
						filtroProdutos.setFim(DateUtils.fimDoDiaDe(fim));
					
					provider.setFiltro(filtroProdutos);
					target.add(cadProdutosForm);
					ocultaCarregamento(target);
				}
			};
			
			add(caseIdTF, codFornecConsincoTF, codProdConsincoTF, descricaoTF, ncmTF, eanTF, inicioDTF, fimDTF, statusCb, pesquisarBtn);
		}
		
	}
	
	/**
	 * Ordenador de colunas da tabela de cadastro de produtos
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	@SuppressWarnings("rawtypes")
	private class OrderByBorderDadosCadProduto extends AjaxFallbackOrderByBorder {

		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unchecked")
		public OrderByBorderDadosCadProduto(String id, Object sortProperty) {
			super(id, sortProperty, provider, new OrderCssProvider<DadosCadastroProduto>());
		}

		@Override
		protected void onAjaxClick(AjaxRequestTarget target) {
			target.add(dadosProdutoTabela);
		}

		@Override
		protected void onSortChanged() {
		}

	}	
}
