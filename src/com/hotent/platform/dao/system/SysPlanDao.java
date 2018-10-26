package com.hotent.platform.dao.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.SysPlan;
/**
 *<pre>
 * 对象功能:日程表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 *</pre>
 */
@Repository
public class SysPlanDao extends BaseDao<SysPlan>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysPlan.class;
	}
	
	public SysPlan getById(Long id){		
		return (SysPlan)this.getUnique("getById", id);
	}
	
	public List<SysPlan> getBySubmitId(Map<String,Object> params){		
		return this.getBySqlKey("getBySubmitId", params);
	}
	
	public List<SysPlan> getByChargeId(Map<String,Object> params){		
		return this.getBySqlKey("getByChargeId", params);
	}
	
	public List<SysPlan> getByParticipantId(Map<String,Object> params){		
		return this.getBySqlKey("getByParticipantId", params);
	}
	
	public List<SysPlan> getBySubscribeId(Map<String,Object> params){		
		return this.getBySqlKey("getBySubscribeId", params);
	}
	
	public List<SysPlan> getListBySubscribeId(QueryFilter queryFilter){		
		return this.getBySqlKey("getListBySubscribeId", queryFilter);
	}
	
	public List<SysPlan> getByChargeUserIds(QueryFilter queryFilter){		
		return this.getBySqlKey("getByChargeUserIds", queryFilter);
	}

	public List<SysPlan> getMyList(QueryFilter queryFilter) {
		return  this.getBySqlKey("getMyList", queryFilter);
	}
}