package com.hotent.platform.job;


import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.table.ITableOperator;
import com.hotent.core.util.AppUtil;
/**
 * 索引重建任务。
 * 
 * @author Raise
 * 
 */
public class IndexRebuildJob extends BaseJob {

	private Log logger = LogFactory.getLog(IndexRebuildJob.class);
	

	private ITableOperator tableOperator=(ITableOperator) AppUtil.getBean(ITableOperator.class);
	
	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		//计划重建的索引存放在JobDetail中JobDataMap，key值为indexes的Entry中，
		//其中Entry的值是一个字符串数据，数组的第一个项为索引名，第二项为表名。
		@SuppressWarnings("unchecked")
		Map<String , String>[] indexesMap = (Map<String , String>[]) jobDataMap.get("indexes");
		for(Map<String , String> indexMap:indexesMap){
			String indexName=indexMap.get("name");
			String tableName=indexMap.get("table");
			tableOperator.rebuildIndex(tableName, indexName);
		}
	}
}