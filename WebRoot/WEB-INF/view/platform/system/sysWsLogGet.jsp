
<%--
	time:2013-05-30 14:53:45
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>cxf调用日志明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">cxf调用日志详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			
			<tr>
				<th width="20%">创建时间:</th>
				<td><fmt:formatDate value="${sysWsLog.createtime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			</tr>
			
			<tr>
				<th width="20%">访问来源:</th>
				<td>${sysWsLog.sourceMsg}</td>
			</tr>
			
			<tr>
				<th width="20%">访问路径:</th>
				<td>${sysWsLog.clsName}</td>
			</tr>
			
			<tr>
				<th width="20%">传入参数:</th>
				<td>${sysWsLog.impDesc}</td>
			</tr>
			
			<tr>
				<th width="20%">返回值:</th>
				<td>${sysWsLog.expDesc}</td>
			</tr>
			
			<tr>
				<th width="20%">方法名称:</th>
				<td>${sysWsLog.callName}</td>
			</tr>
			
			<tr>
				<th width="20%">方法描述:</th>
				<td>${sysWsLog.callDesc}</td>
			</tr>
			
			<tr>
				<th width="20%">是否成功:</th>
				<td>
					<c:if test="${sysWsLog.isSuccess==1}">成功</c:if>
					<c:if test="${sysWsLog.isSuccess==0}">失败</c:if>
				</td>
			</tr>
			
			<tr>
				<th width="20%">错误详细描述:</th>
				<td>${sysWsLog.errDetail}</td>
			</tr>
			
		</table>
		</div>
		
	</div>
</body>
</html>

