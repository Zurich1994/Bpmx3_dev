<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>消息日志管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">消息日志管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<!--
					<div class="group"><a class="link add" href="edit.ht">添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht">修改</a></div>
					-->
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label">主题:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
						<li><span class="label">接收者:</span><input type="text" name="Q_receiver_SL"  class="inputText" value="${param['Q_receiver_SL']}"/></li>
						<li><span class="label">消息类型:</span>			
						<select name="Q_messageType_SN" value="${param['Q_messageType_SN']}">
							<option value="">请选择</option>
							<option value="1" <c:if test="${param['Q_messageType_SN'] == 1}">selected</c:if>>邮件信息</option>
							<option value="2" <c:if test="${param['Q_messageType_SN'] == 2}">selected</c:if>>手机短信</option>
							<option value="3" <c:if test="${param['Q_messageType_SN'] == 3}">selected</c:if>>内部消息</option>
						</select>
						</li>
						<li>
						<span class="label">发送状态:</span>
						<select name="Q_state_SN" value="${param['Q_state_SN']}">
							<option value="">请选择</option>
							<option value="1" <c:if test="${param['Q_state_SN'] == 1}">selected</c:if>>成功</option>
							<option value="0" <c:if test="${param['Q_state_SN'] == 0}">selected</c:if>>失败</option>
						</select></li>
						<div class="row_date">
							<li><span class="label">发送时间 从:</span><input  name="Q_beginSendTime_DL"  class="inputText date" value="${param['Q_beginSendTime_DL']}"/></li>
							<li><span class="label">至: </span><input  name="Q_endSendTime_DG" class="inputText date" value="${param['Q_endSendTime_DG']}"/></li>
						</div>
					</ul>
				</form>
			</div>
			</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="messageLogList" id="messageLogItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${messageLogItem.id}">
				</display:column>
				<display:column property="subject" title="主题" sortable="true" sortName="subject"></display:column>
				<display:column  title="发送时间" sortable="true" sortName="sendTime">
					<fmt:formatDate value="${messageLogItem.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				<display:column property="receiver" title="接收者" sortable="true" sortName="receiver" maxLength="80"></display:column>
				<display:column title="消息类型" sortable="true" sortName="messageType">
					<c:choose>
						<c:when test="${messageLogItem.messageType==1 }">邮件信息</c:when>
						<c:when test="${messageLogItem.messageType==2 }">手机短信</c:when>
						<c:when test="${messageLogItem.messageType==3 }">内部消息</c:when>
						<c:otherwise>
							未知类型
						</c:otherwise>
					</c:choose>
				</display:column>
				
				<display:column title="发送状态" sortable="true" sortName="state">
						<c:choose>
							<c:when test="${messageLogItem.state==1 }"><font style="color: green;">成功</font></c:when>
							<c:otherwise>
								<font style="color: red;">失败</font>
							</c:otherwise>
						</c:choose>
				</display:column>
				
				<display:column title="管理" media="html" style="width:180px;text-align:center">
					<a href="del.ht?id=${messageLogItem.id}" class="link del">删除</a>
					<!--
					<a href="edit.ht?id=${messageLogItem.id}" class="link edit">编辑</a>
					-->
					<a href="get.ht?id=${messageLogItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="messageLogItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


