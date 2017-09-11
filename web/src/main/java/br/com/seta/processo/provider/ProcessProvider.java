package br.com.seta.processo.provider;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.service.ExecuteRestAPI;

public class ProcessProvider implements  IDataProvider<TaskProcess> {	

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ExecuteRestAPI executeRestApi;
	
	public ProcessProvider(){
		CdiContainer.get().getNonContextualManager().inject(this);				
	}

	@Override
	public Iterator<? extends TaskProcess> iterator(long first, long count) {
		List<TaskProcess> processList;
		try {
			User user = (User) Session.get().getAttribute("user");	
			processList = executeRestApi.retriveProcessList(user, first, count);
		} catch (IOException | HttpStatusException e) {
			throw new RuntimeException(e);	
		}
		return processList.iterator();
	}

	@Override
	public long size() {
		int size;
		try {
			User user = (User) Session.get().getAttribute("user");	
			size = executeRestApi.retriveProcessListSize(user);
		} catch (IOException | HttpStatusException e) {
			throw new RuntimeException(e);			
		}
		return size;
	}

	@Override
	public IModel<TaskProcess> model(final TaskProcess taskProcess) {
		return new LoadableDetachableModel<TaskProcess>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected TaskProcess load() {
				return taskProcess;
			}
		};
	}

	@Override
	public void detach() {
	}
	
}
