package br.com.seta.processo.model;

import java.io.Serializable;

import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.dto.Transportadora;

public class TransportadoraModel implements IClusterable, Serializable {

	private static final long serialVersionUID = 1L;

	private Transportadora transportadora;
	private String razaoSocial;

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
