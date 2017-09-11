package br.com.seta.processo.service.interfaces;

import java.util.List;

import javax.ejb.Local;

import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.DashboardUserTasks;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;

/**
 * @author Eliel Sobral
 *
 */
@Local
public interface GestaoFilasService {

	/**
	 * Retorna uma lista de usuarios 
	 * 
	 * Se usuarioSelecionado for NULL será retornado TODOS OS USUARIOS
	 * Se atorSelecionado for NULL será retornado TODOS OS USUARIOS DE TODOS OS ATORES DO PROCESSO
	 * 
	 * @param usuarioLogado
	 * @param processoSelecionado
	 * @param atorSelecionado
	 * @param usuarioSelecionado
	 * @return
	 * @throws BonitaException
	 */
	public abstract List<User> listaTodosUsuariosPorProcesso(User usuarioLogado, TaskProcess processoSelecionado, Actor atorSelecionado, User usuarioSelecionado) throws BonitaException;

	
	/**
	 * @param usuarioLogado
	 * @param processoSelecionado
	 * @return
	 * @throws BonitaException
	 */
	public abstract List<Actor> listaTodosAtoresPorProcesso(User usuarioLogado, TaskProcess processoSelecionado) throws BonitaException;
	
	/**
	 * @param usuarioLogado
	 * @param processoSelecionado
	 * @param usuarios
	 * @return
	 * @throws BonitaException
	 */
	public abstract List<DashboardUserTasks> retornaTotalAtividadesPorUsuario(User usuarioLogado, TaskProcess processoSelecionado, List<User> usuarios) throws BonitaException;

	/**
	 * @param usuarioLogado
	 * @param atividadeSelecionada
	 * @throws BonitaException
	 */
	public abstract void atribuiAtividadePorUsuario(User usuarioLogado, User usuarioSelecionado, HumanTask atividadeSelecionada) throws BonitaException;

	/**
	 * @param usuarioLogado
	 * @throws BonitaException
	 */
	public abstract void cancelaAtividadePorUsuario(User usuarioLogado, HumanTask atividadeSelecionada) throws BonitaException;


}
