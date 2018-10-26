package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import com.hotent.core.page.PageList;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.page.PageBean;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.platform.dao.bpm.BpmFormQueryDao;
import com.hotent.platform.model.bpm.BpmFormQuery;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.model.form.QueryResult;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 对象功能:通用表单查询 Service类 开发公司:广州宏天软件有限公司 开发人员:ray 创建时间:2012-11-27 10:37:12
 */
@Service
public class BpmFormQueryService extends BaseService<BpmFormQuery> {
	@Resource
	private BpmFormQueryDao dao;

	public BpmFormQueryService() {
	}

	@Override
	protected IEntityDao<BpmFormQuery, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 根据别名获取对话框对象。
	 * 
	 * @param alias
	 * @return
	 */
	public BpmFormQuery getByAlias(String alias) {
		return dao.getByAlias(alias);
	}

	/**
	 * 检查别名是否唯一
	 * 
	 * @param alias
	 * @return
	 */
	public boolean isExistAlias(String alias) {
		return dao.isExistAlias(alias) > 0;
	}

	/**
	 * 检查别名是否唯一。
	 * 
	 * @param alias
	 * @return
	 */
	public boolean isExistAliasForUpd(Long id, String alias) {
		return dao.isExistAliasForUpd(id, alias) > 0;
	}

	/**
	 * 根据别名获取对应查询的数据
	 * 
	 * @param bpmFormQuery
	 *            表单查询对象
	 * @param params
	 *            参数
	 * @param page
	 *            页码
	 * @param pageSize
	 *            每页记录条数
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public QueryResult getData(BpmFormQuery bpmFormQuery, String queryData, Integer page, Integer pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(queryData)) {
			JSONObject jsonObj = JSONObject.fromObject(queryData);
			Iterator<?> it = jsonObj.keys();
			while (it.hasNext()) {
				String key = it.next().toString();
				String value = jsonObj.getString(key);
				params.put(key, value);
			}
		}

//		JdbcHelper<Map<String, Object>, ?> jdbcHelper = ServiceUtil.getJdbcHelper(bpmFormQuery.getDsalias());
		
		List<DialogField> resultList = bpmFormQuery.getReturnList();
		List<DialogField> conditionList = bpmFormQuery.getConditionList();
		String objectName = bpmFormQuery.getObjName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectName", objectName);
		map.put("returnList", resultList);
		map.put("conditionList", conditionList);
		map.put("sortList", bpmFormQuery.getSortList());
		QueryResult queryResult = new QueryResult();
		// 是否需要分页。
		if (page > 0 && pageSize > 0) {
			String sql = ServiceUtil.getSql(map, params);
//			PageBean pageBean = new PageBean(page, pageSize);
//			 List<Map<String, Object>> list= jdbcHelper.getPage(page, pageSize, sql, params,pageBean);
			
			PageList pageList=JdbcTemplateUtil.getPage(bpmFormQuery.getDsalias(), page, pageSize, sql, params);
			List list =  handList(pageList);
			queryResult.setList(list);
			queryResult.setIsPage(1);
			queryResult.setPage(page);
			queryResult.setPageSize(pageSize);
			PageBean pageBean=pageList.getPageBean();
			int totalCount = pageBean.getTotalCount();
			int totalPage = pageBean.getTotalPage();
			queryResult.setTotalCount(totalCount);
			queryResult.setTotalPage(totalPage);
		} else {
			String sql = ServiceUtil.getSql(map, params);
//			List<Map<String, Object>> list = jdbcHelper.queryForList(sql, params);

			List<Map<String, Object>> list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(bpmFormQuery.getDsalias()).queryForList(sql, params);
			list = handList(list);
			queryResult.setList(list);
			queryResult.setTotalCount(list.size());
		}
		
		return queryResult;
	}

	/**
	 * 处理list
	 * 
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>> handList(List<Map<String, Object>> list) {
		List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) list.get(i);
			Map<String, Object> rtnMap = handMap(map);
			rtnList.add(rtnMap);
		}
		return rtnList;
	}

	/**
	 * 处理Map
	 * 
	 * @param map
	 * @return
	 */
	private Map<String, Object> handMap(Map<String, Object> map) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			// 把数据转换成小写
			String key = entry.getKey().toLowerCase();
			Object obj = entry.getValue();
			if (obj == null) {
				rtnMap.put(key, "");
				continue;
			}
			// 对时间字段单独处理。
			if (obj instanceof Date) {
				String format = "yyyy-MM-dd HH:mm:ss";
				String str = TimeUtil.getDateTimeString((Date) obj, format);
				rtnMap.put(key, str);
			} else {
				rtnMap.put(key, obj);
			}
		}
		return rtnMap;
	}
}
