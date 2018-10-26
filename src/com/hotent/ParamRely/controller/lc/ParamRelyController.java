
package com.hotent.ParamRely.controller.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.base.core.json.JSONArray;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.FixParam.model.lc.FixParam;
import com.hotent.FixParam.service.lc.FixParamService;
import com.hotent.HistoryData.model.lc.HistoryData;
import com.hotent.HistoryData.service.lc.HistoryDataService;
import com.hotent.ParamRely.model.lc.ParamRely;
import com.hotent.ParamRely.service.lc.ParamRelyService;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.engine.GroovyScriptEngine;
/**
 * 对象功能:参数依赖 控制器类
 */
@Controller
@RequestMapping("/ParamRely/lc/paramRely/")
public class ParamRelyController extends BaseController
{
	String paramMsg = null;
	
	@Resource
	private ParamRelyService paramRelyService;
	@Resource
	private GroovyScriptEngine engine;
	
	@Resource
	private HistoryDataService historyDataService;
	@Resource
	private FixParamService fixParamService;
	/**
	 * 添加或更新参数依赖。
	 * @param request
	 * @param response
	 * @param paramRely 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新参数依赖")
	public void save(HttpServletRequest request, HttpServletResponse response,ParamRely paramRely) throws Exception
	{
		String resultMsg=null;	
		String processId = request.getParameter("processId");
		System.out.println("processId:"+processId);
		request.getSession().setAttribute("processId", processId);
		try{
			if(paramRely.getId()==null){
				Long id=UniqueIdUtil.genId();
				paramRely.setId(id);
				paramRelyService.add(paramRely);
				resultMsg=getText("添加","参数依赖");
			}else{
			    paramRelyService.update(paramRely);
				resultMsg=getText("更新","参数依赖");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得参数依赖分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看参数依赖分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String processId = String.valueOf(request.getSession().getAttribute("processId"));
		List<ParamRely> list=paramRelyService.getAll(new QueryFilter(request,"paramRelyItem"));
		ModelAndView mv=this.getAutoView().addObject("paramRelyList",list).addObject("processId", processId);
		
		return mv;
	}
	
	/**
	 * 删除参数依赖
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除参数依赖")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			paramRelyService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除参数依赖成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑参数依赖
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑参数依赖")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		//System.out.println("新增-修改");
		Long id=RequestUtil.getLong(request,"id");
		//HashMap<String,String> processIdMap = new HashMap<String,String>();
		List<String> processIdList = new ArrayList<String>();
		String timeType = request.getParameter("timeType");
		System.out.println("--timeType--:"+timeType);
		List<HistoryData> historyDataList = historyDataService.getProcessId(timeType);
		
		System.out.println("historyDataList:"+historyDataList.size());
		
		for(int i=0;i<historyDataList.size();i++){
			HistoryData hd = historyDataList.get(i);
			System.out.println(hd.getProcessId());
			//System.out.println(hd.getOccurenceAmount());
			String processId = hd.getProcessId();
			//String occurenceAmount = String.valueOf(hd.getOccurenceAmount());
			System.out.println(processId);
			//System.out.println(occurenceAmount);
			
			
			processIdList.add(processId);
			
			
		}
		
		//JSONObject relyFlow = JSONObject.fromObject( processIdList ); 
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(processIdList);
		String relyFlowStr = jsonArray.toString();
		String newStr = new StrOperate().processIdStrConvert(relyFlowStr);
		request.getSession().setAttribute("newStr",newStr);
		
		System.out.println("跳转成功");
		System.out.println("relyFlowStr:"+relyFlowStr);
		
		
		
		List<FixParam> fixParamList = fixParamService.getParamName(timeType);
		System.out.println("fixParamList:"+fixParamList.size());
		if(fixParamList.size() == 0){
			paramMsg = "请添加固定参数";
		}
		HashMap<String,String> paramMap = new HashMap<String,String>();
		for(int i=0;i<fixParamList.size();i++){
			FixParam fp = fixParamList.get(i);
			System.out.println(fp.getFix_param_name());
			System.out.println(fp.getFix_param_value());
			String paramName = fp.getFix_param_name();
			String paramValue = fp.getFix_param_value();
			paramMap.put(paramName, paramValue);
			System.out.println("paramMapSize:"+paramMap.size());
		}
		JSONObject paramObject = JSONObject.fromObject( paramMap );
		String paramStr = new StrOperate().paramStrConvert(paramObject.toString());
		System.out.println("paramStr?:"+paramStr);
		request.getSession().setAttribute("paramStr",paramStr);
		
		String returnUrl=RequestUtil.getPrePage(request);
		ParamRely paramRely=paramRelyService.getById(id);
		if(BeanUtils.isEmpty(paramRely)){
			paramRely=new ParamRely();
			//String script_script="";
			//paramRely.setScript(engine.executeObject(script_script, null).toString());
			//System.out.println("script");
		}
		
		return getAutoView().addObject("paramRely",paramRely)
							.addObject("returnUrl",returnUrl)
							.addObject("paramMsg",paramMsg);
							
	}

	/**
	 * 取得参数依赖明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看参数依赖明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ParamRely paramRely=paramRelyService.getById(id);
		return getAutoView().addObject("paramRely", paramRely);
	}
	
}
