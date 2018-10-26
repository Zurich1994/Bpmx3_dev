package com.hotent.platform.controller.bpm;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.ProcessRunService;

/**
 * 对象功能:流程实例扩展控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-03 09:33:06
 */
@Controller
@RequestMapping("/platform/bpm/processRun/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class ProcessRunFormController extends BaseFormController
{
	@Resource
	private ProcessRunService processRunService;
	
	/**
	 * 添加或更新流程实例扩展。
	 * @param request
	 * @param response
	 * @param processRun 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新流程实例扩展",execOrder=ActionExecOrder.AFTER,
			detail="<#if StringUtil.isNotEmpty(isAdd)>" +
					"<#if isAdd==0>添加" +
					"<#else>更新" +
					"</#if>" +
					"流程实例扩展  :【${SysAuditLinkService.getProcessRunLink(Long.valueOf(runId))}】" +
				"<#else>" +
					"添加或更新流程实例扩展：【${subject}】失败" +
				"</#if>")
	public void save(HttpServletRequest request, HttpServletResponse response, ProcessRun processRun,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("processRun", processRun, bindResult, request);
		String isAdd="0";
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		if(processRun.getRunId()==null){
			processRun.setRunId(UniqueIdUtil.genId());
			processRunService.add(processRun);
			resultMsg="添加流程实例扩展成功";
		}else{
			processRunService.update(processRun);
			resultMsg="更新流程实例扩展成功";
			isAdd="1";
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isAdd);
		SysAuditThreadLocalHolder.putParamerter("runId", processRun.getRunId().toString());
		SysAuditThreadLocalHolder.putParamerter("subject", processRun.getSubject());
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param runId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected ProcessRun getFormObject(@RequestParam("runId") Long runId,Model model) throws Exception {
		logger.debug("enter ProcessRun getFormObject here....");
		ProcessRun processRun=null;
		if(runId!=null){
			processRun=processRunService.getById(runId);
		}else{
			processRun= new ProcessRun();
		}
		return processRun;
    }

}
