package com.hotent.DviceLoadRatePath.service.DviceLoadRateCode;
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
import com.hotent.DviceLoadRatePath.model.DviceLoadRateCode.DviceLoadRate;
import com.hotent.DviceLoadRatePath.dao.DviceLoadRateCode.DviceLoadRateDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DviceLoadRateService extends BaseService<DviceLoadRate>
{
	@Resource
	private DviceLoadRateDao dao;
	
	public DviceLoadRateService()
	{
	}
	
	@Override
	protected IEntityDao<DviceLoadRate,Long> getEntityDao() 
	{
		return dao;
	}
	
}
