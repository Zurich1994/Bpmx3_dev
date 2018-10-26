package com.hotent.kvmSet.LineActionShowPath.model.LineActionShowCode;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:线路动作展现表 Model对象
 */
public class LineActionShow extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *线路id
	 */
	protected Long  line_id;
	/**
	 *时间
	 */
	protected Long  start_time;
	/**
	 *动作名称
	 */
	protected String  actionname;
	/**
	 *触发动作间隔时间
	 */
	protected String  actiontime;
	/**
	 *动作循环次数
	 */
	protected String  actioncount;
	/**
	 *重复次数
	 */
	protected String  sameaction_sign;
	/**
	 *重复次数1
	 */
	protected String  sameaction_sign1;
	/**
	 *线路颜色
	 */
	protected String  line_color;
	/**
	 *线路颜色1
	 */
	protected String  line_color1;
	/**
	 *传输信息
	 */
	protected String  line_info;
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
	
	public void setLine_id(Long line_id) 
	{
		this.line_id = line_id;
	}
	/**
	 * 返回 线路id
	 * @return
	 */
	public Long getLine_id() 
	{
		return this.line_id;
	}
	public void setStart_time(Long start_time) 
	{
		this.start_time = start_time;
	}
	/**
	 * 返回 时间
	 * @return
	 */
	public Long getStart_time() 
	{
		return this.start_time;
	}
	public void setActionname(String actionname) 
	{
		this.actionname = actionname;
	}
	/**
	 * 返回 动作名称
	 * @return
	 */
	public String getActionname() 
	{
		return this.actionname;
	}
	public void setActiontime(String actiontime) 
	{
		this.actiontime = actiontime;
	}
	/**
	 * 返回 触发动作间隔时间
	 * @return
	 */
	public String getActiontime() 
	{
		return this.actiontime;
	}
	public void setActioncount(String actioncount) 
	{
		this.actioncount = actioncount;
	}
	/**
	 * 返回 动作循环次数
	 * @return
	 */
	public String getActioncount() 
	{
		return this.actioncount;
	}
	public void setSameaction_sign(String sameaction_sign) 
	{
		this.sameaction_sign = sameaction_sign;
	}
	/**
	 * 返回 重复次数
	 * @return
	 */
	public String getSameaction_sign() 
	{
		return this.sameaction_sign;
	}
	public void setSameaction_sign1(String sameaction_sign1) 
	{
		this.sameaction_sign1 = sameaction_sign1;
	}
	/**
	 * 返回 重复次数1
	 * @return
	 */
	public String getSameaction_sign1() 
	{
		return this.sameaction_sign1;
	}
	public void setLine_color(String line_color) 
	{
		this.line_color = line_color;
	}
	/**
	 * 返回 线路颜色
	 * @return
	 */
	public String getLine_color() 
	{
		return this.line_color;
	}
	public void setLine_color1(String line_color1) 
	{
		this.line_color1 = line_color1;
	}
	/**
	 * 返回 线路颜色1
	 * @return
	 */
	public String getLine_color1() 
	{
		return this.line_color1;
	}
	public void setLine_info(String line_info) 
	{
		this.line_info = line_info;
	}
	/**
	 * 返回 传输信息
	 * @return
	 */
	public String getLine_info() 
	{
		return this.line_info;
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
		if (!(object instanceof LineActionShow)) 
		{
			return false;
		}
		LineActionShow rhs = (LineActionShow) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.line_id, rhs.line_id)
		.append(this.start_time, rhs.start_time)
		.append(this.actionname, rhs.actionname)
		.append(this.actiontime, rhs.actiontime)
		.append(this.actioncount, rhs.actioncount)
		.append(this.sameaction_sign, rhs.sameaction_sign)
		.append(this.sameaction_sign1, rhs.sameaction_sign1)
		.append(this.line_color, rhs.line_color)
		.append(this.line_color1, rhs.line_color1)
		.append(this.line_info, rhs.line_info)
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
		.append(this.start_time) 
		.append(this.actionname) 
		.append(this.actiontime) 
		.append(this.actioncount) 
		.append(this.sameaction_sign) 
		.append(this.sameaction_sign1) 
		.append(this.line_color) 
		.append(this.line_color1) 
		.append(this.line_info) 
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
		.append("start_time", this.start_time) 
		.append("actionname", this.actionname) 
		.append("actiontime", this.actiontime) 
		.append("actioncount", this.actioncount) 
		.append("sameaction_sign", this.sameaction_sign) 
		.append("sameaction_sign1", this.sameaction_sign1) 
		.append("line_color", this.line_color) 
		.append("line_color1", this.line_color1) 
		.append("line_info", this.line_info) 
		.append("a", this.a) 
		.append("b", this.b) 
		.append("c", this.c) 
		.toString();
	}

}
