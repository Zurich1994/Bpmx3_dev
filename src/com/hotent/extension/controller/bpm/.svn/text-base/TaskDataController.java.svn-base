package com.hotent.extension.controller.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.hotent.core.page.PageBean;
import com.hotent.core.page.PageUtils;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.extension.model.bpm.TaskData;
import com.hotent.extension.service.bpm.ProcessRunDataService;
import com.hotent.extension.service.bpm.TaskDataService;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.form.BpmFormDialogService;

@Controller
@RequestMapping("/extension/bpm/taskData/")
public class TaskDataController extends BaseController{
	@Resource
	TaskDataService taskDataService;
	@Resource
	BpmFormDialogService bpmFormDialogService;
	@Resource
	ProcessRunDataService processRunDataService;
	@Resource
	TaskDao taskDao;
	@Resource
	BpmService bpmService;
	@Resource
	BpmDefinitionService bpmDefinitionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("getMyTaskView")
	public ModelAndView getTaskView(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		QueryFilter filter = new QueryFilter(request, "taskItem");
		Map<String,Object> typeParams=getTypeParams(request);
		Object obj = typeParams.get("processkey");
		ModelAndView mv;
		
		if(obj!=null){
		
		filter.getFilters().putAll(typeParams);
		//String actDefId=typeParams.get("processkey").toString();
		String actDefId=obj.toString();
		System.out.println("流程定义的ID是:"+actDefId);
		String nodeId="";
		if(BeanUtils.isNotEmpty(typeParams.get("ptaskkey"))){
			nodeId=typeParams.get("ptaskkey").toString();
		}
		
		System.out.println("nodeId="+nodeId);
		
		//待办事宜列表
		List<?> list = bpmService.getMyTasks(filter);
		Map paramsMap = RequestUtil.getQueryMap(request);
		String nextUrl = RequestUtil.getUrl(request);
		//根据流程信息获取业务表单设置
		BpmFormDialog byDefIdAndNodeId = bpmFormDialogService.getByDefIdAndNodeId(actDefId, nodeId);
		String alias=byDefIdAndNodeId.getAlias();
		
		BpmFormDialog bpmFormDialog = bpmFormDialogService.getData(alias, paramsMap);
				
		mv = this.getAutoView();
		mv.addObject("bpmFormDialog", bpmFormDialog).addObject("typeId", RequestUtil.getString(request, "typeId"));

		String sortField=RequestUtil.getString(request, "sortField");
		String orderSeq=RequestUtil.getString(request, "orderSeq", "DES");
		String newSortField=RequestUtil.getString(request, "newSortField");
		if(StringUtil.isNotEmpty(sortField)){
			paramsMap.put("sortField", sortField);
			paramsMap.put("orderSeq", orderSeq);
		}
		if(StringUtil.isEmpty(sortField)){
			DialogField dialogField;
			if(BeanUtils.isNotEmpty(bpmFormDialog.getSortList())){
				dialogField = bpmFormDialog.getSortList().get(0);
				sortField = dialogField.getFieldName();
				orderSeq = dialogField.getComment();
			}else{
				dialogField = bpmFormDialog.getDisplayList().get(0);
				sortField = dialogField.getFieldName();
			}
		}
		
		if(newSortField.equals(sortField)){
			if(orderSeq.equals("ASC")){
				orderSeq="DESC";
			}else{
				orderSeq="ASC";
			}
		}
		if(!StringUtil.isEmpty(newSortField)){
			sortField=newSortField;
		}
		List<TaskData> taskDataList=taskDataService.getTaskDataList(list, bpmFormDialog,paramsMap);
		Map<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("sortField", StringUtil.isEmpty(newSortField)?sortField:newSortField);
		parameters.put("newSortField",null);
		parameters.put("orderSeq", orderSeq);
		nextUrl = addParametersToUrl(nextUrl, parameters);
		
		mv.addObject("sortField",sortField);
		mv.addObject("orderSeq",orderSeq);
		mv.addObject("baseHref", nextUrl);
		// 需要分页
		if (bpmFormDialog.getNeedpage() == 1) {
			PageBean pageBean = bpmFormDialog.getPageBean();
			//设置总数
			pageBean.setTotalCount(taskDataList.size());
			String pageHtml = PageUtils.getPageHtml(pageBean, nextUrl, "", true, true);
			mv.addObject("pageHtml", pageHtml);
		}
		
		mv.addObject("paramsMap", paramsMap) ;		
		return mv.addObject("taskDataList", taskDataList);
		
		}
		else{
			String typeId = typeParams.get("typekey").toString();
			String nodePath = taskDataService.getNodePath(typeId);
			System.out.println("nodePath:"+nodePath);
			if(StringUtils.isNotEmpty(nodePath))
				filter.getFilters().put("nodePath",nodePath + "%");
			List<?> list = bpmService.getMyTasks(filter);
			
			System.out.println("size of list:"+list.size());
			
			mv=new ModelAndView("/platform/bpm/taskPendingMattersList.jsp");
			
			return mv.addObject("taskList", list);
		}	
	}
	
	private static String addParametersToUrl(String url,Map<String, Object> params){
		StringBuffer sb=new StringBuffer();
		int idx1=url.indexOf("?");
		if(idx1>0){
			sb.append(url.substring(0, idx1));
		}
		sb.append("?");
		
		Map<String,Object> map=getQueryStringMap(url);
		map.putAll(params);
		
		for(Entry<String, Object> entry:map.entrySet()){
			if(BeanUtils.isEmpty(entry.getValue()))
				continue;
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		return sb.substring(0, sb.length()-1);
	}
	
	private static Map<String,Object> getQueryStringMap(String url){
		Map<String,Object> map = new  HashMap<String, Object>();
		int idx1=url.indexOf("?");
		if(idx1>0){
			String queryStr=url.substring(idx1+1);
			String[] queryNodeAry = queryStr.split("&");
			for(String queryNode:queryNodeAry){
				String[] strAry = queryNode.split("=");
				if(strAry.length>=2)
					map.put(strAry[0].trim(),strAry[1]);
			}
		}
		return map;
	}
	
	/**
	 * 获取分类参数
	 * @param request
	 * @return
	 */
	private Map<String,Object> getTypeParams(HttpServletRequest request){
		String typeId = RequestUtil.getString(request, "typeId");
		Map<String,Object> params=new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(typeId)){
			if(!typeId.contains(":")){
				//分类节点
				params.put("typekey",typeId);
			
			}
			else{
				if(typeId.contains("-")){
					//任务节点
					String[] a = typeId.split("-");
					params.put("processkey",a[0]);
					params.put("ptaskkey",a[1]);
				}
				else{
					//流程节点
					String[] a = typeId.split(":", 2);
				
					params.put("typeId",a[0]);
					params.put("processkey",a[1]);
				}
			}
		}
		return params;
	}
}
