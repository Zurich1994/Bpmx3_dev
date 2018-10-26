package com.hotent.eventType.model.eventType;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:事件类型表 Model对象
 */
public class EventType extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *事件名称
	 */
	protected String  eventName;
	/**
	 *备用字段1
	 */
	protected String  spare1;
	/**
	 *备用字段2
	 */
	protected String  spare2;
	/**
	 *备用字段3
	 */
	protected String  spare3;
	/**
	 *备用字段4
	 */
	protected String  spare4;
	/**
	 *备用字段5
	 */
	protected String  spare5;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEventName(String eventName) 
	{
		this.eventName = eventName;
	}
	/**
	 * 返回 事件名称
	 * @return
	 */
	public String getEventName() 
	{
		return this.eventName;
	}
	public void setSpare1(String spare1) 
	{
		this.spare1 = spare1;
	}
	/**
	 * 返回 备用字段1
	 * @return
	 */
	public String getSpare1() 
	{
		return this.spare1;
	}
	public void setSpare2(String spare2) 
	{
		this.spare2 = spare2;
	}
	/**
	 * 返回 备用字段2
	 * @return
	 */
	public String getSpare2() 
	{
		return this.spare2;
	}
	public void setSpare3(String spare3) 
	{
		this.spare3 = spare3;
	}
	/**
	 * 返回 备用字段3
	 * @return
	 */
	public String getSpare3() 
	{
		return this.spare3;
	}
	public void setSpare4(String spare4) 
	{
		this.spare4 = spare4;
	}
	/**
	 * 返回 备用字段4
	 * @return
	 */
	public String getSpare4() 
	{
		return this.spare4;
	}
	public void setSpare5(String spare5) 
	{
		this.spare5 = spare5;
	}
	/**
	 * 返回 备用字段5
	 * @return
	 */
	public String getSpare5() 
	{
		return this.spare5;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof EventType)) 
		{
			return false;
		}
		EventType rhs = (EventType) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.eventName, rhs.eventName)
		.append(this.spare1, rhs.spare1)
		.append(this.spare2, rhs.spare2)
		.append(this.spare3, rhs.spare3)
		.append(this.spare4, rhs.spare4)
		.append(this.spare5, rhs.spare5)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.eventName) 
		.append(this.spare1) 
		.append(this.spare2) 
		.append(this.spare3) 
		.append(this.spare4) 
		.append(this.spare5) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("eventName", this.eventName) 
		.append("spare1", this.spare1) 
		.append("spare2", this.spare2) 
		.append("spare3", this.spare3) 
		.append("spare4", this.spare4) 
		.append("spare5", this.spare5) 
		.toString();
	}

}
