package com.hotent.platform.service.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hotent.platform.model.form.BpmFormTable;

/**
 * 对html表单设计解析的结果。
 * 1.解析的表。
 * 2.解析出的fremaker模版。
 * 3.解析的错误信息。
 * @author ray
 *
 */
public class ParseReult {
	//表
	private BpmFormTable bpmFormTable=new BpmFormTable();
	//模版
	private String template="";
	//错误信息
	private List<String> errors=new ArrayList<String>();
	//意见。
	private Map<String,String> opinionMap=new HashMap<String, String>();
	
	
	/**
	 * 添加错误。
	 * @param error
	 */
	public void addError(String error){
		errors.add(error);
	}
	
	public BpmFormTable getBpmFormTable() {
		return bpmFormTable;
	}
	public void setBpmFormTable(BpmFormTable bpmFormTable) {
		this.bpmFormTable = bpmFormTable;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	/**
	 * 获取错误信息。
	 * @return
	 */
	public List<String> getErrors() {
		return errors;
	}
	
	/**
	 * 获取错误。
	 * @return
	 */
	public String getError() {
		String error="";
		for(Iterator<String> it=errors.iterator();it.hasNext();){
			error+=it.next() +"<br>";
		}
		return error;
	}
	
	/**
	 * 是否有错误信息。
	 * @return
	 */
	public boolean hasErrors(){
		return errors.size()>0;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	/**
	 * 获取意见map。
	 * @return
	 */
	public Map<String, String> getOpinionMap() {
		return opinionMap;
	}

	public void setOpinionMap(Map<String, String> opinionMap) {
		this.opinionMap = opinionMap;
	}
	
	
	public void addOpinion(String name,String memo){
		this.opinionMap.put(name, memo);
	}
	
}
