package com.hotent.platform.controller.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Resource;
import javax.management.JMException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.alibaba.druid.stat.JdbcSqlStat;
import com.alibaba.druid.stat.JdbcStatManager;
import com.alibaba.druid.util.MapComparator;
import com.alibaba.fastjson.JSON;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;

/**
 * 对象功能:SYS_DEMENSION 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-01 14:23:26
 */
//@Controller
@RequestMapping("/platform/system/bpmDruid/")
public class BpmDruidController extends BaseController
{	
	
	protected Logger logger = LoggerFactory.getLogger(BpmDruidController.class);

	private static final long   serialVersionUID            = 1L;

    private final static int    RESULT_CODE_SUCCESS         = 1;
    
    private static final String DEFAULT_SORT_FIELD          = "SQL";
    private static final String DEFAULT_ORDER_SEQ           = "DESC";



//    private static List<Filter> totalFilters=new ArrayList<Filter>();
//    private static List<Map<String, Object>> totalFilters=new ArrayList<Map<String,Object>>();
    
    public String               templatePage;
    
    
	@Resource
	private FreemarkEngine freemarkEngine;
    
	@Resource
	DruidDataSource datasource;

	
	@RequestMapping("index")
	public ModelAndView druidIndex(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return getAutoView();
	}
    
	@RequestMapping("config")
	public ModelAndView druidConfig(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mv=getAutoView();
//		mv.addObject("totalFilters",totalFilters);
		return mv;
	}
	
//	@RequestMapping("saveConfig")
//	public synchronized void  druidSaveConfig(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		ResultMessage message=null;
//		String preUrl=RequestUtil.getPrePage(request);
//		Long id=RequestUtil.getLong(request, "id");
//		String operateType=RequestUtil.getString(request, "operateType");
//		for(Map<String, Object> map:totalFilters){
//			Long idTem=(Long) map.get("id");
//			if(id.longValue()!=idTem.longValue()){
//				continue;
//			}else{
//				try {
//					Filter filter = (Filter) map.get("instance");
//					if (operateType.equalsIgnoreCase("open")) {
//						datasource.getProxyFilters().add(filter);
//						map.put("using", true);
//						message=new ResultMessage(ResultMessage.Success, "开启过滤器成功！");
//					} else if (operateType.equalsIgnoreCase("close")) {
//						datasource.getProxyFilters().remove(filter);
//						map.put("using", false);
//						message=new ResultMessage(ResultMessage.Success, "关闭过滤器成功！");
//					}
//					
//				} catch (Exception e) {
//					message=new ResultMessage(ResultMessage.Fail, "过滤器开启/关闭失败！");
//				}
//				break;
//			}
//		}
//		addMessage(message, request);
//		response.sendRedirect(preUrl);
//	}
    
	
	@RequestMapping("sql")
	public ModelAndView druidSql(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String sortField=RequestUtil.getString(request, "sortField", DEFAULT_SORT_FIELD);
		String orderSeq=RequestUtil.getString(request, "orderSeq", DEFAULT_ORDER_SEQ);
		String newSortField=RequestUtil.getString(request, "newSortField");
		String curUrl = RequestUtil.getUrl(request);
		if(newSortField.equals(sortField)){
			if(orderSeq.equals("ASC")){
				orderSeq="DESC";
			}else{
				orderSeq="ASC";
			}
		}
		if(!StringUtil.isEmpty(newSortField)){
			sortField=newSortField;
		}
		
		Map<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("sortField", StringUtil.isEmpty(newSortField)?sortField:newSortField);
		parameters.put("orderSeq", orderSeq);
		curUrl = addParametersToUrl(curUrl, parameters);

		
        List<Map<String, Object>> sqlStatList = new ArrayList<Map<String, Object>>();
        for (DruidDataSource datasource : DruidDataSourceStatManager.getDruidDataSourceInstances()) {
            for (JdbcSqlStat sqlStat : datasource.getDataSourceStat().getSqlStatMap().values()) {
            	Map<String,Object> map = getSqlStatData(sqlStat);
            	long totalTime = (Long) map.get("TotalTime");
            	long executeCount = (Long) map.get("ExecuteCount");
            	map.put("AverageTime",totalTime/executeCount);
            	sqlStatList.add(map);
            }
        }
        
        if (sortField != null && orderSeq.trim().length() != 0) {
            Collections.sort(sqlStatList, new MapComparator<String, Object>(sortField, DEFAULT_ORDER_SEQ.equalsIgnoreCase(orderSeq)));
        }
		ModelAndView mv = getAutoView();
		mv.addObject("sqlStatList", sqlStatList);
		mv.addObject("curUrl",curUrl);
		
        return mv;
	}
	
