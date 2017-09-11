package br.com.seta.processo.dto;

import java.io.Serializable;

public class TaskProcess implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String deploymentDate;
	private String description;
	private String activationState;
	private String name;
	private String deployedBy;
	private String displayName;
	private String actorinitiatorid;
	private String last_update_date;
	private String configurationState;
	private String version;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeploymentDate() {
		return deploymentDate;
	}
	public void setDeploymentDate(String deploymentDate) {
		this.deploymentDate = deploymentDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getActivationState() {
		return activationState;
	}
	public void setActivationState(String activationState) {
		this.activationState = activationState;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeployedBy() {
		return deployedBy;
	}
	public void setDeployedBy(String deployedBy) {
		this.deployedBy = deployedBy;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getActorinitiatorid() {
		return actorinitiatorid;
	}
	public void setActorinitiatorid(String actorinitiatorid) {
		this.actorinitiatorid = actorinitiatorid;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getConfigurationState() {
		return configurationState;
	}
	public void setConfigurationState(String configurationState) {
		this.configurationState = configurationState;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	@Override
	public String toString() {
		return "TaskProcess [id=" + id + ", deploymentDate=" + deploymentDate
				+ ", description=" + description + ", activationState="
				+ activationState + ", name=" + name + ", deployedBy="
				+ deployedBy + ", displayName=" + displayName
				+ ", actorinitiatorid=" + actorinitiatorid
				+ ", last_update_date=" + last_update_date
				+ ", configurationState=" + configurationState + ", version="
				+ version + "]";
	}
	
	

	
	

}
