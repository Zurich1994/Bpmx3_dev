package com.hotent.RegularOccurrence.service.lc;
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
import com.hotent.RegularOccurrence.model.lc.Regularoccurrence;
import com.hotent.RegularOccurrence.dao.lc.RegularoccurrenceDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class RegularoccurrenceService extends BaseService<Regularoccurrence>
{
	@Resource
	private RegularoccurrenceDao dao;
	
	public RegularoccurrenceService()
	{
	}
	
	@Override
	protected IEntityDao<Regularoccurrence,Long> getEntityDao() 
	{
		return dao;
	}
	
	public List<Regularoccurrence> getRegular(String processId,String timeType){
		return dao.getRegular(processId, timeType);
	}
	
	public List<Regularoccurrence> getProcessId(String timeType){
		return dao.getProcessId(timeType);
	}
	
}
