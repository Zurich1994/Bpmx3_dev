
package com.hotent.banking.controller.banking;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.banking.model.banking.UserTab;
import com.hotent.banking.service.banking.UserTabService;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:用户信息表 控制器类
 */
@Controller
@RequestMapping("/banking/banking/userTab/")
public class UserTabController extends BaseController
{
	private String succeedUrl="userTabCheck.ht";
	@Resource
	private UserTabService userTabService;

	/**
	 * 添加或更新用户信息表。
	 * @param request
	 * @param response
	 * @param userTab 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新用户信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,UserTab userTab) throws Exception
	{
     String resultMsg=null;		
 //   String name=userTab.getUSERID();
 //    ModelAndView mv=null;

 //    String password=userTab.getPASSWORD();
		try{
			if(userTab.getId()==null){
				System.out.println("1");
				Long id=UniqueIdUtil.genId();			   
//				id= Long.valueOf(name);			    
//				UserTab userTab1=userTabService.getById(id);

//				String password1=userTab1.getPASSWORD();
//				System.out.println(name+"+"+password+"+"+password1);
	       			
				userTab.setId(id);
				userTabService.add(userTab);
				resultMsg=getText("请确认","用户信息表");
//				this.getAutoView().setViewName("/UserTab/UserTab/userTabSave.ht");
//				ModelAndView _mv = new ModelAndView("/UserTab/UserTab/userTabSave.ht");
//				mv=this.getAutoView().addObject("userTab",userTab);
//				mv = _mv.addObject("userTab",userTab);
				
			}else{
			    userTabService.update(userTab);
				resultMsg=getText("请确认","用户信息表");
				
			
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}

		
//		return mv;
//		request.getRequestDispatcher("/UserTab/UserTab/userTabSave.ht").forward(request, response);
//		response.sendRedirect("/UserTab/UserTab/userTabSave.ht");
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
		List<UserTab> list=userTabService.getAll(new QueryFilter(request,"userTabItem"));
		ModelAndView mv=this.getAutoView().addObject("userTabList",list);
		
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
			userTabService.delByIds(lAryId);
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
	@Action(description="跳转界面")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		
		return getAutoView();
		
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
		UserTab userTab=userTabService.getById(id);
		return getAutoView().addObject("userTab", userTab);
	}
	/**
	 * 取得用户信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	@Action(description="按客户号 或姓氏")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		System.out.println("1");
        Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		UserTab userTab=userTabService.getById(id);
		return getAutoView().addObject("userTab",userTab);
	}
	
	
	@RequestMapping("check")
	@Action(description="check")
	public ModelAndView check(HttpServletRequest request, HttpServletResponse response) throws Exception
	{ /*  System.out.println("3");
		Long name=RequestUtil.getLong(request,"name");
		String password=RequestUtil.getString(request,"password");
		System.out.println(name+"+"+password);
		ModelAndView mv=null;
		

		UserTab userTab=userTabService.getById(name);
		String pass=userTab.getPASSWORD();
		System.out.println(pass);

		if(password.equals(pass ))
		{ 
			System.out.println("4");
			mv=this.getAutoView().addObject("userTab",userTab);
		
		}
		else
		{
			  System.out.println("6");
			  mv.setViewName("passwrong");
		}
		
		  return  mv;*/
	
		Long name=RequestUtil.getLong(request,"name");
		String password=RequestUtil.getString(request,"password");
		System.out.println(name+"+"+password);
		ModelAndView mv=null;
		
		UserTab userTab=userTabService.getById(name);
		String pass=userTab.getPASSWORD();
		System.out.println(pass);
		
              if(password.equals(pass))
      		{ 
      			System.out.println("4");
      			mv=this.getAutoView().addObject("userTab",userTab);
      		
      		}
      		else
      		{
      			  System.out.println("6");
      			  mv.setViewName("passwrong");
      		}
      		
      		  return  mv;
 }
	@RequestMapping("yanzheng")
	@Action(description="添加或更新用户信息表")
	public void yanzheng(HttpServletRequest request, HttpServletResponse response,UserTab userTab) throws Exception 
	{
    
	
		
		String name=userTab.getUSERID();
		Long id= Long.valueOf(name);
        
		String password=userTab.getPASSWORD();
		
		System.out.println(id+"+"+name+"+"+password);
		
		UserTab userTab1=userTabService.getById(id);
	
	
		String password1=userTab1.getPASSWORD();
		
		System.out.println(password1);
		
		if(password.equals(password1))
		{			
			String  resultMsg="1";  
		    writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);		
  		}
		else{
			String  resultMsg="2";  
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}
	
	

	}
}
