package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;


@Entity("ProcessCase")
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))

public class ProcessCase implements Serializable {
		

	private static final long serialVersionUID = 1L;
	@Id
	private ObjectId id = new ObjectId();
	private Integer caseId;
	private java.util.Date date;
	private String processName;
	private Class<?> clazz;
	
		
	@Embedded
	private Object object;
	
	@Embedded
	private List<OrIndicador> indicadors = new ArrayList<OrIndicador>();
	
	@Embedded
	private List<Object> objects = new ArrayList<Object>();
	
	
	@Embedded	
	private Status status = new Status();


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	public Integer getCaseId() {
		return caseId;
	}


	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}


	public java.util.Date getDate() {
		return date;
	}


	public void setDate(java.util.Date date) {
		this.date = date;
	}


	public String getProcessName() {
		return processName;
	}


	public void setProcessName(String processName) {
		this.processName = processName;
	}


	public Class<?> getClazz() {
		return clazz;
	}


	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}


	public Object getObject() {
		return object;
	}


	public void setObject(Object object) {
		this.object = object;
	}


	public List<OrIndicador> getIndicadors() {
		return indicadors;
	}


	public void setIndicadors(List<OrIndicador> indicadors) {
		this.indicadors = indicadors;
	}


	public List<Object> getObjects() {
		return objects;
	}


	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}

	
}
