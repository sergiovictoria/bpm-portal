package br.com.seta.processo.consultas;

import java.io.Serializable;
import java.util.Date;

import br.com.seta.processo.dto.FormularioFornecedor;

public class DadosCadFornecedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long caseId;
	private Date inicio;
	private Date fim;
	private String status;
	private FormularioFornecedor formularioFornecedor;
	
	public DadosCadFornecedor(){
		
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FormularioFornecedor getFormularioFornecedor() {
		return formularioFornecedor;
	}

	public void setFormularioFornecedor(FormularioFornecedor formularioFornecedor) {
		this.formularioFornecedor = formularioFornecedor;
	}	

}
