package br.com.seta.processo.service.gestor.acesso.implement;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.service.MembershipService;
import br.com.seta.processo.service.RoleService;
import br.com.seta.processo.service.UserService;
import br.com.seta.processo.service.gestor.acesso.interfaces.IntegracaoBonitaAtualizar;

@Stateless(name="IntegracaoBonitaAtualizarImpl")
@Remote(IntegracaoBonitaAtualizar.class)
public class IntegracaoBonitaAtualizarImpl implements IntegracaoBonitaAtualizar {

	@Inject UserService userService;
	@Inject GroupService groupService;
	@Inject RoleService roleService;
	@Inject MembershipService membershipService;
	
	@Override
	public void updateUser(User user) {
		try {
			userService.updateUser(user);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateGroup(Group group) {
		try {
			groupService.updateGroup(group);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateRole(Role role) {
		try {
			roleService.updateRole(role);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateUserMembership(long membershipId, long newGroupId, long newRoleId) {
		try {
			membershipService.updateMembership(membershipId, newGroupId, newRoleId);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateProfile(long profileUserId, long userId, long profileId) {
		try {
			userService.updateProfileUser(profileUserId, userId, profileId);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteMembership(long userMembershipId) {
		try {
			membershipService.deleteMembership(userMembershipId);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
}
