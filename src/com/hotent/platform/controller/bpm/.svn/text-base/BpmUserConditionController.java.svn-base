package com.hotent.platform.controller.bpm;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmNodeUserService;
import com.hotent.platform.service.bpm.BpmUserConditionService;

/**
 * 对象功能:用户条件配置  控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-31 15:26:17
 */
@Controller
@RequestMapping("/platform/bpm/bpmUserCondition/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmUserConditionController extends BaseController
{
	@Resource
	private BpmUserConditionService bpmUserConditionService;
	@Resource
	private BpmNodeUserService bpmNodeUserService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	
	
	
	/**
	 * 添加或更新用户条件配置 。
	 * @param request
	 * @param response
	 * @param bpmUserCondition 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新用户条件配置",
			detail="<#if 0==conditionId!0>添加<#else>更新</#if>" +
					"流程定义【${SysAuditLinkService.getBpmDefinitionLink(actDefId)}】节点【${SysAuditLinkService.getNodeName(actDefId,nodeId)}】的用户条件配置 "
	)
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long defId=RequestUtil.getLong(request, "defId");
		Long conditionId=RequestUtil.getLong(request, "conditionId");
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		String condition = RequestUtil.getString(request, "condition");
		String users = request.getParameter( "users");
		if(StringUtil.isEmpty(users)){
			users = "";
		}
		String conditionShow = request.getParameter("conditionShow");
		if(StringUtil.isEmpty(conditionShow)){
			conditionShow = "";
		}
		Integer conditionType = RequestUtil.getInt(request, "conditionType",0);
		Long sn = RequestUtil.getLong(request, "sn");
		String formIdentity = RequestUtil.getString(request, "formIdentity");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		
		String resultMsg = null;
		BpmUserCondition bpmUserCondition = null;
		try{
			if(conditionId==null||conditionId==0){
				bpmUserCondition=new BpmUserCondition();
				
			}else{
				bpmUserCondition=bpmUserConditionService.getById(conditionId);
			}
			
			bpmUserCondition.setNodeid(nodeId);
			bpmUserCondition.setActdefid(actDefId);
			bpmUserCondition.setSn(sn);
			bpmUserCondition.setCondition(condition);
			bpmUserCondition.setFormIdentity(formIdentity);
			//如果节点不为空,获取setId设置到用户条件当中。
			if(StringUtil.isNotEmpty(nodeId)){
				BpmNodeSet bpmNodeSet = null;
				if(StringUtil.isEmpty(parentActDefId)){
					bpmNodeSet= bpmNodeSetService.getByDefIdNodeId(defId, nodeId);
				}else{
					bpmNodeSet= bpmNodeSetService.getByDefIdNodeId(defId, nodeId, parentActDefId);
				}
				if(BeanUtils.isNotEmpty(bpmNodeSet)){
					bpmUserCondition.setSetId(bpmNodeSet.getSetId());
				}
			}
			
			bpmUserCondition.setConditionShow(conditionShow);
			bpmUserCondition.setConditionType(conditionType);
			bpmUserCondition.setParentActDefId(parentActDefId);
			
			bpmUserConditionService.saveConditionAndUser(bpmUserCondition, users);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			e.printStackTrace();
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 BpmUserCondition 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected BpmUserCondition getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		BpmUserCondition bpmUserCondition = (BpmUserCondition)JSONObject.toBean(obj, BpmUserCondition.class);
		
		return bpmUserCondition;
    }
	
	/**
	 * 取得用户条件配置 分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
//	@Action(description="查看用户条件配置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmUserCondition> list=bpmUserConditionService.getAll(new QueryFilter(request,"bpmUserConditionItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmUserConditionList",list);
		
		return mv;
	}
	
	/**
	 * 删除用户条件配置 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除用户条件配置 ",
	execOrder=ActionExecOrder.BEFORE,
	detail="<#list StringUtils.split(id,\",\") as item>" +
				"<#assign entity=bpmUserConditionService.getById(Long.valueOf(item))/>" +
				"删除流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.actdefid)}】" +
				"节点【${SysAuditLinkService.getNodeName(entity.actdefid,entity.nodeid)}】的用户条件配置 "+
			"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			for(int i=0;i<lAryId.length;i++){
				bpmNodeUserService.delByConditionId(lAryId[i]);
			}			
			bpmUserConditionService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除用户条件配置成功!");
		}catch(Exception ex){
			ex.printStackTrace();
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑用户条件配置 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑用户条件配置")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmUserCondition bpmUserCondition=bpmUserConditionService.getById(id);
		
		return getAutoView().addObject("bpmUserCondition",bpmUserCondition).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得用户条件配置明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
//	@Action(description="查看用户条件配置明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		BpmUserCondition bpmUserCondition = bpmUserConditionService.getById(id);	
		return getAutoView().addObject("bpmUserCondition", bpmUserCondition);
	}
	
	/**
	 * 添加或更新用户条件配置 。
	 * @param request
	 * @param response
	 * @param bpmUserCondition 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateSn")
	@Action(description="添加或更新用户条件配置")
	public void updateSn(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		
		String conditionIds=RequestUtil.getString(request, "conditionIds");
		String[] aryConditions=conditionIds.split(",");
		String resultMsg=null;		

		try{
			for(int i=0;i<aryConditions.length;i++){
				long lId=Long.parseLong(aryConditions[i]);
				BpmUserCondition bpmUserCondition=bpmUserConditionService.getById(lId);
				bpmUserCondition.setSn((long)i);
				bpmUserConditionService.update(bpmUserCondition);
			}
			resultMsg="更新用户条件配置成功";

			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
		
//		Long currentId=RequestUtil.getLong(request, "currentId");
//		Long currentSn=RequestUtil.getLong(request, "currentSn");
//		Long otherId=RequestUtil.getLong(request, "otherId");
//		Long otherSn=RequestUtil.getLong(request, "otherSn");
//		
//		String resultMsg=null;		
//
//		try{
//			
//				BpmUserCondition bpmUserCondition=bpmUserConditionService.getById(currentId);		
//				BpmUserCondition  otherCondition=bpmUserConditionService.getById(otherId);
//				if(bpmUserCondition==null || otherCondition==null)
//					throw new Exception("获取不到对象");
//				if(currentSn==otherSn){
//					otherSn++;
//				}
//				bpmUserCondition.setSn(currentSn);
//			    bpmUserConditionService.update(bpmUserCondition);
//			
//			    otherCondition.setSn(otherSn);
//			    bpmUserConditionService.update(otherCondition);
//			    
//				resultMsg=getText("TT_BPM_USER_CONDITION");
//
//			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
//		}catch(Exception e){
//			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
//		}
	}
	
	/**
	 * 删除用户条件配置 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delByAjax")
	@ResponseBody
	@Action(description="删除消息抄送中的用户条件配置 ",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除" +
			"<#list StringUtils.split(id,\",\") as item>" +
				"<#assign entity=bpmUserConditionService.getById(Long.valueOf(item))/>" +
				" 流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.actdefid)}】" +
				"<#if StringUtils.isNotEmpty(entity.nodeid)>" +
					"节点【${SysAuditLinkService.getNodeName(entity.actdefid,entity.nodeid)}】" +
				"</#if>" +
				"的用户条件配置 "+
			"</#list>"
	)
	public String delByAjax(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		JSONObject jsonObject = new JSONObject();
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			bpmUserConditionService.delConditionById(lAryId);
			jsonObject.accumulate("result", true)
					  .accumulate("message", "删除用户条件配置 成功!");
		}catch(Exception ex){
			String failMsg = "删除失败" + ex.getMessage();
			jsonObject.accumulate("result", false)
			          .accumulate("message", failMsg);
		}
		return jsonObject.toString();
	}
	
	@RequestMapping("updateGroup")
	@Action(description="添加或更新用户条件配置 分组号",
			detail="更新" +
			"<#list StringUtils.split(conditionIds,\",\") as item>" +
				"<#assign entity=bpmUserConditionService.getById(Long.valueOf(item))/>" +
				"流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.actdefid)}】" +
				"节点【${SysAuditLinkService.getNodeName(entity.actdefid,entity.nodeid)}】的用户条件配置 的分组号"+
			"</#list>"
	)
	public void updateGroup(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		String ids = RequestUtil.getString(request, "conditionIds");
		String groupNos = RequestUtil.getString(request, "groupNos");
		String[] aryConditionId=ids.split(",");
		String[] arygroupNo=groupNos.split(",");
		String resultMsg=null;		
		try{
			for(int i=0;i<aryConditionId.length;i++){
				String idStr = aryConditionId[i];
				Long id = Long.parseLong(idStr);
				Integer groupNo = Integer.parseInt(arygroupNo[i]);
			
				BpmUserCondition bpmUserCondition=bpmUserConditionService.getById(id);
				bpmUserCondition.setGroupNo(groupNo);
				bpmUserConditionService.update(bpmUserCondition);
				resultMsg="更新批次号成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
}
