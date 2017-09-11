package br.com.seta.processo.page;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Session;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.service.ExecuteRestAPI;

/**
 * 
 * Classe base para todas páginas que representam uma tarefa (task) do processo. 
 * As classes que extendê-na devem utilizar o seu construtor com parâmetros para receber e tratar os dados vindos na URL (url query string).
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public abstract class TaskPage extends Templete {
	
	private static final long serialVersionUID = 1L;
	
	private long taskId = 0;	
	private HumanTask humanTask;
	private static final String TASK_ID = "id";
	private Integer caseId;
	
	
	@Inject
	private ExecuteRestAPI restApi;
	
	public TaskPage(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException {
		this.taskId = pageParameters.get(TASK_ID).toLong(0L);
		retriveHumanTask();
		this.caseId = ((Long) humanTask.getCaseId()).intValue();
		setTituloPagina(humanTask.getDisplayName());
		setDadosAdicionais("Case Id: " + this.caseId);		
	}
	
	public long getTaskId() {
		return taskId;
	}
	
	public Integer getCaseId() {
		return caseId;
	}
	
	public HumanTask getHumanTask(){
		return humanTask;
	}
	
	private void retriveHumanTask() throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		User usuario = (User) Session.get().getAttribute("user");
		this.humanTask = restApi.retriveHumanTask(usuario, taskId);
	}
	
}
