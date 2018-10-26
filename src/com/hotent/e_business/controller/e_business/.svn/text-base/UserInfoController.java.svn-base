
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

import com.hotent.e_business.model.e_business.UserInfo;
import com.hotent.e_business.service.e_business.UserInfoService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:用户信息表 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/userInfo/")
public class UserInfoController extends BaseController
{
	@Resource
	private UserInfoService userInfoService;
	/**
	 * 添加或更新用户信息表。
	 * @param request
	 * @param response
	 * @param userInfo 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新用户信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,UserInfo userInfo) throws Exception
	{
		String resultMsg=null;	
		
		try{
			if(userInfo.getId()==null){
				Long id=UniqueIdUtil.genId();
				System.out.print(id);
				userInfo.setId(id);
				userInfoService.add(userInfo);
				resultMsg=getText("登陆","用户信息表");
			}else{
			    userInfoService.update(userInfo);
				resultMsg=getText("更新","用户信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	@RequestMapping("match")
	@Action(description="添加或更新用户信息表")
	public void match(HttpServletRequest request, HttpServletResponse response,UserInfo userInfo) throws Exception
	{//userInfoService.match(userInfo);
		String EMAIL=RequestUtil.getString(request,"EMAIL");
		String PASSWORD=RequestUtil.getString(request,"PASSWORD");
		 userInfo=userInfoService.getByEMAIL(EMAIL,PASSWORD);
		
		//Long id=UniqueIdUtil.genId();
		 // System.out.println("123456789");
		  //userInfo.getId(email);
//		String name=userInfo.getEMAIL();
//		 String PASSWORD=RequestUtil.getString(request,"PASSWORD");
//		System.out.println(EMAIL);
		//String password=userInfo.getPASSWORD();
		//System.out.print(PASSWORD);
		//userInfo=userInfoService.getByEMAIL(EMAIL);
//		ModelAndView mv=null;
		//Long id=UniqueIdUtil.genId();
	   // String EMAIL=request.getParameter("EMAIL");
//	    System.out.println(EMAIL);
	    
	   // String PASSWORD=request.getParameter("PASSWORD");
	   //System.out.println(PASSWORD);
	
	    
		///System.out.println(PASSWORD);
		//String returnUrl=RequestUtil.getPrePage(request);
		//userInfo=userInfoService.getById(id);
//		String email11=userInfo.getEMAIL();
		///System.out.println(PASSWORD);
		
//		String password11=userInfo.getPASSWORD();
		//System.out.println(password11);
//		if(PASSWORD.equals(password11))
		//{  System.out.println("1111");
//			mv=this.getAutoView().addObject("userInfo", userInfo);
		
		//}
		
/*		//else
		{//String resultMsg="登陆失败";
		//System.out.println("45465");
		//mv.setViewName("wrong");
	//	}
		return mv;
		}*/
		if(userInfo != null)
			{writeResultMessage(response.getWriter(),getText("登陆成功","登陆成功"),ResultMessage.Success);
//			request.getSession().setAttribute("EMAIL", EMAIL);}
		    request.getAttribute("EMAIL");
		     
		     System.out.println("URL传到USERcon"+EMAIL);}
		 	
		else
			writeResultMessage(response.getWriter(),getText("登陆失败","登陆失败"),ResultMessage.Fail);
							
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
		List<UserInfo> list=userInfoService.getAll(new QueryFilter(request,"userInfoItem"));
		ModelAndView mv=this.getAutoView().addObject("userInfoList",list);
		
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
			userInfoService.delByIds(lAryId);
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
		UserInfo userInfo=userInfoService.getById(id);
		
		return getAutoView().addObject("userInfo",userInfo)
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
		UserInfo userInfo=userInfoService.getById(id);
		return getAutoView().addObject("userInfo", userInfo);
	}
	
}
