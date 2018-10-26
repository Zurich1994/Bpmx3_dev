
package com.hotent.redirectionresource.controller.redirectionresource;

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

import com.hotent.redirectionresource.model.redirectionresource.Redirectionresource;
import com.hotent.redirectionresource.service.redirectionresource.RedirectionresourceService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:redirectionresource 控制器类
 */
@Controller
@RequestMapping("/redirectionresource/redirectionresource/redirectionresource/")
public class RedirectionresourceController extends BaseController
{
	@Resource
	private RedirectionresourceService redirectionresourceService;
	/**
	 * 添加或更新redirectionresource。
	 * @param request
	 * @param response
	 * @param redirectionresource 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新redirectionresource")
	public void save(HttpServletRequest request, HttpServletResponse response,Redirectionresource redirectionresource) throws Exception
	{
		String resultMsg=null;		
		try{
			if(redirectionresource.getRedirectionno()==null){
				Long redirectionno=UniqueIdUtil.genId();
				redirectionresource.setRedirectionno(redirectionno);
				redirectionresourceService.add(redirectionresource);
				resultMsg=getText("添加","redirectionresource");
			}else{
			    redirectionresourceService.update(redirectionresource);
				resultMsg=getText("更新","redirectionresource");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得redirectionresource分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看redirectionresource分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Redirectionresource> list=redirectionresourceService.getAll(new QueryFilter(request,"redirectionresourceItem"));
		ModelAndView mv=this.getAutoView().addObject("redirectionresourceList",list);
		
		return mv;
	}
	
	/**
	 * 删除redirectionresource
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除redirectionresource")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			redirectionresourceService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除redirectionresource成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑redirectionresource
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑redirectionresource")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long redirectionno=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Redirectionresource redirectionresource=redirectionresourceService.getById(redirectionno);
		
		return getAutoView().addObject("redirectionresource",redirectionresource)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得redirectionresource明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看redirectionresource明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long redirectionno=RequestUtil.getLong(request,"id");
		Redirectionresource redirectionresource=redirectionresourceService.getById(redirectionno);
		return getAutoView().addObject("redirectionresource", redirectionresource);
	}
	
}
