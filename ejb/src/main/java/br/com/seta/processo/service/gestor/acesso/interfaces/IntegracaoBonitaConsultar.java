package br.com.seta.processo.service.gestor.acesso.interfaces;

import java.util.List;

import javax.ejb.Remote;

import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.User;

@Remote
public interface IntegracaoBonitaConsultar {

	/**
	 * Busco a primeira ocorrencia de um grupo que possua o nome passado.
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param name Nome do grupo
	 * @return
	 */
	public abstract Group getFirstGroupByName(String name);

	/**
	 * Busco todos os grupos do Bonita.
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @return
	 */
	public abstract List<Group> findAllGroups();

	/**
	 * Busco os grupos por nome.
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro 
	 * @param name
	 * @return
	 */
	public abstract List<Group> findAllGroupsByName(String name);

	/**
	 * Busco os grupo que possuem como grupo superior o nome passado 
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param parent nome do grupo superior
	 * @return
	 */
	public abstract List<Group> findAllGroupsByParent(String parent);

	/**
	 * Retorna o grupo com o id correspondente
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param id do grupo
	 * @return
	 */
	public abstract Group findGroupById(long id);

	/**
	 * Busco a primeira ocorrencia de uma role que possua o nome passado.
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param name Nome da role
	 * @return
	 */
	public abstract Role findRoleByName(String name);

	/**
	 * Retorna todas as rules do Bonita
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @return
	 */
	public abstract List<Role> findAllRoles();

	/**
	 * Retorna um LIST contendo todas as rules do Bonita que possuem o nome passado como parametro
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param name
	 * @return
	 */
	public abstract List<Role> findAllRolesByName(String name);

	/**
	 * Retorna uma lista com todos os usuários do Bonita
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @return
	 */
	public abstract List<User> findAllUsers();

	/**
	 * Retorna uma lista preenchida com o resultado da pesquisa por nome
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param firstName
	 * @return
	 */
	public abstract List<User> findUsersByName(String firstName);

	/**
	 * Retorna uma lista preenchida com o resultado da pesquisa por nome e ultimo nome
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public abstract List<User> findUsersByFirstAndLastName(String firstName,
			String lastName);

	/**
	 * Busca o usuario pelo id
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param idUser
	 * @return
	 */
	public abstract User findUserById(long idUser);

	/**
	 * Retorna uma lista de usuários que estiverem no grupo que foi passado por parametro
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param idGroup
	 * @return
	 */
	public abstract List<User> findUsersByGroup(long idGroup);

	/**
	 * Retorna todos os usuários que estiverem associados ao grupo e a role.
	 * <br/>
	 * Group - Comercial
	 * <br/>
	 * Role - Funcionario
	 * <br/>
	 * É retornado <b>null</b> caso são seja encontrado nenhum registro
	 * @param idGroup
	 * @param idRole
	 * @return
	 */
	public abstract List<User> findUsersByMembership(long idGroup, long idRole);

}