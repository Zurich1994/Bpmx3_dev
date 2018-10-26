
package com.hotent.BusinessCollectCot.controller.lc;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.BusinessCollectCot.model.lc.BusinessCollectCot;
import com.hotent.BusinessCollectCot.service.lc.BusinessCollectCotService;
import com.hotent.BusinessInformat.model.lc.BusinessInformat;
import com.hotent.BusinessInformat.service.lc.BusinessInformatService;
import com.hotent.HistoryData.model.lc.HistoryData;
import com.hotent.HistoryData.service.lc.HistoryDataService;
import com.hotent.RegularOccurrence.model.lc.Regularoccurrence;
import com.hotent.RegularOccurrence.service.lc.RegularoccurrenceService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:业务发生量采集与计算表 控制器类
 */
@Controller
@RequestMapping("/BusinessCollectCot/lc/businessCollectCot/")
public class BusinessCollectCotController extends BaseController
{
	@Resource
	private BusinessCollectCotService businessCollectCotService;
	@Resource
	private HistoryDataService historyDataService;
	@Resource
	private RegularoccurrenceService regularoccurrenceService;
	@Resource
	private BusinessInformatService businessInformatService;
	/**
	 * 添加或更新业务发生量采集与计算表。
	 * @param request
	 * @param response
	 * @param businessCollectCot 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新业务发生量采集与计算表")
	public void save(HttpServletRequest request, HttpServletResponse response,BusinessCollectCot businessCollectCot) throws Exception
	{
		String resultMsg=null;	
		String startTime = request.getParameter("startTime");
		System.out.println("startTime:"+startTime);
		String endTime = request.getParameter("endTime");
		System.out.println("endTime:"+endTime);
		try{
			if(businessCollectCot.getId()==null){
				Long id=UniqueIdUtil.genId();
				businessCollectCot.setId(id);
				businessCollectCot.setStartTime(startTime);
				businessCollectCot.setEndTime(endTime);
				businessCollectCotService.add(businessCollectCot);
				resultMsg=getText("添加","业务发生量采集与计算表");
			}else{
			    businessCollectCotService.update(businessCollectCot);
				resultMsg=getText("更新","业务发生量采集与计算表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得业务发生量采集与计算表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看业务发生量采集与计算表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BusinessCollectCot> list=businessCollectCotService.getAll(new QueryFilter(request,"businessCollectCotItem"));
		ModelAndView mv=this.getAutoView().addObject("businessCollectCotList",list);
		
		return mv;
	}
	
	/**
	 * 删除业务发生量采集与计算表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除业务发生量采集与计算表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			businessCollectCotService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除业务发生量采集与计算表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑业务发生量采集与计算表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑业务发生量采集与计算表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		BusinessCollectCot businessCollectCot=businessCollectCotService.getById(id);
		
		return getAutoView().addObject("businessCollectCot",businessCollectCot)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得业务发生量采集与计算表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看业务发生量采集与计算表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		BusinessCollectCot businessCollectCot=businessCollectCotService.getById(id);
		return getAutoView().addObject("businessCollectCot", businessCollectCot);
	}
	
	
	
	@RequestMapping("showRule")
	@Action(description="显示图表")
	public ModelAndView showRule(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String processId = request.getParameter("processId");
		System.out.println("@processId:"+processId);
		String startTime = request.getParameter("startTime");
		System.out.println(startTime);
		String endTime = request.getParameter("endTime");
		System.out.println(endTime);
		String returnUrl=RequestUtil.getPrePage(request);
		//System.out.println(returnUrl);
		String timeType = new TimeOperate().timeOperate(startTime, endTime);
		System.out.println("@timeType:"+timeType);
		request.getSession().setAttribute("timeType", timeType);
		//List<HistoryData> historyDataList = historyDataService.getProcessId(timeType);
		//System.out.println(historyDataList.size());
		//HistoryData hd = historyDataList.get(0);
		//String processId = hd.getProcessId();
		System.out.println("regularSize:"+regularoccurrenceService.getRegular(processId, timeType).size());
		if(regularoccurrenceService.getRegular(processId, timeType).size() == 0){
			System.out.println("发生规律表里无数据，准备开始导入");
			List<HistoryData> historyDataList2 = historyDataService.getPictureData(processId, timeType);
			System.out.println(historyDataList2.size());
		
			String[] counts = new String[historyDataList2.size()];
			for(String countsStr : counts){
				System.out.println("countsStr:"+countsStr);
			}
			String[] times = new String[historyDataList2.size()];
			for(String timesStr : times){
				System.out.println("countsStr:"+timesStr);
			}
			for(int i=0;i<historyDataList2.size();i++){
				HistoryData hdPicture = historyDataList2.get(i);
				counts[i] = (String.valueOf(hdPicture.getOccurenceAmount()));
				times[i] = (new TimeOperate().timeSubString(hdPicture.getOccurenceTime()));
			}
			List<String> proportionList = new ParamCalculate().paramCalculate(counts, times);
			System.out.println("proportionList:"+proportionList.size());
			System.out.println(counts.length);
			System.out.println(times.length);
			
		
			for(int i=0;i<proportionList.size();i++){
				Long id=UniqueIdUtil.genId();
				Regularoccurrence ro = new Regularoccurrence();
				ro.setId(id);
				ro.setProcessId(processId);
				ro.setTime(times[i]);
				ro.setRegularValue(proportionList.get(i));
				ro.setTimeType(timeType);
				regularoccurrenceService.add(ro);
			}
			
		}else{
			System.out.println("表内已存在该类型数据");
		}
		
	
		return getAutoView().addObject("returnUrl", returnUrl);
	}
	
	@RequestMapping("regularShow")
	@Action(description="显示图表")
	public ModelAndView regularShow(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		String processId = request.getParameter("processId");
		System.out.println("processId:"+processId);
		//String timeType = request.getParameter("timeType");
		String timeType = String.valueOf(request.getSession().getAttribute("timeType"));
		System.out.println(timeType);
		System.out.println("regularShow:"+"显示图表");
		
		//List<Regularoccurrence> roList = regularoccurrenceService.getProcessId(timeType);
		//String processId = roList.get(0).getProcessId();
		//System.out.println("processId:"+processId);
		List<Regularoccurrence> regularDataList = regularoccurrenceService.getRegular(processId, timeType);
		System.out.println(regularDataList.size());
		StringBuffer msg = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>");
		for(int i=0;i<regularDataList.size();i++){
			Regularoccurrence ro = regularDataList.get(i);
			msg.append("<deal time=\" "+ro.getTime()+"\" number1=\""+ro.getRegularValue()+"\" />");
		}
		msg.append("</data>");
		System.out.println(msg.toString());
		PrintWriter out = response.getWriter();
		out.print(msg.toString());
		return null;
		
	}
	
	
	@RequestMapping("showResult")
	@Action(description="显示图表")
	public ModelAndView showResult(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long beginTime = System.currentTimeMillis();
		System.out.println("result");
		String returnUrl=RequestUtil.getPrePage(request);
		String processId = request.getParameter("processId");
		
		String startTime = request.getParameter("startTime");
		System.out.println("startTime:"+startTime);
		String endTime = request.getParameter("endTime");
		System.out.println("endTime:"+endTime);
		String timeType = new TimeOperate().timeOperate(startTime, endTime);
		System.out.println("timeType:"+timeType);
		//List<HistoryData> hdList = historyDataService.getProcessId(timeType);
		//String processId = hdList.get(0).getProcessId();
		System.out.println(processId);
		List<String> occurenceTimeList = new TimeOperate().getOccurenceTimeList(startTime, endTime);
		for(int i=0;i<occurenceTimeList.size();i++){
			System.out.println(occurenceTimeList.get(i));
		}
		String errorMsg = null;
		if(occurenceTimeList.size() == 0){
			errorMsg = "发生量不存在！";
		}
		long result = 0;
		System.out.println("occurenceTimeListSize:"+occurenceTimeList.size());
		for(int i=0;i<occurenceTimeList.size();i++){
			String occurenceTime = occurenceTimeList.get(i);
			String newOccurenceTime = new TimeOperate().getTimeType(timeType, occurenceTime);
			List<HistoryData> occurenceList = historyDataService.getOccurence(processId, newOccurenceTime);
			System.out.println("occurenceList:"+occurenceList.size());
			HistoryData hdOccurence = occurenceList.get(0);
			
			long occurenceAmount = Long.valueOf(hdOccurence.getOccurenceAmount());
			result += occurenceAmount;
		}
		long overTime = System.currentTimeMillis();
		long runTime = overTime - beginTime ;
		
		
		System.out.println("runTime:"+runTime);
		BusinessInformat businessInformat = new BusinessInformat();
		Long id=UniqueIdUtil.genId();
		businessInformat.setId(id);
		businessInformat.setBusinessId(String.valueOf(id));
		businessInformat.setProcessId(processId);
		businessInformat.setStartTime(startTime);
		businessInformat.setEndTime(endTime);
		businessInformat.setOccurenceAmount(result);
		businessInformat.setOperationTime(String.valueOf(runTime));
		businessInformatService.add(businessInformat);
		System.out.println("数据写入成功.....");
		return getAutoView().addObject("returnUrl", returnUrl).addObject("result", result).addObject("errorMsg", errorMsg);
	}
	
}
