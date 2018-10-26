
package com.hotent.banking.dao.banking;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.banking.model.banking.checkaccount;

@Repository
public class checkaccountDao extends BaseDao<checkaccount>
{
	@Override
	public Class<?> getEntityClass()
	{
		return checkaccount.class;
	}

}
