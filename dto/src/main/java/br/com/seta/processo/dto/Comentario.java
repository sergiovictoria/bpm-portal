package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.seta.processo.dtobonita.Usuario;

/**
 * Comentário feito por um determinado usuário
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Comentario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private Date data;
	private String texto;
	
	public Comentario(){
		
	}
	
	public Comentario(Usuario usuario, String texto) {
		super();
		this.usuario = usuario;
		this.data = new Date();
		this.texto = texto;
	}

	public Comentario(Usuario usuario, Date data, String texto) {
		super();
		this.usuario = usuario;
		this.data = data;
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
