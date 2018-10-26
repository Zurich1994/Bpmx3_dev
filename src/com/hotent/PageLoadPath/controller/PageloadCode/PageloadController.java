
package com.hotent.PageLoadPath.controller.PageloadCode;

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

import com.hotent.PageLoadPath.model.PageloadCode.Pageload;
import com.hotent.PageLoadPath.service.PageloadCode.PageloadService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:页面加载 控制器类
 */
@Controller
@RequestMapping("/PageLoadPath/PageloadCode/pageload/")
public class PageloadController extends BaseController
{
	@Resource
	private PageloadService pageloadService;
	/**
	 * 添加或更新页面加载。
	 * @param request
	 * @param response
	 * @param pageload 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新页面加载")
	public void save(HttpServletRequest request, HttpServletResponse response,Pageload pageload) throws Exception
	{
		String resultMsg=null;		
		try{
			if(pageload.getId()==null){
				Long id=UniqueIdUtil.genId();
				pageload.setId(id);
				pageloadService.add(pageload);
				resultMsg=getText("添加","页面加载");
			}else{
			    pageloadService.update(pageload);
				resultMsg=getText("更新","页面加载");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得页面加载分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看页面加载分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Pageload> list=pageloadService.getAll(new QueryFilter(request,"pageloadItem"));
		ModelAndView mv=this.getAutoView().addObject("pageloadList",list);
		
		return mv;
	}
	
	/**
	 * 删除页面加载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除页面加载")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			pageloadService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除页面加载成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑页面加载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑页面加载")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Pageload pageload=pageloadService.getById(id);
		
		return getAutoView().addObject("pageload",pageload)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得页面加载明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看页面加载明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Pageload pageload=pageloadService.getById(id);
		return getAutoView().addObject("pageload", pageload);
	}
	
}
