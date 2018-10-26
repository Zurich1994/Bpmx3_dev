package com.hotent.platform.dao.bpm;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.AgentCondition;
/**
 *<pre>
 * 对象功能:条件代理的配置 Dao类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-04-29 11:15:10
 *</pre>
 */
@Repository
public class AgentConditionDao extends BaseDao<AgentCondition>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AgentCondition.class;
	}
	
	public List<AgentCondition> getByMainId(Long settingid) {
		return this.getBySqlKey("getAgentConditionList", settingid);
	}
	
	public void delByMainId(Long settingid) {
		this.delBySqlKey("delByMainId", settingid);
	}

}