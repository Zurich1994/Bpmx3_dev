<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Druid SQL执行状态监控详细信息</title>
<%@include file="/commons/include/get.jsp"%>
<!--[if lt IE 9]><script type="text/javascript" src="excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.jqplot.min.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery/plugins/jquery.jqplot.min.css" /> --%>
<f:link href="jqplot/jquery.jqplot.min.css"></f:link>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jqplotPlugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jqplotPlugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jqplotPlugins/jqplot.pointLabels.min.js"></script>
<script type="text/javascript">
	$(function(){
		//Histogram
		var targetId='divHistogramChart';
		var data=new Array();
		var title="执行统计图";
		data[0]=${sqlStatData['HistogramJsAry']};
		var ticks=new Array('1-10 ms',
	            '10-100 ms',
	            '100ms-1 s',
	            '1-10 s',
	            '10-100 s',
	            '100-1000 s',
	            '> 1000 s');
		
		generateChart(targetId,data,title,ticks);
		
		
		//Histogram
		targetId='divFetchRowCountHistogramChart';
		data=new Array();
		title="获取记录统计直方图";
		data[0]=${sqlStatData['FetchRowCountHistogramJsAry']};
		ticks=new Array(
				'0-9行',
	            '10-99行',
	            '100-999行',
	            '1000-9999行',
	            '> 9999行');
		
		generateChart(targetId,data,title,ticks);
		
		//Histogram
		targetId='divEffectedRowCountHistogramChart';
		data=new Array();
		title="更新统计直方图";
		data[0]=${sqlStatData['EffectedRowCountHistogramJsAry']};
		ticks=new Array(
				'0-9行',
	            '10-99行',
	            '100-999行',
	            '1000-9999行',
	            '> 9999行');
		
		generateChart(targetId,data,title,ticks);

		targetId='divExecuteAndResultHoldTimeHistogramChart';
		data=new Array();
		title="ExecAndRsHold";
		data[0]=${sqlStatData['ExecuteAndResultHoldTimeHistogramJsAry']};
		ticks=new Array(
				'1-10 ms',
	            '10-100 ms',
	            '100ms-1 s',
	            '1-10 s',
	            '10-100 s',
	            '100-1000 s',
	            '> 1000 s');
		generateChart(targetId,data,title,ticks);
	});
	
	function generateChart(targetId,data,title,ticks){
		$.jqplot(targetId, data,{
			title:title,
			animate: !$.jqplot.use_excanvas,
            seriesDefaults:{
                renderer:$.jqplot.BarRenderer,
                pointLabels: { show: true }
            },
            axes: {
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: ticks
                }
            },
            highlighter: { show: false }
		});
	}
</script>
</head>
<body>
	<div class="panel">

		<div class="panel-body">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="sql.ht">返回</a></div>
				</div>
			</div>
			<div class="panel-data">
				<table class="table-detail" id="tblSqlStat" cellpadding="1" cellspacing="1">
					<tr>
						<th>完整SQL语句</th>
						<td>
						<textarea style='width: 99%; height: 120px;; border: 1px #A8C7CE solid; line-height: 20px; font-size: 12px;'>${sqlStatData['formatSQL']}
	       						</textarea></td>
					</tr>
					<tr>
						<th>执行次数</th>
						<td>${sqlStatData['ExecuteCount'] }</td>
					</tr>
					<tr>
						<th>总执行时间（毫秒）</th>
						<td>${sqlStatData['TotalTime'] }</td>
					</tr>
					<tr>
						<th>最大执行时间（毫秒）</th>
						<td>${sqlStatData['MaxTimespan'] }</td>
					</tr>
					<tr>
						<th>在事务中执行次数</th>
						<td>${sqlStatData['InTransactionCount'] }</td>
					</tr>
					<tr>
						<th>错误次数</th>
						<td>${sqlStatData['ErrorCount'] }</td>
					</tr>
					<tr>
						<th>影响记录数</th>
						<td>${sqlStatData['EffectedRowCount'] }</td>
					</tr>
					<tr>
						<th>获取记录数</th>
						<td>${sqlStatData['FetchRowCount'] }</td>
					</tr>
					<tr>
						<th>正在运行数</th>
						<td>${sqlStatData['RunningCount'] }</td>
					</tr>
					<tr>
						<th>并发数</th>
						<td>${sqlStatData['ConcurrentMax'] }</td>
					</tr>
					<tr>
						<th>执行统计直方图</th>
						<td><div id="divHistogramChart" style="height: 400px; width: 600px;"></div></td>
					</tr>
					<tr>
						<th>获取记录统计直方图</th>
						<td><div id="divFetchRowCountHistogramChart" style="height: 400px; width: 600px;"></div></td>
					</tr>
					<tr>
						<th>更新统计直方图</th>
						<td><div id="divEffectedRowCountHistogramChart" style="height: 400px; width: 600px;"></div></td>
					</tr>
					<tr>
						<th>ExecAndRsHold</th>
						<td><div id="divExecuteAndResultHoldTimeHistogramChart" style="height: 400px; width: 600px;"></div></td>
					</tr>
				</table>


			</div>
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


