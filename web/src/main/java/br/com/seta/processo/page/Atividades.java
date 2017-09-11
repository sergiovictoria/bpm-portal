package br.com.seta.processo.page;

import static br.com.seta.processo.utils.BonitaPriorityConstants.ABOVE_NORMAL_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.HIGHEST_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.LOWEST_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.NORMAL_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.UNDER_NORMAL_PRIORITY;
import static br.com.seta.processo.utils.PriorityIconCssClass.ABOVE_NORMAL_ICON;
import static br.com.seta.processo.utils.PriorityIconCssClass.HIGHEST_ICON;
import static br.com.seta.processo.utils.PriorityIconCssClass.LOWEST_ICON;
import static br.com.seta.processo.utils.PriorityIconCssClass.NORMAL_ICON;
import static br.com.seta.processo.utils.PriorityIconCssClass.UNDER_NORMAL_ICON;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.model.TaskModel;
import br.com.seta.processo.pagecomponentes.TaskTimelinePanel;
import br.com.seta.processo.provider.TaskProvider;
import br.com.seta.processo.service.ExecuteRestAPI;

public class Atividades extends Templete {

	private static final long serialVersionUID = 1L;
	
	public final static int QUANT_ATIVIDADES_POR_PAGINA = 10;		
	private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yy  HH:mm:ss");	

	private AtividadesForm atividadesForm;
	private WebMarkupContainer secaoTimeline;
	private TaskModel atividadeSelecionada = new TaskModel();
	private DataView<HumanTask> atividadesDataView;
	private AjaxPagingNavigator navigator;
	private transient User user = (User) Session.get().getAttribute("user");
	private TaskTimelinePanel timeline;	
	@Inject
	private ExecuteRestAPI executeRestAPI;
	
	public Atividades() throws HttpStatus401Exception, ParseException, IOException, HttpStatusException {  
		atividadesForm = new AtividadesForm("atividadesForm");
		secaoTimeline = (WebMarkupContainer) new WebMarkupContainer("secaoTimeline").setOutputMarkupId(true);
		timeline = (TaskTimelinePanel) new TaskTimelinePanel("timeline", user).setOutputMarkupId(true);
		add(atividadesForm);
		secaoTimeline.add(timeline);
		atividadesForm.add(secaoTimeline);
		setTituloPagina("Atividades");
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
	}  

	public class AtividadesForm extends Form<Void>{
		private static final long serialVersionUID = 1L;		

		private TaskProvider taskProvider;
		//private AjaxPagingNavigator navigator;
		private WebMarkupContainer atividadesContainer;	
		private WebMarkupContainer navigatorContainer;			
		private TextField<String> filtroAtividadeInput;
		private AjaxButton filtroAtividadeBtn;

		public AtividadesForm(String id) throws HttpStatus401Exception, ParseException, IOException, HttpStatusException {
			super(id);

			taskProvider = new TaskProvider();			
			atividadesContainer = (WebMarkupContainer) new WebMarkupContainer("atividadesContainer").setOutputMarkupId(true);	
			navigatorContainer = (WebMarkupContainer) new WebMarkupContainer("navigatorContainer").setOutputMarkupId(true);			
			filtroAtividadeInput = new TextField<String>("buscaAtividadeInput", Model.of(""));
			filtroAtividadeBtn = new AjaxButton("buscaAtividadeBtn", this) {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {					
					String filtroAtividade = filtroAtividadeInput.getModelObject();
					taskProvider.setFilter(filtroAtividade);
					filtroAtividadeInput.setDefaultModelObject("");
					
					if(existePaginacao()){
						navigator.setVisible(true);
					}else{
						navigator.setVisible(false);
					}
					
					target.add(atividadesContainer, navigatorContainer, secaoTimeline);
					target.appendJavaScript("setEstiloAtividades(); existeAtividades();");
				}
			};
			atividadesDataView = new DataView<HumanTask>("atividadesDataView", taskProvider) {		
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(Item<HumanTask> item) {
					final HumanTask atividade = (HumanTask)item.getModelObject();

					Label descricaoLabel = (Label) new Label("descricao", atividade.getDisplayName())
					.add(AttributeAppender.append("title", atividade.getDisplayName()));
					item.add(descricaoLabel);
					item.add(new Label("rootContainerId", atividade.getRootCaseId()));
					item.add(new Label("rootContainer", atividade.getRootContainerId().getDisplayName()));
					AttributeAppender cssPriorityClass = criarCssPrioridade(atividade.getPriority());
					AttributeAppender titleHtmlAtribute = criarPrioridadeTitleHtml(atividade.getPriority());
					item.add(new Label("prioridadeIcone").add(cssPriorityClass, titleHtmlAtribute));
					AttributeAppender cssPrazoClass = obterClassCssPrazo(atividade.getDueDate());
					item.add(new Label("prazo", dateFormater.format(atividade.getDueDate())).add(cssPrazoClass));
					item.add(new Label("prazoRelogio").add(cssPrazoClass));
					item.add(new Label("prioridade", getPrioridade(atividade.getPriority())));
					item.add(new AjaxButton("executarAtividadeBtn") {					
						private static final long serialVersionUID = 1L;						
						@Override
						protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
							try {
								executeRestAPI.executeAssignTask(user, atividade);
								RedirectPage atividadePage = obtemAtividadePage(atividade);
								setResponsePage(atividadePage);
							}catch(Exception e){
								throw new RuntimeException(e);
							}
						}
					});
					item.add(new AjaxEventBehavior("click") {
						private static final long serialVersionUID = 1L;
						@Override
						protected void onEvent(AjaxRequestTarget target) {
							try{
								atividadeSelecionada.setTask(atividade);							
								timeline.criaTimeLinePara(atividadeSelecionada.getTask().getCaseId(), false);
								target.add(timeline);
							}catch(HttpStatus401Exception | HttpStatus404Exception | GenericHttpStatusException | IOException e){
								throw new RuntimeException(e);
							}
						}
					});		
				}
			};	
			atividadesDataView.setItemsPerPage(QUANT_ATIVIDADES_POR_PAGINA);
			atividadesDataView.setOutputMarkupId(true);

