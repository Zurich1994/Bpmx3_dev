package com.hotent.platform.model.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:常用语管理 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-03-16 10:53:20
 */
@XmlRootElement(name = "taskApprovalItems")
@XmlAccessorType(XmlAccessType.NONE)
public class TaskApprovalItems extends BaseModel
{	

	//全局
	public final static Short TYPE_GLOBAL = 1; 
	//对于流程分类
	public final static Short TYPE_FLOWTYPE = 2; 
	//对于流程
	public final static Short TYPE_FLOW = 3;
	//对于个人的常用语
	public final static Short TYPE_USER =4;
	
	// 主键
	@XmlAttribute
	protected Long itemId;
	// 流程分类ID
	@XmlAttribute
	protected Long typeId;
	// actDefId
	@XmlAttribute
	protected String defKey;
	// 常用语分类
	@XmlAttribute
	protected Short type;
	// expression
	@XmlAttribute
	protected String expression;
	// 用户ID
	@XmlAttribute
	protected Long userId;

	
	
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}



	public String getDefKey() {
		return defKey;
	}

	public void setDefKey(String defKey) {
		this.defKey = defKey;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
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
		if (!(object instanceof TaskApprovalItems)) 
		{
			return false;
		}
		TaskApprovalItems rhs = (TaskApprovalItems) object;
		return new EqualsBuilder()
		.append(this.itemId, rhs.itemId)
		.append(this.type, rhs.type)
		.append(this.defKey, rhs.defKey)
		.append(this.typeId, rhs.typeId)
		.append(this.expression, rhs.expression)
		.append(this.userId, rhs.userId)
		.isEquals();
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.itemId) 
		.append(this.type) 
		.append(this.defKey)
		.append(this.typeId)
		.append(this.expression) 
		.append(this.userId)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("itemId", this.itemId) 
		.append("type", this.type) 
		.append("defKey", this.defKey) 
		.append("typeId", this.typeId) 
		.append("expression", this.expression)
		.append("userId",this.userId)
		.toString();
	}
   
  

}