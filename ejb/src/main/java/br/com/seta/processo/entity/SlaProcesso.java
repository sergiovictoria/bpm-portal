package br.com.seta.processo.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Representa um SLA de um processo
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Entity
public class SlaProcesso implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum UnidadeTempo {
		Dias(24*60*60*1000), Horas(1*60*60*1000);

		private long quantMilisegundos;

		UnidadeTempo(long quantMilisegundos) {
			this.quantMilisegundos = quantMilisegundos;
		}

		public long emMilisegundos() {
			return this.quantMilisegundos;
		}
	}

	@Id
	private ObjectId id;
	private String processo;
	private int tempo;
	private UnidadeTempo unidadeTempo;

	public SlaProcesso(){
		
	}
	
	public SlaProcesso(String processo, int tempo, UnidadeTempo unidadeTempo) {
		this.processo = processo;
		this.tempo = tempo;
		this.unidadeTempo = unidadeTempo;
	}
	
	public long getSlaEmMilisegundos(){
		return this.getUnidadeTempo().emMilisegundos() * this.getTempo(); 
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public UnidadeTempo getUnidadeTempo() {
		return unidadeTempo;
	}

	public void setUnidadeTempo(UnidadeTempo unidadeTempo) {
		this.unidadeTempo = unidadeTempo;
	}	

}
