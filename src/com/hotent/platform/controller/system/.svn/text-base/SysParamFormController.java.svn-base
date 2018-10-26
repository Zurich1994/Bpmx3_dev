package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.hotent.core.annotion.Action;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysParam;
import com.hotent.platform.service.system.SysParamService;

/**
 * 对象功能:系统参数属性 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-23 17:43:35
 */
@Controller
@RequestMapping("/platform/system/sysParam/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class SysParamFormController extends BaseFormController
{
	@Resource
	private SysParamService sysParamService;
	
	/**
	 * 添加或更新系统参数属性。
	 * @param request
	 * @param response
	 * @param sysParam 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新系统参数属性",
			detail="<#if isAdd>添加<#else>更新</#if>" +
					"<#if isUser>人员<#else>组织</#if>的系统参数：" +
					"【${SysAuditLinkService.getSysParamLink(Long.valueOf(id))}】")
	public void save(HttpServletRequest request, HttpServletResponse response, SysParam sysParam,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("sysParam", sysParam, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名")

		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		boolean isadd=true;
		if(sysParam.getParamId()==null){
			if(sysParamService.getByParamKey(sysParam.getParamKey())!=null){//参数Key已被使用
				writeResultMessage(response.getWriter(), "参数Key:"+sysParam.getParamKey()+",已被使用", ResultMessage.Fail);
				return;
			}
			sysParam.setParamId(UniqueIdUtil.genId());
			sysParam = setSourceKey(request,sysParam);
			sysParamService.add(sysParam);
			resultMsg="添加系统参数属性成功";
		}else {
			
			sysParam = setSourceKey(request,sysParam);
			sysParamService.update(sysParam);
			resultMsg="更新系统参数属性成功";
			isadd=false;
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		SysAuditThreadLocalHolder.putParamerter("id", sysParam.getParamId().toString());
		SysAuditThreadLocalHolder.putParamerter("isUser", sysParam.getEffect()==SysParam.EFFECT_USER);
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	private SysParam setSourceKey(HttpServletRequest request,SysParam sysParam) {
		String[] keys = request.getParameterValues("key");
		String[] values=request.getParameterValues("value");
		if(sysParam.getSourceType().equals("dict")){
			return sysParam;
		}else if(sysParam.getSourceType().equals("input")){
		    sysParam.setSourceKey("");
		}else{
			sysParam.setSourceKey((getJson(keys, values)).toString());
		}
		return sysParam;
	}

	private JSONObject getJson(String[] keys, String[] values) {
		JSONObject json = new JSONObject();
		for(int i= 0 ;i<keys.length;i++){
			json.put(keys[i], values[i]);
		}
		return json;
	}

	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param paramId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected SysParam getFormObject(@RequestParam("paramId") Long paramId,Model model) throws Exception {
		logger.debug("enter SysParam getFormObject here....");
		SysParam sysParam=null;
		if(paramId!=null){
			sysParam=sysParamService.getById(paramId);
		}else{
			sysParam= new SysParam();
		}
		return sysParam;
    }

}
