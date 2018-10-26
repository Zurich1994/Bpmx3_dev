
package com.hotent.Xlkcs.dao.Xlkcs;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.Xlkcs.model.Xlkcs.Xlkcs;

@Repository
public class XlkcsDao extends BaseDao<Xlkcs>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Xlkcs.class;
	}

}
