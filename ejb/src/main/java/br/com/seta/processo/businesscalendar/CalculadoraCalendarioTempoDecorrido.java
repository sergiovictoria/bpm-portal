package br.com.seta.processo.businesscalendar;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Years;

public class CalculadoraCalendarioTempoDecorrido {

	public CalculadoraCalendarioTempoDecorrido() {
	}

	public String calculaTempoDecorrido(Date inicio, Date fim) {

		StringBuilder td = new StringBuilder();

		try {

			DateTime dt1 = new DateTime(inicio);
			DateTime dt2 = new DateTime(fim);

			int years = Years.yearsBetween(dt1, dt2).getYears();
			int months = Months.monthsBetween(dt1, dt2).getMonths() % 12;
			int days = Days.daysBetween(dt1, dt2).getDays() % 30;
			int hours = Hours.hoursBetween(dt1, dt2).getHours() % 24;
			int minutes = Minutes.minutesBetween(dt1, dt2).getMinutes() % 60;

			td.append((years > 0 ? (years > 1 ? years + " anos, " : years + " ano, ") : ""))
					.append((months > 0 ? (months > 1 ? months + " meses, " : months + " mês, ") : ""))
					.append((days > 0 ? (days > 1 ? days + " dias, " : days + " dia, ") : ""))
					.append((hours > 0 ? (hours > 1 ? hours + " hs, " : hours + " h, ") : ""))
					.append((minutes > 0 ? (minutes > 1 ? minutes + " min" : minutes + " min") : ""));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return td.toString();
	}

}
