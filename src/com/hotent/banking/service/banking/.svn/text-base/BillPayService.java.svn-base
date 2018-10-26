package com.hotent.banking.service.banking;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.banking.model.banking.BillPay;
import com.hotent.banking.dao.banking.BillPayDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class BillPayService extends BaseService<BillPay>
{
	@Resource
	private BillPayDao dao;
	
	public BillPayService()
	{
	}
	
	@Override
	protected IEntityDao<BillPay,Long> getEntityDao() 
	{
		return dao;
	}
	public List<BillPay> getUSERID(String USERID) {
		return dao.getUSERID(USERID);
	}
	public List<BillPay> getDate(String start_time_text,String end_time_text)
	{
		return dao.getDate(start_time_text, end_time_text);
	}
	
}
