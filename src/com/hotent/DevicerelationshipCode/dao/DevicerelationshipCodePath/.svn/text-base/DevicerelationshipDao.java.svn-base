
package com.hotent.DevicerelationshipCode.dao.DevicerelationshipCodePath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.DevicerelationshipCode.model.DevicerelationshipCodePath.Devicerelationship;

@Repository
public class DevicerelationshipDao extends BaseDao<Devicerelationship>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Devicerelationship.class;
	}
//	public List<HashMap> getByDevAndOpp(String f_dev_id,String f_opp_id){
//		return getBySqlKey("getByDevAndOpp",f_dev_id,f_opp_id);
//	}
	//zl更新actdefid...............................................
	public int updateActdefid(Map params){
		
		return getSqlSessionTemplate().update("updateActdefids",params);
	}

}
