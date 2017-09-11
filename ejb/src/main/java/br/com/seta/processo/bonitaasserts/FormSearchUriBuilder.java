package br.com.seta.processo.bonitaasserts;

import br.com.seta.processo.dto.Form;

/**
 * Builder para a URI da API de Forms do Bonita BPM 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class FormSearchUriBuilder {
	
	private static final String FORM_API = "API/form/mapping";
	private static final String AND = "&";
	private static final String SEPARATOR = "/";
	private static final String INTERROGATION = "?";
	private static final String MAX_NUMBER_ELEMENTS = "c=";
	private static final String PAGE = "p=";	
	private static final String FILTER = "f=";
	private static final String PROCESS_DEFINITION_ID = "processDefinitionId%3d";
	private static final String TASK = "task%3d";
	private static final String TYPE = "type%3d";
	private StringBuilder uri;
	
	public FormSearchUriBuilder(String contextUrl){
		this.uri = new StringBuilder(contextUrl).append(SEPARATOR).append(FORM_API).append(INTERROGATION);		
	}
	
	public FormSearchUriBuilder pageAndNumberOfElements(int page, int numberOfElements){
		this.uri.append(PAGE + page).append(AND).append(MAX_NUMBER_ELEMENTS + numberOfElements).append(AND);
		return this;
	}
	
	public FormSearchUriBuilder processDefinitionId(String processDefinitionId){
		this.uri.append(FILTER).append(PROCESS_DEFINITION_ID).append(processDefinitionId).append(AND);
		return this;
	}
	
	public FormSearchUriBuilder task(String taskName){
		this.uri.append(FILTER).append(TASK).append(taskName).append(AND);
		return this;
	}
	
	public FormSearchUriBuilder type(Form.TYPE type){
		this.uri.append(FILTER).append(TYPE).append(type).append(AND);
		return this;
	}
	
	public String create(){
		clearLastCaracterIfEquals(AND);
		return this.uri.toString();
	}
	
	private void clearLastCaracterIfEquals(String caracter){
		int lastIndex = uri.length() - 1;
		if(uri.lastIndexOf(caracter) == lastIndex){
			this.uri.replace(lastIndex, lastIndex + 1, "");						
		}
	}
	
	public static void main(String[] args){
		FormSearchUriBuilder uriBuilder = new FormSearchUriBuilder("http://localhost:8080/bonita");
		String uri = uriBuilder.pageAndNumberOfElements(10, 0).processDefinitionId("4926411753575040412").task("Preencher Formulário Intensão de Compra").create();
		System.out.println(uri);
		
		uriBuilder = new FormSearchUriBuilder("http://localhost:8080/bonita");
		uri = uriBuilder.pageAndNumberOfElements(10, 0).processDefinitionId("4926411753575040412").type(Form.TYPE.PROCESS_START).create();
		System.out.println(uri);
	}
}
