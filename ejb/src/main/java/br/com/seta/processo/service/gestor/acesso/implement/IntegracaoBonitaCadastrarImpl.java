package br.com.seta.processo.service.gestor.acesso.implement;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.bonitasoft.engine.exception.BonitaException;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.service.MembershipService;
import br.com.seta.processo.service.RoleService;
import br.com.seta.processo.service.UserService;
import br.com.seta.processo.service.gestor.acesso.interfaces.IntegracaoBonitaCadastrar;

@Stateless(name="IntegracaoBonitaCadastrarImpl")
@Remote(IntegracaoBonitaCadastrar.class)
public class IntegracaoBonitaCadastrarImpl implements IntegracaoBonitaCadastrar {

	@Inject UserService userService;
	@Inject GroupService groupService;
	@Inject RoleService roleService;
	@Inject MembershipService membershipService;
	@Inject	Logger logger;
	
	@Override
	public long createUser(User user) {
		try {
			logger.info("Criando Usuário [ "+user.getFirstname()+ " ]");
			return userService.createUser(user);
		} catch (BonitaException e) {
			logger.error("Erro ao tentat criar Usuário [ "+user.getFirstname()+ " ]",e);
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public long createGroup(Group group) {
		try {
			return groupService.createGroup(group);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public long createRole(Role role) {
		try {
			return roleService.createRole(role);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public long insertUserMembership(long userId, long groupId, long roleId) {
		try {
			return membershipService.insertMembership(userId, groupId, roleId);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public long insertUserInProfile(long userId, long profileId) {
		try {
			return userService.inserProfile(userId, profileId);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
