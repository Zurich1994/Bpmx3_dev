package com.hotent.extension.controller.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.extension.model.bpm.ProcessType;
import com.hotent.extension.service.bpm.ProcessTypeService;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskAmount;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.system.GlobalTypeService;

@Controller
@RequestMapping("/extension/bpm/processType/")
public class ProcessTypeController extends BaseController{
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private ProcessTypeService processTypeService;
	@Resource
	private BpmService bpmService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@RequestMapping("forPending")
	@ResponseBody
	public Set<ProcessType> forPending(HttpServletRequest request) {
		String catKey = "FLOW_TYPE";
		Set<ProcessType> ProcessTypeSet = new LinkedHashSet<ProcessType>();
		//为了利用globalTypeService，将获取的GlobalType集合转换为ProcessType集合
		List<GlobalType> GlobalTypeList = globalTypeService.getByCatKey(catKey,true);		
		List<ProcessType> processTypeList = new ArrayList<ProcessType>();
		for(GlobalType globalType:GlobalTypeList){
			ProcessType processType = new ProcessType();
			processType.setTypeId(Long.toString(globalType.getTypeId()));
			processType.setTypeName(globalType.getTypeName());
			processType.setParentId(Long.toString(globalType.getParentId()));
			processTypeList.add(processType);
		}
		//获取我的待办流程，封装成processType，获取每个流程的未读流程数、流程总数
		List<ProcessType> processTypeList2 = processTypeService.getAllMyProcessType(ContextUtil.getCurrentUserId());
		List<TaskAmount> countlist2 = bpmService.getMyProcessCount(ContextUtil.getCurrentUserId());
				
		Map<String, Integer> typeMap2 = new HashMap<String, Integer>();
		Map<String, Integer> typeNotReadMap2 = new HashMap<String, Integer>();
		
		for(TaskAmount taskAmount:countlist2){
			String typeId=String.valueOf(taskAmount.getTypeId());
			if(BeanUtils.isEmpty( typeId) ){
				continue;
			}	
			typeMap2.put(typeId, taskAmount.getTotal());
			typeNotReadMap2.put(typeId, taskAmount.getNotRead());
		}
		for(ProcessType processType:processTypeList2){
			String typeId = processType.getTypeId();
			Integer count = typeMap2.get(typeId);
			Integer readCount = typeNotReadMap2.get(typeId);
			if (count == null)
				continue;
			readCount = (readCount == null ? 0 : readCount);

	
			String typeName = processType.getTypeName();
			processType.setTypeName(typeName + "(" + readCount + "/" + count
					+ ")");
			ProcessTypeSet.add(processType);
		}
		
		//获取我的待办流程的任务，封装成processType，获取每个流程的未读任务数、任务总数
		List<ProcessType> processTypeList3 = processTypeService.getAllMyTaskType(ContextUtil.getCurrentUserId());
		List<TaskAmount> countlist3 = bpmService.getMyTaskKeyCount(ContextUtil.getCurrentUserId());
		
		Map<String, Integer> typeMap3 = new HashMap<String, Integer>();
		Map<String, Integer> typeNotReadMap3 = new HashMap<String, Integer>();
		
		for(TaskAmount taskAmount:countlist3){
			String typeId=String.valueOf(taskAmount.getTypeId());
			if(BeanUtils.isEmpty( typeId) ){
				continue;
			}	
			typeMap3.put(typeId, taskAmount.getTotal());
			typeNotReadMap3.put(typeId, taskAmount.getNotRead());
		}
		for(ProcessType processType:processTypeList3){
			String typeId = processType.getTypeId();
			Integer count = typeMap3.get(typeId);
			Integer readCount = typeNotReadMap3.get(typeId);
//			if (count == null)
//				continue;
			readCount = (readCount == null ? 0 : readCount);

	
			String typeName = processType.getTypeName();
			processType.setTypeName(typeName + "(" + readCount + "/" + count
					+ ")");
			ProcessTypeSet.add(processType);
		}
		
//		processTypeList.addAll(processTypeList2);
//		processTypeList.addAll(processTypeList3);
//		ProcessTypeSet.addAll(processTypeList);
		
		//计算待办未读任务数、任务总数
		List<TaskAmount> countlist = bpmService.getMyTasksCount(ContextUtil.getCurrentUserId());

		// 待办的分类
		Map<String, Integer> typeMap = new HashMap<String, Integer>();
		// 待办未读的分类
		Map<String, Integer> typeNotReadMap = new HashMap<String, Integer>();
		
		for (TaskAmount taskAmount : countlist) {
			
			String typeId=String.valueOf(taskAmount.getTypeId());
			if(BeanUtils.isEmpty( typeId) ){
				continue;
			}	
			typeMap.put(typeId, taskAmount.getTotal());
			typeNotReadMap.put(typeId, taskAmount.getNotRead());
			
		}
		
		//分别存放任务总数、已读任务数，用来拼接流程分类（未读/总数）
		int totalTaskNum = 0;
		int readTaskNum = 0;
		//拼接typename=typename(未读数/总数)
		for (ProcessType processType : processTypeList) {
			String typeId = processType.getTypeId();
			Integer count = typeMap.get(typeId);
			Integer readCount = typeNotReadMap.get(typeId);
			if (count == null)
				continue;
			readCount = (readCount == null ? 0 : readCount);
			totalTaskNum+= count;
			readTaskNum+= readCount;
	
			String typeName = processType.getTypeName();
			processType.setTypeName(typeName + "(" + readCount + "/" + count
					+ ")");
			ProcessTypeSet.add(processType);
		}		
		ProcessType sysProcessType = processTypeList.get(0);
		sysProcessType.setTypeName(sysProcessType.getTypeName()+"("+readTaskNum+"/"+totalTaskNum+")");
		ProcessTypeSet.add(sysProcessType);
		
		System.out.println(ProcessTypeSet);//add by lzc
		
		return ProcessTypeSet;
	}
	
