package br.com.seta.processo.servicos;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.mapping.ByteArrayToBase64TypeAdapter;
import br.com.seta.processo.mapping.DateDeserializer;
import br.com.seta.processo.mapping.UserDeserializer;
import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_200_OK;
import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_401_UNAUTHORIZED;
import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_404_NOT_FOUND;


public final class RemoteService implements Serializable {


	private static Logger logger = Logger.getLogger(RemoteService.class);
	private static final long serialVersionUID = 1L;
	private static RemoteService remoteService;
	private String portName;
	private String hostName;
	private Gson gson;

	public static final String FIND_EMAIL     = "/service/rest/Emails/get/List/";
	public static final String FIND_CONTRATOS = "/service/rest/Contratos/get/List/";
	public static final String UPDATEWITHATTIBUTES = "/service/rest/ExecuteMongo/post/update/";



	/*****
	 * 
	 * @return Singleton Object  
	 */

	public static RemoteService getInstance(){
		if (remoteService == null){
			logger.info("  **** Criando Serviço Singleton RemoteService *** ");
			remoteService = new RemoteService();
		}
		return remoteService;
	}


	public RemoteService() {
//		this.hostName = PropertiesDtoUtils.getInstance().getValues("hostNameBPM");
//		this.portName = PropertiesDtoUtils.getInstance().getValues("portNameBPM");
		
		this.hostName = "localhost";
		this.portName = "8080";
		
		this.gson = new GsonBuilder()
		.registerTypeAdapter(Date.class, new DateDeserializer())		
		.registerTypeAdapter(User.class, new UserDeserializer())
		.registerTypeAdapter(byte[].class, new ByteArrayToBase64TypeAdapter())
		.create();
	}

	public List<String> getEmailS(String groupName) throws Exception {
		
		String uriGroups=  buildURL(FIND_EMAIL)+URLEncoder.encode(groupName,"utf-8");
		System.out.println("Executando getEmailS [  "+uriGroups + " ] ");	
		String data = executeGetRequest(buildURL(FIND_EMAIL)+URLEncoder.encode(groupName,"utf-8"));
		List<String> emails = gson.fromJson(data, List.class);
		return emails.size() > 0 ? emails : null;
		
	}
	
	

	public String getEmailSList(String groupName) throws Exception {
		String listEmails = null;
		List<String>  emails = getEmailS(groupName);
		for (String dto : emails) {
			listEmails += dto + ", ";
		}
		return listEmails;
	}
	
	/*****
	 * @author Sérgio da victória
	 * @param key Atributo pra procura 
	 * @param value Valor do Atributo
	 * @param keyOps Atributo para Update
	 * @param valueOps Valor do Atributo para Update
	 * @param classType CLasse Objecto DTO para fazer update
	 * @throws IOException 
	 * @throws GenericHttpStatusException 
	 * @throws ClientProtocolException 
	 * @throws HttpStatus404Exception 
	 * @throws HttpStatus401Exception 
	 */
	public void updateWithTwoAttributes(String key, Object value, String keyOps, Object valueOps, Class<?> classType) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException {
		logger.info("Fazendo chamada a rest "+buildURL(UPDATEWITHATTIBUTES+key+"/"+value+"/"+keyOps+"/"+valueOps+"/"+classType.getCanonicalName().toString()));
		executeGetRequest(buildURL(UPDATEWITHATTIBUTES+key+"/"+value+"/"+keyOps+"/"+valueOps+"/"+classType.getCanonicalName().toString()));
	}
	

	
	/****
	 * @author Sérgio da victória
	 * @param key Atributo pra procura
	 * @param value Valor do Atributo
	 * @param opsMap Lista de Atributos a Serem Alterados Em Um Map
	 * @param type CLasse Objecto DTO para fazer update
	 */
	public void updateWithTListMap(String key, Object value, Map<String, Object> opsMap, Class<?> type) {
		
	}
	
	public List<Contrato> getContratoS(String statusContrato) throws Exception {
		logger.info("Fazendo chamada a rest "+buildURL(buildURL(FIND_CONTRATOS)+statusContrato));
		String response = executeGetRequest(buildURL(FIND_CONTRATOS)+statusContrato);
		Type listContratoType = new TypeToken<List<Contrato>>() {}.getType();
		List<Contrato> contratos = getGson().fromJson(response, listContratoType);
		return contratos.size() > 0 ? contratos : null; 
	}
	
	
	protected Gson getGson(){
		return this.gson;
	}
	
	
	private String buildURL(String url) {
		return  "http://"+this.hostName+":"+this.portName+url;
	}
	
	private String executeGetRequest(String url) throws ClientProtocolException, IOException, GenericHttpStatusException, HttpStatus401Exception, HttpStatus404Exception {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);

		int statusCode = response.getStatusLine().getStatusCode();

		if(is_200_OK(statusCode)){
			return EntityUtils.toString(response.getEntity());
		}
		else{
			if (is_401_UNAUTHORIZED(statusCode)) {				
				throw new HttpStatus401Exception(url);
			} 				
			if(is_404_NOT_FOUND(statusCode)){
				throw new HttpStatus404Exception(url);
			}			
			throw new GenericHttpStatusException(statusCode, url);			
		}
	}
	
	private String executePostRequest(String url) throws ClientProtocolException, IOException, GenericHttpStatusException, HttpStatus401Exception, HttpStatus404Exception {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost getPost = new HttpPost(url);
		getPost.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getPost);

		int statusCode = response.getStatusLine().getStatusCode();

		if(is_200_OK(statusCode)){
			return EntityUtils.toString(response.getEntity());
		}
		else{
			if (is_401_UNAUTHORIZED(statusCode)) {				
				throw new HttpStatus401Exception(url);
			} 				
			if(is_404_NOT_FOUND(statusCode)){
				throw new HttpStatus404Exception(url);
			}			
			throw new GenericHttpStatusException(statusCode, url);			
		}
	}
  
	public static void main(String[] args) throws Exception {
		
//		RemoteService remoteService = new RemoteService();
//		List<Contrato> contratos = RemoteService.getInstance().getContratoS("1");
//		for (Contrato contrato : contratos) {
//			System.out.println(" contrato "+contrato.getNumeroInstanciaJuridico());
//		}

		///Contrato.class,  "updateWithTwoAttributes" ,  "statusContrato", 1, "numeroInstanciaJuridico", processInstanceId);
		RemoteService remoteService = new RemoteService();
		RemoteService.getInstance().updateWithTwoAttributes("statusContrato",333, "numeroInstanciaOrigem", 13, Contrato.class);
		
	}

}
