package com.hotent.platform.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.sf.json.JSONObject;

import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.ResourcesUrlExt;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.webservice.api.SystemResourcesService;

/**
 * 取得子系统资源。
 * @author Administrator
 *
 */
public class SystemResourcesServiceImpl implements SystemResourcesService {
	
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 返回格式如下：
	 * {"roleList":["admin","common"],"isUrlExist":true}
	 */
	@WebMethod(operationName = "getRoleByUrl")
	public String getRoleByUrl(@WebParam(name = "sysAlias") String sysAlias,
			@WebParam(name = "url") String url) {
		Map<String, Object> map=sysRoleService.getUrlRightMap(sysAlias, url);
		JSONObject jsonObject=new JSONObject();
    	jsonObject.putAll(map);
		return jsonObject.toString();
	}
	

	/**
	 * 返回格式如下：
	 * {"isFuncExist":true,"roleList":["admin","common"]}
	 */
	@WebMethod(operationName = "getRoleByFunc")
	public String getRoleByFunc(@WebParam(name = "sysAlias") String sysAlias,
			@WebParam(name = "func") String func) {
	
		List<ResourcesUrlExt> funcRoleList=sysRoleService.getFunctionRoleList(sysAlias,func);
		boolean isFuncExist=funcRoleList.size()>0;
		List<String> roleList=new ArrayList<String>();
		for(ResourcesUrlExt table:funcRoleList){
			if(StringUtil.isNotEmpty(table.getRole())){
				roleList.add(table.getRole());
			}
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("isFuncExist", isFuncExist);
		jsonObject.put("roleList", roleList);
    	return jsonObject.toString();
		
	}
	
}
