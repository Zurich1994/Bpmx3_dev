package com.hotent.platform.model.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpUtils;

import com.hotent.core.model.BaseModel;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 对象功能:通用表单对话框 Model对象 开发公司:广州宏天软件有限公司 开发人员:ray 创建时间:2012-06-25 11:05:08
 */
public class BpmFormDialog extends BaseModel {
	// 分页常量
	public static final String Page = "p";
	// 分页大小常量。
	public static final String PageSize = "z";
	// 主键ID
	protected Long id = 0L;
	// 对话框名称
	protected String name = "";
	// 对话框别名
	protected String alias = "";
	// 显示样式 0,列表1,树形
	protected Integer style = 0;
	// 对话框宽度
	protected Integer width = 400;
	// 高度
	protected Integer height = 300;
	// 是否单选 0,多选 1.单选
	protected Integer issingle = 1;
	// 是否分页
	protected Integer needpage = 1;
	// 是否为表 0,视图 1,数据库表
	protected Integer istable = 1;
	// 对象名称
	protected String objname = "";
	// 需要显示的字段
	protected String displayfield = "";
	// 条件字段
	protected String conditionfield = "";
	// 显示结果字段
	protected String resultfield = "";
	// 排序字段
	protected String sortfield = "";
	// 数据源名称
	protected String dsname = "";
	// 数据源别名
	protected String dsalias = "";
	// 分页数据
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// 分页bean。
	private PageBean pageBean = new PageBean(1, 20);
	// 分页大小
	private Integer pagesize = 10;

	/**
	 * 取得tree字段。
	 * 
	 * @return
	 */
	public Map<String, String> getTreeField() {
		if (this.style == 0) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject = JSONObject.fromObject(this.displayfield);
		map.put("id", jsonObject.get("id").toString());
		map.put("pid", jsonObject.get("pid").toString());
		map.put("displayName", jsonObject.get("displayName").toString());
		return map;
	}

	/**
	 * 返回显示字段列表 [{"field":"","comment":""}]
	 * 
	 * @return
	 */
	public List<DialogField> getDisplayList() {
		if (this.style == 1) {
			return null;
		}
		if (StringUtil.isEmpty(this.displayfield)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(this.displayfield);
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
			Object paraCt = jsonObj.get("paraCt");
			Object dialog = jsonObj.get("dialog");
			Object param = jsonObj.get("param");
			
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
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键ID
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 对话框名称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 返回 对话框别名
	 * 
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	/**
	 * 返回 显示样式 0,列表 1,树形
	 * 
	 * @return
	 */
	public Integer getStyle() {
		return this.style;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * 返回 对话框宽度
	 * 
	 * @return
	 */
	public Integer getWidth() {
		return this.width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * 返回 高度
	 * 
	 * @return
	 */
	public Integer getHeight() {
		return this.height;
	}

	public void setIssingle(Integer issingle) {
		this.issingle = issingle;
	}

	/**
	 * 返回 是否单选 0,多选 1.单选
	 * 
	 * @return
	 */
	public Integer getIssingle() {
		return this.issingle;
	}

	public void setNeedpage(Integer needpage) {
		this.needpage = needpage;
	}

	/**
	 * 返回 是否分页
	 * 
	 * @return
	 */
	public Integer getNeedpage() {
		return this.needpage;
	}

	public void setIstable(Integer istable) {
		this.istable = istable;
	}

	/**
	 * 返回 是否为表 0,视图 1,数据库表
	 * 
	 * @return
	 */
	public Integer getIstable() {
		return this.istable;
	}

	public void setObjname(String objname) {
		this.objname = objname;
	}

	/**
	 * 返回 对象名称
	 * 
	 * @return
	 */
	public String getObjname() {
		return this.objname;
	}

	public void setDisplayfield(String displayfield) {
		this.displayfield = displayfield;
	}

	/**
	 * 返回 需要显示的字段
	 * 
	 * @return
	 */
	public String getDisplayfield() {
		return this.displayfield;
	}

	public void setConditionfield(String conditionfield) {
		this.conditionfield = conditionfield;
	}

	/**
	 * 返回 条件字段
	 * 
	 * @return
	 */
	public String getConditionfield() {
		return this.conditionfield;
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

	//排序字段
	public String getSortfield() {
		return sortfield;
	}

	public void setSortfield(String sortfield) {
		this.sortfield = sortfield;
	}

	public void setResultfield(String resultfield) {
		this.resultfield = resultfield;
	}

	/**
	 * 返回 显示结果字段
	 * 
	 * @return
	 */
	public String getResultfield() {
		return this.resultfield.trim();
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	/**
	 * 返回 数据源名称
	 * 
	 * @return
	 */
	public String getDsname() {
		return this.dsname;
	}

	public void setDsalias(String dsalias) {
		this.dsalias = dsalias;
	}

	/**
	 * 返回 数据源别名
	 * 
	 * @return
	 */
	public String getDsalias() {
		return this.dsalias;
	}

	/**
	 * 返回 分页大小
	 * 
	 * @return
	 */
	public Integer getPagesize() {
		return pagesize;
	}

	/**
	 * 
	 * @return
	 */
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpmFormDialog)) {
			return false;
		}
		BpmFormDialog rhs = (BpmFormDialog) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.name, rhs.name).append(this.alias, rhs.alias).append(this.style, rhs.style)
				.append(this.width, rhs.width).append(this.height, rhs.height).append(this.issingle, rhs.issingle).append(this.needpage, rhs.needpage)
				.append(this.istable, rhs.istable).append(this.objname, rhs.objname).append(this.displayfield, rhs.displayfield)
				.append(this.conditionfield, rhs.conditionfield).append(this.resultfield, rhs.resultfield).append(this.dsname, rhs.dsname)
				.append(this.dsalias, rhs.dsalias).append(this.sortfield, rhs.sortfield).isEquals();
	}
	
	

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id).append(this.name).append(this.alias).append(this.style).append(this.width)
				.append(this.height).append(this.issingle).append(this.needpage).append(this.istable).append(this.objname).append(this.displayfield)
				.append(this.conditionfield).append(this.resultfield).append(this.dsname).append(this.dsalias).append(this.sortfield).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("alias", this.alias).append("style", this.style)
				.append("width", this.width).append("height", this.height).append("issingle", this.issingle).append("needpage", this.needpage)
				.append("istable", this.istable).append("objname", this.objname).append("displayfield", this.displayfield)
				.append("conditionfield", this.conditionfield).append("resultfield", this.resultfield).append("dsname", this.dsname)
				.append("dsalias", this.dsalias).append("sortfield",this.sortfield).toString();
	}

}