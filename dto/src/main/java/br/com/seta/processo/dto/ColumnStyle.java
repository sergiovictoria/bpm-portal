package br.com.seta.processo.dto;

import java.io.Serializable;

public class ColumnStyle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String title;
    private String field;
    private String dataType;

    
    public ColumnStyle(String title, String field, String dataType) {
       this.title = title;
       this.field = field;
       this.dataType = dataType;
    }

    

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
    
    
	

}
