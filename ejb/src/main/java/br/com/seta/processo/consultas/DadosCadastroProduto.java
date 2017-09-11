package br.com.seta.processo.consultas;

import java.io.Serializable;
import java.util.Date;

import br.com.seta.processo.dto.FormularioProduto;

/**
 * Mantém os dados utilizados na consulta de Cadastro de Produtos
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class DadosCadastroProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long caseId;
	private String status;
	private Date inicio;
	private Date fim;
	private FormularioProduto formularioProduto;

	public DadosCadastroProduto() {
	}
	
	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public FormularioProduto getFormularioProduto() {
		return formularioProduto;
	}

	public void setFormularioProduto(FormularioProduto formularioProduto) {
		this.formularioProduto = formularioProduto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
