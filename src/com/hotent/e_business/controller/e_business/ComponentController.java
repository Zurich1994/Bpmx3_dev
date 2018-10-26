
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

import com.hotent.e_business.model.e_business.Component;
import com.hotent.e_business.service.e_business.ComponentService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:用户定制 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/component/")
public class ComponentController extends BaseController
{
	@Resource
	private ComponentService componentService;
	/**
	 * 添加或更新用户定制。
	 * @param request
	 * @param response
	 * @param component 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新用户定制")
	public void save(HttpServletRequest request, HttpServletResponse response,Component component) throws Exception
	{
		String resultMsg=null;		
		try{
			if(component.getId()==null){
				Long id=UniqueIdUtil.genId();
				component.setId(id);
				componentService.add(component);
				resultMsg=getText("添加","用户定制");
			}else{
			    componentService.update(component);
				resultMsg=getText("更新","用户定制");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得用户定制分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看用户定制分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Component> list=componentService.getAll(new QueryFilter(request,"componentItem"));
		ModelAndView mv=this.getAutoView().addObject("componentList",list);
		
		return mv;
	}
	
	/**
	 * 删除用户定制
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除用户定制")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			componentService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除用户定制成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑用户定制
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑用户定制")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Component component=componentService.getById(id);
		
		return getAutoView().addObject("component",component)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得用户定制明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看用户定制明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Component component=componentService.getById(id);
		System.out.println("getid="+id);
		return getAutoView().addObject("component", component);
	}
	@RequestMapping("Modify")
	@Action(description="查看用户定制明细")
	public ModelAndView Modify(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long PRODUCTID=RequestUtil.getLong(request,"id");
		System.out.println("PRODUCTID"+PRODUCTID);
		
		String EMAIL=request.getParameter("EMAIL"); 
		return getAutoView().addObject("PRODUCTID",PRODUCTID);
		
	}
}
