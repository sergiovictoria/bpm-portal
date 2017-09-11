package br.com.seta.processo.suprimentos.componentespagina.abas;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.CalculoRequisicao;
import br.com.seta.processo.dto.CentroCusto;
import br.com.seta.processo.dto.NaturezaDespesa;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.model.DefaultMoneyModel;
import br.com.seta.processo.model.DefaultZeroBigDecimalModel;
import br.com.seta.processo.provider.NaturezaProvider;
import br.com.seta.processo.provider.OrReqplanilhalanctoProvider;

public class SecaoContabilizacao extends Panel {
	private static final long serialVersionUID = 1L;

	@Inject
	private CalculoRequisicao calculoRequisicao;
	
	private OrRequisicao requisicao;	
	
	private SecaoBuscaNaturezaDespesa secaoBuscaNaturezaDespesa;
	private BuscaNaturezaDespesaModal buscaNaturezaDespesaModal;
	private SecaoCentroCusto centroCustoSecao;
	private SecaoRateioCusto secaoRateioCusto;	
	
	public SecaoContabilizacao(String id, OrRequisicao requisicao) {
		super(id);
		
		setOutputMarkupId(true);
		
		this.requisicao = requisicao;
		if(requisicao.getOrReqplanilhalanctos() == null)	
			requisicao.setOrReqplanilhalanctos(new HashSet<OrReqplanilhalancto>());		
		
		secaoBuscaNaturezaDespesa = new SecaoBuscaNaturezaDespesa("secaoBuscaNaturezaDespesa"); 
		buscaNaturezaDespesaModal = new BuscaNaturezaDespesaModal("buscaNaturezaDespesaModal");
		centroCustoSecao = (SecaoCentroCusto) new SecaoCentroCusto("secaoCentroCusto").setOutputMarkupId(true);
		secaoRateioCusto = new SecaoRateioCusto("secaoRateioCusto");		
		
		add(secaoBuscaNaturezaDespesa, buscaNaturezaDespesaModal, centroCustoSecao, secaoRateioCusto);
	}
	
	public void visibilidadeSecaoBuscaNaturezaDespesa(boolean visivel){
		this.secaoBuscaNaturezaDespesa.setEnabled(visivel);
	}
	
	private class SecaoBuscaNaturezaDespesa extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private TextField<String> naturezaDespesa;
		private Button abrirModalBuscaNaturezas;
		
