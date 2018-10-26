<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>线路表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">线路表管理列表</span>
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
						<span class="label">设备编号:</span><input type="text" name="Q_dev_ID_S"  class="inputText" />
						<span class="label">设备端口:</span><input type="text" name="Q_dev_Port_S"  class="inputText" />
						<span class="label">对端设备编号:</span><input type="text" name="Q_opp_ID_S"  class="inputText" />
						<span class="label">对端设备端口:</span><input type="text" name="Q_opp_Port_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="devicerelationshipList" id="devicerelationshipItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${devicerelationshipItem.id}">
				</display:column>
				<display:column property="dev_ID" title="设备编号" sortable="true" sortName="F_DEV_ID"></display:column>
				<display:column property="dev_Port" title="设备端口" sortable="true" sortName="F_DEV_PORT"></display:column>
				<display:column property="opp_ID" title="对端设备编号" sortable="true" sortName="F_OPP_ID"></display:column>
				<display:column property="opp_PortType" title="对端设备端口类型" sortable="true" sortName="F_OPP_PORTTYPE"></display:column>
				<display:column property="opp_Port" title="对端设备端口" sortable="true" sortName="F_OPP_PORT"></display:column>
				<display:column property="remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${devicerelationshipItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${devicerelationshipItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${devicerelationshipItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="devicerelationshipItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


