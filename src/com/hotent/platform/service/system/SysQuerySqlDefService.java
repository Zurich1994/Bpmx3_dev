package com.hotent.platform.service.system;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysQuerySqlDefDao;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQueryMetaField;
import com.hotent.platform.model.system.SysQuerySqlDef;
import com.hotent.platform.model.util.FieldPool;

/**
 *<pre>
 * 对象功能:自定义SQL定义 Service类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Service
public class SysQuerySqlDefService extends  BaseService<SysQuerySqlDef>
{
	@Resource
	private SysQuerySqlDefDao dao;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private SysQueryMetaFieldService sysQueryMetaFieldService;
	@Resource
	private SysQueryViewService sysQueryViewService;
	@Resource
	private CurrentUserService  currentUserService;
	
	
	public SysQuerySqlDefService()
	{
	}
	
	@Override
	protected IEntityDao<SysQuerySqlDef, Long> getEntityDao() 
	{
		return dao;
	}
	
	public void save(SysQuerySqlDef querySqlDef){
		if(querySqlDef.getId()==null||querySqlDef.getId()==0){
			long id=UniqueIdUtil.genId();
			List<SysQueryMetaField> fields=querySqlDef.getMetaFields();
			for(SysQueryMetaField field:fields){
				field.setId(UniqueIdUtil.genId());
				field.setSqlId(id);
				sysQueryMetaFieldService.add(field);
			}
			querySqlDef.setId(id);
			
			dao.add(querySqlDef);
		}
		else{
			sysQueryMetaFieldService.removeBySQLId(querySqlDef.getId());
			List<SysQueryMetaField> fields=querySqlDef.getMetaFields();
			for(SysQueryMetaField field:fields){
				//field.setId(UniqueIdUtil.genId());
				long id=UniqueIdUtil.genId();
				if(field.getId()==null || field.getId()==0L){
					field.setId(id);
					sysQueryMetaFieldService.add(field);
				}
				else{
					sysQueryMetaFieldService.update(field);
				}
			}
			
			dao.update(querySqlDef);
		}
	}
	
	/**
	 * 判断别名是否存在。
	 * @param alias
	 * @param id
	 * @return
	 */
	public boolean isAliasExists(String alias,Long id){
		return dao.isAliasExists(alias, id);
	}
	
	
	/**
	 * 根据json数组字符串获取SysQueryMetaField对象集，同时同步json内容
	 * 
	 * @param jsonArr
	 * @return List<SysQueryField>
	 */
	public List<SysQueryMetaField> getSysQueryMetaFieldArr(String jsonArr) {
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if (StringUtil.isEmpty(jsonArr))
			return null;
		List<SysQueryMetaField> sysQueryFieldList = new ArrayList<SysQueryMetaField>();
		JSONArray jsonArray = JSONArray.fromObject(jsonArr);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			String controlContent = obj.containsKey("controlContent")?obj.getString("controlContent"):"";
			String alarmSetting = obj.containsKey("alarmSetting")?obj.getString("alarmSetting"):"";
			String resultFrom = obj.containsKey("resultFrom")?obj.getString("resultFrom"):"";
			obj.remove("controlContent");
			obj.remove("alarmSetting");
			obj.remove("resultFrom");
			SysQueryMetaField sysQueryField = (SysQueryMetaField) JSONObject.toBean(obj, SysQueryMetaField.class);
			sysQueryField.setControlContent(controlContent);
			sysQueryField.setAlarmSetting(alarmSetting);
			sysQueryField.setResultFrom(resultFrom);
			sysQueryField.setId(null);
			sysQueryFieldList.add(sysQueryField);
		}

		return sysQueryFieldList;
	}

	/**
	 * 根据别名获取SQL定义。
	 * @param alias
	 * @return
	 */
	public SysQuerySqlDef getByAlias(String alias){
		return dao.getByAlias(alias);
	}

	
	/**
	 * 根据json字符串获取SysQuerySqlDef对象
	 * @param json
	 * @return
	 */
	public SysQuerySqlDef getSysQuerySqlDef(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		SysQuerySqlDef sysQuerySqlDef = (SysQuerySqlDef)JSONObject.toBean(obj, SysQuerySqlDef.class);
		return sysQuerySqlDef;
	}
	
	public List<SysQueryMetaField> obtainFieldListBySql(String sql){
		List<SysQueryMetaField> list=new ArrayList<SysQueryMetaField>();
		
		SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
		SqlRowSetMetaData srsmd = srs.getMetaData();
		// 列从1开始算
		for (int i = 1; i < srsmd.getColumnCount() + 1; i++) {
			String cn = srsmd.getColumnName(i).toUpperCase();
			
			String ctn = srsmd.getColumnTypeName(i);
			SysQueryMetaField field = new SysQueryMetaField();
			field.setId(UniqueIdUtil.genId());
			
			field.setName(cn);
			field.setFieldName(cn);
			field.setFieldDesc(cn);
			
			field.setDataType(getDataType(ctn));
			field.setIsShow(SysQueryField.IS_SHOW);
			field.setIsSearch(SysQueryField.IS_NOT_SEARCH);
			field.setControlType(FieldPool.TEXT_INPUT);
			field.setIsVirtual((short)0);
			
			list.add(field);
		}
		
		return list;
	}
	
	private String getDataType(String dataType) {
		String dbType = dataType.toLowerCase();
		
		String number=SysPropertyService.getByAlias("datatype.number");
		String date=SysPropertyService.getByAlias("datatype.date");
		String text=SysPropertyService.getByAlias("datatype.text");
		String varchar=SysPropertyService.getByAlias("datatype.varchar");
		
		boolean isChar=isSpecType(dbType,varchar);
		if(isChar){
			return ColumnModel.COLUMNTYPE_VARCHAR;
		}
		
		boolean isNumber=isSpecType(dbType,number);
		if(isNumber){
			return ColumnModel.COLUMNTYPE_NUMBER;
		}
		
		boolean isDate=isSpecType(dbType,date);
		if(isDate){
			return ColumnModel.COLUMNTYPE_DATE;
		}
		
		boolean isText=isSpecType(dbType,text);
		if(isText){
			return ColumnModel.COLUMNTYPE_TEXT;
		}
		
		return dbType;
	}
	
	/**
	 * 是否包含指定的数据类型。
	 * @param dbType
	 * @param dataType
	 * @return
	 */
	private boolean isSpecType(String dbType,String dataType){
		String[] aryType=dataType.split(",");
		for(String str:aryType){
			if(dbType.equals(str)  || dbType.indexOf(str)>-1){
				return true;
			}
		}
		return false;
	}

	public void delByIds(Long[] lAryId) {
		for(Long id : lAryId){
			SysQuerySqlDef sq = this.getById(id);
			sysQueryMetaFieldService.removeBySQLId(id);
			String alias = sq.getAlias();
			sysQueryViewService.removeBySQLAlias(alias);
			this.delById(id);
		}
	}
	
}
