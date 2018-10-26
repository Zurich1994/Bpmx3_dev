package com.hotent.extension.controller.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRel;
import com.hotent.deviceQuotaRel.service.deviceQuotaRelPac.DeviceQuotaRelService;
import com.hotent.extension.model.monitor.BaseZtreeNode;
import com.hotent.extension.service.monitor.MonitorService;
import com.hotent.monitorDevice.model.monitorDevice.MonitorDevice;
import com.hotent.monitorDevice.service.monitorDevice.MonitorDeviceService;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.service.system.GlobalTypeService;

@Controller
@RequestMapping("/extension/monitor/monitor/")
public class MonitorController extends BaseController {
	@Resource
	GlobalTypeService globalTypeService;
	@Resource
	MonitorDeviceService monitorDeviceService;
	@Resource
	DeviceQuotaRelService deviceQuotaRelService;
	@Resource
	MonitorQuotaService monitorQuotaService;
	
	@RequestMapping("getMonitorTree")
	@ResponseBody
	public List<BaseZtreeNode> getMonitorTree(HttpServletRequest request){
		
		String catKey=RequestUtil.getString(request,"catKey");
		boolean hasRoot=RequestUtil.getInt(request, "hasRoot",1)==1;
		List<GlobalType> list=globalTypeService.getByCatKey(catKey, hasRoot);
		List<BaseZtreeNode> resultlist = new ArrayList<BaseZtreeNode>();
		for(GlobalType gt : list) {
			BaseZtreeNode bzb = new BaseZtreeNode();
			bzb.setTypeId(gt.getTypeId());
			bzb.setParentId(gt.getParentId());
			bzb.setTypeName(gt.getTypeName());
			bzb.setIsLeaf(false);
			resultlist.add(bzb);
		}		
		List<MonitorDevice> list2=monitorDeviceService.getAll(); 
		for(MonitorDevice md : list2) {
			BaseZtreeNode bzb = new BaseZtreeNode();
			bzb.setTypeId(md.getId());
			bzb.setParentId(Long.parseLong(md.getTypeid()));
			bzb.setTypeName(md.getIp());
			bzb.setIsLeaf(false);
			resultlist.add(bzb);
		}
		List<DeviceQuotaRel> list3 = deviceQuotaRelService.getAll();
		for(DeviceQuotaRel dqr : list3) {
			BaseZtreeNode bzb = new BaseZtreeNode();
			bzb.setTypeId(dqr.getId());
			bzb.setParentId(Long.parseLong(dqr.getDevice_id()));
			bzb.setTypeName(monitorQuotaService.getById(Long.parseLong(dqr.getQuota_id())).getName());
			bzb.setIsLeaf(true);
			resultlist.add(bzb);
		}
		return resultlist;
	}
}
