package br.com.seta.processo.cadastrofornecedores;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;
import org.jboss.logging.Logger;

import br.com.seta.processo.bean.parse.ParseFile;
import br.com.seta.processo.cadastrofornecedores.dominios.Banco;
import br.com.seta.processo.cadastrofornecedores.dominios.Categoria;
import br.com.seta.processo.cadastrofornecedores.dominios.Estados;
import br.com.seta.processo.cadastrofornecedores.dominios.FormaPagamento;
import br.com.seta.processo.cadastrofornecedores.dominios.Fornecedor;
import br.com.seta.processo.cadastrofornecedores.dominios.SimNao;
import br.com.seta.processo.cadastrofornecedores.dominios.Solicitacao;
import br.com.seta.processo.cadastrofornecedores.dominios.TipoDaConta;
import br.com.seta.processo.cadastrofornecedores.dominios.TipoDeConta;
import br.com.seta.processo.cadastrofornecedores.dominios.TipoFrete;
import br.com.seta.processo.cadastrofornecedores.dominios.TipoLogradouro;
import br.com.seta.processo.constant.StatusCadastroFornecedor;
import br.com.seta.processo.constant.VariaveisCadastroFornecedores;
import br.com.seta.processo.dominios.TipoPessoa;
import br.com.seta.processo.dto.CadastroFornecedor;
import br.com.seta.processo.dto.EstadosCadastroFornecedores;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.HistoricoFornecedor;
import br.com.seta.processo.dto.Professional;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.exceptions.ParseException;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.helper.WicketHelper;
import br.com.seta.processo.model.CadastroFornecedorModel;
import br.com.seta.processo.page.InstanciacaoProcessoPage;
import br.com.seta.processo.service.DadosUsuarioService;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.utils.PropertiesEJBUtils;
import br.com.seta.processo.validacao.ValidadorBean;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaFisicaCheck;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaJuridicaCheck;

