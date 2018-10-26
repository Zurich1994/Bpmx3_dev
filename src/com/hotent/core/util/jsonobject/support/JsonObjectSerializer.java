package com.hotent.core.util.jsonobject.support;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonObjectSerializer implements JsonDeserializer<String>,JsonSerializer<String>{
	//json转java bean
	public String deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		String result = "";
		if(json.isJsonPrimitive()){
			result = json.getAsString();
		}
		if(json.isJsonArray()||json.isJsonObject()){
			result = json.toString();
		}
		return result;
	}

	//java bean转json
	public JsonElement serialize(String arg0, Type arg1, JsonSerializationContext arg2) {
		return new JsonPrimitive(arg0);
	}
}