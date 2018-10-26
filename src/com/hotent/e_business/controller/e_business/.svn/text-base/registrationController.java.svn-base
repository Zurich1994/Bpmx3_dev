
package com.hotent.e_business.controller.e_business;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.e_business.model.e_business.registration;
import com.hotent.e_business.service.e_business.registrationService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:用户信息表 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/registration/")
public class registrationController extends BaseController
{
	@Resource
	private registrationService registrationService;
	/**
	 * 添加或更新用户信息表。
	 * @param request
	 * @param response
	 * @param registration 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新用户信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,registration registration) throws Exception
	{
		String resultMsg=null;		
		try{
			if(registration.getId()==null){
				Long id=UniqueIdUtil.genId();
				registration.setId(id);
				
				resultMsg=getText("添加 ","登陆成功");
				String EMAIL=RequestUtil.getString(request,"EMAIL");
				//String PASSWORD=RequestUtil.getString(request,"PASSWORD");
				registration registration1;
				registration1=registrationService.getByEMAIL(EMAIL);
				if(registration1 == null)
					{registrationService.add(registration);
					writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);}
				else
					writeResultMessage(response.getWriter(),getText("注册失败:已注册","注册失败:已注册"),ResultMessage.Fail);
									
			}else{
			    registrationService.update(registration);
				resultMsg=getText("注册","用户信息表");
			}
			
			
			//writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	@RequestMapping("success")
	@Action(description="注册成功")
	public ModelAndView seccess(HttpServletRequest request, HttpServletResponse response) throws Exception
	{ModelAndView mv=null;
	return mv;
	}
	@RequestMapping("error")
	@Action(description="添加或更新用户信息表")
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response) throws Exception
	{ModelAndView mv=null;
	return mv;
	}
	/**
	 * 取得用户信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看用户信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<registration> list=registrationService.getAll(new QueryFilter(request,"registrationItem"));
		ModelAndView mv=this.getAutoView().addObject("registrationList",list);
		
		return mv;
	}
	
	/**
	 * 删除用户信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除用户信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			registrationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除用户信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑用户信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑用户信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		registration registration=registrationService.getById(id);
		
		return getAutoView().addObject("registration",registration)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得用户信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看用户信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		registration registration=registrationService.getById(id);
		return getAutoView().addObject("registration", registration);
	}
	
}
