package br.com.seta.processo.cadastrofornecedores;

import static br.com.seta.processo.cadastrofornecedores.AtividadeCadastroFornecedor.APROVAR_CADASTRO_FORNECEDOR;
import static br.com.seta.processo.cadastrofornecedores.AtividadeCadastroFornecedor.AVALIAR_CADASTRO_FORNECEDOR;
import static br.com.seta.processo.cadastrofornecedores.AtividadeCadastroFornecedor.CORRIGIR_CADASTRO_FORNECEDOR;
import static br.com.seta.processo.cadastrofornecedores.AtividadeCadastroFornecedor.PRE_ANALISE_CADASTRO;
import static br.com.seta.processo.dto.EstadosCadastroFornecedores.INTEGRADO_CONSINCO;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;
import org.jboss.logging.Logger;

import br.com.seta.processo.bean.GePessoas;
import br.com.seta.processo.bean.map.MapFornecedor;
import br.com.seta.processo.bean.parse.ParseFile;
import br.com.seta.processo.cadastrofornecedores.dominios.Banco;
import br.com.seta.processo.cadastrofornecedores.dominios.Categoria;
import br.com.seta.processo.cadastrofornecedores.dominios.Estados;
import br.com.seta.processo.cadastrofornecedores.dominios.FormaPagamento;
import br.com.seta.processo.cadastrofornecedores.dominios.Fornecedor;
import br.com.seta.processo.cadastrofornecedores.dominios.MotivoRejeicao;
import br.com.seta.processo.cadastrofornecedores.dominios.SimNao;
import br.com.seta.processo.cadastrofornecedores.dominios.Solicitacao;
import br.com.seta.processo.cadastrofornecedores.dominios.TipoDaConta;
import br.com.seta.processo.cadastrofornecedores.dominios.TipoDeConta;
import br.com.seta.processo.cadastrofornecedores.dominios.TipoFrete;
import br.com.seta.processo.cadastrofornecedores.dominios.TipoLogradouro;
import br.com.seta.processo.constant.StatusCadastroFornecedor;
import br.com.seta.processo.constant.VariaveisCadastroFornecedores;
import br.com.seta.processo.dominios.TipoPessoa;
import br.com.seta.processo.dto.AprovacaoGerenciaComercial;
import br.com.seta.processo.dto.CadastroFornecedor;
import br.com.seta.processo.dto.EstadosCadastroFornecedores;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.HistoricoFornecedor;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.exceptions.CadastroFornecedorException;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.exceptions.ParseException;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.helper.WicketHelper;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.page.TaskPage;
import br.com.seta.processo.provider.EnviaFornecedor;
import br.com.seta.processo.service.DadosUsuarioService;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.validacao.ValidadorBean;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaFisicaCheck;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaJuridicaCheck;

public class VisualizarPlanilha extends TaskPage {
	
	private static final long serialVersionUID = 1L;

	private static final String _SEPARATOR = System.getProperty("file.separator");
	private static final String _FOLDER = System.getProperty("user.home")+_SEPARATOR;

	private static Logger logger = Logger.getLogger(VisualizarPlanilha.class);
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	
	private WebMarkupContainer secaoBotoesAprovador, secaoBotoesCadastro, secaoBotoesComercial;
	private AjaxButton enviarConsincoBtn;
	private Button voltarBtn, solicitarCorrecaoBtn;
	private Form<Void> planilhaFornecedorForm;	
	private PlanilhaFornecedoresContainer planilhaFornecedoresContainer;
	private WebMarkupContainer salvarAlteracoesContainer;
	private Form<Void> aprovacaoModalForm, rejeicaoModalForm, importacaoPlanilhaModalForm, correcaoModalForm, sucessoCadastroForm;
	private Label nroFornecedorConsincoLabel;
	private PageReference paginaAnterior;
	private AtividadeCadastroFornecedor tipoAtividade;
	private FormularioFornecedor formularioFornecedor, formularioTemp;
	private StatusCadastroFornecedor statusCadastroFornecedor;
	private CadastroFornecedor cadastroFornecedor;
	
	@Inject	private ExecuteRestAPI restApi;
	@Inject	private ParseFile parseFile;
	@Inject	private MapFornecedor mapFornecedor;
	@Inject	private GePessoas gePessoas;
	@Inject	private EnviaFornecedor enviaFornecedor;
	@Inject	private ValidadorBean validador;	
	@Inject	private DadosUsuarioService dadosUsuarioService;
	
	private transient User user = (User) Session.get().getAttribute("user");

