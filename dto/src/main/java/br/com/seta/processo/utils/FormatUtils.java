package br.com.seta.processo.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;


public final class FormatUtils {
	
	private static Logger logger = Logger.getLogger(FormatUtils.class);
	private static FormatUtils formatUtils;

	public static FormatUtils parse(){
		if (formatUtils == null){
			logger.info("  **** Criando Serviço Singleton FormatUtils *** ");
			formatUtils = new FormatUtils();
		}
		return formatUtils;
	}

	
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final DecimalFormat sdf = new DecimalFormat("#,##0.00");

	public String formatDate(java.util.Date date) {
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}
	
	public static String formatValorMonetario(BigDecimal value) {
		if(value.equals(BigDecimal.ZERO))
			return "0,00";
		
		return sdf.format(value);
	}
	
	public static String formata(BigDecimal valor, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(valor);
	}	
	
	public static String formata(Date data, String pattern){
		if(data == null)
			return "";
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		return sdf.format(data);
	}
	
	public static Object seNuloSubstitui(Object valor, Object substituto){
		if(valor == null)
			return substituto;
		
		return valor;
	}
	
}