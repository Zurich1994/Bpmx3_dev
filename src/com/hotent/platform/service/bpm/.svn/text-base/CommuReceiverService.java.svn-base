package com.hotent.platform.service.bpm;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.CommuReceiverDao;
import com.hotent.platform.model.bpm.CommuReceiver;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysUserService;


/**
 *<pre>
 * 对象功能:沟通接收人 Service类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-04-09 19:44:59
 *</pre>
 */
@Service
public class CommuReceiverService extends BaseService<CommuReceiver>
{
	@Resource
	private CommuReceiverDao dao;
	
	@Resource
	private SysUserService sysUserService;
	
	
	
	public CommuReceiverService()
	{
	}
	
	@Override
	protected IEntityDao<CommuReceiver, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 保存接收人
	 * @param opinionId
	 * @param usrIdTaskIds
	 */
	public void saveReceiver(Long opinionId, Map<Long, Long> usrIdTaskIds,SysUser curUser){
		Iterator<Entry<Long, Long>> it = usrIdTaskIds.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Long, Long> ent=it.next();
			Long receiverId=ent.getKey();
			if(curUser.getUserId().equals(receiverId)) continue;
			Long taskId=ent.getValue();
			SysUser sysUser= (SysUser)sysUserService.getById(receiverId);
			CommuReceiver comm=new CommuReceiver();
			comm.setId(UniqueIdUtil.genId());
			comm.setStatus(CommuReceiver.NOT_READ);
			comm.setOpinionid(opinionId);
			comm.setTaskId(taskId);
			comm.setReceivetime(new Date());
			comm.setCreatetime(new Date());
			comm.setFeedbacktime(new Date());
			comm.setRecevierid(receiverId);
			comm.setReceivername(sysUser.getFullname());
			dao.add(comm);
		}
	}
	
	
	/**
	 * 根据意见ID和接收人Id获取接收人信息。
	 * @param opinionId
	 * @param recevierId
	 * @return
	 */
	public CommuReceiver getByTaskReceiver(Long taskId, Long recevierId){
		return dao.getByTaskReceiver(taskId, recevierId);
	}
	
	/**
	 * 根据意见ID获取接收人。
	 * @param opinionId
	 * @return
	 */
	public List<CommuReceiver> getByOpinionId(Long opinionId){
		return dao.getByOpinionId(opinionId);
	}
	
	
	/**
	 * 设置沟通人员或流转人员的查看状态。
	 * @param taskEntity
	 * @param sysUser
	 */
	public void setCommuReceiverStatus(TaskEntity taskEntity,SysUser sysUser){
		Long userId=sysUser.getUserId();
		String taskId=taskEntity.getId();
		//沟通意见任务、流转任务
		if(!TaskOpinion.STATUS_COMMUNICATION.toString().equals(taskEntity.getDescription()) &&
				!TaskOpinion.STATUS_TRANSTO.toString().equals(taskEntity.getDescription())) return;
		//当前人和任务执行人一致。
		if(!taskEntity.getAssignee().equals(userId.toString())) return;
		CommuReceiver commuReceiver= dao.getByTaskReceiver(new Long(taskId), userId);
		//被通知人员为未读状态，则更新为已读。
		if(commuReceiver.getStatus().shortValue()==CommuReceiver.NOT_READ.shortValue()){
			commuReceiver.setStatus(CommuReceiver.READED);
			commuReceiver.setReceivetime(new Date());
			dao.update(commuReceiver);
		}
	}
		
	/**
	 * 设置沟通人员或流转人员的查看状态为反馈状态。
	 * @param taskEntity
	 * @param sysUser
	 */
	public void setCommuReceiverStatusToFeedBack(TaskEntity taskEntity,SysUser sysUser){
		Long userId=sysUser.getUserId();
		String taskId=taskEntity.getId();
		//沟通意见任务、流转任务
		if(!TaskOpinion.STATUS_COMMUNICATION.toString().equals(taskEntity.getDescription()) &&
				!TaskOpinion.STATUS_TRANSTO.toString().equals(taskEntity.getDescription())) return;
		//当前人和任务执行人一致。
		if(!taskEntity.getAssignee().equals(userId.toString())) return;
		CommuReceiver commuReceiver= dao.getByTaskReceiver(new Long(taskId), userId);
		//被通知人员为未读或已读状态，则更新为已反馈。
		if(commuReceiver.getStatus().shortValue()!=CommuReceiver.FEEDBACK.shortValue()){
			commuReceiver.setStatus(CommuReceiver.FEEDBACK);
			commuReceiver.setReceivetime(new Date());
			dao.update(commuReceiver);
		}
	}
	
	/**
	 * 设置重启人员查看状态。
	 * @param taskEntity
	 * @param sysUser
	 */
	public void setRestartCommuReceiverStatus(TaskEntity taskEntity,SysUser sysUser){
		Long userId=sysUser.getUserId();
		String taskId=taskEntity.getId();
		//沟通意见任务
		if(!TaskOpinion.STATUS_RESTART_TASK.toString().equals(taskEntity.getDescription())) return;
		//当前人和任务执行人一致。
		if(!taskEntity.getAssignee().equals(userId.toString())) return;
		CommuReceiver commuReceiver= dao.getByTaskReceiver(new Long(taskId), userId);
		if(BeanUtils.isEmpty(commuReceiver))
			return;
		//被通知人员为未读状态，则更新为已读。
		if(commuReceiver.getStatus().shortValue()==CommuReceiver.NOT_READ.shortValue()){
			commuReceiver.setStatus(CommuReceiver.READED);
			commuReceiver.setReceivetime(new Date());
			dao.update(commuReceiver);
		}
	}

	public void delByTaskId(Long taskId) {
		dao.delByTaskId(taskId);
	}
	
	/**
	 * 修改接收者状态
	 * @param taskId
	 * @param status
	 * @param recevierId
	 */
	public void setCommuReceiverStatus(Long taskId, Integer status, Long recevierId){
		CommuReceiver commuReceiver = this.getByTaskReceiver(taskId, recevierId);
		if(BeanUtils.isEmpty(commuReceiver))
			return;
		commuReceiver.setStatus(status);
		commuReceiver.setFeedbacktime(new Date());
		dao.update(commuReceiver);
	}
}
