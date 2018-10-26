package com.hotent.deviceRouter.service.deviceRouter;
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
import com.hotent.deviceRouter.model.deviceRouter.DeviceRouter;
import com.hotent.deviceRouter.dao.deviceRouter.DeviceRouterDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceRouterService extends BaseService<DeviceRouter>
{
	@Resource
	private DeviceRouterDao dao;
	
	public DeviceRouterService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceRouter,Long> getEntityDao() 
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
	public List<DeviceRouter> getByNodeIdANDActdefId(String actdefID,String nodeID){
		return dao.getByNodeIdANDActdefId(actdefID, nodeID);
	}
}
