package com.hotent.Container.dao.Container;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.Container.model.Container.Container;

@Repository
public class ContainerDao extends BaseDao<Container>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Container.class;
	}

	/**
	 * 添加  删除  修改  查询  
	 * @param 
	 * @return
	 */
	
}