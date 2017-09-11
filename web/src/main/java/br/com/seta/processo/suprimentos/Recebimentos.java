/**
 * 
 */
package br.com.seta.processo.suprimentos;

import static br.com.seta.processo.constant.ConstantesRecebimento._RECEBIMENTO_CASE;
import static br.com.seta.processo.constant.ConstantesRecebimento._RECEBIMENTO_VESAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.joda.time.Instant;

import br.com.seta.processo.bean.MapInstanciaProcesso;
import br.com.seta.processo.businesscalendar.CalculadoraCalendarioTempoDecorrido;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dominios.TipoDespesa;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.model.DefaultMoneyModel;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.pagecomponentes.OrdemColunaTabelaCssProvider;
import br.com.seta.processo.provider.RecebimentosDetalhesProvider;
import br.com.seta.processo.provider.RecebimentosProvider;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.utils.PropertiesEJBUtils;

/**
 * @author Eliel Sobral
 *
 */
public class Recebimentos extends BonitaPage {
	private static final long serialVersionUID = 1L;

	private RecebimentosProvider provider = new RecebimentosProvider(getUser());
	private WebMarkupContainer rptRecebimentos = new WebMarkupContainer("rptRecebimentos");
	
	
	@Inject private ExecuteRestAPI executeRestAPI;
	@Inject private MapInstanciaProcesso mapInstanciaProcesso;
	private ModalDetalhes modalDetalhes;
	private ModalFiltro modalFiltro;
	private ModalReceber modalReceber;
	private AjaxLink<Void> btnModalDetalhes;
	private AjaxLink<Void> btnModalReceber;
	private RecebimentosForm form;
	private RecebimentosModalForm modalForm;
	private String bpmUser = PropertiesEJBUtils.getInstance().getValues("bpmUser");
	private String bpmPassword = PropertiesEJBUtils.getInstance().getValues("bpmPassword");
	private Map<String, Object> listVariablesSerializable = new HashMap<String, Object>();
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = new SolicitacaoIntencaoCompra();

	
	public Recebimentos(PageParameters pageParameters) throws ClientProtocolException, IOException, ParseException, InstantiationException, IllegalAccessException, HttpStatusException {
		super(pageParameters);
		setTituloPagina("Lista de Intenções de Compra");
			
		form = new RecebimentosForm("form");
		modalForm = new RecebimentosModalForm("modalForm");

		modalDetalhes = (ModalDetalhes) new ModalDetalhes("modalDetalhes").setOutputMarkupId(true);
		modalFiltro = (ModalFiltro) new ModalFiltro("modalFiltro").setOutputMarkupId(true);
		modalReceber = (ModalReceber) new ModalReceber("modalReceber").setOutputMarkupId(true);

		form.add(modalDetalhes);
		modalForm.add(modalFiltro);
		modalForm.add(modalReceber);

		add(form);
		add(modalForm);
	}
	
	private class RecebimentosModalForm extends Form<Void> {
		private static final long serialVersionUID = 1L;

		public RecebimentosModalForm(String id) {
			super(id);
		}
		
	}
	
	/*****
	 * 
	 * @author Sérgio da Victória
	 *
	 *
	 */

	private class RecebimentosForm extends Form<Void> {
		private static final long serialVersionUID = 1L;

