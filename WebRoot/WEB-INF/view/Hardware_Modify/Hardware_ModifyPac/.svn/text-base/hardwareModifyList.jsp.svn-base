<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>设备硬件更换记录表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备硬件更换记录表管理列表</span>
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
						<span class="label">设备编号:</span><input type="text" name="Q_Dev_ID_S"  class="inputText" />
						<span class="label">硬件名称:</span><input type="text" name="Q_Hardware_Name_S"  class="inputText" />
						<span class="label">变更状态:</span><input type="text" name="Q_Modify_State_S"  class="inputText" />
						<span class="label">记录人:</span><input type="text" name="Q_Operator_S"  class="inputText" />
						<span class="label">操作时间:</span><input type="text" name="Q_Operate_Time_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="hardwareModifyList" id="hardwareModifyItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${hardwareModifyItem.id}">
				</display:column>
				<display:column property="Dev_ID" title="设备编号" sortable="true" sortName="F_DEV_ID"></display:column>
				<display:column property="Hardware_Name" title="硬件名称" sortable="true" sortName="F_HARDWARE_NAME"></display:column>
				<display:column property="Modify_State" title="变更状态" sortable="true" sortName="F_MODIFY_STATE"></display:column>
				<display:column property="Operator" title="记录人" sortable="true" sortName="F_OPERATOR"></display:column>
				<display:column property="Operate_Time" title="操作时间" sortable="true" sortName="F_OPERATE_TIME"></display:column>
				<display:column property="Operate_Record" title="操作记录" sortable="true" sortName="F_OPERATE_RECORD"></display:column>
				<display:column property="Remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${hardwareModifyItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${hardwareModifyItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${hardwareModifyItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="hardwareModifyItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


