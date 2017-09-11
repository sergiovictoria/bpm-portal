package br.com.seta.processo.authorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.utils.PropertiesEJBUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@javax.ejb.Singleton
@javax.ejb.Startup
@javax.ejb.ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@LocalBean
@AccessTimeout(value=5, unit=TimeUnit.SECONDS)
public class AcessoService {



	private static Logger logger = Logger.getLogger(AcessoService.class);


	private HttpClient httpClient;
	private HttpContext httpContext;
	private String hostNameBPM;
	private String portNameBPM;
	private String _BONITA_URI; 
	private static final String _TASK = "/API/bpm/humanTask?p=0&c=100&f=state%3dready&f=user_id%3d"; 
	private long userLogin;
	private PoolingClientConnectionManager conMan;


	public AcessoService() {
		try{
			this.hostNameBPM = PropertiesEJBUtils.getInstance().getValues("hostNameBPM");
			this.portNameBPM = PropertiesEJBUtils.getInstance().getValues("portNameBPM");
		}catch(Exception e) {
			this.hostNameBPM = "localhost";
			this.portNameBPM = "8080";
		}
		this._BONITA_URI = "http://"+ this.hostNameBPM +":" +this.portNameBPM +"/bonita";
		this.conMan = getConnectionManager();
		this.httpClient = new DefaultHttpClient(this.conMan);
		logger.info("Executando modulo de acesso bonita hostName "+this._BONITA_URI);
	}



	@PostConstruct
	public void init() {
		try{
			this.hostNameBPM = PropertiesEJBUtils.getInstance().getValues("hostNameBPM");
			this.portNameBPM = PropertiesEJBUtils.getInstance().getValues("portNameBPM");
		}catch(Exception e) {
			this.hostNameBPM = "localhost";
			this.portNameBPM = "8080";
		}
		this._BONITA_URI = "http://"+ this.hostNameBPM +":" +this.portNameBPM +"/bonita";
		this.conMan = getConnectionManager();
		this.httpClient = new DefaultHttpClient(this.conMan);
		logger.info("Executando modulo de acesso bonita hostName "+this._BONITA_URI);
	}



	private static PoolingClientConnectionManager getConnectionManager() {
		PoolingClientConnectionManager conMan = new PoolingClientConnectionManager(SchemeRegistryFactory.createDefault());
		conMan.setMaxTotal(1000);
		conMan.setDefaultMaxPerRoute(1000);
		return conMan;
	}


