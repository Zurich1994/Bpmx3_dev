package com.hotent.core.db;

import java.util.Map;   
import java.util.Properties;

import javax.sql.DataSource;   
  
import org.apache.ibatis.datasource.DataSourceFactory;
import org.logicalcobwebs.proxool.ProxoolDataSource;   
  


public class ProxoolDataSourceFactory implements DataSourceFactory {

    private static ProxoolDataSource dataSource;    
     
    public  DataSource getDataSource() {   
        return dataSource;   
    }   
  
    /**  
     * 此方法中实现对ProxoolDataSource类中相关属性的设置  
     * house-keeping-sleep-time
     * 线程保持休眠时间，house keeper负责检查所有连接的状态，并测试是否需要销毁或者创建，默认时间为30秒
     * house-keeping-test-sql
     * 如果house keep 线程发现空闲的连接，他会测试使用这个sql进行测试，这个语句应该快速的被执行。像查询日期的sql语句。
     * maximum-active-time
     * 最大线程活动时间。
     * maximum-connection-count
     * 数据库最大连接数（默认值为15）
     * maximum-connection-count
     * 一个连接存在的最长保持活动的时间。默认值是4小时，单位是毫秒
     * overload-without-refusal-lifetime
     * 这个帮助我们确定连接池的状态。如果在这个时间阀值内（单位为毫秒）拒绝了一个连接，就认为是过载了。默认时间60秒。
     */  
    public static DataSource createDataSource(Map map) {   
        dataSource = new ProxoolDataSource();   
        
        String driver="";
        String driverUrl="";
        String user="";
        String alias="";
       if(map.containsKey("driver")){
    	   driver= (String)map.get("driver");
       }else{
    	   driver= (String)map.get("driverClassName");
       }
        dataSource.setDriver(driver); 
        
        if(map.containsKey("driverUrl")){
        	driverUrl= (String)map.get("driverUrl");
        }else{
        	driverUrl= (String)map.get("url");
        }
        dataSource.setDriverUrl(driverUrl);
        
        if(map.containsKey("user")){
        	user= (String)map.get("user");
         }else{
        	user= (String)map.get("username");
         }
        dataSource.setUser(user); 
        
        dataSource.setPassword((String)map.get("password")); 
        
        if(map.containsKey("alias")){
        	alias= (String)map.get("alias");
         }else{
        	 alias=driverUrl+user;
         }
        dataSource.setAlias(driverUrl); //别名必须唯一，不能有相同的
          
        //线程保持休眠时间，house keeper负责检查所有连接的状态，并测试是否需要销毁或者创建，默认时间为30秒
        if(map.containsKey("house-keeping-sleep-time")){
            dataSource.setHouseKeepingSleepTime(Integer.parseInt(map.get("house-keeping-sleep-time").toString()));
        }
        //如果house keep 线程发现空闲的连接，他会测试使用这个sql进行测试，这个语句应该快速的被执行。像查询日期的sql语句。
        if(map.containsKey("house-keeping-test-sql")){
            dataSource.setHouseKeepingTestSql(map.get("house-keeping-test-sql").toString());
        }else{
        	  dataSource.setHouseKeepingTestSql("select 1 from SYS_ACCEPT_IP");
        }
        //最大线程活动时间。
        //如果housekeeper 遇到一个线程活动时间超过定义的时间，将会终止这个线程。
        //所以你需要设置这个时间大于预计最慢响应的时间(默认时间是5分钟)。
        if(map.containsKey("maximum-active-time")){
            dataSource.setMaximumActiveTime(Integer.parseInt(map.get("maximum-active-time").toString()));
        }
        //数据库最大连接数（默认值为15）
        if(map.containsKey("maximum-connection-count")){
            dataSource.setMaximumConnectionCount(Integer.parseInt(map.get("maximum-connection-count").toString()));
        }
        //一个连接存在的最长保持活动的时间。默认值是4小时，单位是毫秒。
        if(map.containsKey("maximum-connection-lifetime")){
            dataSource.setMaximumConnectionLifetime(Integer.parseInt(map.get("maximum-connection-lifetime").toString()));
        }
        //最小连接保持打开的个数，不管是否需要，默认值是5个。
        if(map.containsKey("minimum-connection-count")){
            dataSource.setMaximumConnectionLifetime(Integer.parseInt(map.get("minimum-connection-count").toString()));
        }
        //这个帮助我们确定连接池的状态。如果在这个时间阀值内（单位为毫秒）拒绝了一个连接，就认为是过载了。默认时间60秒。
        if(map.containsKey("overload-without-refusal-lifetime")){
            dataSource.setMaximumConnectionLifetime(Integer.parseInt(map.get("overload-without-refusal-lifetime").toString()));
        }      
        return dataSource;
    }

	@Override
	public void setProperties(Properties paramProperties) {
		// TODO Auto-generated method stub
		
	}   
}