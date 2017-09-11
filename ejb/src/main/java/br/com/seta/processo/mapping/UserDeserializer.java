package br.com.seta.processo.mapping;

import java.lang.reflect.Type;
import java.util.Date;

import br.com.seta.processo.dto.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 
 * Deserializador customizado para o DTO User. Algumas vezes quando não existe valores para os atributos execute_by e assigned (Users), 
 * o Bonita retorna zero ou "" em vez de nulo 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class UserDeserializer implements JsonDeserializer<User> {

	@Override
	public User deserialize(JsonElement element, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		if(element.isJsonPrimitive() || element.isJsonNull() ){
			return null;
		}
		
		 Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create(); 
		 return gson.fromJson(element, User.class); 
	}
}
