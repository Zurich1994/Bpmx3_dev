package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.excel.Excel;
import com.hotent.core.excel.editor.IFontEditor;
import com.hotent.core.excel.style.Color;
import com.hotent.core.excel.style.font.BoldWeight;
import com.hotent.core.excel.style.font.Font;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQuerySetting;

/**
 * <pre>
 * 对象功能:sql自定义查询 导出字段解析 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:欧晓斌
 * 创建时间:2014年6月4日15:05:28
 * </pre>
 */
@Service
public class SysQueryExportFieldParseService {
	@Resource
	private SysQueryRightParseService sysQueryRightParseService;
	@Resource
	private SysQueryFieldService sysQueryFieldService; // 字段配置

	/**
	 * 是否有显示字段
	 * 
	 * @param conditionField
	 * @return
	 */
	public boolean hasExportField(String exportField) {
		if (StringUtil.isEmpty(exportField))
			return false;
		JSONArray jsonAry = JSONArray.fromObject(exportField);
		return jsonAry.size() > 0 ? true : false;
	}

	/**
	 * 设置默认显示字段
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDefaultExportField(Long sqlId) {
		List<SysQueryField> sysQueryFields = sysQueryFieldService.getDisplayFieldListBySqlId(sqlId);
		if (sysQueryFields == null)
			return null;
		JSONArray jsonArray = new JSONArray();
		for (SysQueryField field : sysQueryFields) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("id", field.getId());
			jsonObject.accumulate("name", field.getName());
			jsonObject.accumulate("desc", field.getFieldDesc());
			jsonObject.accumulate("right", this.getDefaultExportFieldRight());
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}

	/**
	 * 
	 * 显示导出字段默认的权限json
	 * 
	 * @return String
	 */
	private String getDefaultExportFieldRight() {
		return sysQueryRightParseService.getDefaultRight(SysQuerySetting.RIGHT_TYPE_EXPORT);
	}

	/**
	 * 获取用户用户的导出字段权限
	 * 
	 * @param rightTypeExport
	 * @param exportField
	 * @param rightMap
	 * @return
	 */
	public Map<String, Boolean> getExportFieldPermission(int rightTypeExport, String exportField, Map<String, Object> rightMap) {
		return sysQueryRightParseService.getPermission(rightTypeExport, exportField, rightMap);
	}

	/**
	 * 获取对应的导出字段显示名称
	 * 
	 * @param exportField
	 * @param exportFieldMap
	 * @return
	 */
	public Map<String, String> getExportFieldShowName(String exportField, Map<String, Boolean> exportFieldMap) {
		Map<String, String> returnShowName = new LinkedHashMap<String, String>();
		JSONArray exportFieldJsonAry = JSONArray.fromObject(exportField);
		if (JSONUtil.isEmpty(exportFieldJsonAry) || BeanUtils.isEmpty(exportFieldMap))
			return returnShowName;
		for (Object obj : exportFieldJsonAry) {
			JSONObject exportFieldJson = JSONObject.fromObject(obj);
			String nameField = exportFieldJson.getString("name");
			Boolean isShowField = exportFieldMap.get(nameField);
			if (isShowField) {
				returnShowName.put(nameField, exportFieldJson.getString("desc"));
			}
		}
		return returnShowName;
	}

	/**
	 * 获取出有权限字段的数据
	 * 
	 * @param list
	 * @param exportFieldMap
	 * @param hashMap
	 * @return
	 */
	public List<Map<String, Object>> getRightDataList(List<Map<String, Object>> dataList, Map<String, Boolean> exportFieldMap) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> dataMap : dataList) {
			Map<String, Object> flagObj = new LinkedHashMap<String, Object>();
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {

				Boolean isShow = false;
				for (String key : exportFieldMap.keySet()) {
					if (key.equalsIgnoreCase(entry.getKey())) {
						isShow = exportFieldMap.get(key);
						break;
					}
				}

				if (isShow != null && isShow) {
					flagObj.put((String) entry.getKey(), entry.getValue());
				}
			}
			returnList.add(flagObj);
		}
		return returnList;
	}

	/**
	 * 获取Excel
	 * 
	 * @param rightDataList
	 * @param displayFieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Excel getExcel(List<Map<String, Object>> rightDataList, Map<String, String> displayFieldName) {
		Excel excel = new Excel();

		List<String> columnName = this.mapValueToList(displayFieldName);
		// 设置页名
		// excel.sheet().sheetName("test");// 重命名当前处于工作状态的表的名称
		excel.row(0, 0).value(columnName.toArray()).font(new IFontEditor() {
			// 设置字体
			public void updateFont(Font font) {
				font.boldweight(BoldWeight.BOLD);// 粗体
			}
		}).bgColor(Color.GREY_25_PERCENT);
		// 取得表的数据
		for (int i = 0; i < rightDataList.size(); i++) {
			if (BeanUtils.isNotEmpty(rightDataList.get(i))) {
				List<Object> values = this.mapValueToList(rightDataList.get(i));
				// 从第2行写入数据
				excel.row(i + 1).value(values.toArray()).dataFormat("@");
			}
		}
		return excel;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private List mapValueToList(Map map) {
		List list = new ArrayList<Object>();
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			list.add(map.get(s));
		}
		return list;
	}

}
