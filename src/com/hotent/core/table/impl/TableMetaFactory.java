package com.hotent.core.table.impl;

import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.util.AppConfigUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.controller.system.SysDataSourceController;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.service.system.SysDataSourceService;
//import com.hotent.platform.controller.system.SysDataSourceLController;
//import com.hotent.platform.model.system.SysDataSourceL;
//import com.hotent.platform.service.system.SysDataSourceLService;
/**
 * 元数据读取工厂。
 * 
 * @author ray
 * 
 */
public class TableMetaFactory {

	/**
	 * 根据数据源名称获取表元素据读取对象。 这里使用了简单工厂。
	 * 
	 * @param dsName
	 * @return
	 * @throws Exception
	 */
	public static BaseTableMeta getMetaData(String dsName) throws Exception {
		// SysDataSource sysDataSource = null;
		// if (dsName == null) {
		// sysDataSource = (SysDataSource) AppUtil.getBean("sysdatasource");
		// } else if (!dsName.equals(BpmConst.LOCAL_DATASOURCE)) {
		// SysDataSourceService sysDataSourceService = (SysDataSourceService) AppUtil.getBean(SysDataSourceService.class);
		// sysDataSource = sysDataSourceService.getByAlias(dsName);
		// } else {
		// sysDataSource = getLocalSysDataSource(dsName);
		// }

		String dbType = getDbTypeBySysDataSourceAlias(dsName);

		BaseTableMeta meta = null;
		if (dbType.equals(SqlTypeConst.ORACLE)) {
			// meta = new OracleTableMeta();
			meta = AppUtil.getBean(OracleTableMeta.class);
		} else if (dbType.equals(SqlTypeConst.MYSQL)) {
			// meta = new MySqlTableMeta();
			meta = AppUtil.getBean(MySqlTableMeta.class);
		} else if (dbType.equals(SqlTypeConst.SQLSERVER)) {
//			meta = new SqlServerTableMeta();
			meta = AppUtil.getBean(SqlServerTableMeta.class);
		} else if (dbType.equals(SqlTypeConst.DB2)) {
//			meta = new Db2TableMeta();
			meta = AppUtil.getBean(Db2TableMeta.class);
		} else if (dbType.equals(SqlTypeConst.H2)) {
//			meta = new H2TableMeta();
			meta = AppUtil.getBean(H2TableMeta.class);
		} else if (dbType.equals(SqlTypeConst.DM)) {
//			meta = new DmTableMeta();
			meta = AppUtil.getBean(DmTableMeta.class);
		} else {
			throw new Exception("未知的数据库类型");
		}
		// meta.setDataSource(sysDataSource);
		DbContextHolder.setDataSource(dsName);
		return meta;
	}

	/**
	 * 根据数据源获取
	 * 
	 * @param dsName
	 * @return
	 * @throws Exception
	 */
	public static IDbView getDbView(String dsName) throws Exception {
		/*
		 * SysDataSource sysDataSource = null; if(dsName.equals(BpmConst.LOCAL_DATASOURCE)){ sysDataSource=getLocalSysDataSource(dsName); }else{ SysDataSourceService sysDataSourceService = (SysDataSourceService) AppUtil .getBean(SysDataSourceService.class); sysDataSource = sysDataSourceService.getByAlias(dsName); }
		 */

		// String dbType =SqlTypeConst.getDbType(sysDataSource.getUrl());
		String dbType = getDbTypeBySysDataSourceAlias(dsName);

		IDbView meta = null;
		if (dbType.equals(SqlTypeConst.ORACLE)) {
			//meta = new OracleDbView();
			meta = AppUtil.getBean(OracleDbView.class);
		} else if (dbType.equals(SqlTypeConst.SQLSERVER)) {
			meta = AppUtil.getBean(SqlserverDbView.class);
		} else if (dbType.equals(SqlTypeConst.MYSQL)) {
			//meta = new MysqlDbView();
			meta = AppUtil.getBean(MysqlDbView.class);
		} else if (dbType.equals(SqlTypeConst.DB2)) {
			//meta = new Db2DbView();
			meta = AppUtil.getBean(Db2DbView.class);
		} else if (dbType.equals(SqlTypeConst.H2)) {
			//meta = new H2DbView();
			meta = AppUtil.getBean(H2DbView.class);
		} else if (dbType.equals(SqlTypeConst.DM)) {
			//meta = new DmDbView();
			meta = AppUtil.getBean(DmDbView.class);
		} else {
			throw new Exception("未知的数据库类型");
		}
		DbContextHolder.setDataSource(dsName);
		return meta;
	}

	// public static SysDataSource getLocalSysDataSource(String dsName) {
	// return (SysDataSource) AppUtil.getBean("sysdatasource");
	// }
	/**
	 * 根据别名从sys_Data_Source中找出对应的数据类型，默认是本地数据源
	 * 
	 * @param alias
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	private static String getDbTypeBySysDataSourceAlias(String alias) {
		SysDataSource sysDataSource = null;
		SysDataSourceService sysDataSourceService = (SysDataSourceService) AppUtil.getBean("sysDataSourceService");
		sysDataSource = sysDataSourceService.getByAlias(alias);

		String dbType = AppConfigUtil.get("jdbc.dbType");
		if (sysDataSource != null) {
			dbType = sysDataSource.getDbType();
		}

		return dbType;
	}
}
