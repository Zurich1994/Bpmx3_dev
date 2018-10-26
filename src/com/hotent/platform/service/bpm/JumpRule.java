package com.hotent.platform.service.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.BpmNodeRule;
import com.hotent.platform.model.bpm.BpmNodeSet;


/**
 * 流程跳转规则类。<br>
 * 返回流程跳转节点id，如果返回为空则忽略跳转，按照默认的规则执行。<br>
 * <pre>
 * 实现原理：
 * 1.在流程节点规则数据库中根据节点deployId和节点名称获取跳转规则列表。
 * 2.在流程规则中传入流程变量。
 * 3.根据流程变量循环计算流程条件，如果流程条件返回true，则返回跳转节点。
 * 4.如果循环完毕，没有找到跳转节点，则忽略流程跳转规则，使用流程默认跳转进行跳转。 
 * </pre>
 * @author hotent
 *
 */
@Service
public class JumpRule {
	/**
	 * 当所有的规则都不发生作用时，返回该值。
	 */
	public static final String RULE_INVALID="_RULE_INVALID";
	
	private Logger logger=LoggerFactory.getLogger(JumpRule.class);
	
	@Resource
	private BpmNodeRuleService bpmNodeRuleService;
	
	@Resource 
	GroovyScriptEngine scriptEngine; 
	
	@Resource
	RuntimeService runtimeService;
	
	public String evaluate(ExecutionEntity execution,Short isJumpForDef){
		logger.debug("enter the rule decision");
	
		String activityId=execution.getActivityId();
		String actDefId=execution.getProcessDefinitionId();
		//获取规则列表
		List<BpmNodeRule> ruleList=bpmNodeRuleService.getByDefIdNodeId(actDefId, activityId);
		
		//获取流程变量，参与规则的计算。
		Map<String, Object> vars=new HashMap<String, Object>();
		vars.putAll(execution.getVariables());
	
		if(BeanUtils.isEmpty(ruleList)){
			return "";
		}

		for(BpmNodeRule nodeRule:ruleList){
			try{
				Boolean rtn=scriptEngine.executeBoolean(nodeRule.getConditionCode(), vars) ;
				
				//返回不为空
				if(rtn!=null){
					//条件返回true，取得对应的节点，进行跳转。
					if(rtn){
						logger.debug("the last rule decision is " + nodeRule.getTargetNode() );
						return nodeRule.getTargetNode();
					}
				}
				//返回为空，说明条件表达式为空。
				else{
					logger.debug("条件表达式返回为空，请使用return 语句返回!" );
				}
			}
			//捕获执行出错。
			catch(Exception ex)
			{
				logger.debug("error message: " + ex.getMessage() );
			}
		}
		if(ruleList.size()>0 && BpmNodeSet.RULE_INVALID_NO_NORMAL.equals(isJumpForDef)) return RULE_INVALID;
		return "";
	}

}
