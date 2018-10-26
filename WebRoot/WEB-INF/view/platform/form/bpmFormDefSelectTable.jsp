
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择表</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx }/js/ligerui/plugins/ligerWindow.js" ></script>
<script type="text/javascript">

</script>
<style type="text/css">
	body{overflow: hidden;}
</style>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">请选择一个表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
				</div>	
			</div>
		</div>
		</div>
		<div class="panel-body">
			<div class="panel-search">
				<form id="searchForm" method="post" action="">
					<ul class="row">
						<li><span class="label">表名:</span><input type="text" name="Q_tableName_S"  class="inputText" value="${param['Q_tableName_S']}"/></li>
						<li><span class="label">描述:</span><input type="text" name="Q_tableDesc_S"  class="inputText" value="${param['Q_tableDesc_S']}"/></li>
					</ul>
				</form>
			</div>
			<br/>
			<div class="panel-data">
			    <display:table name="bpmFormTableList" id="bpmFormTableItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column property="tableName" title="表名"  style="text-align:left"></display:column>
					<display:column property="tableDesc" title="描述"  style="text-align:left"></display:column>
					<display:column title="选择">
						<a href="edit.ht?tableId=${bpmFormTableItem.tableId}" class="link get" target="_parent">选择</a>
					</display:column>
				</display:table>
				<hotent:paging tableId="bpmFormTableItem"/>
			</div>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


