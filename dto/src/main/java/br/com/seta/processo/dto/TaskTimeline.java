package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.List;

public class TaskTimeline implements Serializable {	

	private static final long serialVersionUID = 1L;
	
	private Case cs;
	private List<HumanTask> humanTasks;
	
	public TaskTimeline(Case cs, List<HumanTask> tasks){
		this.cs = cs;
		this.humanTasks = tasks;
	}

	public Case getCase() {
		return cs;
	}

	public void setCase(Case cs) {
		this.cs = cs;
	}

	public List<HumanTask> getHumanTasks() {
		return humanTasks;
	}

	public void setHumanTasks(List<HumanTask> humanTasks) {
		this.humanTasks = humanTasks;
	}

	@Override
	public String toString() {
		return "TaskTimeline [cs=" + cs + ", humanTasks=" + humanTasks + "]";
	}
}
