package br.com.seta.processo.solicitacaopagamento.abas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.EmpresaFontePagadoraService;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.entity.CategoriaNaturezaDespesa;
import br.com.seta.processo.entity.FontePagadora;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.model.DefaultMoneyModel;
import br.com.seta.processo.provider.CentroCustoSolicitacaoProvider;
import br.com.seta.processo.provider.GePessoaFornecedorProvider;
import br.com.seta.processo.utils.WrapperUtils;

public class SecaoSolicitacao extends Panel{
	private static final long serialVersionUID = 1L;
	
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static final int QUANT_ITENS_POR_PAGINA = 15;
	
	@Inject
	private EmpresaFontePagadoraService empresaFontePagadoraService;

	private TextField<String> nomeSolicitante, emailSolicitante, telefoneSolicitante, chaveAcesso, buscaFornecedorInput, fornecedor;
	private Label chaveAcessoLbl;
	private WebMarkupContainer grpChaveAcesso;
	private TextField<Long> nroNota;
	private TextField<Object> valor;
	private DateTextField dataEmissao;
	private DropDownChoice<FontePagadora> empresas;
	private DropDownChoice<CategoriaNaturezaDespesa> naturezaDespesas;
	private TextArea<String> mensagem;
	private AjaxButton buscaFornecedorBtn;
	private GePessoaFornecedorProvider fornecedorProvider = new GePessoaFornecedorProvider();
	private DataView<GePessoaEntity> fornecedoresDataView;
	private AjaxPagingNavigator navigatorFornecedores;
	private WebMarkupContainer pesquisarFornecedorModal, tabelaFornecedorContainer, msgFonecedoresNaoEncontrados;
	private FontePagadora empresaSelecionada;
	private CategoriaNaturezaDespesa naturezaDespesaSelecionada;
	private SecaoContabilizacao secaoContabilizacao;
	private SecaoFinanceiro secaoFinanceiro;
	
	private SolicitacaoPagamento solicitacao;
	
