package com.hotent.platform.controller.bpm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.extension.model.bpm.BpmNodeData;
import com.hotent.extension.service.bpm.BpmNodeDataService;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormTableService;

/**
 * 对象功能:节点设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-09 14:57:19
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeSet/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmNodeSetController extends BaseController
{
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmService bpmService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	
	//add by liuzhenchang
	@Resource
	private BpmNodeDataService bpmNodeDataService; 
	
//changed by liuzhenchang
	/**
	 * 取得节点设置列表。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看节点设置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long defId=RequestUtil.getLong(request, "defId");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		String isNew = "yes";         //是否是新的流程 或者 重新设置    并且没有绑定任何表单的流程   
		BpmDefinition bpmDefinition=bpmDefinitionService.getById(defId);
		String deployId=bpmDefinition.getActDeployId().toString();
		List<String> nodeList=new ArrayList<String>();
		
		Map<String, Map<String, String>> activityList=new HashMap<String, Map<String, String>>();
		String defXml = bpmService.getDefXmlByDeployId(deployId);
		Map<String, Map<String, String>> activityAllList= BpmUtil.getTranstoActivitys(defXml, nodeList);
		List<BpmNodeSet> list = null;
		
		//start by fangjie 2014.06.11
		List<BpmNodeData> listData = bpmNodeDataService.getBpmNodeData(defId);
		//end
		
		if(StringUtil.isEmpty(parentActDefId)){
			list = bpmNodeSetService.getByDefId(defId);
		}else{
			list = bpmNodeSetService.getByDefId(defId, parentActDefId);
		}
		//start by fangjie 2014.06.11
		for(int i = 0 ;i<listData.size();i++){
			list.get(i).setName(listData.get(i).getDialogName());
			list.get(i).setAlias(listData.get(i).getDialogAlias());
			
		}		
		//end
		
		Map<String, String> taskMap=activityAllList.get("任务节点");
		for(int i=0;i<list.size();i++){	
			String nodeId=list.get(i).getNodeId();
			Map<String, String> tempMap=new HashMap<String,String>();
			Set<Map.Entry<String, String>> set = taskMap.entrySet();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			   Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			   if(!nodeId.equals(entry.getKey())){
				   tempMap.put(entry.getKey(), entry.getValue());
			   }
			}
			activityList.put(list.get(i).getNodeId(), tempMap);
		}
		
		BpmNodeSet globalForm = null;
		BpmNodeSet bpmForm = null;
		List<BpmNodeData> globalData = this.bpmNodeDataService.getGlobalData(defId) ;
		if(StringUtil.isEmpty(parentActDefId)){
			globalForm = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_GloabalForm);
			bpmForm = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_BpmForm);
			if(globalData.size()>0){
				bpmForm.setAlias(globalData.get(0).getDialogAlias());
				bpmForm.setName(globalData.get(0).getDialogName());
			}
		}else{
			globalForm = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_GloabalForm, parentActDefId);
			bpmForm = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_BpmForm, parentActDefId);
			if(globalData.size()>0){
				bpmForm.setAlias(globalData.get(0).getDialogAlias());
				bpmForm.setName(globalData.get(0).getDialogName());
				}
			}
			if (BeanUtils.isNotEmpty(globalForm)) {
			BpmFormDef bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(globalForm.getFormKey());
			if(BeanUtils.isNotEmpty(bpmFormDef) &&  BeanUtils.isNotEmpty(bpmFormDef.getTableId()) ){
				
				List<BpmFormTable> formTablelist = bpmFormTableService.getSubTableByMainTableId(bpmFormDef.getTableId());
				if(BeanUtils.isNotEmpty(formTablelist)){
					globalForm.setExistSubTable(new Short("1"));
					globalForm.setMainTableId(bpmFormDef.getTableId());
				}else{
					globalForm.setExistSubTable(new Short("0"));
					globalForm.setMainTableId(0l);
				}
			}
		}
			
		for(BpmNodeSet bpmNodeSet:list){
		//BpmFormDef bpmFormDef = bpmFormDefService.getById(bpmNodeSet.getFormKey());
			BpmFormDef bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
			if(BeanUtils.isNotEmpty(bpmFormDef) &&  BeanUtils.isNotEmpty(bpmFormDef.getTableId()) ){
				if("yes".equals(isNew)){
					isNew = "no";
				}
				List<BpmFormTable> formTablelist = bpmFormTableService.getSubTableByMainTableId(bpmFormDef.getTableId());
				if(BeanUtils.isNotEmpty(formTablelist)){
					bpmNodeSet.setExistSubTable(new Short("1"));
					bpmNodeSet.setMainTableId(bpmFormDef.getTableId());
				}else{
					bpmNodeSet.setExistSubTable(new Short("0"));
					bpmNodeSet.setMainTableId(0l);
				}
			}
		}
		
		if( "yes".equals(isNew)&& (BeanUtils.isNotEmpty(globalForm)||BeanUtils.isNotEmpty(bpmForm)) ){    //当 bpmNodeSetList globalForm bpmForm 三个都为空时 表示没有绑定表单
			isNew = "no";
		}
		
		ModelAndView mv=this.getAutoView()
				.addObject("bpmNodeSetList",list)
				.addObject("bpmDefinition",bpmDefinition)
				.addObject("globalForm", globalForm)
				.addObject("bpmForm", bpmForm)
				.addObject("activityList", activityList)
		        .addObject("isNew", isNew)
		        .addObject("parentActDefId", parentActDefId);
		return mv;
	}
	@RequestMapping("listform")
	@Action(description="查看节点设置分页列表")
	public ModelAndView listform(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long defId=RequestUtil.getLong(request, "defId");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		String isNew = "yes";         //是否是新的流程 或者 重新设置    并且没有绑定任何表单的流程   
		BpmDefinition bpmDefinition=bpmDefinitionService.getById(defId);
		String deployId=bpmDefinition.getActDeployId().toString();
		List<String> nodeList=new ArrayList<String>();
		
		Map<String, Map<String, String>> activityList=new HashMap<String, Map<String, String>>();
		String defXml = bpmService.getDefXmlByDeployId(deployId);
		Map<String, Map<String, String>> activityAllList= BpmUtil.getTranstoActivitys(defXml, nodeList);
		List<BpmNodeSet> list = null;
		
		//start by fangjie 2014.06.11
		List<BpmNodeData> listData = bpmNodeDataService.getBpmNodeData(defId);
		//end
		
		if(StringUtil.isEmpty(parentActDefId)){
			list = bpmNodeSetService.getByDefId(defId);
		}else{
			list = bpmNodeSetService.getByDefId(defId, parentActDefId);
		}
		//start by fangjie 2014.06.11
		for(int i = 0 ;i<listData.size();i++){
			list.get(i).setName(listData.get(i).getDialogName());
			list.get(i).setAlias(listData.get(i).getDialogAlias());
			
		}		
		//end
		
		Map<String, String> taskMap=activityAllList.get("任务节点");
		for(int i=0;i<list.size();i++){	
			String nodeId=list.get(i).getNodeId();
			Map<String, String> tempMap=new HashMap<String,String>();
			Set<Map.Entry<String, String>> set = taskMap.entrySet();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			   Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			   if(!nodeId.equals(entry.getKey())){
				   tempMap.put(entry.getKey(), entry.getValue());
			   }
			}
			activityList.put(list.get(i).getNodeId(), tempMap);
		}
		
		BpmNodeSet globalForm = null;
		BpmNodeSet bpmForm = null;
		List<BpmNodeData> globalData = this.bpmNodeDataService.getGlobalData(defId) ;
		if(StringUtil.isEmpty(parentActDefId)){
			globalForm = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_GloabalForm);
			bpmForm = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_BpmForm);
			if(globalData.size()>0){
				bpmForm.setAlias(globalData.get(0).getDialogAlias());
				bpmForm.setName(globalData.get(0).getDialogName());
			}
		}else{
			globalForm = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_GloabalForm, parentActDefId);
			bpmForm = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_BpmForm, parentActDefId);
			if(globalData.size()>0){
				bpmForm.setAlias(globalData.get(0).getDialogAlias());
				bpmForm.setName(globalData.get(0).getDialogName());
				}
			}
			if (BeanUtils.isNotEmpty(globalForm)) {
			BpmFormDef bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(globalForm.getFormKey());
			if(BeanUtils.isNotEmpty(bpmFormDef) &&  BeanUtils.isNotEmpty(bpmFormDef.getTableId()) ){
				
				List<BpmFormTable> formTablelist = bpmFormTableService.getSubTableByMainTableId(bpmFormDef.getTableId());
				if(BeanUtils.isNotEmpty(formTablelist)){
					globalForm.setExistSubTable(new Short("1"));
					globalForm.setMainTableId(bpmFormDef.getTableId());
				}else{
					globalForm.setExistSubTable(new Short("0"));
					globalForm.setMainTableId(0l);
				}
			}
		}
			
		for(BpmNodeSet bpmNodeSet:list){
		//BpmFormDef bpmFormDef = bpmFormDefService.getById(bpmNodeSet.getFormKey());
			BpmFormDef bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
			if(BeanUtils.isNotEmpty(bpmFormDef) &&  BeanUtils.isNotEmpty(bpmFormDef.getTableId()) ){
				if("yes".equals(isNew)){
					isNew = "no";
				}
				List<BpmFormTable> formTablelist = bpmFormTableService.getSubTableByMainTableId(bpmFormDef.getTableId());
				if(BeanUtils.isNotEmpty(formTablelist)){
					bpmNodeSet.setExistSubTable(new Short("1"));
					bpmNodeSet.setMainTableId(bpmFormDef.getTableId());
				}else{
					bpmNodeSet.setExistSubTable(new Short("0"));
					bpmNodeSet.setMainTableId(0l);
				}
			}
		}
		
		if( "yes".equals(isNew)&& (BeanUtils.isNotEmpty(globalForm)||BeanUtils.isNotEmpty(bpmForm)) ){    //当 bpmNodeSetList globalForm bpmForm 三个都为空时 表示没有绑定表单
			isNew = "no";
		}
		
		ModelAndView mv=this.getAutoView()
				.addObject("bpmNodeSetList",list)
				.addObject("bpmDefinition",bpmDefinition)
				.addObject("globalForm", globalForm)
				.addObject("bpmForm", bpmForm)
				.addObject("activityList", activityList)
		        .addObject("isNew", isNew)
		        .addObject("parentActDefId", parentActDefId);
		return mv;
	}
	
		/**
		 * 删除节点设置
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		@RequestMapping("del")
		@Action(description="删除节点设置",
				execOrder=ActionExecOrder.BEFORE,
				detail="<#list StringUtils.split(setId,\",\") as item>" +
							"<#assign entity = bpmNodeSetService.getById(Long.valueOf(item))/>" +
							"<#if item_index==0>" +
								"删除流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.defId)}】的节点" +
							"</#if>"+
							"【${entity.nodeName}】" +
						"</#list>的设置"
		)
		public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			String preUrl= RequestUtil.getPrePage(request);
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "setId");
			bpmNodeSetService.delByIds(lAryId);
			response.sendRedirect(preUrl);
		}

	@RequestMapping("edit")
	@Action(description="编辑节点设置")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long setId=RequestUtil.getLong(request,"setId");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmNodeSet bpmNodeSet=null;
		if(setId!=null){
			 bpmNodeSet= bpmNodeSetService.getById(setId);
		}else{
			bpmNodeSet=new BpmNodeSet();
		}
		return getAutoView().addObject("bpmNodeSet",bpmNodeSet).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得节点设置明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看节点设置明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"setId");
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getById(id);		
		return getAutoView().addObject("bpmNodeSet", bpmNodeSet);
	}
	
	
	@RequestMapping("save")
	@Action(description="成功设置流程节点表单",
			detail="设置流程定义${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}的节点表单及跳转方式"
	)
//	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) throws Exception
//	{
	public void save(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long defId=RequestUtil.getLong(request,"defId");
		//父流程定义Id
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		BpmDefinition bpmDefinition=bpmDefinitionService.getById(defId);
		
		String[] nodeIds=request.getParameterValues("nodeId");
		String[] nodeNames=request.getParameterValues("nodeName");
		String[] formTypes=request.getParameterValues("formType");
		String[] aryFormKey=request.getParameterValues("formKey");
		String[] formUrls=request.getParameterValues("formUrl");
		String[] formDefNames=request.getParameterValues("formDefName");
		String[] aryBeforeHandler=request.getParameterValues("beforeHandler");
		String[] aryAfterHandler=request.getParameterValues("afterHandler");
		String[] aryDetailUrl=request.getParameterValues("detailUrl");
		String[] aryMobileFormKey=request.getParameterValues("mobileFormKey");
		/**
		 * 接受节点对应的对话框名称与别名
		 */
		String[] dialogName      =     request.getParameterValues("formDialogName");
		String[] formDialogAlias =     request.getParameterValues("formDialogAlias");
		/**
		 * end by fangjie
		 */
		//数据模板 
		//节点表单
		String[] subtemplateid =   request.getParameterValues("subTemplateId");
		String[] subtableId =   request.getParameterValues("subTableId");
		String[] subformkey =   request.getParameterValues("subFormKey");
		String[] subject =   request.getParameterValues("anning");
		
		System.out.println("subtemplateid:"+subtemplateid.length+"subtableId:"+subtableId.length+"subformkey:"+subformkey.length);
		
		System.out.println("名字长度:"+formDefNames.length);
		
		Map<String,BpmNodeSet> nodeMap = null;
		if(StringUtil.isEmpty(parentActDefId)){
			nodeMap = bpmNodeSetService.getMapByDefId(defId);
		}else{//子流程的节点数据
			nodeMap = bpmNodeSetService.getMapByDefId(defId, parentActDefId);
		}
	
		List<BpmNodeSet> nodeList=new ArrayList<BpmNodeSet>();
		for(int i=0;i<nodeIds.length;i++){
			
			String nodeId=nodeIds[i];
			BpmNodeSet nodeSet=new BpmNodeSet();
			if(nodeMap.containsKey(nodeId)){
				nodeSet=nodeMap.get(nodeId);
				//设置原有的表单key，用于删除之前设置表单节点的权限。
				//	if(nodeSet.getFormKey()>0L){
				if(StringUtil.isNotEmpty(nodeSet.getFormKey())){
					nodeSet.setOldFormKey(nodeSet.getFormKey());
				}
				
			}
			nodeSet.setNodeId(nodeId);
			nodeSet.setActDefId(bpmDefinition.getActDefId());
			nodeSet.setNodeName(nodeNames[i]);
			
			//start by fangjie 2014.06.10
			nodeSet.setName(dialogName[i]);
			nodeSet.setAlias(formDialogAlias[i]);
			//end
			
			
			short formType= Short.parseShort(formTypes[i]);
			System.out.println("formTypes:"+formTypes[i]);
			String beforeHandler=aryBeforeHandler[i];
			String afterHandler=aryAfterHandler[i];
			
			beforeHandler=getHandler(beforeHandler);
			afterHandler=getHandler(afterHandler);
			
			String detailUrl=aryDetailUrl[i];
			String formUrl=formUrls[i];
			nodeSet.setFormType(formType);
			//没有选择表单
			if(formType==-1){
				nodeSet.setFormUrl("");
				nodeSet.setDetailUrl("");
				
				nodeSet.setFormKey("");
				nodeSet.setFormDefName("");
				
			}
			//在线表单
			else if(formType==0){
				nodeSet.setFormUrl("");
				nodeSet.setDetailUrl("");
				nodeSet.setTemplateName("");
				nodeSet.setTemplateId("");
				nodeSet.setTableId("");
				String formKey="";
				System.out.println("aryFormKey:"+aryFormKey[i]);
				if(StringUtil.isNotEmpty(aryFormKey[i])){
					formKey=aryFormKey[i];
					
					nodeSet.setFormKey(formKey);
					nodeSet.setFormDefName(formDefNames[i]);
					System.out.println("名字:"+formDefNames[i]);
				}
				else{
					nodeSet.setFormKey("");
					nodeSet.setFormDefName("");
					
					nodeSet.setFormType((short) -1);
				}
				
				//手机表单
				String mobileFormKey="";
				
				if(StringUtil.isNotEmpty(aryMobileFormKey[i]) && !aryMobileFormKey[i].equals("0")){
					mobileFormKey=aryMobileFormKey[i];
				}
				nodeSet.setMobileFormKey(mobileFormKey);
			}
			//数据模板
			//设置数据空不空
			else if(formType==2){
				
					nodeSet.setTemplateName(subject[i]);
				
				
					nodeSet.setTemplateId(subtemplateid[i]);
				
				
					nodeSet.setFormKey(subformkey[i]);
				
				
					nodeSet.setTableId(subtableId[i]);
					
					nodeSet.setFormDefName("");
				
				System.out.println("setFormKey:"+subformkey[i]);
				System.out.println("subtableId:"+subtableId[i]);
				System.out.println("subtemplateid:"+subtemplateid[i]);
			}
			//url表单
			else{
				nodeSet.setFormKey("");
				nodeSet.setFormDefName("");
				
				nodeSet.setFormUrl(formUrl);
				nodeSet.setDetailUrl(detailUrl);

			}

			nodeSet.setBeforeHandler(beforeHandler);
			nodeSet.setAfterHandler(afterHandler);
						
			nodeSet.setDefId(new Long(defId));
			
			String[] jumpType=request.getParameterValues("jumpType_"+nodeId);
			if(jumpType!=null){
				nodeSet.setJumpType(StringUtil.getArrayAsString(jumpType));
			}else{
				nodeSet.setJumpType("");
			}
			String isHideOption=request.getParameter("isHideOption_"+nodeId);
			String isRequired=request.getParameter("isRequired_"+nodeId);
			String isPopup=request.getParameter("isPopup_"+nodeId);
			String opinionField=request.getParameter("opinionField_"+nodeId);
			String isHidePath=request.getParameter("isHidePath_"+nodeId);
			String opinionHtml=request.getParameter("opinionHtml_"+nodeId);
			if(StringUtil.isNotEmpty(isHideOption)){
				nodeSet.setIsHideOption(BpmNodeSet.HIDE_OPTION);
			}else{
				nodeSet.setIsHideOption(BpmNodeSet.NOT_HIDE_OPTION);
			}
			if(StringUtil.isNotEmpty(isRequired)){
				nodeSet.setIsRequired(BpmNodeSet.IS_REQUIRED);
			}else{
				nodeSet.setIsRequired(BpmNodeSet.NOT_IS_REQUIRED);
			}
			if(StringUtil.isNotEmpty(isPopup)){
				nodeSet.setIsPopup(BpmNodeSet.IS_POPUP);
			}else{
				nodeSet.setIsPopup(BpmNodeSet.NOT_IS_POPUP);
			}
			
			nodeSet.setOpinionField(opinionField);
			
			if(StringUtil.isNotEmpty(isHidePath)){
				nodeSet.setIsHidePath(BpmNodeSet.HIDE_PATH);
			}else{
				nodeSet.setIsHidePath(BpmNodeSet.NOT_HIDE_PATH);
			}
			
			if(StringUtil.isNotEmpty(opinionHtml)){
				nodeSet.setOpinionHtml((short)1);
			}else{
				nodeSet.setOpinionHtml((short)0);
			}
			nodeSet.setSetType(BpmNodeSet.SetType_TaskNode);
			nodeSet.setParentActDefId(parentActDefId);
			nodeList.add(nodeSet);
		}
				
		List<BpmNodeSet> list=getGlobalBpm(request,bpmDefinition);
		nodeList.addAll(list);
		String returnUrl = "list.ht?defId="+defId +"&time=" + System.currentTimeMillis();
		try{
			bpmNodeSetService.save(defId, nodeList, parentActDefId);
			addMessage(new ResultMessage(ResultMessage.Success,"成功设置流程节点表单及跳转方式 !"), request);
			if(StringUtil.isNotEmpty(parentActDefId)){
				returnUrl += "&parentActDefId="+parentActDefId;
			}
			response.sendRedirect(returnUrl);
		}
		catch(Exception ex){
			ex.printStackTrace();
			addMessage(new ResultMessage(ResultMessage.Fail,ex.getMessage()), request);
			response.sendRedirect(returnUrl);
		}
		
					
