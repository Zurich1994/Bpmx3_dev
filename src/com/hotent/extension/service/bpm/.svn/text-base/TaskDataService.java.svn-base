package com.hotent.extension.service.bpm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Service;


import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.db.JdbcHelper;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.extension.dao.bpm.BpmNodeDataDao;
import com.hotent.extension.model.bpm.BpmNodeData;
import com.hotent.extension.model.bpm.TaskData;
import com.hotent.extension.model.bpm.TaskDataTemplate;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormDialogService;
import com.hotent.platform.service.util.ServiceUtil;

import freemarker.template.TemplateException;

@Service
public class TaskDataService {
	@Resource
	BpmFormDialogService bpmFormDialogService;
	@Resource
	TaskDao taskDao;
	@Resource
	ProcessRunService processRunService;
	@Resource
	BpmNodeDataService bpmNodeDataService;
	@Resource
	BpmNodeDataDao bpmNodeDataDao;

	/**
	 * 获取待办事宜的业务数据列表
	 * @param taskList 任务列表
	 * @param bpmFormDialog 流程业务数据列表设置
	 * @param params 查询参数
	 * @return
	 * @throws Exception
	 */
	public List<TaskData> getTaskDataList(List taskList,BpmFormDialog bpmFormDialog, Map<String, Object> params)throws Exception {
		List<TaskData>  dataList = new ArrayList<TaskData>();
		JdbcHelper jdbcHelper = ServiceUtil.getJdbcHelper(bpmFormDialog
				.getDsalias());
		// 根据defId,nodeId获取该节点的待办事宜业务列表
		for(int i=0;i<taskList.size();i++){
			ProcessTask task=(ProcessTask)taskList.get(i);
			TaskData taskData=new TaskData();
			taskData.setTaskId(task.getId());
			taskData.setTaskName(task.getSubject());
			if(StringUtil.isEmpty(task.getStatus())){
				taskData.setStatus(1);
			}else{
				taskData.setStatus(Integer.parseInt(task.getStatus()));
			}
			String actInstId=task.getProcessInstanceId();
			taskData.setHasRead(task.getHasRead());
			taskData.setType(Integer.parseInt(task.getDescription()));
			//根据设置，查询参数获取业务数据sql语句
			String sql=bpmNodeDataService.getSqlByDialog(bpmFormDialog, params);
			
			List<DialogField> returnField=bpmFormDialog.getReturnList();
			String pkName=returnField.get(0).getFieldName();
			String pk=processRunService.getByActInstanceId(Long.parseLong(actInstId)).getBusinessKey();
			//将待办事项与业务数据对应 获得最终的sql
			String endSql="select bus.* from ( "+sql+" ) bus where bus."+pkName+"="+pk;
						
			List<Map<String, Object>> list = jdbcHelper.queryForList(
					endSql, params);
			if(BeanUtils.isNotEmpty(list)){
				Map<String,Object> data=list.get(0);
				taskData.setData(data);
				dataList.add(taskData);
			}
		}
		return dataList;
	}
	
	public String getNodePath(String typeId){
		return bpmNodeDataDao.getNodePathByTypeId(typeId);
	}
	
}