	@SuppressWarnings("unchecked")
	public SecaoSolicitacao(String id, final SolicitacaoPagamento solicitacaoPagamento, CentroCustoSolicitacaoProvider centroCustoSolicitacaoProvider, SecaoContabilizacao secaoContabilizacao, SecaoFinanceiro secaoFinanceiro) {
		super(id);
		this.secaoContabilizacao = secaoContabilizacao;
		this.secaoFinanceiro = secaoFinanceiro;
		this.solicitacao = solicitacaoPagamento;
		nomeSolicitante = (TextField<String>) new TextField<String>("nomeSolicitante", new PropertyModel<String>(solicitacaoPagamento, "nomeSolicitante")).setEnabled(false); 
		emailSolicitante = (TextField<String>) new TextField<String>("emailSolicitante", new PropertyModel<String>(solicitacaoPagamento, "emailSolicitante")).setEnabled(false); 
		telefoneSolicitante = new TextField<String>("telefoneSolicitante", new PropertyModel<String>(solicitacaoPagamento, "telefoneSolicitante")); 
		nroNota = new TextField<Long>("nroNota", new PropertyModel<Long>(solicitacaoPagamento, "nroNota")); 
		chaveAcesso = new TextField<String>("chaveAcesso", new PropertyModel<String>(solicitacaoPagamento, "chaveAcesso"));
		chaveAcesso.setOutputMarkupId(true);
		chaveAcessoLbl = new Label("chaveAcessoLbl", "Chave de Acesso");
		grpChaveAcesso = new WebMarkupContainer("grpChaveAcesso"){
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onConfigure() {
				super.onConfigure();
				if(solicitacaoPagamento.getPossuiChaveAcesso()){
					chaveAcesso.setVisible(true);
					chaveAcessoLbl.setVisible(true);
				}					
				else{
					chaveAcesso.setVisible(false);
					chaveAcessoLbl.setVisible(false);
					solicitacaoPagamento.setChaveAcesso(null);
				}
			}
			
		};
		grpChaveAcesso.add(chaveAcesso, chaveAcessoLbl).setOutputMarkupId(true);
		DefaultMoneyModel valorModel = new DefaultMoneyModel( new PropertyModel<BigDecimal>(solicitacaoPagamento, "valor"));
		valor = (TextField<Object>)new TextField<Object>("valor", valorModel).setOutputMarkupId(true);	
		dataEmissao = new DateTextField("dataEmissao", new PropertyModel<Date>(solicitacaoPagamento, "dataEmissao"), DATE_PATTERN);
		fornecedor = (TextField<String>) new TextField<String>("fornecedor", new PropertyModel<String>(solicitacaoPagamento, "fornecedor")).setEnabled(false).setOutputMarkupId(true);
		mensagem = new TextArea<String>("mensagem", new PropertyModel<String>(solicitacaoPagamento, "mensagem"));
		pesquisarFornecedorModal = (WebMarkupContainer) new WebMarkupContainer("pesquisarFornecedorModal").setOutputMarkupId(true);
		msgFonecedoresNaoEncontrados = (WebMarkupContainer) new WebMarkupContainer("msgFonecedoresNaoEncontrados").setOutputMarkupId(true);
		
		//PODER ENVIAR O VALOR PARA O LADO SERVIDOR PARA SER USADO NA CONTABILIZAÇÃO
		valor.add(new AjaxFormComponentUpdatingBehavior("change"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				SecaoSolicitacao.this.secaoContabilizacao.zeraTabelaRateiosCentroDeCusto(target);
				SecaoSolicitacao.this.secaoFinanceiro.zeraTabelaParcelas(target);
				target.add(valor);
			}
		});
		
		
		fornecedoresDataView = new DataView<GePessoaEntity>("fornecedores", fornecedorProvider, QUANT_ITENS_POR_PAGINA){
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(Item<GePessoaEntity> item) {
				final GePessoaEntity fornecedor = (GePessoaEntity) item.getDefaultModelObject();
				Label codFornecedor = new Label("codFornecedor", fornecedor.getSeqpessoa());
				Label descricaoFornecedor = new Label("descricaoFornecedor", fornecedor.getNomerazao());
				
				String nrodoc = "";
				String nrodigver = "";
				
				if(fornecedor.getNrocgccpf().toString().length() == 8
						|| fornecedor.getNrocgccpf().toString().length() == 11)
					nrodoc = "0" + fornecedor.getNrocgccpf();
				else if(fornecedor.getNrocgccpf().toString().length() == 10) //CASO DE CNPJS COM DOIS ZEROS À ESQUERDA 
					nrodoc = "00" + fornecedor.getNrocgccpf();
				else
					nrodoc = fornecedor.getNrocgccpf().toString();
				
				if(fornecedor.getDigcgccpf().toString().length() == 1)
					nrodigver = "0" + fornecedor.getDigcgccpf();
				else
					nrodigver = fornecedor.getDigcgccpf().toString();
				
				Label cpfCnpj = new Label("cpfCnpjFornecedor", WrapperUtils.format(WrapperUtils.FormatacaoTipo.CPF_CNPJ, nrodoc + nrodigver));
				
				AjaxEventBehavior onClickEvent = new AjaxEventBehavior("click") {			
				
					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						String descricaoForncedor = fornecedor != null ? fornecedor.getNomerazao() : null;
						solicitacao.setFornecedor(descricaoForncedor);							
						solicitacao.setCodFornecedor(fornecedor.getSeqpessoa());
						target.add(SecaoSolicitacao.this.fornecedor);
						target.appendJavaScript("$('#pesquisarFornecedorModal').modal('hide')");
						
					}
				};					
				item.add(codFornecedor, descricaoFornecedor, cpfCnpj);
				item.add(onClickEvent);		
			}
		};
		
		navigatorFornecedores = new AjaxPagingNavigator("navigatorFornecedores", fornecedoresDataView);			
		buscaFornecedorInput = (TextField<String>) new TextField<String>("buscaFornecedorInput", Model.of("")).setOutputMarkupId(true);
		buscaFornecedorBtn = new AjaxButton("buscaFornecedorBtn") {			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				fornecedorProvider.putParameter((String)buscaFornecedorInput.getDefaultModelObject());
				visibilidadeListaFornecedores();
				target.add(pesquisarFornecedorModal);
			}				
		};
		tabelaFornecedorContainer = (WebMarkupContainer) new WebMarkupContainer("tabelaForncedorContainer").setOutputMarkupId(true);
		visibilidadeListaFornecedores();
		tabelaFornecedorContainer.add(fornecedoresDataView, navigatorFornecedores);
		pesquisarFornecedorModal.add(tabelaFornecedorContainer, msgFonecedoresNaoEncontrados);
		
		List<? extends CategoriaNaturezaDespesa> naturezas = Arrays.asList();			
		if(solicitacaoPagamento.getNroEmpresa() != null){
			empresaSelecionada = empresaFontePagadoraService.buscaFontePagadora(solicitacaoPagamento.getNroEmpresa());
		}			
		if(empresaSelecionada != null){
			naturezas = new ArrayList<>(empresaSelecionada.getNaturezasDespesa());
			naturezaDespesaSelecionada = empresaFontePagadoraService.buscaNaturezaDespesa(empresaSelecionada, solicitacaoPagamento.getCodNaturezaDespesa());
		}			
		
		PropertyModel<FontePagadora> fontePagadoraModel = new PropertyModel<FontePagadora>(this, "empresaSelecionada"){
			private static final long serialVersionUID = 1L;

			@Override
			public void setObject(FontePagadora object) {					
				super.setObject(object);
				if(object != null){
					solicitacaoPagamento.setNroEmpresa(object.getNroEmpresa());
					solicitacaoPagamento.setNomeLoja(object.getNomeReduzido());
				}else{
					solicitacaoPagamento.setNroEmpresa(null);
					solicitacaoPagamento.setNomeLoja(null);
				}					
			}
		};
		
		final PropertyModel<CategoriaNaturezaDespesa> naturezaDespesaModel = new PropertyModel<CategoriaNaturezaDespesa>(this, "naturezaDespesaSelecionada"){
			private static final long serialVersionUID = 1L;
			@Override
			public void setObject(CategoriaNaturezaDespesa object) {					
				super.setObject(object);
				if(object != null){
					solicitacaoPagamento.setCodNaturezaDespesa(object.getCodHistorico());
					solicitacaoPagamento.setNaturezaDespesa(object.getDescricao());
					solicitacaoPagamento.setPossuiChaveAcesso(object.isChaveAcesso());
				}else{
					solicitacaoPagamento.setCodNaturezaDespesa(null);
					solicitacaoPagamento.setNaturezaDespesa(null);
					solicitacaoPagamento.setPossuiChaveAcesso(false);
				}
			}
		};		
		
		empresas = new DropDownChoice<FontePagadora>("empresas", fontePagadoraModel, empresaFontePagadoraService.listaFontesPagadorasHabilitadas(), new ChoiceRenderer<FontePagadora>("nomeReduzido"));
		
		empresas.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				Set<CategoriaNaturezaDespesa> naturezasDespesa = new HashSet<CategoriaNaturezaDespesa>();
				if(empresaSelecionada != null) {
					naturezasDespesa = empresaSelecionada.getNaturezasDespesa();
				}
				
				List<CategoriaNaturezaDespesa> depesas = new ArrayList<CategoriaNaturezaDespesa>(naturezasDespesa);
				naturezaDespesas.setChoices(depesas);				
				
				if(depesas != null && depesas.size() > 0) {
					naturezaDespesaSelecionada = depesas.get(0);					
					naturezaDespesaModel.setObject(naturezaDespesaSelecionada);					
					SecaoSolicitacao.this.secaoContabilizacao.adicionaAtualizaCentroCusto(target, new BigDecimal(empresaSelecionada.getNroEmpresa()), naturezaDespesaSelecionada.getNroplanilha());
				}else{
					naturezaDespesaSelecionada = null;
					naturezaDespesaModel.setObject(naturezaDespesaSelecionada);
					solicitacao.getOrReqplanilhalanctos().clear();
					SecaoSolicitacao.this.secaoContabilizacao.atualizaSecaoRateioCusto(target);
				}
				
				target.add(grpChaveAcesso, naturezaDespesas);				
			}
		});			
					
		
		naturezaDespesas = (DropDownChoice<CategoriaNaturezaDespesa>) 
				new DropDownChoice<CategoriaNaturezaDespesa>("naturezaDespesas", naturezaDespesaModel , naturezas , new ChoiceRenderer<CategoriaNaturezaDespesa>("descricao"))
				.setOutputMarkupId(true);
		
		naturezaDespesas.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(naturezaDespesaSelecionada != null) {					
					SecaoSolicitacao.this.secaoContabilizacao.adicionaAtualizaCentroCusto(target, new BigDecimal(empresaSelecionada.getNroEmpresa()), naturezaDespesaSelecionada.getNroplanilha());
				} 
				
				target.add(grpChaveAcesso);
			}			
			
		});	
		
		
		add(nomeSolicitante, emailSolicitante, telefoneSolicitante, nroNota, grpChaveAcesso, valor, dataEmissao, fornecedor,
				empresas, naturezaDespesas, mensagem, pesquisarFornecedorModal, buscaFornecedorInput, buscaFornecedorBtn);			
		
	}
	
	private void visibilidadeListaFornecedores() {
		if(fornecedorProvider.isEmpty()){
			tabelaFornecedorContainer.setVisible(false);				
			msgFonecedoresNaoEncontrados.setVisible(true);
		}else{
			tabelaFornecedorContainer.setVisible(true);				
			msgFonecedoresNaoEncontrados.setVisible(false);
			
			if(fornecedorProvider.size() <= QUANT_ITENS_POR_PAGINA){
				navigatorFornecedores.setVisible(false);
			}else{
				navigatorFornecedores.setVisible(true);
			}
		}
	}
	
	public TextField<String> getNomeSolicitante() {
		return nomeSolicitante;
	}

	public TextField<String> getEmailSolicitante() {
		return emailSolicitante;
	}

	public TextField<String> getTelefoneSolicitante() {
		return telefoneSolicitante;
	}
}
