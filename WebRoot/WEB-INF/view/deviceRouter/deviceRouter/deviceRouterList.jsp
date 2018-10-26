<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>路由器配置表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">路由器配置表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<!--<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					--><div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">管理IP:</span><input type="text" name="Q_manger_IP_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deviceRouterList" id="deviceRouterItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deviceRouterItem.id}">
				</display:column>
				<display:column property="dev_ID" title="设备编号" sortable="true" sortName="F_DEV_ID"></display:column>
				<display:column property="manger_IP" title="管理IP" sortable="true" sortName="F_MANAGE_IP"></display:column>
				<display:column property="manage_NIC_subnetMas" title="网卡子网掩码" sortable="true" sortName="F_MANAGE_NIC_SUBNETMAS"></display:column>
				<display:column property="manage_NIC_Default_g" title="网卡默认网关" sortable="true" sortName="F_MANAGE_NIC_DEFAULT_G"></display:column>
				<display:column property="manage_NIC_MAC_addre" title="网卡MAC地址" sortable="true" sortName="F_MANAGE_NIC_MAC_ADDRE"></display:column>
				<display:column property="config_info" title="配置信息" sortable="true" sortName="F_CONFIG_INFO"></display:column>
				<display:column property="remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column property="actdefid" title="流程定义ID" sortable="true" sortName="F_ACTDEFID"></display:column>
				<display:column property="nodeid" title="流程节点ID" sortable="true" sortName="F_NODEID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deviceRouterItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deviceRouterItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deviceRouterItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deviceRouterItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


