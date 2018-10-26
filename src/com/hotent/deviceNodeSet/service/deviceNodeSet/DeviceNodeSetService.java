package com.hotent.deviceNodeSet.service.deviceNodeSet;
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
import com.hotent.deviceNodeSet.model.deviceNodeSet.DeviceNodeSet;
import com.hotent.deviceNodeSet.dao.deviceNodeSet.DeviceNodeSetDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceNodeSetService extends BaseService<DeviceNodeSet>
{
	@Resource
	private DeviceNodeSetDao dao;
	
	public DeviceNodeSetService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceNodeSet,Long> getEntityDao() 
	{
		return dao;
	}
	//zl更新actdefid...............................................
	public int updateActdefid(String oldactdefID, String newactdefid){
		Map<String, String> params = new HashMap<String, String>();
		params.put("oldactdefID", oldactdefID);
		params.put("newactdefid", newactdefid);
		return dao.updateActdefid(params);
	}
}
