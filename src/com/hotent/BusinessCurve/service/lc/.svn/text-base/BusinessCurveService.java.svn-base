package com.hotent.BusinessCurve.service.lc;
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
import com.hotent.BusinessCurve.model.lc.BusinessCurve;
import com.hotent.BusinessCurve.dao.lc.BusinessCurveDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class BusinessCurveService extends BaseService<BusinessCurve>
{
	@Resource
	private BusinessCurveDao dao;
	
	public BusinessCurveService()
	{
	}
	
	@Override
	protected IEntityDao<BusinessCurve,Long> getEntityDao() 
	{
		return dao;
	}
	
}
