package com.hotent.core.web.query.scan;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.hotent.core.annotion.query.Table;

/**
 * 构建允许动态构建的搜索实体。
 * 
 * <pre>
 *  在程序启动时，程序自动去查找
 * </pre>
 * 
 * @author csx
 * 
 */
public class SearchCache implements InitializingBean {

	public Logger logger = LoggerFactory.getLogger(SearchCache.class);

	/**
	 * 表的Map
	 */
	private static Map<String, TableEntity> tableEntityMap = new HashMap<String, TableEntity>();
	/**
	 * key：tableId，对应xxxList.jsp页面的displaytag的table tag的id。
	 * value:  table name，对应数据表的表名，如：BUS_DATA_RULE
	 */
	private static Map<String, TableEntity> displayTagIdMap = new HashMap<String, TableEntity>();
	
	/**
	 * 类名查找
	 */
	private static Map<String, TableEntity> tableVarMap = new HashMap<String, TableEntity>();	

	private Resource[]  basePackage;

	public void constractSearchTableList() throws IOException,
			ClassNotFoundException {	
		logger.debug("开始扫描元数据..");
		Long start=System.currentTimeMillis();
		
		List<Class<?>> tableList = TableScaner.findTableScan(basePackage);
		
		logger.debug("扫描结束,共耗时:" + (System.currentTimeMillis()-start) +"毫秒");

		for (Class<?> cls : tableList) {
			Table table = (Table) cls.getAnnotation(Table.class);
			TableEntity tableEntity = new TableEntity(cls.getSimpleName(),table);
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				com.hotent.core.annotion.query.Field qField = field
						.getAnnotation(com.hotent.core.annotion.query.Field.class);
				if (qField != null) {
					TableField tableField = new TableField(field,qField);
					tableEntity.getTableFieldList().add(tableField);
				}
			}
			tableEntityMap.put(tableEntity.getTableName(), tableEntity);
		
			if(StringUtils.isNotEmpty(tableEntity.getDisplayTagId()))
				displayTagIdMap.put(tableEntity.getDisplayTagId(), tableEntity);
			
			if(StringUtils.isNotEmpty(tableEntity.getVar()))
				tableVarMap.put(tableEntity.getVar(), tableEntity);
		}
		//子表建立关联关系。
		Collection<TableEntity> list= tableEntityMap.values();
		for(TableEntity sub:list){
			if(sub.isPrimary())
				continue;
			//是子表的情况。	
			String mainTable = sub.getPrimaryTable();
			if(StringUtils.isNotEmpty(mainTable)){
				TableEntity primaryTable= tableEntityMap.get(mainTable);
				primaryTable.addSubTable(sub);
			}
		}
	}

	public void afterPropertiesSet() throws Exception {
		this.constractSearchTableList();
	}

	public Resource[] getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(Resource[] basePackage) {
		this.basePackage = basePackage;
	}

	public static Map<String, TableEntity> getTableEntityMap() {
		return tableEntityMap;
	}

	public static Map<String, TableEntity> getDisplayTagIdMap() {
		return displayTagIdMap;
	}

	public static Map<String, TableEntity> getTableVarMap() {
		return tableVarMap;
	}
}
