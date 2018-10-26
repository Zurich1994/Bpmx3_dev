/**
 * 对象功能:任务节点催办时间设置 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-17 13:56:53
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.TaskReminder;

@Repository
public class TaskReminderDao extends BaseDao<TaskReminder>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return TaskReminder.class;
	}
	
	/**
	 * 根据流程定义id和节点id获取催办设置信息。
	 * @param actDefId	流程定义ID
	 * @param nodeId	节点ID
	 * @return
	 */
	public List<TaskReminder> getByActDefAndNodeId(String actDefId,String nodeId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("actDefId", actDefId);
		map.put("nodeId", nodeId);
		List<TaskReminder> objs=getBySqlKey("getByActDefAndNodeId", map); //this.getOne("getByActDefAndNodeId", map);
		return objs;
	}
	
	/**
	 * 根据流程定义id和节点id判断是否已定义催办信息。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public Integer isExistByActDefAndNodeId(String actDefId,String nodeId){
		Map map=new HashMap();
		map.put("actDefId", actDefId);
		map.put("nodeId", nodeId);
		Integer obj=(Integer)this.getOne("isExistByActDefAndNodeId", map);
		return obj;
	}

	/**
	 * 根据流程定义id获取定义催办信息。
	 * @param actDefId
	 * @return
	 */
	public List<TaskReminder> getByActDefId(String actDefId) {
		return this.getBySqlKey("getByActDefId", actDefId);
	}
	
	/**
	 * 根据act流程定义Id删除定义催办信息
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}
}