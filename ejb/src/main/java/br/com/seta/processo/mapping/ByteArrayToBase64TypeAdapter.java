package br.com.seta.processo.mapping;

import java.lang.reflect.Type;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Adaptador customizado para conversão de/para String/byte array. 
 * Inicialmente, teremos um array de bytes para representar os documentos enviados para o Bontia BPM
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
	
	public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Base64.decodeBase64(json.getAsString());
	}

	public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(Base64.encodeBase64String(src));
	}
}
