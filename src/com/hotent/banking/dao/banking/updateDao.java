
package com.hotent.banking.dao.banking;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.banking.model.banking.update;

@Repository
public class updateDao extends BaseDao<update>
{
	@Override
	public Class<?> getEntityClass()
	{
		return update.class;
	}

}
