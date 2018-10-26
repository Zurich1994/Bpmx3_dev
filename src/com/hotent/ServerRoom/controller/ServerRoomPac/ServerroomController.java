
package com.hotent.ServerRoom.controller.ServerRoomPac;

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

import com.hotent.ServerRoom.model.ServerRoomPac.Serverroom;
import com.hotent.ServerRoom.service.ServerRoomPac.ServerroomService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:机房信息表 控制器类
 */
@Controller
@RequestMapping("/ServerRoom/ServerRoomPac/serverroom/")
public class ServerroomController extends BaseController
{
	@Resource
	private ServerroomService serverroomService;
	/**
	 * 添加或更新机房信息表。
	 * @param request
	 * @param response
	 * @param serverroom 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新机房信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,Serverroom serverroom) throws Exception
	{
		String resultMsg=null;		
		try{
			if(serverroom.getId()==null){
				Long id=UniqueIdUtil.genId();
				serverroom.setId(id);
				serverroomService.add(serverroom);
				resultMsg=getText("添加","机房信息表");
			}else{
			    serverroomService.update(serverroom);
				resultMsg=getText("更新","机房信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得机房信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看机房信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Serverroom> list=serverroomService.getAll(new QueryFilter(request,"serverroomItem"));
		ModelAndView mv=this.getAutoView().addObject("serverroomList",list);
		
		return mv;
	}
	
	/**
	 * 删除机房信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除机房信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			serverroomService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除机房信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑机房信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑机房信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Serverroom serverroom=serverroomService.getById(id);
		
		return getAutoView().addObject("serverroom",serverroom)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得机房信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看机房信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Serverroom serverroom=serverroomService.getById(id);
		return getAutoView().addObject("serverroom", serverroom);
	}
	
}
