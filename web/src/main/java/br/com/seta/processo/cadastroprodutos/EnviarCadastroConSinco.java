package br.com.seta.processo.cadastroprodutos;

import static br.com.seta.processo.constant.VariaveisCadastroProduto.CADASTRO_PRODUTO;
import static br.com.seta.processo.constant.VariaveisCadastroProduto.FORMULARIO_PRODUTO;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;
import org.jboss.logging.Logger;

import br.com.seta.processo.bean.Categorias;
import br.com.seta.processo.bean.Classificacoes;
import br.com.seta.processo.bean.GePessoas;
import br.com.seta.processo.bean.MafFonecedores;
import br.com.seta.processo.bean.MapEmbalagens;
import br.com.seta.processo.cadastrofornecedores.dominios.SimNao;
import br.com.seta.processo.cadastroprodutos.dominios.ComboDominio;
import br.com.seta.processo.constant.VariaveisCadastroProduto;
import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.MafFornecedor;
import br.com.seta.processo.dto.MapEmbalagem;
import br.com.seta.processo.dto.MapFamilia;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.page.TaskPage;
import br.com.seta.processo.provider.MafFornecedorProvider;
import br.com.seta.processo.provider.MapFamiliaProvider;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.service.interfaces.MapFamiliaService;
import br.com.seta.processo.service.interfaces.MapProdutoService;
import br.com.seta.widgetswicketprocesso.textfields.moneytextfield.MoneyTextField;

public class EnviarCadastroConSinco extends TaskPage {


	private static final long serialVersionUID = 1L;

	private transient User user = (User) Session.get().getAttribute("user");

	@Inject	private Logger logger;
	@Inject	private ExecuteRestAPI restApi;
	@Inject private MapEmbalagens mapEmbalagens;
	@Inject private MapFamiliaService familiaService;
	@Inject private MafFonecedores mafFonecedores;
	@Inject private MapProdutoService mapProdutoService;
	@Inject private Categorias categoriasService;
	@Inject private GroupService groupService;
	@Inject private Classificacoes classificacoesService;
	@Inject private GePessoas gePessoas;

	private FormularioProduto model = new FormularioProduto();
	private CadastroProduto cadastroProduto = new CadastroProduto();
	private ComboDominio comboDominio  = new ComboDominio();
	private List<MapEmbalagem> embalagensFarmilia = new ArrayList<MapEmbalagem>();
	private List<MafFornecedor> fornecedoresFarmilia = new ArrayList<MafFornecedor>();
	private MapFamilia modelFamilia = new MapFamilia();
	private MafFornecedorProvider fornecedorProvider = new MafFornecedorProvider();
	private MapFamiliaProvider familiaProvider;

	private GrpEmbalagem grpEmbalagem;
	private CadastrarFamiliaDialog cadastrarFamiliaDialog;
	private ConfirmExclusaoFornecDialog confirmExclusaoFornecDialog;
	private WebMarkupContainer rptFornecedorFamilia;
	private WebMarkupContainer rptEmbalagensFamilia;
	private TextField<String> txtIdFamiliaDadosProduto;
	private TextField<String> txtDescFamiliaDadosProduto;
	private Label nroProdutoConsincoLabel;
	private AjaxButton enviarConsincoBtn;	
	private BigDecimal seqfornecedor;

	public EnviarCadastroConSinco(PageParameters pageParameters) throws ClientProtocolException,	IOException, ParseException, InstantiationException, IllegalAccessException, HttpStatusException, BonitaException {
		super(pageParameters);

		this.model= (FormularioProduto) restApi.retriveVariableTask(user, this.getTaskId(), FormularioProduto.class, FORMULARIO_PRODUTO);
		this.cadastroProduto = (CadastroProduto) restApi.retriveVariableTask(user, this.getTaskId(), CadastroProduto.class, CADASTRO_PRODUTO);
		this.seqfornecedor   = new BigDecimal(model.getSeqfornecedor());
		familiaProvider = new MapFamiliaProvider(this.seqfornecedor);

		if(model.getSeqfornecedor()!=null){
			MafFornecedor fornecedor = mafFonecedores.find(new BigDecimal(model.getSeqfornecedor()));
			if(fornecedor != null)
				fornecedoresFarmilia.add(fornecedor);
		}

		add(new CadastroProdutoForm("form"));
		add(new enviaForm("enviaCadastroForm"));
	}

