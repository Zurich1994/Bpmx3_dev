<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>服务器配置表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">服务器配置表管理列表</span>
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
						<span class="label">服务器类型:</span><input type="text" name="Q_server_type_S"  class="inputText" />
						<span class="label">操作系统:</span><input type="text" name="Q_os_name_S"  class="inputText" />
						<span class="label">管理IP:</span><input type="text" name="Q_manage_IP_S"  class="inputText" />
						<span class="label">业务IP:</span><input type="text" name="Q_bussiness_IP_S"  class="inputText" />
						<span class="label">业务类型:</span><input type="text" name="Q_type_S"  class="inputText" />
						<br/>
						<span class="label">城市编号:</span><input type="text" name="Q_city_id_S"  class="inputText" />
						<span class="label">功能名称:</span><input type="text" name="Q_function_name_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deviceServerList" id="deviceServerItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deviceServerItem.id}">
				</display:column>
				<display:column property="dev_ID" title="设备编号" sortable="true" sortName="F_DEV_ID"></display:column>
				<display:column property="server_type" title="服务器类型" sortable="true" sortName="F_SERVER_TYPE"></display:column>
				<display:column property="os_name" title="操作系统" sortable="true" sortName="F_OS_NAME"></display:column>
				<display:column property="manage_IP" title="管理IP" sortable="true" sortName="F_MANAGE_IP"></display:column>
				<display:column property="manage_NIC_subnetMas" title="管理IP网卡子网掩码" sortable="true" sortName="F_MANAGE_NIC_SUBNETMAS"></display:column>
				<display:column property="manage_NIC_Default_g" title="管理IP网卡默认网关" sortable="true" sortName="F_MANAGE_NIC_DEFAULT_G"></display:column>
				<display:column property="manage_NIC_MAC_addre" title="管理IP网卡MAC地址" sortable="true" sortName="F_MANAGE_NIC_MAC_ADDRE"></display:column>
				<display:column property="bussiness_IP" title="业务IP" sortable="true" sortName="F_BUSSINESS_IP"></display:column>
				<display:column property="bussiness_NIC_subnet" title="业务IP网卡子网掩码" sortable="true" sortName="F_BUSSINESS_NIC_SUBNET"></display:column>
				<display:column property="bussiness_NIC_Defaul" title="业务IP网卡默认网关" sortable="true" sortName="F_BUSSINESS_NIC_DEFAUL"></display:column>
				<display:column property="bussiness_NIC_MAC_ad" title="业务IP网卡MAC地址" sortable="true" sortName="F_BUSSINESS_NIC_MAC_AD"></display:column>
				<display:column property="type" title="业务类型" sortable="true" sortName="F_TYPE"></display:column>
				<display:column property="city_id" title="城市编号" sortable="true" sortName="F_CITY_ID"></display:column>
				<display:column property="function_name" title="功能名称" sortable="true" sortName="F_FUNCTION_NAME"></display:column>
				<display:column property="remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column property="actdefid" title="流程定义ID" sortable="true" sortName="F_ACTDEFID"></display:column>
				<display:column property="nodeid" title="流程节点ID" sortable="true" sortName="F_NODEID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deviceServerItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deviceServerItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deviceServerItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deviceServerItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


