package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa um registro do histórico do processo de Solicitação de Pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

public class RegistroHistSolicitacaoPagto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String nomeUsuario;
	private Date data;
	private String comentario;

	public RegistroHistSolicitacaoPagto(){
		
	}
	
	public RegistroHistSolicitacaoPagto(String status, String nomeUsuario,
			Date data, String comentario) {
		this.status = status;
		this.nomeUsuario = nomeUsuario;
		this.data = data;
		this.comentario = comentario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "RegistroHistSolicitacaoPagto [status=" + status
				+ ", nomeUsuario=" + nomeUsuario + ", data=" + data
				+ ", comentario=" + comentario + "]";
	}
}
