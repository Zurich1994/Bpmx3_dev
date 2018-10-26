
package com.hotent.whistory.dao.whistory;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.whistory.model.whistory.WHistory;

@Repository
public class WHistoryDao extends BaseDao<WHistory>
{
	@Override
	public Class<?> getEntityClass()
	{
		return WHistory.class;
	}

}
