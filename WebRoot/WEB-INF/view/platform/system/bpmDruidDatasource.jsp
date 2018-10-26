<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Druid数据源信息</title>
<%@include file="/commons/include/get.jsp"%>
<script>
$(function(){
	reloadDsInfo();
});

function reloadDsInfo(){
	 var dsStr='${datasources}';
		var dsJson = jQuery.parseJSON(dsStr);
		$.each(dsJson.Content,function(i){
			var listHtml="";
			listHtml += '<table class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">';
			listHtml += '<tr><th width="230">属性名</th><th>属性值</th><th>描述</th></tr>';
			listHtml += '<tr><td>* UserName</td><td >'+dsJson.Content[i].UserName+'</td><td>用于创建连接的用户名</td></tr>';
			listHtml += '<tr><td>* URL</td><td >'+dsJson.Content[i].URL+'</td><td>JDBC驱动连接URL</td></tr>';
			listHtml += '<tr><td>* DbType</td><td >'+dsJson.Content[i].DbType+'</td><td>数据库类型</td></tr>';
			listHtml += '<tr><td>* DriverClassName</td><td >'+dsJson.Content[i].DriverClassName+'</td><td>数据库驱动类名</td></tr>';
			listHtml += '<tr><td>* FilterClassNames</td><td >'+dsJson.Content[i].FilterClassNames+'</td><td>过滤器类名</td></tr>';

			listHtml += '<tr><td>* TestOnBorrow</td><td >'+dsJson.Content[i].TestOnBorrow+'</td><td>浏览连接前，是否进行连接测试</td></tr>';
			listHtml += '<tr><td>* TestWhileIdle</td><td >'+dsJson.Content[i].TestWhileIdle+'</td><td>连接空闲一段时间后，是否进行连接测试</td></tr>';

			listHtml += '<tr><td>* InitialSize</td><td >'+dsJson.Content[i].InitialSize+'</td><td>初始化数据源，创建的连接数</td></tr>';
			listHtml += '<tr><td>* MinIdle</td><td>'+dsJson.Content[i].MinIdle+'</td><td>连接池至少应持有的连接数</td></tr>';
			listHtml += '<tr><td>* MaxActive</td><td >'+dsJson.Content[i].MaxActive+'</td><td>连接池可持有最大的连接数</td></tr>';

			listHtml += '<tr><td>NotEmptyWaitCount</td><td >'+dsJson.Content[i].NotEmptyWaitCount+'</td><td>等待取得连接的次数</td></tr>';
			listHtml += '<tr><td>NotEmptyWaitMillis</td><td >'+dsJson.Content[i].NotEmptyWaitMillis+'</td><td>等待取得连接的总时间(毫秒)</td></tr>';
			listHtml += '<tr><td>WaitThreadCount</td><td >'+dsJson.Content[i].WaitThreadCount+'</td><td>正在等待连接的线程数</td></tr>';

			listHtml += '<tr><td>StartTransactionCount</td><td >'+dsJson.Content[i].StartTransactionCount+'</td><td>开始事务数</td></tr>';
			listHtml += '<tr><td>TransactionHistogram</td><td >['+dsJson.Content[i].TransactionHistogram+']</td><td>事务花费时间统计直方图, [0-10 ms, 10-100 ms, 100-1 s, 1-10 s, 10-100 s, >100 s]</td></tr>';

			listHtml += '<tr><td>PoolingCount</td><td >'+dsJson.Content[i].PoolingCount+'</td><td>当前可用连接数</td></tr>';
			listHtml += '<tr><td>PoolingPeak</td><td >'+dsJson.Content[i].PoolingPeak+'</td><td>可用连接数峰值</td></tr>';
			listHtml += '<tr><td>PoolingPeakTime</td><td >'+dsJson.Content[i].PoolingPeakTime+'</td><td>可用连接数达到峰值的时间</td></tr>';

			listHtml += '<tr><td>ActiveCount</td><td >'+dsJson.Content[i].ActiveCount+'</td><td>当前活动连接数</td></tr>';
			listHtml += '<tr><td>ActivePeak</td><td >'+dsJson.Content[i].ActivePeak+'</td><td>当前活动连接数峰值</td></tr>';
			listHtml += '<tr><td>ActivePeakTime</td><td >'+dsJson.Content[i].ActivePeakTime+'</td><td>活动连接数达到峰值的时间</td></tr>';

			listHtml += '<tr><td>LogicConnectCount</td><td >'+dsJson.Content[i].LogicConnectCount+'</td><td>总的连接次数</td></tr>';
			listHtml += '<tr><td>LogicCloseCount</td><td >'+dsJson.Content[i].LogicCloseCount+'</td><td>总关闭连接次数</td></tr>';
			listHtml += '<tr><td>LogicConnectErrorCount</td><td>'+dsJson.Content[i].LogicConnectErrorCount+'</td><td>总连接错误次数</td></tr>';

			listHtml += '<tr><td>PhysicalConnectCount</td><td >'+dsJson.Content[i].PhysicalConnectCount+'</td><td>创建物理连接次数</td></tr>';
			listHtml += '<tr><td>PhysicalCloseCount</td><td id="DS-Info-PhysicalCloseCount">'+dsJson.Content[i].PhysicalCloseCount+'</td><td>关闭物理连接次数</td></tr>';
			listHtml += '<tr><td>PhysicalConnectErrorCount</td><td id="DS-Info-PhysicalConnectErrorCount">'+dsJson.Content[i].PhysicalConnectErrorCount+'</td><td>总物理连接总次数</td></tr>';

			listHtml += '<tr><td>PSCacheAccessCount</td><td >'+dsJson.Content[i].PSCacheAccessCount+'</td><td>PerpareStatement访问次数</td></tr>';
			listHtml += '<tr><td>PSCacheHitCount</td><td >'+dsJson.Content[i].PSCacheHitCount+'</td><td>PerpareStatement连接次数</td></tr>';
			listHtml += '<tr><td>PSCacheMissCount</td><td >'+dsJson.Content[i].PSCacheMissCount+'</td><td>PerpareStatement错误次数</td></tr>';
			
			listHtml += '<tr><td>ConnectionHoldTimeHistogram</td><td ><a >['+dsJson.Content[i].ConnectionHoldTimeHistogram+']</td><td>连接保持时间统计直方图, [0-1 ms, 1-10 ms, 10-100 ms, 100ms-1s, 1-10 s, 10-100 s, 100-1000 s, >1000 s]</td></tr>';
// 			if(dsJson.Content[i].RemoveAbandoned == true){
// 				listHtml += '<tr><td>ActiveConnection StackTrace</td><td><a href="activeConnectionStackTrace.ht?id='+dsJson.Content[i].Identity+'">查看</a></td><td>活动连接栈信息跟踪</td></tr>';
// 			}else{
// 				listHtml += '<tr><td>ActiveConnection StackTrace</td><td>要求设置removeAbandoned=true</td><td>活动连接栈信息跟踪</td></tr>';
// 			}
			listHtml += '<tr><td>PollingConnection Info</td><td ><a href="connectionInfo.ht?id='+dsJson.Content[i].Identity+'">查看</a></td><td>池连接信息</td></tr>';

			listHtml += '</table>';
			$('#dataSourceStatList').html(listHtml);
		});
}

</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    数据源信息监控			
			</div>
		
		</div>
		<div class="panel-data">
			
		<div class="panel-body">
			
				<div id="dataSourceStatList"></div>
			</div>
		</div>
	</div>
</body>
</html>