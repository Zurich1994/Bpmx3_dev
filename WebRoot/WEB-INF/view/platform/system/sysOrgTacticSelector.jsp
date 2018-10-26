
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择模版</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">

</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	body {overflow: hidden;}
</style>
</head>
<body>
	<div class="panel">
		<div class="panel-search" moreHeight="30">
				<form action="selector.ht?" id="searchForm" method="POST" target="orgFrame">
					<div class="row">
						<input type="hidden" name="isSingle" value="${isSingle }">
						<span class="label">组织名:</span> 
						<input type="text" id="orgName" name="orgName" class="inputText" value="${param['orgName']}"/> &nbsp; 
						<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a>
					</div>
				</form>
	    </div>
	    <c:if test="${isSingle==false}">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall" name="chkall" />
			</c:set>
		</c:if>
		 <display:table name="sysOrgList" id="sysOrgItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
			<display:column title="${checkAll}" media="html" style="width:30px;">
				<c:choose>
					<c:when test="${isSingle==false}">
						<input type="checkbox" class="pk" name="orgId" value="${sysOrgItem.orgId}#${sysOrgItem.orgName}">
					</c:when>
					<c:otherwise>
						<input type="radio" class="pk" name="orgId" value="${sysOrgItem.orgId}#${sysOrgItem.orgName}">
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="orgName" value="${sysOrgItem.orgName}">
			</display:column>
			<display:column property="orgName" title="名称" sortable="true" sortName="orgName" ></display:column>
			<display:column property="orgPathname" title="所在路径"  sortable="true" sortName="orgPathname" maxLength="250" ></display:column>
		</display:table>
	</div>
</body>
</html>


