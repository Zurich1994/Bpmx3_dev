<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title>条件脚本选择器</title>
</head>
<body>
	<div position="center" id="framecenter"> 
        <div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">条件脚本选择器</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch">查询</a></div>
					<div class="l-bar-separator"></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="selector.ht">
					<div class="row">
						<span class="label">脚本所在类的类名:</span><input type="text" name="Q_className_SL"  class="inputText" value="${param['Q_className_SL']}"/>
						<span class="label">方法名:</span><input type="text" name="Q_methodName_SL"  class="inputText" value="${param['Q_methodName_SL']}"/>
						<span class="label">方法描述:</span><input type="text" name="Q_methodDesc_SL"  class="inputText" value="${param['Q_methodDesc_SL']}"/>
						<span class="label">是否有效:</span>
						<select name="Q_enable_L" class="select" style="width:60px;" value="${param['Q_enable_L']}">
							<option value="">全部</option>
							<option value="0" <c:if test="${param['Q_enable_L'] == 0}">selected</c:if>>是</option>
							<option value="1" <c:if test="${param['Q_enable_L'] == 1}">selected</c:if>>否</option>
						</select>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="conditionScriptList" id="conditionScriptItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="radio"" class="pk" name="id" value="${conditionScriptItem.id}" methodDesc="${conditionScriptItem.methodDesc}">
				</display:column>
				<display:column property="methodName" titleKey="方法名" sortable="true"  sortName="METHOD_NAME"></display:column>
				<display:column property="methodDesc" titleKey="方法描述" maxLength="80"></display:column>
				<display:column property="className" titleKey="脚本所在类的类名" sortable="true" sortName="CLASS_NAME" maxLength="80"></display:column>
				<display:column property="classInsName" titleKey="类实例名" sortable="true" sortName="CLASS_INS_NAME"></display:column>
				<display:column titleKey="是否有效" sortable="true" sortName="enable">
					<c:choose>
						<c:when test="${conditionScriptItem.enable eq 0}"><span class="green">是</span></c:when>
						<c:when test="${conditionScriptItem.enable eq 1}"><span class="red">否</span></c:when>
					</c:choose>
				</display:column>
			</display:table>
			<sf:paging tableId="conditionScriptItem"/>
		</div>
	</div>
</body>
</html>


