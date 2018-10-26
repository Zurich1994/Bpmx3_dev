package org.activiti.engine.impl.el;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.Condition;

import com.hotent.core.bpm.WorkFlowException;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
/**
 * GroovyEngine条件。
 * 
 * <pre>
 *用于分支网关和条件网关。
 * 1.在表达式中如何使用BO.
 * 		例如 有一个订单bo，叫order。
 * 		使用方法如下return order.getInt("amount")>1;
 * 2.如果这个流程属于一个子流程，那么这个时候如果有一个条件分支我们如何处理呢？
 *  	可以判断当前的bo实例是否包含某个bo。
 *  	例如：
 *  	if(boMap.containsKey("order")){
 *  
 *  	}
 * 
 * 构建组：x5-bpmx-activiti
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-10-22-下午10:39:41
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class GroovyCondition implements Condition {
	/**
	 * serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -5577703954744892854L;

	private String script="";
	
	public GroovyCondition(String condition){
		this.script=condition;
	} 
	

	public boolean evaluate(DelegateExecution execution) {
		
		Map<String,Object> maps=execution.getVariables();
		//添加execution。
		maps.put(VariableScopeElResolver.EXECUTION_KEY, execution);
		
		
		GroovyScriptEngine engine=AppUtil.getBean(GroovyScriptEngine.class);
		String newScript = replaceSpecialChar(script);
		 
		try {
			return engine.executeBoolean(newScript, maps);
		} catch (Exception e) { 
			StringBuffer message = new StringBuffer("条件脚本解析异常！请联系管理员。");
			message.append("<br/><br/>节点："+execution.getCurrentActivityName()+"——"+execution.getCurrentActivityId());
			message.append("<br/><br/>脚本："+script);
			message.append("<br/><br/>流程变量："+maps.toString()); 
			
			throw new WorkFlowException(message.toString());
		}
	}
	
	private String replaceSpecialChar(String str){
		if(StringUtil.isEmpty(str)) return ""; 
		str = str.trim();
		
		if(str.startsWith("${")) return  str.substring(2, str.length()-1);
		return str;
	}
}
