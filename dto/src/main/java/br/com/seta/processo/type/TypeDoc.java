package br.com.seta.processo.type;

public enum TypeDoc {
	
	pdf(1,"pdf"),
	csv(2,"csv"),
	doc(3,"doc"),
	xls(4,"xls"),
	xml(5,"xml"),
	html(6,"html");
	
	
	private Integer idFile;
	private String typeFile;

	private TypeDoc(Integer idFile, String typeFile) {
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
