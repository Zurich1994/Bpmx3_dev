
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择日历模版</title>
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
			<form id="searchForm" method="post" action="${ctx}/platform/ats/atsAttenceCycle/selector.ht" >
				<ul class="row">
					<input type="hidden" name="isSingle" value="${isSingle }">
					<li><span class="label" >名称:</span><input size="14" type="text" name="Q_name_SL"  class="inputText" style="width:60%;" value="${param['Q_name_SL']}"/>
					&nbsp;<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a></li>
				</ul>
			</form>
		</div>
	</div>
	<display:table name="atsAttenceCycleList" id="atsAttenceCycleItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="选择" media="html" style="width:30px;">
					<input type="radio" class="pk" name="data" value="${atsAttenceCycleItem.id}#${atsAttenceCycleItem.name}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column  title="周期类型" sortable="true" sortName="TYPE" maxLength="80">
						<c:choose>
							<c:when test="${atsAttenceCycleItem.type==1}">
								<span class="red">自然月</span>
							</c:when>
							<c:otherwise>
								<span class="green">月(固定日期)</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="开始周期" sortable="true" sortName="YEAR">
						${atsAttenceCycleItem.year }年${atsAttenceCycleItem.month}月
				</display:column>
				<display:column  title="周期开始日期" sortable="true" sortName="START_DAY">
						<c:choose>
							<c:when test="${atsAttenceCycleItem.type==1}">
								 1
							</c:when>
							<c:otherwise>
								${atsAttenceCycleItem.startDay}
							</c:otherwise>
						</c:choose>
				</display:column>
				
				<display:column  title="是否默认" sortable="true" sortName="IS_DEFAULT">
								<c:choose>
							<c:when test="${atsAttenceCycleItem.isDefault==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
	</display:table>
	<hotent:paging tableId="atsAttenceCycleItem" showExplain="false"/>
</body>
</html>


