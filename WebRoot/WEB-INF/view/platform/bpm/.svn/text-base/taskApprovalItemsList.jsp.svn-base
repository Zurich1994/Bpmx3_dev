<%--
	time:2012-03-16 10:53:20
	desc:edit the 常用语管理
--%>
<%@page language="java" pageEncoding="UTF-8" import="java.util.*"%>
<%@include file="/commons/include/html_doctype.html"%>

<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<%@include file="/js/msg.jsp"%>
	<title>编辑 常用语</title>
	<script type="text/javascript" src="${ctx}/js/hotent/foldBox.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/displaytag.js" ></script>
	<script type="text/javascript">
	
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-toolbar">
		<div class="toolBar">
				<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>  
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link add" id="btnadd" href="edit.ht?isAdmin=${isAdmin}"><span></span>添加</a></div> 
				<div class="l-bar-separator"></div>
				<div class="group"><a href="javascript:;" class="link del"  action="del.ht" ><span></span>删除</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				
		</div>
	</div>
	<div class="panel-search">
		<form id="searchForm" method="post" action="list.ht">
		<input type="hidden" id="isAdmin" name="isAdmin" value="${isAdmin}">
			<div class="row">
				<span class="label">常用语:</span><input type="text" name="Q_expression_SL"  class="inputText" style="width:120px;" value="${param['Q_expression_SL']}"/>
				<c:if test="${currUserId==1}">
					<span class="label">流程分类:</span>
					<select name="Q_typeId_L" class="select" value="${param['Q_typeId_L']}">
							<option value="">全部</option>
							<c:forEach items="${globalTypeList}" var="item">
							<option value="${item.typeId}" <c:if test="${param['Q_typeId_L'] == item.typeId  }">selected</c:if> >${item.typeName}</option>
							</c:forEach>
					</select>
				</c:if>
			</div>
		</form>
	</div>
	<div class="panel-body">
		<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="taskApprovalItemsList" id="taskApprovalItems" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="itemId" value="${taskApprovalItems.itemId}">
				</display:column>
				<display:column  titleKey="常用语" sortable="true" sortName="expression">
					<font color="blue">${taskApprovalItems.expression}</font>
				</display:column>
				<display:column titleKey="作用范围" sortable="true" sortName="type">
					<c:choose>
						<c:when test="${taskApprovalItems.type==1}"><font color="Green">系统全局</font></c:when>
						<c:when test="${taskApprovalItems.type==2}">流程分类</c:when>
						<c:when test="${taskApprovalItems.type==3}">流程定义</c:when>
						<c:when test="${taskApprovalItems.type==4}">个人常用语</c:when>
					</c:choose>
				</display:column>
				
				
				<display:column  titleKey="作用对象" sortable="true" >
					<c:choose>
						<c:when test="${taskApprovalItems.type==1}">所有流程</c:when>
						<c:when test="${taskApprovalItems.type==2}">
							<c:forEach items="${defTypeMap}" var="globalType">
							   <c:if   test="${globalType.key==taskApprovalItems.typeId}">
          							${globalType.value}
      							</c:if> 
							</c:forEach>
						</c:when>
						<c:when test="${taskApprovalItems.type==3}">
							<c:forEach items="${defMap}" var="bpmdefinition">
								<c:if   test="${bpmdefinition.key==taskApprovalItems.defKey}">
          							${bpmdefinition.value}
      							</c:if> 
							</c:forEach>
						</c:when>
						<c:when test="${taskApprovalItems.type==4}">所有流程</c:when>
					</c:choose>
				</display:column>
				
				<display:column titleKey="管理" media="html" style="width:180px;line-height:21px;">
				<c:choose>
					<c:when test="${isAdmin==1}">
						<a href="del.ht?itemId=${taskApprovalItems.itemId}" class="link del">删除</a>
					</c:when>
					<c:otherwise>
						<c:if test="${taskApprovalItems.userId==currUserId}">
							<a href="del.ht?itemId=${taskApprovalItems.itemId}" class="link del">删除</a>
						</c:if>
					</c:otherwise>
				</c:choose>
					
				</display:column>
			</display:table>
			<hotent:paging tableId="taskApprovalItems"/>
	</div>
</div>
</body>
</html>
