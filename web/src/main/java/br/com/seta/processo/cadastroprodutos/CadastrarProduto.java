package br.com.seta.processo.cadastroprodutos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
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
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.cadastrofornecedores.dominios.SimNao;
import br.com.seta.processo.cadastroprodutos.dominios.ComboDominio;
import br.com.seta.processo.constant.VariaveisCadastroProduto;
import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.MapNcmDto;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.provider.GePessoaFornecedorProvider;
import br.com.seta.processo.provider.MapNcmProvider;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.service.interfaces.AcruxConsultasService;
import br.com.seta.processo.validacao.ValidadorBean;
import br.com.seta.widgetswicketprocesso.textfields.moneytextfield.MoneyTextField;

public class CadastrarProduto extends BonitaPage {

	private static final long serialVersionUID = 1L;
	
	private FormularioProduto formularioProduto = new FormularioProduto();
	private CadastroProduto cadastroProduto = new CadastroProduto();
	
	private ComboDominio comboDominio  = new ComboDominio();
	private MapNcmProvider ncmProvider = new MapNcmProvider();
	
	private TextField<String>	txtNcm;
	private TextField<String>	txtDescricaoNcm;
	
	private ModalBuscaFornecedor modalBuscaFornecedor;
	private Button abrirModalFornecedoresBtn;
	
	private Form<Void> form;
	private TextField<String> nomeFornecedor;
	private Map<String, Object> processVariables = new HashMap<String, Object>();
	
//	private transient User user;
	
	@Inject private AcruxConsultasService embalagemService; 
	//@Inject private ExecuteRestAPI executeRestAPI;
	@Inject private ValidadorBean validador;
	@Inject private GroupService groupService;
	
	public CadastrarProduto(PageParameters pageParameters) throws Exception {
		super(pageParameters);
		
		setTituloPagina("Cadastrar Produto");
		
		this.form = new CadastroProdutoForm("form");
		modalBuscaFornecedor = (ModalBuscaFornecedor) new ModalBuscaFornecedor("modalBuscaFornecedor").setOutputMarkupId(true);

//		user = (User) Session.get().getAttribute("user");
//		User userSearch = (User) Session.get().getAttribute("user");
//		this.user = executeRestAPI.retriveUserProfessionalData(userSearch);
		List<String> emailsFiscal = groupService.findEmails("Fiscal");
		String emails = "";
		for (String dto : emailsFiscal) {
			emails += dto + ", ";
		}
		formularioProduto.setEmailsFiscal(emails);
		
		form.add(modalBuscaFornecedor);
		add(form);
	}
	
	private final class CadastroProdutoForm extends Form<Void> {
		private static final long serialVersionUID = 1L;

		public CadastroProdutoForm(String id) {
			super(id);
			
			AjaxButton btnSalvar = new AjaxButton("btnSalvar") {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form){					
					try {
						validador.valida(formularioProduto, cadastroProduto);						
						
						cadastroProduto.setComentarioCadastro("Enviado pelo BPM");						
						cadastroProduto.setDataSolicitacao(new java.util.Date());						
						cadastroProduto.setIsPrecadastro("");
						
						processVariables.put(VariaveisCadastroProduto.CADASTRO_PRODUTO, cadastroProduto);
						processVariables.put(VariaveisCadastroProduto.FORMULARIO_PRODUTO, formularioProduto);
						
						executeCaseWithVariable(processVariables);	

						target.appendJavaScript("$('#modalMensagemConfirmacao').modal('hide');");
						target.appendJavaScript("$('#modalMensagemConfirmacaoSalvar').modal('show');");
					} catch (ValidacaoBeanException e) {
						target.appendJavaScript("$('#modalMensagemConfirmacao').modal('hide');");
						setMensagensErro(e.getMessages(), target);						
					} catch (BonitaException e) {
						throw new RuntimeException(e);
					}
				}
			};
			
