package com.hotent.BpmFormBang.model.bpmFormBang;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:绑定表 Model对象
 */
public class BpmFormBang extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *按钮名称
	 */
	protected String  btn_name;
	/**
	 *按钮类型
	 */
	protected String  btn_type;
	/**
	 *按钮概率
	 */
	protected String  btn_probability;
	/**
	 *在线流程流程定义Id
	 */
	protected String  defbId;
	/**
	 *表单Id
	 */
	protected String  formId;
	/**
	 *流程定义Id
	 */
	protected String  defId;
	/**
	 *节点Id
	 */
	protected String  nodeId;
	/**
	 *自定义按钮Id
	 */
	protected String  xId;
	/**
	 *表单定义Id
	 */
	protected String  formDefId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setBtn_name(String btn_name) 
	{
		this.btn_name = btn_name;
	}
	/**
	 * 返回 按钮名称
	 * @return
	 */
	public String getBtn_name() 
	{
		return this.btn_name;
	}
	public void setBtn_type(String btn_type) 
	{
		this.btn_type = btn_type;
	}
	/**
	 * 返回 按钮类型
	 * @return
	 */
	public String getBtn_type() 
	{
		return this.btn_type;
	}
	public void setBtn_probability(String btn_probability) 
	{
		this.btn_probability = btn_probability;
	}
	/**
	 * 返回 按钮概率
	 * @return
	 */
	public String getBtn_probability() 
	{
		return this.btn_probability;
	}
	public void setDefbId(String defbId) 
	{
		this.defbId = defbId;
	}
	/**
	 * 返回 在线流程流程定义Id
	 * @return
	 */
	public String getDefbId() 
	{
		return this.defbId;
	}
	public void setFormId(String formId) 
	{
		this.formId = formId;
	}
	/**
	 * 返回 表单Id
	 * @return
	 */
	public String getFormId() 
	{
		return this.formId;
	}
	public void setDefId(String defId) 
	{
		this.defId = defId;
	}
	/**
	 * 返回 流程定义Id
	 * @return
	 */
	public String getDefId() 
	{
		return this.defId;
	}
	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 节点Id
	 * @return
	 */
	public String getNodeId() 
	{
		return this.nodeId;
	}
	public void setXId(String xId) 
	{
		this.xId = xId;
	}
	/**
	 * 返回 自定义按钮Id
	 * @return
	 */
	public String getXId() 
	{
		return this.xId;
	}
	public void setFormDefId(String formDefId) 
	{
		this.formDefId = formDefId;
	}
	/**
	 * 返回 表单定义Id
	 * @return
	 */
	public String getFormDefId() 
	{
		return this.formDefId;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmFormBang)) 
		{
			return false;
		}
		BpmFormBang rhs = (BpmFormBang) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.btn_name, rhs.btn_name)
		.append(this.btn_type, rhs.btn_type)
		.append(this.btn_probability, rhs.btn_probability)
		.append(this.defbId, rhs.defbId)
		.append(this.formId, rhs.formId)
		.append(this.defId, rhs.defId)
		.append(this.nodeId, rhs.nodeId)
		.append(this.xId, rhs.xId)
		.append(this.formDefId, rhs.formDefId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.btn_name) 
		.append(this.btn_type) 
		.append(this.btn_probability) 
		.append(this.defbId) 
		.append(this.formId) 
		.append(this.defId) 
		.append(this.nodeId) 
		.append(this.xId) 
		.append(this.formDefId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("btn_name", this.btn_name) 
		.append("btn_type", this.btn_type) 
		.append("btn_probability", this.btn_probability) 
		.append("defbId", this.defbId) 
		.append("formId", this.formId) 
		.append("defId", this.defId) 
		.append("nodeId", this.nodeId) 
		.append("xId", this.xId) 
		.append("formDefId", this.formDefId) 
		.toString();
	}

}
