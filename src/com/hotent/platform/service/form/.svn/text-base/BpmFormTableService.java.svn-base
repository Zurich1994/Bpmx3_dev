package com.hotent.platform.service.form;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.service.BaseService;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.ITableOperator;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.util.AppConfigUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.XmlBeanUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.form.BpmDataTemplateDao;
import com.hotent.platform.dao.form.BpmFormDefDao;
import com.hotent.platform.dao.form.BpmFormFieldDao;
import com.hotent.platform.dao.form.BpmFormHandlerDao;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.dao.system.IdentityDao;
import com.hotent.platform.model.form.BpmDataTemplate;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.Identity;

import com.hotent.platform.model.system.SysDataSource;

import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.util.FieldPool;

import com.hotent.platform.service.system.SysDataSourceLService;
import com.hotent.platform.service.system.SysDataSourceService;
import com.hotent.platform.xml.table.BpmFormTableXml;
import com.hotent.platform.xml.table.BpmFormTableXmlList;
import com.hotent.platform.xml.util.MsgUtil;
import com.hotent.platform.xml.util.XmlUtil;


/**
 * <pre>
 * 对象功能:自定义表 Service类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:xwy 
 * 创建时间:2011-11-30 14:29:22
 * </pre>
 * 
 */
@Service
public class BpmFormTableService extends BaseService<BpmFormTable> {
	@Resource
	private BpmFormTableDao dao;
	@Resource
	private BpmFormFieldDao bpmFormFieldDao;
	@Resource
	private ITableOperator tableOperator;
//	@Resource
//	private SysDataSourceDao sysDataSourceDao;
	@Resource
	private BpmFormDefDao bpmFormDefDao;
	@Resource
	private BpmFormHandlerDao bpmFormHandlerDao;
	@Resource
	private IdentityDao identityDao;
	@Resource
	private BpmDataTemplateDao bpmDataTemplateDao;

	public BpmFormTableService() {
	}

	@Override
	protected IEntityDao<BpmFormTable, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 添加外部表。
	 * 
	 * @param table
	 * @param fields
	 * @throws Exception
	 */
	public void addExt(BpmFormTable table) throws Exception {
		List<BpmFormField> fields=table.getFieldList();
		long tableId = UniqueIdUtil.genId();
		// 添加表
		table.setTableId(tableId);
		table.setIsPublished((short) 1);
		table.setIsExternal(1);
		// 设置注释
		if (StringUtil.isEmpty(table.getTableDesc())) {
			table.setTableDesc(table.getTableName());
		}
		// 获取数据源名称。
		String dsAlias = table.getDsAlias();
		if (dsAlias.equals(BpmConst.LOCAL_DATASOURCE)) {
			table.setDsName("本地数据库");
		} else {
			// SysDataSource sysDataSource = sysDataSourceDao.getByAlias(dsAlias);
			// table.setDsName(sysDataSource.getName());
			SysDataSource sysDataSource = AppUtil.getBean(SysDataSourceService.class).getByAlias(dsAlias);
			table.setDsName(sysDataSource.getName());
		}
		// 设定主键字段的数据类型。
		setKeyDataType(table);
		dao.add(table);
		// 添加字段。
		addFields(tableId, fields, true);
	}

	/**
	 * 设定主键字段类型那个。
	 * 
	 * @param table
	 */
	private void setKeyDataType(BpmFormTable table) {
		String pkField = table.getPkField();
		Short pkDataType = BpmFormTable.PKTYPE_NUMBER;
		List<BpmFormField> fields = table.getFieldList();
		for (BpmFormField field : fields) {
			if (field.getFieldName().equalsIgnoreCase(pkField)) {
				if (field.getFieldType().contains(BpmFormField.DATATYPE_NUMBER)) {
					pkDataType = BpmFormTable.PKTYPE_NUMBER;
				} else {
					pkDataType = BpmFormTable.PKTYPE_STRING;
				}
			}
		}
		table.setKeyDataType(pkDataType);
	}

	/**
	 * 添加表数据定义。
	 * 
	 * @param table
	 * @param fields
	 * @return -1 表示用户表字段已经存在。
	 * @throws Exception
	 */
	public int addFormTable(BpmFormTable table) throws Exception {
		List<BpmFormField> fields = table.getFieldList();
		// boolean isFieldExist=hasReserveFileds(fields);
		//
		// if(isFieldExist){
		// return -1;
		// }

		long tableId = UniqueIdUtil.genId();
		// 添加表
		table.setTableId(tableId);
		table.setDsAlias(DataSourceUtil.DEFAULT_DATASOURCE);
		table.setDsName(DataSourceUtil.DEFAULT_DATASOURCE);
		table.setIsExternal(0);
		// 设置注释
		if (StringUtil.isEmpty(table.getTableDesc())) {
			table.setTableDesc(table.getTableName());
		}
		SysUser sysUser = ContextUtil.getCurrentUser();

		table.setCreateBy(sysUser.getUserId());
		table.setCreator(sysUser.getFullname());
		table.setCreatetime(new Date());

		dao.add(table);
		// 添加字段。
		addFields(tableId, fields, false);

		return 0;

	}

	/**
	 * 计算字段，这种情况用于在表字段有变化时获取变化的字段，包括添加的字段和删除的字段。
	 * 
	 * @param fields
	 * @param orginFieldList
	 * @param isDelete
	 * @return
	 */
	private Map<String, List<BpmFormField>> caculateFields(List<BpmFormField> fields, List<BpmFormField> orginFieldList) {
		return caculateFields(fields, orginFieldList, true);
	}
	
	/**
	 * 计算字段，这种情况用于在表字段有变化时获取变化的字段，包括添加的字段和删除的字段。
	 * 
	 * <pre>
	 * 需要注意的是：
	 * 	这种情况在表创建之后才会用到。
	 *  更新时：
	 *  数据库中已有的字段，字段名，字段类型不能修改。
	 *  可以添加字段。
	 *  返回值类型：Map&lt;String, List&lt;BpmFormField>>
	 *  
	 *  获取更新的字段：
	 *  Mapp&lt;String, Listp&lt;BpmFormField>> paraTypeMap= caculateFields(fields,orginFieldList);
	 *  
	 *  新添加的列：
	 *  Listp&lt;BpmFormField> addFields=paraTypeMap.get("add");
	 *  更新的列:
	 *  Listp&lt;BpmFormField> updFields=paraTypeMap.get("upd");
	 *  
	 *  更新列时将原来的tableid和fieldid放到新列中。
	 * 
	 * </pre>
	 * 
	 * @param fields
	 * @param orginFieldList
	 * @param isDelete
	 * @return
	 */
//	private Map<String, List<BpmFormField>> caculateFields(
	//		List<BpmFormField> fields, List<BpmFormField> orginFieldList,boolean isDelete) {
		private Map<String, List<BpmFormField>> caculateFields(List<BpmFormField> fields, List<BpmFormField> orginFieldList, boolean isDelete) {
		Map<String, BpmFormField> orginMap = new HashMap<String, BpmFormField>();
		Map<String, BpmFormField> curMap = new HashMap<String, BpmFormField>();

		Map<String, List<BpmFormField>> resultMap = new HashMap<String, List<BpmFormField>>();

		for (BpmFormField field : orginFieldList) {
			String fieldName = field.getFieldName().toLowerCase();
			orginMap.put(fieldName, field);
		}

		int i = 1;
		for (BpmFormField field : fields) {
			String fieldName = field.getFieldName().toLowerCase();

			curMap.put(fieldName, field);
			field.setSn(i);
			i++;
		}

		for (BpmFormField field : fields) {
			String fieldName = field.getFieldName().toLowerCase();
			if (orginMap.containsKey(fieldName)) {
				BpmFormField orginField = orginMap.get(fieldName);
				field.setFieldId(orginField.getFieldId());
				field.setTableId(orginField.getTableId());
				addField("upd", resultMap, field);
			} else {
				addField("add", resultMap, field);
			}
		}
		// 如果现有的字段中不包含上次的字段则将字段做删除标记。
		int deleteNum = 0;
		if(isDelete){               //是否要做标记删除字段的
			deleteNum = 1;
		}
		for (BpmFormField field : orginFieldList) {
			String fieldName = field.getFieldName().toLowerCase();
			if (!curMap.containsKey(fieldName)) {
				field.setIsDeleted((short) deleteNum);
				addField("upd", resultMap, field);
			}
		}

		return resultMap;
	}

	private void addField(String key, Map<String, List<BpmFormField>> resultMap, BpmFormField field) {
		List<BpmFormField> list;
		if (resultMap.containsKey(key)) {
			list = resultMap.get(key);
		} else {
			list = new ArrayList<BpmFormField>();
			resultMap.put(key, list);
		}
		list.add(field);
	}

	/**
	 * 判断添加字段中是否有必填的字段。
	 * 
	 * @param list
	 * @return
	 */
	// private boolean hasNotNullField(List<BpmFormField> list) {
	// for (BpmFormField field : list) {
	// if (field.getIsRequired() == 1) {
	// return true;
	// }
	// }
	// return false;
	// }
	
	public void updExtTable(BpmFormTable bpmFormTable)throws Exception {
		
		List<BpmFormField> fields = bpmFormTable.getFieldList();
		Long tableId=bpmFormTable.getTableId();
		//首先根据tableId删除字段
		bpmFormFieldDao.delByTableId(tableId);
		
		//同意重新添加字段
		addFields(tableId, fields, true);
		// 更新表
		dao.update(bpmFormTable);
			
	}

