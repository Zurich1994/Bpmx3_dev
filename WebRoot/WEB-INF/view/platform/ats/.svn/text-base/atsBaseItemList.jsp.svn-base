<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>基础数据管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">基础数据管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
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
							<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/>
						</li>
						<li>
							<span class="label">是否系统预置:</span>
							<select name="Q_isSys_S" value="${param['Q_isSys_S']}">
								<option value="">请选择</option>
								<option value="1" <c:if test="${param['Q_isSys_S'] == '1'}">selected</c:if>>是</option>	
								<option value="0" <c:if test="${param['Q_isSys_S'] == '0'}">selected</c:if>>否</option>
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
		    <display:table name="atsBaseItemList" id="atsBaseItemItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:20px;text-align:center" maxLength="20">
			  		<input type="checkbox" class="pk" name="id" value="${atsBaseItemItem.id}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80" style="width:80px;text-align:center"></display:column>
				<display:column  title="名称" sortable="true" sortName="NAME" maxLength="80" style="width:80px;text-align:center">
						<a href="${ctx}${atsBaseItemItem.url}" >${atsBaseItemItem.name}</a>
				</display:column>
				<display:column property="url" title="地址" sortable="true" sortName="URL" style="width:180px;"></display:column>
				<display:column title="是否系统预置" sortable="true" sortName="IS_SYS" style="width:80px;text-align:center;">
						<c:choose>
							<c:when test="${atsBaseItemItem.isSys==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="管理" media="html" style="width:80px;text-align:center" class="rowOps" >
					<c:if test="${atsBaseItemItem.isSys!=1}">
						<a href="del.ht?id=${atsBaseItemItem.id}" class="link del">删除</a>
						<a href="edit.ht?id=${atsBaseItemItem.id}" class="link edit">编辑</a>
					</c:if>
					<a href="${ctx}${atsBaseItemItem.url}" class="link detail">设置</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsBaseItemItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


