<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>支付者信息表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">支付者信息表管理列表</span>
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
						<span class="label">用户名:</span><input type="text" name="Q_USERID_S"  class="inputText" />
						<span class="label">别名:</span><input type="text" name="Q_PAYEEID_S"  class="inputText" />
						<span class="label">名字:</span><input type="text" name="Q_NAME_S"  class="inputText" />
						<span class="label">街道:</span><input type="text" name="Q_STREET_S"  class="inputText" />
						<span class="label">城市:</span><input type="text" name="Q_CITY_S"  class="inputText" />
						<span class="label">州:</span><input type="text" name="Q_STATE_S"  class="inputText" />
						<span class="label">区号:</span><input type="text" name="Q_ZIP_S"  class="inputText" />
						<span class="label">电话:</span><input type="text" name="Q_PHONE_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="payeeList" id="payeeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${payeeItem.id}">
				</display:column>
				<display:column property="USERID" title="用户名" sortable="true" sortName="F_USERID"></display:column>
				<display:column property="PAYEEID" title="别名" sortable="true" sortName="F_PAYEEID"></display:column>
				<display:column property="NAME" title="名字" sortable="true" sortName="F_NAME"></display:column>
				<display:column property="STREET" title="街道" sortable="true" sortName="F_STREET"></display:column>
				<display:column property="CITY" title="城市" sortable="true" sortName="F_CITY"></display:column>
				<display:column property="STATE" title="州" sortable="true" sortName="F_STATE"></display:column>
				<display:column property="ZIP" title="区号" sortable="true" sortName="F_ZIP"></display:column>
				<display:column property="PHONE" title="电话" sortable="true" sortName="F_PHONE"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${payeeItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${payeeItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${payeeItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="payeeItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