//		bpmNodeSetService.save(defId, nodeList, parentActDefId);
//		
//		addMessage(new ResultMessage(ResultMessage.Success, getText("controller.bpmNodeSet.save")), request);
//		String returnUrl = "redirect:list.ht?defId="+defId +"&time=" + System.currentTimeMillis();
//		if(StringUtil.isNotEmpty(parentActDefId)){
//			returnUrl += "&parentActDefId="+parentActDefId;
//		}
//		return new ModelAndView(returnUrl);
//	}
	
		
	}
	@RequestMapping("saveform")
	@Action(description="成功设置流程节点表单",
			detail="设置流程定义${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}的节点表单及跳转方式"
	)
//	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) throws Exception
//	{
	public void saveform(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long defId=RequestUtil.getLong(request,"defId");
		//父流程定义Id
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		BpmDefinition bpmDefinition=bpmDefinitionService.getById(defId);
		
		String[] nodeIds=request.getParameterValues("nodeId");
		String[] nodeNames=request.getParameterValues("nodeName");
		String[] formTypes=request.getParameterValues("formType");
		String[] aryFormKey=request.getParameterValues("formKey");
		String[] formUrls=request.getParameterValues("formUrl");
		String[] formDefNames=request.getParameterValues("formDefName");
		String[] aryBeforeHandler=request.getParameterValues("beforeHandler");
		String[] aryAfterHandler=request.getParameterValues("afterHandler");
		String[] aryDetailUrl=request.getParameterValues("detailUrl");
		String[] aryMobileFormKey=request.getParameterValues("mobileFormKey");
		/**
		 * 接受节点对应的对话框名称与别名
		 */
		String[] dialogName      =     request.getParameterValues("formDialogName");
		String[] formDialogAlias =     request.getParameterValues("formDialogAlias");
		/**
		 * end by fangjie
		 */
		//数据模板 
		String[] subtemplateid =   request.getParameterValues("subTemplateId");
		String[] subtableId =   request.getParameterValues("subTableId");
		String[] subformkey =   request.getParameterValues("subFormKey");
		String[] subject =   request.getParameterValues("anning");
		
		System.out.println("subtemplateid:"+subtemplateid.length+"subtableId:"+subtableId.length+"subformkey:"+subformkey.length);
		
		System.out.println("名字长度:"+formDefNames.length);
		
		Map<String,BpmNodeSet> nodeMap = null;
		if(StringUtil.isEmpty(parentActDefId)){
			nodeMap = bpmNodeSetService.getMapByDefId(defId);
		}else{//子流程的节点数据
			nodeMap = bpmNodeSetService.getMapByDefId(defId, parentActDefId);
		}
	
		List<BpmNodeSet> nodeList=new ArrayList<BpmNodeSet>();
		for(int i=0;i<nodeIds.length;i++){
			
			String nodeId=nodeIds[i];
			BpmNodeSet nodeSet=new BpmNodeSet();
			if(nodeMap.containsKey(nodeId)){
				nodeSet=nodeMap.get(nodeId);
				//设置原有的表单key，用于删除之前设置表单节点的权限。
				//	if(nodeSet.getFormKey()>0L){
				if(StringUtil.isNotEmpty(nodeSet.getFormKey())){
					nodeSet.setOldFormKey(nodeSet.getFormKey());
				}
				
			}
			nodeSet.setNodeId(nodeId);
			nodeSet.setActDefId(bpmDefinition.getActDefId());
			nodeSet.setNodeName(nodeNames[i]);
			
			//start by fangjie 2014.06.10
			nodeSet.setName(dialogName[i]);
			nodeSet.setAlias(formDialogAlias[i]);
			//end
			
			
			short formType= Short.parseShort(formTypes[i]);
			System.out.println("formTypes:"+formTypes[i]);
			String beforeHandler=aryBeforeHandler[i];
			String afterHandler=aryAfterHandler[i];
			
			beforeHandler=getHandler(beforeHandler);
			afterHandler=getHandler(afterHandler);
			
			String detailUrl=aryDetailUrl[i];
			String formUrl=formUrls[i];
			nodeSet.setFormType(formType);
			//没有选择表单
			if(formType==-1){
				nodeSet.setFormUrl("");
				nodeSet.setDetailUrl("");
				
				nodeSet.setFormKey("");
				nodeSet.setFormDefName("");
				
			}
			//在线表单
			else if(formType==0){
				nodeSet.setFormUrl("");
				nodeSet.setDetailUrl("");
				
				String formKey="";
				System.out.println("aryFormKey:"+aryFormKey[i]);
				if(StringUtil.isNotEmpty(aryFormKey[i])){
					formKey=aryFormKey[i];
					
					nodeSet.setFormKey(formKey);
					nodeSet.setFormDefName(formDefNames[i]);
					System.out.println("名字:"+formDefNames[i]);
				}
				else{
					nodeSet.setFormKey("");
					nodeSet.setFormDefName("");
					
					nodeSet.setFormType((short) -1);
				}
				
				//手机表单
				String mobileFormKey="";
				if(StringUtil.isNotEmpty(aryMobileFormKey[i]) && !aryMobileFormKey[i].equals("0")){
					mobileFormKey=aryMobileFormKey[i];
				}
				nodeSet.setMobileFormKey(mobileFormKey);
			}
			//数据模板
			else if(formType==2){
				if("".equals(subject[i])){
					System.out.println("到底是不是空");
				}else {
					nodeSet.setTemplateName(subject[i]);
				}
				if("".equals(subtemplateid[i])){
					System.out.println("到底是不是空");
				}else {
					nodeSet.setTemplateId(subtemplateid[i]);
				}
				if("".equals(subformkey[i])){
					System.out.println("到底是不是空");
				}else {
					nodeSet.setFormKey(subformkey[i]);
				}
				if("".equals(subtableId[i])){
					System.out.println("到底是不是空");
				}else {
					nodeSet.setTableId(subtableId[i]);
				}
				System.out.println("setFormKey:"+subformkey[i]);
				System.out.println("subtableId:"+subtableId[i]);
				System.out.println("subtemplateid:"+subtemplateid[i]);
			}
			//url表单
			else{
				nodeSet.setFormKey("");
				nodeSet.setFormDefName("");
				
				nodeSet.setFormUrl(formUrl);
				nodeSet.setDetailUrl(detailUrl);

			}

			nodeSet.setBeforeHandler(beforeHandler);
			nodeSet.setAfterHandler(afterHandler);
						
			nodeSet.setDefId(new Long(defId));
			
			String[] jumpType=request.getParameterValues("jumpType_"+nodeId);
			if(jumpType!=null){
				nodeSet.setJumpType(StringUtil.getArrayAsString(jumpType));
			}else{
				nodeSet.setJumpType("");
			}
			String isHideOption=request.getParameter("isHideOption_"+nodeId);
			String isRequired=request.getParameter("isRequired_"+nodeId);
			String isPopup=request.getParameter("isPopup_"+nodeId);
			String opinionField=request.getParameter("opinionField_"+nodeId);
			String isHidePath=request.getParameter("isHidePath_"+nodeId);
			String opinionHtml=request.getParameter("opinionHtml_"+nodeId);
			if(StringUtil.isNotEmpty(isHideOption)){
				nodeSet.setIsHideOption(BpmNodeSet.HIDE_OPTION);
			}else{
				nodeSet.setIsHideOption(BpmNodeSet.NOT_HIDE_OPTION);
			}
			if(StringUtil.isNotEmpty(isRequired)){
				nodeSet.setIsRequired(BpmNodeSet.IS_REQUIRED);
			}else{
				nodeSet.setIsRequired(BpmNodeSet.NOT_IS_REQUIRED);
			}
			if(StringUtil.isNotEmpty(isPopup)){
				nodeSet.setIsPopup(BpmNodeSet.IS_POPUP);
			}else{
				nodeSet.setIsPopup(BpmNodeSet.NOT_IS_POPUP);
			}
			
			nodeSet.setOpinionField(opinionField);
			
			if(StringUtil.isNotEmpty(isHidePath)){
				nodeSet.setIsHidePath(BpmNodeSet.HIDE_PATH);
			}else{
				nodeSet.setIsHidePath(BpmNodeSet.NOT_HIDE_PATH);
			}
			
			if(StringUtil.isNotEmpty(opinionHtml)){
				nodeSet.setOpinionHtml((short)1);
			}else{
				nodeSet.setOpinionHtml((short)0);
			}
			nodeSet.setSetType(BpmNodeSet.SetType_TaskNode);
			nodeSet.setParentActDefId(parentActDefId);
			nodeList.add(nodeSet);
		}
				
		List<BpmNodeSet> list=getGlobalBpm(request,bpmDefinition);
		nodeList.addAll(list);
		String returnUrl = "listform.ht?defId="+defId +"&time=" + System.currentTimeMillis();
		try{
			bpmNodeSetService.save(defId, nodeList, parentActDefId);
			addMessage(new ResultMessage(ResultMessage.Success,"成功设置流程节点表单及跳转方式 !"), request);
			if(StringUtil.isNotEmpty(parentActDefId)){
				returnUrl += "&parentActDefId="+parentActDefId;
			}
			response.sendRedirect(returnUrl);
		}
		catch(Exception ex){
			ex.printStackTrace();
			addMessage(new ResultMessage(ResultMessage.Fail,ex.getMessage()), request);
			response.sendRedirect(returnUrl);
		}
		
					
//		bpmNodeSetService.save(defId, nodeList, parentActDefId);
//		
//		addMessage(new ResultMessage(ResultMessage.Success, getText("controller.bpmNodeSet.save")), request);
//		String returnUrl = "redirect:list.ht?defId="+defId +"&time=" + System.currentTimeMillis();
//		if(StringUtil.isNotEmpty(parentActDefId)){
//			returnUrl += "&parentActDefId="+parentActDefId;
//		}
//		return new ModelAndView(returnUrl);
//	}
	
		
	}
	
	//changed by liuzhenchang
	/**
	 * 从request中构建全局和流程实例表单数据。
	 * @param request
	 * @param bpmDefinition
	 * @return
	 */
	private List<BpmNodeSet> getGlobalBpm(HttpServletRequest request,BpmDefinition bpmDefinition){
		List<BpmNodeSet> list=new ArrayList<BpmNodeSet>();
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		int globalFormType=RequestUtil.getInt(request, "globalFormType");
		System.out.println("类型: "+globalFormType);
		if(globalFormType>=0){
			String defaultFormKey=RequestUtil.getString(request, "defaultFormKey");
			String defaultFormName=RequestUtil.getString(request, "defaultFormName");
			String beforeHandlerGlobal=RequestUtil.getString(request, "beforeHandlerGlobal");
			String afterHandlerGlobal=RequestUtil.getString(request, "afterHandlerGlobal");
			
			String  defaultMobileFormKey=RequestUtil.getString(request, "defaultMobileFormKey");
			
			
			beforeHandlerGlobal=getHandler(beforeHandlerGlobal);
			afterHandlerGlobal=getHandler(afterHandlerGlobal);
			String formUrlGlobal=RequestUtil.getString(request, "formUrlGlobal");
			String detailUrlGlobal=RequestUtil.getString(request,"detailUrlGlobal");
			//业务数据模板
			String templateId = RequestUtil.getString(request, "id");
			String tableId = RequestUtil.getString(request, "tableid");
			String formKey = RequestUtil.getString(request, "formkey");
			String subject = RequestUtil.getString(request, "subject");
			System.out.println(""+ templateId + "tableid:"+tableId+ "formkey:"+formKey);
			
			BpmNodeSet bpmNodeSet=new BpmNodeSet();
			bpmNodeSet.setDefId(bpmDefinition.getDefId());
			bpmNodeSet.setActDefId(bpmDefinition.getActDefId());
			bpmNodeSet.setFormUrl(formUrlGlobal);
			bpmNodeSet.setBeforeHandler(beforeHandlerGlobal);
			bpmNodeSet.setAfterHandler(afterHandlerGlobal);
			bpmNodeSet.setFormType((short)globalFormType);
			bpmNodeSet.setDetailUrl(detailUrlGlobal);
			bpmNodeSet.setSetType(BpmNodeSet.SetType_GloabalForm);
			bpmNodeSet.setParentActDefId(parentActDefId);
			
			if("".equals(templateId)){
				System.out.println("空空空");
			}else{
				bpmNodeSet.setTemplateId(templateId);
			}
			
			if("".equals(tableId)){
				System.out.println("空空");
			}else{
				bpmNodeSet.setTableId(tableId);
			}
			
			if(globalFormType==2){
				bpmNodeSet.setTemplateName(subject);
				bpmNodeSet.setFormKey(formKey);				
			}else{
				bpmNodeSet.setFormDefName(defaultFormName);
				bpmNodeSet.setFormKey(defaultFormKey);				
				
			}
					
		
			bpmNodeSet.setMobileFormKey(defaultMobileFormKey);
			
			
			if(globalFormType==BpmNodeSet.FORM_TYPE_ONLINE){
				if(StringUtil.isNotEmpty(defaultFormKey)){
					list.add(bpmNodeSet);
				}
			}else if(globalFormType==BpmNodeSet.FORM_TYPE_TEMP){			
					bpmNodeSet.setFormKey(formKey);
					list.add(bpmNodeSet);
			}
			else{
				if(StringUtil.isNotEmpty(formUrlGlobal)){
					bpmNodeSet.setFormKey(null);
					list.add(bpmNodeSet);
				}
			}
		}
		int bpmFormType=RequestUtil.getInt(request, "bpmFormType");
		if(bpmFormType>=0){
			String bpmFormKey=RequestUtil.getString(request, "bpmFormKey");
			String bpmFormName=RequestUtil.getString(request, "bpmFormName");
			String bpmFormUrl=RequestUtil.getString(request, "bpmFormUrl");
			
			BpmNodeSet bpmNodeSet=new BpmNodeSet();
			bpmNodeSet.setDefId(bpmDefinition.getDefId());
			bpmNodeSet.setActDefId(bpmDefinition.getActDefId());
			
			if(StringUtil.isNotEmpty(bpmFormKey)){
				bpmNodeSet.setFormKey(bpmFormKey);
				bpmNodeSet.setFormDefName(bpmFormName);
			}
			bpmNodeSet.setFormUrl(bpmFormUrl);
			bpmNodeSet.setFormType((short)bpmFormType);
			bpmNodeSet.setSetType(BpmNodeSet.SetType_BpmForm);
			bpmNodeSet.setParentActDefId(parentActDefId);
			
			/**
			 * start by fangjie 2014.6.9
			 * 接受全局对话框名称
			 * 接受全局对话框别名
			 */
			String bpmdialogName=RequestUtil.getString(request, "bpmDialogName");
			String bpmFormDialogAlias=RequestUtil.getString(request,"bpmFormDialogAlias");
			bpmNodeSet.setName(bpmdialogName);
			bpmNodeSet.setAlias(bpmFormDialogAlias);
			/**
			 * end
			 */
			
			list.add(bpmNodeSet);
		}
		return list;
	}
	
	

	
	private String getHandler(String handler){
		if(StringUtil.isEmpty(handler) || handler.indexOf(".")==-1){
			handler="";
		}
		return handler;
	}
	
	/**
	 * 验证handler。
	 * 输入格式为 serviceId +"." + 方法名。
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("validHandler")
	@Action(description="验证处理器")
	public void validHandler(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String handler=RequestUtil.getString(request,"handler");
		int rtn=BpmUtil.isHandlerValid(handler);
		String template="{\"result\":\"%s\",\"msg\":\"%s\"}";
		String msg="";
		switch (rtn) {
			case 0:
				msg="输入有效";
				break;
			case -1:
				msg="输入格式无效";
				break;
			case -2:
				msg="没有service类";
				break;
			case -3:
				msg="没有对应的方法";
				break;
			default:
				msg="其他错误";
				break;
		}
		String str=String.format(template, rtn,msg);
		response.getWriter().print(str);
	}
	
	/**
	 * 获取流程节点
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flowScope")
	@Action(description="获取流程节点")
	public ModelAndView flowScope(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long defId=RequestUtil.getLong(request, "defId");
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		String parentActDefId= RequestUtil.getString(request, "parentActDefId");
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getScopeByNodeIdAndActDefId(nodeId,actDefId,parentActDefId);		
		return getAutoView().addObject("bpmNodeSet", bpmNodeSet);
	}	
	/**
	 * 为流程节点设置选择器的的使用范围
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("savaScope")
	@Action(description=" 为流程节点设置选择器的的使用范围")
	public void savaScope(HttpServletRequest request, HttpServletResponse response,BpmNodeSet bpmNodeSet) throws Exception
	{
		String resultMsg=null;
		Long setId=RequestUtil.getLong(request, "setId", 0);
		String scope=RequestUtil.getString(request, "scope");
		try {
			bpmNodeSetService.updateScopeById(setId,scope);
			resultMsg="流程节点设置选择器使用范围成功!";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		} catch (Exception e) {
			resultMsg="设置失败!";
			e.printStackTrace();
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
		}
	}

	
	

}
