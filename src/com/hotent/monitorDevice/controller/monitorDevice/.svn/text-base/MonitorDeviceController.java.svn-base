
package com.hotent.monitorDevice.controller.monitorDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.script.function.MAP;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.monitorDevice.model.monitorDevice.MonitorDevice;
import com.hotent.monitorDevice.service.monitorDevice.MonitorDeviceService;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.core.web.ResultMessage;
import com.hotent.deviceQuotaRel.service.deviceQuotaRelPac.DeviceQuotaRelService;
/**
 * 对象功能:监控设备 控制器类
 */
@Controller
@RequestMapping("/monitorDevice/monitorDevice/monitorDevice/")
public class MonitorDeviceController extends BaseController
{
	@Resource
	private MonitorDeviceService monitorDeviceService;
	@Resource
	private DeviceQuotaRelService deviceQuotaRelService ;
	@Resource
	private GlobalTypeService globalTypeService;
	/**
	 * 添加或更新监控设备。
	 * @param request
	 * @param response
	 * @param monitorDevice 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新监控设备")
	public void save(HttpServletRequest request, HttpServletResponse response,MonitorDevice monitorDevice) throws Exception
	{
		String resultMsg=null;		
		try{
			if(monitorDevice.getId()==null){
				Long id=UniqueIdUtil.genId();
				monitorDevice.setId(id);
				monitorDeviceService.add(monitorDevice);
				resultMsg=getText("添加","监控设备");
			}else{
			    monitorDeviceService.update(monitorDevice);
				resultMsg=getText("更新","监控设备");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得监控设备分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看监控设备分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<MonitorDevice> list=monitorDeviceService.getAll(new QueryFilter(request,"monitorDeviceItem"));
		ModelAndView mv=this.getAutoView().addObject("monitorDeviceList",list);		
		return mv;
	}

	@RequestMapping("listNew")
	@Action(description="查看监控设备分页列表")
	public ModelAndView listNew(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		List<MonitorDevice> list=monitorDeviceService.getAll(new QueryFilter(request,"monitorDeviceItem"));
		ModelAndView mv=this.getAutoView().addObject("monitorDeviceList",list);
		
		return mv;
	}

	
	/**
	 * 取得B表流程定义分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("list_b")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public ModelAndView listB(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "monitorDeviceItem");
		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		if (typeId != 0 && (typeId + "").length() > 1) {
			GlobalType globalType = globalTypeService.getById(typeId);
			System.out.println("ggggggggggg="+globalType);
			if (globalType != null) {
				filter.getFilters().put("nodePath",
						globalType.getNodePath() + "%");
				
			}
		}
		//查询流程列表
		List<MonitorDevice> list = monitorDeviceService.getAll(filter);	
		ModelAndView mv = getAutoView().addObject("monitorDeviceList", list);
		mv.addObject("typeId", typeId);
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("list_b1")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public ModelAndView listB1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "monitorDeviceItem");
		Long typeId =Long.valueOf(request.getParameter("typeId"));
		if (typeId != 0 && (typeId + "").length() > 1) {
			GlobalType globalType = globalTypeService.getById(typeId);
			System.out.println("ggggggggggg="+globalType);
			if (globalType != null) {
				filter.getFilters().put("nodePath",
						globalType.getNodePath() + "%");	
			}
		}
		List<MonitorDevice> list = monitorDeviceService.getAll(filter);
		ModelAndView mv = new ModelAndView("/monitorDevice/monitorDevice/monitorDeviceList_b.jsp");
		mv.addObject("monitorDeviceList", list);
		mv.addObject("typeId", typeId);
		return mv;
	}
	
	/**
	 * 删除监控设备
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除监控设备")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			monitorDeviceService.delByIds(lAryId);
			deviceQuotaRelService.delBydevIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除监控设备成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	/**
	 * 获取监控值
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("mon")
	@Action(description="获取监控值")
	public void mon(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
		System.out.println("-----");
		ArrayList<Map<String, List<String>>> k= new ArrayList<Map<String, List<String>>>(); 
		//先获取IP
		for(int i=0;i<lAryId.length;i++){
			//遍历得到每一台设备的ID，并且获得与之对应的Ip、OID列表
			Long id =lAryId[i];
			//先获取IP
			String ip = monitorDeviceService.getIPById(id);
			System.out.println(ip);
			//在通过ID去获取监控指标ID
		    List<String> QuotaIDS = monitorDeviceService.getQuotaIDSById(id);
		   System.out.println("-------");
		   List <String> OIDS = new ArrayList<String>();
		   for(int j=0;j<QuotaIDS.size();j++){
			 
			 Long Quotaid = Long.valueOf(QuotaIDS.get(j));
			 String oid = monitorDeviceService.getOIDById(Quotaid);
			 OIDS.add(oid);
			System.out.println(OIDS);
		   }
		  Map<String, List<String>> map = new HashMap<String, List<String> >();  
		  map.put(ip, OIDS);
			k.add(map);
		}
			message=new ResultMessage(ResultMessage.Success, "删除监控设备成功!");
		}catch(Exception ex){
			//message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑监控设备
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑监控设备")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Long typeId = RequestUtil.getLong(request,"typeId",0);
		String returnUrl=RequestUtil.getPrePage(request);
		//
		MonitorDevice monitorDevice=monitorDeviceService.getById(id);
		if(id==0||id.equals("")){
			monitorDevice=new MonitorDevice();
			monitorDevice.setTypeid(typeId.toString());
		}
		return getAutoView().addObject("monitorDevice",monitorDevice)
							.addObject("returnUrl",returnUrl)
							.addObject("typeId",typeId);
		
	}

	/**
	 * 取得监控设备明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看监控设备明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		MonitorDevice monitorDevice=monitorDeviceService.getById(id);
		return getAutoView().addObject("monitorDevice", monitorDevice);
	}
	
	/**
	 * 显示添加所有监控界面
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("showAllMonitorAdd")
	@Action(description="查看监控设备明细")
	public ModelAndView showAllMonitorAdd(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println(getAutoView().getViewName()); 
		return getAutoView();
	}
	
	
	
}
