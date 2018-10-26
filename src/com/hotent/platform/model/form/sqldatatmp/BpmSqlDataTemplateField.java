/**
 * 描述：TODO
 * 包名：com.hotent.platform.model.form.sqldatatmp
 * 文件名：BpmSqlDataTemplateField.java
 * 作者：User-mailto:liyj@jee-soft.cn
 * 日期2014-5-20-上午9:29:26
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.model.form.sqldatatmp;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * <pre>
 * 描述：TODO
 * 构建组：bpm_3.3
 * 作者：lyj
 * 邮箱:liyj@jee-soft.cn
 * 日期:2014-5-20-上午9:29:26
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmSqlDataTemplateField {
	// Id主键
	protected Long id ;
	// sql模板id
	protected Long tmplId ;
	// 字段名称
	protected String name = "";
	// 字段类型
	protected String type = "";
	// 说明
	protected String fieldDesc = "";
	//是否显示字段：0:false,1:true
	protected Short isShow=1;
	//显示的时候的字段名
	protected String showName;
	//字段显示权限,用json格式来保存
	protected String showRight;
	//是否查询字段
	protected Short isSearch=0;
	//查询时候显示的字段名
	protected String searchName;
	//是否排序字段
	protected Short isSort=0;
	//排序方式，升序还降序,asc:升序，desc:
	protected String sortType;
	/**
	 * id
	 * @return  the id
	 * @since   1.0.0
	 */
	
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * tmplId
	 * @return  the tmplId
	 * @since   1.0.0
	 */
	
	public Long getTmplId() {
		return tmplId;
	}
	/**
	 * @param tmplId the tmplId to set
	 */
	public void setTmplId(Long tmplId) {
		this.tmplId = tmplId;
	}
	/**
	 * name
	 * @return  the name
	 * @since   1.0.0
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * type
	 * @return  the type
	 * @since   1.0.0
	 */
	
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * fieldDesc
	 * @return  the fieldDesc
	 * @since   1.0.0
	 */
	
	public String getFieldDesc() {
		return fieldDesc;
	}
	/**
	 * @param fieldDesc the fieldDesc to set
	 */
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	/**
	 * isShow
	 * @return  the isShow
	 * @since   1.0.0
	 */
	
	public Short getIsShow() {
		return isShow;
	}
	/**
	 * @param isShow the isShow to set
	 */
	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}
	/**
	 * showName
	 * @return  the showName
	 * @since   1.0.0
	 */
	
	public String getShowName() {
		return showName;
	}
	/**
	 * @param showName the showName to set
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}
	/**
	 * showRight
	 * @return  the showRight
	 * @since   1.0.0
	 */
	
	public String getShowRight() {
		return showRight;
	}
	/**
	 * @param showRight the showRight to set
	 */
	public void setShowRight(String showRight) {
		this.showRight = showRight;
	}
	/**
	 * isSearch
	 * @return  the isSearch
	 * @since   1.0.0
	 */
	
	public Short getIsSearch() {
		return isSearch;
	}
	/**
	 * @param isSearch the isSearch to set
	 */
	public void setIsSearch(Short isSearch) {
		this.isSearch = isSearch;
	}
	/**
	 * searchName
	 * @return  the searchName
	 * @since   1.0.0
	 */
	
	public String getSearchName() {
		return searchName;
	}
	/**
	 * @param searchName the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	/**
	 * isSort
	 * @return  the isSort
	 * @since   1.0.0
	 */
	
	public Short getIsSort() {
		return isSort;
	}
	/**
	 * @param isSort the isSort to set
	 */
	public void setIsSort(Short isSort) {
		this.isSort = isSort;
	}
	/**
	 * sortType
	 * @return  the sortType
	 * @since   1.0.0
	 */
	
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
}
