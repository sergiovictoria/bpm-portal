package br.com.seta.processo.model;

import static br.com.seta.processo.utils.BonitaPriorityConstants.ABOVE_NORMAL_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.HIGHEST_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.LOWEST_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.NORMAL_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.UNDER_NORMAL_PRIORITY;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.RootContainer;
import br.com.seta.processo.dto.HumanTask;

public class TaskModel implements IClusterable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private HumanTask task;
	
	public TaskModel(){
		this.task = createNullObject();
	}

	public HumanTask getTask() {
		return task;
	}

	public void setTask(HumanTask task) {
		this.task = task;
	}
	
	public String getDisplayName(){
		return task.getDisplayName();
	}
	
	public String getRootContainerId(){
		return task.getRootCaseId();
	}
	
	public String getRootContainer(){
		return task.getRootContainerId().getDisplayName();
	}
	
	public Date getDueDate(){
		return task.getDueDate();
	}
	
	public String getPriority(){
		return getPrioridade(task.getPriority());
	}
	
	public String getDisplayDescription(){
		return task.getDisplayDescription();
	}
	
	private HumanTask createNullObject(){
		HumanTask nullObject = new HumanTask();		
		nullObject.setRootContainerId(new RootContainer());
		nullObject.setActorId(new Actor());
		
		return nullObject;
	}
	
	private String getPrioridade (String priority){
		if (priority==null) {
			return "Sem Prioridade";
		}
		switch (priority) {
			case LOWEST_PRIORITY:
				return "Baixíssimo";
			case UNDER_NORMAL_PRIORITY:
				return "Baixo";
			case NORMAL_PRIORITY:
				return "Normal";
			case ABOVE_NORMAL_PRIORITY:
				return "Acima do Normal";
			case HIGHEST_PRIORITY:
				return "Altíssimo";
			default:
				return "";
		}
	}

	@Override
	public String toString() {
		return "TaskModel [task=" + task + "]";
	}
	
	
}
