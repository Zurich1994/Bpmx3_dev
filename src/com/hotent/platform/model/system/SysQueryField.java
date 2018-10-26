package com.hotent.platform.model.system;


import com.hotent.core.model.BaseModel;

/**
 * 对象功能:SYS_QUERY_FIELD Model对象 开发公司:广州宏天软件有限公司 开发人员:Aschs 创建时间:2014-05-22
 * 12:47:39
 */
public class SysQueryField extends BaseModel {
	private static final long serialVersionUID = 1L;
	/** 不显示 */
	public static final Short IS_NOT_SHOW = 0;
	/** 显示 */
	public static final Short IS_SHOW = 1;

	/** 不做查询字段 */
	public static final Short IS_NOT_SEARCH = 0;
	/** 不做查询字段 */
	public static final Short IS_SEARCH = 1;

	public static final String DATATYPE_VARCHAR = "varchar";
	public static final String DATATYPE_CLOB = "clob";
	public static final String DATATYPE_DATE = "date";
	public static final String DATATYPE_NUMBER = "number";

	/**
	 * ID
	 */
	protected Long id;
	/**
	 * sqlId
	 */
	protected Long sqlId;
	/**
	 * 名称
	 */
	protected String name;
	/**
	 * 类型
	 */
	protected String type;
	/**
	 * 字段描述
	 */
	protected String fieldDesc;
	/**
	 * 是否显示
	 */
	protected Short isShow;
	
	
	// get() and set()
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSqlId() {
		return sqlId;
	}
	public void setSqlId(Long sqlId) {
		this.sqlId = sqlId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	public Short getIsShow() {
		return isShow;
	}
	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}
	public Short getIsSearch() {
		return isSearch;
	}
	public void setIsSearch(Short isSearch) {
		this.isSearch = isSearch;
	}
	public Short getControlType() {
		return controlType;
	}
	public void setControlType(Short controlType) {
		this.controlType = controlType;
	}
	public String getControlContent() {
		return controlContent;
	}
	public void setControlContent(String controlContent) {
		this.controlContent = controlContent;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * 是否查询
	 */
	protected Short isSearch;
	/**
	 * 空间类型
	 */
	protected Short controlType;
	/**
	 * 控件内容，例如下拉框和数据字典需要装载一些信息
	 */
	protected String controlContent;
	/**
	 * 格式化，选填
	 */
	protected String format;


}