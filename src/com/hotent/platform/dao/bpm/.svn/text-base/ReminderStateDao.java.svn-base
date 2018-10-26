/**
 * 对象功能:任务催办执行情况 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-17 17:17:37
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.ReminderState;

@Repository
public class ReminderStateDao extends BaseDao<ReminderState>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return ReminderState.class;
	}
	
	/**
	 * 取得任务执行的次数。
	 * @param taskId
	 * @return
	 */
	public Integer getAmountByUserTaskId(long taskId,long userId,int remindType){
		Map params=new HashMap();
		params.put("taskId", taskId);
		params.put("userId", userId);
		params.put("remindType", remindType);
		
		Integer rtn=(Integer)this.getOne("getAmountByUserTaskId", params);
		return rtn;
	}
	
	/**
	 * 根据任务id查询催办执行次数。
	 * 这个方法用在查询到期的催办。
	 * @param taskId
	 * @param remindType
	 * @return
	 */
	public Integer getAmountByTaskId(long taskId,int remindType){
		Map params=new HashMap();
		params.put("taskId", taskId);
		params.put("remindType", remindType);
		Integer rtn=(Integer)this.getOne("getAmountByTaskId", params);
		return rtn;
	}
	
	
	/**
	 * 清除已过期的任务提醒状态数据。
	 */
	public void delExpiredTaskReminderState(){
		this.delBySqlKey("delExpiredTaskReminderState", null);
	}
	
	/**
	 * 根据act流程定义Id删除任务提醒状态数据。
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}
}