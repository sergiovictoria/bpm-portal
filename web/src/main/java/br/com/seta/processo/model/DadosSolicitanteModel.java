package br.com.seta.processo.model;

import java.io.Serializable;

public class DadosSolicitanteModel implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private String tipoRequisicao;
	private String tipoRequisicaoSelected = "Produto";
	

	
	public String getTipoRequisicao() {
		return tipoRequisicao;
	}

	public void setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}
	

	public String getTipoRequisicaoSelected() {
		return tipoRequisicaoSelected;
	}
	
	
	
	

}
