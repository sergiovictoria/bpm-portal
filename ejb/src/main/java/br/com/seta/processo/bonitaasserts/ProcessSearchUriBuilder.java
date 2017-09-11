package br.com.seta.processo.bonitaasserts;

import br.com.seta.processo.utils.PropertiesEJBUtils;

/**
 * 
 * Classe responsável pela construção da URI de consulta de processos do BontitaSoft
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ProcessSearchUriBuilder {
		
	private StringBuilder sb;
	private static final String SEPARADOR = "&";
	private static final String EQUALS = "%3d";
	private static final String SPACE = "%20";
	private static final String MAX_NUMBER_ELEMENTS = "c=";
	private static final String PAGE = "p=";
	private static final String ORDER_BY = "o=";
	private static final String FILTER = "f=";	
	private static final String PROCESS_API = "/API/bpm/process?";
	
	private static final String PROTOCOL = "http://";
	private static final String CONTEXT = "bonita";
	
	private static final String HOST_NAME_PROPERTIES_KEY = "hostNameBPM";
	private static final String PORT_NUMBER_PROPERTIES_KEY = "portNameBPM";	
	
	public static enum ORDER{
		ASC("ASC"), DESC("DESC");
		
		public String value;
		
		ORDER(String str){
			value = str;
		}
	}
	
	public static enum ORDER_ELEMENT {
		DISPLAY_NAME("displayName");

		public String value;

		ORDER_ELEMENT(String str) {
			value = str;
		}
	}
	
	public static enum FILTER_ELEMENT{
		USER_ID("user_id");
		
		public String value;
		
		FILTER_ELEMENT(String str){
			value = str;
		}
	}
	
	public ProcessSearchUriBuilder(){
		String host = PropertiesEJBUtils.getInstance().getValues(HOST_NAME_PROPERTIES_KEY);
		String port = PropertiesEJBUtils.getInstance().getValues(PORT_NUMBER_PROPERTIES_KEY);
		String bonitaUri = PROTOCOL + host + ":" + port + "/" + CONTEXT;
		
		this.sb = new StringBuilder(bonitaUri).append(PROCESS_API);
	}
	
	public ProcessSearchUriBuilder(String urlBase){
		this.sb = new StringBuilder(urlBase).append(PROCESS_API);
	}
	
	public ProcessSearchUriBuilder numberOfElements(long n){
		this.sb.append(MAX_NUMBER_ELEMENTS + n).append(SEPARADOR);
		return this;
	}
	
	
	public ProcessSearchUriBuilder numberOfElements(int n){
		return numberOfElements(n);
	}
	
	public ProcessSearchUriBuilder page(long page){
		this.sb.append(PAGE + page).append(SEPARADOR);
		return this;
	}
	
	public ProcessSearchUriBuilder page(int page){
		return page(page);		
	}
	
	public ProcessSearchUriBuilder orderBy(ORDER_ELEMENT element, ORDER order){
		this.sb.append(ORDER_BY).append(element.value).append(SPACE).append(order.value).append(SEPARADOR);
		return this;		
	}
	
	public ProcessSearchUriBuilder filter(FILTER_ELEMENT filter, String filterValue){
		this.sb.append(FILTER).append(filter.value).append(EQUALS).append(filterValue).append(SEPARADOR);
		return this;
	}
	
	public ProcessSearchUriBuilder filter(FILTER_ELEMENT filter, Object filterValue){
		filter(filter, String.valueOf(filterValue));
		return this;
	}	
	
	public String create(){
		//se o último caracter é igual a '&', este é removido
		if(sb.lastIndexOf(SEPARADOR) == sb.length() - 1){
			return this.sb.substring(0, sb.length() - 1);
		}
		
		return this.sb.toString();
	}
	
	/*
	public static void main(String[] args){
		ProcessSearchParamsBuilder builder = new ProcessSearchParamsBuilder("http://localhost:8080/bonita");
		String url = builder.page(0).numberOfElements(10).orderBy(ORDER_ELEMENT.DISPLAY_NAME, ORDER.ASC).filter(FILTER_ELEMENT.USER_ID, 204).create();
		System.out.println(url);
	}*/
}
