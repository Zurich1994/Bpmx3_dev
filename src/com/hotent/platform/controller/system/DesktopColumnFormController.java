package com.hotent.platform.controller.system;

import java.util.List;

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
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.platform.model.system.DesktopColumn;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.DesktopColumnService;

/**
 * 对象功能:桌面栏目 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
@Controller
@RequestMapping("/platform/system/desktopColumn/")
@Action(ownermodel=SysAuditModelType.DESKTOP_MANAGEMENT)
public class DesktopColumnFormController extends BaseFormController
{
	@Resource
	private DesktopColumnService desktopColumnService;
	
	/**
	 * 添加或更新桌面栏目。
	 * @param request
	 * @param response
	 * @param desktopColumn 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新桌面栏目",
			detail="<#if isAdd>添加新<#else>更新</#if>桌面栏目" +
					"【${SysAuditLinkService.getDesktopColumnLink(Long.valueOf(id))}】"
					)
	public void save(HttpServletRequest request, HttpServletResponse response, DesktopColumn desktopColumn,BindingResult bindResult) throws Exception
	{
		String resultMsg=null;
		ResultMessage resultMessage=validForm("desktopColumn", desktopColumn, bindResult, request);
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		//验证方法是否正确。
		if(StringUtil.isNotEmpty(desktopColumn.getServiceMethod())){
			int rtn= BpmUtil.isHandlerValidNoCmd(desktopColumn.getServiceMethod(), null);//原本没有,null
			if(rtn<0){
				resultMsg="方法路径有填写不合法，请检查方法！";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
				return;
			}
		}
		
		boolean isadd=desktopColumn.getId()==null;
	
		
		if(desktopColumn.getId()==null){
			boolean isExist= desktopColumnService.isExistDesktopColumn(desktopColumn.getTemplateId());
			if(isExist){
				resultMsg="该栏目别名已经存在不能重复定义！";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
				return;
			}
			desktopColumn.setId(UniqueIdUtil.genId());
			desktopColumnService.add(desktopColumn);
			resultMsg="添加桌面栏目成功";
		}else{
			desktopColumnService.update(desktopColumn);
			resultMsg="更新桌面栏目成功";
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
			SysAuditThreadLocalHolder.putParamerter("id", desktopColumn.getId().toString());
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
    protected DesktopColumn getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter DesktopColumn getFormObject here....");
		DesktopColumn desktopColumn=null;
		if(id!=null){
			desktopColumn=desktopColumnService.getById(id);
		}else{
			desktopColumn= new DesktopColumn();
		}
		return desktopColumn;
    }
}
