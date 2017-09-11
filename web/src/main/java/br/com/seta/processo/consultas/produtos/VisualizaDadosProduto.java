package br.com.seta.processo.consultas.produtos;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.Categorias;
import br.com.seta.processo.bean.Classificacoes;
import br.com.seta.processo.bean.MapEmbalagens;
import br.com.seta.processo.cadastrofornecedores.dominios.SimNao;
import br.com.seta.processo.cadastroprodutos.dominios.ComboDominio;
import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.MapNcmDto;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.provider.MapNcmProvider;

public class VisualizaDadosProduto extends BonitaPage {

	private static final long serialVersionUID = 1L;

	@Inject private Categorias categoriasService;
	@Inject private Classificacoes classificacoesService;
	@Inject private MapEmbalagens mapEmbalagens;
	
	private MapNcmProvider ncmProvider = new MapNcmProvider();
	private FormularioProduto model;
	private ComboDominio comboDominio  = new ComboDominio();
	private Button btnVoltar, consultaCadProdutoBtn;
	private WebMarkupContainer grpdadosproduto;
	private WebMarkupContainer grpembalagem;
	private WebMarkupContainer grppaletizacaoestocagem;
	private WebMarkupContainer grpdadosnutricionais;
	private WebMarkupContainer grpdadosfiscais;
	private WebMarkupContainer grppreenchimentointerno;
	private TextField<String>	txtNcm;
	private TextField<String>	txtDescricaoNcm;
	private Form<Void> produtoForm;
	
	public VisualizaDadosProduto(final FormularioProduto model, final CadastroProduto cadastro){
		setTituloPagina("Dados do Produto");
		
		this.model = model;
		grpdadosproduto = (WebMarkupContainer) new GrpDadosProduto("grpDadosProduto").setEnabled(false);
		grpembalagem = (WebMarkupContainer) new GrpEmbalagem("grpEmbalagem").setEnabled(false);
		grppaletizacaoestocagem = (WebMarkupContainer) new GrpPaletizacaoEstocagem("grpPaletizacaoEstocagem").setEnabled(false);
		grpdadosnutricionais = (WebMarkupContainer) new GrpDadosNutricionais("grpDadosNutricionais").setEnabled(false);
		grpdadosfiscais = (WebMarkupContainer) new GrpDadosFiscais("grpDadosFiscais").setEnabled(false);
		grppreenchimentointerno =  (WebMarkupContainer) new GrpPreenchimentoInterno("grpPreenchimentoInterno").setEnabled(false);
		produtoForm = new Form<Void>("produtoForm");	
		
		btnVoltar = new Button("btnVoltar"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				setResponsePage(new SolicitacaoCadastroProduto(cadastro, model));
			}
		};
		
