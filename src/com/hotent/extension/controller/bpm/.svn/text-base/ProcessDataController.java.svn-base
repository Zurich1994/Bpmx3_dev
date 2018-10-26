package com.hotent.extension.controller.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.page.PageBean;
import com.hotent.core.page.PageUtils;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.extension.model.bpm.ProcessRunData;
import com.hotent.extension.service.bpm.ProcessRunDataService;
import com.hotent.extension.service.bpm.TaskDataService;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormDialogService;
@Controller
@RequestMapping("/extension/bpm/processRunData/")
public class ProcessDataController extends BaseController{
	@Resource
	ProcessRunDataService processRunDataService;
	@Resource
	BpmFormDialogService bpmFormDialogService;
	
	@Resource
	TaskDao taskDao;
	@Resource
	BpmService bpmService;
	@Resource
	BpmDefinitionService bpmDefinitionService;
	@Resource
	ProcessRunService processRunService;
	
	@Resource
	TaskDataService taskDataService;//add by liuzhenchang
	
	/*
	 * 已办事宜的业务数据显示
	 * 2014,7,23 by zengqingyun
	 */
  @RequestMapping("alreadyMattersView")
  public ModelAndView getAlreadyMattersView(HttpServletRequest request,HttpServletResponse response)throws Exception{
	  QueryFilter filter = new QueryFilter(request,"processRunItem");
	  Map<String,Object> typeParams=getTypeParams(request);
	  filter.getFilters().putAll(typeParams);
	  String actDefId=typeParams.get("processkey").toString();
	  String nodeId="";
		if(BeanUtils.isNotEmpty(typeParams.get("ptaskkey"))){
			nodeId=typeParams.get("ptaskkey").toString();
		}
	  //已办事宜
	  filter.addFilter("assignee", ContextUtil.getCurrentUserId().toString());
	  List<ProcessRun> list=processRunService.getAlreadyMattersListWithBus(filter);
	
	  Map paramsMap=RequestUtil.getQueryMap(request);
	  String nextUrl=RequestUtil.getUrl(request);
	  //根据流程信息获取业务表单设置
	  String alias=bpmFormDialogService.getByDefIdAndNodeId(actDefId, nodeId).getAlias();
	  BpmFormDialog bpmFormDialog=bpmFormDialogService.getData(alias, paramsMap);
	  ModelAndView mv=this.getAutoView();
	  mv.addObject("bpmFormDialog",bpmFormDialog).addObject("typeId",RequestUtil.getString(request, "typeId"));
	  
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
		
		List<ProcessRunData> processRunDataList=processRunDataService.getProcessDataList(list, bpmFormDialog, paramsMap);
	   Map<String,Object> parameters=new HashMap<String,Object>();
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
			pageBean.setTotalCount(processRunDataList.size());
			String pageHtml = PageUtils.getPageHtml(pageBean, nextUrl, "", true, true);
			mv.addObject("pageHtml", pageHtml);
		}
		mv.addObject("paramsMap", paramsMap) ;
		
	  return mv.addObject("processRunDataList",processRunDataList);
  }
  /*
   * 办结事宜的业务数据显示
   * 2014,7,25 by zengqingyun
   */
  @SuppressWarnings("unchecked")
@RequestMapping("completedMattersView")
  public ModelAndView getCompletedMattersView(HttpServletRequest request,HttpServletResponse response)throws Exception{
	  QueryFilter filter = new QueryFilter(request,"processRunItem");
	  Map<String,Object> typeParams=getTypeParams(request);
	  
	  Object obj=typeParams.get("processkey");
	  ModelAndView mv;
	  
	  if(obj!=null){
	  
      filter.getFilters().putAll(typeParams);

	  String actDefId=obj.toString();
	  System.out.println("流程定义ID："+actDefId);
	  String nodeId="";
		if(BeanUtils.isNotEmpty(typeParams.get("ptaskkey"))){
			nodeId=typeParams.get("ptaskkey").toString();
		}
	  //办结事宜
	  filter.addFilter("assignee", ContextUtil.getCurrentUserId().toString());
	  List<ProcessRun> list=processRunService.getCompletedMattersList(filter);	  
	
	  Map paramsMap=RequestUtil.getQueryMap(request);
	  String nextUrl=RequestUtil.getUrl(request);
	  //根据流程信息获取业务表单设置
	  BpmFormDialog dialog=bpmFormDialogService.getByDefIdAndNodeId(actDefId, nodeId);
	  if(BeanUtils.isEmpty(dialog)){
		  throw new Exception("流程及节点未设置业务数据");
	  }
	  String alias=dialog.getAlias();

	  BpmFormDialog bpmFormDialog=bpmFormDialogService.getData(alias, paramsMap);
	  mv=this.getAutoView();
	  mv.addObject("bpmFormDialog",bpmFormDialog).addObject("typeId",RequestUtil.getString(request, "typeId"));
	  
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
		
		System.out.println("size of list:"+list.size());
		  //add by liuzhenchang for testing
		  for(ProcessRun temp:list){
			  System.out.println(temp.getRunId());
		  }
		
		List<ProcessRunData> processRunDataList=processRunDataService.getProcessAlreadyDataList(list, bpmFormDialog, paramsMap);
	    Map<String,Object> parameters=new HashMap<String,Object>();
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
			pageBean.setTotalCount(processRunDataList.size());
			String pageHtml = PageUtils.getPageHtml(pageBean, nextUrl, "", true, true);
			mv.addObject("pageHtml", pageHtml);
		}
		mv.addObject("paramsMap", paramsMap) ;
		
		System.out.println("size of list:"+processRunDataList.size());
		
	    return mv.addObject("processRunDataList",processRunDataList);
	    }
	    else{
			String typeId = typeParams.get("typekey").toString();
			String nodePath = taskDataService.getNodePath(typeId);
			
			if(StringUtils.isNotEmpty(nodePath))
				filter.getFilters().put("nodePath",nodePath + "%");
			
			filter.addFilter("assignee", ContextUtil.getCurrentUserId().toString());
			List<ProcessRun> list = processRunService.getCompletedMattersList(filter);
			
			for (ProcessRun processRun : list) {
				BpmDefinition bpmDefinition = bpmDefinitionService
						.getByActDefId(processRun.getActDefId());
				if (BeanUtils.isNotEmpty(bpmDefinition) && bpmDefinition.getIsPrintForm() == 1) {
					processRun.setIsPrintForm(bpmDefinition.getIsPrintForm());
				}
			}
			
			System.out.println("size of list:"+list.size());
			  //add by liuzhenchang for testing
			  for(ProcessRun temp:list){
				  System.out.println(temp.getRunId());
			  }
			
			mv = new ModelAndView("/platform/bpm/processRunCompletedMattersList.jsp");
			mv = mv.addObject("processRunList", list);
			return mv;		
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
