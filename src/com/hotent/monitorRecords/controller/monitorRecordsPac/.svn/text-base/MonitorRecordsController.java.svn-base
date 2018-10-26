
package com.hotent.monitorRecords.controller.monitorRecordsPac;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.monitorRecords.model.monitorRecordsPac.MonitorRecords;
import com.hotent.monitorRecords.service.monitorRecordsPac.MonitorRecordsService;
import com.hotent.core.web.ResultMessage;
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRel;
import com.hotent.deviceQuotaRel.service.deviceQuotaRelPac.DeviceQuotaRelService;
/**
 * 对象功能:监控记录表 控制器类
 */
@Controller
@RequestMapping("/monitorRecords/monitorRecordsPac/monitorRecords/")
public class MonitorRecordsController extends BaseController
{
	@Resource
	private MonitorRecordsService monitorRecordsService;
	@Resource
	private DeviceQuotaRelService deviceQuotaRelService;
	@Resource
	private MonitorQuotaService monitorQuotaService;
	//当前页面数据列表
	private List<MonitorRecords> curList = new ArrayList<MonitorRecords>();
	/**
	 * 添加或更新监控记录表。
	 * @param request
	 * @param response
	 * @param monitorRecords 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新监控记录表")
	public void save(HttpServletRequest request, HttpServletResponse response,MonitorRecords monitorRecords) throws Exception
	{
		String resultMsg=null;		
		try{
			if(monitorRecords.getId()==null){
				Long id=UniqueIdUtil.genId();
				monitorRecords.setId(id);
				monitorRecordsService.add(monitorRecords);
				resultMsg=getText("添加","监控记录表");
			}else{
			    monitorRecordsService.update(monitorRecords);
				resultMsg=getText("更新","监控记录表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得监控记录表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看监控记录表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String deviceQuotaRelId = request.getParameter("deviceQuotaRelId");
		List<MonitorRecords> list;
		ModelAndView mv=this.getAutoView();
		if(deviceQuotaRelId!=null){
			DeviceQuotaRel deviceQuotaRel = deviceQuotaRelService.getById(Long.parseLong(deviceQuotaRelId));
			String deviceId = deviceQuotaRel.getDevice_id();
			String quotaId = deviceQuotaRel.getQuota_id();
			MonitorQuota monitorQuota = monitorQuotaService.getById(Long.parseLong(quotaId));
			//是否可以图形化
			String monitorType = monitorQuota.getDeviceType();
			list = monitorRecordsService.getBydeviceidAndquotaid(deviceId, quotaId, null, null,new QueryFilter(request,"monitorRecordsItem"));
			mv.addObject("deviceQuotaRelId",deviceQuotaRelId);
			mv.addObject("monitorType",monitorType);
		}
		else {
			
			list=monitorRecordsService.getAll(new QueryFilter(request,"monitorRecordsItem"));
		}
		curList = list;
		mv.addObject("monitorRecordsList",list);
		
		return mv;
	}
	
	/**
	 * 删除监控记录表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除监控记录表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			monitorRecordsService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除监控记录表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑监控记录表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑监控记录表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		MonitorRecords monitorRecords=monitorRecordsService.getById(id);
		
		return getAutoView().addObject("monitorRecords",monitorRecords)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得监控记录表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看监控记录表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		MonitorRecords monitorRecords=monitorRecordsService.getById(id);
		return getAutoView().addObject("monitorRecords", monitorRecords);
	}
	
	/**
	 * 图形化显示
	 */
	@RequestMapping("listByDeviceQuotaRel")
	@Action(description="查看指定设备中指定指标监控记录表分页列表")
	public ModelAndView listByDeviceAndQuota(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
		String deviceQuotaRelId = request.getParameter("deviceQuotaRelId");
		DeviceQuotaRel deviceQuotaRel = deviceQuotaRelService.getById(Long.parseLong(deviceQuotaRelId));
		String quotaId = deviceQuotaRel.getQuota_id();
		ModelAndView mv = new ModelAndView("monitorRecords/monitorRecordsPac/monitorRecordsList2.jsp");
		MonitorQuota monitorQuota = monitorQuotaService.getById(Long.parseLong(quotaId));
		String quotaName = monitorQuota.getName();
		String oldquotaName = quotaName;
		int num = curList.size();
		String quotaUnit = monitorQuota.getUnit();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//x坐标值
		String[] x = new String[num];
		//y坐标值
		double[] y = new double[num];
		if(curList.size()!=0){
		//截取数字
		String regEx="[^0-9.]";   
		//截取盘符
		String regEx2="[^A-Z/]"; 
		Pattern p = Pattern.compile(regEx);   
		//处理各个分区
		Pattern p2 = Pattern.compile(regEx2); 
		String quotaNameTemp = curList.get(0).getMonitorValue();
		Matcher matcher = p2.matcher(quotaNameTemp);
		quotaNameTemp = matcher.replaceAll("").trim();
		if(!quotaNameTemp.equalsIgnoreCase("")&&!quotaNameTemp.equalsIgnoreCase("MB")){
			int endIndex = quotaNameTemp.indexOf("MB");
			if(endIndex==-1)
				endIndex = quotaNameTemp.length();
			if(endIndex!=-1)
			quotaName = quotaNameTemp.substring(0,endIndex) + quotaName.substring(3);
		}
		//若磁盘没有分类
		for(int i = 0;i < num - 1; ++i){
			
			String pan1 = curList.get(i + 1).getMonitorValue();
			if(!pan1.contains(quotaName.substring(0, 1))){
				quotaName = oldquotaName;
				break;
			}
		}
		//处理网络状态
		if(curList.get(0).getMonitorValue().contains("平均往返时间")){
			quotaName = "平均往返时间";
			quotaUnit = "ms";
			//若没有分类
			for(int i = 1;i < num ; ++i){
				
				String pan1 = curList.get(i).getMonitorValue();
				if(!pan1.contains("平均")){
					quotaName = oldquotaName;
					quotaUnit = "";
					break;
				}
			}
		}
		if(curList.get(0).getMonitorValue().contains("服务成功率")){
			quotaName = "服务成功率";
			quotaUnit = "%";
			//若没有分类
			for(int i = 1;i < num ; ++i){
				
				String pan1 = curList.get(i).getMonitorValue();
				if(!pan1.contains("服务")){
					quotaName = oldquotaName;
					quotaUnit = "";
					break;
				}
			}
		}
		
		for(int i = 0; i<num; ++i){
			x[i] = format.format(curList.get(i).getMonitorTime());
			String MonitorValue = curList.get(i).getMonitorValue();
			Matcher m = p.matcher(MonitorValue);   
			MonitorValue = m.replaceAll("").trim();
			y[i] = Double.valueOf(MonitorValue);
		}
		}
		if(quotaUnit.contains("无"))
			quotaUnit="";
		JSONArray xList = JSONArray.fromObject(x);
		JSONArray yList = JSONArray.fromObject(y);
		mv.addObject("list", curList);
		mv.addObject("quotaName", quotaName);
		mv.addObject("quotaUnit", quotaUnit);
		mv.addObject("xList", xList);
		mv.addObject("yList", yList);
		mv.addObject("deviceQuotaRelId",deviceQuotaRelId);
		return mv;
	}
	
}
