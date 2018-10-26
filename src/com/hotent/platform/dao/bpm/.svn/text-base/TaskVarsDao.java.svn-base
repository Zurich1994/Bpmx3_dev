package com.hotent.platform.dao.bpm;

import java.util.List;


import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.TaskVars;
@Repository
public class TaskVarsDao extends BaseDao<TaskVars>
{
	@Override
	public Class<TaskVars> getEntityClass()
	{
		return TaskVars.class;
	}
	
	/**
	 * 获取本任务所有的流程变量
	 * @param queryFilter
	 * @return
	 */
	public List<TaskVars> getTaskVars(QueryFilter queryFilter)
	{
		
		return getBySqlKey("getTaskVars", queryFilter);
	}

	public void delVarsByActInstId(String actInstId) {
		this.delBySqlKey("delVarsByActInstId", actInstId);
		
	}
}
