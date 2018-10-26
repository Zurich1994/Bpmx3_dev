package com.hotent.LineLoadRatePath.service.LineLoadRateCode;
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
import com.hotent.LineLoadRatePath.model.LineLoadRateCode.LineLoadRate;
import com.hotent.LineLoadRatePath.dao.LineLoadRateCode.LineLoadRateDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class LineLoadRateService extends BaseService<LineLoadRate>
{
	@Resource
	private LineLoadRateDao dao;
	
	public LineLoadRateService()
	{
	}
	
	@Override
	protected IEntityDao<LineLoadRate,Long> getEntityDao() 
	{
		return dao;
	}
	
}
