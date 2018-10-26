<%--
	time:2012-01-14 15:15:43
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>消息回复明细</title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">消息回复详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-detail">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">标题:</th>
					<td>${messageReply.messageId}</td>
				</tr>
				<tr>
					<th width="20%">内容:</th>
					<td>${messageReply.content}</td>
				</tr>
			</table>	
		</div>				
		<div class="panel-body">
			<div class="panel-data">
				<table id="dicTable" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
					   		<thead>
					    		<th style="width:18%">回复人</th>
					    		<th>回复内容</th>
					    		<th style="width:18%">回复时间</th>			    		
					    	</thead>					    	
					    	<tbody>
					    	<c:forEach items="${replyList}" var="replyItem">
					    		<tr class="${status.index%2==0?'odd':'even'}">					    								    								    								    								    			
					    			<td>${replyItem.reply }</td> 
					    			<td>${replyItem.content }</td>
					    			<td>${replyItem.replyTime }</td> 					    								    			
					    		</tr>
					    	</c:forEach>					    						    					    	
					    </tbody>					    	
			     </table>								
			</div>
		</div>
</div>

</body>
</html>
