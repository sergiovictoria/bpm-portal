package br.com.seta.processo.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.bonitasoft.engine.exception.BonitaException;

import com.google.gson.reflect.TypeToken;

import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name = "ProcessoService")
@LocalBean
public class ProcessoService extends BonitaService {
	
	private static final String processApiUrl = "/bonita/API/bpm/process";
	
	public List<TaskProcess> listaProcessos(User user) throws BonitaException{
		String url = getApiProcessosUrl();
		url = url + "?p=0&c=1000";
		
		List<TaskProcess> processos = executeGetRequestForProcessList(user, url);
		return processos;
	}
	
	private String getApiProcessosUrl(){
		return getBonitaURI() + processApiUrl;
	}
	
	private List<TaskProcess> executeGetRequestForProcessList(User user, String processUri) throws BonitaException{
		try {
			String responseBody = executeGetRequest(user, processUri);
			Type listProcessType = new TypeToken<List<TaskProcess>>() {}.getType();
			List<TaskProcess> processos = getGson().fromJson(responseBody, listProcessType);
			return processos;
		} catch (HttpStatus401Exception | HttpStatus404Exception
				| GenericHttpStatusException | IOException e) {
			throw new BonitaException(e);
		}
	}
}
