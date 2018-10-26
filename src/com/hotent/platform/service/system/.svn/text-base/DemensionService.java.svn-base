package com.hotent.platform.service.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.dao.system.DemensionDao;

/**
 * 对象功能:维度信息 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-11-08 12:04:22
 */
@Service
public class DemensionService extends BaseService<Demension>
{
	@Resource
	private DemensionDao dao;
	
	public DemensionService()
	{
	}
	
	@Override
	protected IEntityDao<Demension, Long> getEntityDao() {
		return dao;
	}
	
	/* *
	 * 对象功能：判断是否存在该维度的记录
	 * 开发公司:广州宏天软件有限公司
	 * 开发人员:pkq
     * 创建时间:2011-11-08 12:04:22 
	 */
	public boolean getNotExists(Map params)
	{
		return dao.getNotExists(params);
	}	
	
	/**
	 * 对象功能：根据查询条件查询维度的记录
	 * 开发公司:广州宏天软件有限公司
	 * 开发人员:pkq
     * 创建时间:2011-11-08 12:04:22 
	 */
	public List<Demension> getDemenByQuery(QueryFilter queryFilter)
	{
		return dao.getDemenByQuery(queryFilter);
	}
}
