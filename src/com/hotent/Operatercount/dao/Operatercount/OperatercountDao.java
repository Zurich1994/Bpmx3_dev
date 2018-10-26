
package com.hotent.Operatercount.dao.Operatercount;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.Operatercount.model.Operatercount.Operatercount;

@Repository
public class OperatercountDao extends BaseDao<Operatercount>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Operatercount.class;
	}

}