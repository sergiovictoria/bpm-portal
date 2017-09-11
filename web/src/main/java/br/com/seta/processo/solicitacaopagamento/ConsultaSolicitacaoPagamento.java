package br.com.seta.processo.solicitacaopagamento;

import static br.com.seta.processo.solicitacaopagamento.DadosConsultaSolicitacaoPagtoComparators.POR_FORNECEDOR;
import static br.com.seta.processo.solicitacaopagamento.DadosConsultaSolicitacaoPagtoComparators.POR_ID;
import static br.com.seta.processo.solicitacaopagamento.DadosConsultaSolicitacaoPagtoComparators.POR_NOTA_DESPESA;
import static br.com.seta.processo.solicitacaopagamento.DadosConsultaSolicitacaoPagtoComparators.POR_SOLICITANTE;
import static br.com.seta.processo.solicitacaopagamento.DadosConsultaSolicitacaoPagtoComparators.POR_STATUS;
import static br.com.seta.processo.solicitacaopagamento.DadosConsultaSolicitacaoPagtoComparators.POR_VALOR;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.dao.FiltroSolicitacaoPagamento;
import br.com.seta.processo.componentes.messagebox.MessageBox;
import br.com.seta.processo.componentes.messagebox.MessageBoxButtons;
import br.com.seta.processo.componentes.messagebox.MessageBoxTipo;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.pagecomponentes.OrdemColunaTabelaCssProvider;
import br.com.seta.processo.provider.GePessoaFornecedorProvider;
import br.com.seta.processo.provider.SolicitacaoPagamentoProvider;
import br.com.seta.processo.solicitacaopagamento.dominios.Status;
import br.com.seta.processo.utils.DateUtils;

public class ConsultaSolicitacaoPagamento extends Templete {
	private static final long serialVersionUID = 1L;
	
	private FiltroSolicitacaoPagamento filtro; 
	
	private SolicitacaoPagamentoProvider solicitacaoProvider;
	private GePessoaFornecedorProvider fornecedorProvider;

	private MessageBox msgBox = new MessageBox("msbAlerta", "Atenção", "", MessageBoxTipo.ALERT, MessageBoxButtons.CONFIRMAR);
	private TextField<String> txtFornecedor;
	private ModalPesquisa modalPesquisa;
	private ModalConsultarFornecedor modalConsultarFornecedor;
	private ConsultaSolicitacaoPagamentoForm form;
	private WebMarkupContainer secaoSolicitacoesPagto, tabelaSolicitacoesContainer, msgSolicitacaoPagamento, tabelaFornecedores, msgFonecedores;
	
	private static final DecimalFormat FORMATADOR_MONETARIO = new java.text.DecimalFormat("R$ #,###,##0.00");
	
	public ConsultaSolicitacaoPagamento() {			
		setTituloPagina("Consultar Solicitação de Pagamento");
		filtro = new FiltroSolicitacaoPagamento();
		filtro.setUserNameSolicitante(getUser().getUserName());		
		solicitacaoProvider = new SolicitacaoPagamentoProvider(filtro);
		fornecedorProvider = new GePessoaFornecedorProvider();
		
		this.modalPesquisa = new ModalPesquisa("modalPesquisa");
		this.modalConsultarFornecedor = new ModalConsultarFornecedor("modalConsultarFornecedor");
		this.form = new ConsultaSolicitacaoPagamentoForm("form");
		
		form.add(modalPesquisa, modalConsultarFornecedor);
		add(msgBox, form);
	}
	
	private final class ConsultaSolicitacaoPagamentoForm extends Form<Object> {
		private static final long serialVersionUID = 1L;
		private static final long QUANT_SOLICITACOES_POR_PAGINA = 15L;
		private AjaxPagingNavigator navigatorSolicitacoes;
		
		public ConsultaSolicitacaoPagamentoForm(String id) {
			super(id);
			
			add(new AjaxButton("btnFiltro") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					target.appendJavaScript("$('#modalFiltro').modal('show');");
				}
			});
			
