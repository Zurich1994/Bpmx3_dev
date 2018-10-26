<%--
	time:2012-01-14 15:10:58
	desc:edit the 发送消息
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 发送消息</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=messageSend"></script>
	<script type="text/javascript"  src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_msg.js"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${messageSend.canReply==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.sendMessage").click(function() {
				 if($("#receiverName").val()==""&&$('#receiverOrgName').val()=="")
				 {
			        $.ligerDialog.warn('请选择收信人或收信组织！','提示信息');
			        return;
				 }
				$('#content').val(editor.getData());
				$('#messageSendForm').submit(); 
			});
			editor=ckeditor('content');
		});
		
		function dlgCallBack(userIds,fullnames,emails,mobiles,retypes)
		{								
			$("#receiverName").val(fullnames);	
			$("#receiverId").val(userIds);	
			$("#receiveType").val(retypes);	
			
		};
		function addClick(oName)
		{
			UserDialog({callback:dlgCallBack,isSingle:false});
		};
		//清空
		function reSet(obj)
		{
			$("#receiverName").val("");	
			$("#receiverId").val("");	
			$("#receiveType").val("");				
		}
		
		// 弹出组织框
		function showOrgDialog(){
			OrgDialog({callback:dlgOrgCallBack,isSingle:false});
		};
		
		// 组织框返回数据   
		function dlgOrgCallBack(orgIds, orgNames)
		{
			$("#receiverOrgName").val(orgNames);	
			$("#receiverOrgId").val(orgIds);	
			//$("#receiveType").val(retypes);	
		}
		
		// 清空所选组织
		function reSetOrg(obj)
		{
			$("#receiverOrgName").val("");	
			$("#receiverOrgId").val("");				
		}
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑发送消息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link sendMessage" id="dataFormSave" href="javascript:;"><span></span>发送</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht?userId=${userId}"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="messageSendForm" method="post" action="save.ht">
					
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">						
							
							<tr>
								<th width="20%">标题: </th>
								<td><input type="text" id="subject" name="subject" value="${messageSend.subject}"  class="inputText" style="width:308px !important;"/></td>
							</tr>
							<tr>
								<th width="20%">收信人: </th>
								<td>
								<input id="receiverName" name="receiverName"  size="80" readonly="readonly" value="${messageSend.receiverName}" />
								<a href="javascript:;" onclick="addClick()" class="link get">选择</a>
						        <a href="javascript:;" onclick="reSet()" class="link clean">清空</a>
								<input type="hidden" id="receiverId" name="receiverId" value=""  class="inputText"/>
								<input type="hidden" id="receiveType" name="receiveType" value=""  class="inputText"/>
								</td>
							</tr>
							<tr>
								<th width="20%">收信组织: </th>
								<td>
								<input id="receiverOrgName" name="receiverOrgName"  size="80" readonly="readonly" value="${messageSend.receiverOrgName}" />
								<a href="javascript:;" onclick="showOrgDialog()" class="link get">选择</a>
						        <a href="javascript:;" onclick="reSetOrg()" class="link clean">清空</a>
								<input type="hidden" id="receiverOrgId" name="receiverOrgId" value=""  class="inputText"/>
								</td>
							</tr>
							<tr>
								<th width="20%">需要回复: </th>
								<td>
								<input type="hidden" name="messageType" value="1" />
								<input type="radio" name="canReply" value="1" <c:if test='${messageSend.canReply==1||messageSend.canReply==null}'>checked</c:if>/>是
								<input type="radio" name="canReply" value="0" <c:if test='${messageSend.canReply==0}'>checked</c:if>/>否</td>
							</tr>		
							<tr>
								<th width="20%">内容: </th>
								<td>
								<textarea id="content" name="content" rows="10" cols="38">${messageSend.content}</textarea>								
								</td>
							</tr>
									
						</table>
				
					<input type="hidden" name="id" value="${messageSend.id}" />
				</form>
		</div>
</div>
</body>
</html>
