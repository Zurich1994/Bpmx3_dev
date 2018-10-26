
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>加班请假管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">加班请假管理列表</span>
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
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label">标题:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
					
						<li><span class="label">用户名称:</span><input type="text" name="Q_userName_SL"  class="inputText" value="${param['Q_userName_SL']}"/></li>
						
						<div class="row_date">	
						<li><span class="label">加班时间:</span><input name="Q_startTime_DL"  class="inputText datetime" value="${param['Q_startTime_DL']}"/></li>
							
						<li><span class="label">到</span><input name="Q_endTime_DG"  class="inputText datetime" value="${param['Q_endTime_DG']}"/></li>
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
			    <display:table name="overTimeList" id="overTimeItem" requestURI="list.ht" 
			    	sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						<input type="checkbox" class="pk" name="id" value="${overTimeItem.id}">
					</display:column>
					<display:column title="标题" sortable="true" sortName="subject" property="subject" />
					<display:column title="用户名称" sortable="true" sortName="userId">
						<c:out value="${overTimeItem.userName}"></c:out>
					</display:column>
					<display:column title="工作日变更类型" sortable="true" sortName="workType">
						<c:if test="${overTimeItem.workType==1}">
							加班
						</c:if>
						<c:if test="${overTimeItem.workType==2}">
							请假
						</c:if>
					</display:column>
					<display:column  title="开始时间" sortable="true" sortName="startTime">
						<fmt:formatDate value="${overTimeItem.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column  title="结束时间" sortable="true" sortName="endTime">
						<fmt:formatDate value="${overTimeItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column title="管理" media="html" style="width:180px;text-align:center" >
						<a href="del.ht?id=${overTimeItem.id}" class="link del">删除</a>
						<a href="edit.ht?id=${overTimeItem.id}" class="link edit">编辑</a>
						<a href="get.ht?id=${overTimeItem.id}" class="link detail">明细</a>
					</display:column>
				</display:table>
				<hotent:paging tableId="overTimeItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


