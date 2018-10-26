package com.hotent.platform.service.bpm;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.bpm.AgentDefDao;
import com.hotent.platform.model.bpm.AgentDef;

/**
 *<pre>
 * 对象功能:代理的流程列表 Service类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-04-29 11:15:10
 *</pre>
 */
@Service
public class AgentDefService extends BaseService<AgentDef>
{
	@Resource
	private AgentDefDao dao;
	
	
	
	public AgentDefService()
	{
	}
	
	@Override
	protected IEntityDao<AgentDef, Long> getEntityDao() 
	{
		return dao;
	}
	
	
}
