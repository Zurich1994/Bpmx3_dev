package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.service.BaseService;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.FormDefTreeDao;
import com.hotent.platform.model.bpm.FormDefTree;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormTableService;

/**
 * <pre>
 * 对象功能:form_def_tree Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-05-12 14:46:20
 * </pre>
 */
@Service
public class FormDefTreeService extends BaseService<FormDefTree> {
	@Resource
	private FormDefTreeDao dao;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormDefService bpmFormDefService;

	public FormDefTreeService() {
	}

	@Override
	protected IEntityDao<FormDefTree, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 form_def_tree 信息
	 * 
	 * @param formDefTree
	 */
	public void save(FormDefTree formDefTree) {
		Long id = formDefTree.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			formDefTree.setId(id);
			this.add(formDefTree);
		} else {
			this.update(formDefTree);
		}
	}

	public FormDefTree getByFormDefId(Long formDefId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("formDefId", formDefId);
		return (FormDefTree) dao.getOne("getByFormDefId", map);
	}

	public FormDefTree getByAlias(String alias) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alias", alias);
		return (FormDefTree) dao.getOne("getByAlias", map);
	}

	public void delByFormDefId(Long formDefId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("formDefId", formDefId);
		dao.delBySqlKey("delByFormDefId", map);
	}

	public List<JSONObject> treeListJson(Long formDefId, Map<String, Object> params) throws Exception {
		FormDefTree formDefTree = getByFormDefId(formDefId);
		BpmFormDef bpmFormDef = bpmFormDefService.getById(formDefTree.getFormDefId());
		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(bpmFormDef.getTableId());
		String treeIdField = getFieldNameByFieldId(formDefTree.getTreeId(), bpmFormTable.isExtTable());
		String parentIdField = getFieldNameByFieldId(formDefTree.getParentId(), bpmFormTable.isExtTable());
		String displayField = getFieldNameByFieldId(formDefTree.getDisplayField(), bpmFormTable.isExtTable());

		List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
		StringBuffer sqlBuffer = new StringBuffer();

		sqlBuffer.append("select " + treeIdField + " id ");
		sqlBuffer.append("," + parentIdField + " parentId ");
		sqlBuffer.append("," + displayField + " name ");

		// 异步加载，需要返回isParent的值
		if (formDefTree.getLoadType().equals(FormDefTree.LOADTYPE_ASYNC)) {
			StringBuffer isParentSql = new StringBuffer();
			isParentSql.append("( case (select count(*)  from " + bpmFormTable.getDbTableName() + " p ");
			isParentSql.append("where p." + parentIdField + " = o." + treeIdField + " and p." + treeIdField + " != p." + parentIdField + ")");
			isParentSql.append("when 0 then 'false' else 'true' end )isParent ");
			sqlBuffer.append("," + isParentSql);
		}

		sqlBuffer.append("from " + bpmFormTable.getDbTableName() + " o ");

		// 异步加载，根据id查询
		if (formDefTree.getLoadType().equals(FormDefTree.LOADTYPE_ASYNC)) {
			String id = params.get("id").toString();
			if (StringUtil.isNotEmpty(id)) {
				sqlBuffer.append("where " + parentIdField + " = " + id);
			} else {
				sqlBuffer.append("where " + parentIdField + " = " + formDefTree.getRootId());
			}

		}

		// 获取jdbcTemplate
		JdbcTemplate jdbcTemplate = JdbcTemplateUtil.getNewJdbcTemplate(bpmFormTable.getDsAlias());
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sqlBuffer.toString());
		for (Map<String, Object> map : mapList) {
			JSONObject jo = new JSONObject();
			for (String key : map.keySet()) {
				Object val=map.get(key) == null ? "" : map.get(key);
				//下面换大小写是为了处理oracle没大小写之分的状况
				if (key.equals("PARENTID")) {
					jo.put("parentId", val);
					continue;
				}
				if(key.equals("ID")){
					jo.put("id", val);
					continue;
				}
				if(key.equals("NAME")){
					jo.put("name", val);
					continue;
				}
				if(key.equals("ISPARENT")){
					jo.put("isParent", val);
					continue;
				}
				jo.put(key, val);
			}

			jsonObjectList.add(jo);
		}

		return jsonObjectList;
	}

	/**
	 * 这个是专门为formDefTree服务的 当fid=0的时候，就当选择的是表的主键 因为内部表的fieldList是不包含其主键"ID"的
	 * 
	 * @param fid
	 *            ：字段ID
	 * @param isExtTable
	 *            :是否外部表
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	private String getFieldNameByFieldId(String fid, boolean isExtTable) {
		if (fid.toString().equals("0")) {
			return TableModel.PK_COLUMN_NAME;
		} else {
			return bpmFormFieldService.getById(Long.parseLong(fid)).getDbFieldName(isExtTable);
		}
	}
}
