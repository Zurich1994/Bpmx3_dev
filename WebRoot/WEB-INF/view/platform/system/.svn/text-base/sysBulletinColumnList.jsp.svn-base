<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>公告栏目管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<!-- <div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div> -->
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">栏目名:</span><input type="text" name="Q_name_SL"  value="${param['Q_name_SL']}" class="inputText" />
						<span class="label">别名:</span><input type="text" name="Q_alias_SL"  value="${param['Q_alias_SL']}" class="inputText" />
						<span class="label">创建人:</span><input type="text" name="Q_creator_SL" value="${param['Q_creator_SL']}" class="inputText" />
						<span class="label">创建时间 从:</span> <input  name="Q_begincreatetime_DL"  value="${param['Q_begincreatetime_DL']}" class="inputText date" />
						<span class="label">至: </span><input  name="Q_endcreatetime_DG" value="${param['Q_endcreatetime_DG']}" class="inputText date" />
						<span class="label">是否可用</span>
						  <select name="Q_status_SL">
						    <option value="1" <c:if test="${param['Q_status_SL']=='1'}">selected="selected"</c:if>>可用</option>
						    <option value="0" <c:if test="${param['Q_status_SL']=='0'}">selected="selected"</c:if>>禁用</option>
						  </select>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysBulletinColumnList" id="sysBulletinColumnList" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysBulletinColumnList.id}">
				</display:column>
				<display:column property="name" title="栏目名" sortable="true" sortName="NAME"></display:column>
				<display:column property="alias" title="别名" sortable="true" sortName="ALIAS"></display:column>
				<display:column title="所属分公司" sortable="true" sortName="TENANTID">
				 	<c:choose>
					    <c:when test="${empty sysBulletinColumnList.tenantname}">
					       	 所有公司
					    </c:when>
					    <c:otherwise>
					        ${sysBulletinColumnList.tenantname}
					    </c:otherwise>			   
			     	</c:choose>
				</display:column>
				<display:column property="creator" title="创建人" sortable="true" sortName="CREATOR"></display:column>
				<display:column  title="创建时间" sortable="true" sortName="CREATETIME">
					<fmt:formatDate value="${sysBulletinColumnList.createtime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  title="是否可用" sortable="true" sortName="STATUS">
				   <c:if test="${sysBulletinColumnList.status==1}">可用</c:if>
				   <c:if test="${sysBulletinColumnList.status==0}">禁用</c:if>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
					<c:if test="${sysBulletinColumnList.tenantid ne 0  or  isSuperAdmin eq true}">
						<a href="del.ht?id=${sysBulletinColumnList.id}" class="link del"><span></span>删除</a>
						<a href="edit.ht?id=${sysBulletinColumnList.id}&ispublic=${ispublic}" class="link edit"><span></span>编辑</a>
					</c:if>
					<a href="get.ht?id=${sysBulletinColumnList.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysBulletinColumnList"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>
