package com.hotent.HistoryData.controller.lc;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.HistoryData.model.lc.HistoryData;
import com.hotent.HistoryData.service.lc.HistoryDataService;
import com.hotent.core.web.ResultMessage;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.service.system.SysFileService;
/**
 * 对象功能:历史数据信息表 控制器类
 */
@Controller
@RequestMapping("/HistoryData/lc/historyDataLc/")
public class HistoryDataController extends BaseController
{
	@Resource
	private HistoryDataService historyDataLcService;
	
	@Resource
	private SysFileService sysFileService;
	/**
	 * 添加或更新历史数据信息表。
	 * @param request
	 * @param response
	 * @param historyDataLc 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新历史数据信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,HistoryData historyDataLc) throws Exception
	{
		String resultMsg=null;	
		String fileId = String.valueOf(request.getSession().getAttribute("fileId"));
		String timeType = request.getParameter("timeType");
		String processId = request.getParameter("processId");
		request.getSession().setAttribute("processId", processId);
		System.out.println("processId:"+processId);
		System.out.println("timeType:"+timeType);
		try{
			if(historyDataLc.getId()==null){
				
				//Long id=UniqueIdUtil.genId();
				//historyDataLc.setId(id);
				SysFile sysFile = sysFileService.getById(Long.parseLong(fileId));
				
				String filePath = sysFile.getFilePath();
				System.out.println(filePath);
				String[] filePaths = filePath.split("\\.");
				System.out.println(filePaths.length);
				//Long processId=UniqueIdUtil.genId();
				
				if(filePaths[1].equals("xls")){
					System.out.println("上传的是xls文件");
					ArrayList<String> list = new ExcelImport().excelOperateToList(filePath);
					HashMap<String,String> map = new ExcelImport().excelOperateToMap(filePath);
					for(int i=0;i<list.size();i++){
						String time = list.get(i);
						System.out.println(time);
						String count = map.get(time);
						System.out.println(count);
						HistoryData historyData = new HistoryData();
						long id = UniqueIdUtil.genId();
						
						historyData.setId(id);
						long historyDataId = UniqueIdUtil.genId();
						historyData.setHistoryDataId(String.valueOf(historyDataId));
						historyData.setProcessId(processId);
						historyData.setTimeType(timeType);
						historyData.setOccurenceAmount(Long.valueOf(count));
						historyData.setOccurenceTime(time);
						historyDataLcService.add(historyData);
						System.out.println("成功写入数据-------"+time+":"+count);
						
					}
				}else{
					System.out.println("上传的不是xls文件,请重新上传");
				}
				
				
				//historyDataLcService.add(historyDataLc);
				resultMsg=getText("添加","历史数据信息表");
				
			
			}else{
			    historyDataLcService.update(historyDataLc);
				resultMsg=getText("更新","历史数据信息表");
			}
			//writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			//writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
			
		}
		//InetAddress addr = InetAddress.getLocalHost();
		//String ip = addr.getHostAddress().toString();
		//String url = "http://" + ip + ":8080/mas/HistoryData/lc/historyDataLc/list.ht";
		//request.getRequestDispatcher("list.ht").forward(request, response);
		response.sendRedirect("list.ht");
		System.out.println("跳转结束");
		
		
	}
	
	/**
	 * 取得历史数据信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看历史数据信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String processId = String.valueOf(request.getSession().getAttribute("processId"));
		System.out.println(processId);
		List<HistoryData> list=historyDataLcService.getAll(new QueryFilter(request,"historyDataLcItem"));
		ModelAndView mv=this.getAutoView().addObject("historyDataLcList",list).addObject("processId", processId);
		
		return mv;
	}
	
	/**
	 * 删除历史数据信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除历史数据信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			historyDataLcService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除历史数据信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑历史数据信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑历史数据信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		String fileId = String.valueOf(request.getSession().getAttribute("fileId"));
		System.out.println(fileId);
		SysFile sysFile = sysFileService.getById(Long.parseLong(fileId));
		
		String filePath = sysFile.getFilePath();
		System.out.println(filePath);
		String[] filePaths = filePath.split("\\.");
		System.out.println(filePaths.length);
		
		if(filePaths[1].equals("xls")){
			System.out.println("上传的是xls文件");
			ArrayList<String> list = new ExcelImport().excelOperateToList(filePath);
			HashMap<String,String> map = new ExcelImport().excelOperateToMap(filePath);
			for(int i=0;i<list.size();i++){
				String time = list.get(i);
				System.out.println(time);
				String count = map.get(time);
				System.out.println(count);
				HistoryData historyData = new HistoryData();
				long id = UniqueIdUtil.genId();
				Long processId=UniqueIdUtil.genId();
				historyData.setId(id);
				long historyDataId = UniqueIdUtil.genId();
				historyData.setHistoryDataId(String.valueOf(historyDataId));
				historyData.setProcessId(String.valueOf(processId));
				historyData.setTimeType("年");
				historyData.setOccurenceAmount(Long.valueOf(count));
				historyData.setOccurenceTime(time);
				historyDataLcService.add(historyData);
				System.out.println("成功写入数据-------"+time+":"+count);
			}
		}else{
			System.out.println("上传的不是xls文件,请重新上传");
		}
		
		
		
		Long id=RequestUtil.getLong(request,"id");
		
		
		String returnUrl=RequestUtil.getPrePage(request);
		HistoryData historyDataLc=historyDataLcService.getById(id);
		
		return getAutoView().addObject("historyDataLc",historyDataLc)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得历史数据信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看历史数据信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		HistoryData historyDataLc=historyDataLcService.getById(id);
		return getAutoView().addObject("historyDataLc", historyDataLc);
	}
	
	@RequestMapping("showHistory")
	@Action(description="显示图表")
	public ModelAndView showHistory(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String returnUrl=RequestUtil.getPrePage(request);
		System.out.println(returnUrl);
		System.out.println("show");
		return getAutoView().addObject("returnUrl", returnUrl);
	}
	
	@RequestMapping("flexShow")
	@Action(description="显示图表")
	public ModelAndView flexShow(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		//String processId = request.getParameter("processId");
		//System.out.println(processId);
		String timeType = request.getParameter("timeType");
		System.out.println(timeType);
		System.out.println("show:"+"显示图表");
		
		List<HistoryData> hdList = historyDataLcService.getProcessId(timeType);
		String processId = hdList.get(0).getProcessId();
		System.out.println("processId:"+processId);
		List<HistoryData> pictureDataList = historyDataLcService.getPictureData(processId,timeType);
		System.out.println(pictureDataList.size());
		StringBuffer msg = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>");
		for(int i=0;i<pictureDataList.size();i++){
			HistoryData hd = pictureDataList.get(i);
			msg.append("<deal time=\" "+hd.getOccurenceTime()+"\" number1=\""+hd.getOccurenceAmount()+"\" />");
		}
		msg.append("</data>");
		System.out.println(msg.toString());
		PrintWriter out = response.getWriter();
		out.print(msg.toString());
		return null;
		
	}
	
}