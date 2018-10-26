package com.hotent.switchSet.service.switchSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.switchSet.model.switchSet.DeviceSwitch;
import com.hotent.switchSet.dao.switchSet.DeviceSwitchDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceSwitchService extends BaseService<DeviceSwitch>
{
	@Resource
	private DeviceSwitchDao dao;
	
	public DeviceSwitchService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceSwitch,Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 获取指定节点记录。
	 * @param defId
	 * @param creatorId
	 * @param instanceAmount
	 * @return
	 */
	//zl...............................................
	public List<DeviceSwitch> getByNodeIdANDActdefId(String actdefID,String nodeID){
		return dao.getByNodeIdANDActdefId(actdefID, nodeID);
	}
}
