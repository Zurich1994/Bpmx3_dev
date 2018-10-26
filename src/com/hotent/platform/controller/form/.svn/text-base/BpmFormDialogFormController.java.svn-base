package com.hotent.platform.controller.form;

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
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.form.BpmFormDialogService;

/**
 * 对象功能:通用表单对话框 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-06-25 11:05:09
 */
@Controller
@RequestMapping("/platform/form/bpmFormDialog/")
@Action(ownermodel=SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormDialogFormController extends BaseFormController
{
	@Resource
	private BpmFormDialogService bpmFormDialogService;
	
	/**
	 * 添加或更新通用表单对话框。
	 * @param request
	 * @param response
	 * @param bpmFormDialog 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新通用表单对话框",
	detail="<#if StringUtil.isNotEmpty(isAdd)>" +
			"<#if isAdd==0>添加<#else>更新</#if>" +
			"通用表对话框【${SysAuditLinkService.getBpmFormDialogLink(Long.valueOf(dialogId))}】成功" +
			"<#else>添加或更新通用表对话框【${name}】失败</#if>")
	public void save(HttpServletRequest request, HttpServletResponse response, BpmFormDialog bpmFormDialog,BindingResult bindResult) throws Exception
	{
		ResultMessage resultMessage=validForm("bpmFormDialog", bpmFormDialog, bindResult, request);
		String  isAdd="0"; 
		if(resultMessage.getResult()==ResultMessage.Fail){
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg="";
		
		if(StringUtil.isEmpty(bpmFormDialog.getDisplayfield())){
			resultMsg="未设置显示的字段";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
			return;
		}
		if(StringUtil.isEmpty(bpmFormDialog.getResultfield())){
			resultMsg="未设置返回的字段";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
			return;
		}		
		if(bpmFormDialog.getId()==0){
			bpmFormDialog.setId(UniqueIdUtil.genId());
			String alias=bpmFormDialog.getAlias();
			boolean isExist=bpmFormDialogService.isExistAlias(alias);
			if(isExist){
				resultMsg="该别名已经存在！";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
				return;
			}
			bpmFormDialogService.add(bpmFormDialog);
			resultMsg="添加通用表单对话框成功";
			
		}else{
			String alias=bpmFormDialog.getAlias();
			Long id=bpmFormDialog.getId();
			boolean isExist=bpmFormDialogService.isExistAliasForUpd(id, alias);
			if(isExist){
				resultMsg="该别名已经存在！";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
				return;
			}
			bpmFormDialogService.update(bpmFormDialog);
			resultMsg="更新通用表单对话框成功";
			isAdd="1";
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isAdd);
		SysAuditThreadLocalHolder.putParamerter("dialogId", bpmFormDialog.getId().toString());
		SysAuditThreadLocalHolder.putParamerter("name", bpmFormDialog.getName());
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param ID
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected BpmFormDialog getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter BpmFormDialog getFormObject here....");
		BpmFormDialog bpmFormDialog=null;
		if(id>0){
			bpmFormDialog=bpmFormDialogService.getById(id);
		}else{
			bpmFormDialog= new BpmFormDialog();
		}
		return bpmFormDialog;
    }

}
