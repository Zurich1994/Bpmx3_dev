package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.AppUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeMessage;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeMessageService;
import com.hotent.platform.service.bpm.BpmUserConditionService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.system.SysTemplateService;

/**
 * 对象功能:流程节点邮件 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-31 15:48:59
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeMessage/")
public class BpmNodeMessageController extends BaseController
{
	@Resource
	private BpmNodeMessageService bpmNodeMessageService;
	@Resource
	private SysTemplateService sysTempplateService;

	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmUserConditionService bpmUserConditionService;
	
	/**
	 * 取得流程节点邮件分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程节点邮件分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmNodeMessage> list=bpmNodeMessageService.getAll(new QueryFilter(request,"bpmNodeMessageItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmNodeMessageList",list);
		return mv;
	}
	
	/**
	 * 删除流程节点邮件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程节点邮件")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
		bpmNodeMessageService.delByIds(lAryId);
		response.sendRedirect(preUrl);
	}

	
	@RequestMapping("edit")
	@Action(description="编辑流程节点邮件")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		List<SysTemplate> tempList=sysTempplateService.getAll(new QueryFilter(request,"sysTemplateItem"));
		String actDefId=RequestUtil.getString(request,"actDefId");
		String nodeId=RequestUtil.getString(request,"nodeId");	
		
		List<BpmUserCondition> userConditions = bpmUserConditionService.getByActDefIdAndNodeId(actDefId, nodeId);
		
		List<BpmUserCondition> receiverMailConds = new ArrayList<BpmUserCondition>();
		List<BpmUserCondition> copyToMailConds = new ArrayList<BpmUserCondition>();
		List<BpmUserCondition> bccMailConds = new ArrayList<BpmUserCondition>();
		List<BpmUserCondition> receiverMobileConds = new ArrayList<BpmUserCondition>();
		List<BpmUserCondition> receiverInnerConds = new ArrayList<BpmUserCondition>();
		
		for(BpmUserCondition condition:userConditions){
			if(condition.getConditionType()==null){
				continue;
			}
			switch (condition.getConditionType().intValue()) {
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_RECEIVER:
				receiverMailConds.add(condition);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_COPYTO:
				copyToMailConds.add(condition);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_BCC:
				bccMailConds.add(condition);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MOBILE_RECEIVER:
				receiverMobileConds.add(condition);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_INNER_RECEIVER:
				receiverInnerConds.add(condition);
				break;
			default:
				break;
			}
		}
		List<BpmNodeMessage> bpmNodeMessages= bpmNodeMessageService.getListByActDefIdNodeId(actDefId, nodeId);
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		BpmNodeMessage mailMessage=null;
		BpmNodeMessage innerMessage=null;
		BpmNodeMessage smsMessage=null;
		
		for(BpmNodeMessage message:bpmNodeMessages){
			Short messageType = message.getMessageType();
			if(messageType==null){
				messageType = -1;
			}
			switch (messageType) {
			case BpmNodeMessage.MESSAGE_TYPE_MAIL:
				mailMessage=message;
				break;
			case BpmNodeMessage.MESSAGE_TYPE_INNER:
				innerMessage = message;
				break;
			case BpmNodeMessage.MESSAGE_TYPE_SMS:
				smsMessage=message;
				break;
			default:
				break;
			}
		}
		return getAutoView()
			.addObject("defId", bpmDefinition.getDefId())
			.addObject("actDefId",actDefId)
			.addObject("nodeId", nodeId)
			/////////////////////
			.addObject("mailMessage",mailMessage)
			.addObject("innerMessage",innerMessage)
			.addObject("smsMessage",smsMessage)
			/////////////////////
			.addObject("receiverMailConds",receiverMailConds)
			.addObject("copyToMailConds",copyToMailConds)
			.addObject("bccMailConds",bccMailConds)
			.addObject("receiverInnerConds",receiverInnerConds)
			.addObject("receiverMobileConds",receiverMobileConds)
			/////////////////
			.addObject("receiverMailCondJsons",JSONArray.fromObject(receiverMailConds).toString())
			.addObject("bccMailCondJsons",JSONArray.fromObject(bccMailConds).toString())
			.addObject("copyToMailCondJsons",JSONArray.fromObject(copyToMailConds).toString())
			.addObject("receiverInnerCondJsons",JSONArray.fromObject(receiverInnerConds).toString())
			.addObject("receiverMobileCondJsons",JSONArray.fromObject(receiverMobileConds).toString())
			.addObject("tempList",tempList);
		
	}
	/**消息参数edit1*/
	@RequestMapping("edit1")
	@Action(description="编辑流程节点邮件")
	public ModelAndView edit1(HttpServletRequest request) throws Exception
	{
		List<SysTemplate> tempList=sysTempplateService.getAll(new QueryFilter(request,"sysTemplateItem"));
		String actDefId=RequestUtil.getString(request,"actDefId");
		String nodeId=RequestUtil.getString(request,"nodeId");	
		
		List<BpmUserCondition> userConditions = bpmUserConditionService.getByActDefIdAndNodeId(actDefId, nodeId);
		
		List<BpmUserCondition> receiverMailConds = new ArrayList<BpmUserCondition>();
		List<BpmUserCondition> copyToMailConds = new ArrayList<BpmUserCondition>();
		List<BpmUserCondition> bccMailConds = new ArrayList<BpmUserCondition>();
		List<BpmUserCondition> receiverMobileConds = new ArrayList<BpmUserCondition>();
		List<BpmUserCondition> receiverInnerConds = new ArrayList<BpmUserCondition>();
		
		for(BpmUserCondition condition:userConditions){
			if(condition.getConditionType()==null){
				continue;
			}
			switch (condition.getConditionType().intValue()) {
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_RECEIVER:
				receiverMailConds.add(condition);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_COPYTO:
				copyToMailConds.add(condition);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_BCC:
				bccMailConds.add(condition);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MOBILE_RECEIVER:
				receiverMobileConds.add(condition);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_INNER_RECEIVER:
				receiverInnerConds.add(condition);
				break;
			default:
				break;
			}
		}
		List<BpmNodeMessage> bpmNodeMessages= bpmNodeMessageService.getListByActDefIdNodeId(actDefId, nodeId);
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		BpmNodeMessage mailMessage=null;
		BpmNodeMessage innerMessage=null;
		BpmNodeMessage smsMessage=null;
		
		for(BpmNodeMessage message:bpmNodeMessages){
			Short messageType = message.getMessageType();
			if(messageType==null){
				messageType = -1;
			}
			switch (messageType) {
			case BpmNodeMessage.MESSAGE_TYPE_MAIL:
				mailMessage=message;
				break;
			case BpmNodeMessage.MESSAGE_TYPE_INNER:
				innerMessage = message;
				break;
			case BpmNodeMessage.MESSAGE_TYPE_SMS:
				smsMessage=message;
				break;
			default:
				break;
			}
		}
		return getAutoView()
			.addObject("defId", bpmDefinition.getDefId())
			.addObject("actDefId",actDefId)
			.addObject("nodeId", nodeId)
			/////////////////////
			.addObject("mailMessage",mailMessage)
			.addObject("innerMessage",innerMessage)
			.addObject("smsMessage",smsMessage)
			/////////////////////
			.addObject("receiverMailConds",receiverMailConds)
			.addObject("copyToMailConds",copyToMailConds)
			.addObject("bccMailConds",bccMailConds)
			.addObject("receiverInnerConds",receiverInnerConds)
			.addObject("receiverMobileConds",receiverMobileConds)
			/////////////////
			.addObject("receiverMailCondJsons",JSONArray.fromObject(receiverMailConds).toString())
			.addObject("bccMailCondJsons",JSONArray.fromObject(bccMailConds).toString())
			.addObject("copyToMailCondJsons",JSONArray.fromObject(copyToMailConds).toString())
			.addObject("receiverInnerCondJsons",JSONArray.fromObject(receiverInnerConds).toString())
			.addObject("receiverMobileCondJsons",JSONArray.fromObject(receiverMobileConds).toString())
			.addObject("tempList",tempList);
		
	}
	/**
	 * 取得流程节点邮件明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看流程节点邮件明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		BpmNodeMessage bpmNodeMessage = bpmNodeMessageService.getById(id);		
		return getAutoView().addObject("bpmNodeMessage", bpmNodeMessage);
	}
	
	@RequestMapping("getFlowVars")
	@ResponseBody
	@Action(description="编辑消息参数流程变量设置") 
	public Map<String,Object> getFlowVars(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long defId=RequestUtil.getLong(request, "defId");
		Map<String,Object> map=new HashMap<String, Object>();
		
		BpmFormFieldService bpmFormFieldService=(BpmFormFieldService)AppUtil.getBean(BpmFormFieldService.class);
		List<BpmFormField> flowVars= bpmFormFieldService.getFlowVarByFlowDefId(defId);
		map.put("flowVars", flowVars);
		return map;
		
	}
	
	/**
	 * 消息节点  消息接收人员   人员规则设置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("receiverSetting")
	public ModelAndView receiverSetting(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actDefId);
		int type = RequestUtil.getInt(request, "type");
		List<BpmUserCondition> receiverSettings=null;
		switch (type) {
		case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_RECEIVER:
			receiverSettings = bpmUserConditionService.getReceiverMailConditions(actDefId, nodeId);
			break;
		case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_COPYTO:
			receiverSettings = bpmUserConditionService.getCopyToMailConditions(actDefId, nodeId);
			break;
		case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_BCC:
			receiverSettings = bpmUserConditionService.getBccMailConditions(actDefId, nodeId);
			break;
		case BpmUserCondition.CONDITION_TYPE_MSG_INNER_RECEIVER:
			receiverSettings = bpmUserConditionService.getReceiverInnerConditions(actDefId, nodeId);
			break;
		
		case BpmUserCondition.CONDITION_TYPE_MSG_MOBILE_RECEIVER:
			receiverSettings = bpmUserConditionService.getReceiverSmsConditions(actDefId, nodeId);
			break;
		case BpmUserCondition.CONDITION_TYPE_TRIGGER_NEWFLOW_STARTUSER:
			receiverSettings = bpmUserConditionService.getTriggerNewFlowStartUserConditions(actDefId, nodeId);
			break;
		default:
			receiverSettings=new ArrayList<BpmUserCondition>();
			break;
		}
		ModelAndView mv = getAutoView();
		mv.addObject("receiverSettings", receiverSettings)
		  .addObject("bpmDefinition", bpmDefinition)
		  .addObject("nodeId",nodeId)
		  .addObject("conditionType",type);
		return mv;
	}
	
	
	@RequestMapping("getReceiverUserCondition")
	@ResponseBody
	public Map<String,Object> getReceiverUserCondition(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		int status = 0;
		String msg = "";
		try{
			String actDefId = RequestUtil.getString(request, "actDefId");
			String nodeId = RequestUtil.getString(request, "nodeId");
			int type = RequestUtil.getInt(request, "receiverType");
			List<BpmUserCondition> conditions  = null;
			switch (type) {
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_RECEIVER:
				conditions = bpmUserConditionService.getReceiverMailConditions(actDefId, nodeId);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_COPYTO:
				conditions = bpmUserConditionService.getCopyToMailConditions(actDefId, nodeId);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_BCC:
				conditions = bpmUserConditionService.getBccMailConditions(actDefId, nodeId);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_INNER_RECEIVER:
				conditions = bpmUserConditionService.getReceiverInnerConditions(actDefId, nodeId);
				break;
			case BpmUserCondition.CONDITION_TYPE_MSG_MOBILE_RECEIVER:
				conditions = bpmUserConditionService.getReceiverSmsConditions(actDefId, nodeId);
				break;
			case BpmUserCondition.CONDITION_TYPE_TRIGGER_NEWFLOW_STARTUSER:
				conditions = bpmUserConditionService.getTriggerNewFlowStartUserConditions(actDefId, nodeId);
				break; 
			default:
				conditions = new ArrayList<BpmUserCondition>();
				break;
			}
			map.put("conditions",conditions);
		}catch (Exception e) {
			status = -1;
			msg = e.getMessage();
		}
		map.put("status", status);
		map.put("msg", msg);
		return map;
	}
}
