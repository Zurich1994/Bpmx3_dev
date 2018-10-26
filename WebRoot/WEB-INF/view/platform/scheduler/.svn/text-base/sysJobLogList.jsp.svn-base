
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>任务执行日志管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">任务执行日志管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="delJobLog.ht"><span></span>删除</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link back" href="getJobList.ht"><span></span>返回</a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="getLogList.ht?jobName=${jobName }&trigName=${trigName}">
									<ul class="row">
									           <li> <span class="label">任务名称:</span><input type="text" name="Q_jobName_SL"  class="inputText" value="${param['Q_jobName_SL']}"/></li>
									            <li><span class="label">计划名称:</span><input type="text" name="Q_trigName_SL"  class="inputText" value="${param['Q_trigName_SL']}"/></li>
												<li><span class="label">时间从:</span> <input type="text" name="Q_startTime_DL"  class="inputText date" value="${param['Q_startTime_DL']}"/>
												<span class="label">至: </span><input type="text" name="Q_endTime_DG" class="inputText date" value="${param['Q_endTime_DG']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
						<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysJobLogList" id="sysJobLogItem" requestURI="getLogList.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="logId"  value="${sysJobLogItem.logId}">
							</display:column>
							<display:column property="jobName" title="任务名称" style="text-align:left" ></display:column>
							<display:column property="trigName" title="计划名称" style="text-align:left" ></display:column>
							<display:column  title="开始时间" sortable="true" sortName="startTime">
							<fmt:formatDate value="${sysJobLogItem.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>							
							<display:column  title="结束时间" sortable="true" sortName="endTime">
							<fmt:formatDate value="${sysJobLogItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column property="content" title="结果"  maxLength="80"></display:column>
							<display:column  title="状态" style="text-align:center">
							<c:if test="${sysJobLogItem.state eq 1 }"><span class="green">成功</span></c:if>
							<c:if test="${sysJobLogItem.state eq 0 }"><span class="red">失败</span></c:if>
							</display:column>
							<display:column property="runTime" title="执行时间(秒)" sortable="true" sortName="runTime" style="width:100px"></display:column>
							<display:column title="管理" media="html" style="width:80px;text-align:center">
							<a href="delJobLog.ht?logId=${sysJobLogItem.logId }" class="link del">删除</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysJobLogItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


