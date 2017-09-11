package br.com.seta.processo.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import br.com.seta.processo.bean.dao.GeFeriadoDao;
import br.com.seta.processo.businesscalendar.CalculadoraCalendarioComercial;
import br.com.seta.processo.entity.GeFeriado;
import br.com.seta.processo.interceptor.LogInterceptor;

/**
 * Implementação do calendário comercial do Seta utilizando a base de dados da CONSINCO (table GE_FERIADO)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Singleton(name="CalendarioComercialSeta")
@Local(CalendarioComercialSeta.class)
@Interceptors({LogInterceptor.class})
public class CalendarioComercialPelaC5 implements CalendarioComercialSeta {
	private static final String CALEDARIO_FERIADOS_SETA = "calendarioDeFeriadosSeta";
	private static final LocalTime INICIO_HORARIO_COMERCIAL = new LocalTime(8, 0, 0);
	private static final LocalTime FIM_HORARIO_COMERCIAL = new LocalTime(18, 0, 0);
	
	private CalculadoraCalendarioComercial calcCalendarioComercial;
	
	@Inject
	private GeFeriadoDao feriadoDao;
	
	@PostConstruct
	public void inicializa(){
		List<GeFeriado> geFeriados = feriadoDao.listaFeriadosNacionais();
		Set<LocalDate> calendarioFeriados = paraSetDeLocalDate(geFeriados);
		DateCalculator<LocalDate> dateCalculator = criaCalendario(calendarioFeriados);
		this.calcCalendarioComercial = new CalculadoraCalendarioComercial(dateCalculator, INICIO_HORARIO_COMERCIAL, FIM_HORARIO_COMERCIAL);
	}
	
	@Schedule(hour="0")
	public void reIniciarCalendarioComercial(){
		inicializa();
	}	
	
	@Override
	public Period calculaDuracao(DateTime inicio, DateTime fim){
		return this.calcCalendarioComercial.calculaDuracao(inicio, fim);
	}	
	
	@Override
	public Period calculaDuracao(Date inicio, Date fim){
		DateTime dataInicio = new DateTime(inicio);
		DateTime dataFim = new DateTime(fim);
		return this.calcCalendarioComercial.calculaDuracao(dataInicio, dataFim);
	}
	
	private DateCalculator<LocalDate> criaCalendario(Set<LocalDate> feriados){
		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(feriados);
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays(CALEDARIO_FERIADOS_SETA, calendarioDeFeriados);
		DateCalculator<LocalDate> calendario = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator(CALEDARIO_FERIADOS_SETA, HolidayHandlerType.FORWARD);
		
		return calendario;
	}
	
	private Set<LocalDate> paraSetDeLocalDate(List<GeFeriado> geFeriados){
		Set<LocalDate> feriados = new HashSet<LocalDate>();
		
		for(GeFeriado geFeriado : geFeriados){
			Date dtaferiado = geFeriado.getDtaferiado();
			feriados.add(new LocalDate(dtaferiado));
		}
		
		return feriados;
	}
}