	private final class CadastroProdutoForm extends Form<Object> {
		private static final long serialVersionUID = 1L;

		public CadastroProdutoForm(String id) throws BonitaException {
			super(id);

			enviarConsincoBtn = new AjaxButton("enviarConsincoBtn") {
				private static final long serialVersionUID = 1L; 
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					try {
						if(model.getIdFamilia()!=null) { 							

							BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
							beanUtilsBean.getConvertUtils().register(false, false, 0);
							String seqProduto = mapProdutoService.create((FormularioProduto)beanUtilsBean.cloneBean(model));
							nroProdutoConsincoLabel.setDefaultModelObject(seqProduto);
                            
							/****
							 *  Verifica origem do cadastro se  Portal. vem carimbanco getIsPrecadastro(true)
							 * 
							 */
							if (cadastroProduto.getIsPrecadastro()==null) {
								try {
									GePessoaEntity gePessoaEntity = gePessoas.buscaGePessoaPorFornecedorByID(new BigDecimal(model.getSeqfornecedor()));
									model.setIdentificador(gePessoaEntity.getNrocgccpf().toString()+gePessoaEntity.getDigcgccpf());
								}catch (Exception e) {
									e.printStackTrace();
								}
							}

							Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();
							responseVariables.put(VariaveisCadastroProduto.FORMULARIO_PRODUTO,model);
							restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);

							target.add(nroProdutoConsincoLabel);
							ocultaCarregamento(target);
							target.appendJavaScript("$('#sucessoCriacaoFornecedorModal').modal('show')");
						}else {
							ocultaCarregamento(target);
							setMensagemErro("E necessario cadastrar uma família !", target);
						}
					} catch (Exception e) {
						ocultaCarregamento(target);
						setMensagemErro(e.getMessage(), target);
						logger.error(e);
					}
				}			
			};

			grpEmbalagem = (GrpEmbalagem) new	GrpEmbalagem("grpEmbalagem").setEnabled(false).setOutputMarkupId(true);
			GrpDadosProduto grpDadosProduto = (GrpDadosProduto) new GrpDadosProduto("grpDadosProduto");
			GrpPaletizacaoEstocagem grpPaletizacaoEstocagem = (GrpPaletizacaoEstocagem) new	GrpPaletizacaoEstocagem("grpPaletizacaoEstocagem").setEnabled(false);
			GrpDadosNutricionais grpDadosNutricionais = (GrpDadosNutricionais) new	GrpDadosNutricionais("grpDadosNutricionais").setEnabled(false);
			GrpDadosFiscais grpDadosFiscais = (GrpDadosFiscais) new GrpDadosFiscais("grpDadosFiscais").setEnabled(false);
			GrpPreenchimentoInterno grpPreenchimentoInterno = (GrpPreenchimentoInterno) new GrpPreenchimentoInterno("grpPreenchimentoInterno").setEnabled(false);

			cadastrarFamiliaDialog = new CadastrarFamiliaDialog("cadastrarFamiliaDialog");
			cadastrarFamiliaDialog.setOutputMarkupId(true);

			confirmExclusaoFornecDialog = new ConfirmExclusaoFornecDialog("confirmExclusaoFornecDialog");
			confirmExclusaoFornecDialog.setOutputMarkupId(true);

			add(enviarConsincoBtn);
			add(grpDadosProduto); 
			add(grpEmbalagem); 
			add(grpPaletizacaoEstocagem); 
			add(grpDadosNutricionais);
			add(grpDadosFiscais);
			add(grpPreenchimentoInterno);

