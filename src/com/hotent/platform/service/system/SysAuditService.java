package com.hotent.platform.service.system;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.model.system.SysAudit;
import com.hotent.platform.dao.system.SysAuditDao;

/**
 * 对象功能:系统日志 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-26 11:35:04
 */
@Service
public class SysAuditService extends BaseService<SysAudit>
{
	@Resource
	private SysAuditDao dao;
	
	public SysAuditService()
	{
	}
	
	@Override
	protected IEntityDao<SysAudit, Long> getEntityDao() {
		return dao;
	}
}
