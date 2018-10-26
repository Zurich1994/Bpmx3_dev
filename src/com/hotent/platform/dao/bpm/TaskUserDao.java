package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.TaskUser;
@Repository
public class TaskUserDao extends BaseDao<TaskUser>
{
	@Override
	public Class getEntityClass(){
		return TaskUser.class;
	}
	
	/**
	 * 取得某个任务下的所有候选用户
	 * @param taskId
	 * @return
	 */
	public List<TaskUser> getByTaskId(String taskId){
		
		Map<String, Object> params=new HashMap<String,Object>();
		params.put("taskId",taskId);
		return getBySqlKey("getByTaskId",params);
	}
	
	
}
