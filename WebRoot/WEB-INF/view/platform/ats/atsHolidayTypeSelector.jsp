
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择假期类型</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	$(function(){
		$("tr.odd,tr.even").unbind("hover");
		$("tr.odd,tr.even").click(function(){
			$(this).siblings().removeClass("over").end().addClass("over");
		});
	}); 
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	body {overflow: hidden;}
</style>
</head>
<body>

	<div  class="panel-top">
		<div class="panel-search">
			<form id="searchForm" method="post" action="${ctx}/platform/ats/atsHolidayType/selector.ht" >
				<ul class="row">
					<input type="hidden" name="isSingle" value="${isSingle }">
					<li><span class="label" >名称:</span><input size="14" type="text" name="Q_name_SL"  class="inputText" style="width:60%;" value="${param['Q_name_SL']}"/>
					&nbsp;<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a></li>
				</ul>
			</form>
		</div>
	</div>
	<display:table name="atsHolidayTypeList" id="atsHolidayTypeItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="选择" media="html" style="width:30px;">
					<input type="radio" class="pk" name="data" value="${atsHolidayTypeItem.id}#${atsHolidayTypeItem.name}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>

	</display:table>
	<hotent:paging tableId="atsHolidayTypeItem" showExplain="false"/>
</body>
</html>


