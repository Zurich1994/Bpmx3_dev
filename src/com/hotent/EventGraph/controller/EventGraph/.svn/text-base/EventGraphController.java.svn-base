

package com.hotent.EventGraph.controller.EventGraph;

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

import com.hotent.EventGraph.model.EventGraph.EventGraph;
import com.hotent.EventGraph.service.EventGraph.EventGraphService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:事件绑定 控制器类
 */
@Controller
@RequestMapping("/EventGraph/EventGraph/eventGraph/")
public class EventGraphController extends BaseController
{
	@Resource
	private EventGraphService eventGraphService;
	
	/**
	 * 添加或更新事件绑定。
	 * @param request
	 * @param response
	 * @param eventGraph 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新事件绑定")
	public void save(HttpServletRequest request, HttpServletResponse response,EventGraph eventGraph) throws Exception
	{
		String resultMsg=null;		
		try{
			if(eventGraph.getId()==null){
				eventGraphService.save(eventGraph);
				resultMsg=getText("添加","事件绑定");
			}else{
			    eventGraphService.save(eventGraph);
				resultMsg=getText("更新","事件绑定");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得事件绑定分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看事件绑定分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<EventGraph> list=eventGraphService.getAll(new QueryFilter(request,"eventGraphItem"));
		ModelAndView mv=this.getAutoView().addObject("eventGraphList",list);
		return mv;
	}
	
	/**
	 * 删除事件绑定
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除事件绑定")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			eventGraphService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除事件绑定成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑事件绑定
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑事件绑定")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		EventGraph eventGraph=eventGraphService.getById(id);
		
		return getAutoView().addObject("eventGraph",eventGraph)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得事件绑定明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看事件绑定明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		EventGraph eventGraph=eventGraphService.getById(id);
		return getAutoView().addObject("eventGraph", eventGraph);
	}
	
}