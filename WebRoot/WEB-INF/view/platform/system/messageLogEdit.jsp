<%--
	time:2012-11-29 16:24:35
	desc:edit the 发送消息日志
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 消息日志</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#messageLogForm').form();
			$("a.save").click(function() {
				frm.setData();
				frm.ajaxForm(options);
				if(frm.valid()){
					form.submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/platform/system/messageLog/list.ht";
					}
				});
			} else {
				$.ligerDialog.err('出错信息',"编辑消息日志失败",obj.getMessage());
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${messageLog.id !=null}">
			        <span class="tbar-label">编辑发送消息日志</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加发送消息日志</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="messageLogForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">主题: </th>
					<td><input type="text" id="subject" name="subject" value="${messageLog.subject}"  class="inputText" validate="{required:false,maxlength:100}"  /></td>
				</tr>
				<tr>
					<th width="20%">发送时间: </th>
					<td><input type="text" id="sendTime" name="sendTime" value="<fmt:formatDate value='${messageLog.sendTime}' pattern='yyyy-MM-dd'/>" class="inputText date" validate="{date:true}" /></td>
				</tr>
				<tr>
					<th width="20%">接收者: </th>
					<td><input type="text" id="receiver" name="receiver" value="${messageLog.receiver}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">消息类型
1;邮件信息
2;手机短信
3;内部消息: </th>
					<td><input type="text" id="messageType" name="messageType" value="${messageLog.messageType}"  class="inputText" validate="{required:false,maxlength:22,number:true }"  /></td>
				</tr>
				<tr>
					<th width="20%">发送状态
1:成功
0:不成功: </th>
					<td><input type="text" id="state" name="state" value="${messageLog.state}"  class="inputText" validate="{required:false,maxlength:22,number:true }"  /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${messageLog.id}" />					
		</form>
		
	</div>
</div>
</body>
</html>
