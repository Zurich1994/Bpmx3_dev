<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Druid数据源连接信息</title>
<%@include file="/commons/include/get.jsp"%>

</head>
<body>
	<div class="panel">
	<div class="panel-top">
			<div class="tbar-title">
			    数据源-${datasourceId } 连接信息		
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="datasource.ht">返回</a></div>
				</div>
			</div>
			<div class="panel-data">
            <table cellspacing="1" cellpadding="5" class="table-detail">
				<thead>
					<tr>
						<th >ID</th>
						<th >使用次数</th>
						<th >最近活动时间</th>
						<th >连接时间</th>
						<th >Holdability</th>
						<th >事务隔离性</th>
						<th >自动提交事务</th>
						<th >只读性</th>
						<th >Pscache</th>
                    </tr>
                </thead> 
                <tbody>
                	<c:forEach items="${connectionInfos }" var="connectionInfo">
						<tr>
							<td>${connectionInfo['id']}</td>
							<td>${connectionInfo['useCount']}</td>
							<td>${connectionInfo['lastActiveTime']}</td>
							<td>${connectionInfo['connectTime']}</td>
							<td>${connectionInfo['holdability']}</td>
							<td>${connectionInfo['transactionIsolation']}</td>
							<td>${connectionInfo['autoCommit']}</td>
							<td>${connectionInfo['readoOnly']}</td>
							<td>
							
								<table class="table-detail">
									<thead>
										<tr>
											<th>SQL</th>
											<th>default Row Pretch</th>
											<th>row Prefetch</th>
											<th>hit Count</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${connectionInfo['pscache'] }" var="pscache">
											<tr>
												<td>${pscache['sql']}</td>
												<td>${pscache['defaultRowPretch']}</td>
												<td>${pscache['rowPrefetch']}</td>
												<td>${pscache['hitCount']}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</td>
						</tr>
                	</c:forEach>
                </tbody>
             </table>
			</div>
		</div>
	</div>
</body>
</html>