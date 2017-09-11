package br.com.seta.processo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.bean.Compradores;
import br.com.seta.processo.dto.Comprador;

public class CompradorModel implements IClusterable, Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Compradores compradorService;
	
	
	private List<Comprador> compradores = new ArrayList<Comprador>(0);
	private Comprador comprador;
	
	
	
	public CompradorModel(){
		CdiContainer.get().getNonContextualManager().inject(this);
		this.compradores = this.compradorService.getCompradoreS();
		this.comprador = this.compradores.get(0);
	}



	public List<Comprador> getCompradores() {
		return compradores;
	}



	public void setCompradores(List<Comprador> compradores) {
		this.compradores = compradores;
	}



	public Comprador getComprador() {
		return comprador;
	}



	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	
	
	


}
