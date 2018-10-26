package edu.hrbeu.MDA.DBAccess.core;

import org.apache.commons.lang.StringUtils;

public class FieldTableUtil {
	/**
	 * 对字段增加表名的前缀。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param tableName
	 *            表名前缀，会将其置小写
	 * @param isAs
	 *            是否加as
	 * @return 修正后字段名
	 */
	public static String fixFieldName(String fieldName, String tableName,
			boolean isAs) {
		return fixFieldName(fieldName, fieldName, tableName, isAs);
	}

	/**
	 * 对字段增加表名的前缀。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param tableName
	 *            表名前缀，会将其置小写
	 * @return
	 */
	public static String fixFieldName(String fieldName, String tableName) {
		return fixFieldName(fieldName, tableName, false);
	}

	/**
	 * 对字段增加表名的前缀。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param entityName
	 *            实体名
	 * @param tableName
	 *            表名前缀，会将其置小写
	 * @param isAs
	 *            是否加as
	 * @return
	 */
	public static String fixFieldName(String fieldName, String entityName,
			String tableName, boolean isAs) {
		if (StringUtils.isEmpty(tableName))
			return fieldName;
		StringBuilder sb = new StringBuilder();
		sb.append(tableName.toLowerCase()).append(".").append(fieldName);
		if (isAs)
			sb.append(" AS ").append(entityName);
		return sb.toString();
	}

}
