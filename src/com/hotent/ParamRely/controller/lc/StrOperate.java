package com.hotent.ParamRely.controller.lc;


public class StrOperate {
	public static String processIdStrConvert(String str){
		System.out.println(str);
		String newStr = str.replaceAll(",", "\"");
		String newStr1 = newStr.replaceAll("\"\"\"", "\"");
		String newStr2 = newStr1.replaceAll("]", "\"");
		String newStr3 = newStr2.replaceAll("\\[", "\"");
		String newStr4 = newStr3.replaceAll("\"\"" ," ");
		String newStr5 = newStr4.trim();
		return newStr5;
	}
	
	public static String paramStrConvert(String str){
		System.out.println(str);
		String newStr = str.replaceAll(":", "\"");
		String newStr1 = newStr.replaceAll(",", "\"");
		String newStr2 = newStr1.replaceAll("\"\"\"", "\"");
		String newStr3 = newStr2.replaceAll("}", "\"");
		String newStr4 = newStr3.replaceAll("\\{", "\"");
		String newStr5 = newStr4.replaceAll("\"\"" ," ");
		String newStr6 = newStr5.trim();
		return newStr6;
	}
}
