
package com.hotent.ServerSoftware.controller.ServerSoftwarePac;

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

import com.hotent.ServerSoftware.model.ServerSoftwarePac.ServerSoftware;
import com.hotent.ServerSoftware.service.ServerSoftwarePac.ServerSoftwareService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:服务器-软件表 控制器类
 */
@Controller
@RequestMapping("/ServerSoftware/ServerSoftwarePac/serverSoftware/")
public class ServerSoftwareController extends BaseController
{
	@Resource
	private ServerSoftwareService serverSoftwareService;
	/**
	 * 添加或更新服务器-软件表。
	 * @param request
	 * @param response
	 * @param serverSoftware 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新服务器-软件表")
	public void save(HttpServletRequest request, HttpServletResponse response,ServerSoftware serverSoftware) throws Exception
	{
		String resultMsg=null;		
		try{
			if(serverSoftware.getId()==null){
				Long id=UniqueIdUtil.genId();
				serverSoftware.setId(id);
				serverSoftwareService.add(serverSoftware);
				resultMsg=getText("添加","服务器-软件表");
			}else{
			    serverSoftwareService.update(serverSoftware);
				resultMsg=getText("更新","服务器-软件表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得服务器-软件表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看服务器-软件表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ServerSoftware> list=serverSoftwareService.getAll(new QueryFilter(request,"serverSoftwareItem"));
		ModelAndView mv=this.getAutoView().addObject("serverSoftwareList",list);
		
		return mv;
	}
	
	/**
	 * 删除服务器-软件表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除服务器-软件表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			serverSoftwareService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除服务器-软件表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑服务器-软件表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑服务器-软件表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		ServerSoftware serverSoftware=serverSoftwareService.getById(id);
		
		return getAutoView().addObject("serverSoftware",serverSoftware)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得服务器-软件表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看服务器-软件表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ServerSoftware serverSoftware=serverSoftwareService.getById(id);
		return getAutoView().addObject("serverSoftware", serverSoftware);
	}
	
}
