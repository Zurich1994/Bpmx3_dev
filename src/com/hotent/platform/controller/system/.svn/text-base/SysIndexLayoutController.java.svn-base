package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysIndexLayout;
import com.hotent.platform.service.system.SysIndexLayoutService;
/**
 *<pre>
 * 对象功能:首页布局 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 17:30:22
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysIndexLayout/")
public class SysIndexLayoutController extends BaseController
{
	@Resource
	private SysIndexLayoutService sysIndexLayoutService;
	
	
	/**
	 * 添加或更新首页布局。
	 * @param request
	 * @param response
	 * @param sysIndexLayout 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新首页布局")
	public void save(HttpServletRequest request, HttpServletResponse response,SysIndexLayout sysIndexLayout) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysIndexLayout.getId()==null||sysIndexLayout.getId()==0){
				sysIndexLayout.setId(UniqueIdUtil.genId());
				sysIndexLayoutService.add(sysIndexLayout);
				resultMsg=getText("添加","首页布局");
			}else{
			    sysIndexLayoutService.update(sysIndexLayout);
				resultMsg=getText("更新","首页布局");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得首页布局分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看首页布局分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysIndexLayout> list=sysIndexLayoutService.getAll(new QueryFilter(request,"sysIndexLayoutItem"));
		ModelAndView mv=this.getAutoView().addObject("sysIndexLayoutList",list);
		
		return mv;
	}
	
	/**
	 * 删除首页布局
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除首页布局")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysIndexLayoutService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除首页布局成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑首页布局
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑首页布局")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		SysIndexLayout sysIndexLayout=sysIndexLayoutService.getById(id);
		
		return getAutoView().addObject("sysIndexLayout",sysIndexLayout)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得首页布局明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看首页布局明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysIndexLayout sysIndexLayout = sysIndexLayoutService.getById(id);	
		return getAutoView().addObject("sysIndexLayout", sysIndexLayout);
	}
	
}
