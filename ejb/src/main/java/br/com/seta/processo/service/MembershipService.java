package br.com.seta.processo.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.UserMembership;
import org.bonitasoft.engine.session.APISession;
import org.jboss.logging.Logger;

import com.google.gson.reflect.TypeToken;

import br.com.seta.processo.constant.MemberType;
import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Membership;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;

@Stateless(name = "MembershipService")
@LocalBean
public class MembershipService extends BonitaService {
	
	private static final String actorMemberApiUrl = "/bonita/API/bpm/actorMember";
	
	@Inject 
	private RoleService roleService;
	
	@Inject 
	private GroupService groupService;
	
	@Inject private Logger logger;
	
	private User user = new User();

	@PostConstruct
	public void init() {
		this.user.setUserName("install");
		this.user.setPassword("install");
		logger.info("Acessando EJB -  Membership");
	}
	
	public List<Membership> listaMembershipsPorAtor(User user, TaskProcess processo, Actor ator, MemberType memberType) throws BonitaException {
		String url = getApiActorMemberUrl();
		url = url + "?c=100&d=user_id&f=process_id%3D" +
					processo.getId() + 
					"&f=actor_id%3D" + 
					ator.getId() +
					"&f=member_type%3D" +
					memberType.name() +
					"&p=0";

		List<Membership> memberships = executeGetRequestForMembershipList(user, url);
		return memberships;
	}
	
	public List<User> listaUsuariosPorMemberships(User user, Membership membership) throws BonitaException {
		Set<User> usuariosMemberships = new HashSet<User>();
		
		Role role = new Role();
		Group group = new Group();
		
		role.setId(membership.getRole_id());
		group.setId(membership.getGroup_id());
		
		List<User> usersRole = roleService.listaUsuariosPorRole(user, role);
		List<User> usersGroup = groupService.listaUsuariosPorGrupo(user, group);
		
		for(User usuarioDaRole : usersRole){
			if(usersGroup.contains(usuarioDaRole)){
				usuariosMemberships.add(usuarioDaRole);
			}
		}
		
		return new ArrayList<User>(usuariosMemberships);
	}
	
	private List<Membership> executeGetRequestForMembershipList(User user, String processUri) throws BonitaException {
		try {
			String responseBody = executeGetRequest(user, processUri);
			Type listMembershipType = new TypeToken<List<Membership>>() {}.getType();
			List<Membership> memberships = getGson().fromJson(responseBody, listMembershipType);
			return memberships;
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
	
	public long insertMembership(long userId, long groupId, long roleId) throws BonitaException {
		logger.info(" Chamando serviço  createRole...  ");
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		UserMembership ums = identityAPI.addUserMembership(userId, groupId, roleId);
		
		doTenantLogout(session);
		return ums.getId();
	}
	
	public void updateMembership(long userMembershipId, long groupId, long roleId) throws BonitaException {
		logger.info(" Chamando serviço  updateMembership...  ");
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		UserMembership ums = identityAPI.updateUserMembership(userMembershipId, groupId, roleId);
		
		doTenantLogout(session);
	}
	
	public void deleteMembership(long userMembershipId) throws BonitaException {
		logger.info(" Chamando serviço  deleteMembership...  ");
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		identityAPI.deleteUserMembership(userMembershipId);
		
		doTenantLogout(session);
	}
}
