package br.com.seta.processo.service.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.apache.http.ParseException;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.MemberType;
import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.DashboardUserTasks;
import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.Membership;
import br.com.seta.processo.dto.Role;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.service.AtorService;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.service.MembershipService;
import br.com.seta.processo.service.RoleService;
import br.com.seta.processo.service.UserService;
import br.com.seta.processo.service.interfaces.GestaoFilasService;


@Stateless(name = "GestaoFilasService")
@Local(GestaoFilasService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})

public class GestaoFilasServiceImpl implements GestaoFilasService {

		
	@Inject	private AtorService atorService;
	@Inject	private UserService userService;
	@Inject	private RoleService roleService;
	@Inject	private GroupService groupService;
	@Inject	private MembershipService membershipService;
	@Inject	private ExecuteRestAPI restApi;

	@Override
	public List<User> listaTodosUsuariosPorProcesso(User usuarioLogado, 
													TaskProcess processoSelecionado, 
													Actor atorSelecionado, 
													User usuarioSelecionado) throws BonitaException {
		
		List<User> usuariosEncontrados = new ArrayList<User>();
		List<User> usuariosUnicos = new ArrayList<User>();
		
		User naoAtribuido = geraUsuarioNaoAtribuidoParaCombo();
		usuariosEncontrados.add(naoAtribuido);
		
		//- Retorna a Lista de Todos os Usuarios de Todos os Atores
		if(null == atorSelecionado && null == usuarioSelecionado && null != processoSelecionado) {
			List<Actor> listaAtores = atorService.listaAtores(usuarioLogado, processoSelecionado);
			for (Actor actor : listaAtores) {
				//- Recupera os usuarios por Ator e Processo
				retornaListaUsuarios(usuarioLogado, processoSelecionado, usuariosEncontrados, actor);
			}			
		}
		
		//- Retorna a Lista de Todos os Usuarios, filtrados por um Ator especifico
		if(null == usuarioSelecionado && null != atorSelecionado & null != processoSelecionado) {
			retornaListaUsuarios(usuarioLogado, processoSelecionado, usuariosEncontrados, atorSelecionado);
		}
		
		//- Retorna a Lista de Todos os Usuarios, filtrados por um Usuario especifico
		if(null != usuarioSelecionado) {
			List<User> listaUsuarios = new ArrayList<>();
			List<Actor> listaAtores = atorService.listaAtores(usuarioLogado, processoSelecionado);
			if (null == atorSelecionado) {
				atorSelecionado = listaAtores.get(0);
			}
			retornaListaUsuarios(usuarioLogado, processoSelecionado, listaUsuarios, atorSelecionado);
			usuariosEncontrados.addAll(listaUsuarios);
			for (User user : listaUsuarios) {
				if (user.getId() != usuarioSelecionado.getId()) {
					usuariosEncontrados.remove(usuariosEncontrados.indexOf(user));
				}
			}
		} 

		Set<User> set = new HashSet<User>(usuariosEncontrados);
		for (User user : set) {
			usuariosUnicos.add(user);
		}
		Collections.sort(usuariosUnicos);

		return usuariosUnicos;
	}

	@Override
	public List<DashboardUserTasks> retornaTotalAtividadesPorUsuario(User usuarioLogado, TaskProcess processoSelecionado, List<User> usuarios) {

		List<DashboardUserTasks> atividadesUsuarios = new ArrayList<DashboardUserTasks>();
		
		for (User usuario : usuarios) {
			try {
				List<HumanTask> taskList = restApi.retriveTaskList(usuarioLogado, usuario, processoSelecionado);
				DashboardUserTasks atividadesUsuario = new DashboardUserTasks();
				atividadesUsuario.setTasks(taskList);
				atividadesUsuario.setUser(usuario);
				atividadesUsuarios.add(atividadesUsuario);
			} catch (ParseException | IOException | HttpStatusException e) {
				e.printStackTrace();
			}
		}
		return atividadesUsuarios;
	}

	@Override
	public List<Actor> listaTodosAtoresPorProcesso(User usuarioLogado, TaskProcess processoSelecionado) throws BonitaException {
		return atorService.listaAtores(usuarioLogado, processoSelecionado);
	}

	@Override
	public void atribuiAtividadePorUsuario(User usuarioLogado, User usuarioSelecionado, HumanTask atividadeSelecionada) throws BonitaException {
		restApi.executeAssignTaskUpdateDueDate(usuarioLogado, usuarioSelecionado, atividadeSelecionada, new Date());
	}

	@Override
	public void cancelaAtividadePorUsuario(User usuarioLogado, HumanTask atividadeSelecionada) throws BonitaException {
		restApi.executeUnassignTask(usuarioLogado, null, atividadeSelecionada);
	}
	
	private void retornaListaUsuarios(User usuarioLogado, TaskProcess processoSelecionado, List<User> usuariosEncontrados, Actor atorSelecionado) throws BonitaException {
		List<User> users = userService.listaUsuariosPorAtor(usuarioLogado, processoSelecionado, atorSelecionado, MemberType.USER);
		List<Role> roles = roleService.listaRolesPorAtor(usuarioLogado, processoSelecionado, atorSelecionado, MemberType.ROLE);
		List<Group> groups = groupService.listaGruposPorAtor(usuarioLogado, processoSelecionado, atorSelecionado,	MemberType.GROUP);
		List<Membership> memberships = membershipService.listaMembershipsPorAtor(usuarioLogado, processoSelecionado, atorSelecionado, MemberType.MEMBERSHIP);
		// - Roles
		if (!roles.isEmpty()) {
			for (Role role : roles) {
				List<User> usersRole = roleService.listaUsuariosPorRole(usuarioLogado, role);
				usuariosEncontrados.addAll(usersRole);
			}
		}
		// - Groups
		if (!groups.isEmpty()) {
			for (Group group : groups) {
				List<User> usersGroup = groupService.listaUsuariosPorGrupo(usuarioLogado, group);
				usuariosEncontrados.addAll(usersGroup);
			}
		}
		// - Memberships
		if (!memberships.isEmpty()) {
			for (Membership membership : memberships) {
				List<User> usersMemberships = membershipService.listaUsuariosPorMemberships(usuarioLogado, membership);
				usuariosEncontrados.addAll(usersMemberships);
			}
		}
		usuariosEncontrados.addAll(users);
	}
	
	/**
	 * @return
	 */
	private User geraUsuarioNaoAtribuidoParaCombo() {
		User naoAtribuido = new User();
		naoAtribuido.setId(0L);
		naoAtribuido.setFirstname("Não Atribuido");
		naoAtribuido.setLastname("");
		return naoAtribuido;
	}

}
