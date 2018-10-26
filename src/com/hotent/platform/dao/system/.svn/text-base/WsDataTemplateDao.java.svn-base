
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.WsDataTemplate;

@Repository
public class WsDataTemplateDao extends BaseDao<WsDataTemplate>
{
	@Override
	public Class<?> getEntityClass()
	{
		return WsDataTemplate.class;
	}
	
	public List<WsDataTemplate> getByWsSetId(Long wsSetId){
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("serviceId", wsSetId);
		return this.getList(getIbatisMapperNamespace() + ".getAll", params);
	}
}
