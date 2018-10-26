package com.hotent.platform.controller.bpm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.util.BpmUtil;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeRule;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeRuleService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.thread.MessageUtil;

/**
 * 对象功能:流程节点规则 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2011-12-14 15:41:53
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeRule/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmNodeRuleController extends BaseController
{
	@Resource
	private BpmNodeRuleService bpmNodeRuleService;
	@Resource
	private BpmService bpmService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	
	
	/**
	 * 取得流程节点规则分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程节点规则分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmNodeRule> list=bpmNodeRuleService.getAll(new QueryFilter(request,"bpmNodeRuleItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmNodeRuleList",list);
		return mv;
	}
	
	/**
	 * 删除流程节点规则
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程节点规则",
			execOrder=ActionExecOrder.BEFORE,
			detail="<#list StringUtils.split(ruleId,\",\") as item>" +
							"<#assign entity = bpmNodeRuleService.getById(Long.valueOf(item))/>" +
							"<#if item_index==0>" +
							    "删除流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.actDefId)}】的节点 【${SysAuditLinkService.getNodeName(entity.actDefId,entity.nodeId)}】 自定义工具按钮：" +
							"</#if>"+
							"【${entity.ruleName}】 " +
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ResultMessage resultMessage = null;
//		String preUrl= RequestUtil.getPrePage(request);
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "ruleId");
			bpmNodeRuleService.delByIds(lAryId);
			resultMessage=new ResultMessage(ResultMessage.Success,"删除流程节点规则成功");
		}
		catch (Exception e) {
			resultMessage = new ResultMessage(ResultMessage.Fail,"删除流程节点规则失败:" + e.getMessage());
		}
		
		response.getWriter().print(resultMessage);
		//addMessage(resultMessage, request);
		//response.sendRedirect(preUrl);	
	}

	@RequestMapping("edit")
	@Action(description="编辑流程节点规则")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		String deployId=RequestUtil.getString(request, "deployId");
		String actDefId=RequestUtil.getString(request, "actDefId");
		String nodeId=RequestUtil.getString(request, "nodeId");
		String nodeName=RequestUtil.getString(request, "nodeName");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		
		FlowNode flowNode= NodeCache.getByActDefId(actDefId).get(nodeId);
		
		BpmNodeRule bpmNodeRule=new BpmNodeRule();
		String defXml = bpmService.getDefXmlByDeployId(deployId);
		BpmDefinition bpmDefinition= bpmDefinitionService.getByActDefId(actDefId);
		//取得可跳转活动节点
		List<String> nodeList=new ArrayList<String>();
		//nodeList.add(nodeId);
		Map<String, Map<String, String>> activityList= BpmUtil.getTranstoActivitys(defXml, nodeList);
		
		BpmNodeSet bpmNodeSet = null;
		if(StringUtil.isEmpty(parentActDefId)){
			bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(actDefId, nodeId);
		}else{
			bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(actDefId, nodeId, parentActDefId);
		}
		
		bpmNodeRule.setActDefId(actDefId);
		bpmNodeRule.setNodeId(nodeId);
		
		ModelAndView mv=getAutoView();
		String vers= request.getHeader("USER-AGENT");
		if(vers.indexOf("MSIE 6")!=-1){
			mv= new ModelAndView("/platform/bpm/bpmNodeRuleEdit_ie6.jsp");
		}
		mv.addObject("activityList", activityList)
			.addObject("nodeName",nodeName)
			.addObject("bpmNodeRule",bpmNodeRule)
			.addObject("deployId",deployId)
			.addObject("actDefId",actDefId)
			.addObject("nodeId",nodeId)
			.addObject("bpmNodeSet",bpmNodeSet)
			.addObject("defId", bpmDefinition.getDefId())
			.addObject("nextNodes", flowNode.getNextFlowNodes())
			.addObject("parentActDefId", parentActDefId);
		
		return mv;
	}
	
	/**
	 * 根据流程定义id和节点ID获取流程规则列表。
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getByDefIdNodeId")
	public void getByDefIdNodeId(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String actDefId=RequestUtil.getString(request, "actDefId");
		String nodeId=RequestUtil.getString(request, "nodeId");
		PrintWriter out=response.getWriter();
		List<BpmNodeRule> ruleList= bpmNodeRuleService.getByDefIdNodeId(actDefId, nodeId);
		String str=JSONArray.fromObject(ruleList).toString();
		out.print(str);
	}
	
	/**
	 * 排序
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("sortRule")
	public void sortRule(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		try{
			String ruleIds=RequestUtil.getString(request, "ruleids");
			bpmNodeRuleService.reSort(ruleIds);
			ResultMessage resObj=new ResultMessage(ResultMessage.Success,"规则排序成功");
			response.getWriter().print(resObj);
			
		}
		catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"规则排序失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		
		
	}

	/**
	 * 取得流程节点规则明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看流程节点规则明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"ruleId");
		BpmNodeRule bpmNodeRule = bpmNodeRuleService.getById(id);		
		return getAutoView().addObject("bpmNodeRule", bpmNodeRule);
	}
	
	/**
	 * 根据ID获取json对象
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getById")
	public void getById(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		long id=RequestUtil.getLong(request,"ruleId");
		BpmNodeRule bpmNodeRule = bpmNodeRuleService.getById(id);	
		String rtn=JSONObject.fromObject(bpmNodeRule).toString();
	
		response.getWriter().print(rtn);
	}
	
	@RequestMapping("updateIsJumpForDef")
	@ResponseBody
	@Action(description="设置流程定义节点的跳转规则",
	detail="流程定义【${SysAuditLinkService.getBpmDefinitionLink(actDefId)}的节点 ${nodeId}】的跳转规则为" +
			"<#if BpmNodeSet.RULE_INVALID_NORMAL.equals(Short.valueOf(isJumpForDef))> 规则不符合条件时，任务按定义正常跳转" +
			"<#elseif BpmNodeSet.RULE_INVALID_NO_NORMAL.equals(Short.valueOf(isJumpForDef))> 规则不符合条件时，任务仅是完成当前节点，不作跳转处理" +
			"</#if>" 
    )
	public String updateIsJumpForDef(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String nodeId=request.getParameter("nodeId");
		String actDefId=request.getParameter("actDefId");
		String isJumpForDef=request.getParameter("isJumpForDef");
		logger.debug("nodeId:" + nodeId + " actDefId:" + actDefId + " isJumpForDef:" + isJumpForDef);
		bpmNodeSetService.updateIsJumpForDef(nodeId, actDefId, new Short(isJumpForDef));
		return "{success:true}";
	}
	
	

}
