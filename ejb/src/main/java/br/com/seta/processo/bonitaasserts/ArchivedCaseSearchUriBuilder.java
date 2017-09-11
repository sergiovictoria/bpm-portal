package br.com.seta.processo.bonitaasserts;

import br.com.seta.processo.utils.PropertiesEJBUtils;

/**
 * 
 * Classe responsável pela construção da URI de consulta de cases arquivados (archived case)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ArchivedCaseSearchUriBuilder {
	private StringBuilder sb;
	private static final String SEPARATOR = "/";
	private static final String INTERROGATION = "?";
	private static final String AND = "&";
	private static final String PROCESS_API = "/API/bpm/archivedCase";
	private static final String PROCESS_DEFINITION = "d=processDefinitionId";
	private static final String STARTED_BY = "d=started_by";
	private static final String FILTER = "f=";
	
	private static final String PAGE = "p=";
	private static final String NUMBER_OF_ITENS = "c=";
	private static final String SOURCE_OBJECT_ID = "sourceObjectId%3d";
	
	private static final String PROTOCOL = "http://";
	private static final String CONTEXT = "bonita";
	
	private static final String HOST_NAME_PROPERTIES_KEY = "hostNameBPM";
	private static final String PORT_NUMBER_PROPERTIES_KEY = "portNameBPM";		
	
	public static enum ORDER_ELEMENT {
		SOURCE_OBJECT_ID("sourceObjectId");

		public String value;

		ORDER_ELEMENT(String str) {
			value = str;
		}
	}
	
	public ArchivedCaseSearchUriBuilder(){
		String host = PropertiesEJBUtils.getInstance().getValues(HOST_NAME_PROPERTIES_KEY);
		String port = PropertiesEJBUtils.getInstance().getValues(PORT_NUMBER_PROPERTIES_KEY);
		String bonitaUri = PROTOCOL + host + ":" + port + "/" + CONTEXT;
		
		this.sb = new StringBuilder(bonitaUri).append(PROCESS_API);
	}
	
	public ArchivedCaseSearchUriBuilder(String urlBase){
		this.sb = new StringBuilder(urlBase)
			.append(PROCESS_API)
			.append(INTERROGATION)
			.append(PROCESS_DEFINITION)
			.append(AND)
			.append(STARTED_BY)
			.append(AND);
	}
	
	public ArchivedCaseSearchUriBuilder(String urlBase,long caseId){
		this.sb = new StringBuilder(urlBase)
			.append(PROCESS_API)
			.append(SEPARATOR)
			.append(caseId)
			.append(INTERROGATION)			
			.append(PROCESS_DEFINITION)
			.append(AND)
			.append(STARTED_BY)
			.append(AND);
	}
	
	public ArchivedCaseSearchUriBuilder numberOfItens(int numberOfItens){
		sb.append(NUMBER_OF_ITENS).append(numberOfItens).append(AND);
		return this;
	}
	
	public ArchivedCaseSearchUriBuilder sourceObjectId(long id){
		sb.append(FILTER).append(SOURCE_OBJECT_ID).append(id).append(AND);
		return this;
	}
	
	public ArchivedCaseSearchUriBuilder page(int page){
		sb.append(PAGE).append(page).append(AND);
		return this;
	}
	
	public String create(){
		//se o último caracter é igual a '&', este é removido
		if(sb.lastIndexOf(AND) == sb.length() - 1){
			return this.sb.substring(0, sb.length() - 1);
		}	
		return this.sb.toString();
	} 		
	
	/*
	public static void main(String[] args){
		ArchivedCaseSearchUriBuilder builder = new ArchivedCaseSearchUriBuilder("http://localhost:8080/bonita", 1);
		String url = builder.create();
		System.out.println(url);
		
		String url2 = new ArchivedCaseSearchUriBuilder("http://localhost:8080/bonita").page(0).numberOfItens(10).sourceObjectId(1001).create();
		System.out.println(url2);
	}*/
}
