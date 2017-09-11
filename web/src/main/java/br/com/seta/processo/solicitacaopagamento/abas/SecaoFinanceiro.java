package br.com.seta.processo.solicitacaopagamento.abas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.Fc5Datautil;
import br.com.seta.processo.dto.OrSolicitacaovencto;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.solicitacaopagamento.SolicitacaoPagamentoTemplate;

public class SecaoFinanceiro extends Panel{
	private static final long serialVersionUID = 1L;
	
	private static final String TIPO_PRAZO = "Prazo";
	private static final String TIPO_PARCELADO = "Parcelado";
	
	private SolicitacaoPagamento solicitacao;
	private DocumetoPgtoProvider documetoPgtoProvider = new DocumetoPgtoProvider();	
	@Inject private Fc5Datautil fc5Datautil;
	
	public SecaoFinanceiro(String id, final SolicitacaoPagamento solicitacao, final SolicitacaoPagamentoTemplate template) {
		super(id);
		this.solicitacao = solicitacao;
		final WebMarkupContainer rptLctos = (WebMarkupContainer) new WebMarkupContainer("rptLctos").setOutputMarkupId(true);
		
		setOutputMarkupId(true);
		
		List<String> opcoes = Arrays.asList(new String[]{TIPO_PRAZO, TIPO_PARCELADO});
		
		if(solicitacao.getTipoGeracaoParecelas() == null){
			solicitacao.setTipoGeracaoParecelas(TIPO_PRAZO);
		}
		
		final DropDownChoice<String> cmbTipo = new DropDownChoice<>("cmbTipo", new PropertyModel<String>(solicitacao, "tipoGeracaoParecelas"), opcoes);
		final TextField<Integer> txtPrazoDias = new TextField<>("txtPrazoDias", new PropertyModel<Integer>(solicitacao, "qtdPrazo"));
		final TextField<Integer> txtQtdDias = new TextField<>("txtQtdDias", new PropertyModel<Integer>(solicitacao, "qtdDiasEntreVenc"));
		final TextField<Integer> txtQtdParcelas = new TextField<>("txtQtdParcelas", new PropertyModel<Integer>(solicitacao, "qtdParcelas"));
		final DateTextField txtDataVencimento = new DateTextField("txtDataVencimento", new PropertyModel<Date>(solicitacao, "dataVencimentoInicial"));
		final CheckBox chkGeraFixo = new CheckBox("chkGeraFixo", new PropertyModel<Boolean>(solicitacao, "geraDiaFixo"));
		
		final WebMarkupContainer grpPrazo = (WebMarkupContainer) new WebMarkupContainer("grpPrazo").setOutputMarkupId(true);
		final WebMarkupContainer grpCamposPrazo = (WebMarkupContainer) new WebMarkupContainer("grpCamposPrazo").setOutputMarkupId(true);
		grpCamposPrazo.add(txtPrazoDias);
		grpPrazo.add(grpCamposPrazo);
		
		final WebMarkupContainer grpParcelas = (WebMarkupContainer) new WebMarkupContainer("grpParcelas").setOutputMarkupId(true);
		final WebMarkupContainer grpCamposParcelados = (WebMarkupContainer) new WebMarkupContainer("grpCamposParcelados").setOutputMarkupId(true);
				
		grpCamposParcelados.add(txtQtdDias, txtQtdParcelas, txtDataVencimento, chkGeraFixo);
		grpParcelas.add(grpCamposParcelados);
		
		if(solicitacao.getTipoGeracaoParecelas().equals(TIPO_PRAZO)) {
			grpCamposPrazo.setVisible(true);
			grpCamposParcelados.setVisible(false);
		}
		else {
			grpCamposPrazo.setVisible(false);
			grpCamposParcelados.setVisible(true);
		}
		
		cmbTipo.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
			if(solicitacao.getTipoGeracaoParecelas().equals(TIPO_PRAZO)) {
					grpCamposPrazo.setVisible(true);
					grpCamposParcelados.setVisible(false);
				}
				else {
					grpCamposPrazo.setVisible(false);
					grpCamposParcelados.setVisible(true);
				}
				
				documetoPgtoProvider.clear();
				target.add(grpPrazo, grpParcelas, rptLctos);
				
				if(!solicitacao.getTipoGeracaoParecelas().equals(TIPO_PRAZO))
					target.appendJavaScript("iniciarCamposData();");
			}
		});
		
		DataView<OrSolicitacaovencto> dtvLctos = new DataView<OrSolicitacaovencto>("dtvLctos", documetoPgtoProvider) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(Item<OrSolicitacaovencto> item) {
				final OrSolicitacaovencto lcto = (OrSolicitacaovencto) item.getDefaultModelObject();
				DateFormat df = DateFormat.getDateInstance();
				item.add(new Label("parcela", lcto.getNroparcela()));
				item.add(new Label("dataVencimento", df.format(lcto.getDtavencimento())));
				item.add(new Label("dataProgramada", df.format(lcto.getDtaprogramada())));
				item.add(new Label("valorLiquido",  NumberFormat.getCurrencyInstance().format(lcto.getVlrtotal())));
			}
		};
		
		Label totalLcto = new Label("totalLcto", new PropertyModel<String>(documetoPgtoProvider, "valorTotalExibicao"));
		
		rptLctos.add(dtvLctos, totalLcto);
		
		AjaxButton btnIncluirLcto = new AjaxButton("btnIncluirLcto") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if(!validaCamposNecessarios(template, target))
					return;
				
				//CALCULA O VALOR PARA OS LANCAMENTOS A PRAZO E PARCELADO, O MESMO ADICIONA NO PROVIDER
				calculaLancamento();
				
				target.add(rptLctos);
			}
		};
		
		add(cmbTipo,grpPrazo, grpParcelas, rptLctos, btnIncluirLcto);
	}
	
	
	
	private boolean validaCamposNecessarios(SolicitacaoPagamentoTemplate template, AjaxRequestTarget target) {
		List<String> erros = new ArrayList<>();
		boolean houveErros = false;
		
		if(solicitacao.getValor() == null || (solicitacao.getValor() != null && solicitacao.getValor().doubleValue() <= 0)) {
			houveErros = true;
			erros.add("O Valor deve ser maior que zero na etapa de Solicitação.");
		}
		
		if(solicitacao.getNroEmpresa() == null) {
			houveErros = true;
			erros.add("Selecione uma Empresa na etapa de Solicitação.");
		}
		
		if(solicitacao.getNaturezaDespesa() == null || (solicitacao.getNaturezaDespesa() != null && solicitacao.getNaturezaDespesa().isEmpty())) {
			houveErros = true;
			erros.add("Selecione uma Nat. Despesa na etapa de Solicitação.");
		}
		
		if(solicitacao.getTipoGeracaoParecelas().equals(TIPO_PRAZO)) {
			if(solicitacao.getDataEmissao() == null) {
				houveErros = true;
				erros.add("Selecione uma Emissão na etapa de Solicitação.");
			}
			
			if(solicitacao.getQtdPrazo() == null || (solicitacao.getQtdPrazo()!= null && solicitacao.getQtdPrazo() < 0)) {
				houveErros = true;
				erros.add("O Prazo deve se ser maior ou igual a zero.");
			}
		}
		
		if(solicitacao.getTipoGeracaoParecelas().equals(TIPO_PARCELADO)) {
			if(solicitacao.getQtdParcelas() == null || (solicitacao.getQtdParcelas()!= null && solicitacao.getQtdParcelas() <= 0)) {
				houveErros = true;
				erros.add("A Qtd Parcelas deve ser maior que zero.");
			}
			
			if(!solicitacao.isGeraDiaFixo()) {
				if(solicitacao.getQtdDiasEntreVenc() == null || (solicitacao.getQtdDiasEntreVenc()!= null && solicitacao.getQtdDiasEntreVenc() <= 0)) {
					houveErros = true;
					erros.add("Dias entre Vcto. deve ser maior que zero.");
				}
			}
			
			if(solicitacao.getDataVencimentoInicial() == null) {
				houveErros = true;
				erros.add("Vcto. Inicial é obrigatório.");
			}
		}
		
		if(houveErros) {
			template.setMensagensErro(erros, "", target);
			return false;
		}
		
		return true;
	}
	
	
	/*
	 * CALCULA O VALOR PARA OS LANCAMENTOS A PRAZO E PARCELADO, O MESMO ADICIONA NO PROVIDER
	 */
	@SuppressWarnings("deprecation")
	private void calculaLancamento() {
		if(solicitacao.getTipoGeracaoParecelas().equals(TIPO_PRAZO)) {
			documetoPgtoProvider.clear();
			
			OrSolicitacaovencto lcto = new OrSolicitacaovencto();
			lcto.setVlrtotal(solicitacao.getValor());
			lcto.setNroparcela((short)1);
			lcto.setQtdparcela(1);
			lcto.setQtdPrazo(solicitacao.getQtdPrazo());
			
			Date vcto = new Date(solicitacao.getDataEmissao().getTime()); 
			
			vcto.setDate(solicitacao.getDataEmissao().getDate() + solicitacao.getQtdPrazo());
			
			vcto = fc5Datautil.getDate(vcto, solicitacao.getNroEmpresa().toString());
			
			lcto.setDtavencimento(vcto);
			lcto.setDtaprogramada(vcto);
			
			documetoPgtoProvider.add(lcto);
		}
		else {
			documetoPgtoProvider.clear();
			int qtdParcelas = solicitacao.getQtdParcelas();
			
			for(int i = 1; i <= qtdParcelas; i++) {
				BigDecimal valorParcelas = solicitacao.getValor().divide(new BigDecimal(solicitacao.getQtdParcelas()), 2, RoundingMode.UP); 
				
				if(i == qtdParcelas) {
					BigDecimal valorTotalCalculado = valorParcelas.multiply(new BigDecimal(qtdParcelas));
					if(valorTotalCalculado.doubleValue() < solicitacao.getValor().doubleValue() 
							|| valorTotalCalculado.doubleValue() > solicitacao.getValor().doubleValue()) {
						valorParcelas = valorParcelas.add(solicitacao.getValor().subtract(valorTotalCalculado));
					}
				}
				
				OrSolicitacaovencto lcto = new OrSolicitacaovencto();
				lcto.setVlrtotal(valorParcelas);
				lcto.setNroparcela((short)i);
				lcto.setQtdparcela(qtdParcelas);
				lcto.setQtdPrazo(null);
				
				if(solicitacao.isGeraDiaFixo()) {
					Date vcto = new Date(solicitacao.getDataVencimentoInicial().getTime());
					vcto.setMonth(vcto.getMonth() + (i-1));
					
					vcto = fc5Datautil.getDate(vcto, solicitacao.getNroEmpresa().toString());
					
					lcto.setDtavencimento(vcto);
					lcto.setDtaprogramada(vcto);
				} else {
					Date vcto = new Date(solicitacao.getDataVencimentoInicial().getTime());
					vcto.setDate(vcto.getDate() + (solicitacao.getQtdDiasEntreVenc() * i));
					
					vcto = fc5Datautil.getDate(vcto, solicitacao.getNroEmpresa().toString());
					
					lcto.setDtavencimento(vcto);
					lcto.setDtaprogramada(vcto);
				}
				
				documetoPgtoProvider.add(lcto);
			}
		}
	}
	
	
	/*
	 * PROVIDER DO GRID DE LANCAMENTOS
	 */
	private class DocumetoPgtoProvider implements IDataProvider<OrSolicitacaovencto> {
		private static final long serialVersionUID = 1L;
		private BigDecimal valorTotal = BigDecimal.ZERO;
		private String valorTotalExibicao = "R$ 0,00";
		
		public DocumetoPgtoProvider() { }
		
		@Override
		public void detach() { }

		@Override
		public Iterator<? extends OrSolicitacaovencto> iterator(long first, long count) {
			recalcular();
			return solicitacao.getOrSolicitacaovenctos().iterator(); 
		}

		@Override
		public long size() { return solicitacao.getOrSolicitacaovenctos().size(); }

		@Override
		public IModel<OrSolicitacaovencto> model(final OrSolicitacaovencto object) {
			return new LoadableDetachableModel<OrSolicitacaovencto>() {
				private static final long serialVersionUID = 1L;
				@Override
				protected OrSolicitacaovencto load() {
					return object;
				}
			};
		}
		
		public void clear() {
			this.valorTotal = BigDecimal.ZERO;
			this.valorTotalExibicao = "R$ 0,00";
			solicitacao.getOrSolicitacaovenctos().clear(); 
		}
		
		public void add(OrSolicitacaovencto obj) {
			solicitacao.getOrSolicitacaovenctos().add(obj);
			recalcular();
		}
		
		public void recalcular() {
			this.valorTotal = BigDecimal.ZERO;
			for (OrSolicitacaovencto obj : solicitacao.getOrSolicitacaovenctos()) {
				this.valorTotal = this.valorTotal.add(obj.getVlrtotal());
			}
			
			this.valorTotalExibicao = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(this.valorTotal);
		}
		
		@SuppressWarnings("unused")
		public String getValorTotalExibicao() {
			return valorTotalExibicao;
		}

		public void setValorTotalExibicao(String valorTotalExibicao) {
			this.valorTotalExibicao = valorTotalExibicao;
		}
		
	}
	
	public void zeraTabelaParcelas(AjaxRequestTarget target){
		solicitacao.getOrSolicitacaovenctos().clear();
		documetoPgtoProvider.setValorTotalExibicao("R$ 0,00");

		target.add(this);
	}
	
}
