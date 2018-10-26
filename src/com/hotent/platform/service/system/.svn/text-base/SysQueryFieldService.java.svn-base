package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysQueryFieldDao;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.model.util.FieldPool;

/**
 * <pre>
 * 对象功能:SYS_QUERY_FIELD Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-05-22 11:33:37
 * </pre>
 */
@Service
public class SysQueryFieldService extends BaseService<SysQueryField> {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private SysQueryFieldDao dao;
	@Resource
	private SysQuerySettingService sysQuerySettingService;

	public SysQueryFieldService() {
	}

	@Override
	protected IEntityDao<SysQueryField, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 根据json数组字符串获取SysQueryField对象集，同时同步json内容
	 * 
	 * @param jsonArr
	 * @return List<SysQueryField>
	 */
	public List<SysQueryField> getSysQueryFieldArr(String jsonArr) {
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if (StringUtil.isEmpty(jsonArr))
			return null;
		List<SysQueryField> sysQueryFieldList = new ArrayList<SysQueryField>();
		JSONArray jsonArray = JSONArray.fromObject(jsonArr);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			String controlContent = obj.getString("controlContent");
			obj.remove("controlContent");
			SysQueryField sysQueryField = (SysQueryField) JSONObject.toBean(obj, SysQueryField.class);
			sysQueryField.setControlContent(controlContent);
			sysQueryFieldList.add(sysQueryField);
		}

