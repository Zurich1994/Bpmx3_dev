
package com.hotent.nodetimeandcount.dao.nodetimeandcount;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.nodetimeandcount.model.nodetimeandcount.Nodetimeandcount;

@Repository
public class NodetimeandcountDao extends BaseDao<Nodetimeandcount>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Nodetimeandcount.class;
	}

}
