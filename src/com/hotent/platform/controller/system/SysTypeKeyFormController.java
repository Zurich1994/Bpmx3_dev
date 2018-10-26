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
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysTypeKey;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.SysTypeKeyService;

/**
 * 对象功能:系统分类键定义 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-10 10:18:36
 */
@Controller
@RequestMapping("/platform/system/sysTypeKey/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class SysTypeKeyFormController extends BaseFormController
{
	@Resource
	private SysTypeKeyService sysTypeKeyService;
	
	/**
	 * 添加或更新系统分类键定义。
	 * @param request
	 * @param response
	 * @param sysTypeKey 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新系统分类键定义",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>系统分类键定义" +
					"【${SysAuditLinkService.getSysTypeKeyLink(Long.valueOf(typeId))}】"
					)
	public void save(HttpServletRequest request, HttpServletResponse response, SysTypeKey sysTypeKey,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("sysTypeKey", sysTypeKey, bindResult, request);
	
		if(resultMessage.getResult()==ResultMessage.Fail){
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", sysTypeKey.getTypeId()==null);
		String typeKey=sysTypeKey.getTypeKey();
		ResultMessage resultMsg=null;
		if(sysTypeKey.getTypeId()==0){
			if(sysTypeKeyService.isExistKey(typeKey)){
				String message="键值已经存在!";
				resultMsg=new ResultMessage(ResultMessage.Fail, message);
			}
			else{
				try{
					sysTypeKey.setTypeId(UniqueIdUtil.genId());
					sysTypeKey.setFlag(0);
					sysTypeKey.setSn(0);
					sysTypeKeyService.add(sysTypeKey);
					String message="添加系统分类键定义成功";
					resultMsg=new ResultMessage(ResultMessage.Success, message);
				}
				catch(Exception ex){
					String str = MessageUtil.getMessage();
					if (StringUtil.isNotEmpty(str)) {
						resultMsg = new ResultMessage(ResultMessage.Fail,"添加系统分类key失败:" + str);
						response.getWriter().print(resultMsg);
					} else {
						String message = ExceptionUtil.getExceptionMessage(ex);
						resultMsg = new ResultMessage(ResultMessage.Fail, message);
						response.getWriter().print(resultMsg);
					}
				}
			}
		}else{
			if(sysTypeKeyService.isKeyExistForUpdate(typeKey,sysTypeKey.getTypeId())){
				String message="键值已经存在!";
				resultMsg=new ResultMessage(ResultMessage.Fail, message);
			}
			else{
				try{
					sysTypeKeyService.update(sysTypeKey);
					String message="更新系统分类键定义成功";
					resultMsg=new ResultMessage(ResultMessage.Success, message);
				}
				catch(Exception ex){
					String str = MessageUtil.getMessage();
					if (StringUtil.isNotEmpty(str)) {
						resultMsg = new ResultMessage(ResultMessage.Fail,"修改系统分类key失败:" + str);
						response.getWriter().print(resultMsg);
					} else {
						String message = ExceptionUtil.getExceptionMessage(ex);
						resultMsg = new ResultMessage(ResultMessage.Fail, message);
						response.getWriter().print(resultMsg);
					}
				}
			}
		}
		writeResultMessage(response.getWriter(),resultMsg);
		SysAuditThreadLocalHolder.putParamerter("typeId", sysTypeKey.getTypeId().toString());
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param typeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected SysTypeKey getFormObject(@RequestParam("typeId") Long typeId,Model model) throws Exception {
		logger.debug("enter SysTypeKey getFormObject here....");
		SysTypeKey sysTypeKey=null;
		if(typeId>0){
			sysTypeKey=sysTypeKeyService.getById(typeId);
		}else{
			sysTypeKey= new SysTypeKey();
		}
		return sysTypeKey;
    }

}
