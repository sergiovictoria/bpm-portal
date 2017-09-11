package br.com.seta.processo.mapping;

import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class BonitaHelper<T> {
	
	private static String VALUE_MEMBER = "value";	
	private Class<T> classType;

	public BonitaHelper(Class<T> classType) {
		this.classType = classType;				
	}

	@SuppressWarnings("rawtypes")
	public T fromBonitaObject(Map bonitaObject){
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(bonitaObject);		
		T obj = gson.fromJson(json, classType);
		
		return obj;
	}
	
	public T fromJsonBonitaActivityVariable(String jsonBonitaActivityVariable) {
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(jsonBonitaActivityVariable);
		JsonObject root = jsonElement.getAsJsonObject();
		JsonElement valueMember = root.get(VALUE_MEMBER);
        
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new BonitaDateTypeAdapter()).serializeNulls().create();
		
		return gson.fromJson(valueMember, this.classType);
	}
	
	public String toJson(T object){
		return toJson(object, true, true);
	}	
	
	/**
	 * Serializa um objeto 
	 * 
	 * @param object Objeto que será serializado em um json
	 * @param serializarNulls true para serializar os valores nulos, false para não serializá-los
	 * @param dateEmLongMilisegundos true para converter dates (datas) em milisegundos (long)
	 * @return Uma string contendo o objeto serializado no formato json
	 */
	public String toJson(T object, boolean serializarNulls, boolean dateEmLongMilisegundos){
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		if(serializarNulls){
			gsonBuilder.serializeNulls();
		}
		if(dateEmLongMilisegundos){
			gsonBuilder.registerTypeAdapter(Date.class, new BonitaDateTypeAdapter());
		}
		
		Gson gson  = gsonBuilder.create();
		return gson.toJson(object);		
	}
}
