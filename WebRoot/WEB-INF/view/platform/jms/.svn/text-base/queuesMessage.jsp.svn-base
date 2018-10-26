<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
<html>
<head>
<title>消息详情</title>
<%@include file="/commons/include/form.jsp" %>
	<%-- <link href="${ctx}/styles/ligerUI/ligerui-all.css" rel="stylesheet" type="text/css" /> --%>
	<f:link href="Aqua/css/ligerui-all.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/calendar/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
<c:set var="row" value="${messageQuery.message}"/>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">消息详情</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="browse.ht">
					
				</form>
			</div>
		</div>
		
		
	<div class="panel-body">
		<form id="sysRoleForm" method="post" action="save.ht">
<c:choose>
	<c:when test="${empty row}">
		<div>
		No message could be found for ID <c:out value="${messageQuery.id}"/>
		</div>
	</c:when>

<c:otherwise>
	
	            <table id="body3" class="table-detail" cellpadding="0" cellspacing="0" border="0">
	                    <tr>
	                        <th  title="Unique Message ID for this message">消息ID</th>
	                        <td><c:out value="${row.JMSMessageID}"/></td>
	                    </tr>
	                    <tr>
	                        <th >目的地</th>
	                        <td>
	                        <!--form:tooltip text="${row.JMSDestination}" length="50"/ -->
	                         <c:out value="${row.JMSDestination}" />
	                        </td>
	                    </tr>
	                    <tr>
	                        <th  title="The ID used to correlate messages together in a conversation">相关性ID</th>
	                        <td><c:out value="${row.JMSCorrelationID}"/></td>
	                    </tr>
	                    <tr>
	                        <th title="Message Group Identifier">消息组标志</th>
	                        <td><c:out value="${row.groupID}"/></td>
	                    </tr>
	                    <tr>
	                        <th title="Message Group Sequence Number">消息组序列号</th>
	                        <td><c:out value="${row.groupSequence}"/></td>
	                    </tr>
	                    <tr>
	                        <th>消息失效时间</th>
	                        <td><c:out value="${row.JMSExpiration}"/></td>
	                    </tr>
	                    <tr>
	                        <th>持久化</th>
	                        <td><jms:persistent message="${row}"/></td>
	                    </tr>
	                    <tr>
	                        <th title= "0的优先级最低,9最高">优先级</th>
	                        <td><c:out value="${row.JMSPriority}"/></td>
	                    </tr>
	                    <tr>
	                        <th title="带有该字段的消息通常过去发送过但是没有被确认，如果要再次发送，提供者必须设置该字段。如果true，则消息接受者必须进行消息重复处理的逻辑。">重复发送</th>
	                        <td><c:out value="${row.JMSRedelivered}"/></td>
	                    </tr>
	                    <tr>
	                        <th title="带有这样属性的消息通常是发送方希望有一个响应，这个响应是可选的。">回复消息的目的地</th>
	                        <td><c:out value="${row.JMSReplyTo}"/></td>
	                    </tr>
	                    <tr>
	                        <th>时间</th>
	                        <td><jms:formatTimestamp timestamp="${row.JMSTimestamp}"/></td>
	                    </tr>
	                    <tr>
	                        <th>类型</th>
	                        <td><c:out value="${row.JMSType}"/></td>
	                    </tr>
	            </table>
	    
	     <%--
	        <td  class="layout" valign="top">
	            <table id="properties" class="sortable autostripe">
	                <thead>
	                    <tr>
	                        <th colspan="2">
	                            Properties
	                        </th>
	                    </tr>
	                </thead>
	                <tbody>
	                   <form:forEachMapEntry items="${messageQuery.propertiesMap}" var="prop">
	                        <tr>
	                            <th><c:out value="${prop.key}"/></td>
	                            <td><c:out value="${prop.value}"/></td>
	                        </tr>
	                        <tr>
	                    </form:forEachMapEntry>
	                </tbody>
	            </table>
	        </td>
	         --%>
	         
	         
	    <%--
	            <table id="body1" width="100%" class="table-detail" cellpadding="0" cellspacing="0" border="0">
	                <thead>
	                    <tr>
	                        <th colspan="2"  style="text-align:left">
	                            消息操作
	                        </th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                        <td colspan="2"><a href="deleteMessage.action?JMSDestination=<c:out value="${row.JMSDestination}" />&messageId=${row.JMSMessageID}&secret=<c:out value='${sessionScope["secret"]}'/>">Delete</a></td>
	                    </tr>
	                    <tr class="odd">
	                    <td><a href="javascript:confirmAction('queue', 'copyMessage.action?destination=%target%&JMSDestination=<c:out value="${row.JMSDestination}" />&messageId=${row.JMSMessageID}&JMSDestinationType=queue&secret=<c:out value='${sessionScope["secret"]}'/>')">Copy</a></td>
	                        <td rowspan="2">
	                            <select id="queue">
	                                <option value=""> -- Please select --</option>
	                                <c:forEach items="${requestContext.brokerQuery.queues}" var="queues">
	                                    <c:if test="${queues.name != requestContext.messageQuery.JMSDestination}">
	                                    <option value="<c:out value="${queues.name}" />"><form:short text="${queues.name}" length="80"/></option>
	                                    </c:if>
	                                </c:forEach>
	                            </select>
	                        </td>
	
	                    </tr>
	                    <tr class="odd">
	                        <td><a href="javascript:confirmAction('queue', 'moveMessage.action?destination=%target%&JMSDestination=<c:out value="${row.JMSDestination}" />&messageId=${row.JMSMessageID}&JMSDestinationType=queue&secret=<c:out value='${sessionScope["secret"]}'/>')">Move</a></td>
	                    </tr>
	                </tbody>
	            </table>
	
	     --%>
	 
	            <table id="body2" width="100%" class="table-detail" cellpadding="0" cellspacing="0" border="0">
	                <thead>
	                    <tr>
	                        <th style="text-align:left">
	                            消息详情
	                        </th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                        <td><div class="message"><pre class="prettyprint"><c:out value="${messageQuery.body}"/></pre></div></td>
	                    </tr>
	                </tbody>
	            </table>
	  
	</c:otherwise>
</c:choose>
     </form>
	</div>
</div>
</body>
</html>


