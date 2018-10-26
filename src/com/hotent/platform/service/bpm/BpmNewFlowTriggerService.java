package com.hotent.platform.service.bpm;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmNewFlowTriggerDao;
import com.hotent.platform.model.bpm.BpmNewFlowTrigger;

/**
 *<pre>
 * 对象功能:触发新流程配置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2015-05-28 11:20:59
 *</pre>
 */
@Service
public class BpmNewFlowTriggerService extends  BaseService<BpmNewFlowTrigger>
{
	@Resource
	private BpmNewFlowTriggerDao dao;
	
	
	
	public BpmNewFlowTriggerService()
	{
	}
	
	@Override
	protected IEntityDao<BpmNewFlowTrigger, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 通过 节点ID 流程key获取触发设置
	 * @param nodeId
	 * @param flowKey
	 * @return
	 */
	public BpmNewFlowTrigger getByFlowkeyNodeId(String nodeId, String flowKey) {
		return dao.getByFlowkeyNodeId(nodeId,flowKey);
	}
	
	/**
	 * 获取某动作触发流程数据
	 * @param nodeId
	 * @param flowKey
	 * @param action
	 */
	public BpmNewFlowTrigger getByNodeAction(String nodeId, String flowKey, String action) {
		BpmNewFlowTrigger triggerSetting = dao.getByFlowkeyNodeId(nodeId,flowKey);
		if(triggerSetting != null && action.equals(triggerSetting.getAction()))
			return triggerSetting;
		
		return null;
	}
	
}
