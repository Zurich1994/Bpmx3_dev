package com.hotent.platform.service.system;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.system.SysUrlPermissionDao;
import com.hotent.platform.model.system.SysUrlPermission;

/**
 *<pre>
 * 对象功能:URL地址拦截管理 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wdr
 * 创建时间:2014-03-27 16:32:01
 *</pre>
 */
@Service
public class SysUrlPermissionService extends BaseService<SysUrlPermission>
{
	@Resource
	private SysUrlPermissionDao dao;
	
	
	public SysUrlPermissionService()
	{
	}
	
	@Override
	protected IEntityDao<SysUrlPermission, Long> getEntityDao() 
	{
		return dao;
	}


	
	
}
