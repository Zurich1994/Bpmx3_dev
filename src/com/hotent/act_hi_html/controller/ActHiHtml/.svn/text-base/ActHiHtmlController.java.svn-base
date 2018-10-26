

package com.hotent.act_hi_html.controller.ActHiHtml;

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

import com.hotent.act_hi_html.model.ActHiHtml.ActHiHtml;
import com.hotent.act_hi_html.service.ActHiHtml.ActHiHtmlService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:act_hi_html 控制器类
 */
@Controller
@RequestMapping("/act_hi_html/ActHiHtml/actHiHtml/")
public class ActHiHtmlController extends BaseController
{
	@Resource
	private ActHiHtmlService actHiHtmlService;
	
	/**
	 * 添加或更新act_hi_html。
	 * @param request
	 * @param response
	 * @param actHiHtml 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新act_hi_html")
	public void save(HttpServletRequest request, HttpServletResponse response,ActHiHtml actHiHtml) throws Exception
	{
		String resultMsg=null;		
		try{
			if(actHiHtml.getId()==null){
				actHiHtmlService.save(actHiHtml);
				resultMsg=getText("添加","act_hi_html");
			}else{
			    actHiHtmlService.save(actHiHtml);
				resultMsg=getText("更新","act_hi_html");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得act_hi_html分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看act_hi_html分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ActHiHtml> list=actHiHtmlService.getAll(new QueryFilter(request,"actHiHtmlItem"));
		ModelAndView mv=this.getAutoView().addObject("actHiHtmlList",list);
		return mv;
	}
	
	/**
	 * 删除act_hi_html
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除act_hi_html")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			actHiHtmlService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除act_hi_html成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑act_hi_html
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑act_hi_html")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		ActHiHtml actHiHtml=actHiHtmlService.getById(id);
		
		return getAutoView().addObject("actHiHtml",actHiHtml)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得act_hi_html明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看act_hi_html明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ActHiHtml actHiHtml=actHiHtmlService.getById(id);
		return getAutoView().addObject("actHiHtml", actHiHtml);
	}
	
}