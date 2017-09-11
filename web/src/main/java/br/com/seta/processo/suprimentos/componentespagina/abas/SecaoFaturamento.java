package br.com.seta.processo.suprimentos.componentespagina.abas;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashSet;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.constant.ConstantesSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.Empresa;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.OrRequisicaovencto;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.helper.DocumentoHelper;
import br.com.seta.processo.pagecomponentes.Anexos;
import br.com.seta.processo.provider.AnexosProvider;
import br.com.seta.processo.provider.EmpresaProvider;
import br.com.seta.processo.provider.GePessoaFornecedorProvider;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;
import br.com.seta.processo.utils.PropertiesEJBUtils;
import static br.com.seta.processo.constant.ConstantesSolicitacaoIntencaoCompra.FORMULARIO_ORCAMENTO;
import static br.com.seta.processo.constant.ConstantesSolicitacaoIntencaoCompra.FORMULARIO_CADASTRO_FORNECEDOR;

public class SecaoFaturamento extends Panel { 
	private static final long serialVersionUID = 1L;
	
	private Documento orcamento;
	private Documento formularioCadFornecedor;
	private OrRequisicao requisicao = new OrRequisicao();
	private static final String PATH_CADASTRO_FORNECEDOR_XLS  = PropertiesEJBUtils.getInstance().getValues("fornecedor_file_xls");

	
	private Secao secaoFaturamento;
	private ModalBuscaFornecedor modalBuscaFornecedor;
	private ModalBuscaEmpresa modalBuscaEmpresa;
	private AnexarOrcamentoDialog anexarOrcamentoDialog;
	private AnexarCadastroFornecedorDialog anexarCadastroFornecedorDialog;
	
	public SecaoFaturamento(String id, OrRequisicao requisicao, FormularioIntencaoCompraTemplate parentPage){
		super(id);
		this.requisicao = requisicao;
		
		secaoFaturamento = (Secao) new Secao("secaoFaturamento", requisicao).setOutputMarkupId(true);
		modalBuscaFornecedor = (ModalBuscaFornecedor) new ModalBuscaFornecedor("modalBuscaFornecedor").setOutputMarkupId(true);
		modalBuscaEmpresa = (ModalBuscaEmpresa) new ModalBuscaEmpresa("modalBuscaEmpresa").setOutputMarkupId(true);
		anexarOrcamentoDialog = new AnexarOrcamentoDialog("anexarOrcamentoDialog");
		anexarCadastroFornecedorDialog = new AnexarCadastroFornecedorDialog("anexarCadastroFornecedorDialog");
		
		AnexosProvider orcamentoProvider = new AnexosProvider(parentPage.getCaseId(), ConstantesSolicitacaoIntencaoCompra.FORMULARIO_ORCAMENTO);
		Anexos secaoAnexoOrcamento = new Anexos("secaoAnexoOrcamento", orcamentoProvider);	
		if(orcamentoProvider.size() == 0) 
			secaoAnexoOrcamento.setVisible(false);
		
		AnexosProvider cadastroFornecedorprovider = new AnexosProvider(parentPage.getCaseId(), ConstantesSolicitacaoIntencaoCompra.FORMULARIO_CADASTRO_FORNECEDOR);
		Anexos secaoAnexoCadastroFornecedor = new Anexos("secaoAnexoCadastroFornecedor", cadastroFornecedorprovider);
		if(cadastroFornecedorprovider.size() == 0){
			secaoAnexoCadastroFornecedor.setVisible(false);
		}
		
		add(secaoFaturamento, modalBuscaFornecedor, modalBuscaEmpresa, anexarOrcamentoDialog, anexarCadastroFornecedorDialog, secaoAnexoOrcamento, secaoAnexoCadastroFornecedor);
	}
	
	public void addSecaoAnexos(Panel secaoAnexos){
		addOrReplace(secaoAnexos);
	}
	
	private class Secao extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private TextField<Long> nroIntencao;
		private TextField<String> tipoDespesa;
		private DateTextField dataHora;
		private TextField<String> fornecedor, nomeEmpresa;
		private Button abrirModalAnexoOrcamentoBtn, abrirModalAnexoCadFornecedorBtn, abrirModalFornecedoresBtn;
		private DownloadLink cadastroFornecedorDownload;
		
