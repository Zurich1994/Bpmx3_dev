package com.hotent.serviceSet.service.serviceSet;
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
import com.hotent.serviceSet.model.serviceSet.DeviceServer;
import com.hotent.serviceSet.dao.serviceSet.DeviceServerDao;
import com.hotent.switchSet.model.switchSet.DeviceSwitch;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceServerService extends BaseService<DeviceServer>
{
	@Resource
	private DeviceServerDao dao;
	
	public DeviceServerService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceServer,Long> getEntityDao() 
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
	public List<DeviceServer> getByNodeIdANDActdefId(String actdefID,String nodeID){
		return dao.getByNodeIdANDActdefId(actdefID, nodeID);
	}
}
