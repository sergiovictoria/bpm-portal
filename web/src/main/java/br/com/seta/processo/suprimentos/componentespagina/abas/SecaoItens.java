package br.com.seta.processo.suprimentos.componentespagina.abas;

import static br.com.seta.processo.constant.ConstantesSolicitacaoIntencaoCompra.FORMULARIO_CADASTRO_NOVOS_ITENS;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.CalculoRequisicao;
import br.com.seta.processo.constant.ConstantesSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.entity.OrvProdutotribEntity;
import br.com.seta.processo.helper.DocumentoHelper;
import br.com.seta.processo.model.DefaultMoneyModel;
import br.com.seta.processo.model.DefaultZeroBigDecimalModel;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.pagecomponentes.Anexos;
import br.com.seta.processo.provider.AnexosProvider;
import br.com.seta.processo.provider.ProdutoProvider;
import br.com.seta.processo.utils.PropertiesEJBUtils;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class SecaoItens extends Panel {
	private static final long serialVersionUID = 1L;

	@Inject
	private CalculoRequisicao calculoRequisicao;
	
	private Documento formularioCadastroItens;
	private OrRequisicao requisicao;	
	
	private WebMarkupContainer tabelaItensSelecionados;
	private ItensSelecionadosDataView itensSelecionadosDV;
	private ItensSelecionadosProvider itensSelecionadosProvider;
	private BuscaItensModal buscaItensModal;
	private SecaoTotais secaoTotais;
	private SecaoAnexoCadastroProduto secaoAnexoCadastroProduto;
	private Anexos secaoAnexos;
	private Button buscarItensBtn, anexarCadProdutosBtn;
	private DownloadLink downloadCadProduto;
	private static final String _FILE_XLS  = PropertiesEJBUtils.getInstance().getValues("requisicao_cadastro_produto");
	private File file = new File(_FILE_XLS);
	
	public SecaoItens(String id, OrRequisicao requisicao, BonitaPage parentPage) {
		super(id);
		
		this.requisicao = requisicao;		
		if(this.requisicao.getOrReqitensdespesas() == null)
			this.requisicao.setOrReqitensdespesas(new HashSet<OrReqitensdespesa>());
		
		setOutputMarkupId(true);
		
		itensSelecionadosProvider = new ItensSelecionadosProvider();
		itensSelecionadosDV = new ItensSelecionadosDataView("itensSelecionados", itensSelecionadosProvider);
		tabelaItensSelecionados = (WebMarkupContainer) new WebMarkupContainer("tabelaItensSelecionados").setOutputMarkupId(true);
		tabelaItensSelecionados.add(itensSelecionadosDV);	
		buscaItensModal = new BuscaItensModal("buscaItensModal");
		secaoTotais = new SecaoTotais("secaoTotais", requisicao);
		secaoAnexoCadastroProduto = new SecaoAnexoCadastroProduto("secaoAnexoCadastroProduto");
		
		buscarItensBtn = new Button("buscarItensBtn");
		anexarCadProdutosBtn = new Button("anexarCadProdutosBtn");
		downloadCadProduto = new DownloadLink("downloadCadProduto",file);
		
		AnexosProvider provider = new AnexosProvider(parentPage.getCaseId(), ConstantesSolicitacaoIntencaoCompra.FORMULARIO_CADASTRO_NOVOS_ITENS);
		secaoAnexos = new Anexos("secaoAnexos", provider);	
		if(provider.size() == 0)
			secaoAnexos.setVisible(false);
		
		add(tabelaItensSelecionados, buscaItensModal, secaoTotais, secaoAnexoCadastroProduto, buscarItensBtn, anexarCadProdutosBtn, downloadCadProduto, secaoAnexos);
	}
	
	public SecaoItens visibilidadeSecaoAnexo(boolean visibilidade){
		secaoAnexos.setVisible(visibilidade);
		return this;
	}
	
	public SecaoItens visibilidadeArquivoCadProdutoBtn(boolean visibilidade){
		this.downloadCadProduto.setVisible(visibilidade);
		return this;
	}
	
	public SecaoItens visibilidadeAnexarCadProdutosBtn(boolean visibildiade){
		this.anexarCadProdutosBtn.setVisible(visibildiade);
		return this;
	}
	
	public void habilitaSecaoTotais(boolean habilitado){
		this.secaoTotais.setEnabled(habilitado);
	}
	
	public void habilitaBuscarItensBtn(boolean habilitado){
		this.buscarItensBtn.setEnabled(habilitado);
	}
	
	public void habilitaArquivoCadProdutoBtn(boolean habilitado){
		this.downloadCadProduto.setEnabled(habilitado);
	}
	
	public void habilitaAnexarCadProdutosBtn(boolean habilitado){
		this.anexarCadProdutosBtn.setEnabled(habilitado);
	}
	
	public void habilitaQuantidade(boolean habilitado){
		itensSelecionadosDV.quantidadeHabilitada = habilitado;
	}
	
	public void habilitaValorUnitario(boolean habilitado){
		itensSelecionadosDV.vlrUnitarioHabilitado = habilitado;
	}
	
	public void habilitaCfop(boolean habilitado){
		itensSelecionadosDV.cfopHabilitado = habilitado;
	}
	
	public void habilitaBtnExclusao(boolean habilitado){
		itensSelecionadosDV.exclusaoBtnHabilitado = habilitado;
	}
	
	public void atualizaVisibilidadeItens(){
		buscaItensModal.visibilidadeTabelaItens();
	}
	
	private class BuscaItensModal extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private static final int QUANT_ITENS_POR_PAGINA = 10;

		private TextField<String> novoItemInput;
		private AjaxButton addNovoItemBtn;
		
		private TextField<String> filtroItem;
		private AjaxButton buscaItemBtn;
		private WebMarkupContainer tabelaItens;
		private ItensDataView itensDataView;
		private ProdutoProvider produtoProvider;
		private AjaxPagingNavigator itensNavigator;
		private WebMarkupContainer msgItensNaoEncontrados;
		
		@SuppressWarnings("unchecked")
		public BuscaItensModal(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			filtroItem = new TextField<String>("filtroItem", Model.of(""));
			produtoProvider = new ProdutoProvider(requisicao);
			itensDataView = (ItensDataView) new ItensDataView("itens", produtoProvider, QUANT_ITENS_POR_PAGINA).setOutputMarkupId(true);
			tabelaItens = (WebMarkupContainer) new WebMarkupContainer("tabelaItens").setOutputMarkupId(true);
			itensNavigator = (AjaxPagingNavigator) new AjaxPagingNavigator("itensNavigator", itensDataView).setOutputMarkupId(true);
			msgItensNaoEncontrados = new WebMarkupContainer("msgItensNaoEncontrados");
			buscaItemBtn = new AjaxButton("buscaItemBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					String filtro = filtroItem.getDefaultModelObjectAsString();
					produtoProvider.putParameter(filtro);
					visibilidadeTabelaItens();
					target.add(BuscaItensModal.this);
				}
			};
			
			novoItemInput = (TextField<String>) new TextField<String>("novoItemInput", Model.of("")).setOutputMarkupId(true);
			addNovoItemBtn = new AjaxButton("addNovoItemBtn") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					String descricao = novoItemInput.getDefaultModelObjectAsString();
					if(!descricao.trim().isEmpty()){
						novoItemInput.setDefaultModelObject("");
						
						OrReqitensdespesa itemNaoCadastrado = criaItemNaoCadastrado(descricao);
						requisicao.getOrReqitensdespesas().add(itemNaoCadastrado);
						target.add(tabelaItensSelecionados, novoItemInput);
					}
					target.appendJavaScript("$('#buscaItemDialog').modal('hide')");
				}
			};
			
			tabelaItens.add(itensDataView, itensNavigator);
			add(filtroItem, buscaItemBtn, tabelaItens, msgItensNaoEncontrados, novoItemInput, addNovoItemBtn);
			visibilidadeTabelaItens();
		}
		
		private OrReqitensdespesa criaItemNaoCadastrado(String descricao){
			OrReqitensdespesa itemNaoCadastrado = new OrReqitensdespesa();
			itemNaoCadastrado.setDescricao(descricao);
			itemNaoCadastrado.setNaoCadastrado(true);
			itemNaoCadastrado.setCfop(1000);
			itemNaoCadastrado.setVlrtotal(new BigDecimal(0));
			itemNaoCadastrado.setNroempresa(requisicao.getNroempresa());
			itemNaoCadastrado.setNroempresaorc(requisicao.getNroempresa());
			itemNaoCadastrado.setComplemento(BigDecimal.ZERO);
			
			return itemNaoCadastrado;
		}
		
		private void visibilidadeTabelaItens() {
			if(produtoProvider.size() == 0){
				tabelaItens.setVisible(false);
				msgItensNaoEncontrados.setVisible(true);
			}else{
				tabelaItens.setVisible(true);
				msgItensNaoEncontrados.setVisible(false);
				if(produtoProvider.size() <= QUANT_ITENS_POR_PAGINA){
					itensNavigator.setVisible(false);
				}else{
					itensNavigator.setVisible(true);
				}
			}
		}
	}	

	
	private class ItensDataView extends DataView<OrvProdutotribEntity>{
		private static final long serialVersionUID = 1L;

		protected ItensDataView(String id, IDataProvider<OrvProdutotribEntity> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<OrvProdutotribEntity> item) {
			final OrvProdutotribEntity produtoTributo = (OrvProdutotribEntity) item.getDefaultModelObject();
			Label lblCodigo = new Label("lblCodigo", produtoTributo.getSeqproduto());
			Label lblDescricao = new Label("lblDescricao", produtoTributo.getDescricao());
			
			AjaxEventBehavior onClickEvent = new AjaxEventBehavior("click"){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {					
					adicionaAosItensSelecionados(produtoTributo);
					target.add(tabelaItensSelecionados);
					target.appendJavaScript("$('#buscaItemDialog').modal('hide')");
					target.appendJavaScript("iniciarCamposMonetarios()");
				}
				
			};
			
			item.add(lblCodigo, lblDescricao).add(onClickEvent);
		}
		
	}
	
	private void adicionaAosItensSelecionados(OrvProdutotribEntity produtoTributo) {
		OrReqitensdespesa itensDespesa =  paraOrReqitensdespesa(produtoTributo);
		requisicao.getOrReqitensdespesas().add(itensDespesa);
	}
	
	private OrReqitensdespesa paraOrReqitensdespesa(OrvProdutotribEntity produtoTributo) {
		OrReqitensdespesa itemDespesa = new OrReqitensdespesa();

		itemDespesa.setCodproduto(produtoTributo.getCodproduto());		
		itemDespesa.setDescricao(produtoTributo.getDescricao());
		itemDespesa.setCfop(1000);
		itemDespesa.setVlrtotal(new BigDecimal(0));
		itemDespesa.setNroempresa(requisicao.getNroempresa());
		itemDespesa.setNroempresaorc(requisicao.getNroempresa());
		itemDespesa.setServico(produtoTributo.getServico());
		itemDespesa.setUnidade(produtoTributo.getUnidadepadrao());
		itemDespesa.setUnidadepadrao(produtoTributo.getUnidadepadrao());
		itemDespesa.setVeiculo(produtoTributo.getVeiculo().toString());
		itemDespesa.setCodtributacao(produtoTributo.getNrotributacao());
		itemDespesa.setVersaoprod(produtoTributo.getVersaoprod().shortValue());
		itemDespesa.setComplemento(BigDecimal.ZERO);
		
		return itemDespesa;		
	}

	/**
	 * Tabela contendo os itens selecionados
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class ItensSelecionadosDataView extends DataView<OrReqitensdespesa>{
		private static final long serialVersionUID = 1L;

		private boolean quantidadeHabilitada = true;
		private boolean cfopHabilitado = true;
		private boolean vlrUnitarioHabilitado = true;
		private boolean exclusaoBtnHabilitado = true;
		
		protected ItensSelecionadosDataView(String id, IDataProvider<OrReqitensdespesa> dataProvider) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(final Item<OrReqitensdespesa> item) {
			OrReqitensdespesa itemDespesa =  (OrReqitensdespesa) item.getDefaultModelObject();
			
			Label codProduto = new Label("codproduto", itemDespesa.getCodproduto());
			Label descricao = new Label("descricao", itemDespesa.getDescricao());
			TextField<Integer> cfop = new TextField<Integer>("cfop", new PropertyModel<Integer>(itemDespesa, "cfop"));
			DefaultZeroBigDecimalModel quantidadeModel = new DefaultZeroBigDecimalModel(new PropertyModel<BigDecimal>(itemDespesa, "quantidade"));
			TextField<String> quantidade = new TextField<String>("quantidade", quantidadeModel);
			DefaultMoneyModel vlritemModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(itemDespesa, "vlritem"));
			TextField<Object> vlritem = new TextField<Object>("vlritem", vlritemModel);
			DefaultMoneyModel vlrTotalModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(itemDespesa, "vlrtotal"));
			Label vlrTotal = new Label("vlrTotalItem", vlrTotalModel);
			AjaxLink<Void> exclusaoItemBtn = new AjaxLink<Void>("exclusaoItemBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onClick(AjaxRequestTarget target) {
					requisicao.getOrReqitensdespesas().remove(item.getModelObject());	
					calculoRequisicao.calculaValorTotalRequisicao(requisicao);
					calculoRequisicao.calculaValorRateio(requisicao);
					target.add(tabelaItensSelecionados, secaoTotais);
				}
			};		
		
			quantidade.add(calculaVlrTotalEvent(item, itemDespesa));
			vlritem.add(calculaVlrTotalEvent(item, itemDespesa));
			cfop.add(defineCfopEvent(item));
			
			quantidade.setEnabled(quantidadeHabilitada);
			cfop.setEnabled(cfopHabilitado);
			vlritem.setEnabled(vlrUnitarioHabilitado);
			exclusaoItemBtn.setEnabled(exclusaoBtnHabilitado);
			
			item.add(codProduto, descricao, cfop, quantidade, vlritem, vlrTotal, exclusaoItemBtn).setOutputMarkupId(true);	
		}		
		
		private AjaxFormComponentUpdatingBehavior calculaVlrTotalEvent(final Item<OrReqitensdespesa> item, final OrReqitensdespesa itemDespesa){
			return new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					calculoRequisicao.calculaValorTotalRequisicao(requisicao);
					calculoRequisicao.calculaValorRateio(requisicao);
					target.add(item, secaoTotais);					
				}
			};
		}
		
		private AjaxFormComponentUpdatingBehavior defineCfopEvent(final Item<OrReqitensdespesa> item){
			return new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					OrReqitensdespesa itemDespesa =  (OrReqitensdespesa) item.getDefaultModelObject();	
					int cfop = itemDespesa.getCfop();
					if(cfop < 1000){
						itemDespesa.setCfop(1000);
					}else if(cfop > 3999){
						itemDespesa.setCfop(3999);
					}
					target.add(item);
				}
			};
		}		
	}
	
	/**
	 * Provider para população da tabela de itens selecionados
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class ItensSelecionadosProvider implements IDataProvider<OrReqitensdespesa>{
		private static final long serialVersionUID = 1L;

		@Override
		public void detach() {
			
		}

		@Override
		public Iterator<? extends OrReqitensdespesa> iterator(long first, long count) {
			return requisicao.getOrReqitensdespesas().iterator();
		}

		@Override
		public long size() {
			return requisicao.getOrReqitensdespesas().size();
		}

		@Override
		public IModel<OrReqitensdespesa> model(final OrReqitensdespesa orvProdutoTributo) {
			return new LoadableDetachableModel<OrReqitensdespesa>() {
				private static final long serialVersionUID = 1L;
				@Override
				protected OrReqitensdespesa load() {
					return orvProdutoTributo;
				}
			};
		}
		
	}
	
	private class SecaoTotais extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private TextField<Object> descontos, acrescimos, total, liquido;
		//private Label total, liquido;
		
		public SecaoTotais(String id, OrRequisicao requisicao) {
			super(id, new CompoundPropertyModel<OrRequisicao>(requisicao));
			
			setOutputMarkupId(true);
			
			DefaultMoneyModel totalModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(requisicao, "valor"));
			total = new TextField<Object>("valor", totalModel);
			total.setEnabled(false);
			
			DefaultMoneyModel descontosModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(requisicao, "vlrdescontos"));
			descontos = new TextField<Object>("vlrdescontos", descontosModel);
			
			DefaultMoneyModel acrescimosModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(requisicao, "vlracrescimos"));
			acrescimos = new TextField<Object>("vlracrescimos", acrescimosModel);
			
			DefaultMoneyModel liquidoModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(requisicao, "vlrliqreq"));
			liquido = new TextField<Object>("vlrliqreq", liquidoModel);
			liquido.setEnabled(false);
			
			descontos.add(calculaVlrTotalEvent());
			acrescimos.add(calculaVlrTotalEvent());
			
			add(total, descontos, acrescimos, liquido);
		}
		
		private AjaxFormComponentUpdatingBehavior calculaVlrTotalEvent(){
			return new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					calculoRequisicao.calculaValorTotalRequisicao(requisicao);
					calculoRequisicao.calculaValorRateio(requisicao);
					target.add(SecaoTotais.this);					
				}
			};
		}
		
	}
	
	private class SecaoAnexoCadastroProduto extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private FileUploadField anexoCadastroProduto;
		private AjaxButton anexoBtn;
		
		public SecaoAnexoCadastroProduto(String id) {
			super(id);
			
			anexoCadastroProduto = new FileUploadField("anexoCadastroProduto");
			anexoBtn = new AjaxButton("anexoBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					FileUpload fileUpload = anexoCadastroProduto.getFileUpload();
					
					if(fileUpload != null){
						setFormularioCadastroItens(DocumentoHelper.criaDocumento(fileUpload, FORMULARIO_CADASTRO_NOVOS_ITENS));
					}
					
					target.appendJavaScript("$('#anexoDialog').modal('hide');");
					System.out.println(fileUpload);
				}
			};			
			add(anexoCadastroProduto, anexoBtn);
			
		}		
	}

	public void atualizaSecao(AjaxRequestTarget target) {
	    atualizaVisibilidadeItens();
	    target.add(this);
	}

	public Documento getFormularioCadastroItens() {
		return formularioCadastroItens;
	}

	public void setFormularioCadastroItens(Documento formularioCadastroItens) {
		this.formularioCadastroItens = formularioCadastroItens;
	}	

}
