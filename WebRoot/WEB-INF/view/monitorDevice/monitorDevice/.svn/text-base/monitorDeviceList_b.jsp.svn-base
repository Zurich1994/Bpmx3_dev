<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.core.web.util.RequestUtil"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>监控设备管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">监控设备管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
			<!--	<div class="group"><a class="link mon" id="btnUpd2" action="mon.ht"><span></span>获取监控</a></div>	-->
					<c:if test="${typeId!=0}">
					<div class="l-bar-separator"></div>
			   		<div class="group"><a class="link add" href="edit.ht?typeId=<%=RequestUtil.getLong(request, "typeId", 0)%>"><span></span>添加</a></div> 
			   		</c:if> 					
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<%
				//Long typeid=Long.parseLong(request.getParameter(typeid));
			 %>
			<div class="panel-search">
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="monitorDeviceList" id="monitorDeviceItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${monitorDeviceItem.id}">
				</display:column>
				<display:column property="name" title="设备名" sortable="true" sortName="F_NAME"></display:column>
				<display:column property="os_type" title="操作系统类型" sortable="true" sortName="F_OS_TYPE"></display:column>
				<display:column property="brand" title="品牌" sortable="true" sortName="F_BRAND"></display:column>
				<display:column property="ip" title="主机ip" sortable="true" sortName="F_IP"></display:column>
				<display:column property="port" title="SNMP端口号" sortable="true" sortName="F_PORT"></display:column>
				<display:column property="community"  title="团体号" sortable="true" sortName="F_COMMUNITY"></display:column>
				<display:column title="管理" media="html" style="width:300px">
					<a href="del.ht?id=${monitorDeviceItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${monitorDeviceItem.id}&typeId=<%=RequestUtil.getLong(request, "typeId", 0)%>" class="link edit"><span></span>编辑</a>
					<a href='${ctx}/deviceQuotaRel/deviceQuotaRelPac/deviceQuotaRel/listQuotaName.ht?id=${monitorDeviceItem.id}' class="link edit"><span>配置监控指标</span></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="monitorDeviceItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


