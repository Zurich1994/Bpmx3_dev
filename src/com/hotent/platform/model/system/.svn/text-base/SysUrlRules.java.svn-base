package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:URL地址拦截脚本管理 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wdr
 * 创建时间:2014-03-27 16:32:01
 */
public class SysUrlRules extends BaseModel
{
	// 主键
	protected Long  id;
	// groovy脚本
	protected String  script;
	// 是否启用；0：禁用；1：启用
	protected Short  enable;
	// URL地址拦截管理ID
	protected Long  sysUrlId;
	// 描述
	protected String  descp;
	// 排序
	protected Short  sort;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setScript(String script) 
	{
		this.script = script;
	}
	/**
	 * 返回 groovy脚本
	 * @return
	 */
	public String getScript() 
	{
		return this.script;
	}
	public void setEnable(Short enable) 
	{
		this.enable = enable;
	}
	/**
	 * 返回 是否启用；0：禁用；1：启用
	 * @return
	 */
	public Short getEnable() 
	{
		return this.enable;
	}
	public void setSysUrlId(Long sysUrlId) 
	{
		this.sysUrlId = sysUrlId;
	}
	/**
	 * 返回 URL地址拦截管理ID
	 * @return
	 */
	public Long getSysUrlId() 
	{
		return this.sysUrlId;
	}
	public void setDescp(String descp) 
	{
		this.descp = descp;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getDescp() 
	{
		return this.descp;
	}
	public void setSort(Short sort) 
	{
		this.sort = sort;
	}
	/**
	 * 返回 排序
	 * @return
	 */
	public Short getSort() 
	{
		return this.sort;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysUrlRules)) 
		{
			return false;
		}
		SysUrlRules rhs = (SysUrlRules) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.script, rhs.script)
		.append(this.enable, rhs.enable)
		.append(this.sysUrlId, rhs.sysUrlId)
		.append(this.descp, rhs.descp)
		.append(this.sort, rhs.sort)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.script) 
		.append(this.enable) 
		.append(this.sysUrlId) 
		.append(this.descp) 
		.append(this.sort) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("script", this.script) 
		.append("enable", this.enable) 
		.append("sysUrlId", this.sysUrlId) 
		.append("descp", this.descp) 
		.append("sort", this.sort) 
		.toString();
	}
   
  

}