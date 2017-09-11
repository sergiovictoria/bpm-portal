package br.com.seta.processo.dto;

import java.io.Serializable;

public class CentroFuncCusto implements Serializable {
	

	private static final long serialVersionUID = 1L;
    private long numcentro;
    private String nomeCentroCusto;
    private String CodigoCentroCusto;
    
    
    
	
	public CentroFuncCusto(String nomeCentroCusto, String codigoCentroCusto) {
		this.nomeCentroCusto = nomeCentroCusto;
		this.CodigoCentroCusto = codigoCentroCusto;
	}
	


	public CentroFuncCusto() {
	}
	
	
	public long getNumcentro() {
		return numcentro;
	}
	public void setNumcentro(long numcentro) {
		this.numcentro = numcentro;
	}
	
	
	public String getNomeCentroCusto() {
		return nomeCentroCusto;
	}
	public void setNomeCentroCusto(String nomeCentroCusto) {
		this.nomeCentroCusto = nomeCentroCusto;
	}



	public String getCodigoCentroCusto() {
		return CodigoCentroCusto;
	}

	public void setCodigoCentroCusto(String codigoCentroCusto) {
		CodigoCentroCusto = codigoCentroCusto;
	}


    
    




}
