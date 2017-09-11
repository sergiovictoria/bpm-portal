package br.com.seta.processo.dto;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;


@Entity("OrIndicador")
public class OrIndicador implements Serializable {


	private static final long serialVersionUID = 1L;

	private java.util.Date data;
	private String usuario;
	private String email;
	private String intervalo;
	private String tempoTotal;
	private String atividade;
	private String status;
	
	
	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}
	public String getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(String tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "OrIndicador [data=" + data + ", usuario=" + usuario + ", email=" + email + ", intervalo=" + intervalo + ", tempoTotal="
				+ tempoTotal + ", atividade=" + atividade + ", status=" + status + "]";
	}
	
	
	


}
