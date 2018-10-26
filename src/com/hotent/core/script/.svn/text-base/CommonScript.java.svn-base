package com.hotent.core.script;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hotent.core.engine.IScript;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;

/**
 * 通用脚本方法
 * 
 * @author zxh
 * 
 */
public class CommonScript implements IScript {

	/**
	 * 比较两个字符串是否相等，不区分大小写，如果两个均为空则也认为相等。
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public boolean equalsIgnoreCase(String str1, String str2) {
		return StringUtils.equalsIgnoreCase(str1, str2);
	}

	/**
	 * 比较两个字符串是否相等，如果两个均为空则也认为相等。
	 * @param str1
	 * @param str2
	 * @return
	 */
	public boolean equals(String str1, String str2) {
		return StringUtils.equals(str1, str2);
	}
	
	/**
	 * 将字符串转化为short类型（默认值0）
	 * 
	 * @param str
	 *            字符串值
	 * @return
	 */
	public short parseShort(String str) {
		return parseShort(str, (short) 0);
	}

	/**
	 * 将字符串转化为short类型
	 * 
	 * @param str
	 *            字符串值
	 * @param defaultValue
	 *            自定义默认
	 * @return 返回转化的值
	 */
	public short parseShort(String str, short defaultValue) {
		if (StringUtils.isEmpty(str))
			return defaultValue;
		return Short.parseShort(str);
	}	
	
	
	/**
	 * 将字符串转化为int类型（默认值0）
	 * 
	 * @param str
	 *            字符串值
	 * @return
	 */
	public int parseInt(String str) {
		return parseInt(str, 0);
	}

	/**
	 * 将字符串转化为int类型
	 * 
	 * @param str
	 *            字符串值
	 * @param defaultValue
	 *            自定义默认
	 * @return 返回转化的值
	 */
	public int parseInt(String str, int defaultValue) {
		if (StringUtils.isEmpty(str))
			return defaultValue;
		return Integer.parseInt(str);
	}

	/**
	 * 将字符串转化为long类型
	 * 
	 * @param str
	 *            字符串值
	 * @return 返回转化的值
	 */
	public long parseLong(String str) {
		return parseLong(str, 0L);
	}

	/**
	 * 将字符串转化为long类型
	 * 
	 * @param str
	 *            字符串值
	 * @param defaultValue
	 *            自定义默认
	 * @return 返回转化的值
	 */
	public long parseLong(String str, long defaultValue) {
		if (StringUtils.isEmpty(str))
			return defaultValue;
		return Long.parseLong(str);
	}
	
	
	/**
	 * 将字符串转化为float类型
	 * 
	 * @param str
	 *            字符串值
	 * @return 返回转化的值
	 */
	public float parseFloat(String str) {
		return parseFloat(str, 0f);
	}

	/**
	 * 将字符串转化为float类型
	 * 
	 * @param str
	 *            字符串值
	 * @param defaultValue
	 *            自定义默认
	 * @return 返回转化的值
	 */
	public float parseFloat(String str, float defaultValue) {
		if (StringUtils.isEmpty(str))
			return defaultValue;
		return Float.parseFloat(str);
	}	
	
	/**
	 * 将字符串转化为double类型
	 * 
	 * @param str
	 *            字符串值
	 * @return 返回转化的值
	 */
	public double parseDouble(String str) {
		return parseDouble(str, 0d);
	}

	/**
	 * 将字符串转化为double类型
	 * 
	 * @param str
	 *            字符串值
	 * @param defaultValue
	 *            自定义默认
	 * @return 返回转化的值
	 */
	public double parseDouble(String str, double defaultValue) {
		if (StringUtils.isEmpty(str))
			return defaultValue;
		return Double.parseDouble(str);
	}
	
	/**
	 * 将字符串转化为boolean类型（默认值false）
	 * 
	 * @param str
	 *            字符串值（如无值则返回缺省值, 如值为数字1，则返回true,不是数字1的）
	 * @return 返回转化的值
	 */
	public boolean parseBoolean(String str) {
		return parseBoolean(str, false);
	}

