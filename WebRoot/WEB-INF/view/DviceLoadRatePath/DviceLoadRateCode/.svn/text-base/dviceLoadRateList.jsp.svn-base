<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>设备负载值及利用率表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备负载值及利用率表管理列表</span>
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
						<span class="label">节点设备id:</span><input type="text" name="Q_node_dev_id_L"  class="inputText" />
						<span class="label">时间:</span><input type="text" name="Q_active_start_L"  class="inputText" />
						<span class="label">负载类型:</span><input type="text" name="Q_load_type_S"  class="inputText" />
						<span class="label">负载值:</span><input type="text" name="Q_load_value_L"  class="inputText" />
						<span class="label">资源类型:</span><input type="text" name="Q_load_info_S"  class="inputText" />
						<span class="label">利用率:</span><input type="text" name="Q_load_use_rate_L"  class="inputText" />
						<span class="label">b:</span><input type="text" name="Q_b_S"  class="inputText" />
						<span class="label">c:</span><input type="text" name="Q_c_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="dviceLoadRateList" id="dviceLoadRateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${dviceLoadRateItem.id}">
				</display:column>
				<display:column property="node_dev_id" title="节点设备id" sortable="true" sortName="F_NODE_DEV_ID"></display:column>
				<display:column property="active_start" title="时间" sortable="true" sortName="F_ACTIVE_START"></display:column>
				<display:column property="load_type" title="负载类型" sortable="true" sortName="F_LOAD_TYPE"></display:column>
				<display:column property="load_value" title="负载值" sortable="true" sortName="F_LOAD_VALUE"></display:column>
				<display:column property="load_info" title="资源类型" sortable="true" sortName="F_LOAD_INFO"></display:column>
				<display:column property="load_use_rate" title="利用率" sortable="true" sortName="F_LOAD_USE_RATE"></display:column>
				<display:column property="a" title="a" sortable="true" sortName="F_A"></display:column>
				<display:column property="b" title="b" sortable="true" sortName="F_B"></display:column>
				<display:column property="c" title="c" sortable="true" sortName="F_C"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${dviceLoadRateItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${dviceLoadRateItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${dviceLoadRateItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="dviceLoadRateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


