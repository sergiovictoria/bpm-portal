package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigInteger;

import br.com.seta.processo.annotations.ReportColumn;
import br.com.seta.processo.classTypes.Classes;


public class Indicador implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

	@ReportColumn(property="data_criacao",title="Data Criação", colClass=Classes.STRING)
	private String data_criacao;
	
	@ReportColumn(property="qtd_produto_sub", title="Produtos", colClass=Classes.BIGINTEGER)
	private BigInteger qtd_produto_sub;
	
	@ReportColumn(property="qtd_servico_sub",title="Serviços", colClass=Classes.BIGINTEGER)
	private BigInteger qtd_servico_sub;
	

	public BigInteger getQtd_produto_sub() {
		return qtd_produto_sub;
	}

	public void setQtd_produto_sub(BigInteger qtd_produto_sub) {
		this.qtd_produto_sub = qtd_produto_sub;
	}

	public BigInteger getQtd_servico_sub() {
		return qtd_servico_sub;
	}

	public void setQtd_servico_sub(BigInteger qtd_servico_sub) {
		this.qtd_servico_sub = qtd_servico_sub;
	}

	public String getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(String data_criacao) {
		this.data_criacao = data_criacao;
	}
	

	public Indicador(String data_criacao, BigInteger qtd_produto_sub, BigInteger qtd_servico_sub) {
		this.data_criacao = data_criacao;
		this.qtd_produto_sub = qtd_produto_sub;
		this.qtd_servico_sub = qtd_servico_sub;
	}

	
}
