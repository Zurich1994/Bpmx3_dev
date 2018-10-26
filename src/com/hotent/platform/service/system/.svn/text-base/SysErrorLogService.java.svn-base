package com.hotent.platform.service.system;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysErrorLogDao;
import com.hotent.platform.model.system.SysErrorLog;

/**
 *<pre>
 * 对象功能:系统错误日志 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-07-12 16:42:11
 *</pre>
 */
@Service
public class SysErrorLogService extends BaseService<SysErrorLog>
{
	@Resource
	private SysErrorLogDao dao;
	
	
	
	public SysErrorLogService()
	{
	}
	
	@Override
	protected IEntityDao<SysErrorLog, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 添加错误日志。
	 * @param account	账号
	 * @param ip		IP
	 * @param error		错误日志
	 * @param errorUrl	错误的URL
	 */
	public Long addError(String account,String ip,String error,String errorUrl){
		Long id = UniqueIdUtil.genId();
		SysErrorLog sysErrorLog =  new SysErrorLog();
		sysErrorLog.setId(id);
		sysErrorLog.setHashcode(error.hashCode()+"");
		sysErrorLog.setAccount(account);
		sysErrorLog.setIp(ip);
		sysErrorLog.setError(error);
		sysErrorLog.setErrorurl(StringUtils.substring(errorUrl, 0,1999));
		sysErrorLog.setErrordate(new Date());
		dao.add(sysErrorLog);
		return id;
	}
	
}
