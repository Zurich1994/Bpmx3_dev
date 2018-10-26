<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>监控记录表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script src="${ctx}/js/highcharts/jquery.1.9.1.min.js" type="text/javascript"></script>
  <script src="${ctx}/js/highcharts/highcharts.js" type="text/javascript"></script>
  <script src="${ctx}/js/highcharts/exporting.js" type="text/javascript"></script>
  <script src="${ctx}/js/highcharts/highcharts-more.js" type="text/javascript"></script>
   <script src="${ctx}/js/highcharts/drakblue.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
    darkblue();
    Highcharts.setOptions(Highcharts.theme);
    $('#container').highcharts({
        chart: {
            type: 'area',
            zoomType: 'x'
        },
        title: {
            text: '${quotaName}'
        },
         credits:{
     			enabled:false // 禁用版权信息
				},
        xAxis: {
            labels: {
                formatter: function() {
                    return this.value; // clean, unformatted number for year
                }
            },
            categories:<%=request.getAttribute("xList")%>
        },
        yAxis: {
            title: {
                text: '${quotaName}${quotaUnit}'
            },
             labels: {
                formatter: function() {
                    return this.value;
                }
            }
        },
        tooltip: {
             pointFormat: '{series.name}<b>{point.y}${quotaUnit}</b>'
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            area: {
                
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 2,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },
        series: [{
            type: 'area',
            name: '${quotaName}',
            data: <%=request.getAttribute("yList")%>
       	 	}]
    	});
});
        
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">监控图形</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht?deviceQuotaRelId=${deviceQuotaRelId}"><span></span>返回</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
	    	<div id="container" style="min-width:800px;height:400px"></div>
	    	
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


