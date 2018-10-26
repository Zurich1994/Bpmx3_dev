package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.system.SysAcceptIpDao;
import com.hotent.platform.model.system.SysAcceptIp;

/**
 * 对象功能:SYS_ACCEPT_IP Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-20 17:35:46
 */
@Service
public class SysAcceptIpService extends BaseService<SysAcceptIp>
{
	@Resource
	private SysAcceptIpDao sysAcceptIpDao;
	
	public SysAcceptIpService()
	{
	}
	
	@Override
	protected IEntityDao<SysAcceptIp, Long> getEntityDao() 
	{
		return sysAcceptIpDao;
	}	
}
