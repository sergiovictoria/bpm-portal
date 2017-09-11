package br.com.seta.processo.componentes.messagebox;

public enum MessageBoxTipo {
	ALERT ("modal-block modal-header-color modal-block-warning mfp-hide", "fa fa-warning"),
	INFO ("modal-block modal-header-color modal-block-primary mfp-hide", "fa fa-info-circle"),
	ERROR ("modal-block modal-header-color modal-block-danger mfp-hide", "fa fa-times-circle"),
	SUCCESS ("modal-block modal-header-color modal-block-success mfp-hide", "fa fa-check");
	
	private String cssClass;
	private String icon;

	private MessageBoxTipo(String cssClass, String icon) {
		this.cssClass = cssClass;
		this.icon = icon;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
