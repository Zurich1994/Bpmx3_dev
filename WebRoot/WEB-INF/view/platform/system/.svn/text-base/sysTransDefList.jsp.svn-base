<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>sys_trans_def管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">sys_trans_def管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a href="set.ht" class="link edit"><span></span>开始转移</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a href="${ctx}/platform/system/sysObjLog/list.ht?Q_objType_S=transDef&rtnUrl=${ctx}/platform/system/sysTransDef/list.ht" class="link detail"><span></span>日志</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}" />
						<span class="label">状态:</span>
						<select name="Q_state_S" value="${param['Q_state_S']}"  >
							<option value =""></option>
							<option value ="1" <c:if test="${param['Q_state_S']  == 1}">selected</c:if> >是</option>
					  		<option value ="0" <c:if test="${param['Q_state_S']  == 0}">selected</c:if> >否</option>
						</select>
						<span class="label">创建人:</span><input type="text" name="Q_creator_SL"  class="inputText" value="${param['Q_creator_SL']}" />
						<span class="label">创建时间 从:</span> <input  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}" />
						<span class="label">至: </span><input  name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"  />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysTransDefList" id="sysTransDefItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysTransDefItem.id}">
				</display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column title="状态" sortable="true" sortName="STATE">
					<c:choose>
						<c:when test="${sysTransDefItem.state==0}">
							<span class="red">否</span>
						</c:when>
						<c:otherwise>
							<span class="green">是</span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="creator" title="创建人" sortable="true" sortName="CREATOR"></display:column>
				<display:column title="创建时间" sortable="true" sortName="CREATETIME">
					<fmt:formatDate value="${sysTransDefItem.createTime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysTransDefItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysTransDefItem.id}" class="link edit">编辑</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysTransDefItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


