package com.hotent.core.util.jsonobject.support;
import java.lang.reflect.Type;
import java.util.Date;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BooleanSerializer implements JsonDeserializer<Boolean>,JsonSerializer<Boolean>{
	public Boolean deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		//System.out.println(json+"----"+json.toString().equals("\"1\""));
		if(json.toString().equals("\"1\"")){
			return true;
		}else if(json.toString().equals("\"0\"")){
			return false;
		}
		return json.getAsBoolean();
	}

	public JsonElement serialize(Boolean arg0, Type arg1, JsonSerializationContext arg2) {
		return new JsonPrimitive(arg0.toString());
	}
}
