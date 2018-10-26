package com.hotent.mobile.controller.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.mobile.controller.base.BaseMobileController;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysUserService;

/**
 * 对象功能:联系人
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2014-5-14 上午8:53:45
 */
@Controller
@RequestMapping("/mobile/contact/bpmMobileContact/")
public class BpmMobileContactController extends BaseMobileController {
	
	@Resource
	SysOrgService orgService;
	
	@Resource
	SysUserService userService;
	
	private int idx=0;
	
	/**
	 * 部门列表
	 * 1. 如果部门的子节点为部门，返回部门列表
	 * 2. 如果部门的子节点为人员，返回人员列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "部门用户列表")
	public void list(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			QueryFilter filter;
			String username = request.getParameter("username");
			String orgid = request.getParameter("orgid");
			Long oid = ("0".equals(orgid))?SysOrg.BEGIN_ORGID:Long.valueOf(orgid);
			
			if(oid==SysOrg.BEGIN_ORGID){
				//oid = orgService.getBySupId(oid).getOrgId();
			}
			
			if(StringUtil.isNotEmpty(username)){
				// 联系人按工号姓名搜索
				filter = new QueryFilter(request,false);
				filter.addFilter("orgid", oid);
				filter.addFilter("orderField", "account");
				filter.addFilter("orderSeq", "asc");
				List<SysUser> users = userService.getAllMobile(filter);
				map.put("isUser", "true");
				List<Map<String, Object>> list = filterUsers(users);
				this.returnCallbackPageList(request, response, list, filter, map);
			}else{
				filter = new QueryFilter(request,false);
				filter.addFilter("orgid", oid);
				List<SysOrg> orgs = orgService.getOrgForMobile(filter);
				if(orgs!=null&&orgs.size()>0){
					// 联系人/组织 子节点为组织返回组织列表
					map.put("isUser", "false");
					List<Map<String, Object>> list = filterOrgs(orgs);
					this.returnCallbackPageList(request, response, list, filter, map);
				}
				else
				{   
					// 如果部门的子节点为人员，返回人员列表
					filter = new QueryFilter(request);
					filter.addFilter("orderField", "account");
					filter.addFilter("orderSeq", "asc");
					List<SysUser> users = userService.getAllMobile(filter);
					map.put("isUser", "true");
					List<Map<String, Object>> list = filterUsers(users);
					this.returnCallbackPageList(request, response, list, filter, map);
				}
			}
		}catch(Exception e){
			String json = this.getError("error");
			returnCallbackData(request, response, json);
		}
	}

	/**
	 * 部门列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orglist")
	@Action(description = "部门列表")
	public void orglist(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			QueryFilter filter = new QueryFilter(request,false);
			String orgid = request.getParameter("orgid");
			Long oid = ("0".equals(orgid))?SysOrg.BEGIN_ORGID:Long.valueOf(orgid);
			filter.addFilter("orgid", oid);
			
			if(oid==SysOrg.BEGIN_ORGID){
//				oid = orgService.getBySupId(oid).getOrgId();
			}
			
			filter.addFilter("orgid", oid);
			List<SysOrg> orgs =  orgService.getOrgForMobile(filter);
			if(orgs!=null&&orgs.size()>0){
				List<Map<String, Object>> list = filterOrgs(orgs);
				this.returnCallbackPageList(request, response, list, filter, map);
			}else{
				String json = this.getSuccess("已是最顶层");
				returnCallbackData(request, response, json);
			}

		}catch(Exception e){
			String json = this.getError("error");
			returnCallbackData(request, response, json);
		}
	}
	
	private List<Map<String, Object>> filterOrgs(List<SysOrg> orgs){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(SysOrg org:orgs){
			Map<String, Object> o = new HashMap<String, Object>();
			o.put("id", org.getOrgId());
			o.put("name", org.getOrgName());
			list.add(o);
		}
		return list;
	}
	
	private List<Map<String, Object>> filterUsers(List<SysUser> users){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(SysUser user:users){
			Map<String, Object> o = new HashMap<String, Object>();
			o.put("id", user.getUserId());
			o.put("name", user.getFullname());
			o.put("account", user.getAccount());
//			o.put("position", user.getPosname());
			o.put("type", "u");
			list.add(o);
		}
		return list;
	}
	
	@RequestMapping("_list")
	@Action(description = "部门用户列表")
	public void _list(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String isRefresh = request.getParameter("isRefresh");
			
			String orgid = request.getParameter("orgid");
			Long oid = ("0".equals(orgid))?SysOrg.BEGIN_ORGID:Long.valueOf(orgid);
			
			if(oid==SysOrg.BEGIN_ORGID){
	//			oid = orgService.getBySupId(oid).getOrgId();
			}
			
			QueryFilter filter;
			String fullname = request.getParameter("username");
			if(StringUtil.isNotEmpty(fullname)){
				// 联系人按工号姓名搜索
				filter = new QueryFilter(request);
				filter.addFilter("orgid", oid);
				filter.addFilter("orderField", "account");
				filter.addFilter("orderSeq", "asc");
				filter.addFilter("fullname", "%"+fullname+"%");
				List<SysUser> users = userService.getAllMobile(filter);
				List<Map<String, Object>> list = filterUsers(users);
				map.put("userCount", String.valueOf(list.size()));
				this.returnCallbackPageList(request, response, list, filter, map);
			}else{
				List<SysOrg> orgs = null;
				int orgCount = 0;
				if(StringUtil.isEmpty(isRefresh)){
					filter = new QueryFilter(request,false);
					filter.addFilter("orgid", oid);
					orgs = orgService.getOrgForMobile(filter);
					orgCount = orgs.size();
				}else{
					orgs = new ArrayList<SysOrg>();
					orgCount = Integer.valueOf(request.getParameter("orgCount"));
				}
				
				filter = new QueryFilter(request);
				filter.addFilter("orgid", oid);
				filter.addFilter("orderField", "account");
				filter.addFilter("orderSeq", "asc");
				List<SysUser> users = userService.getAllMobile(filter);

				List<Map> rslist = merge(orgs,users);
				map.put("userCount", String.valueOf(filter.getPageBean().getTotalCount()));
				map.put("orgCount", String.valueOf(orgCount));
				this.returnCallbackPageList(request, response, rslist, filter, map);
			}
		}catch(Exception e){
			String json = this.getError("error");
			returnCallbackData(request, response, json);
		}
	}
	
	private Map<String, String> convertOrg(SysOrg org){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(org.getOrgId()));
		map.put("name", org.getOrgName());
		map.put("type", "o");
		map.put("order", idx+"");
		return map;
	}
	
	private Map<String, String> convertUser(SysUser user){
		Map<String, String> map = new HashMap();
		map.put("id", String.valueOf(user.getUserId()));
		map.put("name", user.getFullname());
		map.put("account", user.getAccount());
//		map.put("position", user.getPosname()!=null?user.getPosname():"");
		map.put("type", "u");
		map.put("order", idx+"");
		return map;
	}
	
	/**
	 * 融合组织用户
	 * @param orgs
	 * @param users
	 * @return
	 */
	private List<Map> merge(List<SysOrg> orgs,List<SysUser> users){
		List<Map> rslist = new ArrayList<Map>();
		for(SysOrg o:orgs){
			rslist.add(this.convertOrg(o));
			idx++;
		}
		for(SysUser u:users){
			rslist.add(this.convertUser(u));
			idx++;
		}
		return rslist;
	}
	
}

