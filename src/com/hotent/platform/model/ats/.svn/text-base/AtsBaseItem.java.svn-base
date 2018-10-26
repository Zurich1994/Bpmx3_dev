package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:基础数据 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-16 18:08:43
 */
public class AtsBaseItem extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4572479228036585509L;
	// 主键
	protected Long id;
	// 编码
	protected String code;
	// 名称
	protected String name;
	// 地址
	protected String url;
	// 是否系统预置
	protected Short isSys =AtsConstant.NO;
	// 描述
	protected String memo;

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
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 返回 编码
	 * @return
	 */
	public String getCode() {
		return this.code;
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
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 * 返回 地址
	 * @return
	 */
	public String getUrl() {
		return this.url;
	}
	public void setIsSys(Short isSys){
		this.isSys = isSys;
	}
	/**
	 * 返回 是否系统预置
	 * @return
	 */
	public Short getIsSys() {
		return this.isSys;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsBaseItem)) 
		{
			return false;
		}
		AtsBaseItem rhs = (AtsBaseItem) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.code, rhs.code)
		.append(this.name, rhs.name)
		.append(this.url, rhs.url)
		.append(this.isSys, rhs.isSys)
		.append(this.memo, rhs.memo)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.code) 
		.append(this.name) 
		.append(this.url) 
		.append(this.isSys) 
		.append(this.memo) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("code", this.code) 
		.append("name", this.name) 
		.append("url", this.url) 
		.append("isSys", this.isSys) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}