package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:自定义SQL字段定义 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-06-17 09:02:24
 */
public class SysQueryMetaField extends BaseModel{
	private static final long serialVersionUID = 1L;
	// 主键
	protected Long id;
	// SQLID
	protected Long sqlId;
	// 字段名
	protected String name;
	// 实际字段名
	protected String fieldName;
	// 字段备注
	protected String fieldDesc;
	// 是否可见
	protected Short isShow;
	// 是否搜索
	protected Short isSearch;
	// 控件类型
	protected Short controlType;
	// 数据类型
	protected String dataType;
	// 是否衍生列
	protected Short isVirtual;
	// 衍生列来自列
	protected String virtualFrom;
	// 来自类型 {1:"数据字典",2:"sql",3:"下拉框"}
	protected String resultFromType;
	// 衍生列配置
	protected String resultFrom;
	// 报警设定
	protected String alarmSetting;
	// DATE_FORMAT
	protected String dateFormat;
	// control_content
	protected String controlContent;
	// 列连接地址
	protected String url="";
	//格式函数
	protected String formater="";

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setSqlId(Long sqlId){
		this.sqlId = sqlId;
	}
	/**
	 * 返回 SQLID
	 * @return
	 */
	public Long getSqlId() {
		return this.sqlId;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 字段名
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setFieldName(String fieldName){
		this.fieldName = fieldName;
	}
	/**
	 * 返回 实际字段名
	 * @return
	 */
	public String getFieldName() {
		return this.fieldName;
	}
	public void setFieldDesc(String fieldDesc){
		this.fieldDesc = fieldDesc;
	}
	/**
	 * 返回 字段备注
	 * @return
	 */
	public String getFieldDesc() {
		return this.fieldDesc;
	}
	public void setIsShow(Short isShow){
		this.isShow = isShow;
	}
	/**
	 * 返回 是否可见
	 * @return
	 */
	public Short getIsShow() {
		return this.isShow;
	}
	public void setIsSearch(Short isSearch){
		this.isSearch = isSearch;
	}
	/**
	 * 返回 是否搜索
	 * @return
	 */
	public Short getIsSearch() {
		return this.isSearch;
	}
	public void setControlType(Short controlType){
		this.controlType = controlType;
	}
	/**
	 * 返回 控件类型
	 * @return
	 */
	public Short getControlType() {
		return this.controlType;
	}
	public void setDataType(String dataType){
		this.dataType = dataType;
	}
	/**
	 * 返回 数据类型
	 * @return
	 */
	public String getDataType() {
		return this.dataType;
	}
	public void setIsVirtual(Short isVirtual){
		this.isVirtual = isVirtual;
	}
	/**
	 * 返回 是否衍生列
	 * @return
	 */
	public Short getIsVirtual() {
		return this.isVirtual;
	}
	public void setVirtualFrom(String virtualFrom){
		this.virtualFrom = virtualFrom;
	}
	/**
	 * 返回 衍生列来自列
	 * @return
	 */
	public String getVirtualFrom() {
		return this.virtualFrom;
	}
	public void setResultFromType(String resultFromType){
		this.resultFromType = resultFromType;
	}
	/**
	 * 返回 来自类型
	 * {1:"数据字典",2:"sql",3:"下拉框"}
	 * @return
	 */
	public String getResultFromType() {
		return this.resultFromType;
	}
	public void setResultFrom(String resultFrom){
		this.resultFrom = resultFrom;
	}
	/**
	 * 返回 衍生列配置
	 * @return
	 */
	public String getResultFrom() {
		return this.resultFrom;
	}
	public void setAlarmSetting(String alarmSetting){
		this.alarmSetting = alarmSetting;
	}
	/**
	 * 返回 报警设定
	 * @return
	 */
	public String getAlarmSetting() {
		return this.alarmSetting;
	}
	public void setDateFormat(String dateFormat){
		this.dateFormat = dateFormat;
	}
	/**
	 * 返回 DATE_FORMAT
	 * @return
	 */
	public String getDateFormat() {
		return this.dateFormat;
	}
	public void setControlContent(String controlContent){
		this.controlContent = controlContent;
	}
	/**
	 * 返回 control_content
	 * @return
	 */
	public String getControlContent() {
		return this.controlContent;
	}
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 * 返回 列连接地址
	 * @return
	 */
	public String getUrl() {
		return this.url;
	}
	

   	public String getFormater() {
		return formater;
	}
	public void setFormater(String formater) {
		this.formater = formater;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysQueryMetaField)) 
		{
			return false;
		}
		SysQueryMetaField rhs = (SysQueryMetaField) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.sqlId, rhs.sqlId)
		.append(this.name, rhs.name)
		.append(this.fieldName, rhs.fieldName)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("sqlId", this.sqlId) 
		.append("name", this.name) 
		.append("fieldName", this.fieldName) 
		.append("fieldDesc", this.fieldDesc) 
		.append("isShow", this.isShow) 
		.append("isSearch", this.isSearch) 
		.append("controlType", this.controlType) 
		.append("dataType", this.dataType) 
		.append("isVirtual", this.isVirtual) 
		.append("virtualFrom", this.virtualFrom) 
		.append("resultFromType", this.resultFromType) 
		.append("resultFrom", this.resultFrom) 
		.append("alarmSetting", this.alarmSetting) 
		.append("dateFormat", this.dateFormat) 
		.append("controlContent", this.controlContent) 
		.append("url", this.url) 
		.toString();
	}

}