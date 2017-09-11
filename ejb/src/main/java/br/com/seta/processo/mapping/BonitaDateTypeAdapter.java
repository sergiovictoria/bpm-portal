package br.com.seta.processo.mapping;

import java.io.IOException;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * TypeAdapter customizado para converter o tipo Date do Bonita (vindo do Json da consulta Rest) que vem como Long (Date em milisegundos)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class BonitaDateTypeAdapter extends TypeAdapter<Date> {	
	
	@Override
	public void write(JsonWriter out, Date date) throws IOException {
		if(date != null){
			long dateInMiliseconds = date.getTime();
			out.value(dateInMiliseconds);
		}else{
			out.nullValue();
		}
	}

	@Override
	public Date read(JsonReader in) throws IOException {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(in);	
		
		if(element.isJsonNull()) return null;		
		
		long dateInMiliseconds = element.getAsLong();
		return new Date(dateInMiliseconds);				
	}

}
