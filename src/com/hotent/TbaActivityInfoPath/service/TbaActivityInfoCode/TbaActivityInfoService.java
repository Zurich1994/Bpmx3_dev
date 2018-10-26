package com.hotent.TbaActivityInfoPath.service.TbaActivityInfoCode;
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
import com.hotent.TbaActivityInfoPath.model.TbaActivityInfoCode.TbaActivityInfo;
import com.hotent.TbaActivityInfoPath.dao.TbaActivityInfoCode.TbaActivityInfoDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class TbaActivityInfoService extends BaseService<TbaActivityInfo>
{
	@Resource
	private TbaActivityInfoDao dao;
	
	public TbaActivityInfoService()
	{
	}
	
	@Override
	protected IEntityDao<TbaActivityInfo,Long> getEntityDao() 
	{
		return dao;
	}
	
}
