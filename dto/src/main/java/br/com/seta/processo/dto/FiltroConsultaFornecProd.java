package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Filtro contendos os dados de consulta  do andamento dos cadastros de Fornecedor e Produto
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class FiltroConsultaFornecProd implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long caseId;
	private String operacao;
	private Date inicio;
	private Date fim;	
	private String identificador;
	
	public FiltroConsultaFornecProd(){
		
	}
	
	public FiltroConsultaFornecProd(Long caseId, String operacao, Date inicio,	Date fim) {
		super();
		this.caseId = caseId;
		this.operacao = operacao;
		this.inicio = inicio;
		this.fim = fim;
	}	
	
	public FiltroConsultaFornecProd(Long caseId, String operacao, Date inicio,	Date fim, String identificador) {
		super();
		this.caseId = caseId;
		this.operacao = operacao;
		this.inicio = inicio;
		this.fim = fim;
		this.identificador = identificador;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
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

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	
}
