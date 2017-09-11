package br.com.seta.processo.report;



/**
 * Enum para definir o tipo de report a ser gerado.
 * 
 * @author jairo.almires
 * @version 1.0.0
 * 
 */
public enum TypeReportEnum {
	
	/**
	 * Arquivo em formato PDF para ser imprimido.
	 */
	PDF(".pdf"),
	
	/**
	 * Arquivo em formado XLS, como padr�o microsoft Excel.
	 */
	XLS(".xls"),
	
	/**
	 * Arquivo em formato HTML.
	 */
	HTML(".html"),
	
	/**
	 * Arquivo em formato CSV.
	 */
	CSV(".csv"),
	
	/**
	 * Arquivo em formato CSV.
	 */
	EMAIL(""),
	
	TXT(".txt");
	
	private String tipo;
	
	private TypeReportEnum (String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
