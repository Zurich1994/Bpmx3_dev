package com.hotent.platform.service.system;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.model.system.SysQuerySql;


/**
 * <pre>
 * 对象功能:sql自定义查询 管理按钮Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:欧晓斌
 * 创建时间:2014年6月4日15:05:28
 * </pre>
 */
@Service
public class SysQueryManageFieldParseService {

	@Resource
	private SysQuerySqlService sysQuerySqlService;
	@Resource
	private SysQueryRightParseService sysQueryRightParseService;

	/**
	 * 是否有管理字段
	 * 
	 * @param conditionField
	 * @return
	 */
	public boolean hasManageField(String manageField) {
		if (StringUtil.isEmpty(manageField))
			return false;
		JSONArray jsonAry = JSONArray.fromObject(manageField);
		return jsonAry.size() > 0 ? true : false;
	}

	/**
	 * 设置默认按钮字段
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDefaultManageButton(Long sqlId) {
		SysQuerySql sysQuerySql = sysQuerySqlService.getById(sqlId);
		// 增加一个默认的导出按钮
		JSONObject exportJsonObject = this.getDefaultExportButton();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(exportJsonObject);
		if (sysQuerySql != null
				&& StringUtil.isNotEmpty(sysQuerySql.getUrlParams())) {
			JSONArray manageJsonArray = JSONArray.fromObject(sysQuerySql
					.getUrlParams());
			for (Object obj : manageJsonArray) {
				JSONObject jsonObject = JSONObject.fromObject(obj);
				jsonObject.accumulate("right",
						this.getDefaultManageFieldRight());
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray.toString();
	}
	
	/**
	 * 默认【导出】按钮
	 * @return
	 */
	private JSONObject getDefaultExportButton() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("name", SysQuerySetting.EXPORT_BUTTON);
		jsonObject.accumulate("desc", "导出");
		jsonObject.accumulate("right", this.getDefaultManageFieldRight());
		return jsonObject;
	}

	/**
	 * 
	 * 显示导出字段默认的权限json
	 * 
	 * @return String
	 */
	private String getDefaultManageFieldRight() {
		
		
		return SysQueryRightParseService.getDefaultRight(SysQuerySetting.RIGHT_TYPE_MANAGE);
		
	
	}

	/**
	 * 返回当前用户有权限的按钮
	 * 
	 * @param sysQuerySetting
	 * @param rightMap
	 * @return
	 */
	public String getRightManage(SysQuerySetting sysQuerySetting,
			Map<String, Object> rightMap) {
		JSONArray manageJsonArray = JSONArray.fromObject(sysQuerySetting
				.getManageField());
		Map<String, Boolean> managePermission = sysQueryRightParseService
				.getPermission(SysQuerySetting.RIGHT_TYPE_MANAGE,
						sysQuerySetting.getManageField(), rightMap);
		JSONArray jsonArrayTemp = new JSONArray();
		for (Object obj : manageJsonArray) {
			JSONObject jsonObject = JSONObject.fromObject(obj);
			boolean hasPerminssion = managePermission.get(jsonObject
					.get("name"));
			if (hasPerminssion) {
				JSONObject jsonTemp = new JSONObject();
				// 排除掉导出按钮
				if (!SysQuerySetting.EXPORT_BUTTON.equals(jsonObject
						.get("name"))) {
					String urlAndParam = this.mergeUrlAndParams(
							jsonObject.getString("urlPath"),
							jsonObject.getString("urlParams"));
					jsonTemp.accumulate("urlAndParam", urlAndParam);
				}
				jsonTemp.accumulate("name", jsonObject.get("name"));
				jsonTemp.accumulate("desc", jsonObject.get("desc"));
				jsonArrayTemp.add(jsonTemp);
			}
		}
		return jsonArrayTemp.toString();
	}

	/**
	 * 合并url和参数集
	 * 
	 * @param urlPath
	 * @param urlParams
	 * @return
	 * @throws Exception 
	 */
	private String mergeUrlAndParams(String urlPath, String urlParams) {
		urlPath = "[ctx]"+urlPath;  // 特殊处理
		if (StringUtil.isEmpty(urlParams))
			return urlPath;
		StringBuffer sb = new StringBuffer();
		int idx1 = urlPath.indexOf("?");
		if (idx1 > 0) {
			sb.append(urlPath.substring(0, idx1));
		} else {
			sb.append(urlPath);
		}
		sb.append("?");
		JSONArray paramsJsonArray = JSONArray.fromObject(urlParams);
		for (Object obj : paramsJsonArray) {
			JSONObject jsonObject = JSONObject.fromObject(obj);
			String isCustomParam = jsonObject.getString("isCustomParam");
			String key = jsonObject.getString("key");
			String value = jsonObject.getString("value");
			if (SysQuerySql.IS_CUSTOM_PARAM == Short.valueOf(isCustomParam)) {
				sb.append(key);
				sb.append("=");
				if(StringUtil.isNotEmpty(value)){
					sb.append(value);
				}
				sb.append("&");
			} else {
				sb.append(key);
				sb.append("=");
				sb.append("[data." + value + "]"); // 配合模板，固定写法
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * 返回有权限的按钮
	 * 
	 * @param rightTypeManage
	 * @param manageField
	 * @param rightMap
	 * @return
	 */
	public Map<String, Boolean> getManagePermission(int rightTypeManage,
			String manageField, Map<String, Object> rightMap) {
		return sysQueryRightParseService.getPermission(rightTypeManage,
				manageField, rightMap);
	}

	/**
	 * 判断该用户是否用户管理按钮,但是不包含顶部的【导出】按钮
	 * 
	 * @param manageField
	 * @return
	 */
	public boolean hasManageButton(String manageField,
			Map<String, Object> rightMap) {
		Map<String, Boolean> managePermission = sysQueryRightParseService
				.getPermission(SysQuerySetting.RIGHT_TYPE_MANAGE, manageField,
						rightMap);
		// 先排除掉导出按钮
		if (managePermission.containsKey(SysQuerySetting.EXPORT_BUTTON)) {
			managePermission.remove(SysQuerySetting.EXPORT_BUTTON);
		}
		return managePermission.containsValue(true);
	}

}
