package com.hotent.dbFunc.model.dbFunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.core.model.BaseModel;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.DialogField;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:数据库方法参数表 Model对象
 */
public class DbFunc extends BaseModel
{
	//主键
	protected Long id = 0L;
	/**
	 *表名
	 */
	protected String  table_name;
	/**
	 *方法类型
	 */
	protected Long  func_type;
	/**
	 *方法名称
	 */
	protected String  func_name;
	/**
	 *方法别名
	 */
	protected String  func_alias;
	/**
	 *备注
	 */
	protected String  remarks;
	/**
	 *传参类型
	 */
	protected String  parameterType;
	/**
	 *返回值类型
	 */
	protected String  resultType;
	/**
	 *返回字段
	 */
	protected String  resultField;
	/**
	 *条件字段
	 */
	protected String  conditionField;
	/**
	 *排序字段
	 */
	protected String  sortField;
	/**
	 *设置字段
	 */
	protected String  settingField;
	/**
	 *显示字段
	 */
	protected String  displayField;
	/**
	 *数据源名称
	 */
	protected String  dsName;
	/**
	 *数据源别名
	 */
	protected String  ds_Alias;
	/**
	 *是否为表 
	 */
	protected Long  isTable;

	/**
	 *是否正在使用
	 */
	protected Long  is_Using;
	/**
	 *objname
	 */
	protected String  objname;
	
