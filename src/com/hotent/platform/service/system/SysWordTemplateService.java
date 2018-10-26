package com.hotent.platform.service.system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysWordTemplateDao;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.SubTable;
import com.hotent.platform.model.system.SysWordTemplate;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 *<pre>
 * 对象功能:word套打模板 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:guojh
 * 创建时间:2015-03-23 10:48:07
 *</pre>
 */
@Service
public class SysWordTemplateService extends  BaseService<SysWordTemplate>
{
	@Resource
	private SysWordTemplateDao dao;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private SysQueryFieldService sysQueryFieldService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	
	public SysWordTemplateService()
	{
	}
	
	@Override
	protected IEntityDao<SysWordTemplate, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 保存 word套打模板 信息
	 * @param sysWordTemplate
	 */
	public void save(SysWordTemplate sysWordTemplate){
		Long id=sysWordTemplate.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			sysWordTemplate.setId(id);
			this.add(sysWordTemplate);
		}
		else{
			this.update(sysWordTemplate);
		}
	}
	
	public SysWordTemplate getByAlias(String alias){
		return dao.getByAlias(alias);
	}
	
	public Map<String, Object> preivew(SysWordTemplate sysWordTemplate, Long pk) throws Exception{
		String businessKey=pk.toString();
		String type = sysWordTemplate.getType();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> mainFields = new HashMap<String, Object>();
		Map<String, SubTable> subTable = new HashMap<String, SubTable>();
		if(SysWordTemplate.FORM_TYPE.equals(type)){
			BpmFormData data = bpmFormHandlerService.getByKey(sysWordTemplate.getTableId(), businessKey);
			mainFields = data.getMainFields();
			subTable = data.getSubTableMap();
		}else {
			String sqlJson = sysWordTemplate.getSql();
			String dsAlias = sysWordTemplate.getDsAlias();
			JdbcTemplate template = ServiceUtil.getJdbcTemplate(dsAlias);
			JSONObject sqlObj = JSONObject.fromObject(sqlJson);
			String main = sqlObj.getString("main").replaceAll(SysWordTemplate.PK_KEY_, businessKey);
			// 如果使用queryForMap，则需要确保查询出来的结果有且只有一条
			List<Map<String, Object>> mainList = template.queryForList(main);
			if(BeanUtils.isNotEmpty(mainList)){
				mainFields = mainList.get(0);
			}
			
			JSONObject subTables = sqlObj.getJSONObject("subTable");
			for(Object subTableName:subTables.keySet()){
				String tableName = subTableName.toString();
				String sql = subTables.getString(tableName).replaceAll(SysWordTemplate.PK_KEY_, businessKey);
				SubTable sub = new SubTable();
				sub.setTableName(tableName);
				List<Map<String, Object>> dataList = template.queryForList(sql);
				sub.setDataList(dataList);
				subTable.put(tableName, sub);
			}
			template.getDataSource().getConnection().close();
		}
		dataMap.put("main", mainFields);
		dataMap.put("subTable", subTable);
		return dataMap;
	}
	
	/**
	 * 根据sql找出对应的列名、列的属性、 方法名称描述
	 * 
	 * @param sql
	 * @return List<SysQueryField>
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	private Map<String, Object> getSqlField(String sqlJson, JdbcTemplate template) throws Exception {
		if (StringUtil.isEmpty(sqlJson)) return null;
		Map<String, Object> result = new HashMap<String, Object>();
		List<BpmFormField> mainfields = new ArrayList<BpmFormField>();
		List<Map<String, Object>> subtables = new ArrayList<Map<String, Object>>();
		result.put("mainfields", mainfields);
		result.put("subtables", subtables);
		JSONObject sqlObj = JSONObject.fromObject(sqlJson);
		getFieldList(sqlObj, "main", mainfields, template);
		
		JSONObject subTables = sqlObj.getJSONObject("subTable");
		int subTableId = 1101;// 为各个子表添加一个ID
		for(Object subTableName:subTables.keySet()){
			List<BpmFormField> subTableFields = new ArrayList<BpmFormField>();
			getFieldList(subTables, subTableName.toString(), subTableFields, template);
			Map<String, Object> subTable = new HashMap<String, Object>();
			subTable.put("name", subTableName);
			subTable.put("tablename", subTableName);
			subTable.put("subfields", subTableFields);
			subTable.put("id", subTableId++);
			subtables.add(subTable);
		}
		return result;
	}
	
	/**
	 * 1、根据objKey，从obj中获取对应的String，替换其中的SysWordTemplate.PK_KEY_为"0"
	 * 2、通过jdbcTemplate执行sql语句，获取元数据并填充到list中
	 * @param obj
	 * @param objKey
	 * @param list 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	private void getFieldList(JSONObject obj, String objKey, List<BpmFormField> list, JdbcTemplate template){
		String sql = obj.getString(objKey).replaceAll(SysWordTemplate.PK_KEY_, "0");
		SqlRowSet srs = template.queryForRowSet(sql);
		SqlRowSetMetaData srsmd = srs.getMetaData();
		// 列从1开始算
		BpmFormField field = null;
		for (int i = 1; i < srsmd.getColumnCount() + 1; i++) {
			// 如果字段有别名，则取别名，没有则取字段名
			String cn = srsmd.getColumnLabel(i);
			
			String ctn = srsmd.getColumnTypeName(i);
			field = new BpmFormField();
			field.setFieldDesc(cn);
			field.setFieldName(cn);
			field.setFieldType(sysQueryFieldService.getJdbcType(ctn).toLowerCase());
			list.add(field);
		}
	}

	public Map<String, Object> getTableMap(SysWordTemplate sysWordTemplate) throws Exception {
		String type = sysWordTemplate.getType();
		Map<String, Object> tableMap = null;
		if(SysWordTemplate.FORM_TYPE.equals(type)){
			tableMap = bpmFormDefService.getAllFieldsByTableId(sysWordTemplate.getTableId());
		}else {
			String dsAlias = sysWordTemplate.getDsAlias();
			JdbcTemplate template = ServiceUtil.getJdbcTemplate(dsAlias);
			tableMap = getSqlField(sysWordTemplate.getSql(), template);
		}
		return tableMap;
	}

	public List<SysWordTemplate> getAllTemplate() {
		return dao.getAllTemplate();
	}
	
}
