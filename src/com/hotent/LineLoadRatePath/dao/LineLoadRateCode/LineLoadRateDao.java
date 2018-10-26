
package com.hotent.LineLoadRatePath.dao.LineLoadRateCode;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.LineLoadRatePath.model.LineLoadRateCode.LineLoadRate;

@Repository
public class LineLoadRateDao extends BaseDao<LineLoadRate>
{
	@Override
	public Class<?> getEntityClass()
	{
		return LineLoadRate.class;
	}

}
