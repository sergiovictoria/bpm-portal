package br.com.seta.processo.type;

public enum TypePage {
	
	LANDSCAPE(1,"LANDSCAPE"),
	PORTRAIT(2,"PORTRAIT");
			
	private TypePage(Integer idPage, String typePage) {
		this.idPage = idPage;
		this.typePage = typePage;
	}
	
	
	public Integer getIdPage() {
		return idPage;
	}
	public void setIdPage(Integer idPage) {
		this.idPage = idPage;
	}
	
	
	public String getTypePage() {
		return typePage;
	}
	public void setTypePage(String typePage) {
		this.typePage = typePage;
	}

	private Integer idPage;
	private String typePage;

	
}
