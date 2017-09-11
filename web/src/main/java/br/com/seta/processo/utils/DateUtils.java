package br.com.seta.processo.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Contém métodos funcionais para o tratamento de datas (java.util.Date)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class DateUtils {
	
	/**
	 * Ajusta o horário de um determinado Date para o início do dia, ou seja, horário igual a 00:00:00:00. 
	 * 
	 * @param data Date a ser ajustado.
	 * @return Date ajustado para o início do dia
	 */
	public static Date inicioDoDiaDe(Date data){
		return setHorarioDe(data, 0, 0, 0, 0);
	}
	
	/**
	 * Ajusta o horário de um determinado Date para o fim do dia, ou seja, horário igual a 23:59:59:999
	 * 
	 * @param data Date a ser ajustado.
	 * @return Date ajustado para o fim do dia
	 */
	public static Date fimDoDiaDe(Date data){
		return setHorarioDe(data, 23, 59, 59, 999);
	}
	
	/**
	 * Ajusta o horário de um determinado Date para os valores passados como parâmetros
	 * 
	 * @param data Date a ser ajustado.
	 * @param horas
	 * @param minutos
	 * @param segundos
	 * @param milisegundos
	 * @return Date ajustado com base nos parâmetros fornecidos
	 */
	public static Date setHorarioDe(Date data, int horas, int minutos, int segundos, int milisegundos){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, horas);
		calendar.set(Calendar.MINUTE, minutos);
		calendar.set(Calendar.SECOND, segundos);
		calendar.set(Calendar.MILLISECOND, milisegundos);
		
		return calendar.getTime();
	}

}
