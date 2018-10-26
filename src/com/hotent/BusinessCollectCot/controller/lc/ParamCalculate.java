package com.hotent.BusinessCollectCot.controller.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParamCalculate {
	public static List<String> paramCalculate(String[] counts,String[] times){
		int itemLength = counts.length;
		for(String str : times){
			System.out.println("str:"+str);
		}
		List<String> areas = new ArrayList<String>();
		areas.add(String.valueOf((Long.valueOf(counts[0])+0)*(Long.valueOf(times[0])-0)/2));
		System.out.println(areas.get(0));
		for(int i=0;i<counts.length-1;i++){
			System.out.println("times:"+times[i]);
			long areaResult = (Long.valueOf(counts[i])+Long.valueOf(counts[i+1]))*(Long.valueOf(times[i+1])-Long.valueOf(times[i]))/2;
			System.out.println("areaResult:"+areaResult);
			areas.add(String.valueOf(areaResult));
		}
		System.out.println("size:"+areas.size());
		double result = 0;
		for(int i=0;i<areas.size();i++){
			result += Double.valueOf(areas.get(i));
		}
		System.out.println("result:"+result);
		List<String> proportionList = new ArrayList<String>();
		for(int i=0;i<areas.size();i++){
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00"); 
			double proportionResult = (Double.valueOf(areas.get(i)) / result);
			proportionList.add(String.valueOf(proportionResult));
		}
		System.out.println("proportionList:"+proportionList.size());
		
		return proportionList;
		
	}
}
