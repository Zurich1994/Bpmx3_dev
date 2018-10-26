
package com.hotent.switchSet.controller.switchSet;

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

import com.hotent.switchSet.model.switchSet.DeviceSwitch;
import com.hotent.switchSet.service.switchSet.DeviceSwitchService;
import com.hotent.core.web.ResultMessage;
import com.hotent.deviceNodeSet.model.deviceNodeSet.DeviceNodeSet;
import com.hotent.deviceNodeSet.service.deviceNodeSet.DeviceNodeSetService;
import com.hotent.firewallSet.model.firewallSet.Firewall;
/**
 * 对象功能:交换机配置表 控制器类
 */
@Controller
@RequestMapping("/switchSet/switchSet/deviceSwitch/")
public class DeviceSwitchController extends BaseController
{
	@Resource
	private DeviceSwitchService deviceSwitchService;
	@Resource
	private DeviceNodeSetService deviceNodeSetService;
	/**
	 * 添加或更新交换机配置表。
	 * @param request
	 * @param response
	 * @param deviceSwitch 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("save")
	@Action(description="添加或更新交换机配置表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceSwitch deviceSwitch) throws Exception
	{
		String resultMsg=null;	
		//修改设备部署表
		if(deviceSwitch.getActdefid()!=null&&deviceSwitch.getNodeid()!=null){
			DeviceNodeSet deviceNodeSet = new DeviceNodeSet();
			deviceNodeSet.setActdefid(deviceSwitch.getActdefid());
			deviceNodeSet.setBusinessIP(deviceSwitch.getManage_IP());
			deviceNodeSet.setDeviceTable("w_device_switch");
			deviceNodeSet.setNodeid(deviceSwitch.getNodeid());
			deviceNodeSetService.add(deviceNodeSet);
		}
		try{
			if(deviceSwitch.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceSwitch.setId(id);
				deviceSwitchService.add(deviceSwitch);
				resultMsg=getText("添加","交换机配置表");
			}else{
			    deviceSwitchService.update(deviceSwitch);
				resultMsg=getText("更新","交换机配置表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得交换机配置表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看交换机配置表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceSwitch> list=deviceSwitchService.getAll(new QueryFilter(request,"deviceSwitchItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceSwitchList",list);
		
		return mv;
	}
	
	/**
	 * 删除交换机配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除交换机配置表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceSwitchService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除交换机配置表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑交换机配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑交换机配置表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceSwitch deviceSwitch=deviceSwitchService.getById(id);
		
		return getAutoView().addObject("deviceSwitch",deviceSwitch)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	编辑交换机配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("newedit")
	@Action(description="编辑交换机配置表")
	public ModelAndView newedit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String actdefid = request.getParameter("actdefid");
		String nodeid = request.getParameter("nodeid");
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceSwitch deviceSwitch=deviceSwitchService.getById(id);
		if(deviceSwitch==null){
			deviceSwitch = new DeviceSwitch();
		}
		deviceSwitch.setActdefid(actdefid);
		deviceSwitch.setNodeid(nodeid);
		return getAutoView().addObject("deviceSwitch",deviceSwitch)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	小页面编辑交换机配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("smailedit")
	@Action(description="小页面编辑交换机信息配置")
	public ModelAndView smailedit(HttpServletRequest request) throws Exception
	{
		String actDefId  = request.getParameter("actDefId");
		String nodeId  = request.getParameter("nodeId");
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceSwitch deviceSwitch = null;
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select F_businessIP from w_device_node_set where F_actDefId='"+actDefId+"' AND F_nodeId='"+nodeId+"'";
		List<Map<String,Object>> list=template.queryForList(sql);
		if(list.size()!=0){
		String manageIP = list.get(0).get("F_businessIP").toString();
		sql = "select ID from W_DEVICE_SWITCH where F_manage_IP='"+manageIP+"'";
		list=template.queryForList(sql);
		String deviceSwitchID = list.get(0).get("ID").toString();
		deviceSwitch = deviceSwitchService.getById(Long.valueOf(deviceSwitchID));
		request.setAttribute("selecteIP", manageIP);
		}else{
			deviceSwitch = new DeviceSwitch();
		}
		deviceSwitch.setActdefid(actDefId);
		deviceSwitch.setNodeid(nodeId);
		return getAutoView().addObject("deviceSwitch",deviceSwitch)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	刷新交换机配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("refresh")
	@Action(description="刷新交换机配置")
	public ModelAndView refresh(HttpServletRequest request) throws Exception
	{
		String selectID  = request.getParameter("value");
		String actdefid=request.getParameter("actdefid");
		String nodeid=request.getParameter("nodeid");
		DeviceSwitch deviceSwitch = deviceSwitchService.getById(Long.valueOf(selectID));
		String selecteIP = deviceSwitch.getManage_IP();
		deviceSwitch.setActdefid(actdefid);
		deviceSwitch.setNodeid(nodeid);
		request.setAttribute("selecteIP",selecteIP);
		ModelAndView mv=new ModelAndView("/switchSet/switchSet/deviceSwitchSmailedit.jsp");
		mv.addObject("deviceSwitch",deviceSwitch);
		return mv;
	}
	/**
	 * 取得交换机配置表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看交换机配置表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceSwitch deviceSwitch=deviceSwitchService.getById(id);
		return getAutoView().addObject("deviceSwitch", deviceSwitch);
	}
	
}