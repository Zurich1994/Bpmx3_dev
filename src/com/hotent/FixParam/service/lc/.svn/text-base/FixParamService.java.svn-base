package com.hotent.FixParam.service.lc;
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
import com.hotent.FixParam.model.lc.FixParam;
import com.hotent.FixParam.dao.lc.FixParamDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class FixParamService extends BaseService<FixParam>
{
	@Resource
	private FixParamDao dao;
	
	public FixParamService()
	{
	}
	
	@Override
	protected IEntityDao<FixParam,Long> getEntityDao() 
	{
		return dao;
	}
	
	
	public List<FixParam> getParamName(String timeType){
		return dao.getParamName(timeType);
	}
	
	public List<FixParam> getAllByTimeType(String timeType){
		return dao.getAllByTimeType(timeType);
	}
}
