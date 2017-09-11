package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String displayDescription; 
	private long processId;
	private String state;
	private String type;
	private long assigned_id;
	private java.util.Date assigned_date;
	private long id;
	private long executedBy;
	private long caseId;
	private String priority;
	private long actorId;
	private String description;
	private String name;
	private java.util.Date reached_state_date;
	private long rootCaseId;
	private String displayName;
	private java.util.Date dueDate;
	private java.util.Date last_update_date;
	
		
	public Usuario() {
	}
	
		
	public Usuario(String displayDescription, long processId, String state,
			String type, long assigned_id, Date assigned_date, long id,
			long executedBy, long caseId, String priority, long actorId,
			String description, String name, Date reached_state_date,
			long rootCaseId, String displayName, Date dueDate,
			Date last_update_date) {
		super();
		this.displayDescription = displayDescription;
		this.processId = processId;
		this.state = state;
		this.type = type;
		this.assigned_id = assigned_id;
		this.assigned_date = assigned_date;
		this.id = id;
		this.executedBy = executedBy;
		this.caseId = caseId;
		this.priority = priority;
		this.actorId = actorId;
		this.description = description;
		this.name = name;
		this.reached_state_date = reached_state_date;
		this.rootCaseId = rootCaseId;
		this.displayName = displayName;
		this.dueDate = dueDate;
		this.last_update_date = last_update_date;
	}



	public String getDisplayDescription() {
		return displayDescription;
	}
	public void setDisplayDescription(String displayDescription) {
		this.displayDescription = displayDescription;
	}
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(long processId) {
		this.processId = processId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getAssigned_id() {
		return assigned_id;
	}
	public void setAssigned_id(long assigned_id) {
		this.assigned_id = assigned_id;
	}
	public java.util.Date getAssigned_date() {
		return assigned_date;
	}
	public void setAssigned_date(java.util.Date assigned_date) {
		this.assigned_date = assigned_date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getExecutedBy() {
		return executedBy;
	}
	public void setExecutedBy(long executedBy) {
		this.executedBy = executedBy;
	}
	public long getCaseId() {
		return caseId;
	}
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public long getActorId() {
		return actorId;
	}
	public void setActorId(long actorId) {
		this.actorId = actorId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.util.Date getReached_state_date() {
		return reached_state_date;
	}
	public void setReached_state_date(java.util.Date reached_state_date) {
		this.reached_state_date = reached_state_date;
	}
	public long getRootCaseId() {
		return rootCaseId;
	}
	public void setRootCaseId(long rootCaseId) {
		this.rootCaseId = rootCaseId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public java.util.Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(java.util.Date dueDate) {
		this.dueDate = dueDate;
	}
	public java.util.Date getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(java.util.Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	
		
	
	@Override
	public String toString() {
		return "Usuario [displayDescription=" + displayDescription
				+ ", processId=" + processId + ", state=" + state + ", type="
				+ type + ", assigned_id=" + assigned_id + ", assigned_date="
				+ assigned_date + ", id=" + id + ", executedBy=" + executedBy
				+ ", caseId=" + caseId + ", priority=" + priority
				+ ", actorId=" + actorId + ", description=" + description
				+ ", name=" + name + ", reached_state_date="
				+ reached_state_date + ", rootCaseId=" + rootCaseId
				+ ", displayName=" + displayName + ", dueDate=" + dueDate
				+ ", last_update_date=" + last_update_date + "]";
	}
	
	
	
	
}
