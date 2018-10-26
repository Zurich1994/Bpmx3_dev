package com.hotent.core.table;


import org.springframework.beans.factory.FactoryBean;

import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.table.impl.TableMetaFactory;

/**
 * TableOperator factorybean，用户创建ITableOperator对象。
 * <pre>
 * 配置文件：app-beans.xml
 * &lt;bean id="tableOperator" class="com.hotent.core.customertable.TableOperatorFactoryBean">
 *			&lt;property name="dbType" value="${jdbc.dbType}"/>
 *			&lt;property name="jdbcTemplate" ref="jdbcTemplate"/>
 * &lt;/bean>
 * </pre>
 * @author ray
 *
 */
public class TableMetaFactoryBean implements FactoryBean<BaseTableMeta> {
	

	
	private BaseTableMeta tableMeta;
	
	private String dbType=SqlTypeConst.MYSQL;

//	private SysDataSource sysDataSource;

	public BaseTableMeta getObject() throws Exception {
//		dbType=sysDataSource.getDbType();
		
		tableMeta=TableMetaFactory.getMetaData(DataSourceUtil.DEFAULT_DATASOURCE);
	//	sysDataSource.setPassword(sysDataSource.getEncPassword());
//		tableMeta.setDataSource(sysDataSource);
		return tableMeta;
	}
	
	
	/**
	 * 设置数据库类型
	 * @param dbType
	 */
	public void setDbType(String dbType)
	{
		 this.dbType=dbType;
	}
	
	
//	public void setSysDataSource(SysDataSource sysDataSource) {
//		this.sysDataSource = sysDataSource;
//	}

	
	 

	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return BaseTableMeta.class;
	}

	public boolean isSingleton() {
		
		return true;
	}

}
