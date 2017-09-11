package br.com.seta.processo.componentes.messagebox;

public enum MessageBoxLoadingGif {
	DEFAULT ("resources/img/loading/477.GIF"),
	CIRCLE ("resources/img/loading/712.GIF"),
	PROGRESS ("resources/img/loading/76.GIF"),
	PROGRESS2 ("resources/img/loading/294.GIF");;
	
	private String value;

	private MessageBoxLoadingGif(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
