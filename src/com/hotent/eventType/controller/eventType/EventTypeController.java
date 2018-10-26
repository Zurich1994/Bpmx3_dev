
package com.hotent.eventType.controller.eventType;

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

import com.hotent.eventType.model.eventType.EventType;
import com.hotent.eventType.service.eventType.EventTypeService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:事件类型表 控制器类
 */
@Controller
@RequestMapping("/eventType/eventType/eventType/")
public class EventTypeController extends BaseController
{
	@Resource
	private EventTypeService eventTypeService;
	/**
	 * 添加或更新事件类型表。
	 * @param request
	 * @param response
	 * @param eventType 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新事件类型表")
	public void save(HttpServletRequest request, HttpServletResponse response,EventType eventType) throws Exception
	{
		String resultMsg=null;		
		try{
			if(eventType.getId()==null){
				Long id=UniqueIdUtil.genId();
				eventType.setId(id);
				eventTypeService.add(eventType);
				resultMsg=getText("添加","事件类型表");
			}else{
			    eventTypeService.update(eventType);
				resultMsg=getText("更新","事件类型表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得事件类型表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看事件类型表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<EventType> list=eventTypeService.getAll(new QueryFilter(request,"eventTypeItem"));
		ModelAndView mv=this.getAutoView().addObject("eventTypeList",list);
		
		return mv;
	}
	
	/**
	 * 取得事件类型表分页列表以便选择
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listSelect")
	@Action(description="查看事件类型表分页列表以便选择")
	public ModelAndView listSelect(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<EventType> list=eventTypeService.getAll(new QueryFilter(request,"eventTypeItem"));
		ModelAndView mv=this.getAutoView().addObject("eventTypeList",list);
		
		return mv;
	}
	
	/**
	 * 删除事件类型表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除事件类型表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			eventTypeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除事件类型表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑事件类型表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑事件类型表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		EventType eventType=eventTypeService.getById(id);
		
		return getAutoView().addObject("eventType",eventType)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得事件类型表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看事件类型表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		EventType eventType=eventTypeService.getById(id);
		return getAutoView().addObject("eventType", eventType);
	}
	
}
