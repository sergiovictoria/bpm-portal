package br.com.seta.processo.dto;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;


@Entity("Status")
public class Status implements Serializable {


	private static final long serialVersionUID = 1L;
	private java.util.Date data;
	private String status;
	private String observacao;
	private String nomeProcesso;
	
	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}
	
	@Override
	public String toString() {
		return "Status [data=" + data + ", status=" + status + ", observacao=" + observacao + ", nomeProcesso=" + nomeProcesso + "]";
	}
	


}
