package br.com.seta.processo.service.gestor.acesso.interfaces;

import javax.ejb.Remote;

import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.User;

@Remote
public interface IntegracaoBonitaAtualizar {

	public abstract void updateUser(User user);

	public abstract void updateGroup(Group group);

	public abstract void updateRole(Role role);

	public abstract void updateUserMembership(long membershipId, long newGroupId, long newRoleId);

	public abstract void updateProfile(long profileUserId, long userId, long profileId);

	public abstract void deleteMembership(long userMembershipId);
}