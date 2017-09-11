package br.com.seta.processo.teste;

import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.ATRIBUTE_ACTOR_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.ATRIBUTE_ASSIGNED_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.ATRIBUTE_EXECUTE_BY;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.ATRIBUTE_PROFESSIONAL;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.ATRIBUTE_ROOT_CONTAINER_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.FILTER_ACTOR_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.FILTER_ASSIGNED_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.FILTER_CASE_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.FILTER_GROUP_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.FILTER_GROUP_NAME;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.FILTER_PROCESS_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.FILTER_STATE;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.FILTER_USER_ID;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.MAX_NUMBER_ELEMENTS;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.ORDER_BY_PRIORITY_DESC;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.PAGE;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.PROCESS_ID_GROUP;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.SEARCH;
import static br.com.seta.processo.bonitaasserts.SearchParamsBuilder.STATE_READY;
import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_200_OK;
import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_401_UNAUTHORIZED;
import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_404_NOT_FOUND;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.ProcessRuntimeAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.bpm.data.DataInstance;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.engine.identity.GroupCriterion;
import org.bonitasoft.engine.identity.GroupSearchDescriptor;
import org.bonitasoft.engine.identity.UserCriterion;
import org.bonitasoft.engine.identity.UserMembership;
import org.bonitasoft.engine.identity.UserWithContactData;
import org.bonitasoft.engine.operation.Operation;
import org.bonitasoft.engine.operation.OperationBuilder;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;

import br.com.seta.processo.bonitaasserts.ArchivedCaseSearchUriBuilder;
import br.com.seta.processo.bonitaasserts.CaseSearchUriBuilder;
import br.com.seta.processo.bonitaasserts.FormSearchUriBuilder;
import br.com.seta.processo.bonitaasserts.ProcessSearchUriBuilder;
import br.com.seta.processo.bonitaasserts.SearchParamsBuilder;
import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.Case;
import br.com.seta.processo.dto.Form;
import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.TaskTimeline;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dto.UserGroup;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.service.FiltroDataHumanTask;
import br.com.seta.processo.service.OperadorFiltro;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;




/****
 * 
 * Serviço para acesso a rest do bonita
 * @author Sérgio da Victória 
 *
 */


public class APITeste {



	private static final String URL_HUMAN_TASKS = "/API/bpm/humanTask";	
	private static final String URL_ARCHIVED_HUMAN_TASKS = "/API/bpm/archivedHumanTask";	
	private static final String URL_RETRIVE_VARIABLE = "/API/bpm/activityVariable/";
	private static final String URL_RETRIVE_GROUP    = "/API/identity/group";
	private static final String URL_RETRIVE_USER_FOR_GROUP = "/API/identity/user";	
	

	private String hostNameBPM;
	private String portNameBPM;
	private String BONITA_URI;
	private String _URI;
	private Gson gson;
	private HttpClient httpClient;
	private HttpContext httpContext;
	private User user = new User();


	public APITeste() {
		this.hostNameBPM = "127.0.0.1";
		this.portNameBPM = "40133";
		this.BONITA_URI = "http://"+ this.hostNameBPM +":" +this.portNameBPM +"/bonita";
		this._URI = "http://"+ this.hostNameBPM +":" +this.portNameBPM;
		this.user.setUserName("install");
		this.user.setPassword("install");
	}

	/****
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws BonitaException
	 */
	public APISession doTenantLogin(String username, String password) throws BonitaException {
		APISession session = getLoginAPI().login(username, password);
		return session;
	}

	/***
	 * 
	 * @param session
	 * @throws BonitaException
	 */

	public void doTenantLogout(APISession session) throws BonitaException {
		getLoginAPI().logout(session);
		System.out.println("User '" + session.getUserName() + "' has logged out!");
	}	
	
	
//	http://localhost:40133/bonita/API/identity/group?p=0&c=100&f=parent_path=/Seta/Check%20-%20in
	
	public List<Group> retriveSubGroupS(User user, long inicio, long quantidade, String gropName) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException{
		String uriGroups = new SearchParamsBuilder(BONITA_URI + URL_RETRIVE_GROUP)
		.append(PROCESS_ID_GROUP, quantidade)
		.create()+ URLEncoder.encode(gropName,"utf-8");
		System.out.println("Executando retriveTaskList [  "+uriGroups + " ] ");		
		List<Group> groups = executeGetRequestForGruopkList(user, uriGroups);		
		System.out.println("Retornando retriveTaskList [  " + uriGroups + " ] " + groups.size());		
		return groups; 
	}

	

	/***
	 * 
	 * @param user
	 * @param task
	 * @param clazzType
	 * @param field
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatusException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object retriveVariableTask(User user, HumanTask task, Class<?> clazzType, String field) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException, InstantiationException, IllegalAccessException {
		String processUri = this.BONITA_URI + URL_RETRIVE_VARIABLE+task.getId()+"/"+field;
		System.out.println("Executando [  "+ processUri + " ] ");
		String response =  executeGetRequestVariableForTask(user,processUri);
		String value = response.substring(response.indexOf("value")+7 ,response.length());
		JsonReader reader = new JsonReader(new StringReader(value));
		reader.setLenient(true);
		Object object = this.gson.fromJson(reader, clazzType);
		return object;
	}

	/***
	 * 
	 * @param user
	 * @param taskId
	 * @param clazzType
	 * @param field
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatusException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object retriveVariableTask(User user, long taskId, Class<?> clazzType, String field) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException, InstantiationException, IllegalAccessException {
		String processUri = this.BONITA_URI + URL_RETRIVE_VARIABLE+taskId+"/"+field;
		System.out.println("Executando [  "+ processUri + " ] ");
		String response =  executeGetRequestVariableForTask(user,processUri);
		String value = response.substring(response.indexOf("value")+7 ,response.length());
		JsonReader reader = new JsonReader(new StringReader(value));
		reader.setLenient(true);
		Object object = this.gson.fromJson(reader, clazzType);
		return object;
	}

	/**
	 * Recupera uma Human Task
	 * 
	 * @param user Usuário
	 * @param taskId id da tarefa a ser buscada
	 * @return
	 * @throws HttpStatus401Exception
	 * @throws HttpStatus404Exception
	 * @throws ClientProtocolException
	 * @throws GenericHttpStatusException
	 * @throws IOException
	 */
	public HumanTask retriveHumanTask(User user, long taskId) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{

		String uriTask = new SearchParamsBuilder(BONITA_URI + URL_HUMAN_TASKS, taskId)
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.create();

		System.out.println("Executando retriveHumanTask [  "+ uriTask + " ] ");		
		HumanTask humanTask = executeGetRequestForTask(user, uriTask);		
		System.out.println("Retornando retriveHumanTask [  " + uriTask + " ] " + humanTask);

		return humanTask;
	}