	public VisualizarPlanilha(PageParameters pageParameters, PageReference pageReference, FormularioFornecedor fornecedor) throws HttpStatus401Exception, org.apache.http.ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException, CadastroFornecedorException{
		super(pageParameters);	

		//Recuperar os dados do Bonita BPM
		this.formularioFornecedor = fornecedor;
		this.statusCadastroFornecedor = (StatusCadastroFornecedor)restApi.retriveVariableTask(user, this.getTaskId(), StatusCadastroFornecedor.class, VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR);
		this.cadastroFornecedor = (CadastroFornecedor) restApi.retriveVariableTask(user, this.getTaskId(), CadastroFornecedor.class, VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR);
		this.cadastroFornecedor.setCaseId(getCaseId());
		this.formularioFornecedor.setCaseId(getCaseId());
				
		paginaAnterior = pageReference;
		tipoAtividade = obtemTipoAtividade();
		planilhaFornecedorForm = new Form<Void>("planilhaFornecedoresForm");		
		voltarBtn = new Button("voltarBtn"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		iniciaSecaoBotoes();
		iniciaPlanilhaFornecedores();
		iniciaModals();
		planilhaFornecedorForm.add(voltarBtn, secaoBotoesAprovador, secaoBotoesCadastro, secaoBotoesComercial, planilhaFornecedoresContainer, salvarAlteracoesContainer);
		add(planilhaFornecedorForm, aprovacaoModalForm, rejeicaoModalForm, importacaoPlanilhaModalForm, correcaoModalForm, sucessoCadastroForm);
	}

	/**
	 * Regras de visibilidade das seções de botões
	 * 
	 * @throws CadastroFornecedorException
	 */
	private void iniciaSecaoBotoes() throws CadastroFornecedorException {
		this.secaoBotoesAprovador = new WebMarkupContainer("secaoBotoesAprovador");
		this.secaoBotoesCadastro = (WebMarkupContainer) new WebMarkupContainer("secaoBotoesCadastro").setOutputMarkupId(true);
		this.secaoBotoesComercial = new WebMarkupContainer("secaoBotoesComercial");

		switch (tipoAtividade) {
		case APROVAR_CADASTRO_FORNECEDOR:
			this.secaoBotoesAprovador.setVisible(true);
			this.secaoBotoesCadastro.setVisible(false);
			this.secaoBotoesComercial.setVisible(false);
			break;
			
		case AVALIAR_CADASTRO_FORNECEDOR:
			this.secaoBotoesAprovador.setVisible(false);
			this.secaoBotoesCadastro.setVisible(true);
			this.secaoBotoesComercial.setVisible(false);
			break;
			
		case PRE_ANALISE_CADASTRO:
			
		case CORRIGIR_CADASTRO_FORNECEDOR:
			this.secaoBotoesAprovador.setVisible(false);
			this.secaoBotoesCadastro.setVisible(false);
			this.secaoBotoesComercial.setVisible(true);
			break;
			
		default:
			break;
		}

		iniciaSecaoBotoesCadastro();
	}

	/**
	 * Regras de visibilidade de planilha de fornecedores
	 */
	private void iniciaPlanilhaFornecedores(){
		planilhaFornecedoresContainer = (PlanilhaFornecedoresContainer) new PlanilhaFornecedoresContainer("planilhaFornecedoresContainer")
		.setOutputMarkupId(true);

		if(tipoAtividade.equals(AtividadeCadastroFornecedor.CORRIGIR_CADASTRO_FORNECEDOR) || tipoAtividade.equals(AtividadeCadastroFornecedor.PRE_ANALISE_CADASTRO)){
			planilhaFornecedoresContainer.setEnabled(true);
		}else{
			planilhaFornecedoresContainer.setEnabled(false);
		}
	}

	/**
	 * Método que inicia os modals existentes na página
	 */
	private void iniciaModals(){
		this.aprovacaoModalForm = new AprovacaoModalForm("aprovacaoModalForm");
		this.rejeicaoModalForm = new RejeicaoModalForm("rejeicaoModalForm");
		this.importacaoPlanilhaModalForm = new ImportacaoPlanilhaModalForm("importacaoPlanilhaModalForm");
		this.salvarAlteracoesContainer = new SalvarAlteracoesContainer("salvarAlteracoesContainer");
		this.correcaoModalForm = new CorrecaoModalForm("correcaoModalForm");		
		this.sucessoCadastroForm = new SucessoCadastroForm("sucessoCadastroForm");
	}	

	/**
	 * Regras de inicialização dos botões da seção cadastro
	 */
	private void iniciaSecaoBotoesCadastro(){
		enviarConsincoBtn = new AjaxButton("enviarConsincoBtn", planilhaFornecedorForm) {
			private static final long serialVersionUID = 1L; 

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {	
				try {
					validaDados();
					
					boolean cnpjCpfExistente = Boolean.TRUE;

					if (formularioFornecedor.getCpnjJuridico()!=null) {
						cnpjCpfExistente  =  isExisteCnpjCpf(formularioFornecedor.getCpnjJuridico());
					}
					if (formularioFornecedor.getCpfFisica()!=null) {
						cnpjCpfExistente  = isExisteCnpjCpf(formularioFornecedor.getCpfFisica());
					}
					if (cnpjCpfExistente == false) {
						/******
						 *  Cria fornecedor na C5
						 */
						BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
						beanUtilsBean.getConvertUtils().register(false, false, 0);
						String seqFornecedorConsinco = mapFornecedor.create( (FormularioFornecedor) beanUtilsBean.cloneBean(formularioFornecedor));
						
						solicitarCorrecaoBtn.setVisible(false);
						enviarConsincoBtn.setVisible(false);
					
						nroFornecedorConsincoLabel.setDefaultModelObject(seqFornecedorConsinco);						
						formularioFornecedor.setIdFornecedorC5(seqFornecedorConsinco);
						
						/****
						 * 
						 *  Verifica origem do cadastro se portal ou bpm
						 * 
						 */
						if (formularioFornecedor.getIsPrecadastro()==null) {
							if (formularioFornecedor.getTipoPessoa().equals(TipoPessoa.JURIDICA)) {
								formularioFornecedor.setIdentificador(formularioFornecedor.getCpnjJuridico());
							}else {
								formularioFornecedor.setIdentificador(formularioFornecedor.getCpfFisica());
							}
						}else {
						}
						
						target.add(secaoBotoesCadastro, nroFornecedorConsincoLabel);
						ocultaCarregamento(target);
						target.appendJavaScript("$('#sucessoCriacaoFornecedorModal').modal('show')");
						
						Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
						processVariables.put(VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR, EstadosCadastroFornecedores.FINALIZADO); 
						processVariables.put(VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR, cadastroFornecedor);
						processVariables.put(VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR, formularioFornecedor);
						restApi.executeFlowAndUpdateVariable(user, getTaskId(), processVariables);
					
						Map<String, String> objectsC5 = new HashMap<String, String>();
						objectsC5.put("seqFornecedor",seqFornecedorConsinco);
						objectsC5.put("idUsuario",cadastroFornecedor.getIdUsuario());
						objectsC5.put("formularioAprovado",Boolean.TRUE.toString());
						objectsC5.put("emailContato",formularioFornecedor.getEmailContato());
						
						if (formularioFornecedor.getTipoPessoa().equals(TipoPessoa.JURIDICA)) {
							objectsC5.put("cnpjCpf",formularioFornecedor.getCpnjJuridico());
							objectsC5.put("razao",formularioFornecedor.getRazaoSocialReduzido());
							objectsC5.put("usuarioTipo","J");
						}else {
							objectsC5.put("usuarioTipo","F");
							objectsC5.put("cnpjCpf",formularioFornecedor.getCpfFisica());
							objectsC5.put("razao",formularioFornecedor.getNomeReduzido());
						}
		                		
						enviaFornecedor.env(objectsC5);
												
						logger.info("Cadastro finalizado por " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
					}else {
						ocultaCarregamento(target);
						setMensagemErro("Fornecedor já cadastrado com este cpnj ou cpf na consinco", target);
					}
					
				} 
				catch (ValidacaoBeanException e) {
					setMensagensErro(e.getMessages(), target);
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		};	
		
		solicitarCorrecaoBtn = (Button) new Button("solicitarCorrecaoBtn").setOutputMarkupId(true);		

		if(statusCadastroFornecedor != null && statusCadastroFornecedor.equals(INTEGRADO_CONSINCO)){
			solicitarCorrecaoBtn.setVisible(false);
			enviarConsincoBtn.setVisible(false);
		}

		secaoBotoesCadastro.add(solicitarCorrecaoBtn, enviarConsincoBtn);
	}

	/**
	 * Obtem o tipo da atividade do processo de Cadastro de Fornecedor
	 * 
	 * @return Uma constante representando uma atividade do processo
	 * @throws CadastroFornecedorException Caso não seja possível encontrar a atividade
	 */
	private AtividadeCadastroFornecedor obtemTipoAtividade() throws CadastroFornecedorException{
		String atividade = getHumanTask().getName();

		switch(atividade){
		case "Aprovar Cadastro de Fornecedor" : 
			return APROVAR_CADASTRO_FORNECEDOR;
		case "Avaliar dados - Cadastro Fornecedor" : 
			return AVALIAR_CADASTRO_FORNECEDOR;
		case "Verificar a solicitação  de correção para o cadastro do Fornecedor" :
			return CORRIGIR_CADASTRO_FORNECEDOR;
		case "Pré-Análise de Cadastro":
			return AtividadeCadastroFornecedor.PRE_ANALISE_CADASTRO;
		}
			
		throw new CadastroFornecedorException("Não foi possível mapear o valor " + atividade + " para uma atividade do processo de Cadastro de Fornecedor");		
	}

	private class SalvarAlteracoesContainer extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private Label usuarioSalvarCorrecao;
		private TextArea<String> comentariosAlteracoes;
		private AjaxButton salvarAlteracoesBtn;

		public SalvarAlteracoesContainer(String id) {
			super(id);

			usuarioSalvarCorrecao = new Label("usuarioSalvarCorrecao", getNomeCompletoUsuario());
			comentariosAlteracoes = new TextArea<String>("comentariosAlteracoes", Model.of(""));
			salvarAlteracoesBtn = new AjaxButton("salvarAlteracoesBtn", planilhaFornecedorForm){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {	
					try {	
						validaDados();
						Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();
						responseVariables.put(VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR, cadastroFornecedor);
						
						if(tipoAtividade == PRE_ANALISE_CADASTRO){
							responseVariables.put(VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR, EstadosCadastroFornecedores.PENDENTE_DE_APROVACAO);
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAlteracoes.getDefaultModelObject(), StatusCadastroFornecedor.CADASTRO_PRE_ANALISADO);
						}else{
							responseVariables.put(VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR, EstadosCadastroFornecedores.CORRIGIDO);
							adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAlteracoes.getDefaultModelObject(), StatusCadastroFornecedor.SOLICITANTE_CORRIGIDO);
						}
						
						responseVariables.put(VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR, formularioFornecedor);
						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
						setResponsePage(Atividades.class);
						
						logger.info("Alterações do formulário foram salvas por " + user.getUserName() + " com sucesso. CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId() +	"\ndados do formulário: " + formularioFornecedor);
						
					}  catch (BonitaException e) {
						logger.error("Ocorreu um erro na atividade " + getHumanTask().getDisplayName() +" no momento de salvar os dados alterados pelo usuário " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
						throw new RuntimeException(e);
					} catch (ValidacaoBeanException e) {
						setMensagensErro(e.getMessages(), target);
					} 
					
				}				
			};	

			add(usuarioSalvarCorrecao, comentariosAlteracoes, salvarAlteracoesBtn);
		}		
	}

	private class AprovacaoModalForm extends Form<Void>{
		private static final long serialVersionUID = 1L;

		private TextArea<String> comentariosAprovacao;
		private AjaxButton enviarAprovacaoBtn;
		private Label nomeAprovador;

		public AprovacaoModalForm(String id) {
			super(id);
			nomeAprovador = new Label("nomeAprovador", getNomeCompletoUsuario());
			comentariosAprovacao = new TextArea<String>("comentariosAprovacao", Model.of(""));
			enviarAprovacaoBtn = new AjaxButton("enviarAprovacaoBtn", this){				
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosAprovacao.getDefaultModelObject(), StatusCadastroFornecedor.GERENTE_COMERCIAL_APROVADO);

					AprovacaoGerenciaComercial aprovacao = new AprovacaoGerenciaComercial(getNomeCompletoUsuario(), new Date(), (String)comentariosAprovacao.getDefaultModelObject());
					formularioFornecedor.setAprovacaoGerenciaComercial(aprovacao);

					Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();
					responseVariables.put(VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR, cadastroFornecedor);
					responseVariables.put(VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR, EstadosCadastroFornecedores.APROVADO_PELO_GERENTE);
					responseVariables.put(VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR, formularioFornecedor);

					try {
						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
						logger.info("Atividade " + getHumanTask().getDisplayName() + ". O cadastro do fornecedor foi aprovado por " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
						logger.info("Formulário Fornecedor: " + formularioFornecedor);
					} catch (BonitaException e) {
						logger.error("Ocorreu um erro na atividade " + getHumanTask().getDisplayName() +" no momento da aprovação do cadastro de fornecedor pelo usuário " + user.getUserName());
						throw new RuntimeException(e);
					}					

					setResponsePage(Atividades.class);
				}
			};

			add(nomeAprovador, comentariosAprovacao, enviarAprovacaoBtn);
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
					
					adicionaItemAoHistorico(getNomeCompletoUsuario(), motivoRejeicaoSelecionado, (String)comentariosRejeicao.getDefaultModelObject(), StatusCadastroFornecedor.GERENTE_COMERCIAL_REJEITADO);
					Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();
					responseVariables.put(VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR, cadastroFornecedor);
					responseVariables.put(VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR, EstadosCadastroFornecedores.REJEITADO_PELO_GERENTE);
					responseVariables.put(VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR, formularioFornecedor);
										
					try {

						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
						logger.info("Atividade " + getHumanTask().getDisplayName() + ". O cadastro do fornecedor foi rejeitado por " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
						logger.info("Formulário Fornecedor: " + formularioFornecedor);
						
						Map<String, String> objectsC5 = new HashMap<String, String>();
						objectsC5.put("formularioAprovado",Boolean.FALSE.toString());
						enviaFornecedor.env(objectsC5);
						
					} catch (BonitaException e) {
						logger.error("Ocorreu um erro na atividade " + getHumanTask().getDisplayName() +"no momento da rejeição do cadastro de fornecedor pelo usuário " + user.getUserName());
						throw new RuntimeException(e);
					}
					setResponsePage(Atividades.class);
				}
			};			
			add(nomeAprovadorRejeicao, motivosRejeicao, comentariosRejeicao, enviarRejeicaoBtn);			
		}

	}

	private class CorrecaoModalForm extends Form<Void>{	
		private static final long serialVersionUID = 1L;

		private Label usuarioSolicitacaoCorrecao;
		private TextArea<String> comentariosCorrecao;
		private AjaxButton enviarSolicitacaoCorrecaoBtn;

		public CorrecaoModalForm(String id) {
			super(id);

			usuarioSolicitacaoCorrecao = new Label("usuarioSolicitacaoCorrecao", getNomeCompletoUsuario());
			comentariosCorrecao = new TextArea<String>("comentariosCorrecao", Model.of(""));
			enviarSolicitacaoCorrecaoBtn = new AjaxButton("enviarSolicitacaoCorrecaoBtn", this){		
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {					
					
					adicionaItemAoHistorico(getNomeCompletoUsuario(), "", (String)comentariosCorrecao.getDefaultModelObject(), StatusCadastroFornecedor.CADASTRO_SOLICITACAO_CORRECAO);
					Map<String, Serializable> responseVariables = new HashMap<String, Serializable>();
					cadastroFornecedor.setHistoricoFornecedors(formularioFornecedor.getHistoricoFornecedores());
					responseVariables.put(VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR, cadastroFornecedor);
					responseVariables.put(VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR, EstadosCadastroFornecedores.INCORRETO);
					responseVariables.put(VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR, formularioFornecedor);

					try {
						restApi.executeFlowAndUpdateVariable(user, getTaskId(), responseVariables);
						logger.info("Enviado para correção por " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
						logger.info("Formulário Fornecedor: " + formularioFornecedor);
					} catch (BonitaException e) {
						logger.error("Ocorreu um erro na atividade " + getHumanTask().getDisplayName() + " no momento de enviar para a correção pelo usuário " + user.getUserName() + ". CaseId: " + getHumanTask().getCaseId() + " TaskId: " + getTaskId());
						throw new RuntimeException(e);
					}
					setResponsePage(Atividades.class);
				}
			};		

			add(usuarioSolicitacaoCorrecao, comentariosCorrecao, enviarSolicitacaoCorrecaoBtn);
		}		
	}

	private class ImportacaoPlanilhaModalForm extends Form<Void>{
		private static final long serialVersionUID = 1L;

		private FileUploadField fileUploadField;
		private AjaxButton importBtn; 

		public ImportacaoPlanilhaModalForm(String id) {
			super(id);			
			fileUploadField = new FileUploadField("fileUploadField");

			importBtn = new AjaxButton("importBtn", this){
				private static final long serialVersionUID = 1L;				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					if(fileUploadField == null || fileUploadField.getFileUpload() == null){
						return;						
					}

					FileUpload fileUpload = fileUploadField.getFileUpload();
					File file = new File(_FOLDER + fileUpload.getClientFileName());
					try{
						fileUpload.writeTo(file);
						formularioTemp = parseFile.parse(file);							
						
						BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
						beanUtilsBean.getConvertUtils().register(false, false, 0);
						beanUtilsBean.copyProperties(formularioFornecedor, formularioTemp);					
						
						formularioTemp.setHistoricoFornecedores(formularioFornecedor.getHistoricoFornecedores());
						formularioTemp.setAprovacaoGerenciaComercial(formularioFornecedor.getAprovacaoGerenciaComercial());

						target.add(planilhaFornecedoresContainer);
						target.appendJavaScript("$('#importarPlanilhaModal').modal('hide');");
						exibeMensagemSucesso("Arquivo", "Arquivo importado com sucesso!", target);
					}catch(IOException | ParseException e){
						logger.error(e);
						VisualizarPlanilha.this.setMensagemErro("Este tipo de arquivo não é válido para importar!", target);
					}catch(Exception e){
						logger.error(e);
						VisualizarPlanilha.this.setMensagemErro("Ocorreu um erro durante a importação do arquivo", target);
					}
				}				
			};			
			add(fileUploadField, importBtn);
		}		
	
	}
	
	private class SucessoCadastroForm extends Form<Void>{	
		private static final long serialVersionUID = 1L;
		
		private AjaxButton finalizarCadastroBtn;
		
		public SucessoCadastroForm(String id) {
			super(id);
			nroFornecedorConsincoLabel = (Label) new Label("nroFornecedorConsincoLabel", Model.of("")).setOutputMarkupId(true);
			this.finalizarCadastroBtn = new AjaxButton("finalizarCadastroBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					setResponsePage(Atividades.class);
				}
			};
			
			add(nroFornecedorConsincoLabel, finalizarCadastroBtn);
		}		
	}		

