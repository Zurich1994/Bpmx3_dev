<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>公告表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span>查询</a>
					</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link add" href="edit.ht"><span></span>添加</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link del" action="del.ht"><span></span>删除</a>
						</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">主题:</span><input type="text" name="Q_subject_SL" value="${param['Q_subject_SL']}" class="inputText" />
						<span class="label">栏目:</span>
						<select  name="Q_column_S"> 
						    <option value="" <c:if test="${param['Q_columnid_S']==''}">selected="selected"</c:if>>所有栏目</option>
						    <c:forEach items="${columnList}" var="column">
						        <option value="${column.id}" <c:if test="${column.id==param['Q_columnid_S']}">selected="selected"</c:if>>${column.name}</option>
						    </c:forEach>
						</select>
						<span class="label">创建人:</span><input type="text" name="Q_creator_SL"  value="${param['Q_creator_SL']}" class="inputText" />
						<span class="label">创建时间从:</span> <input  name="Q_begincreatetime_DL" value="${param['Q_begincreatetime_DL']}"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endcreatetime_DG" value="${param['Q_endcreatetime_DG']}"  class="inputText date" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysBulletinList" id="sysBulletinItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysBulletinItem.id}">
				</display:column>
				<display:column property="subject" title="主题" sortable="true" sortName="SUBJECT"></display:column>
				<display:column property="columnname" title="栏目" sortable="true" sortName="columnname"></display:column>
				<display:column property="creator" title="创建人" sortable="true" sortName="CREATOR"></display:column>
				<display:column  title="创建时间" sortable="true" sortName="CREATETIME">
					<fmt:formatDate value="${sysBulletinItem.createtime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
					<c:if test="${sysBulletinColumnList.tenantid ne 0  or  isSuperAdmin eq true}">
						<a href="del.ht?id=${sysBulletinItem.id}" class="link del"><span></span>删除</a>
						<a href="edit.ht?id=${sysBulletinItem.id}&ispublic=${ispublic}" class="link edit"><span></span>编辑</a>
					</c:if>
					<a href="get.ht?id=${sysBulletinItem.id}" class="link detail"><span></span>查看</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysBulletinItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


