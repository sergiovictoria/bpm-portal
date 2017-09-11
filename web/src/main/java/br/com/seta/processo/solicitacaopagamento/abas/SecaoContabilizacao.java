package br.com.seta.processo.solicitacaopagamento.abas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.dto.CentroCusto;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.model.DefaultZeroBigDecimalModel;
import br.com.seta.processo.provider.CentroCustoSolicitacaoProvider;

public class SecaoContabilizacao extends Panel {
	private static final long serialVersionUID = 1L;
	
	private SecaoCentroCusto centroCustoSecao;
	private SecaoRateioCusto secaoRateioCusto;	
	private CentroCustoSolicitacaoProvider centroCustoSolicitacaoProvider;
	private RateioCustoProvider rateioCustoProvider = new RateioCustoProvider();;
	private WebMarkupContainer tabelaCentroCusto;
	private SolicitacaoPagamento solicitacaoPagamento;
	
	public SecaoContabilizacao(String id, CentroCustoSolicitacaoProvider centroCustoSolicitacaoProvider, SolicitacaoPagamento solicitacao) {
		super(id);
		
		this.centroCustoSolicitacaoProvider = centroCustoSolicitacaoProvider;
		this.solicitacaoPagamento = solicitacao;
		setOutputMarkupId(true);
		
		centroCustoSecao = (SecaoCentroCusto) new SecaoCentroCusto("secaoCentroCusto").setOutputMarkupId(true);
		secaoRateioCusto = new SecaoRateioCusto("secaoRateioCusto");		
		centroCustoSecao.setVisible(false);
		add(centroCustoSecao, secaoRateioCusto);
	}
	
	
	public void atualizaCentroCusto(AjaxRequestTarget target){
		target.add(tabelaCentroCusto);
	}
	
	public void adicionaAtualizaCentroCusto(AjaxRequestTarget target, BigDecimal nroempresa, String nroplanilha){
		centroCustoSolicitacaoProvider.putParameter(nroempresa, nroplanilha);
		rateioCustoProvider.clean();
		for(CentroCusto cc : centroCustoSolicitacaoProvider.getCentrosCustos()) {
			OrReqplanilhalancto lancto = deCentroDeCustoParaOrReqplanilhalancto(cc);
			rateioCustoProvider.add(lancto);
		}
		
		target.add(secaoRateioCusto);		
	}
	
	public void atualizaSecaoRateioCusto(AjaxRequestTarget target){
		target.add(secaoRateioCusto);
	}
	
	public SecaoCentroCusto getCentroCustoSecao() {
		return centroCustoSecao;
	}
	
	public WebMarkupContainer getTabelaCentroCusto(){
		return tabelaCentroCusto;
	}
	


	/**
	 * Utilizado para limpar a lista/seção de rateio de centro de custo
	 */
//	private void limparRateioCentroDeCusto(AjaxRequestTarget target) {
//		target.add(secaoRateioCusto);
//	}
	
	private class SecaoCentroCusto extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private static final int ITENS_POR_PAGINA = 15;
		private CentroCustoDV centrosCustos;
		
