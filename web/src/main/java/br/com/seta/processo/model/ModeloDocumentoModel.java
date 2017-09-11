package br.com.seta.processo.model;

import java.io.Serializable;

import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.dto.ModeloDocumento;

public class ModeloDocumentoModel implements Serializable, IClusterable {

	private static final long serialVersionUID = 1L;

	private ModeloDocumento modeloDocumento;
	private String descricao;

	public ModeloDocumento getModeloDocumento() {
		return modeloDocumento;
	}

	public void setModeloDocumento(ModeloDocumento modeloDocumento) {
		this.modeloDocumento = modeloDocumento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
