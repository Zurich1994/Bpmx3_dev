package com.hotent.core.wsdl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ws.commons.schema.XmlSchemaType;



public class ParameterInfo {
	/**
	 * 复杂类型
	 */
	public static final Short COMPLEX_YES =1;
	/**
	 *不是复杂类型
	 */
	public static final Short COMPLEX_NO =0;
	/**
	 * 显示类型
	 */
	public static final Short SHOW_YES =0;
	/**
	 *不显示类型
	 */
	public static final Short SHOW_NO =1;
	private String name;
	private String type;
	private Short isComplext=0;    //是否是复杂类型，默认为不是0
	private Short isShow=0; 	   //是否显示，默认为是0，不是1
	private String deep;		   //复杂深度
	private Boolean isList = false;//是否为列表
	
	/*复杂类型数组*/
	private Map<String,ParameterInfo> complextParams=new  HashMap<String, ParameterInfo>() ;
	
	/*已经处理过的复杂类型*/
	private Map<String,String> parentComplext=new  HashMap<String, String>() ;
	
	public Short getIsComplext() {
		return isComplext;
	}

	public void setIsComplext(Short isComplext) {
		this.isComplext = isComplext;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ParameterInfo> getComplextParams() {
		return complextParams;
	}

	public void setComplextParams(Map<String, ParameterInfo> complextParams) {
		this.complextParams = complextParams;
	}

	public Map<String, String> getParentComplext() {
		return parentComplext;
	}

	public void setParentComplext(Map<String, String> parentComplext) {
		this.parentComplext = parentComplext;
	}

	public Short getIsShow() {
		return isShow;
	}

	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}

	public String getDeep() {
		return deep;
	}

	public void setDeep(String deep) {
		this.deep = deep;
	}

	public Boolean isList() {
		return isList;
	}

	public void setIsList(Boolean isList) {
		this.isList = isList;
	}
}


