package com.hotent.schedule.controller.schedulePac;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.scheduler.SchedulerService;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRelAll;
import com.hotent.deviceQuotaRel.service.deviceQuotaRelPac.DeviceQuotaRelService;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.platform.service.bpm.thread.MessageUtil;


/**
 * 
 * 对象功能:设备指标关联表 控制器类
 */
@Controller
@RequestMapping("/schedule/schedulePac/schedule/")
public class ScheduleController extends BaseController
{		
	@Resource
	private DeviceQuotaRelService deviceQuotaRelService;
	@Resource
	
	private MonitorQuotaService monitorQuotaService;
	@Resource
	private SchedulerService schedulerService;	
	
	
	@RequestMapping("allMonitorAdd")
	@Action(description="设置所有监控")
	public ModelAndView allMonitorAdd(HttpServletResponse response, HttpServletRequest request) throws Exception 
	{		
		PrintWriter out=response.getWriter();
			try
				{
					String planJson=RequestUtil.getString(request,"planJson");
					List<DeviceQuotaRelAll> relAllList = deviceQuotaRelService.getAllAll();
					String jobName = "";
					String className = "";
					String deviceIp = "";
					String OID = "";
					long deviceId;
					long quotaId;
					long snmp_port;
					String community = "";
					String trigName = "";
					boolean isExistJob= false;
					boolean isExistTrigger =false;
					for(DeviceQuotaRelAll relAll : relAllList) {
						jobName = String.valueOf(relAll.getId());
						trigName = jobName;
						isExistJob=schedulerService.isJobExists(jobName);
						if(!isExistJob) {
							className = relAll.getQuota().getClassName();		//根据指标获取className relAll.getClassName()
							deviceIp = relAll.getDevice().getIp();
							OID = relAll.getQuota().getOID();
							snmp_port = relAll.getDevice().getPort();
							deviceId = relAll.getDevice().getId();
							quotaId = relAll.getQuota().getId();
							community = relAll.getDevice().getCommunity();
							Map parameterMap = new HashMap();
							parameterMap.put("deviceIp", deviceIp);
							parameterMap.put("OID", OID);
							parameterMap.put("snmp_port", snmp_port);
							parameterMap.put("OID", OID);
							parameterMap.put("community", community);
							parameterMap.put("deviceId", deviceId);
							parameterMap.put("quotaId", quotaId);
							schedulerService.addMonitorJob(jobName, className, parameterMap);
						}
						isExistTrigger=schedulerService.isTriggerExists(trigName);
						if(isExistTrigger)
						{
							schedulerService.delTrigger(trigName);
						}						
						schedulerService.addTrigger(jobName, trigName, planJson);												
					}
					//schedulerService.activeAllMo();
					ResultMessage obj=new ResultMessage(ResultMessage.Success,"设置所有监控成功!");
					out.print(obj.toString());
				}								
				catch (Exception ex)
				{
					String str = MessageUtil.getMessage();
					if (StringUtil.isNotEmpty(str)) {
						ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"设置所有监控失败:" + str);
						response.getWriter().print(resultMessage);
					} else {
						String message = ExceptionUtil.getExceptionMessage(ex);
						ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
						response.getWriter().print(resultMessage);
					}
				}
		return null;
	}
	
	@RequestMapping("delAllMon")
	@Action(description="删除所有监控")
	public void delAllMon(HttpServletResponse response, HttpServletRequest request) throws Exception 
	{
		ResultMessage message=null;
		try {
			schedulerService.delAllMon();
			message=new ResultMessage(ResultMessage.Success,"删除所有监控成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除所有监控失败:"+e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(RequestUtil.getPrePage(request));
	}
	
	@RequestMapping("pauseAllMon")
	@Action(description="暂停所有监控")
	public void pauseAllMon(HttpServletResponse response, HttpServletRequest request) throws Exception 
	{
		ResultMessage message=null;
		try {
			schedulerService.pauseAllMo();
			message=new ResultMessage(ResultMessage.Success,"暂停所有监控成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"暂停所有监控失败:"+e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(RequestUtil.getPrePage(request));
	}
	
	@RequestMapping("activeAllMon")
	@Action(description="激活所有监控")
	public void activeAllMon(HttpServletResponse response, HttpServletRequest request) throws Exception 
	{
		ResultMessage message=null;
		try {
			schedulerService.activeAllMo();
			message=new ResultMessage(ResultMessage.Success,"激活所有监控成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"激活所有监控失败:"+e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(RequestUtil.getPrePage(request));
	}
	
	
	
	
	
	
}
