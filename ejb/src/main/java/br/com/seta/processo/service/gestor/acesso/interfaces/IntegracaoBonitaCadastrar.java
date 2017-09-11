package br.com.seta.processo.service.gestor.acesso.interfaces;

import javax.ejb.Remote;

import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.User;

@Remote
public interface IntegracaoBonitaCadastrar {

	public abstract long createUser(User user);

	public abstract long createGroup(Group group);

	public abstract long createRole(Role role);

	public abstract long insertUserMembership(long userId, long groupId, long roleId);

	public abstract long insertUserInProfile(long userId, long profileId);

}