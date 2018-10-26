package com.hotent.platform.controller.system;

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

import com.hotent.platform.model.system.DesktopMycolumn;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.DesktopMycolumnService;

/**
 * 对象功能:桌面个人栏目 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
@Controller
@RequestMapping("/platform/system/desktopMycolumn/")
@Action(ownermodel=SysAuditModelType.DESKTOP_MANAGEMENT) 
public class DesktopMycolumnFormController extends BaseFormController
{
	@Resource
	private DesktopMycolumnService desktopMycolumnService;
	
	/**
	 * 添加或更新桌面个人栏目。
	 * @param request
	 * @param response
	 * @param desktopMycolumn 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新桌面个人栏目",execOrder=ActionExecOrder.AFTER,
			detail="<#if StringUtil.isNotEmpty(isAdd)>" +
					"<#if isAdd==0>添加" +
					"<#else>更新" +
					"</#if>" +
					"自定义表  :【${SysAuditLinkService.getDesktopMycolumnLink(Long.valueOf(id))}】" +
				"<#else>" +
					"添加或更新自定义表：【表名:${table.tableName}, 表注释:${table.tableDesc}】失败" +
				"</#if>"
					)
	public void save(HttpServletRequest request, HttpServletResponse response, DesktopMycolumn desktopMycolumn,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("desktopMycolumn", desktopMycolumn, bindResult, request);
		String isAdd="0";
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		if(desktopMycolumn.getId()==null){
			desktopMycolumn.setId(UniqueIdUtil.genId());
			desktopMycolumnService.add(desktopMycolumn);
			resultMsg="添加桌面个人栏目成功";
		}else{
			desktopMycolumnService.update(desktopMycolumn);
			resultMsg="更新桌面个人栏目成功";
			isAdd="1";
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isAdd);
		
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected DesktopMycolumn getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter DesktopMycolumn getFormObject here....");
		DesktopMycolumn desktopMycolumn=null;
		if(id!=null){
			desktopMycolumn=desktopMycolumnService.getById(id);
		}else{
			desktopMycolumn= new DesktopMycolumn();
		}
		return desktopMycolumn;
    }
}
