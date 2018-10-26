package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 对象功能:系统配置参数表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-04-16 11:20:41
 */
public class SysProperty extends BaseModel{
	
	public static final String PropertyCache="PropertyCache";
	
	/**
	 * 全局流水号。
	 */
	public static final String GlobalFlowNo="globalFlowNo";
	
	
	
	// 主键
	protected Long  id;
	// 名称
	protected String  name;
	// 别名
	protected String  alias;
	// 值
	protected String  value;
	// 分组
	protected String  group;
	// 排序
	protected Long  sn;
	
	//备注
	protected String  memo;

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
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setAlias(String alias){
		this.alias = alias;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}
	public void setValue(String value){
		this.value = value;
	}
	/**
	 * 返回 值
	 * @return
	 */
	public String getValue() {
		return this.value;
	}
	public void setGroup(String group){
		this.group = group;
	}
	/**
	 * 返回 分组
	 * @return
	 */
	public String getGroup() {
		return this.group;
	}
	public void setSn(Long sn){
		this.sn = sn;
	}
	/**
	 * 返回 排序
	 * @return
	 */
	public Long getSn() {
		return this.sn;
	}
	

   	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysProperty)) 
		{
			return false;
		}
		SysProperty rhs = (SysProperty) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.alias, rhs.alias)
		.append(this.value, rhs.value)
		.append(this.group, rhs.group)
		.append(this.sn, rhs.sn)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.alias) 
		.append(this.value) 
		.append(this.group) 
		.append(this.sn) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("alias", this.alias) 
		.append("value", this.value) 
		.append("group", this.group) 
		.append("sn", this.sn) 
		.toString();
	}
   
  

}