
package com.hotent.deviceQuotaRel.controller.deviceQuotaRelPac;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.scheduler.SchedulerService;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRel;
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRelAll;
import com.hotent.deviceQuotaRel.service.deviceQuotaRelPac.DeviceQuotaRelService;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:设备指标关联表 控制器类
 */
@Controller
@RequestMapping("/deviceQuotaRel/deviceQuotaRelPac/deviceQuotaRel/")
public class DeviceQuotaRelController extends BaseController
{
	@Resource
	private DeviceQuotaRelService deviceQuotaRelService;
	@Resource
	private MonitorQuotaService monitorQuotaService;
	@Resource
	private SchedulerService schedulerService;
	/**
	 * 添加或更新设备指标关联表。
	 * @param request
	 * @param response
	 * @param deviceQuotaRel 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新设备指标关联表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceQuotaRel deviceQuotaRel) throws Exception
	{
		String resultMsg=null;		
		try{
			if(deviceQuotaRel.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceQuotaRel.setId(id);
				deviceQuotaRelService.add(deviceQuotaRel);
				resultMsg=getText("添加","设备指标关联表");
			}else{
			    deviceQuotaRelService.updateFreq(deviceQuotaRel);
				resultMsg=getText("更新","设备指标关联表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得设备指标关联表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看设备指标关联表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceQuotaRel> list=deviceQuotaRelService.getAll(new QueryFilter(request,"deviceQuotaRelItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceQuotaRelList",list);
		
		return mv;
	}
	
	@RequestMapping("listQuotaName")
	@Action(description="查看设备指标关联表分页列表")
	public ModelAndView listQuotaName(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String deviceid = request.getParameter("id");
		List<DeviceQuotaRelAll> list=deviceQuotaRelService.getDevQuaRelByDevId(deviceid);		
		ModelAndView mv=this.getAutoView().addObject("deviceQuotaRelAllList",list).addObject("deviceid",deviceid);;
		
		return mv;
	}
	
	/**
	 * 删除设备指标关联表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除设备指标关联表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceQuotaRelService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除设备指标关联表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑设备指标关联表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑设备指标关联表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceQuotaRel deviceQuotaRel=deviceQuotaRelService.getById(id);
		
		String quota_name = monitorQuotaService.getById(Long.parseLong(deviceQuotaRel.getQuota_id())).getName();
		return getAutoView().addObject("deviceQuotaRel",deviceQuotaRel)
							.addObject("quota_name", quota_name)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得设备指标关联表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看设备指标关联表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceQuotaRel deviceQuotaRel=deviceQuotaRelService.getById(id);
		return getAutoView().addObject("deviceQuotaRel", deviceQuotaRel);
	}
	
	
	@RequestMapping("saveMulti")
	@Action(description="添加或更新设备指标关联表")
	public void saveMulti(HttpServletRequest request, HttpServletResponse response,DeviceQuotaRel deviceQuotaRel) throws Exception
	{
		String deviceid = request.getParameter("deviceid");
		String[] quotaids = request.getParameter("quotaids").split(","); 
		String resultMsg=null;		
		try{
			for(int i=0;i<quotaids.length;i++) {
				if(!deviceQuotaRelService.exist(deviceid,quotaids[i])) {
					Long id=UniqueIdUtil.genId();
					DeviceQuotaRel dqr = new DeviceQuotaRel();
					dqr.setId(id);
					dqr.setDevice_id(deviceid);
					dqr.setQuota_id(quotaids[i]);				
					deviceQuotaRelService.add(dqr);	
				}
			}
			String returnUrl=RequestUtil.getPrePage(request);
			resultMsg=getText("添加","设备指标关联表");
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
//			return new ModelAndView("redirect:"+returnUrl);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
//			return new ModelAndView("");
		}
	}
		
	
	
	
	
}
