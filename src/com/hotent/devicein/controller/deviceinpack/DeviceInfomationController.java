
package com.hotent.devicein.controller.deviceinpack;

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
import com.hotent.deviceRouter.model.deviceRouter.DeviceRouter;
import com.hotent.deviceRouter.service.deviceRouter.DeviceRouterService;
import com.hotent.devicein.model.deviceinpack.DeviceInfomation;
import com.hotent.devicein.service.deviceinpack.DeviceInfomationService;
import com.hotent.firewallSet.model.firewallSet.Firewall;
import com.hotent.firewallSet.service.firewallSet.FirewallService;
import com.hotent.kvmSet.model.kvmSet.Kvm;
import com.hotent.kvmSet.service.kvmSet.KvmService;
import com.hotent.serviceSet.model.serviceSet.DeviceServer;
import com.hotent.serviceSet.service.serviceSet.DeviceServerService;
import com.hotent.switchSet.model.switchSet.DeviceSwitch;
import com.hotent.switchSet.service.switchSet.DeviceSwitchService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:设备基本信息表 控制器类
 */
@Controller
@RequestMapping("/devicein/deviceinpack/deviceInfomation/")
public class DeviceInfomationController extends BaseController
{
	@Resource
	private DeviceInfomationService deviceInfomationService;
	@Resource
	private DeviceServerService deviceServerService;
	@Resource
	private DeviceRouterService deviceRouterService;
	@Resource
	private FirewallService firewallService;
	@Resource
	private DeviceSwitchService deviceSwitchService;
	@Resource
	private KvmService kvmService;
	
