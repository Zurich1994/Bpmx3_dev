<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>网络配置表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">网络配置表管理列表</span>
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
						<span class="label">设备编号:</span><input type="text" name="Q_Server_ID_S"  class="inputText" />
						<span class="label">网卡IP:</span><input type="text" name="Q_NIC_IP_S"  class="inputText" />
						<span class="label">网卡序号:</span><input type="text" name="Q_NIC_Net_sequence_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="nicList" id="nicItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${nicItem.id}">
				</display:column>
				<display:column property="NIC_ID" title="网卡编号" sortable="true" sortName="F_NIC_ID"></display:column>
				<display:column property="Server_ID" title="设备编号" sortable="true" sortName="F_SERVER_ID"></display:column>
				<display:column property="NIC_IP" title="网卡IP" sortable="true" sortName="F_NIC_IP"></display:column>
				<display:column property="NIC_subnetMask" title="网卡子网掩码" sortable="true" sortName="F_NIC_SUBNETMASK"></display:column>
				<display:column property="NIC_Default_gateway" title="网卡默认网关" sortable="true" sortName="F_NIC_DEFAULT_GATEWAY"></display:column>
				<display:column property="NIC_MAC_address" title="网卡MAC地址" sortable="true" sortName="F_NIC_MAC_ADDRESS"></display:column>
				<display:column property="NIC_Net_sequence" title="网卡序号" sortable="true" sortName="F_NIC_NET_SEQUENCE"></display:column>
				<display:column property="Remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${nicItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${nicItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${nicItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="nicItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