	private class PlanilhaFornecedoresContainer extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private SecaoPF secaoPF;
		private SecaoPJ secaoPJ;
		
		private TextField<String> logradouro, numero, complemento,bairro, cidade, cep,
		nomeTitularConta, agenciaTitularConta, cpfCnpjTitularConta, contaTitularConta, prazoPagamento, prazoEntrega, prazoAtrasoEntrega, prazoVisita,
		emailContato,emailParaNf, foneComercial, foneFiscal, foneFinanceiro, homePage,
		nomeCompletoReponsavelAcordo, cpfReponsavelAcordo, rgReponsavelAcordo, cargoReponsavelAcordo, foneReponsavelAcordo, emailReponsavelAcordo,
		nomePrepostoRepresentante, cpfPrepostoRepresentante, rgPrepostoRepresentante, cargoPrepostoRepresentante, fonePrepostoRepresentante, emailPrepostoRepresentante,
		digitoAgenciaTitularConta, digitoContaTitularConta, enderecoTitularConta, numeroTitularConta, bairroTitularConta, cidadeTitularConta, cepTitularConta,
		observacaoTitularConta, observacaoEntrega, ddFoneComercial, ddFoneFiscal, ddFoneFinanceiro, ddFoneReponsavelAcordo, 
		complementoTitularConta, ddFonePrepostoRepresentante;

