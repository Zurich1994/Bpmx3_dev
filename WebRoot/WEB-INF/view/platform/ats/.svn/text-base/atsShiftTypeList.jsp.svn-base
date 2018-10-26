<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>班次类型管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title" style="display: block;">
				<span class="tbar-label">班次类型管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/platform/ats/atsBaseItem/list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li>
							<span class="label">编码:</span>
							<input type="text" name="Q_code_SL"  class="inputText" value="${param['Q_code_SL']}"/>
						</li>
						<li>
							<span class="label">名称:</span>
							<input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/>
						</li>
						<li>
							<span class="label">系统预置:</span>
							<select name="Q_isSys_S" value="${param['Q_isSys_S']}" class="inputText">
								<option value="">请选择</option>
								<option value="1" <c:if test="${param['Q_isSys_S'] == '1'}">selected</c:if>>是</option>	
								<option value="0" <c:if test="${param['Q_isSys_S'] == '0'}">selected</c:if>>否</option>
							</select>
						</li>
						<li>
							<span class="label">状态:</span>
							<select name="Q_status_S" value="${param['Q_status_S']}"  class="inputText">
								<option value="">请选择</option>
								<option value="1" <c:if test="${param['Q_status_S'] == '1'}">selected</c:if>>启用</option>	
								<option value="0" <c:if test="${param['Q_status_S'] == '0'}">selected</c:if>>禁用</option>
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
		    <display:table name="atsShiftTypeList" id="atsShiftTypeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsShiftTypeItem.id}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column title="是否系统预置" sortable="true" sortName="IS_SYS">
						<c:choose>
							<c:when test="${atsShiftTypeItem.isSys==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="状态" sortable="true" sortName="STATUS">
						<c:choose>
							<c:when test="${atsShiftTypeItem.status==1}">
								<span class="green">启用</span>
							</c:when>
							<c:otherwise>
								<span class="red">禁用</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="管理" media="html" class="rowOps">
					<c:if test="${atsShiftTypeItem.isSys!=1}">
						<a href="del.ht?id=${atsShiftTypeItem.id}" class="link del">删除</a>
						<a href="edit.ht?id=${atsShiftTypeItem.id}" class="link edit">编辑</a>
					</c:if>
					<a href="get.ht?id=${atsShiftTypeItem.id}" class="link detail">明细</a>
					<c:choose>
						<c:when test="${atsShiftTypeItem.status==1}">
							<a href="enable.ht?id=${atsShiftTypeItem.id}&enable=false" class="link lock" style="color:red;font-weight: bold;">禁用</a>
						</c:when>
						<c:otherwise>
							<a href="enable.ht?id=${atsShiftTypeItem.id}&enable=true" class="link lock" style="color:green;font-weight: bold;">启用</a>
						</c:otherwise>
					</c:choose>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsShiftTypeItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