	/***
	 * 
	 * @param user
	 * @return Lista de Task por Usuários
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatusException
	 */
	public List<HumanTask> retriveTaskList(User user, String filtroDeBusca) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException {
		String criterioBusca = filtroDeBusca == null ? "" : URLEncoder.encode(filtroDeBusca, "utf-8");

		String uriTasks = new SearchParamsBuilder(BONITA_URI + URL_HUMAN_TASKS)				
		.append(ORDER_BY_PRIORITY_DESC)
		.append(FILTER_STATE, STATE_READY)
		.append(FILTER_USER_ID, user.getIdUser())
		.append(SEARCH, criterioBusca)
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.create();

		System.out.println("Executando retriveTaskList [  "+ uriTasks + " ] ");		
		List<HumanTask> tasks = executeGetRequestForTaskList(user, uriTasks);		
		System.out.println("Retornando retriveTaskList [  " + uriTasks + " ] " + tasks.size());		
		return tasks; 
	}	

	/**
	 * Recupera as tarefas pendentes de um determinado usuário
	 * 
	 * @param user Usuário para o qual será recuperada as atividades
	 * @param inicio Índice do primeiro registro
	 * @param quantidade Quantidade de itens retornados a partir do índice do primeiro registro
	 * @param filtroDeBusca Opcional. Filtro contendo um critério de busca. Por exemplo, o nome da atividade
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatusException
	 */
	public List<HumanTask> retriveTaskList(User user, long inicio, long quantidade, String filtroDeBusca) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException{
		long pageIndex = getPageIndex(inicio, quantidade);		
		String criterioBusca = filtroDeBusca == null ? "" : URLEncoder.encode(filtroDeBusca, "utf-8");
		String uriTasks = new SearchParamsBuilder(BONITA_URI + URL_HUMAN_TASKS)
		.append(PAGE, pageIndex)
		.append(MAX_NUMBER_ELEMENTS, quantidade)
		.append(ORDER_BY_PRIORITY_DESC)
		.append(FILTER_STATE, STATE_READY)
		.append(FILTER_USER_ID, user.getIdUser())
		.append(SEARCH, criterioBusca)
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.create();

		System.out.println("Executando retriveTaskList [  "+ uriTasks + " ] ");		
		List<HumanTask> tasks = executeGetRequestForTaskList(user, uriTasks);		
		System.out.println("Retornando retriveTaskList [  " + uriTasks + " ] " + tasks.size());		
		return tasks; 
	}

	/**
	 * Recupera a Lista de Tarefas de um determinado Processo
	 * 
	 * @param user
	 * @param process
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatusException
	 */
	public List<HumanTask> retriveTaskList(User usuarioLogado, User usuarioSelecionado, TaskProcess processo )throws ParseException, IOException, HttpStatus401Exception, HttpStatusException {

		String uriTasks = new SearchParamsBuilder(BONITA_URI + URL_HUMAN_TASKS)			
		.append(PAGE, 0)
		.append(MAX_NUMBER_ELEMENTS, 10000)
		.append(FILTER_STATE, STATE_READY)
		.append(FILTER_ASSIGNED_ID, usuarioSelecionado.getId())
		.append(FILTER_PROCESS_ID, processo.getId())
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.create();

		System.out.println("Executando retriveTaskList [  "+ uriTasks + " ] ");		
		List<HumanTask> tasks = executeGetRequestForTaskList(usuarioLogado, uriTasks);		
		System.out.println("Retornando retriveTaskList [  " + uriTasks + " ] " + tasks.size());		
		return tasks; 
	}

	/**
	 * @param usuarioLogado Usuário logado no Sistema
	 * @param usuarioSelecionado Usuário para o qual será recuperada as atividades
	 * @param processo Processo para o qual será recuperada as atividades
	 * @param atorSelecionado Ator para o qual será recuperada as atividades
	 * @param inicio Índice do primeiro registro
	 * @param quantidade Quantidade de itens retornados a partir do índice do primeiro registro
	 * @param filtroDeBusca Opcional. Filtro contendo um critério de busca. Por exemplo, o nome da atividade
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatusException
	 */
	public List<HumanTask> retriveTaskList(User usuarioLogado, 
			User usuarioSelecionado, 
			TaskProcess processo, 
			Actor atorSelecionado, 
			long inicio, 
			long quantidade, 
			String filtroDeBusca) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException{
		long pageIndex = getPageIndex(inicio, quantidade);		
		String criterioBusca = filtroDeBusca == null ? "" : URLEncoder.encode(filtroDeBusca, "utf-8");

		String uriTasks = new SearchParamsBuilder(BONITA_URI + URL_HUMAN_TASKS)
		.append(PAGE, pageIndex)
		.append(MAX_NUMBER_ELEMENTS, quantidade)
		.append(ORDER_BY_PRIORITY_DESC)
		.append(FILTER_STATE, STATE_READY)
		.append(FILTER_PROCESS_ID, processo.getId())
		.append(FILTER_ACTOR_ID, (atorSelecionado.getId()>0 ? atorSelecionado.getId() : ""))
		.append(FILTER_ASSIGNED_ID, (null != usuarioSelecionado ? usuarioSelecionado.getId() :""))
		.append(SEARCH, criterioBusca)
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.create();

		System.out.println("Executando retriveTaskList [  "+ uriTasks + " ] ");		
		List<HumanTask> tasks = executeGetRequestForTaskList(usuarioLogado, uriTasks);		
		System.out.println("Retornando retriveTaskList [  " + uriTasks + " ] " + tasks.size());		
		return tasks; 
	}