		public SecaoBuscaNaturezaDespesa(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			naturezaDespesa = new TextField<String>("naturezaDespesa", new PropertyModel<String>(requisicao, "naturezaDespesa"));
			naturezaDespesa.setEnabled(false);
			abrirModalBuscaNaturezas = new Button("abrirModalBuscaNaturezas");
			
			add(naturezaDespesa, abrirModalBuscaNaturezas);
		}
		
	}
	
	private class BuscaNaturezaDespesaModal extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private static final int QUANT_ITENS_PAGINA = 15;
		
		private TextField<String> filtroNaturezaDespesa;
		private AjaxButton buscaNaturezaBtn;
		private WebMarkupContainer tabelaNaturezaDespesa;
		private NaturezaDespesaDV naturezaDespesaDV;
		private AjaxPagingNavigator naturezaNavigator;
		private NaturezaProvider naturezaProvider;
		private WebMarkupContainer msgNaturezasNaoEncontrados;
		
		public BuscaNaturezaDespesaModal(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			filtroNaturezaDespesa = new TextField<String>("filtroNaturezaDespesa", Model.of(""));
			buscaNaturezaBtn = new AjaxButton("buscaNaturezaBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					String filtro = filtroNaturezaDespesa.getDefaultModelObjectAsString();
					naturezaProvider.setFiltro(filtro);
					visibilidadeTabelaNaturezas();
					target.add(BuscaNaturezaDespesaModal.this);	
				}
			};
			
			naturezaProvider = new NaturezaProvider(requisicao);
			naturezaDespesaDV = new NaturezaDespesaDV("naturezas", naturezaProvider, QUANT_ITENS_PAGINA);
			tabelaNaturezaDespesa = (WebMarkupContainer) new WebMarkupContainer("tabelaNaturezaDespesa").setOutputMarkupId(true);
			naturezaNavigator = new AjaxPagingNavigator("naturezaNavigator", naturezaDespesaDV);
			msgNaturezasNaoEncontrados = new WebMarkupContainer("msgNaturezasNaoEncontrados");
			
			tabelaNaturezaDespesa.add(naturezaDespesaDV, naturezaNavigator);
			add(filtroNaturezaDespesa, buscaNaturezaBtn, tabelaNaturezaDespesa, msgNaturezasNaoEncontrados);
			
			visibilidadeTabelaNaturezas();
		}
		
		public void visibilidadeTabelaNaturezas() {
			if(naturezaProvider.size() == 0){
				tabelaNaturezaDespesa.setVisible(false);
				msgNaturezasNaoEncontrados.setVisible(true);
			}else{
				tabelaNaturezaDespesa.setVisible(true);
				msgNaturezasNaoEncontrados.setVisible(false);
				if(naturezaProvider.size() <= QUANT_ITENS_PAGINA){
					naturezaNavigator.setVisible(false);
				}else{
					naturezaNavigator.setVisible(true);
				}
			}
		}
		
	}
	
	private void atualizaVisibilidadeNaturezasDespesas(){
		buscaNaturezaDespesaModal.visibilidadeTabelaNaturezas();
	}
	
	public void atualizaSecao(AjaxRequestTarget target){
		atualizaVisibilidadeNaturezasDespesas();
		target.add(this);
	}
	
	public void reiniciaSecao(AjaxRequestTarget target){
		buscaNaturezaDespesaModal.visibilidadeTabelaNaturezas();
		requisicao.setNaturezaDespesa("");
		requisicao.setCodhistorico((short)0);
		target.add(this);
	}	
	
	public void atualizaSecaoRateioCusto(AjaxRequestTarget target){
		target.add(secaoRateioCusto);
	}

	private class NaturezaDespesaDV extends DataView<NaturezaDespesa>{
		private static final long serialVersionUID = 1L;

		protected NaturezaDespesaDV(String id, IDataProvider<NaturezaDespesa> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<NaturezaDespesa> item) {
			NaturezaDespesa natureza = (NaturezaDespesa) item.getDefaultModelObject();
			final String descricao = natureza.getHistorico();
			final BigDecimal id = natureza.getCodhistorico();
			final String codmodelo = natureza.getCodmodelo().toString();
			final String exigeitensnota = natureza.getExiste_itens_nota();
			final Short cgo = natureza.getCgo().shortValue();
			
			Label idLbl = new Label("idLbl", id.toString());
			Label descricaoLbl = new Label("descricaoLbl", descricao);
			AjaxEventBehavior onclickEvent = new AjaxEventBehavior("click") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {					
					requisicao.setNaturezaDespesa(descricao);
					requisicao.setCodhistorico(id.shortValue());
					requisicao.setCodmodelo(codmodelo);
					requisicao.setExigeitensnota(exigeitensnota);
					requisicao.setCgo(cgo);
					requisicao.setNroempresaorc(requisicao.getNroempresa());
					requisicao.setNroempresanatdesp(requisicao.getNroempresa());
							
					limparRateioCentroDeCusto(target);
					target.add(secaoBuscaNaturezaDespesa ,centroCustoSecao);
					target.appendJavaScript("$('#buscaNaturezaDespesa').modal('hide')");
				}				
				
			};
			
			item.add(idLbl, descricaoLbl).add(onclickEvent);
		}		
	}
	
	/**
	 * Utilizado para limpar a lista/seção de rateio de centro de custo
	 */
	private void limparRateioCentroDeCusto(AjaxRequestTarget target) {
		requisicao.setOrReqplanilhalanctos(new HashSet<OrReqplanilhalancto>());
		target.add(secaoRateioCusto);
	}
	
	private class SecaoCentroCusto extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private static final int ITENS_POR_PAGINA = 15;
		private CentroCustoDV centrosCustos;
		private WebMarkupContainer tabelaCentroCusto;
		private OrReqplanilhalanctoProvider provider;
		
		public SecaoCentroCusto(String id) {
			super(id);
			
			provider = new OrReqplanilhalanctoProvider(requisicao);
			centrosCustos = new CentroCustoDV("centrosCustos", provider, ITENS_POR_PAGINA);
			tabelaCentroCusto = new WebMarkupContainer("tabelaCentroCusto");
			
			tabelaCentroCusto.add(centrosCustos);
			add(tabelaCentroCusto);
		}
		
	}
	
	private class CentroCustoDV extends DataView<CentroCusto>{
		private static final long serialVersionUID = 1L;

		protected CentroCustoDV(String id, IDataProvider<CentroCusto> dataProvider, long itemsPerPage) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(Item<CentroCusto> item) {
			final CentroCusto cc = (CentroCusto) item.getDefaultModelObject();	
			
			Label linhaLbl = new Label("linhaLbl", cc.getNrolinha());
			Label centroCustoLbl = new Label("centroCustoLbl", cc.getCentrocustodb());;
			Label contaDebitoLbl = new Label("contaDebitoLbl", cc.getContadebito());;
			Label contaCreditoLbl = new Label("contaCreditoLbl", cc.getContacredito());;
			Label historico = new Label("historico", cc.getHistorico());
			
			AjaxLink<Void> addCentroCustoBtn = new AjaxLink<Void>("addCentroCustoBtn") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					OrReqplanilhalancto lancto = deCentroDeCustoParaOrReqplanilhalancto(cc);
					requisicao.getOrReqplanilhalanctos().add(lancto);
					target.add(secaoRateioCusto);					
				}
			};
			
			item.add(linhaLbl, centroCustoLbl, contaDebitoLbl, contaCreditoLbl, historico, addCentroCustoBtn);
		}
		
		private OrReqplanilhalancto deCentroDeCustoParaOrReqplanilhalancto(CentroCusto cc){
			OrReqplanilhalancto lancto = new OrReqplanilhalancto();
			
			short nroLinha = (cc.getNrolinha() == null) ? 0 :  cc.getNrolinha().shortValue();
			long contaDebito = (cc.getContadebito() == null) ? 0 : cc.getContadebito().longValue();
			long contaCredito = (cc.getContacredito() == null) ? 0 : cc.getContacredito().longValue();
			int centroCusto = (cc.getCentrocustodb() == null) ? 0 : cc.getCentrocustodb().intValue();
			
			lancto.setNrolinha(nroLinha);
			lancto.setNroplanilha(cc.getNroplanilha());
			lancto.setNroempresa(requisicao.getNroempresa());		
			lancto.setContadebito(contaDebito);
			lancto.setFilial(requisicao.getNroempresa());
			lancto.setContacredito(contaCredito);
			lancto.setCentrocustodb(centroCusto);
			lancto.setHistorico(cc.getHistorico());
			lancto.setVlrlancamento(new BigDecimal("0"));
			lancto.setPercrateio(new BigDecimal("0"));
			lancto.setTipoentidadecr(cc.getTipoentidadecr());
			lancto.setTipocontab("L");			
			return lancto;
		}
	}	
	
	private class SecaoRateioCusto extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private RateioCustoProvider provider;
		private RateioCentroCustoDV rateios;
		private WebMarkupContainer tabelaRateios;
		
		public SecaoRateioCusto(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			provider = new RateioCustoProvider();
			rateios = new RateioCentroCustoDV("rateios", provider);
			tabelaRateios = new WebMarkupContainer("tabelaRateios");
			
			tabelaRateios.add(rateios);
			add(tabelaRateios);
		}		
	}
	
	private class RateioCentroCustoDV extends DataView<OrReqplanilhalancto>{
		private static final long serialVersionUID = 1L;

		protected RateioCentroCustoDV(String id, IDataProvider<OrReqplanilhalancto> dataProvider) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(final Item<OrReqplanilhalancto> item) {
			final OrReqplanilhalancto lancto = (OrReqplanilhalancto) item.getDefaultModelObject();
			
			Label linhaLbl = new Label("linhaLbl", lancto.getNrolinha());
			Label centroCustoLbl = new Label("centroCustoLbl", lancto.getCentrocustodb());
			Label contaDebitoLbl = new Label("contaDebitoLbl", lancto.getContadebito());
			Label contaCreditoLbl = new Label("contaCreditoLbl", lancto.getContacredito());
			Label historicoLbl = new Label("historicoLbl", lancto.getHistorico());
			
			DefaultZeroBigDecimalModel percentualModel = new DefaultZeroBigDecimalModel(new PropertyModel<BigDecimal>(lancto, "percrateio"));
			TextField<String> porcentagem = new TextField<String>("porcentagem", percentualModel);
			
			DefaultMoneyModel vlrLancamentoModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(lancto, "vlrlancamento"));
			Label valorLbl = new Label("valorLbl", vlrLancamentoModel);
			
			AjaxLink<Void> excluirBtn = new AjaxLink<Void>("excluirBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onClick(AjaxRequestTarget target) {
					requisicao.getOrReqplanilhalanctos().remove(item.getModelObject());
					target.add(secaoRateioCusto);					
				}		
			};			
			
			porcentagem.add(criaUpdateEvent(item, lancto));			
			item.add(linhaLbl, centroCustoLbl, contaDebitoLbl, contaCreditoLbl, historicoLbl, porcentagem, valorLbl, excluirBtn).setOutputMarkupId(true);			
		}
		
		private AjaxFormComponentUpdatingBehavior criaUpdateEvent(final Item<OrReqplanilhalancto> item, final OrReqplanilhalancto lancto){
			return new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					OrReqplanilhalancto lancto = (OrReqplanilhalancto) item.getDefaultModelObject();
					BigDecimal percRateio = lancto.getPercrateio() == null ? BigDecimal.ZERO : lancto.getPercrateio();
					
					BigDecimal _100 = new BigDecimal(100);
					
					if(percRateio.compareTo(_100) > 0){
						lancto.setPercrateio(_100);
					}else if(percRateio.compareTo(BigDecimal.ZERO) < 0){
						lancto.setPercrateio(BigDecimal.ZERO);
					}					
					
					calculoRequisicao.calculaValorRateio(lancto, requisicao.getVlrliqreq());
					target.add(item);					
				}
			};
		}
		
	}
	
	private class RateioCustoProvider implements IDataProvider<OrReqplanilhalancto>{
		private static final long serialVersionUID = 1L;

		@Override
		public void detach() {
			
		}

		@Override
		public Iterator<? extends OrReqplanilhalancto> iterator(long first, long count) {
			return requisicao.getOrReqplanilhalanctos().iterator();
		}

		@Override
		public long size() {
			return requisicao.getOrReqplanilhalanctos().size();
		}

		@Override
		public IModel<OrReqplanilhalancto> model(final OrReqplanilhalancto object) {
			return new LoadableDetachableModel<OrReqplanilhalancto>() {
				private static final long serialVersionUID = 1L;
				@Override
				protected OrReqplanilhalancto load() {
					return object;
				}
			};
		}


		
	}

}
