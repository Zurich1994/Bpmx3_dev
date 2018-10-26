package com.hotent.core.valid;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证的字段。
 * @author hotent
 *
 */
public class ValidField {
	
	private String formName="";
	
	private String displayName="";
	
	private List<Rule> ruleList=new ArrayList<Rule>();

	/**
	 * 表单名称。<br>
	 * 例如：姓名 <input name="name" type="input" /><br>
	 * 表单名称就为 name。
	 * @return
	 */
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	/**
	 * 显示名称。<br/>
	 *	例如：姓名 <input name="name" type="input" /> 显示名称就为姓名。
	 * @return
	 */
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 验证规则列表。
	 * 比如必填，邮件等等。
	 * @return
	 */
	public List<Rule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<Rule> ruleList) {
		this.ruleList = ruleList;
	}
	
	/**
	 * 添加规则。
	 * @param rule
	 */
	public void addRule(Rule rule)
	{
		this.ruleList.add(rule);
	}
	
	
	 
}
