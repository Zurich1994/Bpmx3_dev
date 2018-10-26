
package com.hotent.BusinessInformat.dao.lc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.devicein.model.deviceinpack.DeviceInfomation;
import com.hotent.BusinessInformat.model.lc.BusinessInformat;

@Repository
public class BusinessInformatDao extends BaseDao<BusinessInformat>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BusinessInformat.class;
	}
	/**
	 * 获取指定流程定义的发生列表。
	 * @param defId
	 * @param creatorId
	 * @param instanceAmount
	 * @return
	 */
	//zl...............................................
	public List<BusinessInformat> getByDefId(String defId){

		List<BusinessInformat> list = getBySqlKey("getByDefId", defId);
		
		return list;
	}
}
