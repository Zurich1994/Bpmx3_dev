package com.hotent.Xlkcs.model.Xlkcs;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:下拉框测试 Model对象
 */
public class Xlkcs extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *设备品牌
	 */
	protected String  yh;
	/**
	 *字段2
	 */
	protected String  zd2;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setYh(String yh) 
	{
		this.yh = yh;
	}
	/**
	 * 返回 设备品牌
	 * @return
	 */
	public String getYh() 
	{
		return this.yh;
	}
	public void setZd2(String zd2) 
	{
		this.zd2 = zd2;
	}
	/**
	 * 返回 字段2
	 * @return
	 */
	public String getZd2() 
	{
		return this.zd2;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Xlkcs)) 
		{
			return false;
		}
		Xlkcs rhs = (Xlkcs) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.yh, rhs.yh)
		.append(this.zd2, rhs.zd2)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.yh) 
		.append(this.zd2) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("yh", this.yh) 
		.append("zd2", this.zd2) 
		.toString();
	}

}
