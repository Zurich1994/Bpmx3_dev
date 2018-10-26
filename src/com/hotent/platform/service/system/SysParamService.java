package com.hotent.platform.service.system;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.dao.system.SysParamDao;
import com.hotent.platform.model.system.SysParam;

/**
 * 对象功能:组织或人员参数属性 Service类 开发公司:广州宏天软件有限公司 开发人员:csx 创建时间:2012-02-23 17:43:35
 */
@Service
public class SysParamService extends BaseService<SysParam> {
	@Resource
	private SysParamDao dao;

	public SysParamService() {
	}

	@Override
	protected IEntityDao<SysParam, Long> getEntityDao() {
		return dao;
	}
	public List<SysParam> getStatusParam() {

		return dao.getUserParam();
	}

	public List<SysParam> getUserParam() {

		return dao.getUserParam();
	}
	public List<SysParam> getOrgParam(long demId) {
		return dao.getOrgParam(demId);
	}

	public List<SysParam> getOrgParam() {
		return dao.getOrgParam();
	}

	private static String getIconPath(int type) {
		String path = "";
		switch (type) {
		case 1:
			path = "/themes/img/commons/or.gif";
			break;
		case 2:
			path = "/themes/img/commons/and.gif";
			break;
		case 3:
			path = "/themes/img/commons/code.gif";
			break;
		}
		return path;
	}

	public static String setParamIcon(String ctx, String json) {
		if (json == null || json.equals(""))
			return null;
		JSONArray ja = JSONArray.fromObject(json);
		List<Map> ml = (List) JSONArray.toCollection(ja, Map.class);
		if (BeanUtils.isEmpty(ml))
			return null;
		for (int i = 0; i < ml.size(); i++) {
			Map m = ml.get(i);
			int type = Integer.parseInt(m.get("type").toString());
			String icon = ctx + getIconPath(type);
			m.put("icon", icon);
			JSONArray children = JSONArray.fromObject(m.get("children"));
			if (!children.get(0).toString().equals("null")) {
				List<Map> childrenMap = (List)JSONArray.toCollection(children, Map.class);
				if (BeanUtils.isEmpty(childrenMap))
					return null;
				for (int j = 0; j < childrenMap.size(); j++) {
					Map mc = childrenMap.get(j);
					int type_ = Integer.parseInt(mc.get("type").toString());
					String icon_ = ctx + getIconPath(type_);
					mc.put("icon", icon_);
				}
				m.put("children", JSONArray.fromObject(childrenMap));
			}
		}
		JSONArray j1 = JSONArray.fromObject(ml);
		return j1.toString();
	}
	/**
	 * 通过参数KEY获取参数
	 * @param paramKey
	 * @return
	 */
	public SysParam getByParamKey(String paramKey){
		return dao.getByParamKey(paramKey);
	}
	/**
	 * 
	 * @param type 1 用户， 2 组织
	 * @param dimId 组织维度
	 * @return
	 */
	public List<String> getDistinctCategory(Integer type, Long dimId) {
		return dao.getDistinctCategory(type,dimId);
	}

}
