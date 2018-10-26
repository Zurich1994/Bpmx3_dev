package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:首页栏目 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 11:22:45
 */
public class SysIndexColumn extends BaseModel{
	
	/**SERVICE方法*/
	public static short DATA_MODE_SERVICE = 0;
	/**自定义查询*/
	public static short DATA_MODE_QUERY = 1;
	
	/**栏目类型-一般栏目*/
	public static short  COLUMN_TYPE_COMMON =0;
	
	/**栏目类型-图表栏目*/
	public static short  COLUMN_TYPE_CHART =1;
	/**栏目类型-日历栏目*/
	public static short  COLUMN_TYPE_CALENDAR =2;
	/**栏目类型-滚动栏目*/
	public static short  COLUMN_TYPE_ROLL =3;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4018816120529407191L;
	// 主键
	protected Long  id;
	// 栏目名称
	protected String  name;
	// 栏目别名
	protected String  alias;
	// 栏目分类
	protected Long  catalog;
	// 栏目类别(0：一般栏目、1：图表栏目、2、滚动栏目)
	protected Short  colType;
	// 数据加载方式(0:服务方法;1:自定义查询)
	protected Short  dataMode;
	// 数据来源
	protected String  dataFrom;
	// 数据参数
	protected String  dataParam;
	// 数据别名
	protected String  dsAlias;
	// 数据源名称
	protected String  dsName;

	// 栏目高度
	protected Long  colHeight;
	// 栏目url
	protected String  colUrl;
	// 栏目模版
	protected String  templateHtml;
	// 是否公共栏目
	protected Short  isPublic;
	// 所属组织ID
	protected Long  orgId;
	// 是否支持刷新
	protected Short  supportRefesh;
	// 刷新时间
	protected Long  refeshTime;
	// 展示效果
	protected Short  showEffect;
	// 描述
	protected String  memo;
	//是否需要分页
	protected Short needPage = 0;
	
	//组织名称，非数据字段
	protected String orgName;

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
	 * 返回 栏目名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setAlias(String alias){
		this.alias = alias;
	}
	/**
	 * 返回 栏目别名
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}
	public void setCatalog(Long catalog){
		this.catalog = catalog;
	}
	/**
	 * 返回 栏目分类
	 * @return
	 */
	public Long getCatalog() {
		return this.catalog;
	}
	
	/**
	 * 返回 栏目类型(0：一般栏目、1：图表栏目、2、滚动栏目)
	 * @return
	 */
	public Short getColType() {
		return this.colType;
	}
	
	/**
	 * 返回  数据加载方式(0:服务方法;1:自定义查询)
	 * @return
	 */
	public Short getDataMode() {
		return dataMode;
	}
	public void setDataMode(Short dataMode) {
		this.dataMode = dataMode;
	}
	public void setColType(Short colType){
		this.colType = colType;
	}
	public void setDsAlias(String dsAlias){
		this.dsAlias = dsAlias;
	}
	/**
	 * 返回 数据别名
	 * @return
	 */
	public String getDsAlias() {
		return this.dsAlias;
	}
	public void setDsName(String dsName){
		this.dsName = dsName;
	}
	/**
	 * 返回 数据源名称
	 * @return
	 */
	public String getDsName() {
		return this.dsName;
	}
	public void setDataFrom(String dataFrom){
		this.dataFrom = dataFrom;
	}
	/**
	 * 返回 数据来源
	 * @return
	 */
	public String getDataFrom() {
		return this.dataFrom;
	}
	public void setColHeight(Long colHeight){
		this.colHeight = colHeight;
	}
	/**
	 * 返回 栏目高度
	 * @return
	 */
	public Long getColHeight() {
		return this.colHeight;
	}
	public void setColUrl(String colUrl){
		this.colUrl = colUrl;
	}
	/**
	 * 返回 栏目url
	 * @return
	 */
	public String getColUrl() {
		return this.colUrl;
	}
	public void setTemplateHtml(String templateHtml){
		this.templateHtml = templateHtml;
	}
	/**
	 * 返回 栏目模版
	 * @return
	 */
	public String getTemplateHtml() {
		return this.templateHtml;
	}
	public void setIsPublic(Short isPublic){
		this.isPublic = isPublic;
	}
	/**
	 * 返回 是否公共栏目
	 * @return
	 */
	public Short getIsPublic() {
		return this.isPublic;
	}
	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	/**
	 * 返回 所属组织ID
	 * @return
	 */
	public Long getOrgId() {
		return this.orgId;
	}
	public void setSupportRefesh(Short supportRefesh){
		this.supportRefesh = supportRefesh;
	}
	/**
	 * 返回 是否支持刷新
	 * @return
	 */
	public Short getSupportRefesh() {
		return this.supportRefesh;
	}
	public void setRefeshTime(Long refeshTime){
		this.refeshTime = refeshTime;
	}
	/**
	 * 返回 刷新时间
	 * @return
	 */
	public Long getRefeshTime() {
		return this.refeshTime;
	}
	public void setShowEffect(Short showEffect){
		this.showEffect = showEffect;
	}
	/**
	 * 返回 展示效果
	 * @return
	 */
	public Short getShowEffect() {
		return this.showEffect;
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
	
   	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getDataParam() {
		return dataParam;
	}
	public void setDataParam(String dataParam) {
		this.dataParam = dataParam;
	}
	
	public Short getNeedPage() {
		return needPage;
	}
	public void setNeedPage(Short needPage) {
		this.needPage = needPage;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysIndexColumn)) 
		{
			return false;
		}
		SysIndexColumn rhs = (SysIndexColumn) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.alias, rhs.alias)
		.append(this.catalog, rhs.catalog)
		.append(this.colType, rhs.colType)
		.append(this.dataMode, rhs.dataMode)
		.append(this.dataFrom, rhs.dataFrom)
		.append(this.dsAlias, rhs.dsAlias)
		.append(this.dsName, rhs.dsName)
		.append(this.colHeight, rhs.colHeight)
		.append(this.colUrl, rhs.colUrl)
		.append(this.templateHtml, rhs.templateHtml)
		.append(this.isPublic, rhs.isPublic)
		.append(this.orgId, rhs.orgId)
		.append(this.supportRefesh, rhs.supportRefesh)
		.append(this.refeshTime, rhs.refeshTime)
		.append(this.showEffect, rhs.showEffect)
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
		.append(this.name) 
		.append(this.alias) 
		.append(this.catalog) 
		.append(this.colType)
		.append(this.dataMode)
		.append(this.dataFrom) 
		.append(this.dsAlias) 
		.append(this.dsName) 
		.append(this.colHeight) 
		.append(this.colUrl) 
		.append(this.templateHtml) 
		.append(this.isPublic) 
		.append(this.orgId) 
		.append(this.supportRefesh) 
		.append(this.refeshTime) 
		.append(this.showEffect) 
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
		.append("name", this.name) 
		.append("alias", this.alias) 
		.append("catalog", this.catalog) 
		.append("colType", this.colType)
		.append("dataMode", this.dataMode)
		.append("dataFrom", this.dataFrom) 
		.append("dsAlias", this.dsAlias) 
		.append("dsName", this.dsName) 
		.append("colHeight", this.colHeight) 
		.append("colUrl", this.colUrl) 
		.append("templateHtml", this.templateHtml) 
		.append("isPublic", this.isPublic) 
		.append("orgId", this.orgId) 
		.append("supportRefesh", this.supportRefesh) 
		.append("refeshTime", this.refeshTime) 
		.append("showEffect", this.showEffect) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}