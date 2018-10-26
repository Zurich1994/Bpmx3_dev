<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
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
				<span class="tbar-label">WEB服务自定义查询管理列表</span>
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
					<div class="l-bar-separator"></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/>
						<span class="label">别名:</span>
						<select name="Q_serviceId_L" class="select" style="width:60px;">
							<option value="">请选择……</option>
							<c:forEach items="${setList}" var="set">
								<option value="${set.id}" <c:if test="${set.id == param['Q_serviceId_L']}">selected</c:if>>${set.alias}</option>
							</c:forEach>
						</select>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="wsDataTemplateList" id="wsDataTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${wsDataTemplateItem.id}">
				</display:column>
				<display:column property="name" titleKey="名称"></display:column>
				<display:column titleKey="别名" media="html">
					<c:forEach items="${setList}" var="set">
						<c:if test="${set.id == wsDataTemplateItem.serviceId}">${set.alias}</c:if>
					</c:forEach>
				</display:column>
				<display:column titleKey="管理" media="html" style="width:50px;text-align:center" class="rowOps">
					<f:a alias="delWsTemplateData" href="del.ht?id=${wsDataTemplateItem.id}" css="link del">删除</f:a>
					<a href="edit.ht?id=${wsDataTemplateItem.id}" class="link edit">编辑</a>				
					<a href="get.ht?id=${wsDataTemplateItem.id}" class="link detail">明细</a>				
					<a href="show_${wsDataTemplateItem.id}.ht" target="_balnk" class="link preview">预览</a>				
				</display:column>
			</display:table>
			<hotent:paging tableId="wsDataTemplateItem"/>
		</div>
	</div>
</body>
</html>