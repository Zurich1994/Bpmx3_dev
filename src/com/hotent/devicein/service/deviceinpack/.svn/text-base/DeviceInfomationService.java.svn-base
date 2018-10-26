package com.hotent.devicein.service.deviceinpack;
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
import com.hotent.devicein.model.deviceinpack.DeviceInfomation;
import com.hotent.devicein.dao.deviceinpack.DeviceInfomationDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceInfomationService extends BaseService<DeviceInfomation>
{
	@Resource
	private DeviceInfomationDao dao;
	
	public DeviceInfomationService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceInfomation,Long> getEntityDao() 
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
	public List<DeviceInfomation> getByNodeIdANDActdefId(String actdefID,String nodeID){
		return dao.getByNodeIdANDActdefId(actdefID, nodeID);
	}
}
