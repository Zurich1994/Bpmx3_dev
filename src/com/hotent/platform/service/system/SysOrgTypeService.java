package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysOrgParam;
import com.hotent.platform.model.system.SysOrgType;
import com.hotent.platform.dao.system.SysOrgTypeDao;

/**
 * 对象功能:组织结构类型 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-11-27 09:55:21
 */
@Service
public class SysOrgTypeService extends BaseService<SysOrgType>
{
	@Resource
	private SysOrgTypeDao dao;
	
	public SysOrgTypeService()
	{
	}
	
	@Override
	protected IEntityDao<SysOrgType, Long> getEntityDao() 
	{
		return dao;
	}
	
	public Integer getMaxLevel(Long demId) {
		return dao.getMaxLevel(demId);
	}
	/**
	 * 根据维度Id获取该维度下所有类型。
	 * @param demId
	 * @return
	 */
	public List<SysOrgType> getByDemId(long demId){
		return dao.getByDemId(demId);
	}
}
