package com.hotent.extension.service.bpm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.extension.dao.bpm.ProcessTypeDao;
import com.hotent.extension.model.bpm.ProcessType;
@Service
public class ProcessTypeService extends BaseService{
	@Resource
	private ProcessTypeDao processTypeDao;
	
	protected IEntityDao getEntityDao() {
		// TODO Auto-generated method stub
		return this.processTypeDao;
	}
	//获取我的流程分类，封装成processType
	public List<ProcessType> getAllMyProcessType(Long userId){
		return processTypeDao.getAllMyProcessType(userId);
	}

	//获取我的任务分类，封装成processType
	public List<ProcessType> getAllMyTaskType(Long userId){
		return processTypeDao.getAllMyTaskType(userId);
	}
	//获取我的已办流程分类，封装成processType
		public List<ProcessType> getAlreadyProcess(Long userId) {
			return processTypeDao.getAlreadyProcess(userId);
		}
		
		//获取我的已办任务分类，封装成processType
		public List<ProcessType> getAlreadyTask(Long userId) {
			return processTypeDao.getAlreadyTask(userId);
		}
		
		//获取我的办结流程分类，封装成processType
		public List<ProcessType> getCompletedProcess(Long userId) {
			return processTypeDao.getCompletedProcess(userId);
		}
		
		//获取我的办结任务分类，封装成processType
		public List<ProcessType> getCompletedTask(Long userId) {
			return processTypeDao.getCompletedTask(userId);
		}
}
