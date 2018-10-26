package com.hotent.platform.service.form;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.TaskReadDao;
import com.hotent.platform.model.bpm.TaskRead;
import com.hotent.platform.model.system.SysUser;

/**
 *<pre>
 * 对象功能:任务是否已读 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hotent
 * 创建时间:2013-04-16 17:30:53
 *</pre>
 */
@Service
public class TaskReadService extends BaseService<TaskRead>
{
	@Resource
	private TaskReadDao dao;
	
	
	
	public TaskReadService()
	{
	}
	
	@Override
	protected IEntityDao<TaskRead, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 添加查看记录
	 * @param actInstId		流程实例ID
	 * @param taskId		任务ID
	 */
	public void saveReadRecord(Long actInstId,Long taskId){
		SysUser sysUser=ContextUtil.getCurrentUser();
		Long userId=sysUser.getUserId();
		if(dao.isTaskRead(taskId, userId)) return;
		
		TaskRead taskRead=new TaskRead();
		taskRead.setId(UniqueIdUtil.genId());
		taskRead.setActinstid(actInstId);
		taskRead.setTaskid(taskId);
		taskRead.setUserid(userId);
		taskRead.setUsername(sysUser.getFullname());
		taskRead.setCreatetime(new Date());
		dao.add(taskRead);
	}
	
	/**
	 * 判断任务是否已读
	 * @param taskId 任务ID
	 * @param userId 用户ID
	 * @return
	 */
	public boolean isTaskRead(Long taskId,Long userId){
		return dao.isTaskRead(taskId, userId);
	}
	
	public List<TaskRead> getTaskRead(Long actInstId,Long taskId){
		return dao.getTaskRead(actInstId,taskId);
	}

	public void delByActInstId(Long actInstId) {
		dao.delByActInstId(actInstId);
	}
}
