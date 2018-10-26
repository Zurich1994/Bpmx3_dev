package com.hotent.BusinessInformat.service.lc;
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
import com.hotent.BusinessInformat.model.lc.BusinessInformat;
import com.hotent.BusinessInformat.dao.lc.BusinessInformatDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class BusinessInformatService extends BaseService<BusinessInformat>
{
	@Resource
	private BusinessInformatDao dao;
	
	public BusinessInformatService()
	{
	}
	
	@Override
	protected IEntityDao<BusinessInformat,Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 获取指定流程定义的发生列表。
	 * @param defId
	 * @param creatorId
	 * @param instanceAmount
	 * @return
	 */
	//zl...............................................
	public List<BusinessInformat> getByDefId(String defId){
		return dao.getByDefId(defId);
	}
}
