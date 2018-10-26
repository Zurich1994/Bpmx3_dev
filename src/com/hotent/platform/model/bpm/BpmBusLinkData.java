package com.hotent.platform.model.bpm;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:业务流程关联表数据 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2012-02-14 09:19:33
 */
public class BpmBusLinkData extends BaseModel
{
	// dataId
	protected Long dataId;
	// 表名
	protected String tableName;
	// 主键名
	protected String pkName;
	// 流程运行ID
	protected Long runId;
	// 主键值
	protected String pkValue;
	// 流程定义ID
	protected String actDefId;
	//用户ID
	protected Long userId=0L;

	public void setDataId(Long dataId) 
	{
		this.dataId = dataId;
	}
	/**
	 * 返回 dataId
	 * @return
	 */
	public Long getDataId() 
	{
		return dataId;
	}

	public void setTableName(String tableName) 
	{
		this.tableName = tableName;
	}
	/**
	 * 返回 表名
	 * @return
	 */
	public String getTableName() 
	{
		return tableName;
	}

	public void setPkName(String pkName) 
	{
		this.pkName = pkName;
	}
	/**
	 * 返回 主键名
	 * @return
	 */
	public String getPkName() 
	{
		return pkName;
	}

	public void setRunId(Long runId) 
	{
		this.runId = runId;
	}
	/**
	 * 返回 流程运行ID
	 * @return
	 */
	public Long getRunId() 
	{
		return runId;
	}

	public void setPkValue(String pkValue) 
	{
		this.pkValue = pkValue;
	}
	/**
	 * 返回 主键值
	 * @return
	 */
	public String getPkValue() 
	{
		return pkValue;
	}

	public void setActDefId(String actDefId) 
	{
		this.actDefId = actDefId;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getActDefId() 
	{
		return actDefId;
	}
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmBusLinkData)) 
		{
			return false;
		}
		BpmBusLinkData rhs = (BpmBusLinkData) object;
		return new EqualsBuilder()
		.append(this.dataId, rhs.dataId)
		.append(this.tableName, rhs.tableName)
		.append(this.pkName, rhs.pkName)
		.append(this.runId, rhs.runId)
		.append(this.pkValue, rhs.pkValue)
		.append(this.actDefId, rhs.actDefId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.dataId) 
		.append(this.tableName) 
		.append(this.pkName) 
		.append(this.runId) 
		.append(this.pkValue) 
		.append(this.actDefId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("dataId", this.dataId) 
		.append("tableName", this.tableName) 
		.append("pkName", this.pkName) 
		.append("runId", this.runId) 
		.append("pkValue", this.pkValue) 
		.append("actDefId", this.actDefId) 
		.toString();
	}
   
  

}