	private String addParametersToUrl(String url,Map<String, Object> params){
		StringBuffer sb=new StringBuffer();
		int idx1=url.indexOf("?");
		if(idx1>0){
			sb.append(url.substring(0, idx1));
		}else{
			sb.append(url);
		}
		sb.append("?");
		
		for(Entry<String, Object> entry:params.entrySet()){
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		return sb.substring(0, sb.length()-1);
	}
	
	@RequestMapping("sqlDetail")
	public ModelAndView druidSqlDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Integer id=RequestUtil.getInt(request, "id");
		
		Map<String,Object> sqlStatData = getSqlStatData(id);
		String sql = (String) sqlStatData.get("SQL");
		String formatSQL ;
		try{
			formatSQL= SQLUtils.format(sql, datasource.getDbType());
		}catch(ParserException e){
			formatSQL=sql;
		}
		sqlStatData.put("formatSQL", formatSQL);
		sqlStatData.put("HistogramJsAry", JSON.toJSONString(sqlStatData.get("Histogram")));
		sqlStatData.put("FetchRowCountHistogramJsAry", JSON.toJSONString(sqlStatData.get("FetchRowCountHistogram")));
		sqlStatData.put("EffectedRowCountHistogramJsAry", JSON.toJSONString(sqlStatData.get("EffectedRowCountHistogram")));
		sqlStatData.put("ExecuteAndResultHoldTimeHistogramJsAry", JSON.toJSONString(sqlStatData.get("ExecuteAndResultHoldTimeHistogram")));

		
		ModelAndView mv = getAutoView();
		mv.addObject("sqlStatData", sqlStatData);
        return mv;
	}
	
	@RequestMapping("resetAll")
	public void druidResetAll(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String preUrl=RequestUtil.getPrePage(request);
		ResultMessage message=null;
		resetAllStat();
		message=new ResultMessage(ResultMessage.Success, "重置监控信息状态成功！");
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	@RequestMapping("datasource")
	public ModelAndView druidDatasrouce(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Object> datasources = getDataSourceStatList();
		String datasourcesStat = getJSONStat(RESULT_CODE_SUCCESS, datasources);
		ModelAndView mv = getAutoView();
		mv.addObject("datasources", datasourcesStat);
		return mv;
	}
	
	
	@RequestMapping("connectionInfo")
	public ModelAndView druidConnectionInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = RequestUtil.getInt(request, "id");
		DruidDataSource datasource = getDruidDataSourceById(id);
		List<Map<String, Object>> connectionInfos = datasource.getPoolingConnectionInfo();
		ModelAndView mv=getAutoView();
		mv.addObject("datasourceId",id);
		mv.addObject("connectionInfos",connectionInfos);
		return mv;

	}
	
	@RequestMapping("activeConnectionStackTrace")
	public ModelAndView druidConnectionStackTrace(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = RequestUtil.getInt(request, "id");
		DruidDataSource datasource = getDruidDataSourceById(id);
		List<String> activeConnectionStackTraces = datasource.getActiveConnectionStackTrace();
		Random random=new Random(System.currentTimeMillis());
		for(int i=0;i<random.nextInt(100);i++){
			activeConnectionStackTraces.add(String.valueOf(random.nextDouble()));
		}
		ModelAndView mv=getAutoView();
		mv.addObject("datasourceId",id);
		mv.addObject("activeConnectionStackTraces",JSON.toJSONString(activeConnectionStackTraces));
		logger.info(JSON.toJSONString(activeConnectionStackTraces));
		return mv;

	}
	


    private void resetAllStat() {
        JdbcStatManager.getInstance().reset();
        DruidDataSourceStatManager.getInstance().reset();
    }

   


    private List<Object> getDataSourceStatList() {
        List<Object> datasourceList = new ArrayList<Object>();
        for (DruidDataSource dataSource : DruidDataSourceStatManager.getDruidDataSourceInstances()) {
            datasourceList.add(dataSourceToMapData(dataSource));
        }
        return datasourceList;
    }


