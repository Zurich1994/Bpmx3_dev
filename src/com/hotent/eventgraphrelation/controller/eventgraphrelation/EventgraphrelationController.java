
package com.hotent.eventgraphrelation.controller.eventgraphrelation;

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

import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;
import com.hotent.eventgraphrelation.service.eventgraphrelation.EventgraphrelationService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:事件图关联表 控制器类
 */
@Controller
@RequestMapping("/eventgraphrelation/eventgraphrelation/eventgraphrelation/")
public class EventgraphrelationController extends BaseController
{
	@Resource
	private EventgraphrelationService eventgraphrelationService;
	/**
	 * 添加或更新事件图关联表。
	 * @param request
	 * @param response
	 * @param eventgraphrelation 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新事件图关联表")
	public void save(HttpServletRequest request, HttpServletResponse response,Eventgraphrelation eventgraphrelation) throws Exception
	{
		String resultMsg=null;		
		try{
			if(eventgraphrelation.getId()==null){
				Long id=UniqueIdUtil.genId();
				eventgraphrelation.setId(id);
				eventgraphrelationService.add(eventgraphrelation);
				resultMsg=getText("添加","事件图关联表");
			}else{
			    eventgraphrelationService.update(eventgraphrelation);
				resultMsg=getText("更新","事件图关联表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得事件图关联表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看事件图关联表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Eventgraphrelation> list=eventgraphrelationService.getAll(new QueryFilter(request,"eventgraphrelationItem"));
		ModelAndView mv=this.getAutoView().addObject("eventgraphrelationList",list);
		
		return mv;
	}
	
	/**
	 * 删除事件图关联表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除事件图关联表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			eventgraphrelationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除事件图关联表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑事件图关联表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑事件图关联表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Eventgraphrelation eventgraphrelation=eventgraphrelationService.getById(id);
		
		return getAutoView().addObject("eventgraphrelation",eventgraphrelation)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得事件图关联表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看事件图关联表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Eventgraphrelation eventgraphrelation=eventgraphrelationService.getById(id);
		return getAutoView().addObject("eventgraphrelation", eventgraphrelation);
	}
	
}
