package br.com.seta.processo.bean;

import java.util.Date;

import javax.ejb.Local;

import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * Define as operações do Calendário Comercial do Seta. O calendário deve levar em consideração os dias utéis e não úteis
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Local
public interface CalendarioComercialSeta {
	public abstract Period calculaDuracao(DateTime inicio, DateTime fim);
	public abstract Period calculaDuracao(Date inicio, Date fim);
}