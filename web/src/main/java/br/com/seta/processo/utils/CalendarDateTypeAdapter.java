package br.com.seta.processo.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;



public class CalendarDateTypeAdapter extends TypeAdapter<Calendar> implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {

	private static final Gson gson = new GsonBuilder().create();
	private static final TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
	private static final String MONGO_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public JsonElement serialize(Calendar src, Type type,
			JsonSerializationContext context) {
		if (src == null) {
			return null;
		} else {
			SimpleDateFormat format = new SimpleDateFormat(MONGO_UTC_FORMAT);
			JsonObject jo = new JsonObject();
			jo.addProperty("$date", format.format(src.getTime()));
			return jo;
		}
	}

	@Override
	public Calendar deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(MONGO_UTC_FORMAT);
		try {
			date = format.parse(json.getAsJsonObject().get("$date").getAsString());
		} catch (ParseException e) {
			date = null;
		}
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		return gregorianCalendar;
	}
	@Override
	public void write(JsonWriter out, Calendar value) throws IOException {
		dateTypeAdapter.write(out, value.getTime());
	}

	@Override
	public Calendar read(JsonReader in) throws IOException {
		Date read = dateTypeAdapter.read(in);
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(read);
		return gregorianCalendar;
	}
}
