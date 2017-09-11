package br.com.seta.processo.mapping;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Classe com a função de deserialização dos tipos Date vindos do Bonita BPM. O Bonita BPM retorna datas em dois formatos:<br>
 * 1 - Como long para Dates vindos de jars externos. <br>
 * 2 - Como um String no formato 'yyyy-MM-dd HH:mm:ss.SSS' para dados internos retornados por sua API Rest.
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class DateDeserializer implements JsonDeserializer<Date> {
	
	@Override
	public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		String date = element.getAsString();
		
		if(date.trim().length() == 0) return null;		
		
		if(isLong(date)){
			return new Date(new Long(date));
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	private boolean isLong(String longValue){
		try{
			Long.parseLong(longValue);
			return true;
		}catch(NumberFormatException exception){
			return false;
		}		
	}
}
