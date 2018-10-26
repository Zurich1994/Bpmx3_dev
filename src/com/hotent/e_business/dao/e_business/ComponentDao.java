
package com.hotent.e_business.dao.e_business;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.e_business.model.e_business.Component;

@Repository
public class ComponentDao extends BaseDao<Component>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Component.class;
	}

}
