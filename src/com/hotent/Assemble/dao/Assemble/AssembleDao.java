package com.hotent.Assemble.dao.Assemble;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.Assemble.model.Assemble.Assemble;

@Repository
public class AssembleDao extends BaseDao<Assemble>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Assemble.class;
	}

	public List<Assemble> getBySystemId(Long sysId) {
		// TODO Auto-generated method stub
		List<Assemble> list=getBySqlKey("getBySystemId", sysId);
		return list;
	}

	public List<Assemble> getByServiceId(Long serviceId) {
		// TODO Auto-generated method stub
		List<Assemble> list=getBySqlKey("getByServiceId", serviceId);
		return list;
	}

	public void gengxin(Long a, Long applicationId) {
		// TODO Auto-generated method stub
		Map  map=new HashMap();
		map.put("id", a);
		map.put("applicationId", applicationId);	
		getBySqlKey("updateApplicationId", map);
	}
	
	public void cleanByApplicationId(Long applicationId) {
		// TODO Auto-generated method stub
		getBySqlKey("cleanByApplicationId", applicationId);
	}
	public void cleanByApplicationIdAndSystemId(Long applicationId,Long systemId) {
		// TODO Auto-generated method stub
		Map  map=new HashMap();
		map.put("sysId", systemId);
		map.put("applicationId", applicationId);	
		getBySqlKey("cleanByApplicationIdAndSystemId", map);
	}
	public void cleanByApplicationIdAndServiceId(Long applicationId,Long serviceId) {
		// TODO Auto-generated method stub
		Map  map=new HashMap();
		map.put("serviceId", serviceId);
		map.put("applicationId", applicationId);	
		getBySqlKey("cleanByApplicationIdAndServiceId", map);
	}
}