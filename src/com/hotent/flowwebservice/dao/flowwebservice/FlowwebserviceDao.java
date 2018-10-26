
package com.hotent.flowwebservice.dao.flowwebservice;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.flowwebservice.model.flowwebservice.Flowwebservice;

@Repository
public class FlowwebserviceDao extends BaseDao<Flowwebservice>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Flowwebservice.class;
	}


}
