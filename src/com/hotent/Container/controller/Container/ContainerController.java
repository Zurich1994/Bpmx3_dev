

package com.hotent.Container.controller.Container;

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

import com.hotent.Container.model.Container.Container;
import com.hotent.Container.service.Container.ContainerService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:容器表 控制器类
 */
@Controller
@RequestMapping("/Container/Container/container/")
public class ContainerController extends BaseController
{
	@Resource
	private ContainerService containerService;
	
	/**
	 * 添加或更新容器表。
	 * @param request
	 * @param response
	 * @param container 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新容器表")
	public void save(HttpServletRequest request, HttpServletResponse response,Container container) throws Exception
	{
		String resultMsg=null;		
		try{
			if(container.getId()==null){
				containerService.save(container);
				resultMsg=getText("添加","容器表");
			}else{
			    containerService.save(container);
				resultMsg=getText("更新","容器表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得容器表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看容器表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Container> list=containerService.getAll(new QueryFilter(request,"containerItem"));
		ModelAndView mv=this.getAutoView().addObject("containerList",list);
		return mv;
	}
	
	/**
	 * 删除容器表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除容器表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			containerService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除容器表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑容器表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑容器表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Container container=containerService.getById(id);
		
		return getAutoView().addObject("container",container)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得容器表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看容器表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Container container=containerService.getById(id);
		return getAutoView().addObject("container", container);
	}

}