	/**
	 * 更新表的设计。
	 * 
	 * @param table
	 * @param fields
	 * @return -1 表示当前字段中存在字段 curentUserId。-2 有数据字段不能设置为非空。
	 * @throws Exception
	 */
	public int upd(BpmFormTable table,int generate) throws Exception {
		
		List<BpmFormField> fields = new ArrayList<BpmFormField>();
		fields.addAll(table.getFieldList());

		Long tableId = table.getTableId();
		String tableName = table.getTableName().trim();
		// 获取表定义。
		BpmFormTable originTable = dao.getById(tableId);

		Long mainTableId = tableId;
		int isMain = table.getIsMain();
		if (isMain == 0) {
			mainTableId = table.getMainTableId();
		}
		// 修改之前的字段列表
		List<BpmFormField> originFieldList = bpmFormFieldDao.getFieldsByTableId(tableId);
		
		// 设置注释
		if (StringUtil.isEmpty(table.getTableDesc())) {
			table.setTableDesc(tableName);
		}
		// 该表已经有表单定义了。
		boolean hasFormDef = false;
		if (tableId > 0) {
			hasFormDef = bpmFormDefDao.isTableHasFormDef(mainTableId);
		}
		
		// 该表已经有数据了。
		boolean hasData = false;
		if (StringUtil.isNotEmpty(tableName)) {			
			if(table.getIsPublished()==BpmFormTable.IS_PUBLISH){
				Map map = new HashMap();
				map.put("tableName", TableModel.CUSTOMER_TABLE_PREFIX+tableName);
				hasData = bpmFormDefDao.isTableHasData(map);
			}
		}
		
		// 已经有表单定义，
		// 1.不能删除字段
		// 2.可以更新字段
		// 3.可以添加字段。
		if (hasFormDef||hasData) {
			if (BeanUtils.isNotEmpty(fields)) {
				fields = convertFields(fields, false);
			}

			Map<String, List<BpmFormField>> resultMap = caculateFields(fields,originFieldList);

			// 处理新增的字段
			List<BpmFormField> addList = resultMap.get("add");
			// 更新表
			dao.update(table);
			// 需要更新的字段
			List<BpmFormField> updList = resultMap.get("upd");
			for (BpmFormField field : updList) {
				bpmFormFieldDao.update(field);
			}
			// 没有增加的列就直接返回。
			if (BeanUtils.isEmpty(addList))
				return 0;
			int i = updList.size();
			int k = 0;
			// 添加字段
			for (BpmFormField field : addList) {
				k++;
				field.setFieldId(UniqueIdUtil.genId());
				field.setTableId(tableId);
				field.setSn(k + i);
				bpmFormFieldDao.add(field);
				ColumnModel columnModel = getByField(field, 2);
				tableOperator.addColumn(TableModel.CUSTOMER_TABLE_PREFIX+ tableName, columnModel);
			}
		}
		// 没有表单定义的情况。
		else {
			//已发布的情况
			if (table.getIsPublished() == 1) {
				// 主表的情况
				if (table.getIsMain() == 1) {
					// 获取所有的字表
					List<BpmFormTable> tableList = dao.getSubTableByMainTableId(tableId);
					// 删除子表的外键
					for (BpmFormTable subTable : tableList) {
						String tabName = TableModel.CUSTOMER_TABLE_PREFIX+ subTable.getTableName();
						String shortTabName =  subTable.getTableName();
						tableOperator.dropForeignKey(tabName, "fk_" + shortTabName);
					}
					dao.update(table);
					// 删除列的定义
					bpmFormFieldDao.delByTableId(tableId);
					// 添加表列的定义
					addFields(tableId, fields, false);
					// 删除原来的表
					tableOperator.dropTable(TableModel.CUSTOMER_TABLE_PREFIX+ originTable.getTableName());
					// 重新创建物理表
					List<BpmFormField> fieldList = convertFields(fields, false);
					table.setFieldList(fieldList);
					createTable(table);
					String pkTableName = TableModel.CUSTOMER_TABLE_PREFIX + table.getTableName();
					// 重新添加子表外键
					for (BpmFormTable subTable : tableList) {
						String tabName = TableModel.CUSTOMER_TABLE_PREFIX + subTable.getTableName();
						tableOperator.addForeignKey(pkTableName, tabName, TableModel.PK_COLUMN_NAME, TableModel.FK_COLUMN_NAME);
					}
				} else {
					// 更新表定义，添加表列的定义。
					// 删除物理表重新创建表。
					dao.update(table);
					// 删除列的定义
					bpmFormFieldDao.delByTableId(tableId);
					// 添加表列的定义
					addFields(tableId, fields, false);
					// 删除原来的表
					tableOperator.dropTable(TableModel.CUSTOMER_TABLE_PREFIX+ originTable.getTableName());
					// 重新创建物理表
					List<BpmFormField> fieldList = convertFields(fields, false);

					table.setFieldList(fieldList);
					createTable(table);
				}
			}
			// 未发布
			else {
				if(generate==1){
					table.setIsPublished((short)1);
					SysUser sysUser=ContextUtil.getCurrentUser();
					table.setPublishedBy(sysUser.getFullname());
					table.setPublishedBy(sysUser.getFullname());
				}
				// 直接该表的定义。
				dao.update(table);
				// 删除字段，重新加入字段
				bpmFormFieldDao.delByTableId(tableId);
				// 添加字段
				addFields(tableId, fields, false);
				if(table.getIsMain()==0 && generate==1){
					BpmFormTable mainTable= dao.getById(mainTableId);
					if(mainTable.getIsPublished().shortValue()==0){
						List<BpmFormField> fieldList = convertFields(fields, false);
						table.setFieldList(fieldList);
						createTable(table);
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 添加选择器ID隐藏字段
	 * 
	 * @param field
	 * @return
	 */
	private BpmFormField addHiddenBpmFormField(BpmFormField field) {
		BpmFormField fieldHidden = (BpmFormField) field.clone();
		fieldHidden.setFieldId(UniqueIdUtil.genId());

		fieldHidden.setFieldName(fieldHidden.getFieldName() + BpmFormField.FIELD_HIDDEN);
		fieldHidden.setFieldDesc(field.getFieldDesc() + BpmFormField.FIELD_HIDDEN);
		fieldHidden.setIsHidden(BpmFormField.HIDDEN);
		short valueFrom = fieldHidden.getValueFrom().shortValue();
		// 设置人员选择器的ID脚本。
		if (valueFrom == BpmFormField.VALUE_FROM_SCRIPT_HIDDEN || valueFrom == BpmFormField.VALUE_FROM_SCRIPT_SHOW) {
			fieldHidden.setScript(fieldHidden.getScriptID());
		}

		// TODO
		return fieldHidden;
	}

	/**
	 * 添加自定义表字段。
	 * 
	 * @param tableId
	 *            表名。
	 * @param fields
	 *            字段数组
	 * @param isExternal
	 *            是否外部表。
	 * @throws Exception
	 */
	private void addFields(Long tableId, List<BpmFormField> fields, boolean isExternal) throws Exception {
		List<BpmFormField> fieldList = convertFields(fields, isExternal);
		for (int i = 0; i < fieldList.size(); i++) {
			BpmFormField field = fieldList.get(i);
			Long fieldId = UniqueIdUtil.genId();
			field.setFieldId(fieldId);
			field.setSn(i);
			field.setTableId(tableId);
			if(field.getControlType()==null){
				field.setControlType(BpmFormField.HIDDEN);
			}
			bpmFormFieldDao.add(field);
		}
	}

	/**
	 * 转换字段。
	 * 
	 * <pre>
	 * 增加隐藏字段，并设置字段的主键，数据类型，名称，注释信息。
	 * </pre>
	 * 
	 * @param fields
	 * @return
	 * @throws Exception
	 */
	private List<BpmFormField> convertFields(List<BpmFormField> fields, boolean isExternal) throws Exception {
		List<BpmFormField> list = new ArrayList<BpmFormField>();
		int i = 1;
		for (BpmFormField field : fields) {
			field.setSn(i);
			i++;
			if (StringUtil.isEmpty(field.getFieldDesc()))
				field.setFieldDesc(field.getFieldName());

			list.add(field);
			if (isExternal)
				continue;

			if (field.getControlType() == null) {
				field.setControlType(BpmFormField.HIDDEN);
				continue;
			}

			if (isExecutorSelector(field.getControlType()) && field.getIsHidden().shortValue() == BpmFormField.NO_HIDDEN) {
				BpmFormField fieldHidden = this.addHiddenBpmFormField(field);
				list.add(fieldHidden);
			}
		}
		return list;
	}

	/**
	 * 获取选择器关联的字段
	 * 
	 * @param tableId
	 * @return
	 */
	public Map<String, BpmFormField[]> getExecutorSelectorField(Long tableId) {
		Map<String, BpmFormField[]> selectorFieldMap = new HashMap<String, BpmFormField[]>();
		BpmFormTable bpmFormTable = getTableByIdContainHidden(tableId);
		List<BpmFormField> fields = bpmFormTable.getFieldList();
		Map<String, BpmFormField> fieldsMap = new HashMap<String, BpmFormField>();
		for (BpmFormField field : fields) {
			fieldsMap.put(field.getFieldName(), field);
		}
		for (BpmFormField field : fields) {
			if (isExecutorSelector(field.getControlType()) && field.getIsHidden().shortValue() == BpmFormField.NO_HIDDEN) {
				if (fieldsMap.containsKey(field.getFieldName() + BpmFormField.FIELD_HIDDEN)) {
					BpmFormField[] fds = new BpmFormField[2];
					fds[0] = field;
					fds[1] = fieldsMap.get(field.getFieldName() + BpmFormField.FIELD_HIDDEN);
					selectorFieldMap.put(field.getFieldName(), fds);
				}
			}
		}

		return selectorFieldMap;
	}

	/**
	 * 根据表名判断是否该表在系统中已经定义。
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean isTableNameExisted(String tableName) {
		return dao.isTableNameExisted(tableName);
	}

	/**
	 * 判断表是否已存在，在更新时使用。
	 * 
	 * @param tableId
	 * @param tableName
	 * @return
	 */
	public boolean isTableNameExistedForUpd(Long tableId, String tableName) {
		return dao.isTableNameExistedForUpd(tableId, tableName);
	}

	/**
	 * 判断表名是否存在。
	 * 
	 * @param tableName
	 * @param dsAlias
	 * @return
	 */
	public boolean isTableNameExternalExisted(String tableName, String dsAlias) {
		return dao.isTableNameExternalExisted(tableName, dsAlias);
	}

	/**
	 * 通过mainTableId获得所有字表
	 * 
	 * @param mainTableId
	 * @return
	 */
	public List<BpmFormTable> getSubTableByMainTableId(Long mainTableId) {
		return dao.getSubTableByMainTableId(mainTableId);
	}

	/**
	 * 获得所有未分配的子表
	 * 
	 * @param tableName
	 * @return
	 */
	public List<BpmFormTable> getAllUnassignedSubTable() {
		return dao.getAllUnassignedSubTable();
	}

	/**
	 * 根据数据源名称取得子表。
	 * 
	 * @param dsName
	 * @return
	 */
	public List<BpmFormTable> getByDsSubTable(String dsName) {
		return dao.getByDsSubTable(dsName);
	}

	/**
	 * 将表定义进行发布。
	 * 
	 * @param tableId
	 *            表定义ID
	 * @param operator
	 *            发布人
	 * @throws Exception
	 */
	public void generateTable(Long tableId, String operator) throws Exception {
		BpmFormTable mainTable = dao.getById(tableId);
		// 将主表设为已发布
		publish(tableId, operator);

		// 将主表设为默认版本
		List<BpmFormField> mainFields = bpmFormFieldDao.getAllByTableId(tableId);
		// 添加
		mainFields = convertFields(mainFields, false);
		mainTable.setFieldList(mainFields);

		createTable(mainTable);

		List<BpmFormTable> subTables = dao.getSubTableByMainTableId(tableId);
		for (BpmFormTable subTable : subTables) {
			// 将子表设为已发布
			publish(subTable.getTableId(), operator);

			List<BpmFormField> subFields = bpmFormFieldDao.getAllByTableId(subTable.getTableId());
			subFields = convertFields(subFields, false);
			subTable.setFieldList(subFields);
			// 改表结构
			createTable(subTable);
		}
	}

	/**
	 * 创建业务数据历史表
	 * 
	 * @Methodname: generateHistoryTable
	 * @Discription:
	 * @param tableId
	 * @throws Exception
	 * 
	 */
	public void ensureHistoryTableExixts(Long tableId) throws Exception {
		BpmFormTable mainTable = dao.getById(tableId);

		// 将主表设为默认版本
		List<BpmFormField> mainFields = bpmFormFieldDao.getAllByTableId(tableId);
		// 添加
		mainFields = convertFields(mainFields, false);
		mainTable.setFieldList(mainFields);

		// 给历史表的表名加上后缀
		String hisTableName = mainTable.getTableName() + TableModel.CUSTOMER_TABLE_HIS_SUFFIX;
		mainTable.setTableName(hisTableName);

		String fullTableName = TableModel.CUSTOMER_TABLE_PREFIX + hisTableName;

		if (!bpmFormHandlerDao.tableExists(fullTableName)) {
			createTable(mainTable);
		}
	}

	/**
	 * 设置表为发布状态。
	 * 
	 * @param tableId
	 * @param operator
	 */
	private void publish(Long tableId, String operator) {
		BpmFormTable table = new BpmFormTable();
		table.setTableId(tableId);
		table.setPublishedBy(operator);
		table.setIsPublished((short) 1);
		table.setPublishTime(new Date());
		dao.updPublished(table);
	}

	/**
	 * 关联子表。
	 * 
	 * @param mainTableId
	 * @param subTableId
	 * @throws Exception
	 */
	public void linkSubtable(Long mainTableId, Long subTableId, String fkField) throws Exception {
		// 获取主表
		BpmFormTable mainTable = dao.getById(mainTableId);
		BpmFormTable subTable = dao.getById(subTableId);

		// 建立关系
		// StringBuffer relationSb=new StringBuffer("<relation pk='"+ mainTable.getPkField() + "'>");
		// relationSb.append("<table name='").append(mainTable.getTableName()).append("' fk='"+fkField+"' />");
		// relationSb.append("</relation>");

		subTable.setRelation(fkField);

		// 主表已经发布，则生成子表。
		if (mainTable.getIsPublished() == 1) {
			subTable.setMainTableId(mainTableId);
			dao.update(subTable);
			// 如果表已生成则先删除表。
			if (subTable.getIsPublished() == 1) {
				tableOperator.dropTable(TableModel.CUSTOMER_TABLE_PREFIX + subTable.getTableName());
			}
			// 发布子表
			publish(subTableId, mainTable.getPublishedBy());
			
			List<BpmFormField> subFields = bpmFormFieldDao.getByTableId(subTableId);
			subFields = convertFields(subFields, false);
			subTable.setFieldList(subFields);
			// 生成实体表
			createTable(subTable);
		}
		// 未发布修改字段。
		else {
			subTable.setMainTableId(mainTableId);
			dao.update(subTable);
		}
	}

	/**
	 * 取消关联
	 * 
	 * @param subTableId
	 */
	public void unlinkSubTable(Long subTableId) {
		BpmFormTable table = dao.getById(subTableId);
		table.setMainTableId(null);
		dao.update(table);
	}

	/**
	 * 获取可以关联的主表。 1.没有关联表单。 2.可以是发布或未发布的。
	 * 
	 * @return
	 */
	public List<BpmFormTable> getAssignableMainTable() {

		List<BpmFormTable> list = dao.getAssignableMainTable();

		return list;
	}

	/**
	 * 获取未发布的主表。
	 * 
	 * @return
	 */
	public List<BpmFormTable> getAllUnpublishedMainTable() {
		List<BpmFormTable> list = dao.getAllUnpublishedMainTable();
		return list;
	}

	/**
	 * 查找默认主表列表
	 * 
	 * @param queryFilter
	 * @return
	 */
	public List<BpmFormTable> getAllMainTable(QueryFilter queryFilter) {
		return dao.getAllMainTable(queryFilter);
	}

	public List<BpmFormTable> getMainTables(String tableName) {
		return dao.getMainTables(tableName);
	}

	public BpmFormTable getByTableName(String tableName) {
		return dao.getByTableName(tableName);
	}

	/**
	 * 根据表字段获取列定义对象。 columnType: 1.主键 2.一般列 3.外键 4.用户字段 5.流程RUN字段 6.流程定义ID
	 * 7.获取默认时间字段 8.组织字段
	 * 
	 * @param field
	 * @return
	 */
	private ColumnModel getByField(BpmFormField field, int columnType) {
		ColumnModel columnModel = new ColumnModel();
		switch (columnType) {
		// 主键
		case 1:
			columnModel.setName(TableModel.PK_COLUMN_NAME);
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(20);
			columnModel.setComment("主键");
			columnModel.setIsPk(true);
			columnModel.setIsNull(false);
			break;
		// 一般列
		case 2:
			columnModel.setName(TableModel.CUSTOMER_COLUMN_PREFIX + field.getFieldName());
			columnModel.setColumnType(field.getFieldType());
			columnModel.setCharLen(field.getCharLen().intValue());
			columnModel.setIntLen(field.getIntLen().intValue());
			columnModel.setDecimalLen(field.getDecimalLen().intValue());
			columnModel.setIsNull(field.getIsRequired() == 0);
			columnModel.setComment(field.getFieldDesc());
			break;
		// 外键
		case 3:
			columnModel.setName(TableModel.FK_COLUMN_NAME);
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(20);
			columnModel.setComment("外键");
			columnModel.setIsFk(true);
			columnModel.setIsNull(false);
			break;
		// 用户字段
		case 4:
			columnModel.setName(TableModel.CUSTOMER_COLUMN_CURRENTUSERID);
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(20);
			columnModel.setComment("用户字段");
			columnModel.setIsFk(false);
			columnModel.setIsNull(true);
			break;
		// 流程RUN字段。
		case 5:
			columnModel.setName(TableModel.CUSTOMER_COLUMN_FLOWRUNID);
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(20);
			columnModel.setComment("流程RUN字段");
			columnModel.setIsFk(false);
			columnModel.setIsNull(true);
			break;
		// 流程定义ID。
		case 6:
			columnModel.setName(TableModel.CUSTOMER_COLUMN_DEFID);
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(20);
			columnModel.setComment("流程定义ID");
			columnModel.setIsFk(false);
			columnModel.setIsNull(true);
			break;
		// 获取默认时间字段
		case 7:
			String defaultValue = getDefaultCurrentTime();
			columnModel.setName(TableModel.CUSTOMER_COLUMN_CREATETIME);
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
			// columnModel.setIntLen(20);
			columnModel.setComment("记录插入时间");
			columnModel.setIsFk(false);
			columnModel.setIsNull(true);
			columnModel.setDefaultValue(defaultValue);
			break;
		// 组织字段
		case 8:
			columnModel.setName(TableModel.CUSTOMER_COLUMN_CURRENTORGID);
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(20);
			columnModel.setComment("组织字段");
			columnModel.setIsFk(false);
			columnModel.setIsNull(true);
			break;

		}

		return columnModel;
	}

	/**
	 * 根据表和表字段创建实体表。
	 * 
	 * @param table
	 *            表对象
	 * @param fields
	 *            表字段
	 * @throws SQLException
	 */
	private void createTable(BpmFormTable table) throws SQLException {
		TableModel tableModel = getTableModelByBpmFormTable(table);
		//创建表的国际化资源
		
		tableOperator.createTable(tableModel);
		// 当前表为子表，添加表关联。
		if (table.getIsMain() == 0) {
			// 添加外键
			//tableOperator.addForeignKey(TableModel.CUSTOMER_TABLE_PREFIX + bpmFormTable.getTableName(), TableModel.CUSTOMER_TABLE_PREFIX + table.getTableName(), TableModel.PK_COLUMN_NAME, TableModel.FK_COLUMN_NAME);
			// 创建索引。
			tableOperator.createIndex(TableModel.CUSTOMER_TABLE_PREFIX + table.getTableName(), TableModel.FK_COLUMN_NAME);
		}
	}

	/**
	 * 根据BpmFormTable获取TableModel对象。
	 * 
	 * @param table
	 */
	private TableModel getTableModelByBpmFormTable(BpmFormTable table) {
		TableModel tableModel = new TableModel();
		tableModel.setName(TableModel.CUSTOMER_TABLE_PREFIX + table.getTableName());
		tableModel.setComment(table.getTableDesc());

		// 给表添加主键
		ColumnModel pkModel = getByField(null, 1);
		tableModel.addColumnModel(pkModel);

//		// 用户字段。
//		ColumnModel userColumnModel = getByField(null, 4);
//		tableModel.addColumnModel(userColumnModel);
//		// 组织字段。
//		ColumnModel orgColumnModel = getByField(null, 8);
//		tableModel.addColumnModel(orgColumnModel);
		// 主表的情况。
		if (table.getIsMain() == 1) {
			// 添加一个运行列
//			ColumnModel runColumnModel = getByField(null, 5);
//			tableModel.addColumnModel(runColumnModel);
//			// 流程定义ID
//			ColumnModel defColumnModel = getByField(null, 6);
//			tableModel.addColumnModel(defColumnModel);
//			// 默认时间。
//			ColumnModel createTimeColumnModel = getByField(null, 7);
//			tableModel.addColumnModel(createTimeColumnModel);
		}

		// 添加自定义表的字段。
		for (BpmFormField field : table.getFieldList()) {
			field.setFieldName(StringUtil.trim(field.getFieldName(), " "));
			ColumnModel columnModel = getByField(field, 2);
			tableModel.addColumnModel(columnModel);
		}
		// 如果表定义为子表的情况。
		// 添加一个外键的列定义。
		if (table.getIsMain() == 0) {
			ColumnModel fkModel = getByField(null, 3);
			tableModel.addColumnModel(fkModel);
		}
		return tableModel;

	}

	/**
	 * 根据数据库获取时间默认值函数用于创建数据库表。
	 * 
	 * @return
	 */
	private String getDefaultCurrentTime() {
		String dbType = tableOperator.getDbType();
		if (SqlTypeConst.ORACLE.equals(dbType)) {
			return "SYSDATE";
		} else if (SqlTypeConst.MYSQL.equals(dbType)) {
			return "CURRENT_TIMESTAMP";
		} else if (SqlTypeConst.SQLSERVER.equals(dbType)) {
			return "getdate()";
		} else if (SqlTypeConst.DB2.equals(dbType)) {
			return "CURRENT DATE";
		}
		return "";
	}

	

	/**
	 * 根据数据源和表名获取表对象。
	 * 
	 * @param dsName
	 * @param tableName
	 * @return
	 */
	public BpmFormTable getByDsTablename(String dsName, String tableName) {
		return this.dao.getByDsTablename(dsName, tableName);
	}

	/**
	 * 根据表的Id删除外部表定义。
	 * 
	 * @param tableId
	 */
	public void delExtTableById(Long tableId) {
		BpmFormTable bpmFormTable = this.getById(tableId);
		int isExternal = bpmFormTable.getIsExternal();
		// 自定义表直接返回
		if (isExternal == 0)
			return;
		// 子表的处理情况
		this.dao.delById(tableId);
	}

	/**
	 * 根据表ID删除表定义。
	 * 
	 * <pre>
	 * 1.如果该表是主表，那么先删除其所有的关联的子表。
	 * 2.根据表的ID删除表定义。
	 * </pre>
	 * 
	 * @param tableId
	 */
	public void delByTableId(Long tableId) {
		BpmFormTable bpmFormTable = this.getById(tableId);
	if (BeanUtils.isEmpty(bpmFormTable))
			return;
		String tableName = bpmFormTable.getTableName();
		//删除和表相关的国际化资源记录
		
		if (bpmFormTable.getIsMain() == 1) {
			List<BpmFormTable> subTableList = getSubTableByMainTableId(tableId);
			if (BeanUtils.isNotEmpty(subTableList)) {
				for (BpmFormTable subTable : subTableList) {
					// 删除实体表
					tableOperator.dropTable(TableModel.CUSTOMER_TABLE_PREFIX + subTable.getTableName());
					dao.delById(subTable.getTableId());
				}
			}
		}
		// 删除实体表。
		tableOperator.dropTable(TableModel.CUSTOMER_TABLE_PREFIX + tableName);
		dao.delById(tableId);

		// 删除字段信息
		bpmFormFieldDao.delByTableId(tableId);
	}

	/**
	 * 根据表id获取表的表和表字段的信息。
	 * 
	 * <pre>
	 * 	如果输入的是主表id。
	 *  那么将返回主表的信息和字段信息，同时返回子表和字段的信息。
	 *  字段数据不包含删除的字段和隐藏的字段。
	 *  need 是否包含隐藏或者删除字段   0为正常字段   1为正常字段加上隐藏字段    2 为正常字段加上逻辑删除字段 
	 * </pre>
	 * 
	 * @param tableId
	 * @return
	 */
	public BpmFormTable getTableById(Long tableId,int need) {
		BpmFormTable bpmFormTable = dao.getById(tableId);
		if (bpmFormTable == null)
			return null;
		this.getTabInfo(bpmFormTable,need);
		
		return bpmFormTable;
	}
	

	
	/**
	 * 根据主表获取整个表的信息包括列。
	 * @param bpmFormTable 自定义表
	 * @param need 是否包含隐藏或者删除字段   0为正常字段   1为正常字段加上隐藏字段    2 为正常字段加上逻辑删除字段 
	 */
	private void getTabInfo(BpmFormTable bpmFormTable,int need){
		Long tableId=bpmFormTable.getTableId();
		List<BpmFormField> fieldList=null;
		if(need==1){
			fieldList = bpmFormFieldDao.getByTableIdContainHidden(tableId);
		}else if((need==2)){
			fieldList = bpmFormFieldDao.getAllByTableId(tableId);
		}else{
			fieldList = bpmFormFieldDao.getByTableId(tableId);
		}

		bpmFormTable.setFieldList(fieldList);
		if (bpmFormTable.getIsMain() == 0)
			return;

		List<BpmFormTable> subTableList = dao.getSubTableByMainTableId(tableId);
		if (BeanUtils.isEmpty(subTableList))
			return;

		for (BpmFormTable table : subTableList) {
			List<BpmFormField> subFieldList=null;
			
			if(need==1){
				subFieldList = bpmFormFieldDao.getByTableIdContainHidden(table.getTableId());
			}else if((need==2)){
				subFieldList = bpmFormFieldDao.getAllByTableId(table.getTableId());
			}else{
				subFieldList = bpmFormFieldDao.getByTableId(table.getTableId());
			}
			
			//List<BpmFormField> subFieldList = bpmFormFieldDao.getByTableId(table.getTableId());
			table.setFieldList(subFieldList);
		}
		bpmFormTable.setSubTableList(subTableList);
	}
	
	/**
	 * 根据流程定义获取流程表的所有数据。
	 * <pre>
	 * 1.包括表的元数据。
	 * 2.表的列元数据列表。
	 * 3.子表数据。
	 * 4.子表列的元数据。
	 * </pre>
	 * @param defId
	 * @return
	 * @throws Exception 
	 */
	public BpmFormTable getByDefId(Long defId) throws Exception {
		BpmFormTable bpmFormTable = dao.getByDefId(defId);
		if (bpmFormTable == null)
			return null;
		getTabInfo(bpmFormTable, 1);
		return bpmFormTable;
	}
	
	/**
	 * 根据流程定义获取流程表的所有数据。
	 * 
	 * <pre>
	 * 1.包括表的元数据。
	 * 2.表的列元数据列表。
	 * 3.子表数据。
	 * 4.子表列的元数据。
	 * </pre>
	 * 
	 * @param defId
	 * @param parentActDefId
	 * @return
	 * @throws Exception
	 */
	public BpmFormTable getByDefId(Long defId, String parentActDefId) throws Exception {
		BpmFormTable bpmFormTable = dao.getByDefId(defId, parentActDefId);
		if (bpmFormTable == null)
			return null;
		getTabInfo(bpmFormTable, 1);
		return bpmFormTable;
	}
	
	/**
	 * 
	 * @param tableId
	 * @return
	 */
	public BpmFormTable getTableById(Long tableId) {
		return getByTableId(tableId,0);
	}

	/**
	 * 根据表id获取表的表和子表信息。
	 * @param tableId 表ID
	 * @param needHidden 是否包含隐藏的字段
	 * @return
	 */
	public BpmFormTable getByTableId(Long tableId,int need) {
		BpmFormTable bpmFormTable = dao.getById(tableId);
		if (bpmFormTable == null)
			return null;
		this.getTabInfo(bpmFormTable, need);
		return bpmFormTable;
	}

	/**
	 * 根据表的ID取得表的列名是否可以编辑。
	 * 
	 * @param tableId
	 * @return
	 */
	public boolean getCanEditColumnNameTypeByTableId(Long tableId) {
		if (tableId == 0)
			return true;
		BpmFormTable bpmFormTable = dao.getById(tableId);
		if (bpmFormTable == null)
			return true;
		boolean hasData = bpmFormHandlerDao.getHasData(TableModel.CUSTOMER_TABLE_PREFIX + bpmFormTable.getTableName());
		if (hasData) {
			return false;
		}
		int formAmount = bpmFormDefDao.getFormDefAmount(tableId);
		if (formAmount > 1) {
			return false;
		}
		return true;
	}

	/**
	 * 保存数据库表。
	 * 
	 * <pre>
	 *  bpmFormTable 需要指定表的主键id，如果没有指定，那么当作新表处理。
	 * 	1.BpmFormTable 表的表id为0。
	 * 		1.isPublish为true，生成表。
	 * 		2.isPublish为false，不生成表。
	 *  2.已经保存了表单数据。
	 *  	1.已经发布。
	 *  		1.是否生成数据。
	 *  			更新表。
	 *  		2.表单数量大于1个的情况。
	 *  			更新表。
	 *  		  否则:
	 *  			删除表定义。
	 * 				删除表。
	 * 				添加表定义。
	 * 				重新生成表。
	 *  	2.未发布。
	 *  		1.删除表定义。
	 *  		2.添加表。
	 *  		3.如果为发布。
	 *  			生成物理表。
	 * </pre>
	 * 
	 * @param bpmFormTable
	 * @throws Exception
	 */
	public Long saveTable(BpmFormTable bpmFormTable) throws Exception {
		Long mainTableId = bpmFormTable.getTableId();
		// 新表的情况。
		if (mainTableId == 0) {
			// 添加表信息
			Long tableId = addTable(bpmFormTable);
			// 生成表。
			genTable(bpmFormTable);
			return tableId;
		}
		// 定义已经添加。
		else {
			// 获取原表定义。
			BpmFormTable orginTable = getTableById(mainTableId,2);          //包含有删除字段
			// 判断表中是否已有数据。
			boolean hasData = bpmFormHandlerDao.getHasData(TableModel.CUSTOMER_TABLE_PREFIX + orginTable.getTableName());
			// 如果这个表定义了多个表单的情况，那么不能对表做重新创建的的操作
			// 有数据
			if (hasData) {
				// 更新表
				updTable(bpmFormTable, orginTable);
			}
			// 无数据的情况
			else {
				// 取得表单数量
				int formAmount = bpmFormDefDao.getFormDefAmount(mainTableId);
				if (formAmount > 1) {
					// 更新表。
					updTable(bpmFormTable, orginTable);
				}
				// 1.删除表定义信息
				// 2.删除表
				// 3.添加表
				// 4.添加实体表
				else {
					delTable(orginTable);
					dropTable(orginTable);
					Long tableId = addTable(bpmFormTable);
					updateDataTemplate(tableId, orginTable.getTableId());
					genTable(bpmFormTable);
					return tableId;
				}
			}
		}
		return mainTableId;
	}
	
	/**
	 * 删除旧表、添加新表时，同时更新业务数据模板
	 * @param newTableId
	 * @param oldTableId
	 * @author helh
	 */
	private void updateDataTemplate(Long newTableId, Long oldTableId) {
		List<BpmDataTemplate> bpmDataTemplateList = bpmDataTemplateDao.getByTableId(oldTableId);
		if(BeanUtils.isNotEmpty(bpmDataTemplateList)){
			for(BpmDataTemplate bpmDataTemplate : bpmDataTemplateList){
				bpmDataTemplate.setTableId(newTableId);
				bpmDataTemplateDao.update(bpmDataTemplate);
			}
		}
	}

	/**
	 * 添加表。
	 * 
	 * <pre>
	 * 	根据表对象，添加主表和子表。
	 * </pre>
	 * 
	 * @param bpmFormTable
	 * @return
	 * @throws Exception
	 */
	private Long addTable(BpmFormTable bpmFormTable) throws Exception {
		// 保存主表数据
		Long mainTableId = UniqueIdUtil.genId();
		bpmFormTable.setTableId(mainTableId);
		bpmFormTable.setGenByForm(1);
		bpmFormTable.setMainTableId(0L);
		bpmFormTable.setIsMain((short) 1);
		// 设置发布字段
		bpmFormTable.setIsPublished((short) 1);
		bpmFormTable.setGenByForm(1);

		// 添加主表
		dao.add(bpmFormTable);

		// 添加子表列数据。
		List<BpmFormField> fields = bpmFormTable.getFieldList();
		List<BpmFormField> mainFields = convertFields(fields, false);
		// 重新设置字段。
		bpmFormTable.setFieldList(mainFields);

		for (int i = 0; i < mainFields.size(); i++) {
			BpmFormField field = mainFields.get(i);
			if (field.getFieldId() == 0) {
				field.setFieldId(UniqueIdUtil.genId());
			}
			field.setSn(i + 1);
			field.setTableId(mainTableId);
			bpmFormFieldDao.add(field);
		}
		// 子表列表。
		List<BpmFormTable> subTableList = bpmFormTable.getSubTableList();

		if (BeanUtils.isEmpty(subTableList)) {
			return mainTableId;
		}
		// 添加子表
		for (int i = 0; i < subTableList.size(); i++) {
			BpmFormTable subTable = subTableList.get(i);
			// 生成子表主键。
			Long subTableId = UniqueIdUtil.genId();
			subTable.setTableId(subTableId);
			subTable.setMainTableId(mainTableId);
			subTable.setGenByForm(1);
			subTable.setIsMain((short) 0);
			subTable.setIsPublished((short) 1);

			dao.add(subTable);

			List<BpmFormField> subfields = subTable.getFieldList();
			// 字段转换。
			List<BpmFormField> subfieldList = convertFields(subfields, false);

			subTable.setFieldList(subfieldList);

			for (int k = 0; k < subfieldList.size(); k++) {
				BpmFormField field = subfieldList.get(k);

				if (field.getFieldId() == 0) {
					field.setFieldId(UniqueIdUtil.genId());
				}

				field.setSn(k + 1);
				field.setTableId(subTableId);
				bpmFormFieldDao.add(field);
			}
		}

		return mainTableId;
	}

	/**
	 * 删除表定义。
	 * 
	 * <pre>
	 * 	删除表定义，如果有子表定义，将子表定义一并删除。
	 * </pre>
	 * 
	 * @param bpmFormTable
	 */
	public void delTable(BpmFormTable bpmFormTable) {
		Long tableId = bpmFormTable.getTableId();
		// 删除子表
		List<BpmFormTable> subTableList = bpmFormTable.getSubTableList();
		if (BeanUtils.isNotEmpty(subTableList)) {
			for (BpmFormTable subTable : subTableList) {
				Long subTableId = subTable.getTableId();
				bpmFormFieldDao.delByTableId(subTableId);
				dao.delById(subTableId);
			}
		}
		bpmFormFieldDao.delByTableId(tableId);
		dao.delById(tableId);
	}

	/**
	 * 根据BpmFormTable一次性生成表。
	 * 
	 * <pre>
	 * 同时创建主表和子表。
	 * 并创建外键。
	 * </pre>
	 * 
	 * @param table
	 * @throws SQLException
	 */
	private void genTable(BpmFormTable table) throws SQLException {
		// 创建主表
		TableModel mainTableModel = getTableModelByBpmFormTable(table);
		tableOperator.createTable(mainTableModel);
		for (BpmFormTable subTable : table.getSubTableList()) {
			TableModel subTableModel = getTableModelByBpmFormTable(subTable);
			tableOperator.createTable(subTableModel);
			tableOperator.addForeignKey(TableModel.CUSTOMER_TABLE_PREFIX + table.getTableName(), TableModel.CUSTOMER_TABLE_PREFIX + subTable.getTableName(), TableModel.PK_COLUMN_NAME, TableModel.FK_COLUMN_NAME);
		}
	}

	/**
	 * 一次性删除表。
	 * 
	 * @param table
	 * @throws SQLException
	 */
	public void dropTable(BpmFormTable table) throws SQLException {
		for (BpmFormTable subTable : table.getSubTableList()) {
			String subTableName = TableModel.CUSTOMER_TABLE_PREFIX+ subTable.getTableName();
			tableOperator.dropTable(subTableName);
		}
		tableOperator.dropTable(TableModel.CUSTOMER_TABLE_PREFIX + table.getTableName());
	}

	/**
	 * 更新表。 bpmFormTable:不需要指定主键信息。
	 * 
	 * <pre>
	 * 	1.更新表数据。
	 * 	2.更新字段数据。
	 * 		1.如果字段为添加。
	 * 			1.添加列定义。
	 * 			2.添加字段。
	 * 		2.更新字段。
	 * 			1.如果删除字段，字段不做真正的删除，只是做删除标记。
	 * 			2.更新字段信息，不更新实体表信息。
	 * 	3.更新子表数据信息。
	 * 		1.添加子表。
	 * 			添加子表定义。
	 * 			添加子表字段定义。
	 * 			添加是体表
	 * 		2.更新表。
	 * 			1.更新列。
	 * 			2.添加列定义，添加列实体列。
	 * 
	 *   注意事项：
	 *   bpmFormTable必须指定tableId。
	 *   子表对象也需要指定。
	 * </pre>
	 * 
	 * @param bpmFormTable
	 *            新表
	 * @param orginTable
	 *            原表。
	 * @throws Exception
	 */
	private void updTable(BpmFormTable bpmFormTable, BpmFormTable orginTable) throws Exception {
		Long mainTableId = orginTable.getTableId();
		String tableName = bpmFormTable.getTableName();
		// 设置主键相同
		bpmFormTable.setTableId(mainTableId);
		bpmFormTable.setGenByForm(1);
		bpmFormTable.setIsPublished((short) 1);
		bpmFormTable.setPublishedBy(orginTable.getPublishedBy());
		bpmFormTable.setPublishTime(orginTable.getPublishTime());

		dao.update(bpmFormTable);

		List<BpmFormField> fields = bpmFormTable.getFieldList();
		// 获取原表字段。
		List<BpmFormField> orginFields = orginTable.getFieldList();
		// 取得表单数量
		int formAmount = bpmFormDefDao.getFormDefAmount(mainTableId);
		Map<String, List<BpmFormField>> mainFieldMap = new HashMap<String, List<BpmFormField>>();
		if (formAmount > 1) {
			mainFieldMap = caculateFields(fields, orginFields, false);
		} else {
			mainFieldMap = caculateFields(fields, orginFields, true);
		}
		List<BpmFormField> updList = mainFieldMap.get("upd");
		List<BpmFormField> addList = mainFieldMap.get("add");
		//产生选择器的隐藏字段    helh
		updList = convertFields(updList, false);
		// 更新列
		for (int i = 0; i < updList.size(); i++) {
			BpmFormField bpmFormField = updList.get(i);
			// 更新选择器的隐藏字段 helh
			if (isExecutorSelector(bpmFormField.getControlType()) && bpmFormField.getIsHidden().shortValue() == BpmFormField.HIDDEN) {
				BpmFormField field = bpmFormFieldDao.getFieldByTidFna(bpmFormField.getTableId(), bpmFormField.getFieldName());
				if (BeanUtils.isNotEmpty(field)) {
					bpmFormField.setFieldId(field.getFieldId());
				}
			}
			
			bpmFormFieldDao.update(bpmFormField);
		}
		if (BeanUtils.isNotEmpty(addList)) {
			addList = convertFields(addList, false);
			// 添加列
			for (int i = 0; i < addList.size(); i++) {
				BpmFormField bpmFormField = addList.get(i);
				// 设置字段id。
				if (bpmFormField.getFieldId() == 0) {
					bpmFormField.setFieldId(UniqueIdUtil.genId());
				}

				bpmFormField.setTableId(mainTableId);
				bpmFormFieldDao.add(bpmFormField);
				ColumnModel columnModel = getByField(bpmFormField, 2);
				tableOperator.addColumn(TableModel.CUSTOMER_TABLE_PREFIX + tableName, columnModel);
			}
		}

		// 子表处理
		// 获取原子表
		List<BpmFormTable> originTableList = orginTable.getSubTableList();
		Map<String, BpmFormTable> originTableMap = new HashMap<String, BpmFormTable>();
		for (BpmFormTable orginSubTable : originTableList) {
			originTableMap.put(orginSubTable.getTableName().toLowerCase(), orginSubTable);
		}
		// 子表的处理
		List<BpmFormTable> subTableList = bpmFormTable.getSubTableList();
		if (BeanUtils.isEmpty(subTableList))
			return;

		for (int i = 0; i < subTableList.size(); i++) {
			BpmFormTable subTable = subTableList.get(i);
			String subTableName = subTable.getTableName().toLowerCase();
			if (originTableMap.containsKey(subTableName)) {
				BpmFormTable orginSubTable = originTableMap.get(subTableName);
				List<BpmFormField> orginSubFields = orginSubTable.getFieldList();

				Long orginTableId = orginSubTable.getTableId();
				// 更新表的信息。
				subTable.setTableId(orginTableId);
				subTable.setMainTableId(mainTableId);
				subTable.setIsPublished((short) 1);
				subTable.setPublishedBy(orginSubTable.getPublishedBy());
				subTable.setPublishTime(orginSubTable.getPublishTime());

				dao.update(subTable);
				List<BpmFormField> subFields = subTable.getFieldList();
				Map<String, List<BpmFormField>> subFieldMap = new HashMap<String, List<BpmFormField>>();
				if(formAmount>1){
					subFieldMap = caculateFields(subFields,orginSubFields,false);
				}else{
					subFieldMap = caculateFields(subFields,orginSubFields,true);
				}
				List<BpmFormField> updSubList = subFieldMap.get("upd");
				List<BpmFormField> addSubList = subFieldMap.get("add");
				//产生选择器的隐藏字段    helh
				updSubList = convertFields(updSubList, false);
				// 更新列
				for (int k = 0; k < updSubList.size(); k++) {
					BpmFormField bpmFormField = updSubList.get(k);
					// 更新选择器的隐藏字段 helh
					if (isExecutorSelector(bpmFormField.getControlType()) && bpmFormField.getIsHidden().shortValue() == BpmFormField.HIDDEN) {
						BpmFormField field = bpmFormFieldDao.getFieldByTidFna(bpmFormField.getTableId(), bpmFormField.getFieldName());
						if (BeanUtils.isNotEmpty(field)) {
							bpmFormField.setFieldId(field.getFieldId());
						}
					}
					
					bpmFormFieldDao.update(bpmFormField);
				}
				// 添加列不为空
				if (BeanUtils.isNotEmpty(addSubList)) {
					addSubList = convertFields(addSubList, false);
					// 添加列
					for (int k = 0; k < addSubList.size(); k++) {

						BpmFormField bpmFormField = addSubList.get(k);
						if (bpmFormField.getFieldId() == 0) {
							bpmFormField.setFieldId(UniqueIdUtil.genId());
						}
						bpmFormField.setTableId(orginTableId);
						// 设置表
						bpmFormFieldDao.add(bpmFormField);
						// 添加字段
						ColumnModel columnModel = getByField(bpmFormField, 2);
						tableOperator.addColumn(TableModel.CUSTOMER_TABLE_PREFIX + subTableName, columnModel);
					}
				}
			}
			// 新加的子表。
			else {
				Long newTableId = UniqueIdUtil.genId();
				subTable.setTableId(newTableId);
				subTable.setIsMain((short) 0);
				subTable.setMainTableId(mainTableId);
				subTable.setIsPublished((short) 1);
				dao.add(subTable);

				List<BpmFormField> subFields = subTable.getFieldList();
				subFields = convertFields(subFields, false);
				for (BpmFormField field : subFields) {
					Long newFieldId = UniqueIdUtil.genId();
					field.setFieldId(newFieldId);
					field.setTableId(newTableId);
					bpmFormFieldDao.add(field);
				}
				TableModel subTableModel = getTableModelByBpmFormTable(subTable);
				tableOperator.createTable(subTableModel);
				tableOperator.addForeignKey(TableModel.CUSTOMER_TABLE_PREFIX + tableName, TableModel.CUSTOMER_TABLE_PREFIX + subTable.getTableName(), TableModel.PK_COLUMN_NAME, TableModel.FK_COLUMN_NAME);
			}
		}
	}

	/**
	 * 生成所有发布表
	 * 
	 * @param queryFilter
	 * @throws Exception
	 */
	public void genAllTable() throws Exception {
		List<BpmFormTable> list = dao.getAllTable();
		for (BpmFormTable bpmFormTable : list) {
			if (bpmFormTable.getIsMain().shortValue() == 1 && bpmFormTable.getIsPublished().shortValue() == 1) {
				if (bpmFormTable.getTableName().length() < 20) {
					// genTable(bpmFormTable.getTableId());
				} else {
					System.err.println("表：" + bpmFormTable.getTableName() + " 长度" + bpmFormTable.getTableName().length());
					// bpmFormTable.setIsPublished((short) 0);
					// dao.update(bpmFormTable);
				}
			}
		}
	}

	/**
	 * 生成表
	 * 
	 * @param tableId
	 * @throws Exception
	 */
	public void genTable(Long tableId) throws Exception {
		BpmFormTable mainTable = dao.getById(tableId);

		// 将主表设为默认版本
		List<BpmFormField> mainFields = bpmFormFieldDao.getAllByTableId(tableId);
		// 添加
		mainFields = convertFields(mainFields, false);
		mainTable.setFieldList(mainFields);
		List<BpmFormTable> subTables = dao.getSubTableByMainTableId(tableId);
		for (BpmFormTable subTable : subTables) {
			if (subTable.getTableName().length() >= 19) {
				return;
			}
		}

		createTable(mainTable);

		for (BpmFormTable subTable : subTables) {

			List<BpmFormField> subFields = bpmFormFieldDao.getAllByTableId(subTable.getTableId());
			subFields = convertFields(subFields, false);
			subTable.setFieldList(subFields);
			// 改表结构
			createTable(subTable);
		}
	}

	/**
	 * 保存复制的表和字段
	 * 
	 * @param json
	 * @throws Exception
	 */
	public void saveCopy(String json) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(json);
		Map<Long, Long> mainTable = new HashMap<Long, Long>();

		for (Object object : jsonArray) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			String tableId = (String) jsonObject.get("tableId");
			if (BeanUtils.isEmpty(tableId))
				continue;
			String tableName = (String) jsonObject.get("tableName");
			String tableDesc = (String) jsonObject.get("tableDesc");

			BpmFormTable bpmFormTable = dao.getById(new Long(tableId));
			List<BpmFormField> fieldList = bpmFormFieldDao.getByTableIdContainHidden(bpmFormTable.getTableId());

			Long newTableId = UniqueIdUtil.genId();
			mainTable.put(new Long(tableId), newTableId);

			bpmFormTable.setTableId(newTableId);
			Long mainTableId = bpmFormTable.getMainTableId();
			if (BeanUtils.isNotEmpty(mainTableId) && mainTableId.longValue() > 0L)
				bpmFormTable.setMainTableId(mainTable.get(mainTableId));

			bpmFormTable.setTableName(tableName);
			bpmFormTable.setTableDesc(tableDesc);
			bpmFormTable.setPublishTime(new Date());
			bpmFormTable.setIsPublished(new Short("0"));
			bpmFormTable.setIsExternal(0);
			bpmFormTable.setKeyType(new Short("0"));
			dao.add(bpmFormTable);
			// 保存字段
			this.saveBpmFormField(fieldList, newTableId);
		}
	}

	/**
	 * 保存字段
	 * 
	 * @param fieldList
	 * @param tableId
	 */
	private void saveBpmFormField(List<BpmFormField> fieldList, Long tableId) {
		for (BpmFormField bpmFormField : fieldList) {
			bpmFormField.setFieldId(UniqueIdUtil.genId());
			bpmFormField.setTableId(tableId);
			bpmFormFieldDao.add(bpmFormField);
		}
	}

	/**
	 * 判断控件类型是否选择器
	 * 
	 * @param controlType
	 * @return
	 */
	public static boolean isExecutorSelector(Short controlType) {
		if (BeanUtils.isEmpty(controlType))
			return false;
		if (controlType.shortValue() == FieldPool.SELECTOR_USER_SINGLE || controlType.shortValue() == FieldPool.SELECTOR_USER_MULTI || controlType.shortValue() == FieldPool.SELECTOR_ORG_SINGLE || controlType.shortValue() == FieldPool.SELECTOR_ORG_MULTI || controlType.shortValue() == FieldPool.SELECTOR_POSITION_SINGLE || controlType.shortValue() == FieldPool.SELECTOR_POSITION_MULTI || controlType.shortValue() == FieldPool.SELECTOR_ROLE_SINGLE || controlType.shortValue() == FieldPool.SELECTOR_ROLE_MULTI || controlType.shortValue() == FieldPool.SELECTOR_PROCESS_INSTANCE)
			return true;
		return false;
	}

	// ======================================导入导出分割线======================================================

	/**
	 * TODO 导入自定义表XML
	 * 
	 * @param fileStr
	 * @throws Exception
	 */
	public void importXml(InputStream inputStream) throws Exception {
		Document doc = Dom4jUtil.loadXml(inputStream);
		Element root = doc.getRootElement();
		// 验证格式是否正确
		XmlUtil.checkXmlFormat(root, "bpm", "tables");

		String xmlStr = root.asXML();
		BpmFormTableXmlList bpmFormTableXmlList = (BpmFormTableXmlList) XmlBeanUtil.unmarshall(xmlStr, BpmFormTableXmlList.class);

		List<BpmFormTableXml> list = bpmFormTableXmlList.getBpmFormTableXmlList();
		Set<Identity> identitySet = new HashSet<Identity>();
		for (BpmFormTableXml bpmFormTableXml : list) {
			// 导入表，并解析相关信息
			this.importBpmFormTableXml(bpmFormTableXml);
			this.setIdentity(bpmFormTableXml.getIdentityList(), identitySet);
		}
		// 流水号的导入
		this.importIdentity(identitySet);

	}

	/**
	 * 设置导入的流水号
	 * 
	 * @param identityList
	 * @param identitySet
	 */
	public void setIdentity(List<Identity> identityList, Set<Identity> identitySet) {
		if (BeanUtils.isNotEmpty(identityList)) {
			for (Identity identity : identityList) {
				identitySet.add(identity);
			}
		}
	}

	/**
	 * 导入的流水号
	 * 
	 * @param identitySet
	 * @throws Exception
	 */
	public void importIdentity(Set<Identity> identitySet) throws Exception {
		if (BeanUtils.isNotEmpty(identitySet)) {
			for (Identity identity : identitySet) {
				this.importIdentity(identity);
			}
		}
	}

	/**
	 * 转换导入数据
	 * 
	 * @param bpmFormTableXml
	 * @param isMain
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public BpmFormTable convertBpmFormTableXml(BpmFormTableXml bpmFormTableXml, Short isMain, Map<Long, Long> map) throws Exception {
		BpmFormTable mainTable = new BpmFormTable();

		mainTable = bpmFormTableXml.getBpmFormTable();
		if (BeanUtils.isNotEmpty(map)) {
			Long tableId = map.get(mainTable.getTableId());
			if (BeanUtils.isNotEmpty(tableId))
				mainTable.setTableId(tableId);
		}

		if (BeanUtils.isNotEmpty(bpmFormTableXml.getBpmFormFieldList())) {
			List<BpmFormField> mainFields = bpmFormTableXml.getBpmFormFieldList();
			List<BpmFormField> fieldList = new ArrayList<BpmFormField>();
			for (BpmFormField bpmFormField : mainFields) {
				Long tableId = map.get(bpmFormField.getTableId());
				if (BeanUtils.isNotEmpty(tableId))
					bpmFormField.setTableId(tableId);
				if (bpmFormField.getIsDeleted() != null && bpmFormField.getIsDeleted().intValue() != 1)
					fieldList.add(bpmFormField);
			}
			mainTable.setFieldList(fieldList);
		}

		if (isMain.shortValue() == BpmFormTable.NOT_MAIN.shortValue())
			return mainTable;
		List<BpmFormTableXml> subTableXmlList = bpmFormTableXml.getBpmFormTableXmlList();
		if (BeanUtils.isEmpty(subTableXmlList))
			return mainTable;
		List<BpmFormTable> subTableList = new ArrayList<BpmFormTable>();
		for (BpmFormTableXml subTableXml : subTableXmlList) {
			BpmFormTable subTable = convertBpmFormTableXml(subTableXml, BpmFormTable.NOT_MAIN, map);
			subTableList.add(subTable);
		}
		mainTable.setSubTableList(subTableList);

		return mainTable;

	}

	/**
	 * 导入时候生成自定义表
	 * 
	 * @param bpmFormTableXml
	 * @throws Exception
	 */
	public Map<Long, Long> importBpmFormTableXml(BpmFormTableXml bpmFormTableXml) throws Exception {
		Short isMain = BpmFormTable.IS_MAIN;
		Map<Long, Long> map = new HashMap<Long, Long>();

		BpmFormTable bpmFormTable = this.convertBpmFormTableXml(bpmFormTableXml, isMain, map);
		BpmFormTable bft = dao.getByTableName(bpmFormTable.getTableName());
		BpmFormTable orginTable = new BpmFormTable();
		if (BeanUtils.isNotEmpty(bft))
			orginTable = this.getTableByIdContainHidden(bft.getTableId());
		// 导入的表进行
		this.importBpmFormTable(bpmFormTable, isMain, map);

		BpmFormTable destFormTable = this.convertBpmFormTableXml(bpmFormTableXml, isMain, map);

		if (destFormTable.getIsPublished().shortValue() == BpmFormTable.NOT_PUBLISH.shortValue()) {
			// parseMap(destFormTable,bpmFormTable,map);
			return map;
		}
		// 对物理表处理
		this.importGenPhysicsTable(destFormTable, orginTable);

		// parseMap(destFormTable,bpmFormTable,map);
		return map;
	}

	/**
	 * 查询包含隐藏的表和字段
	 * 
	 * @param tableId
	 * @return
	 */
	public BpmFormTable getTableByIdContainHidden(Long tableId) {
		BpmFormTable bpmFormTable = dao.getById(tableId);
		if (bpmFormTable == null)
			return null;
		List<BpmFormField> fieldList = bpmFormFieldDao.getByTableIdContainHidden(tableId);
		bpmFormTable.setFieldList(fieldList);
		if (bpmFormTable.getIsMain().shortValue() == BpmFormTable.NOT_MAIN.shortValue())
			return bpmFormTable;

		List<BpmFormTable> subTableList = dao.getSubTableByMainTableId(tableId);
		if (BeanUtils.isEmpty(subTableList))
			return bpmFormTable;

		for (BpmFormTable table : subTableList) {
			List<BpmFormField> subFieldList = bpmFormFieldDao.getFieldsByTableId(table.getTableId());
			table.setFieldList(subFieldList);
		}
		bpmFormTable.setSubTableList(subTableList);

		return bpmFormTable;
	}

	/**
	 * 导入生成物理表
	 * 
	 * @param bpmFormTable
	 * @param orginTable
	 * @throws Exception
	 */
	private void importGenPhysicsTable(BpmFormTable destFormTable, BpmFormTable orginTable) throws Exception {
		if (BeanUtils.isEmpty(destFormTable))
			return;
		boolean isExists = bpmFormHandlerDao.tableExists(TableModel.CUSTOMER_TABLE_PREFIX + destFormTable.getTableName());
		// 物理表不存在则生
		if (!isExists) {
			this.genTable(destFormTable);
		} else {
			this.importUpdTable(destFormTable, orginTable);
		}
	}

	/**
	 * 导入更新表
	 * 
	 * @param destFormTable
	 * @param orginTable
	 * @throws Exception
	 */
	private void importUpdTable(BpmFormTable destFormTable, BpmFormTable orginTable) throws Exception {
		boolean hasData = bpmFormHandlerDao.getHasData(TableModel.CUSTOMER_TABLE_PREFIX + destFormTable.getTableName());
		if (hasData) {// 如果存在数据
			this.importDataTable(destFormTable, orginTable, BpmFormTable.IS_MAIN);
		} else {
			// 删除表
			try {
				dropTable(destFormTable);
			} catch (Exception e) {
				//存在子表的问题
				BpmFormTable bpmFormTable = this.getTableByIdContainHidden(destFormTable.getTableId());
				this.dropTable(bpmFormTable);
				destFormTable.setSubTableList(bpmFormTable.getSubTableList());
			}
			// 生成表
			this.genTable(destFormTable);
		}
	}

	/**
	 * 导入有数据的表 并生成字段
	 * 
	 * @param bpmFormTable
	 *            现在的表
	 * @param orginTable
	 *            原来的表
	 * @param isMain
	 *            是否主表
	 * @throws Exception
	 */
	private void importDataTable(BpmFormTable destFormTable, BpmFormTable orginTable, Short isMain) throws Exception {
		String tableName = destFormTable.getTableName();

		List<BpmFormField> fields = destFormTable.getFieldList();

		if (BeanUtils.isNotEmpty(fields))
			fields = convertFields(fields, false);

		// 处理是否存在的字段
		BaseTableMeta meta = TableMetaFactory.getMetaData(AppConfigUtil.get("jdbc.dbType"));
		TableModel tableModel = meta.getTableByName(TableModel.CUSTOMER_TABLE_PREFIX + tableName);
		if (BeanUtils.isNotEmpty(tableModel) && BeanUtils.isNotEmpty(tableModel.getColumnList())) {

			Set<BpmFormField> fileSet = new HashSet<BpmFormField>();
			for (BpmFormField bpmFormField : fields) {
				boolean flag = true;
				for (ColumnModel column : tableModel.getColumnList()) {
					if (column.getName().toUpperCase().equals(TableModel.CUSTOMER_COLUMN_PREFIX + bpmFormField.getFieldName().toUpperCase())) {
						flag = false;
						break;
					}
				}
				if (flag)
					fileSet.add(bpmFormField);
			}

			if (BeanUtils.isNotEmpty(fileSet)) {
				for (BpmFormField bpmFormField : fileSet) {
					// 设置字段id。
					ColumnModel columnModel = this.getByField(bpmFormField, 2);
					tableOperator.addColumn(TableModel.CUSTOMER_TABLE_PREFIX + tableName, columnModel);
				}
			}

		}

		if (isMain.shortValue() == BpmFormTable.NOT_MAIN.shortValue())
			return;
		// =========子表处理===
		// 子表的处理
		List<BpmFormTable> subTableList = destFormTable.getSubTableList();
		if (BeanUtils.isEmpty(subTableList))
			return;
		// 获取原来子表
		List<BpmFormTable> originTableList = orginTable.getSubTableList();

		Map<String, BpmFormTable> originTableMap = new HashMap<String, BpmFormTable>();
		for (BpmFormTable orginSubTable : originTableList) {
			originTableMap.put(orginSubTable.getTableName().toLowerCase(), orginSubTable);
		}

		for (BpmFormTable subTable : subTableList) {
			String subTableName = subTable.getTableName().toLowerCase();
			if (originTableMap.containsKey(subTableName)) {
				// 更新表
				this.importDataTable(destFormTable, orginTable, BpmFormTable.NOT_MAIN);
			}
			// 新加的子表。
			else {
				List<BpmFormField> subFields = subTable.getFieldList();
				subFields = this.convertFields(subFields, false);

				TableModel subTableModel = this.getTableModelByBpmFormTable(subTable);
				tableOperator.createTable(subTableModel);
				tableOperator.addForeignKey(TableModel.CUSTOMER_TABLE_PREFIX + tableName, TableModel.CUSTOMER_TABLE_PREFIX + subTable.getTableName(), TableModel.PK_COLUMN_NAME, TableModel.FK_COLUMN_NAME);
			}
		}
	}

	/**
	 * 导入表，并解析相关信息
	 * 
	 * @param map
	 * @param bpmFormTableXml
	 * @param mainTableId
	 * @throws Exception
	 */
	public void importBpmFormTable(BpmFormTable bpmFormTable, Short isMain, Map<Long, Long> map) throws Exception {
		Long tableId = bpmFormTable.getTableId();
		// 导入字段
		List<BpmFormField> bpmFormFieldList = bpmFormTable.getFieldList();
		// 如果存在子表，递归导入子表
		List<BpmFormTable> subTableList = bpmFormTable.getSubTableList();
		// 导入表
		bpmFormTable = this.importFormTable(bpmFormTable);
		if (BeanUtils.isNotEmpty(bpmFormFieldList)) {
			for (BpmFormField bpmFormField : bpmFormFieldList) {
				this.importFormField(bpmFormField, bpmFormTable);
			}
		}
		map.put(tableId, bpmFormTable.getTableId());
		if (isMain.shortValue() == BpmFormTable.NOT_MAIN.shortValue())
			return;

		if (BeanUtils.isNotEmpty(subTableList)) {
			for (BpmFormTable subTable : subTableList) {
				this.importBpmFormTable(subTable, BpmFormTable.NOT_MAIN, map);
			}
		}
	}

	/**
	 * 导入自定义表时插入 BpmFormTable
	 * 
	 * @param t
	 * 
	 * @param xmlStr
	 * @param tableId
	 * @throws Exception
	 */
	private BpmFormTable importFormTable(BpmFormTable bpmFormTable) throws Exception {
		BpmFormTable table = dao.getByTableName(bpmFormTable.getTableName());
		String isMain = (bpmFormTable.getIsMain().shortValue() == BpmFormTable.IS_MAIN) ? "主" : "从";
		if (BeanUtils.isEmpty(table)) {
			table = new BpmFormTable();
			Long tableId = bpmFormTable.getTableId();
			// bpmFormTable.setCreatetime(new Date());
			// bpmFormTable.setPublishTime(new Date());
			bpmFormTable.setUpdatetime(new Date());
			// BeanUtils.copyProperties(table, bpmFormTable);
			BeanUtils.copyNotNullProperties(table, bpmFormTable);
			table.setTableId(tableId);
			dao.add(table);
			MsgUtil.addMsg(MsgUtil.SUCCESS, isMain + "表名为‘" + table.getTableDesc() + "’，该表成功导入！");

			return table;
		} else {
			Long tableId = table.getTableId();
			// table.setUpdatetime(new Date());
			BeanUtils.copyNotNullProperties(bpmFormTable, table);
			bpmFormTable.setTableId(tableId);
			dao.update(bpmFormTable);
			MsgUtil.addMsg(MsgUtil.WARN, isMain + "表名为‘" + bpmFormTable.getTableDesc() + "’的已经存在，该表进行更新！");
			return bpmFormTable;
		}

	}

	/**
	 * 导入自定义表时插入 BpmFormField
	 * 
	 * @param bpmFormTable
	 * @param bpmFormTable
	 * 
	 * @param xmlStr
	 * @param tableId
	 * @throws Exception
	 */
	private void importFormField(BpmFormField field, BpmFormTable bpmFormTable) throws Exception {
		BpmFormField bpmFormField = bpmFormFieldDao.getById(field.getFieldId());
		Long tableId = bpmFormTable.getTableId();
		if (BeanUtils.isEmpty(bpmFormField)) {
			BpmFormField bpmFormField1 = bpmFormFieldDao.getFieldByTidFna(tableId, field.getFieldName());
			if (BeanUtils.isNotEmpty(bpmFormField1)) {
				Long fieldId = bpmFormField1.getFieldId();
				// field.setCreatetime(new Date());
				field.setUpdatetime(new Date());
				BeanUtils.copyNotNullProperties(bpmFormField1, field);
				bpmFormField1 = this.convertFormField(bpmFormField1);
				bpmFormField1.setFieldId(fieldId);
				bpmFormField1.setTableId(tableId);
				bpmFormFieldDao.update(bpmFormField1);
				MsgUtil.addMsg(MsgUtil.WARN, "字段名为‘" + field.getFieldDesc() + "’的已经存在，该字段进行更新！");
			} else {
				field.setTableId(tableId);
				field = this.convertFormField(field);
				bpmFormFieldDao.add(field);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "字段名为‘" + field.getFieldDesc() + "’，该字段成功导入！");
			}

		} else {
			Long fieldId = bpmFormField.getFieldId();
			// field.setCreatetime(new Date());
			field.setUpdatetime(new Date());
			BeanUtils.copyNotNullProperties(bpmFormField, field);
			bpmFormField = this.convertFormField(bpmFormField);
			bpmFormField.setFieldId(fieldId);
			bpmFormField.setTableId(tableId);
			bpmFormFieldDao.update(bpmFormField);
			MsgUtil.addMsg(MsgUtil.WARN, "字段名为‘" + bpmFormField.getFieldDesc() + "’的已经存在，该字段进行更新！");
		}
	}

	/**
	 * 导入自定义表
	 * 
	 * @param field
	 */
	private BpmFormField convertFormField(BpmFormField field) {
		field.setFieldId(field.getFieldId());
		// 脚本
		field.setScript(StringUtil.convertScriptLine(field.getScript(), false));
		// 下拉框
		field.setOptions(StringUtil.convertLine(field.getOptions(), false));

		if (isExecutorSelector(field.getControlType())) {
			if (field.getFieldName().lastIndexOf(BpmFormField.FIELD_HIDDEN) != -1) {
				field.setIsHidden(BpmFormField.HIDDEN);
			}
			// 处理导入旧数据
			if (field.getFieldName().lastIndexOf("Id") != -1) {
				field.setIsHidden(BpmFormField.HIDDEN);
				field.setFieldName(field.getFieldName().substring(0, field.getFieldName().length() - 2) + BpmFormField.FIELD_HIDDEN);
			}

		}
		return field;
	}

	/**
	 * 导入流水号时插入Identity
	 * 
	 * @param identity
	 * @return
	 * @throws Exception
	 */
	private void importIdentity(Identity identity) throws Exception {
		Identity isExist = identityDao.getByAlias(identity.getAlias());
		if (isExist == null) {
			identity.setId(UniqueIdUtil.genId());
			identity.setCurDate(DateFormatUtil.format(new Date(), "yyyy-MM-dd"));
			identityDao.add(identity);
			MsgUtil.addMsg(MsgUtil.SUCCESS, "流水号为‘" + identity.getName() + "’，该流水号成功导入！");
		} else {
			MsgUtil.addMsg(MsgUtil.ERROR, "流水号为‘" + identity.getName() + "’已经存在，该流水号终止导入！");
		}
	}

	/**
	 * TODO 导出自定义表XML
	 * 
	 * @param Long
	 *            [] tableIds 导出的tableId
	 * @param map
	 * @return
	 */
	public String exportXml(Long[] tableIds, Map<String, Boolean> map) throws Exception {
		BpmFormTableXmlList bpmFormTableXmls = new BpmFormTableXmlList();
		List<BpmFormTableXml> list = new ArrayList<BpmFormTableXml>();
		for (int i = 0; i < tableIds.length; i++) {
			BpmFormTable formTable = dao.getById(tableIds[i]);
			BpmFormTableXml bpmFormTableXml = this.exportTable(formTable, map);
			list.add(bpmFormTableXml);
		}
		bpmFormTableXmls.setBpmFormTableXmlList(list);
		return XmlBeanUtil.marshall(bpmFormTableXmls, BpmFormTableXmlList.class);
	}

	/**
	 * 导出表的信息
	 * 
	 * @param formTable
	 * @param map
	 * @return
	 */
	public BpmFormTableXml exportTable(BpmFormTable formTable, Map<String, Boolean> map) {
		map = XmlUtil.getTableDefaultExportMap(map);

		BpmFormTableXml bpmFormTableXml = new BpmFormTableXml();
		// 字段列表
		List<BpmFormField> bpmFormFieldList = new ArrayList<BpmFormField>();
		// 流水号列表
		List<Identity> identityList = new ArrayList<Identity>();
		// 子表
		List<BpmFormTableXml> bpmFormTableXmlList = new ArrayList<BpmFormTableXml>();
		Long tableId = formTable.getTableId();
		if (BeanUtils.isNotEmpty(tableId)) {
			List<BpmFormField> formFieldList = new ArrayList<BpmFormField>();
			// 字段
			if (BeanUtils.isNotEmpty(map) && BeanUtils.isNotEmpty(map.get("bpmFormField"))) {
				formFieldList = bpmFormFieldDao.getByTableIdContainHidden(tableId);
				this.exportBpmFormTable(formFieldList, bpmFormFieldList);
			}
			// 流水号
			if (BeanUtils.isNotEmpty(map) && BeanUtils.isNotEmpty(map.get("identity"))) {
				if (map.get("identity")) {
					this.exportIdentity(formFieldList, identityList);
				}
			}
			// 有子表，递归
			if (BeanUtils.isNotEmpty(map) && BeanUtils.isNotEmpty(map.get("subTable"))) {
				if (map.get("subTable")) {
					this.exportSubTable(tableId, map, bpmFormTableXmlList);
				}

			}
		}

		bpmFormTableXml.setBpmFormTable(formTable);
		if (BeanUtils.isNotEmpty(bpmFormFieldList))
			bpmFormTableXml.setBpmFormFieldList(bpmFormFieldList);
		if (BeanUtils.isNotEmpty(identityList))
			bpmFormTableXml.setIdentityList(identityList);
		if (BeanUtils.isNotEmpty(bpmFormTableXmlList))
			bpmFormTableXml.setBpmFormTableXmlList(bpmFormTableXmlList);
		return bpmFormTableXml;
	}

	/**
	 * 导出字段
	 * 
	 * @param formFieldList
	 * @param bpmFormFieldList
	 */
	private void exportBpmFormTable(List<BpmFormField> formFieldList, List<BpmFormField> bpmFormFieldList) {
		for (BpmFormField bpmFormField : formFieldList) {
			// 脚本
			bpmFormField.setScript(StringUtil.convertScriptLine(bpmFormField.getScript(), true));
			// 下拉框
			bpmFormField.setOptions(StringUtil.convertLine(bpmFormField.getOptions(), true));
			bpmFormFieldList.add(bpmFormField);
		}
	}

	/**
	 * 导出流水号
	 * 
	 * @param formFieldList
	 * @param identityList
	 */
	private void exportIdentity(List<BpmFormField> formFieldList, List<Identity> identityList) {
		for (BpmFormField bpmFormField : formFieldList) {
			// 流水号
			if (StringUtil.isNotEmpty(bpmFormField.getIdentity())) {
				Identity identity = identityDao.getByAlias(bpmFormField.getIdentity());
				identityList.add(identity);
			}
		}

	}

	/**
	 * 导出子表
	 * 
	 * @param tableId
	 * @param map
	 * @param bpmFormTableXmlList
	 */
	private void exportSubTable(Long tableId, Map<String, Boolean> map, List<BpmFormTableXml> bpmFormTableXmlList) {
		List<BpmFormTable> subTables = dao.getSubTableByMainTableId(tableId);
		if (BeanUtils.isNotEmpty(subTables)) {
			for (BpmFormTable bpmFormTable : subTables) {
				bpmFormTableXmlList.add(this.exportTable(bpmFormTable, map));
			}
		}
	}

	/**
	 * 重置自定义表
	 * @param tableId
	 * @throws Exception 
	 */
	public void reset(Long tableId){
		BpmFormTable bpmFormTable =getById(tableId);
		if (bpmFormTable.getIsMain() == 1) {
			List<BpmFormTable> subTableList = getSubTableByMainTableId(tableId);
			if (BeanUtils.isNotEmpty(subTableList)) {
				for (BpmFormTable subTable : subTableList) {
					// 删除实体表
					tableOperator.dropTable(TableModel.CUSTOMER_TABLE_PREFIX + subTable.getTableName());
					if (subTable.isExtTable())
						continue;
					resetFormTableInfo(subTable);
				}
			}
		}
		// 删除实体表。
		tableOperator.dropTable(TableModel.CUSTOMER_TABLE_PREFIX+bpmFormTable.getTableName());
		
		resetFormTableInfo(bpmFormTable);
		
	}
	
	private void resetFormTableInfo(BpmFormTable bpmFormTable){
		if(bpmFormTable.getIsMain()==0){
			bpmFormTable.setMainTableId(0L);
		}
		bpmFormTable.setIsPublished(BpmFormTable.NOT_PUBLISH);
		bpmFormTable.setPublishedBy(null);
		bpmFormTable.setPublishTime(null);
		dao.update(bpmFormTable);
		List<BpmFormField> fields = bpmFormFieldDao.getByTableId(bpmFormTable.getTableId());
		for(BpmFormField field:fields){
			int isDeleted = field.getIsDeleted()==null?BpmFormField.IS_DELETE_N:field.getIsDeleted();
			if(BpmFormField.IS_DELETE_Y==isDeleted){
				bpmFormFieldDao.delById(field.getFieldId());
			}
		}
	}
	// ======================================导入导出分割线======================================================
	
	public BpmFormTable getByAliasTableName(String dsAlias,String tableName){
		return dao.getByAliasTableName(dsAlias, tableName);
	}

	
	/**
	 * 获取可授权的主表
	 * @param tableId 子表的Id
	 * @return
	 */
	public List<BpmFormTable> getMainTableSubTableId(Long tableId){
		BpmFormTable subTable=dao.getById(tableId);
		List<BpmFormTable> list=null;
		if(StringUtil.isNotEmpty(subTable.getDsAlias())){
			list=dao.getMainTableByDsName(subTable.getDsAlias());
		}else{
			list = dao.getAssignableMainTable();
		}
		return list;
	}
	
	/**
	 * 获取默认的导出的Map
	 * @param map
	 * @return
	 */
	public Map<String, Boolean>  getDefaultExportMap(Map<String, Boolean> map){
		if(BeanUtils.isEmpty(map)){
			map = new HashMap<String, Boolean>();
			map.put("bpmFormTable", true);
			map.put("bpmFormField", true);
			map.put("subTable",true);
			map.put("identity", true);
		}
		return map;
	}

	/**
	 * 更新分类
	 * 
	 * @param categoryId
	 * @param aryTableId
	 */
	public void updCategory(Long categoryId, String[] aryTableId) {
		dao.updCategory(categoryId, aryTableId);
	}
}
