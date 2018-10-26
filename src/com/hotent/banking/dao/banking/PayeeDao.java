
package com.hotent.banking.dao.banking;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.banking.model.banking.Payee;

@Repository
public class PayeeDao extends BaseDao<Payee>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Payee.class;
	}
	public Payee getUSERID(String USERID)
	{
		return this.getUnique("getUSERID", USERID);
	}

}
