<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>设备指标关联表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备指标关联表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">设备编号:</span><input type="text" name="Q_device_id_S"  class="inputText" />
						<span class="label">指标编号:</span><input type="text" name="Q_quota_id_S"  class="inputText" />
						<span class="label">监控频率:</span><input type="text" name="Q_monitor_freq_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deviceQuotaRelList" id="deviceQuotaRelItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deviceQuotaRelItem.id}">
				</display:column>
				<display:column property="device_id" title="设备编号" sortable="true" sortName="F_DEVICE_ID"></display:column>
				<display:column property="quota_id" title="指标编号" sortable="true" sortName="F_QUOTA_ID"></display:column>
				<display:column property="monitor_freq" title="监控频率" sortable="true" sortName="F_MONITOR_FREQ"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deviceQuotaRelItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deviceQuotaRelItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deviceQuotaRelItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deviceQuotaRelItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


