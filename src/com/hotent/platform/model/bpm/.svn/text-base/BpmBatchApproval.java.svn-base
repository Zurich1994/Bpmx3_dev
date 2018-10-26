package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:流程批量审批定义设置 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-04-17 15:34:17
 */
public class BpmBatchApproval extends BaseModel{
	public static final String IS_SHOW_Y = "1";
	// 主键
	protected Long  id;
	// 流程定义key
	protected String defKey;
	// 节点ID
	protected String  nodeId;
	// 自定义表ID
	protected Long  tableId;
	// 字段设置
	protected String  fieldJson;
	
	
	//流程标题
	protected String subject;
	//节点名称
	protected String nodeName;
	
	protected Integer count;

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
	public void setDefKey(String defKey){
		this.defKey = defKey;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getDefKey() {
		return this.defKey;
	}
	public void setNodeId(String nodeId){
		this.nodeId = nodeId;
	}
	/**
	 * 返回 节点ID
	 * @return
	 */
	public String getNodeId() {
		return this.nodeId;
	}
	public void setTableId(Long tableId){
		this.tableId = tableId;
	}
	/**
	 * 返回 自定义表ID
	 * @return
	 */
	public Long getTableId() {
		return this.tableId;
	}
	public void setFieldJson(String fieldJson){
		this.fieldJson = fieldJson;
	}
	
	/**
	 * 返回 字段设置
	 * 
	 * @return
	 */
	public String getFieldJson() {
		return this.fieldJson;
	}

   	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmBatchApproval)) 
		{
			return false;
		}
		BpmBatchApproval rhs = (BpmBatchApproval) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.defKey, rhs.defKey)
		.append(this.nodeId, rhs.nodeId)
		.append(this.tableId, rhs.tableId)
		.append(this.fieldJson, rhs.fieldJson)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.defKey) 
		.append(this.nodeId) 
		.append(this.tableId) 
		.append(this.fieldJson) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("defKey", this.defKey) 
		.append("nodeId", this.nodeId) 
		.append("tableId", this.tableId) 
		.append("fieldJson", this.fieldJson) 
		.toString();
	}
   
  

}