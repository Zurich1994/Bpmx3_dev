package com.hotent.platform.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fr.third.org.hsqldb.lib.HashSet;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgParam;
import com.hotent.platform.model.system.SysParam;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserParam;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.DemensionService;
import com.hotent.platform.service.system.SysOrgParamService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysParamService;
import com.hotent.platform.service.system.SysUserService;

/**
 * 对象功能:组织参数属性 控制器类 开发公司:广州宏天软件有限公司 开发组织:csx 创建时间:2012-02-24 10:04:50
 */
@Controller
@RequestMapping("/platform/system/sysOrgParam/")
public class SysOrgParamController extends BaseController {
	@Resource
	private SysOrgParamService sysOrgParamService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysParamService sysParamService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private DemensionService demensionService;

	/**
	 * 取得组织参数属性分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editByOrgId")
	@Action(description = "修改组织参数属性分页列表")
	public ModelAndView editByOrgId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String returnUrl = RequestUtil.getPrePage(request);
		long orgId = RequestUtil.getLong(request, "orgId");
		String paramTypeFilter = RequestUtil.getString(request, "paramType");
		boolean isFirst = false;
		
		SysOrg sysOrg = sysOrgService.getById(orgId);
		List<String> categoryList = sysParamService.getDistinctCategory(2,sysOrg.getDemId());
		List<SysOrgParam> list = sysOrgParamService.getByOrgId(orgId);
		List<SysParam> sysParamList = sysParamService.getOrgParam(sysOrg.getDemId());
		
		isFirst =	BeanUtils.isEmpty(list);
		List< SysOrgParam> orgParam=convertByList(sysParamList,list,paramTypeFilter);
		ModelAndView mv = this.getAutoView()
				.addObject("paramList", orgParam)
				.addObject("sysOrg", sysOrg)
				.addObject("isFirst",isFirst)
				.addObject("categoryList",categoryList)
				.addObject("paramType",paramTypeFilter)
				.addObject("returnUrl", returnUrl);
		return mv;
	}
	
	private List< SysOrgParam> convertByList(List<SysParam> sysParamList,List<SysOrgParam> orgParaList,String paramTypeFilter){
		List< SysOrgParam> list=new ArrayList<SysOrgParam>();
		if(orgParaList.size()==0){
			for(SysParam sysParam:sysParamList){
				//如果新增时过滤参数分类
				if(StringUtil.isEmpty(paramTypeFilter) 
						|| (StringUtil.isNotEmpty(sysParam.getCategory())&&paramTypeFilter.contains(sysParam.getCategory()+","))
						||paramTypeFilter.contains("all,")){
					list.add(new SysOrgParam(sysParam));
				}
			}
			return list;
		}
		else{
			Set<String> paramKey = new java.util.HashSet<String>();
			convertToList(orgParaList,paramKey);
			
			for(SysParam sysParam:sysParamList){
				if((StringUtil.isNotEmpty(sysParam.getCategory())&&paramTypeFilter.contains(sysParam.getCategory()+","))
						||paramTypeFilter.contains("all,")){
					
					if(!paramKey.contains(sysParam.getParamKey()))
					orgParaList.add(new SysOrgParam(sysParam));
				}
			}
			return orgParaList;
		}
	}
	
	private void convertToList(List<SysOrgParam> orgParaList, Set<String> paramKey){
		for(SysOrgParam param :orgParaList){
			SysParam sysParam=sysParamService.getById(param.getParamId());
			param.setSourceType(sysParam.getSourceType());
			param.setSourceKey(sysParam.getSourceKey());
			param.setDescription(sysParam.getDescription());
			paramKey.add(sysParam.getParamKey());
		}
	}
	 
	@RequestMapping("saveByOrgId")
	@Action(description = "编辑组织参数属性")
	public void saveByOrgId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		try {
			long orgId = RequestUtil.getLong(request, "orgId");
			String jsonParamData=request.getParameter("jsonParamData");
			
			List<SysOrgParam> valueList = coverBean(orgId, jsonParamData);;
			sysOrgParamService.add(orgId, valueList);
			resultMessage = new ResultMessage(ResultMessage.Success,
					"编辑组织参数属性成功");
			out.print(resultMessage.toString());
		} catch (Exception e) {
			e.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"编辑组织参数属性失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}

	private List<SysOrgParam> coverBean(long orgId, String jsonData) throws Exception {
		Map<String,String> orgParamMap = jsonToMap(jsonData);
		List<SysOrgParam> list = new ArrayList<SysOrgParam>();
		List<SysParam> paramList =sysParamService.getAll();
		for(SysParam sysParam:paramList){
			if(orgParamMap.containsKey((sysParam.getParamId()).toString())){
				long paramId=sysParam.getParamId();
				SysOrgParam orgParam = new SysOrgParam();
				orgParam.setValueId(UniqueIdUtil.genId());
				orgParam.setParamId(sysParam.getParamId());
				orgParam.setParamValue(orgParamMap.get((sysParam.getParamId()).toString()));
				orgParam.setOrgId(orgId);
				String dataType = sysParamService.getById(paramId)
						.getDataType();
				String sourceType=sysParamService.getById(paramId).getSourceType();
				if (sourceType.equals("input")) {
					if (SysParam.DATA_TYPE_MAP.get(dataType) != null
							&& SysParam.DATA_TYPE_MAP.get(dataType).equals("数字")) {
						orgParam.setParamIntValue(Long.parseLong(orgParamMap.get((sysParam.getParamId()).toString())));
					} else if (SysParam.DATA_TYPE_MAP.get(dataType) != null
							&& SysParam.DATA_TYPE_MAP.get(dataType).equals("日期")) {
						orgParam.setParamDateValue(SysParam.PARAM_DATE_FORMAT.parse(orgParamMap.get((sysParam.getParamId()).toString())));
					}
				}
				list.add(orgParam);
			}
		}
		return list;
	}

	private Map<String, String> jsonToMap(String jsonData) {
		Map<String, String> map = null ;
	    JSONObject json = null;
		json = JSONObject.fromObject(jsonData);
		Iterator<?> iter = json.keySet().iterator();
		map = new HashMap<String, String>();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = json.getString(key);
			map.put(key, value);
		}
		return map;
	}

	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
//		Long nodeUserId = RequestUtil.getLong(request, "nodeUserId");
//		BpmNodeUser nu = bpmNodeUserService.getById(nodeUserId);
//		if (nu != null) {
//			String cmpIds = nu.getCmpIds();
//			cmpIds = SysParamService.setParamIcon(request.getContextPath(),
//					cmpIds);
//			String cmpNames = nu.getCmpNames();
//			mv.addObject("cmpIds", cmpIds);
//			mv.addObject("cmpNames", cmpNames);
//		} else {
//			String cmpIds = RequestUtil.getString(request, "cmpIds");
//			cmpIds = SysParamService.setParamIcon(request.getContextPath(),
//					cmpIds);
//			String cmpNames = RequestUtil.getString(request, "cmpNames");
//			mv.addObject("cmpIds", cmpIds);
//			mv.addObject("cmpNames", cmpNames);
//		}

		List<SysParam> sysParamList = sysParamService.getOrgParam();

		mv.addObject("sysParamList", sysParamList).addObject("conditionUS",
				SysParam.CONDITION_US);
		return mv;
	}

	@RequestMapping("getByParamKey")
	public ModelAndView getByParamKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		String orgParam = RequestUtil.getString(request, "orgParam");
		int postFlag=RequestUtil.getInt(request, "postflag");  
		orgParam = new String(orgParam.getBytes("ISO8859_1"), "utf-8");
		List<SysUser> userList = sysUserService.getByOrgParam(orgParam);
		mv.addObject("userList", userList);
		mv.addObject("postFlag",postFlag);
		return mv;
	}

}
