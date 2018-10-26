
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>消息设置管理</title>
	<%@include file="/commons/include/get.jsp" %>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">消息设置管理列表</span>
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
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label">主题:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
											
												<li><span class="label">收件人:</span><input type="text" name="Q_receiver_SL"  class="inputText" value="${param['Q_receiver_SL']}"/></li>
											
												<li><span class="label">抄送:</span><input type="text" name="Q_copyTo_SL"  class="inputText" value="${param['Q_copyTo_SL']}"/></li>
											
												<li><span class="label">秘密抄送:</span><input type="text" name="Q_bcc_SL"  class="inputText" value="${param['Q_bcc_SL']}"/></li>
											
												<li><span class="label">发件人:</span><input type="text" name="Q_fromUser_SL"  class="inputText" value="${param['Q_fromUser_SL']}"/></li>
											
												<li><span class="label">内容模版:</span><input type="text" name="Q_templateId_S"  class="inputText" value="${param['Q_templateId_S']}"/></li>
											
												<li><span class="label">消息类型:</span><input type="text" name="Q_messageType_S"  class="inputText" value="${param['Q_messageType_S']}"/></li>
											
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="messageList" id="messageItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="messageId" value="${messageItem.messageId}">
							</display:column>
							<display:column property="subject" title="主题" sortable="true" sortName="subject"></display:column>
								<display:column property="receiver" title="收件人" sortable="true" sortName="receiver" maxLength="80"></display:column>
								<display:column property="copyTo" title="抄送" sortable="true" sortName="copyTo" maxLength="80"></display:column>
								<display:column property="bcc" title="秘密抄送" sortable="true" sortName="bcc" maxLength="80"></display:column>
							<display:column property="fromUser" title="发件人" sortable="true" sortName="fromUser"></display:column>
							<display:column property="templateId" title="内容模版" sortable="true" sortName="templateId"></display:column>
							<display:column property="messageType" title="消息类型" sortable="true" sortName="messageType"></display:column>
							<display:column title="管理" media="html" style="width:180px;text-align:center">
								<a href="del.ht?messageId=${messageItem.messageId}" class="link del">删除</a>
								<a href="edit.ht?messageId=${messageItem.messageId}" class="link edit">编辑<</a>
								<a href="get.ht?messageId=${messageItem.messageId}" class="link detail">明细</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="messageItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


