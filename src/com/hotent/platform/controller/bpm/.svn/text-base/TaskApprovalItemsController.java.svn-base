package com.hotent.platform.controller.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.TaskApprovalItems;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.TaskApprovalItemsService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.GlobalTypeService;

/**
 * 对象功能:常用语管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-03-16 10:53:20
 */
@Controller
@RequestMapping("/platform/bpm/taskApprovalItems/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class TaskApprovalItemsController extends BaseController
{
	@Resource
	private TaskApprovalItemsService taskApprovalItemsService;
	@Resource 
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private GlobalTypeService globalTypeService;
	


	@RequestMapping("edit")
	@Action(description="编辑常用语管理")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		//获取所有的流程分类，处理流程分类
		String isAdmin = RequestUtil.getString(request, "isAdmin");
		return getAutoView()
				.addObject("isAdmin", isAdmin);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="",
			detail=""
	)
	public void save(HttpServletRequest request, HttpServletResponse response, TaskApprovalItems taskApprovalItems, BindingResult bindResult) throws Exception
	{
		String approvalItem = RequestUtil.getString(request, "approvalItem");
		Short type=RequestUtil.getShort(request, "type", (short)1);
		String typeId=RequestUtil.getString(request, "flowTypeId");
		String defKey=RequestUtil.getString(request, "defKey");
		
		try {
			taskApprovalItemsService.addTaskApproval(approvalItem, type, typeId, defKey, ContextUtil.getCurrentUserId());
			writeResultMessage(response.getWriter(),"保存常用语成功!",ResultMessage.Success);
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"保存常用语失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long currUserId=ContextUtil.getCurrentUserId();
		Long isAdmin=RequestUtil.getLong(request, "isAdmin");
		List<TaskApprovalItems> taskApprovalItemsList=null;
		QueryFilter queryFilter=new QueryFilter(request,"taskApprovalItems");
		
		if (isAdmin==1) {
			taskApprovalItemsList=taskApprovalItemsService.getAll(queryFilter);
		}else {
			queryFilter.addFilter("admin", 1);
			queryFilter.addFilter("userId", currUserId);
			taskApprovalItemsList=taskApprovalItemsService.getAll(queryFilter);
		}
		
		//保存流程分类为map，键为流程分类的Id，值为TypeName
		Map<Long, String> defTypeMap=new HashMap<Long, String>();
		Map<Long, String> defTypeTempMap=new HashMap<Long, String>();
		//保存流程为map，键为流程的defKey，值为subject
		Map<String, String> defMap=new HashMap<String, String>();
		Map<String, String> defTempMap=new HashMap<String, String>();
		
		//获取所有的最新版流程
		QueryFilter queryFilter1=new QueryFilter(request);
		queryFilter1.addFilter("isMain", 1);
		List<BpmDefinition> bpmDefinitionlist= bpmDefinitionService.getAll(queryFilter1);
		for (BpmDefinition bpmDefinition:bpmDefinitionlist) {
			defTempMap.put(bpmDefinition.getDefKey(), bpmDefinition.getSubject());
		}
		//获取所有的流程分类，处理流程分类
		List<GlobalType> globalTypeList= globalTypeService.getByCatKey(GlobalType.CAT_FLOW, false);
		for (GlobalType globalType:globalTypeList) {
			defTypeTempMap.put(globalType.getTypeId(), globalType.getTypeName());
		}
		
		
		for (TaskApprovalItems taskApprovalItems:taskApprovalItemsList) {
			if (taskApprovalItems.getType()==TaskApprovalItems.TYPE_FLOW) {
				defMap.put(taskApprovalItems.getDefKey(), defTempMap.get(taskApprovalItems.getDefKey()));
			}else if (taskApprovalItems.getType()==TaskApprovalItems.TYPE_FLOWTYPE) {
				defTypeMap.put(taskApprovalItems.getTypeId(), defTypeTempMap.get(taskApprovalItems.getTypeId()));
			}
		}
		
		return getAutoView()
				.addObject("taskApprovalItemsList", taskApprovalItemsList)
				.addObject("defMap", defMap)
				.addObject("defTypeMap", defTypeMap)
				.addObject("isAdmin", isAdmin)
				.addObject("currUserId", currUserId)
				.addObject("globalTypeList", globalTypeList);
	}
	
	@RequestMapping("del")
	public void del(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ResultMessage message=null;
		String preUrl= RequestUtil.getPrePage(request);
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "itemId");
			taskApprovalItemsService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
}