			selecionaPrimeiroItemDaPaginaCorrente();

			navigator = new AjaxPagingNavigator("navigator", atividadesDataView){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onAjaxEvent(AjaxRequestTarget target) {					
					target.add(atividadesContainer, navigatorContainer, secaoTimeline);
					target.appendJavaScript("setEstiloAtividades(); existeAtividades();");
				}
				
				@Override
				protected void onBeforeRender() {					
					super.onBeforeRender();
					try {
						selecionaPrimeiroItemDaPaginaCorrente();
						//if(!existePaginacao()) setVisible(false);					
						
					} catch (HttpStatus401Exception | HttpStatus404Exception | GenericHttpStatusException | IOException e) {
						throw new RuntimeException(e);
					}
				}				
			};			
			
			navigator.setOutputMarkupId(true);
			navigatorContainer.add(navigator);
			atividadesContainer.add(atividadesDataView);	
			add(atividadesContainer, navigatorContainer, filtroAtividadeInput, filtroAtividadeBtn);				
		}		
	}	

	private RedirectPage obtemAtividadePage(HumanTask ht) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		String processDefinitionId = String.valueOf(ht.getProcessId());
		String taskName = ht.getName();
		String taskId = "?id=" + ht.getId();
		br.com.seta.processo.dto.Form taskForm = executeRestAPI.retriveTaskForm(user,processDefinitionId, taskName);
		String url = obtemContexto() + taskForm.getUrl() + taskId;
		return new RedirectPage(url);
	}
	
	private AttributeAppender criarCssPrioridade(String prioridade){
		String cssPriorityIconClass = obterClasseCssIconePrioridade(prioridade);
		return AttributeAppender.append("class", cssPriorityIconClass);
	}

	private AttributeAppender criarPrioridadeTitleHtml(String prioridade){
		String title = getPrioridade(prioridade);
		return AttributeAppender.append("title", title);
	}
	
	
	private String getPrioridade (String priority){
		switch (priority) {
			case LOWEST_PRIORITY:
				return "Baixíssimo";
			case UNDER_NORMAL_PRIORITY:
				return "Baixo";
			case NORMAL_PRIORITY:
				return "Normal";
			case ABOVE_NORMAL_PRIORITY:
				return "Acima do Normal";
			case HIGHEST_PRIORITY:
				return "Altíssimo";
			default:
				return "";
		}
	}

	private String obterClasseCssIconePrioridade(String prioridade) {
		switch (prioridade) {
		case LOWEST_PRIORITY:
			return LOWEST_ICON;
		case UNDER_NORMAL_PRIORITY:
			return UNDER_NORMAL_ICON;
		case NORMAL_PRIORITY:
			return NORMAL_ICON;
		case ABOVE_NORMAL_PRIORITY:
			return ABOVE_NORMAL_ICON;
		case HIGHEST_PRIORITY:
			return HIGHEST_ICON;
		default:
			return "";
		}
	}

	private AttributeAppender obterClassCssPrazo(Date prazo){
		String estiloCSS = atividadeAtrasada(prazo)? "atrasado" : "pontual";

		return AttributeAppender.append("class", estiloCSS);
	}

	private boolean atividadeAtrasada(Date prazo){
		Date agora = new Date();
		return agora.after(prazo);
	}

	private void selecionaPrimeiroItemDaPaginaCorrente() throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		Iterator<Item<HumanTask>> items = atividadesDataView.getItems();

		if(items.hasNext()){
			Item<HumanTask> primeiroItem = items.next();
			atividadeSelecionada.setTask(primeiroItem.getModelObject());
			timeline.criaTimeLinePara(atividadeSelecionada.getTask().getCaseId(), false);
		}
	}
	
	private boolean existePaginacao(){
		long pageCount = atividadesDataView.getPageCount();
		return pageCount > 1;
	}	
}
