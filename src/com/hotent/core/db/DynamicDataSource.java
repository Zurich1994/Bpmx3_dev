package  com.hotent.core.db;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.hotent.core.db.DbContextHolder;

/**
 * 根据当前上下文取得数据源。
 * 
 * Spring配置， 可以配置多个数据源。
 * <pre>
 * <bean id="dataSource" class="com.hotent.core.db.DynamicDataSource">
 *		<property name="targetDataSources">
 *			<map key-type="java.lang.String">
 *				<entry key="1" value-ref="ds1" />
 *				<entry key="2" value-ref="ds2" />
 *			&lt;/map>
 *		&lt;/property>
 *		<property name="defaultTargetDataSource" ref="ds1" />
 *	&lt;/bean>
 *	</pre>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * 取得当前使用那个数据源。
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDbType();  
	}

	
	public Logger getParentLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
