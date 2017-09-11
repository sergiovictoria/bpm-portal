package br.com.seta.processo.service.interfaces;

import java.util.List;

import javax.ejb.Local;

import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;

@Local
public interface DashboardService {
	
	public abstract String dashboardGestaoFilas(String divId, User usuarioLogado, TaskProcess processoSelecionado, List<User> usersBPM) throws BonitaException;

	public abstract Object dashboardGestaoFilasPorAtor(User usuarioLogado, TaskProcess processoSelecionado, Actor atorSelecionado) throws BonitaException;
	
	public abstract Object dashboardGestaoFilasPorUsuario(User usuarioLogado, TaskProcess processoSelecionado, Actor atorSelecionado, User usuarioSelecionado) throws BonitaException;
	
}