		public SecaoCentroCusto(String id) {
			super(id);
			
			centrosCustos = new CentroCustoDV("centrosCustos", centroCustoSolicitacaoProvider, ITENS_POR_PAGINA);
			tabelaCentroCusto = new WebMarkupContainer("tabelaCentroCusto");
			tabelaCentroCusto.setOutputMarkupId(true);
			
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
					rateioCustoProvider.add(lancto);
					target.add(secaoRateioCusto);
				}
			};
			
			item.add(linhaLbl, centroCustoLbl, contaDebitoLbl, contaCreditoLbl, historico, addCentroCustoBtn);
		}
		
		
		
	}	
	
	
	private OrReqplanilhalancto deCentroDeCustoParaOrReqplanilhalancto(CentroCusto cc){
		OrReqplanilhalancto lancto = new OrReqplanilhalancto();
		
		short nroLinha = (cc.getNrolinha() == null) ? 0 :  cc.getNrolinha().shortValue();
		Long contaDebito = (cc.getContadebito() == null) ? null : cc.getContadebito().longValue();
		Long contaCredito = (cc.getContacredito() == null) ? null : cc.getContacredito().longValue();
		Integer centroCusto = (cc.getCentrocustodb() == null) ? null : cc.getCentrocustodb().intValue();
		
		lancto.setNroplanilha(cc.getNroplanilha());
		lancto.setNrolinha(nroLinha);
		lancto.setNroempresa(cc.getNroempresa() != null ? cc.getNroempresa().shortValue() : 0);
		lancto.setFilial(cc.getFilial() != null ? Short.parseShort(cc.getFilial()) : cc.getNroempresa() != null ? cc.getNroempresa().shortValue() : 0);
		lancto.setContadebito(contaDebito);
		lancto.setTipoentidadedb(cc.getTipoentidadedb());
		lancto.setCodentidadedb(cc.getCodentidadedb() != null ? cc.getCodentidadedb().intValue() : null);
		lancto.setCentrocustodb(centroCusto);
		lancto.setContacredito(contaCredito);
		lancto.setTipoentidadecr(cc.getTipoentidadecr());
		lancto.setCodentidadecr(cc.getCodentidadecr()!= null ? cc.getCodentidadecr().intValue() : null);
		lancto.setCentrocustocr(cc.getCentrocustocr() != null ? cc.getCentrocustocr().intValue() : null);
		lancto.setHistorico(cc.getHistorico());
		lancto.setVlrlancamento(new BigDecimal(0));
		lancto.setPercrateio(new BigDecimal("0"));
		lancto.setTipocontab("L");	
		
		return lancto;
	}
	
	
	
	private class SecaoRateioCusto extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private DataView<OrReqplanilhalancto> rateios;
		private WebMarkupContainer tabelaRateios;
		private Label totalValor, totalPorcentagem;
		
		public SecaoRateioCusto(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			totalValor = (Label) new Label("totalValor", new PropertyModel<String>(rateioCustoProvider, "totalValor")).setOutputMarkupId(true);
			totalPorcentagem = (Label) new Label("totalPorcentagem", new PropertyModel<String>(rateioCustoProvider, "totalPercentual")).setOutputMarkupId(true);
			
			rateios = new DataView<OrReqplanilhalancto>("rateios", rateioCustoProvider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<OrReqplanilhalancto> item) {
					final OrReqplanilhalancto lancto = (OrReqplanilhalancto) item.getDefaultModelObject();
					
					Label linhaLbl = new Label("linhaLbl", lancto.getNrolinha());
					Label centroCustoLbl = new Label("centroCustoLbl", lancto.getCentrocustodb());
					Label contaDebitoLbl = new Label("contaDebitoLbl", lancto.getContadebito());
					Label contaCreditoLbl = new Label("contaCreditoLbl", lancto.getContacredito());
					Label historicoLbl = new Label("historicoLbl", lancto.getHistorico());
					
					DefaultZeroBigDecimalModel vlrLancamentoModel = new DefaultZeroBigDecimalModel(new PropertyModel<BigDecimal>(lancto, "vlrlancamento"));
					TextField<String> valorLbl = new TextField<String>("valorLbl", vlrLancamentoModel);
					
					DefaultZeroBigDecimalModel percentualModel = new DefaultZeroBigDecimalModel(new PropertyModel<BigDecimal>(lancto, "percrateio"));
					Label porcentagem = new Label("porcentagem", percentualModel);		
					
					valorLbl.add(criaUpdateEvent(item, lancto));			
					item.add(linhaLbl, centroCustoLbl, contaDebitoLbl, contaCreditoLbl, historicoLbl, porcentagem, valorLbl).setOutputMarkupId(true);
				}
				
				private AjaxFormComponentUpdatingBehavior criaUpdateEvent(final Item<OrReqplanilhalancto> item, final OrReqplanilhalancto lancto){
					return new AjaxFormComponentUpdatingBehavior("change") {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onUpdate(AjaxRequestTarget target) {
							OrReqplanilhalancto lancto = (OrReqplanilhalancto) item.getDefaultModelObject();
							BigDecimal valorRateio = lancto.getVlrlancamento();
							
							if(valorRateio == null || valorRateio.compareTo(BigDecimal.ZERO) < 0){
								valorRateio = BigDecimal.ZERO;
								lancto.setVlrlancamento(BigDecimal.ZERO);
							}							
							
							BigDecimal valorSolicitacao = solicitacaoPagamento.getValor();
							if(valorSolicitacao != null && valorSolicitacao.compareTo(BigDecimal.ZERO) > 0) {
								BigDecimal percentualCalculado = BigDecimal.ZERO;
								BigDecimal _100 = new BigDecimal(100);
								
								percentualCalculado = (valorRateio.multiply(_100)).divide(valorSolicitacao, 2, RoundingMode.HALF_EVEN);
								lancto.setPercrateio(percentualCalculado);
							}
							
							rateioCustoProvider.recalcular();
							
							target.add(item, totalValor, totalPorcentagem);					
						}
					};
				}
				
			};
			tabelaRateios = new WebMarkupContainer("tabelaRateios");
			
			tabelaRateios.add(rateios, totalValor, totalPorcentagem);
			add(tabelaRateios);
		}		
	}
	
	@SuppressWarnings("unused")
	private class RateioCustoProvider implements IDataProvider<OrReqplanilhalancto>{
		private static final long serialVersionUID = 1L;
		private BigDecimal somaValor = BigDecimal.ZERO;
		private BigDecimal percentualTotal = BigDecimal.ZERO;
		private String totalValor, totalPercentual;

		@Override
		public void detach() {
			
		}

		@Override
		public Iterator<? extends OrReqplanilhalancto> iterator(long first, long count) {
			recalcular();
			
			return solicitacaoPagamento.getOrReqplanilhalanctos().iterator();
		}

		@Override
		public long size() {
			return solicitacaoPagamento.getOrReqplanilhalanctos().size();
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
		
		public void clean() {
			solicitacaoPagamento.getOrReqplanilhalanctos().clear();
		}
		
		public void add(OrReqplanilhalancto obj) {
			Set<OrReqplanilhalancto> set = new HashSet<OrReqplanilhalancto>(solicitacaoPagamento.getOrReqplanilhalanctos());
			set.add(obj);
			
			solicitacaoPagamento.getOrReqplanilhalanctos().clear();
			solicitacaoPagamento.getOrReqplanilhalanctos().addAll(set);
		}
		
		public void remove(OrReqplanilhalancto obj) {
			solicitacaoPagamento.getOrReqplanilhalanctos().remove(obj);
			recalcular();
		}
		
		public void recalcular() {
			percentualTotal = BigDecimal.ZERO;
			somaValor = BigDecimal.ZERO;
			BigDecimal valorTotalSolicitacao = solicitacaoPagamento.getValor();
			
			for(OrReqplanilhalancto lcto : solicitacaoPagamento.getOrReqplanilhalanctos()) {
				somaValor = somaValor.add(lcto.getVlrlancamento());
			}
			
			if(valorTotalSolicitacao != null && valorTotalSolicitacao.compareTo(BigDecimal.ZERO) > 0){
				BigDecimal _100 = new BigDecimal(100);
				percentualTotal = somaValor.divide(valorTotalSolicitacao, 2, RoundingMode.HALF_DOWN).multiply(_100);
			}
			
			totalValor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(somaValor);
			totalPercentual = percentualTotal + "%";
		}

		public String getTotalValor() { return totalValor; } 
		public void setTotalValor(String totalValor) { this.totalValor = totalValor; }
		public String getTotalPercentual() { return totalPercentual; }
		public void setTotalPercentual(String totalPercentual) { this.totalPercentual = totalPercentual; }
		
	}
	
	
	public void zeraTabelaRateiosCentroDeCusto(AjaxRequestTarget target){
		String totalValor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(BigDecimal.ZERO);
		String totalPercentual = "0%";

		rateioCustoProvider.setTotalValor(totalValor);
		rateioCustoProvider.setTotalPercentual(totalPercentual);
		
		
		for(OrReqplanilhalancto rateios : solicitacaoPagamento.getOrReqplanilhalanctos()) {
			rateios.setVlrlancamento(BigDecimal.ZERO);
			rateios.setPercrateio(BigDecimal.ZERO);
		}
		
		target.add(this);
	}

}
