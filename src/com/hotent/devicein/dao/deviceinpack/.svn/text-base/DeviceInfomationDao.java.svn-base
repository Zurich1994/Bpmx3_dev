
package com.hotent.devicein.dao.deviceinpack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.devicein.model.deviceinpack.DeviceInfomation;

@Repository
public class DeviceInfomationDao extends BaseDao<DeviceInfomation>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DeviceInfomation.class;
	}
	/**
	 * 获取指定节点记录。
	 * @param defId
	 * @param creatorId
	 * @param instanceAmount
	 * @return
	 */
	//zl...............................................
	public List<DeviceInfomation> getByNodeIdANDActdefId(String actdefID,String nodeID){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actdefid", actdefID);
		params.put("nodeid", nodeID);
		List<DeviceInfomation> list = getBySqlKey("getByNodeIdANDActdefId", params);
		
		return list;
	}
}
