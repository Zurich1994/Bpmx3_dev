package com.hotent.platform.service.worktime;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.model.worktime.WorkTimeSetting;
import com.hotent.platform.dao.worktime.WorkTimeSettingDao;

/**
 * 对象功能:班次设置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:31
 */
@Service
public class WorkTimeSettingService extends BaseService<WorkTimeSetting>
{
	@Resource
	private WorkTimeSettingDao dao;
	
	public WorkTimeSettingService()
	{
	}
	
	@Override
	protected IEntityDao<WorkTimeSetting, Long> getEntityDao() 
	{
		return dao;
	}
}