		public RecebimentosForm(String id) {
			super(id);
			
			provider = new RecebimentosProvider(getUser());
			DataView<OrRequisicao> dtvRecebimentos = new DataView<OrRequisicao>("dtvRecebimentos", provider) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(final Item<OrRequisicao> item) {
					final OrRequisicao requisicao = (OrRequisicao) item.getDefaultModelObject();
					item.setOutputMarkupId(true);

					DefaultMoneyModel valorModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(requisicao, "vlrliqreq"));
					Label lblDtvValor = new Label("lblDtvValor", valorModel);
					
					Date in = requisicao.getDataSolicitacaoIntencao();
					CalculadoraCalendarioTempoDecorrido emFila = new CalculadoraCalendarioTempoDecorrido();
					Label lblDtvEmFila = new Label("lblDtvEmFila", emFila.calculaTempoDecorrido(in, Instant.now().toDate()));

					item.add(new Label("lblDtvTipoDespesa", requisicao.getTipoDespesa()));
					item.add(new Label("lblDtvRequisicao", requisicao.getNumeroIntencaoSolicitacaoCompra()));
					item.add(new Label("lblDtvLocalEntrega", requisicao.getGrupoRecebimento()));
					item.add(new Label("lblDtvNomeSolicitante", requisicao.getNomeSolicitanteBPM()));
					item.add(new Label("lblDtvFornecedor", requisicao.getNomeFornecedor()));					
					item.add(lblDtvValor);
					item.add(lblDtvEmFila); 
										
					final CheckBox checkboxRecebimentos = new CheckBox("checkboxRecebimentos", new PropertyModel<Boolean>(requisicao, "selecionado"));
					checkboxRecebimentos.add(new AjaxEventBehavior("click") {
						private static final long serialVersionUID = 1L;
						@Override
						protected void onEvent(AjaxRequestTarget target) {
							if(requisicao.isSelecionado()) {
								requisicao.setSelecionado(false);
							} else {
								requisicao.setSelecionado(true);
							}
							target.add(rptRecebimentos);
							target.appendJavaScript("removerSelecionados('#" + item.getMarkupId() + "')");
						}
					});
					
					btnModalDetalhes = new AjaxLink<Void>("btnModalDetalhes") {
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick(AjaxRequestTarget target) {
							modalDetalhes.setOrRequisicao(requisicao);
							modalDetalhes.getProviderDetalhes().configureProvider(requisicao.getNumeroIntencaoSolicitacaoCompra());

							target.appendJavaScript("$('#modalDetalhes').modal('show');");
							target.add(modalDetalhes);
						}
					};
					
					item.add(btnModalDetalhes);
					item.add(checkboxRecebimentos);
				}
			};
			dtvRecebimentos.setOutputMarkupId(true);
			dtvRecebimentos.setItemsPerPage(10L);
			AjaxPagingNavigator ajaxPagingNavigator = new AjaxPagingNavigator("ajaxPagingNavigator", dtvRecebimentos);
			
			AjaxFallbackOrderByBorder<?> TipoDespesa = new OrderByBorderTipoDespesa("TipoDespesa", "TipoDespesa",
					provider, rptRecebimentos, dtvRecebimentos, new OrdemColunaTabelaCssProvider());
			AjaxFallbackOrderByBorder<?> IDRequisicao = new OrderByBorderTipoDespesa("IDRequisicao", "IDRequisicao",
					provider, rptRecebimentos, dtvRecebimentos, new OrdemColunaTabelaCssProvider());
			AjaxFallbackOrderByBorder<?> LocalEntrega = new OrderByBorderTipoDespesa("LocalEntrega", "LocalEntrega",
					provider, rptRecebimentos, dtvRecebimentos, new OrdemColunaTabelaCssProvider());
			AjaxFallbackOrderByBorder<?> Solicitante = new OrderByBorderTipoDespesa("Solicitante", "Solicitante",
					provider, rptRecebimentos, dtvRecebimentos, new OrdemColunaTabelaCssProvider());
			AjaxFallbackOrderByBorder<?> Fornecedor = new OrderByBorderTipoDespesa("Fornecedor", "Fornecedor", provider,
					rptRecebimentos, dtvRecebimentos, new OrdemColunaTabelaCssProvider());
			AjaxFallbackOrderByBorder<?> EmFila = new OrderByBorderTipoDespesa("EmFila", "EmFila", provider,
					rptRecebimentos, dtvRecebimentos, new OrdemColunaTabelaCssProvider()); 

