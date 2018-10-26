package com.hotent.HistoryFlowRely.service.lc;
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
import com.hotent.HistoryFlowRely.model.lc.HistoryFlowRely;
import com.hotent.HistoryFlowRely.dao.lc.HistoryFlowRelyDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class HistoryFlowRelyService extends BaseService<HistoryFlowRely>
{
	@Resource
	private HistoryFlowRelyDao dao;
	
	public HistoryFlowRelyService()
	{
	}
	
	@Override
	protected IEntityDao<HistoryFlowRely,Long> getEntityDao() 
	{
		return dao;
	}
	
}
