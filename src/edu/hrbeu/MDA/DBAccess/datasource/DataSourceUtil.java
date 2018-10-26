package edu.hrbeu.MDA.DBAccess.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import edu.hrbeu.MDA.DBAccess.core.AppUtil;



/**
 * 数据源工具。 可以动态添加删除数据源。
 * 
 * <pre>
 * 构建组：x5-base-db
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-4-11-下午2:56:04
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DataSourceUtil {

	public static final String GLOBAL_DATASOURCE = "dataSource";

	public static final String DEFAULT_DATASOURCE = "dataSource_Default";

	public static final String TARGET_DATASOURCES = "targetDataSources";

	/**
	 * 添加数据源 。
	 * 
	 * @param key
	 * @param dataSource
	 *            void
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static void addDataSource(String key, DataSource dataSource) throws IllegalAccessException, NoSuchFieldException {
		DynamicDataSource dynamicDataSource = (DynamicDataSource) AppUtil.getBean(GLOBAL_DATASOURCE);
		dynamicDataSource.addDataSource(key, dataSource);
	}

	/**
	 * 添加数据源。
	 * 
	 * @param alias
	 *            数据源名称
	 * @param driverClassName
	 *            数据源驱动名称
	 * @param url
	 *            数据源URL
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 *             void
	 */
	public static void addDataSource(String alias, String driverClassName, String url, String username, String password) throws IllegalAccessException, NoSuchFieldException {
		BasicDataSource ds = createDataSource(driverClassName, url, username, password);
		addDataSource(alias, ds);
	}

	/**
	 * 根据名字删除数据源。
	 * 
	 * @param key
	 *            void
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static void removeDataSource(String key) throws IllegalAccessException, NoSuchFieldException {
		DynamicDataSource dynamicDataSource = (DynamicDataSource) AppUtil.getBean(GLOBAL_DATASOURCE);
		dynamicDataSource.removeDataSource(key);
	}

	/**
	 * 取得数据源。
	 * 
	 * @return Map&lt;String,DataSource>
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static Map<Object, Object> getDataSources() throws IllegalAccessException, NoSuchFieldException {
		DynamicDataSource dynamicDataSource = (DynamicDataSource) AppUtil.getBean(GLOBAL_DATASOURCE);
		return dynamicDataSource.getDataSource();
	}

	/**
	 * 创建数据源。
	 * 
	 * @param driverClassName
	 * @param url
	 * @param username
	 * @param password
	 * @return BasicDataSource
	 */
	public static BasicDataSource createDataSource(String driverClassName, String url, String username, String password) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setTestWhileIdle(true);
		return dataSource;
	}

	/**
	 * 根据别名返回容器里对应的数据源
	 * 
	 * @param alias
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 *             DataSource
	 * @exception
	 * @since 1.0.0
	 */
	public static DataSource getDataSourceByAlias(String alias) throws Exception {
		Map<Object, Object> map = getDataSources();
		for (Object key : map.keySet()) {
			if (alias.equals(key.toString())) {
				return (DataSource) map.get(key);
			}
		}
		return getDataSourceByAlias(DataSourceUtil.DEFAULT_DATASOURCE);
	}

	public static void showAllDataSource() {
		Map<Object, Object> map=new HashMap<Object, Object>();
		try {
			map = getDataSources();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----------->系统数据源");
		for (Object key : map.keySet()) {
			System.out.println(key+"："+map.get(key));
		}
		System.out.println("<-----------系统数据源");
	}

}
