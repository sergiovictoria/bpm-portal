package br.com.seta.processo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.bean.Empresas;
import br.com.seta.processo.dto.EmpresaMatriz;

public class EmpresaModel implements IClusterable, Serializable {


	private static final long serialVersionUID = 1L;
	private BigDecimal nroempresa;
	private String razaosocial;
	private List<EmpresaMatriz> empresaMatrizs = new ArrayList<EmpresaMatriz>(0);
	private EmpresaMatriz empresaMatriz;
	private String fantasia;
	private java.util.Date DtaCompra;
	private java.util.Date DtaInclusao;


	@Inject private Empresas empresaService; 



	
	public EmpresaModel() {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.empresaMatrizs = empresaService.getEmpresaMatriz();
		this.empresaMatriz =  this.empresaMatrizs.get(0);
		this.nroempresa = this.empresaMatriz.getNroempresa();
		this.fantasia = this.empresaMatriz.getFantasia();
	}



	public BigDecimal getNroempresa() {
		return nroempresa;
	}


	public void setNroempresa(BigDecimal nroempresa) {
		this.nroempresa = nroempresa;
	}


	public String getRazaosocial() {
		return razaosocial;
	}


	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}


	public List<EmpresaMatriz> getEmpresaMatrizs() {
		return empresaMatrizs;
	}


	public void setEmpresaMatrizs(List<EmpresaMatriz> empresaMatrizs) {
		this.empresaMatrizs = empresaMatrizs;
	}


	public EmpresaMatriz getEmpresaMatriz() {
		return empresaMatriz;
	}


	public void setEmpresaMatriz(EmpresaMatriz empresaMatriz) {
		this.empresaMatriz = empresaMatriz;
	}



	public String getFantasia() {
		return fantasia;
	}



	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}



	public Empresas getEmpresaService() {
		return empresaService;
	}



	public void setEmpresaService(Empresas empresaService) {
		this.empresaService = empresaService;
	}



	/**
	 * @return the dtaCompra
	 */
	public java.util.Date getDtaCompra() {
		return DtaCompra;
	}



	/**
	 * @param dtaCompra the dtaCompra to set
	 */
	public void setDtaCompra(java.util.Date dtaCompra) {
		DtaCompra = dtaCompra;
	}



	/**
	 * @return the dtaInclusao
	 */
	public java.util.Date getDtaInclusao() {
		return DtaInclusao;
	}



	/**
	 * @param dtaInclusao the dtaInclusao to set
	 */
	public void setDtaInclusao(java.util.Date dtaInclusao) {
		DtaInclusao = dtaInclusao;
	}






}
