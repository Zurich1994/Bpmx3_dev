package com.hotent.core.web.query.scan;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.annotion.query.Table;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;

/**
 * 表的元数据表。
 * 
 * @author csx
 */
public class TableEntity {

	/**
	 * 表名（针对主表）。
	 */
	private String tableName;

	/**
	 * 类别名。
	 */
	private String var;

	/**
	 * 页面的DisplayTag的ID
	 */
	private String displayTagId;

	/**
	 * 主表的主键名。
	 */
	private String pk;
	/**
	 * 表的描述。
	 */
	private String comment;
	/**
	 * 是否是主表。
	 */
	private boolean isPrimary = true;

	/**
	 * 外键,如果是主表外键为空。
	 */
	private String relation = "";

	/**
	 * 是否是主表。
	 */
	private String primaryTable = "";

	/**
	 * 表字段。
	 */
	private List<TableField> tableFieldList = new ArrayList<TableField>();

	/**
	 * 子表列表。
	 */
	private List<TableEntity> subTableList = new ArrayList<TableEntity>();

	public TableEntity() {

	}

	public TableEntity(String claName, Table table) {
		this.tableName = table.name();
		this.var = StringUtils.isNotEmpty(table.var()) ? table.var()
				: StringUtil.makeFirstLetterLowerCase(claName);
		this.displayTagId = StringUtils.isNotEmpty(table.displayTagId()) ? table
				.displayTagId() : table.var() + "Item";
		this.pk = table.pk();
		this.comment = table.comment();
		this.isPrimary = table.isPrimary();
		this.relation = table.relation();
		this.primaryTable = table.primaryTable();
	}

	public String getTableName() {
		return tableName;
	}

	public String getVar() {
		return var;
	}

	public String getDisplayTagId() {
		return displayTagId;
	}

	public String getPk() {
		return pk;
	}

	public String getComment() {
		return comment;
	}

	public List<TableField> getTableFieldList() {
		return tableFieldList;
	}

	public void setTableFieldList(List<TableField> tableFieldList) {
		this.tableFieldList = tableFieldList;
	}

	private static ThreadLocal<Map<String, Object>> queryFilterLocal = new ThreadLocal<Map<String, Object>>();

	public static void setQueryFilterLocal(Map<String, Object> map) {
		queryFilterLocal.set(map);
	}

	public static Map<String, Object> getQueryFilterLocal() {
		return queryFilterLocal.get();
	}

	public static void removeQueryFilterLocal() {
		queryFilterLocal.remove();
	}

	/**
	 * 取得表分页的数据
	 * 
	 * @param queryFilter
	 * @return
	 */
	public static List<TableEntity> getAll(QueryFilter queryFilter) {
		List<TableEntity> tableEntitylist = new ArrayList<TableEntity>(
				SearchCache.getTableEntityMap().values());
		List<TableEntity> queryList = new ArrayList<TableEntity>(
				tableEntitylist);
		if (BeanUtils.isNotEmpty(queryFilter.getFilters())) {
			queryList = getQueryList(queryList, tableEntitylist, queryFilter);
			// 进行排序
			queryList = getSortList(queryList, queryFilter);
		}

		// 进行分页
		PageBean pageBean = queryFilter.getPageBean();
		// 总记录数
		int total = queryList.size();
		// 每一页显示的条目数
		int pageSize = pageBean.getPageSize();
		// 每一页显示的条目数
		int currentPage = pageBean.getCurrentPage();

		int fromIndex = pageSize * (currentPage - 1);
		int toIndex = ((pageSize * currentPage) > total ? total
				: (pageSize * currentPage));
		List<TableEntity> list = queryList.subList(fromIndex, toIndex);
		pageBean.setTotalCount(total);
		queryFilter.setForWeb();
		return list;
	}