		private TextArea<String> dadosAdicionais;
		private DropDownChoice<String> formaPagamento, bancoTitularConta, tipoDaConta, tipoDeConta, tipoSolicatacao, categoria, comprador, ufTitularConta, 
		tipoFornecedor, tipoLogradouro, tipoFrete, estadoUf, tipoPessoa;
		private CheckBox ieProdutorRural, contribuinteIPI, microEmpresa;
		
		private RadioGroup<String> recebeNfDevolucao, negocicacaoBoleto, trocaNfBonificacao, 
								   trocaProdutoProduto, trocaRecolheProduto, indenizaTroca, acordoComercial;
		
		private WebMarkupContainer grpIeProdutorRural, grpContribuinteIPI, grpMicroEmpresa;
		
		public PlanilhaFornecedoresContainer(String id) {
			super(id);

			formularioFornecedor.setTipoSolicatacao(Solicitacao.INCLUSÃO);
			if(formularioFornecedor.getTipoPessoa() == null || formularioFornecedor.getTipoPessoa().isEmpty())
				formularioFornecedor.setTipoPessoa(TipoPessoa.JURIDICA);
			
			
			secaoPF = new SecaoPF("secaoPF");
			secaoPJ = new SecaoPJ("secaoPJ");
			final WebMarkupContainer secaoDadosFornecedor = 
					(WebMarkupContainer) new WebMarkupContainer("secaoDadosFornecedor"){
						private static final long serialVersionUID = 1L;
						
						@Override
						protected void onConfigure() {			
							super.onConfigure();
							
							if(ehPessoaFisica()){
								removeDadosPJ();
								secaoPF.setVisible(true);
								secaoPJ.setVisible(false);
								grpIeProdutorRural.setVisible(false);
								grpContribuinteIPI.setVisible(false);
								grpMicroEmpresa.setVisible(false);
							}else{
								removeDadosPF();
								secaoPF.setVisible(false);
								secaoPJ.setVisible(true);
								grpIeProdutorRural.setVisible(true);
								grpContribuinteIPI.setVisible(true);
								grpMicroEmpresa.setVisible(true);
							}
							
						}
			}.setOutputMarkupId(true);
			
			tipoPessoa = new DropDownChoice<String>("tipoPessoa", new PropertyModel<String> (formularioFornecedor, "tipoPessoa"), TipoPessoa.getValores()){
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isNullValid() {
					return false;
				}
			};			
			tipoPessoa.add(new AjaxFormComponentUpdatingBehavior("change"){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					target.add(secaoDadosFornecedor);
					target.appendJavaScript("iniciarCamposData()");
				}
				
			});				

