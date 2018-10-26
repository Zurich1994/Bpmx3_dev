package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmProCopyto;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmProCopytoService;
import com.hotent.platform.service.bpm.BpmRunLogService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.util.ServiceUtil;
/**
 *<pre>
 * 对象功能:流程抄送转发 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2013-03-27 19:26:52
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmProCopyto/")
@Action(ownermodel=SysAuditModelType.PROCESS_CENTER)
public class BpmProCopytoController extends BaseController
{
	@Resource
	private BpmProCopytoService bpmProCopytoService;
	
	@Resource
	private ProcessRunService  processRunService;
	
	@Resource
	private BpmRunLogService  bpmRunLogService;
	
	@Resource
	Map<String, IMessageHandler> handlersMap;
	
	
	/**
	 * 添加或更新流程抄送转发。
	 * @param request
	 * @param response
	 * @param bpmProCopyto 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新流程抄送转发",
			detail="<#if>添加<#else>更新</#if>流程" +
			"【${SysAuditLinkService.getProcessRunLink(Long.valueOf(runId)),subject}】的抄送转发")
	@Deprecated
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		BpmProCopyto bpmProCopyto=getFormObject(request);
		
		
		//添加系统日志信息 -E
		boolean isAdd=true;
		try{
			if(bpmProCopyto.getCopyId()==null||bpmProCopyto.getCopyId()==0){
				bpmProCopyto.setCopyId(UniqueIdUtil.genId());
				bpmProCopytoService.add(bpmProCopyto);
				resultMsg="添加流程抄送转发成功";
			}else{
			    bpmProCopytoService.update(bpmProCopyto);
				resultMsg="更新流程抄送转发成功";
				isAdd=false;
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("isAdd", isAdd);
			SysAuditThreadLocalHolder.putParamerter("runId", bpmProCopyto.getRunId().toString());
			SysAuditThreadLocalHolder.putParamerter("subject", bpmProCopyto.getSubject().toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 取得 BpmProCopyto 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected BpmProCopyto getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		BpmProCopyto bpmProCopyto = (BpmProCopyto)JSONObject.toBean(obj, BpmProCopyto.class);
		
		return bpmProCopyto;
    }
	
	/**
	 * 取得流程抄送转发分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程抄送转发分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		QueryFilter queryFilter = new QueryFilter(request,"bpmProCopytoItem");
		Long runId = RequestUtil.getLong(request, "runId",0);
		List<BpmProCopyto> list=bpmProCopytoService.getAll(queryFilter);
		ModelAndView mv=this.getAutoView().addObject("bpmProCopytoList",list).addObject("runId",runId);
		
		return mv;
	}
	
	/**
	 * 查看我的流程抄送转发列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myList")
	@Action(description="查看我的流程抄送转发列表")
	public ModelAndView myList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		QueryFilter filter = new QueryFilter(request,"bpmProCopytoItem");
		String nodePath = RequestUtil.getString(request, "nodePath");
		if(StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath",nodePath + "%");
		
		filter.getFilters().put("ccUid", ContextUtil.getCurrentUserId());
		List<BpmProCopyto> list=bpmProCopytoService.getMyList(filter);
	        Long porIndex = RequestUtil.getLong(request,"porIndex");
		Long tabIndex = RequestUtil.getLong(request,"tabIndex");
		ModelAndView mv=this.getAutoView()
			.addObject("bpmProCopytoList",list)
			 .addObject("porIndex",porIndex)
			        .addObject("tabIndex",tabIndex);
		
		return mv;
	}
	
	/**
	 * 查看我的流程抄送转发列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("browse")
	@Action(description="查看我的流程抄送转发列表")
	public ModelAndView browse(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	      Long porIndex = RequestUtil.getLong(request,"porIndex");
	      Long tabIndex = RequestUtil.getLong(request,"tabIndex");
	      ModelAndView mv=this.getAutoView()
		        .addObject("porIndex",porIndex)
		        .addObject("tabIndex",tabIndex);
	      return mv;
	}
	
	/**
	 * 标记任务抄送消息为已读
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("mark")
	@ResponseBody
	@Action(description="标记任务抄送消息为已读",
			detail="标记任务抄送消息为已读:" +
			"<#list RequestUtil.getLongAryByStr(request, \"copyIds\") as item>" +
				"<#assign entity=bpmProCopytoService.getById(item)>"+
				"【${SysAuditLinkService.getSysUserLink(entity.ccUid,entity.ccUname)}】对流程【${SysAuditLinkService.getProcessRunLink(entity.runId,entity.subject)}】任务抄送消息为已读;" +
			"</#list>"	
	)
	public String mark(HttpServletRequest request,HttpServletResponse response){
		String copyIds = RequestUtil.getString(request, "copyIds");
		JSONObject jobject = new JSONObject();
		try{
			bpmProCopytoService.updateReadStatus(copyIds);
			jobject.accumulate("result", true)
				   .accumulate("message", "成功标记为已读!");
		}
		catch(Exception ex){
			jobject.accumulate("result", false)
			   .accumulate("message", ex.getMessage());
		
		}
		return jobject.toString();
	}
	
	/**
	 * 删除流程抄送转发
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程抄送转发",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除流程抄送转发:" +
			"<#list RequestUtil.getLongAryByStr(request, \"copyId\") as item>" +
				"<#assign entity=bpmProCopytoService.getById(item)>"+
				"删除 【${SysAuditLinkService.getSysUserLink(entity.ccUid,entity.ccUname)}】对流程【${SysAuditLinkService.getProcessRunLink(entity.runId,entity.subject)}】任务抄送消息;" +
			"</#list>"	
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "copyId");
			bpmProCopytoService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除流程抄送转发成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑流程抄送转发
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑流程抄送转发")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long copyId=RequestUtil.getLong(request,"copyId");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmProCopyto bpmProCopyto=bpmProCopytoService.getById(copyId);
		
		return getAutoView().addObject("bpmProCopyto",bpmProCopyto).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得流程抄送转发明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看流程抄送转发明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long copyId=RequestUtil.getLong(request,"copyId");
		BpmProCopyto bpmProCopyto = bpmProCopytoService.getById(copyId);	
		return getAutoView().addObject("bpmProCopyto", bpmProCopyto);
	}
	
	
	@RequestMapping("getCopyUserByInstId")
	@Action(description = "查看办结事宜流程列表")
	public ModelAndView getCopyUserByInstId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String returnUrl=RequestUtil.getPrePage(request);
		Long runId = RequestUtil.getLong(request, "runId");
		ProcessRun processRun = processRunService.getById(runId);
		QueryFilter filter = new QueryFilter(request,"bpmProCopytoItem");
		List<BpmProCopyto> list=bpmProCopytoService.getUserInfoByRunId(filter);
		ModelAndView mv=this.getAutoView().addObject("bpmProCopytoList",list);
		mv.addObject(RequestUtil.RETURNURL, returnUrl)
			.addObject("isOpenDialog",RequestUtil.getInt(request, "isOpenDialog",0))
			.addObject("link",RequestUtil.getInt(request, "link"))
			.addObject("processRun",processRun);
		return mv;
	}
	
	/**
	 * 办结转发
	 * 
	 * @Methodname: finishDivert
	 * @Discription:
	 * @param request
	 * @param response
	 * @throws Exception
	 * @Author HH
	 * @Time 2012-11-16 下午9:06:57
	 */
	@RequestMapping("finishDivert")
	public void finishDivert(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResultMessage resultMessage;
		Long runId = RequestUtil.getLong(request, "runId", 0);
		String users = RequestUtil.getString(request, "assigneeId");
		String informType = RequestUtil.getStringAry(request, "informType");
		String suggestion =RequestUtil.getStringAry(request, "suggestion");
		
		
		if (runId == 0 || BeanUtils.isEmpty(users)) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "办结转发失败!");
		} else {
			String[] userArray = users.split(",");
			List<String> userList = Arrays.asList(userArray);
			int returnResult = 0;
			try {
				ProcessRun processRun = processRunService.getById(runId);
				SysUser currUser = ContextUtil.getCurrentUser();
				returnResult=processRunService.divertProcess(processRun, userList, currUser,informType,suggestion);
				String memo = "转发了流程:" + processRun.getSubject();
				if (suggestion!=null || suggestion!="") {
					memo=memo+"  转发意见："+suggestion;
				}
				bpmRunLogService.addRunLog(runId,
						BpmRunLog.OPERATOR_TYPE_FINISHDIVERT, memo);
			} catch (Exception e) {
				logger.error("办结转发出错", e);
				resultMessage = new ResultMessage(ResultMessage.Fail, "办结转发失败!");
			}
			if (returnResult==-1) {
				resultMessage = new ResultMessage(ResultMessage.Fail, "办结转发失败!不能转发给自己!");
			}else if (returnResult==1) {
				resultMessage = new ResultMessage(ResultMessage.Success, "办结转发成功!");
			}else {
				resultMessage = new ResultMessage(ResultMessage.Fail, "办结转发失败!");
			} 
		}
		response.getWriter().print(resultMessage);
		
		SysAuditThreadLocalHolder.putParamerter("curUser", ContextUtil.getCurrentUser());
	}
	
	
	@RequestMapping("forward")
	@Action(description="编辑流程抄送转发")
	public ModelAndView forward(HttpServletRequest request) throws Exception
	{

		Map<String, IMessageHandler> handlersMap=ServiceUtil.getHandlerMap();
		return getAutoView()
				//.addObject("param",paramList)
				.addObject("handlersMap",handlersMap);
				
	}
}