	/**
	 * 列表排序
	 * 
	 * @param queryList
	 * @param queryFilter
	 * @return
	 */
	private static List<TableEntity> getSortList(List<TableEntity> queryList,
			QueryFilter queryFilter) {
		Object orderField = queryFilter.getFilters().get("orderField");
		Object orderSeq = queryFilter.getFilters().get("orderSeq");

		if (BeanUtils.isEmpty(orderField) || BeanUtils.isEmpty(orderSeq))
			return queryList;
		TableEntity.setQueryFilterLocal(queryFilter.getFilters());
		// 排序
		Comparator<TableEntity> comparator = new Comparator<TableEntity>() {
			public int compare(TableEntity o1, TableEntity o2) {
				// 在这里实现你的比较
				Map<String, Object> m = TableEntity.getQueryFilterLocal();
				Object field = m.get("orderField");
				Object seq = m.get("orderSeq");
				String orderField = "";
				if ("tableName".equalsIgnoreCase((String) field))
					orderField = "tableName";
				boolean order = true;
				if ("asc".equalsIgnoreCase((String) seq))
					order = false;

				String s1 = BeanUtils.isNotEmpty(orderField) ? o1
						.getTableName() : o1.getComment();
				String s2 = BeanUtils.isNotEmpty(orderField) ? o2
						.getTableName() : o2.getComment();

				return compare(s1, s2, order);
			}

			public int compare(String s1, String s2, boolean order) {
				// Collator 类是用来执行区分语言环境的 String 比较的，这里选择使用CHINA
				Comparator<Object> cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				if (cmp.compare(s1, s2) < 0)
					return order ? -1 : 1;
				else if (cmp.compare(s1, s2) > 0)
					return order ? 1 : -1;
				else
					return 0;
			}
		};

		Collections.sort(queryList, comparator);
		TableEntity.removeQueryFilterLocal();
		return queryList;
	}

	/**
	 * 查询的字段
	 * 
	 * @param queryList
	 * @param list
	 * @param queryFilter
	 * @return
	 */
	private static List<TableEntity> getQueryList(List<TableEntity> queryList,
			List<TableEntity> list, QueryFilter queryFilter) {
		Object tableName = queryFilter.getFilters().get("tableName");
		Object description = queryFilter.getFilters().get("comment");
		if (BeanUtils.isEmpty(tableName) && BeanUtils.isEmpty(description))
			return queryList;
		queryList = new ArrayList<TableEntity>();
		int type = getQueryType(tableName, description);

		for (TableEntity tableEntity : list) {
			boolean flag = isContainsQuery(tableEntity, tableName, description,
					type);
			if (flag)
				queryList.add(tableEntity);
		}
		return queryList;
	}

	private static boolean isContainsQuery(TableEntity tableEntity,
			Object tableName, Object description, int type) {
		switch (type) {
		case 1:
			return isContainsQuery(tableEntity, tableName, description);
		case 2:
			return StringUtils.containsIgnoreCase(tableEntity.getTableName(),
					tableName.toString());
		case 3:
			return StringUtils.containsIgnoreCase(tableEntity.getComment(),
					description.toString());
		}
		return false;
	}

	/**
	 * 是否包含TableName 和Description
	 * 
	 * @param tableEntity
	 * @param tableName
	 * @param description
	 * @return
	 */
	private static boolean isContainsQuery(TableEntity tableEntity,
			Object tableName, Object description) {
		if (isContainsQuery(tableEntity, tableName, 2)
				&& isContainsQuery(tableEntity, description, 3))
			return true;
		return false;
	}

	/**
	 * 查询的类型
	 * 
	 * @param tableName
	 * @param description
	 * @return
	 */
	private static int getQueryType(Object tableName, Object description) {
		int type = 0;
		if (BeanUtils.isNotEmpty(tableName)
				&& BeanUtils.isNotEmpty(description)) {
			type = 1;
		} else if (BeanUtils.isNotEmpty(tableName)
				&& BeanUtils.isEmpty(description)) {
			type = 2;
		} else if (BeanUtils.isEmpty(tableName)
				&& BeanUtils.isNotEmpty(description)) {
			type = 3;
		}
		return type;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getPrimaryTable() {
		return primaryTable;
	}

	public void setPrimaryTable(String primaryTable) {
		this.primaryTable = primaryTable;
	}

	public List<TableEntity> getSubTableList() {
		return subTableList;
	}

	public void setSubTableList(List<TableEntity> subTableList) {
		this.subTableList = subTableList;
	}

	/**
	 * 添加子表。
	 * 
	 * @param tableEnt
	 */
	public void addSubTable(TableEntity tableEnt) {
		this.subTableList.add(tableEnt);
	}
	
	/**
	 * 获取子表map
	 * @param tableEntity
	 * @return
	 */
	public static Map<String,TableEntity>  getSubTableMap(TableEntity tableEntity){
		List<TableEntity> subTableList = tableEntity.getSubTableList();
		 Map<String,TableEntity> map =  new HashMap<String, TableEntity>();
		if(BeanUtils.isEmpty(subTableList))
			return map;
		for (TableEntity table : subTableList) {
			map.put(table.getTableName(), table);
		}
		return map;
		
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tableName", tableName)
				.append("pk", pk).append("comment", comment)
				.append("tableFieldList.size", tableFieldList.size())
				.toString();
	}

}
