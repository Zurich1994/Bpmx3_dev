package com.hotent.Atomicoperate.dao.Atomicoperate;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.Atomicoperate.model.Atomicoperate.Atomicoperate;

@Repository
public class AtomicoperateDao extends BaseDao<Atomicoperate>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Atomicoperate.class;
	}

	/**
	 * 添加  删除  修改  查询  
	 * @param 
	 * @return
	 */
	public List<Atomicoperate> getByServiceId(Long serviceId){
		List<Atomicoperate> list=getBySqlKey("getByServiceId", serviceId);
		return list;
	}
}