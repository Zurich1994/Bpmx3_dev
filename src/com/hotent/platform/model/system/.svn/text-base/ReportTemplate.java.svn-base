package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:报表模板Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-04-12 09:59:46
 */
public class ReportTemplate extends BaseModel
{
	public static final String targetPath = "reportlets";
	
	// reportId
	protected Long reportId;
	// 标题
	protected String title;
	// 描述
	protected String descp;
	// 报表模块的jasper文件的路径
	protected String reportLocation;
	// 创建时间
	protected java.util.Date createTime;
	// 修改时间
	protected java.util.Date updateTime;
	// 标识key
	protected String reportKey;
	// 是否缺省
	protected Short isDefaultIn;
	// 类型ID
	protected Long typeId;
	// 附件名称
	protected String fileName;
	
	protected String reportSeverlet = "/ReportServer?reportlet=";

	public void setReportId(Long reportId)
	{
		this.reportId = reportId;
	}
	/**
	 * 返回 reportId
	 * @return
	 */
	public Long getReportId() 
	{
		return reportId;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
	/**
	 * 返回 标题
	 * @return
	 */
	public String getTitle() 
	{
		return title;
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
		return descp;
	}

	public void setReportLocation(String reportLocation) 
	{
		this.reportLocation = reportLocation;
	}
	/**
	 * 返回 报表模块的jasper文件的路径
	 * @return
	 */
	public String getReportLocation() 
	{
		return reportLocation;
	}

	public void setCreateTime(java.util.Date createTime) 
	{
		this.createTime = createTime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateTime() 
	{
		return createTime;
	}

	public void setUpdateTime(java.util.Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	/**
	 * 返回 修改时间
	 * @return
	 */
	public java.util.Date getUpdateTime() 
	{
		return updateTime;
	}

	public void setReportKey(String reportKey) 
	{
		this.reportKey = reportKey;
	}
	/**
	 * 返回 标识key
	 * @return
	 */
	public String getReportKey() 
	{
		return reportKey;
	}

	public void setIsDefaultIn(Short isDefaultIn) 
	{
		this.isDefaultIn = isDefaultIn;
	}
	/**
	 * 返回 是否缺省
	 * @return
	 */
	public Short getIsDefaultIn() 
	{
		return isDefaultIn;
	}
	

	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	public String getFileName() 
	{
		//解决报表在linux下预览的问题
		String[] paths=null;
		if(this.reportLocation.indexOf("/")!=-1){
			paths=this.reportLocation.split("/");
		}else{
		 paths=this.reportLocation.split("\\\\");
		}
		return paths[paths.length-1];
	}
	
	public String getReportSeverlet(){
		return reportSeverlet;
	}
	
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ReportTemplate)) 
		{
			return false;
		}
		ReportTemplate rhs = (ReportTemplate) object;
		return new EqualsBuilder()
		.append(this.reportId, rhs.reportId)
		.append(this.title, rhs.title)
		.append(this.descp, rhs.descp)
		.append(this.reportLocation, rhs.reportLocation)
		.append(this.createTime, rhs.createTime)
		.append(this.updateTime, rhs.updateTime)
		.append(this.reportKey, rhs.reportKey)
		.append(this.isDefaultIn, rhs.isDefaultIn)
		.append(this.typeId, rhs.typeId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.reportId) 
		.append(this.title) 
		.append(this.descp) 
		.append(this.reportLocation) 
		.append(this.createTime) 
		.append(this.updateTime) 
		.append(this.reportKey) 
		.append(this.isDefaultIn) 
		.append(this.typeId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("reportId", this.reportId) 
		.append("title", this.title) 
		.append("descp", this.descp) 
		.append("reportLocation", this.reportLocation) 
		.append("createTime", this.createTime) 
		.append("updateTime", this.updateTime) 
		.append("reportKey", this.reportKey) 
		.append("isDefaultIn", this.isDefaultIn) 
		.append("typeId", this.typeId) 
		.toString();
	}
   
  

}