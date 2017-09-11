package br.com.seta.processo.recebimento.schedule.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleContratoDominio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<String> HORAS_DIA;
	private List<String> DIAS_SEMANA;
	private List<String> MINUTOS_SEGUNDOS;
	
	
	public ScheduleContratoDominio() {
		
		ArrayList<String> hora = new ArrayList<String>();
		hora.add("*");
		for(int i = 0; i<24; i++) {
			hora.add(Integer.toString(i));			
		}
		
		HORAS_DIA = Collections.unmodifiableList(hora);
		
		ArrayList<String> diaSemana = new ArrayList<String>();
		diaSemana.add("Segunda-Feira");
		diaSemana.add("Terça-Feira");
		diaSemana.add("Quarta-Feira");
		diaSemana.add("Quinta-Feira");
		diaSemana.add("Sexta-Feira");
		diaSemana.add("Sabado");
		diaSemana.add("Domingo");
		
		DIAS_SEMANA = Collections.unmodifiableList(diaSemana);
		
		ArrayList<String> minutoSegundo = new ArrayList<String>();
		minutoSegundo.add("*");
		for(int i = 0; i<60; i++) {
			minutoSegundo.add(Integer.toString(i));			
		}
		
		MINUTOS_SEGUNDOS = Collections.unmodifiableList(minutoSegundo);
	}


	public List<String> getHORAS_DIA() {
		return HORAS_DIA;
	}

	public List<String> getDIAS_SEMANA() {
		return DIAS_SEMANA;
	}

	public List<String> getMINUTOS_SEGUNDOS() {
		return MINUTOS_SEGUNDOS;
	}

}
