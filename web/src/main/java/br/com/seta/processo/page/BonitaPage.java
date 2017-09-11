package br.com.seta.processo.page;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.service.ExecuteRestAPI;

/**
 * Classe que representa uma página do Bonita BPM (páginas de atividade (task) ou de instanciação de processo ).<br>
 * Esta classe representa a junção das classes TaskPage e InstanciacaoProcessoPage e será utilizada para os casos onde precisamos dos dois comportamento para uma mesma página
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public abstract class BonitaPage extends Templete {
	private static final long serialVersionUID = 1L;

	private static final String TASK_ID = "id";
	private final static String PROCESSO_NOME = "processo";
	private final static String PROCESSO_VERSAO = "versao";
	private final Long ID_INVALIDO = -1L;
	
	private String nomeProcesso;
	private String versaoProcesso;	
	
	private long taskId = -1;	
	private long caseId = -1;
	private HumanTask humanTask;
	
	@Inject
	private ExecuteRestAPI restApi;		
	
	public BonitaPage(){
		
	}
	
	public BonitaPage(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		this.taskId = pageParameters.get(TASK_ID).toLong(ID_INVALIDO);
		this.nomeProcesso = pageParameters.get(PROCESSO_NOME).toString();	
		this.versaoProcesso = pageParameters.get(PROCESSO_VERSAO).toString();
		
		if(isTaskPage()){
			retriveHumanTask();
			this.caseId = humanTask.getCaseId();
			setTituloPagina(humanTask.getDisplayName());
			setDadosAdicionais("Case Id: " + this.caseId);
		}
	}
	
	/**
	 * Recupera o id da tarefa. Válido apenas para os casos onde a página representa uma atividade (task)
	 * 
	 * @return id da tarefa ou -1 para os casos onde a página não representa uma atividade
	 */
	public long getTaskId() {
		return taskId;
	}
	
	/**
	 * Recupera o id do case. Válido apenas para os casos onde a página representa uma atividade (task)
	 * 
	 * @return id do case ou -1 para os casos onde a página não representa uma atividade
	 */
	public long getCaseId() {
		return caseId;
	}
	
	/**
	 * Recupera os dados da tarefa humana
	 * 
	 * @return Um objeto representando a tarefa humana ou null para os casos onde a página não representa uma atividade
	 */
	public HumanTask getHumanTask(){
		return humanTask;
	}	
	
	/**
	 * Instância um novo case (instância do processo) enviando dados para sua inicialização
	 * 
	 * @param campo Nome da váriável de processo (process variable) contida em um processo do Bonita BPM
	 * @param variavel Objeto contendo os valores para serem enviado para o Bonita BPM
	 * @throws BonitaException
	 */
	public void executeCaseWithVariable(String campo, Object variavel) throws BonitaException {
		User usuario = getUser();
		restApi.execueteCaseWithVariable(usuario.getUserName(), usuario.getPassword(), variavel, campo, nomeProcesso, versaoProcesso);
	}
	
	/**
	 * Instância um novo case (instância do processo) enviando dados para sua inicialização
	 * 
	 * @param listVariablesSerializable Map contendo um conjunto de chave/valor representando os nomes e valores a serem enviados para o Bontia BPM
	 * @throws BonitaException
	 */
	public void executeCaseWithVariable(Map<String, Object> listVariablesSerializable) throws BonitaException {
		User usuario = getUser();
		restApi.execueteCaseWithVariable(usuario.getUserName(), usuario.getPassword(), listVariablesSerializable, nomeProcesso, versaoProcesso);
	}
	
	/**
	 * Faz a instância prosseguir para a próxima atividade e atualiza as variáveis de processo
	 * 
	 * @param processVariables
	 * @throws BonitaException
	 */
	public void  executeFlowAndUpdateVariables(Map<String, Serializable> processVariables) throws BonitaException{
		restApi.executeFlowAndUpdateVariable(getUser(), getTaskId(), processVariables);
	}

	/**
	 * Recupera uma variável do processo para os casos onde a página representa uma página de atividade (task page)
	 * 
	 * @param field nome do campo a ser recuperado do processo do Bonita BPM	
	 * @param clazzType classe do campo a ser recuperado do processo do Bonita BPM
	 * @param defaultValue Valor retornado para os casos onde o valor é nulo ou não se trata de uma página de atividade
	 * @return
	 * @throws HttpStatus401Exception
	 * @throws ParseException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws HttpStatusException
	 */
	public Object retriveProcessVariable(String field, Class<?> clazzType, Object defaultValue) throws HttpStatus401Exception, ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException{
		if(isTaskPage()){
			Object value = restApi.retriveVariableTask(getUser(), getHumanTask(), clazzType, field);
			return value == null ? defaultValue : value;
		}
		
		return defaultValue;		
	}
	
	/**
	 * Recupera uma variável do processo para os casos onde a página representa uma página de atividade (task page)
	 * 
	 * @param field field nome do campo a ser recuperado do processo do Bonita BPM
	 * @param taskId Id da atividade para a qual será recuperada os valores das variáveis
	 * @param clazzType clazzType classe do campo a ser recuperado do processo do Bonita BPM
	 * @param defaultValue defaultValue Valor retornado para os casos onde o valor é nulo ou não se trata de uma página de atividade
	 * @return
	 * @throws HttpStatus401Exception
	 * @throws ParseException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws HttpStatusException
	 */
	public Object retriveProcessVariable(String field, long taskId, Class<?> clazzType, Object defaultValue) throws HttpStatus401Exception, ParseException, InstantiationException, IllegalAccessException, IOException, HttpStatusException{
		if(isTaskPage()){
			Object value = restApi.retriveVariableTask(getUser(), taskId, clazzType, field);
			return value == null ? defaultValue : value;
		}
		
		return defaultValue;		
	}
	
	/**
	 * Redireciona para a página de Atividades do Portal
	 */
	public void redirecionaParaPaginaDeAtividades(){
		String pageName = Atividades.class.getSimpleName();
		String contexto = getRequest().getContextPath();
		String url = contexto + "/" + pageName;
		setResponsePage(new RedirectPage(url));
	}
	
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	
	public String getVersaoProcesso() {
		return versaoProcesso;
	}
	
	private void retriveHumanTask() throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		User usuario = getUser();
		this.humanTask = restApi.retriveHumanTask(usuario, taskId);
	}

	public User getUser() {
		return (User) Session.get().getAttribute("user");
	}
	
	private boolean isTaskPage(){
		if(this.taskId == this.ID_INVALIDO)
			return false;
		
		return true;
	}
}
