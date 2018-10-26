<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择表单</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	$(function() {
		$('#bpmFormDefItem tr').click(function() {
			$(this).find(':radio').attr('checked', 'checked');
		});
	});
</script>
<style type="text/css">
	html { overflow-x: hidden; }

</style>
</head>
<body>
	<div class="panel">
		<div class="panel-body">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="${ctx}/platform/form/bpmFormDialog/selector.ht">
					<ul class="row">
							<li><span class="label">对话框名称:</span><input type="text" name="name"  class="inputText" /></li>
					</ul>
				</form>
			</div>
		    <display:table name="bpmFormDialogList" id="bpmFormDialogItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
				<display:column  media="html" style="width:30px;">
					  	<input type="radio" class="pk" name="id" value="${bpmFormDialogItem.id}">
					  	<input type="hidden" name="name" value="${bpmFormDialogItem.name}">
					  	<input type="hidden" name="alias" value="${bpmFormDialogItem.alias}">
				</display:column>
				<display:column property="name" title="自定义对话框" sortable="true" sortName="subject" style="text-align:left"></display:column>
				<display:column property="alias" title="别名" sortable="true" sortName="formDesc" style="text-align:left"></display:column>
			</display:table>
			<hotent:paging tableId="bpmFormDialogItem" showExplain="false"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