		@SuppressWarnings("unchecked")
		public Secao(String id, OrRequisicao requisicao) {
			super(id, new CompoundPropertyModel<OrRequisicao>(requisicao));
			
			nroIntencao = (TextField<Long>) new TextField<Long>("numeroIntencaoSolicitacaoCompra").setEnabled(false);
			tipoDespesa = (TextField<String>) new TextField<String>("tipoDespesa").setEnabled(false);
			dataHora = (DateTextField) new DateTextField("dtainclusao", "dd/MM/yyyy HH:mm").setEnabled(false);
			fornecedor = (TextField<String>) new TextField<String>("nomeFornecedor").setEnabled(false);
			nomeEmpresa = (TextField<String>) new TextField<String>("nomeEmpresa").setEnabled(false);
			abrirModalAnexoOrcamentoBtn = new Button("abrirModalAnexoOrcamentoBtn");
			abrirModalAnexoCadFornecedorBtn = new Button("abrirModalAnexoCadFornecedorBtn");
			abrirModalFornecedoresBtn = new Button("abrirModalFornecedoresBtn");
			
			File cadastroFornecedorFile = new File(PATH_CADASTRO_FORNECEDOR_XLS);
			cadastroFornecedorDownload = new DownloadLink("cadastroFornecedorDownload", cadastroFornecedorFile);
			
			add(nroIntencao, tipoDespesa, dataHora, fornecedor, nomeEmpresa, abrirModalAnexoOrcamentoBtn, abrirModalFornecedoresBtn, abrirModalAnexoCadFornecedorBtn, cadastroFornecedorDownload);
		}
		
		public Button getAbrirModalAnexoOrcamentoBtn(){
			return abrirModalAnexoOrcamentoBtn;
		}
		
		public Button getAbrirModalFornecedoresBtn(){
			return abrirModalFornecedoresBtn;
		}
		
		public DownloadLink getCadastroFornecedorDownload(){
			return this.cadastroFornecedorDownload;
		}
		
