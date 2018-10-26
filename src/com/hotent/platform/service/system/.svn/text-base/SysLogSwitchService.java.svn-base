package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysAudit;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysLogSwitch;
import com.hotent.platform.dao.system.SysLogSwitchDao;
import com.sun.corba.se.spi.logging.LogWrapperBase;

/**
 *<pre>
 * 对象功能:日志开关 Service类
 * 开发公司:广州宏天
 * 开发人员:Raise
 * 创建时间:2013-06-24 11:12:27
 *</pre>
 */
@Service
public class SysLogSwitchService extends BaseService<SysLogSwitch>
{
	@Resource
	private SysLogSwitchDao dao;
	
	
	
	public SysLogSwitchService()
	{
	}
	
	@Override
	protected IEntityDao<SysLogSwitch, Long> getEntityDao() 
	{
		return dao;
	}
	
	public List<SysLogSwitch> getAll(){
		
		List<SysLogSwitch> switchs = new ArrayList<SysLogSwitch>();
		SysAuditModelType[] types =  SysAuditModelType.values();
		for(SysAuditModelType type:types){
			SysLogSwitch logSwitch = dao.getByModel(type.toString());
			if(logSwitch ==null){
				logSwitch = getInitSysLogSwitch(type);
			}
			switchs.add(logSwitch);
		}
		return switchs;
	}
	
	private SysLogSwitch getInitSysLogSwitch(SysAuditModelType sysAuditModelType){
		SysLogSwitch sysLogSwitch = new SysLogSwitch();
		sysLogSwitch.setModel(sysAuditModelType.toString());
		sysLogSwitch.setStatus(SysLogSwitch.STATUS_CLOSE);
		return sysLogSwitch;
	}

	public SysLogSwitch getByModel(String ownermodel) {
		return dao.getByModel(ownermodel);
	}
}
