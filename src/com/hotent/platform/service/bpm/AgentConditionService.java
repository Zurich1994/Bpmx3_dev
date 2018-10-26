package com.hotent.platform.service.bpm;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.model.bpm.AgentCondition;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.dao.bpm.AgentConditionDao;

/**
 *<pre>
 * 对象功能:条件代理的配置 Service类
 * 开发公司:hotent
 * 开发人员:xxx
 * 创建时间:2013-04-29 11:15:10
 *</pre>
 */
@Service
public class AgentConditionService extends BaseService<AgentCondition>
{
	@Resource
	private AgentConditionDao dao;
	
	@Resource
	private BpmNodeUserService bpmNodeUserService;
	
	public AgentConditionService()
	{
	}
	
	@Override
	protected IEntityDao<AgentCondition, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 计算规则是否成立
	 * @param agentCondition 规则实体
	 * @param formId 表单ID
	 * @param vars 流程变量
	 * @return
	 */
	public Boolean conditionCheck(AgentCondition agentCondition,Long formId,Map<String,Object> formVars) {
		Boolean isPassCondition=true;
		
		JSONObject jsonObject = JSONObject.parseObject(agentCondition.getCondition());
		if(jsonObject==null)return isPassCondition;
		
		//如果表单ID为空  或者 与现用表单不匹配，则认为规则 成立		
		Long currentTableId = jsonObject.getLong("tableId");
		if(currentTableId == null || !currentTableId.equals(formId))return isPassCondition;
		
		BpmUserCondition bpmUserCondition = new BpmUserCondition();
		bpmUserCondition.setFormIdentity(currentTableId.toString());
		bpmUserCondition.setCondition(jsonObject.getString("condition"));
		isPassCondition = bpmNodeUserService.conditionCheck(bpmUserCondition, formId.toString(), formVars);
		return isPassCondition;
	}
}
