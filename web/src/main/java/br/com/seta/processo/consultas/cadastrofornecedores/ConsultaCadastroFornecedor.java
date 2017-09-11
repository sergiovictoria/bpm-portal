package br.com.seta.processo.consultas.cadastrofornecedores;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.consultas.DadosCadFornecedor;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.pagecomponentes.OrdemColunaTabelaCssProvider;
import br.com.seta.processo.provider.RelatoriosCadastroDeFornecedorProvider;
import br.com.seta.processo.utils.DateUtils;
import static br.com.seta.processo.comparators.DadosCadFornecedorComparators.*;

public class ConsultaCadastroFornecedor extends BonitaPage {

	private static final long QUANT_ITENS_DATAVIEW = 15L;

	private static final long serialVersionUID = 1L;
	
	private Form<Void> formFiltro, formFornecedores;
	private WebMarkupContainer tabelaFornecedorContainer;
	private ModalFiltro modal;
	private DadosFornecedor dadosFornecedor;
	private RelatoriosCadastroDeFornecedorProvider provider = new RelatoriosCadastroDeFornecedorProvider();
	private AjaxPagingNavigator navigator;
	private FiltroFormulario filtros;
	
	public ConsultaCadastroFornecedor(PageParameters pageParameters)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		
		super(pageParameters);
		this.setTituloPagina("Consulta Cadastro de Fornecedor");
		
		this.filtros = new FiltroFormulario();
		this.modal = new ModalFiltro("modalFiltro", filtros);
		this.formFiltro = new Form<Void>("formFiltro");
		this.formFiltro.add(this.modal);
		
		this.tabelaFornecedorContainer = (WebMarkupContainer) new WebMarkupContainer("tabelaFornecedorContainer").setOutputMarkupId(true);
		this.dadosFornecedor = new DadosFornecedor("dadosFornecedor", this.provider, QUANT_ITENS_DATAVIEW);
		this.navigator = new AjaxPagingNavigator("navigator", this.dadosFornecedor);
		this.formFornecedores = new Form<Void>("formFornecedores");
		this.tabelaFornecedorContainer.add(this.dadosFornecedor, this.navigator);
		this.formFornecedores.add(this.tabelaFornecedorContainer);
		
		add(formFiltro, formFornecedores);
		
		OrderByBorderFornecedores caseIdOrder = new OrderByBorderFornecedores("caseIdOrder", POR_CASE_ID, provider);
		OrderByBorderFornecedores codConsincoOrder = new OrderByBorderFornecedores("codConsincoOrder", POR_COD_CONSINCO, provider);
		OrderByBorderFornecedores dataOrder = new OrderByBorderFornecedores("dataOrder", POR_DATA, provider);
		OrderByBorderFornecedores razaoSocialOrder = new OrderByBorderFornecedores("razaoSocialOrder", POR_RAZAO_SOCIAL, provider);
		OrderByBorderFornecedores nomeOrder = new OrderByBorderFornecedores("nomeOrder", POR_NOME, provider);
		OrderByBorderFornecedores cnpjOrder = new OrderByBorderFornecedores("cnpjOrder", POR_CNPJ, provider);
		OrderByBorderFornecedores cpfOrder = new OrderByBorderFornecedores("cpfOrder", POR_CPF, provider);
		OrderByBorderFornecedores compradorOrder = new OrderByBorderFornecedores("compradorOrder", POR_COMPRADOR, provider);
		OrderByBorderFornecedores statusOrder = new OrderByBorderFornecedores("statusOrder", POR_STATUS, provider);
		
