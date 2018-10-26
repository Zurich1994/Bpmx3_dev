

package com.hotent.Serviceproducts.controller.Serviceproducts;

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

import com.hotent.Atomicoperate.model.Atomicoperate.Atomicoperate;
import com.hotent.Serviceproducts.model.Serviceproducts.Serviceproducts;
import com.hotent.Serviceproducts.service.Serviceproducts.ServiceproductsService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:服务产品表 控制器类
 */
@Controller
@RequestMapping("/Serviceproducts/Serviceproducts/serviceproducts/")
public class ServiceproductsController extends BaseController
{
	@Resource
	private ServiceproductsService serviceproductsService;
	
	/**
	 * 添加或更新服务产品表。
	 * @param request
	 * @param response
	 * @param serviceproducts 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新服务产品表")
	public void save(HttpServletRequest request, HttpServletResponse response,Serviceproducts serviceproducts) throws Exception
	{
		String resultMsg=null;		
		try{
			if(serviceproducts.getId()==null){
				serviceproductsService.save(serviceproducts);
				resultMsg=getText("添加","服务产品表");
			}else{
			    serviceproductsService.save(serviceproducts);
				resultMsg=getText("更新","服务产品表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得服务产品表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看服务产品表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long status= Long.parseLong(request.getParameter("status"));
		Long serviceId= Long.parseLong(request.getParameter("id"));
		ModelAndView mv=null;
		if(status==1)
		{
			List<Serviceproducts> list=serviceproductsService.getByserviceId(serviceId);
			mv=this.getAutoView().addObject("serviceproductsList",list);
		}
		else if(status==2){
			List<Serviceproducts> list=serviceproductsService.getAll(new QueryFilter(request,"serviceproductsItem"));
			mv=this.getAutoView().addObject("serviceproductsList",list);
		}
		return mv;
	}
	
	/**
	 * 删除服务产品表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除服务产品表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			serviceproductsService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除服务产品表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑服务产品表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑服务产品表")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Serviceproducts serviceproducts=serviceproductsService.getById(id);
		
		return getAutoView().addObject("Serviceproducts",serviceproducts)
							 .addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得服务产品表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看服务产品表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Serviceproducts serviceproducts=serviceproductsService.getById(id);
		return getAutoView().addObject("Serviceproducts", serviceproducts);
	}
	
	
}