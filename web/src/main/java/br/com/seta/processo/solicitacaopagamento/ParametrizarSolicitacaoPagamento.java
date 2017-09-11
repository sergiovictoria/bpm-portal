package br.com.seta.processo.solicitacaopagamento;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.ConfiguracoesSolicitacaoPagamentoService;
import br.com.seta.processo.bean.EmpresaFontePagadoraService;
import br.com.seta.processo.dto.ConfiguracaoSolicitacaoPagamento;
import br.com.seta.processo.entity.CategoriaNaturezaDespesa;
import br.com.seta.processo.entity.FontePagadora;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.provider.FontePagadoraProvider;
import br.com.seta.processo.provider.NaturezaDespesaProvider;

/**
 * 
 * Controller da página ParametrizarSolicitacaoPagamento.html
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ParametrizarSolicitacaoPagamento extends Templete {
	private static final long serialVersionUID = 1L;
	
	private static final Long QUANT_ITENS_FONTE_PAGADORA = 20L;
	private static final Long QUANT_ITENS_NATUREZA_DESPESA = 20L;
	
	@Inject
	EmpresaFontePagadoraService mapeamentoEmpresaFontePagadora;
	@Inject
	ConfiguracoesSolicitacaoPagamentoService configuracoesService;
	
	private WebMarkupContainer fontesPagadorasContainer, naturezaDespesaContainer,  secaoConfiguracoes;
	private SecaoReplicaLoja secaoReplicaLoja;
	private FontePagadoraDataView fontePagadoraDataView;
	private NaturezaDespesaDataView naturezaDataView;
	private AjaxPagingNavigator fontePagadoraNavigator, naturezaDespesaNavigator; 
	private AjaxButton salvarBtn, buscaFontePagadoraBtn, buscaNatDespesaBtn;
	private TextField<String> buscaFontePagadoraInput, buscaNatDespesaInput;
	private WebMarkupContainer msgFontePagadoraContainer, msgNatDespesaContainer, fontePagadoraTabela, naturezaDespesaTabela;
	
	private FontePagadoraProvider fontesPagadorasProvider = new FontePagadoraProvider();
	private NaturezaDespesaProvider naturezaDespesaProvider;
	private ConfiguracaoSolicitacaoPagamento configuracoes;
	
	private FontePagadora fonteAtualSelecionada;
	private Set<FontePagadora> fontesHabilitadas = new HashSet<FontePagadora>(); //cache local de fontes já habilitadas
	
	public ParametrizarSolicitacaoPagamento(){
		setTituloPagina("Parametrizar Solicitação de Pagamento");
		this.fonteAtualSelecionada = recuperaPrimeiraFontePagadora();
		this.fontesHabilitadas = new HashSet<FontePagadora>(fontesPagadorasProvider.getFontePagadorasHabilitadas());
		
		this.configuracoes = configuracoesService.buscaConfiguracaoSolicitacaoPagamento();
		this.secaoConfiguracoes = new SecaoConfiguracoes("secaoConfiguracoes", configuracoes);
		
		Form<Void> parametrizacaoForm = new Form<Void>("parametrizacaoForm");		
		
		this.buscaFontePagadoraInput = new TextField<String>("buscaFontePagadoraInput", Model.of(""));
		this.buscaFontePagadoraBtn = new BuscaEmpresasBtn("buscaFontePagadoraBtn");
		
		this.buscaNatDespesaInput = new TextField<String>("buscaNatDespesaInput", Model.of(""));
		this.buscaNatDespesaBtn = new BuscaNatDespesaBtn("buscaNatDespesaBtn");
		
		this.fontePagadoraDataView = new FontePagadoraDataView("fontesPagadorasDataView", fontesPagadorasProvider, QUANT_ITENS_FONTE_PAGADORA);
		this.fontePagadoraTabela = (WebMarkupContainer) new WebMarkupContainer("fontePagadoraTabela").setOutputMarkupId(true);
		fontePagadoraTabela.add(fontePagadoraDataView);
		this.fontePagadoraNavigator = new FontePagadoraNavigator("fontePagadoraNavigator", fontePagadoraDataView);
		this.msgFontePagadoraContainer = (WebMarkupContainer) new WebMarkupContainer("msgFontePagadoraContainer").setOutputMarkupId(true).setVisible(false);
		this.fontesPagadorasContainer = (WebMarkupContainer) new WebMarkupContainer("fontesPagadorasContainer").setOutputMarkupId(true);
		
		this.naturezaDespesaProvider = new NaturezaDespesaProvider(this.fonteAtualSelecionada);
		
		this.naturezaDataView = new NaturezaDespesaDataView("natureDataView", this.naturezaDespesaProvider, QUANT_ITENS_NATUREZA_DESPESA);
		this.naturezaDespesaTabela = (WebMarkupContainer) new WebMarkupContainer("naturezaDespesaTabela").setOutputMarkupId(true);
		this.naturezaDespesaTabela.add(naturezaDataView);
		this.naturezaDespesaNavigator = new AjaxPagingNavigator("naturezaDespesaNavigator", naturezaDataView);
		this.msgNatDespesaContainer = (WebMarkupContainer) new WebMarkupContainer("msgNatDespesaContainer").setOutputMarkupId(true).setVisible(false);
		this.naturezaDespesaContainer = (WebMarkupContainer) new WebMarkupContainer("naturezaDespesaContainer").setOutputMarkupId(true);
		
		this.salvarBtn = new SalvarBtn("salvarBtn");
		
		fontesPagadorasContainer.add(buscaFontePagadoraInput, buscaFontePagadoraBtn, fontePagadoraTabela, fontePagadoraNavigator, msgFontePagadoraContainer);
		naturezaDespesaContainer.add(buscaNatDespesaInput, buscaNatDespesaBtn, naturezaDespesaTabela, naturezaDespesaNavigator, msgNatDespesaContainer);
		parametrizacaoForm.add(salvarBtn, fontesPagadorasContainer, naturezaDespesaContainer, secaoConfiguracoes);
		
		this.secaoReplicaLoja = new SecaoReplicaLoja("secaoReplicaLoja");
		
		add(parametrizacaoForm, this.secaoReplicaLoja);
	}
	
	private class SalvarBtn extends AjaxButton{	
		private static final long serialVersionUID = 1L;

		public SalvarBtn(String id) {
			super(id);			
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {	
			try {
				configuracoesService.salvaConfiguracaoSolicitacaoPagamento(configuracoes);
				mapeamentoEmpresaFontePagadora.atualizaFontesPagadoras(fontesHabilitadas);
				exibeMensagemSucesso("Sucesso", "Dados atualizados com sucesso", target);	
			} catch (ValidacaoBeanException exception) {
				setMensagensErro(exception.getMessages(), target);
			}		
		}		
	}
	
	private class BuscaEmpresasBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;

		public BuscaEmpresasBtn(String id) {
			super(id);			
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			String filtro = (String) buscaFontePagadoraInput.getDefaultModelObject();
			fontesPagadorasProvider.setNomeReduzido(filtro);
			fonteAtualSelecionada = fontesPagadorasProvider.getFontesPagadoras().get(0);
			
			if(fontesPagadorasProvider.getFontesPagadoras().isEmpty()){
				exibiMensagemFontePagadora(target, true);
			}else{
				exibiMensagemFontePagadora(target, false);
				fontesHabilitadas.remove(fonteAtualSelecionada);
				fontesHabilitadas.add(fonteAtualSelecionada);
			}
			
			target.add(fontesPagadorasContainer, naturezaDespesaContainer);	
			target.appendJavaScript("selecionaPrimeiraLinha()");
		}		
	}
	
	private class BuscaNatDespesaBtn extends AjaxButton{	
		private static final long serialVersionUID = 1L;

		public BuscaNatDespesaBtn(String id) {
			super(id);
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			String filtro = (String) buscaNatDespesaInput.getDefaultModelObject();
			naturezaDespesaProvider.setDescricao(filtro);
			
			if(naturezaDespesaProvider.getNaturezasDespesas().isEmpty()){
				exibiMensagemNaturezaDespesa(target, true);
			}else{
				exibiMensagemNaturezaDespesa(target, false);
			}
			
			target.add(naturezaDespesaContainer);
		}		
	}	
	
	private class FontePagadoraDataView extends DataView<FontePagadora>{
		private static final long serialVersionUID = 1L;

		protected FontePagadoraDataView(String id, IDataProvider<FontePagadora> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);			
		}

		@Override
		protected void populateItem(Item<FontePagadora> item) {
			final FontePagadora fontePagadora = (FontePagadora) item.getDefaultModelObject();
			final String idHtml = item.getMarkupId();
			Label idLabel = new Label("nroEmpresa", fontePagadora.getNroEmpresa());
			Label nomeReduzidoLabel = new Label("nomeReduzido", fontePagadora.getNomeReduzido());
			Label cnpjLabel = new Label("cnpj", fontePagadora.getNroCGC() + "-" + fontePagadora.getDigitoCGC());
			CheckBox habilitadoCheck = new CheckBox("habilitarFontePagadora", new PropertyModel<Boolean>(fontePagadora, "habilitado"));
			Label labelCheckbox = (Label) new Label("labelCheckbox").add(AttributeAppender.append("for", habilitadoCheck.getMarkupId()));			
			
			AjaxEventBehavior onchangeEvent = new AjaxEventBehavior("change"){			
				private static final long serialVersionUID = 1L;
				@Override
				protected void onEvent(AjaxRequestTarget target) {
					boolean flagHabilitado = !fontePagadora.isHabilitado();					
					if(flagHabilitado){
						fontePagadora.setHabilitado(flagHabilitado);
						fontesHabilitadas.add(fontePagadora);
					}else{
						fontePagadora.setHabilitado(!flagHabilitado);
						fontesHabilitadas.remove(fontePagadora);
						fontePagadora.removeTodasNaturezasDespesas();						
					}
					selecionaFonteComoAtualEAtualizaNaturezaDespesa(fontePagadora, target);					
					target.appendJavaScript("selecionaLinha(\""+idHtml+"\")");
				}				
			};		
			
			habilitadoCheck.add(onchangeEvent );
			
			WebMarkupContainer tdNroEmpresa = (WebMarkupContainer) new WebMarkupContainer("tdNroEmpresa").add(idLabel).add(criaOnclickEventLinhaFontePagadora(fontePagadora, idHtml));
			WebMarkupContainer tdNomeReduzido = (WebMarkupContainer) new WebMarkupContainer("tdNomeReduzido").add(nomeReduzidoLabel).add(criaOnclickEventLinhaFontePagadora(fontePagadora, idHtml));
			WebMarkupContainer tdCnpj = (WebMarkupContainer) new WebMarkupContainer("tdCnpj").add(cnpjLabel).add(criaOnclickEventLinhaFontePagadora(fontePagadora, idHtml));
			
			item.add(tdNroEmpresa, tdNomeReduzido, tdCnpj, habilitadoCheck, labelCheckbox);
			
		}
	}
	
	private class FontePagadoraNavigator extends AjaxPagingNavigator{
		private static final long serialVersionUID = 1L;

		public FontePagadoraNavigator(String id, IPageable pageable) {
			super(id, pageable);
		}
		
		@Override
		protected void onAjaxEvent(AjaxRequestTarget target) {			
			target.add(fontesPagadorasContainer, naturezaDespesaContainer);	
			target.appendJavaScript("selecionaPrimeiraLinha()");
		}
		
		@Override
		protected void onBeforeRender() {
			super.onBeforeRender();
			//A cada paginação devemos selecionar a primeira fonte pagadora como a corrente e carregar a lista de natureza despesa referente a esta fonte pagadora
			Iterator<Item<FontePagadora>> items = fontePagadoraDataView.getItems();
			if (items.hasNext()) {
				Item<FontePagadora> item = items.next();
				fonteAtualSelecionada = (FontePagadora) item.getDefaultModelObject();
				naturezaDespesaProvider.setFontePagadora(fonteAtualSelecionada);
			}else{
				naturezaDespesaProvider.setFontePagadora(null);
			}			
		}
	}
	
	private class NaturezaDespesaDataView extends DataView<CategoriaNaturezaDespesa>{	
		private static final long serialVersionUID = 1L;

		protected NaturezaDespesaDataView(String id, IDataProvider<CategoriaNaturezaDespesa> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);			
		}

		@Override
		protected void populateItem(final Item<CategoriaNaturezaDespesa> item) {
			item.setOutputMarkupId(true);
			final CategoriaNaturezaDespesa naturezaDespesa =(CategoriaNaturezaDespesa)item.getDefaultModelObject();
			Label id = new Label("idNatDespesa", naturezaDespesa.getCodHistorico());
			Label descricao = new Label("descricao", naturezaDespesa.getDescricao());
			CheckBox habilitarDespesaNatureza = new CheckBox("habilitarDespesaNatureza", new PropertyModel<Boolean>(naturezaDespesa, "habilitado"));
			final CheckBox habilitarChaveAcesso = new CheckBox("habilitarChaveAcesso", new PropertyModel<Boolean>(naturezaDespesa, "chaveAcesso"));
			habilitarChaveAcesso.setOutputMarkupId(true);
			
			AjaxFormComponentUpdatingBehavior onChangeEvent = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {					
					boolean flagHabilitado = naturezaDespesa.isHabilitado();
					naturezaDespesa.setHabilitado(flagHabilitado);
					if(flagHabilitado){						
						fonteAtualSelecionada.adicionaNaturezaDespesa(naturezaDespesa);;
					}else{
						naturezaDespesa.setChaveAcesso(false);
						fonteAtualSelecionada.removeNaturezaDespesa(naturezaDespesa);
					}
					
					habilitarChaveAcesso.setEnabled(naturezaDespesa.isHabilitado());
					target.add(habilitarChaveAcesso);
				}
			};
			
			habilitarDespesaNatureza.add(onChangeEvent);
			

			AjaxFormComponentUpdatingBehavior  onChangeEvent2 = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					fonteAtualSelecionada.removeNaturezaDespesa(naturezaDespesa);
					fonteAtualSelecionada.adicionaNaturezaDespesa(naturezaDespesa);
				}
			};
			
			habilitarChaveAcesso.add(onChangeEvent2);
			habilitarChaveAcesso.setEnabled(naturezaDespesa.isHabilitado());
			
			item.add(id, descricao, habilitarDespesaNatureza, habilitarChaveAcesso);
		}		
	}
	
	/**
	 * Seção que contém as configurações da Solicitação de Pagamento
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class SecaoConfiguracoes extends WebMarkupContainer{	
		private static final long serialVersionUID = 1L;

		private TextField<String> emailNotificacao;
		
		public SecaoConfiguracoes(String id, ConfiguracaoSolicitacaoPagamento configuracao) {
			super(id, new CompoundPropertyModel<ConfiguracaoSolicitacaoPagamento>(configuracao));	
			
			emailNotificacao = new TextField<String>("emailNotificacao");
			
			add(emailNotificacao);
		}		
	}
	
	/**
	 * Seção (Modal) que contém a ação de replicação de loja
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class SecaoReplicaLoja extends Form<Void>{	
		private static final long serialVersionUID = 1L;

		private DropDownChoice<FontePagadora> origem, destino;
		private AjaxButton replicarBtn;
		
		
		public SecaoReplicaLoja(String id) {
			super(id);
			
			IChoiceRenderer<? super FontePagadora> renderer = new IChoiceRenderer<FontePagadora>() {				
				private static final long serialVersionUID = 1L;

				@Override
				public Object getDisplayValue(FontePagadora object) {
					return object.getNomeReduzido();					
				}

				@Override
				public String getIdValue(FontePagadora object, int index) {
					return String.valueOf(object.getNroEmpresa());
				}
			};
			origem = new DropDownChoice<FontePagadora>("origem", Model.of(new FontePagadora()), mapeamentoEmpresaFontePagadora.listaFontesPagadoras(), renderer);
			destino = new DropDownChoice<FontePagadora>("destino", Model.of(new FontePagadora()), mapeamentoEmpresaFontePagadora.listaFontesPagadoras(), renderer);
			replicarBtn = new AjaxButton("replicarBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					FontePagadora fonteOrigem = (FontePagadora) origem.getDefaultModelObject();
					FontePagadora fonteDestino = (FontePagadora) destino.getDefaultModelObject();
					mapeamentoEmpresaFontePagadora.replicaFontePagadora(fonteOrigem, fonteDestino);
					fontesPagadorasProvider.setNomeReduzido("");
					target.add(fontesPagadorasContainer, naturezaDespesaContainer);
					exibeMensagemSucesso("Sucesso", "As configurações foram replicadas com sucesso", target);
				}
			};
			
			add(origem, destino, replicarBtn);
		}		
	}
	
	private AjaxEventBehavior criaOnclickEventLinhaFontePagadora(final FontePagadora fontePagadora, final String id){
		
		AjaxEventBehavior onclickEvent = new AjaxEventBehavior("click") {			
			
			private static final long serialVersionUID = 1L;
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				selecionaFonteComoAtualEAtualizaNaturezaDespesa(fontePagadora, target);
				target.appendJavaScript("selecionaLinha(\""+id+"\")");
			}
			
		};
		
		return onclickEvent;
	}
	
	private void  selecionaFonteComoAtualEAtualizaNaturezaDespesa(final FontePagadora fontePagadora, AjaxRequestTarget target) {
		this.fonteAtualSelecionada = fontePagadora;
		naturezaDespesaProvider.setFontePagadora(fontePagadora);
		
		if(!fonteAtualSelecionada.isHabilitado()){
			naturezaDespesaProvider.desabilitaTodos();
		}
		
		if(naturezaDespesaProvider.getNaturezasDespesas().isEmpty()){
			naturezaDespesaTabela.setVisible(false);
			naturezaDespesaNavigator.setVisible(false);
			msgNatDespesaContainer.setVisible(true);
		}else{
			naturezaDespesaTabela.setVisible(true);
			naturezaDespesaNavigator.setVisible(true);
			msgNatDespesaContainer.setVisible(false);
		}
		
		target.add(naturezaDespesaContainer);
	}
	
	private FontePagadora recuperaPrimeiraFontePagadora(){		
		return this.fontesPagadorasProvider.getFontesPagadoras().get(0);
	}
	
	private void exibiMensagemFontePagadora(AjaxRequestTarget target, boolean visivel){
		this.msgFontePagadoraContainer.setVisible(visivel);
		this.fontePagadoraTabela.setVisible(!visivel);
		this.fontePagadoraNavigator.setVisible(!visivel);
		this.naturezaDespesaTabela.setVisible(!visivel);
		this.naturezaDespesaNavigator.setVisible(!visivel);
		if(this.msgNatDespesaContainer.isVisible()){
			this.msgNatDespesaContainer.setVisible(false);
		}
		
		target.add(this.fontesPagadorasContainer, this.naturezaDespesaContainer);
	}
	
	private void exibiMensagemNaturezaDespesa(AjaxRequestTarget target, boolean visivel){
		this.msgNatDespesaContainer.setVisible(visivel);
		this.naturezaDespesaTabela.setVisible(!visivel);
		this.naturezaDespesaNavigator.setVisible(!visivel);
	}
}
