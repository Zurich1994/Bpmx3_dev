
package com.hotent.e_business.dao.e_business;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.e_business.model.e_business.Confirmation;

@Repository
public class ConfirmationDao extends BaseDao<Confirmation>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Confirmation.class;
	}
	public List<Confirmation> getByEmail(String email)
	{
		return getBySqlKey("getByEmail",email);
		
		
	}
}
