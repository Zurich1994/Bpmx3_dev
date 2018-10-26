package com.hotent.core.log;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于 系统日志 保存线程相关的信息 
 * @author Raise
 *
 */
public class SysAuditThreadLocalHolder {
	
	public static final short RESULT_SUCCESS 					= 0;
	public static final short RESULT_FAIL 						= 0;
	
	/**
	 * 日志明细信息
	 */
	private static ThreadLocal<String> detailLocal=new ThreadLocal<String>();
	/**
	 * 执行结果
	 */
	private static ThreadLocal<Short> resultLocal = new ThreadLocal<Short>();
	/**
	 * 参数
	 */
	private static ThreadLocal<Map<String,Object>> paramertersLocal = new ThreadLocal<Map<String,Object>>();
	/**
	 * 
	 */
	private static ThreadLocal<Boolean> shouldLogLocal = new ThreadLocal<Boolean>();
	
	public static String getDetail(){
		return detailLocal.get();
	}
	
	public static void setDatail(String detail){
		detailLocal.set(detail);
	}
	
	
	public static void clearDetail(){
		detailLocal.remove();
	}
	
	public static Short getResult(){
		return resultLocal.get();
	}
	
	public static void setResult(Short result){
		resultLocal.set(result);
	}
	public static void clearResult(){
		resultLocal.remove();
	}
	
	public static Boolean getShouldLog(){
		return shouldLogLocal.get();
	}
	public static void setShouldLog(Boolean shouldLog){
		shouldLogLocal.set(shouldLog);
	}
	public static void clearShouldLog(){
		shouldLogLocal.remove();
	}
	
	public static Map<String,Object> getParamerters(){
		if(paramertersLocal.get()==null){
			paramertersLocal.set(new HashMap<String, Object>());
		}
		return paramertersLocal.get();
	}
	
	public static Object getParamerter(String key){
		if(paramertersLocal.get()==null){
			paramertersLocal.set(new HashMap<String, Object>());
		}
		return paramertersLocal.get().get(key);
	}
	
	public static void putParamerter(String key,Object value){
		if(paramertersLocal.get()==null){
			paramertersLocal.set(new HashMap<String, Object>());
		}
		paramertersLocal.get().put(key, value);
	}
	public static void clearParameters(){
		paramertersLocal.remove();
	}
}
