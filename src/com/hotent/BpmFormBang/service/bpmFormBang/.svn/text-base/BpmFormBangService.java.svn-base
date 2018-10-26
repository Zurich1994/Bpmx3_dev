package com.hotent.BpmFormBang.service.bpmFormBang;
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
import com.hotent.BpmFormBang.model.bpmFormBang.BpmFormBang;
import com.hotent.BpmFormBang.dao.bpmFormBang.BpmFormBangDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;


@Service
public class BpmFormBangService extends BaseService<BpmFormBang>
{
	@Resource
	private BpmFormBangDao dao;
	
	public BpmFormBangService()
	{
	}
	
	public BpmFormBang getByDefbId(String defbId)	{
		//return dao.getByDefbId(defbId);
		return dao.getUnique("getByDefbId", defbId);
	}
	
	public List<BpmFormBang> getByBtnName(String btnname)	{
		//return dao.getByDefbId(defbId);
		return dao.getByBtnName(btnname);
	}
	public List<BpmFormBang> getByFormDefId(String formDefId)	{
		//return dao.getByDefbId(defbId);
		return dao.getByFormDefId(formDefId);
	}
	@Override
	protected IEntityDao<BpmFormBang,Long> getEntityDao() 
	{
		return dao;
	}
	
}
