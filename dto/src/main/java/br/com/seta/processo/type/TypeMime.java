package br.com.seta.processo.type;

public enum TypeMime {
	
	
	pdf(1,"application/pdf"),
	csv(2,"application/csv"),
	doc(3,"application/doc"),
	xls(4,"application/xls"),
	xml(5,"application/xml"),
	html(6,"application/html");
	
		
	private Integer idFile;
	private String typeFile;
	

	private TypeMime(Integer idFile, String typeFile) {
		this.idFile = idFile;
		this.typeFile = typeFile;
	}
	
	public Integer getIdFile() {
		return idFile;
	}
	public void setIdFile(Integer idFile) {
		this.idFile = idFile;
	}
	public String getTypeFile() {
		return typeFile;
	}
	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}
	
	

}
