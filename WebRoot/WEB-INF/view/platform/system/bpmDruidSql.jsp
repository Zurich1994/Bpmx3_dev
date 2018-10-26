<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Druid SQL执行状态监控列表</title>
<%@include file="/commons/include/get.jsp"%>

<script type="text/javascript">
$(function(){
	$.confirm("#resetAll",'确定重置监控信息吗？');
});

function subSqlString(sql, len) {
	if (sql.length <= len)
		return sql;
	return sql.substr(0, len) + '...';
}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    SQL执行监控			
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<a class="link update" id="resetAll" href="resetAll.ht"><span></span>重置监控信息</a>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<table class="table-grid" id="tblSqlStat" cellpadding="1" cellspacing="1">
					<thead>
						<tr>
							<th><a id="SQL" href="${curUrl }&newSortField=SQL" >SQL</a></th>
							<th><a id="ExecuteCount" href="${curUrl }&newSortField=ExecuteCount">执行次数</a></th>
							<th><a id="TotalTime" href="${curUrl }&newSortField=TotalTime">总执行时间（毫秒）</a></th>
							<th><a id="AverageTime" href="${curUrl }&newSortField=AverageTime">平均执行时间（毫秒）</a></th>
							<th><a id="MaxTimespan" href="${curUrl }&newSortField=MaxTimespan">最大执行时间（毫秒）</a></th>
							<th><a id="InTransactionCount" href="${curUrl }&newSortField=InTransactionCount ">在事务中执行次数</a></th>
							<th><a id="ErrorCount" href="${curUrl }&newSortField=ErrorCount">错误次数</a></th>
							<th><a id="EffectedRowCount" href="${curUrl }&newSortField=EffectedRowCount">影响记录数</a></th>
							<th><a id="FetchRowCount" href="${curUrl }&newSortField=FetchRowCount">获取记录数</a></th>
							<th><a id="RunningCount" href="${curUrl }&newSortField=RunningCount">正在运行数</a></th>
							<th><a id="ConcurrentMax" href="${curUrl }&newSortField=ConcurrentMax">并发数</a></th>
							<th>管理</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sqlStatList}" var="sqlStat">
							<tr>
								<td title="${sqlStat['SQL']}">${fn:substring(sqlStat['SQL'],0,25)}${fn:length(sqlStat['SQL'])>35?"...":"" }</td>
								<td>${sqlStat['ExecuteCount'] }</td>
								<td>${sqlStat['TotalTime'] }</td>
								<td>${sqlStat['AverageTime'] }</td>
								<td>${sqlStat['MaxTimespan'] }</td>
								<td>${sqlStat['InTransactionCount'] }</td>
								<td>${sqlStat['ErrorCount'] }</td>
								<td>${sqlStat['EffectedRowCount'] }</td>
								<td>${sqlStat['FetchRowCount'] }</td>
								<td>${sqlStat['RunningCount'] }</td>
								<td>${sqlStat['ConcurrentMax'] }</td>
								<td><a class="link detail" href="sqlDetail.ht?id=${sqlStat['ID']}">明细</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- end of panel-body -->
	<!-- end of panel -->
</body>
</html>


