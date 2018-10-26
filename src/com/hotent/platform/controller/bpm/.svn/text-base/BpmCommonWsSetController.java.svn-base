package com.hotent.platform.controller.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmCommonWsParams;
import com.hotent.platform.model.bpm.BpmCommonWsSet;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmCommonWsSetService;
import com.hotent.platform.service.bpm.WebserviceHelper;
/**
 *<pre>
 * 对象功能:通用webservice调用设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-10-17 10:09:20
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpmCommonWsSet/bpmCommonWsSet/")
@Action(ownermodel=SysAuditModelType.PROCESS_AUXILIARY)
public class BpmCommonWsSetController extends BaseController
{
	@Resource
	private BpmCommonWsSetService bpmCommonWsSetService;
	
	
	/**
	 * 添加或更新通用webservice调用设置。
	 * @param request
	 * @param response
	 * @param bpmCommonWsSet 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		JSONObject result = new JSONObject();
		try{
			BpmCommonWsSet bpmCommonWsSet = getFormObject(request);
			
			if(bpmCommonWsSet.getId()==null||bpmCommonWsSet.getId()==0){
				bpmCommonWsSet.setId(UniqueIdUtil.genId());
				bpmCommonWsSetService.addAll(bpmCommonWsSet);			
			}else{
			    bpmCommonWsSetService.updateAll(bpmCommonWsSet);
			}
			result.accumulate("result", ResultMessage.Success).accumulate("message", "保存成功");
		}catch(Exception e){
			result.accumulate("result", ResultMessage.Fail).accumulate("message", e.getMessage());
		}
		return result.toString();
	}
	
	/**
	 * 取得 BpmCommonWsSet 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected BpmCommonWsSet getFormObject(HttpServletRequest request) throws Exception {
		String json = RequestUtil.getString(request, "setObj");
		String customParams = RequestUtil.getString(request, "customParams");
		
		if(StringUtil.isEmpty(json))return null;
		JSONObject jobject = JSONObject.fromObject(json);
		BpmCommonWsSet bpmCommonWsSet = (BpmCommonWsSet)JSONObject.toBean(jobject,BpmCommonWsSet.class);
		
		if(StringUtil.isNotEmpty(customParams)){
			JSONArray obj = JSONArray.fromObject(customParams);
			if(BeanUtils.isNotEmpty(obj)){
				List<BpmCommonWsParams> list = JSONArray.toList(obj,BpmCommonWsParams.class);
				bpmCommonWsSet.setBpmCommonWsParamsList(list);
			}
		}
		return bpmCommonWsSet;
    }
	
	/**
	 * 取得通用webservice调用设置分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看通用webservice调用设置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmCommonWsSet> list=bpmCommonWsSetService.getAll(new QueryFilter(request,"bpmCommonWsSetItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmCommonWsSetList",list);
		
		return mv;
	}
	
	/**
	 * 删除通用webservice调用设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除通用webservice调用设置")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			bpmCommonWsSetService.delAll(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除通用webservice调用设置及其从表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑通用webservice调用设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑通用webservice调用设置")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		BpmCommonWsSet bpmCommonWsSet=bpmCommonWsSetService.getById(id);
		
		return getAutoView().addObject("bpmCommonWsSet",bpmCommonWsSet)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * webservice测试
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("test")
	@Action(description="查看通用webservice调用设置明细")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"setId");
		BpmCommonWsSet bpmCommonWsSet = bpmCommonWsSetService.getById(id);	
		return getAutoView().addObject("bpmCommonWsSet",bpmCommonWsSet);
	}
	
	/**
	 * 获取webservice调用的 自定义参数
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getWsParams")
	@ResponseBody
	public String getWsParams(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long setId = RequestUtil.getLong(request, "setId");
		List<BpmCommonWsParams> list = bpmCommonWsSetService.getBpmCommonWsParamsList(setId);
		JSONArray ary = JSONArray.fromObject(list);
		return ary.toString();
	}
	
	
	@RequestMapping("doExecute")
	@ResponseBody
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long setId = RequestUtil.getLong(request, "setId");
		String json = RequestUtil.getString(request, "json");
		JSONObject jobject = new JSONObject();
		BpmCommonWsSet bpmCommonWsSet = bpmCommonWsSetService.getById(setId);
		if(bpmCommonWsSet==null){
			jobject.accumulate("result", ResultMessage.Fail)
				   .accumulate("message", "未获取到该webservice调用设置");
		}
		else{
			try{
				JSONArray jarray = JSONArray.fromObject(json);
				Map<String,Object> map = new HashMap<String, Object>();
				for(Object obj : jarray){
					JSONObject jObj = (JSONObject)obj;
					String bindingVal = jObj.getString("bindingVal");
					map.put(bindingVal, getTestVal(jObj));
				}
				String result = WebserviceHelper.executeXml(bpmCommonWsSet.getAlias(), map);
				jobject.accumulate("result", ResultMessage.Success)
				       .accumulate("message", result);
			}
			catch(Exception ex){
				jobject.accumulate("result", ResultMessage.Fail)
				   	   .accumulate("message", ex.getMessage());
			}
		}
		return jobject.toString();
	}
	
	private Object getTestVal(JSONObject obj) throws Exception{
		Integer javaType = obj.getInt("javaType");
		Object testVal = obj.get("testVal");
		switch(javaType){
			//列表
			case 3:
				if(testVal instanceof JSONArray){
					List list = JSONArray.toList((JSONArray)testVal,String.class);
					testVal = list;
				}
				break;
			//日期
			case 4:
				String[] formatter = new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","HH:mm:ss","yyyy-MM-dd HH:mm:00"};
				if(testVal instanceof String){
					testVal = DateUtils.parseDate(testVal.toString(), formatter);
				}
				break;
		}
		return testVal;
	}
}
