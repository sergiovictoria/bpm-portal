package br.com.seta.processo.utils;

import java.util.Calendar;

public class DateUtils {

	public static String dataSemana(int diaSemana) {

		switch (diaSemana) {

		case Calendar.SUNDAY:
			return "Domingo";
		case Calendar.MONDAY:
			return "Segunda-feira";
		case Calendar.TUESDAY:
			return "Terça-feira";
		case Calendar.WEDNESDAY:
			return "Quarta-Feira";
		case Calendar.THURSDAY:
			return "Quinta-Feira";
		case Calendar.FRIDAY:
			return "Sexta-Feira";
		case Calendar.SATURDAY:
			return "Sabado";
		default:
			return "Sem Dias";
		}

	}

	public static String dataSemanaResumido(int diaSemana) {
		switch (diaSemana) {

		case Calendar.SUNDAY:
			return "Dom";
		case Calendar.MONDAY:
			return "Seg";
		case Calendar.TUESDAY:
			return "Ter";
		case Calendar.WEDNESDAY:
			return "Qua";
		case Calendar.THURSDAY:
			return "Qui";
		case Calendar.FRIDAY:
			return "Sex";
		case Calendar.SATURDAY:
			return "Sab";
		default:
			return "Sem Dias";
		}

	}

	public static String pesquisarNomeMes(int mes) {
		String nomeMes = null;

		switch (mes) {
		case 1:
			nomeMes = "Janeiro";
			break;

		case 2:
			nomeMes = "Fevereiro";
			break;

		case 3:
			nomeMes = "Março";
			break;

		case 4:
			nomeMes = "Abril";
			break;

		case 5:
			nomeMes = "Maio";
			break;

		case 6:
			nomeMes = "Junho";
			break;

		case 7:
			nomeMes = "Julho";
			break;

		case 8:
			nomeMes = "Agosto";
			break;

		case 9:
			nomeMes = "Setembro";
			break;

		case 10:
			nomeMes = "Outubro";
			break;

		case 11:
			nomeMes = "Novembro";
			break;

		case 12:
			nomeMes = "Dezembro";
			break;
		}

		return nomeMes;
	}

	public static String pesquisarNomeMesResumido(int mes) {
		String nomeMes = null;

		switch (mes) {
		case 1:
			nomeMes = "Jan";
			break;

		case 2:
			nomeMes = "Fev";
			break;

		case 3:
			nomeMes = "Mar";
			break;

		case 4:
			nomeMes = "Abr";
			break;

		case 5:
			nomeMes = "Mai";
			break;

		case 6:
			nomeMes = "Jun";
			break;

		case 7:
			nomeMes = "Jul";
			break;

		case 8:
			nomeMes = "Ago";
			break;

		case 9:
			nomeMes = "Set";
			break;

		case 10:
			nomeMes = "Out";
			break;

		case 11:
			nomeMes = "Nov";
			break;

		case 12:
			nomeMes = "Dez";
			break;
		}

		return nomeMes;
	}

}
