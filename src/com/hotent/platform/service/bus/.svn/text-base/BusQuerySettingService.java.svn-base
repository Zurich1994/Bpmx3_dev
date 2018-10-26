package com.hotent.platform.service.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.entity.FieldShow;
import com.hotent.core.web.query.util.QueryUtil;
import com.hotent.platform.dao.bus.BusQuerySettingDao;
import com.hotent.platform.model.bus.BusQueryRule;
import com.hotent.platform.model.bus.BusQuerySetting;

/**
 * <pre>
 * 对象功能:查询设置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-01-20 15:31:03
 * </pre>
 */
@Service
public class BusQuerySettingService extends BaseService<BusQuerySetting> {
	@Resource
	private BusQuerySettingDao dao;
	@Resource
	private BusQueryRuleService busQueryRuleService;

	public BusQuerySettingService() {
	}

	@Override
	protected IEntityDao<BusQuerySetting, Long> getEntityDao() {
		return dao;
	}

	public BusQuerySetting getByTableNameUserId(String tableName, Long userId) {
		return dao.getByTableNameUserId(tableName, userId);
	}

	public BusQuerySetting getShowList(String tableName, Long userId) {

		BusQueryRule busQueryRule = busQueryRuleService
				.getByTableName(tableName);
		// 当前用户的权限
		Map<String, Object> rightMap = QueryUtil.getRightMap();

		BusQuerySetting busQuerySetting = dao.getByTableNameUserId(tableName,
				userId);
		// 字段权限
		Map<String, Boolean> permission = QueryUtil.getPermission(busQueryRule,
				rightMap);

		if (BeanUtils.isEmpty(busQuerySetting)) {
			busQuerySetting = new BusQuerySetting();
			busQuerySetting.setTableName(tableName);
			busQuerySetting.setFieldShowList(this.getFieldShowList(permission,
					busQueryRule, ""));
		} else {
			busQuerySetting.setFieldShowList(this.getFieldShowList(permission,
					busQueryRule, busQuerySetting.getDisplayField()));
		}
		return busQuerySetting;
	}

	private List<FieldShow> getFieldShowList(Map<String, Boolean> permission,
			BusQueryRule busQueryRule, String displayField) {
		Map<String, Boolean> map = getDisplayFieldShow(displayField);
		Map<String, String> descMap = getDisplayFieldDesc(busQueryRule
				.getDisplayField());
		List<FieldShow> fieldShowList = new ArrayList<FieldShow>();
		for (Iterator<Entry<String, Boolean>> it = permission.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, Boolean> e = (Map.Entry<String, Boolean>) it
					.next();
			String key = e.getKey();
			boolean val = e.getValue();
			if (!val)
				continue;
			FieldShow fieldShow = new FieldShow();
			fieldShow.setName(key);
			int show = 0;
			if (BeanUtils.isNotEmpty(map))
				show = BeanUtils.isEmpty(map.get(key)) ? 0 :(map.get(key)?0:1);
			fieldShow.setShow(show);
			fieldShow.setDesc(descMap.get(key));
			fieldShowList.add(fieldShow);
		}
		return fieldShowList;
	}

	private Map<String, String> getDisplayFieldDesc(String displayField) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtil.isEmpty(displayField))
			return map;
		JSONArray jsonAry = JSONArray.fromObject(displayField);

		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String name = (String) json.get("variable");
			String desc = (String) json.get("desc");
			map.put(name, desc);
		}
		return map;
	}

	private Map<String, Boolean> getDisplayFieldShow(String displayField) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (StringUtil.isEmpty(displayField))
			return null;

		JSONArray jsonAry = JSONArray.fromObject(displayField);

		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String name = (String) json.get("name");
			String show = (String) json.get("show");
			map.put(name, "1".equals(show) ? false : true);
		}
		return map;
	}

}