	@Lock(LockType.WRITE)
	@AccessTimeout(value=5, unit=TimeUnit.SECONDS)
	public User loginAs(String username, String password) {
		User user = new User();
		user.setIsLogin(false);
		try {
			CookieStore cookieStore = new BasicCookieStore();
			this.httpContext = new BasicHttpContext();
			this.httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
			String loginURL = "/loginservice";
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("username", username));
			urlParameters.add(new BasicNameValuePair("password", password));
			urlParameters.add(new BasicNameValuePair("redirect", "false"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(urlParameters, "utf-8");
			HttpResponse response = executePostRequestLogin(loginURL, entity);
			if (IsStatusOk(response)==200) {
				String[] returnString = getUserIdFromSession();
				user.setIdUser(new Long(returnString[0]));
				user.setFirstname(returnString[1]);
				usuarioLogado(username,password);
				user.setHttpClient(this.httpClient);
				user.setHttpContext(this.httpContext);
				user.setCookieStore(cookieStore);
				user.setIsLogin(true);
				user.setUserName(username);
				user.setPassword(password);
				return user;
			}
		}catch (Exception e ) {
			logger.error(" Erro ao tentar fazer login no bonita ", e );
		}
		return user;
	}


	private  HttpResponse executePostRequestLogin(String apiURI, UrlEncodedFormEntity entity) {
		try {
			HttpPost postRequest = new HttpPost(_BONITA_URI + apiURI);
			postRequest.setEntity(entity);
			HttpResponse response = this.httpClient.execute(postRequest, this.httpContext);
			return response;
		} catch (HttpHostConnectException e) {
			throw new RuntimeException("Não foi possível conexão com bonita" + _BONITA_URI, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	private int executePostRequest(String apiURI, UrlEncodedFormEntity entity) {
		try {
			HttpPost postRequest = new HttpPost(_BONITA_URI + apiURI);
			postRequest.setEntity(entity);
			HttpResponse response = this.httpClient.execute(postRequest, this.httpContext);
			return consumeResponse(response, true);
		} catch (HttpHostConnectException e) {
			throw new RuntimeException("Bonita não foi acesso ou não esta up " + _BONITA_URI, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	private int consumeResponse(HttpResponse response, boolean printResponse) {
		String responseAsString = consumeResponseIfNecessary(response);
		if(printResponse) {
			//			System.out.println(responseAsString);
		}
		return ensureStatusOk(response);
	}

	private int consumeResponse(HttpResponse response, boolean printResponse, boolean isLogin) {
		String responseAsString = consumeResponseIfNecessary(response);
		if(printResponse) {
			System.out.println(responseAsString);
		}
		return ensureStatusOk(response);
	}


	private String consumeResponseIfNecessary(HttpResponse response) {
		if (response.getEntity() != null) {
			BufferedReader rd;
			try {
				rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				return result.toString();
			} catch (Exception e) {
				throw new RuntimeException("Falhou consumidor de reposta ", e);
			}
		} else {
			return "";
		}
	}



	private Integer IsStatusOk(HttpResponse response) {
		return response.getStatusLine().getStatusCode();
	}


	private int ensureStatusOk(HttpResponse response) {
		//		if (response.getStatusLine().getStatusCode() != 201 && response.getStatusLine().getStatusCode() != 200) {
		//			throw new RuntimeException("Falha : HTTP erro codigo : " + response.getStatusLine().getStatusCode() + " : "	+ response.getStatusLine().getReasonPhrase());
		//		}
		return response.getStatusLine().getStatusCode();
	}


	public void usuarioLogado(String username, String password ) {
		logger.info(" Usuário [  "+username+ " ] está efetuando login, acesso de numero [ "+getUserLogin() +" ]" );
	}


	@AccessTimeout(value=5, unit=TimeUnit.SECONDS)
	@Lock(LockType.WRITE)
	public long getUserLogin() {
		return userLogin++;
	}


	private String[] extractUserIdFrom(HttpResponse response) {
		String [] returnString = new String [2]; 
		try {
			String session = EntityUtils.toString(response.getEntity());
			String remain = session.substring(session.indexOf("user_id\":") + 10);
			String userid = remain.substring(0, remain.indexOf("\""));
			returnString[0] = userid;
			remain = session.substring(session.indexOf("user_name\":")+12);
			remain = remain.substring(0, remain.indexOf(",")-1);
			returnString[1] = remain;
			
			System.out.println("  ****************************  { "+returnString[0]+ " }"+" [ "+returnString[1] +"] ");
			
			return returnString;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String[] getUserIdFromSession() {
		try {
			HttpGet getRequest = new HttpGet(_BONITA_URI + "/API/system/session/unusedid");
			HttpResponse response = this.httpClient.execute(getRequest, httpContext);
			return extractUserIdFrom(response);
		} catch (Exception e) {
			logger.error("Erro na session do usuário ",e);
		}
		return null;
	}


	private HttpResponse executeGetRequest(String apiURI) {
		try {
			HttpGet getRequest = new HttpGet(_BONITA_URI + apiURI);
			HttpResponse response = httpClient.execute(getRequest, httpContext);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@AccessTimeout(value=5, unit=TimeUnit.SECONDS)
	private String extractProcessId(HttpResponse response) {
		ensureStatusOk(response);
		try {
			String processInJSON = EntityUtils.toString(response.getEntity());
			String remain = processInJSON.substring(processInJSON.indexOf("id\":") + 5);
			String id = remain.substring(0, remain.indexOf("\""));
			return id;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@AccessTimeout(value=5, unit=TimeUnit.SECONDS)
	private List<HumanTask> extractProcessList(String userId) throws ParseException, IOException {
		List<HumanTask> tasks = new ArrayList<HumanTask>();
		HttpResponse responseListTask = executeGetRequest(_TASK + userId);
		String processInJSON = EntityUtils.toString(responseListTask.getEntity());
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Type listType = new TypeToken<List<HumanTask>>() {  
		}.getType();
		tasks = gson.fromJson(processInJSON, listType);
		return tasks;
	}


	public static void main(String[] args) throws ParseException, IOException {
		AcessoService acessoService = new AcessoService();
		User user = acessoService.loginAs("walter.bates","bpm");
		List<HumanTask> list = acessoService.extractProcessList(Long.toString(user.getIdUser()));
		System.out.println(" Executado "+user.getIdUser());
		for (HumanTask task : list) {
             System.out.println(" task "+task.toString());			
		}
	}

}
