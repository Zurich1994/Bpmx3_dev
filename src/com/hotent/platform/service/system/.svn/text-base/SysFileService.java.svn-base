package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.dao.system.SysFileDao;

/**
 * 对象功能:附件 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-26 18:19:16
 */
@Service
public class SysFileService extends BaseService<SysFile>
{
	@Resource
	private SysFileDao dao;
	
	public SysFileService()
	{
	}
	
	@Override
	protected IEntityDao<SysFile, Long> getEntityDao() {
		return dao;
	}
	
	public List<SysFile> getFileAttch(QueryFilter fileter){
		return dao.getFileAttch(fileter);
	}

	
}
