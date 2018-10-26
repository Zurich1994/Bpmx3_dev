package com.hotent.application.dao.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.application.model.application.Application;

@Repository
public class ApplicationDao extends BaseDao<Application>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Application.class;
	}

	public void gengxin(Long a, Long applicationId) {
		// TODO Auto-generated method stub
		Map  map=new HashMap();
		map.put("id", a);
		map.put("applicationId", applicationId);	
		getBySqlKey("updateApplicationId", map);
	}

	/**
	 * 添加  删除  修改  查询  
	 * @param 
	 * @return
	 */
	
}