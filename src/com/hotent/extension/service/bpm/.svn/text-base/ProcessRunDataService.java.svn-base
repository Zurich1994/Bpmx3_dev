package com.hotent.extension.service.bpm;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.db.JdbcHelper;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.extension.model.bpm.ProcessRunData;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormDialogService;
import com.hotent.platform.service.util.ServiceUtil;

@Service
public class ProcessRunDataService {
	@Resource
	BpmFormDialogService bpmFormDialogService;
	@Resource
	ProcessRunService processRunService;
	@Resource
	BpmNodeDataService bpmNodeDataService;
	/**
	 * 获取流程实例及其业务数据列表
	 * @param processRunList 流程实例列表
	 * @param bpmFormDialog 流程业务表单设置
	 * @param params 查询参数
	 * @return
	 * @throws Exception
	 */
	public List<ProcessRunData> getProcessDataList(List<ProcessRun> processRunList,
			BpmFormDialog bpmFormDialog,Map<String, Object> params)throws Exception{
			List<ProcessRunData> processRunDataList=new ArrayList<ProcessRunData>();
			JdbcHelper jdbcHelper=ServiceUtil.getJdbcHelper(bpmFormDialog.getDsalias());
			//根据defID,nodeID获取该节点的待办事宜业务列表
			for(int i=0;i<processRunList.size();i++){
				ProcessRun processRun=processRunList.get(i);
				ProcessRunData processRunData=new ProcessRunData();
				processRunData.setActDefId(processRun.getActDefId());
				processRunData.setActInstId(processRun.getActInstId());
				processRunData.setRunId(processRun.getRunId());
				processRunData.setSubject(processRun.getSubject());
				if(StringUtil.isEmpty(String.valueOf(processRun.getStatus()))){
					processRunData.setStatus((short)1);
				}else{
					processRunData.setStatus(processRun.getStatus());
				}
				String actInstId=processRun.getActInstId();
				//根据设置，查询参数获取业务数据sql语句
				String sql=bpmNodeDataService.getSqlByDialog(bpmFormDialog, params);
				List<DialogField>returnField = bpmFormDialog.getReturnList();
				String pkName = returnField.get(0).getFieldName();
				String pk=processRunService.getByActInstanceId(Long.parseLong(actInstId)).getBusinessKey();
				//将已办事项与业务数据对应获得最终的sql
				String endSql="select bus.* from ("+sql+") bus where bus."+pkName+"="+pk;
				List<Map<String,Object>> list=jdbcHelper.queryForList(endSql, params);
				if(BeanUtils.isNotEmpty(list)){
					Map<String,Object> data=list.get(0);
					processRunData.setData(data);
					processRunDataList.add(processRunData);
				}
			}
		
		return processRunDataList;
	}
	
	/**
	 * 获取流程实例及其业务数据列表
	 * @param processRunList 流程实例列表
	 * @param bpmFormDialog 流程业务表单设置
	 * @param params 查询参数
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProcessRunData> getProcessAlreadyDataList(List<ProcessRun> processRunList,
			BpmFormDialog bpmFormDialog,Map<String, Object> params)throws Exception{
			List<ProcessRunData> processRunDataList=new ArrayList<ProcessRunData>();
			JdbcHelper jdbcHelper=ServiceUtil.getJdbcHelper(bpmFormDialog.getDsalias());
			//根据defID,nodeID获取该节点的待办事宜业务列表
			for(int i=0;i<processRunList.size();i++){
				ProcessRun processRun=processRunList.get(i);
				ProcessRunData processRunData=new ProcessRunData();
				processRunData.setActDefId(processRun.getActDefId());
				processRunData.setActInstId(processRun.getActInstId());
				processRunData.setRunId(processRun.getRunId());
				processRunData.setSubject(processRun.getSubject());
				if(StringUtil.isEmpty(String.valueOf(processRun.getStatus()))){
					processRunData.setStatus((short)2);
				}else{
					processRunData.setStatus(processRun.getStatus());
				}
				String actInstId=processRun.getActInstId();
				//根据设置，查询参数获取业务数据sql语句
				String sql=bpmNodeDataService.getSqlByDialog(bpmFormDialog, params);
				List<DialogField>returnField = bpmFormDialog.getReturnList();
				String pkName = returnField.get(0).getFieldName();
				String pk=processRunService.getByActInstanceId(Long.parseLong(actInstId)).getBusinessKey();
				//将已办事项与业务数据对应获得最终的sql
				String endSql="select bus.* from ("+sql+") bus where bus."+pkName+"="+pk;
				
				System.out.println("sql:"+endSql);
				
				List<Map<String,Object>> list=jdbcHelper.queryForList(endSql, params);
				if(BeanUtils.isNotEmpty(list)){
					Map<String,Object> data=list.get(0);
					processRunData.setData(data);
					processRunDataList.add(processRunData);
				}
			}
		return processRunDataList;
	}
}
