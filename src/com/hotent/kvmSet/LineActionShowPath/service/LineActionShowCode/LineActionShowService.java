package com.hotent.kvmSet.LineActionShowPath.service.LineActionShowCode;
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
import com.hotent.LineActionShowPath.model.LineActionShowCode.LineActionShow;
import com.hotent.LineActionShowPath.dao.LineActionShowCode.LineActionShowDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class LineActionShowService extends BaseService<LineActionShow>
{
	@Resource
	private LineActionShowDao dao;
	
	public LineActionShowService()
	{
	}
	
	@Override
	protected IEntityDao<LineActionShow,Long> getEntityDao() 
	{
		return dao;
	}
	
}