			ieProdutorRural = (CheckBox) new CheckBox("ieProdutorRural", new PropertyModel<Boolean>(formularioFornecedor, "ieProdutorRural")).setOutputMarkupId(true);
			contribuinteIPI = (CheckBox) new CheckBox("contribuinteIPI", new PropertyModel<Boolean>(formularioFornecedor, "contribuinteIPI")).setOutputMarkupId(true);
			microEmpresa = (CheckBox) new CheckBox("microEmpresa", new PropertyModel<Boolean>(formularioFornecedor, "microEmpresa")).setOutputMarkupId(true);
			
			grpIeProdutorRural = new WebMarkupContainer("grpIeProdutorRural");
			grpIeProdutorRural.add(ieProdutorRural);
			grpContribuinteIPI = new WebMarkupContainer("grpContribuinteIPI");
			grpContribuinteIPI.add(contribuinteIPI);
			grpMicroEmpresa = new WebMarkupContainer("grpMicroEmpresa");
			grpMicroEmpresa.add(microEmpresa);
			
			
			tipoSolicatacao = new DropDownChoice<String>("tipoSolicatacao", new PropertyModel<String>(formularioFornecedor, "tipoSolicatacao"), Solicitacao.getTiposSolicitacao());
			tipoSolicatacao.setEnabled(false);
			categoria = new DropDownChoice<String>("categoria", new PropertyModel<String>(formularioFornecedor, "categoria"), Categoria.getCategorias());
			comprador = new DropDownChoice<String>("comprador", new PropertyModel<String>(formularioFornecedor, "comprador"), compradores());
			tipoFornecedor = new DropDownChoice<String>("tipoFornecedor", new PropertyModel<String>(formularioFornecedor, "tipoFornecedor"), Fornecedor.getFornecedores());			
			estadoUf = new DropDownChoice<String>("estadoUf", new PropertyModel<String>(formularioFornecedor, "estadoUf"), Estados.getUFs());			

