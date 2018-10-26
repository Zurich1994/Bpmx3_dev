
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>系统日志管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">系统日志管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
							<div class="l-bar-separator"></div>
					        <div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
										<li><span class="label">操作名称:</span><input type="text" name="Q_opName_SL"  class="inputText" value="${param['Q_opName_SL']}"/></li>
										<li><span class="label">执行人:</span><input type="text" name="Q_executor_SL"  class="inputText" value="${param['Q_executor_SL']}"/></li>
										<div class="row_date">
										<li><span class="label">执行时间 从:</span><input  name="Q_beginexeTime_DL"  class="inputText date" value="${param['Q_beginexeTime_DL']}"/></li>
										<li><span class="label">至: </span><input  name="Q_endexeTime_DG" class="inputText date" value="${param['Q_endexeTime_DG']}"/></li>
										</div>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysAuditList" id="sysAuditItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="auditId" value="${sysAuditItem.auditId}">
							</display:column>
							<display:column property="opName" title="操作名称" sortable="true" sortName="opName"></display:column>
							<display:column title="执行时间" sortable="true" sortName="exeTime">
								<fmt:formatDate value="${sysAuditItem.exeTime}" pattern="yyyy-MM-dd HH:mm"/>
							</display:column>
						
							<display:column property="executor" title="执行人" sortable="true" sortName="executor"></display:column>
							<display:column property="fromIp" title="IP" sortable="true" sortName="fromIp"></display:column>
							<%-- <display:column property="exeMethod" title="执行方法" sortable="true" sortName="exeMethod"></display:column>
							<display:column property="requestURI" title="请求URL" sortable="true" sortName="requestURI"></display:column> --%>
							<display:column property="ownermodel" title="归属模块" sortable="true" sortName="ownermodel"></display:column>
							<display:column property="exectype" title="日志类型" sortable="true" sortName="exectype"></display:column>
							<display:column title="管理" media="html" style="width:100px;text-align:center">
								<a href="del.ht?auditId=${sysAuditItem.auditId}" class="link del">删除</a>
								<a href="get.ht?auditId=${sysAuditItem.auditId}" class="link detail">明细</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysAuditItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


