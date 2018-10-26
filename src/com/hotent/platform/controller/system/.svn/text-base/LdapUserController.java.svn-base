package com.hotent.platform.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.ldap.model.LdapUser;
import com.hotent.core.ldap.until.UserHelper;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.ldap.LdapUserService;
import com.hotent.platform.service.system.SysUserService;
/**
 * 对象功能:LdapUser 控制器类
 * 开发公司:宏天
 * 开发人员:Raise
 * 创建时间:2012-09-21 11:37:20
 */
@Controller
@RequestMapping("/platform/system/ldapUser/")
public class LdapUserController extends BaseController
{
	
	@Resource
	private LdapUserService ldapUserService;

	@Resource 
	private SysUserService  sysUserService;
	/**
	 * 取得LdapUser分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看LdapUser分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Map<String,Object> queryMap = RequestUtil.getQueryMap(request);
		List<LdapUser> list=ldapUserService.getAll(queryMap);
		List<SysUser> totalDbUsers = sysUserService.getAll();
		
		List<Map<String, Object>> userMaps=new ArrayList<Map<String,Object>>();
		for(LdapUser ldapUser:list){
			boolean isContain = UserHelper.isContains(ldapUser, totalDbUsers);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("user",ldapUser);
			map.put("sync", isContain);
			userMaps.add(map);
		}
		
		ModelAndView mv=this.getAutoView().addObject("ldapUserMapList",userMaps);
		return mv;
	}
	
	/**
	 * 删除LdapUser
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除LdapUser")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			message=new ResultMessage(ResultMessage.Success, "删除LdapUser成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑LdapUser
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑LdapUser")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		LdapUser ldapUser=null;//ldapUserService.getById(id);
		
		return getAutoView().addObject("ldapUser",ldapUser).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得LdapUser明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看LdapUser明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String path=RequestUtil.getString(request,"dn");
		List<LdapUser> ldapUsers =ldapUserService.get(path);
		return getAutoView().addObject("ldapUser", ldapUsers.get(0));
	}
}

