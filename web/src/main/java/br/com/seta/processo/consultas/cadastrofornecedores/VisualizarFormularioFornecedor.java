package br.com.seta.processo.consultas.cadastrofornecedores;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

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
import br.com.seta.processo.dominios.TipoPessoa;
import br.com.seta.processo.dto.CadastroFornecedor;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.exceptions.CadastroFornecedorException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.helper.WicketHelper;
import br.com.seta.processo.page.BonitaPage;

public class VisualizarFormularioFornecedor extends BonitaPage {
	
	private static final long serialVersionUID = 1L;

	private static final String DATE_PATTERN = "dd/MM/yyyy";
	
	private Button voltarBtn, consultaCadastrosBtn;
	private Form<Void> planilhaFornecedorForm;	
	private PlanilhaFornecedoresContainer planilhaFornecedoresContainer;
	
	private FormularioFornecedor formularioFornecedor;
	
	public VisualizarFormularioFornecedor(final FormularioFornecedor formulario, final CadastroFornecedor cadastro) throws HttpStatus401Exception, org.apache.http.ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException, CadastroFornecedorException{
		setTituloPagina("Dados do Fornecedor");		
		this.formularioFornecedor = formulario;		
		
		this.planilhaFornecedorForm = new Form<Void>("planilhaFornecedoresForm");	
		this.planilhaFornecedoresContainer = new PlanilhaFornecedoresContainer("planilhaFornecedoresContainer");
		this.planilhaFornecedoresContainer.setEnabled(false);
		
		voltarBtn = new Button("voltarBtn"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				setResponsePage(new CadastroFornecedores(formulario, cadastro));
			}
		};
		
		consultaCadastrosBtn = new Button("consultaCadastrosBtn"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				setResponsePage(ConsultaCadastroFornecedor.class);
			}
			
		};
		
		planilhaFornecedorForm.add(voltarBtn, consultaCadastrosBtn, planilhaFornecedoresContainer);
		add(planilhaFornecedorForm);
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
		complementoTitularConta, ddFonePrepostoRepresentante, comprador;

		private TextArea<String> dadosAdicionais;
		private DropDownChoice<String> formaPagamento, bancoTitularConta, tipoDaConta, tipoDeConta, tipoSolicatacao, categoria, ufTitularConta, 
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
				}
				
			});				
			
			
			ieProdutorRural = new CheckBox("ieProdutorRural", new PropertyModel<Boolean>(formularioFornecedor, "ieProdutorRural"));
			contribuinteIPI = new CheckBox("contribuinteIPI", new PropertyModel<Boolean>(formularioFornecedor, "contribuinteIPI"));
			microEmpresa = new CheckBox("microEmpresa", new PropertyModel<Boolean>(formularioFornecedor, "microEmpresa"));
			
			grpIeProdutorRural = new WebMarkupContainer("grpIeProdutorRural");
			grpIeProdutorRural.add(ieProdutorRural);
			grpContribuinteIPI = new WebMarkupContainer("grpContribuinteIPI");
			grpContribuinteIPI.add(contribuinteIPI);
			grpMicroEmpresa = new WebMarkupContainer("grpMicroEmpresa");
			grpMicroEmpresa.add(microEmpresa);

			tipoSolicatacao = new DropDownChoice<String>("tipoSolicatacao", new PropertyModel<String>(formularioFornecedor, "tipoSolicatacao"), Solicitacao.getTiposSolicitacao());
			tipoSolicatacao.setEnabled(false);
			categoria = new DropDownChoice<String>("categoria", new PropertyModel<String>(formularioFornecedor, "categoria"), Categoria.getCategorias());
			comprador = new TextField<String>("comprador", new PropertyModel<String>(formularioFornecedor, "comprador"));
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
		}
	}
	
	private boolean ehPessoaFisica() {
		return TipoPessoa.FISICA.equals(formularioFornecedor.getTipoPessoa());
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

	
	
}
