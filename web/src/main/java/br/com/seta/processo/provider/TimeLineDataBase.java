package br.com.seta.processo.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.service.ExecuteRestAPI;


public class TimeLineDataBase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ExecuteRestAPI executeRestAPI; 
    private List<HumanTask> task;
		
	public TimeLineDataBase(List<HumanTask> task, java.util.Date dateStart, java.util.Date dateEnd) {
		CdiContainer.get().getNonContextualManager().inject(this);
//		this.task =  executeRestAPI.retriveTaskList((User) Session.get().getAttribute("user"), first, count, filter);
	    this.add(task,dateStart,dateEnd);
	    this.updateIndecies();
	}
	
	
	private List<HumanTask> taskDisplayNameAsc = Collections.synchronizedList(new ArrayList<HumanTask>());
	private List<HumanTask> taskDisplayNameDes = Collections.synchronizedList(new ArrayList<HumanTask>());
	private List<HumanTask> taskProcessIDAsc   = Collections.synchronizedList(new ArrayList<HumanTask>());
	private List<HumanTask> taskProcessIDDes   = Collections.synchronizedList(new ArrayList<HumanTask>());
	private List<HumanTask> taskStateAsc       = Collections.synchronizedList(new ArrayList<HumanTask>());
	private List<HumanTask> taskStateDes       = Collections.synchronizedList(new ArrayList<HumanTask>());
	private List<HumanTask> taskStartDateAsc   = Collections.synchronizedList(new ArrayList<HumanTask>());
	private List<HumanTask> taskStartDateDes   = Collections.synchronizedList(new ArrayList<HumanTask>());
		
	
		
	public List<HumanTask> getIndex(SortParam<?> sort) {

		if (sort == null) {
			return taskDisplayNameAsc;
		}
		
		if (sort.getProperty().equals("DisplayName"))  {
			return sort.isAscending() ? taskDisplayNameAsc : taskDisplayNameDes;
		}

		else if (sort.getProperty().equals("Process")) {
			return sort.isAscending() ? taskProcessIDAsc: taskProcessIDDes;
		}
		
		else if (sort.getProperty().equals("State")) {
			return sort.isAscending() ? taskStateAsc: taskStateDes;
		}
		
		else if (sort.getProperty().equals("Date")) {
			return sort.isAscending() ? taskStartDateAsc: taskStartDateDes;
		}

		throw new RuntimeException("Verificar não foi não existe indices [" + sort + "]");

	}
	
	
	public Iterator<HumanTask> find(long first, long count, SortParam<?> sort)  {
		return getIndex(sort).subList((int)first, (int)(first + count)).iterator();
	}
	
	public List<HumanTask> find(SortParam<?> sort)  {
		return getIndex(sort);
	}

	
	public long size() {
		return this.task.size();
	}
	
	
	private void add(List<HumanTask> task, java.util.Date dateStart, java.util.Date dateEnd) {
		
		for (HumanTask humanTask : task) {
			if ( (humanTask.getAssigned_date().after(dateStart)) &&  (humanTask.getAssigned_date().before(dateEnd)) ) {
				this.task.add(humanTask);
			}
		}
		
		taskDisplayNameAsc = this.task;
		taskDisplayNameDes = this.task;
		taskProcessIDAsc   = this.task;
		taskProcessIDDes   = this.task;
		taskStateAsc       = this.task;
		taskStateDes       = this.task;
		taskStartDateAsc   = this.task;
		taskStartDateDes   = this.task;
		
		
	}		
	
	
	private void updateIndecies() {
		Collections.sort(taskDisplayNameAsc, HumanTask.Comparators._DISPLAY_NAME);
		Collections.sort(taskDisplayNameDes, Collections.reverseOrder(HumanTask.Comparators._DISPLAY_NAME));
		Collections.sort(taskProcessIDAsc, HumanTask.Comparators._PROCESS_ID);
		Collections.sort(taskProcessIDDes, Collections.reverseOrder(HumanTask.Comparators._PROCESS_ID));
		Collections.sort(taskStateAsc, HumanTask.Comparators._STATE);
		Collections.sort(taskStateDes, Collections.reverseOrder(HumanTask.Comparators._STATE));
		Collections.sort(taskStartDateAsc, HumanTask.Comparators._STATE);
		Collections.sort(taskStartDateDes, Collections.reverseOrder(HumanTask.Comparators._STATE));
	}
	

}
