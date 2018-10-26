package com.hotent.platform.model.system;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:组织或人员参数属性 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-23 17:43:34
 */
public class SysParam extends BaseModel{
	
	/**
	 * OR条件
	 */
	public static final int CONDITION_OR=1;
	/**
	 * and 条件
	 */
	public static final int CONDITION_AND=2;
	/**
	 * 表达式
	 */
	public static final int CONDITION_EXP=3;
	
	
	
	
	public static final Map<String,String> DATA_COLUMN_MAP=new HashMap<String,String>();
	static {
		DATA_COLUMN_MAP.put("String", "paramValue");
		DATA_COLUMN_MAP.put("Date", "paramDateValue");
		DATA_COLUMN_MAP.put("Integer", "paramIntValue");
	}
	
	
	public static java.text.SimpleDateFormat PARAM_DATE_FORMAT=new SimpleDateFormat("yyyy-MM-DD");
	
	public static final Map<String,String> DATA_TYPE_MAP=new HashMap<String,String>();
	static {
		DATA_TYPE_MAP.put("String", "字符");
		DATA_TYPE_MAP.put("Date", "日期");
		DATA_TYPE_MAP.put("Integer", "数字");
	}
	
	public static final Map<String,String> CONDITION_US=new HashMap<String,String>();
	static {
		CONDITION_US.put("=", "=");
		CONDITION_US.put("&lt;", "<");
		CONDITION_US.put("&gt;", ">");
		CONDITION_US.put("!=", "!=");
		CONDITION_US.put("&gt;=", ">=");
		CONDITION_US.put("&lt;=", "<=");
		CONDITION_US.put("LIKE", "like");
	}
	
	
	/**
	 * 用户
	 */
	public static final short EFFECT_USER=1;
	/**
	 * 组织
	 */
	public static final short EFFECT_ORG=2;
	// paramId
	protected Long paramId;
	// 参数Key
	protected String paramKey;
	// 参数名
	protected String paramName;
	// 参数数据类型
	protected String dataType;
	// 
	protected Short effect;
	
	// 属于维度
	protected Long belongDem;
	
	//数据来源
	protected String sourceType;

	//数据来源key
	protected String sourceKey;
	
	//参数描述
	protected String description="";
	
	//状态
	protected Short status_;
	
	//分类
	protected String category;
	
	public Short getEffect() {
		return effect;
	}
	public void setEffect(Short effect) {
		this.effect = effect;
	}
	public void setParamId(Long paramId) 
	{
		this.paramId = paramId;
	}
	/**
	 * 返回 paramId
	 * @return
	 */
	public Long getParamId() 
	{
		return paramId;
	}

	public void setParamKey(String paramKey) 
	{
		this.paramKey = paramKey;
	}
	/**
	 * 返回 参数Key
	 * @return
	 */
	public String getParamKey() 
	{
		return paramKey;
	}

	public void setParamName(String paramName) 
	{
		this.paramName = paramName;
	}
	/**
	 * 返回 参数名
	 * @return
	 */
	public String getParamName() 
	{
		return paramName;
	}

	public void setDataType(String dataType) 
	{
		this.dataType = dataType;
	}
	/**
	 * 返回 参数数据类型
	 * @return
	 */
	public String getDataType() 
	{
		return dataType;
	}
	/**
	 * 返回 所属维度
	 * @return
	 */
	public Long getBelongDem() {
		return belongDem;
	}
	
	public void setBelongDem(Long belongDem) {
		this.belongDem = belongDem;
	}
	
	/**
	 * 返回数据来源
	 * @return
	 */
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	/**
	 * 返回数据来源key
	 * @return
	 */
	public String getSourceKey() {
		return sourceKey;
	}
	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}
	
	/**
	 * 返回参数描述
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 返回状态
	 * @return
	 */
	public Short getStatus_() {
		return status_;
	}
	public void setStatus_(Short status_) {
		this.status_ = status_;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysParam)) 
		{
			return false;
		}
		SysParam rhs = (SysParam) object;
		return new EqualsBuilder()
		.append(this.paramId, rhs.paramId)
		.append(this.paramKey, rhs.paramKey)
		.append(this.paramName, rhs.paramName)
		.append(this.dataType, rhs.dataType)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.paramId) 
		.append(this.paramKey) 
		.append(this.paramName) 
		.append(this.dataType) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("paramId", this.paramId) 
		.append("paramKey", this.paramKey) 
		.append("paramName", this.paramName) 
		.append("dataType", this.dataType) 
		.toString();
	}
   
  

}