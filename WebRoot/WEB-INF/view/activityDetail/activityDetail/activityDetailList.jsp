<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程右键信息表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程右键信息表管理列表</span>
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
						<span class="label">流程ID:</span><input type="text" name="Q_actDef_Id_S"  class="inputText" />
						<span class="label">活动ID:</span><input type="text" name="Q_activity_id_S"  class="inputText" />
						<span class="label">活动名称:</span><input type="text" name="Q_activity_name_S"  class="inputText" />
						<span class="label">节点编号:</span><input type="text" name="Q_activity_num_S"  class="inputText" />
						<span class="label">活动形态:</span><input type="text" name="Q_server_shape_S"  class="inputText" />
						<span class="label">活动方式:</span><input type="text" name="Q_server_way_S"  class="inputText" />
						<span class="label">活动类型:</span><input type="text" name="Q_server_class_S"  class="inputText" />
						<span class="label">活动来源:</span><input type="text" name="Q_server_source_S"  class="inputText" />
						<span class="label">活动性质:</span><input type="text" name="Q_server_nature_S"  class="inputText" />
						<span class="label">信息形态:</span><input type="text" name="Q_info_shape_S"  class="inputText" />
						<span class="label">信息标准化:</span><input type="text" name="Q_info_stand_S"  class="inputText" />
						<span class="label">信息类型:</span><input type="text" name="Q_info_type_S"  class="inputText" />
						<span class="label">活动类别:</span><input type="text" name="Q_server_type_S"  class="inputText" />
						<span class="label">活动成熟度:</span><input type="text" name="Q_op_comp_L"  class="inputText" />
						<span class="label">作业名:</span><input type="text" name="Q_work_name_S"  class="inputText" />
						<span class="label">信息量:</span><input type="text" name="Q_op_info_sum_L"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="activityDetailList" id="activityDetailItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${activityDetailItem.id}">
				</display:column>
				<display:column property="actDef_Id" title="流程ID" sortable="true" sortName="F_ACTDEF_ID"></display:column>
				<display:column property="activity_id" title="活动ID" sortable="true" sortName="F_ACTIVITY_ID"></display:column>
				<display:column property="activity_name" title="活动名称" sortable="true" sortName="F_ACTIVITY_NAME"></display:column>
				<display:column property="activity_num" title="节点编号" sortable="true" sortName="F_ACTIVITY_NUM"></display:column>
				<display:column property="server_shape" title="活动形态" sortable="true" sortName="F_SERVER_SHAPE"></display:column>
				<display:column property="server_way" title="活动方式" sortable="true" sortName="F_SERVER_WAY"></display:column>
				<display:column property="server_class" title="活动类型" sortable="true" sortName="F_SERVER_CLASS"></display:column>
				<display:column property="server_source" title="活动来源" sortable="true" sortName="F_SERVER_SOURCE"></display:column>
				<display:column property="server_nature" title="活动性质" sortable="true" sortName="F_SERVER_NATURE"></display:column>
				<display:column property="info_shape" title="信息形态" sortable="true" sortName="F_INFO_SHAPE"></display:column>
				<display:column property="info_stand" title="信息标准化" sortable="true" sortName="F_INFO_STAND"></display:column>
				<display:column property="info_type" title="信息类型" sortable="true" sortName="F_INFO_TYPE"></display:column>
				<display:column property="server_type" title="活动类别" sortable="true" sortName="F_SERVER_TYPE"></display:column>
				<display:column property="op_comp" title="活动成熟度" sortable="true" sortName="F_OP_COMP"></display:column>
				<display:column property="work_name" title="作业名" sortable="true" sortName="F_WORK_NAME"></display:column>
				<display:column property="op_info_sum" title="信息量" sortable="true" sortName="F_OP_INFO_SUM"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${activityDetailItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${activityDetailItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${activityDetailItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="activityDetailItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>

