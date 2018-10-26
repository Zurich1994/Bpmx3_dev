<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程实例扩展管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
</head>
<body>
			<div class="panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">流程历史列表</span>
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
							<form id="searchForm" method="post" action="history.ht">
								<ul class="row">
								<li>
									<span class="label">流程定义名称:</span><input type="text" name="Q_processName_SL"  class="inputText" value="${param['Q_processName_SL']}"/>
								</li>
								<li>
									<span class="label">流程实例标题:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/>
								</li>
								<li>	
									<span class="label">创建人:</span><input type="text" name="Q_creator_SL"  class="inputText" value="${param['Q_creator_SL']}"/>
								</li>
								<div class="row_date">
								<li>
									<span class="label">创建时间 从:</span><input  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/>
								</li>
								<li>	
									<span class="label">至: </span><input  name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"/>
								</li>
								</div>
								</ul>
							</form>
					</div>		
				</div>
				<div class="panel-body">
						<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="processRunList" id="processRunItem" requestURI="history.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="runId" value="${processRunItem.runId}">
							</display:column>
							<display:column property="processName" title="流程定义名称" sortable="true" sortName="processName"></display:column>
							<display:column property="subject" title="流程实例标题" sortable="true" sortName="subject"></display:column>
							<display:column property="creator" title="创建人" sortable="true" sortName="creator"></display:column>
							<display:column  title="创建时间" sortable="true" sortName="createtime">
								<fmt:formatDate value="${processRunItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column  title="结束时间" sortable="true" sortName="endtime">
								<fmt:formatDate value="${processRunItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column title="持续时间" sortable="true" sortName="duration">
								${f:getTime(processRunItem.duration)}
							</display:column>
							<display:column title="状态" sortable="true" sortName="status">
								<c:choose>
									<c:when test="${processRunItem.status==1}">
										<span class='green'>正在运行</span>
									</c:when>
									<c:when test="${processRunItem.status==2}">
										<span class="red">结束</span>
									</c:when>
									<c:when test="${processRunItem.status==3}">
										<span class="red">手工结束</span>
									</c:when>
								</c:choose>
							</display:column>
							<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
								<f:a alias="delHistory" href="del.ht?runId=${processRunItem.runId}" css="link del">删除</f:a>
								<a  href="javascript:;" onclick="FlowDetailWindow({runId:${processRunItem.runId}});" class="link detail">明细</a>
								<a href="${ctx}/platform/bpm/bpmRunLog/list.ht?runId=${processRunItem.runId}" class="link log">操作日志</a>
								<a href="${ctx}/platform/bpm/bpmProCopyto/list.ht?runId=${processRunItem.runId}" class="link copyTo">抄送转发</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="processRunItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


