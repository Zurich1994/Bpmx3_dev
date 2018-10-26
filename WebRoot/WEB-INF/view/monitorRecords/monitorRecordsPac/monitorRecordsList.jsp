<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>监控记录表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">监控记录表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link pic" href="${ctx}/schedule/schedulePac/schedule/showAllMonitorAdd.ht"><span></span>设置所有监控</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link mon" href="${ctx}/schedule/schedulePac/schedule/delAllMon.ht"><span></span>删除所有监控</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link mon" href="${ctx}/schedule/schedulePac/schedule/pauseAllMon.ht"><span></span>暂停所有监控</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link pic" href="${ctx}/schedule/schedulePac/schedule/activeAllMon.ht"><span></span>激活所有监控</a></div>
					<c:if test="${monitorType==1}">
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link pic"  href="listByDeviceQuotaRel.ht?deviceQuotaRelId=${deviceQuotaRelId}"><span></span>图形化</a></div>
					</c:if>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht?deviceQuotaRelId=${deviceQuotaRelId}">
					<div class="row">
						<span class="label">指标监控值从:</span><input type="text" name="Q_beginmonitorValue_S"  class="inputText" />
						<span class="label">至: </span><input  name="Q_endmonitorValue_S" class="inputText" />
						<span class="label">监控时间 从:</span> <input  name="Q_beginmonitorTime_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endmonitorTime_DG" class="inputText date" />
						<span class="label">监控指标过滤:</span><input type="text" name="Q_monitorValue_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="monitorRecordsList" id="monitorRecordsItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${monitorRecordsItem.id}">
				</display:column>
				<display:column property="device_name" title="主机名称" sortable="true" sortName="F_DEVICE_ID"></display:column>
				<display:column property="quota_name" title="指标名称" sortable="true" sortName="F_QUOTA_ID"></display:column>
				<display:column property="monitorValue" title="指标监控值" sortable="true" sortName="F_MONITORVALUE"></display:column>
				<display:column  title="监控时间" sortable="true" sortName="F_MONITORTIME">
					<fmt:formatDate value="${monitorRecordsItem.monitorTime}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
				</display:column>
				</display:table>
			<hotent:paging tableId="monitorRecordsItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


