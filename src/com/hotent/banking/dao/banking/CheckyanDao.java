
package com.hotent.banking.dao.banking;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.banking.model.banking.Checkyan;

@Repository
public class CheckyanDao extends BaseDao<Checkyan>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Checkyan.class;
	}

}
