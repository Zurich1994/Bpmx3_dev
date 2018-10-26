package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefVar;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.TaskReminder;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.util.WarningSetting;
import com.hotent.platform.service.bpm.BpmDefVarService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.TaskReminderService;
import com.hotent.platform.service.form.BpmFormFieldService;

/**
 * 对象功能:任务节点催办时间设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-17 13:56:53
 */
@Controller
@RequestMapping("/platform/bpm/taskReminder/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class TaskReminderController extends BaseController
{
	@Resource
	private TaskReminderService taskReminderService;
	
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	@Resource
	private BpmDefVarService bpmDefVarService;
	
	/**
	 * 取得任务节点催办时间设置分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看任务节点催办时间设置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<TaskReminder> list=taskReminderService.getAll(new QueryFilter(request,"taskReminderItem"));
		ModelAndView mv=this.getAutoView().addObject("taskReminderList",list);
		
		return mv;
	}
	
	/**
	 * 删除任务节点催办时间设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除任务节点催办时间设置",
			execOrder=ActionExecOrder.BEFORE,
			detail="<#list StringUtils.split(taskDueId,\",\") as item>" +
						"<#assign entity = taskReminderService.getById(Long.valueOf(item))/>" +
						"<#if item_index==0>" +
							"删除流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.actDefId)}】的节点" +
						    "【${SysAuditLinkService.getNodeName(entity.actDefId,entity.nodeId)}】" +
							"的任务节点催办时间设置：" +
						"</#if>"+
						"【 ${entity.name}】 " +
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "taskDueId");
			taskReminderService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除任务节点催办时间设置成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	
	@RequestMapping("edit")
	@Action(description="编辑任务节点催办时间设置")
	public ModelAndView eidt(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request, "id");
		String actDefId=RequestUtil.getString(request,"actDefId");
		String nodeId=RequestUtil.getString(request, "nodeId");
		String parentActDefId=RequestUtil.getString(request, "parentActDefId", "");
		String returnUrl=RequestUtil.getPrePage(request);
		
		List<TaskReminder> taskReminders= taskReminderService.getByActDefAndNodeId(actDefId, nodeId);
		BpmDefinition bpmDefinition =bpmDefinitionService.getByActDefId(actDefId);
		Long defId=bpmDefinition.getDefId();
		List<WarningSetting> warningSettingList =  taskReminderService.getWaringSettingList();
		
		Map<String,FlowNode> nodeMaps = NodeCache.getByActDefId(actDefId);
		List<FlowNode> nodes = new ArrayList<FlowNode>();
		Iterator<Entry<String, FlowNode>> it = nodeMaps.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, FlowNode> entry = it.next();
			FlowNode node = entry.getValue();
			if("userTask".equals(node.getNodeType())||"startEvent".equals(node.getNodeType())){
				//排序中将当前节点排到第一个，这样页面默认选中当前节点为相对节点
				if(nodeId.equals(node.getNodeId()))
					nodes.add(0, node);
				else
					nodes.add(node);
			}
			else
				continue;
		}
		
		BpmFormFieldService bpmFormFieldService=(BpmFormFieldService)AppUtil.getBean(BpmFormFieldService.class);
		List<BpmFormField> flowVars = null;
		if(StringUtil.isEmpty(parentActDefId)){
			flowVars = bpmFormFieldService.getFlowVarByFlowDefId(defId);
		}else{
			flowVars = bpmFormFieldService.getFlowVarByFlowDefId(defId, parentActDefId);
		}
		List<BpmDefVar> bpmdefVars= bpmDefVarService.getVarsByFlowDefId(defId);
		
		int reminderStartDay=0;
		int reminderStartHour=0;
		int reminderStartMinute=0;
		int reminderEndDay=0;
		int reminderEndHour=0;
		int reminderEndMinute=0;
		int completeTimeDay=0;
		int completeTimeHour=0;
		int completeTimeMinute=0;
		
		TaskReminder taskReminder = taskReminderService.getById(id);
		if (id == 0 || taskReminder==null) {
			taskReminder = new TaskReminder();
	    }else{
		   int reminderStart=taskReminder.getReminderStart();
		   reminderStartDay=reminderStart/(60*24);
		   reminderStartHour=(reminderStart-reminderStartDay*(60*24))/60;
		   reminderStartMinute=reminderStart-reminderStartDay*(60*24)-reminderStartHour*60;
			
		   int reminderEnd=taskReminder.getReminderEnd();
		   reminderEndDay=reminderEnd/(60*24);
		   reminderEndHour=(reminderEnd-reminderEndDay*(60*24))/60;
		   reminderEndMinute=reminderEnd-reminderEndDay*(60*24)-reminderEndHour*60;
			
		   int complateTime=taskReminder.getCompleteTime();
		   completeTimeDay=complateTime/(60*24);
		   completeTimeHour=(complateTime-completeTimeDay*(60*24))/60;
	       completeTimeMinute=complateTime-completeTimeDay*(60*24)-completeTimeHour*60;
		}
		
		return getAutoView()
				.addObject("taskReminder", taskReminder)
				.addObject("taskReminders",taskReminders)
				.addObject("warningSettingList",warningSettingList)
				.addObject("returnUrl", returnUrl)
				.addObject("defId",defId )
				.addObject("actDefId", actDefId)
				.addObject("nodeId", nodeId)
				.addObject("flowVars",flowVars)
				.addObject("defVars",bpmdefVars)
				.addObject("reminderStartDay", reminderStartDay)
				.addObject("reminderStartHour", reminderStartHour)
				.addObject("reminderStartMinute", reminderStartMinute)
				.addObject("reminderEndDay",reminderEndDay )
				.addObject("reminderEndHour",reminderEndHour )
				.addObject("reminderEndMinute",reminderEndMinute )
				.addObject("completeTimeDay",completeTimeDay )
				.addObject("completeTimeHour",completeTimeHour)
				.addObject("completeTimeMinute",completeTimeMinute )
				.addObject("nodes",nodes);
	}
	
	@RequestMapping("getFlowVars")
	@ResponseBody
	@Action(description="编辑任务节点催办时间设置")
	public Map<String,Object> getFlowVars(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long defId=RequestUtil.getLong(request, "defId");
		Map<String,Object> map=new HashMap<String, Object>();
		
		BpmFormFieldService bpmFormFieldService=(BpmFormFieldService)AppUtil.getBean(BpmFormFieldService.class);
		List<BpmFormField> flowVars= bpmFormFieldService.getFlowVarByFlowDefId(defId);
		map.put("flowVars", flowVars);
		return map;
		
	}
}
