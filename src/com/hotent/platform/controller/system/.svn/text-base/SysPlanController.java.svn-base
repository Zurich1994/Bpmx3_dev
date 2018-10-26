package com.hotent.platform.controller.system;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.StringPool;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.dao.system.SysPlanExchangeDao;
import com.hotent.platform.dao.system.SysPlanParticipantsDao;
import com.hotent.platform.dao.system.SysPlanSubscribeDao;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.bpm.BpmProCopyto;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysPlan;
import com.hotent.platform.model.system.SysPlanExchange;
import com.hotent.platform.model.system.SysPlanParticipants;
import com.hotent.platform.model.system.SysPlanSubscribe;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmProCopytoService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.system.IUserUnderService;
import com.hotent.platform.service.system.SysPlanService;
import com.hotent.platform.service.system.SysUserService;
/**
 *<pre>
 * 对象功能:自定义别名脚本表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysPlan/")
public class SysPlanController extends BaseController
{

	@Resource
	private SysPlanService sysPlanService;
	
	@Resource
	private SysPlanParticipantsDao sysPlanParticipantsDao;
	
	@Resource
	private SysPlanExchangeDao sysPlanExchangeDao;
	
	@Resource
	private SysPlanSubscribeDao sysPlanSubscribeDao;
	
	@Resource
	private BpmProCopytoService bpmProCopytoService;
	
	@Resource
	private ProcessRunService  processRunService;
	
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	
	@Resource
	private IUserUnderService iUserUnderService;
	
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 添加或更新日程表。
	 * @param request
	 * @param response
	 * @param sysPlan 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新日程表")
	public void save(HttpServletRequest request, HttpServletResponse response,SysPlan sysPlan) throws Exception{
		String resultMsg="保存日程失败！";		
		try{
			String participantIds = RequestUtil.getString(request, "participantIds", "");
			Date startTime = RequestUtil.getDate(request, "startTime" ,StringPool.DATE_FORMAT_DATETIME_NOSECOND);
			sysPlan.setStartTime(startTime);
			Date endTime = RequestUtil.getDate(request, "endTime" ,StringPool.DATE_FORMAT_DATETIME_NOSECOND);
			sysPlan.setEndTime(endTime);
			String participants = RequestUtil.getString(request, "participants", "");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("participantIds", participantIds);
			params.put("participants", participants);
			resultMsg = sysPlanService.saveSysPlan(sysPlan, params);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得月份的我提交日程列表,并response列表的JSON字符串
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listBySubmitAndMonth")
	@Action(description="取得月份的我提交日程列表，并response列表的JSON字符串")
	public void listBySubmitAndMonth(HttpServletRequest request,HttpServletResponse response){	
		try {
			String json = getSysPlanListJson(request, response,"submit");
			response.getWriter().print(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * 取得月份的我负责的日程列表,并response列表的JSON字符串
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listByChargeAndMonth")
	@Action(description="取得月份的我负责的日程列表,并response列表的JSON字符串")
	public void listByChargeAndMonth(HttpServletRequest request,HttpServletResponse response){	
		try {
			String json = getSysPlanListJson(request, response,"charge");
			response.getWriter().print(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 取得月份的我参与的日程列表,并response列表的JSON字符串
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listByParticipantAndMonth")
	@Action(description="取得月份的我参与的日程列表,并response列表的JSON字符串")
	public void listByParticipantAndMonth(HttpServletRequest request,HttpServletResponse response){	
		try {
			String json = getSysPlanListJson(request, response,"participant");
			response.getWriter().print(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * 取得月份的我订阅的日程列表,并response列表的JSON字符串
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listBySubscribeAndMonth")
	@Action(description="取得月份的我订阅的日程列表,并response列表的JSON字符串")
	public void listBySubscribeAndMonth(HttpServletRequest request,HttpServletResponse response){	
		try {
			String json = getSysPlanListJson(request, response,"subscribe");
			response.getWriter().print(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String getSysPlanListJson(HttpServletRequest request,HttpServletResponse response,String type) throws ParseException {
		long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
		Date selectDate = RequestUtil.getDate(request, "selectDate", StringPool.DATE_FORMAT_DATE);
		Date startDate = RequestUtil.getDate(request, "startDate", StringPool.DATE_FORMAT_DATE);
		Date endDate = RequestUtil.getDate(request, "endDate", StringPool.DATE_FORMAT_DATE);
		List<SysPlan> sysPlanList =  null;
		
		//只要中间时间的这个月份时间的信息,所以只要中间的时间
		/*if(selectDate==null){
			if(startDate!=null&&endDate!=null){
				int num = DateUtil.daysBetween(startDate, endDate);
				if(num>1){
					int days = num/2;
					Calendar calendar=Calendar.getInstance();  
					calendar.setTime(startDate); 
					calendar.add(Calendar.DAY_OF_MONTH,days);
					selectDate = calendar.getTime();
				}else{
					//相同一天或者相隔一天的， 就选两个时间其中一个
					selectDate = startDate;
				}
			}
		}*/
		
		if("submit".equals(type)){
			sysPlanList = sysPlanService.getBySubmitId(userId, startDate, endDate, selectDate);
		}else if("charge".equals(type)){
			sysPlanList = sysPlanService.getByChargeId(userId, startDate, endDate, selectDate);
		}else if("participant".equals(type)){
			sysPlanList = sysPlanService.getByParticipantId(userId, startDate, endDate, selectDate);
		}else if("subscribe".equals(type)){
			sysPlanList = sysPlanService.getBySubscribeId(userId, startDate, endDate, selectDate);
		}
		String json = "";
		if(sysPlanList!=null&&sysPlanList.size()>0){
			json = getSysPlanJsonStr(sysPlanList);
		}
		return json;
	}
	
	
	/**
	 * 进入日程订阅总页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("underMatters")
	@Action(description="进入日程订阅总页面")
	public ModelAndView underMatters(HttpServletRequest request,HttpServletResponse response) throws Exception {	
		ModelAndView mv=this.getAutoView();
		return mv.addObject("userId",ContextUtil.getCurrentUserId());
	}
	
	
	/**
	 * 取得下属负责的日程列表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("underList")
	@Action(description="取得下属负责的日程列表分页列表")
	public ModelAndView underList(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		QueryFilter queryFilter = new QueryFilter(request,"sysPlanItem");
		//Date  = RequestUtil.getDate(request, "");
		long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
		Long queryUserId = RequestUtil.getLong(request, "queryUserId",0);  // 获取当前用户的一下属的id
		if(queryUserId == userId ){
			queryUserId = 0l;
		}
		List<SysPlan> sysPlanList=sysPlanService.getUnderSysPlanByUserId(userId,queryUserId,queryFilter);
		ModelAndView mv=this.getAutoView().addObject("sysPlanList",sysPlanList).addObject("queryUserId",queryUserId);
		return mv;
	}
	
	
	/**
	 * 取得我订阅的日程列表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subscribeList")
	@Action(description="取得我订阅的日程列表分页列表")
	public ModelAndView subscribeList(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		QueryFilter queryFilter = new QueryFilter(request,"sysPlanItem");
		//Date  = RequestUtil.getDate(request, "");
		long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
		List<SysPlan> sysPlanList=sysPlanService.getListBySubscribeId(userId, queryFilter);
		ModelAndView mv=this.getAutoView().addObject("sysPlanList",sysPlanList);
		return mv;
	}
	
	
	
	/**
	 * 取得下属负责列表
	 * @param request
	 * @param response

	 * @throws Exception
	 */
	@RequestMapping("underUserList")
	@Action(description="取得下属负责列表")
	public void underUserList(HttpServletRequest request,HttpServletResponse response){	
		try {
			long queryUserId = RequestUtil.getLong(request, "queryUserId",0);  // 获取用户的一下属json
			if(queryUserId <1){
				queryUserId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
			}		
			List<SysUser> sysUserList = iUserUnderService.getMyUnderUser(queryUserId);
			String json = "";
			if(sysUserList!=null&&sysUserList.size()>0){
				SysUser sysUser = sysUserService.getById(queryUserId);
				json = getUserJsonStr(sysUser, sysUserList);
			}
			response.getWriter().print(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * 把日程列表信息转为JSON数组信息
	 */
	private String getUserJsonStr(SysUser parentSysUser, List<SysUser> sysUserList){	
		JSONArray jsonAry = new JSONArray();
		
		//主用
		long userId = ContextUtil.getCurrentUserId(); // 获取当前用户的id
		if(userId == parentSysUser.getUserId()){
			JSONObject root =  new JSONObject();
			root.accumulate("id", parentSysUser.getUserId())
			.accumulate("parentId","0")
			.accumulate("title", "我["+parentSysUser.getFullname()+"]")
			.accumulate("name", "我["+parentSysUser.getFullname()+"]")
			.accumulate("sysUser", JSONObject.fromObject(parentSysUser));
			jsonAry.add(root);
		}
		
		
		for (SysUser sysUser : sysUserList) {
			JSONObject json =  new JSONObject();
			JSONObject sysUserJson = JSONObject.fromObject(sysUser);
			json.accumulate("id", sysUser.getUserId())
			.accumulate("parentId",parentSysUser.getUserId())
			.accumulate("title", sysUser.getFullname())
			.accumulate("name", sysUser.getFullname())
			.accumulate("sysUser", sysUserJson);
			jsonAry.add(json);
		} 
		return jsonAry.toString();
	}
	
	
	
	/**
	 * 进入 我提交的日程展示视图
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("submit")
	@Action(description="进入我提交日程展示视图")
	public ModelAndView submit(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		String currentViweDate = RequestUtil.getString(request, "currentViweDate", "");
		mv.addObject("currentViweDate", currentViweDate);
		return mv;
	}
	
	
	
	/**
	 * 进入 我负责的日程展示视图
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("charge")
	@Action(description="进入 我负责的日程展示视图")
	public ModelAndView charge(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		String currentViweDate = RequestUtil.getString(request, "currentViweDate", "");
		mv.addObject("currentViweDate", currentViweDate);
		return mv;
	}
	
	
	/**
	 * 进入 我订阅的日程展示视图
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subscribe")
	@Action(description="进入我订阅日程展示视图")
	public ModelAndView subscribe(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		String currentViweDate = RequestUtil.getString(request, "currentViweDate", "");
		mv.addObject("currentViweDate", currentViweDate);
		return mv;
	}
	
	
	/**
	 * 进入 我参与的日程展示视图
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("participant")
	@Action(description="进入我参与日程展示视图")
	public ModelAndView participant(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		String currentViweDate = RequestUtil.getString(request, "currentViweDate", "");
		mv.addObject("currentViweDate", currentViweDate);
		return mv;
	}
	
	
	/**
	 * 进入日程编辑
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="进入日程编辑")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		makeSysPlan(request, response, mv);
		return mv;
	}
	
	
	/**
	 * 进入日程详情
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="进入日程编辑")
	public ModelAndView get(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		makeSysPlan(request, response, mv);
		return mv;
	}
	
	
	/**
	 * 进入日程交流页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exchange")
	@Action(description="进入日程交流页面")
	public ModelAndView exchange(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		makeSysPlan(request, response, mv);
		return mv;
	}
	
	/**
	 * 进入参与者日程交流页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("participantToExchange")
	@Action(description="进入参与者日程交流页面")
	public ModelAndView participantToExchange(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		makeSysPlan(request, response, mv);
		return mv;
	}
	
	/**
	 * 进入我订阅的日程交流页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subscribeToExchange")
	@Action(description="进入我订阅的日程交流页面")
	public ModelAndView subscribeToExchange(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		makeSysPlan(request, response, mv);
		return mv;
	}
	
	/**
	 * 进入日程订阅信息查看页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subscribeGet")
	@Action(description="进入日程订阅信息查看页面")
	public ModelAndView subscribeGet(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		mv = makeSysPlan(request, response, mv);
		Long subscribeId = RequestUtil.getLong(request, "subscribeId",0);
		Long queryUserId = RequestUtil.getLong(request, "queryUserId",0);
		mv.addObject("queryUserId", queryUserId);
		if(subscribeId<1){
			return mv;
		}
		Map<String,Object> map = mv.getModelMap();
		SysPlan sysPlan = (SysPlan) map.get("sysPlan");
		if(sysPlan!=null){
			sysPlan.setSubscribeId(subscribeId);
			mv.addObject("sysPlan", sysPlan);
		}
		return mv;
	}
	
	
	/**
	 * 获取日程信息
	 */
	private ModelAndView makeSysPlan(HttpServletRequest request,HttpServletResponse response,ModelAndView mv) throws Exception{	
		if(mv==null){
			mv=this.getAutoView();
		}
		Long id = RequestUtil.getLong(request, "id", 0);
		String currentViweDate = RequestUtil.getString(request, "currentViweDate", "");
		mv.addObject("currentViweDate", currentViweDate);
		String type = RequestUtil.getString(request, "type", "");
		mv.addObject("type", type);
		SysPlan sysPlan = new SysPlan();
		if(id>0){
			//日程
			sysPlan = sysPlanService.getById(id);
			mv.addObject("sysPlan", sysPlan);
			//日程参与者
			List<SysPlanParticipants> sysPlanParticipantsList = sysPlanParticipantsDao.getByPlanId(id);
			String participantIds = "";
			String participants=""; 
			if(sysPlanParticipantsList!=null&&sysPlanParticipantsList.size()>0){
				for (SysPlanParticipants sysPlanParticipants : sysPlanParticipantsList) {
					participantIds += sysPlanParticipants.getParticipantId()+",";
					participants += sysPlanParticipants.getParticipant()+",";
				}
				participantIds = participantIds.substring(0, participantIds.length()-1);
				participants = participants.substring(0, participants.length()-1);
			}
			mv.addObject("participantIds", participantIds);
			mv.addObject("participants", participants);
			//日程交流表
			List<SysPlanExchange> sysPlanExchangeList = sysPlanExchangeDao.getByPlanId(id);
			mv.addObject("sysPlanExchangeList", sysPlanExchangeList);
		}else{
			sysPlan.setSubmitId(ContextUtil.getCurrentUserId());
			sysPlan.setSubmitor(ContextUtil.getCurrentUser().getFullname());
			sysPlan.setChargeId(ContextUtil.getCurrentUserId());
			sysPlan.setCharge(ContextUtil.getCurrentUser().getFullname());
			sysPlan.setCreateTime(new Date());
			mv.addObject("sysPlan", sysPlan);
		}
		return mv;
	}
	
	/**
	 * 添加或更新日程交流信息。
	 * @param request
	 * @param response
	 * @param sysPlan 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveExchange")
	@Action(description=" 添加或更新日程交流信息")
	public void saveExchange(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultMsg="保存日程交流信息失败！";	
   		SysUser user = ContextUtil.getCurrentUser();
		try{
	   		Long id = RequestUtil.getLong(request, "id", 0);
	   		Long planId = RequestUtil.getLong(request, "planId", 0);
	   		String doc = RequestUtil.getString(request, "doc", "");
	   		String content = RequestUtil.getString(request, "content", "");
			SysPlanExchange sysPlanExchange = new SysPlanExchange();
	   		sysPlanExchange.setId(id);
	   		sysPlanExchange.setPlanId(planId);
	   		sysPlanExchange.setContent(content);
	   		sysPlanExchange.setDoc(doc);
	   		sysPlanExchange.setSubmitId(user.getUserId());
	   		sysPlanExchange.setSubmitor(user.getFullname());
	   		sysPlanExchange.setCreateTime(new Date());
			resultMsg = sysPlanService.saveSysPlanExchange(sysPlanExchange, null);
			JSONObject json = JSONObject.fromObject(sysPlanExchange);
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setMessage(resultMsg);
			resultMessage.setResult(ResultMessage.Success);
			resultMessage.setCause(json.toString());
			writeResultMessage(response.getWriter(), resultMessage);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 进入日程交流编辑交流信息页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exchangeEdit")
	@Action(description="进入日程交流编辑交流信息页面")
	public ModelAndView exchangeEdit(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		mv = makeSysPlanExchange(request, response, mv);
		return mv;
	}
	
	
	/**
	 * 进入日程交流信息查看页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exchangeGet")
	@Action(description="进入日程交流信息查看页面")
	public ModelAndView exchangeGet(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		mv = makeSysPlanExchange(request, response, mv);
		return mv;
	}
	
	
	/**
	 * 获取日程交流信息
	 */
	private ModelAndView makeSysPlanExchange(HttpServletRequest request,HttpServletResponse response,ModelAndView mv) throws Exception{	
		if(mv==null){
			mv=this.getAutoView();
		}
		Long id = RequestUtil.getLong(request, "id", 0);
		Long planId = RequestUtil.getLong(request, "planId", 0);
		String currentViweDate = RequestUtil.getString(request, "currentViweDate", "");
		mv.addObject("currentViweDate", currentViweDate);
		String type = RequestUtil.getString(request, "type", "");
		mv.addObject("type", type);
		if(id>0L){
			SysPlanExchange sysPlanExchange = sysPlanExchangeDao.getById(id);
			if(sysPlanExchange!=null){
				planId = sysPlanExchange.getPlanId();
			}
			mv.addObject("sysPlanExchange", sysPlanExchange);
		}
		mv.addObject("planId", planId);
		return mv;
	}
	
	
	/**
	 * 删除日程信息。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	@Action(description="删除日程信息")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultMsg="删除日程失败！";		
		try{
			String ids = RequestUtil.getString(request, "ids", "");
			String [] idArry = ids.split("\\,");
			resultMsg = sysPlanService.deleteSysPlans(idArry);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 删除日程交流信息。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteExchange")
	@Action(description="删除日程交流信息")
	public void deleteExchange(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultMsg="删除日程交流信息失败！";		
		try{
			String ids = RequestUtil.getString(request, "ids", "");
			String [] idArry = ids.split("\\,");
			resultMsg = sysPlanService.deleteSysPlanExchanges(idArry);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 更新日程的任务情况。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chargeSysPlans")
	@Action(description="更新日程的任务情况")
	public void chargeSysPlans(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultMsg="更新日程状态成功！";		
		try{
			Long rate = RequestUtil.getLong(request, "rate", 0);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("rate", rate);
			String ids = RequestUtil.getString(request, "ids", "");
			String [] idArry = ids.split("\\,");
			resultMsg = sysPlanService.changeSysPlans(idArry,params);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 把日程列表信息转为JSON数组信息
	 */
	private String getSysPlanJsonStr(List<SysPlan> sysPlanList){	
		JSONArray jsonAry = new JSONArray();
		for (int i = 0; i < sysPlanList.size(); i++) {
			SysPlan sysPlan = sysPlanList.get(i);
			JSONObject json =  new JSONObject();
			JSONObject sysPlanJson = JSONObject.fromObject(sysPlan);
			
			json.accumulate("id", sysPlan.getId())
			.accumulate("title", sysPlan.getTaskName())
			.accumulate("start", DateFormatUtil.format(sysPlan.getStartTime(), StringPool.DATE_FORMAT_DATETIME))
			.accumulate("end", DateFormatUtil.format(sysPlan.getEndTime(), StringPool.DATE_FORMAT_DATETIME))
			.accumulate("backgroundColor", "#D9534F")
			.accumulate("planId", sysPlan.getId())
			.accumulate("sysPlan", sysPlanJson);
			
			jsonAry.add(json);
		} 
		return jsonAry.toString();
	}
	
	
	/**
	 * 订阅日程
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subscribeSysPlan")
	@Action(description="订阅日程")
	public void subscribeSysPlan(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultMsg="订阅日程失败！";		
		try{
			SysUser user = ContextUtil.getCurrentUser();
			Long planId = RequestUtil.getLong(request, "planId", 0);
            SysPlanSubscribe ps = new SysPlanSubscribe();
            ps.setId(UniqueIdUtil.genId());
            ps.setPlanId(planId);
            ps.setSubscribeId(user.getUserId());
            ps.setSubscribe(user.getFullname());
            ps.setSubscribeTime(new Date());
            sysPlanSubscribeDao.add(ps);
            resultMsg="订阅日程成功！";	
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取消日程订阅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cancelSysPlan")
	@Action(description="取消日程订阅")
	public void cancelSysPlan(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultMsg="取消日程订阅失败！";		
		try{
			Long id = RequestUtil.getLong(request, "id", 0);    
            sysPlanSubscribeDao.delById(id);
            resultMsg="取消日程订阅成功！";	
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得流程结束工单的对话框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("processRunDialog")
	@Action(description="取得流程结束工单的对话框")
	public ModelAndView processRunDialog(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		String isSingle = RequestUtil.getString(request, "isSingle", "yes");
		return mv.addObject("isSingle", isSingle);
	}
	
	
	
	/**
	 * 取得流程结束工单的分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("processRunList")
	@Action(description="取得流程结束工单的分页列表")
	public ModelAndView processRunList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long userId = ContextUtil.getCurrentUserId();
		//查询抄送给我的流程实例工单  （不要分页）
		QueryFilter filter = new QueryFilter(request,false,"processRunItem");
		filter.getFilters().put("ccUid", userId);
		List<BpmProCopyto> bpmProCopytoList=bpmProCopytoService.getMyList(filter);
        String actRights = "";
        for (BpmProCopyto bpmProCopyto : bpmProCopytoList) {
        	actRights += "'"+bpmProCopyto.getRunId()+"',";
		}
		
        // 获得流程分管授权与用户相关的信息
		Map<String, Object> actRightMap = bpmDefAuthorizeService.getActRightByUserMap(userId, BPMDEFAUTHORIZE_RIGHT_TYPE.INSTANCE, false, true);
		// 获得流程分管授权与用户相关的信息集合的流程KEY
		String defRights = (String) actRightMap.get("authorizeIds");
		if(StringUtil.isNotEmpty(actRights)&&StringUtil.isNotEmpty(defRights)){
			actRights += defRights;
		}else if(StringUtil.isNotEmpty(actRights)&&StringUtil.isEmpty(defRights)){
			actRights = actRights.substring(0, actRights.length()-1);
		}else if(StringUtil.isEmpty(actRights)&&StringUtil.isNotEmpty(defRights)){
			actRights = defRights;
		}else{
			actRights = "";
		}
		
		//查询已结束并与我相关的流程工单
		QueryFilter processfilter = new QueryFilter(request,"processRunItem");
		processfilter.addFilter("actRights", actRights);
		processfilter.addFilter("isNeedRight", "yes");
		processfilter.addFilter("status", ProcessRun.STATUS_FINISH);
		List<ProcessRun> processRunList = processRunService.getAll(processfilter);
		
		ModelAndView mv=this.getAutoView().addObject("processRunList",processRunList);
		String isSingle = RequestUtil.getString(request, "isSingle", "yes");
		mv.addObject("isSingle", isSingle);
		return mv;
	}
	
	/**
	 * 取得我负责的日程和我 参与的日程列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myList")
	@Action(description = "查看系统配置参数表分页列表")
	public ModelAndView myList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "sysPlanItem");
		filter.addFilter("userId", ContextUtil.getCurrentUserId());
		List<SysPlan> list = sysPlanService.getMyList(filter);
		
		ModelAndView mv = this.getAutoView().addObject("sysPlanList", list)
			.addObject("curUserId", ContextUtil.getCurrentUserId());
		return mv;
	}
	
	/**
	 * 删除我负责的日程
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除我负责的日程")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String preUrl= RequestUtil.getPrePage(request);
		String[] lAryId =RequestUtil.getStringAryByStr(request, "ids");
		sysPlanService.deleteSysPlans(lAryId);
		response.sendRedirect(preUrl);
	}
	
}