			btnModalReceber = new AjaxLink<Void>("btnModalReceber") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					int contador = 0;
					for (OrRequisicao or : provider.getOrRequisicaoList()) {
						if (or.isSelecionado()) {
							contador++;
						}
					}
					if (contador == 0) {
						setMensagemErro("Selecione uma requisição para Iniciar o Recebimento!", target);
					} else {
						target.add(modalReceber);
						target.appendJavaScript("$('#modalReceber').modal('show')");
					}
				}

			};
			
			rptRecebimentos.setOutputMarkupId(true);
			rptRecebimentos.add(dtvRecebimentos, ajaxPagingNavigator);
			rptRecebimentos.add(TipoDespesa, IDRequisicao, LocalEntrega, Solicitante, Fornecedor, EmFila);
			add(btnModalReceber);
			add(rptRecebimentos);
		}
	}

	/*
	 * MODAL FILTRO
	 */
	private final class ModalFiltro extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		private List<String> fornecedores = new ArrayList<String>();
		private List<String> grupos = new ArrayList<String>();
		private List<String> solicitantes = new ArrayList<String>();
		private List<String> tiposDespesa = new ArrayList<String>();

		private String nomeFornecedor = new String();
		private String grupoRecebimento = new String();
		private String nomeSolicitanteBPM = new String();
		private String tipoDespesa = new String();
		
		TextField<String> numeroIntencaoCompra;
		TextField<String> numeroRequisicao;
