package com.hotent.core.table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.hotent.core.mybatis.Dialect;
import com.hotent.core.mybatis.dialect.DB2Dialect;
import com.hotent.core.mybatis.dialect.DmDialect;
import com.hotent.core.mybatis.dialect.H2Dialect;
import com.hotent.core.mybatis.dialect.MySQLDialect;
import com.hotent.core.mybatis.dialect.OracleDialect;
import com.hotent.core.mybatis.dialect.SQLServer2005Dialect;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.DialectUtil;

/**
 * 获取视图信息基类。
 * @author ray。
 *
 */
@Component 
public abstract class BaseDbView {
	@Resource
	private JdbcTemplate jdbcTemplate;

//	@SuppressWarnings("rawtypes")
//	protected JdbcHelper jdbcHelper;
	
	protected String currentDb;
	
	public abstract String getType(String type);
	
	
//	public void setDataSource(SysDataSource sysDataSource) throws Exception {
//		String dsName=sysDataSource.getAlias();
//		String className = sysDataSource.getDriverName();
//		String url = sysDataSource.getUrl();
//		String userName = sysDataSource.getUserName();
//		String pwd = sysDataSource.getPassword();
////		jdbcHelper = JdbcHelper.getInstance();
////		jdbcHelper.init(dsName, className, url, userName, pwd);
//		currentDb=dsName;
////		jdbcTemplate=jdbcHelper.getJdbcTemplate();
//	}
	
	/**
	 * 根据视图名称获取model。
	 * @param viewName
	 * @return
	 * @throws SQLException
	 */
	public TableModel getModelByViewName(String viewName) throws SQLException {
		Connection conn= jdbcTemplate.getDataSource().getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		TableModel tableModel=new TableModel();
		tableModel.setName(viewName);
		tableModel.setComment(viewName);

		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from " + viewName);
			ResultSetMetaData metadata=rs.getMetaData();
	
			int count=metadata.getColumnCount();
			for(int i=1;i<=count;i++){
				ColumnModel columnModel=new ColumnModel();
				String columnName=metadata.getColumnName(i);
				String typeName=metadata.getColumnTypeName(i);
				String dataType=getType(typeName);
				columnModel.setName(columnName);
				columnModel.setColumnType(dataType);
				columnModel.setComment(columnName);
				tableModel.addColumnModel(columnModel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tableModel;
	}


	protected <T> List<T> getForList(String sql, PageBean pageBean,Class<T> elementType,String dbType)
			throws Exception {
		if(pageBean!=null){
			int pageSize=pageBean.getPageSize();
			int offset=pageBean.getFirst();
			Dialect dialect=DialectUtil.getDialect(dbType);
			String pageSql=dialect.getLimitString(sql, offset, pageSize);
			String totalSql=dialect.getCountSql(sql);
			List<T> list= this.jdbcTemplate.queryForList(pageSql,elementType);
			int total=this.jdbcTemplate.queryForInt(totalSql);
			pageBean.setTotalCount(total);
			return list;
		}else{
			return this.jdbcTemplate.queryForList(sql,elementType);
		}
	}
	
	
	protected <T> List<T> getForList(String sql, PageBean pageBean,RowMapper<T> rowMapper,String dbType)
			throws Exception {
		if(pageBean!=null){
			int pageSize=pageBean.getPageSize();
			int offset=pageBean.getFirst();
			Dialect dialect=DialectUtil.getDialect(dbType);
			String pageSql=dialect.getLimitString(sql, offset, pageSize);
			String totalSql=dialect.getCountSql(sql);
			List<T> list= this.jdbcTemplate.query(pageSql, rowMapper);
			int total=this.jdbcTemplate.queryForInt(totalSql);
			pageBean.setTotalCount(total);
			return list;
		}else{
			return this.jdbcTemplate.query(sql, rowMapper);
		}
	}
	
	
	/**
	 * 获取方言。
	 * 
	 * @param dbType
	 * @return
	 * @throws Exception
	 */
	/*private Dialect getDialect(String dbType) throws Exception {
		Dialect dialect;
		if (dbType.equals(SqlTypeConst.ORACLE)) {
			dialect = new OracleDialect();
		} else if (dbType.equals(SqlTypeConst.SQLSERVER)) {
			dialect = new SQLServer2005Dialect();
		} else if (dbType.equals(SqlTypeConst.DB2)) {
			dialect = new DB2Dialect();
		} else if (dbType.equals(SqlTypeConst.MYSQL)) {
			dialect = new MySQLDialect();
		} else if (dbType.equals(SqlTypeConst.H2)) {
			dialect = new H2Dialect();
		}else if (dbType.equals(SqlTypeConst.DM)) {
			dialect = new DmDialect();
		}else {
			throw new Exception("没有设置合适的数据库类型");
		}
		return dialect;

	}*/

	
	
}
