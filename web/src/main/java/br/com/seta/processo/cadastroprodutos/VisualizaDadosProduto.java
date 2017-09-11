package br.com.seta.processo.cadastroprodutos;

import static br.com.seta.processo.cadastroprodutos.AtividadeCadastroProduto.APROVAR_CADASTRO_PRODUTO;
import static br.com.seta.processo.cadastroprodutos.AtividadeCadastroProduto.CORRIGIR_DADOS_FISCAIS_FORNECEDOR;
import static br.com.seta.processo.cadastroprodutos.AtividadeCadastroProduto.CORRIGIR_DADOS_FORNECEDOR;
import static br.com.seta.processo.cadastroprodutos.AtividadeCadastroProduto.DEFINIR_CLASSIFICAO;
import static br.com.seta.processo.cadastroprodutos.AtividadeCadastroProduto.PRE_CADASTRO_PRODUTO;
import static br.com.seta.processo.cadastroprodutos.AtividadeCadastroProduto.VALIDAR_DADOS_LOGISTICO;
import static br.com.seta.processo.cadastroprodutos.AtividadeCadastroProduto.VERIFICA_INFORMACOES;
import static br.com.seta.processo.cadastroprodutos.AtividadeCadastroProduto.VERIFICA_INFORMACOES_PENDENTES;
import static br.com.seta.processo.constant.MensagemProdutos.APROVAR_CADASTRO;
import static br.com.seta.processo.constant.MensagemProdutos.ENVIA_PARA_APROVACAO;
import static br.com.seta.processo.constant.MensagemProdutos.REJEITAR_CADASTRO;
import static br.com.seta.processo.constant.StatusCadastroProduto.ANALISTA_DADOS_CORRIGIDO;
import static br.com.seta.processo.constant.StatusCadastroProduto.CADASTRO_DEFINIR_CLASSIFICAO;
import static br.com.seta.processo.constant.StatusCadastroProduto.CADASTRO_PRE_ANALISADO;
import static br.com.seta.processo.constant.StatusCadastroProduto.CADASTRO_REVISADO;
import static br.com.seta.processo.constant.StatusCadastroProduto.CADASTRO_SOLICITA_CORRECAO;
import static br.com.seta.processo.constant.StatusCadastroProduto.DADOS_FISCAIS_CORRIGIDOS;
import static br.com.seta.processo.constant.StatusCadastroProduto.FISCAL_CADASTRO_APROVADO;
import static br.com.seta.processo.constant.StatusCadastroProduto.FISCAL_SOLICITA_CORRECAO;
import static br.com.seta.processo.constant.StatusCadastroProduto.GERENTE_COMERCIAL_APROVADO;
import static br.com.seta.processo.constant.StatusCadastroProduto.GERENTE_COMERCIAL_REJEITADO;
import static br.com.seta.processo.constant.StatusCadastroProduto.LOGISTICA_CADASTRO_APROVADO;
import static br.com.seta.processo.constant.StatusCadastroProduto.LOGISTICA_CADASTRO_REJEITADO;
import static br.com.seta.processo.dto.EstadosCadastroProdutos.APROVADO;
import static br.com.seta.processo.dto.EstadosCadastroProdutos.ENVIADO_PARA_CORRRECAO;
import static br.com.seta.processo.dto.EstadosCadastroProdutos.REJEITADO;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;
import org.jboss.logging.Logger;

import br.com.seta.processo.bean.Categorias;
import br.com.seta.processo.bean.Classificacoes;
import br.com.seta.processo.bean.MapEmbalagens;
import br.com.seta.processo.cadastrofornecedores.dominios.MotivoRejeicao;
import br.com.seta.processo.cadastrofornecedores.dominios.SimNao;
import br.com.seta.processo.cadastroprodutos.dominios.ComboDominio;
import br.com.seta.processo.constant.VariaveisCadastroProduto;
import br.com.seta.processo.dto.AprovacaoGerenciaComercial;
import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.HistoricoProduto;
import br.com.seta.processo.dto.MapNcmDto;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dtobonita.Contatos;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.exceptions.CadastroProdutoException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.model.ProdutoCompradorModel;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.page.TaskPage;
import br.com.seta.processo.provider.MapNcmProvider;
import br.com.seta.processo.service.DadosUsuarioService;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.service.UserService;
import br.com.seta.processo.validacao.ValidadorBean;
import br.com.seta.widgetswicketprocesso.textfields.moneytextfield.MoneyTextField;

