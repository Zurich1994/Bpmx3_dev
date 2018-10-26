<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>flowWebService管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">flowWebService管理列表</span>
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
						<span class="label">serviceName:</span><input type="text" name="Q_serviceName_S"  class="inputText" />
						<span class="label">serviceUrl:</span><input type="text" name="Q_serviceUrl_S"  class="inputText" />
						<span class="label">serviceFuncName:</span><input type="text" name="Q_serviceFuncName_S"  class="inputText" />
						<span class="label">serviceType:</span><input type="text" name="Q_serviceType_S"  class="inputText" />
						<span class="label">serviceState:</span><input type="text" name="Q_serviceState_S"  class="inputText" />
					
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="flowwebserviceList" id="flowwebserviceItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${flowwebserviceItem.id}">
				</display:column>
				<display:column property="serviceName" title="服务名称" sortable="true" sortName="F_SERVICENAME"></display:column>
				<display:column property="serviceUrl" title="服务地址" sortable="true" sortName="F_SERVICEURL"></display:column>
				<display:column property="serviceFuncName" title="服务方法名" sortable="true" sortName="F_SERVICEFUNCNAME"></display:column>
				<display:column property="serviceType" title="服务分类" sortable="true" sortName="F_SERVICETYPE"></display:column>
				<display:column property="serviceState" title="服务状态" sortable="true" sortName="F_SERVICESTATE"></display:column>
		
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${flowwebserviceItem.id}" class="link del"><span></span>删除</a>
					<a href="edit2.ht?id=${flowwebserviceItem.id}&typeId=10000021910018" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${flowwebserviceItem.id}" class="link detail"><span></span>明细</a>
					<a href="${ctx}/platform/bpm/bpmDefinition/list_defid.ht?defId=${flowwebserviceItem.defid}" class="link detail" ><span></span>流程</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="flowwebserviceItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


