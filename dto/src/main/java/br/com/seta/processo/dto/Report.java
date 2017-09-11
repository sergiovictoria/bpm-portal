package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.List;



public class Report implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private List<?> cols; 
	private Class<?> myClass; 
	private String typeDoc; 
	private String fileName; 
	private String title; 
	private String subTitle; 
	private String typePage;
	private Object object;
	private Boolean isStatus;
	private String mime;
	private String fileNameType;
	private byte[] contextFile;

	
		
	public List<?> getCols() {
		return cols;
	}
	public void setCols(List<?> cols) {
		this.cols = cols;
	}
	public Class<?> getMyClass() {
		return myClass;
	}
	public void setMyClass(Class<?> myClass) {
		this.myClass = myClass;
	}
	public String getTypeDoc() {
		return typeDoc;
	}
	public void setTypeDoc(String typeDoc) {
		this.typeDoc = typeDoc;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getTypePage() {
		return typePage;
	}
	public void setTypePage(String typePage) {
		this.typePage = typePage;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Boolean getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(Boolean isStatus) {
		this.isStatus = isStatus;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public String getFileNameType() {
		return fileNameType;
	}
	public void setFileNameType(String fileNameType) {
		this.fileNameType = fileNameType;
	}
	public byte[] getContextFile() {
		return contextFile;
	}
	public void setContextFile(byte[] contextFile) {
		this.contextFile = contextFile;
	}
	

}
