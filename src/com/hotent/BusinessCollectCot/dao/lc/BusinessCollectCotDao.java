
package com.hotent.BusinessCollectCot.dao.lc;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.BusinessCollectCot.model.lc.BusinessCollectCot;

@Repository
public class BusinessCollectCotDao extends BaseDao<BusinessCollectCot>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BusinessCollectCot.class;
	}

}
