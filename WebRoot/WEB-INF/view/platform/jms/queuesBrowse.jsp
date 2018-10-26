<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>浏览队列</title>
<%@include file="/commons/include/get.jsp" %>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">浏览队列</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>刷新</a></div>
					<div class="l-bar-separator"></div>
			        <div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			<form id="searchForm" method="post" action="browse.ht">
				<input type="hidden" name="JMSDestination" value="${JMSDestination}">	
			</form>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="browseList" id="item" requestURI="browse.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="name" value="${item.JMSMessageID}">
				</display:column>
				<display:column property="JMSMessageID" title="消息ID"  href="message.ht?JMSDestination=${JMSDestination}&"  paramId="id" paramProperty="JMSMessageID"  style="width:280px;"  maxLength="50">
				</display:column>
				<display:column property="JMSCorrelationID" title="相关性 ID"></display:column>
				<display:column  title="是否持久化">
				<jms:persistent message="${item}" />
				</display:column>
				<display:column property="JMSPriority" title="优先级"></display:column>
				<display:column property="JMSRedelivered" title="重复发送"></display:column>
				<display:column property="JMSReplyTo" title="回复" ></display:column>
				<display:column title="时间"  >
				<jms:formatTimestamp timestamp="${item.JMSTimestamp}" />
				</display:column>
			</display:table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