			tipoLogradouro = new DropDownChoice<String>("tipoLogradouro", new PropertyModel<String>(formularioFornecedor, "tipoLogradouro"), TipoLogradouro.getTiposLogradouro());
			logradouro = new TextField<String>("logradouro", new PropertyModel<String>(formularioFornecedor, "logradouro"));
			numero = new TextField<String>("numero", new PropertyModel<String>(formularioFornecedor, "numero"));
			complemento = new TextField<String>("complemento", new PropertyModel<String>(formularioFornecedor, "complemento"));
			bairro = new TextField<String>("bairro", new PropertyModel<String>(formularioFornecedor, "bairro"));
			cidade = new TextField<String>("cidade", new PropertyModel<String>(formularioFornecedor, "cidade"));
			cep = new TextField<String>("cep", new PropertyModel<String>(formularioFornecedor, "cep"));

			nomeTitularConta = new TextField<String>("nomeTitularConta", new PropertyModel<String>(formularioFornecedor, "nomeTitularConta"));
			cpfCnpjTitularConta = new TextField<String>("cpfCnpjTitularConta", new PropertyModel<String>(formularioFornecedor, "cpfCnpjTitularConta"));
			bancoTitularConta = new DropDownChoice<String>("bancoTitularConta", new PropertyModel<String>(formularioFornecedor, "bancoTitularConta"), Banco.getBancos());
			agenciaTitularConta = new TextField<String>("agenciaTitularConta", new PropertyModel<String>(formularioFornecedor, "agenciaTitularConta"));
			contaTitularConta = new TextField<String>("contaTitularConta", new PropertyModel<String>(formularioFornecedor, "contaTitularConta"));
			formaPagamento = new DropDownChoice<String>("formaPagamento", new PropertyModel<String>(formularioFornecedor, "formaPagamento"), FormaPagamento.getFormasPagamento());
			tipoDaConta = new DropDownChoice<String>("tipoDaConta", new PropertyModel<String>(formularioFornecedor, "tipoDaConta"), TipoDaConta.getTiposDaConta());
			tipoDeConta = new DropDownChoice<String>("tipoDeConta", new PropertyModel<String>(formularioFornecedor, "tipoDeConta"), TipoDeConta.getTiposDeConta());
			prazoPagamento = new TextField<String>("prazoPagamento", new PropertyModel<String>(formularioFornecedor, "prazoPagamento"));
			prazoEntrega = new TextField<String>("prazoEntrega", new PropertyModel<String>(formularioFornecedor, "prazoEntrega"));
			prazoAtrasoEntrega = new TextField<String>("prazoAtrasoEntrega", new PropertyModel<String>(formularioFornecedor, "prazoAtrasoEntrega"));
			prazoVisita = new TextField<String>("prazoVisita", new PropertyModel<String>(formularioFornecedor, "prazoVisita"));
			digitoAgenciaTitularConta = new TextField<String>("digitoAgenciaTitularConta", new PropertyModel<String>(formularioFornecedor, "digitoAgenciaTitularConta"));
			digitoContaTitularConta = new TextField<String>("digitoContaTitularConta", new PropertyModel<String>(formularioFornecedor, "digitoContaTitularConta"));
			enderecoTitularConta = new TextField<String>("enderecoTitularConta", new PropertyModel<String>(formularioFornecedor, "enderecoTitularConta")); 
			numeroTitularConta = new TextField<String>("numeroTitularConta", new PropertyModel<String>(formularioFornecedor, "numeroTitularConta"));
			complementoTitularConta = new TextField<String>("complementoTitularConta", new PropertyModel<String>(formularioFornecedor, "complementoTitularConta"));
			bairroTitularConta = new TextField<String>("bairroTitularConta", new PropertyModel<String>(formularioFornecedor, "bairroTitularConta")); 
			cidadeTitularConta = new TextField<String>("cidadeTitularConta", new PropertyModel<String>(formularioFornecedor, "cidadeTitularConta"));
			ufTitularConta = new DropDownChoice<String>("ufTitularConta", new PropertyModel<String>(formularioFornecedor, "ufTitularConta"), Estados.getUFs());
			cepTitularConta = new TextField<String>("cepTitularConta", new PropertyModel<String>(formularioFornecedor, "cepTitularConta"));
			observacaoTitularConta = new TextField<String>("observacaoTitularConta", new PropertyModel<String>(formularioFornecedor, "observacaoTitularConta"));

			emailContato =  new TextField<String>("emailContato", new PropertyModel<String>(formularioFornecedor, "emailContato"));
			emailParaNf =  new TextField<String>("emailParaNf", new PropertyModel<String>(formularioFornecedor, "emailParaNf"));
			foneComercial =  new TextField<String>("foneComercial", new PropertyModel<String>(formularioFornecedor, "foneComercial"));
			foneFiscal =  new TextField<String>("foneFiscal", new PropertyModel<String>(formularioFornecedor, "foneFiscal"));
			foneFinanceiro =  new TextField<String>("foneFinanceiro", new PropertyModel<String>(formularioFornecedor, "foneFinanceiro"));
			homePage =  new TextField<String>("homePage", new PropertyModel<String>(formularioFornecedor, "homePage"));

			tipoFrete = new DropDownChoice<String>("tipoFrete", new PropertyModel<String>(formularioFornecedor, "tipoFrete"), TipoFrete.getTiposFrete());						
			observacaoEntrega = new TextField<String>("observacaoEntrega", new PropertyModel<String>(formularioFornecedor, "observacaoEntrega"));

			nomeCompletoReponsavelAcordo = new TextField<String>("nomeCompletoReponsavelAcordo", new PropertyModel<String>(formularioFornecedor, "nomeCompletoReponsavelAcordo"));
			cpfReponsavelAcordo = new TextField<String>("cpfReponsavelAcordo", new PropertyModel<String>(formularioFornecedor, "cpfReponsavelAcordo"));
			rgReponsavelAcordo = new TextField<String>("rgReponsavelAcordo", new PropertyModel<String>(formularioFornecedor, "rgReponsavelAcordo"));
			cargoReponsavelAcordo = new TextField<String>("cargoReponsavelAcordo", new PropertyModel<String>(formularioFornecedor, "cargoReponsavelAcordo"));
			foneReponsavelAcordo = new TextField<String>("foneReponsavelAcordo", new PropertyModel<String>(formularioFornecedor, "foneReponsavelAcordo"));
			emailReponsavelAcordo = new TextField<String>("emailReponsavelAcordo", new PropertyModel<String>(formularioFornecedor, "emailReponsavelAcordo"));

