package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Dados da aprovação da gerência comercial
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class AprovacaoGerenciaComercial implements Serializable  {
	

	private static final long serialVersionUID = 1L;
	private String nome;
	private Date dataHora;
	private String comentarios;
	
	public AprovacaoGerenciaComercial(){
		
	}
	
	public AprovacaoGerenciaComercial(String nome, Date dataHora, String comentarios) {
		super();
		this.nome = nome;
		this.dataHora = dataHora;
		this.comentarios = comentarios;
	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public String toString() {
		return "AprovacaoGerenciaComercial [nome=" + nome + ", dataHora="
				+ dataHora + ", comentarios=" + comentarios + "]";
	}	
}
