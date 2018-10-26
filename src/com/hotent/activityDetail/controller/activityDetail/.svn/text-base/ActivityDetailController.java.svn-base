
package com.hotent.activityDetail.controller.activityDetail;

import java.net.URL;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SOAPMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.activityDetail.service.activityDetail.ActivityDetailService;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.soap.SoapUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;


import com.hotent.core.web.ResultMessage;
import com.hotent.platform.model.bpm.BpmNodeWebService;
import com.hotent.platform.model.system.WSystemInformation;
import com.hotent.platform.service.bpm.BpmNodeWebServiceService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.sysinfomation.service.sysinfomation.SysinfomationService;



/**
 * 对象功能:流程右键信息表 控制器类
 */
@Controller
@RequestMapping("/activityDetail/activityDetail/activityDetail/")
public class ActivityDetailController extends BaseController
{
	@Resource
	private ActivityDetailService activityDetailService;
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	@Resource
	private SysinfomationService sysinfomationService;
	@Resource
	private SubSystemService subSystemService;
	/**
	 * 添加或更新流程右键信息表。
	 * @param request
	 * @param response
	 * @param activityDetail 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新流程右键信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,ActivityDetail activityDetail) throws Exception
	{
		String resultMsg=null;		
		try{
			System.out.println("进入保存函数");
			Object[] a=new Object[30];
			System.out.println(activityDetail.toString());
			a=activityDetail.toStringArray();
			int i;
			for(i=0;i<a.length;i++)
			{
				if(a[i].toString().equals(""))break;
			}
			if(i<a.length)
			{writeResultMessage(response.getWriter(),a[i]+"信息没有输入完整"+i,ResultMessage.Fail);}
			else
			{
				if(activityDetail.getId()==null){
				Long id=UniqueIdUtil.genId();
				activityDetail.setId(id);
				activityDetailService.add(activityDetail);
				resultMsg=getText("添加","流程右键信息表");
				}else{
			   activityDetailService.update(activityDetail);
				resultMsg=getText("更新","流程右键信息表");
				}
			
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
			}
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得流程右键信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程右键信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ActivityDetail> list=activityDetailService.getAll(new QueryFilter(request,"activityDetailItem"));
		ModelAndView mv=this.getAutoView().addObject("activityDetailList",list);
		
		return mv;
	}
	
	/**
	 * 删除流程右键信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程右键信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			activityDetailService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除流程右键信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑流程右键信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑流程右键信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		ActivityDetail activityDetail=activityDetailService.getById(id);
		
		return getAutoView().addObject("activityDetail",activityDetail)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得流程右键信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看流程右键信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ActivityDetail activityDetail=activityDetailService.getById(id);
		return getAutoView().addObject("activityDetail", activityDetail);
	}
	
	/**
	 * 设置任务信息方法页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @powered by zonghy
	 */
	@RequestMapping("activityInform")
	public ModelAndView activityInform(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String actDefId = request.getParameter("actDefId");
		String nodeId = request.getParameter("nodeId");
		ActivityDetail adSet = activityDetailService.getByactDefId(actDefId,nodeId);
		if(adSet==null)
		{
			ActivityDetail adSets = new ActivityDetail();
			adSets.setActDef_Id(actDefId);
			adSets.setActivity_id(nodeId);
			return getAutoView().addObject("activityDetail", adSets);
		}
		return getAutoView().addObject("activityDetail", adSet);
				//.addObject("handlersMap",handlersMap)
				//.addObject("parentActDefId", parentActDefId);
	}
	
	
	
	@RequestMapping("operation")    //业务操作分析表1
	@Action(description="跳到另个JSP")
	public ModelAndView operation(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("table")   //业务操作分析表2
	@Action(description="跳到另个JSP")
	public ModelAndView table(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("analysis")   //业务操作分析表3
	@Action(description="跳到另个JSP")
	public ModelAndView analysis(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("service")   //业务操作分析表4
	@Action(description="跳到另个JSP")
	public ModelAndView service(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("handle")   //业务操作分析表5
	@Action(description="跳到另个JSP")
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	

	@RequestMapping("work")   //作业与作业实例对照表
	@Action(description="跳到另个JSP")
	public ModelAndView work(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		 
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	
	@RequestMapping("job")  //操作与操作实例对照表
	@Action(description="跳到另个JSP")
	public ModelAndView job(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	
	@RequestMapping("thing")    //实体交易能力
	@Action(description="跳到另个JSP")
	public ModelAndView thing(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	
	@RequestMapping("data")    //实体交易能力
	@Action(description="跳到另个JSP")
	public ModelAndView data(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("operating")  //操作 级负载 分析 表1
	@Action(description="跳到另个JSP")
	public ModelAndView operloadone(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("load")        //操作 级负载 分析 表2
	@Action(description="跳到另个JSP")
	public ModelAndView operloadtwo(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("three")      //操作 级负载 分析 表3
	@Action(description="跳到另个JSP")
	public ModelAndView operloadthree(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("work1")  //作业级负载 分析 表1
	@Action(description="跳到另个JSP")
	public ModelAndView workLoad(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("work2")  //作业级负载 分析 表2
	@Action(description="跳到另个JSP")
	public ModelAndView workAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("work3")   //作业级负载 分析 表3
	@Action(description="跳到另个JSP")
	public ModelAndView workTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("power1")   //作业级能力需求计算表1
	@Action(description="跳到另个JSP")
	public ModelAndView power1(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("power2")   //作业级能力需求计算表2
	@Action(description="跳到另个JSP")
	public ModelAndView power2(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("system")   //子系统能力需求表
	@Action(description="跳到另个JSP")
	public ModelAndView system(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	

	@RequestMapping("find")  //作业量信息量分析表
	public ModelAndView find(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Long[] ids =RequestUtil.getLongAryByStr(request, "ids") ;//获得jsp中打对钩的子系统id	
		for(Long a:ids)
		System.out.println("获得子系统ids++-----------------------------------+++++++"+a);	
		List<WSystemInformation> sys_information = new ArrayList<WSystemInformation>();//创建子系统链表
		for (int i1=0;i1<ids.length;i1++)//遍历传过来的id【】
		{	
			WSystemInformation sys_info = new WSystemInformation(Long.toString(ids[i1]),subSystemService.getById(ids[i1]).getSysName());//因为通过for循环说明有一个子系统，创建一个子系统对象						
			sys_info=sysdefnodeergodicService.getlianjie(sys_info);	
			sys_information.add(sys_info);	//江子系统放入子系统list中	
		}
		//sysdefnodeergodicService.ceshilianbiao(sys_information);//输出			
		sys_information=sysdefnodeergodicService.sysListBasicStatistics(sys_information);
		sysdefnodeergodicService.get_jisuan_tongji(sys_information);		
		sysinfomationService.writeToWTbaSubsysList(sys_information);//将子系统信息转成我想要的表		
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	
	
	
}
