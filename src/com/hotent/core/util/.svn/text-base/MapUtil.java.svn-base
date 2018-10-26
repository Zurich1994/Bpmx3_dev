package com.hotent.core.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 根据键值在hashmap中取值。无视大小写
 * 
 */
public class MapUtil {
	/**
	 * 根据字段取得map中的字段字符串值,如果值为空，则返回 ""空串，无视大小写
	 * 
	 * @param map
	 * @param field
	 * @return
	 */
	public static String getString(Map<?, ?> map, String field) {
		field = field.toLowerCase();
		Set<?> set = map.keySet();
		Iterator<?> it = set.iterator();
		Hashtable<String, String> ht = new Hashtable<String, String>();
		while (it.hasNext()) {
			String key = (String) it.next();
			ht.put(key.toLowerCase(), key);
		}
		field = ht.get(field);
		Object obj = map.get(field);
		return (obj != null) ? obj.toString().trim() : "";
	}

	/**
	 * 取得map中字段的长整型值。取不到值返回-1.
	 * 
	 * @param map
	 * @param field
	 * @return
	 */
	public static long getLong(Map<?, ?> map, String field) {
		String value = (String) getString(map, field);
		if (value.equals(""))
			return -1;
		return Long.parseLong(value);
	}

	/**
	 * 取得map中字段的整型值。取不到值返回-1.
	 * 
	 * @param map
	 * @param field
	 * @return
	 */
	public static int getInt(Map<?, ?> map, String field) {
		String value = (String) getString(map, field);
		if (value.equals(""))
			return -1;
		return Integer.parseInt(value);
	}

	/**
	 * 取得map中字段的浮点数值。取不到值返回-1.
	 * 
	 * @param map
	 * @param field
	 * @return
	 */
	public static float getFloat(Map<?, ?> map, String field) {
		String value = (String) getString(map, field);
		if (value.equals(""))
			return -1;
		return Float.parseFloat(value);
	}

	/**
	 * 取得map中字段的double数值。取不到值返回-1.
	 * 
	 * @param map
	 * @param field
	 * @return
	 */
	public static double getDouble(Map<?, ?> map, String field) {
		String value = (String) getString(map, field);
		if (value.equals(""))
			return -1;
		return Double.parseDouble(value);
	}

	public static Object get(Map<?, ?> map, String field) {
		field = field.toLowerCase();
		Set<?> set = map.keySet();
		Iterator<?> it = set.iterator();
		Hashtable<String, String> ht = new Hashtable<String, String>();
		while (it.hasNext()) {
			String key = (String) it.next();
			ht.put(key.toLowerCase(), key);
		}
		field = ht.get(field);
		Object obj = map.get(field);
		return obj;
	}

	/**
	 * map映射成一个对象
	 * @param map
	 * @param obj
	 *            void
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
	public static Object transMap2Bean(Map<String, Object> map) {
		Object obj = new Object();
		if (map == null) {
			return null;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			System.out.println("transMap2Bean2 Error " + e);
		}
		return obj;
	}
	
	/**
	 * 
	 * 对象映射成map
	 * @param obj
	 * @return 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}
}
