package com.hotent.platform.service.system;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.model.system.SysJobLog;
import com.hotent.platform.dao.system.SysJobLogDao;

/**
 * 对象功能:SYS_JOBLOG Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-28 17:01:51
 */
@Service
public class SysJobLogService extends BaseService<SysJobLog>
{
	@Resource
	private SysJobLogDao dao;
	
	public SysJobLogService()
	{
	}
	
	@Override
	protected IEntityDao<SysJobLog, Long> getEntityDao() 
	{
		return dao;
	}
	
	public void add(ProcessCmd cmd){
		
	}
}
