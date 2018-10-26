<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>线路动作展现表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">线路动作展现表管理列表</span>
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
						<span class="label">线路id:</span><input type="text" name="Q_line_id_L"  class="inputText" />
						<span class="label">时间:</span><input type="text" name="Q_start_time_L"  class="inputText" />
						<span class="label">动作名称:</span><input type="text" name="Q_actionname_S"  class="inputText" />
						<span class="label">触发动作间隔时间:</span><input type="text" name="Q_actiontime_S"  class="inputText" />
						<span class="label">动作循环次数:</span><input type="text" name="Q_actioncount_S"  class="inputText" />
						<span class="label">重复次数:</span><input type="text" name="Q_sameaction_sign_S"  class="inputText" />
						<span class="label">重复次数1:</span><input type="text" name="Q_sameaction_sign1_S"  class="inputText" />
						<span class="label">线路颜色:</span><input type="text" name="Q_line_color_S"  class="inputText" />
						<span class="label">线路颜色1:</span><input type="text" name="Q_line_color1_S"  class="inputText" />
						<span class="label">传输信息:</span><input type="text" name="Q_line_info_S"  class="inputText" />
						<span class="label">a:</span><input type="text" name="Q_a_S"  class="inputText" />
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
		    <display:table name="lineActionShowList" id="lineActionShowItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${lineActionShowItem.id}">
				</display:column>
				<display:column property="line_id" title="线路id" sortable="true" sortName="F_LINE_ID"></display:column>
				<display:column property="start_time" title="时间" sortable="true" sortName="F_START_TIME"></display:column>
				<display:column property="actionname" title="动作名称" sortable="true" sortName="F_ACTIONNAME"></display:column>
				<display:column property="actiontime" title="触发动作间隔时间" sortable="true" sortName="F_ACTIONTIME"></display:column>
				<display:column property="actioncount" title="动作循环次数" sortable="true" sortName="F_ACTIONCOUNT"></display:column>
				<display:column property="sameaction_sign" title="重复次数" sortable="true" sortName="F_SAMEACTION_SIGN"></display:column>
				<display:column property="sameaction_sign1" title="重复次数1" sortable="true" sortName="F_SAMEACTION_SIGN1"></display:column>
				<display:column property="line_color" title="线路颜色" sortable="true" sortName="F_LINE_COLOR"></display:column>
				<display:column property="line_color1" title="线路颜色1" sortable="true" sortName="F_LINE_COLOR1"></display:column>
				<display:column property="line_info" title="传输信息" sortable="true" sortName="F_LINE_INFO"></display:column>
				<display:column property="a" title="a" sortable="true" sortName="F_A"></display:column>
				<display:column property="b" title="b" sortable="true" sortName="F_B"></display:column>
				<display:column property="c" title="c" sortable="true" sortName="F_C"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${lineActionShowItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${lineActionShowItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${lineActionShowItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="lineActionShowItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


