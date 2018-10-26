<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Druid数据源连接栈信息</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	$(function(){
		reloadTrace();
		setInterval(reloadTrace,1000);
	});
	function reloadTrace() {
		var url="activeConnectionStackTrace.ht?id="+${datasourceId};
		$.get(url,function(){
			var items=${activeConnectionStackTraces};
			var activeConnectionStackTraces=$.parseJSON(items);
			var html="";
			for(var item in activeConnectionStackTraces){
				html+="<tr><td>"+activeConnectionStackTraces[item]+"</td></tr>";
			}
			$('#tblTrace tbody').html(html);
		})
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-body">
			<div class="panel-data">
				<h2>数据源-${datasourceId} 活动连栈信息跟踪</h2>
				<hr/>
				<div class="panel-detail">
				<table id="tblTrace" cellspacing="1" cellpadding="5" class="table-detail">
					<tbody>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>