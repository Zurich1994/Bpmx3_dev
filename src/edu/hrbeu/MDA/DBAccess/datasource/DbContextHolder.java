package edu.hrbeu.MDA.DBAccess.datasource;



import edu.hrbeu.MDA.DBAccess.core.AppConfigUtil;
import edu.hrbeu.MDA.DBAccess.core.AppUtil;


/**
 * spring数据源，设置当前数据源。<br>
 * 
 * 设置方法：<br/>
 * 
 * <pre>
 * ApplicationContext c = new ClassPathXmlApplicationContext(locations);
 * 
 * DbContextHolder.setDbType(&quot;dataSource_Default2&quot;);
 * manager.addOracle();
 * 
 * DbContextHolder.setDefaultDbType();
 * manager.addMysql();
 * </pre>
 */
public class DbContextHolder {
	private static final ThreadLocal<String> contextHolderAlias = new ThreadLocal<String>();
	private static final ThreadLocal<String> contextHolderDbType = new ThreadLocal<String>();

	/**
	 * 
	 * 设置当前数据库。
	 * 
	 * @param dbAlias
	 *            :数据源别名，是在spring容器的
	 * @param dbType
	 *            ：数据源的类型：oracle,mysql... void
	 * @exception
	 * @since 1.0.0
	 */
	public static void setDataSource(String dbAlias, String dbType) {
		contextHolderAlias.set(dbAlias);
		contextHolderDbType.set(dbType);
	}

	public static void setDefaultDataSource() {
		String dbType = AppConfigUtil.get("jdbc.dbType");
		contextHolderAlias.set(DataSourceUtil.DEFAULT_DATASOURCE);
		contextHolderDbType.set(dbType);
	}

	/**
	 * 取得当前数据源。
	 * 
	 * @return
	 */
	public static String getDataSource() {
		String str = (String) contextHolderAlias.get();
		return str;
	}

	public static String getDbType() {
		String str = (String) contextHolderDbType.get();
		return str;
	}

	/**
	 * 清除上下文数据
	 */
	public static void clearDataSource() {
		contextHolderAlias.remove();
		contextHolderDbType.remove();
		//System.out.println("------------》清空数据源");
	}

	/**
	 * 注意这个别名sysDataSourceL的别名
	 * 
	 * @param alias
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
//	public static void setDataSource(String alias) {
//		SysDataSourceLService sysDataSourceLService = AppUtil.getBean(SysDataSourceLService.class);
//		SysDataSourceL sysDataSourceL = sysDataSourceLService.getByAlias(alias);
//		if (sysDataSourceL == null)
//			return;
//		setDataSource(alias, sysDataSourceL.getDbType());
//		//System.out.println(alias+" 被设置为当前数据源");
//	}
}
