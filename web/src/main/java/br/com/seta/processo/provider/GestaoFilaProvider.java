package br.com.seta.processo.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.management.RuntimeErrorException;

import org.apache.http.ParseException;
import org.apache.wicket.Session;
import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.gestaofilas.GestaoFilas;
import br.com.seta.processo.service.ExecuteRestAPI;

public class GestaoFilaProvider extends SortableDataProvider<HumanTask, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ExecuteRestAPI restApi;

	private String filter = "";
	private List<HumanTask> taskList = new ArrayList<HumanTask>();

	private transient User usuarioLogado;
	private TaskProcess processoSelecionado;
	private Actor atorSelecionado;
	private User usuarioSelecionado;
	private List<User> usuarios;
	
	public GestaoFilaProvider(TaskProcess processoSelecionado, Actor atorSelecionado, User usuarioSelecionado) throws HttpStatus401Exception, ParseException, IOException, HttpStatusException {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.usuarioLogado = (User) Session.get().getAttribute("user");
		this.processoSelecionado = processoSelecionado;
		this.atorSelecionado = atorSelecionado;
		this.usuarioSelecionado = usuarioSelecionado;
	}

	@Override
	public Iterator<? extends HumanTask> iterator(long first, long count) {
		this.taskList.clear();
		List<HumanTask> tasksEncontradas = new ArrayList<HumanTask>();
		try {
			List<HumanTask> tasks = restApi.retriveTaskList(usuarioLogado, usuarioSelecionado, processoSelecionado, atorSelecionado, first, GestaoFilas.QUANT_ATIVIDADES_POR_PAGINA, filter);
			tasksEncontradas.addAll(tasks);
			
			if (usuarioSelecionado != null) {
				for (HumanTask humanTask : tasks) {
					if (humanTask.getAssigned_id() != null && usuarioSelecionado.getId() != humanTask.getAssigned_id().getId()) {
						tasksEncontradas.remove(tasksEncontradas.indexOf(humanTask));
					}
				}
			}
			
		} catch (ParseException | IOException | HttpStatusException e) {
			throw new RuntimeException(e);
		}
		
		Set<HumanTask> set = new HashSet<HumanTask>(tasksEncontradas);
		this.taskList.addAll(set);
		
		return taskList.iterator();
	}

	@Override
	public long size() {
		try {
			List<HumanTask> tasks = restApi.retriveTaskList(this.usuarioLogado, this.usuarioSelecionado, this.processoSelecionado, this.atorSelecionado, 0, 100000, filter);
			return tasks.size();
		} catch (ParseException | IOException | HttpStatusException e) {
			throw new RuntimeErrorException(new Error(e));
		}
	}

	@Override
	public IModel<HumanTask> model(final HumanTask task) {
		return new LoadableDetachableModel<HumanTask>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected HumanTask load() {
				return task;
			}
		};
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<HumanTask> getTaskList() {
		return taskList;
	}

	public final TaskProcess getProcessoSelecionado() {
		return processoSelecionado;
	}

	public final void setProcessoSelecionado(TaskProcess processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public final Actor getAtorSelecionado() {
		return atorSelecionado;
	}

	public final void setAtorSelecionado(Actor atorSelecionado) {
		this.atorSelecionado = atorSelecionado;
	}

	public final List<User> getUsuarios() {
		return usuarios;
	}

	public final void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}

	public User getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(User usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
