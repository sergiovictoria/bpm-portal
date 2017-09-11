package br.com.seta.processo.utils;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.MaskFormatter;


public final class WrapperUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy:hh:mm:ss");
	private static final String TYPE_NAME_PREFIX = "class ";
	
	public static String format(FormatacaoTipo enmPattern, Object value) {

		switch (enmPattern) {
		case DATA:
		case DATA_HORA:
		case HORA_MINUTO:
		case ANO_MES:
		case MES_ANO:
			try {
				Locale locale = new Locale("pt", "BR");
				DateFormat df = new SimpleDateFormat(enmPattern.getValue(), locale);
				df.setLenient(false);
				return df.format((Date) value);
			} catch (Exception e) {
				return "";
			}
		case CPF_CNPJ:
			if(value != null && value.toString().length() == 11) {
				Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
				Matcher matcher = pattern.matcher(value.toString());
				if (matcher.matches()) 
					return matcher.replaceAll("$1.$2.$3-$4");
			}
			
			if(value != null && value.toString().length() == 14) {
				Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
				Matcher matcher = pattern.matcher(value.toString());
				if (matcher.matches()) 
					return matcher.replaceAll("$1.$2.$3/$4-$5");
			}
			return "";
		default:
			MaskFormatter mask;
			try {
				mask = new MaskFormatter(enmPattern.getValue());
				mask.setValueContainsLiteralCharacters(false);
				return mask.valueToString(value);
			} catch (ParseException e) {
				return "";
			}
		}
	}
	
	public static List<Object> sorteiaObjetos(List<?> listaObjetos, int qtdeElementos){

		List<Object> sorteados = new ArrayList<Object>();

		Collections.shuffle(listaObjetos);

		for (int i = 0; i < qtdeElementos; i++) {
			sorteados.add(listaObjetos.remove (0));
		}
		return sorteados;
	}

	public static boolean isNotNull(Object o) {	
		return !isNull(o);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNull(Object o) {		
		if(o!=null){
			if(o instanceof String){
				if (((String)o).trim().length()>0){
					return false;	
				}else{
					return true;
				}					
			}else if(o instanceof Collection){
				if (((Collection) o).size()>0){
					for(Object child : (Collection) o){						
						if(child instanceof String){
							if (((String)child).trim().length()>0){
								return false;	
							}
						}else{
							return false;
						}
					}
				}else{
					return true;
				}			
			}else if(o instanceof String[]){
				if(((String[])o).length>0){
					for (int i = 0; i < ((String[])o).length; i++) {
						if(((String[])o)[i].trim().length()>0){
							return false;
						}
					}					
				}
			}else{
				return false;
			}						
		}
		return true;
	}

	public static String parseDate(Date data,String pattern) {	
		String retorno = "";
		if (data == null || data.equals("")) {
			retorno = " - ";
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat(pattern);
			retorno = f.format(data);
		} catch (Exception e) {
		}
		return retorno;
	}

	public static String encodeStringToHtml(String sequence) {
		String out = "";
		for (int i = 0; i < sequence.length(); i++) {
			char ch = sequence.charAt(i);
			if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.BASIC_LATIN) {
				out += ch;
			} else {
				int codepoint = Character.codePointAt(sequence, i);
				// handle supplementary range chars
				i += Character.charCount(codepoint) - 1;
				// emit entity
				out += "&#x";
				out+= Integer.toHexString(codepoint);
				out += ";";
			}
		}
		return out;

	}

	public static String decodeUrlString(String string, String encodeUtf8) {
		String retorno = "";
		try {
			retorno = URLDecoder.decode(string, encodeUtf8);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}


	public static java.util.Date addDays(java.util.Date date, int days)  {
		Locale ptBr = new Locale("pt", "BR");
		Calendar calendar = Calendar.getInstance(ptBr);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}


	public static String getTime(java.util.Date dateStart, java.util.Date dateEnd)  {
		GregorianCalendar c1 = new GregorianCalendar();
		GregorianCalendar c2 = new GregorianCalendar();
		c1.setTime(dateStart);
		c2.setTime(dateEnd);
		long millis = c1.getTimeInMillis()-c2.getTimeInMillis();
		long segundos = millis / 1000;
		long minutos = segundos / 60; 
		long horas = minutos / 60;
		String time = addZeros(horas)+":"+addZeros(minutos)+":"+addZeros(segundos);
		return time;
	}


	public static String addZeros(long value) {
		String valueReturn=Long.toString(value);
		if (valueReturn.length()<2) {
			for (int i=0; i < (2-valueReturn.length() ) ;i++) {
				valueReturn ="0"+valueReturn;
			}
		}else {
			return valueReturn.substring(0, 2);
		}
		return valueReturn;
	}

	public static Object getObject(HttpServletRequest request, Class<?> classType) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchFieldException, SecurityException, ParseException {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		Object object = classType.newInstance();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for (int i =0 ; i<paramValues.length; i++)  {
			    Field field = classType.getDeclaredField(paramName);
				field.setAccessible(true);
				String typeCLass = WrapperUtils.getClassName(field); 			
				if (typeCLass.equals("java.lang.Integer")) {			
					field.set(object,Integer.parseInt(paramValues[i]));
				}else if (typeCLass.equals("java.lang.String")) {
					field.set(object,(String) paramValues[i]);
				}else if (typeCLass.equals("java.lang.Double")) {
					field.set(object,(Double.parseDouble(paramValues[i])));
				}else if (typeCLass.equals("java.lang.Boolean")) {
					field.set(object,(Boolean.parseBoolean(paramValues[i])));
				}if (typeCLass.equals("java.util.Date")) {
					field.set(object,(formatDate.parse(paramValues[i])));
				}
			    System.out.println(paramName +": "+paramValues[i]+" "+typeCLass );
			}
		}
		return object;
	}


	public static String formatarFloatBr(Double valor) {
		String s3 = "0,00";
		try{
			Locale locale = new Locale( "pt", "BR" );
			NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols (locale) );
			Double d1 = valor.doubleValue();
			s3 = nf.format (d1);
		}catch (Exception e) {
		}
		return s3;
	}



	public static String formatDate(java.util.Date date) {
		return sdf.format(date);
	}

	public static Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = Class.forName(className);
		Object object = clazz.newInstance();
		return object;
	}

	public static String getClassName(Field field) {
	    if (field==null) {
	        return "";
	    }
	    String className = field.getGenericType().toString();
	    if (className.startsWith(TYPE_NAME_PREFIX)) {
	    	className = className.substring(TYPE_NAME_PREFIX.length());
	    }
	    return className;
	}
	
	
	public enum FormatacaoTipo {
		/**
		 * Retorna #####-###
		 */
		CEP("#####-###"),

		/**
		 * Retorna ###.###.###-##
		 */
		CPF("###.###.###-##"),

		/**
		 * Retorna ##.###.###/####-##
		 */
		CNPJ("##.###.###/####-##"),

		/**
		 * Retorna ##.###.#####/##
		 */
		CEI("##.###.#####/##"),

		/**
		 * Esse pattern passa como parametro a data e hora, fazendo o metodo
		 * retornar ##/##/#### ##:##
		 */
		DATA_HORA("dd/MM/yyyy HH:mm"),

		/**
		 * Esse pattern passa como parametro a data e hora, fazendo o metodo
		 * retornar ##/##/####
		 */
		DATA("dd/MM/yyyy"),

		/**
		 * Esse pattern passa como parametro o mes e ano, fazendo o metodo
		 * retornar yyyy-MM
		 */
		ANO_MES("yyyy-MM"),

		/**
		 * Esse pattern passa como parametro o mes e ano, fazendo o metodo
		 * retornar MM/yyyy
		 */
		MES_ANO("MM/yyyy"),

		/**
		 * Esse pattern passa como parametro a hora e o minuto, fazendo o metodo
		 * retornar ##:##
		 */
		HORA_MINUTO("HH:mm"),
		
		CPF_CNPJ("");

		private String value;

		private FormatacaoTipo(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}

