package com.hotent.HistoryScale.service.lc;
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
import com.hotent.HistoryScale.model.lc.HistoryScale;
import com.hotent.HistoryScale.dao.lc.HistoryScaleDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class HistoryScaleService extends BaseService<HistoryScale>
{
	@Resource
	private HistoryScaleDao dao;
	
	public HistoryScaleService()
	{
	}
	
	@Override
	protected IEntityDao<HistoryScale,Long> getEntityDao() 
	{
		return dao;
	}
	
}
