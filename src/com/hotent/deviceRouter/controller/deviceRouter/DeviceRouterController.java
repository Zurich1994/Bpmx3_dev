
package com.hotent.deviceRouter.controller.deviceRouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.deviceNodeSet.model.deviceNodeSet.DeviceNodeSet;
import com.hotent.deviceNodeSet.service.deviceNodeSet.DeviceNodeSetService;
import com.hotent.deviceRouter.model.deviceRouter.DeviceRouter;
import com.hotent.deviceRouter.service.deviceRouter.DeviceRouterService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:路由器配置表 控制器类
 */
@Controller
@RequestMapping("/deviceRouter/deviceRouter/deviceRouter/")
public class DeviceRouterController extends BaseController
{
	@Resource
	private DeviceRouterService deviceRouterService;
	@Resource
	private DeviceNodeSetService deviceNodeSetService;
	/**
	 * 添加或更新路由器配置表。
	 * @param request
	 * @param response
	 * @param deviceRouter 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新路由器配置表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceRouter deviceRouter) throws Exception
	{
		String resultMsg=null;
		
		//修改设备部署表
		if(deviceRouter.getActdefid()!=null&&deviceRouter.getNodeid()!=null){
			DeviceNodeSet deviceNodeSet = new DeviceNodeSet();
			deviceNodeSet.setActdefid(deviceRouter.getActdefid());
			deviceNodeSet.setBusinessIP(deviceRouter.getManger_IP());
			deviceNodeSet.setDeviceTable("w_device_router");
			deviceNodeSet.setNodeid(deviceRouter.getNodeid());
			deviceNodeSetService.add(deviceNodeSet);
		}
		try{
			if(deviceRouter.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceRouter.setId(id);
				deviceRouterService.add(deviceRouter);
				resultMsg=getText("添加","路由器配置表");
			}else{
			    deviceRouterService.update(deviceRouter);
				resultMsg=getText("更新","路由器配置表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 	小页面编辑路由器配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	
	@RequestMapping("smailedit")
	@Action(description="小页面编辑设备基本信息配置")
	public ModelAndView smailedit(HttpServletRequest request) throws Exception
	{
		String actDefId  = request.getParameter("actDefId");
		String nodeId  = request.getParameter("nodeId");
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceRouter deviceRouter = null;
		//List<DeviceRouter> list = deviceRouterService.getByNodeIdANDActdefId(actDefId, nodeId);
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select F_businessIP from w_device_node_set where F_actDefId='"+actDefId+"' AND F_nodeId='"+nodeId+"'";
		List<Map<String,Object>> list=template.queryForList(sql);
		if(list.size()!=0){
		String manageIP = list.get(0).get("F_businessIP").toString();
		sql = "select ID from w_device_router where F_manage_IP='"+manageIP+"'";
		list=template.queryForList(sql);
		String routerID = list.get(0).get("ID").toString();
		deviceRouter = deviceRouterService.getById(Long.valueOf(routerID));
		request.setAttribute("selecteIP", manageIP);
		}else{
			deviceRouter = new DeviceRouter();
		}
		deviceRouter.setActdefid(actDefId);
        deviceRouter.setNodeid(nodeId);
		return getAutoView().addObject("deviceRouter",deviceRouter)
							.addObject("returnUrl",returnUrl);
	}
	
	/**
	 * 	刷新路由器配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	
	@RequestMapping("refresh")
	@Action(description="刷新设备基本信息配置")
	public ModelAndView refresh(HttpServletRequest request) throws Exception
	{
		String selectID  = request.getParameter("value");
		String actdefid=request.getParameter("actdefid");
		String nodeid=request.getParameter("nodeid");
		DeviceRouter deviceRouter = deviceRouterService.getById(Long.valueOf(selectID));
		String selecteIP = deviceRouter.getManger_IP();
		deviceRouter.setActdefid(actdefid);
		deviceRouter.setNodeid(nodeid);
		request.setAttribute("selecteIP",selecteIP);
		ModelAndView mv=new ModelAndView("/deviceRouter/deviceRouter/deviceRouterSmailedit.jsp");
		mv.addObject("deviceRouter",deviceRouter);
		return mv;
	}
	/**
	 * 取得路由器配置表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看路由器配置表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceRouter> list=deviceRouterService.getAll(new QueryFilter(request,"deviceRouterItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceRouterList",list);
		
		return mv;
	}
	
	/**
	 * 删除路由器配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除路由器配置表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceRouterService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除路由器配置表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑路由器配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑路由器配置表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceRouter deviceRouter=deviceRouterService.getById(id);
		
		return getAutoView().addObject("deviceRouter",deviceRouter)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	编辑路由器配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("newedit")
	@Action(description="编辑路由器配置表")
	public ModelAndView newedit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String actdefid = request.getParameter("actdefid");
		String nodeid = request.getParameter("nodeid");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceRouter deviceRouter=deviceRouterService.getById(id);
		
		if(deviceRouter==null){
			deviceRouter = new DeviceRouter();
		}
		deviceRouter.setActdefid(actdefid);
		deviceRouter.setNodeid(nodeid);
		return getAutoView().addObject("deviceRouter",deviceRouter)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得路由器配置表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看路由器配置表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceRouter deviceRouter=deviceRouterService.getById(id);
		return getAutoView().addObject("deviceRouter", deviceRouter);
	}
	
}
