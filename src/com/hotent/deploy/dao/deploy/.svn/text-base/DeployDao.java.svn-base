package com.hotent.deploy.dao.deploy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.deploy.model.deploy.Deploy;

@Repository
public class DeployDao extends BaseDao<Deploy>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Deploy.class;
	}

	public List<Deploy> getByEqupmentId(Long deviceID) {
		// TODO Auto-generated method stub
		return getBySqlKey("getByEqupmentId",deviceID);
	}

	public void delByEquipmentId(Long deviceID) {
		// TODO Auto-generated method stub
		getBySqlKey("delByEquipmentId",deviceID);
	}

	public void updateEquipmentId(Long deviceID) {
		// TODO Auto-generated method stub
		getBySqlKey("updateEquipmentId",deviceID);
	}

	public void updateEquipmentIdByApplicationId(Long applicationID, Long deviceID) {
		// TODO Auto-generated method stub
		Map  map=new HashMap();
		map.put("deviceID", deviceID);
		map.put("applicationID", applicationID);	
		getBySqlKey("updateEquipmentIdByApplicationId", map);
	}
	public void delByEquipmentIdAndApplicationId(Long deviceID, Long applicationID) {
		// TODO Auto-generated method stub
		Map  map=new HashMap();
		map.put("deviceID", deviceID);
		map.put("applicationID", applicationID);	
		getBySqlKey("delByEquipmentIdAndApplicationId", map);
	}

	/**
	 * 添加  删除  修改  查询  
	 * @param 
	 * @return
	 */
	
}