			AjaxButton okBtn = new AjaxButton("okBtn") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					redirecionaParaPaginaDeAtividades();
				}
			};			
			
			add(btnSalvar);
			add(okBtn);
			add(new PreenchimentoInternoPanel("grpPreenchInterno", cadastroProduto, PreenchimentoInternoPanel.BUSCAR_DADOS_RESP_PREENCHIMENTO_DO_USUARIO_DA_SESSAO));
			add(new GrpDadosProduto			("grpDadosProduto")); 
			add(new	GrpEmbalagem			("grpEmbalagem")); 
			add(new	GrpPaletizacaoEstocagem	("grpPaletizacaoEstocagem")); 
			add(new	GrpDadosNutricionais	("grpDadosNutricionais")); 
			add(new	GrpDadosFiscais			("grpDadosFiscais"));
			add(new ConsultarNcmDialog("consultarNcmDialog"));			
		}

	}

	private final class GrpDadosProduto extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("unchecked")
		public GrpDadosProduto(String id) {
			super(id);

			final TextField<String> txtDescCompleta = new TextField<String>("txtDescCompleta",	new PropertyModel<String>(formularioProduto, "descCompleta"));
			final TextField<String> txtCodProduto 	= new TextField<String>("txtCodProduto", 	new PropertyModel<String>(formularioProduto, "codProduto"));
			final TextField<String> txtDescReduzida = new TextField<String>("txtDescReduzida",	new PropertyModel<String>(formularioProduto, "descReduzida"));
			final TextField<String> txtPaisOrigem 	= new TextField<String>("txtPaisOrigem", 	new PropertyModel<String>(formularioProduto, "paisOrigem"));
			nomeFornecedor = (TextField<String>) new TextField<String>("nomeFornecedor", 	new PropertyModel<String>(formularioProduto, "nomeFornecedor"))
							 .setOutputMarkupId(true)
							 .setEnabled(false);
			
			final DropDownChoice<String> cmbOrigemProduto = new DropDownChoice<String>("cmbOrigemProduto", 
							new PropertyModel<String> (formularioProduto, "origemProduto"), 
							embalagemService.findOrigemProduto());		
			
			RadioGroup<String> rdgSimplesNacional = new RadioGroup<String>("rdgSimplesNacional", new PropertyModel<String>(formularioProduto, "simplesNacional"));
			rdgSimplesNacional.add(new Radio<String>("rdbCertificadoNacional_0", new Model<String>(SimNao.getValores().get(0))));
			rdgSimplesNacional.add(new Radio<String>("rdbCertificadoNacional_1", new Model<String>(SimNao.getValores().get(1))));
			
			abrirModalFornecedoresBtn = new Button("abrirModalFornecedoresBtn");
			
			add(txtDescCompleta, txtCodProduto, txtDescReduzida, txtPaisOrigem);
			add(cmbOrigemProduto);
			add(rdgSimplesNacional);
			add(nomeFornecedor);
			add(abrirModalFornecedoresBtn);
		}
	}

	private final class GrpEmbalagem extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public GrpEmbalagem(String id) {
			super(id);

			final TextField<String> txtQuantidadeUnidade 	= new TextField<String>("txtQuantidadeUnidade", 	new PropertyModel<String>(formularioProduto, "quantidadeUnidade"));
			final TextField<String> txtEanUnidade 			= new TextField<String>("txtEanUnidade", 			new PropertyModel<String>(formularioProduto, "eanUnidade"));
			final TextField<String> txtPesoLiqUnidade 		= new TextField<String>("txtPesoLiqUnidade", 		new PropertyModel<String>(formularioProduto, "pesoLiqUnidade"));
			final TextField<String> txtPesoBrutoUnidade 	= new TextField<String>("txtPesoBrutoUnidade", 		new PropertyModel<String>(formularioProduto, "pesoBrutoUnidade"));
			final TextField<String> txtComprimentoUnidade 	= new TextField<String>("txtComprimentoUnidade",	new PropertyModel<String>(formularioProduto, "comprimentoUnidade"));
			final TextField<String> txtAlturaUnidade 		= new TextField<String>("txtAlturaUnidade", 		new PropertyModel<String>(formularioProduto, "alturaUnidade"));
			final TextField<String> txtLarguraUnidade 		= new TextField<String>("txtLarguraUnidade", 		new PropertyModel<String>(formularioProduto, "larguraUnidade"));
			final TextField<String> txtQuantidadeDisplay 	= new TextField<String>("txtQuantidadeDisplay", 	new PropertyModel<String>(formularioProduto, "quantidadeDisplay"));
			final TextField<String> txtEanDisplay 			= new TextField<String>("txtEanDisplay", 			new PropertyModel<String>(formularioProduto, "eanDisplay"));
			final TextField<String> txtPesoLiqDisplay 		= new TextField<String>("txtPesoLiqDisplay", 		new PropertyModel<String>(formularioProduto, "pesoLiqDisplay"));
			final TextField<String> txtPesoBrutoDisplay 	= new TextField<String>("txtPesoBrutoDisplay", 		new PropertyModel<String>(formularioProduto, "pesoBrutoDisplay"));
			final TextField<String> txtComprimentoDisplay 	= new TextField<String>("txtComprimentoDisplay",	new PropertyModel<String>(formularioProduto, "comprimentoDisplay"));
			final TextField<String> txtAlturaDisplay 		= new TextField<String>("txtAlturaDisplay", 		new PropertyModel<String>(formularioProduto, "alturaDisplay"));
			final TextField<String> txtLarguraDisplay 		= new TextField<String>("txtLarguraDisplay", 		new PropertyModel<String>(formularioProduto, "larguraDisplay"));
			final TextField<String> txtEanEmbalagem			= new TextField<String>("txtEanEmbalagem", 			new PropertyModel<String>(formularioProduto, "eanEmbalagem"));
			final TextField<String> txtQuantidadeEmbalagem 	= new TextField<String>("txtQuantidadeEmbalagem",	new PropertyModel<String>(formularioProduto, "quantidadeEmbalagem"));
			final TextField<String> txtPesoBrutoEmbalagem 	= new TextField<String>("txtPesoBrutoEmbalagem", 	new PropertyModel<String>(formularioProduto, "pesoBrutoEmbalagem"));
			final TextField<String> txtComprimentoEmbalagem = new TextField<String>("txtComprimentoEmbalagem", 	new PropertyModel<String>(formularioProduto, "comprimentoEmbalagem"));
			final TextField<String> txtAlturaEmbalagem 		= new TextField<String>("txtAlturaEmbalagem", 		new PropertyModel<String>(formularioProduto, "alturaEmbalagem"));
			final TextField<String> txtLarguraEmbalagem 	= new TextField<String>("txtLarguraEmbalagem",	 	new PropertyModel<String>(formularioProduto, "larguraEmbalagem"));
			
			RadioGroup<String> rdgPossuiEmbalagemEmbalagem = new RadioGroup<String>("rdgPossuiEmbalagemEmbalagem", new PropertyModel<String>(formularioProduto, "possuiEmbalagem"));
			rdgPossuiEmbalagemEmbalagem.add(new Radio<String>("rdbPossuiEmbalagemEmbalagem_0", new Model<String>(SimNao.getValores().get(0))));
			rdgPossuiEmbalagemEmbalagem.add(new Radio<String>("rdbPossuiEmbalagemEmbalagem_1", new Model<String>(SimNao.getValores().get(1))));
			
			final DropDownChoice<String> cmbEmbalagemEmbalagem = new DropDownChoice<String>("cmbEmbalagemEmbalagem", 
							new PropertyModel<String> (formularioProduto, "embalagemEmbalagem"), 
							embalagemService.findEmbalagem());
			
			final DropDownChoice<String> cmbFormaEntregaEmbalagem = new DropDownChoice<String>("cmbFormaEntregaEmbalagem", 
							new PropertyModel<String> (formularioProduto, "formaEntrega"), 
							comboDominio.getFormasEntrega());
			
			final DropDownChoice<String> cmbEmbalagemDisplay = new DropDownChoice<String>("cmbEmbalagemDisplay", 
							new PropertyModel<String> (formularioProduto, "embalagemDisplay"), 
							embalagemService.findEmbalagem());
		
			final DropDownChoice<String> cmbEmbalagemUnidade = new DropDownChoice<String>("cmbEmbalagemUnidade", 
							new PropertyModel<String> (formularioProduto, "embalagemUnidade"), 
							embalagemService.findEmbalagem());
				
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
			
			final TextField<String>	txtQuantidadeLastroPaletizado	= new TextField<String>("txtQuantidadeLastroPaletizado", 	new PropertyModel<String>(formularioProduto, "quantidadeLastroPaletizado"));
			final TextField<String>	txtQuantidadeAlturaPaletizado	= new TextField<String>("txtQuantidadeAlturaPaletizado", 	new PropertyModel<String>(formularioProduto, "quantidadeAlturaPaletizado"));
			final TextField<String>	txtTotalPalletPaletizado		= new TextField<String>("txtTotalPalletPaletizado", 		new PropertyModel<String>(formularioProduto, "totalPalletPaletizado"));
			final TextField<String>	txtPesoTotalPaletizado			= new TextField<String>("txtPesoTotalPaletizado", 			new PropertyModel<String>(formularioProduto, "pesoTotalPaletizado"));
			final TextField<String>	txtComprimentoPaletizado		= new TextField<String>("txtComprimentoPaletizado", 		new PropertyModel<String>(formularioProduto, "comprimentoPaletizado"));
			final TextField<String>	txtAlturaPaletizado				= new TextField<String>("txtAlturaPaletizado", 				new PropertyModel<String>(formularioProduto, "alturaPaletizado"));
			final TextField<String>	txtLarguraPaletizado			= new TextField<String>("txtLarguraPaletizado", 			new PropertyModel<String>(formularioProduto, "larguraPaletizado"));
			final TextField<String>	txtCMaximaEstocagem				= new TextField<String>("txtCMaximaEstocagem", 				new PropertyModel<String>(formularioProduto, "cMaximaEstocagem"));
			final TextField<String>	txtCMinimaEstocagem				= new TextField<String>("txtCMinimaEstocagem", 				new PropertyModel<String>(formularioProduto, "cMinimaEstocagem"));
			final TextField<String>	txtValidadeEstocagem			= new TextField<String>("txtValidadeEstocagem", 			new PropertyModel<String>(formularioProduto, "validadeEstocagem"));
			final TextField<String>	txtEmpilhamentoEstocagem		= new TextField<String>("txtEmpilhamentoEstocagem", 		new PropertyModel<String>(formularioProduto, "empilhamentoEstocagem"));
			
			RadioGroup<String>  rdgPerecivelPaletizado = new RadioGroup<String>("rdgPerecivelPaletizado", new PropertyModel<String>(formularioProduto, "perecivel"));
			rdgPerecivelPaletizado.add(new Radio<String>("rdbPerecivelPaletizado_0", new Model<String>(SimNao.getValores().get(0))));
			rdgPerecivelPaletizado.add(new Radio<String>("rdbPerecivelPaletizado_1", new Model<String>(SimNao.getValores().get(1))));
			
			RadioGroup<String> rdgPesoVariavelPaletizado = new RadioGroup<String>("rdgPesoVariavelPaletizado", new PropertyModel<String>(formularioProduto, "pesoVariavel"));
			rdgPesoVariavelPaletizado.add(new Radio<String>("rdbPesoVariavelPaletizado_0", new Model<String>(SimNao.getValores().get(0))));
			rdgPesoVariavelPaletizado.add(new Radio<String>("rdbPesoVariavelPaletizado_1", new Model<String>(SimNao.getValores().get(1))));
			
			RadioGroup<String> rdgSazonalPaletizado = new RadioGroup<String>("rdgSazonalPaletizado", new PropertyModel<String>(formularioProduto, "sazonal"));
			rdgSazonalPaletizado.add(new Radio<String>("rdbSazonalPaletizado_0", new Model<String>(SimNao.getValores().get(0))));
			rdgSazonalPaletizado.add(new Radio<String>("rdbSazonalPaletizado_1", new Model<String>(SimNao.getValores().get(1))));
			
			RadioGroup<String> rdgProdutoPaletizado = new RadioGroup<String>("rdgProdutoPaletizado", new PropertyModel<String>(formularioProduto, "produtoPaletizado"));
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
			
			final TextField<String>	txtQuantidadeParcao	= new TextField<String>("txtQuantidadeParcao", 	new PropertyModel<String>(formularioProduto,"quantidadePorcao"));
			final TextField<String>	txtValorCalorico	= new TextField<String>("txtValorCalorico", 	new PropertyModel<String>(formularioProduto,"valorCalorico"));
			final TextField<String>	txtProteina			= new TextField<String>("txtProteina", 			new PropertyModel<String>(formularioProduto,"proteina"));
			final TextField<String>	txtGorduraSaturada	= new TextField<String>("txtGorduraSaturada", 	new PropertyModel<String>(formularioProduto,"gorduraSaturada"));
			final TextField<String>	txtFibraAlimentar	= new TextField<String>("txtFibraAlimentar", 	new PropertyModel<String>(formularioProduto,"fibraAlimentar"));
			final TextField<String>	txtSodio			= new TextField<String>("txtSodio", 			new PropertyModel<String>(formularioProduto,"sodio"));
			final TextField<String>	txtCarboidrato		= new TextField<String>("txtCarboidrato", 		new PropertyModel<String>(formularioProduto,"carboidrato"));
			final TextField<String>	txtGorduraTotal		= new TextField<String>("txtGorduraTotal", 		new PropertyModel<String>(formularioProduto,"gorduraTotal"));
			final TextField<String>	txtGorduraTrans		= new TextField<String>("txtGorduraTrans", 		new PropertyModel<String>(formularioProduto,"gorduraTrans"));
			
			final DropDownChoice<String> cmbUnidadePorcao = new DropDownChoice<String>("cmbUnidadePorcao", 
							new PropertyModel<String> (formularioProduto, "unidadePorcao"), 
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
				
				txtNcm				= new TextField<String>("txtNcm", 			new PropertyModel<String>(formularioProduto,"cnm"));
				txtDescricaoNcm		= new TextField<String>("txtDescricaoNcm", 	new PropertyModel<String>(formularioProduto,"descricaoNcm"));
				final TextField<String>	txtAliquotaIpi		= new TextField<String>("txtAliquotaIpi", 	new PropertyModel<String>(formularioProduto,"aliquotaIpi"));
				final TextField<String>	txtAliquotaCofins	= new TextField<String>("txtAliquotaCofins",new PropertyModel<String>(formularioProduto,"aliquotaCofins"));
				final TextField<String>	txtAliquotaPis		= new TextField<String>("txtAliquotaPis", 	new PropertyModel<String>(formularioProduto,"aliquotaPis"));
				final TextField<String>	txtPauta			= new TextField<String>("txtPauta", 		new PropertyModel<String>(formularioProduto,"pauta"));
				final TextField<String>	txtIvaSt			= new TextField<String>("txtIvaSt", 		new PropertyModel<String>(formularioProduto,"ivaSt"));
				final TextField<String>	txtFundamentacao	= new TextField<String>("txtFundamentacao", new PropertyModel<String>(formularioProduto,"fundamentacao"));
				final TextField<String>	txtIncentivoFiscalQual	= new TextField<String>("txtIncentivoFiscalQual", new PropertyModel<String>(formularioProduto,"incentivoFiscalQual"));
				final MoneyTextField	txtcustoBase      	= new MoneyTextField("txtcustoBase", new PropertyModel<BigDecimal>(formularioProduto,"custoBase"));
				final MoneyTextField	txtdesconto	        = new MoneyTextField("txtdesconto", new PropertyModel<BigDecimal>(formularioProduto,"desconto"));
				final MoneyTextField	txtdespAcessorios	= new MoneyTextField("txtdespAcessorios", new PropertyModel<BigDecimal>(formularioProduto,"despAcessorios"));
				final MoneyTextField	txtcustoBruto	    = new MoneyTextField("txtcustoBruto", new PropertyModel<BigDecimal>(formularioProduto,"custoBruto"));
				
				RadioGroup<String> rdgIncentivoFiscal = new RadioGroup<String>("rdgIncentivoFiscal", new PropertyModel<String>(formularioProduto, "incentivoFiscal"));
				rdgIncentivoFiscal.add(new Radio<String>("rdbIncentivoFiscal_0", new Model<String>(SimNao.getValores().get(0))));
				rdgIncentivoFiscal.add(new Radio<String>("rdbIncentivoFiscal_1", new Model<String>(SimNao.getValores().get(1))));				
				
				final DropDownChoice<String> cmbTributacaoIcms = new DropDownChoice<String>("cmbTributacaoIcms", 
								new PropertyModel<String> (formularioProduto, "tributacaoIcms"), 
								comboDominio.getTributacoesIcms());
				
				final DropDownChoice<String> cmbAlicotaIcms = new DropDownChoice<String>("cmbAlicotaIcms", 
								new PropertyModel<String> (formularioProduto, "alicotaIcms"), 
								comboDominio.getAliquostaIcms());
				
				final DropDownChoice<String> cmbReducaoIcms = new DropDownChoice<String>("cmbReducaoIcms", 
								new PropertyModel<String> (formularioProduto, "reducaoIcms"), 
								comboDominio.getReducoes());
				
				txtNcm.setEnabled(false);
				txtDescricaoNcm.setEnabled(false);
				
				txtNcm.setOutputMarkupId(true);
				txtDescricaoNcm.setOutputMarkupId(true);
				
				AjaxButton btnExibirModalNcm = new AjaxButton("btnExibirModalNcm") {
					private static final long serialVersionUID = 1L;
					@Override
					public void onSubmit(AjaxRequestTarget target, Form<?> form){
						target.appendJavaScript("$('#consultarNcmDialog').modal('show');");
					}
				};
				
				add(txtNcm,txtDescricaoNcm,txtAliquotaIpi,txtAliquotaCofins,txtAliquotaPis,txtPauta,txtIvaSt,txtFundamentacao,txtIncentivoFiscalQual,txtcustoBase,txtdesconto,txtdespAcessorios,txtcustoBruto);
				add(rdgIncentivoFiscal);
				add(cmbTributacaoIcms,cmbAlicotaIcms,cmbReducaoIcms);
				add(btnExibirModalNcm);
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

					AjaxEventBehavior onclick = new AjaxEventBehavior("click") {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onEvent(AjaxRequestTarget target) {
							formularioProduto.setCnm(dto.getCodncm());
							formularioProduto.setDescricaoNcm(dto.getDescricao());

							target.add(txtNcm);
							target.add(txtDescricaoNcm);

							target.appendJavaScript("$('#consultarNcmDialog').modal('hide');");
						}
					};
					item.add(onclick);
					
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
	
	private class ModalBuscaFornecedor extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private static final int QUANT_ITENS_POR_PAGINA = 10;
		
		private TextField<String> filtroFornecedor;
		private AjaxButton buscaFornecedorBtn;
		private WebMarkupContainer tabelaFornecedores;
		private FornecedoresDataView fornecedoresDataView;
		private GePessoaFornecedorProvider fornecedorProvider = new GePessoaFornecedorProvider();
		private AjaxPagingNavigator navigator;
		private WebMarkupContainer msgFonecedoresNaoEncontrados;		
		
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
			
			navigator = new AjaxPagingNavigator("navigator", fornecedoresDataView);
			msgFonecedoresNaoEncontrados = new WebMarkupContainer("msgFonecedoresNaoEncontrados");
			tabelaFornecedores = (WebMarkupContainer) new WebMarkupContainer("tabelaFornecedores").setOutputMarkupId(true);
			tabelaFornecedores.add(fornecedoresDataView, navigator);			
			
			add(filtroFornecedor, buscaFornecedorBtn, tabelaFornecedores, msgFonecedoresNaoEncontrados);			
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
	
	private class FornecedoresDataView extends DataView<GePessoaEntity>{
		private static final long serialVersionUID = 1L;

		protected FornecedoresDataView(String id, IDataProvider<GePessoaEntity> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<GePessoaEntity> item) {
			final GePessoaEntity fornecedor = (GePessoaEntity) item.getModelObject();

			item.add(new Label("lblCodigoFornec", fornecedor.getSeqpessoa()));
			item.add(new Label("lblDescricaoFornec", fornecedor.getNomerazao()));
			
			AjaxEventBehavior onClickEvent = new AjaxEventBehavior("click") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {
					formularioProduto.setSeqfornecedor(String.valueOf(fornecedor.getSeqpessoa()));
					formularioProduto.setNomeFornecedor(fornecedor.getNomerazao());
					formularioProduto.setIdentificador(fornecedor.getNrocgccpf().toString() + toDigitos(fornecedor.getDigcgccpf()));
					target.appendJavaScript("$('#consultarFornecedorDialog').modal('hide')");
					target.add(nomeFornecedor);
				}
			};					

			item.add(onClickEvent);
		}		
	}
	
	private String toDigitos(Long digitos){
		String strDigitos = digitos.toString();
		
		while(strDigitos.length() < 2){
			strDigitos = "0" + strDigitos;
		}
		
		return strDigitos;
	}	
	
}