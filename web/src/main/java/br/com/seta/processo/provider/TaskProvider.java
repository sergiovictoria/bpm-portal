package br.com.seta.processo.provider;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.management.RuntimeErrorException;

import org.apache.http.ParseException;
import org.apache.wicket.Session;
import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.service.ExecuteRestAPI;

/**
 * Provedor de dados para as tarefas (tasks) do usuário 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class TaskProvider extends SortableDataProvider<HumanTask, String> {
	private static final long serialVersionUID = 1L;

	@Inject
	private ExecuteRestAPI restApi; 
    private transient User user;
    private String filter = "";
    private List<HumanTask> taskList;
    
    public TaskProvider() throws HttpStatus401Exception, ParseException, IOException, HttpStatusException{
		CdiContainer.get().getNonContextualManager().inject(this);
		this.user = (User) Session.get().getAttribute("user");
	}

	@Override
	public Iterator<? extends HumanTask> iterator(long first, long count) {
		
		try {
			taskList = restApi.retriveTaskList(user, first, Atividades.QUANT_ATIVIDADES_POR_PAGINA, filter);
			return taskList.iterator();
		} catch (ParseException | IOException | HttpStatusException e) {
			throw new RuntimeErrorException(new Error(e));
		}	
	}

	@Override
	public long size() {
		long size;
		try {
			size = restApi.retriveTaskListSize(user, filter);
		} catch (IOException | HttpStatusException e) {			
			throw new RuntimeErrorException(new Error(e));
		}
		return size;
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
	
	public void setFilter(String filter){
		this.filter = filter;
	}
	
	public List<HumanTask> getTaskList(){
		return taskList;
	}
}
