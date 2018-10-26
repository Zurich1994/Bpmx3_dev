package com.hotent.platform.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.hotent.platform.model.system.DesktopLayout;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.DesktopLayoutService;
/**
 * 对象功能:桌面布局 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
@Controller
@RequestMapping("/platform/system/desktopLayout/")
@Action(ownermodel=SysAuditModelType.DESKTOP_MANAGEMENT)
public class DesktopLayoutFormController extends BaseFormController
{
	@Resource
	private DesktopLayoutService desktopLayoutService;
	protected Logger logger = LoggerFactory.getLogger(DesktopLayoutcolFormController.class);
	/**
	 * 添加或更新桌面布局。
	 * @param request
	 * @param response
	 * @param desktopLayout 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新桌面布局",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>桌面布局：" +
					"【${SysAuditLinkService.getDesktopLayoutLink(Long.valueOf(id))}】"
			)
	public void save(HttpServletRequest request, HttpServletResponse response, DesktopLayout desktopLayout,BindingResult bindResult) throws Exception
	{
		ResultMessage resultMessage=validForm("desktopLayout", desktopLayout, bindResult, request);
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		boolean isadd=desktopLayout.getId()==null;
		String resultMsg=null;
		if(desktopLayout.getId()==null){
			desktopLayout.setId(UniqueIdUtil.genId());
			if(desktopLayoutService.getDefaultLayout()==null){
				desktopLayout.setIsDefault(1);
			}else{
				desktopLayout.setIsDefault(0);}
			String[] aryWidth=desktopLayout.getWidth().split(",");
			logger.info(StringUtils.join(aryWidth, ",")); 
			desktopLayout.setWidth(StringUtils.join(aryWidth, ","));
			desktopLayoutService.add(desktopLayout);
			resultMsg="桌面布局成功";
		}else{
			desktopLayoutService.update(desktopLayout);
			resultMsg="桌面布局成功";
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		//添加系统日志信息 -B
				try {
					SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
					SysAuditThreadLocalHolder.putParamerter("id", desktopLayout.getId().toString());
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected DesktopLayout getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter DesktopLayout getFormObject here....");
		DesktopLayout desktopLayout=null;
		if(id!=null){
			desktopLayout=desktopLayoutService.getById(id);
		}else{
			desktopLayout= new DesktopLayout();
		}
		return desktopLayout;
    }
}
