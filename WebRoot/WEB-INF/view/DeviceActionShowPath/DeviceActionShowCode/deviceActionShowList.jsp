<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>设备动作展现表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备动作展现表管理列表</span>
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
						<span class="label">节点设备id:</span><input type="text" name="Q_node_dev_id_S"  class="inputText" />
						<span class="label">发起时间:</span><input type="text" name="Q_start_time_S"  class="inputText" />
						<span class="label">动作名称:</span><input type="text" name="Q_actionname_S"  class="inputText" />
						<span class="label">触发动作间隔时间:</span><input type="text" name="Q_actiontime_S"  class="inputText" />
						<span class="label">动作循环次数:</span><input type="text" name="Q_actioncount_S"  class="inputText" />
						<span class="label">负载类型:</span><input type="text" name="Q_lode_type_S"  class="inputText" />
						<span class="label">利用率值:</span><input type="text" name="Q_load_use_rate_L"  class="inputText" />
						<span class="label">设备类型:</span><input type="text" name="Q_dev_type_code_S"  class="inputText" />
						<span class="label">重复次数:</span><input type="text" name="Q_sameaction_sign_S"  class="inputText" />
						<span class="label">节点颜色:</span><input type="text" name="Q_node_color_S"  class="inputText" />
						<span class="label">节点动画:</span><input type="text" name="Q_node_flash_S"  class="inputText" />
						<span class="label">a:</span><input type="text" name="Q_a_S"  class="inputText" />
						<span class="label">b:</span><input type="text" name="Q_b_S"  class="inputText" />
						<span class="label">c:</span><input type="text" name="Q_c_S"  class="inputText" />
						<span class="label">d:</span><input type="text" name="Q_d_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deviceActionShowList" id="deviceActionShowItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deviceActionShowItem.id}">
				</display:column>
				<display:column property="node_dev_id" title="节点设备id" sortable="true" sortName="F_NODE_DEV_ID"></display:column>
				<display:column property="start_time" title="发起时间" sortable="true" sortName="F_START_TIME"></display:column>
				<display:column property="actionname" title="动作名称" sortable="true" sortName="F_ACTIONNAME"></display:column>
				<display:column property="actiontime" title="触发动作间隔时间" sortable="true" sortName="F_ACTIONTIME"></display:column>
				<display:column property="actioncount" title="动作循环次数" sortable="true" sortName="F_ACTIONCOUNT"></display:column>
				<display:column property="lode_type" title="负载类型" sortable="true" sortName="F_LODE_TYPE"></display:column>
				<display:column property="load_use_rate" title="利用率值" sortable="true" sortName="F_LOAD_USE_RATE"></display:column>
				<display:column property="dev_type_code" title="设备类型" sortable="true" sortName="F_DEV_TYPE_CODE"></display:column>
				<display:column property="sameaction_sign" title="重复次数" sortable="true" sortName="F_SAMEACTION_SIGN"></display:column>
				<display:column property="sameaction_sign1" title="重复次数1" sortable="true" sortName="F_SAMEACTION_SIGN1"></display:column>
				<display:column property="node_color" title="节点颜色" sortable="true" sortName="F_NODE_COLOR"></display:column>
				<display:column property="node_flash" title="节点动画" sortable="true" sortName="F_NODE_FLASH"></display:column>
				<display:column property="a" title="a" sortable="true" sortName="F_A"></display:column>
				<display:column property="b" title="b" sortable="true" sortName="F_B"></display:column>
				<display:column property="c" title="c" sortable="true" sortName="F_C"></display:column>
				<display:column property="d" title="d" sortable="true" sortName="F_D"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deviceActionShowItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deviceActionShowItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deviceActionShowItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deviceActionShowItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