		return sysQueryFieldList;
	}

	/**
	 * 
	 * 根据sqlId找fields.如果为空则返回默认列表值
	 * 
	 * @param sqlId
	 * @return List<SysQueryField>
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public List<SysQueryField> getListBySqlId(Long sqlId) {
		List<SysQueryField> list = dao.getBySqlKey("getListBySqlId", sqlId);
		if (list != null && list.size() > 0)
			return list;
		return null;
	}

	/**
	 * 添加或保存对象集
	 * 
	 * @param sysQueryFields
	 * @throws Exception
	 */
	public void saveOrUpdate(List<SysQueryField> sysQueryFields) throws Exception {
		if (sysQueryFields == null || sysQueryFields.size() == 0)
			return;
		for (SysQueryField sysQueryField : sysQueryFields) {
			if (sysQueryField.getId() == null || sysQueryField.getId() == 0) {
				sysQueryField.setId(UniqueIdUtil.genId());
				this.add(sysQueryField);
			} else {
				this.updateWithoutControlAndFormat(sysQueryField);
			}
		}
	}

	/**
	 * 获取要显示
	 * 
	 * @param sqlId
	 * @return
	 */
	public List<SysQueryField> getDisplayFieldListBySqlId(Long sqlId) {
		List<SysQueryField> list = dao.getDisplayFieldListBySqlId(sqlId);
		if (list != null && list.size() > 0)
			return list;
		return null;
	}

	/**
	 * 获取要查询条件字段的字段集
	 * 
	 * @param sqlId
	 * @return
	 */
	public List<SysQueryField> getConditionFieldListBySqlId(Long sqlId) {
		List<SysQueryField> list = dao.getConditionFieldListBySqlId(sqlId);
		if (list != null && list.size() > 0)
			return list;
		return null;
	}

	/**
	 * 添加字段
	 * 
	 * @param sqlId
	 * @param dsalias
	 * @param sysQuerySql
	 * @throws Exception
	 */
	/*public void addFields(Long sqlId, String sysQuerySql) throws Exception {
		List<SysQueryField> list = this.getSqlField(sqlId, sysQuerySql);
		for (SysQueryField sysQueryField : list) {
			this.add(sysQueryField);
		}
	}*/

	/**
	 * 根据sql找出对应的列名、列的属性、 方法名称描述
	 * 
	 * @param sql
	 * @return List<SysQueryField>
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public List<SysQueryField> getSqlField(Long sqlId, String sysQuerySql) throws Exception {
		if (StringUtil.isEmpty(sysQuerySql))
			return null;
		List<SysQueryField> fields = new ArrayList<SysQueryField>();
//		JdbcHelper<Map<String, Object>, ?> jdbcHelper = ServiceUtil.getJdbcHelper(dsalias);
//		JdbcTemplate template = jdbcHelper.getJdbcTemplate();
//		DbContextHolder.setDataSource(dsalias);
		// 得到sql的结果集
		SqlRowSet srs = jdbcTemplate.queryForRowSet(sysQuerySql);
		SqlRowSetMetaData srsmd = srs.getMetaData();
		// 列从1开始算
		for (int i = 1; i < srsmd.getColumnCount() + 1; i++) {
			String cn = srsmd.getColumnName(i);
			String ctn = srsmd.getColumnTypeName(i);
			SysQueryField field = new SysQueryField();
			field.setId(UniqueIdUtil.genId());
			field.setSqlId(sqlId);
			field.setName(cn);
			field.setFieldDesc(cn);
			field.setType(this.getJdbcType(ctn));
			field.setIsShow(SysQueryField.IS_SHOW);
			field.setIsSearch(SysQueryField.IS_NOT_SEARCH);
			field.setControlType(FieldPool.TEXT_INPUT);
			fields.add(field);
		}
		return fields;
	}
	
	/**
	 * 根据不同数据库传入不同的数据类型，获取统一的jdbc类型,详细参考:function.ftl 中 getJdbcType 方法
	 * 
	 * @param dataType 
	 * @return NUMERIC,VARCHAR,DATE,TIMESTAMP,CLOB
	 */
	public String getJdbcType(String dataType) {
		String dbType = dataType.toLowerCase();
		if ("int".endsWith(dbType) || "double".equals(dbType)
				|| "float".equals(dbType) || "decimal".equals(dbType)
				|| "number".endsWith(dbType) || "numeric".startsWith(dbType)) {
			return "NUMERIC";
		} else if (dbType.indexOf("char") > -1) {
			return "VARCHAR";
		} else if ("date".equals(dbType)) {
			return "DATE";
		} else if (dbType.indexOf("timestamp") > -1
				|| "datetime".equals(dbType)) {
			return "TIMESTAMP";
		} else if ("text".endsWith("text") || dbType.endsWith("clob")) {
			return "CLOB";
		} else {
			return dbType;
		}
	}
	
	
	/**
	 * 
	 * 更新field不更新控件类型和格式化
	 * @param sysQueryField 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public int updateWithoutControlAndFormat(SysQueryField sysQueryField) {
		return dao.update("updateWithoutControlAndFormat", sysQueryField);
	}
	
	/**
	 * 
	 * 同步setting的查询条件json的控件类型和field字段的控件类型，如发生同步处理会更新setting数据库
	 * @param sysQueryField
	 * @param sysQuerySetting 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void syncConditionControlAndField(SysQueryField sysQueryField,SysQuerySetting sysQuerySetting){
		JSONArray jsonArray = new JSONArray();
		if (sysQuerySetting.getConditionField() != null && !sysQuerySetting.getConditionField().equals("null") && !sysQuerySetting.getConditionField().equals("")) {
			jsonArray = JSONArray.fromObject(sysQuerySetting.getConditionField());
			
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if(jsonObject.getLong("id")==sysQueryField.getId()){
					if(sysQueryField.getControlType()!=null&&jsonObject.getInt("ct")!=sysQueryField.getControlType()){
						jsonObject.element("ct", sysQueryField.getControlType());
						jsonArray.set(i, jsonObject);
						sysQuerySetting.setConditionField(jsonArray.toString());
						sysQuerySettingService.update(sysQuerySetting);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * 删除数据
	 * @param lAryId
	 */
	public void delBySqlIds(Long[] lAryId) {
		if(lAryId != null && lAryId.length > 0){
			for(Long sqlId : lAryId){
				dao.delBySqlId(sqlId);
			}
		}
	}
}
