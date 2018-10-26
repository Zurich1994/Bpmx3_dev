package com.hotent.BusinessCollectCot.controller.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TimeOperate {
	

	
	public static int getTypeIndex(String timeType){
		Map<String,Integer> timeTypeMap = new HashMap<String,Integer>();
		timeTypeMap.put("year", 0);
		timeTypeMap.put("month", 1);
		timeTypeMap.put("day", 2);
		timeTypeMap.put("hour", 3);
		timeTypeMap.put("minute", 4);
		int typeIndex = timeTypeMap.get(timeType);
		return typeIndex;
	}
	
	public static String timeOperate(String startTime,String endTime){
		String[] startStrs = strOperate(startTime);
		String[] endStrs = strOperate(endTime);
		if(!(startStrs[0].equals(endStrs[0]))){
			return "year";
		}else if((startStrs[0].equals(endStrs[0])) && !(startStrs[1].equals(endStrs[1])) ){
			return "month";
		}else if((startStrs[0].equals(endStrs[0])) && (startStrs[1].equals(endStrs[1])) && !(startStrs[2].equals(endStrs[2]))){
			return "day";
		}else if((startStrs[0].equals(endStrs[0]))  && (startStrs[1].equals(endStrs[1])) && (startStrs[2].equals(endStrs[2])) && !(startStrs[3].equals(endStrs[3]))){
			return "hour";
		}else if((startStrs[0].equals(endStrs[0]))  && (startStrs[1].equals(endStrs[1])) && (startStrs[2].equals(endStrs[2])) && (startStrs[3].equals(endStrs[3])) && !(startStrs[4].equals(endStrs[4])) ){
			return "minute";
		}
			
		return "null";    
	}
	
	//发生量计算
	public static List<String> getOccurenceTimeList(String startTime, String endTime){
		String[] startStrs = strOperate(startTime);
		String[] endStrs = strOperate(endTime);
		String timeType = timeOperate(startTime,endTime);
		int typeIndex = getTypeIndex(timeType);
		System.out.println("typeIndex:"+typeIndex);
		int timeLength = Integer.parseInt(endStrs[typeIndex])- Integer.parseInt(startStrs[typeIndex]);
		System.out.println("timeLength:"+timeLength);
		List<String> timeList = new ArrayList<String>();
		for(int i=0;i<=timeLength;i++){
			timeList.add(String.valueOf(Integer.parseInt(startStrs[typeIndex])+i));
		}
		
		return timeList;
	}
	
	public static String[] strOperate(String timeStr){
		String[] strs1 = timeStr.split(" ");
		String[] strs2 = strs1[0].split("-");
		String[] strs3 = strs1[1].split(":");
		String[] timeStrs = new String[6];
		for(int i=0;i<strs2.length;i++){
			timeStrs[i] = strs2[i];
		}
		for(int i=0;i<strs3.length;i++){
			timeStrs[i+3]=strs3[i];
		}
		for(int i=0;i<timeStrs.length;i++){
			if(timeStrs[i].charAt(0)  == '0'){
				String newStr = timeStrs[i].substring(1);
				timeStrs[i] = newStr;
			}
		}
		return timeStrs;
	}
	
	public static String[] strConvert(String[] strs){
		for(int i=0;i<strs.length;i++){
			if(strs[i].charAt(0)  == '0'){
				String newStr = strs[i].substring(1);
				strs[i] = newStr;
			}
		}
		return strs;
	}
	
	public static String timeSubString(String timeStr){
		int n = timeStr.length();
		String newTimeStr = timeStr.substring(0, n-1);
		return newTimeStr;
	}
	
	public static void printStr(String[] strs){
		System.out.println("-----------------");
		for(int i=0;i<strs.length;i++){
			System.out.println(strs[i]);
		}
		System.out.println("-----------------");
	}
	
	public static String getTimeType(String timeType,String timeStr){
		String newStr = null;
		if(timeType.equals("year")){
			newStr = timeStr+"年";
		}else if(timeType.equals("month")){
			newStr = timeStr+"月";
		}else if(timeType.equals("day")){
			newStr = timeStr+"号";
		}else if(timeType.equals("hour")){
			newStr = timeStr+"时";
		}else if(timeType.equals("minute")){
			newStr = timeStr+"分";
		}
		return newStr;
	}
	
	
	
	public static void main(String [] args){
		String startTime = "2013-08-10 15:42:03";
		String endTime = "2015-08-10 15:11:16";
		String[] startTimes = strOperate(startTime);
		String[] endTimes = strOperate(endTime);
		int year = Integer.parseInt(endTimes[0]) - Integer.parseInt(startTimes[0]);
		System.out.println("year:"+year);
		//System.out.println(startTimes[0].equals(endTimes[0]));
		//System.out.println(startTimes[1].equals(endTimes[1]));
		//printStr(startTimes);
		//printStr(endTimes);
		//if(startTimes[0] != endTimes[0]){
		//	System.out.println("duck");
		//}
		//System.out.println("time:"+timeOperate(startTime,endTime));
		//String[] startStrs = strOperate(startTime);
		//char monthStr2 = startStrs[1].charAt(0);
		//System.out.println(monthStr2);
		//String monthStr = startStrs[1].substring(1);
		//System.out.println(monthStr);
	}
	
}
