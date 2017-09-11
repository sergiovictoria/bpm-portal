package br.com.seta.processo.suprimentos.componentespagina.abas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.CalculoRequisicao;
import br.com.seta.processo.bean.Transportadoras;
import br.com.seta.processo.cadastrofornecedores.dominios.SimNao;
import br.com.seta.processo.dominios.FormaPagamentoSuprimentos;
import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.OrRequisicaovencto;
import br.com.seta.processo.dto.Transportadora;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.DesdobramentoException;
import br.com.seta.processo.model.DefaultMoneyModel;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoFormaPagamento extends Panel {
	private static final long serialVersionUID = 1L;

	private OrRequisicao requisicao;

	@Inject	private CalculoRequisicao calculoRequisicao;
	@Inject	private Transportadoras transportadoras;
	@Inject	private ExecuteRestAPI executeRestAPI; 

	private BonitaPage parentPage;
	private DropDownChoice<String> formaPagamento;
	private TextField<String> prazoPagto;
	private CalcularDesdobramentoBtn geraVencimentosBtn;
	private DesdobramentosDataView desdobramentosDV;
	private WebMarkupContainer tabelaDesdobramentos;
	private DropDownChoice<String> transportadoraCb;
	private DropDownChoice<Transportadora> valorTranspCb;
	private DropDownChoice<String> freteCb;
	private DropDownChoice<String> localDeEntrega;
	private TextField<Object> valorFrete;
	private TextArea<String> observacao;
	private transient User user = (User) Session.get().getAttribute("user");

	public SecaoFormaPagamento(String id, final OrRequisicao requisicao, BonitaPage parentPage) {
		super(id);
		this.requisicao = requisicao;
		this.parentPage = parentPage;


		if(this.requisicao.getOrRequisicaovenctos() == null)
			this.requisicao.setOrRequisicaovenctos(new HashSet<OrRequisicaovencto>());		

		formaPagamento = new DropDownChoice<String>("formaPagamento", new PropertyModel<String>(requisicao, "formaPagamento"), FormaPagamentoSuprimentos.getValores());
		prazoPagto = new TextField<String>("prazoPgto", new PropertyModel<String>(requisicao, "prazopagto"));
		geraVencimentosBtn = new CalcularDesdobramentoBtn("geraVencimentosBtn");
		desdobramentosDV = new DesdobramentosDataView("desdobramentos", new OrRequisicaovenctoProvider());
		tabelaDesdobramentos = (WebMarkupContainer) new WebMarkupContainer("tabelaDesdobramentos").setOutputMarkupId(true);

		valorFrete =  new TextField<Object>("valorFrete", new DefaultMoneyModel(new PropertyModel<BigDecimal>(requisicao, "vlrFrete")) );
		final WebMarkupContainer secaoValorFrete = (WebMarkupContainer) new WebMarkupContainer("secaoValorFrete"){
			private static final long serialVersionUID = 1L;

			protected void onBeforeRender() {
				super.onBeforeRender();
				if(requisicao.getOpcaoFrete().equals("SIM")){
					setVisible(true);
				}else{
					setVisible(false);	
				}

			}			
		};
		final WebMarkupContainer valorFreteContainer = (WebMarkupContainer) new WebMarkupContainer("valorFreteContainer").setOutputMarkupId(true);
		secaoValorFrete.add(valorFrete);
		valorFreteContainer.add(secaoValorFrete);

		IChoiceRenderer<? super Transportadora> renderer = new IChoiceRenderer<Transportadora>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(Transportadora object) {
				return object.getNomerazao();
			}

			@Override
			public String getIdValue(Transportadora object, int index) {
				return object.getSeqpessoa().toPlainString();
			}
		};
		valorTranspCb = new DropDownChoice<Transportadora>("valorTranspCb", new PropertyModel<Transportadora>(requisicao, "transportadora"), transportadoras.buscaTodasTransportadoras(), renderer);

		final WebMarkupContainer transportadoraContainer = (WebMarkupContainer) new WebMarkupContainer("transportadoraContainer").setOutputMarkupId(true);
		final WebMarkupContainer secaoTransportadora = (WebMarkupContainer) new WebMarkupContainer("secaoTransportadora"){
			private static final long serialVersionUID = 1L;

			protected void onBeforeRender() {
				super.onBeforeRender();
				if(requisicao.getOpcaoTransportadora().equals("SIM")){
					setVisible(true);
				}else{
					setVisible(false);	
				}				
			};

		};
		secaoTransportadora.add(valorTranspCb);
		transportadoraContainer.add(secaoTransportadora);

		transportadoraCb = new DropDownChoice<String>("transportadoraCb", new PropertyModel<String>(requisicao, "opcaoTransportadora"), SimNao.getValores());
		transportadoraCb.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(requisicao.getOpcaoTransportadora().equals("SIM")){
					secaoTransportadora.setVisible(true);
				}else{
					secaoTransportadora.setVisible(false);	
					requisicao.setTransportadora(null);
				}

				target.add(transportadoraContainer);

			}
		});

		freteCb = new DropDownChoice<String>("freteCb", new PropertyModel<String>(requisicao, "opcaoFrete"), SimNao.getValores());
		freteCb.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(requisicao.getOpcaoFrete().equals("SIM")){					
					secaoValorFrete.setVisible(true);
				}else{
					secaoValorFrete.setVisible(false);
					requisicao.setVlrFrete(BigDecimal.ZERO);
				}

				target.add(valorFreteContainer);
			}
		});
		observacao = new TextArea<String>("observacao", new PropertyModel<String>(requisicao, "observacao"));
		localDeEntrega = new DropDownChoice<String>("localDeEntrega", new PropertyModel<String>(requisicao, "localDeEntrega"), localEntregas(user));


		localDeEntrega.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (requisicao.getTipoDespesa().equals("Serviço")) {
					requisicao.setGrupoRecebimento("CSC Validação");
				}else {
					requisicao.setGrupoRecebimento(requisicao.getLocalDeEntrega());
				}
			}

		});

		tabelaDesdobramentos.add(desdobramentosDV);
		add(formaPagamento, prazoPagto, geraVencimentosBtn, tabelaDesdobramentos, transportadoraCb, freteCb, observacao, localDeEntrega, valorFreteContainer, transportadoraContainer);
	}

	public void atualizaSecao(AjaxRequestTarget target){
		target.add(tabelaDesdobramentos);
	}

	public TextField<String> getPrazoPagamentoInput(){
		return this.prazoPagto;
	}

	public DropDownChoice<String> getFormaPagamentoDropDown(){
		return this.formaPagamento;
	}

	public void habilitaFormaPagamentoDropDoown(boolean habilitado){
		getFormaPagamentoDropDown().setEnabled(habilitado);
	}

	public AjaxButton getGerarVencimentosBtn(){
		return this.geraVencimentosBtn;
	}

	public TextArea<String> getObservacaoInput(){
		return this.observacao;
	}

	public void habilitaLocalDeEntrega(boolean habilitado){
		localDeEntrega.setEnabled(habilitado);
	}

	public void habilitaObservacaoInput(boolean habilitado){
		getObservacaoInput().setEnabled(habilitado);
	}

	public DropDownChoice<String> getTransportadoraCb(){
		return this.transportadoraCb;
	}

	public void habilitaTransportadoraCb(boolean habilitado){
		getTransportadoraCb().setEnabled(habilitado);
	}

	public DropDownChoice<Transportadora> getValorTranspCb(){
		return this.valorTranspCb;
	}

	public void habilitaValorTranspCb(boolean habilitado){
		getValorTranspCb().setEnabled(habilitado);
	}

	public DropDownChoice<String> getFreteCb(){
		return this.freteCb;
	}

	public void habilitaFreteCb(boolean habilitado){
		getFreteCb().setEnabled(habilitado);
	}

	public TextField<Object> getValorFreteInput(){
		return this.valorFrete;
	}

	public void habilitaValorFreteInput(boolean habilitado){
		getValorFreteInput().setEnabled(habilitado);
	}

	public WebMarkupContainer getTabelaDesdobramentos(){
		return this.tabelaDesdobramentos;
	}

	public void habilitaTabelaDesdobramentos(boolean habilitado){
		getTabelaDesdobramentos().setEnabled(habilitado);
	}

	private class CalcularDesdobramentoBtn extends AjaxButton {
		private static final long serialVersionUID = 1L;

		public CalcularDesdobramentoBtn(String id) {
			super(id);
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			String prazopagto = requisicao.getPrazopagto();
			
			if(requisicao.getValor().compareTo(BigDecimal.ZERO) <= 0){
				parentPage.setMensagemErro("O valor total da requisição deve ser maior que zero para gerar vencimentos", target);
				return;
			}
			
			if(prazopagto == null || prazopagto.trim().isEmpty()){
				parentPage.setMensagemErro("Preencher o prazo de pagamento", target);
				return;
			}
			
			if(!validarFormatoPrazoPagamento(prazopagto)){
				parentPage.setMensagemErro("O formato do prazo de pagamento está incorreto. Ex: 15/20/30", target);
				return;
			}
			
			if(!validarOrdemPrazoPagamento(prazopagto)){
				parentPage.setMensagemErro("As parcelas do prazo de pagamento devem estar na ordem crescente", target);
				return;
			}			
			
			try {
				calculoRequisicao.calculaValorTotalEVencimentos(requisicao);
				target.add(tabelaDesdobramentos);
			} catch (DesdobramentoException e) {
				parentPage.setMensagemErro("Ocorreu um erro no cálculo", target);
			}
		}

	}	

	private class DesdobramentosDataView extends DataView<OrRequisicaovencto>{
		private static final long serialVersionUID = 1L;

		protected DesdobramentosDataView(String id, IDataProvider<OrRequisicaovencto> dataProvider) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(final Item<OrRequisicaovencto> item) {
			OrRequisicaovencto vencimento = (OrRequisicaovencto) item.getDefaultModelObject();
			Label dtavencimento = new Label("dtavencimento", new PropertyModel<Date>(vencimento, "dtavencimento"));
			Label nroParcela = new Label("nroparcela", new PropertyModel<Short>(vencimento, "nroparcela"));
			DefaultMoneyModel vlrParcelaModel = new DefaultMoneyModel(new PropertyModel<BigDecimal>(vencimento, "vlrliquido"));
			TextField<Object> vlrParcela = new TextField<Object>("vlrParcela", vlrParcelaModel);

			vlrParcela.add(new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					target.add(item);					
				}
			});

			item.add(dtavencimento, nroParcela, vlrParcela).setOutputMarkupId(true);
		}		
	}

	/**
	 * Provider que popula a tabela que contém os desdobramentos
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class OrRequisicaovenctoProvider implements IDataProvider<OrRequisicaovencto>{

		private static final long serialVersionUID = 1L;

		@Override
		public void detach() {

		}

		@Override
		public Iterator<? extends OrRequisicaovencto> iterator(long first, long count) {
			ArrayList<OrRequisicaovencto> vencimentos = new ArrayList<OrRequisicaovencto>(requisicao.getOrRequisicaovenctos());
			Collections.sort(vencimentos, new Comparator<OrRequisicaovencto>() {

				@Override
				public int compare(OrRequisicaovencto r1, OrRequisicaovencto r2) {
					short nroParcela1 = r1.getNroparcela();
					short nroParcela2 = r2.getNroparcela();
					return nroParcela1 - nroParcela2;
				}
			});
			
			return vencimentos.iterator();
		}

		@Override
		public long size() {
			return requisicao.getOrRequisicaovenctos().size();
		}

		@Override
		public IModel<OrRequisicaovencto> model(final OrRequisicaovencto object) {
			return new LoadableDetachableModel<OrRequisicaovencto>() {
				private static final long serialVersionUID = 1L;
				@Override
				protected OrRequisicaovencto load() {
					return object;
				}
			};
		}

	}

	private List<String> localEntregas(User user) {
		List<String> strings = new ArrayList<String>();
		try {
			List<Group> groups = executeRestAPI.retriveSubGroupS(user,1000,"Check - In");
			for (Group dto : groups) {
				strings.add(dto.getDisplayName());
			}
		}catch(Exception e){
		}
		return strings;
	}

	public void habilitaPrazoPagamento(boolean habilitado) {
		this.prazoPagto.setEnabled(habilitado);
	}

	public void habilitaGerarVencimentosBtn(boolean habilitado) {
		this.geraVencimentosBtn.setEnabled(habilitado);
	}	
	
	private boolean validarFormatoPrazoPagamento(String prazoPagamento){
		String REGEX_PRAZO_PAGTO = "^(\\d+\\/?)+$";
		Pattern pattern = Pattern.compile(REGEX_PRAZO_PAGTO);
		
		return pattern.matcher(prazoPagamento).matches();
	}
	
	private boolean validarOrdemPrazoPagamento(String prazoPagamento){
		StringTokenizer strTokenizer = new StringTokenizer(prazoPagamento, "/");
		List<Integer> parcelas = new ArrayList<Integer>();
		
		while(strTokenizer.hasMoreTokens()){
			String strParcela = strTokenizer.nextToken();
			parcelas.add(Integer.valueOf(strParcela));
		}
		
		return validarOrdemPrazoPagamento(parcelas);
	}
	
	private boolean validarOrdemPrazoPagamento(List<Integer> parcelas){		
		if(parcelas.size() < 2) 
			return true;
		
		int menorParcela = parcelas.get(0);
		
		for(int i = 1; i < parcelas.size(); i++){
			if(menorParcela < parcelas.get(i)){
				menorParcela =  parcelas.get(i);
			}else{
				return false;
			}
		}		
		
		return true;
	}
}
