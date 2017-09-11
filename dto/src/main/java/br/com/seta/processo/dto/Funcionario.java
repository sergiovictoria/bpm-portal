package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;



public class Funcionario implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	private String nome;
	private BigDecimal cpf;
	private java.sql.Timestamp dataNacimento;
	private java.sql.Timestamp admissao;
	private String cargo;
	private String local;
	private String ccusto;
	private String situcao;
	private BigDecimal registro;
		
	
	public Funcionario(String nome, BigDecimal cpf, Timestamp dataNacimento,
			Timestamp admissao, String cargo, String local, String ccusto,
			String situcao, BigDecimal registro) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNacimento = dataNacimento;
		this.admissao = admissao;
		this.cargo = cargo;
		this.local = local;
		this.ccusto = ccusto;
		this.situcao = situcao;
		this.registro = registro;
	}

	
	public Funcionario( ) {
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getCpf() {
		return cpf;
	}
	public void setCpf(BigDecimal cpf) {
		this.cpf = cpf;
	}
	public java.sql.Timestamp getDataNacimento() {
		return dataNacimento;
	}
	public void setDataNacimento(java.sql.Timestamp dataNacimento) {
		this.dataNacimento = dataNacimento;
	}
	public java.sql.Timestamp getAdmissao() {
		return admissao;
	}
	public void setAdmissao(java.sql.Timestamp admissao) {
		this.admissao = admissao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getCcusto() {
		return ccusto;
	}
	public void setCcusto(String ccusto) {
		this.ccusto = ccusto;
	}
	public String getSitucao() {
		return situcao;
	}
	public void setSitucao(String situcao) {
		this.situcao = situcao;
	}
	public BigDecimal getRegistro() {
		return registro;
	}
	public void setRegistro(BigDecimal registro) {
		this.registro = registro;
	}
	
	
	
	
	
	
	
	
	

}
