package com.hotent.platform.service.bpm;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.bpm.TaskReminderDao;
import com.hotent.platform.model.bpm.TaskReminder;
import com.hotent.platform.model.system.SysPropertyConstants;
import com.hotent.platform.model.util.WarningSetting;
import com.hotent.platform.service.system.SysPropertyService;

/**
 * 对象功能:任务节点催办时间设置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-17 13:56:53
 */
@Service
public class TaskReminderService extends BaseService<TaskReminder>
{
	@Resource
	private TaskReminderDao dao;
	
	public TaskReminderService()
	{
	}
	
	@Override
	protected IEntityDao<TaskReminder, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据流程定义Id和节点Id获取催办信息。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public List<TaskReminder> getByActDefAndNodeId(String actDefId,String nodeId){
		return dao.getByActDefAndNodeId(actDefId, nodeId);
	}
	
	/**
	 * 根据流程定义Id获取催办信息。
	 * @param actDefId
	 * @return
	 */
	public List<TaskReminder> getByActDefId(String actDefId){
		return dao.getByActDefId(actDefId);
	}
	
	/**
	 * 判断节点是否已经定义催办信息。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public boolean isExistByActDefAndNodeId(String actDefId,String nodeId){
		Integer rtn= dao.isExistByActDefAndNodeId(actDefId, nodeId);
		return rtn>0;
	}
	
	/**
	 * 保存催办信息。
	 * @param taskReminder
	 */
	@Override
	public void add(TaskReminder taskReminder){
		this.dao.add(taskReminder);
	}
	
	
	/**
	 * 保存催办信息。
	 * @param taskReminder
	 */
	@Override
	public void update(TaskReminder taskReminder){
		this.dao.update(taskReminder);

	}
	public List<WarningSetting> getWaringSettingList(){
		String json = SysPropertyService.getByAlias(SysPropertyConstants.TASK_WARNLEVEL);
		if(StringUtil.isEmpty(json))return Collections.emptyList();
		List<WarningSetting> warningSettingList = JSONObject.parseArray(json,com.hotent.platform.model.util.WarningSetting.class);
		return warningSettingList;
	}
	
	public Map<Integer,WarningSetting> getWaringSetMap(){
		Map<Integer,WarningSetting> map = new HashMap<Integer, WarningSetting>();
		map.put(TaskEntity.DEFAULT_PRIORITY, new WarningSetting(TaskEntity.DEFAULT_PRIORITY,"普通","blue"));
		
		List<WarningSetting> warningSettingList  = getWaringSettingList();
		if(BeanUtils.isEmpty(warningSettingList))return map;
		for (WarningSetting set : warningSettingList) {
			map.put(set.getLevel(),set);
		}
		return map;
	}
}
