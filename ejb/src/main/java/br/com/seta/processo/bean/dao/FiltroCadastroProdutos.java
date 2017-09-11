package br.com.seta.processo.bean.dao;

import java.io.Serializable;
import java.util.Date;

public class FiltroCadastroProdutos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long caseId;
	private String codFornecedor;
	private String eanUnidade;
	private String codProdutoC5;
	private String descricaoProduto;
	private String ncm;
	private String status;
	private Date inicio;
	private Date fim;

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getCodFornecedor() {
		return codFornecedor;
	}

	public void setCodFornecedor(String codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public String getEanUnidade() {
		return eanUnidade;
	}

	public void setEanUnidade(String eanUnidade) {
		this.eanUnidade = eanUnidade;
	}

	public String getCodProdutoC5() {
		return codProdutoC5;
	}

	public void setCodProdutoC5(String codProdutoC5) {
		this.codProdutoC5 = codProdutoC5;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
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

}
