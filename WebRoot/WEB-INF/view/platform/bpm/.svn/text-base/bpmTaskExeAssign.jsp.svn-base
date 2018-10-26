<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title>流程交办</title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			$("#bpmTaskExeForm").ajaxForm(options);
			
			$("a.save").click(delegate);
		});
		
		var objSave=null;
		
		function delegate(){
			objSave=$(this);	
			if(objSave.hasClass('disabled')){
				return;
			}
			objSave.addClass("disabled");
			var frm=$('#bpmTaskExeForm').form();
			if(!frm.valid()){
				objSave.removeClass("disabled");
				return;
			} 
			$.ligerDialog.confirm('确认转办操作吗?','提示',function(rtn){
				if(!rtn){
					objSave.removeClass("disabled");
					return;
				}
				$("#bpmTaskExeForm").submit();
			});
		}
			
		function showResponse(responseText) {
			objSave.removeClass("disabled");
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {//成功
				 $.ligerDialog.success(obj.getMessage(),'提示',function(){
					 //window.returnValue=1;
					 dialog.get('sucCall')(1);
					 dialog.close();
					 window.close();
				 });
			} else {
				$.ligerDialog.error(obj.getMessage(),'提示',function(rtn){
					window.parent.close();
				});
			}
		}
	
		function chooseUser() {
			UserDialog({isSingle:true,
				callback:function(userIds, fullnames){
					$("#assigneeId").val(userIds);
					$("#assigneeName").val(fullnames);
					$('#bpmTaskExeForm').form().valid();
				}
			});
		};

	</script>
</head>
<body style="overflow-x:hidden;">
<div class="panel">
<div class="hide-panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
				流程交办
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>分配任务</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close()" ><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	</div>
	<div class="panel-body">
		<form id="bpmTaskExeForm" method="post" action="assignSave.ht">
			<input type="hidden" name="taskId" value="${taskId}">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="120px">接收人:</th>
					<td>
						<input type="hidden" id="assigneeId" name="assigneeId" >
						<input type="text"  id="assigneeName" name="assigneeName" readonly="readonly" style="width:200px;" validate="{required:true}"  >
						<a href="javascript:;" class="button"  onclick="chooseUser()"><span class="icon ok" ></span><span class="chosen" >选择</span></a>
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">提醒消息方式: </th>
					<td>
						<%--这里消息发送方式固定死了，改成：消息方式从app-jms.xml中的handlersMap中获取，名称从具体的hander中获取，如mailMessageHandler.getTitle()
						<label><input type="checkbox" name="informtype" value="3"  checked="checked"/>站内消息</label>
						<label><input type="checkbox" name="informtype" value="1"  checked="checked"/>邮件</label>
						<label><input type="checkbox" name="informtype" value="2" />手机短信</label>
					 --%>	
							<c:forEach items="${handlersMap}" var="item">
							   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
					            ${item.value.title }
							</c:forEach>
					</td>
				</tr>
				<tr>
					<th>转办原因: </th>
					<td>
						<textarea rows="5" cols="50" name="memo"  validate="{required:true}" id="memo" ></textarea>
					</td>
				</tr>
				
				
			</table>
		</form>
	</div>
</div>
</body>
</html>