		consultaCadProdutoBtn = new Button("consultaCadProdutoBtn"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				setResponsePage(ConsultaProdutos.class);
			}			
		};
		
		produtoForm.add(grpdadosproduto, grpembalagem, grppaletizacaoestocagem,
				grpdadosnutricionais, grpdadosfiscais, grppreenchimentointerno, btnVoltar, consultaCadProdutoBtn);
		add(produtoForm);
	}

	private final class GrpDadosProduto extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public GrpDadosProduto(String id) {
			super(id);

			final TextField<String> txtDescCompleta = new TextField<String>("txtDescCompleta",	new PropertyModel<String>(model, "descCompleta"));
			final TextField<String> txtCodProduto 	= new TextField<String>("txtCodProduto", 	new PropertyModel<String>(model, "codProduto"));
			final TextField<String> txtDescReduzida = new TextField<String>("txtDescReduzida",	new PropertyModel<String>(model, "descReduzida"));
			final TextField<String> txtPaisOrigem 	= new TextField<String>("txtPaisOrigem", 	new PropertyModel<String>(model, "paisOrigem"));

			final DropDownChoice<String> cmbOrigemProduto = new DropDownChoice<String>("cmbOrigemProduto", 
					new PropertyModel<String> (model, "origemProduto"), 
					mapEmbalagens.findOrigemProduto());		

			RadioGroup<String> rdgSimplesNacional = new RadioGroup<String>("rdgSimplesNacional", new PropertyModel<String>(model, "simplesNacional"));
			rdgSimplesNacional.add(new Radio<String>("rdbCertificadoNacional_0", new Model<String>(SimNao.getValores().get(0))));
			rdgSimplesNacional.add(new Radio<String>("rdbCertificadoNacional_1", new Model<String>(SimNao.getValores().get(1))));	
			
			add(txtDescCompleta, txtCodProduto, txtDescReduzida, txtPaisOrigem);
			add(cmbOrigemProduto);
			add(rdgSimplesNacional);
		}
	}

	private final class GrpEmbalagem extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public GrpEmbalagem(String id) {
			super(id);

			final TextField<String> txtQuantidadeUnidade 	= new TextField<String>("txtQuantidadeUnidade", 	new PropertyModel<String>(model, "quantidadeUnidade"));
			final TextField<String> txtEanUnidade 			= new TextField<String>("txtEanUnidade", 			new PropertyModel<String>(model, "eanUnidade"));
			final TextField<String> txtPesoLiqUnidade 		= new TextField<String>("txtPesoLiqUnidade", 		new PropertyModel<String>(model, "pesoLiqUnidade"));
			final TextField<String> txtPesoBrutoUnidade 	= new TextField<String>("txtPesoBrutoUnidade", 		new PropertyModel<String>(model, "pesoBrutoUnidade"));
			final TextField<String> txtComprimentoUnidade 	= new TextField<String>("txtComprimentoUnidade",	new PropertyModel<String>(model, "comprimentoUnidade"));
			final TextField<String> txtAlturaUnidade 		= new TextField<String>("txtAlturaUnidade", 		new PropertyModel<String>(model, "alturaUnidade"));
			final TextField<String> txtLarguraUnidade 		= new TextField<String>("txtLarguraUnidade", 		new PropertyModel<String>(model, "larguraUnidade"));
			final TextField<String> txtQuantidadeDisplay 	= new TextField<String>("txtQuantidadeDisplay", 	new PropertyModel<String>(model, "quantidadeDisplay"));
			final TextField<String> txtEanDisplay 			= new TextField<String>("txtEanDisplay", 			new PropertyModel<String>(model, "eanDisplay"));
			final TextField<String> txtPesoLiqDisplay 		= new TextField<String>("txtPesoLiqDisplay", 		new PropertyModel<String>(model, "pesoLiqDisplay"));
			final TextField<String> txtPesoBrutoDisplay 	= new TextField<String>("txtPesoBrutoDisplay", 		new PropertyModel<String>(model, "pesoBrutoDisplay"));
			final TextField<String> txtComprimentoDisplay 	= new TextField<String>("txtComprimentoDisplay",	new PropertyModel<String>(model, "comprimentoDisplay"));
			final TextField<String> txtAlturaDisplay 		= new TextField<String>("txtAlturaDisplay", 		new PropertyModel<String>(model, "alturaDisplay"));
			final TextField<String> txtLarguraDisplay 		= new TextField<String>("txtLarguraDisplay", 		new PropertyModel<String>(model, "larguraDisplay"));
			final TextField<String> txtEanEmbalagem			= new TextField<String>("txtEanEmbalagem", 			new PropertyModel<String>(model, "eanEmbalagem"));
			final TextField<String> txtQuantidadeEmbalagem 	= new TextField<String>("txtQuantidadeEmbalagem",	new PropertyModel<String>(model, "quantidadeEmbalagem"));
			final TextField<String> txtPesoBrutoEmbalagem 	= new TextField<String>("txtPesoBrutoEmbalagem", 	new PropertyModel<String>(model, "pesoBrutoEmbalagem"));
			final TextField<String> txtComprimentoEmbalagem = new TextField<String>("txtComprimentoEmbalagem", 	new PropertyModel<String>(model, "comprimentoEmbalagem"));
			final TextField<String> txtAlturaEmbalagem 		= new TextField<String>("txtAlturaEmbalagem", 		new PropertyModel<String>(model, "alturaEmbalagem"));
			final TextField<String> txtLarguraEmbalagem 	= new TextField<String>("txtLarguraEmbalagem",	 	new PropertyModel<String>(model, "larguraEmbalagem"));

			RadioGroup<String> rdgPossuiEmbalagemEmbalagem = new RadioGroup<String>("rdgPossuiEmbalagemEmbalagem", new PropertyModel<String>(model, "possuiEmbalagem"));
			rdgPossuiEmbalagemEmbalagem.add(new Radio<String>("rdbPossuiEmbalagemEmbalagem_0", new Model<String>(SimNao.getValores().get(0))));
			rdgPossuiEmbalagemEmbalagem.add(new Radio<String>("rdbPossuiEmbalagemEmbalagem_1", new Model<String>(SimNao.getValores().get(1))));

			final DropDownChoice<String> cmbEmbalagemEmbalagem = new DropDownChoice<String>("cmbEmbalagemEmbalagem", 
					new PropertyModel<String> (model, "embalagemEmbalagem"), 
					mapEmbalagens.findEmbalagemReduzido());

			final DropDownChoice<String> cmbFormaEntregaEmbalagem = new DropDownChoice<String>("cmbFormaEntregaEmbalagem", 
					new PropertyModel<String> (model, "formaEntrega"), 
					comboDominio.getFormasEntrega());

			final DropDownChoice<String> cmbEmbalagemDisplay = new DropDownChoice<String>("cmbEmbalagemDisplay", 
					new PropertyModel<String> (model, "embalagemDisplay"), 
					mapEmbalagens.findEmbalagemReduzido());

			final DropDownChoice<String> cmbEmbalagemUnidade = new DropDownChoice<String>("cmbEmbalagemUnidade", 
					new PropertyModel<String> (model, "embalagemUnidade"), 
					mapEmbalagens.findEmbalagemReduzido());

			add(txtQuantidadeUnidade,txtEanUnidade,txtPesoLiqUnidade,txtPesoBrutoUnidade,txtComprimentoUnidade,txtAlturaUnidade,
					txtLarguraUnidade,txtQuantidadeDisplay,txtEanDisplay,txtPesoLiqDisplay,txtPesoBrutoDisplay,
					txtComprimentoDisplay,txtAlturaDisplay,txtLarguraDisplay,txtEanEmbalagem,txtQuantidadeEmbalagem,
					txtPesoBrutoEmbalagem,txtComprimentoEmbalagem,txtAlturaEmbalagem,txtLarguraEmbalagem);

			add(rdgPossuiEmbalagemEmbalagem);

			add(cmbEmbalagemEmbalagem,cmbFormaEntregaEmbalagem,cmbEmbalagemDisplay,cmbEmbalagemUnidade);
		}
	}

	private final class GrpPaletizacaoEstocagem extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public GrpPaletizacaoEstocagem(String id) {
			super(id);

			final TextField<String>	txtQuantidadeLastroPaletizado	= new TextField<String>("txtQuantidadeLastroPaletizado", 	new PropertyModel<String>(model, "quantidadeLastroPaletizado"));
			final TextField<String>	txtQuantidadeAlturaPaletizado	= new TextField<String>("txtQuantidadeAlturaPaletizado", 	new PropertyModel<String>(model, "quantidadeAlturaPaletizado"));
			final TextField<String>	txtTotalPalletPaletizado		= new TextField<String>("txtTotalPalletPaletizado", 		new PropertyModel<String>(model, "totalPalletPaletizado"));
			final TextField<String>	txtPesoTotalPaletizado			= new TextField<String>("txtPesoTotalPaletizado", 			new PropertyModel<String>(model, "pesoTotalPaletizado"));
			final TextField<String>	txtComprimentoPaletizado		= new TextField<String>("txtComprimentoPaletizado", 		new PropertyModel<String>(model, "comprimentoPaletizado"));
			final TextField<String>	txtAlturaPaletizado				= new TextField<String>("txtAlturaPaletizado", 				new PropertyModel<String>(model, "alturaPaletizado"));
			final TextField<String>	txtLarguraPaletizado			= new TextField<String>("txtLarguraPaletizado", 			new PropertyModel<String>(model, "larguraPaletizado"));
			final TextField<String>	txtCMaximaEstocagem				= new TextField<String>("txtCMaximaEstocagem", 				new PropertyModel<String>(model, "cMaximaEstocagem"));
			final TextField<String>	txtCMinimaEstocagem				= new TextField<String>("txtCMinimaEstocagem", 				new PropertyModel<String>(model, "cMinimaEstocagem"));
			final TextField<String>	txtValidadeEstocagem			= new TextField<String>("txtValidadeEstocagem", 			new PropertyModel<String>(model, "validadeEstocagem"));
			final TextField<String>	txtEmpilhamentoEstocagem		= new TextField<String>("txtEmpilhamentoEstocagem", 		new PropertyModel<String>(model, "empilhamentoEstocagem"));

			RadioGroup<String>  rdgPerecivelPaletizado = new RadioGroup<String>("rdgPerecivelPaletizado", new PropertyModel<String>(model, "perecivel"));
			rdgPerecivelPaletizado.add(new Radio<String>("rdbPerecivelPaletizado_0", new Model<String>(SimNao.getValores().get(0))));
			rdgPerecivelPaletizado.add(new Radio<String>("rdbPerecivelPaletizado_1", new Model<String>(SimNao.getValores().get(1))));

			RadioGroup<String> rdgPesoVariavelPaletizado = new RadioGroup<String>("rdgPesoVariavelPaletizado", new PropertyModel<String>(model, "pesoVariavel"));
			rdgPesoVariavelPaletizado.add(new Radio<String>("rdbPesoVariavelPaletizado_0", new Model<String>(SimNao.getValores().get(0))));
			rdgPesoVariavelPaletizado.add(new Radio<String>("rdbPesoVariavelPaletizado_1", new Model<String>(SimNao.getValores().get(1))));

			RadioGroup<String> rdgSazonalPaletizado = new RadioGroup<String>("rdgSazonalPaletizado", new PropertyModel<String>(model, "sazonal"));
			rdgSazonalPaletizado.add(new Radio<String>("rdbSazonalPaletizado_0", new Model<String>(SimNao.getValores().get(0))));
			rdgSazonalPaletizado.add(new Radio<String>("rdbSazonalPaletizado_1", new Model<String>(SimNao.getValores().get(1))));

			RadioGroup<String> rdgProdutoPaletizado = new RadioGroup<String>("rdgProdutoPaletizado", new PropertyModel<String>(model, "produtoPaletizado"));
			rdgProdutoPaletizado.add(new Radio<String>("rdbProdutoPaletizado_0", new Model<String>(SimNao.getValores().get(0))));
			rdgProdutoPaletizado.add(new Radio<String>("rdbProdutoPaletizado_1", new Model<String>(SimNao.getValores().get(1))));

			add(txtQuantidadeLastroPaletizado,txtQuantidadeAlturaPaletizado,txtTotalPalletPaletizado,txtPesoTotalPaletizado,
					txtComprimentoPaletizado,txtAlturaPaletizado,txtLarguraPaletizado,txtCMaximaEstocagem,txtCMinimaEstocagem,
					txtCMinimaEstocagem,txtValidadeEstocagem,txtEmpilhamentoEstocagem);

			add(rdgPerecivelPaletizado,rdgPesoVariavelPaletizado,rdgSazonalPaletizado,rdgProdutoPaletizado);
		}
	}

	private final class GrpDadosNutricionais extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public GrpDadosNutricionais(String id) {
			super(id);

			final TextField<String>	txtQuantidadeParcao	= new TextField<String>("txtQuantidadeParcao", 	new PropertyModel<String>(model,"quantidadePorcao"));
			final TextField<String>	txtValorCalorico	= new TextField<String>("txtValorCalorico", 	new PropertyModel<String>(model,"valorCalorico"));
			final TextField<String>	txtProteina			= new TextField<String>("txtProteina", 			new PropertyModel<String>(model,"proteina"));
			final TextField<String>	txtGorduraSaturada	= new TextField<String>("txtGorduraSaturada", 	new PropertyModel<String>(model,"gorduraSaturada"));
			final TextField<String>	txtFibraAlimentar	= new TextField<String>("txtFibraAlimentar", 	new PropertyModel<String>(model,"fibraAlimentar"));
			final TextField<String>	txtSodio			= new TextField<String>("txtSodio", 			new PropertyModel<String>(model,"sodio"));
			final TextField<String>	txtCarboidrato		= new TextField<String>("txtCarboidrato", 		new PropertyModel<String>(model,"carboidrato"));
			final TextField<String>	txtGorduraTotal		= new TextField<String>("txtGorduraTotal", 		new PropertyModel<String>(model,"gorduraTotal"));
			final TextField<String>	txtGorduraTrans		= new TextField<String>("txtGorduraTrans", 		new PropertyModel<String>(model,"gorduraTrans"));

			final DropDownChoice<String> cmbUnidadePorcao = new DropDownChoice<String>("cmbUnidadePorcao", 
					new PropertyModel<String> (model, "unidadePorcao"), 
					comboDominio.getUnidadesPorcao());

			add(txtQuantidadeParcao,txtValorCalorico,txtProteina,txtGorduraSaturada,txtFibraAlimentar,
					txtSodio,txtCarboidrato,txtGorduraTotal,txtGorduraTrans);

			add(cmbUnidadePorcao);
		}
	}
	
	private final class GrpDadosFiscais extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public GrpDadosFiscais(String id) {
			super(id);

			txtNcm = new TextField<String>("txtNcm", 			new PropertyModel<String>(model,"cnm"));
			txtNcm.setOutputMarkupId(true);
			txtNcm.setEnabled(false);
			txtDescricaoNcm		= new TextField<String>("txtDescricaoNcm", 	new PropertyModel<String>(model,"descricaoNcm"));
			txtDescricaoNcm.setOutputMarkupId(true);
			txtDescricaoNcm.setEnabled(false);
			final TextField<String>	txtAliquotaIpi		= new TextField<String>("txtAliquotaIpi", 	new PropertyModel<String>(model,"aliquotaIpi"));
			final TextField<String>	txtAliquotaCofins	= new TextField<String>("txtAliquotaCofins",new PropertyModel<String>(model,"aliquotaCofins"));
			final TextField<String>	txtAliquotaPis		= new TextField<String>("txtAliquotaPis", 	new PropertyModel<String>(model,"aliquotaPis"));
			final TextField<String>	txtPauta			= new TextField<String>("txtPauta", 		new PropertyModel<String>(model,"pauta"));
			final TextField<String>	txtIvaSt			= new TextField<String>("txtIvaSt", 		new PropertyModel<String>(model,"ivaSt"));
			final TextField<String>	txtFundamentacao	= new TextField<String>("txtFundamentacao", new PropertyModel<String>(model,"fundamentacao"));
			final TextField<String>	txtIncentivoFiscalQual	= new TextField<String>("txtIncentivoFiscalQual", new PropertyModel<String>(model,"incentivoFiscalQual"));
			final TextField<String>	txtcustoBase      	= new TextField<String>("txtcustoBase", new PropertyModel<String>(model,"custoBase"));
			final TextField<String>	txtdesconto	        = new TextField<String>("txtdesconto", new PropertyModel<String>(model,"desconto"));
			final TextField<String>	txtdespAcessorios	= new TextField<String>("txtdespAcessorios", new PropertyModel<String>(model,"despAcessorios"));
			final TextField<String>	txtcustoBruto	    = new TextField<String>("txtcustoBruto", new PropertyModel<String>(model,"custoBruto"));
			

			RadioGroup<String> rdgIncentivoFiscal = new RadioGroup<String>("rdgIncentivoFiscal", new PropertyModel<String>(model, "incentivoFiscal"));
			rdgIncentivoFiscal.add(new Radio<String>("rdbIncentivoFiscal_0", new Model<String>(SimNao.getValores().get(0))));
			rdgIncentivoFiscal.add(new Radio<String>("rdbIncentivoFiscal_1", new Model<String>(SimNao.getValores().get(1))));				

			final DropDownChoice<String> cmbTributacaoIcms = new DropDownChoice<String>("cmbTributacaoIcms", 
					new PropertyModel<String> (model, "tributacaoIcms"), 
					comboDominio.getTributacoesIcms());

			final DropDownChoice<String> cmbAlicotaIcms = new DropDownChoice<String>("cmbAlicotaIcms", 
					new PropertyModel<String> (model, "alicotaIcms"), 
					comboDominio.getAliquostaIcms());

			final DropDownChoice<String> cmbReducaoIcms = new DropDownChoice<String>("cmbReducaoIcms", 
					new PropertyModel<String> (model, "reducaoIcms"), 
					comboDominio.getReducoes()); 
			
			add(new AjaxButton("btnExibirModalNcm") {
				private static final long serialVersionUID = 1L;
				@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					target.appendJavaScript("$('#consultarNcmDialog').modal('show');");
				}
			});

			add(txtNcm,txtDescricaoNcm,txtAliquotaIpi,txtAliquotaCofins,txtAliquotaPis,txtPauta,txtIvaSt,txtFundamentacao,txtIncentivoFiscalQual,txtcustoBase,txtdesconto,txtdespAcessorios,txtcustoBruto);
			add(rdgIncentivoFiscal);
			add(cmbTributacaoIcms,cmbAlicotaIcms,cmbReducaoIcms);
		}

	}

	private final class GrpPreenchimentoInterno extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		public GrpPreenchimentoInterno(String id) {
			super(id);

			final DropDownChoice<String> cmbProdutoPara = new DropDownChoice<String>("cmbProdutoPara", 
					new PropertyModel<String> (model, "produtoPara"), 
					comboDominio.getProdutoPara());

			final DropDownChoice<String> cmbCategoria = new DropDownChoice<String>("cmbCategoria",  
				    new PropertyModel<String> (model, "categoria"), categoriasService.listaCategorias());
			
			final TextField<String> cmbComprador = new TextField<String>("cmbComprador", new PropertyModel<String> (model, "nomeComprador"));

			final DropDownChoice<String> cmbDivisao = new DropDownChoice<String>("cmbDivisao", 
					new PropertyModel<String> (model, "divisao"), 
					comboDominio.getDivisao());

			List<String> classificacoesComercial = classificacoesService.listaClassificacoesComercial();
			final DropDownChoice<String> cmbAtacado = new DropDownChoice<String>("cmbAtacado",  
					new PropertyModel<String> (model, "atacado"), 
					classificacoesComercial);
			
			final DropDownChoice<String> cmbTelemarketing = new DropDownChoice<String>("cmbTelemarketing",  
					new PropertyModel<String> (model, "telemarketing"), 
					classificacoesComercial);
			
			final DropDownChoice<String> cmbGrupo = new DropDownChoice<String>("cmbGrupo",  
					new PropertyModel<String> (model, "grupo"), classificacoesComercial);
			
			final DropDownChoice<String> cmbVarejo = new DropDownChoice<String>("cmbVarejo",  
					new PropertyModel<String> (model, "varejo"), classificacoesComercial);
			
			add(cmbProdutoPara, cmbCategoria, cmbComprador, cmbAtacado, cmbTelemarketing, cmbGrupo, cmbVarejo);
			add(cmbDivisao);
		}

	} 

	@SuppressWarnings("unused")
	private final class ConsultarNcmDialog extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		private String ncm;
		public ConsultarNcmDialog(String id) {
			super(id);
			final WebMarkupContainer rptConsultarNcm = new WebMarkupContainer("rptConsultarNcm");
			rptConsultarNcm.setOutputMarkupId(true);

			TextField<String> txtDescricaoNcmConsulta = new TextField<String>("txtDescricaoNcmConsulta", new PropertyModel<String>(this, "ncm"));

			AjaxButton btnPesquisarNcm = new AjaxButton("btnPesquisarNcm") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					ncmProvider.putParameter(ncm);
					target.add(rptConsultarNcm);
				}
			};

			DataView<MapNcmDto> dtvNcmConsulta = new DataView<MapNcmDto>("dtvNcmConsulta", ncmProvider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<MapNcmDto> item) {
					final MapNcmDto dto = (MapNcmDto) item.getModelObject();

					item.add(new Label("lblCodigoNcmConsulta", dto.getCodncm()));
					item.add(new Label("lblNcmConsulta", dto.getDescricao()));

					AjaxLink<String> btnSelecionarNcm = new AjaxLink<String>("btnSelecionarNcm") {
						private static final long serialVersionUID = 1L;
						@Override
						public void onClick(AjaxRequestTarget target) {
							model.setCnm(dto.getCodncm());
							model.setDescricaoNcm(dto.getDescricao());

							target.add(txtNcm);
							target.add(txtDescricaoNcm);

							target.appendJavaScript("$('#consultarNcmDialog').modal('hide');");
						}
					};

					item.add(btnSelecionarNcm);
				}
			};

			AjaxPagingNavigator apn = new AjaxPagingNavigator("navigatorNcm", dtvNcmConsulta);
			dtvNcmConsulta.setItemsPerPage(10L);
			rptConsultarNcm.add(txtDescricaoNcmConsulta);
			rptConsultarNcm.add(dtvNcmConsulta, apn);
			rptConsultarNcm.add(btnPesquisarNcm);
			add(rptConsultarNcm);
		}
		
		public String getNcm() {
			return ncm;
		}
		public void setNcm(String ncm) {
			this.ncm = ncm;
		}
	}
}