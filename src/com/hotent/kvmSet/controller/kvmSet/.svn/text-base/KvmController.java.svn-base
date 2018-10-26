
package com.hotent.kvmSet.controller.kvmSet;

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

import com.hotent.kvmSet.model.kvmSet.Kvm;
import com.hotent.kvmSet.service.kvmSet.KvmService;
import com.hotent.serviceSet.model.serviceSet.DeviceServer;
import com.hotent.core.web.ResultMessage;
import com.hotent.deviceNodeSet.model.deviceNodeSet.DeviceNodeSet;
import com.hotent.deviceNodeSet.service.deviceNodeSet.DeviceNodeSetService;
import com.hotent.firewallSet.model.firewallSet.Firewall;
/**
 * 对象功能:KVM表 控制器类
 */
@Controller
@RequestMapping("/kvmSet/kvmSet/kvm/")
public class KvmController extends BaseController
{
	@Resource
	private KvmService kvmService;
	@Resource
	private DeviceNodeSetService deviceNodeSetService;
	/**
	 * 添加或更新KVM表。
	 * @param request
	 * @param response
	 * @param kvm 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("save")
	@Action(description="添加或更新KVM表")
	public void save(HttpServletRequest request, HttpServletResponse response,Kvm kvm) throws Exception
	{
		String resultMsg=null;		
		//修改设备部署表
		if(kvm.getActdefid()!=null&&kvm.getNodeid()!=null){
			DeviceNodeSet deviceNodeSet = new DeviceNodeSet();
			deviceNodeSet.setActdefid(kvm.getActdefid());
			deviceNodeSet.setBusinessIP(kvm.getManage_IP());
			deviceNodeSet.setDeviceTable("w_kvm");
			deviceNodeSet.setNodeid(kvm.getNodeid());
			deviceNodeSetService.add(deviceNodeSet);
		}
		try{
			if(kvm.getId()==null){
				Long id=UniqueIdUtil.genId();
				kvm.setId(id);
				kvmService.add(kvm);
				resultMsg=getText("添加","KVM表");
			}else{
			    kvmService.update(kvm);
				resultMsg=getText("更新","KVM表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得KVM表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看KVM表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Kvm> list=kvmService.getAll(new QueryFilter(request,"kvmItem"));
		ModelAndView mv=this.getAutoView().addObject("kvmList",list);
		
		return mv;
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
		String returnUrl=RequestUtil.getPrePage(request);
		Kvm kvm = null;
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select F_businessIP from w_device_node_set where F_actDefId='"+actDefId+"' AND F_nodeId='"+nodeId+"'";
		List<Map<String,Object>> list=template.queryForList(sql);
		if(list.size()!=0){
		String manageIP = list.get(0).get("F_businessIP").toString();
		sql = "select ID from W_KVM where F_manage_IP='"+manageIP+"'";
		list=template.queryForList(sql);
		String kvmID = list.get(0).get("ID").toString();
		kvm = kvmService.getById(Long.valueOf(kvmID));
		request.setAttribute("selecteIP", manageIP);
		}else{
			kvm = new Kvm();
		}
		kvm.setActdefid(actDefId);
		kvm.setNodeid(nodeId);
		return getAutoView().addObject("kvm",kvm)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	刷新K配置
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
		Kvm kvm = kvmService.getById(Long.valueOf(selectID));
		String selecteIP = kvm.getManage_IP();
		kvm.setActdefid(actdefid);
		kvm.setNodeid(nodeid);
		request.setAttribute("selecteIP",selecteIP);
		ModelAndView mv=new ModelAndView("/kvmSet/kvmSet/kvmSmailedit.jsp");
		mv.addObject("kvm",kvm);
		return mv;
	}
	/**
	 * 删除KVM表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除KVM表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			kvmService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除KVM表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑KVM表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑KVM表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Kvm kvm=kvmService.getById(id);
		
		return getAutoView().addObject("kvm",kvm)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	编辑KVM表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("newedit")
	@Action(description="编辑KVM表")
	public ModelAndView newedit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String actdefid = request.getParameter("actdefid");
		String nodeid = request.getParameter("nodeid");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Kvm kvm=kvmService.getById(id);
		if(kvm==null){
			kvm = new Kvm();
		}
		kvm.setActdefid(actdefid);
		kvm.setNodeid(nodeid);
		return getAutoView().addObject("kvm",kvm)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 取得KVM表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看KVM表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Kvm kvm=kvmService.getById(id);
		return getAutoView().addObject("kvm", kvm);
	}
	
}
