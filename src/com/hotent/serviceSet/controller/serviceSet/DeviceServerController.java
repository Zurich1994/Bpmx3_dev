
package com.hotent.serviceSet.controller.serviceSet;

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

import com.hotent.serviceSet.model.serviceSet.DeviceServer;
import com.hotent.serviceSet.service.serviceSet.DeviceServerService;
import com.hotent.switchSet.model.switchSet.DeviceSwitch;
import com.hotent.core.web.ResultMessage;
import com.hotent.deviceNodeSet.model.deviceNodeSet.DeviceNodeSet;
import com.hotent.deviceNodeSet.service.deviceNodeSet.DeviceNodeSetService;
/**
 * 对象功能:服务器配置表 控制器类
 */
@Controller
@RequestMapping("/serviceSet/serviceSet/deviceServer/")
public class DeviceServerController extends BaseController
{
	@Resource
	private DeviceServerService deviceServerService;
	@Resource
	private DeviceNodeSetService deviceNodeSetService;
	/**
	 * 添加或更新服务器配置表。
	 * @param request
	 * @param response
	 * @param deviceServer 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("save")
	@Action(description="添加或更新服务器配置表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceServer deviceServer) throws Exception
	{
		String resultMsg=null;		
		//修改设备部署表
		if(deviceServer.getActdefid()!=null&&deviceServer.getNodeid()!=null){
			DeviceNodeSet deviceNodeSet = new DeviceNodeSet();
			deviceNodeSet.setActdefid(deviceServer.getActdefid());
			deviceNodeSet.setBusinessIP(deviceServer.getManage_IP());
			deviceNodeSet.setDeviceTable("w_device_server");
			deviceNodeSet.setNodeid(deviceServer.getNodeid());
			deviceNodeSetService.add(deviceNodeSet);
		}
		try{
			if(deviceServer.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceServer.setId(id);
				deviceServerService.add(deviceServer);
				resultMsg=getText("添加","服务器配置表");
			}else{
			    deviceServerService.update(deviceServer);
				resultMsg=getText("更新","服务器配置表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得服务器配置表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看服务器配置表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceServer> list=deviceServerService.getAll(new QueryFilter(request,"deviceServerItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceServerList",list);
		
		return mv;
	}
	
	/**
	 * 删除服务器配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除服务器配置表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceServerService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除服务器配置表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑服务器配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑服务器配置表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceServer deviceServer=deviceServerService.getById(id);
		
		return getAutoView().addObject("deviceServer",deviceServer)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 	编辑服务器配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("newedit")
	@Action(description="编辑服务器配置表")
	public ModelAndView newedit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String actdefid = request.getParameter("actdefid");
		String nodeid = request.getParameter("nodeid");
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceServer deviceServer=deviceServerService.getById(id);
		if(deviceServer==null){
			deviceServer = new DeviceServer();
		}
		deviceServer.setActdefid(actdefid);
		deviceServer.setNodeid(nodeid);
		return getAutoView().addObject("deviceServer",deviceServer)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	小页面编辑服务器配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("smailedit")
	@Action(description="小页面编辑服务器信息配置")
	public ModelAndView smailedit(HttpServletRequest request) throws Exception
	{
		String actDefId  = request.getParameter("actDefId");
		String nodeId  = request.getParameter("nodeId");
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceServer deviceServer = null;
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select F_businessIP from w_device_node_set where F_actDefId='"+actDefId+"' AND F_nodeId='"+nodeId+"'";
		List<Map<String,Object>> list=template.queryForList(sql);
		if(list.size()!=0){
		String manageIP = list.get(0).get("F_businessIP").toString();
		sql = "select ID from W_DEVICE_SERVER where F_manage_IP='"+manageIP+"'";
		list=template.queryForList(sql);
		String deviceServerID = list.get(0).get("ID").toString();
		deviceServer = deviceServerService.getById(Long.valueOf(deviceServerID));
		request.setAttribute("selecteIP", manageIP);
		}else{
			deviceServer = new DeviceServer();
		}
		deviceServer.setActdefid(actDefId);
		deviceServer.setNodeid(nodeId);
		return getAutoView().addObject("deviceServer",deviceServer)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	刷新服务器配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("refresh")
	@Action(description="刷新服务器配置")
	public ModelAndView refresh(HttpServletRequest request) throws Exception
	{
		String selectID  = request.getParameter("value");
		String actdefid=request.getParameter("actdefid");
		String nodeid=request.getParameter("nodeid");
		DeviceServer deviceServer = deviceServerService.getById(Long.valueOf(selectID));
		String selecteIP = deviceServer.getManage_IP();
		deviceServer.setActdefid(actdefid);
		deviceServer.setNodeid(nodeid);
		request.setAttribute("selecteIP",selecteIP);
		ModelAndView mv=new ModelAndView("/serviceSet/serviceSet/deviceServerSmailedit.jsp");
		mv.addObject("deviceServer",deviceServer);
		return mv;
	}
	/**
	 * 取得服务器配置表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看服务器配置表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceServer deviceServer=deviceServerService.getById(id);
		return getAutoView().addObject("deviceServer", deviceServer);
	}
	
}
