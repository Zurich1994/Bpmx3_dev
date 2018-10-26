package com.hotent.extension.dao.bpm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.extension.model.bpm.ProcessType;
@Repository
public class ProcessTypeDao extends BaseDao<ProcessType> {

	
	public Class getEntityClass() {
		return ProcessType.class;
	}
	
	public List<ProcessType> getAllMyProcessType(Long userId){
		return getBySqlKey("getAllMyProcessType", userId);
	}
	public List<ProcessType> getAllMyTaskType(Long userId){
		return getBySqlKey("getAllMyTaskType", userId);
	}
	public List<ProcessType> getAlreadyProcess(Long userId){
		return getBySqlKey("getAlreadyProcess", userId);
	}
	public List<ProcessType> getAlreadyTask(Long userId){
		return getBySqlKey("getAlreadyTask", userId);
	}
	public List<ProcessType> getCompletedProcess(Long userId){
		return getBySqlKey("getCompletedProcess", userId);
	}
	public List<ProcessType> getCompletedTask(Long userId){
		return getBySqlKey("getCompletedTask", userId);
	}
}
