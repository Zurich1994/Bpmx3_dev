package com.hotent.platform.service.system;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.system.AuthRoleDao;
import com.hotent.platform.model.system.AuthRole;

/**
 *<pre>
 * 对象功能:SYS_AUTH_ROLE Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2014-08-08 10:16:21
 *</pre>
 */
@Service
public class AuthRoleService extends BaseService<AuthRole>
{
	@Resource
	private AuthRoleDao dao;
	
	
	
	public AuthRoleService()
	{
	}
	
	@Override
	protected IEntityDao<AuthRole, Long> getEntityDao() 
	{
		return dao;
	}

	public void delByAuthId(Long authId) {
		dao.delByAuthId(authId);
	}
	
	
}
