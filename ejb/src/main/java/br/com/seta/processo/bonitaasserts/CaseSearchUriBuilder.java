package br.com.seta.processo.bonitaasserts;

public class CaseSearchUriBuilder{

	private StringBuilder uri;	
	
	private static final String CASE_API = "API/bpm/case";
	private static final String AND = "&";
	private static final String SEPARATOR = "/";
	private static final String INTERROGATION = "?";
	private static final String STARTED_BY = "d=started_by";
	private static final String PROCESS_DEFINITION_ID = "d=processDefinitionId";
	
	public CaseSearchUriBuilder(String contextUrl){
		this.uri = new StringBuilder(contextUrl);
	}
	
	public CaseSearchUriBuilder(String contextUrl,long caseId){
		this(contextUrl);
		this.uri = uri.append(SEPARATOR).append(CASE_API).append(SEPARATOR).append(caseId);
	}
	
	public CaseSearchUriBuilder getCase(long caseId){		
		uri.append(SEPARATOR).append(caseId);
		return this;
	}
	
	public String create(){
		this.uri.append(INTERROGATION).append(STARTED_BY).append(AND).append(PROCESS_DEFINITION_ID);
		
		//se o último caracter é igual a '&', este é removido
		/*
		if(uri.lastIndexOf(AND) == uri.length() - 1){
			return this.uri.substring(0, uri.length() - 1);
		}*/
		return this.uri.toString();
	}
	/*
	public static void main(String[] args){
		String uri = new CaseSearchUriBuilder("http://localhost:8080/bonita", 10L).create();
		System.out.println(uri);
	}*/
}
