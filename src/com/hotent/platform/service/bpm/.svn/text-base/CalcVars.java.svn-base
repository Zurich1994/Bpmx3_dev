package com.hotent.platform.service.bpm;

import java.util.HashMap;
import java.util.Map;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.util.BeanUtils;

/**
 * 计算用户接口
 * @author ray
 *
 */
public class CalcVars{
	
	public CalcVars(){
		
	}
	
	/**
	 * 构造函数
	 * @param startUserId		发起人ID
	 * @param preExecUserId		上一步执行人ID
	 * @param actInstId			流程实例ID
	 * @param extractUser		是否抽取用户。
	 * @param vars				流程变量。
	 */
	public CalcVars(Long startUserId,Long preExecUserId,String actInstId,Map<String,Object> vars){
		this.startUserId=startUserId;
		this.prevExecUserId=preExecUserId;
		this.actInstId=actInstId;
		this.vars=vars;
	}
	/**
	 * 流程发起人
	 */
	private  Long startUserId;
	/**
	 * 上一步执行人
	 */
	private Long prevExecUserId;
	/**
	 * 流程实例Id
	 */
	private String actInstId;

	
	/**
	 * 流程变量
	 */
	private Map<String,Object> vars=new HashMap<String, Object>();

	public Long getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(Long startUserId) {
		this.startUserId = startUserId;
	}

	public Long getPrevExecUserId() {
		return prevExecUserId;
	}

	public void setPrevExecUserId(Long prevExecUserId) {
		this.prevExecUserId = prevExecUserId;
	}

	public String getActInstId() {
		return actInstId;
	}

	public void setActInstId(String actInstId) {
		this.actInstId = actInstId;
	}

	

	public Map<String, Object> getVars() {
		return vars;
	}

	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}
	
	/**
	 * 获取发起人所在的组织。
	 * @return
	 */
	public String getStartOrgId(){
		Object obj=getVariable(BpmConst.START_ORG_ID);
		if(BeanUtils.isEmpty(obj)) return "";
		return obj.toString();
	}
	
	/**
	 * 获取上一步执人的组织。
	 * @return
	 */
	public String getPreStepOrgId(){
		Object obj=getVariable(BpmConst.PRE_ORG_ID);
		return obj.toString();
	}
	
	
	
	/**
	 * 获取流程变量。
	 * @param varName
	 * @return
	 */
	public Object getVariable(String varName){
		if(this.vars==null) return null;
		if(!this.vars.containsKey(varName))return null;
		return this.vars.get(varName);
	}
	
};