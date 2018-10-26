

package com.hotent.sysinfomation.controller.sysinfomation;

import java.util.HashMap;

import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.WSystemInformation;

import java.util.ArrayList;
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
import com.hotent.core.page.PageList;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SubSystemService;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.sysinfomation.model.sysinfomation.Sysinfomation;
import com.hotent.sysinfomation.service.sysinfomation.SysinfomationService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:子系统内部信息统计 控制器类
 */
@Controller
@RequestMapping("/sysinfomation/sysinfomation/sysinfomation/")
public class SysinfomationController extends BaseController
{
	@Resource
	private SysinfomationService sysinfomationService;
	@Resource
	private SubSystemService subSystemService;
	/**
	 * 添加或更新子系统内部信息统计。
	 * @param request
	 * @param response
	 * @param sysinfomation 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新子系统内部信息统计")
	public void save(HttpServletRequest request, HttpServletResponse response,Sysinfomation sysinfomation) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysinfomation.getId()==null){
				sysinfomationService.save(sysinfomation);
				resultMsg=getText("添加","子系统内部信息统计");
			}else{
			    sysinfomationService.save(sysinfomation);
				resultMsg=getText("更新","子系统内部信息统计");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得子系统内部信息统计分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看子系统内部信息统计分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Sysinfomation> list=sysinfomationService.getAll(new QueryFilter(request,"sysinfomationItem"));
		ModelAndView mv=this.getAutoView().addObject("sysinfomationList",list);
		return mv;
	}
	
	/**
	 * 删除子系统内部信息统计
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除子系统内部信息统计")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			sysinfomationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除子系统内部信息统计成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑子系统内部信息统计
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑子系统内部信息统计")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Sysinfomation sysinfomation=sysinfomationService.getById(id);
		
		return getAutoView().addObject("sysinfomation",sysinfomation)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得子系统内部信息统计明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看子系统内部信息统计明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Sysinfomation sysinfomation=sysinfomationService.getById(id);
		return getAutoView().addObject("sysinfomation", sysinfomation);
	}	
	/**
	 * 信息 规范化程度 
	 */
	@RequestMapping("information")
	@Action(description="跳到另个JSP")
	public ModelAndView information(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
      
		ModelAndView mv=this.getAutoView();
		return mv;
	
	}
	@RequestMapping("knowledge")  //知识结构化程度
	@Action(description="跳到另个JSP")
	public ModelAndView knowledge(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	
	@RequestMapping("source")   //业务资源开放程度
	@Action(description="跳到另个JSP")
	public ModelAndView source(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("business")  //业务架构开放程度
	@Action(description="跳到另个JSP")
	public ModelAndView business(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("degree")   //作业集中度
	@Action(description="跳到另个JSP")
	public ModelAndView degree(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	
	
	
	
	@RequestMapping("plat")   //Logo链接的相关信息
	@Action(description="跳到另个JSP")
	public ModelAndView platformInfo(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		System.out.println("进入plat");	
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		SubSystem subSystem=null;
	
		if(id!=0){
			subSystem= subSystemService.getById(id);
			
		}
		
		System.out.println("完成plat" +subSystem);	
		return getAutoView().addObject("subSystem",subSystem).addObject("returnUrl", returnUrl);
	}  //结束
	
}