
package com.hotent.support.dao.catelog;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.support.model.catelog.ProductOs;

@Repository
public class ProductOsDao extends BaseDao<ProductOs>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ProductOs.class;
	}

}