	/**
	 * Retorna a quantidade de atividades pendentes de um determinado usuário
	 * 
	 * @param user Usuário para o qual será recuperada as atividades
	 * @param filtroDeBusca Opcional. Filtro contendo um critério de busca. Por exemplo, o nome da atividade
	 * @return
	 * @throws HttpStatus401Exception
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws HttpStatusException
	 */
	public int retriveTaskListSize(User user, String filtroDeBusca) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		String criterioBusca = filtroDeBusca == null ? "" : URLEncoder.encode(filtroDeBusca, "utf-8");
		String uriTasks = new SearchParamsBuilder(BONITA_URI + URL_HUMAN_TASKS)
		.append(PAGE, 0)
		.append(MAX_NUMBER_ELEMENTS, 10000)
		.append(FILTER_STATE, STATE_READY)
		.append(FILTER_USER_ID, user.getIdUser())
		.append(SEARCH, criterioBusca)
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.create();

		System.out.println("Executando retriveTaskListSize [  "+ uriTasks + " ] ");		
		List<HumanTask> tasks = executeGetRequestForTaskList(user, uriTasks);		
		System.out.println("Retornando retriveTaskListSize [  " + uriTasks + " ] " + tasks.size());		

		return tasks.size();
	}

	public List<HumanTask> retriveCompletedTaskListByAssignedDate(User user, long inicio, long quantidade, String filtro, Date dataInicio, Date dataFim) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		return retriveCompletedTaskListByDate(user, inicio, quantidade, filtro, FiltroDataHumanTask.POR_DATA_ATRIBUICAO, dataInicio, dataFim);
	}

	public List<HumanTask> retriveCompletedTaskListByArchivedDate(User user, long inicio, long quantidade, String filtro, Date dataInicio, Date dataFim) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		return retriveCompletedTaskListByDate(user, inicio, quantidade, filtro, FiltroDataHumanTask.POR_DATA_CONCLUSAO, dataInicio, dataFim);
	}	

	public List<HumanTask> retriveCompletedTaskListByDate(User user, long inicio, long quantidade, String filtro, int tipoData, Date dataInicio, Date dataFim) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		List<HumanTask> completedTaskList = retriveCompletedTaskList(user, inicio, quantidade, filtro);
		Predicate<HumanTask> predicadoDataInicio = Predicates.alwaysTrue();
		Predicate<HumanTask> predicadoDataFim = Predicates.alwaysTrue();

		if(dataInicio != null){
			predicadoDataInicio = new FiltroDataHumanTask(tipoData, OperadorFiltro.MAIOR_IGUAL, dataInicio, true);
		}		
		if(dataFim != null){
			predicadoDataFim = new FiltroDataHumanTask(tipoData, OperadorFiltro.MENOR_IGUAL, dataFim, true);
		}

		Predicate<HumanTask> predicados = Predicates.and(predicadoDataInicio, predicadoDataFim);
		Collection<HumanTask> collectionFiltrada = Collections2.filter(completedTaskList, predicados);

		return new ArrayList<HumanTask>(collectionFiltrada);		
	}



	private List<HumanTask> retriveCompletedTaskList(User user, long inicio, long quantidade, String filtro) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		String criterioBusca = filtro == null ? "" : URLEncoder.encode(filtro, "utf-8");
		String uriCompletedTasks = new SearchParamsBuilder(BONITA_URI + URL_ARCHIVED_HUMAN_TASKS)
		.append(PAGE, inicio)
		.append(MAX_NUMBER_ELEMENTS, quantidade)
		.append(FILTER_USER_ID, user.getIdUser())
		.append(SEARCH, criterioBusca)
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.create();		

		System.out.println("Executando retriveTaskListSize [  "+ uriCompletedTasks + " ] ");		
		List<HumanTask> completedTasks = executeGetRequestForTaskList(user, uriCompletedTasks);		
		System.out.println("Retornando retriveTaskListSize [  " + uriCompletedTasks + " ] " + completedTasks.size());		

		return completedTasks;
	}

	/****
	 * 
	 * @param user
	 * @param inicio
	 * @param quantidade
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatusException
	 */

	public List<Group> retriveGroupList(User user, long inicio, long quantidade) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException{
		long pageIndex = getPageIndex(inicio, quantidade);		
		String uriGroups = new SearchParamsBuilder(BONITA_URI + URL_RETRIVE_GROUP)
		.append(PAGE, pageIndex)
		.append(MAX_NUMBER_ELEMENTS, quantidade)
		.create();
		System.out.println("Executando [  "+ uriGroups + " ] ");		
		List<Group> groups = executeGetRequestForGroupList(user, uriGroups);		
		System.out.println("Retornando [  " + uriGroups + " ] " + groups.size());		
		return groups;
	}	

	public List<UserGroup> retriveUserForGroupList(User user, long inicio, long quantidade, long group) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException{
		long pageIndex = getPageIndex(inicio, quantidade);		
		String uriUserGroups = new SearchParamsBuilder(BONITA_URI + URL_RETRIVE_USER_FOR_GROUP)
		.append(PAGE, pageIndex)
		.append(MAX_NUMBER_ELEMENTS, quantidade)
		.append(FILTER_GROUP_ID, group)
		.create();
		System.out.println("Executando [  "+ uriUserGroups + " ] ");		
		List<UserGroup> userGroups = executeGetRequestForUserGroupList(user, uriUserGroups);		
		System.out.println("Retornando [  " + uriUserGroups + " ] " + userGroups.size());		
		return userGroups;
	}	


	public Group retriveGroupName(User user, String nameGroup) throws ParseException, IOException, HttpStatus401Exception, HttpStatusException{
		String uriGroups = new SearchParamsBuilder(BONITA_URI + URL_RETRIVE_GROUP)
		.append(FILTER_GROUP_NAME,nameGroup)       
		.create();
		System.out.println("Executando [  "+ uriGroups + " ] ");		
		Group group = executeGetRequestGroupName(user, uriGroups);		
		System.out.println("Retornando [  " + uriGroups + " ] " + uriGroups.toString());		
		return group;
	}	


	public List<TaskProcess> retriveProcessList(User user) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		String processUri = new ProcessSearchUriBuilder(this.BONITA_URI)
		.orderBy(ProcessSearchUriBuilder.ORDER_ELEMENT.DISPLAY_NAME, ProcessSearchUriBuilder.ORDER.ASC)
		.filter(ProcessSearchUriBuilder.FILTER_ELEMENT.USER_ID, user.getIdUser())
		.create();

		System.out.println("Executando [  "+ processUri + " ] ");	
		List<TaskProcess> processos = executeGetRequestForProcessList(user, processUri);
		System.out.println("Retornando [  " + processUri + " ] " + processos.size());

		return processos;
	}

	public List<TaskProcess> retriveProcessList(User user, long inicio, long quantidade) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		long pageIndex = getPageIndex(inicio, quantidade);
		String processUri = new ProcessSearchUriBuilder(this.BONITA_URI)
		.page(pageIndex)
		.numberOfElements(quantidade)
		.orderBy(ProcessSearchUriBuilder.ORDER_ELEMENT.DISPLAY_NAME, ProcessSearchUriBuilder.ORDER.ASC)
		.filter(ProcessSearchUriBuilder.FILTER_ELEMENT.USER_ID, user.getIdUser())
		.create();	

		System.out.println("Executando [  "+ processUri + " ] ");		
		List<TaskProcess> processos = executeGetRequestForProcessList(user, processUri);		
		System.out.println("Retornando [  " + processUri + " ] " + processos.size());

		return processos;
	}

	public int retriveProcessListSize(User user) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		String processUri = new ProcessSearchUriBuilder(this.BONITA_URI)		
		.filter(ProcessSearchUriBuilder.FILTER_ELEMENT.USER_ID, user.getIdUser())
		.create();

		System.out.println("Executando [  "+ processUri + " ] ");		
		List<TaskProcess> processos = executeGetRequestForProcessList(user, processUri);		
		System.out.println("Retornando [  " + processUri + " ] " + processos.size());

		return processos.size();		
	}

	/******
	 * 
	 * @param user
	 * @return Retorna dados profissional do usuário tipo fone, email, cidade etc....
	 * @throws HttpStatus401Exception
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws HttpStatusException
	 */

	public User retriveUserProfessionalData(User user) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		String uriUser = new SearchParamsBuilder(BONITA_URI + URL_RETRIVE_USER_FOR_GROUP, user.getIdUser())
		.append(ATRIBUTE_PROFESSIONAL)
		.create();
		System.out.println("Executando retrive user [  "+ uriUser + " ] ");		
		User returnUser = executeGetRequestForUser(user, uriUser);		
		System.out.println("Retornando retrive user [  " + uriUser.toString() + " ] ");		
		return returnUser;
	}


	public void startACase(TaskProcess taskProcess, User user, String nae) {
		String apiURI  = "/API/bpm/case/";
		System.out.println("Executando [  "+this.BONITA_URI+apiURI + " ] ");
		String payload = "";
		executePostRequest(apiURI, user ,payload);
	}


	public void executeAssignUpdateVariableTask(User user, HumanTask task, Object object, String field) throws BonitaException {
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		Map<String, Serializable> listVariablesSerializable = new HashMap<String, Serializable>();
		listVariablesSerializable.put(field, (Serializable) object);
		HumanTaskInstance taskToExecute = processAPI.getHumanTaskInstance(task.getId());
		processAPI.assignUserTask(task.getId(), session.getUserId());
		processAPI.updateActivityInstanceVariables(task.getId(), listVariablesSerializable);
		processAPI.executeFlowNode(taskToExecute.getId());
		doTenantLogout(session);
	}

	public List<org.bonitasoft.engine.identity.Group> findGroups(User user, int startIndex, int maxResults, GroupCriterion groupcriterion) throws BonitaException {
		List<org.bonitasoft.engine.identity.Group> grupos = new ArrayList<org.bonitasoft.engine.identity.Group>(); 
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		grupos = identityAPI.getGroups(startIndex, maxResults , groupcriterion);
		doTenantLogout(session);
		return grupos;
	}


	public org.bonitasoft.engine.identity.Group findOneGroup(User user, String groupName) throws BonitaException {

		org.bonitasoft.engine.identity.Group group = null; 
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, groupName).done(); 
		SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
		group = searchResultGroup.getResult().get(0);
		doTenantLogout(session);
		return group;

	}

	public List<org.bonitasoft.engine.identity.User> findUserForGroups(User user, long idGrupo,  int startIndex, int maxResults, UserCriterion usercriterion) throws BonitaException {
		List<org.bonitasoft.engine.identity.User> users = new ArrayList<org.bonitasoft.engine.identity.User>(); 
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		users = identityAPI.getUsersInGroup(idGrupo,startIndex,maxResults,usercriterion);
		doTenantLogout(session);
		return users;
	}


	public UserWithContactData findOneUserForContact(User user, org.bonitasoft.engine.identity.User userData) throws BonitaException {
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		UserWithContactData returnUserData = null;
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		returnUserData = identityAPI.getUserWithProfessionalDetails(userData.getId());
		doTenantLogout(session);
		return returnUserData;
	}

	public void executeAssignUpdateVariableTask(User user, long idTask, Object object, String field) throws BonitaException {
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		Map<String, Serializable> listVariablesSerializable = new HashMap<String, Serializable>();
		listVariablesSerializable.put(field, (Serializable) object);
		HumanTaskInstance taskToExecute = processAPI.getHumanTaskInstance(idTask);
		processAPI.assignUserTask(idTask, session.getUserId());
		processAPI.updateActivityInstanceVariables(idTask, listVariablesSerializable);
		processAPI.executeFlowNode(taskToExecute.getId());
		doTenantLogout(session);
	}

	/**
	 * Faz a instância do processo (representado pelo taskId) seguir para o próximo passo e atualiza uma ou mais variáveis de processo
	 * 
	 * @param user Usuário
	 * @param taskId id da tarefa corrente a ser executada
	 * @param processVariables Uma Map contendo os nomes da variáveis de processo do Bonita BPM como chave (key) e os Objetos como valores (value)
	 * @throws BonitaException Quando ocorre algum erro na API Java do Bonita BPM
	 */
	public void executeFlowAndUpdateVariable(User user, long taskId, Map<String, Serializable> processVariables) throws BonitaException{
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		HumanTaskInstance taskToExecute  = processAPI.getHumanTaskInstance(taskId);
		processAPI.updateActivityInstanceVariables(taskId, processVariables);
		processAPI.executeFlowNode(taskToExecute.getId());
		doTenantLogout(session);
	}

	/**
	 * Atualiza as variáves de processo
	 * 
	 * @param user Usuário
	 * @param taskId id da tarefa corrente a ter as variáveis atualizadas
	 * @param processVariables Uma Map contendo os nomes da variáveis de processo do Bonita BPM como chave (key) e os Objetos como valores (value)
	 * @throws BonitaException Quando ocorre algum erro na API Java do Bonita BPM
	 */
	public void executeUpdateVariable(User user, long taskId, Map<String, Serializable> processVariables) throws BonitaException{
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);		
		processAPI.updateActivityInstanceVariables(taskId, processVariables);
		doTenantLogout(session);
	}

	public void attachCaseWithDocument(User user, long idTask, byte[] file , String documentName) throws BonitaException {

		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);

		processAPI.attachDocument(idTask, documentName, documentName, "application/pdf", file);
		doTenantLogout(session);

	}

	public void updateDueDate(User user, HumanTask task, Date date) throws BonitaException {

		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);
		processAPI.updateDueDateOfTask(task.getId(),date);
		doTenantLogout(session);

	}

	public void executeAssignTask(User user,HumanTask task) throws BonitaException {

		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		processAPI.assignUserTask(task.getId(), session.getUserId());
		doTenantLogout(session);

	}

	public void executeAssignTaskUpdateDueDate(User user, HumanTask task, Date date) throws BonitaException {

		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		processAPI.updateDueDateOfTask(task.getId(),date);
		processAPI.assignUserTask(task.getId(), session.getUserId());
		doTenantLogout(session);

	}

	public void executeAssignTaskUpdateDueDate(User usuarioLogado, User usuarioSelecionado, HumanTask task, Date date) throws BonitaException {

		APISession session = doTenantLogin(usuarioLogado.getUserName(), usuarioLogado.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		processAPI.updateDueDateOfTask(task.getId(), date);
		processAPI.assignUserTask(task.getId(), usuarioSelecionado.getId());
		doTenantLogout(session);

	}

	public void executeUnassignTask(User usuarioLogado, User usuarioSelecionado, HumanTask task) throws BonitaException {

		APISession session = doTenantLogin(usuarioLogado.getUserName(),usuarioLogado.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		processAPI.assignUserTask(task.getId(), (null == usuarioSelecionado ?0 :usuarioSelecionado.getId()));
		doTenantLogout(session);

	}

	public void executeUpdateTaskVariable(User user, long taskId, Object object, String field) throws BonitaException {
		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		Map<String, Serializable> listVariablesSerializable = new HashMap<String, Serializable>();
		listVariablesSerializable.put(field, (Serializable) object);
		processAPI.updateActivityInstanceVariables(taskId, listVariablesSerializable);
		doTenantLogout(session);
	}


	public void executeUpdateTaskVariable(User user, HumanTask task, Object object, String field) throws BonitaException {

		APISession session = doTenantLogin(user.getUserName(),user.getPassword());
		ProcessRuntimeAPI processAPI = getProcessAPI(session);
		Map<String, Serializable> listVariablesSerializable = new HashMap<String, Serializable>();
		listVariablesSerializable.put(field, (Serializable) object);
		processAPI.updateActivityInstanceVariables(task.getId(), listVariablesSerializable);
		doTenantLogout(session);

	}


	public void execueteCaseWithVariable(String username, String password, Object object, String field, String processName, String version) throws BonitaException {

		APISession session = doTenantLogin(username,password);
		Map<String, Serializable> listVariablesSerializable = new HashMap<String, Serializable>();
		listVariablesSerializable.put(field, (Serializable) object);
		ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);
		long processId = processAPI.getProcessDefinitionId(processName, version);
		processAPI.startProcess(processId, listVariablesSerializable);
		doTenantLogout(session);

	}

	public void execueteCaseWithVariable(String username, String password, Map<String, Object> variables, String processName, String version) throws BonitaException {

		List<Operation> listOperations = new ArrayList<Operation>();
		Map<String, Serializable> listVariablesSerializable = new HashMap<String, Serializable>();

		for (String variableName : variables.keySet()) {
			Object value = variables.get(variableName);
			Serializable valueSerializable = (Serializable) value;
			Expression expr = new ExpressionBuilder().createExpression(variableName, variableName, value.getClass().getName(), ExpressionType.TYPE_INPUT);
			Operation op = new OperationBuilder().createSetDataOperation(variableName, expr);
			listOperations.add(op);
			listVariablesSerializable.put(variableName, valueSerializable);
		}

		APISession session = doTenantLogin(username,password);
		ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);
		long processId = processAPI.getProcessDefinitionId(processName, version);
		processAPI.startProcess(processId, listOperations, listVariablesSerializable);
		doTenantLogout(session);

	}

	/**
	 * Inicia uma nova instância de um processo (Case)
	 * 
	 * @param username
	 * @param password
	 * @param variables
	 * @param processName
	 * @param version
	 * @return caseId da nova instância
	 * @throws BonitaException
	 */
	public ProcessInstance initCaseInstanceWithVariable(String username, String password, Map<String, Object> variables, String processName, String version) throws BonitaException{
		List<Operation> listOperations = new ArrayList<Operation>();
		Map<String, Serializable> listVariablesSerializable = new HashMap<String, Serializable>();

		for (String variableName : variables.keySet()) {
			Object value = variables.get(variableName);
			Serializable valueSerializable = (Serializable) value;
			Expression expr = new ExpressionBuilder().createExpression(variableName, variableName, value.getClass().getName(), ExpressionType.TYPE_INPUT);
			Operation op = new OperationBuilder().createSetDataOperation(variableName, expr);
			listOperations.add(op);
			listVariablesSerializable.put(variableName, valueSerializable);
		}

		APISession session = doTenantLogin(username,password);
		ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);
		long processId = processAPI.getProcessDefinitionId(processName, version);
		ProcessInstance processInstance = processAPI.startProcess(processId, listOperations, listVariablesSerializable);
		doTenantLogout(session);

		return processInstance;
	}

	public Case retriveCase(long caseId, User user) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		String uriCase = new CaseSearchUriBuilder(BONITA_URI, caseId).create();

		System.out.println("Executando [" + uriCase + "]");
		String responseBody = executeGetRequest(user, uriCase);	
		Case cs = gson.fromJson(responseBody, Case.class);
		System.out.println("Retornando [" + uriCase + "] Case: " + cs);

		return cs;
	}	

	public Case retriveArchivedCase(long caseId, User user) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		String uriArchivedCase = new ArchivedCaseSearchUriBuilder(BONITA_URI).page(0).numberOfItens(1).sourceObjectId(caseId).create();


		System.out.println("Executando [" + uriArchivedCase + "]");
		String responseBody = executeGetRequest(user, uriArchivedCase);	

		Type caseListType = new TypeToken<List<Case>>() {}.getType();
		List<Case> cases = gson.fromJson(responseBody, caseListType);
		System.out.println("Retornando [" + uriArchivedCase + "] Case: " + cases);

		return cases.size() > 0 ? cases.get(0) : null ;
	}

	public TaskTimeline retriveHumanTasksTimeline(User user, Long caseId, Comparator<HumanTask> order) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		Case cs = retriveCase(caseId, user);	

		String uriArchivedTasks = new SearchParamsBuilder(BONITA_URI + URL_ARCHIVED_HUMAN_TASKS)
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.append(FILTER_CASE_ID, caseId)		
		.create();

		String uriTasks = new SearchParamsBuilder(BONITA_URI + URL_HUMAN_TASKS)	
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.append(FILTER_STATE, STATE_READY)
		.append(FILTER_CASE_ID, caseId)		
		.create();

		System.out.println("Executando [" + uriArchivedTasks + "]");
		List<HumanTask> archivedHumanTasks = executeGetRequestForTaskList(user, uriArchivedTasks);
		System.out.println("Retornando [" + uriArchivedTasks + "] " + archivedHumanTasks.size() + " itens");

		System.out.println("Executando [" + uriTasks + "]");
		List<HumanTask> humanTasks = executeGetRequestForTaskList(user, uriTasks);
		System.out.println("Retornando [" + uriTasks + "] " + humanTasks.size() + " itens");

		humanTasks.addAll(archivedHumanTasks);
		Collections.sort(humanTasks, order);		

		return new TaskTimeline(cs, humanTasks);
	}

	/**
	 * Constrói um objeto do tipo TaskTimeline contendo as informações de um Case arquivado (terminado)
	 * 
	 * @param user Objeto contedo os dados da sessão do usuário
	 * @param caseId id do case arquivado
	 * @param order ordenação das tarefas da timeline
	 * @return Um objeto do tipo TaskTimeline contendo as atividades arquivadas
	 * @throws HttpStatus401Exception
	 * @throws HttpStatus404Exception
	 * @throws ClientProtocolException
	 * @throws GenericHttpStatusException
	 * @throws IOException
	 */
	public TaskTimeline retriveArchivedTasksTimeline(User user, Long caseId, Comparator<HumanTask> order) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		Case cs = retriveArchivedCase(caseId, user);	

		String uriArchivedTasks = new SearchParamsBuilder(BONITA_URI + URL_ARCHIVED_HUMAN_TASKS)
		.append(ATRIBUTE_ROOT_CONTAINER_ID)
		.append(ATRIBUTE_ACTOR_ID)
		.append(ATRIBUTE_ASSIGNED_ID)
		.append(ATRIBUTE_EXECUTE_BY)
		.append(FILTER_CASE_ID, caseId)		
		.create();		

		System.out.println("Executando [" + uriArchivedTasks + "]");
		List<HumanTask> archivedHumanTasks = executeGetRequestForTaskList(user, uriArchivedTasks);
		System.out.println("Retornando [" + uriArchivedTasks + "] " + archivedHumanTasks.size() + " itens");

		Collections.sort(archivedHumanTasks, order);		

		return new TaskTimeline(cs, archivedHumanTasks);
	}

	public Form retriveTaskForm(User user, String processDefinitionId, String taskName) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatus404Exception, GenericHttpStatusException{
		taskName = URLEncoder.encode(taskName, "utf-8");
		String uriTaskForm = new FormSearchUriBuilder(BONITA_URI)
		.pageAndNumberOfElements(0, 1)
		.processDefinitionId(processDefinitionId)
		.task(taskName)
		.type(Form.TYPE.TASK)
		.create();

		String response = executeGetRequest(user, uriTaskForm);
		Type listFormType = new TypeToken<List<Form>>(){}.getType();
		List<Form> forms = gson.fromJson(response, listFormType);
		return forms.size() > 0 ? forms.get(0) : Form.createNullObject();
	}

	public Form retriveInstantiationForm(User user, String processDefinitionId) throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		String uriInstantiationForm = new FormSearchUriBuilder(BONITA_URI)
		.pageAndNumberOfElements(0, 1)
		.processDefinitionId(processDefinitionId)		
		.type(Form.TYPE.PROCESS_START)
		.create();

		String response = executeGetRequest(user, uriInstantiationForm);
		Type listFormType = new TypeToken<List<Form>>(){}.getType();
		List<Form> forms = gson.fromJson(response, listFormType);
		return forms.size() > 0 ? forms.get(0) : Form.createNullObject();
	}

	@SuppressWarnings("unused")
	private HttpResponse executeGetRequest(String apiURI, HttpClient httpClient, HttpContext httpContext) {
		try {
			HttpGet getRequest = new HttpGet(BONITA_URI + apiURI);
			HttpResponse response = httpClient.execute(getRequest, httpContext);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}	

	@SuppressWarnings("unused")
	private HttpResponse executeGetRequest(String apiURI) {
		try {
			HttpGet getRequest = new HttpGet(BONITA_URI + apiURI);
			HttpResponse response = httpClient.execute(getRequest, httpContext);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String executeGetRequestVariableForTask(User user, String processUri) throws IOException, ClientProtocolException, HttpStatus401Exception, HttpStatusException, InstantiationException, IllegalAccessException {
		String responseBody = executeGetRequest(user, processUri);
		return responseBody;
	}


	private List<TaskProcess> executeGetRequestForProcessList(User user, String processUri) throws IOException, ClientProtocolException, HttpStatus401Exception, HttpStatusException {
		String responseBody = executeGetRequest(user, processUri);		
		Type listProcessType = new TypeToken<List<TaskProcess>>() {}.getType();
		List<TaskProcess> processos = gson.fromJson(responseBody, listProcessType);
		return processos;
	}

	/**
	 * Recupera uma Human Task do Bonita BPM
	 * 
	 * @param user Objeto que representa o usuário 
	 * @param uriTask Uri para recuperação do Human Task
	 * @return
	 * @throws HttpStatus401Exception
	 * @throws HttpStatus404Exception
	 * @throws ClientProtocolException
	 * @throws GenericHttpStatusException
	 * @throws IOException
	 */
	private HumanTask executeGetRequestForTask(User user, String uriTask) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		String responseBody = executeGetRequest(user, uriTask);
		HumanTask humanTask = gson.fromJson(responseBody, HumanTask.class);
		return humanTask;
	}

	private List<HumanTask> executeGetRequestForTaskList(User user, String uriTasks) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		String responseBody = executeGetRequest(user, uriTasks);
		Type listTaskType = new TypeToken<List<HumanTask>>() {}.getType();
		List<HumanTask> tasks = gson.fromJson(responseBody, listTaskType);
		return tasks;
	}

	private List<Group> executeGetRequestForGruopkList(User user, String uriGroups) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		String responseBody = executeGetRequest(user, uriGroups);
		Type listTaskType = new TypeToken<List<Group>>() {}.getType();
		List<Group> groups = gson.fromJson(responseBody, listTaskType);
		return groups;
	}

	private User executeGetRequestForUser(User user, String uriUser) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		String responseBody = executeGetRequest(user, uriUser);
		Type userType = new TypeToken<User>() {}.getType();
		User returnUser = gson.fromJson(responseBody, userType);
		return returnUser;
	}

	private List<Group> executeGetRequestForGroupList(User user, String uriGroups) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		String responseBody = executeGetRequest(user, uriGroups);
		Type listTaskType = new TypeToken<List<Group>>() {}.getType();
		List<Group> groups = gson.fromJson(responseBody, listTaskType);
		return groups;
	}


	private List<UserGroup> executeGetRequestForUserGroupList(User user, String uriGroups)	throws IOException, ClientProtocolException, HttpStatus401Exception, HttpStatusException {
		String responseBody = executeGetRequest(user, uriGroups);
		Type listTaskType = new TypeToken<List<UserGroup>>() {}.getType();
		List<UserGroup> userGroup = gson.fromJson(responseBody, listTaskType);
		return userGroup;
	}


	private Group executeGetRequestGroupName(User user, String uriGroups)	throws IOException, ClientProtocolException, HttpStatus401Exception, HttpStatusException {
		String responseBody = executeGetRequest(user, uriGroups);
		Type listTaskType = new TypeToken<List<Group>>() {}.getType();
		List<Group> userGroup = gson.fromJson(responseBody, listTaskType);
		return userGroup.get(0);
	}


	private String executeGetRequest(User user, String resourceUri) throws IOException, ClientProtocolException, HttpStatus401Exception, HttpStatus404Exception, GenericHttpStatusException {
		HttpGet getRequest = new HttpGet(resourceUri);
		HttpResponse response = user.getHttpClient().execute(getRequest, user.getHttpContext());		
		int statusCode = response.getStatusLine().getStatusCode();

		if(is_200_OK(statusCode)){
			return EntityUtils.toString(response.getEntity());
		}
		else{
			if (is_401_UNAUTHORIZED(statusCode)) {				
				throw new HttpStatus401Exception(resourceUri);
			} 				
			if(is_404_NOT_FOUND(statusCode)){
				throw new HttpStatus404Exception(resourceUri);
			}			
			throw new GenericHttpStatusException(statusCode, resourceUri);			
		}
	}


	private long getPageIndex(long record, long quantRecordsPerPage){
		if(record <= 0) return 0;
		return record/quantRecordsPerPage;
	}


	private HttpResponse executePostRequest(String apiURI, User user, String payloadAsString) {
		try {
			HttpPost postRequest = new HttpPost(this.BONITA_URI + apiURI);
			StringEntity input = new StringEntity(payloadAsString);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest, httpContext);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	private ProcessAPI getProcessAPI(APISession session) throws BonitaException {
		return TenantAPIAccessor.getProcessAPI(session);
	}


	private LoginAPI getLoginAPI() throws BonitaException {
		Map<String, String> settings = new HashMap<String, String>();
		//		System.out.println("Acesso api Enginer   "+this._URI);
		settings.put("server.url", this._URI);
		settings.put("application.name", "bonita");
		APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, settings);
		return TenantAPIAccessor.getLoginAPI();
	}


	@SuppressWarnings("unused")
	private static <T> List<T> getObjectToObjects(String json, Class<T> clazz) {
		/*****
		Type listOfObject = new TypeToken<List<T>>(){}.getType();
		TypeToken<List<T>> token = new TypeToken<List<T>>(){};
		objects = gson.fromJson(objectJson, token.getType());  Dessa forma não coverte nem fodendo  *****/
		Gson gson= new Gson();
		//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(json).getAsJsonArray();
		List<T> list = new ArrayList<T>();
		for (final JsonElement jsonElement : array) {
			T entity = gson.fromJson(jsonElement, clazz);
			list.add(entity);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public Class<Object> getInstanceofClass(Class<?> type) {
		Class<Object> clazz = null;
		try {
			clazz = (Class<Object>) Class.forName(type.getCanonicalName());
		}catch (Exception e ) {  
		}
		return clazz;
	}
	
	
	@SuppressWarnings("unused")
	public Object teste3(Class<?> typeClass) throws BonitaException  {
		Object object = new Object();
		APISession session = doTenantLogin("sergio.victoria","bpm");
		ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);
//		APIAccessor apiAccessor = TenantAPIAccessor.get
				
		List<DataInstance> dataInstances = processAPI.getProcessDataInstances(2L, 0, 100);

		
		  
		  for(DataInstance variavel : dataInstances){
			  
		   String nome = variavel.getName();
		   Object valor = variavel.getValue();
		   String classe = variavel.getClassName();
		   
		   if (typeClass.getCanonicalName().equals(variavel.getClassName())) {
			   object = variavel.getValue();
		   }
		   System.out.println(" "+classe);
		   
		  }

		return object;

	}

	public void sendMessage( ) {

		try {

			APISession session = doTenantLogin("sergio.victoria","bpm");
			ProcessRuntimeAPI processAPI = getProcessAPI(session);
			Expression targetProcess = new ExpressionBuilder().createConstantStringExpression("Processo de Suprimentos");
			Expression targetFlowNode = new ExpressionBuilder().createConstantStringExpression("Aguardar devolução do contrato");

			Expression messageContentKey = new ExpressionBuilder().createConstantStringExpression("numeroContrato");
			Expression messageContentValue = new ExpressionBuilder().createConstantStringExpression("3");

			Expression messageCorrelationItem = new ExpressionBuilder().createConstantStringExpression("numero");
			Expression messageCorrelationValue = new ExpressionBuilder().createConstantStringExpression("3");		

			Expression correlationKey = new ExpressionBuilder().createConstantStringExpression("numero");
			Expression correlationValue = new ExpressionBuilder().createConstantLongExpression(new Long(3));
			
			
			Map<Expression, Expression> mapContextMessage = new HashMap<Expression, Expression>();
			Map<Expression, Expression> mapCorrelationBetweenIntances = new HashMap<Expression, Expression>();
			Map<Expression, Expression> mapCorrelation = new HashMap<Expression, Expression>();
			
			
			mapContextMessage.put(messageContentKey, messageContentValue);
			mapCorrelationBetweenIntances.put(messageCorrelationItem, messageCorrelationValue);
			mapCorrelation.put(correlationKey, correlationValue);
			
			
			processAPI.sendMessage("3", targetProcess, targetFlowNode, mapContextMessage);
			doTenantLogout(session);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public List<org.bonitasoft.engine.identity.Group> findGroups(int startIndex, int maxResults, GroupCriterion groupcriterion) throws BonitaException {
		List<org.bonitasoft.engine.identity.Group> grupos = new ArrayList<org.bonitasoft.engine.identity.Group>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		grupos = identityAPI.getGroups(startIndex, maxResults , groupcriterion);
		doTenantLogout(session);
		return grupos;

	}


	public org.bonitasoft.engine.identity.Group findOneGroup(String groupName) throws BonitaException {
		org.bonitasoft.engine.identity.Group group = null; 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, groupName).done(); 
		SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
		group = searchResultGroup.getResult().get(0);
		doTenantLogout(session);
		return group;

	}
	
	
	public org.bonitasoft.engine.identity.Group findOneGroupS(String groupName) throws BonitaException {
		org.bonitasoft.engine.identity.Group group = null; 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, groupName).done(); 
		SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
		group = searchResultGroup.getResult().get(0);
		doTenantLogout(session);
		return group;

	}
	
	
	public List<org.bonitasoft.engine.identity.Group> find(String groupName) throws BonitaException {
 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, groupName).done(); 
		SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
		
		
		List<org.bonitasoft.engine.identity.Group> groups = searchResultGroup.getResult();
		
		for (org.bonitasoft.engine.identity.Group dto : groups) {
			System.out.println("  dto "+dto.getDescription());
			System.out.println("  dto "+dto.getId());
			dto.getId();
			List<UserMembership> memberships = identityAPI.getUserMembershipsByGroup(dto.getId(),0,100);
			for (UserMembership userMembership : memberships) {
				System.out.println(userMembership); 
			}
		}
		
		
		doTenantLogout(session);
		return groups;
	

	}

	public void teste() throws BonitaException {
		APISession session = doTenantLogin("sergio.victoria","bpm");
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		org.bonitasoft.engine.identity.User dto = identityAPI.getUserByUserName("sergio.victoria");
		Long idManager = dto.getManagerUserId();
		System.out.println(" dto "+dto.getFirstName()+"idManager   "+idManager);
	}

	public static void main(String[] args) throws BonitaException, HttpStatus401Exception, ParseException, IOException, HttpStatusException {
		APITeste restAPI = new APITeste();
		User user = new User();
		user.setUserName("sergio.victoria");
		user.setPassword("bpm");
		
		restAPI.sendMessage();
//		lo
//		restAPI.retriveSubGroupS(user, 0, 100, "Check - in");
////		List<org.bonitasoft.engine.identity.Group> groups = restAPI.find("Check - in");
////		for (org.bonitasoft.engine.identity.Group dto : groups) {
////			System.out.println("  dto "+dto.getName());
////		}
	}

}
