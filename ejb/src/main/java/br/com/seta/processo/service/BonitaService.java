package br.com.seta.processo.service;

import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_200_OK;
import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_401_UNAUTHORIZED;
import static br.com.seta.processo.utils.HttpStatusCodeUtils.is_404_NOT_FOUND;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.mapping.ByteArrayToBase64TypeAdapter;
import br.com.seta.processo.mapping.DateDeserializer;
import br.com.seta.processo.mapping.UserDeserializer;
import br.com.seta.processo.utils.PropertiesEJBUtils;

/**
 * Classe base para os serviços que acessam a base do Bonita BPM
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public abstract class BonitaService {
	
	private String hostNameBPM;
	private String portNameBPM;
	private String BONITA_URI;
	private Gson gson;
	
	public BonitaService(){
		this.hostNameBPM = PropertiesEJBUtils.getInstance().getValues("hostNameBPM");
		this.portNameBPM = PropertiesEJBUtils.getInstance().getValues("portNameBPM");
		this.BONITA_URI = "http://"+ this.hostNameBPM +":" +this.portNameBPM;
		
		this.gson = new GsonBuilder()
		.registerTypeAdapter(Date.class, new DateDeserializer())		
		.registerTypeAdapter(User.class, new UserDeserializer())
		.registerTypeAdapter(byte[].class, new ByteArrayToBase64TypeAdapter())
		.create();
	}

	protected APISession doTenantLogin(User user) throws BonitaException {
		APISession session = getLoginAPI().login(user.getUserName(), user.getPassword());
		return session;
	}
	
	protected APISession doTenantLogin(String username, String password) throws BonitaException {
		APISession session = getLoginAPI().login(username, password);
		return session;
	}
	
	/**
	 * 
	 * @return URI do bonita no formato http://{host}:{porta}
	 */
	protected String getBonitaURI(){
		return this.BONITA_URI;
	}
	
	/**
	 * Executa uma requisição HTTP GET
	 * 
	 * @param user Usuário
	 * @param resourceUri Uri do recurso 
	 * @return Uma string cujo valor é o recurso requisitado 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws HttpStatus401Exception
	 * @throws HttpStatus404Exception
	 * @throws GenericHttpStatusException
	 */
	protected String executeGetRequest(User user, String resourceUri) throws IOException, ClientProtocolException, HttpStatus401Exception, HttpStatus404Exception, GenericHttpStatusException {
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
	
	protected Gson getGson(){
		return this.gson;
	}

	private LoginAPI getLoginAPI() throws BonitaException {
		Map<String, String> settings = new HashMap<String, String>();		
		settings.put("server.url", this.BONITA_URI);
		settings.put("application.name", "bonita");
		APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, settings);
		return TenantAPIAccessor.getLoginAPI();
	}	
	
	public void doTenantLogout(APISession session) throws BonitaException {
		getLoginAPI().logout(session);
	}
	
}
