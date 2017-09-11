package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Representa dados de um registro de histórico
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Historico implements Serializable, Comparable<Historico> {

	private static final long serialVersionUID = 1L;
	private String status;
	private String nome;
	private java.util.Date data;
	private String motivo;
	private String comentario;	
		
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public int compareTo(Historico o) {
		return Comparators._STATUS.compare(this, o);
	}	
	
	public static class Comparators {
		
		public static Comparator<Historico> _STATUS = new Comparator<Historico>() {
			@Override
			public int compare(Historico o1, Historico o2) {
				return o1.getStatus().compareTo(o2.getStatus());
			}
		};
		
		public static Comparator<Historico> _NOME = new Comparator<Historico>() {
			@Override
			public int compare(Historico o1, Historico o2) {
				return o1.getNome().compareTo(o2.getNome());
			}
		};
		
		
		public static Comparator<Historico> _MOTIVO = new Comparator<Historico>() {
			@Override
			public int compare(Historico o1, Historico o2) {
				return o1.getMotivo().compareTo(o2.getMotivo());
			}
		};
		
		public static Comparator<Historico> DATA = new Comparator<Historico>(){
			@Override
			public int compare(Historico hf1, Historico hf2) {
				return hf1.getData().compareTo(hf2.getData());
			}
		};		
		
	}	
	
	@Override
	public String toString() {
		return "HistoricoFornecedor [status=" + status + ", nome=" + nome
				+ ", data=" + data + ", motivo=" + motivo + ", comentario="
				+ comentario + "]";
	}	

}