public class VisualizaDadosProduto extends TaskPage {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(VisualizaDadosProduto.class);

	@Inject private GroupService groupService;
	@Inject private DadosUsuarioService dadosUsuarioService;
	@Inject private Categorias categoriasService;
	@Inject private Classificacoes classificacoesService;
	@Inject	private ValidadorBean validadorBean;
	@Inject private UserService userService;

	private MapNcmProvider ncmProvider = new MapNcmProvider();
	private FormularioProduto model;
	private ComboDominio comboDominio  = new ComboDominio();
	private CadastroProduto cadastroProduto =  new CadastroProduto();
	private PageReference paginaAnterior;
	private AtividadeCadastroProduto tipoAtividade;
	private Button btnVoltar;
	private WebMarkupContainer salvarAlteracoesContainer, aprovacaoContainer;
	private WebMarkupContainer grpdadosproduto;
	private WebMarkupContainer grpembalagem;
	private WebMarkupContainer grppaletizacaoestocagem;
	private WebMarkupContainer grpdadosnutricionais;
	private WebMarkupContainer grpdadosfiscais;
	private WebMarkupContainer grppreenchimentointerno;
	private TextField<String>	txtNcm;
	private TextField<String>	txtDescricaoNcm;
	private Form<Void> produtoForm, rejeicaoModalForm;
	private String mensagemAprovacao;
	private String mensagemRejeicao;
	private String mensagemBoxAprovao;
	private String mensagemBoxRejeicao;
	private SolicitacaoCorrecaoModal solicitacaoCorrecaoModal;
	private EnvioFiscalModal envioFiscalModal;
	private ComentariosFiscalModal comentariosFiscalModal;

	private WebMarkupContainer secaoAprovar, secaoSalvar, secaoFiscal;

	private transient User user = (User) Session.get().getAttribute("user");
	@Inject private MapEmbalagens mapEmbalagens;
	@Inject private ExecuteRestAPI restApi;

