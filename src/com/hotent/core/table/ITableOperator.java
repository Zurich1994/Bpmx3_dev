package com.hotent.core.table;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.core.mybatis.Dialect;
import com.hotent.core.page.PageBean;
import com.hotent.platform.model.form.BpmFormTableIndex;

/**
 * 数据表操作接口。
 * 对每一个数据库写一个实现，实现对不同数据库的统一操作。
 * @author ray
 *
 */
public interface ITableOperator {
	
	/**
	 * 取得数据库的类型
	 * @return
	 */
	public String getDbType();
	
	/**
	 * 设置spring 的JDBCTemplate
	 * @param template
	 */
	public void setJdbcTemplate(JdbcTemplate template);
	
	/**
	 * 根据TableModel创建表。
	 * @param model
	 * @throws SQLException
	 */
	public void createTable(TableModel model) throws SQLException;
	
	/**
	 * 根据表名删除表。
	 * @param tableName
	 */
	public void dropTable(String tableName);
	
	/**
	 * 修改表的注释
	 * @param tableName
	 * @param comment
	 * @throws SQLException
	 */
	public void updateTableComment(String tableName,String comment) throws SQLException;
	
	
	/**
	 * 添加列
	 * @param tableName
	 * @param model
	 * @throws SQLException
	 */
	public void addColumn(String tableName,ColumnModel model) throws SQLException;
	
	/**
	 * 更新列。<br/>
	 * 可以修改列名，字段类型，字段是否非空，字段的注释。
	 * @param tableName
	 * @param columnName
	 * @param model
	 * @throws SQLException
	 */
	public void updateColumn(String tableName, String columnName, ColumnModel model) throws SQLException;
	
	/**
	 * 添加外键
	 * @param pkTableName	主键表
	 * @param fkTableName	外键表
	 * @param pkField		主键
	 * @param fkField		外键
	 */
	public void addForeignKey(String pkTableName,String fkTableName,String pkField,String fkField);
	
	/**
	 * 添加索引。
	 * @param tableName	表名
	 * @param fieldName	表字段
	 */
	public void createIndex(String tableName,String fieldName);

	/**
	 * 删除外键
	 * @param tableName
	 * @param keyName
	 */
	public void dropForeignKey(String tableName,String keyName);
	
	
//	public void createIndex(String tableName,String indexName,String indexType,List<String> fields) throws Exception;
	
	/**
	 * 通过BpmFormTableIndex实例，在数据库中创建索引
	 * @param index
	 * @throws SQLException
	 */
	public void createIndex(BpmFormTableIndex index) throws SQLException;
	
	/**
	 * 根据表名和索引名，删除表名和索引名对应的索引.所有实现使用精确匹配方式。
	 * @param tableName
	 * @param indexName
	 */
	public void dropIndex(String tableName,String indexName);
	
	/**	/**
	 * 根据表名和索引名，取得表名和索引名对应的索引.所有实现使用精确匹配方式。
	 * @param tableName
	 * @param tableName
	 * @param indexName
	 * @return
	 */
	public BpmFormTableIndex getIndex(String tableName,String indexName);
	
	/**
	 * 根据表名，取得表名对应的索引列表.所有实现使用精确匹配方式。
	 * @param tableName
	 * @return
	 */
	public List<BpmFormTableIndex> getIndexesByTable(String tableName);
	
	/**
	 * 根据表名，索引名使用模糊匹配，取得索引列表。
	 * @param tableName 表名
	 * @param indexName 索引名
	 * @param getDDL 是否获取索引的DDL
	 * @return
	 */
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName,String indexName,Boolean getDDL);
	
	/**
	 * 要把表名，索引名使用模糊匹配，取得索引列表。
	 * @param tableName 表名
	 * @param indexName 索引名
	 * @param getDDL 是否获取索引的DDL
	 * @param pageBean 分页Bean
	 * @return 取得索引列表
	 */
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName,String indexName,Boolean getDDL,PageBean pageBean);
	
	/**
	 * 重建索引
	 * @param tableName 表名
	 * @param indexName 索引名
	 */
	public void rebuildIndex(String tableName,String indexName);
	
	/**
	 * 根据表名，取得相应的主键的列表
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<String> getPKColumns(String tableName) throws SQLException;
	/**
	 * 根据表名列表，取得相应的主键的列列表的Map。Map中:key="name",value=表名；key=columns，value=相应的主键的列列表。
	 * @param tableNames
	 * @return
	 * @throws SQLException
	 */
	public Map<String,List<String>> getPKColumns(List<String> tableNames) throws SQLException;
	
	/**
	 * 设置相应的实现使用的方言
	 * @param dialect
	 */
	void setDialect(Dialect dialect);
	
	/**
	 * 设置相应的实现使用的数据库
	 * @param dbType
	 */
	void setDbType(String dbType);
	
	/**
	 * 判断表是否存在。
	 * @author hjx
	 * @version 创建时间：2013-10-28  下午4:56:52
	 * @param tableName
	 * @return
	 */
	boolean isTableExist(String tableName);
}
