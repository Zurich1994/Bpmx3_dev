
package com.hotent.HistoryScale.dao.lc;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.HistoryScale.model.lc.HistoryScale;

@Repository
public class HistoryScaleDao extends BaseDao<HistoryScale>
{
	@Override
	public Class<?> getEntityClass()
	{
		return HistoryScale.class;
	}

}
