package br.com.seta.processo.mapping;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BadDoubleDeserializer implements JsonDeserializer<Double>, JsonSerializer<Double> {

	
	@Override
	public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Double cost;
		try {
			cost = json.getAsDouble();
		} catch (NumberFormatException e) {
			cost = 0.00d;
		}
		return cost;
	}

		
	
	
	@Override
	public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src);
	}



}
