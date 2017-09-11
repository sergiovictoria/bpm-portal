package br.com.seta.processo.suprimentos.componentespagina.abas;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
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

import br.com.seta.processo.bean.ComplementoCentroCustos;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.OrvCentrocusto;
import br.com.seta.processo.model.DefaultMoneyModel;

public class SecaoContabilizacaoValidacao extends Panel {
	private static final long serialVersionUID = 1L;	
	
	private OrRequisicao requisicao;	
	
	@Inject
	private ComplementoCentroCustos complementoCentroCusto;
	
	private TextField<String> naturezaDespesa;
	private SecaoRateioCusto secaoRateioCusto;
	private SecaoRateiosValidados secaoRateiosValidados;
	private ValidarCRDialog validarCRDialog;
	
	
	private Set<OrReqplanilhalancto> rateiosValidados = new HashSet<OrReqplanilhalancto>();
	
	@SuppressWarnings("unchecked")
	public SecaoContabilizacaoValidacao(String id, OrRequisicao requisicao, Set<OrReqplanilhalancto> rateiosValidados) {
		super(id);
		
		setOutputMarkupId(true);
		
		this.requisicao = requisicao;
		if(requisicao.getOrReqplanilhalanctos() == null)	
			requisicao.setOrReqplanilhalanctos(new HashSet<OrReqplanilhalancto>());		
		
		this.rateiosValidados = rateiosValidados;
		
		naturezaDespesa = (TextField<String>) new TextField<String>("naturezaDespesa", new PropertyModel<String>(requisicao, "naturezaDespesa")).setEnabled(false);
		secaoRateioCusto = new SecaoRateioCusto("secaoRateioCusto");
		secaoRateiosValidados = new SecaoRateiosValidados("secaoRateiosValidados");
		validarCRDialog = new ValidarCRDialog("validarCRDialog");
		
		add(naturezaDespesa, secaoRateioCusto, secaoRateiosValidados, validarCRDialog);
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
			TextField<Integer> centroResultado = new TextField<Integer>("centroResultado", new PropertyModel<Integer>(lancto, "centrocustodb"));
			Label centroCustoLbl = new Label("centroCustoLbl", lancto.getCentrocustodb());
			Label contaDebitoLbl = new Label("contaDebitoLbl", lancto.getContadebito());
			Label contaCreditoLbl = new Label("contaCreditoLbl", lancto.getContacredito());
			Label historicoLbl = new Label("historicoLbl", lancto.getHistorico());
			Label porcentagem = new Label("porcentagem", lancto.getPercrateio());
			DefaultMoneyModel vlrLancamentoModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(lancto, "vlrlancamento"));
			Label valorLbl = new Label("valorLbl", vlrLancamentoModel);
			