			//modals
			add(cadastrarFamiliaDialog, confirmExclusaoFornecDialog);
			add( new ConsultarFornecedorDialog("consultarFornecedorDialog"));
			add( new ConsultarFamiliaDialog("consultarFamiliaDialog"));
		}
	}

	private final class GrpDadosProduto extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public GrpDadosProduto(String id) {
			super(id);			

			AjaxButton btnModalFamilia = new AjaxButton("btnModalFamilia") {
				private static final long serialVersionUID = 1L;
				@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form){

					if(embalagensFarmilia.size() > 0)
						embalagensFarmilia.clear();

					//UNIDADE
					if((model.getQuantidadeUnidade() != null && !model.getQuantidadeUnidade().isEmpty())
							&& (model.getEanUnidade() != null && !model.getEanUnidade().isEmpty())
							&& (model.getEmbalagemUnidade() != null && !model.getEmbalagemUnidade().isEmpty())){
						MapEmbalagem emb = new MapEmbalagem();

						emb.setTipo("Unidade");
						emb.setQtdembalagem(new BigDecimal(model.getQuantidadeUnidade()));
						emb.setEmbalagem(model.getEmbalagemUnidade());
						emb.setAltura(new BigDecimal(model.getAlturaUnidade() != null ? model.getAlturaUnidade() : "0" ));
						emb.setProfundidade(new BigDecimal(model.getComprimentoUnidade() != null ? model.getComprimentoUnidade() : "0" ));
						emb.setLargura(new BigDecimal(model.getLarguraUnidade() != null ? model.getLarguraUnidade() : "0" ));
						emb.setPesobruto(new BigDecimal(model.getPesoBrutoUnidade() != null ? model.getPesoBrutoUnidade() : "0" ));
						emb.setPesoliquido(new BigDecimal(model.getPesoLiqUnidade() != null ? model.getPesoLiqUnidade() : "0" ));

						embalagensFarmilia.add(emb);
					}

					//DISPLAY
					if((model.getQuantidadeDisplay() != null && !model.getQuantidadeDisplay().isEmpty())
							&& (model.getEanDisplay() != null && !model.getEanDisplay().isEmpty())
							&& (model.getEmbalagemDisplay() != null && !model.getEmbalagemDisplay().isEmpty())){

						MapEmbalagem emb = new MapEmbalagem();

						emb.setTipo("Display");
						emb.setQtdembalagem(new BigDecimal(model.getQuantidadeDisplay() != null ? model.getQuantidadeDisplay() : "0" ));
						emb.setEmbalagem(model.getEmbalagemDisplay() != null ? model.getEmbalagemDisplay() : "0" );
						emb.setAltura(new BigDecimal(model.getAlturaDisplay() != null ? model.getAlturaDisplay() : "0" ));
						emb.setProfundidade(new BigDecimal(model.getComprimentoDisplay() != null ? model.getComprimentoDisplay() : "0" ));
						emb.setLargura(new BigDecimal(model.getLarguraDisplay() != null ? model.getLarguraDisplay() : "0" ));
						emb.setPesobruto(new BigDecimal(model.getPesoBrutoDisplay() != null ? model.getPesoBrutoDisplay() : "0" ));
						emb.setPesoliquido(new BigDecimal(model.getPesoLiqDisplay() != null ? model.getPesoLiqDisplay() : "0" ));

						embalagensFarmilia.add(emb);
					}

					//MASTER
					if((model.getQuantidadeEmbalagem() != null && !model.getQuantidadeEmbalagem().isEmpty())
							&& (model.getEanEmbalagem() != null && !model.getEanEmbalagem().isEmpty())
							&& (model.getEmbalagemEmbalagem() != null && !model.getEmbalagemEmbalagem().isEmpty())){

						MapEmbalagem emb = new MapEmbalagem();

						emb.setTipo("Master");
						emb.setQtdembalagem(new BigDecimal(model.getQuantidadeEmbalagem() != null ? model.getQuantidadeEmbalagem() : "0" ));
						emb.setEmbalagem(model.getEmbalagemEmbalagem() != null ? model.getEmbalagemEmbalagem() : "0" );
						emb.setAltura(new BigDecimal(model.getAlturaEmbalagem() != null ? model.getAlturaEmbalagem() : "0" ));
						emb.setProfundidade(new BigDecimal(model.getComprimentoEmbalagem() != null ? model.getComprimentoEmbalagem() : "0" ));
						emb.setLargura(new BigDecimal(model.getLarguraEmbalagem() != null ? model.getLarguraEmbalagem() : "0" ));
						emb.setPesobruto(new BigDecimal(model.getPesoBrutoEmbalagem() != null ? model.getPesoBrutoEmbalagem() : "0" ));
						emb.setPesoliquido(new BigDecimal("0"));

						embalagensFarmilia.add(emb);
					}

					target.add(rptEmbalagensFamilia);

					target.appendJavaScript("$('#cadastrarFamiliaDialog').modal('show');");
				}
			};

			AjaxButton btnPesquisarFamilia = new AjaxButton("btnPesquisarFamilia") {
				private static final long serialVersionUID = 1L;
				@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					target.appendJavaScript("$('#consultarFamiliaDialog').modal('show');");
				}
			};

			txtIdFamiliaDadosProduto = new TextField<String>("txtIdFamiliaDadosProduto",	new PropertyModel<String>(model, "idFamilia"));
			txtIdFamiliaDadosProduto.setOutputMarkupId(true).setEnabled(false);
			txtDescFamiliaDadosProduto = new TextField<String>("txtDescFamiliaDadosProduto",	new PropertyModel<String>(model, "descFamilia"));
			txtDescFamiliaDadosProduto.setOutputMarkupId(true).setEnabled(false);
			final TextField<String> txtDescCompleta = new TextField<String>("txtDescCompleta",	new PropertyModel<String>(model, "descCompleta"));
			txtDescCompleta.setEnabled(false);
			final TextField<String> txtCodProduto 	= new TextField<String>("txtCodProduto", 	new PropertyModel<String>(model, "codProduto"));
			txtCodProduto.setEnabled(false);
			final TextField<String> txtDescReduzida = new TextField<String>("txtDescReduzida",	new PropertyModel<String>(model, "descReduzida"));
			txtDescReduzida.setEnabled(false);
			final TextField<String> txtPaisOrigem 	= new TextField<String>("txtPaisOrigem", 	new PropertyModel<String>(model, "paisOrigem"));
			txtPaisOrigem.setEnabled(false);

			final DropDownChoice<String> cmbOrigemProduto = new DropDownChoice<String>("cmbOrigemProduto", 
					new PropertyModel<String> (model, "origemProduto"), 
					mapEmbalagens.findOrigemProduto());		
			cmbOrigemProduto.setEnabled(false);

			RadioGroup<String> rdgSimplesNacional = new RadioGroup<String>("rdgSimplesNacional", new PropertyModel<String>(model, "simplesNacional"));
			rdgSimplesNacional.add(new Radio<String>("rdbCertificadoNacional_0", new Model<String>(SimNao.getValores().get(0))));
			rdgSimplesNacional.add(new Radio<String>("rdbCertificadoNacional_1", new Model<String>(SimNao.getValores().get(1))));
			rdgSimplesNacional.setEnabled(false);

			add(txtIdFamiliaDadosProduto, txtDescFamiliaDadosProduto, txtDescCompleta, txtCodProduto, txtDescReduzida, txtPaisOrigem);
			add(cmbOrigemProduto);
			add(rdgSimplesNacional);
			add(btnModalFamilia, btnPesquisarFamilia);
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

			final TextField<String>	txtNcm				= new TextField<String>("txtNcm", 			new PropertyModel<String>(model,"cnm"));
			final TextField<String>	txtDescricaoNcm		= new TextField<String>("txtDescricaoNcm", 	new PropertyModel<String>(model,"descricaoNcm"));
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

			final DropDownChoice<String> cmbComprador = new DropDownChoice<String>("cmbComprador",  new PropertyModel<String> (model, "comprador"), campradoreS());

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
					new PropertyModel<String> (model, "grupo"), 
					classificacoesComercial);

			final DropDownChoice<String> cmbVarejo = new DropDownChoice<String>("cmbVarejo",  
					new PropertyModel<String> (model, "varejo"), 
					classificacoesComercial);


			add(cmbProdutoPara, cmbCategoria, cmbComprador, cmbAtacado, cmbTelemarketing, cmbGrupo, cmbVarejo);
			add(cmbDivisao);
		}

	} 


	//MODAIS

	private final class CadastrarFamiliaDialog extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		public CadastrarFamiliaDialog(String id) {
			super(id);

			//FAMILIA
			final WebMarkupContainer grpFamiliaModalFamilia = new WebMarkupContainer("grpFamiliaModalFamilia");
			grpFamiliaModalFamilia.setOutputMarkupId(true);

			TextField<BigDecimal> txtIdFamilia = new TextField<BigDecimal>("txtIdFamilia", new PropertyModel<BigDecimal>(modelFamilia, "seqfamilia"));
			TextField<String> txtDescricaoFamilia = new TextField<String>("txtDescricaoFamilia", new PropertyModel<String>(modelFamilia, "familia"));

			CheckBox chkVasilhame = new CheckBox("chkVasilhame", new PropertyModel<Boolean>(modelFamilia, "vasilhame"));
			CheckBox chkPesavel = new CheckBox("chkPesavel", new PropertyModel<Boolean>(modelFamilia, "pesavelchk"));
			CheckBox chkPermDecimais = new CheckBox("chkPermDecimais", new PropertyModel<Boolean>(modelFamilia, "permiteDecimais"));
			CheckBox chkPermMultiplicacao = new CheckBox("chkPermMultiplicacao", new PropertyModel<Boolean>(modelFamilia, "permiteMultiplicacao"));
			CheckBox chkBebidaAlcoolica = new CheckBox("chkBebidaAlcoolica", new PropertyModel<Boolean>(modelFamilia, "bebidaAlcoolica"));

			grpFamiliaModalFamilia.add(txtIdFamilia, txtDescricaoFamilia);
			grpFamiliaModalFamilia.add(chkVasilhame, chkPesavel, chkPermDecimais, chkPermMultiplicacao, chkBebidaAlcoolica);

			//FORNECEDORES
			AjaxButton btnIncluirFornecedor = new AjaxButton("btnIncluirFornecedor") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					target.appendJavaScript("$('#cadastrarFamiliaDialog').modal('hide');");
					target.appendJavaScript("$('#consultarFornecedorDialog').modal('show');");
				}
			};

			DataView<MafFornecedor> dtvFornecedor = new DataView<MafFornecedor>("dtvFornecedor", new ListDataProvider<MafFornecedor>(fornecedoresFarmilia)) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<MafFornecedor> item) {
					final MafFornecedor fornec = (MafFornecedor) item.getModelObject();

					item.add(new Label("lblCodigo", fornec.getSeqfornecedor()));
					item.add(new Label("lblFornecedor", fornec.getGePessoa().getNomerazao()));
					item.add(new Label("lblUf", fornec.getGePessoa().getUf()));
					item.add(new CheckBox("chkPrincipal", new PropertyModel<Boolean>(fornec, "princicalchk")));

					AjaxLink<String> btnRemoverFornecedor = new AjaxLink<String>("btnRemoverFornecedor") {
						private static final long serialVersionUID = 1L;
						@Override
						public void onClick(AjaxRequestTarget target) {
							confirmExclusaoFornecDialog.selcionaFornecedorParaRemocao(fornec);
							target.appendJavaScript("$('#cadastrarFamiliaDialog').modal('hide');");
							target.appendJavaScript("$('#confirmExclusaoFornec').modal('show');");
						}
					};

					item.add(btnRemoverFornecedor);
				}
			};

			rptFornecedorFamilia = new WebMarkupContainer("rptFornecedorFamilia");
			rptFornecedorFamilia.setOutputMarkupId(true);
			rptFornecedorFamilia.add(dtvFornecedor, btnIncluirFornecedor);

			//EMBALAGENS
			rptEmbalagensFamilia = new WebMarkupContainer("rptEmbalagensFamilia");
			rptEmbalagensFamilia.setOutputMarkupId(true);

			DataView<MapEmbalagem> dtvEmbalagem = new DataView<MapEmbalagem>("dtvEmbalagem", new ListDataProvider<MapEmbalagem>(embalagensFarmilia)) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<MapEmbalagem> item) {
					MapEmbalagem emb = (MapEmbalagem) item.getModelObject();
					item.add(new Label("lblTipo", emb.getTipo()));
					item.add(new Label("lblQtd", emb.getQtdembalagem()));
					item.add(new Label("lblEmbalagem", emb.getEmbalagem()));
					item.add(new Label("lblPesoBruto", emb.getPesobruto()));
					item.add(new Label("lblPesoLiq", emb.getPesoliquido()));
					item.add(new Label("lblAltura", emb.getAltura()));
					item.add(new Label("lblLargura", emb.getLargura()));
					item.add(new Label("lblProfundidade", emb.getProfundidade()));
				}
			};

			rptEmbalagensFamilia.add(dtvEmbalagem);

			final AjaxButton btnEnviarFamiliaAcrux = new AjaxButton("btnEnviarFamiliaAcrux") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){

					List<String> erros = new ArrayList<String>();

					if(modelFamilia.getFamilia() == null || (modelFamilia.getFamilia() != null && modelFamilia.getFamilia().isEmpty())){
						erros.add("A Descrição é um campo obrigatório.");
					}

					if(fornecedoresFarmilia.size() == 0) {
						erros.add("É necessario adicionar ao menos um fornecedor.");
					}

					if(embalagensFarmilia.size() == 0) {
						erros.add("É necessario preencher os dados de pelo menos uma embalagem no formulario do produto.");
					}

					if(erros.size() == 0) {
						modelFamilia.setFornecedores(fornecedoresFarmilia);
						modelFamilia.setEmbalagens(embalagensFarmilia);

						if(familiaService.save(modelFamilia)){
							model.setIdFamilia(modelFamilia.getSeqfamilia().longValue()+"");
							model.setDescFamilia(modelFamilia.getFamilia().toUpperCase());

							rptEmbalagensFamilia.setEnabled(false);
							rptFornecedorFamilia.setEnabled(false);
							grpFamiliaModalFamilia.setEnabled(false);
							this.setEnabled(false);

							target.add(txtIdFamiliaDadosProduto, txtDescFamiliaDadosProduto);
							target.add(rptEmbalagensFamilia, rptFornecedorFamilia, grpFamiliaModalFamilia);
							target.add(this);

							target.appendJavaScript("$('#cadastrarFamiliaDialog').modal('hide');");
							target.appendJavaScript("$('#idFamiliaConfirmacao').text('"+model.getIdFamilia()+"');");
							target.appendJavaScript("$('#modalMensagemConfirmacaoFamilia').modal('show');");
						}
					} else {
						setMensagensErro(erros, target);
					}
				}
			};

			add(rptFornecedorFamilia, rptEmbalagensFamilia, grpFamiliaModalFamilia);
			add(btnEnviarFamiliaAcrux);

		}
	}

	private class ConfirmExclusaoFornecDialog extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		private AjaxButton removerFornecBtn;
		private MafFornecedor fornecedorSelecionado;

		public ConfirmExclusaoFornecDialog(String id) {
			super(id);

			this.removerFornecBtn = new AjaxButton("removerFornecBtn") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					if(fornecedorSelecionado == null) return;

					fornecedoresFarmilia.remove(fornecedorSelecionado);
					target.add(rptFornecedorFamilia);
					target.appendJavaScript("$('#confirmExclusaoFornec').modal('hide');");
					target.appendJavaScript("$('#cadastrarFamiliaDialog').modal('show');");

				}

			};

			add(removerFornecBtn);
		}

		public void selcionaFornecedorParaRemocao(MafFornecedor fornecedor){
			this.fornecedorSelecionado = fornecedor;
		}

	}

	@SuppressWarnings("unused")
	private final class ConsultarFornecedorDialog extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		private BigDecimal idFornecedor;
		private String razao;
		public ConsultarFornecedorDialog(String id) {
			super(id);
			final WebMarkupContainer rptConsultarFornecedor = new WebMarkupContainer("rptConsultarFornecedor");
			rptConsultarFornecedor.setOutputMarkupId(true);

			TextField<BigDecimal> txtIdFornecedor = new TextField<BigDecimal>("txtIdFornecedor", new PropertyModel<BigDecimal>(this, "idFornecedor"));
			TextField<String> txtRazaoSocial = new TextField<String>("txtRazaoSocial", new PropertyModel<String>(this, "razao"));

			AjaxButton btnPesquisarFornecedor = new AjaxButton("btnPesquisarFornecedor") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					fornecedorProvider.putParameter(razao, idFornecedor);
					target.add(rptConsultarFornecedor);
				}
			};

			DataView<MafFornecedor> dtvFornecedorConsulta = new DataView<MafFornecedor>("dtvFornecedorConsulta", fornecedorProvider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<MafFornecedor> item) {
					final MafFornecedor fornec = (MafFornecedor) item.getModelObject();

					item.add(new Label("lblCodigoFornecedorConsulta", fornec.getSeqfornecedor().longValue()));
					item.add(new Label("lblRazaoFornecedorConsulta", fornec.getGePessoa().getNomerazao()));
					item.add(new Label("lblFantasiaFornecedorConsulta", fornec.getGePessoa().getFantasia()));
					item.add(new Label("lblCidadeFornecedorConsulta", fornec.getGePessoa().getCidade()));

					AjaxEventBehavior event = new AjaxEventBehavior("click") {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onEvent(AjaxRequestTarget target) {
							fornecedoresFarmilia.add(fornec);
							target.add(rptFornecedorFamilia);
							target.appendJavaScript("$('#consultarFornecedorDialog').modal('hide');");
							target.appendJavaScript("$('#cadastrarFamiliaDialog').modal('show');");
						}
					};
					item.add(event);
				}
			};

			AjaxPagingNavigator apn = new AjaxPagingNavigator("navigator", dtvFornecedorConsulta);
			dtvFornecedorConsulta.setItemsPerPage(10L);

			AjaxButton btnFecharModalFornecedor = new AjaxButton("btnFecharModalFornecedor") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					target.appendJavaScript("$('#consultarFornecedorDialog').modal('hide');");
					target.appendJavaScript("$('#cadastrarFamiliaDialog').modal('show');");
				}
			};



			rptConsultarFornecedor.add(txtIdFornecedor, txtRazaoSocial);
			rptConsultarFornecedor.add(dtvFornecedorConsulta, apn);
			rptConsultarFornecedor.add(btnPesquisarFornecedor);

			add(btnFecharModalFornecedor);
			add(rptConsultarFornecedor);
		}
		public BigDecimal getIdFornecedor() {
			return idFornecedor;
		}
		public void setIdFornecedor(BigDecimal idFornecedor) {
			this.idFornecedor = idFornecedor;
		}
		public String getRazao() {
			return razao;
		}
		public void setRazao(String razao) {
			this.razao = razao;
		}

	}



	private class enviaForm extends Form<Void>{	
		private static final long serialVersionUID = 1L;

		private AjaxButton finalizarCadastroBtn;

		public enviaForm(String id) {
			super(id);
			nroProdutoConsincoLabel = (Label) new Label("nroProdutoConsincoLabel", Model.of("")).setOutputMarkupId(true);
			this.finalizarCadastroBtn = new AjaxButton("finalizarCadastroBtn") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					setResponsePage(Atividades.class);
				}
			};

			add(nroProdutoConsincoLabel, finalizarCadastroBtn);
		}		
	}


	@SuppressWarnings("unused")
	private final class ConsultarFamiliaDialog extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		private BigDecimal idFamilia;
		private String familia;
		public ConsultarFamiliaDialog(String id) {
			super(id);
			final WebMarkupContainer rptConsultarFamilia = new WebMarkupContainer("rptConsultarFamilia");
			rptConsultarFamilia.setOutputMarkupId(true);

			TextField<BigDecimal> txtIdFamilia = new TextField<BigDecimal>("txtIdFamilia", new PropertyModel<BigDecimal>(this, "idFamilia"));
			TextField<String> txtFamilia = new TextField<String>("txtFamilia", new PropertyModel<String>(this, "familia"));

			AjaxButton btnPesquisarFamilia = new AjaxButton("btnPesquisarFamilia") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					familiaProvider.putParameter(familia, idFamilia, seqfornecedor);
					target.add(rptConsultarFamilia);
				}
			};
			
		
			DataView<MapFamilia> dtvFamiliaConsulta = new DataView<MapFamilia>("dtvFamiliaConsulta", familiaProvider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<MapFamilia> item) {
					final MapFamilia fam = (MapFamilia) item.getModelObject();

					item.add(new Label("lblCodigoFamiliaConsulta", fam.getSeqfamilia()));
					item.add(new Label("lblFamiliaConsulta", fam.getFamilia()));

					AjaxEventBehavior event = new AjaxEventBehavior("click") {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onEvent(AjaxRequestTarget target) {
							model.setDescFamilia(fam.getFamilia().toUpperCase());
							model.setIdFamilia(fam.getSeqfamilia().longValue()+"");

							target.add(txtIdFamiliaDadosProduto);
							target.add(txtDescFamiliaDadosProduto);

							target.appendJavaScript("$('#consultarFamiliaDialog').modal('hide');");
						}
					};
					item.add(event);
				}
			};

			AjaxPagingNavigator apn = new AjaxPagingNavigator("navigatorFamilia", dtvFamiliaConsulta);
			dtvFamiliaConsulta.setItemsPerPage(10L);

			AjaxButton btnFecharModalFamiliaConsulta = new AjaxButton("btnFecharModalFamiliaConsulta") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					target.appendJavaScript("$('#consultarFamiliaDialog').modal('hide');");
				}
			};	


			rptConsultarFamilia.add(txtIdFamilia, txtFamilia);
			rptConsultarFamilia.add(dtvFamiliaConsulta, apn);
			rptConsultarFamilia.add(btnPesquisarFamilia);

			add(btnFecharModalFamiliaConsulta);
			add(rptConsultarFamilia);
		}


		public BigDecimal getIdFamilia() {
			return idFamilia;
		}
		public void setIdFamilia(BigDecimal idFamilia) {
			this.idFamilia = idFamilia;
		}
		public String getFamilia() {
			return familia;
		}
		public void setFamilia(String familia) {
			this.familia = familia;
		}

	}


	public List<String> campradoreS() throws BonitaException {
		List<String> strings = new ArrayList<String>();
		List<org.bonitasoft.engine.identity.User> users = groupService.findUserForGroups("Comercial",0,3000);
		for (org.bonitasoft.engine.identity.User dto : users) {
			strings.add(dto.getUserName());
		}
		return strings;
	}


}