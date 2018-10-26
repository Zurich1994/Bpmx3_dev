<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>queques</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">队列</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>刷新</a></div>
				</div>	
			</div>
			<form id="searchForm" method="post" action="list.ht">
			</form>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="queuesList" id="queuesItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column property="name" title="名称"  href="browse.ht"  paramId="JMSDestination" paramProperty="name"  style="width:155px;"  maxLength="15"></display:column>
				<display:column property="queueSize" title="待处理的消息数"></display:column>
				<display:column property="consumerCount" title="消费者数量"></display:column>
				<display:column property="enqueueCount" title="入队消息"></display:column>
				<display:column property="dequeueCount" title="出队消息"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="purge.ht?JMSDestination=${queuesItem.name}" class="link edit">清空</a>
				</display:column>
			</display:table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