			AjaxButton validarCrBtn = new AjaxButton("validarCrBtn") {

				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					validarCRDialog.validaItem(lancto, target);
					
				}
			};
			
			item.add(linhaLbl, centroResultado, centroCustoLbl, contaDebitoLbl, contaCreditoLbl, historicoLbl, porcentagem, valorLbl, validarCrBtn);			
		}	
		
	}
	
	private class SecaoRateiosValidados extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private RateiosValidadosDV rateiosValidadosDV;

		public SecaoRateiosValidados(String id) {
			super(id);	
			
			setOutputMarkupId(true);
			rateiosValidadosDV = new RateiosValidadosDV("rateiosValidadosDV");
			add(rateiosValidadosDV);
		}
		
	}
	
	private class RateiosValidadosDV extends DataView<OrReqplanilhalancto>{
		private static final long serialVersionUID = 1L;

		protected RateiosValidadosDV(String id) {
			super(id, new RateiosValidadosProvider());
		}

		@Override
		protected void populateItem(Item<OrReqplanilhalancto> item) {
			final OrReqplanilhalancto lancto = (OrReqplanilhalancto) item.getDefaultModelObject();
			
			Label linhaLbl = new Label("linhaLbl", lancto.getNrolinha());
			Label centroResultado = new Label("centroResultado", lancto.getCentrocustodb());
			Label centroCustoLbl = new Label("centroCustoLbl", lancto.getCentrocustodb());
			Label contaCreditoLbl = new Label("contaCreditoLbl", lancto.getContacredito());
			Label historicoLbl = new Label("historicoLbl", lancto.getHistorico());
			Label porcentagem = new Label("porcentagem", lancto.getPercrateio());
			DefaultMoneyModel vlrLancamentoModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(lancto, "vlrlancamento"));
			Label valorLbl = new Label("valorLbl", vlrLancamentoModel);
			AjaxLink<Void> excluirBtn = new AjaxLink<Void>("excluirBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onClick(AjaxRequestTarget target) {
					rateiosValidados.remove(lancto);
					target.add(secaoRateiosValidados);					
				}		
			};
			
			
			item.add(linhaLbl, centroResultado, centroCustoLbl, porcentagem, contaCreditoLbl, historicoLbl, historicoLbl, valorLbl, excluirBtn);
		}
		
	}
	
	private class RateiosValidadosProvider implements IDataProvider<OrReqplanilhalancto>{
		private static final long serialVersionUID = 1L;

		@Override
		public void detach() {
			
		}

		@Override
		public Iterator<? extends OrReqplanilhalancto> iterator(long first, long count) {
			return rateiosValidados.iterator();
		}

		@Override
		public long size() {
			return rateiosValidados.size();
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
	
	private class ValidarCRDialog extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private TextField<String> codigo;
		private TextField<String> descricao;
		private AjaxButton addOutroCodigoBtn, confirmarBtn;
		private WebMarkupContainer mensagem;
		private WebMarkupContainer camposDescritivos;
		private OrReqplanilhalancto lancto;
		private OrvCentrocusto centroCusto;
		
		public ValidarCRDialog(String id) {
			super(id);			
			
			setOutputMarkupId(true);
			codigo = new TextField<String>("codigo", Model.of(""));
			descricao = new TextField<String>("descricao", Model.of(""));
			camposDescritivos = (WebMarkupContainer) new WebMarkupContainer("camposDescritivos").setEnabled(false);
			camposDescritivos.add(codigo, descricao);
			mensagem = new WebMarkupContainer("mensagem");
			
			addOutroCodigoBtn = new AddOutroCodigoBtn("addOutroCodigoBtn");
			confirmarBtn = new ConfirmarBtn("confirmarBtn");
			
			add(camposDescritivos, addOutroCodigoBtn, confirmarBtn, mensagem);
		}
		
		private class ConfirmarBtn extends AjaxButton{
			private static final long serialVersionUID = 1L;

			public ConfirmarBtn(String id) {
				super(id);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				rateiosValidados.add(lancto);
				target.add(secaoRateiosValidados);
				oculta(target);				
			}
			
		}
		
		private class AddOutroCodigoBtn extends AjaxButton{
			private static final long serialVersionUID = 1L;

			public AddOutroCodigoBtn(String id) {
				super(id);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				oculta(target);
			}
			
		}
		
		public void validaItem(OrReqplanilhalancto lancto, AjaxRequestTarget target){
			centroCusto = complementoCentroCusto.getCentroCusto(lancto);
			
			if(centroCusto == null){
				setVisibilidadeCampos(false);
				this.lancto = null;				
			}else{			
				this.lancto = lancto;
				setVisibilidadeCampos(true);
				descricao.setDefaultModelObject(centroCusto.getCentrocusto());
				codigo.setDefaultModelObject(centroCusto.getNroempresa());
			}			
			
			exibe(target);
		}
		
		public void exibe(AjaxRequestTarget target) {
			target.add(this);
			target.appendJavaScript("$('#validarCRDialog').modal('show')");
		}

		public void oculta(AjaxRequestTarget target) {
			target.appendJavaScript("$('#validarCRDialog').modal('hide')");
		}
		
		private void setVisibilidadeCampos(boolean visibilidade){
			mensagem.setVisible(!visibilidade);
			camposDescritivos.setVisible(visibilidade);
			confirmarBtn.setVisible(visibilidade);
		}
		
	}

}
