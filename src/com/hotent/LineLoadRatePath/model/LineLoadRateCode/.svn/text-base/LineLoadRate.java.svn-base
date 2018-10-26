package com.hotent.LineLoadRatePath.model.LineLoadRateCode;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:线路负载值及利用率表 Model对象
 */
public class LineLoadRate extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *线路id
	 */
	protected String  line_id;
	/**
	 *发起时间
	 */
	protected Long  time;
	/**
	 *流量值
	 */
	protected Long  flownum;
	/**
	 *负载类型
	 */
	protected String  load_type;
	/**
	 *利用率
	 */
	protected Long  load_use_rate;
	/**
	 *a
	 */
	protected String  a;
	/**
	 *b
	 */
	protected String  b;
	/**
	 *c
	 */
	protected String  c;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setLine_id(String line_id) 
	{
		this.line_id = line_id;
	}
	/**
	 * 返回 线路id
	 * @return
	 */
	public String getLine_id() 
	{
		return this.line_id;
	}
	public void setTime(Long time) 
	{
		this.time = time;
	}
	/**
	 * 返回 发起时间
	 * @return
	 */
	public Long getTime() 
	{
		return this.time;
	}
	public void setFlownum(Long flownum) 
	{
		this.flownum = flownum;
	}
	/**
	 * 返回 流量值
	 * @return
	 */
	public Long getFlownum() 
	{
		return this.flownum;
	}
	public void setLoad_type(String load_type) 
	{
		this.load_type = load_type;
	}
	/**
	 * 返回 负载类型
	 * @return
	 */
	public String getLoad_type() 
	{
		return this.load_type;
	}
	public void setLoad_use_rate(Long load_use_rate) 
	{
		this.load_use_rate = load_use_rate;
	}
	/**
	 * 返回 利用率
	 * @return
	 */
	public Long getLoad_use_rate() 
	{
		return this.load_use_rate;
	}
	public void setA(String a) 
	{
		this.a = a;
	}
	/**
	 * 返回 a
	 * @return
	 */
	public String getA() 
	{
		return this.a;
	}
	public void setB(String b) 
	{
		this.b = b;
	}
	/**
	 * 返回 b
	 * @return
	 */
	public String getB() 
	{
		return this.b;
	}
	public void setC(String c) 
	{
		this.c = c;
	}
	/**
	 * 返回 c
	 * @return
	 */
	public String getC() 
	{
		return this.c;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof LineLoadRate)) 
		{
			return false;
		}
		LineLoadRate rhs = (LineLoadRate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.line_id, rhs.line_id)
		.append(this.time, rhs.time)
		.append(this.flownum, rhs.flownum)
		.append(this.load_type, rhs.load_type)
		.append(this.load_use_rate, rhs.load_use_rate)
		.append(this.a, rhs.a)
		.append(this.b, rhs.b)
		.append(this.c, rhs.c)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.line_id) 
		.append(this.time) 
		.append(this.flownum) 
		.append(this.load_type) 
		.append(this.load_use_rate) 
		.append(this.a) 
		.append(this.b) 
		.append(this.c) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("line_id", this.line_id) 
		.append("time", this.time) 
		.append("flownum", this.flownum) 
		.append("load_type", this.load_type) 
		.append("load_use_rate", this.load_use_rate) 
		.append("a", this.a) 
		.append("b", this.b) 
		.append("c", this.c) 
		.toString();
	}

}
