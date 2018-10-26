package com.hotent.deviceQuotaRel.service.deviceQuotaRelPac;
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
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRel;
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRelAll;
import com.hotent.deviceQuotaRel.dao.deviceQuotaRelPac.DeviceQuotaRelDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;


@Service
public class DeviceQuotaRelService extends BaseService<DeviceQuotaRel>
{
	@Resource
	private DeviceQuotaRelDao dao;
	
	public DeviceQuotaRelService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceQuotaRel,Long> getEntityDao() 
	{
		return dao;
	}
	
	public List<DeviceQuotaRel> getMonQuaID(String deviceid){
		return dao.getMonQuaID(deviceid);	
	}

	public List<DeviceQuotaRel> getDevQuoRelID(String deviceid,String mqid) {
		return dao.getDevQuoRelID(deviceid, mqid);
	}
	public List<DeviceQuotaRelAll> getAllAll(){
		return dao.getAllAll();	
	}
	public List<DeviceQuotaRelAll> getDevQuaRelByDevId(String deviceid){
		return dao.getDevQuaRelByDevId(deviceid);	
	}
	public int updateFreq(DeviceQuotaRel deviceQuotaRel)
	{
		return dao.updateFreq(deviceQuotaRel);
	}
	public boolean exist(String deviceid, String quotaid) {
		return dao.exist(deviceid, quotaid);
	}

	public void delBydevIds(Long[] ids) {
	     dao.delBydevIds(ids);
	}
	
	
}
