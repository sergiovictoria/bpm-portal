package br.com.seta.processo.businesscalendar;

import net.objectlab.kit.datecalc.common.DateCalculator;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;

public class CalculadoraCalendarioComercial {
	
	private LocalTime inicioHorarioComercial;
	private LocalTime fimHorarioComercial;
	private Period periodoComercial;
	private DateCalculator<LocalDate> calendario;
	
	public CalculadoraCalendarioComercial(DateCalculator<LocalDate> calendarioDeFeriados, LocalTime inicioHorarioComercial, LocalTime fimHorarioComercial){
		this.inicioHorarioComercial = inicioHorarioComercial;
		this.fimHorarioComercial = fimHorarioComercial;
		this.periodoComercial = new Period(inicioHorarioComercial, fimHorarioComercial);
		this.calendario = calendarioDeFeriados;
	}
	
	/**
	 * Calcula a duração entre duas datas, baseando-se no horário e calendário comercial do Seta
	 * 
	 * @param inicio
	 * @param fim
	 * @return
	 */
	public Period calculaDuracao(DateTime inicio, DateTime fim){
		
		if(saoMesmoDia(inicio, fim)){
			if(calendario.isNonWorkingDay(inicio.toLocalDate())){
				return Period.ZERO;
			}
			return calculaPeriodoPara(inicio.toLocalTime(), fim.toLocalTime());
		}		
		
		Period periodoDoPrimeiroDia = calculaPeriodoAteFimHorarioComercial(inicio, inicioHorarioComercial, fimHorarioComercial);
		Period periodoDoDiaFinal = calculaPeriodoAteInicioHorarioComercial(fim, inicioHorarioComercial, fimHorarioComercial);		
		
		Period peridoDoSegundoDiaAoPenultimoDia = Period.ZERO;
		DateTime segundoDia = inicio.plusDays(1);
		DateTime penultimoDia = fim.minusDays(1);
		if(!saoMesmoDia(segundoDia, fim)){
			int quantDiasNaoUteis = calculaQuantDiasNaoUteis(segundoDia.toLocalDate(), penultimoDia.toLocalDate());
			int quantDias = Days.daysBetween(segundoDia.toLocalDate(), penultimoDia.toLocalDate()).getDays() + 1; //contar o penúltimo dia também
			int quantDiasUteis = quantDias - quantDiasNaoUteis;
			peridoDoSegundoDiaAoPenultimoDia = periodoComercial.multipliedBy(quantDiasUteis);
		}
		
		return periodoDoPrimeiroDia.plus(peridoDoSegundoDiaAoPenultimoDia).plus(periodoDoDiaFinal);
	}
	
	/*
	public DateCalculator<LocalDate> criaCalendario(Set<LocalDate> feriados){
		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(feriados);
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays(FERIADOS_SETA, calendarioDeFeriados);
		DateCalculator<LocalDate> calendario = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator(FERIADOS_SETA, HolidayHandlerType.FORWARD);
		
		return calendario;
	}*/
	
	
	/**
	 * Calcula o período de trabalho, sendo que é válido apenas o período dentro do horário comercial
	 * 
	 * @param horarioDeInicio Horário de inicio do período
	 * @param horarioDeFim Horário de fim do período
	 * @return O período entre o horarioDeInicio e o horarioDeFim
	 */
	public Period calculaPeriodoPara(LocalTime horarioDeInicio, LocalTime horarioDeFim){
		if(horarioDeInicio.isAfter(fimHorarioComercial) || horarioDeFim.isBefore(inicioHorarioComercial)){
			return Period.ZERO;
		}
		
		if(horarioDeInicio.isBefore(inicioHorarioComercial)){
			horarioDeInicio = inicioHorarioComercial;
		}		
		
		if(horarioDeFim.isAfter(fimHorarioComercial)){
			horarioDeFim = fimHorarioComercial;
		}
		
		return new Period(horarioDeInicio, horarioDeFim);
	}
	
	/**
	 * Calcula o período até o fim do horário comercial
	 * 
	 * @param data
	 * @param inicioHorarioComerical
	 * @param fimHorarioComercial
	 * @return
	 */
	private Period calculaPeriodoAteFimHorarioComercial(DateTime data, LocalTime inicioHorarioComerical, LocalTime fimHorarioComercial){
		LocalTime horarioInicial = data.toLocalTime();
		if(horarioInicial.isAfter(fimHorarioComercial) || calendario.isNonWorkingDay(data.toLocalDate())){
			return Period.ZERO;
		}
		if(horarioInicial.isBefore(inicioHorarioComerical)){
			return this.periodoComercial;
		}
		return new Period(horarioInicial, fimHorarioComercial);
	}

	private Period calculaPeriodoAteInicioHorarioComercial(DateTime data, LocalTime inicioHorarioComercial, LocalTime fimHorarioComercial) {
		LocalTime horarioFinal = data.toLocalTime();
		if(horarioFinal.isBefore(inicioHorarioComercial) || calendario.isNonWorkingDay(data.toLocalDate())){
			return Period.ZERO;
		}
		if(horarioFinal.isAfter(fimHorarioComercial)){
			return this.periodoComercial;
		}
		return new Period(inicioHorarioComercial, horarioFinal);
	}
	
	private int calculaQuantDiasNaoUteis(LocalDate inicio, LocalDate fim) {
		int diasNaoUteis = 0;

		while(!inicio.isAfter(fim)) {
			if(calendario.isNonWorkingDay(inicio)) {
				diasNaoUteis++;
			}
			inicio = inicio.plusDays(1);
		}

		return diasNaoUteis;
	}
	
	private boolean saoMesmoDia(DateTime data1, DateTime data2){
		return data1.toLocalDate().isEqual(data2.toLocalDate());
	}
}
