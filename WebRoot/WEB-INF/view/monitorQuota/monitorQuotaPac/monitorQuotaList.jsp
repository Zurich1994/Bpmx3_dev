<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>设备监控指标表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备监控指标表管理列表</span>
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
						<span class="label">指标名称:</span><input type="text" name="Q_name_S"  class="inputText" />
					    <span class="label">指标单位:</span><input type="text" name="Q_unit_S"  class="inputText" />
						<span class="label">监控类型:</span><input type="text" name="Q_monitorType_S"  class="inputText" />
						<span class="label">对象标识符:</span><input type="text" name="Q_OID_S"  class="inputText" />
						<span class="label">监控类名:</span><input type="text" name="Q_className_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="monitorQuotaList" id="monitorQuotaItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${monitorQuotaItem.id}">
				</display:column>
				<display:column property="name" title="指标名称" sortable="true" sortName="F_NAME"></display:column>
				<display:column property="unit" title="指标单位" sortable="true" sortName="F_UNIT"></display:column>
				<display:column property="monitorType" title="监控类型" sortable="true" sortName="F_MONITORTYPE"></display:column>
				<display:column property="OID" title="对象标识符" sortable="true" sortName="F_OID"></display:column>
				<display:column property="className" title="监控类名" sortable="true" sortName="F_CLASSNAME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${monitorQuotaItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${monitorQuotaItem.id}" class="link edit"><span></span>编辑</a>
					
				</display:column>
			</display:table>
			<hotent:paging tableId="monitorQuotaItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


