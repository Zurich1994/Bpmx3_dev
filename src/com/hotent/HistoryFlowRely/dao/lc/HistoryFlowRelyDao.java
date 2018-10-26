
package com.hotent.HistoryFlowRely.dao.lc;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.HistoryFlowRely.model.lc.HistoryFlowRely;

@Repository
public class HistoryFlowRelyDao extends BaseDao<HistoryFlowRely>
{
	@Override
	public Class<?> getEntityClass()
	{
		return HistoryFlowRely.class;
	}

}
