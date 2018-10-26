/**
 * 对象功能:任务分发 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-27 09:11:41
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.TaskFork;

@Repository
public class TaskForkDao extends BaseDao<TaskFork>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return TaskFork.class;
	}
	
	/**
	 * 返回当前分发序号的最大数
	 * @param actInstId
	 * @return
	 */
	public Integer getMaxSn(String actInstId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		Integer sn=(Integer)getOne("getMaxSn", params);
		return sn;
	}
	
	/**
	 * 返回某个流程实例中的汇总节点的配置信息
	 * @param actInstId
	 * @param joinTaskKey
	 * @return
	 */
	public  TaskFork getByInstIdJoinTaskKey(String actInstId,String joinTaskKey){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("joinTaskKey", joinTaskKey);
		TaskFork taskFork=getUnique("getByInstIdJoinTaskKey",params);
		return taskFork;
	}
	
	/**
	 * 返回带有某个流程实例汇总的令牌的分发实体
	 * @param actInstId
	 * @param joinTaskKey
	 * @param forkToken
	 * @return
	 */
	public TaskFork getByInstIdJoinTaskKeyForkToken(String actInstId,String joinTaskKey,String forkToken){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("joinTaskKey", joinTaskKey);
		params.put("forkToken", StringUtil.isNotEmpty(forkToken)?("%"+forkToken+",%"):forkToken);
		return getUnique("getByInstIdJoinTaskKeyForkToken",params);
	}
	/**
	 * 根据流程实例ID删除流程分发实体
	 * @param actInstId
	 */
	public void delByActInstId(String actInstId){
		delBySqlKey("delByActInstId", actInstId);
	}
}