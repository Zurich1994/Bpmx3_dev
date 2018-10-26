
package com.hotent.firewallSet.controller.firewallSet;

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
import com.hotent.core.web.query.QueryFilter;

import com.hotent.firewallSet.model.firewallSet.Firewall;
import com.hotent.firewallSet.service.firewallSet.FirewallService;
import com.hotent.core.web.ResultMessage;
import com.hotent.deviceNodeSet.model.deviceNodeSet.DeviceNodeSet;
import com.hotent.deviceNodeSet.service.deviceNodeSet.DeviceNodeSetService;
/**
 * 对象功能:防火墙配置表 控制器类
 */
@Controller
@RequestMapping("/firewallSet/firewallSet/firewall/")
public class FirewallController extends BaseController
{
	@Resource
	private FirewallService firewallService;
	@Resource
	private DeviceNodeSetService deviceNodeSetService;
	/**
	 * 添加或更新防火墙配置表。
	 * @param request
	 * @param response
	 * @param firewall 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新防火墙配置表")
	public void save(HttpServletRequest request, HttpServletResponse response,Firewall firewall) throws Exception
	{
		String resultMsg=null;		
		//修改设备部署表
		if(firewall.getActdefid()!=null&&firewall.getNodeid()!=null){
			DeviceNodeSet deviceNodeSet = new DeviceNodeSet();
			deviceNodeSet.setActdefid(firewall.getActdefid());
			deviceNodeSet.setBusinessIP(firewall.getManage_IP());
			deviceNodeSet.setDeviceTable("w_firewall");
			deviceNodeSet.setNodeid(firewall.getNodeid());
			deviceNodeSetService.add(deviceNodeSet);
		}
		try{
			if(firewall.getId()==null){
				Long id=UniqueIdUtil.genId();
				firewall.setId(id);
				firewallService.add(firewall);
				resultMsg=getText("添加","防火墙配置表");
			}else{
			    firewallService.update(firewall);
				resultMsg=getText("更新","防火墙配置表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得防火墙配置表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看防火墙配置表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Firewall> list=firewallService.getAll(new QueryFilter(request,"firewallItem"));
		ModelAndView mv=this.getAutoView().addObject("firewallList",list);
		
		return mv;
	}
	
	/**
	 * 删除防火墙配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除防火墙配置表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			firewallService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除防火墙配置表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑防火墙配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑防火墙配置表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Firewall firewall=firewallService.getById(id);
		return getAutoView().addObject("firewall",firewall)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	编辑防火墙配置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("newedit")
	@Action(description="编辑防火墙配置表")
	public ModelAndView newedit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String actdefid = request.getParameter("actdefid");
		String nodeid = request.getParameter("nodeid");
		String returnUrl=RequestUtil.getPrePage(request);
		Firewall firewall=firewallService.getById(id);
		if(firewall==null){
			firewall = new Firewall();
		}
		firewall.setActdefid(actdefid);
		firewall.setNodeid(nodeid);
		return getAutoView().addObject("firewall",firewall)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	小页面编辑防火墙配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("smailedit")
	@Action(description="小页面编辑防火墙信息配置")
	public ModelAndView smailedit(HttpServletRequest request) throws Exception
	{
		String actDefId  = request.getParameter("actDefId");
		String nodeId  = request.getParameter("nodeId");
		String returnUrl=RequestUtil.getPrePage(request);
		Firewall firewall = null;
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select F_businessIP from w_device_node_set where F_actDefId='"+actDefId+"' AND F_nodeId='"+nodeId+"'";
		List<Map<String,Object>> list=template.queryForList(sql);
		if(list.size()!=0){
		String manageIP = list.get(0).get("F_businessIP").toString();
		sql = "select ID from W_FIREWALL where F_manage_IP='"+manageIP+"'";
		list=template.queryForList(sql);
		String firewallID = list.get(0).get("ID").toString();
		firewall = firewallService.getById(Long.valueOf(firewallID));
		request.setAttribute("selecteIP", manageIP);
		}else{
			firewall = new Firewall();
		}
		firewall.setActdefid(actDefId);
		firewall.setNodeid(nodeId);
		return getAutoView().addObject("firewall",firewall)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	刷新防火墙配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("refresh")
	@Action(description="刷新防火墙配置")
	public ModelAndView refresh(HttpServletRequest request) throws Exception
	{
		String selectID  = request.getParameter("value");
		String actdefid=request.getParameter("actdefid");
		String nodeid=request.getParameter("nodeid");
		Firewall firewall = firewallService.getById(Long.valueOf(selectID));
		String selecteIP = firewall.getManage_IP();
		firewall.setActdefid(actdefid);
		firewall.setNodeid(nodeid);
		request.setAttribute("selecteIP",selecteIP);
		ModelAndView mv=new ModelAndView("/firewallSet/firewallSet/firewallSmailedit.jsp");
		mv.addObject("firewall",firewall);
		return mv;
	}
	/**
	 * 取得防火墙配置表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看防火墙配置表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Firewall firewall=firewallService.getById(id);
		return getAutoView().addObject("firewall", firewall);
	}
	
}
