
package com.hotent.banking.dao.banking;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.banking.model.banking.BillPay;
import com.hotent.core.db.BaseDao;
import com.hotent.banking.model.banking.quickpay;

@Repository
public class quickpayDao extends BaseDao<quickpay>
{
	@Override
	public Class<?> getEntityClass()
	{
		return quickpay.class;
	}
	public List<quickpay> getUSERID(String USERID){
		return getBySqlKey("getUSERID", USERID);
	}
}
