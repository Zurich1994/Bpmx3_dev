package com.hotent.core.util.jsonobject.support;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hotent.core.util.DateFormatUtil;

public class DateSerializer implements JsonDeserializer<Date>,JsonSerializer<Date>{
	public Date deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		if (json.getAsJsonPrimitive().isString()) {
			try {
				Date date = DateFormatUtil.parse(json.getAsJsonPrimitive().getAsString());
				return date;
			} catch (ParseException e) {
			}
		}
		return new Date(json.getAsJsonPrimitive().getAsLong()); 
	}

	public JsonElement serialize(Date arg0, Type arg1, JsonSerializationContext arg2) {
		return new JsonPrimitive(arg0.getTime());
	}
}
