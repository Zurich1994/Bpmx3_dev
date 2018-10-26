package com.hotent.core.util;


public class LongUtil {
	/**
     */
    public static Long [] transStrsToLongs(String [] strs){
    	Long[] longs =  new Long[strs.length];
    	for(int i=0 ; i < strs.length ; i++){
    		longs[i]=Long.parseLong(strs[i]);
    	}
    	return longs;
    }
    /**
     */
    public static Long [] transStrsToLongs(String str){
    	if(StringUtil.isNotEmpty(str)){
    		return LongUtil.transStrsToLongs(str.split(","));
    	}
    	return null;
    }
}