	public VisualizaDadosProduto(PageParameters pageParameters, PageReference pageReference, FormularioProduto produto) throws HttpStatus401Exception, org.apache.http.ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException, CadastroProdutoException, BonitaException {
		super(pageParameters);

		this.model = produto;
		this.paginaAnterior  = pageReference;
		this.cadastroProduto = (CadastroProduto) restApi.retriveVariableTask(user, this.getTaskId(), CadastroProduto.class, VariaveisCadastroProduto.CADASTRO_PRODUTO);
		this.cadastroProduto.setCaseId(Integer.toString(getCaseId()));
		this.model.setCaseId(Integer.toString(getCaseId()));
		this.tipoAtividade = obtemTipoAtividade();

		grpdadosproduto = new GrpDadosProduto("grpDadosProduto");
		grpembalagem = new GrpEmbalagem("grpEmbalagem");
		grppaletizacaoestocagem = new GrpPaletizacaoEstocagem("grpPaletizacaoEstocagem");
		grpdadosnutricionais = new GrpDadosNutricionais("grpDadosNutricionais");
		grpdadosfiscais = new GrpDadosFiscais("grpDadosFiscais");
		grppreenchimentointerno =  new GrpPreenchimentoInterno("grpPreenchimentoInterno");
		ConsultarNcmDialog consultarNcmDialog = new ConsultarNcmDialog("consultarNcmDialog");

		produtoForm = new Form<Void>("produtoForm");	
		btnVoltar = new Button("btnVoltar"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		iniciaSecaoBotoes();
		iniciaModals();		
		
		produtoForm.add(grpdadosproduto,grpembalagem,grppaletizacaoestocagem,grpdadosnutricionais,grpdadosfiscais,grppreenchimentointerno,
				btnVoltar,secaoAprovar, secaoSalvar, secaoFiscal, salvarAlteracoesContainer, aprovacaoContainer, consultarNcmDialog, solicitacaoCorrecaoModal, envioFiscalModal, comentariosFiscalModal);
		add(produtoForm, rejeicaoModalForm);

		AjaxEventBehavior onloadEvent = new AjaxEventBehavior("onload") {		    
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {
				target.appendJavaScript("$(function(){"+ javascriptSecaoAtiva() + "})");
			}
		};
		add(onloadEvent);

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
			final MoneyTextField	txtcustoBase      	= new MoneyTextField("txtcustoBase", new PropertyModel<BigDecimal>(model,"custoBase"));
			final MoneyTextField	txtdesconto	        = new MoneyTextField("txtdesconto", new PropertyModel<BigDecimal>(model,"desconto"));
			final MoneyTextField	txtdespAcessorios	= new MoneyTextField("txtdespAcessorios", new PropertyModel<BigDecimal>(model,"despAcessorios"));
			final MoneyTextField	txtcustoBruto	    = new MoneyTextField("txtcustoBruto", new PropertyModel<BigDecimal>(model,"custoBruto"));

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
		public GrpPreenchimentoInterno(String id) throws BonitaException {
			super(id);

			final DropDownChoice<String> cmbProdutoPara = new DropDownChoice<String>("cmbProdutoPara", 
					new PropertyModel<String> (model, "produtoPara"), 
					comboDominio.getProdutoPara());

			final DropDownChoice<String> cmbCategoria = new DropDownChoice<String>("cmbCategoria",  
					new PropertyModel<String> (model, "categoria"), categoriasService.listaCategorias());
			
			IChoiceRenderer<? super Usuario> comboCompradorRender = new IChoiceRenderer<Usuario>() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object getDisplayValue(Usuario object) {
					return object.getNomeCompleto();
				}

				@Override
				public String getIdValue(Usuario object, int index) {
					return Integer.toString(index);
				}
			};
			final DropDownChoice<Usuario> cmbComprador  = new DropDownChoice<Usuario>("cmbComprador", new  ProdutoCompradorModel(getUser(), model), compradores(), comboCompradorRender );
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
					AjaxEventBehavior event = new AjaxEventBehavior("click") {
						private static final long serialVersionUID = 1L;
						@Override
						protected void onEvent(AjaxRequestTarget target) {
							model.setCnm(dto.getCodncm());
							model.setDescricaoNcm(dto.getDescricao());

							target.add(txtNcm);
							target.add(txtDescricaoNcm);

							target.appendJavaScript("$('#consultarNcmDialog').modal('hide');");							
						}
					};
					item.add(event);					
				}
			};

			AjaxPagingNavigator apn = new AjaxPagingNavigator("navigatorNcm", dtvNcmConsulta);
			dtvNcmConsulta.setItemsPerPage(10L);

