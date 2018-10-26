package com.hotent.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * JSON帮助类
 * 
 * @author zxh
 * 
 */
public class JSONUtil {

	/**
	 * 判断JSON是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof JSONObject)
			return ((JSONObject) obj).isNullObject();
		if (obj instanceof JSONArray) {
			return ((JSONArray) obj).isEmpty();
		}
		return JSONNull.getInstance().equals(obj);
	}

	/**
	 * 判断JSON 是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(JSONObject obj) {
		return !isEmpty(obj);
	}
	
	/**
	 * 替换掉包含富文本的json 字符串中特殊的字符
	 * @param str
	 * @return
	 */
	public static String escapeSpecialChar(String str){
        StringBuffer sb = new StringBuffer();      
        for (int i=0; i<str.length(); i++) { 
        
            char c = str.charAt(i);      
            switch (c) {      
            case '\"':      
                sb.append("\\\"");      
                break;      
            case '\\':      
                sb.append("\\\\");      
                break;      
            case '/':      
                sb.append("\\/");      
                break;      
            case '\b':      
                sb.append("\\b");      
                break;      
            case '\f':      
                sb.append("\\f");      
                break;      
            case '\n':      
                sb.append("\\n");      
                break;      
            case '\r':      
                sb.append("\\r");      
                break;      
            case '\t':      
                sb.append("\\t");      
                break;      
            default:      
                sb.append(c);   
            }
        }
		        
		return sb.toString();      
	}
	

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(JSONUtil.class);
		JSONArray jsonAry = new JSONArray();
		JSONObject json = new JSONObject();
		jsonAry.add(json);
		logger.info(String.valueOf(JSONUtil.isEmpty(jsonAry)));
	}

}
