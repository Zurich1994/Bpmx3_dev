package com.hotent.platform.service.share.rights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.util.QueryUtil;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.service.system.SysUserOrgService;

public class ShareRightsUtil {

	public static final short RULE_TYPE_USER  = 0; 
	public static final short RULE_TYPE_ROLE  = 1; 
	public static final short RULE_TYPE_ORG  = 2; 
	public static final short RULE_TYPE_POST  = 3; 
	

	public static final short RIGHTS_TYPE_DISPLAY  = 0; 
	public static final short RULE_TYPE_EXPORT  = 2; 
	public static final short RULE_TYPE_FILTER = 3; 
	public static final short RIGHTS_TYPE_MANAGER  = 4; 
	public static final String RIGHTS_TYPE_DISPLAY_STR  = "display"; 
	public static final String RIGHTS_TYPE_MANAGER_STR  = "manager"; 
	public static final String RULE_TYPE_FILTER_STR = "filter"; 
	public static final String RULE_TYPE_EXPORT_STR  = "exports"; 
	/**
	 * 获取当前用户的权限Map
	 * 
	 * @return
	 */
	public static Map<String, Object> getRightMapByRule(String ids,short type) {
		
		Long userId = null;
		String curOrgId = "";
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysRole> roles = new ArrayList<SysRole> ();
		List<Position> positions = new ArrayList<Position> ();
		List<SysOrg> orgs =  new ArrayList<SysOrg> ();
		List<SysUserOrg> ownOrgs =  new ArrayList<SysUserOrg> ();
		// 实例化bean
		SysRoleService sysRoleService = (SysRoleService) AppUtil.getBean(SysRoleService.class);
		PositionService positionService = (PositionService) AppUtil.getBean(PositionService.class);
		SysOrgService sysOrgService = (SysOrgService) AppUtil.getBean(SysOrgService.class);
		SysUserOrgService sysUserOrgService = AppUtil.getBean(SysUserOrgService.class);
		
		switch(type){
			case ShareRightsUtil.RULE_TYPE_USER:
				for(String str : ids.split(",")){
					userId=Long.parseLong(str);
					curOrgId+=sysOrgService.getOrgIdByUserId(userId).toString()+",";
					roles.addAll(sysRoleService.getByUserId(userId));
					positions.addAll(positionService.getByUserId(userId));
					orgs.addAll(sysOrgService.getOrgsByUserId(userId));
					// 获取可以管理的组织列表。
					List<SysUserOrg> chargeOrgs = sysUserOrgService.getChargeOrgByUserId(userId);
					if(chargeOrgs!=null)
						ownOrgs.addAll(chargeOrgs);
				}
				map.put("userId", ids);
				map.put("curOrgId", curOrgId);
				map.put("roles", roles);
				map.put("positions", positions);
				map.put("orgs", orgs);
				map.put("ownOrgs", ownOrgs);
				return map;
			case ShareRightsUtil.RULE_TYPE_ORG:
				for(String str : ids.split(",")){
					orgs.add(sysOrgService.getById(Long.parseLong(str)));
				}
				map.put("orgs", orgs);
				return map;
			case ShareRightsUtil.RULE_TYPE_POST:
				for(String str : ids.split(",")){
					positions.add(positionService.getById(Long.parseLong(str)));
				}
				map.put("positions", positions);
				return map;
			case ShareRightsUtil.RULE_TYPE_ROLE:
				for(String str : ids.split(",")){
					roles.add(sysRoleService.getById(Long.parseLong(str)));
				}
				map.put("roles", roles);
				return map;
		}
		return null;
	}
	public static Map<String, Object> getPermissionByRule(DataTemplateVO dataTemplateVO, String pks, short type,short rightsType ) {
		if(BeanUtils.isEmpty(dataTemplateVO)) return null;
		// 当前用户的权限
		Map<String, Object> rightMap = ShareRightsUtil.getRightMapByRule(pks,type);
		// 取得有权限的过滤条件，如果有权限的过滤条件为空则返回null
		dataTemplateVO = getRightFilter(dataTemplateVO, rightMap);
		// 字段权限
		switch(rightsType){
			case ShareRightsUtil.RIGHTS_TYPE_DISPLAY:
				return getPermissionDetailMap(ShareRightsUtil.RIGHTS_TYPE_DISPLAY,JSONArray.fromObject(dataTemplateVO.getDisplayField()), rightMap);
			case ShareRightsUtil.RIGHTS_TYPE_MANAGER:
				return getPermissionDetailMap(ShareRightsUtil.RIGHTS_TYPE_MANAGER,JSONArray.fromObject(dataTemplateVO.getManageField()), rightMap);
			case ShareRightsUtil.RULE_TYPE_FILTER:
				return getPermissionDetailMap(ShareRightsUtil.RULE_TYPE_FILTER,JSONArray.fromObject(dataTemplateVO.getFilterField()), rightMap);
			case ShareRightsUtil.RULE_TYPE_EXPORT:
				JSONObject object = (JSONObject) JSONArray.fromObject(dataTemplateVO.getExportField()).get(0);
				return getPermissionDetailMap(ShareRightsUtil.RULE_TYPE_EXPORT,object.getJSONArray("fields"), rightMap);
		}
		return null;
	}
	/**
	 * 获取权限map
	 * 
	 * @param jsonAry
	 * @param type
	 * @param rightMap
	 * @return
	 */
	private static Map<String, Object> getPermissionDetailMap(int type, JSONArray jsonAry, Map<String, Object> rightMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (JSONUtil.isEmpty(jsonAry)||jsonAry.toString().equalsIgnoreCase("[null]"))
			return map;
		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String var = QueryUtil.getRightsName(json);
			if(StringUtil.isEmpty(var))
				continue;
			JSONArray rights = (JSONArray) json.get("right");
			Object desc = json.get("desc");
			if(BeanUtils.isEmpty(desc)) 
				desc = var;
			Iterator<JSONObject> iterator = rights.iterator();
			boolean hasRight = false;
			while(true){
				JSONObject permission = iterator.next();
				Integer s = (Integer) permission.get("s");
				if (s.intValue() == type&&permission.containsKey("type")){
					hasRight = QueryUtil.hasRight(permission, rightMap);
					if(hasRight){
						map.put(var, "{\"desc\":\""+desc+"\",\"r\":\""+hasRight+"\"}");
						break;
					}
				}
				if(!iterator.hasNext()){
					map.put(var, "{\"desc\":\""+desc+"\",\"r\":\""+hasRight+"\"}");
					break;
				}
			}
		}
		return map;
	}
	
	/**
	 * 取得有权限的过滤条件，如果有权限的过滤条件为空则返回null
	 * 
	 * @param dataTemplateVO
	 * @param rightMap
	 * @return
	 */
	private static DataTemplateVO getRightFilter(DataTemplateVO dataTemplateVO, Map<String, Object> rightMap) {
		String filterField = dataTemplateVO.getFilterField();
		JSONArray jsonArray = JSONArray.fromObject(filterField);
		String destFilterField = new JSONArray().toString();
		if (JSONUtil.isEmpty(jsonArray)) {
			dataTemplateVO.setFilterField(destFilterField);
			return dataTemplateVO;
		}

		// 有权限过滤条件
		JSONArray jsonAry = new JSONArray();
		for (Object obj : jsonArray) {
			JSONObject jObj = (JSONObject) obj;
			JSONArray rightAry = JSONArray.fromObject(jObj.get("right"));
			Iterator<JSONObject> iterator = rightAry.iterator();
			while(iterator.hasNext()){
				JSONObject permission = iterator.next();
				if(permission.containsKey("type")&&QueryUtil.hasRight(permission, rightMap)){
					jsonAry.add(obj);
					break;
				}
					
			}
		}
		if (JSONUtil.isEmpty(jsonAry)) {
			dataTemplateVO.setFilterField(destFilterField);
			return dataTemplateVO;
		}

		dataTemplateVO.setFilterField(jsonAry.toString());
		return dataTemplateVO;
	}
}