	/**
	 * 将字符串转化为long类型
	 * 
	 * @param str
	 *            字符串值 （如无值则返回缺省值, 如值为数字1，则返回true,不是数字1的）
	 * @param defaultValue
	 *            自定义默认
	 * @return 返回转化的值
	 */
	public Boolean parseBoolean(String str, Boolean defaultValue) {
		if (StringUtils.isEmpty(str))
			return defaultValue;
		if (StringUtils.isNumeric(str))
			return (Integer.parseInt(str) == 1 ? true : false);
		return Boolean.parseBoolean(str);
	}
	
	/**
	 * 将obj类型转换为string
	 * @param obj 如果是null返回空字符串
	 * @return
	 */
	public String parseString(Object obj) {
		if(obj == null)
			return "";
		return obj.toString();
	}
	
	/**
	 * 将obj类型转换为string
	 * @param obj 如果是null返回空字符串
	 * @return
	 */
	public String parseString(Object obj, String style) {
		if(obj == null)
			return "";
		if(obj instanceof java.util.Date)
			return DateFormatUtil.format((java.util.Date) obj, style);
		return obj.toString();
	
	}

	/**
	 * 转化日期格式 ，
	 *  如无值则返回缺省值,如有值则返回 yyyy-MM-dd HH:mm:ss 格式的日期,或者自定义格式的日期
	 * @param str
	 * @param style
	 * @return
	 * @throws Exception
	 */
	public Date parseDate(String str, String style) throws Exception {
		if (StringUtils.isEmpty(str))
			return null;
		if (StringUtils.isEmpty(style))
			style = "yyyy-MM-dd HH:mm:ss";
		return (Date) DateFormatUtil.parse(str, style);
	}
	/**
	 * 转化日期格式 ，
	 *  如无值则返回缺省值,如有值则返回 yyyy-MM-dd HH:mm:ss 格式的日期,或者自定义格式的日期
	 * @param str
	 * @param style
	 * @return
	 * @throws Exception
	 */
	public Date parseDate(String str,Date defaultValue, String style) throws Exception {
		if (StringUtils.isEmpty(str))
			return defaultValue;
		if (StringUtils.isEmpty(style))
			style = "yyyy-MM-dd HH:mm:ss";
		return (Date) DateFormatUtil.parse(str, style);
	}

	/**
	 * 2个日期比较
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return
	 * 	返回 0 表示时间日期相同
	 * 	返回 1 表示日期1>日期2
	 *  返回 -1 表示日期1<日期2
	 *  返回-10 表示日期1为null 
	 *  返回 10 表示日期2为null
	 */
	public  int compareTo(Date date1,Date date2){
		if(date1 == null && date2 == null)
			return 0;	
		if(date1 == null)
			return 10;
		if(date2 == null)
			return -10;
		return date1.compareTo(date2);
	}

	/**
	 * 2个字符串比较
	 * @param str1
	 * @param str2
	 * @return
	 * 	返回 0 表示字符串相同
	 * 	返回 1 表示字符串1>字符串2
	 *  返回 -1 表示字符串1<字符串2
	 *  返回-10 表示字符串1为"" 或null
	 *  返回 10 表示字符串2为""或null
	 */
	public  int compareTo(String str1,String str2){
		if(str1 == null && str2 == null)
			return 0;
		if(str1 == null)
			return 10;
		if(str2 == null)
			return -10;
		return str1.compareTo(str2);	
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public  boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * 判断对象是否为空(Map,Collection,String,Array,Long是否为空)
	 * @param obj
	 * @return
	 */
	public  boolean isEmpty(Object obj){
		return BeanUtils.isEmpty(obj);
	}
	
	/**
	 * 测试的方法
	 * @param args
	 */
	public static void main(String[] args) {
	
	}
}
