package br.com.seta.processo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.GroupCriterion;
import org.bonitasoft.engine.identity.GroupSearchDescriptor;
import org.bonitasoft.engine.identity.UserWithContactData;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.RemoteException;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name = "FindGroups")
@LocalBean
@Interceptors({LogInterceptor.class})
public class FindGroups extends BonitaService {

	@Inject
	private Logger logger;

	private User user = new User();

	@PostConstruct
	public void init() {
		this.user.setUserName("install");
		this.user.setPassword("install");
		logger.info("Acessando EJB - Grupos");
	}


	public List<org.bonitasoft.engine.identity.Group> findGroups(int startIndex, int maxResults, GroupCriterion groupcriterion) throws BonitaException {
		logger.info(" Chamando serviço  findGroups...  ");
		List<org.bonitasoft.engine.identity.Group> grupos = new ArrayList<org.bonitasoft.engine.identity.Group>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		grupos = identityAPI.getGroups(startIndex, maxResults , groupcriterion);
		doTenantLogout(session);
		return grupos;

	}


	public org.bonitasoft.engine.identity.Group findOneGroup(String groupName) throws BonitaException {
		logger.info(" Chamando serviço  findOneGroup...  ");
		org.bonitasoft.engine.identity.Group group = null; 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, groupName).done(); 
		SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
		group = searchResultGroup.getResult().get(0);
		doTenantLogout(session);
		return group;

	}


	public List<org.bonitasoft.engine.identity.User> findUserForGroups(long idGrupo, int startIndex, int maxResults) throws BonitaException {
		logger.info(" Chamando serviço  findUserForGroups...  ");
		List<org.bonitasoft.engine.identity.User> users = new ArrayList<org.bonitasoft.engine.identity.User>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		users = identityAPI.getUsersInGroup(idGrupo,startIndex,maxResults,org.bonitasoft.engine.identity.UserCriterion.FIRST_NAME_ASC);

		doTenantLogout(session);
		return users;

	}


	public List<UserWithContactData> findWithContactDataS(List<org.bonitasoft.engine.identity.User> userSearch) throws BonitaException {
		logger.info(" Chamando serviço  findWithContactDataS...  ");
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		return findWithContactDataS(identityAPI, userSearch);
	}


	private List<UserWithContactData> findWithContactDataS(IdentityAPI identityAPI , List<org.bonitasoft.engine.identity.User> userSearch) throws BonitaException {
		logger.info(" Chamando serviço  findWithContactDataS...  ");
		List<UserWithContactData> contactDatas = new ArrayList<UserWithContactData>();
		for (org.bonitasoft.engine.identity.User dto : userSearch) {
			contactDatas.add(identityAPI.getUserWithProfessionalDetails(dto.getId()));	
		}
		return contactDatas;

	}



	public List<String> findEmails(String groupName) throws Exception {

		logger.info(" Chamando serviço  findEmails...  ");
		List<String> emails = new ArrayList<String>();

		try {
			APISession session = doTenantLogin("install","install");
			SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, groupName).done(); 
			IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
			SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
			org.bonitasoft.engine.identity.Group group;
			
			if (searchResultGroup.getResult().size()>0) {
				group = searchResultGroup.getResult().get(0);
			}else {
				emails.add("Grupo não possui emails");
				return emails;
			}

			List<org.bonitasoft.engine.identity.User> users = new ArrayList<org.bonitasoft.engine.identity.User>(); 
			users = identityAPI.getUsersInGroup(group.getId(),0,1000,org.bonitasoft.engine.identity.UserCriterion.FIRST_NAME_ASC);

			for (org.bonitasoft.engine.identity.User dto : users) {
				UserWithContactData withContactData = identityAPI.getUserWithProfessionalDetails(dto.getId());
				if (withContactData.getContactData().getEmail() != null ) {
					emails.add(withContactData.getContactData().getEmail().toString());	
				}
			}
		} catch (Exception e) {
			throw new RemoteException("Ocorreu um erro ao tentar acesssar Bonita API "+e);
		}

		return emails;

	}

}
