package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;

public class Case implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private long sourceObjectId; //the id of the case before it was archived
	private Date start;
	private User started_by;
	private TaskProcess processDefinitionId;
	private Date archivedDate; //the date set when the case was archived

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public User getStarted_by() {
		return started_by;
	}

	public void setStarted_by(User started_by) {
		this.started_by = started_by;
	}

	public TaskProcess getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(TaskProcess processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public long getSourceObjectId() {
		return sourceObjectId;
	}

	public void setSourceObjectId(long sourceObjectId) {
		this.sourceObjectId = sourceObjectId;
	}

	public Date getArchivedDate() {
		return archivedDate;
	}

	public void setArchivedDate(Date archivedDate) {
		this.archivedDate = archivedDate;
	}

	@Override
	public String toString() {
		return "Case [id=" + id + ", sourceObjectId=" + sourceObjectId
				+ ", start=" + start + ", started_by=" + started_by
				+ ", processDefinitionId=" + processDefinitionId
				+ ", archivedDate=" + archivedDate + "]";
	}
}
