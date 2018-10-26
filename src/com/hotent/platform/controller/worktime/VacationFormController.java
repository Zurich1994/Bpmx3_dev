package com.hotent.platform.controller.worktime;

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
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.worktime.Vacation;
import com.hotent.platform.service.worktime.VacationService;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;

/**
 * 对象功能:法定假期设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:49
 */
@Controller
@RequestMapping("/platform/worktime/vacation/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class VacationFormController extends BaseFormController
{
	@Resource
	private VacationService vacationService;
	
	/**
	 * 添加或更新法定假期设置。
	 * @param request
	 * @param response
	 * @param vacation 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新法定假期设置",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>法定假期设置 :" +
					"【${SysAuditLinkService.getVacationLink(Long.valueOf(id))}】")
	public void save(HttpServletRequest request, HttpServletResponse response, Vacation vacation) throws Exception
	{
		
		ResultMessage resultMessage=null;
		boolean isadd=vacation.getId()==null;
		String resultMsg=null;
		if(vacation.getId()==null){
			vacation.setId(UniqueIdUtil.genId());
			vacationService.add(vacation);
			resultMsg="法定假期设置添加成功";
		}else{
			vacationService.update(vacation);
			resultMsg="法定假期设置更新成功";
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
			SysAuditThreadLocalHolder.putParamerter("id", vacation.getId().toString());
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
    protected Vacation getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter Vacation getFormObject here....");
		Vacation vacation=null;
		if(id!=null){
			vacation=vacationService.getById(id);
		}else{
			vacation= new Vacation();
		}
		return vacation;
    }

}