//		TextField<String> emFilaDe;
//		TextField<String> emFilaAte;

		public ModalFiltro(String id) {
			super(id);

			this.fornecedores = provider.listaFornecedorRequisicao("");
			this.grupos = provider.listaGrupoRecebimentoRequisicao("");
			this.solicitantes = provider.listaSolicitantesRequisicao("");
			this.tiposDespesa = TipoDespesa.getValores();

			final DropDownChoice<String> cmbTipoDespesa = new DropDownChoice<String>("cmbTipoDespesa",
					new PropertyModel<String>(this, "tipoDespesa"), this.tiposDespesa);
			cmbTipoDespesa.setNullValid(true).setOutputMarkupId(true);

			final DropDownChoice<String> cmbLocalEntrega = new DropDownChoice<String>("cmbLocalEntrega",
					new PropertyModel<String>(this, "grupoRecebimento"), this.grupos);
			cmbLocalEntrega.setNullValid(true).setOutputMarkupId(true);

			final DropDownChoice<String> cmbFornecedor = new DropDownChoice<String>("cmbFornecedor",
					new PropertyModel<String>(this, "nomeFornecedor"), this.fornecedores);
			cmbFornecedor.setNullValid(true).setOutputMarkupId(true);

			final DropDownChoice<String> cmbSolicitante = new DropDownChoice<String>("cmbSolicitante",
					new PropertyModel<String>(this, "nomeSolicitanteBPM"), this.solicitantes);
			cmbSolicitante.setNullValid(true).setOutputMarkupId(true);

			numeroIntencaoCompra = new TextField<String>("numeroIntencaoCompra", Model.of(""));
			numeroIntencaoCompra.setOutputMarkupId(true);

			numeroRequisicao = new TextField<String>("numeroRequisicao", Model.of(""));
			numeroRequisicao.setOutputMarkupId(true);

			AjaxButton btnPesquisar = new AjaxButton("btnPesquisar") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					provider.executaFiltroRequisicao(tipoDespesa, grupoRecebimento, nomeFornecedor, nomeSolicitanteBPM, 
													 Long.parseLong((null!=numeroIntencaoCompra.getDefaultModelObject() ?(String) numeroIntencaoCompra.getDefaultModelObject() :"0")), 
													 Long.parseLong((null!=numeroRequisicao.getDefaultModelObject() ?(String) numeroRequisicao.getDefaultModelObject() :"0")), null, null); 
					target.add(rptRecebimentos, modalDetalhes);
					target.appendJavaScript("$('#modalFiltro').modal('hide');");
				}
			};
			
			AjaxFormComponentUpdatingBehavior onChangeEventTipoDespesa = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					
					provider.executaFiltroRequisicao(tipoDespesa, null, null, null, null, null, null, null);
					
					fornecedores = provider.listaFornecedorRequisicao("");
					solicitantes = provider.listaSolicitantesRequisicao("");
					grupos = provider.listaGrupoRecebimentoRequisicao("");
					
					target.add(cmbLocalEntrega, cmbFornecedor, cmbSolicitante, cmbTipoDespesa);
					target.add(numeroIntencaoCompra, numeroRequisicao);
				}
			};
			cmbTipoDespesa.add(onChangeEventTipoDespesa);
			
			AjaxFormComponentUpdatingBehavior onChangeEventLocalEntrega = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					
					provider.executaFiltroRequisicao(null, grupoRecebimento, null, null, null, null, null, null);
					
					fornecedores = provider.listaFornecedorRequisicao("");
					solicitantes = provider.listaSolicitantesRequisicao("");
					
					target.add(cmbLocalEntrega, cmbFornecedor, cmbSolicitante, cmbTipoDespesa);
					target.add(numeroIntencaoCompra, numeroRequisicao);
				}
			};
			cmbLocalEntrega.add(onChangeEventLocalEntrega);
			
			AjaxFormComponentUpdatingBehavior onChangeEventFornecedor = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					
					provider.executaFiltroRequisicao(null, null, nomeFornecedor, null, null, null, null, null);
					
					grupos = provider.listaGrupoRecebimentoRequisicao("");
					solicitantes = provider.listaSolicitantesRequisicao("");
					
					target.add(cmbLocalEntrega, cmbFornecedor, cmbSolicitante, cmbTipoDespesa);
					target.add(numeroIntencaoCompra, numeroRequisicao);
				}
			};
			cmbFornecedor.add(onChangeEventFornecedor);
			
			AjaxFormComponentUpdatingBehavior onChangeEventSolicitante = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					
					provider.executaFiltroRequisicao(null, null, null, nomeSolicitanteBPM, null, null, null, null);
					
					fornecedores = provider.listaFornecedorRequisicao(nomeSolicitanteBPM);
					grupos = provider.listaGrupoRecebimentoRequisicao(nomeSolicitanteBPM);
					
					target.add(cmbLocalEntrega, cmbFornecedor, cmbSolicitante, cmbTipoDespesa);
					target.add(numeroIntencaoCompra, numeroRequisicao);
				}
			};
			cmbSolicitante.add(onChangeEventSolicitante);

			add(cmbFornecedor, cmbLocalEntrega, cmbSolicitante, cmbTipoDespesa);
			add(numeroIntencaoCompra, numeroRequisicao);
			add(btnPesquisar);

		}

	}

	/*
	 * MODAL RECEBER
	 */
	private final class ModalReceber extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public ModalReceber(String id) {
			super(id);

			AjaxButton btnReceber = new AjaxButton("btnReceber") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					for (OrRequisicao dto : provider.getRequisicoesParaEnviar()) {
						try {
							solicitacaoIntencaoCompra = (SolicitacaoIntencaoCompra) mapInstanciaProcesso.listaSolicitacaoIntencaoCompraOne(dto.getNumeroIntencaoSolicitacaoCompra());
							listVariablesSerializable.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,dto);
							listVariablesSerializable.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA, solicitacaoIntencaoCompra);
							
							executeRestAPI.execueteCaseWithVariable(solicitacaoIntencaoCompra.getIdResponsavelPreenchimento(),bpmUser, bpmPassword, listVariablesSerializable, _RECEBIMENTO_CASE,_RECEBIMENTO_VESAO);
							provider.receberPedidoSelecionado(dto);

							exibeMensagemSucesso("Enviar", "O pedido # " +dto.getNrorequisicao() + " foi enviado para Recebimento com sucesso!", target);
						} catch (Exception e) {
							e.printStackTrace();
							exibeMensagemAdvertencia("Receber Pedido", "Ao tentar atualizar dados no mongo db, entre em contato com adminstrador do sistema", target);
						}
					}
					provider.refresh();
					target.add(rptRecebimentos);
				}
			};

			add(btnReceber);

		}

	}

	/*
	 * MODAL DETALHES
	 */
	private final class ModalDetalhes extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		
		private TextField<String> nomeFornecedor;
		private TextField<String> nomeSolicitanteBPM;
		private TextField<String> numeroIntencaoCompra;
		private TextField<String> numeroRequisicao;
		
		private RecebimentosDetalhesProvider providerDetalhes;
		private WebMarkupContainer rptRecebimentosDetalhes = new WebMarkupContainer("rptRecebimentosDetalhes");

		OrRequisicao orRequisicao = new OrRequisicao();

		public ModalDetalhes(String id) {
			super(id);
			nomeFornecedor = new TextField<String>("nomeFornecedor", new PropertyModel<String>(this, "orRequisicao.nomeFornecedor"));
			nomeSolicitanteBPM = new TextField<String>("nomeSolicitanteBPM", new PropertyModel<String>(this, "orRequisicao.nomeSolicitanteBPM"));
			numeroIntencaoCompra = new TextField<String>("numeroIntencaoCompra", new PropertyModel<String>(this, "orRequisicao.numeroIntencaoSolicitacaoCompra"));
			numeroRequisicao = new TextField<String>("numeroRequisicao", new PropertyModel<String>(this, "orRequisicao.nrorequisicao"));

			providerDetalhes = new RecebimentosDetalhesProvider();
			DataView<OrReqitensdespesa> dtvRecebimentosDetalhes = new DataView<OrReqitensdespesa>("dtvRecebimentosDetalhes", providerDetalhes) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(Item<OrReqitensdespesa> item) {
					final OrReqitensdespesa despesa = (OrReqitensdespesa) item.getDefaultModelObject();

					DefaultMoneyModel valorModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(despesa, "vlritem"));
					Label lblValor = new Label("lblValor", valorModel);

					item.add(new Label("lblDescricao", despesa.getDescricao()));
					item.add(new Label("lblCFOP", despesa.getCfop()));
					item.add(new Label("lblUnidade", despesa.getUnidade()));
					item.add(lblValor);
				}
			};
			dtvRecebimentosDetalhes.setOutputMarkupId(true);

			dtvRecebimentosDetalhes.setItemsPerPage(10L);
			AjaxPagingNavigator ajaxPagingNavigator = new AjaxPagingNavigator("ajaxPagingNavigator", dtvRecebimentosDetalhes);

			rptRecebimentosDetalhes.setOutputMarkupId(true);
			rptRecebimentosDetalhes.add(dtvRecebimentosDetalhes, ajaxPagingNavigator);
			
			add(nomeFornecedor, nomeSolicitanteBPM, numeroIntencaoCompra, numeroRequisicao);
			add(rptRecebimentosDetalhes);
		}

		public void setOrRequisicao(OrRequisicao orRequisicao) {
			this.orRequisicao = orRequisicao;
		}

		/**
		 * @return the providerDetalhes
		 */
		public final RecebimentosDetalhesProvider getProviderDetalhes() {
			return providerDetalhes;
		}

	}
	
	@SuppressWarnings("rawtypes")
	public class OrderByBorderTipoDespesa extends AjaxFallbackOrderByBorder {
		private static final long serialVersionUID = 1L;

		private WebMarkupContainer rptRecebimentos;
		private DataView<OrRequisicao> dtvRecebimentos;

		@SuppressWarnings("unchecked")
		public OrderByBorderTipoDespesa(String id, Object sortProperty, ISortStateLocator stateLocator,
				WebMarkupContainer rptRecebimentos, DataView<OrRequisicao> dtvRecebimentos) {
			super(id, sortProperty, stateLocator);
			this.rptRecebimentos = rptRecebimentos;
			this.dtvRecebimentos = dtvRecebimentos;
		}

		@SuppressWarnings("unchecked")
		public OrderByBorderTipoDespesa(String id, Object sortProperty, ISortStateLocator stateLocator,
				WebMarkupContainer rptRecebimentos, DataView<OrRequisicao> dtvRecebimentos,
				AjaxFallbackOrderByLink.ICssProvider cssProvider) {
			super(id, sortProperty, stateLocator, cssProvider);
			this.rptRecebimentos = rptRecebimentos;
			this.dtvRecebimentos = dtvRecebimentos;
		}

		@Override
		protected void onAjaxClick(AjaxRequestTarget target) {
			target.add(this.rptRecebimentos);
		}

		@Override
		protected void onSortChanged() {
			dtvRecebimentos.setCurrentPage(0);
		}

	}

}
