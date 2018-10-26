package com.hotent.dbFunc.service.dbFunc;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.JdbcTemplateUtil;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.dbFunc.dao.dbFunc.DbFuncDao;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DbFuncService extends BaseService<DbFunc>
{
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private DbFuncDao dao;
	
	public DbFuncService()
	{
	}
	
	@Override
	protected IEntityDao<DbFunc,Long> getEntityDao() 
	{
		return dao;
	}

	public List<DbFunc> getByTableName(String tableName) 
	{
		// TODO Auto-generated method stub
		return dao.getByTableName(tableName);
	}
	
	public DbFunc getByTableNameandFuncName(String table_name, String func_name) {
		// TODO Auto-generated method stub
		return dao.getByTableNameandFuncName(table_name,func_name);
	}
	
	/**
	 * 根据别名获取对应对话框的数据。
	 * @author yangxiao
	 * @param alias
	 *            对话框别名。
	 * @param params
	 *            参数集合。
	 * @return
	 * @throws Exception
	*/
	@SuppressWarnings("unchecked")
	public List getData1(DbFunc dbFunc, Map<String, Object> params) throws Exception {
//		JdbcHelper jdbcHelper = ServiceUtil.getJdbcHelper(bpmFormDialog.getDsalias());		
		List list = null;
		List<DialogField> displayList = dbFunc.getDisplayList();
		List<DialogField> conditionList = dbFunc.getConditionList();
		String objectName = dbFunc.getObjname();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectName", objectName);
		map.put("displayList", displayList);
		map.put("conditionList", conditionList);
		map.put("sortList", dbFunc.getSortList());

		if (dbFunc.getStyle() == 0) {

				String sql = ServiceUtil.getSql(map, params);				
//				List list = jdbcHelper.getPage(currentPage, pageSize, sql, params, pageBean);
 				//返回list结果集，不再存入到bpmFormDialog对象中
			 list = JdbcTemplateUtil.getPage1(dbFunc.getDs_Alias(), sql, params);
			} else {
				String sql = ServiceUtil.getSql(map, params);
//				List<Map<String, Object>> list = jdbcHelper.queryForList(sql, params);
			    list=jdbcTemplate.queryForList(sql);
			}		
		
		return list;
	}
	
	public String getResults(String namespace, String method) {
		return dao.getResults(namespace, method);
	}

	public String getCondition(String namespace, String method) {
		return dao.getCondition(namespace, method);
	}

	public String getSort(String namespace, String method) {
		return dao.getSort(namespace, method);
	}

	public String getSetting(String namespace, String method) {
		return dao.getSetting(namespace, method);
	}

	public String getDisplay(String namespace, String method) {
		return dao.getDisplay(namespace, method);
	}
	public String getType(String namespace, String method) {
		return dao.getType(namespace, method);
	}
	
}
