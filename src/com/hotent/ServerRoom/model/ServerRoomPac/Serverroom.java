package com.hotent.ServerRoom.model.ServerRoomPac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:机房信息表 Model对象
 */
public class Serverroom extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *机房编号
	 */
	protected String  room_ID;
	/**
	 *机房名称
	 */
	protected String  room_name;
	/**
	 *地址
	 */
	protected String  address;
	/**
	 *备注
	 */
	protected String  remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setRoom_ID(String room_ID) 
	{
		this.room_ID = room_ID;
	}
	/**
	 * 返回 机房编号
	 * @return
	 */
	public String getRoom_ID() 
	{
		return this.room_ID;
	}
	public void setRoom_name(String room_name) 
	{
		this.room_name = room_name;
	}
	/**
	 * 返回 机房名称
	 * @return
	 */
	public String getRoom_name() 
	{
		return this.room_name;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	/**
	 * 返回 地址
	 * @return
	 */
	public String getAddress() 
	{
		return this.address;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemark() 
	{
		return this.remark;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Serverroom)) 
		{
			return false;
		}
		Serverroom rhs = (Serverroom) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.id, rhs.id)
		.append(this.room_ID, rhs.room_ID)
		.append(this.room_name, rhs.room_name)
		.append(this.address, rhs.address)
		.append(this.remark, rhs.remark)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.id) 
		.append(this.room_ID) 
		.append(this.room_name) 
		.append(this.address) 
		.append(this.remark) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("id", this.id) 
		.append("room_ID", this.room_ID) 
		.append("room_name", this.room_name) 
		.append("address", this.address) 
		.append("remark", this.remark) 
		.toString();
	}

}
