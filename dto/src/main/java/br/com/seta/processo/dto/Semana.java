/**
 * 
 */
package br.com.seta.processo.dto;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * @author Sérgio da Victória
 *
 *   
 */
@Entity("Semana")
public class Semana implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String inicioSemana;
	private String fimSemana;
	private String horas;
	private String minutos;
	private String segundos;
	private String intervalo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInicioSemana() {
		return inicioSemana;
	}
	public void setInicioSemana(String inicioSemana) {
		this.inicioSemana = inicioSemana;
	}
	public String getFimSemana() {
		return fimSemana;
	}
	public void setFimSemana(String fimSemana) {
		this.fimSemana = fimSemana;
	}
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}
	public String getMinutos() {
		return minutos;
	}
	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}
	public String getSegundos() {
		return segundos;
	}
	public void setSegundos(String segundos) {
		this.segundos = segundos;
	}
	
	public String getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}
	@Override
	public String toString() {
		return "Semana [id=" + id + ", inicioSemana=" + inicioSemana + ", fimSemana=" + fimSemana + ", horas=" + horas + ", minutos=" + minutos
				+ ", segundos=" + segundos + "]";
	}
	
	

}
