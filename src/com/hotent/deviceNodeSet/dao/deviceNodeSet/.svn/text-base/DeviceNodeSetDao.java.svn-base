
package com.hotent.deviceNodeSet.dao.deviceNodeSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.deviceNodeSet.model.deviceNodeSet.DeviceNodeSet;
import com.hotent.deviceRouter.model.deviceRouter.DeviceRouter;

@Repository
public class DeviceNodeSetDao extends BaseDao<DeviceNodeSet>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DeviceNodeSet.class;
	}

	//zl更新actdefid...............................................
	public int updateActdefid(Map params){
		
		return getSqlSessionTemplate().update("updateActdefid",params);
	}
}
