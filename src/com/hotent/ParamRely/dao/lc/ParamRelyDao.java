
package com.hotent.ParamRely.dao.lc;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.ParamRely.model.lc.ParamRely;

@Repository
public class ParamRelyDao extends BaseDao<ParamRely>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ParamRely.class;
	}

}
