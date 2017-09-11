package br.com.seta.processo.pagecomponentes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.seta.processo.dto.Case;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.TaskTimeline;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.service.ExecuteRestAPI;
import static br.com.seta.processo.utils.BonitaStatusTaskConstants.READY;
import static br.com.seta.processo.utils.BonitaStatusTaskConstants.COMPLETED;
import static br.com.seta.processo.utils.BonitaStatusTaskConstants.SKIPPED;
import static br.com.seta.processo.utils.BonitaStatusTaskConstants.CANCELLED;
import static br.com.seta.processo.utils.BonitaStatusTaskConstants.FAILED;

/**
 * Panel que renderiza um componente de Timeline (Linha do Tempo) para as atividades pertecentes a um determinado case 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class TaskTimelinePanel extends Panel {

	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yy 'às' HH:mm");
	
	@Inject
	private ExecuteRestAPI restApi;
	private transient User user;
	private HumanTaskItens humanTaskItens;
	private Label inicioTimeline;
	
	public TaskTimelinePanel(String id, User user) {
		super(id);	
		this.user = user;		
		this.humanTaskItens = new HumanTaskItens("humanTaskItens", new ArrayList<HumanTask>());
		this.inicioTimeline = new Label("inicioTimeline", Model.of(""));		
		add(humanTaskItens, inicioTimeline);
	}
	
	/**
	 * Construtor para construção da timeline
	 * 
	 * @param id wicketId
	 * @param user Objeto contendo os dados do usuário
	 * @param caseId id do case
	 * @param caseArquivado true para montar uma timeline para um case arquivado e false para montar uma timeline para um case ativo
	 * @throws HttpStatus401Exception
	 * @throws HttpStatus404Exception
	 * @throws ClientProtocolException
	 * @throws GenericHttpStatusException
	 * @throws IOException
	 */
	public TaskTimelinePanel(String id, User user, long caseId, boolean caseArquivado) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		super(id);
		this.user = user;
		TaskTimeline timeline;
		
		if(caseArquivado){
			timeline = restApi.retriveArchivedTasksTimeline(user, caseId, HumanTask.Comparators.REACHED_STATE_DATE_DESC);
		}else{
			timeline = restApi.retriveHumanTasksTimeline(user, caseId, HumanTask.Comparators.REACHED_STATE_DATE_DESC);
		}		
		
		this.humanTaskItens = new HumanTaskItens("humanTaskItens",timeline.getHumanTasks());
		String textoInicioDaTimeline = criaTextoParaInicioDaTimeline(timeline.getCase());
		this.inicioTimeline = new Label("inicioTimeline", textoInicioDaTimeline);
		
		add(humanTaskItens, inicioTimeline);
	}
	
	/**
	 * Monta uma timeline
	 * 
	 * @param caseId id do case
	 * @param caseArquivado true para montar uma timeline para um case arquivado e false para montar uma timeline para um case ativo
	 * @throws HttpStatus401Exception
	 * @throws HttpStatus404Exception
	 * @throws ClientProtocolException
	 * @throws GenericHttpStatusException
	 * @throws IOException
	 */
	public void criaTimeLinePara(long caseId, boolean caseArquivado) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		TaskTimeline timeline;
		
		if(caseArquivado){
			timeline = restApi.retriveArchivedTasksTimeline(user, caseId, HumanTask.Comparators.REACHED_STATE_DATE_DESC);
		}else{
			timeline = restApi.retriveHumanTasksTimeline(user, caseId, HumanTask.Comparators.REACHED_STATE_DATE_DESC);
		}		
		
		String textoInicioDaTimeline = criaTextoParaInicioDaTimeline(timeline.getCase());
		this.humanTaskItens.setDefaultModelObject(timeline.getHumanTasks());
		this.inicioTimeline.setDefaultModelObject(textoInicioDaTimeline);
	}
	
	private String criaTextoParaInicioDaTimeline(Case cs){
		if(cs == null){
			return "";
		}		
		
		User user = cs.getStarted_by();
		Date date = cs.getStart();
		String texto = "Iniciado ";
		//Para os casos de processos iniciados via message, não temos um usuário iniciador
		if(user != null){
			String usuario = user.getFirstname() + " " + user.getLastname();			
			texto = "por " + usuario + " ";		
		}
		
		String dataFormtada = dateFormater.format(date);
		texto = texto + "em " + dataFormtada;
		
		return texto; 
	}
	
	private class HumanTaskItens extends ListView<HumanTask>{
		private static final long serialVersionUID = 1L;		
		
		public HumanTaskItens(String id) {
			super(id);			
		}

		public HumanTaskItens(String id, List<HumanTask> list) {
			super(id, list);			
		}

		@Override
		protected void populateItem(ListItem<HumanTask> item) {
			HumanTask ht = (HumanTask) item.getDefaultModelObject();
			
			if(ht == null) return;
			
			Label atividade = new Label("atividade", ht.getDisplayName());	
			Label data =  new Label("data", getData(ht));
			Label usuario = new Label("usuario", getNomeUsuario(ht));
			Label detalhamentoAtividade = new Label("detalhamentoAtividade", ht.getDisplayName());
			Label grupo = new Label("grupo", ht.getActorId().getDisplayName());
			Label status = new Label("status", deParaEstadoAtividade(ht.getState()));
			Label atribuidoAUsuario = new Label("atribuidoAUsuario", getAtribuidoA(ht));
			Label dataAtribuicao = new Label("dataAtribuicao", formataData(ht.getAssigned_date()));			
			Label executadoPor = new Label("executadoPorUsuario", getExecutadoPor(ht));
			Label dataConclusao = new Label("dataConclusao", formataData(ht.getArchivedDate()));
			Label icone = (Label) new Label("icone").add(criarCssIconeEstado(ht));
			WebMarkupContainer iconeContainer = (WebMarkupContainer) new WebMarkupContainer("iconeContainer")
				.add(icone)
				.add(criarCssEstadoAtividade(ht))
				.add(criaTitleHtmlParaEstadoAtividade(ht));			
			
			item.add(atividade, data, usuario, detalhamentoAtividade, grupo, status, atribuidoAUsuario, dataAtribuicao, executadoPor, dataConclusao, iconeContainer);			
		}
		
		private String getNomeUsuario(HumanTask ht){
			if(ht.getExecutedBy() != null){
				return ht.getExecutedBy().getFirstname();
			}
			if(ht.getAssigned_id() != null){
				return ht.getAssigned_id().getFirstname();
			}
			
			return "";
		}
		
		private String getData(HumanTask ht){
			if(ht.getAssigned_date() != null){
				return dateFormater.format(ht.getAssigned_date());
			}
			
			if(ht.getArchivedDate() != null){
				return dateFormater.format(ht.getArchivedDate());
			}
			
			return "";
		}
		
		private String getAtribuidoA(HumanTask ht){
			if(ht.getAssigned_id() != null){
				return ht.getAssigned_id().getFirstname() + " " + ht.getAssigned_id().getLastname();
			}
			return "";
		}
		
		private String getExecutadoPor(HumanTask ht){
			if(ht.getExecutedBy() != null){
				return  ht.getExecutedBy().getFirstname() + " " + ht.getExecutedBy().getLastname();
			}
			return "";
		}
		
		private String formataData(Date dt){
			if(dt == null) return "";
			return dateFormater.format(dt);
		}
	}
	
	private AttributeAppender criarCssIconeEstado(HumanTask ht){
		String cssIconClass = obtemCssIconeEstadoAtividade(ht.getState());

		return AttributeAppender.append("class", cssIconClass);
	}
	
	private String obtemCssIconeEstadoAtividade(String estado){
		if(estado == null) return "fa fa-asterisk";
		
		switch (estado) {
		case READY:
			return "fa fa-circle-o";

		case COMPLETED:
			return "fa fa-check";

		case SKIPPED:
			return "fa fa-long-arrow-right";

		case CANCELLED:
			return "fa fa-ban";

		case FAILED:
			return "fa fa-exclamation";

		default:
			return "fa fa-asterisk";
		}
	}
	
	private AttributeAppender criarCssEstadoAtividade(HumanTask ht){
		String cssIconClass = obtemCssEstadoAtividade(ht.getState());

		return AttributeAppender.append("class", cssIconClass);
	}
	
	private String obtemCssEstadoAtividade(String estado){
		if(estado == null) return "status-default";
		
		switch (estado) {
		case READY:
			return "aberto";

		case COMPLETED:
			return "completo";

		case SKIPPED:
			return "pulado";

		case CANCELLED:
			return "cancelado";

		case FAILED:
			return "em-falha";

		default:
			return "status-default";
		}
	}
	
	private AttributeAppender criaTitleHtmlParaEstadoAtividade(HumanTask ht){
		String estadoAtividade = deParaEstadoAtividade(ht.getState());
		return criaAtributoHtmlTitle(estadoAtividade);
	}
	
	private AttributeAppender criaAtributoHtmlTitle(String title){
		return AttributeAppender.append("title", title);
	}
	
	private String deParaEstadoAtividade(String estado){
		if(estado == null) return "";
		
		switch (estado) {
		case READY:
			return "Aberto";

		case COMPLETED:
			return "Completo";

		case SKIPPED:
			return "Pulado";

		case CANCELLED:
			return "Cancelado";

		case FAILED:
			return "Em falha";

		default:
			return estado;
		}
	}
}
