<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>条件脚本管理</title>
<style type="text/css">
	html,body{
		overflow:auto;
	}
</style>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">条件脚本列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>编辑</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
					<div class="l-bar-separator"></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
					<li>
						<span class="label">脚本所在类名:</span><input type="text" name="Q_className_SL"  class="inputText" value="${param['Q_className_SL']}"/>
					</li>
					<li>
						<span class="label">方法名:</span><input type="text" name="Q_methodName_SL"  class="inputText" value="${param['Q_methodName_SL']}"/>
					</li>
					<li>
						<span class="label">方法描述:</span><input type="text" name="Q_methodDesc_SL"  class="inputText" value="${param['Q_methodDesc_SL']}"/>
					</li>
					<li>
						<span class="label">是否有效:</span>
						<select name="Q_enable_L" class="select" style="width:60px;" value="${param['Q_enable_L']}">
							<option value="">请选择</option>
							<option value="0" <c:if test="${param['Q_enable_L'] == '0'}">selected</c:if>>是</option>
							<option value="1" <c:if test="${param['Q_enable_L'] == '1'}">selected</c:if>>否</option>
						</select>
					</li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="conditionScriptList" id="conditionScriptItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${conditionScriptItem.id}">
				</display:column>
				<display:column title="方法名称" sortable="true" sortName="METHOD_NAME">
					<a class="link" href="edit.ht?id=${conditionScriptItem.id}" style="color:blue;">${conditionScriptItem.methodName}</a> 
				</display:column>
				<display:column property="methodDesc" title="方法描述" maxLength="80"></display:column>
				<display:column property="className" title="脚本所在类的类名" sortable="true" sortName="CLASS_NAME" maxLength="80"></display:column>
				<display:column property="classInsName" title="类实例名" sortable="true" sortName="CLASS_INS_NAME"></display:column>
				<display:column title="是否有效" sortable="true" sortName="enable">
					<c:choose>
						<c:when test="${conditionScriptItem.enable eq 0}"><span class="green">是</span></c:when>
						<c:when test="${conditionScriptItem.enable eq 1}"><span class="red">否</span></c:when>
					</c:choose>
				</display:column>
			</display:table>
			<hotent:paging tableId="conditionScriptItem"/>
		</div>
	</div>
</body>
</html>


