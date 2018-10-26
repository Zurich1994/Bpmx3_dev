package com.hotent.platform.controller.bpm;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.MonGroup;
import com.hotent.platform.model.bpm.MonGroupItem;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.MonGroupService;
import com.hotent.platform.service.bpm.MonOrgRoleService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysRoleService;
/**
 *<pre>
 * 对象功能:监控分组 控制器类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-06-08 11:14:50
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/monGroup/")
public class MonGroupController extends BaseController
{
	@Resource
	private MonGroupService monGroupService;
	
	@Resource
	private SysOrgService sysOrgService;
	
	@Resource
	private SysRoleService sysRoleService;
	
	@Resource
	private MonOrgRoleService monOrgRoleService;
	
	
	/**
	 * 添加或更新监控分组。
	 * @param request
	 * @param response
	 * @param monGroup 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新监控分组")
	public void save(HttpServletRequest request, HttpServletResponse response,MonGroup group) throws Exception
	{
		String resultMsg=null;		
		MonGroup monGroup=getFormObject(request,group);
		try{
			if(monGroup.getId()==null||monGroup.getId()==0){
				monGroup.setId(UniqueIdUtil.genId());
				SysUser sysUser=ContextUtil.getCurrentUser();
				monGroup.setCreatetime(new Date());
				monGroup.setCreator(sysUser.getFullname());
				monGroup.setCreatorid(sysUser.getUserId());
				monGroupService.addAll(monGroup);			
				resultMsg="添加监控分组成功";
			}else{
			    monGroupService.updateAll(monGroup);
				resultMsg="更新监控分组成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 MonGroup 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected MonGroup getFormObject(HttpServletRequest request,MonGroup monGroup) throws Exception {
    
    	String[] aryFlowkey= request.getParameterValues("flowkey");
    	
    	if(aryFlowkey!=null && aryFlowkey.length>0){
    		for(String flowKey:aryFlowkey ){
    			MonGroupItem item=new MonGroupItem();
    			item.setFlowkey(flowKey);
    			monGroup.getMonGroupItemList().add(item);
    		}
    	}
		return monGroup;
    }
	
	/**
	 * 取得监控分组分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看监控分组分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<MonGroup> list=monGroupService.getAll(new QueryFilter(request,"monGroupItem"));
		ModelAndView mv=this.getAutoView().addObject("monGroupList",list);
		
		return mv;
	}
	
	/**
	 * 删除监控分组
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除监控分组")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			monGroupService.delAll(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除监控分组及其从表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑监控分组
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑监控分组")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		MonGroup monGroup=monGroupService.getById(id);
		List<MonGroupItem> monGroupItemList=monGroupService.getMonGroupItemList(id);
		
		return getAutoView().addObject("monGroup",monGroup)
							.addObject("monGroupItemList",monGroupItemList)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得监控分组明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看监控分组明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		MonGroup monGroup = monGroupService.getById(id);	
		List<MonGroupItem> monGroupItemList=monGroupService.getMonGroupItemList(id);
		return getAutoView().addObject("monGroup",monGroup)
							.addObject("monGroupItemList",monGroupItemList);
	}
	
	@RequestMapping("auth")
	@Action(description="组授权")
	public ModelAndView auth(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long id=RequestUtil.getLong(request,"groupId");
		MonGroup monGroup = monGroupService.getById(id);
		
		List<SysOrg> orgList=sysOrgService.getByOrgMonGroup(id);
		List<SysRole> roleList=sysRoleService.getByOrgMonGroup(id);
		String orgIds="";
		String orgNames="";
		
		String roleIds="";
		String roleNames="";
		for(SysOrg sysOrg:orgList){
			if("".equals(orgIds)){
				orgIds=sysOrg.getOrgId().toString();
				orgNames=sysOrg.getOrgName().toString();
			}
			else{
				orgIds+="," +sysOrg.getOrgId().toString();
				orgNames+="," +sysOrg.getOrgName().toString();
			}
		}
		
		for(SysRole sysRole:roleList){
			if("".equals(roleIds)){
				roleIds=sysRole.getRoleId().toString();
				roleNames=sysRole.getRoleName().toString();
			}
			else{
				roleIds+="," +sysRole.getRoleId().toString();
				roleNames+="," +sysRole.getRoleName().toString();
			}
		}
		
		return getAutoView()
				.addObject("monGroup",monGroup)
				.addObject("orgIds",orgIds)
				.addObject("orgNames",orgNames)
				
				.addObject("roleIds",roleIds)
				.addObject("roleNames",roleNames)
				
				.addObject("roleList",roleList);
	}
	
	@RequestMapping("saveAuth")
	@Action(description="组授权")
	public void saveAuth(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long groupId=RequestUtil.getLong(request,"groupId");
		String orgIds=RequestUtil.getString(request,"orgIds");
		String roleIds=RequestUtil.getString(request,"roleIds");
		PrintWriter out=response.getWriter();
		ResultMessage resultMessage=null;
		
		if(StringUtil.isEmpty(orgIds) ){
			resultMessage=new ResultMessage(ResultMessage.Fail, "没有组织数据!");
		}
		else if(StringUtil.isEmpty(roleIds)){
			resultMessage=new ResultMessage(ResultMessage.Fail, "没有角色数据!");
		}
		else{
			try{
				monOrgRoleService.saveAuth(groupId, roleIds, orgIds);
				resultMessage=new ResultMessage(ResultMessage.Success, "保存权限成功");
			}
			catch (Exception e) {
				e.printStackTrace();
				resultMessage=new ResultMessage(ResultMessage.Fail, e.getMessage());
			}
		}
		out.print(resultMessage);
	}
	
}
