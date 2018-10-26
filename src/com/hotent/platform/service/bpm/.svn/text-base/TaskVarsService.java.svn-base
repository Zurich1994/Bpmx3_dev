package com.hotent.platform.service.bpm;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.bpm.TaskVarsDao;
import com.hotent.platform.model.bpm.TaskVars;

/**
 * 对象功能:流程变量定义 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-01 16:50:07
 */
@Service
public class TaskVarsService extends BaseService<TaskVars>
{

	@Resource
	private TaskVarsDao dao;
	public TaskVarsService()
	{
	}
	
	@Override
	protected IEntityDao<TaskVars, Long> getEntityDao() 
	{
		return dao;
	}
    /**
     * 获取本任务下的所有流程变量
     * @param queryFilter
     * @return
     */
	public List<TaskVars> getVars(QueryFilter queryFilter){
	    return dao.getTaskVars(queryFilter);
	}
	
	public void delVarsByActInstId(String actInstId){
		dao.delVarsByActInstId(actInstId);
	}
}