			nomePrepostoRepresentante = new TextField<String>("nomePrepostoRepresentante", new PropertyModel<String>(formularioFornecedor, "nomePrepostoRepresentante"));
			cpfPrepostoRepresentante = new TextField<String>("cpfPrepostoRepresentante", new PropertyModel<String>(formularioFornecedor, "cpfPrepostoRepresentante"));
			rgPrepostoRepresentante = new TextField<String>("rgPrepostoRepresentante", new PropertyModel<String>(formularioFornecedor, "rgPrepostoRepresentante"));
			cargoPrepostoRepresentante = new TextField<String>("cargoPrepostoRepresentante", new PropertyModel<String>(formularioFornecedor, "cargoPrepostoRepresentante"));
			fonePrepostoRepresentante = new TextField<String>("fonePrepostoRepresentante", new PropertyModel<String>(formularioFornecedor, "fonePrepostoRepresentante"));
			emailPrepostoRepresentante = new TextField<String>("emailPrepostoRepresentante", new PropertyModel<String>(formularioFornecedor, "emailPrepostoRepresentante"));	
			ddFoneComercial = new TextField<String>("ddFoneComercial", new PropertyModel<String>(formularioFornecedor, "ddFoneComercial"));
			ddFoneFiscal = new TextField<String>("ddFoneFiscal", new PropertyModel<String>(formularioFornecedor, "ddFoneFiscal"));
			ddFoneFinanceiro = new TextField<String>("ddFoneFinanceiro", new PropertyModel<String>(formularioFornecedor, "ddFoneFinanceiro"));
			ddFoneReponsavelAcordo = new TextField<String>("ddFoneReponsavelAcordo", new PropertyModel<String>(formularioFornecedor, "ddFoneReponsavelAcordo")); 
			ddFonePrepostoRepresentante = new TextField<String>("ddFonePrepostoRepresentante", new PropertyModel<String>(formularioFornecedor, "ddFonePrepostoRepresentante"));

			recebeNfDevolucao = WicketHelper.criaRadioGroup("recebeNfDevolucao", new PropertyModel<String>(formularioFornecedor, "recebeNfDevolucao"), SimNao.getValores());
			negocicacaoBoleto = WicketHelper.criaRadioGroup("negocicacaoBoleto", new PropertyModel<String>(formularioFornecedor, "negocicacaoBoleto"), SimNao.getValores());
			trocaNfBonificacao = WicketHelper.criaRadioGroup("trocaNfBonificacao", new PropertyModel<String>(formularioFornecedor, "trocaNfBonificacao"), SimNao.getValores());
			trocaProdutoProduto = WicketHelper.criaRadioGroup("trocaProdutoProduto", new PropertyModel<String>(formularioFornecedor, "trocaProdutoProduto"), SimNao.getValores());
			trocaRecolheProduto = WicketHelper.criaRadioGroup("trocaRecolheProduto", new PropertyModel<String>(formularioFornecedor, "trocaRecolheProduto"), SimNao.getValores());
			indenizaTroca = WicketHelper.criaRadioGroup("indenizaTroca", new PropertyModel<String>(formularioFornecedor, "indenizaTroca"), SimNao.getValores());
			acordoComercial = WicketHelper.criaRadioGroup("acordoComercial", new PropertyModel<String>(formularioFornecedor, "acordoComercial"), SimNao.getValores());

			dadosAdicionais = new TextArea<String>("dadosAdicionais", new PropertyModel<String>(formularioFornecedor, "dadosAdicionais"));		

