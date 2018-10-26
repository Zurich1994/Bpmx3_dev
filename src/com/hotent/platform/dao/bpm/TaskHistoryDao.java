package com.hotent.platform.dao.bpm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.bpm.model.ProcessTaskHistory;
import com.hotent.core.db.BaseDao;

@Repository
public class TaskHistoryDao extends BaseDao<ProcessTaskHistory >
{
	
	
	@Override
	public Class getEntityClass()
	{
		return ProcessTaskHistory.class;
	}
	
	/**
	 * 根据流程实例id查找已完成的任务
	 * @param processInstanceId
	 * @return
	 */
	public ProcessTaskHistory getLastFinshTaskByProcId(String processInstanceId){	
		List<ProcessTaskHistory> list= getBySqlKey("getFinshTaskByProcId", processInstanceId);
		if(list.size()>0)
			return list.get(0);
		return  null;
	}

}
