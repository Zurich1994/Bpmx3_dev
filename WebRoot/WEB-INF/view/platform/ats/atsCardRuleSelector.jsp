
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择取卡规则</title>
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
			<form id="searchForm" method="post" action="${ctx}/platform/ats/atsCardRule/selector.ht" >
				<ul class="row">
					<input type="hidden" name="isSingle" value="${isSingle }">
					<li><span class="label" >名称:</span><input size="14" type="text" name="Q_name_SL"  class="inputText" style="width:60%;" value="${param['Q_fullname_SL']}"/>
					&nbsp;<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a></li>
				</ul>
			</form>
		</div>
	</div>
	    <display:table name="atsCardRuleList" id="atsCardRuleItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
			<display:column title="选择" media="html" style="width:30px;">
				<input type="radio" class="pk" name="data" value="${atsCardRuleItem.id}#${atsCardRuleItem.name}#${atsCardRuleItem.segmentNum}">
			</display:column>
			<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
			<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
			<display:column title="段次" sortable="true" sortName="SEGMENT_NUM">
					<c:choose>
						<c:when test="${atsCardRuleItem.segmentNum==1}">
							一段
						</c:when>
						<c:when test="${atsCardRuleItem.segmentNum==2}">
							二段
						</c:when>
						<c:otherwise>
							三段
						</c:otherwise>
					</c:choose>
			</display:column>
			<display:column property="startNum" title="上班取卡提前(小时)" sortable="true" sortName="START_NUM"></display:column>
			<display:column property="endNum" title="下班取卡延后(小时)" sortable="true" sortName="END_NUM"></display:column>
			<display:column property="timeInterval" title="最短取卡间隔(分钟）" sortable="true" sortName="TIME_INTERVAL"></display:column>
			<display:column title="是否默认" sortable="true" sortName="IS_DEFAULT">
					<c:choose>
						<c:when test="${atsAttencePolicyItem.isDefault==1}">
							<span class="red">是</span>
						</c:when>
						<c:otherwise>
							<span class="green">否</span>
						</c:otherwise>
					</c:choose>
			</display:column>
	</display:table>
	<hotent:paging tableId="atsCardRuleItem" showExplain="false"/>
</body>
</html>


