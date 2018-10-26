<%--
	time:2011-12-05 17:00:54
--%>
<%@page language="java" pageEncoding="UTF-8" import="com.hotent.platform.model.system.Resources"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>子系统资源明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
	<c:if test="${resources!=null}">
		<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">${resources.resName}</span>
				</div>
				
			</div>
			<div class="panel-body">
				
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">资源名称:</th>
								<td>${resources.resName}</td>
							</tr>
							<tr>
								<th width="20%">资源别名:</th>
								<td>${resources.alias}</td>
							</tr>
							
							<tr>
								<th width="20%">资源图标:</th>
								<td>
									<img alt="" src="${resources.icon}">
								</td>
							</tr>
							<tr>
								<th width="20%">默认地址:</th>
								<td>${resources.defaultUrl}</td>
							</tr>
							<tr>
								<th width="20%">是否有子节点:</th>
								<td>
									<c:if test="${resources.isFolder==0}">否</c:if>
									<c:if test="${resources.isFolder==1}">是</c:if>
								</td>
							</tr>
							<tr>
								<th width="20%">显示到菜单:</th>
								<td>
									<c:if test="${resources.isDisplayInMenu==0}">否</c:if>
									<c:if test="${resources.isDisplayInMenu==1}">是</c:if>
								</td>
							</tr>
							<tr>
								<th width="20%">默认打开:</th>
								<td>
									<c:if test="${resources.isOpen==0}">否</c:if>
									<c:if test="${resources.isOpen==1}">是</c:if>
								</td>
							</tr>
							<tr>
								<th width="20%">是否打开新窗口:</th>
								<td>
									<c:if test="${resources.isNewOpen==0}">否</c:if>
									<c:if test="${resources.isNewOpen==1}">是</c:if>
								</td>
							</tr>
							<tr>
								<th width="20%">同层顺序:</th>
								<td>
									${resources.sn}
								</td>
							</tr>
					</table>
				
			</div>
			<c:if test="${resources.isFolder==0}">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">资源URL</span>
					</div>
				</div>
				<div class="panel-body">
					
						<table width="100%" id="resourcesUrlItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
					   		<tr>
					   			<th width="25%">名称</th>
					    		<th width="35%">URL</th>
					    		<th width="10%">默认URL</th>
					    	</tr>
					    	<tbody>
					    	<c:forEach items="${resourcesUrlList}" var="resourcesUrlItem" varStatus="status">
					    		<tr class="${status.index%2==0?'even':'odd'}">
					    			<td style="text-align:left">
					    				${resourcesUrlItem.name}
					    			</td>
					    			<td style="text-align:left">
					    				${resourcesUrlItem.url}
					    			</td>
					    			<td>
					    				<c:if test="${resourcesUrlItem.url==resources.defaultUrl}">是</c:if>
					    				<c:if test="${resourcesUrlItem.url!=resources.defaultUrl}">否</c:if>
					    			</td>
					    		</tr>
					    	</c:forEach>
					    	
					    	</tbody>
						</table>
						<c:if test="${resourcesUrlList=='[]'}">
					   	 	<div  width="90%">
						    	当前没有设置资源URL
						    </div>
						</c:if>
				</div>
			</c:if>
		</div>
	</c:if>
</body>
</html>
