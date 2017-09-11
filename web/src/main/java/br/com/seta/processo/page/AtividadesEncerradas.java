package br.com.seta.processo.page;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;

import static br.com.seta.processo.comparators.InstanciaProcessoComparators.*;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.pagecomponentes.OrderCssProvider;
import br.com.seta.processo.pagecomponentes.TaskTimelinePanel;
import br.com.seta.processo.provider.AtividadesEncerradasProvider;

/**
 * Controller para a página de AtividadesEncerradas.html
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class AtividadesEncerradas extends Templete {

	private static final long serialVersionUID = 1L;
	
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static final int ITENS_POR_PAGINA = 15;
	private InstanciaDataView instanciaDataView;
	private AtividadesEncerradasProvider provider = new AtividadesEncerradasProvider();
	private AjaxPagingNavigator navigator;
	private TaskTimelinePanel timelinePanel;
	private WebMarkupContainer atividadesContainer, timelineContainer;
	private transient User user = (User) Session.get().getAttribute("user");
	private InstanciaProcesso instanciaSelecionada;
	
	public AtividadesEncerradas(){
		setTituloPagina("Atividades encerradas");
		
		FiltroForm filtroForm = new FiltroForm("filtroForm");
		instanciaDataView = (InstanciaDataView) new InstanciaDataView("casesDataView", provider, ITENS_POR_PAGINA).setOutputMarkupId(true);
		timelinePanel = (TaskTimelinePanel) new TaskTimelinePanel("timelinePanel", user).setOutputMarkupId(true);
		navigator = new AjaxPagingNavigator("navigator", instanciaDataView){	
			private static final long serialVersionUID = 1L;
			@Override
			protected void onAjaxEvent(AjaxRequestTarget target) {					
				atualizaAtividadesETimeline(target);
			}
		};
		
		atividadesContainer = (WebMarkupContainer) new WebMarkupContainer("atividadesContainer").setOutputMarkupId(true);
		timelineContainer = (WebMarkupContainer) new WebMarkupContainer("timelineContainer").setOutputMarkupId(true);
		
		atividadesContainer.add(instanciaDataView, navigator);
		timelineContainer.add(timelinePanel);
		filtroForm.add(atividadesContainer, timelineContainer);
		add(filtroForm);		
		
		AjaxEventBehavior event = new AjaxEventBehavior("onload") {	
			private static final long serialVersionUID = 1L;
			@Override
		    protected void onEvent(final AjaxRequestTarget target) {
		        if(existePaginacao()){
					navigator.setVisible(true);
				}else{
					navigator.setVisible(false);
				}
		        
		        target.add(navigator);
		    }
		};
		add(event);
		
		OrderByBorderAtividades orderId = new OrderByBorderAtividades("orderId", POR_CASE_ID);
		OrderByBorderAtividades orderProcesso = new OrderByBorderAtividades("orderProcesso", POR_PROCESSO);
		OrderByBorderAtividades orderStatus = new OrderByBorderAtividades("orderStatus", POR_STATUS);
		OrderByBorderAtividades orderTermino = new OrderByBorderAtividades("orderTermino", POR_DATA_TERMINO);
		
		atividadesContainer.add(orderId, orderProcesso, orderStatus, orderTermino);		
	}
	
	private class FiltroForm extends Form<Void>{
		private static final long serialVersionUID = 1L;
		
		private TextField<String> filtroInput;
		private DateTextField inicioInput, fimInput;
		private AjaxButton buscaBtn;
		
		public FiltroForm(String id){
			super(id);
			this.filtroInput = new TextField<String>("filtroInput", Model.of(""));
			this.inicioInput = new DateTextField("inicioInput", Model.of(dataInicial()), DATE_PATTERN);
			this.fimInput = new DateTextField("fimInput", Model.of(dataFinal()), DATE_PATTERN);
			
			this.buscaBtn = new AjaxButton("buscaBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					String filtro = (String) filtroInput.getDefaultModelObject();
					Date inicio = (Date) inicioInput.getDefaultModelObject();
					Date fim = (Date) fimInput.getDefaultModelObject();
					fim = finalDoDiaDe(fim);
					
					if(!validaDatas(inicio, fim, target)) return;
					
					provider.setFiltros(filtro, inicio, fim);
					
					if(existePaginacao()){
						navigator.setVisible(true);
					}else{
						navigator.setVisible(false);
					}
					
					target.add(atividadesContainer, timelineContainer);
					target.appendJavaScript("initTabelas(); visibilidadeCampos()");
				}
			};
			
			provider.setFiltros((String)filtroInput.getDefaultModelObject(), 
					(Date)inicioInput.getDefaultModelObject(), (Date)fimInput.getDefaultModelObject());
			
			add(filtroInput, inicioInput, fimInput, buscaBtn);
		}		
	}	

	private class InstanciaDataView extends DataView<InstanciaProcesso>{
		
		private static final long serialVersionUID = 1L;

		protected InstanciaDataView(String id, IDataProvider<InstanciaProcesso> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<InstanciaProcesso> item) {
			final InstanciaProcesso instancia = (InstanciaProcesso) item.getDefaultModelObject();
			Label idLabel = new Label("id", instancia.getCaseId());
			Label processoLabel = new Label("processo", instancia.getNomeProcesso());
			Label terminoLabel = new Label("termino", instancia.getFim());
			Label statusLabel = new Label("status", instancia.getStatus());
			
			AjaxEventBehavior onclickEvent = new AjaxEventBehavior("click"){			
				private static final long serialVersionUID = 1L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {
					try {
						instanciaSelecionada = instancia;
						timelinePanel.criaTimeLinePara(instancia.getCaseId(), true);
						target.add(timelinePanel);
					} catch (HttpStatus401Exception | HttpStatus404Exception | GenericHttpStatusException | IOException e) {
						throw new RuntimeException(e);
					}
				}				
			};
			
			item.add(idLabel, processoLabel, statusLabel, terminoLabel);
			item.add(onclickEvent);
		}
		
		@Override
		protected void onBeforeRender() {
			super.onBeforeRender();
			Iterator<Item<InstanciaProcesso>> items = getItems();
			if(items.hasNext()){
				instanciaSelecionada = items.next().getModelObject();
				criaTimelineParaInstanciaSelecionada();
			}
		}
	}
	
	/**
	 * Ordenador de colunas da tabela de Intâncias encerradas
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	@SuppressWarnings("rawtypes")
	private class OrderByBorderAtividades extends AjaxFallbackOrderByBorder {

		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unchecked")
		public OrderByBorderAtividades(String id, Object sortProperty) {
			super(id, sortProperty, provider, new OrderCssProvider<InstanciaProcesso>());
		}

		@Override
		protected void onAjaxClick(AjaxRequestTarget target) {
			atualizaAtividadesETimeline(target);
		}				

		@Override
		protected void onSortChanged() {
		}

	}
	
	private void atualizaAtividadesETimeline(AjaxRequestTarget target) {
		target.add(atividadesContainer, timelineContainer);
		target.appendJavaScript("initTabelas(); visibilidadeCampos();");
	}
	
	private void criaTimelineParaInstanciaSelecionada(){
		try{
			timelinePanel.criaTimeLinePara(instanciaSelecionada.getCaseId(), true);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	private boolean existePaginacao(){
		long pageCount = instanciaDataView.getPageCount();
		return pageCount > 1;
	}	
	
	private boolean validaDatas(Date inicio, Date fim, AjaxRequestTarget target){
		if(inicio == null || fim == null){
			setMensagemErro("Preencha as datas, para efetuar a busca", target);
			return false;
		}
		
		if(fim.before(inicio)){
			setMensagemErro("A data de início não pode ser maior do que a data de fim", target);
			return false;
		}
		
		return true;
	}
	
	private Date dataFinal(){
		return finalDoDiaDe(new Date());
	}
	
	private Date dataInicial(){
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date dataInicial = calendar.getTime();
		
		return addDays(dataInicial, -30);
	}
	
	private Date addDays(java.util.Date date, int days) {
		Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
		
	}
	
	public Date finalDoDiaDe(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
		return calendar.getTime();
	}
}
