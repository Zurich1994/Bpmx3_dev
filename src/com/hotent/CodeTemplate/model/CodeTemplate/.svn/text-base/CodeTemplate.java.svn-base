package com.hotent.CodeTemplate.model.CodeTemplate;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:流程模板 Model对象
 */
public class CodeTemplate extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *模版名称
	 */
	protected String  templatename;
	/**
	 *别名
	 */
	protected String  bm;
	/**
	 *模板类别
	 */
	protected String  templatetype;
	/**
	 *流程类别
	 */
	protected String  subjecttype;
	/**
	 *流程名称
	 */
	protected String  subjectname;
	/**
	 *流程Id
	 */
	protected long  subid;
	/**
	 *备注
	 */
	protected String  bz;
	/**
	 *是否标记为模板
	 */
	protected String  issign;
	/**
	 *创建时间
	 */
	protected java.util.Date  createtime;
	/**
	 *模板生成的文件路径
	 */
	protected String  mbscdwjlj;
	/**
	 *模板文件生成的文件名称
	 */
	protected String  mbwjscdwjmc;
	/**
	 *模板XML
	 */
	protected String  templateXML;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTemplatename(String templatename) 
	{
		this.templatename = templatename;
	}
	/**
	 * 返回 模版名称
	 * @return
	 */
	public String getTemplatename() 
	{
		return this.templatename;
	}
	public void setBm(String bm) 
	{
		this.bm = bm;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getBm() 
	{
		return this.bm;
	}
	public void setTemplatetype(String templatetype) 
	{
		this.templatetype = templatetype;
	}
	/**
	 * 返回 模板类别
	 * @return
	 */
	public String getTemplatetype() 
	{
		return this.templatetype;
	}
	public void setSubjecttype(String subjecttype) 
	{
		this.subjecttype = subjecttype;
	}
	/**
	 * 返回 流程类别
	 * @return
	 */
	public String getSubjecttype() 
	{
		return this.subjecttype;
	}
	public void setSubjectname(String subjectname) 
	{
		this.subjectname = subjectname;
	}
	/**
	 * 返回 流程名称
	 * @return
	 */
	public String getSubjectname() 
	{
		return this.subjectname;
	}
	public void setsubId(long subid) 
	{
		this.subid = subid;
	}
	/**
	 * 返回 流程Id
	 * @return
	 */
	public long getSubId() 
	{
		return this.subid;
	}
	public void setBz(String bz) 
	{
		this.bz = bz;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getBz() 
	{
		return this.bz;
	}
	public void setIssign(String issign) 
	{
		this.issign = issign;
	}
	/**
	 * 返回 是否标记为模板
	 * @return
	 */
	public String getIssign() 
	{
		return this.issign;
	}
	public void setCreatetime(java.util.Date createtime) 
	{
		this.createtime = createtime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreatetime() 
	{
		return this.createtime;
	}
	public void setMbscdwjlj(String mbscdwjlj) 
	{
		this.mbscdwjlj = mbscdwjlj;
	}
	/**
	 * 返回 模板生成的文件路径
	 * @return
	 */
	public String getMbscdwjlj() 
	{
		return this.mbscdwjlj;
	}
	public void setMbwjscdwjmc(String mbwjscdwjmc) 
	{
		this.mbwjscdwjmc = mbwjscdwjmc;
	}
	/**
	 * 返回 模板文件生成的文件名称
	 * @return
	 */
	public String getMbwjscdwjmc() 
	{
		return this.mbwjscdwjmc;
	}
	public void setTemplateXML(String templateXML) 
	{
		this.templateXML = templateXML;
	}
	/**
	 * 返回 模板XML
	 * @return
	 */
	public String getTemplateXML() 
	{
		return this.templateXML;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof CodeTemplate)) 
		{
			return false;
		}
		CodeTemplate rhs = (CodeTemplate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.templatename, rhs.templatename)
		.append(this.bm, rhs.bm)
		.append(this.templatetype, rhs.templatetype)
		.append(this.subjecttype, rhs.subjecttype)
		.append(this.subjectname, rhs.subjectname)
		.append(this.bz, rhs.bz)
		.append(this.issign, rhs.issign)
		.append(this.createtime, rhs.createtime)
		.append(this.mbscdwjlj, rhs.mbscdwjlj)
		.append(this.mbwjscdwjmc, rhs.mbwjscdwjmc)
		.append(this.templateXML, rhs.templateXML)
		.append(this.subid,rhs.subid)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.templatename) 
		.append(this.bm) 
		.append(this.templatetype) 
		.append(this.subjecttype) 
		.append(this.subjectname) 
		.append(this.bz) 
		.append(this.issign) 
		.append(this.createtime) 
		.append(this.mbscdwjlj) 
		.append(this.mbwjscdwjmc) 
		.append(this.templateXML) 
		.append(this.subid)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("templatename", this.templatename) 
		.append("bm", this.bm) 
		.append("templatetype", this.templatetype) 
		.append("subjecttype", this.subjecttype) 
		.append("subjectname", this.subjectname) 
		.append("bz", this.bz) 
		.append("issign", this.issign) 
		.append("createtime", this.createtime) 
		.append("mbscdwjlj", this.mbscdwjlj) 
		.append("mbwjscdwjmc", this.mbwjscdwjmc) 
		.append("templateXML", this.templateXML) 
		.append("subid",this.subid)
		.toString();
	}

}