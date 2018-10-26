package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;
import java.util.Map;
import com.hotent.core.db.IRollBack;
import com.hotent.core.db.RollbackJdbcTemplate;


import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.model.system.SysQuerySql;
import com.hotent.platform.dao.system.SysQuerySqlDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * <pre>
 * 对象功能:SYS_QUERY_SQL Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-05-21 17:11:31
 * </pre>
 */
@Service
public class SysQuerySqlService extends BaseService<SysQuerySql> {
	@Resource
	private SysQuerySqlDao dao;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private RollbackJdbcTemplate rollbackJdbcTemplate;

	public SysQuerySqlService() {
	}

	@Override
	protected IEntityDao<SysQuerySql, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 获取对象集
	 * 
	 * @param queryFilter
	 */
	public List<SysQuerySql> getAll(QueryFilter queryFilter) {
		return super.getAll(queryFilter);
	}

	/**
	 * 根据json字符串获取SysQuerySql对象
	 * 
	 * @param json
	 * @return
	 */
	public SysQuerySql getSysQuerySql(String json) {
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if (StringUtil.isEmpty(json))
			return null;
		JSONObject obj = JSONObject.fromObject(json);
		SysQuerySql sysQuerySql = (SysQuerySql) JSONObject.toBean(obj, SysQuerySql.class);
		return sysQuerySql;
	}

	public Boolean validSql(String sql, boolean rollback) {
		if (!rollback) {
			jdbcTemplate.execute(sql);
			return true;
		}

		Map<String, Object> param = new HashMap<String, Object>();
		Boolean b = (Boolean) rollbackJdbcTemplate.executeRollBack(new IRollBack() {

			public Object execute(String script, Map<String, Object> map) {
				try {
					jdbcTemplate.execute(script);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}

				return true;
			}
		}, sql, param);
		
		return b;

	}
	
	/**
	 * 解释Sql，用jsonObject的map来解释sql中的特殊字符
	 * @param sql
	 * @param jsonObject
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public  String explainSql(String sql, JSONObject jsonObject) {
		// 拼装sql
		for (Object obj : jsonObject.keySet()) {
			String key = obj.toString();
			String val = jsonObject.getString(key);
			sql = sql.replace(key, val);
		}
		return sql;
	}
	
}
