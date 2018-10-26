package com.hotent.EventGraph.dao.EventGraph;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.EventGraph.model.EventGraph.EventGraph;

@Repository
public class EventGraphDao extends BaseDao<EventGraph>
{
	@Override
	public Class<?> getEntityClass()
	{
		return EventGraph.class;
	}

	/**
	 * 添加  删除  修改  查询  
	 
	 * @param 
	 *fieldName
	 * @return
	 */

	
}