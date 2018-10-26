package com.hotent.core.web.query.scan;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.util.StringUtil;

/**
 * 表字段。
 * 
 * @author csx
 * 
 */
public class TableField {
	/**
	 * 字段类型
	 */
	private String fieldType;
	/**
	 * 字段名称
	 */
	private String name;
	/**
	 * 实体的参数名，用于sql中，绑定使用。
	 */
	private String var;

	/**
	 * 字段描述
	 */
	private String desc;
	/**
	 * 可选值和名称的串。每组由值和名称构成，组之间用分号分隔。 如：0未激活;1=激活;2=禁用
	 */
	private String options;

	/**
	 * 控件类型
	 */
	private short controlType;
	/**
	 * 字段格式,日期格式或者数字格式
	 */
	private String style;

	/**
	 * 数据库类型
	 */
	private String dataType;

	public TableField() {

	}

	public TableField(java.lang.reflect.Field field,
			com.hotent.core.annotion.query.Field qField) {
		this.fieldType = field.getType().getName();
		this.var = field.getName();
		this.name = qField.name();
		this.desc = qField.desc();
		if (StringUtil.isNotEmpty(qField.options()))
			this.options = qField.options();
		this.controlType = qField.controlType();
		this.style = qField.style();
		this.dataType = qField.dataType();
	}

	public String getFieldType() {
		return fieldType;
	}

	public String getName() {
		return name;
	}

	public String getVar() {
		return var;
	}

	public String getDesc() {
		return desc;
	}

	/**
	 * @return the options
	 */
	public String getOptions() {
		return options;
	}

	public short getControlType() {
		return controlType;
	}

	public String getStyle() {
		return style;
	}

	public String getDataType() {
		return dataType;
	}


	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fieldType", fieldType)
				.append("name", name).append("desc", desc)
				.append("dataType", dataType).toString();
	}
}