public class SolicitarCadastroFornecedor extends InstanciacaoProcessoPage {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SolicitarCadastroFornecedor.class);

	@Inject	private DadosUsuarioService dadosUsuarioService;
	@Inject	private ParseFile parseFile;
	@Inject	private ValidadorBean validador;
	@Inject	private ExecuteRestAPI executeRestAPI;
	@Inject private GroupService groupService;

	private transient User user = (User) Session.get().getAttribute("user");	

	private CadastroFornecedorModel cadastroFornecedorModel = new CadastroFornecedorModel();
	private CadastroFornecedor cadastroFornecedor = new CadastroFornecedor();	
	private FormularioFornecedor formularioFornecedor, formularioTemp;
	private static final String _FILE_XLS     = PropertiesEJBUtils.getInstance().getValues("fornecedor_file_xls");
	private static final String _FILE_ODS     = PropertiesEJBUtils.getInstance().getValues("fornecedor_file_ods");
	private static final String _SEPARATOR = System.getProperty("file.separator");
	private static final String _FOLDER    = System.getProperty("user.home")+_SEPARATOR;
	private static final String DATE_PATTERN = "dd/MM/yyyy";

	private PlanilhaFornecedoresContainer solicitacaoWebMarkupContainer;
	private Form<Void> SolicitacaoForm, importacaoPlanilhaModalForm;

	public SolicitarCadastroFornecedor(PageParameters pageParameters) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException {
		super(pageParameters);
		setTituloPagina("Solicitar Cadastro de Fornecedor");

		formularioFornecedor = new FormularioFornecedor();
		formularioFornecedor.setInitUserMail(email(user));
		formularioFornecedor.setEmailsAprovadorNovosCadastros(EmailsAprovadoNovosCadastro());
		formularioFornecedor.setEmailsCadastro(EmailsCdastro());

		this.solicitacaoWebMarkupContainer = (PlanilhaFornecedoresContainer) new PlanilhaFornecedoresContainer("solicitacaoWebMarkupContainer")
		.setOutputMarkupId(true);

		this.importacaoPlanilhaModalForm = new ImportacaoPlanilhaModalForm("importacaoPlanilhaModalForm");
		this.SolicitacaoForm = new SolicitacaoForm("SolicitacaoForm");

		add(SolicitacaoForm, importacaoPlanilhaModalForm);
	}

	public class SolicitacaoForm extends Form<Void> {

		private static final long serialVersionUID = 1L;

		private WebMarkupContainer secaoBotoesComercial;

		public SolicitacaoForm(String id) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException {
			super(id);

			this.secaoBotoesComercial = new WebMarkupContainer("secaoBotoesComercial");

			File fileXLS = new File(_FILE_XLS);
			File fileODS = new File(_FILE_ODS);

			AjaxButton enviar =  new AjaxButton("enviar") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {					
					try {					

						if(cadastroFornecedorModel.getDescricao() == null || cadastroFornecedorModel.getDescricao().trim().length() == 0){
							setMensagemErro("Favor, preencha o campo descrição na Aba 1 (Responsável Preenchimento)", target);
							return;
						}

						if(ehPessoaJuridica()){
							validador.validaComGroups(formularioFornecedor, FornecedorPessoaJuridicaCheck.class, Default.class);
						}else if (ehPessoaFisica()){
							validador.validaComGroups(formularioFornecedor, FornecedorPessoaFisicaCheck.class, Default.class);
						}else{
							validador.valida(formularioFornecedor);
						}
						validador.valida(cadastroFornecedorModel);						

						Map<String, Object> listVariablesSerializable = new HashMap<String, Object>();
						cadastroFornecedor.setNomeRespPreench(cadastroFornecedorModel.getNomeRespPreench());
						cadastroFornecedor.setEmailSolicitante(cadastroFornecedorModel.getEmailSolicitante());
						cadastroFornecedor.setTelefoneSolicitante(cadastroFornecedorModel.getTelefoneSolicitante());
						cadastroFornecedor.setArea(cadastroFornecedorModel.getArea());
						cadastroFornecedor.setDescricao(cadastroFornecedorModel.getDescricao());
						cadastroFornecedor.setNomeSolicitante(cadastroFornecedorModel.getSolicitantesSelected());
						cadastroFornecedor.setDataSolicitacao(new Date());						
						cadastroFornecedor.setHistoricoFornecedors(formularioFornecedor.getHistoricoFornecedores());
						adicionaPreenchimentoAoHistorico();
						listVariablesSerializable.put(VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR, formularioFornecedor);
						listVariablesSerializable.put(VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR,cadastroFornecedor);
						listVariablesSerializable.put(VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR, EstadosCadastroFornecedores.PENDENTE_DE_APROVACAO);
						executeCaseWithVariable(listVariablesSerializable);

						redirecionaParaPaginaDeAtividades();						
					} catch (BonitaException e) {
						SolicitarCadastroFornecedor.this.setMensagemErro("Erro ao tentar enviar tarefa para bpm seta !", target);
					} catch (ValidacaoBeanException e) {
						setMensagensErro(e.getMessages(), target);						
					}

				}
			};

			Label nomeRespPreench  = new Label("nomeRespPreench",  cadastroFornecedorModel.getNomeRespPreench());
			Label emailSolicitante = new Label("emailSolicitante", cadastroFornecedorModel.getEmailSolicitante());
			Label telefoneSolicitante = new Label("telefoneSolicitante", cadastroFornecedorModel.getTelefoneSolicitante());
			Label area = new Label("area", cadastroFornecedorModel.getArea());
			TextArea<String> descricao =  new TextArea<String>("descricao", new PropertyModel<String>(cadastroFornecedorModel, "descricao"));
			DropDownChoice<String> nomeSolicitante = new DropDownChoice<String>("nomeSolicitante",  new PropertyModel<String>(cadastroFornecedorModel, "solicitantesSelected"),cadastroFornecedorModel.getSolicitantes());
			DownloadLink cadastroFormecedorXLS = new DownloadLink("cadastroFormecedorXLS", fileXLS);
			DownloadLink cadastroFormecedorODS = new DownloadLink("cadastroFormecedorODS", fileODS);

			solicitacaoWebMarkupContainer.add(nomeSolicitante,emailSolicitante,telefoneSolicitante,area,descricao,nomeRespPreench,cadastroFormecedorODS,cadastroFormecedorXLS);
			add(solicitacaoWebMarkupContainer, enviar, secaoBotoesComercial);
		}
	}

	private void adicionaPreenchimentoAoHistorico(){
		HistoricoFornecedor historico = new HistoricoFornecedor();
		historico.setNome(getNomeCompletoUsuario());
		historico.setMotivo("");
		historico.setComentario("");
		historico.setStatus(StatusCadastroFornecedor.SOLICITANTE_PREENCHIMENTO);
		historico.setData(new Date());

		if(formularioFornecedor.getHistoricoFornecedores() == null) 
			formularioFornecedor.setHistoricoFornecedores(new ArrayList<HistoricoFornecedor>());

		formularioFornecedor.getHistoricoFornecedores().add(historico);
	}

	private String getNomeCompletoUsuario(){
		String primeiroNome = user.getFirstname();
		String sobrenome = user.getLastname();

		if(primeiroNome == null || sobrenome == null){
			return user.getUserName();
		}		
		return  primeiroNome + " " + sobrenome;		
	}

	private boolean ehPessoaFisica() {
		return TipoPessoa.FISICA.equals(formularioFornecedor.getTipoPessoa());
	}

	private boolean ehPessoaJuridica() {
		return TipoPessoa.JURIDICA.equals(formularioFornecedor.getTipoPessoa());
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

						target.add(solicitacaoWebMarkupContainer);	
						target.appendJavaScript("$('#importarPlanilhaModal').modal('hide');");
						exibeMensagemSucesso("Arquivo", "Arquivo importado com sucesso!", target);
					}catch(IOException | ParseException e){
						logger.error(e);
						SolicitarCadastroFornecedor.this.setMensagemErro("Este tipo de arquivo não é válido para importar!", target);
					}catch(Exception e){
						logger.error(e);
						SolicitarCadastroFornecedor.this.setMensagemErro("Ocorreu um erro durante a importação do arquivo", target);
					}

				}				
			};			
			add(fileUploadField, importBtn);
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

	private List<String> compradores() {
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

	private String email(User user) {
		User userSearch = null;
		String email = null;
		try {
			executeRestAPI.retriveUserProfessionalData(user);
			Professional professional =	userSearch.getProfessional_data();
			if (professional.getEmail()!=null) {
				email = professional.getEmail(); 
			}else{
				email = "cadastro.csc@setaacadista.com.br";
			}
		}catch (Exception e) {
		}
		return email;
	}

	
	private  String EmailsAprovadoNovosCadastro() {
		String emails="";
		try {
			List<User> users = executeRestAPI.findUserForRolesByNameRole("Aprovador Novos Cadastros");	
			for (User user : users) {
				if ((user.getPersonnal_data_email()!=null) &&  (user.getPersonnal_data_email()!=" ") &&  (user.getPersonnal_data_email()!="") ) {
					emails = emails + user.getPersonnal_data_email()+",";
				}
			}
		}catch (Exception e) {
		}
		return emails;
	}
	
	
	private  String EmailsCdastro() {
		String emails = "";
		try {
			List<String> emailsFiscal = groupService.findEmails("Cadastro");
			for (String dto : emailsFiscal) {
				if ((dto!=null) && (dto!=" ")  && (dto!="") ) {
					emails += dto + ", ";
				}
			}
		}catch (Exception e) {
		}
		return emails;
	}

}
