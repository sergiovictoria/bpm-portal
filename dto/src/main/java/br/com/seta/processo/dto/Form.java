package br.com.seta.processo.dto;

import java.io.Serializable;

public class Form implements Serializable{	

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String processDefinitionId;
	private String task;
	private String pageMappingKey;
	private String url;
	private TYPE type;

	public static enum TYPE {
		PROCESS_START, PROCESS_OVERVIEW, TASK;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getPageMappingKey() {
		return pageMappingKey;
	}

	public void setPageMappingKey(String pageMappingKey) {
		this.pageMappingKey = pageMappingKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}
	
	public static Form createNullObject(){
		return new Form();
	}

	@Override
	public String toString() {
		return "Form [id=" + id + ", processDefinitionId="
				+ processDefinitionId + ", task=" + task + ", pageMappingKey="
				+ pageMappingKey + ", url=" + url + ", type=" + type + "]";
	}

}
