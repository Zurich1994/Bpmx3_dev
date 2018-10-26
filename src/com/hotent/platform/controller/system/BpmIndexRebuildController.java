package com.hotent.platform.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.ITableOperator;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.scheduler.SchedulerService;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.job.IndexRebuildJob;
import com.hotent.platform.model.form.BpmFormTableIndex;


/**
 * 对象功能:索引重建计划控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Raise
 * 创建时间:2011-12-01 14:23:26
 */
@Controller
@RequestMapping("platform/system/bpmIndexRebuild/")
public class BpmIndexRebuildController extends BaseController
{	
	
	private String IINDEX_REBUILD_JOB_CLASS=IndexRebuildJob.class.getName();

	@Resource
	private ITableOperator tableOperator;
	
	@Resource 
	private SchedulerService schedulerService;
	
	
	/**
	 * 取得所有索引的列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("indexList")
	public ModelAndView indexList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String indexName=RequestUtil.getString(request, "Q_indexName_S");
		String tableName=RequestUtil.getString(request, "Q_tableName_S");
		
		ModelAndView mv=getAutoView();
		String dbType = tableOperator.getDbType();
		
		List<Map<String,Object>> indexes=new ArrayList<Map<String,Object>>();
		
			
		List<BpmFormTableIndex> indexList = tableOperator.getIndexesByFuzzyMatching(tableName, indexName,false);
		//用于存放所有索引所包含的所有数据表名(去掉重复)
		List<String> tableNames=new ArrayList<String>();
		for(BpmFormTableIndex index:indexList){
			//将索引对应的表名放到tableNames中。
			if(!tableNames.contains(index.getIndexTable())){
				tableNames.add(index.getIndexTable());
			}
		}
		Map<String,String> tableCommentMaps=getTablesCommentByNames(tableNames);
 		for(BpmFormTableIndex index:indexList){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("index", index);
			if(isCustomTable(index.getIndexTable())){
				map.put("isCustom", true);
			}else{
				map.put("isCustom", false);
			}
			
			String tableComment =  tableCommentMaps.get(index.getIndexTable());
			map.put("tableComment", tableComment);
			//remove the custom table prefix
			index.setIndexTable(index.getIndexTable().replaceFirst(TableModel.CUSTOMER_TABLE_PREFIX, ""));
			index.setIndexName(index.getIndexName().replaceFirst(TableModel.CUSTOMER_INDEX_PREFIX, ""));
			indexes.add(map);
		}
		
		
		mv.addObject("indexList", indexes);	
		mv.addObject("dbType",dbType);
		return mv;
	}
	
	private Map<String,String> getTablesCommentByNames(List<String> tableNames) throws Exception{
		Map<String,String> tableMaps=new HashMap<String, String>();
		List<String> names=new ArrayList<String>();
		for(int i=1;i<=tableNames.size();i++){
			names.add(tableNames.get(i-1));
			if(i%50==0 || i== tableNames.size()){
				BaseTableMeta tableMeta= TableMetaFactory.getMetaData(DataSourceUtil.DEFAULT_DATASOURCE);
				Map<String,String> map = tableMeta.getTablesByName(names);
				tableMaps.putAll(map);
				names.clear();
			}
		}
		return tableMaps;
	}

	/**
	 * 保存索引重建计划
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("saveJob")
	public void saveIndexRebuildJob(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String jobName=RequestUtil.getString(request, "jobName");
		String description=RequestUtil.getString(request, "jobDescription");
		if(description.length()>250){
			description=description.substring(0, 250);
		}
		String indexes=RequestUtil.getString(request, "indexes");
		
		JSONArray indexesJsonAry = JSONArray.fromObject(indexes);
		Map<String , String>[] indexAry=(Map<String, String>[]) JSONArray.toArray(indexesJsonAry,Map.class);
		
		
		for(Map<String, String> indexMap:indexAry){
			String indexName=indexMap.get("name");
			String indexTable=indexMap.get("table");
			boolean isCustom=indexMap.get("isCustom").equalsIgnoreCase("true");
			boolean isPrimaryKeyIndex=indexMap.get("isPrimaryKeyIndex").equalsIgnoreCase("true");
			if(isCustom){
				indexTable = TableModel.CUSTOMER_TABLE_PREFIX+indexTable;
				if(!isPrimaryKeyIndex){
					indexTable = TableModel.CUSTOMER_INDEX_PREFIX+indexTable;
					indexMap.put("index", indexName);
				}
				indexMap.put("table", indexTable);
			}
		}
		
		Map<String,Map<String , String>[]> indexesMap=new HashMap<String, Map<String,String>[]>();
		indexesMap.put("indexes", indexAry);
		ResultMessage resultMessage = schedulerService.addJob(jobName, IINDEX_REBUILD_JOB_CLASS, indexesMap, description);
		writeResultMessage(response.getWriter(),resultMessage);
	}
	
	
    /**
     * 任务列表
     * @param response
     * @param request
     * @return
     * @throws Exception 
     */
	@RequestMapping("jobList")
	public ModelAndView getJobList(HttpServletResponse response, HttpServletRequest request) throws Exception  
	{
		List<JobDetail> JobDetialList= schedulerService.getJobList();
		List<Map<String,Object>> jobDetailMapList=new ArrayList<Map<String,Object>>();
		for(JobDetail jobDetail:JobDetialList){
			if(jobDetail.getJobClass().getName().equals(IINDEX_REBUILD_JOB_CLASS)){
				Map<String,Object> map=new HashMap<String, Object>();
				//Job Name
				map.put("jobName", jobDetail.getKey().getName());
				//Job description
				map.put("jobDescription", jobDetail.getDescription());
				//jobDataMapList
				JobDataMap jobDataMap=jobDetail.getJobDataMap();
				@SuppressWarnings("unchecked")
				Map<String , String>[] indexesMap = (Map<String , String>[]) jobDataMap.get("indexes");
				List<Map<String,Object>> jobDataMapList=new ArrayList<Map<String, Object>>();
				for(Map<String , String> indexMap:indexesMap){
					Map<String,Object> map1=new HashMap<String, Object>();
					String indexName=indexMap.get("name");
					String tableName=indexMap.get("table");
					map1.put("tableName", tableName);
					map1.put("indexName", indexName);
					jobDataMapList.add(map1);
				}
				
				map.put("jobDataMapList", jobDataMapList);
				jobDetailMapList.add(map);
			}
		}

		ModelAndView mv=getAutoView();
		mv.addObject("jobDetailMapList",jobDetailMapList);
		mv.addObject("dbType", tableOperator.getDbType());
		return mv;
	}

	
	private boolean isCustomTable(String tableName){
		if(tableName.toUpperCase().startsWith(TableModel.CUSTOMER_TABLE_PREFIX)){
			return true;
		}else{
			return false;
		}
	}
}
