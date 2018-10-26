package com.hotent.Sysservice.dao.Sysservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.core.db.BaseDao;



import com.hotent.Sysservice.model.Sysservice.Sysservice;

@Repository
public class SysserviceDao extends BaseDao<Sysservice>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Sysservice.class;
	}	
	/**
	 * 添加  删除  修改  查询  
	 * @param 
	 * @return
	 */
	
	
}