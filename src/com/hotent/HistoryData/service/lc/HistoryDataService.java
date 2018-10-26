package com.hotent.HistoryData.service.lc;
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
import com.hotent.HistoryData.model.lc.HistoryData;
import com.hotent.HistoryData.dao.lc.HistoryDataDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class HistoryDataService extends BaseService<HistoryData>
{
	@Resource
	private HistoryDataDao dao;
	
	public HistoryDataService()
	{
	}
	
	@Override
	protected IEntityDao<HistoryData,Long> getEntityDao() 
	{
		return dao;
	}
	
	public List<HistoryData> getProcessId(String timeType){
		return dao.getProcessId(timeType);
	}
	
	public List<HistoryData> getPictureData(String processId,String timeType){
		return dao.getPictureData(processId,timeType);
	}
	
	public List<HistoryData> getOccurence(String processId,String occurenceTime){
		return dao.getOccurence(processId, occurenceTime);
	}
	
}
