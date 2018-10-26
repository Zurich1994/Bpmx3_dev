package com.hotent.banking.service.banking;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hotent.banking.model.banking.BillPay;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.banking.model.banking.quickpay;
import com.hotent.banking.dao.banking.quickpayDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class quickpayService extends BaseService<quickpay>
{
	@Resource
	private quickpayDao dao;
	
	public quickpayService()
	{
	}
	
	@Override
	protected IEntityDao<quickpay,Long> getEntityDao() 
	{
		return dao;
	}
	public List<quickpay> getUSERID(String USERID) {
		return dao.getUSERID(USERID);
	}
}