			final DataView<DadosConsultaSolicitacaoPagto> dtvSolicitacao = new DataView<DadosConsultaSolicitacaoPagto>("dtvSolicitacao", solicitacaoProvider, QUANT_SOLICITACOES_POR_PAGINA) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<DadosConsultaSolicitacaoPagto> item) {
					DadosConsultaSolicitacaoPagto dadosConsultaSolPagto = item.getModelObject();
					
					String valor = dadosConsultaSolPagto.getValor() == null ? "" : FORMATADOR_MONETARIO.format(dadosConsultaSolPagto.getValor());
					item.add(new Label("lblDtvCaseId", dadosConsultaSolPagto.getCaseId()));
					item.add(new Label("lblDtvEmpresa", dadosConsultaSolPagto.getFornecedor()));
					item.add(new Label("lblDtvNotaDespesa", dadosConsultaSolPagto.getNroNota()));
					item.add(new Label("lblDtvValor", valor));
					item.add(new Label("lblDtvStatus", dadosConsultaSolPagto.getStatus()) );
					item.add(new Label("lblDataSolicitacao", dadosConsultaSolPagto.getDataSolicitacao()));
					item.add(new Label("lblDtvComentario", dadosConsultaSolPagto.getComentario()));
				}
			};			
			
			msgSolicitacaoPagamento = new WebMarkupContainer("msgSolicitacaoPagamento");
			navigatorSolicitacoes = new AjaxPagingNavigator("ajaxPagingNavigator", dtvSolicitacao);
			secaoSolicitacoesPagto = (WebMarkupContainer) new WebMarkupContainer("secaoSolicitacoesPagto").setOutputMarkupId(true);
			
			tabelaSolicitacoesContainer = (WebMarkupContainer) new WebMarkupContainer("tabelaSolicitacoesContainer"){
				private static final long serialVersionUID = 1L;
				
				protected void onConfigure() {
					if(dtvSolicitacao.getItemCount() == 0){
						setVisible(false);
						msgSolicitacaoPagamento.setVisible(true);
					}else{
						setVisible(true);
						msgSolicitacaoPagamento.setVisible(false);
						if(dtvSolicitacao.getItemCount() > QUANT_SOLICITACOES_POR_PAGINA){
							navigatorSolicitacoes.setVisible(true);
						}else{
							navigatorSolicitacoes.setVisible(false);
						}
					}
				};				
			}.setOutputMarkupId(true);		
			
			OrderByBorderTabelaSolicitacoes orderId = new OrderByBorderTabelaSolicitacoes("orderId", POR_ID, solicitacaoProvider);
			OrderByBorderTabelaSolicitacoes orderFornecedor = new OrderByBorderTabelaSolicitacoes("orderFornecedor", POR_FORNECEDOR, solicitacaoProvider);
			OrderByBorderTabelaSolicitacoes orderNotaDespesa = new OrderByBorderTabelaSolicitacoes("orderNotaDespesa", POR_NOTA_DESPESA, solicitacaoProvider);
			OrderByBorderTabelaSolicitacoes orderValor = new OrderByBorderTabelaSolicitacoes("orderValor", POR_VALOR, solicitacaoProvider);
			OrderByBorderTabelaSolicitacoes orderStatus = new OrderByBorderTabelaSolicitacoes("orderStatus", POR_STATUS, solicitacaoProvider);
			OrderByBorderTabelaSolicitacoes orderSolicitante = new OrderByBorderTabelaSolicitacoes("orderSolicitante", POR_SOLICITANTE, solicitacaoProvider);
			
			tabelaSolicitacoesContainer.add(dtvSolicitacao, navigatorSolicitacoes);
			tabelaSolicitacoesContainer.add(orderId, orderFornecedor, orderNotaDespesa, orderValor, orderStatus, orderSolicitante);
			secaoSolicitacoesPagto.add(tabelaSolicitacoesContainer, msgSolicitacaoPagamento);
			add(secaoSolicitacoesPagto);			
		}
	}
	
	private final class ModalPesquisa extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;	
		
		@SuppressWarnings("unchecked")
		public ModalPesquisa(String id) {
			super(id);			
			
			txtFornecedor = (TextField<String>) new TextField<String>("txtFornecedor", Model.of("")).setEnabled(false);
			txtFornecedor.setOutputMarkupId(true);
			TextField<Long> txtNotaDepesa = new TextField<Long>("txtNotaDepesa", new PropertyModel<Long>(filtro, "nroNota"));
			TextField<BigDecimal> txtValorDe = new TextField<BigDecimal>("txtValorDe", new PropertyModel<BigDecimal>(filtro, "valorDe"));
			TextField<BigDecimal> txtValorAte = new TextField<BigDecimal>("txtValorAte", new PropertyModel<BigDecimal>(filtro, "valorAte"));
			DateTextField inicioDataSolicitacao = new DateTextField("inicioDataSolicitacao", new PropertyModel<Date>(filtro, "inicioDataSolicitacao"), "dd/MM/yyyy");
			DateTextField fimDataSolicitacao = new DateTextField("fimDataSolicitacao", new PropertyModel<Date>(filtro, "fimDataSolicitacao"), "dd/MM/yyyy");
			
			DropDownChoice<String> cmbStatus = new DropDownChoice<String>("cmbStatus", new PropertyModel<String>(filtro, "status"), Status.getStatus()){
				private static final long serialVersionUID = 1L;
				
				@Override
				public boolean isNullValid() {
					return true;
				}
				
				@Override
				protected String getNullValidDisplayValue() {
					return "Todos";
				}
				
			};
			AjaxButton btnPesquisar = new AjaxButton("btnPesquisar") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> f) {
					if(filtro.getInicioDataSolicitacao() != null){
						Date data = DateUtils.inicioDoDiaDe(filtro.getInicioDataSolicitacao());
						filtro.setInicioDataSolicitacao(data);
					}
					if(filtro.getFimDataSolicitacao() != null){
						Date data = DateUtils.fimDoDiaDe(filtro.getFimDataSolicitacao());
						filtro.setFimDataSolicitacao(data);
					}					
					solicitacaoProvider.setFiltro(filtro);
					target.add(secaoSolicitacoesPagto);
					ocultaCarregamento(target);
				}
			};
			AjaxButton limparFornecedorSelecionadoBtn = new AjaxButton("limparFornecedorSelecionadoBtn") {			
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					filtro.setCodFornecedor(null);
					setDescricaoFornecedor("");
					target.add(txtFornecedor);
				}
			};
			
			add(txtFornecedor, txtNotaDepesa, txtValorDe, txtValorAte, cmbStatus, inicioDataSolicitacao, fimDataSolicitacao,  btnPesquisar, limparFornecedorSelecionadoBtn);
		}
		
		public void setDescricaoFornecedor(GePessoaEntity fornecedor){
			String codFornecedor = fornecedor.getSeqpessoa() != null ? fornecedor.getSeqpessoa().toString() : "";
			String descricaoFornecedor = fornecedor.getNomerazao() != null ? fornecedor.getNomerazao() : "";
			
			txtFornecedor.setDefaultModelObject(codFornecedor + " " + descricaoFornecedor);
		}
		
		public void setDescricaoFornecedor(String descricao){
			txtFornecedor.setDefaultModelObject(descricao);
		}
		
		public TextField<String> getTxtFornecedor(){
			return txtFornecedor;
		}
	}
	
	@SuppressWarnings("unused")
	private final class ModalConsultarFornecedor extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		private static final int QUANT_ITENS_POR_PAGINA = 15;
		
		private BigDecimal idFornecedor;
		private String filtroFornecedor;
		private AjaxPagingNavigator navigatorFornecedores;
		
		public ModalConsultarFornecedor(String id) {
			super(id);
			final WebMarkupContainer secaoConsultarFornecedor = new WebMarkupContainer("secaoConsultarFornecedor");
			secaoConsultarFornecedor.setOutputMarkupId(true);			
			TextField<String> txtRazaoSocial = new TextField<String>("txtFiltroFornecedor", new PropertyModel<String>(this, "filtroFornecedor"));

			AjaxButton btnPesquisarFornecedor = new AjaxButton("btnPesquisarFornecedor") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					fornecedorProvider.putParameter(filtroFornecedor);
					visibilidadeListaFornecedores();
					target.add(secaoConsultarFornecedor);
				}
			};

			DataView<GePessoaEntity> dtvFornecedorConsulta = new DataView<GePessoaEntity>("dtvFornecedorConsulta", fornecedorProvider, QUANT_ITENS_POR_PAGINA) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<GePessoaEntity> item) {
					final GePessoaEntity fornecedor = (GePessoaEntity) item.getModelObject();

					item.add(new Label("lblCodigoFornec", fornecedor.getSeqpessoa()));
					item.add(new Label("lblDescricaoFornec", fornecedor.getNomerazao()));
					
					AjaxEventBehavior onClickEvent = new AjaxEventBehavior("click") {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onEvent(AjaxRequestTarget target) {
							filtro.setCodFornecedor(fornecedor.getSeqpessoa());
							modalPesquisa.setDescricaoFornecedor(fornecedor);
							target.add(modalPesquisa.getTxtFornecedor());
							target.appendJavaScript("$('#consultarFornecedorDialog').modal('hide')");	
							target.appendJavaScript("$('#modalFiltro').modal('show')");
						}
					};					

					item.add(onClickEvent);
				}
			};

			navigatorFornecedores = new AjaxPagingNavigator("navigator", dtvFornecedorConsulta);

			AjaxButton btnFecharModalFornecedor = new AjaxButton("btnFecharModalFornecedor") {
				private static final long serialVersionUID = 1L;
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					target.appendJavaScript("$('#consultarFornecedorDialog').modal('hide');");
					target.appendJavaScript("$('#modalFiltro').modal('show');");
				}
			};
			
			tabelaFornecedores = (WebMarkupContainer) new WebMarkupContainer("tabelaFornecedores").setOutputMarkupId(true);
			msgFonecedores = new WebMarkupContainer("msgFonecedores");
			tabelaFornecedores.add(dtvFornecedorConsulta, navigatorFornecedores);
			secaoConsultarFornecedor.add(txtRazaoSocial);			
			secaoConsultarFornecedor.add(btnPesquisarFornecedor);
			secaoConsultarFornecedor.add(tabelaFornecedores, msgFonecedores);
			add(btnFecharModalFornecedor);
			add(secaoConsultarFornecedor);
			visibilidadeListaFornecedores();
		}
		
		private void visibilidadeListaFornecedores() {
			if(fornecedorProvider.isEmpty()){
				tabelaFornecedores.setVisible(false);
				msgFonecedores.setVisible(true);
			}else{
				tabelaFornecedores.setVisible(true);
				msgFonecedores.setVisible(false);
				if(fornecedorProvider.size() <= QUANT_ITENS_POR_PAGINA){
					navigatorFornecedores.setVisible(false);
				}else{
					navigatorFornecedores.setVisible(true);
				}
			}
		}
		
	}
	
	/**
	 * Ordenador de coluna das tabela de solicitações
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	@SuppressWarnings("rawtypes")
	private class OrderByBorderTabelaSolicitacoes extends AjaxFallbackOrderByBorder{	
		
		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unchecked")
		public OrderByBorderTabelaSolicitacoes(String id, Object sortProperty, ISortStateLocator stateLocator) {
			super(id, sortProperty, stateLocator, new OrdemColunaTabelaCssProvider());			
		}

		@Override
		protected void onAjaxClick(AjaxRequestTarget target) {
			target.add(tabelaSolicitacoesContainer);
		}
		
		@Override
		protected void onSortChanged() {			
		}
		
	}
	
}
