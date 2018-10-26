package com.hotent.platform.controller.system;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import org.apache.log4j.TTCCLayout;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.platform.model.system.Resources;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.ResourcesService;
import com.hotent.redirectionresource.service.redirectionresource.RedirectionresourceService;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.definedAtomForm.model.definedAtomForm.DefinedAtomForm;
import com.hotent.definedAtomForm.service.definedAtomForm.DefinedAtomFormService;
/**
 * 对象功能:子系统资源 控制器类 开发公司:广州宏天软件有限公司 开发人员:csx 创建时间:2011-12-05 17:00:54
 */
@Controller
@RequestMapping("/platform/system/resources/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class ResourcesFormController extends BaseFormController {
	@Resource
	private ResourcesService resourcesService;
	@Resource
	private RedirectionresourceService redirectionresourceService;
	
	@Resource
	private DefinedAtomFormService definedAtomFormService;
	private Logger logger = Logger.getLogger(this.getClass());
	
	

	/**
	 * 添加或更新子系统资源。
	 * 
	 * @param request
	 * @param response
	 * @param resources
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新子系统资源",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>子系统资源" +
					"【${SysAuditLinkService.getResourcesLink(Long.valueOf(resId))}】"
			)
	public void save(HttpServletRequest request, HttpServletResponse response,Resources resources, BindingResult bindResult) throws Exception {
		PrintWriter out=response.getWriter();
		ResultMessage resultMessage = validForm("resources", resources,bindResult, request);
		if (resultMessage.getResult() == ResultMessage.Fail) {
			writeResultMessage(response.getWriter(), resultMessage);
			return;
		}
		
		boolean isadd=resources.getResId() == null;
		
		String icon = resources.getIcon();

		icon = icon.replace(request.getContextPath(), "");
		resources.setIcon(icon);

		String defaultUrl = resources.getDefaultUrl();
		if (defaultUrl != null) {
			defaultUrl = defaultUrl.trim();
			if(defaultUrl.equals("")){
				defaultUrl=null;
			}
			resources.setDefaultUrl(defaultUrl);
		}

		String[] aryName = request.getParameterValues("name");
		String[] aryUrl = request.getParameterValues("url");

		if (resources.getResId() == null) {
			Integer rtn = resourcesService.isAliasExists(resources);
			if (rtn > 0) {
				writeResultMessage(response.getWriter(), "别名在系统中已存在!",ResultMessage.Fail);
				return;
			}
			try {
				long resId= resourcesService.addRes(resources, aryName, aryUrl);
				//删除缓存。
//				SecurityUtil.removeCacheBySystemId( resources.getSystemId());
				String result="{result:1,resId:"+resId+",operate:'add'}";
				out.print(result);
			}
			catch (Exception e) {
				writeResultMessage(response.getWriter(), "添加资源失败!",ResultMessage.Fail);
			}
		} 
		else {
			Integer rtn = resourcesService.isAliasExistsForUpd(resources);
			if (rtn > 0) {
				writeResultMessage(response.getWriter(), "别名在系统中已存在!",ResultMessage.Fail);
				return;
			}
			try {
				resourcesService.updRes(resources, aryName, aryUrl);
				//删除缓存。
//				SecurityUtil.removeCacheBySystemId( resources.getSystemId());
				
				String result="{result:1,operate:'edit'}";
				out.print(result);
			} catch (Exception e) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					resultMessage = new ResultMessage(ResultMessage.Fail,"更新资源失败:" + str);
					response.getWriter().print(resultMessage);
				} else {
					String message = ExceptionUtil.getExceptionMessage(e);
					resultMessage = new ResultMessage(ResultMessage.Fail, message);
					response.getWriter().print(resultMessage);
				}
			}
		}
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("resources", resources);
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
			SysAuditThreadLocalHolder.putParamerter("resId", resources.getResId().toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}
	/*@RequestMapping("save")
	@Action(description = "添加或更新子系统资源",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>子系统资源" +
					"【${SysAuditLinkService.getResourcesLink(Long.valueOf(resId))}】"
			)
	public void save(HttpServletRequest request, HttpServletResponse response,Resources resources, BindingResult bindResult,DefinedAtomForm definedAtomForm) throws Exception {
	logger.info("进来了！！！！！！！");
		PrintWriter out=response.getWriter();
		//logger.log(Level.INFO, "asdfjaskldgjalkgjld"); 
		ResultMessage resultMessage = validForm("resources", resources,bindResult, request);
		String resultMsg=null;		
		try{
			logger.info("save");
			
			if(definedAtomForm.getId()==null){
				//Long id=UniqueIdUtil.genId();
				String sid=request.getParameter("untilid");//主键
				Long id=Long.parseLong(sid);
				logger.info("主键id="+id);
				definedAtomForm.setId(id);
				
				String a=request.getParameter("defaultUrl2");
				logger.info(a);
				
				
				String b=request.getParameter("defaultUrl");
				int sizeb=b.length();
				String shortid1=b.substring(sizeb-14,sizeb);
				Long formkey=Long.parseLong(shortid1);
				
				logger.info(b);
				
				int sizea=a.length();
							
				String shortid=a.substring(sizea-14,sizea);//id
				Long sshortid=Long.parseLong(shortid);
				definedAtomForm.setSetId(id);
				definedAtomForm.setFormKey(formkey);
				logger.info("插入了"+formkey);
				
				
				
			     
				resultMsg=getText("添加","defined_atom_form");//插入到表中 formkey与id
				definedAtomFormService.add(definedAtomForm);
				logger.info("insert formkey and id to definedAtomForm");
				
			}else{
			    definedAtomFormService.update(definedAtomForm);
				resultMsg=getText("更新","defined_atom_form");
				logger.info("update formkey and id to definedAtomForm");
				
			}
			//writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
			//return;
		}
		if (resultMessage.getResult() == ResultMessage.Fail) {
			writeResultMessage(response.getWriter(), resultMessage);
			return;
		}
		
		boolean isadd=resources.getResId() == null;
		
		String icon = resources.getIcon();

		icon = icon.replace(request.getContextPath(), "");
		resources.setIcon(icon);

		//String defaultUrl = resources.getDefaultUrl();
		String defaultUrl = request.getParameter("defaultUrl2");
		if (defaultUrl != null) {
			defaultUrl = defaultUrl.trim();
			if(defaultUrl.equals("")){
				defaultUrl=null;
			}
			resources.setDefaultUrl(defaultUrl);
		}

		String[] aryName = request.getParameterValues("name");

		String[] aryUrl = request.getParameterValues("url");

		
		if (resources.getResId() == null) {
			Integer rtn = resourcesService.isAliasExists(resources);
			if (rtn > 0) {
				writeResultMessage(response.getWriter(), "别名在系统中已存在!",ResultMessage.Fail);
				return;
			}
			if(resources.getRedirectionno()!=null){
				String redirectionno =resources.getRedirectionno();
				String url =resources.getDefaultUrl();
				String alias =resources.getAlias();
				String resname = resources.getResName();
				System.out.println(url+"&&&&");
			redirectionresourceService.addredirection(redirectionno,url,alias,resname);
				
			}
			try {
				long resId= resourcesService.addRes(resources, aryName, aryUrl);
				//删除缓存。
//				SecurityUtil.removeCacheBySystemId( resources.getSystemId());
				String result="{result:1,resId:"+resId+",operate:'add'}";
				out.print(result);
			}
			catch (Exception e) {
				writeResultMessage(response.getWriter(), "添加资源失败!",ResultMessage.Fail);
			}
		} 
		else {
			Integer rtn = resourcesService.isAliasExistsForUpd(resources);
			if (rtn > 0) {
				writeResultMessage(response.getWriter(), "别名在系统中已存在!",ResultMessage.Fail);
				return;
			}
			try {
				resourcesService.updRes(resources, aryName, aryUrl);
				//删除缓存。
//				SecurityUtil.removeCacheBySystemId( resources.getSystemId());
				
				String result="{result:1,operate:'edit'}";
				out.print(result);
			} catch (Exception e) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					resultMessage = new ResultMessage(ResultMessage.Fail,"更新资源失败:" + str);
					response.getWriter().print(resultMessage);
				} else {
					String message = ExceptionUtil.getExceptionMessage(e);
					resultMessage = new ResultMessage(ResultMessage.Fail, message);
					response.getWriter().print(resultMessage);
				}
			}
		}
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("resources", resources);
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
			SysAuditThreadLocalHolder.putParamerter("resId", resources.getResId().toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		
		}
		
	     
	}*/

	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * 
	 * @param resId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute
	protected Resources getFormObject(@RequestParam("resId") Long resId,
			Model model) throws Exception {
		logger.debug("enter Resources getFormObject here....");
		Resources resources = null;
		if (resId != null) {
			resources = resourcesService.getById(resId);
		} 
		else {
			resources = new Resources();
		}
		return resources;
	}

}