	// 显示样式 0,列表1,树形                             //虽然并卵,却不可删
	protected Integer style = 0;
	
	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}
	
	/**
	 * 返回显示字段列表 [{"field":"","comment":""}]
	 * 
	 * @return
	 */
	public List<DialogField> getDisplayList() {
		 if(this.style == 1) {
			return null;
		}
		if (StringUtil.isEmpty(this.displayField)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.displayField);
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
	
	/**
	 * 解析条件字段。 [{"field":"字段名","comment":"注释","condition":"条件","dbType":
	 * "varchar,number,date"},....]
	 * 
	 * @return
	 */
	public List<DialogField> getConditionList() {
		
		if (StringUtil.isEmpty(this.conditionField)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.conditionField);
		for (Object obj : jsonArray) {
			JSONObject jsonObj = (JSONObject) obj;
			String field = jsonObj.getString("field");
			String comment = jsonObj.getString("comment");
			String condition = jsonObj.getString("condition");
			String dbType = jsonObj.getString("dbType");// 字段类型
			Object paraCt = jsonObj.get("paraCt");//控件类型
			Object dialog = jsonObj.get("dialog");// 对话框别名
			Object param = jsonObj.get("param");// 对话框返回字段
			
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
			if(paraCt!=null){
				dialogField.setParaCt(paraCt.toString());
			}
			if(dialog!=null){
				dialogField.setDialog(dialog.toString());
			}
			if(param!=null){
				dialogField.setParam(param.toString());
			}
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
//		if (this.style == 1) {
//			return null;
//		}
		if (StringUtil.isEmpty(this.resultField)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.resultField);
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
	
	
	

	/**
	 * 返回设置字段列表。
	 * 
	 * @return
	 */
	public List<DialogField> getSettingList() {
//		if (this.style == 1) {
//			return null;
//		}
		if (StringUtil.isEmpty(this.settingField)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.settingField);
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
	
	/**
	 * 返回排序字段列表。
	 * 
	 * @return
	 */
	public List<DialogField> getSortList() {
		if (StringUtil.isEmpty(this.sortField)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.sortField);
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
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTable_name(String table_name) 
	{
		this.table_name = table_name;
	}
	/**
	 * 返回 表名
	 * @return
	 */
	public String getTable_name() 
	{
		return this.table_name;
	}
	public void setFunc_type(Long func_type) 
	{
		this.func_type = func_type;
	}
	/**
	 * 返回 方法类型
	 * @return
	 */
	public Long getFunc_type() 
	{
		return this.func_type;
	}
	public void setFunc_name(String func_name) 
	{
		this.func_name = func_name;
	}
	/**
	 * 返回 方法名称
	 * @return
	 */
	public String getFunc_name() 
	{
		return this.func_name;
	}
	public void setFunc_alias(String func_alias) 
	{
		this.func_alias = func_alias;
	}
	/**
	 * 返回 方法别名
	 * @return
	 */
	public String getFunc_alias() 
	{
		return this.func_alias;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemarks() 
	{
		return this.remarks;
	}
	public void setParameterType(String parameterType) 
	{
		this.parameterType = parameterType;
	}
	/**
	 * 返回 传参类型
	 * @return
	 */
	public String getParameterType() 
	{
		return this.parameterType;
	}
	public void setResultType(String resultType) 
	{
		this.resultType = resultType;
	}
	/**
	 * 返回 返回值类型
	 * @return
	 */
	public String getResultType() 
	{
		return this.resultType;
	}
	public void setResultField(String resultField) 
	{
		this.resultField = resultField;
	}
	/**
	 * 返回 返回字段
	 * @return
	 */
	public String getResultField() 
	{
		return this.resultField;
	}
	public void setConditionField(String conditionField) 
	{
		this.conditionField = conditionField;
	}
	/**
	 * 返回 条件字段
	 * @return
	 */
	public String getConditionField() 
	{
		return this.conditionField;
	}
	public void setSortField(String sortField) 
	{
		this.sortField = sortField;
	}
	/**
	 * 返回 排序字段
	 * @return
	 */
	public String getSortField() 
	{
		return this.sortField;
	}
	public void setSettingField(String settingField) 
	{
		this.settingField = settingField;
	}
	/**
	 * 返回 设置字段
	 * @return
	 */
	public String getSettingField() 
	{
		return this.settingField;
	}
	public void setDisplayField(String displayField) 
	{
		this.displayField = displayField;
	}
	/**
	 * 返回 显示字段
	 * @return
	 */
	public String getDisplayField() 
	{
		return this.displayField;
	}
	public void setDsName(String dsName) 
	{
		this.dsName = dsName;
	}
	/**
	 * 返回 数据源名称
	 * @return
	 */
	public String getDsName() 
	{
		return this.dsName;
	}
	public void setDs_Alias(String ds_Alias) 
	{
		this.ds_Alias = ds_Alias;
	}
	/**
	 * 返回 数据源别名
	 * @return
	 */
	public String getDs_Alias() 
	{
		return this.ds_Alias;
	}
	public void setIsTable(Long isTable) 
	{
		this.isTable = isTable;
	}
	/**
	 * 返回 是否为表 
	 * @return
	 */
	public Long getIsTable() 
	{
		return this.isTable;
	}
	public void setIs_Using(Long is_Using) 
	{
		this.is_Using = is_Using;
	}
	/**
	 * 返回 是否正在使用
	 * @return
	 */
	public Long getIs_Using() 
	{
		return this.is_Using;
	}
	public void setObjname(String objname) 
	{
		this.objname = objname;
	}
	/**
	 * 返回 objname
	 * @return
	 */
	public String getObjname() 
	{
		return this.objname;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DbFunc)) 
		{
			return false;
		}
		DbFunc rhs = (DbFunc) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.table_name, rhs.table_name)
		.append(this.func_type, rhs.func_type)
		.append(this.func_name, rhs.func_name)
		.append(this.func_alias, rhs.func_alias)
		.append(this.remarks, rhs.remarks)
		.append(this.parameterType, rhs.parameterType)
		.append(this.resultType, rhs.resultType)
		.append(this.resultField, rhs.resultField)
		.append(this.conditionField, rhs.conditionField)
		.append(this.sortField, rhs.sortField)
		.append(this.settingField, rhs.settingField)
		.append(this.displayField, rhs.displayField)
		.append(this.dsName, rhs.dsName)
		.append(this.ds_Alias, rhs.ds_Alias)
		.append(this.isTable, rhs.isTable)
		.append(this.is_Using, rhs.is_Using)
		.append(this.objname, rhs.objname)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.table_name) 
		.append(this.func_type) 
		.append(this.func_name) 
		.append(this.func_alias) 
		.append(this.remarks) 
		.append(this.parameterType) 
		.append(this.resultType) 
		.append(this.resultField) 
		.append(this.conditionField) 
		.append(this.sortField) 
		.append(this.settingField) 
		.append(this.displayField) 
		.append(this.dsName) 
		.append(this.ds_Alias) 
		.append(this.isTable) 
		.append(this.is_Using) 
		.append(this.objname) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("table_name", this.table_name) 
		.append("func_type", this.func_type) 
		.append("func_name", this.func_name) 
		.append("func_alias", this.func_alias) 
		.append("remarks", this.remarks) 
		.append("parameterType", this.parameterType) 
		.append("resultType", this.resultType) 
		.append("resultField", this.resultField) 
		.append("conditionField", this.conditionField) 
		.append("sortField", this.sortField) 
		.append("settingField", this.settingField) 
		.append("displayField", this.displayField) 
		.append("dsName", this.dsName) 
		.append("ds_Alias", this.ds_Alias) 
		.append("isTable", this.isTable) 
		.append("is_Using", this.is_Using) 
		.append("objname", this.objname) 
		.toString();
	}

}
