package com.hotent.platform.webservice.impl.util;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hotent.core.util.BeanUtils;

public class GsonUtil {
	/**
	 * @param jsonObject
	 * @param cls
	 * @return 
	 * C
	 * @exception 
	 * @since  1.0.0
	 */
	public static <C>  C toBean(JSONObject jsonObject,Class<C> cls){
		return toBean(jsonObject.toString(), cls);
	}
	
	private static Gson getGson(){
		GsonBuilder gsonBuilder = new GsonBuilder();  
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer())
        		   .addDeserializationExclusionStrategy(new SuperclassExclusionStrategy())
        		   .addSerializationExclusionStrategy(new SuperclassExclusionStrategy());
        Gson gson = gsonBuilder.create();
        return gson;
	}
	
	/**
	 * 把jsonStr生成一个cls对象
	 * @param jsonObject
	 * @param cls
	 * @return 
	 * C
	 * @exception 
	 * @since  1.0.0
	 */
	public static <C>  C toBean(String jsonStr,Class<C> cls){
		C o = (C)getGson().fromJson(jsonStr,cls);
		return o;
	}
	
	/**
	 * 转换jsonStr为java bean
	 * @param jsonStr json格式的字符串
	 * @param type 待转换类型(可以是泛型)
	 *		  <pre>例如:new TypeToken&lt;ArrayList&lt;SysServiceParam>>(){}.getType()</pre>
	 * @return
	 */
	public static <C> C toBean(String jsonStr,Type type){
		C o = (C)getGson().fromJson(jsonStr,type);
		return o;
	}
	
	/**
	 * 将对象转化为JsonElement
	 * @param obj
	 * @return
	 */
	public static JsonElement toJsonTree(Object obj){
		return getGson().toJsonTree(obj);
	}
	
	/**
	 * 将字符串转化为JsonElement
	 * @param json json格式的字符串
	 * @return
	 */
	public static JsonElement parse(String json){
		JsonParser jsonParser = new JsonParser();
		return jsonParser.parse(json);
	}
	
	//遍历JsonObject的所有属性进行融合
	private static void extend(JsonObject sourceJob,JsonObject newJob){
		Iterator<Entry<String, JsonElement>> it = newJob.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, JsonElement> next = it.next();
			String key = next.getKey();
			JsonElement oldElement = sourceJob.get(key);
			JsonElement newElement = newJob.get(key);
			if(BeanUtils.isNotEmpty(newElement)){
				if(BeanUtils.isEmpty(oldElement)){
					sourceJob.add(key, newElement);
					continue;
				}
				if(newElement.isJsonArray()){
					if(oldElement.isJsonArray()){
						oldElement.getAsJsonArray().addAll(newElement.getAsJsonArray());
					}
				}
				if(newElement.isJsonObject()){
					extend(oldElement.getAsJsonObject(), newElement.getAsJsonObject());
				}
				if(newElement.isJsonPrimitive()){
					sourceJob.add(key, newElement);
				}
			}
		}
		
	}
	
	/**
	 * 融合JSON
	 * @param sourceJson 源JSON对象
	 * @param newJson 新JSON对象
	 */
	public static void merge(JsonElement sourceJson,JsonElement newJson){
		if(sourceJson.isJsonObject()){
			if(newJson.isJsonObject()){
				extend(sourceJson.getAsJsonObject(), newJson.getAsJsonObject());
			}
		}
		if(sourceJson.isJsonArray()){
			JsonArray asJsonArray = sourceJson.getAsJsonArray();
			if(newJson.isJsonObject()){
				asJsonArray.add(newJson);
			}
			if(newJson.isJsonArray()){
				asJsonArray.addAll(newJson.getAsJsonArray());
			}
		}
	}
}
