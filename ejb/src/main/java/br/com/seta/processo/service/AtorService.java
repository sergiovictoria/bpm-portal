package br.com.seta.processo.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.bonitasoft.engine.exception.BonitaException;

import com.google.gson.reflect.TypeToken;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;

@Stateless(name = "AtorService")
@LocalBean
public class AtorService extends BonitaService {

	private static final String actorApiUrl = "/bonita/API/bpm/actor";

	public List<Actor> listaAtores(User user, TaskProcess processo) throws BonitaException {
		String url = getApiAtorUrl();
		url = url + "?c=100&f=process_id%3D" 
				  + processo.getId()
				  + "&n=users&n=groups&n=roles&n=memberships&o=name+ASC&p=0";

		List<Actor> atores = executeGetRequestForActorList(user, url);
		return atores;
	}

	private List<Actor> executeGetRequestForActorList(User user, String processUri) throws BonitaException {
		try {
			String responseBody = executeGetRequest(user, processUri);
			Type listActorType = new TypeToken<List<Actor>>() {}.getType();
			List<Actor> atores = getGson().fromJson(responseBody, listActorType);
			return atores;
		} catch (HttpStatus401Exception | 
				 HttpStatus404Exception | 
				 GenericHttpStatusException | 
				 IOException e) {
			throw new BonitaException(e);
		}
	}

	private String getApiAtorUrl() {
		return getBonitaURI() + actorApiUrl;
	}

}
