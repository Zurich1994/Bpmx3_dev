/**
 * 描述：TODO
 * 包名：com.hotent.core.util
 * 文件名：DialectUtil.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2014-8-25-下午2:00:09
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.core.util;

import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.mybatis.Dialect;
import com.hotent.core.mybatis.dialect.DB2Dialect;
import com.hotent.core.mybatis.dialect.DmDialect;
import com.hotent.core.mybatis.dialect.H2Dialect;
import com.hotent.core.mybatis.dialect.MySQLDialect;
import com.hotent.core.mybatis.dialect.OracleDialect;
import com.hotent.core.mybatis.dialect.SQLServer2005Dialect;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.service.system.SysDataSourceService;
//import com.hotent.platform.model.system.SysDataSourceL;
//import com.hotent.platform.service.system.SysDataSourceLService;
/**
 * <pre> 
 * 数据库方言工具类
 * 构建组：bpm_3.3
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-8-25-下午2:00:09
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DialectUtil {
	public static Dialect getDialect(String dbType) throws Exception {
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
	}
	
	/**
	 * 获取当前数据源方言
	 * @return
	 * @throws Exception 
	 * Dialect
	 * @exception 
	 * @since  1.0.0
	 */
	public static Dialect getCurrentDialect()throws Exception{
		return getDialect(DbContextHolder.getDbType());
	}
//		public static Dialect getDialect(SysDataSourceL sysDataSourceL) throws Exception{
//		return getDialect(sysDataSourceL.getDbType());
	public static Dialect getDialect(SysDataSource sysDataSource) throws Exception{
		return getDialect(sysDataSource.getDbType());
	}
	
	public static Dialect getDialectByDataSourceAlias(String alias) throws Exception{
//	SysDataSourceLService sysDataSourceLService = AppUtil.getBean(SysDataSourceLService.class);
//		SysDataSourceL sysDataSourceL = sysDataSourceLService.getByAlias(alias);
//		if (sysDataSourceL == null){//默认返回系统默认数据库
//			return getDialect(AppConfigUtil.get("jdbc.dbType"));
//		}
//		return getDialect(sysDataSourceL);
		SysDataSourceService sysDataSourceService = AppUtil.getBean(SysDataSourceService.class);
		SysDataSource sysDataSource = sysDataSourceService.getByAlias(alias);
		if (sysDataSource == null){//默认返回系统默认数据库
			return getDialect(AppConfigUtil.get("jdbc.dbType"));
		}
		return getDialect(sysDataSource);
	}
}
