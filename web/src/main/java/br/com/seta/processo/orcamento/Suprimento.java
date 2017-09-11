//package br.com.seta.processo.orcamento;
//
//import java.io.File;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.inject.Inject;
//
//import org.apache.http.ParseException;
//import org.apache.wicket.Session;
//import org.apache.wicket.ajax.AjaxRequestTarget;
//import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
//import org.apache.wicket.ajax.markup.html.form.AjaxButton;
//import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
//import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
//import org.apache.wicket.markup.html.WebMarkupContainer;
//import org.apache.wicket.markup.html.basic.Label;
//import org.apache.wicket.markup.html.form.ChoiceRenderer;
//import org.apache.wicket.markup.html.form.DropDownChoice;
//import org.apache.wicket.markup.html.form.Form;
//import org.apache.wicket.markup.html.form.TextArea;
//import org.apache.wicket.markup.html.form.TextField;
//import org.apache.wicket.markup.html.form.upload.FileUpload;
//import org.apache.wicket.markup.html.form.upload.FileUploadField;
//import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
//import org.apache.wicket.markup.html.panel.FeedbackPanel;
//import org.apache.wicket.markup.repeater.Item;
//import org.apache.wicket.markup.repeater.data.DataView;
//import org.apache.wicket.model.Model;
//import org.apache.wicket.model.PropertyModel;
//import org.apache.wicket.request.mapper.parameter.PageParameters;
//import org.bonitasoft.engine.exception.BonitaException;
//import org.joda.time.DateTime;
//
//import br.com.seta.processo.bean.CalculoRequisicao;
//import br.com.seta.processo.bean.Requisicao;
//import br.com.seta.processo.dto.Cfop;
//import br.com.seta.processo.dto.EmpresaMatriz;
//import br.com.seta.processo.dto.IntencaoCompra;
//import br.com.seta.processo.dto.ModeloDocumento;
//import br.com.seta.processo.dto.NaturezaDespesa;
//import br.com.seta.processo.dto.OrIndicador;
//import br.com.seta.processo.dto.OrReqitensdespesa;
//import br.com.seta.processo.dto.OrReqplanilhalancto;
//import br.com.seta.processo.dto.OrRequisicao;
//import br.com.seta.processo.dto.OrRequisicaovencto;
//import br.com.seta.processo.dto.OrvProdutoTributo;
//import br.com.seta.processo.dto.Pessoa;
//import br.com.seta.processo.dto.Transportadora;
//import br.com.seta.processo.dto.User;
//import br.com.seta.processo.exceptions.CalculoException;
//import br.com.seta.processo.exceptions.DesdobramentoException;
//import br.com.seta.processo.exceptions.HttpStatus401Exception;
//import br.com.seta.processo.exceptions.HttpStatusException;
//import br.com.seta.processo.exceptions.SuprimentoException;
//import br.com.seta.processo.model.CfopModel;
//import br.com.seta.processo.model.DefaultMoneyModel;
//import br.com.seta.processo.model.EmpresaModel;
//import br.com.seta.processo.model.ModeloDocumentoModel;
//import br.com.seta.processo.model.NaturezaDespesaModel;
//import br.com.seta.processo.model.OrReqplanilhalanctoModel;
//import br.com.seta.processo.model.PessoaModel;
//import br.com.seta.processo.model.ProdutoModel;
//import br.com.seta.processo.model.TransportadoraModel;
//import br.com.seta.processo.model.ValoresModel;
//import br.com.seta.processo.page.Atividades;
//import br.com.seta.processo.page.TaskPage;
//import br.com.seta.processo.provider.CfopProvider;
//import br.com.seta.processo.provider.ModeloProvider;
//import br.com.seta.processo.provider.NaturezaProvider;
//import br.com.seta.processo.provider.OrReqitensdespesaProvider;
//import br.com.seta.processo.provider.OrReqplanilhalanctoProvider;
//import br.com.seta.processo.provider.OrRequisicaovenctoProvider;
//import br.com.seta.processo.provider.PessoaProvider;
//import br.com.seta.processo.provider.ProdutoProvider;
//import br.com.seta.processo.provider.TransportadoraProvider;
//import br.com.seta.processo.service.ExecuteRestAPI;
//import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
//import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
//
//public class Suprimento extends TaskPage  {
//
//
//	private static final long serialVersionUID = 1L;
//
//	@Inject
//	private CalculoRequisicao calculos;
//
//	@Inject
//	private ExecuteRestAPI executeRestAPI;
//
//	@Inject
//	private Requisicao requisicaoService;
//
//	private final static Long QUANT_LINHAS_TABELAS = 10L;
//	private transient User user = (User) Session.get().getAttribute("user");
//
//
//	private EmpresaModel empresaModel = new EmpresaModel();
//	private NaturezaDespesaModel naturezaDespesaModel = new NaturezaDespesaModel(); 
//	private TransportadoraModel transportadoraModel = new TransportadoraModel();
//	private PessoaModel pessoaModel = new PessoaModel();
//	private ModeloDocumentoModel modeloDocModel = new ModeloDocumentoModel();
//	private ValoresModel valoresModel = new ValoresModel();
//	private CfopModel cfopModel = new CfopModel();
//	private ProdutoModel produtoModel = new ProdutoModel();
//	private OrReqplanilhalanctoModel orReqplanilhalanctoModel = new OrReqplanilhalanctoModel();
//
//	private TextField<String> natDespesaSelecionada;
//	private TextField<String> transportadoraSelecionada;
//	private TextField<String> pessoaSelecionada;
//	private TextField<String> modeloDocSelecionado;
//	private TextField<String> cfopSelecionado;
//	private TextField<String> produtoSelecionado;
//	private TextField<Date> datacompra;
//	private TextField<Date> datainclusao;
//	private TextArea<String> observacao;	
//	private final WebMarkupContainer naturezaContainer =  new WebMarkupContainer("naturezaContainer");
//	private final WebMarkupContainer transportadoraContainer =  new WebMarkupContainer("transportadoraContainer");
//	private final WebMarkupContainer pessoasContainer = new WebMarkupContainer("pessoasContainer");
//	private final WebMarkupContainer modeloDocumentoContainer = new WebMarkupContainer("modeloDocumentoContainer");
//	private final WebMarkupContainer cfopContainer =  new WebMarkupContainer("cfopContainer");
//	private final WebMarkupContainer produtosContainer = new WebMarkupContainer("produtosContainer");
//	private final WebMarkupContainer requisicaoContainer =  new WebMarkupContainer("requisicaoContainer");
//	private final WebMarkupContainer itensContainer = new WebMarkupContainer("itensContainer");
//	private final WebMarkupContainer orReqplanilhalanctoContainer = new WebMarkupContainer("orReqplanilhalanctoContainer");
//	private final WebMarkupContainer vencimentosContainer = new WebMarkupContainer("vencimentosContainer");
//	private EmpresaMatriz empresaMatrizSelected;
//	private OrReqplanilhalanctoProvider orReqplanilhalanctoProvider = new OrReqplanilhalanctoProvider(new BigDecimal("0"), new BigDecimal("0"));
//	private OrRequisicaovenctoProvider orRequisicaovenctoProvider = new OrRequisicaovenctoProvider();
//	private OrReqitensdespesaProvider orReqitensdespesaProvider = new OrReqitensdespesaProvider();
//	private long taskId;
//	private long caseId;
//	private String username;
//	private long idUser;
//
//	FeedbackPanel feedback = new FeedbackPanel("feedback");
//	private OrRequisicao orRequisicao = new OrRequisicao();
//
//
//
//	public Suprimento(PageParameters pageParameters) throws HttpStatus401Exception, ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException {
//		super(pageParameters);
//		username = user.getUserName();
//		idUser   = user.getIdUser();
//		this.taskId = getTaskId();
//		IntencaoCompra intencaoCompra = (IntencaoCompra) executeRestAPI.retriveVariableTask(user, taskId, IntencaoCompra.class , "intencaoCompra");
//		Label intensao = new Label("IntencaoCompra",caseId);
//		Label tipoIntensao = new Label("TipoIntensao",intencaoCompra.getTipoIntensao());
//		
//		add(intensao);
//		add(tipoIntensao);
//		add(feedback);
//		add(new NotaFiscalForm("modeloNotaFiscalForm"));
//		add(new PessoaForm("pessoaForm"));
//		add(new TransportadoraForm("transportadoraForm"));
//		add(new NaturezaDespesasForm("natDespesasForm"));
//		add(new CfopForm("cfopForm"));
//		add(new ProdutosForm("produtosForm"));
//		add(new RequsisicaoForm("requisicaoForm"));
//		add(new ItensForm("itensForm"));
//		add(new ContabForm("contabForm"));
//		add(new VencimentoForm("vencimentoForm"));
//		add(new OrRequisicaoForm("enviarOrRequisicaoForm")); 
//		//add(new uploadItensForm("uploadItensForm"));
//		itensContainer.setOutputMarkupId(true);
//		naturezaContainer.setOutputMarkupId(true);
//		pessoasContainer.setOutputMarkupId(true);
//		cfopContainer.setOutputMarkupId(true);
//		feedback.setOutputMarkupId(true);
//		requisicaoContainer.setOutputMarkupId(true);
//		transportadoraContainer.setOutputMarkupId(true);
//		modeloDocumentoContainer.setOutputMarkupId(true);
//		produtosContainer.setOutputMarkupId(true);
//		orReqplanilhalanctoContainer.setOutputMarkupId(true);
//		vencimentosContainer.setOutputMarkupId(true);
//
//		setTituloPagina("Suprimentos");
//	}
//
//
//	public class RequsisicaoForm extends Form<Void> {
//
//		private static final long serialVersionUID = 1L;
//		private final DropDownChoice<EmpresaMatriz> empresaCombo;
//
//		@SuppressWarnings("unchecked")
//		public RequsisicaoForm(String id) {
//
//			super(id);
//
//			OnChangeAjaxBehavior ajaxBehaviorPessoa =  new OnChangeAjaxBehavior() {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onUpdate(AjaxRequestTarget target) {
//					if  ( (empresaMatrizSelected.getNroempresa()!=null) && (naturezaDespesaModel.getCodhistorico()!=null) ) {
//						orReqplanilhalanctoProvider.putParameter(empresaMatrizSelected.getNroempresa(),naturezaDespesaModel.getCodhistorico());
//					}else {
//						orReqplanilhalanctoProvider.putParameter(new BigDecimal("1"),new BigDecimal("1"));
//					}
//					target.add(orReqplanilhalanctoContainer);
//				}
//			};
//
//			empresaCombo = new DropDownChoice<EmpresaMatriz>("empresaCb", new PropertyModel<EmpresaMatriz>(empresaModel, "empresaMatriz"), empresaModel.getEmpresaMatrizs(), new ChoiceRenderer<EmpresaMatriz>("fantasia"));
//			empresaCombo.add(ajaxBehaviorPessoa);
//			empresaMatrizSelected = empresaModel.getEmpresaMatriz();//vem selecionado na primeira posição
//
//
//			natDespesaSelecionada     = (TextField<String>) new TextField<String>("natDespesaSelecionada", new PropertyModel<String>(naturezaDespesaModel, "historico")).setOutputMarkupId(true);
//			transportadoraSelecionada = (TextField<String>) new TextField<String>("transportadoraSelecionada", new PropertyModel<String>(transportadoraModel, "razaoSocial")).setOutputMarkupId(true);
//			pessoaSelecionada         = (TextField<String>) new TextField<String>("pessoaSelecionada", new PropertyModel<String>(pessoaModel, "nomeRazao")).setOutputMarkupId(true);
//			modeloDocSelecionado      = (TextField<String>) new TextField<String>("modeloDocSelecionado", new PropertyModel<String>(modeloDocModel, "descricao")).setOutputMarkupId(true);
//			datacompra                = new TextField<Date>("DataCompra", new PropertyModel<Date>(orRequisicao, "dtacompra"));
//			datainclusao              = new TextField<Date>("DataInclusao", new PropertyModel<Date>(orRequisicao, "dtainclusao"));
//
//			add(empresaCombo, natDespesaSelecionada, requisicaoContainer);
//			requisicaoContainer.add(empresaCombo, natDespesaSelecionada, pessoaSelecionada, transportadoraSelecionada, modeloDocSelecionado, datacompra,datainclusao);
//
//		}
//	}
//
//
//
//	public class ItensForm extends Form<Void> {
//
//		private static final long serialVersionUID = 1L;
//
//		private TextField<String> Versaoprod;
//		private TextField<String> Unidadepadrao;
//		private TextField<Object> Quantidade;
//		private TextField<Object> Valor;
//		private TextField<Object> VlrDesconto;
//		private TextField<Object> VlrAcescimo;
//		private AjaxButton Inserir;
//		private AjaxButton uploadButtonFile;
//		private DataView<OrReqitensdespesa> dataViewOrReqitensdespesa;
//		private FileUploadField fileUpload;
//		private String UPLOAD_FOLDER = "/home/sergio/app/";
//
//
//		public ItensForm(String id) {
//			super(id);
//
//
//			uploadButtonFile = new AjaxButton("uploadButtonFile", ItensForm.this) {				
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//					final FileUpload uploadedFile = fileUpload.getFileUpload();
//					if (uploadedFile != null) {
//						File newFile = new File(UPLOAD_FOLDER+ uploadedFile.getClientFileName());
//						if (newFile.exists()) {
//							newFile.delete();
//						}
//						try {
//							newFile.createNewFile();
//							uploadedFile.writeTo(newFile);
//							orRequisicao.setAnexoItens(uploadedFile.getBytes());
//							Suprimento.this.exibeMensagemSucesso("Arquivo Salvo", uploadedFile.getClientFileName() , target);
//						} catch (Exception e) {
//							e.printStackTrace();
//							throw new IllegalStateException("Error");
//						}
//					}
//				}
//			};
//
//			fileUpload = new FileUploadField("fileUpload");
//
//			cfopSelecionado    = new TextField<String>("cfopSelecionado", new PropertyModel<String>(cfopModel, "descricao"));
//			produtoSelecionado = new TextField<String>("produtoSelecionado", new PropertyModel<String>(produtoModel, "descricao"));
//
//			Versaoprod         = new TextField<String>("Versaoprod",   new PropertyModel<String>(produtoModel, "versaoprod"));
//			Unidadepadrao      = new TextField<String>("Unidadepadrao",new PropertyModel<String>(produtoModel, "unidadepadrao"));
//
//			Quantidade         = new TextField<Object>("Quantidade",   new DefaultMoneyModel(new PropertyModel<BigDecimal>(produtoModel, "quantidade")));
//			Valor              = new TextField<Object>("Valor",        new DefaultMoneyModel(new PropertyModel<BigDecimal>(produtoModel, "valor")));
//			VlrDesconto        = new TextField<Object>("VlrDesconto",  new DefaultMoneyModel(new PropertyModel<BigDecimal>(produtoModel, "vlrDesconto")));
//			VlrAcescimo        = new TextField<Object>("VlrAcescimo",  new DefaultMoneyModel(new PropertyModel<BigDecimal>(produtoModel, "vlrAcescimo")));
//
//			Inserir = new AjaxButton("Inserir", ItensForm.this) {		
//
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//
//					if ( (produtoModel.getSeqproduto()!=null) || (cfopModel.getCfop()!=null) ) {
//						
//						OrReqitensdespesa orReqitensdespesa = new OrReqitensdespesa();
//						orReqitensdespesa.setCodproduto(produtoModel.getSeqproduto().toString());
//						orReqitensdespesa.setDescricao(produtoModel.getDescricao());
//						orReqitensdespesa.setNroempresa(empresaModel.getNroempresa().shortValue());
//						orReqitensdespesa.setCfop(cfopModel.getCfop().intValue());
//						orReqitensdespesa.setDescricaoCfop(cfopModel.getDescricao());
//						orReqitensdespesa.setQuantidade(produtoModel.getQuantidade());
//						orReqitensdespesa.setUnidade(produtoModel.getUnidadepadrao());
//						orReqitensdespesa.setUnidadepadrao(produtoModel.getUnidadepadrao());
//						orReqitensdespesa.setVlracrescimos(produtoModel.getVlrAcescimo());
//						orReqitensdespesa.setVlrdesconto(produtoModel.getVlrDesconto());
//						orReqitensdespesa.setVlrtotal(produtoModel.getValor());
//						orReqitensdespesa.setVersaoprod(produtoModel.getVersaoprod());
//						orReqitensdespesa.setVlrdesconto(produtoModel.getVlrDesconto());
//						orReqitensdespesa.setVlracrescimos(produtoModel.getVlrAcescimo());
//						orReqitensdespesa.setVlritem(produtoModel.getValor());
//						orReqitensdespesa.setCodtributacao(produtoModel.getCodtributacao());
//						orReqitensdespesa.setComplemento(produtoModel.getComplemento());
//						orReqitensdespesa.setNroitem(produtoModel.getNroitem());
//						orReqitensdespesaProvider.add(orReqitensdespesa);
//						target.add(itensContainer);
//						
//					}else {
//						Suprimento.this.setMensagemErro("Produto ou Cfop está vazio !", target);
//					}
//					
//				}
//			};
//
//			dataViewOrReqitensdespesa = new DataView<OrReqitensdespesa>("Itensdespesa",orReqitensdespesaProvider) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void populateItem(Item<OrReqitensdespesa> item) {
//
//					final OrReqitensdespesa orReqitensdespesa  = (OrReqitensdespesa) item.getModelObject();
//
//					Label codproduto    = new Label("codproduto",   orReqitensdespesa.getCodproduto());
//					Label cfop          = new Label("cfop",         orReqitensdespesa.getCfop());
//					Label descricao     = new Label("descricao",    orReqitensdespesa.getDescricao());
//					Label versaoprod    = new Label("versaoprod",   orReqitensdespesa.getVersaoprod());
//					Label unidadepadrao = new Label("unidadepadrao",orReqitensdespesa.getUnidade());
//
//					Label quantidade    = new Label("quantidade",   orReqitensdespesa.getQuantidade());
//					Label vlrtotal      = new Label("vlrtotal",     orReqitensdespesa.getVlrtotal());
//					Label vlrdesconto   = new Label("vlrdesconto",  orReqitensdespesa.getVlrdesconto());
//					Label vlracrescimos = new Label("vlracrescimos",orReqitensdespesa.getVlracrescimos());
//
//					AjaxButton delete = new AjaxButton("delete", ItensForm.this) {				
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//							orReqitensdespesaProvider.delte(orReqitensdespesa);
//							target.add(pessoasContainer,itensContainer);
//						}
//					};
//
//					AjaxButton edit = new AjaxButton("edit", ItensForm.this) {				
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//							produtoModel.setQuantidade(orReqitensdespesa.getQuantidade());
//							produtoModel.setVlrAcescimo(orReqitensdespesa.getVlracrescimos());
//							produtoModel.setVlrDesconto(orReqitensdespesa.getVlrdesconto());
//							produtoModel.setValor(orReqitensdespesa.getVlrtotal());
//							cfopModel.setCfop(new BigDecimal(orReqitensdespesa.getCfop()));
//							produtoModel.setSeqproduto(new BigDecimal(orReqitensdespesa.getCodproduto()));
//							produtoModel.setDescricao(orReqitensdespesa.getDescricao());
//							produtoModel.setUnidadepadrao(orReqitensdespesa.getUnidadepadrao());
//							produtoModel.setVersaoprod(orReqitensdespesa.getVersaoprod());
//							cfopModel.setDescricao(orReqitensdespesa.getDescricaoCfop());
//							produtoModel.setValor(orReqitensdespesa.getVlrtotal());
//							produtoModel.setCodtributacao(orReqitensdespesa.getCodtributacao());
//							produtoModel.setComplemento(orReqitensdespesa.getComplemento());
//							produtoModel.setNroitem(orReqitensdespesa.getNroitem());
//							produtoModel.setVlritem(orReqitensdespesa.getVlritem());
//							produtoModel.setNroitem(orReqitensdespesa.getNroitem());
//							orReqitensdespesaProvider.delte(orReqitensdespesa);
//							target.add(itensContainer);
//						}
//					};
//
//
//					item.add(codproduto);
//					item.add(cfop);
//					item.add(descricao);
//					item.add(versaoprod);
//					item.add(unidadepadrao);
//					item.add(quantidade);
//					item.add(vlrtotal);
//					item.add(vlrdesconto);
//					item.add(vlracrescimos);
//					item.add(edit);
//					item.add(delete);
//
//
//				}
//			};
//
//			itensContainer.add(cfopSelecionado, produtoSelecionado, Versaoprod, Unidadepadrao, Quantidade, Valor, VlrDesconto, VlrAcescimo, Inserir, dataViewOrReqitensdespesa, fileUpload, uploadButtonFile);
//			//			itensContainer.setEnabled(naturezaDespesaModel.getIsExisteIntes());
//			add(itensContainer);
//
//		}		
//	}
//
//	public class PessoaForm extends Form<Void> {
//		private static final long serialVersionUID = 1L;
//
//		private TextField<String> buscaClienteTxt;
//		private AjaxButton buscaClientesBtn;
//		private DataView<Pessoa> dataViewPessoas;
//		private PessoaProvider pessoaProvider = new PessoaProvider();
//		private PagingNavigator navigator;
//
//
//		public PessoaForm(String id) {
//			super(id);
//
//			this.buscaClienteTxt = new TextField<String>("buscaClienteTxt", Model.of(""));
//
//			this.buscaClientesBtn = new AjaxButton("buscaClientesBtn", PessoaForm.this) {				
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//					String nomeCliente = (String)buscaClienteTxt.getDefaultModelObject();
//					pessoaProvider.putParameter(nomeCliente);
//					buscaClienteTxt.setDefaultModelObject("");
//					target.add(pessoasContainer);
//				}
//			};
//
//
//			this.dataViewPessoas = new DataView<Pessoa>("pessoasRow", pessoaProvider) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void populateItem(Item<Pessoa> item) {
//					final Pessoa pessoa = item.getModelObject();
//					item.add(new Label("codigo" ,pessoa.getSeqpessoa().longValue()));
//					item.add(new Label("nome" ,pessoa.getNomerazao()));
//					item.add(new AjaxCheckBox("check", new PropertyModel<Boolean>(pessoa, "selected")) {					
//						private static final long serialVersionUID = 1L;
//
//						@Override
//						protected void onUpdate(AjaxRequestTarget target) {
//							pessoaModel.setPessoa(pessoa);
//							pessoaModel.setNomeRazao(pessoa.getNomerazao());
//							orRequisicao.setSeqpessoa(pessoa.getSeqpessoa().intValue());
//							orRequisicao.setVersaopessoa(pessoa.getVersao());
//							target.add(requisicaoContainer.get("pessoaSelecionada"), pessoasContainer);
//							target.appendJavaScript("$('#clienteFornecedorModal').modal('hide');");
//						}
//
//					});
//				}
//			};
//
//			this.dataViewPessoas.setItemsPerPage(QUANT_LINHAS_TABELAS);
//			this.navigator = new AjaxPagingNavigator("navigator", dataViewPessoas){				
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				protected void onAjaxEvent(AjaxRequestTarget target) {
//					target.add(pessoasContainer);
//				}
//			};			
//
//			pessoasContainer.add(buscaClienteTxt, buscaClientesBtn, dataViewPessoas, navigator);
//			add(pessoasContainer);
//
//		}
//	}
//
//
//	public class TransportadoraForm extends Form<Void> {
//		private static final long serialVersionUID = 1L;
//
//		private TransportadoraProvider transportadoraProvider = new TransportadoraProvider();
//		private TextField<String> buscaTransportadoraTxt;		
//		private AjaxButton buscaTransportadoraBtn;
//		private DataView<Transportadora> dataView;
//		private PagingNavigator navigator;
//
//		public TransportadoraForm(String id) {
//			super(id);
//
//			FeedbackPanel feedback = new FeedbackPanel("feedback");
//
//			this.buscaTransportadoraTxt = new TextField<String>("buscaTransportadoraTxt", Model.of(new String()));
//			this.buscaTransportadoraTxt.setOutputMarkupId(true);
//
//			this.buscaTransportadoraBtn = new AjaxButton("buscaTransportadoraBtn", TransportadoraForm.this) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//					String nomeRazao = (String)buscaTransportadoraTxt.getDefaultModelObject();
//					transportadoraProvider.putParameter(nomeRazao);
//					buscaTransportadoraTxt.setDefaultModelObject("");
//					target.add(transportadoraContainer);
//				}				
//			};
//
//			this.dataView = new  DataView<Transportadora>("row", transportadoraProvider) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void populateItem(Item<Transportadora> item) {
//					final Transportadora transportadora = (Transportadora) item.getModelObject();
//					item.add(new Label("codigoTransportadora", transportadora.getSeqpessoa().longValue()));
//					item.add(new Label("descricaoTransportadora", transportadora.getNomerazao()));
//					item.add(new AjaxCheckBox("check", new PropertyModel<Boolean>(transportadora, "selected")) {
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onUpdate(AjaxRequestTarget target) {	
//							transportadoraModel.setRazaoSocial(transportadora.getNomerazao());
//							transportadoraModel.setTransportadora(transportadora);
//							orRequisicao.setSeqtransportador(transportadora.getSeqpessoa().intValue());
//							target.add(requisicaoContainer.get("transportadoraSelecionada"), transportadoraContainer);
//							target.appendJavaScript("$('#transportadoraModal').modal('hide');");
//						}
//					});
//				}
//			};			
//
//
//			dataView.setItemsPerPage(QUANT_LINHAS_TABELAS);			
//			navigator = new AjaxPagingNavigator("navigator", dataView){				
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				protected void onAjaxEvent(AjaxRequestTarget target) {
//					target.add(transportadoraContainer);					
//				}
//			};
//
//			add(transportadoraContainer);
//			transportadoraContainer.add(buscaTransportadoraBtn, buscaTransportadoraTxt, dataView, feedback, navigator);
//
//
//		}
//	}
//
//
//	public class NotaFiscalForm extends Form<Void> {
//		private static final long serialVersionUID = 1L;
//
//		private TextField<String> buscaDocumentoTxt;
//		private AjaxButton	buscaDocumentoBtn;
//		private DataView<ModeloDocumento> dataVieModeloDoc;
//		private ModeloProvider modeloDocProvider = new ModeloProvider();
//		private AjaxPagingNavigator navigator;
//
//		public NotaFiscalForm(String id) {
//			super(id);
//
//			this.buscaDocumentoTxt = new TextField<String>("buscaDocumentoTxt", Model.of(""));
//			this.buscaDocumentoBtn = new AjaxButton("buscaDocumentoBtn", NotaFiscalForm.this) {
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//					String descricao = (String)buscaDocumentoTxt.getDefaultModelObject();
//					modeloDocProvider.putParameter(descricao);
//					buscaDocumentoTxt.setDefaultModelObject("");
//					target.add(modeloDocumentoContainer);
//				}
//			};
//
//			this.dataVieModeloDoc = new DataView<ModeloDocumento>("documentosRow", modeloDocProvider) {
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				protected void populateItem(Item<ModeloDocumento> item) {
//					final ModeloDocumento modeloDocumento = item.getModelObject();	
//
//					item.add(new Label("codigo", modeloDocumento.getCodmodelo()));
//					item.add(new Label("descricao", modeloDocumento.getDescricao()));
//					item.add(new Label("codEspecie", modeloDocumento.getCodespecie()));
//
//					item.add(new AjaxCheckBox("check", new PropertyModel<Boolean>(modeloDocumento, "selected")) {
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onUpdate(AjaxRequestTarget target) {
//							modeloDocModel.setDescricao(modeloDocumento.getDescricao());
//							modeloDocModel.setModeloDocumento(modeloDocumento);
//							orRequisicao.setCodmodelo(modeloDocumento.getDescricao());
//							orRequisicao.setCodmodelo(modeloDocumento.getCodmodelo());
//							target.add(requisicaoContainer.get("modeloDocSelecionado"), modeloDocumentoContainer);
//							target.appendJavaScript("$('#modeloDocumentoModal').modal('hide');");													
//						}
//					});
//
//				}
//			};
//
//			this.dataVieModeloDoc.setItemsPerPage(QUANT_LINHAS_TABELAS);
//			this.navigator = new AjaxPagingNavigator("navigator", dataVieModeloDoc);
//			modeloDocumentoContainer.add(buscaDocumentoTxt, buscaDocumentoBtn, dataVieModeloDoc, navigator);
//			add(modeloDocumentoContainer);
//
//		}
//	}
//
//
//	public class NaturezaDespesasForm extends Form<Void> {
//
//		private static final long serialVersionUID = 1L;
//
//		private TextField<String> historicoNaturezaInput;
//		private AjaxButton natDespesasBtn;
//		private NaturezaProvider naturezaProvider = new NaturezaProvider(empresaModel.getNroempresa());
//		private DataView<NaturezaDespesa> dataView;
//		private PagingNavigator navigator;
//
//		public NaturezaDespesasForm(String id){
//			super(id);
//
//			FeedbackPanel feedback = new FeedbackPanel("feedback");
//			historicoNaturezaInput = new TextField<String>("historicoNaturezaInput", Model.of(""));
//
//			this.dataView = new  DataView<NaturezaDespesa>("row", naturezaProvider) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void populateItem(Item<NaturezaDespesa> item) {
//					final NaturezaDespesa naturezaDespesa = (NaturezaDespesa) item.getModelObject();
//					item.add(new Label("Codhistorico", naturezaDespesa.getCodhistorico()));
//					item.add(new Label("Historico"   , naturezaDespesa.getHistorico()));
//					item.add(new AjaxCheckBox("check", new PropertyModel<Boolean>(naturezaDespesa, "selected")) {
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onUpdate(AjaxRequestTarget target) {
//							empresaModel.setEmpresaMatriz(empresaMatrizSelected);
//							naturezaDespesaModel.setHistorico(naturezaDespesa.getHistorico());
//							naturezaDespesaModel.setCodhistorico(naturezaDespesa.getCodhistorico());
//							orReqplanilhalanctoProvider.putParameter(empresaMatrizSelected.getNroempresa(),naturezaDespesa.getCodhistorico());
//							orRequisicao.setCodhistorico(naturezaDespesa.getCodhistorico().shortValue());
//							orRequisicao.setExigeitensnota(naturezaDespesa.getExiste_itens_nota());
//							orRequisicao.setEspecienf(naturezaDespesa.getEspecie_nf());
//							target.add(requisicaoContainer.get("natDespesaSelecionada"),naturezaContainer,itensContainer,orReqplanilhalanctoContainer);
//							target.appendJavaScript("$('#consultaNatDespesasModal').modal('hide');");							
//						}
//
//					});
//				}
//			};
//
//			natDespesasBtn = new AjaxButton("natDespesasBtn", NaturezaDespesasForm.this) {			
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//					String historicoNatureza = (String)historicoNaturezaInput.getDefaultModelObject();
//					naturezaProvider.putParameter(historicoNatureza);
//					historicoNaturezaInput.setDefaultModelObject("");
//					target.add(naturezaContainer,itensContainer);
//				}
//			};		
//
//			this.dataView.setItemsPerPage(QUANT_LINHAS_TABELAS);
//			this.navigator = new AjaxPagingNavigator("navigator", dataView){
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onAjaxEvent(AjaxRequestTarget target) {
//					target.add(naturezaContainer);
//				}
//			};
//
//
//			add(naturezaContainer);
//			naturezaContainer.add(historicoNaturezaInput, natDespesasBtn, dataView, navigator, feedback);
//
//
//		}
//	}
//
//	public class CfopForm extends Form<Void>{
//		private static final long serialVersionUID = 1L;
//
//		private TextField<String> buscaCfopTxt;
//		private AjaxButton buscaCfopBtn;
//		private DataView<Cfop> cfopDataView;
//		private CfopProvider cfopProvider = new CfopProvider();
//		private AjaxPagingNavigator navigator;
//
//		public CfopForm(String id) {
//			super(id);	
//
//			this.buscaCfopTxt = new TextField<String>("buscaCfopTxt", Model.of(""));			
//			this.cfopDataView = new DataView<Cfop>("cfopDataView", cfopProvider) {
//
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				protected void populateItem(Item<Cfop> item) {
//					final Cfop cfop = item.getModelObject();
//					item.add(new Label("codigo", cfop.getCfop()));
//					item.add(new Label("descricao", cfop.getDescricaored()));
//					item.add(new AjaxCheckBox("check", new PropertyModel<Boolean>(cfop, "selected")) {
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onUpdate(AjaxRequestTarget target) {
//							cfopModel.setDescricao(cfop.getDescricaored());
//							cfopModel.setCfop(cfop.getCfop());
//							target.add(itensContainer, cfopContainer);
//							target.appendJavaScript("$('#consultaCFOPModal').modal('hide');");
//						}
//					});
//				}
//			};
//
//
//			this.buscaCfopBtn = new AjaxButton("buscaCfopBtn", this) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onAfterSubmit(AjaxRequestTarget target, Form<?> form) {
//					String descricaored = (String) buscaCfopTxt.getDefaultModelObject();
//					cfopProvider.putParameter(descricaored );
//					buscaCfopTxt.setDefaultModelObject("");
//					target.add(cfopContainer);
//				}
//			};
//
//			cfopDataView.setItemsPerPage(QUANT_LINHAS_TABELAS);
//
//			navigator = new AjaxPagingNavigator("navigator", cfopDataView){
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onAjaxEvent(AjaxRequestTarget target) {
//					target.add(cfopContainer);
//				}
//			};
//
//			cfopContainer.add(buscaCfopTxt, buscaCfopBtn, cfopDataView, navigator);
//			add(cfopContainer);
//		}		
//	}
//
//	public class ProdutosForm extends Form<Void> {
//
//		private static final long serialVersionUID = 1L;
//
//		private TextField<String> buscaProdutosTxt;
//		private AjaxButton buscaProdutosBtn;
//		private DataView<OrvProdutoTributo> produtosDataView;
//		private AjaxPagingNavigator navigator;
//		private ProdutoProvider produtoProvider;
//
//		public ProdutosForm(String id) {
//			super(id);
//
//			produtoProvider = new ProdutoProvider(empresaModel.getEmpresaMatriz().getNroempresa());
//
//			buscaProdutosTxt = new TextField<String>("buscaProdutosTxt", Model.of(""));
//			produtosDataView = new DataView<OrvProdutoTributo>("produtosDataView", produtoProvider) {		
//				private static final long serialVersionUID = 1L;
//				private Integer nroitem = 1;
//				@Override
//				protected void populateItem(Item<OrvProdutoTributo> item) {
//					final OrvProdutoTributo produtoTributo = item.getModelObject();
//					item.add(new Label("Seqproduto", produtoTributo.getSeqproduto().longValue()));
//					item.add(new Label("Descricao", produtoTributo.getDescricao()));
//					produtoTributo.setNroitem(new BigDecimal(Integer.toString(nroitem)));
//
//					item.add(new AjaxCheckBox("check", new PropertyModel<Boolean>(produtoTributo, "selected")) {
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onUpdate(AjaxRequestTarget target) {
//
//							produtoModel.setDescricao(produtoTributo.getDescricao());
//							produtoModel.setVersaoprod(produtoTributo.getVersaoprod().shortValue());
//							produtoModel.setUnidadepadrao(produtoTributo.getUnidadepadrao());
//							produtoModel.setSeqproduto(produtoTributo.getSeqproduto());
//							produtoModel.setCodproduto(new BigDecimal(produtoTributo.getCodproduto()));
//							produtoModel.setVeiculo(Character.toString(produtoTributo.getVeiculo()));
//							produtoModel.setNroitem(produtoTributo.getNroitem().shortValue());
//
//
//							target.add(itensContainer, produtosContainer);
//							target.appendJavaScript("$('#consultaProdutosModal').modal('hide');");
//						}
//					});
//
//				}			
//			};
//
//			buscaProdutosBtn = new AjaxButton("buscaProdutosBtn", this) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//					String descricao = (String)buscaProdutosTxt.getDefaultModelObject();
//					BigDecimal nroempresa = empresaModel.getEmpresaMatriz().getNroempresa();
//					produtoProvider.putParameter(descricao, nroempresa);
//					buscaProdutosTxt.setDefaultModelObject("");
//					produtosDataView.setCurrentPage(0);
//					target.add(produtosContainer);
//				}
//			};
//
//			produtosDataView.setItemsPerPage(QUANT_LINHAS_TABELAS);
//			navigator = new AjaxPagingNavigator("navigator", produtosDataView);
//			add(buscaProdutosBtn, buscaProdutosTxt, produtosDataView, navigator);
//			produtosContainer.add(buscaProdutosBtn, buscaProdutosTxt, produtosDataView, navigator);
//			add(produtosContainer);
//
//		}
//	}
//
//
//	public class ContabForm extends Form<Void> {
//
//		private static final long serialVersionUID = 1L;
//		private DataView<OrReqplanilhalancto> dataViewOrReqplanilhalancto;
//
//		TextField<Object> VlrTotalReq = new TextField<Object>("VlrTotalReq", new DefaultMoneyModel(new PropertyModel<BigDecimal>(orReqplanilhalanctoModel, "VlrTotalReq")));
//
//		public ContabForm(String id) {
//			super(id);
//			dataViewOrReqplanilhalancto = new DataView<OrReqplanilhalancto>("orReqplanilhalanctoDataView", orReqplanilhalanctoProvider) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void populateItem(Item<OrReqplanilhalancto> item) {
//					final OrReqplanilhalancto orReqplanilhalancto = (OrReqplanilhalancto) item.getModelObject();
//
//					OnChangeAjaxBehavior ajaxBehaviorVlrlancamento =  new OnChangeAjaxBehavior() {
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onUpdate(AjaxRequestTarget target) {
//							orReqplanilhalanctoProvider.putParameter(orReqplanilhalancto);
//							target.add(orReqplanilhalanctoContainer);
//						}
//					};
//
//					item.add(new Label("Empresa",orReqplanilhalancto.getNroempresa()));
//					item.add(new Label("Filial",orReqplanilhalancto.getNroempresa()));
//					item.add(new Label("Contadebito",orReqplanilhalancto.getContadebito()));
//					item.add(new Label("Centrocustodb",orReqplanilhalancto.getCentrocustodb()));
//					item.add(new Label("Contacredito",orReqplanilhalancto.getContacredito()));
//					item.add(new Label("Historico",orReqplanilhalancto.getHistorico()));
//					item.add(new TextField<Object>("Vlrlancamento",new DefaultMoneyModel(new PropertyModel<BigDecimal>(orReqplanilhalancto, "vlrlancamento"))).add(ajaxBehaviorVlrlancamento));
//
//				}
//			};
//			add(orReqplanilhalanctoContainer,VlrTotalReq,dataViewOrReqplanilhalancto);
//			orReqplanilhalanctoContainer.add(VlrTotalReq,dataViewOrReqplanilhalancto);	
//		}
//
//	}
//
//	public class VencimentoForm extends Form<Void> {
//
//		private static final long serialVersionUID = 1L;
//		private DataView<OrRequisicaovencto> valorsView;
//		private AjaxButton calcularBtn;
//		private Label Vlrliqreq;
//		private TextField<Object> Valor;
//		private TextField<Object> Vlrdescontos;
//		private TextField<Object> Vlracrescimos;
//		private TextField<String> desdobramentoParcela;
//		private List<OrRequisicaovencto> orRequisicaovenctos = Collections.synchronizedList(new ArrayList<OrRequisicaovencto>());
//
//		public VencimentoForm(String id) {
//			super(id);
//
//			OnChangeAjaxBehavior ajaxBehaviorValor =  new OnChangeAjaxBehavior() {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onUpdate(AjaxRequestTarget target) {
//					orReqplanilhalanctoModel.setVlrTotalReq(orRequisicao.getValor());
//					target.add(orReqplanilhalanctoContainer);
//				}
//			};
//
//			Valor = new TextField<Object>("Valor", new DefaultMoneyModel(new PropertyModel<BigDecimal>(orRequisicao, "valor")));
//			Vlrliqreq = new Label("Vlrliqreq", new DefaultMoneyModel(new PropertyModel<BigDecimal>(orRequisicao, "vlrliqreq")));
//			Vlrdescontos = new TextField<Object>("Vlrdescontos", new DefaultMoneyModel(new PropertyModel<BigDecimal>(orRequisicao, "vlrdescontos")));
//			Vlracrescimos = new TextField<Object>("Vlracrescimos", new DefaultMoneyModel(new PropertyModel<BigDecimal>(orRequisicao, "vlracrescimos")));			
//			desdobramentoParcela = new TextField<String>("desdobramentoParcela", new PropertyModel<String>(valoresModel, "desdobramentoParcela"));
//			observacao = new TextArea<String>("Observacao",new PropertyModel<String>(orRequisicao, "observacao"));
//			Valor.add(ajaxBehaviorValor);
//
//
//			calcularBtn = new AjaxButton("calcularBtn", VencimentoForm.this) {				
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//					try {
//						orRequisicaovenctos = calculos.parseVencimentos(valoresModel.getDesdobramentoParcela(), new Date() ,orRequisicao.getValor(), orRequisicao.getVlrdescontos(), orRequisicao.getVlracrescimos() );						
//					} catch (DesdobramentoException e) {
//						Suprimento.this.setMensagemErro(e.getMessage(), target);
//					}
//					orRequisicaovenctoProvider.putParameter(orRequisicaovenctos);
//					target.add(vencimentosContainer);
//				}
//			};
//
//			valorsView = new DataView<OrRequisicaovencto>("valorsView", orRequisicaovenctoProvider) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void populateItem(Item<OrRequisicaovencto> item) {
//					final OrRequisicaovencto orRequisicaovencto  = (OrRequisicaovencto) item.getModelObject();
//
//					final DateTextField Dtavencimento = new DateTextField("Dtavencimento", new PropertyModel<Date>(orRequisicaovencto,"Dtavencimento"),new DateTextFieldConfig()
//					.autoClose(true).withFormat("dd/MM/yyyy")
//					.withLanguage("pt-BR")
//					.withView(DateTextFieldConfig.View.Month)
//					//.showTodayButton(TodayButton.TRUE)
//					.withStartDate(new DateTime().withYear(1900)));
//
//					final DateTextField Dtaprogramada = new DateTextField("Dtaprogramada", new PropertyModel<Date>(orRequisicaovencto,"Dtaprogramada"),new DateTextFieldConfig()
//					.autoClose(true).withFormat("dd/MM/yyyy")
//					.withLanguage("pt-BR")
//					.withView(DateTextFieldConfig.View.Month)
//					//.showTodayButton(TodayButton.TRUE)
//					.withStartDate(new DateTime().withYear(1900)));
//
//					TextField<Object> Vlrtotal      = new TextField<Object>("Vlrtotal",      new DefaultMoneyModel(new PropertyModel<BigDecimal>(orRequisicaovencto, "Vlrtotal")));
//					TextField<Object> Vlrdesconto   = new TextField<Object>("Vlrdesconto",   new DefaultMoneyModel(new PropertyModel<BigDecimal>(orRequisicaovencto, "Vlrdesconto")));
//					TextField<Object> Vlracrescimo  = new TextField<Object>("Vlracrescimo",  new DefaultMoneyModel(new PropertyModel<BigDecimal>(orRequisicaovencto, "Vlracrescimo")));
//					TextField<Object> Vlrliquido    = new TextField<Object>("Vlrliquido",    new DefaultMoneyModel(new PropertyModel<BigDecimal>(orRequisicaovencto, "Vlrliquido")));
//
//
//
//					AjaxButton delete = new AjaxButton("delete", VencimentoForm.this) {				
//						private static final long serialVersionUID = 1L;
//						@Override
//						protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//							orRequisicaovenctoProvider.delte(orRequisicaovencto);
//							target.add(vencimentosContainer);
//						}
//					};
//
//					item.add(Dtavencimento);
//					item.add(Dtaprogramada);
//					item.add(Vlrtotal);
//					item.add(Vlrdesconto);
//					item.add(Vlracrescimo);
//					item.add(Vlrliquido);
//					item.add(delete);
//				}
//			};			
//
//			vencimentosContainer.add(valorsView,desdobramentoParcela, Valor, Vlrliqreq, Vlrdescontos, Vlracrescimos, calcularBtn,observacao);
//			add(vencimentosContainer);
//		}
//	}
//
//	private class OrRequisicaoForm extends Form<Void> {
//		private static final long serialVersionUID = 1L;		
//
//		private AjaxButton enviarOrRequisicaoBtn;
//		private AjaxButton limparOrRequisicaoBtn;
//
//		public OrRequisicaoForm(String id) {
//			super(id);
//
//			enviarOrRequisicaoBtn = new AjaxButton("enviarOrRequisicaoBtn", this) {		
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//
//
//					long seqrequisicao = requisicaoService.getNextSeQRequsicao();
//					orRequisicao.setSeqrequisicao(seqrequisicao); 
//
//					orRequisicao.setNroempresa(empresaModel.getEmpresaMatriz().getNroempresa().shortValue());
//					orRequisicao.setNroempresaorc(empresaModel.getEmpresaMatriz().getNroempresaorc().shortValue());
//					orRequisicao.setNroempresanatdesp(empresaModel.getEmpresaMatriz().getNroempresa().shortValue());
//
//					orReqitensdespesaProvider.getOrReqitensdespesaDatabase().getSeqrequisicao(seqrequisicao);
//					Set<OrReqitensdespesa>  orReqitensdespesas = new HashSet<OrReqitensdespesa>(orReqitensdespesaProvider.getOrReqitensdespesaDatabase().getOrReqitensdespesas());
//					orRequisicao.getOrReqitensdespesas().clear();
//					orRequisicao.setOrReqitensdespesas(orReqitensdespesas);
//
//
//					orRequisicaovenctoProvider.getOrRequisicaovenctoDatabase().getSeqrequisicao(seqrequisicao);
//					Set<OrRequisicaovencto> orRequisicaovenctos = new HashSet<OrRequisicaovencto>(orRequisicaovenctoProvider.getOrRequisicaovenctoDatabase().getOrRequisicaovenctos());
//					orRequisicao.getOrRequisicaovenctos().clear();
//					orRequisicao.setOrRequisicaovenctos(orRequisicaovenctos);
//
//
//					orReqplanilhalanctoProvider.getSeqrequisicao(seqrequisicao);
//					Set<OrReqplanilhalancto> orReqplanilhalanctos = new HashSet<OrReqplanilhalancto>(orReqplanilhalanctoProvider.getOrReqplanilhalanctos());
//					orRequisicao.getOrReqplanilhalanctos().clear();
//					orRequisicao.setOrReqplanilhalanctos(orReqplanilhalanctos);
//
//
//					Set<OrIndicador> indicadors = new HashSet<OrIndicador>();
////					indicadors.add(addOrIndicador()); 
//					orRequisicao.setIndicadors(indicadors);
//
//
//					try {
//						if (isValidateRequsicao(orRequisicao)) {
//							calculos.parseCalcular(orRequisicao);
//							try {
//								executeRestAPI.executeAssignUpdateVariableTask(user, taskId, orRequisicao, "requisicao");
//								orRequisicao.setStatus("L");
//								requisicaoService.criaRequsicao(orRequisicao);
//								Suprimento.this.exibeMensagemSucesso("ERP", "Enviado para consinco", target);
//								setResponsePage(Atividades.class);
//							}catch (BonitaException e) {
//								Suprimento.this.setMensagemErro(e.getMessage(), target);
//							}catch (Exception e) {
//								Suprimento.this.setMensagemErro("Erro inesperado "+e.getMessage(), target);	
//							}
//						}
//					}catch (SuprimentoException e) {
//						Suprimento.this.setMensagemErro(e.getMessage(), target);
//					}catch (CalculoException e ) {
//						Suprimento.this.setMensagemErro(e.getMessage(), target);
//					}catch (Exception e ) {
//						Suprimento.this.setMensagemErro("Erro inesperado "+e.getMessage(), target);
//					}
//
//				}
//
//			};
//
//			limparOrRequisicaoBtn = new AjaxButton("limparOrRequisicaoBtn",	this) {
//				private static final long serialVersionUID = 1L;
//				@Override
//				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//					Suprimento.this.exibeMensagemSucesso("Sucesso", "mensagem de Sucesso", target);
//				}
//			};
//
//			add(new WebMarkupContainer("enviarOrRequisicaoContainer").add(enviarOrRequisicaoBtn, limparOrRequisicaoBtn));
//		}
//	}
//
////	public OrIndicador addOrIndicador() {
////		OrIndicador orIndicador = new OrIndicador();
////		orIndicador.setData(new java.util.Date());
////		orIndicador.setUsuario(username);
////		orIndicador.setUsuarioID(idUser);
////		orIndicador.setObservacao("Requisição criada pela BPMN");
////		return orIndicador;
////	}
//
//
//	public Boolean isValidateRequsicao(OrRequisicao orRequisicao) throws SuprimentoException {
//
//		if(orRequisicao.getCodhistorico()==null) {
//			throw new SuprimentoException("É necessário adicionar uma natureza de despesa !");
//		}
//
//		if(orRequisicao.getSeqpessoa()==null) {
//			throw new SuprimentoException("É necessário adicionar um fornecedor !");
//		}
//
//		if(orRequisicao.getCodmodelo()==null) {
//			throw new SuprimentoException("É necessário adicionar um modelo de nota fiscal !");
//		}
//
//		return Boolean.TRUE;
//
//	}
//
//
//}
