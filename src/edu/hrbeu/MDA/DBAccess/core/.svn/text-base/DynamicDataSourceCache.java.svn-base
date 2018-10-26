package edu.hrbeu.MDA.DBAccess.core;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *  数据源Cache
 * @author csx
 *
 */
public class DynamicDataSourceCache {
	
	private final static ConcurrentMap<String,DriverManagerDataSource> dataSourcesMap=new java.util.concurrent.ConcurrentHashMap<String, DriverManagerDataSource>();
	
	public DynamicDataSourceCache(){
		
	}
	
	public void putDataSource(String dsAlias,DriverManagerDataSource dataSource){
		dataSourcesMap.put(dsAlias, dataSource);
	}
	
	public void delDataSource(String dsAlias){
		dataSourcesMap.remove(dsAlias);
	}
	
	public DriverManagerDataSource putDataSource(String dsAlias,String driverClassName,String url,String username,String password){
		//DriverManagerDataSource dataSource=new DriverManagerDataSource(driverClassName, url, username, password);
		DriverManagerDataSource dataSource =new DriverManagerDataSource(url, username, password);
//		Properties conProps = new Properties();
//		conProps.setProperty("user",username);
//		conProps.setProperty("password",password);
//		DriverManagerDataSource dataSource=new DriverManagerDataSource(url, conProps);
		Properties conProps = new Properties();
		conProps.setProperty("driverClassName",driverClassName);
		dataSource.setConnectionProperties(conProps);
		dataSource.setDriverClassName(driverClassName);
		putDataSource(dsAlias,dataSource);
		return dataSource;
	}
	
	public DriverManagerDataSource getDataSource(String dsAlias){
		return dataSourcesMap.get(dsAlias);
	}
		
}
