package com.hotent.core.table;

import java.util.List;
import java.util.Map;

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
 * 数据表元数据抽象类。
 * 用于读取数据库表的元数据信息。
 * 1.查询数据库中的表列表。
 * 2.取得表的详细数据。
 * @author ray
 *
 */
@Component
public abstract class BaseTableMeta {
	@Resource
	private JdbcTemplate jdbcTemplate;
	

	/**
	 * 根据表名，取得表模型。此处对表名进行非模糊匹配。
	 * @param tableName 表名
	 * @return 表模型
	 */
	public abstract TableModel getTableByName(String tableName);

	/**
	 * 根据表名，使用模糊匹配，查询系统中的表。返回的用Map表示的数据对象。key=表名，value=表注解。
	 * 
	 * @param tableName
	 * @return
	 */
	public abstract Map<String, String> getTablesByName(String tableName);
	
	/**
	 * 根据表名,使用模糊匹配，查询系统中的表。返回的用Map表示的数据对象。key=表名，value=表注解。
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception 
	 */
	public abstract List<TableModel> getTablesByName(String tableName,PageBean pageBean) throws Exception;
	
	
	
	/**
	 * 根据表名查询系统中的表。
	 * 返回的Map中：key=表名，value=表comment
	 * 
	 * @param tableName
	 * @return
	 */
	public abstract Map<String, String> getTablesByName(List<String> names);
	
	public abstract String getAllTableSql();
	
	/**
	 * 设置数据源。
	 * @throws Exception
	 */
//	public void init() throws Exception{
//		SysDataSource sysDataSource=(SysDataSource)AppUtil.getBean("sysdatasource") ;
//		setDataSource(sysDataSource);
//	}
	
	
	/**
	 * 设置数据源。
	 * @param dsName 数据源名称
	 * @throws Exception
	 */
//	public void setDataSource(SysDataSource sysDataSource) throws Exception {
//		String dsName=sysDataSource.getAlias();
//		String className = sysDataSource.getDriverName();
//		String url = sysDataSource.getUrl();
//		String userName = sysDataSource.getUserName();
//		String pwd = sysDataSource.getPassword();
//		jdbcHelper = JdbcHelper.getInstance();
//		jdbcHelper.init(dsName, className, url, userName, pwd);
//		jdbcHelper.setCurrentDb(dsName);
//
//	}
	
	protected <T> List<T> getForList(String sql, PageBean pageBean,RowMapper<T> rowMapper,String dbType)
			throws Exception {
		if(pageBean!=null){
			int pageSize=pageBean.getPageSize();
			int offset=pageBean.getFirst();
			Dialect dialect=DialectUtil.getDialect(dbType);
			String pageSql=dialect.getLimitString(sql, offset, pageSize);
			String totalSql=dialect.getCountSql(sql);
//			jdbcHelper.setCurrentDb(currentDb);
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
		} else if (dbType.equals(SqlTypeConst.DM)) {
			dialect = new DmDialect();
		}else {
			throw new Exception("没有设置合适的数据库类型");
		}
		return dialect;

	}*/
}
