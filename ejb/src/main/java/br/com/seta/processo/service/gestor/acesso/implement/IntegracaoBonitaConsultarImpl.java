package br.com.seta.processo.service.gestor.acesso.implement;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.GroupCriterion;
import org.bonitasoft.engine.identity.GroupSearchDescriptor;
import org.bonitasoft.engine.identity.RoleCriterion;

import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.service.RoleService;
import br.com.seta.processo.service.UserService;
import br.com.seta.processo.service.gestor.acesso.interfaces.IntegracaoBonitaConsultar;

@Stateless(name="IntegracaoBonitaConsultarImpl")
@Remote(IntegracaoBonitaConsultar.class)
public class IntegracaoBonitaConsultarImpl implements IntegracaoBonitaConsultar {

	@Inject UserService userService;
	@Inject GroupService groupService;
	@Inject RoleService roleService;
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#getFirstGroupByName(java.lang.String)
	 */
	@Override
	public Group getFirstGroupByName(String name) {
		
		org.bonitasoft.engine.identity.Group grp = null;
		
		try {
			if(name != null && !name.isEmpty())
				grp = groupService.findOneGroup(name);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		Group grupo = null;
		
		if(grp != null) {
			grupo = new Group();
			grupo.setId(grp.getId());
			grupo.setName(grp.getName());
			grupo.setDisplayName(grp.getDisplayName());
			grupo.setParent_path(grp.getParentPath());
			grupo.setDescription(grp.getDescription());
		}
		
		return grupo;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findAllGroups()
	 */
	@Override
	public List<Group> findAllGroups() {
		
		List<org.bonitasoft.engine.identity.Group> groups = null;
		
		try {
			groups = groupService.findGroups(0, 10000, GroupCriterion.NAME_ASC);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		List<Group> grupos = null;
		
		if(groups != null){
			for(org.bonitasoft.engine.identity.Group grp : groups) {
				if(grupos == null)
					grupos = new ArrayList<Group>();
				Group grupo = new Group();
				grupo.setId(grp.getId());
				grupo.setName(grp.getName());
				grupo.setDisplayName(grp.getDisplayName());
				grupo.setParent_path(grp.getParentPath());
				grupo.setDescription(grp.getDescription());
				
				grupos.add(grupo);
			}
		}
		
		return grupos;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findAllGroupsByName(java.lang.String)
	 */
	@Override
	public List<Group> findAllGroupsByName(String name) {
		
		List<org.bonitasoft.engine.identity.Group> groups = null;
		
		try {
			groups = groupService.findGroupsByGroupDescriptor(0, 10000, GroupSearchDescriptor.DISPLAY_NAME, name);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		List<Group> grupos = null;
		
		if(groups != null){
			for(org.bonitasoft.engine.identity.Group grp : groups) {
				if(grupos == null)
					grupos = new ArrayList<Group>();
				Group grupo = new Group();
				grupo.setId(grp.getId());
				grupo.setName(grp.getName());
				grupo.setDisplayName(grp.getDisplayName());
				grupo.setParent_path(grp.getParentPath());
				grupo.setDescription(grp.getDescription());
				
				grupos.add(grupo);
			}
		}
		
		return grupos;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findAllGroupsByParent(java.lang.String)
	 */
	@Override
	public List<Group> findAllGroupsByParent(String parent) {
		
		List<org.bonitasoft.engine.identity.Group> groups = null;
		
		try {
			groups = groupService.findGroupsByGroupDescriptor(0, 10000, GroupSearchDescriptor.PARENT_PATH, parent);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		List<Group> grupos = null;
		
		if(groups != null){
			for(org.bonitasoft.engine.identity.Group grp : groups) {
				if(grupos == null)
					grupos = new ArrayList<Group>();
				Group grupo = new Group();
				grupo.setId(grp.getId());
				grupo.setName(grp.getName());
				grupo.setDisplayName(grp.getDisplayName());
				grupo.setParent_path(grp.getParentPath());
				grupo.setDescription(grp.getDescription());
				
				grupos.add(grupo);
			}
		}
		
		return grupos;
	}

	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findGroupById(long)
	 */
	@Override
	public Group findGroupById(long id) {
		org.bonitasoft.engine.identity.Group grp = null;
		
		try {
			grp = groupService.findOneGroupById(id);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		Group grupo = null;
		
		if(grp != null) {
			grupo = new Group();
			grupo.setId(grp.getId());
			grupo.setName(grp.getName());
			grupo.setDisplayName(grp.getDisplayName());
			grupo.setParent_path(grp.getParentPath());
			grupo.setDescription(grp.getDescription());
		}
		
		return grupo;
	}
	
	
	
	
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findRoleByName(java.lang.String)
	 */
	@Override
	public Role findRoleByName(String name){
		org.bonitasoft.engine.identity.Role rl = null;
		
		try {
			if(name != null && !name.isEmpty())
				rl = roleService.findOneRole(name);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		Role role = null;
		
		if(rl != null) {
			role = new Role();
			role.setId(rl.getId());
			role.setName(rl.getName());
			role.setDisplayName(rl.getDisplayName());
		}
		
		return role;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findAllRoles()
	 */
	@Override
	public List<Role> findAllRoles(){
		List<org.bonitasoft.engine.identity.Role> rls = null;
		
		try {
			rls = roleService.findRoles(0, 10000, RoleCriterion.NAME_ASC);
		} catch (BonitaException e) {
			e.printStackTrace();
		}

		List<Role> roles = null;
		
		if(rls != null){
			for(org.bonitasoft.engine.identity.Role rl : rls) {
				if(roles == null)
					roles = new ArrayList<Role>();
				Role role = new Role();
				role.setId(rl.getId());
				role.setName(rl.getName());
				role.setDisplayName(rl.getDisplayName());
				
				roles.add(role);
			}
		}
		
		return roles;
	}

	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findAllRolesByName(java.lang.String)
	 */
	@Override
	public List<Role> findAllRolesByName(String name){
		List<org.bonitasoft.engine.identity.Role> rls = null;
		
		try {
			rls = roleService.findRolesByName(0, 10000, name);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		List<Role> roles = null;
		
		if(rls != null){
			for(org.bonitasoft.engine.identity.Role rl : rls) {
				if(roles == null)
					roles = new ArrayList<Role>();
				Role role = new Role();
				role.setId(rl.getId());
				role.setName(rl.getName());
				role.setDisplayName(rl.getDisplayName());
				
				roles.add(role);
			}
		}
		
		return roles;
	}

	
	
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findAllUsers()
	 */
	@Override
	public List<User> findAllUsers() {
		
		try {
			return userService.findUserS();
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findUsersByName(java.lang.String)
	 */
	@Override
	public List<User> findUsersByName(String firstName) {
		try {
			return userService.findUsersByFirstName(firstName);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findUsersByFirstAndLastName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<User> findUsersByFirstAndLastName(String firstName, String lastName) {
		try {
			return userService.findUsersByFirstAndLastName(firstName, lastName);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findUserById(long)
	 */
	@Override
	@SuppressWarnings("deprecation")
	public User findUserById(long idUser) {
		org.bonitasoft.engine.identity.User usr = null;
		
		try {
			usr = userService.findUserById(idUser);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		User user = null;
		
		if(usr != null) {
			user = new User();
			user.setIdUser(usr.getId());
			user.setFirstname(usr.getFirstName());
			user.setLastname(usr.getLastName());
			user.setUserName(usr.getUserName());
			user.setNameManager(usr.getManagerUserName());
		}
		
		return user;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findUsersByGroup(long)
	 */
	@Override
	public List<User> findUsersByGroup(long idGroup) {
		try {
			return userService.findUserS(idGroup);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.gestor.acesso.ws.IntegracaoBonitaConsultas#findUsersByMembership(long, long)
	 */
	@Override
	public List<User> findUsersByMembership(long idGroup, long idRole) {
		try {
			return userService.findUsersByMembership(idGroup, idRole);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
