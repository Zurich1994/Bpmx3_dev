package com.hotent.equipment.dao.equipment;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.equipment.model.equipment.Equipment;

@Repository
public class EquipmentDao extends BaseDao<Equipment>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Equipment.class;
	}

	/**
	 * 添加  删除  修改  查询  
	 * @param 
	 * @return
	 */
	
}