		public Button getAbrirModalAnexoCadFornecedorBtn(){
			return this.abrirModalAnexoCadFornecedorBtn;
		}
	}
	
	public void habilitaAbrirModalFornecedoresBtn(boolean habilitado){
		secaoFaturamento.getAbrirModalFornecedoresBtn().setEnabled(habilitado);
	}
	
	public void habilitaAbrirModalAnexoOrcamentoBtn(boolean habilitado){
		secaoFaturamento.getAbrirModalAnexoOrcamentoBtn().setEnabled(habilitado);
	}
	
	public void habilitaAbrirModalAnexoCadFornecedorBtn(boolean habilitado){
		secaoFaturamento.getAbrirModalAnexoCadFornecedorBtn().setEnabled(habilitado);
	}
	
	public void habilitaDownloadFormularioCadFornecedor(boolean habilitado){
		secaoFaturamento.getCadastroFornecedorDownload().setEnabled(habilitado);
	}
	
	private class ModalBuscaFornecedor extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private static final int QUANT_ITENS_POR_PAGINA = 10;
		
		private TextField<String> fornecedorNCadastradoInput;
		private AjaxButton fornecedorNCadastradoBtn;
		
		private TextField<String> filtroFornecedor;
		private AjaxButton buscaFornecedorBtn;
		private WebMarkupContainer tabelaFornecedores;
		private FornecedoresDataView fornecedoresDataView;
		private GePessoaFornecedorProvider fornecedorProvider = new GePessoaFornecedorProvider();
		private AjaxPagingNavigator navigator;
		private WebMarkupContainer msgFonecedoresNaoEncontrados;		
		
		@SuppressWarnings("unchecked")
		public ModalBuscaFornecedor(String id) {
			super(id);
			
			fornecedoresDataView = new FornecedoresDataView("fornecedores", fornecedorProvider, QUANT_ITENS_POR_PAGINA);
			filtroFornecedor = new TextField<String>("filtroFornecedor", Model.of(""));
			buscaFornecedorBtn = new AjaxButton("buscaFornecedorBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					String filtro = filtroFornecedor.getDefaultModelObjectAsString();
					fornecedorProvider.putParameter(filtro);
					visibilidadeTabelaFornecedores();
					target.add(ModalBuscaFornecedor.this);					
				}
			};
			
			fornecedorNCadastradoInput = (TextField<String>) new TextField<String>("fornecedorNCadastradoInput", Model.of("")).setOutputMarkupId(true);
			fornecedorNCadastradoBtn = new AjaxButton("fornecedorNCadastradoBtn"){
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					String nomeFornecedor = fornecedorNCadastradoInput.getDefaultModelObjectAsString();
					if(!nomeFornecedor.trim().isEmpty()){
						reiniciaPorMudancaDeFornecedor();
						requisicao.setSeqpessoa(0);
						requisicao.setNomeFornecedor(nomeFornecedor);
						fornecedorNCadastradoInput.setDefaultModelObject("");
						target.add(secaoFaturamento, fornecedorNCadastradoInput);
					}						
					target.appendJavaScript("$('#consultarFornecedorDialog').modal('hide')"); 
				}

			};
			
			navigator = new AjaxPagingNavigator("navigator", fornecedoresDataView);
			msgFonecedoresNaoEncontrados = new WebMarkupContainer("msgFonecedoresNaoEncontrados");
			tabelaFornecedores = (WebMarkupContainer) new WebMarkupContainer("tabelaFornecedores").setOutputMarkupId(true);
			tabelaFornecedores.add(fornecedoresDataView, navigator);			
			
			add(filtroFornecedor, buscaFornecedorBtn, tabelaFornecedores, msgFonecedoresNaoEncontrados, fornecedorNCadastradoInput, fornecedorNCadastradoBtn);
			
			visibilidadeTabelaFornecedores();
		}		
		
		private void visibilidadeTabelaFornecedores() {
			if(fornecedorProvider.isEmpty()){
				tabelaFornecedores.setVisible(false);
				msgFonecedoresNaoEncontrados.setVisible(true);
			}else{
				tabelaFornecedores.setVisible(true);
				msgFonecedoresNaoEncontrados.setVisible(false);
				if(fornecedorProvider.size() <= QUANT_ITENS_POR_PAGINA){
					navigator.setVisible(false);
				}else{
					navigator.setVisible(true);
				}
			}
		}
		
	}
	
	/**
	 * Modal para busca de empresas
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class ModalBuscaEmpresa extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		private static final int QUANT_ITENS_POR_PAGINA = 10;		
		
		private TextField<String> filtroEmpresa;
		private AjaxButton buscaEmpresaBtn;
		private WebMarkupContainer tabelaEmpresas;
		private EmpresasDataView empresasDataView;
		private EmpresaProvider empresaProvider = new EmpresaProvider();
		private AjaxPagingNavigator navigatorEmpresa;
		private WebMarkupContainer msgEmpresasNaoEncontradas;

		public ModalBuscaEmpresa(String id) {
			super(id);
			
			filtroEmpresa = new TextField<String>("filtroEmpresa", Model.of(""));
			buscaEmpresaBtn = new AjaxButton("buscaEmpresaBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {					
					String filtro = filtroEmpresa.getDefaultModelObjectAsString();
					empresaProvider.putParameter(filtro);
					visibilidadeTabelaFornecedores();
					target.add(ModalBuscaEmpresa.this);
				}
			};			
			
			empresasDataView = new EmpresasDataView("empresas", empresaProvider, QUANT_ITENS_POR_PAGINA);
			navigatorEmpresa = new AjaxPagingNavigator("navigatorEmpresa", empresasDataView);
			tabelaEmpresas = (WebMarkupContainer) new WebMarkupContainer("tabelaEmpresas").setOutputMarkupId(true);
			tabelaEmpresas.add(empresasDataView, navigatorEmpresa);			
			msgEmpresasNaoEncontradas = new WebMarkupContainer("msgEmpresaNaoEncontrado");
			
			add(filtroEmpresa, buscaEmpresaBtn, tabelaEmpresas, msgEmpresasNaoEncontradas);
			
			visibilidadeTabelaFornecedores();
		}
		
		private void visibilidadeTabelaFornecedores() {
			if(empresaProvider.size() == 0){
				tabelaEmpresas.setVisible(false);
				msgEmpresasNaoEncontradas.setVisible(true);
			}else{
				tabelaEmpresas.setVisible(true);
				msgEmpresasNaoEncontradas.setVisible(false);
				if(empresaProvider.size() <= QUANT_ITENS_POR_PAGINA){
					navigatorEmpresa.setVisible(false);
				}else{
					navigatorEmpresa.setVisible(true);
				}
			}
		}

	}
	
	private class FornecedoresDataView extends DataView<GePessoaEntity>{
		private static final long serialVersionUID = 1L;

		protected FornecedoresDataView(String id, IDataProvider<GePessoaEntity> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<GePessoaEntity> item) {
			final GePessoaEntity fornecedor = (GePessoaEntity) item.getModelObject();

			String codFornecedor = fornecedor.getSeqpessoa().toString();
			item.add(new Label("lblCodigoFornec", codFornecedor));
			item.add(new Label("lblDescricaoFornec", fornecedor.getNomerazao()));
			String cpfCnpj =  fornecedor.getNrocgccpf().toString() + fornecedor.getDigcgccpf();
			item.add(new Label("lblCnpjCgc", cpfCnpj));
			
			AjaxEventBehavior onClickEvent = new AjaxEventBehavior("click") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {
					reiniciaPorMudancaDeFornecedor();
					requisicao.setNomeFornecedor(fornecedor.getNomerazao());
					requisicao.setSeqpessoa(fornecedor.getSeqpessoa().intValue());
					target.add(secaoFaturamento);
					target.appendJavaScript("$('#consultarFornecedorDialog').modal('hide')");
				}
			};					

			item.add(onClickEvent);
		}		
	}
	
	private class EmpresasDataView extends DataView<Empresa> {
		private static final long serialVersionUID = 1L;

		protected EmpresasDataView(String id, IDataProvider<Empresa> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<Empresa> item) {
			final Empresa empresa = (Empresa) item.getDefaultModelObject();
			String cnpj = empresa.getNrocgc().toString() + empresa.getDigcgc();

			Label razaosocial = new Label("razaosocial", new PropertyModel<String>(empresa, "fantazia"));
			Label nrocgc = new Label("nrocgc", cnpj);
			
			AjaxEventBehavior onClickEvent = new AjaxEventBehavior("click") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {					
					reiniciaPorMudancaEmpresa();
					short nroempresa = empresa.getNroempresa().shortValue();
					String nomeEmpresa = empresa.getFantazia();
					requisicao.setNroempresa(nroempresa);
					requisicao.setNomeEmpresa(nomeEmpresa);
					target.add(secaoFaturamento);
					target.appendJavaScript("$('#consultarEmpresaDialog').modal('hide')");
				}
			};
			
			item.add(nrocgc, razaosocial);
			item.add(onClickEvent);
		}

	}
	
	private class AnexarOrcamentoDialog extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private FileUploadField anexoOrcamento;
		private AjaxButton anexoBtn;
		
		public AnexarOrcamentoDialog(String id) {
			super(id);
			
			anexoOrcamento = new FileUploadField("anexoOrcamento");
			anexoBtn = new AjaxButton("anexoBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					FileUpload fileUpload = anexoOrcamento.getFileUpload();
					
					if(fileUpload != null){
						setOrcamento(DocumentoHelper.criaDocumento(fileUpload, FORMULARIO_ORCAMENTO));
					}
					
					target.appendJavaScript("$('#anexarOrcamentoDialog').modal('hide');");
				}
			};			
			add(anexoOrcamento, anexoBtn);
			
		}		
	}
	
	private class AnexarCadastroFornecedorDialog extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private FileUploadField anexoCadastroFornecedor;
		private AjaxButton anexoCadFornecedorBtn;
		
		public AnexarCadastroFornecedorDialog(String id) {
			super(id);
			
			anexoCadastroFornecedor = new FileUploadField("anexoCadastroFornecedor");
			anexoCadFornecedorBtn = new AjaxButton("anexoCadFornecedorBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					FileUpload fileUpload = anexoCadastroFornecedor.getFileUpload();
					
					if(fileUpload != null){
						setFormularioCadFornecedor(DocumentoHelper.criaDocumento(fileUpload, FORMULARIO_CADASTRO_FORNECEDOR));
					}
					
					target.appendJavaScript("$('#anexarCadastroFornecedorDialog').modal('hide');");
				}
			};			
			add(anexoCadastroFornecedor, anexoCadFornecedorBtn);
			
		}		
	}
	
	/**
	 * Utilizado para remover os itens selecionados e os rateios de centro de custo para os casos onde houve mudança de Empresa e/ou Fornecedor
	 */
	private void reiniciaPorMudancaDeFornecedor(){
		requisicao.setOrReqitensdespesas(new HashSet<OrReqitensdespesa>());
		requisicao.setOrReqplanilhalanctos(new HashSet<OrReqplanilhalancto>());
		requisicao.setOrRequisicaovenctos(new HashSet<OrRequisicaovencto>());
		
		requisicao.setValor(BigDecimal.ZERO);
		requisicao.setVlracrescimos(BigDecimal.ZERO);
		requisicao.setVlrdescontos(BigDecimal.ZERO);
		requisicao.setVlrliqreq(BigDecimal.ZERO);
		requisicao.setVlroutrasopdesc(BigDecimal.ZERO);
		requisicao.setCodhistorico((short)0);
		requisicao.setNaturezaDespesa("");
	}
	
	private void reiniciaPorMudancaEmpresa(){		
		requisicao.setOrReqplanilhalanctos(new HashSet<OrReqplanilhalancto>());		
		requisicao.setCodhistorico((short)0);
		requisicao.setNaturezaDespesa("");
	}

	public Documento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Documento orcamento) {
		this.orcamento = orcamento;
	}

	public Documento getFormularioCadFornecedor() {
		return formularioCadFornecedor;
	}

	public void setFormularioCadFornecedor(Documento formularioCadFornecedor) {
		this.formularioCadFornecedor = formularioCadFornecedor;
	}	

}
