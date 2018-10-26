package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNewFlowTrigger;
/**
 *<pre>
 * 对象功能:触发新流程配置 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2015-05-28 11:20:59
 *</pre>
 */
@Repository
public class BpmNewFlowTriggerDao extends BaseDao<BpmNewFlowTrigger>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmNewFlowTrigger.class;
	}
	
	
	public BpmNewFlowTrigger getByFlowkeyNodeId(String nodeId, String flowKey) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("nodeId", nodeId);
		params.put("flowkey", flowKey);
		return this.getUnique("getByFlowkeyNodeId",params);
	}

	
}