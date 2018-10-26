package com.hotent.core.db.datasource;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.hotent.core.util.AppUtil;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.service.system.SysDataSourceService;

/**
 * 扫描系统xml中配置的数据源动态加入到系统的dataSourceMap数据源中，这样做的好处是 不需要修改x5-base-db.xml文件可以在外面的配置文件中添加新的数据源。
 * 
 * <pre>
 * 构建组：x5-base-db
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-4-11-下午2:11:24
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DataSourceInitListener implements ApplicationListener<ContextRefreshedEvent> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(DataSourceInitListener.class);

	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent ev) {
		// 防止重复执行。
		if (ev.getApplicationContext().getParent() == null) {
			ApplicationContext context = ev.getApplicationContext();
			// 加载配置文件中的数据源--->start
			loadFromXml(context);
			// 加载配置文件中的数据源--->end

			// 加载数据库中的数据源--->start
			loadFromDb();
						
			// 加载数据库中的数据源--->end
			connectTest();
		}
	}
	
	private void loadFromXml(ApplicationContext context){
		DynamicDataSource dynamicDataSource = (DynamicDataSource) context.getBean(DataSourceUtil.GLOBAL_DATASOURCE);

		// 加载配置文件中的数据源--->start
		Map<String, DataSource> mapDataSource = context.getBeansOfType(DataSource.class);

		Set<Entry<String, DataSource>> dsSet = mapDataSource.entrySet();
		for (Iterator<Entry<String, DataSource>> it = dsSet.iterator(); it.hasNext();) {
			Entry<String, DataSource> ent = it.next();
			String key = ent.getKey();
			if (key.equals(DataSourceUtil.GLOBAL_DATASOURCE) || key.equals(DataSourceUtil.DEFAULT_DATASOURCE))
				continue;
			try {
				dynamicDataSource.addDataSource(key, ent.getValue());
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			}
			LOGGER.debug("add datasource " + ent.getKey());
		}
		// 加载配置文件中的数据源--->end
	}
	
	/**
	 * 从数据库加载数据源。
	 */
	private void loadFromDb(){
		SysDataSourceService sysDataSourceService = (SysDataSourceService) AppUtil.getBean("sysDataSourceService");
		List<SysDataSource> sysDataSources = sysDataSourceService.getAll();
		for (SysDataSource sysDataSource : sysDataSources) {
			if (!sysDataSource.getEnabled() || !sysDataSource.getInitOnStart()) continue;
			try {
				DataSourceUtil.addDataSource(sysDataSource.getAlias(), sysDataSourceService.getDsFromSysSource(sysDataSource));
			} catch (Exception e) {
				LOGGER.debug("add datasource " + sysDataSource.getAlias());
			}
		}
	}
	
	private void connectTest(){
		LOGGER.debug("目前容器里的数据源--------------->");
		Map<Object, Object> map = null;
		try {
			map = DataSourceUtil.getDataSources();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (map == null) return;
		for (Object object : map.keySet()) {
			boolean isConnect= false;
			try {
				Connection connection = ((javax.sql.DataSource) map.get(object)).getConnection();
				connection.close();
				isConnect= true;
			} catch (Exception e) {
			}
			LOGGER.debug("alias:" + object + "--className:" + map.get(object).getClass().getName() + "--connectable:" + isConnect);
		}
		LOGGER.debug("<----------------------");
	}

}
