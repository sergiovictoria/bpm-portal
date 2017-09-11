package br.com.seta.processo.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.GroupSearchDescriptor;
import org.bonitasoft.engine.identity.RoleCreator;
import org.bonitasoft.engine.identity.RoleCriterion;
import org.bonitasoft.engine.identity.RoleSearchDescriptor;
import org.bonitasoft.engine.identity.RoleUpdater;
import org.bonitasoft.engine.search.SearchFilterOperation;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.search.impl.SearchFilter;
import org.bonitasoft.engine.session.APISession;
import org.jboss.logging.Logger;

import br.com.seta.processo.constant.MemberType;
import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;

import com.google.gson.reflect.TypeToken;

@Stateless(name = "RoleService")
@LocalBean
public class RoleService extends BonitaService {
	
	private static final String actorMemberApiUrl = "/bonita/API/bpm/actorMember";
	private static final String userApiUrl = "/bonita/API/identity/user";
	@Inject private Logger logger;
	
	private User user = new User();

	@PostConstruct
	public void init() {
		this.user.setUserName("install");
		this.user.setPassword("install");
		logger.info("Acessando EJB - Roles");
	}
	
	public org.bonitasoft.engine.identity.Role findOneRole(String roleName) throws BonitaException {
		logger.info(" Chamando serviço  findOneRole...  ");
		org.bonitasoft.engine.identity.Role role = null; 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, roleName).done(); 
		SearchResult<org.bonitasoft.engine.identity.Role> searchResultGroup =  identityAPI.searchRoles(searchOptions);
		role = searchResultGroup.getResult().get(0);
		doTenantLogout(session);
		return role;
	}
	
	public List<org.bonitasoft.engine.identity.Role> findRolesByName(int startIndex, int maxResults, String name) throws BonitaException {
		logger.info(" Chamando serviço  findRolesByName...  ");
		List<org.bonitasoft.engine.identity.Role> roles = new ArrayList<org.bonitasoft.engine.identity.Role>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(RoleSearchDescriptor.NAME, SearchFilterOperation.EQUALS, name));
		SearchOptions srco = new SearchOptionsBuilder(startIndex, maxResults).setFilters(filters).done();
		roles = identityAPI.searchRoles(srco).getResult();
		
		doTenantLogout(session);
		return roles;
	}
	
	public List<org.bonitasoft.engine.identity.Role> findRoles(int startIndex, int maxResults, RoleCriterion rolecriterion) throws BonitaException {
		logger.info(" Chamando serviço  findRoles...  ");
		List<org.bonitasoft.engine.identity.Role> roles = new ArrayList<org.bonitasoft.engine.identity.Role>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		roles = identityAPI.getRoles(startIndex, maxResults, rolecriterion);
		
		doTenantLogout(session);
		return roles;
	}
	
	public List<Role> listaRolesPorAtor(User user, TaskProcess processo, Actor ator, MemberType memberType) throws BonitaException {
		String url = getApiActorMemberUrl();
		url = url + "?c=100&d=user_id&f=process_id%3D" +
					processo.getId() + 
					"&f=actor_id%3D" + 
					ator.getId() +
					"&f=member_type%3D" +
					memberType.name() +
					"&p=0";

		List<Role> roles = executeGetRequestForRoleList(user, url);
		return roles;
	}
	
	public List<User> listaUsuariosPorRole(User user, Role role) throws BonitaException {
		String url = getApiUserUrl();
		url = url + 
			  "?p=0&c=1000&o=lastname%20ASC&f=enabled%3dtrue&f=role_id%3d" +
			  role.getId();
		
		List<User> users = executeGetRequestForUserRoleList(user, url);
		return users;
	}
	
	private List<Role> executeGetRequestForRoleList(User user, String processUri) throws BonitaException {
		try {
			String responseBody = executeGetRequest(user, processUri);
			Type listRoleType = new TypeToken<List<Role>>() {}.getType();
			List<Role> roles = getGson().fromJson(responseBody, listRoleType);
			return roles;
		} catch (HttpStatus401Exception | 
				 HttpStatus404Exception | 
				 GenericHttpStatusException | 
				 IOException e) {
			throw new BonitaException(e);
		}
	}
	
	private List<User> executeGetRequestForUserRoleList(User user, String processUri) throws BonitaException {
		try {
			String responseBody = executeGetRequest(user, processUri);
			Type listUserRoleType = new TypeToken<List<User>>() {}.getType();
			List<User> usersRole = getGson().fromJson(responseBody, listUserRoleType);
			return usersRole;
		} catch (HttpStatus401Exception | 
				 HttpStatus404Exception | 
				 GenericHttpStatusException | 
				 IOException e) {
			throw new BonitaException(e);
		}
	}
	
	private String getApiActorMemberUrl() {
		return getBonitaURI() + actorMemberApiUrl;
	}
	
	private String getApiUserUrl() {
		return getBonitaURI() + userApiUrl;
	}

	public long createRole(Role role) throws BonitaException {
		logger.info(" Chamando serviço  createRole...  ");
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		RoleCreator rc = new RoleCreator(role.getName());
		rc.setDescription(role.getDescription());
		rc.setDisplayName(role.getDisplayName());
		
		org.bonitasoft.engine.identity.Role rl = identityAPI.createRole(rc);
		
		doTenantLogout(session);
		return rl.getId();
	}
	
	public void updateRole(Role role) throws BonitaException {
		logger.info(" Chamando serviço  createRole...  ");
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		RoleUpdater rc = new RoleUpdater();
		rc.setDescription(role.getDescription());
		rc.setName(role.getName());
		rc.setDisplayName(role.getDisplayName());
		
		org.bonitasoft.engine.identity.Role rl = identityAPI.updateRole(role.getId(), rc);
		
		doTenantLogout(session);
	}
}
