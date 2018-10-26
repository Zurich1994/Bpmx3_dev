package com.hotent.platform.controller.mail;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.mail.OutMailUserSeting;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.mail.OutMailUserSetingService;

/**
 * 对象功能:邮箱设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-09 13:43:51
 */
@Controller
@RequestMapping("/platform/mail/outMailUserSeting/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class OutMailUserSetingController extends BaseController
{
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	
	/**
	 * 取得当前用户的外部邮箱分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看外部邮箱分页列表",detail="查看外部邮箱分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter queryFilter=new QueryFilter(request, "outMailUserSetingItem");
		long userId=ContextUtil.getCurrentUserId();
		queryFilter.getFilters().put("userId", userId);
		List<OutMailUserSeting> list=outMailUserSetingService.getAllByUserId(queryFilter);
		ModelAndView mv=this.getAutoView().addObject("outMailUserSetingList",list);
		return mv;
	}
	
	/**
	 * 删除邮箱
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除外部邮箱",execOrder=ActionExecOrder.BEFORE,
			detail="删除外部邮箱:" +
			"<#list StringUtils.split(id,\",\") as item>" +
			"<#assign entity=outMailUserSetingService.getById(Long.valueOf(item))/>" +
			"【${entity.userName}】,邮箱地址是：【${entity.mailAddress}】"+
			"</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			outMailUserSetingService.delAllByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除邮箱成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 编辑邮箱
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="添加或编辑邮箱",detail="<#if isAdd>添加邮箱<#else>编辑邮箱" +
			"<#assign entity=outMailUserSetingService.getById(Long.valueOf(id))/>" +
			"【${entity.userName}】,邮箱地址是：【${entity.mailAddress}】"+
			"</#if>")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		OutMailUserSeting outMailUserSeting=null;
		boolean isadd=true;
		if(id!=0){
			 outMailUserSeting= outMailUserSetingService.getById(id);
			 outMailUserSeting.setMailPass("");
			 isadd=false;
		}else{
			outMailUserSeting=new OutMailUserSeting();
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		return getAutoView().addObject("outMailUserSeting",outMailUserSeting).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得邮箱明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看外部邮箱明细",detail="查看外部邮箱明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request,"canReturn",0);
		OutMailUserSeting outMailUserSeting = outMailUserSetingService.getById(id);		
		return getAutoView().addObject("outMailUserSeting", outMailUserSeting).addObject("canReturn", canReturn);
	}
	
	/**
	 * 测试接收/发送 服务器连接情况
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("test")
	@Action(description="测试邮箱连接情况",detail="测试邮箱连接情况")
	public void test(HttpServletRequest request,HttpServletResponse response, OutMailUserSeting outMailUserSeting)throws Exception
	{
		long id=RequestUtil.getLong(request, "id");
		Long outMailUserSetingId = outMailUserSeting.getId();
		String password = outMailUserSeting.getMailPass();
		int isEdit=RequestUtil.getInt(request, "isEdit", 0);
		ResultMessage resultMessage=null;
		if(isEdit==0){
			outMailUserSeting=outMailUserSetingService.getById(id);
		} else if(StringUtil.isNotEmpty(password)){// 如果密码不为空，则使用密码进行连接测试
			outMailUserSeting.setMailPass(EncryptUtil.encrypt(password));
		} else if(BeanUtils.isNotEmpty(outMailUserSetingId)){
			outMailUserSeting=outMailUserSetingService.getById(outMailUserSetingId);
		}
		try{
			outMailUserSetingService.testConnection(outMailUserSeting);
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "测试通过!"));
		}catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"测试通过失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
//				String message = ExceptionUtil.getExceptionMessage(e);
				String message = "测试连接失败，请检查邮箱设置是否正确！";
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		} 
	}
	
	/**
	 * 更改默认邮箱设置;
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("setDefault")
	@Action(description="设置默认邮箱",detail="设置" +
			"<#assign entity=outMailUserSetingService.getById(Long.valueOf(id))/>" +
			"【${entity.userName}】,邮箱地址是：【${entity.mailAddress}】"+
			"为默认邮箱 <#if>成功<#else>失败</#if>")
	public void setupDefault(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		long id=RequestUtil.getLong(request, "id");
		OutMailUserSeting outMailUserSeting=outMailUserSetingService.getById(id);
		outMailUserSeting.setIsDefault(1);
		boolean issuccess=true;
		try{
			outMailUserSetingService.setDefault(outMailUserSeting);
			message=new ResultMessage(ResultMessage.Success, "设置成功!");
		}catch(Exception ex){
			issuccess=false;
			message=new ResultMessage(ResultMessage.Fail, "设置失败:" + ex.getMessage());
		}
		SysAuditThreadLocalHolder.putParamerter("isSuccess", issuccess);
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
}