			AjaxButton btnFecharModalFamiliaConsulta = new AjaxButton("btnFecharModalNcmConsulta") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					target.appendJavaScript("$('#consultarNcmDialog').modal('hide');");
				}
			};	

			rptConsultarNcm.add(txtDescricaoNcmConsulta);
			rptConsultarNcm.add(dtvNcmConsulta, apn);
			rptConsultarNcm.add(btnPesquisarNcm);

			add(btnFecharModalFamiliaConsulta);
			add(rptConsultarNcm);
		}

		public String getNcm() {
			return ncm;
		}
		public void setNcm(String ncm) {
			this.ncm = ncm;
		}
	}

	private AtividadeCadastroProduto obtemTipoAtividade() throws CadastroProdutoException {
		String atividade = getHumanTask().getName();
		switch(atividade){

		case "Pré Cadastro Produtos" : 
			return PRE_CADASTRO_PRODUTO;
		
		case "Avaliar Dados do Produto" : 
			return APROVAR_CADASTRO_PRODUTO;
		
		case "Definir Classificação Comercial" :
			return DEFINIR_CLASSIFICAO;
		
		case "Validar Dados Logísticos" :
			return VALIDAR_DADOS_LOGISTICO;
		
		case "Corrigir Dados Fiscais com Fornecedor" :	
			return 	CORRIGIR_DADOS_FISCAIS_FORNECEDOR;
			
		case "Corrigir Dados com Fornecedor" :
			return CORRIGIR_DADOS_FORNECEDOR;
		
		case "Verificar informações" :
			return VERIFICA_INFORMACOES;
		
		case "Verificar informações pendentes  para cadastro" :
			return VERIFICA_INFORMACOES_PENDENTES;
		
		case "Cadastrar informações no consinco" :
			return AtividadeCadastroProduto.CADASTRAR_INFORMACOES_C5;
			
		case "Incluir Dados Fiscais" :
			return AtividadeCadastroProduto.INCLUIR_DADOS_FISCAIS;

		}

		throw new CadastroProdutoException("Não foi possível mapear o valor ["+ atividade+"] para uma atividade do processo de Cadastro de Produto");
	}	

	/**
	 * Retorna a função javascript que define qual será a seção/tab ativa
	 * 
	 * @return Uma String contendo a função para ativa uma determinada seção/tab
	 * @throws CadastroProdutoException
	 */
	private String javascriptSecaoAtiva(){
		String idSecao = "";
		String idTab = "";

		AtividadeCadastroProduto tipoAtividade;
		try {
			tipoAtividade = obtemTipoAtividade();
		} catch (CadastroProdutoException e) {
			throw new RuntimeException(e);
		}

		switch(tipoAtividade){
		case DEFINIR_CLASSIFICAO: 
			idSecao = "secaoPreenchimentoInterno";
			idTab = "tabPreenchimentoInterno";
			break;
		case VALIDAR_DADOS_LOGISTICO:
			idSecao = "secaoEmbalagem";
			idTab = "tabEmbalagem";
			break;			
		default:
			idSecao = "secaoDadosProduto";
			idTab = "tab-dadosProduto";			
		}

		String funcJs = "ativaSecao('idTab', 'idSecao');".replace("idTab", idTab).replace("idSecao", idSecao);

		return funcJs;
	}

	private void iniciaSecaoBotoes() throws CadastroProdutoException {

		this.secaoAprovar = new WebMarkupContainer("secaoAprovar");
		this.secaoSalvar  = new WebMarkupContainer("secaoSalvar");
		this.secaoFiscal = new WebMarkupContainer("secaoFiscal");
		secaoFiscal.setVisible(false);

		switch (tipoAtividade) {
		case PRE_CADASTRO_PRODUTO:
			this.secaoAprovar.setVisible(false);
			this.secaoSalvar.setVisible(true);
			mensagemAprovacao = ENVIA_PARA_APROVACAO;
			break;
		case APROVAR_CADASTRO_PRODUTO:
			this.secaoAprovar.setVisible(true);
			this.secaoSalvar.setVisible(false);
			mensagemAprovacao  = APROVAR_CADASTRO;
			mensagemRejeicao   = REJEITAR_CADASTRO;
			mensagemBoxAprovao = "Aprovação - Comentários";
			grpdadosproduto.setEnabled(false);
			grpembalagem.setEnabled(false);
			grppaletizacaoestocagem.setEnabled(false);
			grpdadosnutricionais.setEnabled(false);
			grpdadosfiscais.setEnabled(false);
			grppreenchimentointerno.setEnabled(false);
			break;
		case DEFINIR_CLASSIFICAO:
			this.secaoAprovar.setVisible(false);
			this.secaoSalvar.setVisible(true);
			grpdadosproduto.setEnabled(false);
			grpembalagem.setEnabled(false);
			grppaletizacaoestocagem.setEnabled(false);
			grpdadosnutricionais.setEnabled(false);
			grpdadosfiscais.setEnabled(false);
			grppreenchimentointerno.setEnabled(true);
			break;
		case VALIDAR_DADOS_LOGISTICO:
			this.secaoAprovar.setVisible(true);
			this.secaoSalvar.setVisible(false);
			mensagemAprovacao  = APROVAR_CADASTRO;
			mensagemRejeicao   = REJEITAR_CADASTRO;
			mensagemBoxAprovao = "Aprovação - Comentários";
			grpdadosproduto.setEnabled(false);
			grpembalagem.setEnabled(false);
			grppaletizacaoestocagem.setEnabled(false);
			grpdadosnutricionais.setEnabled(false);
			grpdadosfiscais.setEnabled(false);
			grppreenchimentointerno.setEnabled(false);
			break;

			//case CADASTRAR_INFORMACOES_C5:

		case VERIFICA_INFORMACOES:
			this.secaoAprovar.setVisible(true);
			this.secaoSalvar.setVisible(false);
			mensagemAprovacao  = APROVAR_CADASTRO;
			mensagemRejeicao   = REJEITAR_CADASTRO;
			grpdadosproduto.setEnabled(false);
			grpembalagem.setEnabled(false);
			grppaletizacaoestocagem.setEnabled(false);
			grpdadosnutricionais.setEnabled(false);
			grpdadosfiscais.setEnabled(false);
			grppreenchimentointerno.setEnabled(false);
			mensagemBoxAprovao = "Aprovação - Comentários";
			break;
		
		case VERIFICA_INFORMACOES_PENDENTES:
			this.secaoAprovar.setVisible(false);
			this.secaoSalvar.setVisible(true);
			mensagemAprovacao  = APROVAR_CADASTRO;
			mensagemRejeicao   = REJEITAR_CADASTRO;
			mensagemBoxAprovao = "Aprovação - Comentários";
			break;
		
		case CORRIGIR_DADOS_FISCAIS_FORNECEDOR:
			
		case CORRIGIR_DADOS_FORNECEDOR:
			this.secaoAprovar.setVisible(false);
			this.secaoSalvar.setVisible(true);
			mensagemAprovacao  = APROVAR_CADASTRO;
			mensagemRejeicao   = REJEITAR_CADASTRO;
			mensagemBoxAprovao = "Aprovação - Comentários";
			break;
		
		case INCLUIR_DADOS_FISCAIS:
			this.secaoAprovar.setVisible(false);
			this.secaoSalvar.setVisible(false);
			this.secaoFiscal.setVisible(true);
			grpdadosproduto.setEnabled(false);
			grpembalagem.setEnabled(false);
			grppaletizacaoestocagem.setEnabled(false);
			grpdadosnutricionais.setEnabled(false);
			grpdadosfiscais.setEnabled(true);
			grppreenchimentointerno.setEnabled(false);
			break;
		
		default:
			break;   
		}
	}


	private void iniciaModals() {
		this.rejeicaoModalForm = new RejeicaoModalForm("rejeicaoModalForm");
		this.salvarAlteracoesContainer = new SalvarAlteracoesContainer("salvarAlteracoesContainer");
		this.aprovacaoContainer = new AprovacaoContainer("aprovacaoContainer");
		
		this.solicitacaoCorrecaoModal = new SolicitacaoCorrecaoModal("solicitacaoCorrecaoModal");
		this.envioFiscalModal = new EnvioFiscalModal("envioFiscalModal");
		this.comentariosFiscalModal = new ComentariosFiscalModal("comentariosFiscalModal");
	}	

	private String getNomeCompletoUsuario() {
		String primeiroNome = user.getFirstname();
		String sobrenome = user.getLastname();

		if(primeiroNome == null || sobrenome == null){
			return user.getUserName();
		}		
		return  primeiroNome + " " + sobrenome;		
	}

	private void adicionaItemAoHistorico(String nome, String motivo, String comentario, String status){
		HistoricoProduto historico = new HistoricoProduto();
		historico.setNome(nome);
		historico.setMotivo(motivo);
		historico.setComentario(comentario);
		historico.setStatus(status);
		historico.setData(new Date());
		if(this.model.getHistoricoProdutos() == null) {
			this.model.setHistoricoProdutos(new ArrayList<HistoricoProduto>());
		}
		this.model.getHistoricoProdutos().add(historico);
	}

	private class SalvarAlteracoesContainer extends WebMarkupContainer {

		private static final long serialVersionUID = 1L;
		private Label usuarioSalvarCorrecao;
		private TextArea<String> comentariosAlteracoes;
		private AjaxButton salvarAlteracoesBtn;

		public SalvarAlteracoesContainer(String id) {
			super(id);
			usuarioSalvarCorrecao = new Label("usuarioSalvarCorrecao", getNomeCompletoUsuario());
			comentariosAlteracoes = new TextArea<String>("comentariosAlteracoes", Model.of(""));
			salvarAlteracoesBtn = new AjaxButton("salvarAlteracoesBtn", produtoForm){
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					try {
						validadorBean.valida(model);

						Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();
						responseVariables.put(VariaveisCadastroProduto.FORMULARIO_PRODUTO,model);

						if(tipoAtividade == PRE_CADASTRO_PRODUTO){
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAlteracoes.getDefaultModelObject(), CADASTRO_PRE_ANALISADO);
						}else if (tipoAtividade == DEFINIR_CLASSIFICAO) {
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAlteracoes.getDefaultModelObject(), CADASTRO_DEFINIR_CLASSIFICAO);
						}else if (tipoAtividade == CORRIGIR_DADOS_FORNECEDOR)  {
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAlteracoes.getDefaultModelObject(), ANALISTA_DADOS_CORRIGIDO);
						}else if (tipoAtividade == VERIFICA_INFORMACOES_PENDENTES)  {
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAlteracoes.getDefaultModelObject(), ANALISTA_DADOS_CORRIGIDO);
						}else if(tipoAtividade == CORRIGIR_DADOS_FISCAIS_FORNECEDOR){
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAlteracoes.getDefaultModelObject(), DADOS_FISCAIS_CORRIGIDOS);
						}

						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
						logger.info("Alterações do formulário foram salvas por " + user.getUserName() + " com sucesso. CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId() +	"\ndados do formulário: ");

						setResponsePage(Atividades.class);
					} 
					catch (ValidacaoBeanException validacaoBeanException) {
						setMensagensErro(validacaoBeanException.getMessages(), target);
					}	 
					catch (Exception e) {
						logger.error("Ocorreu um erro na atividade " + getHumanTask().getDisplayName() +" no momento de salvar os dados alterados pelo usuário " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
						throw new RuntimeException(e);
					}
				}				
			};	
			add(usuarioSalvarCorrecao, comentariosAlteracoes, salvarAlteracoesBtn);
		}		
	}


	private class AprovacaoContainer extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private TextArea<String> comentariosAprovacao;
		private AjaxButton enviarAprovacaoBtn;
		private Label nomeAprovador;

		public AprovacaoContainer(String id) {
			super(id);
			nomeAprovador = new Label("nomeAprovador", getNomeCompletoUsuario());
			comentariosAprovacao = new TextArea<String>("comentariosAprovacao", Model.of(""));
			Label messageConfimacao = new Label("mensagemAprovacao", mensagemAprovacao);
			Label messageTitulo     = new Label("messageTitulo", mensagemBoxAprovao); 

			enviarAprovacaoBtn = new AjaxButton("enviarAprovacaoBtn"){				
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					try {
						validadorBean.valida(model);

						AprovacaoGerenciaComercial aprovacao = new AprovacaoGerenciaComercial(getNomeCompletoUsuario(), new Date(), (String)comentariosAprovacao.getDefaultModelObject());
						model.setAprovacaoGerenciaComercial(aprovacao);

						Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();


						if (tipoAtividade.equals(APROVAR_CADASTRO_PRODUTO)) {
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAprovacao.getDefaultModelObject(), GERENTE_COMERCIAL_APROVADO);
							responseVariables.put(VariaveisCadastroProduto.STATUS_CADASTRO_PRODUTO, APROVADO);
						}else if (tipoAtividade.equals(VALIDAR_DADOS_LOGISTICO)) {
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAprovacao.getDefaultModelObject(), LOGISTICA_CADASTRO_APROVADO);
							responseVariables.put(VariaveisCadastroProduto.STATUS_LOGISTICA_PRODUTO, APROVADO);
						}else if (tipoAtividade.equals(VERIFICA_INFORMACOES)) {
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAprovacao.getDefaultModelObject(), CADASTRO_REVISADO);
							responseVariables.put(VariaveisCadastroProduto.STATUS_VERIFICA_INFORMACOES, APROVADO);
						}

						responseVariables.put(VariaveisCadastroProduto.FORMULARIO_PRODUTO,model);
						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
						setResponsePage(Atividades.class);
						logger.info("Atividade " + getHumanTask().getDisplayName() + ". O cadastro do produto foi aprovado por " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
					} catch (BonitaException e) {
						logger.error("Ocorreu um erro na atividade " + getHumanTask().getDisplayName() +" no momento da aprovação do cadastro de produto pelo usuário " + user.getUserName());
						throw new RuntimeException(e);
					} catch (ValidacaoBeanException e) {
						target.appendJavaScript("$('#confirmacaoAprovacaoDialog').modal('hide')");
						setMensagensErro(e.getMessages(), target);
					}					

				}
			};

			add(nomeAprovador, comentariosAprovacao, enviarAprovacaoBtn,messageConfimacao,messageTitulo);
		}		
	}

	private class RejeicaoModalForm extends Form<Void>{
		private static final long serialVersionUID = 1L;

		private Label nomeAprovadorRejeicao;
		private TextArea<String> comentariosRejeicao;
		private AjaxButton enviarRejeicaoBtn;
		DropDownChoice<String> motivosRejeicao;
		private String motivoRejeicaoSelecionado = "";

		public RejeicaoModalForm(String id) {
			super(id);
			nomeAprovadorRejeicao = new Label("nomeAprovadorRejeicao", getNomeCompletoUsuario());
			comentariosRejeicao = new TextArea<String>("comentariosRejeicao", Model.of(""));
			motivosRejeicao = new DropDownChoice<String>("motivosRejeicao", Model.of(motivoRejeicaoSelecionado),MotivoRejeicao.getMotivosRejeicao());
			enviarRejeicaoBtn = new AjaxButton("enviarRejeicaoBtn", this){		
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

					Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();

					if (tipoAtividade.equals(APROVAR_CADASTRO_PRODUTO)) {
						adicionaItemAoHistorico(getNomeCompletoUsuario(), motivoRejeicaoSelecionado, (String)comentariosRejeicao.getDefaultModelObject(), GERENTE_COMERCIAL_REJEITADO);
						responseVariables.put(VariaveisCadastroProduto.STATUS_CADASTRO_PRODUTO, REJEITADO);
					}else if (tipoAtividade.equals(VALIDAR_DADOS_LOGISTICO)) {
						adicionaItemAoHistorico(getNomeCompletoUsuario(), motivoRejeicaoSelecionado, (String)comentariosRejeicao.getDefaultModelObject(), LOGISTICA_CADASTRO_REJEITADO);
						responseVariables.put(VariaveisCadastroProduto.STATUS_LOGISTICA_PRODUTO, REJEITADO);
					}else if (tipoAtividade.equals(VERIFICA_INFORMACOES)) {
						adicionaItemAoHistorico(getNomeCompletoUsuario(), motivoRejeicaoSelecionado, (String)comentariosRejeicao.getDefaultModelObject(), CADASTRO_SOLICITA_CORRECAO);
						responseVariables.put(VariaveisCadastroProduto.STATUS_VERIFICA_INFORMACOES, REJEITADO);
					}

					responseVariables.put(VariaveisCadastroProduto.FORMULARIO_PRODUTO,model);

					try {
						logger.info("Atividade " + getHumanTask().getDisplayName() + ". O cadastro do Produto foi rejeitado por " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
						logger.info("Formulário Produto: " + model);
						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
					} catch (BonitaException e) {
						logger.error("Ocorreu um erro na atividade " + getHumanTask().getDisplayName() +"no momento da rejeição do cadastro de produto pelo usuário " + user.getUserName());
						throw new RuntimeException(e);
					}
					setResponsePage(Atividades.class);
				}
			};			
			add(nomeAprovadorRejeicao, motivosRejeicao, comentariosRejeicao, enviarRejeicaoBtn);			
		}

	}
	
	private class SolicitacaoCorrecaoModal extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private AjaxButton solicitCorrecaoBtn;
		
		public SolicitacaoCorrecaoModal(String id) {
			super(id);
			
			this.solicitCorrecaoBtn = new AjaxButton("solicitCorrecaoBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					adicionaItemAoHistorico(getNomeCompletoUsuario(), null, comentariosFiscalModal.getComentarios(), FISCAL_SOLICITA_CORRECAO);
					Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();					
					responseVariables.put(VariaveisCadastroProduto.STATUS_FISCAL, ENVIADO_PARA_CORRRECAO);
					responseVariables.put(VariaveisCadastroProduto.FORMULARIO_PRODUTO, model);
					try {
						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
						setResponsePage(Atividades.class);
					} catch (BonitaException e) {
						throw new RuntimeException(e);
					}
				}
			};
			
			add(solicitCorrecaoBtn);
			
		}		
	}
	
	private class EnvioFiscalModal extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private AjaxButton envioFiscalOkBtn;
		
		public EnvioFiscalModal(String id) {
			super(id);
			
			this.envioFiscalOkBtn = new AjaxButton("envioFiscalOkBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form)  {
					adicionaItemAoHistorico(getNomeCompletoUsuario(), null, comentariosFiscalModal.getComentarios(), FISCAL_CADASTRO_APROVADO);
					Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();					
					responseVariables.put(VariaveisCadastroProduto.STATUS_FISCAL, APROVADO);
					responseVariables.put(VariaveisCadastroProduto.FORMULARIO_PRODUTO,model);
					try {
						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
						setResponsePage(Atividades.class);
					} catch (BonitaException e) {
						throw new RuntimeException(e);
					}
				}
			};
			
			add(envioFiscalOkBtn);
		}
		
	}
	
	private class ComentariosFiscalModal extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private TextArea<String> comentariosFiscalTxt;
		
		public ComentariosFiscalModal(String id) {
			super(id);
			this.comentariosFiscalTxt = new TextArea<String>("comentariosFiscalTxt", Model.of(""));
			
			add(comentariosFiscalTxt);
		}
		
		public String getComentarios(){
			return this.comentariosFiscalTxt.getDefaultModelObjectAsString();
		}
		
	}
	
	private final class CompradorModel implements IModel<Usuario> {
		private static final long serialVersionUID = 1L;

		@Override
		public void detach() {
			
		}

		@Override
		public Usuario getObject() {
			try {
				return dadosUsuarioService.recuperaUsuario(user, model.getIdComprador());
			} catch (BonitaException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void setObject(Usuario usuario) {
			Contatos contatos;
			try {
				contatos = dadosUsuarioService.recuperaContatosProfissionais(getUser(), usuario.getId());
			} catch (BonitaException e) {
				throw new RuntimeException(e);
			}
			model.setComprador(usuario.getNomeUsuario());
			model.setNomeComprador(usuario.getNomeCompleto());
			model.setIdComprador(usuario.getId());					
			String email = contatos != null ? contatos.getEmail() : null;
			model.setEmailComprador(email);
			
		}
	}


	public List<Usuario> compradores() throws BonitaException {
		return dadosUsuarioService.recuperaUsuariosPorGrupo("Comercial");
	}
	
	public List<String> campradoreS() throws BonitaException {
		List<String> strings = new ArrayList<String>();
		List<org.bonitasoft.engine.identity.User> users = groupService.findUserForGroups("Comercial",0,3000);
		for (org.bonitasoft.engine.identity.User dto : users) {
			strings.add(dto.getUserName());
		}
		return strings;
	}
	
	
	public String getCompradorEmail(String userName) throws BonitaException {
		String email = userService.findUserById(model.getComprador()).getPersonnal_data_email();
		return email;
	}


}