package com.hotent.platform.service.bus;
import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.bus.BusQueryFilterDao;
import com.hotent.platform.dao.bus.BusQueryShareDao;
import com.hotent.platform.model.bus.BusQueryFilter;
import com.hotent.platform.model.bus.BusQueryShare;

/**
 *<pre>
 * 对象功能:查询过滤共享 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-20 10:10:44
 *</pre>
 */
@Service
public class BusQueryShareService extends BaseService<BusQueryShare>
{
	@Resource
	private BusQueryShareDao dao;
	@Resource
	private BusQueryFilterDao busQueryFilterDao;
	
	
	public BusQueryShareService()
	{
	}
	
	@Override
	protected IEntityDao<BusQueryShare, Long> getEntityDao() 
	{
		return dao;
	}

	public BusQueryShare getByFilterId(Long filterId) {
		return dao.getByFilterId(filterId);
	}

	public void save(BusQueryShare busQueryShare,boolean flag) {
		String shareRight =busQueryShare.getShareRight();
		JSONObject json =JSONObject.fromObject(shareRight);
		String type = json.getString("type");
		BusQueryFilter busQueryFilter = busQueryFilterDao.getById(busQueryShare.getFilterId());
		if(flag){
			dao.add(busQueryShare);
		}else{
			dao.update(busQueryShare);
		}
		if("none".equalsIgnoreCase(type))
			busQueryFilter.setIsShare(BusQueryFilter.NO_SHARE);
		else
			busQueryFilter.setIsShare(BusQueryFilter.SHARE);

		busQueryFilterDao.update(busQueryFilter);
	}
	
	
}
