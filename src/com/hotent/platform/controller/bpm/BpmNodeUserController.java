package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;


import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.util.FieldPool;
import com.hotent.platform.service.bpm.BpmNodeUserCalculationSelector;
import com.hotent.platform.service.bpm.BpmNodeUserService;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation.PreViewModel;
import com.hotent.platform.service.form.BpmFormFieldService;

/**
 * 对象功能: 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2011-12-05 17:20:40
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeUser/")
public class BpmNodeUserController extends BaseController
{
	@Resource
	private BpmNodeUserService bpmNodeUserService;
		

	@Resource 
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmNodeUserCalculationSelector bpmNodeUserCalculationSelector;
	
	
	
	/**
	 * 取得分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmNodeUser> list=bpmNodeUserService.getAll(new QueryFilter(request,"bpmNodeUserItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmNodeUserList",list);
		return mv;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		Long[] lAryId =RequestUtil.getLongAryByStr(request, "userNodeId");
		bpmNodeUserService.delByIds(lAryId);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long userNodeId=RequestUtil.getLong(request,"userNodeId");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmNodeUser bpmNodeUser=null;
		if(userNodeId!=null){
			 bpmNodeUser= bpmNodeUserService.getById(userNodeId);
		}else{
			bpmNodeUser=new BpmNodeUser();
		}
		return getAutoView().addObject("bpmNodeUser",bpmNodeUser).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得InnoDB free: 11264 kB明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"userNodeId");
		BpmNodeUser bpmNodeUser = bpmNodeUserService.getById(id);		
		return getAutoView().addObject("bpmNodeUser", bpmNodeUser);
	}
	
	
	
	/**
	 * 对节点数据进行预览。
	 * <pre>
	 * </pre>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("preview")
	@Action(description="查看节点用户设置")
	@ResponseBody
	@SuppressWarnings("unused")
	public List<SysUser> preview(HttpServletRequest request, HttpServletResponse response){
		
		
		Long defId = RequestUtil.getLong(request, "defId");
		Long startUserId=RequestUtil.getLong(request, "startUserId",0);
		Long preUserId=RequestUtil.getLong(request, "preUserId",0);
		
		String formUserId=RequestUtil.getString(request, "formUserId");
		String formOrgId=RequestUtil.getString(request, "formOrgId");
		String formPosId=RequestUtil.getString(request, "formPosId");
		String formRoleId=RequestUtil.getString(request, "formRoleId");
		String startOrgId=RequestUtil.getString(request, "startOrgId");
		String startPosId=RequestUtil.getString(request, "startPosId");
		String startJobId=RequestUtil.getString(request, "startJobId");
		String preOrgId=RequestUtil.getString(request, "preOrgId");
		
		
		//String nodeUser=RequestUtil.getString(request, "nodeUser");
		String nodeUser=request.getParameter("nodeUser");
		if(StringUtil.isEmpty(nodeUser)){
			nodeUser = "";
		}
		JSONArray jsonArray=JSONArray.fromObject(nodeUser);
		
		List<BpmNodeUser> bpmNodeUsers = new ArrayList<BpmNodeUser>();
		//解析BpmNodeUser配置。
		for (int i = 0; i < jsonArray.size(); i++) {
			String userJson = jsonArray.getJSONObject(i).toString();
			JSONObject jsonObj= JSONObject.fromObject(userJson);
			String cmpIds=jsonObj.getString("cmpIds");
			jsonObj.remove("cmpIds");
			BpmNodeUser bpmNodeUser = (BpmNodeUser) JSONObject.toBean(jsonObj, BpmNodeUser.class);
			bpmNodeUser.setCmpIds(cmpIds);
		//	bpmNodeUser.setActDefId(bpmDefintion.getActDefId());
			bpmNodeUsers.add(bpmNodeUser);
		}
		//构建表单流程变量。
		Map<String,Object> vars=new HashMap<String, Object>();
		
		if(StringUtil.isNotEmpty(formUserId)){
			vars.put(BpmConst.PREVIEW_FORMUSER, formUserId);
		}
		if(StringUtil.isNotEmpty(formOrgId)){
			vars.put(BpmConst.PREVIEW_FORMORG, formOrgId);
		}
		if(StringUtil.isNotEmpty(formPosId)){
			vars.put(BpmConst.PREVIEW_FORMPOS, formPosId);
		}
		if(StringUtil.isNotEmpty(formRoleId)){
			vars.put(BpmConst.PREVIEW_FORMROLE, formRoleId);
		}
		if(StringUtil.isNotEmpty(startOrgId)){
			vars.put(BpmConst.START_ORG_ID, startOrgId);
		}
		if(StringUtil.isNotEmpty(startPosId)){
			vars.put(BpmConst.START_POS_ID, startPosId);
		}
		if(StringUtil.isNotEmpty(startJobId)){
			vars.put(BpmConst.START_JOB_ID, startJobId);
		}
		
		if(StringUtil.isNotEmpty(preOrgId)){
			vars.put(BpmConst.PRE_ORG_ID, preOrgId);
		}
		List<SysUser> resultList=new ArrayList<SysUser>();
		//创建返回值。
		Map<Short,List<SysUser>> userMap=new HashMap<Short, List<SysUser>>();
		for(BpmNodeUser bpmNodeUser:bpmNodeUsers){
			List<SysUser> userList =bpmNodeUserService.getPreviewNodeUser(bpmNodeUser, startUserId, preUserId, null,  vars);
			if(BeanUtils.isEmpty(userList) ) continue;
			resultList=userSetCal(bpmNodeUser.getCompType(),resultList,userList);
		}
		return resultList;
	}
	
	
//	@RequestMapping("relativeCal")
//	@Action(description="组合人员计算")
//	public ModelAndView relativeCal(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		Long defId=RequestUtil.getLong(request, "defId",0);
//		long userNodeId=RequestUtil.getLong(request,"nodeUserId",0);
//		
//		//显示表单变量。
//		List<BpmFormField> fieldList= bpmFormFieldService.getFlowVarByFlowDefId(defId);
//		List<BpmFormField> userVarList=new ArrayList<BpmFormField>();
//		List<BpmFormField> orgVarList=new ArrayList<BpmFormField>();
//		
//		for(BpmFormField field:fieldList){
//			Short controlType=field.getControlType();
//			if(controlType==null) continue;
//			if(field.getIsHidden()==1) continue;
//			if(controlType==BpmFormField.Selector_User || controlType==BpmFormField.Selector_UserMulti ){
//				userVarList.add(field);
//			}
//			if(controlType==BpmFormField.Selector_Org || controlType==BpmFormField.Selector_Org_single ){
//				orgVarList.add(field);
//			}
//		}
//		
//		BpmComsiteUser bpmComsiteUser=new BpmComsiteUser();
//		//获取流程变量。
//		if(userNodeId>0){
//			bpmComsiteUser=  bpmComsiteUserDao.getByNodeUserId(userNodeId);
//		}
//		ModelAndView mv=getAutoView();
//		mv.addObject("user", bpmComsiteUser);
//		mv.addObject("userVarList", userVarList);
//		mv.addObject("orgVarList", orgVarList);
//		return mv;
//	}
	
	@RequestMapping("getByUserParams")
	@Action(description="预览数据信息计算")
	@ResponseBody
	public List<PreViewModel> getByUserParams(HttpServletRequest request, HttpServletResponse response){
		List<PreViewModel> list=new ArrayList<PreViewModel>();
		String params=RequestUtil.getString(request, "paramsJson");
		JSONArray jsonArray=JSONArray.fromObject(params);
		Set<PreViewModel> set =new HashSet<PreViewModel>();
		
		for(Object obj:jsonArray){
			JSONObject jsonObj=(JSONObject)obj;
			//cmpIds有可能问JSON对象，先获取字符串。
			String str= jsonObj.getString("cmpIds");
			jsonObj.remove("cmpIds");
			BpmNodeUser bpmNodeUser=(BpmNodeUser)JSONObject.toBean(jsonObj, BpmNodeUser.class);
			bpmNodeUser.setCmpIds(str);
			if(bpmNodeUser.getAssignType()==BpmNodeUser.ASSIGN_TYPE_SAME_NODE) continue;
			
			Map<String,IBpmNodeUserCalculation> map= bpmNodeUserCalculationSelector.getBpmNodeUserCalculation();
			IBpmNodeUserCalculation calc=map.get(bpmNodeUser.getAssignType());
			if(calc.supportMockModel()){
				List<PreViewModel> preViewModelList= calc.getMockModel(bpmNodeUser);
				if(preViewModelList!=null&&preViewModelList.size()>0)
				set.addAll(preViewModelList);
			}
		}
		if(set.size()>0){
			list.addAll(set);
		}
		return list;
	}
	
	
	@RequestMapping("formVar")
	@Action(description="用户表单变量设置")
	public ModelAndView formVar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Long defId=RequestUtil.getLong(request, "defId",0);
		String cmpIds=RequestUtil.getString(request, "cmpIds");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		
		int varType=0;
		String varName="";
		String userType="";
		
		if(StringUtil.isNotEmpty(cmpIds)){
			JSONObject jsonObject=JSONObject.fromObject(cmpIds);
			varType=jsonObject.getInt("type");
			varName=jsonObject.getString("varName");
			if(varType==6){
				userType=jsonObject.getString("userType");
			}
		}
			
		
		//显示表单变量。
		List<BpmFormField> fieldList = null;
		if(StringUtil.isEmpty(parentActDefId)){
			fieldList = bpmFormFieldService.getFlowVarByFlowDefId(defId);
		}else{
			fieldList = bpmFormFieldService.getFlowVarByFlowDefId(defId, parentActDefId);
		}
		List<BpmFormField> userVarList=new ArrayList<BpmFormField>();
		List<BpmFormField> orgVarList=new ArrayList<BpmFormField>();
		//角色
		List<BpmFormField> roleVarList=new ArrayList<BpmFormField>();
		//岗位
		List<BpmFormField> posVarList=new ArrayList<BpmFormField>();
		
		List<BpmFormField> otherList=new ArrayList<BpmFormField>();
		
		for(BpmFormField field:fieldList){
			//if(field.getIsHidden()==1) continue;
			Short controlType=field.getControlType();
			if(controlType==null) continue;
			if( controlType==FieldPool.SELECTOR_USER_SINGLE || controlType==FieldPool.SELECTOR_USER_MULTI ){
				if(field.getIsHidden()!=1 )
					userVarList.add(field);
			}
			else if(controlType==FieldPool.SELECTOR_ORG_SINGLE || controlType==FieldPool.SELECTOR_ORG_MULTI ){
				if(field.getIsHidden()!=1 )
					orgVarList.add(field);
			}
			else if(controlType==FieldPool.SELECTOR_ROLE_SINGLE || controlType==FieldPool.SELECTOR_ROLE_MULTI ){
				if(field.getIsHidden()!=1 )
					roleVarList.add(field);
			}
			else if(controlType==FieldPool.SELECTOR_POSITION_SINGLE || controlType==FieldPool.SELECTOR_POSITION_MULTI ){
				if(field.getIsHidden()!=1 )
					posVarList.add(field);
			}
			else{//普通变量
				if(field.getIsFlowVar()==1) {
					otherList.add(field);
				}
			}
		}
		
		
		ModelAndView mv=getAutoView();
		mv.addObject("userVarList", userVarList);
		mv.addObject("orgVarList", orgVarList);
		
		mv.addObject("roleVarList", roleVarList);
		mv.addObject("posVarList", posVarList);
		
		mv.addObject("varType", varType);
		mv.addObject("varName", varName);
		mv.addObject("userType", userType);
		mv.addObject("otherList", otherList);
		return mv;
		
		
	}
	
	@RequestMapping("startOrPrevDialog")
	@Action(description="用户表单变量设置")
	public ModelAndView startOrPrevDialog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Long defId=RequestUtil.getLong(request, "defId",0);
		String cmpIds=RequestUtil.getString(request, "cmpIds");
		String type="";
		String userType="";
		
		if(StringUtil.isNotEmpty(cmpIds)){
			JSONObject jsonObject=JSONObject.fromObject(cmpIds);
			type=jsonObject.getString("type");
			userType=jsonObject.getString("userType");
		}
		
		ModelAndView mv=getAutoView();
		mv.addObject("type", type);
		mv.addObject("userType", userType);
		return mv;
	}
	
	@RequestMapping("startOrPrevWithOrgDialog")
	@Action(description="发起人或上一任务执行人的组织")
	public ModelAndView startOrPrevWithOrgDialog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Long defId=RequestUtil.getLong(request, "defId",0);
		String cmpIds=RequestUtil.getString(request, "cmpIds");
		
		String type="";
		String userType="";
		
		if(StringUtil.isNotEmpty(cmpIds)){
			JSONObject jsonObject=JSONObject.fromObject(cmpIds);
			type=jsonObject.getString("type");
			userType=jsonObject.getString("userType");
		}
		
		ModelAndView mv=getAutoView();
		mv.addObject("type", type);
		mv.addObject("userType", userType);
		return mv;
	}
	
	/**
	 * 并集运算
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<SysUser> unionSysUserList(List<SysUser> list1,List<SysUser> list2){
		List<SysUser> users = new ArrayList<SysUser>();
		users.addAll(list1);
		for(SysUser u:list2){
			if(!users.contains(u)){
				users.add(u);
			}
		}
		
		return users;
	}
	/**
	 * 交集运算
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<SysUser> intersectSysUserList(List<SysUser> list1,List<SysUser> list2){
		List<SysUser> users = new ArrayList<SysUser>();
		for(SysUser u:list1){
			if(list2.contains(u)){
				users.add(u);
			}
		}
		return users;
	}
	
	/**
	 * 差集运算
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<SysUser> subtractSysUserList(List<SysUser> list1,List<SysUser> list2){
		List<SysUser> users = new ArrayList<SysUser>();
		users.addAll(list1);
		for(SysUser u:list2){
			if(users.contains(u)){
				users.remove(u);
			}
		}
		return users;
	}
	
	/**
	 * 根据集合计算类型，进行集合运算
	 * @param type
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<SysUser> userSetCal(Short type,List<SysUser> list1,List<SysUser> list2){
		if(list1.isEmpty()){
			return list2;
		}
		switch (type) {
			case BpmNodeUser.COMP_TYPE_AND:
				list1 = intersectSysUserList(list1,list2);
				break;
			case BpmNodeUser.COMP_TYPE_OR:
				list1 = unionSysUserList(list1,list2);
				break;
			case BpmNodeUser.COMP_TYPE_EXCLUDE:
				list1 = subtractSysUserList(list1,list2);
			default:
				break;
		}
		return list1;
	}
}
