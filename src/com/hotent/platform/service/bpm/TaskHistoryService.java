package com.hotent.platform.service.bpm;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessTaskHistory;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.bpm.TaskHistoryDao;

@Service
public class TaskHistoryService extends BaseService<ProcessTaskHistory>{
	@Resource
	private TaskHistoryDao dao;

	public TaskHistoryService() {
	}

	@Override
	protected IEntityDao<ProcessTaskHistory, Long> getEntityDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	
}
