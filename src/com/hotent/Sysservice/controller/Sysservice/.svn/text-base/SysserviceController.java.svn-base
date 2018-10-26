

package com.hotent.Sysservice.controller.Sysservice;

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
import com.hotent.core.page.PageList;
import com.hotent.platform.model.system.SysUser;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.Sysservice.model.Sysservice.Sysservice;
import com.hotent.Sysservice.service.Sysservice.SysserviceService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:子系统服务表 控制器类
 */
@Controller
@RequestMapping("/Sysservice/Sysservice/sysservice/")
public class SysserviceController extends BaseController
{
	@Resource
	private SysserviceService sysserviceService;
	
	/**
	 * 添加或更新子系统服务表。
	 * @param request
	 * @param response
	 * @param sysservice 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新子系统服务表")
	public void save(HttpServletRequest request, HttpServletResponse response,Sysservice sysservice) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysservice.getId()==null){
				sysserviceService.save(sysservice);
				resultMsg=getText("添加","子系统服务表");
			}else{
			    sysserviceService.save(sysservice);
				resultMsg=getText("更新","子系统服务表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得子系统服务表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看子系统服务表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Sysservice> list=sysserviceService.getAll(new QueryFilter(request,"sysserviceItem"));
		ModelAndView mv=this.getAutoView().addObject("sysserviceList",list);
		return mv;
	}
	
	/**
	 * 删除子系统服务表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除子系统服务表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			sysserviceService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除子系统服务表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑子系统服务表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑子系统服务表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Sysservice sysservice=sysserviceService.getById(id);
		
		return getAutoView().addObject("sysservice",sysservice)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得子系统服务表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看子系统服务表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Sysservice sysservice=sysserviceService.getById(id);
		return getAutoView().addObject("sysservice", sysservice);
	}
	@RequestMapping("listone")
	@Action(description="查看子系统服务表分页列表")
	public ModelAndView listone(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Sysservice> list=sysserviceService.getAll(new QueryFilter(request,"sysserviceItem"));
		ModelAndView mv=this.getAutoView().addObject("sysserviceList",list);
		return mv;
	}
	@RequestMapping("box")
	@Action(description="业务逻辑校验跳转jsp")
	public ModelAndView box(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		    /*System.out.println("进入box方法 ");	
			String[] ids =RequestUtil.getStringAryByStr(request, "ids");
			String ids1="";
			for(int i=0;i<ids.length;i++)
				ids1=ids1+ids[i]+",";
				*/
			ModelAndView mv=this.getAutoView()/*.addObject("ids",ids1)*/;		
			return mv;	

	}
}