    private Map<String, Object> getSqlStatData(Integer id) {
        if (id == null) {
            return null;
        }
        JdbcSqlStat sqlStat = getSqlStatById(id);
        return sqlStat == null ? null : getSqlStatData(sqlStat);
    }

    private JdbcSqlStat getSqlStatById(Integer id) {
        for (DruidDataSource ds : DruidDataSourceStatManager.getDruidDataSourceInstances()) {
            JdbcSqlStat sqlStat = ds.getDataSourceStat().getSqlStat(id);
            if (sqlStat != null) return sqlStat;
        }
        return null;
    }

    private DruidDataSource getDruidDataSourceById(Integer identity) {
        if (identity == null) {
            return null;
        }
        for (DruidDataSource datasource : DruidDataSourceStatManager.getDruidDataSourceInstances()) {
            if (System.identityHashCode(datasource) == identity) {
                return datasource;
            }
        }
        return null;
    }

    private Map<String, Object> getSqlStatData(JdbcSqlStat sqlStat) {
        try {
            return sqlStat.getData();
        } catch (JMException e) {
        }
        return null;
    }

   

    private Map<String, Object> dataSourceToMapData(DruidDataSource dataSource) {

        Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
        dataMap.put("Identity", System.identityHashCode(dataSource));
        dataMap.put("Name", dataSource.getName());
        dataMap.put("DbType", dataSource.getDbType());
        dataMap.put("DriverClassName", dataSource.getDriverClassName());

        dataMap.put("URL", dataSource.getUrl());
        dataMap.put("UserName", dataSource.getUsername());
        dataMap.put("FilterClassNames", dataSource.getFilterClassNames());

        dataMap.put("WaitThreadCount", dataSource.getWaitThreadCount());
        dataMap.put("NotEmptyWaitCount", dataSource.getNotEmptyWaitCount());
        dataMap.put("NotEmptyWaitMillis", dataSource.getNotEmptyWaitMillis());

        dataMap.put("PoolingCount", dataSource.getPoolingCount());
        dataMap.put("PoolingPeak", dataSource.getPoolingPeak());
        dataMap.put("PoolingPeakTime",
                    dataSource.getPoolingPeakTime() == null ? null : dataSource.getPoolingPeakTime().toString());

        dataMap.put("ActiveCount", dataSource.getActiveCount());
        dataMap.put("ActivePeak", dataSource.getActivePeak());
        dataMap.put("ActivePeakTime",
                    dataSource.getActivePeakTime() == null ? null : dataSource.getActivePeakTime().toString());

        dataMap.put("InitialSize", dataSource.getInitialSize());
        dataMap.put("MinIdle", dataSource.getMinIdle());
        dataMap.put("MaxActive", dataSource.getMaxActive());

        dataMap.put("TestOnBorrow", dataSource.isTestOnBorrow());
        dataMap.put("TestWhileIdle", dataSource.isTestWhileIdle());

        dataMap.put("LogicConnectCount", dataSource.getConnectCount());
        dataMap.put("LogicCloseCount", dataSource.getCloseCount());
        dataMap.put("LogicConnectErrorCount", dataSource.getConnectErrorCount());

        dataMap.put("PhysicalConnectCount", dataSource.getCreateCount());
        dataMap.put("PhysicalCloseCount", dataSource.getDestroyCount());
        dataMap.put("PhysicalConnectErrorCount", dataSource.getCreateErrorCount());

        dataMap.put("PSCacheAccessCount", dataSource.getCachedPreparedStatementAccessCount());
        dataMap.put("PSCacheHitCount", dataSource.getCachedPreparedStatementHitCount());
        dataMap.put("PSCacheMissCount", dataSource.getCachedPreparedStatementMissCount());

        dataMap.put("StartTransactionCount", dataSource.getStartTransactionCount());
        dataMap.put("TransactionHistogram", dataSource.getTransactionHistogramValues());

        dataMap.put("ConnectionHoldTimeHistogram", dataSource.getDataSourceStat().getConnectionHoldHistogram().toArray());
        dataMap.put("RemoveAbandoned", dataSource.isRemoveAbandoned());

        return dataMap;
    }
    
    private String getJSONStat(int resultCode,Object object){
    	Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
        dataMap.put("ResultCode", resultCode);
        dataMap.put("Content", object);
    	return JSON.toJSONString(dataMap);
    }

}
