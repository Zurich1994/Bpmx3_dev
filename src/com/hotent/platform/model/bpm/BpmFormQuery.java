package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.DialogField;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:通用表单查询 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-11-27 10:37:12
 */
public class BpmFormQuery extends BaseModel
{
	// 主键
	protected Long  id = 0L;
	// 名称
	protected String  name = "";
	// 别名
	protected String  alias = "";
	// 查询对象名称
	protected String  objName = "";
	// 是否分页 0：否，1：是
	protected Long  needpage = 0L;
	// 条件字段
	protected String  conditionfield = "";
	// 返回字段
	protected String  resultfield = "";
	// 排序字段
	protected String  sortfield = "";
	// 数据源名称
	protected String  dsname = "";
	// 数据源别名
	protected String  dsalias = "";
	// 分页条数
	protected Long  pagesize = 10L;
	//是否数据库表0:视图,1:数据库表
	protected Integer isTable = 1;
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
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getAlias() 
	{
		return this.alias;
	}
	public void setObjName(String objName) 
	{
		this.objName = objName;
	}
	/**
	 * 返回 查询对象名称
	 * @return
	 */
	public String getObjName() 
	{
		return this.objName;
	}
	public void setNeedpage(Long needpage) 
	{
		this.needpage = needpage;
	}
	/**
	 * 返回 是否分页 0：否，1：是
	 * @return
	 */
	public Long getNeedpage() 
	{
		return this.needpage;
	}
	public void setConditionfield(String conditionfield) 
	{
		this.conditionfield = conditionfield;
	}
	/**
	 * 返回 条件字段
	 * @return
	 */
	public String getConditionfield() 
	{
		return this.conditionfield.trim();
	}
	public void setResultfield(String resultfield) 
	{
		this.resultfield = resultfield;
	}
	/**
	 * 返回 返回字段
	 * @return
	 */
	public String getResultfield() 
	{
		return this.resultfield;
	}
	public String getSortfield() {
		return sortfield;
	}
	public void setSortfield(String sortfield) {
		this.sortfield = sortfield;
	}
	public void setDsname(String dsname) 
	{
		this.dsname = dsname;
	}
	/**
	 * 返回 数据源名称
	 * @return
	 */
	public String getDsname() 
	{
		return this.dsname;
	}
	public void setDsalias(String dsalias) 
	{
		this.dsalias = dsalias;
	}
	/**
	 * 返回 数据源别名
	 * @return
	 */
	public String getDsalias() 
	{
		return this.dsalias;
	}
	public void setPagesize(Long pagesize) 
	{
		this.pagesize = pagesize;
	}
	/**
	 * 返回 分页条数
	 * @return
	 */
	public Long getPagesize() 
	{
		return this.pagesize;
	}
   
   	public Integer getIsTable() {
		return isTable;
	}
   	
	public void setIsTable(Integer isTable) {
		this.isTable = isTable;
	}
	
	/**
	 * 解析条件字段。 [{"field":"字段名","comment":"注释","condition":"条件","dbType":
	 * "varchar,number,date"},....]
	 * 
	 * @return
	 */
	public List<DialogField> getConditionList() {
		
		if (StringUtil.isEmpty(this.conditionfield)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.conditionfield);
		for (Object obj : jsonArray) {
			JSONObject jsonObj = (JSONObject) obj;
			String field = jsonObj.getString("field");
			String comment = jsonObj.getString("comment");
			String condition = jsonObj.getString("condition");
			String dbType = jsonObj.getString("dbType");
			
			Integer defaultType=1;
			Object objDefaultType =jsonObj.get("defaultType");
			if(objDefaultType!=null){
				defaultType=Integer.parseInt( objDefaultType.toString());
			}
			
			String defaultValue =(String)jsonObj.get("defaultValue");
			if(defaultValue==null){
				defaultValue="";
			}

			DialogField dialogField = new DialogField();
			dialogField.setFieldName(field);
			dialogField.setComment(comment);
			dialogField.setCondition(condition);
			dialogField.setFieldType(dbType);
			dialogField.setDefaultType(defaultType);
			dialogField.setDefaultValue(defaultValue);
			list.add(dialogField);
		}
		return list;
	}

	/**
	 * 返回返回字段列表。
	 * 
	 * @return
	 */
	public List<DialogField> getReturnList() {
		if (StringUtil.isEmpty(this.resultfield)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.resultfield);
		for (Object obj : jsonArray) {
			JSONObject jsonObj = (JSONObject) obj;
			String field = jsonObj.getString("field");
			String comment = jsonObj.getString("comment");
			DialogField dialogField = new DialogField();
			dialogField.setFieldName(field);
			dialogField.setComment(comment);
			list.add(dialogField);
		}
		return list;
	}
	
	public String getReturnFields(){
		String s = "";
		JSONObject jObject;
		JSONArray jArray=JSONArray.fromObject(this.resultfield);
		for(Object ob:jArray){
			jObject=JSONObject.fromObject(ob);
			s+=jObject.get("field");
			s+=",";
		}
		return s;
	}
	
	
	/**
	 * 返回排序字段列表。
	 * 
	 * @return
	 */
	public List<DialogField> getSortList() {
		if (StringUtil.isEmpty(this.sortfield)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.sortfield);
		for (Object obj : jsonArray) {
			JSONObject jsonObj = (JSONObject) obj;
			String field = jsonObj.getString("field");
			String comment = jsonObj.getString("comment");
			DialogField dialogField = new DialogField();
			dialogField.setFieldName(field);
			dialogField.setComment(comment);
			list.add(dialogField);
		}
		return list;
	}
	
	public String getConditionFields(){
		String s = "";
		JSONObject jObject;
		JSONArray jArray=JSONArray.fromObject(this.conditionfield);
		for(Object ob:jArray){
			jObject=JSONObject.fromObject(ob);
			s+=jObject.get("field");
			s+=",";
		}
		return s;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmFormQuery)) 
		{
			return false;
		}
		BpmFormQuery rhs = (BpmFormQuery) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.alias, rhs.alias)
		.append(this.objName, rhs.objName)
		.append(this.needpage, rhs.needpage)
		.append(this.conditionfield, rhs.conditionfield)
		.append(this.resultfield, rhs.resultfield)
		.append(this.sortfield, rhs.sortfield)
		.append(this.dsname, rhs.dsname)
		.append(this.dsalias, rhs.dsalias)
		.append(this.pagesize, rhs.pagesize)
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
		.append(this.objName) 
		.append(this.needpage) 
		.append(this.conditionfield) 
		.append(this.resultfield) 
		.append(this.sortfield) 
		.append(this.dsname) 
		.append(this.dsalias) 
		.append(this.pagesize) 
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
		.append("objName", this.objName) 
		.append("needpage", this.needpage) 
		.append("conditionfield", this.conditionfield) 
		.append("resultfield", this.resultfield) 
		.append("sortfield", this.sortfield) 
		.append("dsname", this.dsname) 
		.append("dsalias", this.dsalias) 
		.append("pagesize", this.pagesize) 
		.toString();
	}

}