	//点击分类树的节点，将与该节点相关的任务显示出来
	@RequestMapping("pendingMattersListWithBus")
	public ModelAndView pendingMattersListWithBus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("进入pending控制器");
		QueryFilter filter = new QueryFilter(request, "taskItem");
		String typeId = RequestUtil.getString(request, "typeId");
		if(StringUtils.isNotEmpty(typeId)){
		if(!typeId.contains(":")){
			//分类节点
			filter.getFilters().put("typekey",typeId);
		}
		else{
			if(typeId.contains("-")){
				//任务节点
				String[] a = typeId.split("-");
				filter.getFilters().put("processkey",a[0]);
				filter.getFilters().put("ptaskkey",a[1]);
			}
			else{
				//流程节点
				String[] a = typeId.split(":", 2);
				filter.getFilters().put("typeId",a[0]);
				filter.getFilters().put("processkey",a[1]);
			}
		}
		}
		List<TaskEntity> list = bpmService.getMyTasks(filter);
		return getAutoView().addObject("taskList", list);
		
	}
	
	//将已办任务封装成ProcessType集合，将ProcessType集合交给Ztree
		@RequestMapping("alreadyMatters")
		@ResponseBody
		public Set<ProcessType> alreadyMatters(HttpServletRequest request) {
			String catKey = "FLOW_TYPE";
			Set<ProcessType> ProcessTypeSet = new LinkedHashSet<ProcessType>();
			//获取我的已办流程，封装成processType，获取每个流程的总数
			List<ProcessType> processTypeList2 = processTypeService.getAlreadyProcess(ContextUtil.getCurrentUserId());	
			Map<String, Integer> typeMap = new HashMap<String, Integer>();
			//拼接typename形如：typename(流程总数)，
			for(ProcessType processType:processTypeList2){
				Integer count = processType.getCount();
				String parentId = processType.getParentId();
				this.mapCount(typeMap, parentId, count);
				String typeName = processType.getTypeName();
				processType.setTypeName(typeName + "(" + count
						+ ")");
				ProcessTypeSet.add(processType);
			}
			//为了利用globalTypeService，将获取的GlobalType集合转换为ProcessType集合
			List<GlobalType> GlobalTypeList = globalTypeService.getByCatKey(catKey,true);
			List<ProcessType> processTypeList = new ArrayList<ProcessType>();
			for(GlobalType globalType:GlobalTypeList){
				ProcessType processType = new ProcessType();
				processType.setTypeName(globalType.getTypeName());
				processType.setTypeId(Long.toString(globalType.getTypeId()));
				processType.setParentId(Long.toString(globalType.getParentId()));
				processTypeList.add(processType);
				Integer count = typeMap.get(String.valueOf(globalType.getTypeId()));
				if(count==null)
					continue;
				processType.setTypeName(processType.getTypeName()+"("+count+")");
				ProcessTypeSet.add(processType);
			}		
			//获取我的已办流程的任务，封装成processType，获取每个任务的总数
			List<ProcessType> processTypeList3 = processTypeService.getAlreadyTask(ContextUtil.getCurrentUserId());
			for(ProcessType processType:processTypeList3){
				int count = processType.getCount();	
				String typeName = processType.getTypeName();
				processType.setTypeName(typeName + "("+ count
						+ ")");
				ProcessTypeSet.add(processType);
			}		
			ProcessType sysProcessType = processTypeList.get(0);
			Integer totalcount = 0;
			for(String key:typeMap.keySet())
			totalcount+=typeMap.get(key);
			sysProcessType.setTypeName(sysProcessType.getTypeName()+"("+totalcount+")");
			ProcessTypeSet.add(sysProcessType);
			return ProcessTypeSet;
		}
		//将办结任务封装成ProcessType集合，将ProcessType集合交给Ztree进行处理
		@RequestMapping("completedMatters")
		@ResponseBody
		public Set<ProcessType> completedMatters(HttpServletRequest request){
			String catKey = "FLOW_TYPE";
			Set<ProcessType> ProcessTypeSet = new LinkedHashSet<ProcessType>();
			//获取我的办结流程，封装成processType，获取每个流程的总数
			List<ProcessType> processTypeList2 = processTypeService.getCompletedProcess(ContextUtil.getCurrentUserId());	
			Map<String, Integer> typeMap = new HashMap<String, Integer>();
			//拼接typename形如：typename(流程总数)，
			for(ProcessType processType:processTypeList2){
				Integer count = processType.getCount();
				String parentId = processType.getParentId();
				this.mapCount(typeMap, parentId, count);
				String typeName = processType.getTypeName();
				processType.setTypeName(typeName + "(" + count
						+ ")");
				ProcessTypeSet.add(processType);
			}
			//为了利用globalTypeService，将获取的GlobalType集合转换为ProcessType集合
			List<GlobalType> GlobalTypeList = globalTypeService.getByCatKey(catKey,true);
			List<ProcessType> processTypeList = new ArrayList<ProcessType>();
			for(GlobalType globalType:GlobalTypeList){
				ProcessType processType = new ProcessType();
				processType.setTypeName(globalType.getTypeName());
				processType.setTypeId(Long.toString(globalType.getTypeId()));
				processType.setParentId(Long.toString(globalType.getParentId()));
				processTypeList.add(processType);
				Integer count = typeMap.get(String.valueOf(globalType.getTypeId()));
				if(count==null)
					continue;
				processType.setTypeName(processType.getTypeName()+"("+count+")");
				ProcessTypeSet.add(processType);
			}		
			//获取我的办结流程的任务，封装成processType，获取每个任务的总数
			List<ProcessType> processTypeList3 = processTypeService.getCompletedTask(ContextUtil.getCurrentUserId());
			System.out.println("任务的总数是："+processTypeList3.size());
			for(ProcessType processType:processTypeList3){
				int count = processType.getCount();	
				String typeName = processType.getTypeName();
				processType.setTypeName(typeName + "("+ count
						+ ")");
				ProcessTypeSet.add(processType);
			}		
			ProcessType sysProcessType = processTypeList.get(0);
			Integer totalcount = 0;
			for(String key:typeMap.keySet())
			totalcount+=typeMap.get(key);
			sysProcessType.setTypeName(sysProcessType.getTypeName()+"("+totalcount+")");
			ProcessTypeSet.add(sysProcessType);
			return ProcessTypeSet;
			
		}
		//点击分类树的节点，将与该节点相关的已办任务显示出来
	/*	@RequestMapping("alreadyMattersListWithBus")
		public ModelAndView alreadyMattersListWithBus(HttpServletRequest request, HttpServletResponse response) throws Exception {
			QueryFilter filter = new QueryFilter(request, "processRunItem");
			String typeId = RequestUtil.getString(request, "typeId");
			if(StringUtils.isNotEmpty(typeId)){
			if(!typeId.contains(":")){
				//分类节点
				filter.getFilters().put("typekey",typeId);
			}
			else{
				if(typeId.contains("-")){
					//任务节点
					String[] a = typeId.split("-");
					filter.getFilters().put("processkey",a[0]);
					filter.getFilters().put("ptaskkey",a[1]);
				}
				else{
					//流程节点
					String[] a = typeId.split(":", 2);
					filter.getFilters().put("typekey",a[0]);
					filter.getFilters().put("processkey",a[1]);
				}
			}
			} 
			filter.addFilter("assignee", ContextUtil.getCurrentUserId().toString());
			List<ProcessRun> list = this.processRunService.getAlreadyMattersListWithBus(filter); 
			     for (ProcessRun processRun : list) {
			      if (processRun.getStatus().shortValue() != ProcessRun.STATUS_FINISH.shortValue())
			       {
			         TaskOpinion taskOpinion = this.taskOpinionService.getLatestUserOpinion(processRun.getActInstId(), ContextUtil.getCurrentUserId());
			        if (BeanUtils.isNotEmpty(taskOpinion)) {
			          processRun.setRecover(ProcessRun.STATUS_RECOVER);
			       }
			       }
			     }
			return getAutoView().addObject("processRunList", list);		
		}*/
		
		//将办结任务封装成ProcessType集合，将ProcessType集合交给Ztree
		@RequestMapping("completedMatters1")
		@ResponseBody
		public Set<ProcessType> completedMatters1(HttpServletRequest request) {
			String catKey = "FLOW_TYPE";
			Set<ProcessType> ProcessTypeSet = new LinkedHashSet<ProcessType>();
			//获取我的已办流程，封装成processType，获取每个流程的总数
			List<ProcessType> processTypeList2 = processTypeService.getCompletedProcess(ContextUtil.getCurrentUserId());	
			Map<String, Integer> typeMap = new HashMap<String, Integer>();
			//拼接typename形如：typename(流程总数)，
			for(ProcessType processType:processTypeList2){
				Integer count = processType.getCount();
				String parentId = processType.getParentId();
				this.mapCount(typeMap, parentId, count);
				String typeName = processType.getTypeName();
				processType.setTypeName(typeName + "(" + count
						+ ")");
				ProcessTypeSet.add(processType);
			}
			//为了利用globalTypeService，将获取的GlobalType集合转换为ProcessType集合
			List<GlobalType> GlobalTypeList = globalTypeService.getByCatKey(catKey,true);
			List<ProcessType> processTypeList = new ArrayList<ProcessType>();
			for(GlobalType globalType:GlobalTypeList){
				ProcessType processType = new ProcessType();
				processType.setTypeName(globalType.getTypeName());
				processType.setTypeId(Long.toString(globalType.getTypeId()));
				processType.setParentId(Long.toString(globalType.getParentId()));
				processTypeList.add(processType);
				Integer count = typeMap.get(String.valueOf(globalType.getTypeId()));
				if(count==null)
					continue;
				processType.setTypeName(processType.getTypeName()+"("+count+")");
				ProcessTypeSet.add(processType);
			}		
			//获取我的已办流程的任务，封装成processType，获取每个任务的总数,如果需要扩展到任务节点，去掉下面注释
	/*		List<ProcessType> processTypeList3 = processTypeService.getCompletedTask(ContextUtil.getCurrentUserId());
			for(ProcessType processType:processTypeList3){
				int count = processType.getCount();	
				String typeName = processType.getTypeName();
				processType.setTypeName(typeName + "("+ count
						+ ")");
				ProcessTypeSet.add(processType);
			}*/		
			ProcessType sysProcessType = processTypeList.get(0);
			Integer totalcount = 0;
			for(String key:typeMap.keySet())
			totalcount+=typeMap.get(key);
			sysProcessType.setTypeName(sysProcessType.getTypeName()+"("+totalcount+")");
			ProcessTypeSet.add(sysProcessType);
			return ProcessTypeSet;
		}
		
		//点击分类树的节点，将与该节点相关的办结任务显示出来
			@RequestMapping("completedMattersListWithBus")
			public ModelAndView completedMattersListWithBus(HttpServletRequest request, HttpServletResponse response) throws Exception {
				QueryFilter filter = new QueryFilter(request, "processRunItem");
				String typeId = RequestUtil.getString(request, "typeId");
				if(StringUtils.isNotEmpty(typeId)){
				if(!typeId.contains(":")){
					//分类节点
					filter.getFilters().put("typekey",typeId);
				}
				else{
	/*				if(typeId.contains("-")){
						//任务节点
						String[] a = typeId.split("-");
						filter.getFilters().put("processkey",a[0]);
						filter.getFilters().put("ptaskkey",a[1]);
					}
					else{*/
						//流程节点
						String[] a = typeId.split(":", 2);
						filter.getFilters().put("typekey",a[0]);
						filter.getFilters().put("processkey",a[1]);
		/*			}*/
				}
				} 
				filter.addFilter("assignee", ContextUtil.getCurrentUserId().toString());
				List<ProcessRun> list = this.processRunService.getCompletedMattersList(filter);
				for (ProcessRun processRun : list) {
				BpmDefinition bpmDefinition = this.bpmDefinitionService.getByActDefId(processRun.getActDefId());
				if ((BeanUtils.isNotEmpty(bpmDefinition)) && (bpmDefinition.getIsPrintForm().shortValue() == 1)) {
				processRun.setIsPrintForm(bpmDefinition.getIsPrintForm());
				}
				}
				ModelAndView mv = getAutoView().addObject("processRunList", list);
				return mv;	
			}
		
		private void mapCount(Map<String, Integer> map, String key, Integer num) {
			Integer count = map.get(key);
			if (count != null)
				map.put(key, count + num);
			else
				map.put(key, num);
		}		
}
