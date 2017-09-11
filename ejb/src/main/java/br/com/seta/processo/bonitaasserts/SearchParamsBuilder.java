package br.com.seta.processo.bonitaasserts;

import java.io.Serializable;

/**
 * 
 * Classe que auxilia na montagem dos Parâmetros de Busca (Search Params) das urls para consumo das APIs do Bonita. <br/><br/>
 * Ex:  <br/>
 * 
 * BonitaSearchParams app = new BonitaSearchParams("/API/bpm/humanTask"); <br/>
 * app.append(PAGE, 0).append(MAX_NUMBER_ELEMENTS, "4").append(FILTER_USER_ID, 4).append(ORDER_BY_PRIORITY_DESC).append(ATRIBUTE_ACTOR_ID).create();
 * <br/>
 * <b>Resultado: /API/bpm/humanTask?p=0&c=4&f=user_id%3d4&o=priority%20DESC&d=actorId</b>
 * 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class SearchParamsBuilder implements Serializable {	
	

	private static final long serialVersionUID = 1L;
	private static final String SEPARADOR = "&";
	private static final String VALUE = "{value}";
	
	public static final String MAX_NUMBER_ELEMENTS = "c={value}";
	public static final String PAGE = "p={value}";
	public static final String SEARCH = "s={value}";
	
	public static final String ORDER_BY_PRIORITY_DESC = "o=priority%20DESC";
	public static final String ORDER_BY_PRIORITY_ASC = "o=priority%20ASC";
	public static final String ORDER_BY_ARCHIVED_DATA_DESC = "o=archivedDate%20DESC";
	
	public static final String FILTER_USER_ID = "f=user_id%3d{value}";
	public static final String FILTER_GROUP_ID = "f=group_id%3d{value}";
	public static final String FILTER_GROUP_NAME = "f=name={value}";
	public static final String FILTER_STATE = "f=state%3d{value}";
	public static final String FILTER_CASE_ID = "f=caseId%3d{value}";
	public static final String FILTER_PROCESS_ID = "f=processId%3d{value}";
	public static final String FILTER_ASSIGNED_ID = "f=assigned_id%3d{value}";
	public static final String FILTER_ACTOR_ID = "f=actorId%3d{value}";
	
	public static final String STATE_READY = "ready";
	public static final String STATE_SKIPPED = "skipped";

	public static final String ATRIBUTE_ACTOR_ID= "d=actorId";
	public static final String ATRIBUTE_ROOT_CONTAINER_ID = "d=rootContainerId";
	public static final String ATRIBUTE_EXECUTE_BY = "d=executedBy";
	public static final String ATRIBUTE_ASSIGNED_ID = "d=assigned_id";
	public static final String ATRIBUTE_PROFESSIONAL = "d=professional_data";
	public static final String ATRIBUTE_PROCESS_ID = "d=processId";
	public static final String PROCESS_ID_GROUP = "p=0&c={value}&f=parent_path=/Seta/";
	
	private StringBuilder sb;
	private String baseApiUrl = "";
	
	public SearchParamsBuilder(){
		this.sb = new StringBuilder();
	}
	
	public SearchParamsBuilder(String baseApiUrl){
		this();
		this.baseApiUrl = baseApiUrl + "?";		
	}	
	
	public SearchParamsBuilder(String baseApiUrl, long id){
		this();
		this.baseApiUrl = baseApiUrl + "/"+id+"?";		
	}	
	
	public SearchParamsBuilder append(String value){
		this.sb.append(value).append(SEPARADOR); //value&
		return this;
	}	
	
	public SearchParamsBuilder append(String placeholder, String value){
		String newValue = placeholder.replace(VALUE, value);		
		return append(newValue);
	}
	
	public SearchParamsBuilder append(String placeholder, Object value){
		String newValue = String.valueOf(value);
		return append(placeholder, newValue);
	}
	
	public String create(){
		this.sb.insert(0, this.baseApiUrl);		
		
		//se o último caracter é igual a '&', este é removido
		if(sb.lastIndexOf(SEPARADOR) == sb.length() - 1){
			return this.sb.substring(0, sb.length() - 1);
		}
		
		return this.sb.toString();
	}
	
}
