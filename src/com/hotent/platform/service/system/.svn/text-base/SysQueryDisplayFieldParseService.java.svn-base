package com.hotent.platform.service.system;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQuerySetting;

/**
 * <pre>
 * 对象功能:sql自定义查询 显示字段解析 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:欧晓斌
 * 创建时间:2014年6月4日15:05:28
 * </pre>
 */
@Service
public class SysQueryDisplayFieldParseService {

	@Resource
	private SysQueryFieldService sysQueryFieldService; // 字段配置
	@Resource
	private SysQueryRightParseService sysQueryRightParseService;

	/**
	 * 是否有显示字段
	 * 
	 * @param conditionField
	 * @return
	 */
	public boolean hasDisplayField(String displayField) {
		if (StringUtil.isEmpty(displayField))
			return false;
		JSONArray jsonAry = JSONArray.fromObject(displayField);
		return jsonAry.size() > 0 ? true : false;
	}

	/**
	 * 设置默认显示字段
	 * 
	 * @return
	 */
	public String getDefaultDisplayField(Long sqlId) {
		List<SysQueryField> sysQueryFields = sysQueryFieldService
				.getDisplayFieldListBySqlId(sqlId);
		if (sysQueryFields == null)
			return null;
		JSONArray jsonArray = new JSONArray();
		for (SysQueryField field : sysQueryFields) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("id", field.getId());
			jsonObject.accumulate("name", field.getName());
			jsonObject.accumulate("desc", field.getFieldDesc());
			jsonObject.accumulate("right", this.getDefaultDisplayFieldRight());
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}

	/**
	 * 
	 * 显示字段默认的权限json
	 * 
	 * @return String
	 */
	public String getDefaultDisplayFieldRight() {
		return sysQueryRightParseService.getDefaultRight(SysQuerySetting.RIGHT_TYPE_SHOW);
	}

	/**
	 * 获取字段要显示的权限
	 * @param rightTypeShow
	 * @param displayField
	 * @param rightMap
	 * @return
	 */
	public Object getDisplayFieldPermission(int rightTypeShow,
			String displayField, Map<String, Object> rightMap) {
		return sysQueryRightParseService.getPermission(rightTypeShow,
				displayField, rightMap);
	}
}
