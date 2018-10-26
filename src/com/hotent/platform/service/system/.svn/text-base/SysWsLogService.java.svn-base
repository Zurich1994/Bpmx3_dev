package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysWsLog;
import com.hotent.platform.dao.system.SysWsLogDao;

/**
 *<pre>
 * 对象功能:SYS_WS_LOG Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2013-05-31 10:41:48
 *</pre>
 */
@Service
public class SysWsLogService extends BaseService<SysWsLog>
{
	@Resource
	private SysWsLogDao dao;
	
	
	
	public SysWsLogService()
	{
	}
	
	@Override
	protected IEntityDao<SysWsLog, Long> getEntityDao() 
	{
		return dao;
	}
	
	
}