			secaoDadosFornecedor.add(secaoPF, secaoPJ, tipoPessoa, tipoFornecedor, comprador, tipoSolicatacao, categoria, grpContribuinteIPI, grpIeProdutorRural, grpMicroEmpresa);
			add(secaoDadosFornecedor, estadoUf, tipoFrete,
					recebeNfDevolucao, negocicacaoBoleto, trocaNfBonificacao, trocaProdutoProduto, trocaRecolheProduto, indenizaTroca, acordoComercial,
					tipoDaConta, tipoDeConta,
					tipoLogradouro, logradouro, numero, complemento, bairro, cidade, cep, 
					emailContato,emailParaNf, foneComercial, foneFiscal, foneFinanceiro, homePage, 
					nomeTitularConta, cpfCnpjTitularConta, bancoTitularConta, agenciaTitularConta, contaTitularConta, prazoPagamento, prazoEntrega, prazoAtrasoEntrega, prazoVisita, formaPagamento,
					observacaoEntrega,
					nomeCompletoReponsavelAcordo, cpfReponsavelAcordo, rgReponsavelAcordo, cargoReponsavelAcordo, foneReponsavelAcordo, emailReponsavelAcordo,
					nomePrepostoRepresentante, cpfPrepostoRepresentante, rgPrepostoRepresentante, cargoPrepostoRepresentante, fonePrepostoRepresentante, emailPrepostoRepresentante,
					digitoAgenciaTitularConta, digitoContaTitularConta, enderecoTitularConta, numeroTitularConta, bairroTitularConta, cidadeTitularConta, cepTitularConta,
					observacaoTitularConta, dadosAdicionais, ddFoneComercial, ddFoneFiscal, ddFoneFinanceiro,ddFoneReponsavelAcordo,ddFonePrepostoRepresentante, ufTitularConta, complementoTitularConta);
			
		}
		
		private void removeDadosPF(){
			formularioFornecedor.setNome(null);
			formularioFornecedor.setNomeReduzido(null);
			formularioFornecedor.setCpfFisica(null);
			formularioFornecedor.setRg(null);
			formularioFornecedor.setOrgaoExpUF(null);
			formularioFornecedor.setDataExpedicao(null);
			formularioFornecedor.setDataNascimento(null);
		}
		
		private void removeDadosPJ(){
			formularioFornecedor.setRazaoSocialFornedor(null);
			formularioFornecedor.setRazaoSocialReduzido(null);
			formularioFornecedor.setInscricaoEstadual(null);
			formularioFornecedor.setCpnjJuridico(null);
			formularioFornecedor.setDataFundacao(null);
			formularioFornecedor.setInscricaoMunicipal(null);
			formularioFornecedor.setSimplesNacional(null);
			formularioFornecedor.setMicroEmpresa(false);
			formularioFornecedor.setContribuinteIPI(false);
			formularioFornecedor.setIeProdutorRural(false);
		}
	}
	
	private final class SecaoPF extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private TextField<String> nome, nomeReduzido,cpfFisica, rg, orgaoExpUF;
		private DateTextField dataExpedicao, dataNascimento;		
		
		public SecaoPF(String id) {
			super(id, new CompoundPropertyModel<FormularioFornecedor>(formularioFornecedor));			
			
			nome = new TextField<String>("nome");
			nomeReduzido = new TextField<String>("nomeReduzido");
			cpfFisica = new TextField<String>("cpfFisica");
			rg = new TextField<String>("rg");
			orgaoExpUF = new TextField<String>("orgaoExpUF");
			dataExpedicao = new DateTextField("dataExpedicao", DATE_PATTERN);
			dataNascimento = new DateTextField("dataNascimento", DATE_PATTERN);
			
			add(nome, nomeReduzido,cpfFisica, rg, orgaoExpUF, dataExpedicao, dataNascimento);
		}
		
	}
	
	private final class SecaoPJ extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private TextField<String> razaoSocialFornedor, razaoSocialReduzido, inscricaoEstadual, cpnjJuridico, inscricaoMunicipal;
		private DateTextField dataFundacao;
		private RadioGroup<String> simplesNacional;
		
		public SecaoPJ(String id) {
			super(id, new CompoundPropertyModel<FormularioFornecedor>(formularioFornecedor));	
			
			razaoSocialFornedor = new TextField<String>("razaoSocialFornedor");
			razaoSocialReduzido = new TextField<String>("razaoSocialReduzido");
			inscricaoEstadual = new TextField<String>("inscricaoEstadual");
			cpnjJuridico = new TextField<String>("cpnjJuridico");
			dataFundacao = new DateTextField("dataFundacao", DATE_PATTERN);
			inscricaoMunicipal = new TextField<String>("inscricaoMunicipal");
			simplesNacional = criaNovoRadioGroup("simplesNacional", SimNao.getValores());			
			
			add(razaoSocialFornedor, razaoSocialReduzido, inscricaoEstadual, cpnjJuridico, dataFundacao, inscricaoMunicipal, simplesNacional);
		}
		
	}
	
	private boolean ehPessoaFisica() {
		return TipoPessoa.FISICA.equals(formularioFornecedor.getTipoPessoa());
	}
	
	private boolean ehPessoaJuridica() {
		return TipoPessoa.JURIDICA.equals(formularioFornecedor.getTipoPessoa());
	}
	
	public Boolean isExisteCnpjCpf(String cpfcpnj) throws NumberFormatException, DaoException {
        if (cpfcpnj.length() == 14) {
        	return gePessoas.isExisteCpnjCpf(new BigDecimal(cpfcpnj.substring(0,12)) , new BigDecimal(cpfcpnj.substring(12,14)) );
        }else if(cpfcpnj.length() == 11) {
			return gePessoas.isExisteCpnjCpf(new BigDecimal(cpfcpnj.substring(0,9)), new BigDecimal(new Long(cpfcpnj.substring(9,11))));
		}else {
			return Boolean.FALSE;
		}
	}

	private void adicionaItemAoHistorico(String nome, String motivo, String comentario, String status){
		HistoricoFornecedor historico = new HistoricoFornecedor();
		historico.setNome(nome);
		historico.setMotivo(motivo);
		historico.setComentario(comentario);
		historico.setStatus(status);
		historico.setData(new Date());

		if(formularioFornecedor.getHistoricoFornecedores() == null) 
			formularioFornecedor.setHistoricoFornecedores(new ArrayList<HistoricoFornecedor>());

		formularioFornecedor.getHistoricoFornecedores().add(historico);
	}

	private RadioGroup<String> criaNovoRadioGroup(String wicketId, List<String> valores){
		RadioGroup<String> radioGroup = new RadioGroup<String>(wicketId);
		int count = 0;
		Iterator<String> iterator = valores.iterator();

		while(iterator.hasNext()){
			String idRadio = wicketId + "_" + count;
			count++;			
			radioGroup.add(new Radio<String>(idRadio, new Model<String>(iterator.next())));
		}		
		return radioGroup;
	}

	private String getNomeCompletoUsuario() {
		String primeiroNome = user.getFirstname();
		String sobrenome = user.getLastname();

		if(primeiroNome == null || sobrenome == null){
			return user.getUserName();
		}		
		return  primeiroNome + " " + sobrenome;		
	}
	
	private void validaDados() throws ValidacaoBeanException{
		if(ehPessoaJuridica()){
			validador.validaComGroups(formularioFornecedor, FornecedorPessoaJuridicaCheck.class, Default.class);
		}else if (ehPessoaFisica()){
			validador.validaComGroups(formularioFornecedor, FornecedorPessoaFisicaCheck.class, Default.class);
		}else{
			validador.valida(formularioFornecedor);
		}
	}
	
	public List<String> compradores() {
		List<Usuario> usuarios;
		try {
			usuarios = dadosUsuarioService.recuperaUsuariosPorGrupo("Comercial");
		} catch (BonitaException e) {
			throw new RuntimeException(e);
		}
		List<String> compradores = new ArrayList<String>();
		
		for(Usuario usuario : usuarios)
			compradores.add(usuario.getNomeCompleto());
		
		return compradores;
	}	
	
}
