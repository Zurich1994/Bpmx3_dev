package com.hotent.platform.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.CommonVar;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUserOrg;
/**
 * <pre>
 * 对象功能:sql自定义查询 权限解析 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:欧晓斌
 * 创建时间:2014年6月4日15:05:28
 * </pre>
 */
@Service
public class SysQueryRightParseService {
	/**
	 * 系统角色
	 */
	@Resource
	private SysRoleService sysRoleService;
	/**
	 * 系统职位
	 */
	@Resource
	private PositionService positionService;
	/**
	 * 部门
	 */
	@Resource
	private SysOrgService sysOrgService;
	/**
	 * 获取可以管理的组织列表
	 */
	/*@Resource
	private SysUserOrgService sysUserOrgService;*/

	@Resource
	private GroovyScriptEngine groovyScriptEngine;

	/**
	 * 获取当前用户所拥有的【角色】、【岗位】、【部门】、【管理组织】以及当前ID和部门ID的所有权限
	 * 
	 * @param userId
	 * @param curOrgId
	 * @return
	 */
	public Map<String, Object> getRightMap(Long userId, Long curOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysRole> roles = sysRoleService.getByUserId(userId);
		List<Position> positions = positionService.getByUserId(userId);
		List<SysOrg> orgs = sysOrgService.getOrgsByUserId(userId);
		// 获取可以管理的组织列表。
		/*List<SysUserOrg> ownOrgs = sysUserOrgService
				.getChargeOrgByUserId(userId);*/
		map.put("userId", userId);
		map.put("curOrgId", curOrgId);
		map.put("roles", roles);
		map.put("positions", positions);
		map.put("orgs", orgs);
		//map.put("ownOrgs", ownOrgs);
		return map;
	}

	/**
	 * 判断是否有权限。
	 * 
	 * @param permission
	 * @param rightMap
	 *            权限map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasRight(JSONObject permission, Map<String, Object> rightMap) {
		String type = permission.get("type").toString();
		String id = permission.get("id").toString();
		Object script = permission.get("script");
		if ("none".equals(type)) // 无
			return false;
		if ("everyone".equals(type))// 所有人
			return true;
		Long userId = (Long) rightMap.get("userId");
		Long curOrgId = (Long) rightMap.get("curOrgId");
		List<SysRole> roles = (List<SysRole>) rightMap.get("roles");
		List<Position> positions = (List<Position>) rightMap.get("positions");
		List<SysOrg> orgs = (List<SysOrg>) rightMap.get("orgs");
		List<SysUserOrg> ownOrgs = (List<SysUserOrg>) rightMap.get("ownOrgs");
		// 指定用户
		if ("user".equals(type)) {
			return StringUtil.contain(id, userId.toString());
		}
		// 指定角色
		else if ("role".equals(type)) {
			if (roles == null)
				return false;
			for (SysRole role : roles) {
				if (StringUtil.contain(id, role.getRoleId().toString())) {
					return true;
				}
			}
		}
		// 指定组织
		else if ("org".equals(type)) {
			if (orgs == null)
				return false;
			for (SysOrg org : orgs) {
				if (StringUtil.contain(id, org.getOrgId().toString())) {
					return true;
				}
			}
		}
		// 组织负责人
		else if ("orgMgr".equals(type)) {
			if (ownOrgs == null)
				return false;
			for (SysUserOrg sysUserOrg : ownOrgs) {
				if (StringUtil.contain(id, sysUserOrg.getOrgId().toString())) {
					return true;
				}
			}
		}
		// 岗位
		else if ("pos".equals(type)) {
			if (positions == null)
				return false;
			for (Position position : positions) {
				if (StringUtil.contain(id, position.getPosId().toString())) {
					return true;
				}
			}
		} else if ("script".equals(type)) {
			if (BeanUtils.isEmpty(script))
				return false;
			Map<String, Object> map = new HashMap<String, Object>();
			CommonVar.setCommonVar(map, userId, curOrgId,ContextUtil.getCurrentCompanyId());
			return groovyScriptEngine.executeBoolean(script.toString(), map);
		}
		return false;
	}
	/**
	 * 
	 * 显示字段默认的权限json
	 * 
	 * @return String
	 */
	public static String getDefaultRight(Integer rightType) {
		JSONArray jsonAry = new JSONArray();
		JSONObject json = new JSONObject();
		json.accumulate("s", rightType);
		json.accumulate("type", "none");
		json.accumulate("id", "");
		json.accumulate("name", "");
		json.accumulate("script", "");
		jsonAry.add(json);
		return jsonAry.toString();
	}
	
	/**
	 * 获取字段的权限，返回格式为{"test1":true,"test2":false}
	 * 
	 * @param userId
	 * @param type
	 * @param bpmDataTemplate
	 * @return
	 */
	public Map<String, Boolean> getPermission(int type, String field,
			Map<String, Object> rightMap) {
		JSONArray jsonAry = JSONArray.fromObject(field);
		return getPermissionMap(type, jsonAry, rightMap);
	}

	/**
	 * 获取权限map
	 * 
	 * @param jsonAry
	 * @param type
	 * @param rightMap
	 * @return
	 */
	private Map<String, Boolean> getPermissionMap(int type, JSONArray jsonAry,
			Map<String, Object> rightMap) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (JSONUtil.isEmpty(jsonAry))
			return map;
		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String name = (String) json.get("name");
			JSONArray rights = (JSONArray) json.get("right");
			for (Object right : rights) {
				JSONObject rightJson = JSONObject.fromObject(right);
				Integer s = (Integer) rightJson.get("s");
				if (s.intValue() == type)
					map.put(name, this.hasRight(rightJson,
							rightMap));
			}
		}
		return map;
	}

}