	/**
	 * 添加或更新设备基本信息表。
	 * @param request
	 * @param response
	 * @param deviceInfomation 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新设备基本信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceInfomation deviceInfomation) throws Exception
	{
		String resultMsg=null;
		
		String code = String.valueOf(System.currentTimeMillis());
		StringBuffer devId = new StringBuffer("");
		//lxz.................................
		if("联通".equals(deviceInfomation.getSbgs())) {
			devId.append("LT_");
		}else if("电信".equals(deviceInfomation.getSbgs())) {
			devId.append("DX_");
		}else if("时代新智".equals(deviceInfomation.getSbgs())) {
			devId.append("SDXZ_");
		}else {
			devId.append("QT_");
		}
		
		int devtype = 0;
		
		if("服务器".equals(deviceInfomation.getDev_type())){
			devId.append("SERVER_");
		}else if("路由器".equals(deviceInfomation.getDev_type())) {
			devId.append("ROUTER_");
			devtype = 1;
		}else if("防火墙".equals(deviceInfomation.getDev_type())) {
			devId.append("FIREWALL_");
			devtype = 2;
		}else if("交换机".equals(deviceInfomation.getDev_type())) {
			devId.append("SWITCH_");
			devtype = 3;
		}else if("KVM".equals(deviceInfomation.getDev_type())) {
			devId.append("KVM_");
			devtype = 4;
		}
		devId.append(code);
		//zl...............................................
		if(deviceInfomation.getId()==null){
		switch(devtype){
		case 0:
		    {
			JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
			String sql="select ID from w_device_server where F_dev_ID IS NULL";
			List<Map<String,Object>> list=template.queryForList(sql);
			if(list.size()==0)
			{
			DeviceServer deviceServer= new DeviceServer();
			deviceServer.setId(UniqueIdUtil.genId());
			deviceServer.setDev_ID(devId.toString());
			deviceServerService.add(deviceServer);
			}
			else {
				Long id = Long.valueOf(list.get(0).get("ID").toString());
				DeviceServer deviceServer=deviceServerService.getById(id);
				deviceServer.setDev_ID(devId.toString());
				deviceServerService.update(deviceServer);
			}
			break;
			}
		case 1:
		    {
		    JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		    String sql="select ID from w_device_router where F_dev_ID IS NULL";
		    List<Map<String,Object>> list=template.queryForList(sql);
			if(list.size()==0)
			{
			DeviceRouter deviceRouter = new DeviceRouter();
			deviceRouter.setId(UniqueIdUtil.genId());
			deviceRouter.setDev_ID(devId.toString());
			deviceRouterService.add(deviceRouter);
			}
			else {
				Long id = Long.valueOf(list.get(0).get("ID").toString());
				DeviceRouter deviceRouter=deviceRouterService.getById(id);
				deviceRouter.setDev_ID(devId.toString());
				deviceRouterService.update(deviceRouter);
			}
			break;
		    }
		case 2:
			{
			JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
			String sql="select ID from w_firewall where F_dev_ID IS NULL";
			List<Map<String,Object>> list=template.queryForList(sql);
		    if(list.size()==0)
			{
			Firewall firewall = new Firewall();
			firewall.setId(UniqueIdUtil.genId());
			firewall.setSb_id(devId.toString());
			firewallService.add(firewall);
			}
		    else {
				Long id = Long.valueOf(list.get(0).get("ID").toString());
				Firewall firewall=firewallService.getById(id);
				firewall.setSb_id(devId.toString());
				firewallService.update(firewall);
			}
			break;
			}
		case 3:
		    {
			JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
			String sql="select ID from w_device_switch where F_dev_ID IS NULL";
			List<Map<String,Object>> list=template.queryForList(sql);
		    if(list.size()==0)
			{
			DeviceSwitch deviceSwitch = new DeviceSwitch();
			deviceSwitch.setId(UniqueIdUtil.genId());
			deviceSwitch.setDev_ID(devId.toString());
			deviceSwitchService.add(deviceSwitch);
			}
		    else {
				Long id = Long.valueOf(list.get(0).get("ID").toString());
				DeviceSwitch deviceSwitch=deviceSwitchService.getById(id);
				deviceSwitch.setDev_ID(devId.toString());
				deviceSwitchService.update(deviceSwitch);
			}
			break;
		    }
		case 4:
		{
			JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
			String sql="select ID from w_kvm where F_dev_ID IS NULL";
			List<Map<String,Object>> list=template.queryForList(sql);
		    if(list.size()==0)
			{
			Kvm kvm = new Kvm();
			kvm.setId(UniqueIdUtil.genId());
			kvm.setDev_ID(devId.toString());
			kvmService.add(kvm);
			}
		    else {
				Long id = Long.valueOf(list.get(0).get("ID").toString());
				Kvm kvm=kvmService.getById(id);
				kvm.setDev_ID(devId.toString());
				kvmService.update(kvm);
			}
			break;
		}
		}
		}
		deviceInfomation.setDev_id(devId.toString());
		try{
			if(deviceInfomation.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceInfomation.setId(id);
				deviceInfomationService.add(deviceInfomation);
				resultMsg=getText("添加成功!","设备基本信息表");
			}else{
			    deviceInfomationService.update(deviceInfomation);
				resultMsg=getText("更新成功！","设备基本信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得设备基本信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看设备基本信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceInfomation> list=deviceInfomationService.getAll(new QueryFilter(request,"deviceInfomationItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceInfomationList",list);
		
		return mv;
	}
	
	/**
	 * 删除设备基本信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除设备基本信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceInfomationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除设备基本信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑设备基本信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑设备基本信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceInfomation deviceInfomation=deviceInfomationService.getById(id);
		return getAutoView().addObject("deviceInfomation",deviceInfomation)
							.addObject("returnUrl",returnUrl);
	}
	
	/**
	 * 	编辑设备基本信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("newedit")
	@Action(description="编辑设备基本信息表")
	public ModelAndView newedit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String type = request.getParameter("type");
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceInfomation deviceInfomation=deviceInfomationService.getById(id);
		if(deviceInfomation==null){
			deviceInfomation = new DeviceInfomation();
		}
		if("router".equals(type)){
			deviceInfomation.setDev_type("路由器");
		}else if("switch".equals(type)){
			deviceInfomation.setDev_type("交换机");
		}else if("server".equals(type)){
			deviceInfomation.setDev_type("服务器");
		}else if("kvm".equals(type)){
			deviceInfomation.setDev_type("KVM");
		}else{
			deviceInfomation.setDev_type("防火墙");
		}
		return getAutoView().addObject("deviceInfomation",deviceInfomation)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	小页面编辑KVM配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("smailedit")
	@Action(description="小页面编辑KVM信息配置")
	public ModelAndView smailedit(HttpServletRequest request) throws Exception
	{
		String actDefId  = request.getParameter("actDefId");
		String nodeId  = request.getParameter("nodeId");
		//String manageIP = request.getParameter("manageIP");
		//临时测试用。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
		//manageIP = "'127.0.0.1'";
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select F_deviceTable,F_businessIP from w_device_node_set where F_actDefId='"+actDefId+"' AND F_nodeId='"+nodeId+"'";
		List<Map<String,Object>> list=template.queryForList(sql);
		Long devInfoId = null;
		DeviceInfomation deviceInfomation = null;
		if(list.size()!=0)
		{
		    String tableName = list.get(0).get("F_deviceTable").toString();
		    String manageIP = list.get(0).get("F_businessIP").toString();
		    sql = "select F_dev_ID from "+tableName+" where F_manage_IP='"+manageIP+"'";
		    list=template.queryForList(sql);
		    String devID = list.get(0).get("F_dev_ID").toString();
		    sql = "select ID from w_device_infomation where F_dev_id='"+devID+"'";
		    list=template.queryForList(sql);
		    devInfoId = Long.valueOf(list.get(0).get("ID").toString());
		    deviceInfomation = deviceInfomationService.getById(devInfoId);
		}
		else
		{
			deviceInfomation = new DeviceInfomation();
		}
		//临时测试用。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
		//List<DeviceInfomation> list = deviceInfomationService.getByNodeIdANDActdefId(actDefId, nodeId);
		String returnUrl=RequestUtil.getPrePage(request);
	    deviceInfomation.setActdefid(actDefId);
	    deviceInfomation.setNodeid(nodeId);
	    //ModelAndView mv = new ModelAndView("/deinfo/deinfopack/deviceInfomationGet.jsp");
		return getAutoView().addObject("deviceInfomation",deviceInfomation)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 取得设备基本信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看设备基本信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceInfomation deviceInfomation=deviceInfomationService.getById(id);
		return getAutoView().addObject("deviceInfomation", deviceInfomation);
	}
	
}