		this.tabelaFornecedorContainer.add(caseIdOrder, codConsincoOrder, dataOrder, razaoSocialOrder, 
				nomeOrder, cnpjOrder, cpfOrder, compradorOrder, statusOrder);
	}
	
	private class ModalFiltro extends WebMarkupContainer{

		private static final long serialVersionUID = 1L;
		
		private TextField<Long> caseIdTF;
		private TextField<String> codConsincoTF, razaoSocialOuNomeTF, cpfCnpjTF, compradorTF;
		private DateTextField inicioDTF, fimDTF;
		private DropDownChoice<String> status;
		private AjaxButton pesquisarBtn;
		
		public ModalFiltro(String id, final FiltroFormulario ff) {
			super(id, new CompoundPropertyModel<FiltroFormulario>(ff));
			
			caseIdTF = new TextField<Long>("caseId");
			codConsincoTF = new TextField<String>("codConsinco");
			razaoSocialOuNomeTF = new TextField<String>("razaoSocialOuNome");
			cpfCnpjTF = new TextField<String>("cpfCnpj");
			compradorTF = new TextField<String>("comprador");
			inicioDTF = new DateTextField("inicio");
			fimDTF = new DateTextField("fim");
			status = new DropDownChoice<String>("status", StatusProcessoCadastroFornecedor.listaStatus());
			
			pesquisarBtn = new AjaxButton("pesquisarBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					Date inicio = ff.getInicio();
					Date fim = ff.getFim();
					
					if(inicio != null){
						inicio = DateUtils.inicioDoDiaDe(ff.getInicio());
					}
					if(fim != null){
						fim = DateUtils.fimDoDiaDe(ff.getFim());
					}					
					
					provider.setFiltros(ff.getCaseId(), 
							ff.getCodConsinco(), 
							ff.getRazaoSocialOuNome(), 
							ff.getCpfCnpj(), 
							ff.getComprador(), 
							inicio, 
							fim, 
							ff.getStatus());
					
					target.add(formFornecedores);
					ocultaCarregamento(target);
				}
			};
			
			add(caseIdTF, codConsincoTF, razaoSocialOuNomeTF, cpfCnpjTF, compradorTF, inicioDTF, fimDTF, status, pesquisarBtn);

		}
	}
	
	private class DadosFornecedor extends DataView<DadosCadFornecedor>{

		private static final long serialVersionUID = 1L;

		protected DadosFornecedor(String id, IDataProvider<DadosCadFornecedor> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<DadosCadFornecedor> item) {
			final DadosCadFornecedor dadosFornecedor = (DadosCadFornecedor) item.getDefaultModelObject();
			FormularioFornecedor ff = dadosFornecedor.getFormularioFornecedor();
			
			Label caseID = new Label("caseIdTd", dadosFornecedor.getCaseId().toString());
			Label codConsinco = new Label("codConsincoTd", ff.getIdFornecedorC5());
			Label data = new Label("dataTd", dadosFornecedor.getInicio());
			Label razaoSocial = new Label("razaoSocialTd", ff.getRazaoSocialFornedor());
			Label nome = new Label("nomeTd", ff.getNome());
			Label cnpj = new Label("cnpjTd", ff.getCpnjJuridico());
			Label cpf = new Label("cpfTd", ff.getCpfFisica());
			Label comprador = new Label("compradorTd", ff.getComprador());
			Label status = new Label("statusTd", dadosFornecedor.getStatus());
			
			AjaxButton detalhesBtn = new AjaxButton("detalhesBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					setResponsePage(new CadastroFornecedores(dadosFornecedor.getCaseId()));
				}
			};
			
			item.add(caseID, codConsinco, data, razaoSocial, nome, cpf, cnpj, comprador, status, detalhesBtn);
		}		
	}	
	
	/**
	 * Ordenador de colunas da tabela de fornecedores
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	@SuppressWarnings("rawtypes")
	private class OrderByBorderFornecedores extends AjaxFallbackOrderByBorder {

		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unchecked")
		public OrderByBorderFornecedores(String id, Object sortProperty, ISortStateLocator stateLocator) {
			super(id, sortProperty, stateLocator, new OrdemColunaTabelaCssProvider());
		}

		@Override
		protected void onAjaxClick(AjaxRequestTarget target) {
			target.add(tabelaFornecedorContainer);
		}

		@Override
		protected void onSortChanged() {
		}

	}	
	
	/**
	 * Modelo para os dados contindos no Modal de Filtro
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	@SuppressWarnings("unused")
	private class FiltroFormulario implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		private Long caseId;
		private String codConsinco, razaoSocialOuNome, cpfCnpj, comprador;
		private Date inicio, fim;
		private String status;
		
		public Long getCaseId() {
			return caseId;
		}		
		public void setCaseId(Long caseId) {
			this.caseId = caseId;
		}
		public String getCodConsinco() {
			return codConsinco;
		}
		public void setCodConsinco(String codConsinco) {
			this.codConsinco = codConsinco;
		}
		public String getRazaoSocialOuNome() {
			return razaoSocialOuNome;
		}
		public void setRazaoSocialOuNome(String razaoSocialOuNome) {
			this.razaoSocialOuNome = razaoSocialOuNome;
		}
		public String getCpfCnpj() {
			return cpfCnpj;
		}
		public void setCpfCnpj(String cpfCnpj) {
			this.cpfCnpj = cpfCnpj;
		}
		public String getComprador() {
			return comprador;
		}
		public void setComprador(String comprador) {
			this.comprador = comprador;
		}
		public Date getInicio() {
			return inicio;
		}
		public void setInicio(Date inicio) {
			this.inicio = inicio;
		}
		public Date getFim() {
			return fim;
		}
		public void setFim(Date fim) {
			this.fim = fim;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	}
	
}
