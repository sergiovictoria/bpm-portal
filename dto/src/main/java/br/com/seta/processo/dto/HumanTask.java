package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class HumanTask implements Serializable,  Comparable<HumanTask> {

	private static final long serialVersionUID = 1L;
	
	private String displayDescription;
	private String executedBySubstitute;
	private long processId;
	private long parentCaseId;
	private String state;
	private RootContainer rootContainerId;
	private String type;
	private User assigned_id;
	private Date assigned_date;
	private long id;
	private User executedBy;
	private long caseId;
	private String priority;
	private Actor actorId;
	private String description;
	private String name;
	private Date reached_state_date;
	private String rootCaseId;
	private String displayName;
	private Date dueDate;
	private Date last_update_date;
	private Date archivedDate;
	
	private boolean selecionado = false;

	public String getDisplayDescription() {
		return displayDescription;
	}

	public void setDisplayDescription(String displayDescription) {
		this.displayDescription = displayDescription;
	}

	public String getExecutedBySubstitute() {
		return executedBySubstitute;
	}

	public void setExecutedBySubstitute(String executedBySubstitute) {
		this.executedBySubstitute = executedBySubstitute;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public long getParentCaseId() {
		return parentCaseId;
	}

	public void setParentCaseId(long parentCaseId) {
		this.parentCaseId = parentCaseId;
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

	public Date getAssigned_date() {
		return assigned_date;
	}

	public void setAssigned_date(Date assigned_date) {
		this.assigned_date = assigned_date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getReached_state_date() {
		return reached_state_date;
	}

	public void setReached_state_date(Date reached_state_date) {
		this.reached_state_date = reached_state_date;
	}

	public String getRootCaseId() {
		return rootCaseId;
	}

	public void setRootCaseId(String rootCaseId) {
		this.rootCaseId = rootCaseId;
	}

	public RootContainer getRootContainerId() {
		return rootContainerId;
	}

	public void setRootContainerId(RootContainer rootContainerId) {
		this.rootContainerId = rootContainerId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}

	public Actor getActorId() {
		return actorId;
	}

	public void setActorId(Actor actorId) {
		this.actorId = actorId;
	}
	

	public User getAssigned_id() {
		return assigned_id;
	}

	public void setAssigned_id(User assigned_id) {
		this.assigned_id = assigned_id;
	}

	public User getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(User executedBy) {
		this.executedBy = executedBy;
	}	

	public Date getArchivedDate() {
		return archivedDate;
	}

	public void setArchivedDate(Date archivedDate) {
		this.archivedDate = archivedDate;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	@Override
	public String toString() {
		return "HumanTask [displayDescription=" + displayDescription
				+ ", executedBySubstitute=" + executedBySubstitute
				+ ", processId=" + processId + ", parentCaseId=" + parentCaseId
				+ ", state=" + state + ", rootContainerId=" + rootContainerId
				+ ", type=" + type + ", assigned_id=" + assigned_id
				+ ", assigned_date=" + assigned_date + ", id=" + id
				+ ", executedBy=" + executedBy + ", caseId=" + caseId
				+ ", priority=" + priority + ", actorId=" + actorId
				+ ", description=" + description + ", name=" + name
				+ ", reached_state_date=" + reached_state_date
				+ ", rootCaseId=" + rootCaseId + ", displayName=" + displayName
				+ ", dueDate=" + dueDate + ", last_update_date="
				+ last_update_date + "]";
	}

	@Override
	public int compareTo(HumanTask o) {
		return Comparators._DISPLAY_NAME.compare(this, o);
	}

	
	public static class Comparators {
		
		public static Comparator<HumanTask> _DISPLAY_NAME = new Comparator<HumanTask>() {
			@Override
			public int compare(HumanTask o1, HumanTask o2) {
				return o1.getDisplayName().compareTo(o2.getDisplayName());
			}
		};
		
		public static Comparator<HumanTask> _PROCESS_ID = new Comparator<HumanTask>() {
			@Override
			public int compare(HumanTask o1, HumanTask o2) {
				return ((Long)o1.getProcessId()).compareTo((Long)o2.getProcessId());
			}
		};
				
		public static Comparator<HumanTask> _STATE = new Comparator<HumanTask>() {
			@Override
			public int compare(HumanTask o1, HumanTask o2) {
				return (o1.getState()).compareTo(o2.getState());
			}
		};
		
		
		public static Comparator<HumanTask> _START_DATE = new Comparator<HumanTask>() {
			@Override
			public int compare(HumanTask o1, HumanTask o2) {
				return (o1.getReached_state_date()).compareTo(o2.getReached_state_date());
			}
		};
		
		/**
		 * Compara duas Human Task pela data em que alcançou seu estado atual em ordem decrescente
		 */
		public static Comparator<HumanTask> REACHED_STATE_DATE_DESC = new Comparator<HumanTask>(){
			@Override
			public int compare(HumanTask ht1, HumanTask ht2) {
				if(ht1.getReached_state_date() == null) return -1;
				if(ht2.getReached_state_date() == null) return 1;
				
				return  ht1.getReached_state_date().compareTo(ht2.getReached_state_date()) * -1; //ordem reversa
			}
		};
		
		/**
		 * Compara duas Human Task pela data de arquivamento em ordem decrescente
		 */
		public static Comparator<HumanTask> ARCHIVE_DATE_DESC  = new Comparator<HumanTask>() {

			@Override
			public int compare(HumanTask ht1, HumanTask ht2) {
				if(ht1.getArchivedDate() == null) return -1;
				if(ht2.getArchivedDate() == null) return 1;
				
				return  ht1.getReached_state_date().compareTo(ht2.getReached_state_date()) * -1; //ordem reversa
			}
		};
		
		/**
		 * Compara duas Human Task pela data de atribuição em ordem decrescente
		 */
		public static Comparator<HumanTask>	ASSIGNED_DATE_DESC = new Comparator<HumanTask>(){

			@Override
			public int compare(HumanTask ht1, HumanTask ht2) {
				if(ht1.getAssigned_date () == null) return -1;
				if(ht2.getAssigned_date() == null) return 1;
				
				return ht1.getAssigned_date().compareTo(ht2.getAssigned_date()) * -1; //ordem reversa
			}			
		};
		
	}	
	
	
	

}
