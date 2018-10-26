
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<%@ page language="java" import="com.hotent.platform.model.system.MessageSend" %>
<html>
<head>
	<title>发送消息管理</title>
	<%@include file="/commons/include/get.jsp" %>
	<c:set var="MESSAGETYPE_PERSON" value="<%=MessageSend.MESSAGETYPE_PERSON %>"></c:set>
	<c:set var="MESSAGETYPE_SCHEDULE" value="<%=MessageSend.MESSAGETYPE_SCHEDULE %>"></c:set>
	<c:set var="MESSAGETYPE_PLAN" value="<%=MessageSend.MESSAGETYPE_PLAN %>"></c:set>
	<c:set var="MESSAGETYPE_AGENCY" value="<%=MessageSend.MESSAGETYPE_AGENCY %>"></c:set>
	<c:set var="MESSAGETYPE_FLOWTASK" value="<%=MessageSend.MESSAGETYPE_FLOWTASK %>"></c:set>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">发送消息管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link  sendMessage" href="edit.ht"><span></span>发送消息</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
							<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label">标题:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/>	</li>																					 																					
												<div class="row_date">
												<li><span class="label">发送时间:</span><input  name="Q_beginsendTime_DL"  class="inputText datetime" value="${param['Q_beginsendTime_DL']}"/></li>
												<li><span class="label">至: </span><input  name="Q_endsendTime_DG" class="inputText datetime" value="${param['Q_endsendTime_DG']}"/></li>
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
				    <display:table name="messageSendList" id="messageSendItem" requestURI="list.ht" 
				    	sort="external" cellpadding="1" cellspacing="1" export="false" class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;">
							  	<input type="checkbox" class="pk" name="id" value="${messageSendItem.id}">
						</display:column>
						<display:column property="subject" title="标题" sortable="true" sortName="subject"></display:column>
						<display:column title="消息类型" sortable="true" sortName="messageType" style="text-align:center;">
						<c:choose>
							<c:when test="${messageSendItem.messageType==MESSAGETYPE_PERSON}">
							       个人信息
						   	</c:when>
						   	<c:when test="${messageSendItem.messageType==MESSAGETYPE_SCHEDULE}">
							        日程安排
						   	</c:when>
						   	<c:when test="${messageSendItem.messageType==MESSAGETYPE_PLAN}">
							       计划任务
						   	</c:when>
					       	<c:when test="${messageSendItem.messageType==MESSAGETYPE_AGENCY}">
						                   代办提醒     
						   	</c:when>
						   	<c:when test="${messageSendItem.messageType==MESSAGETYPE_FLOWTASK}">
						                  流程提醒             
						   	</c:when>
					    </c:choose>
						</display:column>							
						<display:column  title="发送时间" sortable="true" sortName="sendTime" style="text-align:center;">
							<fmt:formatDate value="${messageSendItem.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</display:column>
						<display:column title="收信人" sortable="false" style="text-align:center;">
						<c:choose>
							<c:when test="${fn:length(messageSendItem.receiverName)>20}">
							      ${fn:substring(messageSendItem.receiverName,0,20)}....
						   	</c:when>
					       	<c:otherwise>
						          ${messageSendItem.receiverName}    
						   	</c:otherwise>
					    </c:choose>
						</display:column>
						<display:column title="管理" media="html" style="width:180px;text-align:center">
							<a href="del.ht?id=${messageSendItem.id}" class="link del">删除</a>
							<a href="edit.ht?id=${messageSendItem.id}" class="link edit" style='${longTime-messageSendItem.sendTime.time > spanTime ?'display:none':'display:'}'>编辑</a>
							<a href="get.ht?id=${messageSendItem.id}" class="link detail">明细</a>
						</display:column>
					</display:table>
					<hotent:paging tableId="messageSendItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


