package com.hotent.platform.service.bpm;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.bpm.BpmDefActDao;
import com.hotent.platform.model.bpm.BpmDefAct;




/**
 * 对象功能:流程定义权限明细 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xucx
 * 创建时间:2014-03-05 14:10:50
 */
@Service
public class BpmDefActService extends BaseService<BpmDefAct>
{
	@Resource
	private BpmDefActDao bpmDefActDao;
	
	
	public BpmDefActService(){
	}
	
	@Override
	protected IEntityDao<BpmDefAct, Long> getEntityDao() 
	{
		return bpmDefActDao;
	}
}
