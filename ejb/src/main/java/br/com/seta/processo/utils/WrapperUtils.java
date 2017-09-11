package br.com.seta.processo.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public final class WrapperUtils {
	
	
	
	private static final SimpleDateFormat _SIMPLE_DATE_MONGO = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);


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
	
	public static String diffHoursMinutesSeconds(java.util.Date d1, java.util.Date d2)  {
	    long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;   
		long diffMinutes = diff / (60 * 1000) % 60;          
		long diffHours   = diff / (60 * 60 * 1000) % 60;
		String time = addZeros(diffHours)+":"+addZeros(diffMinutes)+":"+addZeros(diffSeconds);
		return time;
	}
	
	
	/*****
	 * 
	 * @param date Data para converter no formato do mongo
	 * @param endDate Tipo de data 
	 * @return Retorna a data no formato do mongo para Morphia
	 * @throws ParseException
	 */
	
	public static Date converteMongoHours(java.util.Date date, boolean endDate) throws ParseException  {
		java.util.Date dateReturn = new java.util.Date();
		Calendar cal = Calendar.getInstance(Locale.ENGLISH);
		cal.setTime(date);
		Integer year = cal.get(Calendar.YEAR);
		Integer month= cal.get(Calendar.MONTH)+1;
		Integer day  = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(day+" dia "+month+" mes "+year);
	    if (endDate) {
	    	dateReturn = _SIMPLE_DATE_MONGO.parse(year+"-"+month+"-"+day+"T00:00:00Z");
	    }else {
	    	dateReturn = _SIMPLE_DATE_MONGO.parse(year+"-"+month+"-"+day+"T24:00:00Z");
	    }
	    return dateReturn; 
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


	public static ByteArrayOutputStream loadFileByteArray(String fileName) {
		FileInputStream file = null;
		int read;

		try {
			file = new FileInputStream(fileName);

			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			byte[] content = new byte[2048 * 16 * 16];

			while ((read = file.read(content)) > 0) {
				byteArray.write(content, 0, read);
			}

			return byteArray;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
				}
		}
		return null;
	}


	public static byte [] loadFile(String fileName) {
		FileInputStream file = null;
		int read;

		try {
			file = new FileInputStream(fileName);

			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

			byte[] content = new byte[2048 * 16 * 16];

			while ((read = file.read(content)) > 0) {
				byteArray.write(content, 0, read);
			}

			return byteArray.toByteArray();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
				}
		}
		return null;
	}



}

