package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:报表 Model对象
 */
public class SysReport extends BaseModel
{
	//主键
	protected Long reportId;
	/**
	 *标题
	 */
	protected String  title;
	/**
	 *描述
	 */
	protected String  descp;
	/**
	 *报表模板文件路径
	 */
	protected String  filePath;
	/**
	 *文件名
	 */
	protected String  fileName;
	/**
	 *创建时间
	 */
	protected java.util.Date  createtime;
	/**
	 *状态
	 */
	protected Long  status;
	/**
	 *数据源
	 */
	protected String  dsName;
	/**
	 *主报表参数
	 */
	protected String  params;
	/**
	 *类型ID
	 */
	protected Long  typeId;
	
	/**
	 * 扩展名
	 * @return
	 */
	protected String ext ;
	
	/**
	 * 报表模板中的SQL
	 * @return
	 */
	protected String sql ;
	
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
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
		return this.title;
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
	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}
	/**
	 * 返回 报表模板文件路径
	 * @return
	 */
	public String getFilePath() 
	{
		return this.filePath;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	/**
	 * 返回 文件名
	 * @return
	 */
	public String getFileName() 
	{
		return this.fileName;
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
	public void setStatus(Long status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态
	 * @return
	 */
	public Long getStatus() 
	{
		return this.status;
	}
	public void setDsName(String dsName) 
	{
		this.dsName = dsName;
	}
	/**
	 * 返回 数据源
	 * @return
	 */
	public String getDsName() 
	{
		return this.dsName;
	}
	public void setParams(String params) 
	{
		this.params = params;
	}
	/**
	 * 返回 主报表参数
	 * @return
	 */
	public String getParams() 
	{
		return this.params;
	}
	public void setTypeId(Long typeId) 
	{
		this.typeId = typeId;
	}
	/**
	 * 返回 类型Id
	 * @return
	 */
	public Long getTypeId() 
	{
		return this.typeId;
	}
	
	/**
	 * 返回文件扩展名
	 * @return
	 */
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	/**
	 * 返回报表模板中的SQL语句
	 * @return
	 */
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysReport)) 
		{
			return false;
		}
		SysReport rhs = (SysReport) object;
		return new EqualsBuilder()
		.append(this.reportId,rhs.reportId)
		.append(this.title, rhs.title)
		.append(this.descp, rhs.descp)
		.append(this.filePath, rhs.filePath)
		.append(this.fileName, rhs.fileName)
		.append(this.createtime, rhs.createtime)
		.append(this.status, rhs.status)
		.append(this.dsName, rhs.dsName)
		.append(this.params, rhs.params)
		.append(this.typeId, rhs.typeId)
		.append(this.ext, rhs.ext)
		.append(this.sql, rhs.sql)
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
		.append(this.filePath) 
		.append(this.fileName) 
		.append(this.createtime) 
		.append(this.status) 
		.append(this.dsName) 
		.append(this.params) 
		.append(this.typeId) 
		.append(this.ext) 
		.append(this.sql) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("reportId",this.reportId)
		.append("title", this.title) 
		.append("descp", this.descp) 
		.append("filePath", this.filePath) 
		.append("fileName", this.fileName) 
		.append("createtime", this.createtime) 
		.append("status", this.status) 
		.append("dsName", this.dsName) 
		.append("params", this.params) 
		.append("typeId", this.typeId) 
		.append("ext", this.ext) 
		.append("sql", this.sql) 
		.toString();
	}

}
